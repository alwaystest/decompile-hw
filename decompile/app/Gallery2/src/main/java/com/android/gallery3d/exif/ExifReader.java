package com.android.gallery3d.exif;

import com.android.gallery3d.util.GalleryLog;
import java.io.IOException;
import java.io.InputStream;

class ExifReader {
    private final ExifInterface mInterface;

    ExifReader(ExifInterface iRef) {
        this.mInterface = iRef;
    }

    protected ExifData read(InputStream inputStream) throws ExifInvalidFormatException, IOException {
        ExifParser parser = ExifParser.parse(inputStream, this.mInterface);
        ExifData exifData = new ExifData(parser.getByteOrder());
        for (int event = parser.next(); event != 5; event = parser.next()) {
            ExifTag tag;
            byte[] buf;
            switch (event) {
                case 0:
                    exifData.addIfdData(new IfdData(parser.getCurrentIfd()));
                    break;
                case 1:
                    tag = parser.getTag();
                    if (!tag.hasValue()) {
                        parser.registerForTagValue(tag);
                        break;
                    }
                    exifData.getIfdData(tag.getIfd()).setTag(tag);
                    break;
                case 2:
                    tag = parser.getTag();
                    if (tag.getDataType() == (short) 7) {
                        parser.readFullTagValue(tag);
                    }
                    exifData.getIfdData(tag.getIfd()).setTag(tag);
                    break;
                case 3:
                    try {
                        buf = new byte[parser.getCompressedImageSize()];
                        if (buf.length != parser.read(buf)) {
                            GalleryLog.w("ExifReader", "Failed to read the compressed thumbnail");
                            break;
                        }
                        exifData.setCompressedThumbnail(buf);
                        break;
                    } catch (Throwable e) {
                        GalleryLog.w("ExifReader", "Failed to read the compressed thumbnail." + e.getMessage());
                        break;
                    }
                case 4:
                    try {
                        buf = new byte[parser.getStripSize()];
                        if (buf.length != parser.read(buf)) {
                            GalleryLog.w("ExifReader", "Failed to read the strip bytes");
                            break;
                        }
                        exifData.setStripBytes(parser.getStripIndex(), buf);
                        break;
                    } catch (Throwable e2) {
                        GalleryLog.w("ExifReader", "Failed to read the strip bytes." + e2.getMessage());
                        break;
                    }
                default:
                    break;
            }
        }
        return exifData;
    }
}
