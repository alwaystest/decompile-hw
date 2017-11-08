package com.fyusion.sdk.common.ext;

import android.util.Log;
import com.fyusion.sdk.common.ext.ProcessItem.ProcessState;
import com.fyusion.sdk.common.ext.util.exif.Exif;
import com.fyusion.sdk.common.ext.util.exif.ExifInterface;
import com.fyusion.sdk.common.ext.util.exif.ExifTag;
import com.fyusion.sdk.common.h;
import com.fyusion.sdk.common.j;
import com.fyusion.sdk.common.j.b;
import com.fyusion.sdk.common.util.a;
import fyusion.vislib.FyuseContainerType;
import fyusion.vislib.FyuseContainerUtils;
import fyusion.vislib.Platform;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

/* compiled from: Unknown */
public class g {
    private static long a = 60000;

    static {
        System.loadLibrary("vislib_jni");
    }

    public static int a(File file) {
        try {
            if (!file.exists()) {
                h.c("FyuseContainerHelper", "Container does not exist!");
                return -10;
            } else if (c(file) != FyuseContainerType.PROCESSED) {
                return -12;
            } else {
                int imageDataOffset = FyuseContainerUtils.getImageDataOffset(file.getPath(), Platform.Android);
                h.a("FyuseContainerHelper", "Unpack | Result: " + imageDataOffset);
                return imageDataOffset >= 0 ? imageDataOffset : -11;
            }
        } catch (Exception e) {
            Log.e("FyuseContainerHelper", "Exception when unpacking fyuse container! Bailing. " + e.toString());
            e.printStackTrace();
            return -13;
        }
    }

    public static void a(File file, File file2, FyuseContainerType fyuseContainerType, boolean z) {
        if (file.exists()) {
            try {
                d(new File(file, k.aj));
            } catch (IOException e) {
                h.d("FyuseContainerHelper", "Error: " + e.getMessage());
            }
            try {
                d(new File(file, k.ab));
            } catch (IOException e2) {
                h.d("FyuseContainerHelper", "Error: " + e2.getMessage());
            }
            if (fyuseContainerType == FyuseContainerType.PROCESSED && !z) {
                g(file.getPath());
            }
            boolean composeFromDir = FyuseContainerUtils.composeFromDir(file.getPath(), file2.getPath(), Platform.Android, fyuseContainerType);
            if (composeFromDir) {
                f(file.getPath());
            } else {
                e(file.getPath());
            }
            File file3 = new File(file, k.ag);
            if (file3.exists()) {
                file3.delete();
            }
            h.a("FyuseContainerHelper", "Pack | Result: " + composeFromDir);
            return;
        }
        h.d("FyuseContainerHelper", "Tried to save container in nonexistent directory: " + file);
    }

