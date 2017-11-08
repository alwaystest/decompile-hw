package com.fyusion.sdk.core.a.a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.android.gallery3d.settings.HicloudAccountReceiver;
import com.fyusion.sdk.common.DLog;
import com.fyusion.sdk.core.a.d;
import com.fyusion.sdk.core.a.f;
import com.fyusion.sdk.core.a.h;
import java.io.FileInputStream;

/* compiled from: Unknown */
public class b implements f<Bitmap> {
    public d a(h hVar, Bitmap bitmap, int i, int i2) {
        FileInputStream d;
        Throwable e;
        byte[] a = com.fyusion.sdk.core.util.pool.b.a.a((int) HicloudAccountReceiver.MSG_HICLOUD_ACCOUNT_LOGOUT);
        Options options = new Options();
        if (!(bitmap == null || bitmap.isRecycled())) {
            options.inBitmap = bitmap;
            options.inPreferredConfig = bitmap.getConfig();
        }
        options.inDither = true;
        options.inMutable = true;
        options.inSampleSize = 1;
        options.inTempStorage = a;
        options.inDither = true;
        options.inMutable = true;
        options.inSampleSize = 1;
        options.inTempStorage = a;
        try {
            d = hVar.d();
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(d, null, options);
                if (decodeStream == null) {
                    DLog.e("JPEGDecoder", "Internal format mismatch!");
                    hVar.a(d);
                    com.fyusion.sdk.core.util.pool.b.a.a(a);
                    return null;
                }
                d aVar = new a(decodeStream);
                hVar.a(d);
                com.fyusion.sdk.core.util.pool.b.a.a(a);
                return aVar;
            } catch (Exception e2) {
                e = e2;
                try {
                    DLog.e("JPEGDecoder", "Unable to decode stream", e);
                    hVar.a(d);
                    com.fyusion.sdk.core.util.pool.b.a.a(a);
                    return null;
                } catch (Throwable th) {
                    e = th;
                    hVar.a(d);
                    com.fyusion.sdk.core.util.pool.b.a.a(a);
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            d = null;
            DLog.e("JPEGDecoder", "Unable to decode stream", e);
            hVar.a(d);
            com.fyusion.sdk.core.util.pool.b.a.a(a);
            return null;
        } catch (Throwable th2) {
            e = th2;
            d = null;
            hVar.a(d);
            com.fyusion.sdk.core.util.pool.b.a.a(a);
            throw e;
        }
    }

    public boolean a(int i) {
        return i == 0;
    }
}