    public static void a(String str) {
        Object obj = 1;
        File file = new File(str + File.separator + ".packing_flag");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Log.e("FyuseContainerHelper", "Error touching " + file);
                obj = null;
            }
        }
        if (obj != null) {
            file.setLastModified(System.currentTimeMillis());
        }
    }

    static boolean a(File file, File file2) {
        if (!c(file, file2)) {
            return false;
        }
        boolean z;
        if (c(file2.getPath())) {
            z = true;
        } else {
            a(file2.getPath());
            z = !f(file, file2);
            b(file2.getPath());
        }
        if (!z) {
            return true;
        }
        h.c("FyuseContainerHelper", "Failed to unpack fyuse container! Bailing.");
        return false;
    }

    private static boolean a(String str, String str2) {
        File file = new File(str + File.separator + str2);
        return file.exists() && file.length() != 0;
    }

    public static int b(File file, File file2) {
        if (!d(file, file2)) {
            return -2;
        }
        int i;
        if (c(file2.getPath())) {
            i = -1;
        } else {
            a(file2.getPath());
            i = e(file, file2);
            b(file2.getPath());
        }
        if (i >= 0) {
            return i;
        }
        h.d("FyuseContainerHelper", "Failed to unpack fyuse container! Bailing.");
        return i;
    }

    public static void b(String str) {
        File file = new File(str + File.separator + ".packing_flag");
        if (file.exists()) {
            file.delete();
        }
    }

    public static boolean b(File file) {
        if (!file.exists()) {
            return false;
        }
        try {
            if (FyuseContainerType.swigToEnum(FyuseContainerUtils.getContainerType(file.getPath(), Platform.Android)) != null) {
                return true;
            }
        } catch (IllegalArgumentException e) {
        }
        return false;
    }

    public static FyuseContainerType c(File file) throws IOException {
        if (file.exists()) {
            try {
                return FyuseContainerType.swigToEnum(FyuseContainerUtils.getContainerType(file.getPath(), Platform.Android));
            } catch (IllegalArgumentException e) {
                Log.e("FyuseContainerHelper", "Failed to convert swig to enum in FyuseContainerType!");
                return null;
            }
        }
        throw new IOException("File not found: " + file.getPath());
    }

    private static boolean c(File file, File file2) {
        File file3 = new File(file2, ".unpack_flag");
        if (!file.exists() || !file3.exists()) {
            return true;
        }
        return (file.lastModified() > file3.lastModified() ? 1 : (file.lastModified() == file3.lastModified() ? 0 : -1)) > 0;
    }

    public static boolean c(String str) {
        File file = new File(str + File.separator + ".packing_flag");
        if (!file.exists()) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long lastModified = file.lastModified();
        if (currentTimeMillis - lastModified <= a) {
            return true;
        }
        Log.e("FyuseContainerHelper", "Pack flag is " + (currentTimeMillis - lastModified) + " ms old, removing");
        file.delete();
        return false;
    }

    public static void d(File file) throws IOException {
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile == null || parentFile.mkdirs() || parentFile.isDirectory()) {
                new FileOutputStream(file).close();
            } else {
                throw new IOException("Directory '" + parentFile + "' could not be created");
            }
        } else if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        } else if (!file.canWrite()) {
            throw new IOException("File '" + file + "' cannot be written to");
        }
        if (!file.setLastModified(System.currentTimeMillis())) {
            throw new IOException("Unable to set the last modification time for " + file);
        }
    }

    private static boolean d(File file, File file2) {
        File file3 = new File(file2, k.ae);
        File file4 = new File(file2, ".unpack_flag");
        if (!file.exists() || !file3.exists() || !file4.exists()) {
            return true;
        }
        return (file.lastModified() > file4.lastModified() ? 1 : (file.lastModified() == file4.lastModified() ? 0 : -1)) > 0;
    }

    public static boolean d(String str) {
        return FyuseContainerUtils.isTagged(str);
    }

    private static int e(File file, File file2) {
        try {
            if (file.exists()) {
                int decomposeMetadataToDir = FyuseContainerUtils.decomposeMetadataToDir(file.getPath(), file2.getPath(), Platform.Android);
                h.a("FyuseContainerHelper", "Unpack | Result: " + decomposeMetadataToDir);
                if (decomposeMetadataToDir < 0) {
                    return -11;
                }
                if (a(file2.getPath(), k.ae)) {
                    return decomposeMetadataToDir;
                }
                Log.e("FyuseContainerHelper", "Empty magic!");
                return -12;
            }
            Log.e("FyuseContainerHelper", "Container does not exist!");
            return -10;
        } catch (Exception e) {
            Log.e("FyuseContainerHelper", "Exception when unpacking fyuse container! Bailing. " + e.toString());
            e.printStackTrace();
            return -13;
        }
    }

    public static List<ExifTag> e(File file) {
        Exif exif = new Exif();
        try {
            ExifInterface exifInterface = new ExifInterface();
            exifInterface.readExif(file.getAbsolutePath());
            return exifInterface.getAllTags();
        } catch (IOException e) {
            return null;
        }
    }

    private static void e(String str) {
        Object obj = 1;
        File file = new File(str + File.separator + ".unpack_flag");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Log.e("FyuseContainerHelper", "Error touching " + file);
                obj = null;
            }
        }
        if (obj != null) {
            file.setLastModified(System.currentTimeMillis());
        }
    }

    private static void f(String str) {
        File file = new File(str);
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File name : listFiles) {
                String name2 = name.getName();
                File file2 = new File(file, name2);
                if (file2.canRead() && file2.canWrite()) {
                    if (name2.equals(k.ai) || name2.equals(k.aj) || name2.equals(k.ah) || name2.equals(k.ao) || name2.equals(k.ar) || name2.equals(k.as) || name2.equals(k.ae) || name2.equals(k.ag) || name2.equals(k.ab) || name2.endsWith(k.at) || name2.equals(k.an)) {
                        file2.delete();
                    }
                    if (name2.equals(k.al)) {
                        a.a(file2);
                    }
                }
            }
        }
    }

    public static boolean f(File file) {
        boolean z = false;
        try {
            if (new m(h.a(), file).g().getLevel() >= ProcessState.READY_FOR_VIEW.getLevel()) {
                z = true;
            }
            return z;
        } catch (IOException e) {
            h.c("FyuseContainerHelper", "IOException: " + e.getMessage());
            return false;
        }
    }

    private static boolean f(File file, File file2) {
        try {
            if (file.exists()) {
                return FyuseContainerUtils.decomposeToDir(file.getPath(), file2.getPath(), Platform.Android);
            }
            Log.e("FyuseContainerHelper", "Container does not exist!");
            return false;
        } catch (Exception e) {
            Log.e("FyuseContainerHelper", "Exception when unpacking fyuse container! Bailing. " + e.toString());
            e.printStackTrace();
            return false;
        }
    }

    private static void g(String str) {
        int i = 0;
        try {
            File file = new File(str + File.separator + k.ag);
            if (file.exists()) {
                file.delete();
            }
            j anonymousClass1 = new j(new File(str)) {
                protected String j() {
                    return k.ag;
                }
            };
            if (anonymousClass1.a(j.a.READ_WRITE, b.TRUNCATE)) {
                File file2 = new File(str + File.separator + String.format(Locale.US, k.aH, new Object[]{Integer.valueOf(0)}));
                byte[] bArr = new byte[262144];
                while (file2.exists()) {
                    try {
                        if (file2.exists()) {
                            InputStream fileInputStream = new FileInputStream(file2);
                            FileOutputStream a = anonymousClass1.a(i);
                            while (true) {
                                int read = fileInputStream.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                a.write(bArr, 0, read);
                            }
                            anonymousClass1.a(a);
                            fileInputStream.close();
                        }
                        StringBuilder append = new StringBuilder().append(str).append(File.separator);
                        Locale locale = Locale.US;
                        String str2 = k.aH;
                        Object[] objArr = new Object[1];
                        i++;
                        objArr[0] = Integer.valueOf(i);
                        file2 = new File(append.append(String.format(locale, str2, objArr)).toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e2) {
                        h.a("FyuseContainerHelper", e2.getMessage());
                    }
                }
                anonymousClass1.h();
            }
        } catch (FileNotFoundException e22) {
            h.a("FyuseContainerHelper", e22.getMessage());
        } catch (IOException e3) {
            h.a("FyuseContainerHelper", e3.getMessage());
        }
    }
}
