package com.fyusion.sdk.processor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Matrix;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.os.Handler;
import android.view.Surface;
import com.fyusion.sdk.common.FyuseSDK;
import com.fyusion.sdk.common.ext.FyuseProcessorParameters;
import com.fyusion.sdk.common.ext.FyuseState;
import com.fyusion.sdk.common.ext.Key;
import com.fyusion.sdk.common.ext.ProcessError;
import com.fyusion.sdk.common.ext.ProcessItem;
import com.fyusion.sdk.common.ext.d;
import com.fyusion.sdk.common.ext.f;
import com.fyusion.sdk.common.ext.filter.ImageFilterFactory;
import com.fyusion.sdk.common.ext.filter.a.l;
import com.fyusion.sdk.common.ext.i;
import com.fyusion.sdk.common.ext.k;
import com.fyusion.sdk.common.ext.m;
import com.fyusion.sdk.common.ext.n;
import com.fyusion.sdk.common.h;
import fyusion.vislib.BuildConfig;
import fyusion.vislib.FyuseSize;
import fyusion.vislib.FyuseSlice;
import fyusion.vislib.FyuseSliceVec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.List;
import java.util.concurrent.Semaphore;
import org.mtnwrw.pdqimg.ConversionService;
import org.mtnwrw.pdqimg.DecompressionService;
import org.mtnwrw.pdqimg.DecompressionService.ImageInformation;
import org.mtnwrw.pdqimg.PDQImage;

/* compiled from: Unknown */
public class g {
    private BufferInfo A;
    private String B;
    private FileOutputStream C = null;
    private FileChannel D = null;
    private b a;
    private int b;
    private f c;
    private Context d;
    private f e = null;
    private boolean f = false;
    private d g = null;
    private l h = new l();
    private boolean i = false;
    private int j = 0;
    private int k = 0;
    private ProcessItem l;
    private Bitmap m = null;
    private int n = 0;
    private int o = 0;
    private c p;
    private com.fyusion.sdk.processor.b.a q;
    private String r = "video/avc";
    private Bitmap[] s = new Bitmap[2];
    private boolean[] t = new boolean[]{false, false};
    private int u = 0;
    private MediaCodec v;
    private a w;
    private MediaMuxer x;
    private boolean y = false;
    private int z = 0;

    /* compiled from: Unknown */
    public interface c {
        void a(int i);

        void a(int i, int i2);

        void a(ProcessError processError);

        void a(boolean z, int i);

        void b(int i);

        void b(int i, int i2);
    }

    /* compiled from: Unknown */
    private static class a {
        EGLDisplay a = EGL14.EGL_NO_DISPLAY;
        EGLContext b = EGL14.EGL_NO_CONTEXT;
        EGLSurface c = EGL14.EGL_NO_SURFACE;
        private Surface d;

        public a(Surface surface) {
            if (surface != null) {
                this.d = surface;
                d();
                return;
            }
            throw new NullPointerException();
        }

        private void a(String str) {
            int eglGetError = EGL14.eglGetError();
            if (eglGetError != 12288) {
                throw new RuntimeException(str + ": EGL error: 0x" + Integer.toHexString(eglGetError));
            }
        }

        private void d() {
            this.a = EGL14.eglGetDisplay(0);
            if (this.a != EGL14.EGL_NO_DISPLAY) {
                int[] iArr = new int[2];
                if (EGL14.eglInitialize(this.a, iArr, 0, iArr, 1)) {
                    int[] iArr2 = new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12610, 1, 12352, -1, 12344};
                    switch (com.fyusion.sdk.common.l.a()) {
                        case 2:
                            iArr2[11] = 4;
                            break;
                        case 3:
                            iArr2[11] = 64;
                            break;
                        default:
                            iArr2[11] = 1;
                            break;
                    }
                    EGLConfig[] eGLConfigArr = new EGLConfig[1];
                    int i = 0;
                    EGL14.eglChooseConfig(this.a, iArr2, 0, eGLConfigArr, i, eGLConfigArr.length, new int[1], 0);
                    a("eglCreateContext RGB888+recordable");
                    this.b = EGL14.eglCreateContext(this.a, eGLConfigArr[0], EGL14.EGL_NO_CONTEXT, new int[]{12440, r8, 12344}, 0);
                    a("eglCreateContext");
                    this.c = EGL14.eglCreateWindowSurface(this.a, eGLConfigArr[0], this.d, new int[]{12344}, 0);
                    a("eglCreateWindowSurface");
                    return;
                }
                throw new RuntimeException("unable to initialize EGL14");
            }
            throw new RuntimeException("unable to get EGL14 display");
        }

        public void a() {
            if (this.a != EGL14.EGL_NO_DISPLAY) {
                EGL14.eglMakeCurrent(this.a, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
                EGL14.eglDestroySurface(this.a, this.c);
                EGL14.eglDestroyContext(this.a, this.b);
                EGL14.eglReleaseThread();
                EGL14.eglTerminate(this.a);
            }
            this.d.release();
            this.a = EGL14.EGL_NO_DISPLAY;
            this.b = EGL14.EGL_NO_CONTEXT;
            this.c = EGL14.EGL_NO_SURFACE;
            this.d = null;
        }

        public void a(long j) {
            EGLExt.eglPresentationTimeANDROID(this.a, this.c, j);
            a("eglPresentationTimeANDROID");
        }

        public void b() {
            EGL14.eglMakeCurrent(this.a, this.c, this.c, this.b);
            a("eglMakeCurrent");
        }

        public boolean c() {
            boolean eglSwapBuffers = EGL14.eglSwapBuffers(this.a, this.c);
            a("eglSwapBuffers");
            return eglSwapBuffers;
        }
    }

    /* compiled from: Unknown */
    class b extends Thread {
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        FyuseSliceVec e = null;
        final /* synthetic */ g f;
        private boolean g = false;
        private c h = null;

        public b(g gVar, int i, int i2, int i3, int i4, c cVar) {
            this.f = gVar;
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.h = cVar;
        }

        public void run() {
            i iVar;
            FileInputStream b;
            boolean b2;
            Throwable th;
            if (this.f.l == null || !this.f.l.addRunner()) {
                this.f.p.a(ProcessError.USER_CANCEL_REQUEST);
            }
            this.f.f = true;
            h.a("UploadEncoder", "Run() called");
            if (this.g) {
                h.a("UploadEncoder", "UploadEncoder already initialized");
            } else {
                this.f.c.c();
                this.e = this.f.a(this.f.e);
                while (this.f.e.getNumberOfSlices() > 0) {
                    this.f.e.removeSlice(0);
                }
                int i = 0;
                while (true) {
                    if (((long) i) >= this.e.size()) {
                        break;
                    }
                    h.a("UploadEncoder", "Slice: " + i + " mjpeg: " + this.e.get(i).getMjpeg_file_name() + " index: " + this.e.get(i).getIndex_file_name());
                    this.f.e.addSlice(this.e.get(i));
                    i++;
                }
                this.f.a(this.h);
                this.f.b = this.f.e.getNumberOfSlices();
                this.g = true;
            }
            try {
                byte[] bArr;
                iVar = new i(this.f.c.a());
                try {
                    bArr = new byte[64];
                    iVar.a(com.fyusion.sdk.common.j.a.READ_ONLY, com.fyusion.sdk.common.j.b.NONE);
                    b = iVar.b(0);
                } catch (IOException e) {
                    b = null;
                    try {
                        b2 = this.f.b(this.e, this.a, this.b, this.c, this.d, this.h);
                        iVar.a(b);
                        h.b("UploadEncoder", "slicing done");
                        if (b2) {
                            h.a("UploadEncoder", "sliceVideo() succeeded!");
                        } else {
                            h.d("UploadEncoder", "sliceVideo() failed!");
                        }
                        if (b2) {
                            this.f.c.a(FyuseState.SLICED);
                            this.f.i = false;
                            this.f.b();
                        }
                        if (this.f.l != null) {
                            this.f.l.removeRunner();
                        }
                        this.h.a(b2, this.f.b);
                        this.f.f = false;
                    } catch (Throwable th2) {
                        th = th2;
                        iVar.a(b);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    b = null;
                    iVar.a(b);
                    throw th;
                }
                try {
                    if (b.read(bArr) >= 64) {
                        DecompressionService.getImageInformation(bArr);
                    }
                    b2 = this.f.a(this.e, this.a, this.b, this.c, this.d, this.h);
                    b.close();
                    if (!(iVar == null || b == null)) {
                        iVar.a(b);
                    }
                } catch (IOException e2) {
                    b2 = this.f.b(this.e, this.a, this.b, this.c, this.d, this.h);
                    iVar.a(b);
                    h.b("UploadEncoder", "slicing done");
                    if (b2) {
                        h.d("UploadEncoder", "sliceVideo() failed!");
                    } else {
                        h.a("UploadEncoder", "sliceVideo() succeeded!");
                    }
                    if (b2) {
                        this.f.c.a(FyuseState.SLICED);
                        this.f.i = false;
                        this.f.b();
                    }
                    if (this.f.l != null) {
                        this.f.l.removeRunner();
                    }
                    this.h.a(b2, this.f.b);
                    this.f.f = false;
                }
            } catch (IOException e3) {
                b = null;
                iVar = null;
                b2 = this.f.b(this.e, this.a, this.b, this.c, this.d, this.h);
                iVar.a(b);
                h.b("UploadEncoder", "slicing done");
                if (b2) {
                    h.d("UploadEncoder", "sliceVideo() failed!");
                } else {
                    h.a("UploadEncoder", "sliceVideo() succeeded!");
                }
                if (b2) {
                    this.f.c.a(FyuseState.SLICED);
                    this.f.i = false;
                    this.f.b();
                }
                if (this.f.l != null) {
                    this.f.l.removeRunner();
                }
                this.h.a(b2, this.f.b);
                this.f.f = false;
            } catch (Throwable th4) {
                th = th4;
                b = null;
                iVar = null;
                if (!(iVar == null || b == null)) {
                    iVar.a(b);
                }
                throw th;
            }
            h.b("UploadEncoder", "slicing done");
            if (b2) {
                h.d("UploadEncoder", "sliceVideo() failed!");
            } else {
                h.a("UploadEncoder", "sliceVideo() succeeded!");
            }
            if (b2) {
                this.f.c.a(FyuseState.SLICED);
                this.f.i = false;
                this.f.b();
            }
            if (this.f.l != null) {
                this.f.l.removeRunner();
            }
            this.h.a(b2, this.f.b);
            this.f.f = false;
        }
    }

    public g(m mVar, ProcessItem processItem, c cVar) {
        if (processItem != null && processItem.getPath() != null) {
            this.c = new f(mVar);
            this.l = processItem;
            this.d = FyuseSDK.getContext();
            this.p = cVar;
            try {
                mVar.f();
                this.e = mVar.d();
                if (this.e == null) {
                    cVar.a(ProcessError.CORRUPT_DATA);
                }
                c();
                this.h.a(mVar.a(this.e, new ImageFilterFactory()));
            } catch (IOException e) {
                e.printStackTrace();
                cVar.a(ProcessError.CORRUPT_DATA);
            }
        }
    }

    private static long a(int i) {
        return (((long) i) * 1000000000) / ((long) k.k);
    }

    private FyuseSliceVec a(f fVar) {
        f fVar2 = new f();
        fVar2.setStartFrame(fVar.getStartFrame());
        fVar2.setEndFrame(fVar.getEndFrame());
        fVar2.setNumberOfProcessedFrames(fVar.getNumberOfProcessedFrames());
        fVar2.setThumbnailIndex(fVar.getThumbnailIndex());
        fVar2.a(fVar2.getStartFrame(), fVar2.getEndFrame(), 0, fVar2.getNumberOfProcessedFrames() - 1);
        fVar.setStartFrame(fVar2.getStartFrame());
        fVar.setEndFrame(fVar2.getEndFrame());
        fVar.setStabilizationDataFrameOffset(fVar2.getStabilizationDataFrameOffset());
        fVar.setThumbnailIndex(fVar2.getThumbnailIndex());
        fVar.setNumberOfProcessedFrames(fVar2.getNumberOfProcessedFrames());
        return new com.fyusion.sdk.processor.mjpegutils.b(this.d).a(fVar2, BuildConfig.FLAVOR);
    }

    private void a(c cVar) {
        this.b = this.e.getNumberOfSlices();
        try {
            this.c.b();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (BuildConfig.FLAVOR.equals(this.e.getDeviceID())) {
            this.e.setDeviceID(com.fyusion.sdk.common.util.a.a());
        }
        this.e.setAppVersionUsedToUpload(FyuseSDK.getVersion());
        this.c.a(this.e);
        h.a("UploadEncoder", "Number of highResolutionSlices: " + this.e.getNumberOfSlices());
        cVar.b(this.e.getNumberOfSlices(), this.e.a(this.e.getThumbnailIndex()).getIndex());
    }

    private void a(String str) {
        synchronized (this) {
            if (this.i) {
                return;
            }
            this.A = new BufferInfo();
            this.v = d();
            if (this.v != null) {
                MediaFormat createVideoFormat = MediaFormat.createVideoFormat(this.r, this.o, this.n);
                createVideoFormat.setInteger("color-format", 2130708361);
                createVideoFormat.setInteger("bitrate", ((double) this.o) > k.e.a ? k.n : k.m);
                createVideoFormat.setInteger("frame-rate", k.k);
                createVideoFormat.setInteger("i-frame-interval", 10);
                createVideoFormat.setInteger("bitrate-mode", 2);
                h.a("UploadEncoder", "format: " + createVideoFormat.toString());
                com.fyusion.sdk.common.util.b.a(this.v, createVideoFormat, null, 1);
                this.w = new a(this.v.createInputSurface());
                this.g = new d(this.w.b, this.w.a, this.w.c, this.o, this.n);
                this.g.a(this.h.d());
                this.v.start();
                h.a("UploadEncoder", "output file is " + str);
                String str2 = str + ".tmp";
                try {
                    this.C = new FileOutputStream(this.B);
                    this.D = this.C.getChannel();
                    this.x = new MediaMuxer(str2, 0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.z = -1;
                this.y = false;
                return;
            }
        }
    }

    private void a(boolean z) {
        boolean z2 = false;
        if (!z) {
            z2 = true;
        }
        this.i = z2;
    }

    private boolean a(FyuseSliceVec fyuseSliceVec, int i, int i2, int i3, int i4, c cVar) {
        final int i5 = !this.i ? i : this.j;
        this.n = i4;
        this.o = i3;
        h.a("UploadEncoder", "Fyuse processed size width: " + i3 + " height: " + i4);
        int i6 = this.k;
        FyuseSlice fyuseSlice = fyuseSliceVec.get(i6);
        int start_frame = fyuseSlice.getStart_frame();
        h.a("UploadEncoder", "Slice: " + i6 + " mFyuse start: " + i + " end: " + i2);
        long nanoTime = System.nanoTime();
        String absolutePath = this.c.a(i6).getAbsolutePath();
        this.B = absolutePath;
        int i7 = 0;
        this.i = false;
        final int numberOfProcessedFrames = i2 >= 0 ? i2 : this.e.getNumberOfProcessedFrames() - 1;
        final Semaphore[] semaphoreArr = new Semaphore[]{new Semaphore(0), new Semaphore(0)};
        final Semaphore semaphore = new Semaphore(0);
        final Object obj = new Object();
        new Matrix().preScale(1.0f, -1.0f);
        new Thread(new Runnable(this) {
            int a = 0;
            final /* synthetic */ g g;

            public void run() {
                PDQImage pDQImage;
                FileChannel channel;
                Throwable th;
                Throwable th2;
                i iVar = new i(this.g.c.a());
                byte[] bArr = new byte[64];
                if (iVar.a(com.fyusion.sdk.common.j.a.READ_ONLY, com.fyusion.sdk.common.j.b.NONE)) {
                    PDQImage pDQImage2;
                    synchronized (obj) {
                        int i = i5;
                        pDQImage2 = null;
                        while (i <= numberOfProcessedFrames) {
                            if (this.g.l.isCancelled()) {
                                this.g.f = false;
                                break;
                            } else if (this.g.i) {
                                break;
                            } else {
                                int i2;
                                if (this.g.t[this.a]) {
                                    i2 = i;
                                    pDQImage = pDQImage2;
                                } else {
                                    try {
                                        FileInputStream b = iVar.b(i);
                                        channel = b.getChannel();
                                        try {
                                            long position = channel.position();
                                            if (b.read(bArr) < 64) {
                                                h.d("UploadEncoder", "Cannot read image data");
                                                semaphoreArr[this.a].release();
                                                this.g.i = true;
                                                if (channel == null) {
                                                    break;
                                                }
                                                channel.close();
                                                break;
                                            }
                                            ImageInformation imageInformation = DecompressionService.getImageInformation(bArr);
                                            ByteBuffer map = channel.map(MapMode.READ_ONLY, position, (long) imageInformation.StreamSize);
                                            if (pDQImage2 == null) {
                                                pDQImage2 = new PDQImage(imageInformation.Width, imageInformation.Height, imageInformation.ImageType);
                                            }
                                            if (this.g.s[this.a] == null) {
                                                this.g.s[this.a] = Bitmap.createBitmap(imageInformation.Width, imageInformation.Height, Config.ARGB_8888);
                                            }
                                            DecompressionService.decompressImage(map, pDQImage2);
                                            pDQImage2.swapUVChannels(false);
                                            ConversionService.convertPDQImageToBitmap(pDQImage2, this.g.s[this.a], false, true);
                                            this.g.t[this.a] = true;
                                            semaphoreArr[this.a].release();
                                            if (channel != null) {
                                                channel.close();
                                            }
                                            pDQImage = pDQImage2;
                                            i2 = i + 1;
                                            this.a ^= 1;
                                        } catch (Throwable th22) {
                                            Throwable th3 = th22;
                                            th22 = th;
                                            th = th3;
                                        }
                                    } catch (Exception e) {
                                        semaphoreArr[this.a].release();
                                        e.printStackTrace();
                                    }
                                }
                                try {
                                    obj.wait();
                                    i = i2;
                                    pDQImage2 = pDQImage;
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                    pDQImage2 = pDQImage;
                                }
                            }
                        }
                    }
                    if (pDQImage2 != null) {
                        pDQImage2.close();
                    }
                } else {
                    h.d("UploadEncoder", "Cannot open datasource for transcoding");
                    this.g.i = true;
                    semaphoreArr[this.a].release();
                }
                semaphore.release();
                return;
                if (channel != null) {
                    if (th22 == null) {
                        channel.close();
                    } else {
                        try {
                            channel.close();
                        } catch (Throwable th4) {
                            th22.addSuppressed(th4);
                        }
                    }
                }
                throw th;
                throw th;
            }
        }).start();
        this.B = absolutePath;
        a(absolutePath);
        if (this.w != null) {
            this.w.b();
        }
        Boolean valueOf = Boolean.valueOf(this.h.c);
        this.h.c = false;
        h.a("UploadEncoder", "Creating encoder took: " + (((float) (System.nanoTime() - nanoTime)) / 1000000.0f));
        cVar.a(i6);
        int i8 = 0;
        while (i5 <= numberOfProcessedFrames) {
            if (this.i) {
                e();
                return false;
            }
            if (this.l.isCancelled()) {
                this.f = false;
            }
            if (!this.f) {
                h.b("UploadEncoder", "stopEncoding");
                this.j = 0;
                this.k = i6;
                b(true);
                a(false);
                synchronized (obj) {
                    obj.notifyAll();
                }
            }
            if (this.i) {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                e();
                return false;
            }
            semaphoreArr[i8].acquireUninterruptibly();
            if (this.t[i8]) {
                this.m = this.s[i8];
            }
            if (this.m != null) {
                this.g.a(this.m);
                this.t[i8] = false;
                int i9 = i8 ^ 1;
                synchronized (obj) {
                    obj.notifyAll();
                }
                this.w.a(a(i5));
                this.w.c();
                i8 = i7 + 1;
                i5++;
                start_frame++;
                if (cVar != null) {
                    i7 = this.u + 1;
                    this.u = i7;
                    cVar.a(i7, this.e.getNumberOfProcessedFrames());
                }
                if (start_frame <= fyuseSlice.getEnd_frame()) {
                    if (i8 % 2 == 0 && i8 > 0) {
                        b(false);
                    }
                    i7 = i8;
                    i8 = i9;
                } else {
                    h.a("UploadEncoder", "Closing slice: " + i6 + " with frames: " + i8);
                    a(true);
                    i7 = 0;
                    if ((((long) (i6 + 1)) < fyuseSliceVec.size() ? 1 : null) == null) {
                        synchronized (g.class) {
                            b(true);
                            e();
                            this.m = null;
                            for (i8 = 0; i8 < this.s.length; i8++) {
                                if (this.s[i8] != null) {
                                    this.s[i8].recycle();
                                }
                            }
                            this.s = null;
                            this.t = null;
                            cVar.b(i6);
                        }
                        this.h.c = valueOf.booleanValue();
                        semaphore.acquire();
                        return this.i;
                    }
                    synchronized (g.class) {
                        b(true);
                        e();
                        cVar.b(i6);
                    }
                    i6++;
                    fyuseSlice = fyuseSliceVec.get(i6);
                    h.a("UploadEncoder", "Slice: " + i6 + " slice start: " + fyuseSlice.getStart_frame() + " end: " + fyuseSlice.getEnd_frame());
                    if ((((long) i6) >= fyuseSliceVec.size() ? 1 : null) == null) {
                        h.a("UploadEncoder", "Opening slice: " + i6);
                        String absolutePath2 = this.c.a(i6).getAbsolutePath();
                        this.B = absolutePath2;
                        a(absolutePath2);
                        if (this.w != null) {
                            this.w.b();
                            cVar.a(i6);
                        }
                    }
                    i8 = i9;
                }
            } else {
                h.d("UploadEncoder", "Got semaphore but no buffer");
                return false;
            }
        }
        this.h.c = valueOf.booleanValue();
        try {
            semaphore.acquire();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        if (this.i) {
        }
        return this.i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(boolean z) {
        synchronized (this) {
            if (this.D == null) {
            } else if (this.i) {
            } else {
                if (z) {
                    this.v.signalEndOfInputStream();
                }
                ByteBuffer[] outputBuffers = this.v.getOutputBuffers();
                while (true) {
                    ByteBuffer[] outputBuffers2;
                    int dequeueOutputBuffer = this.v.dequeueOutputBuffer(this.A, 2000);
                    if (dequeueOutputBuffer == -1) {
                        if (!z) {
                            break;
                        }
                        h.a("UploadEncoder", "no output available, spinning to await EOS");
                    } else if (dequeueOutputBuffer == -3) {
                        outputBuffers2 = this.v.getOutputBuffers();
                        outputBuffers = outputBuffers2;
                    } else if (dequeueOutputBuffer != -2) {
                        if (dequeueOutputBuffer >= 0) {
                            ByteBuffer byteBuffer = outputBuffers[dequeueOutputBuffer];
                            if (byteBuffer != null) {
                                if (this.A.size != 0) {
                                    byteBuffer.position(this.A.offset);
                                    byteBuffer.limit(this.A.offset + this.A.size);
                                    try {
                                        this.D.write(byteBuffer);
                                        this.D.force(false);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    if (this.x == null) {
                                        h.d("UploadEncoder", "No muxer object to write sample data to");
                                    } else {
                                        this.x.writeSampleData(this.z, byteBuffer, this.A);
                                    }
                                }
                                this.v.releaseOutputBuffer(dequeueOutputBuffer, false);
                                if ((this.A.flags & 4) != 0) {
                                    break;
                                }
                            }
                            throw new RuntimeException("encoderOutputBuffer " + dequeueOutputBuffer + " was null");
                        }
                        h.c("UploadEncoder", "unexpected result from encoder.dequeueOutputBuffer: " + dequeueOutputBuffer);
                    } else if (this.y) {
                        throw new RuntimeException("format changed twice");
                    } else {
                        MediaFormat outputFormat = this.v.getOutputFormat();
                        if (this.x == null) {
                            h.d("UploadEncoder", "No muxer object to write data to");
                        } else {
                            this.z = this.x.addTrack(outputFormat);
                            this.x.start();
                            this.y = true;
                        }
                    }
                    outputBuffers2 = outputBuffers;
                    outputBuffers = outputBuffers2;
                }
                if (z) {
                    h.a("UploadEncoder", "end of stream reached");
                } else {
                    h.c("UploadEncoder", "reached end of stream unexpectedly");
                }
            }
        }
    }

    private boolean b(FyuseSliceVec fyuseSliceVec, int i, int i2, int i3, int i4, c cVar) {
        final int i5 = !this.i ? i : this.j;
        boolean[] zArr = new boolean[1];
        this.n = i4;
        this.o = i3;
        this.s[0] = Bitmap.createBitmap(this.o, this.n, Config.ARGB_8888);
        this.s[1] = Bitmap.createBitmap(this.o, this.n, Config.ARGB_8888);
        int i6 = this.k;
        FyuseSlice fyuseSlice = fyuseSliceVec.get(i6);
        String absolutePath = this.c.a(i6).getAbsolutePath();
        h.a("UploadEncoder", "Creating slice video file: " + absolutePath);
        System.nanoTime();
        this.i = false;
        final int numberOfProcessedFrames = i2 >= 0 ? i2 : this.e.getNumberOfProcessedFrames() - 1;
        final Semaphore semaphore = new Semaphore(0);
        final Semaphore semaphore2 = new Semaphore(0);
        final Semaphore semaphore3 = new Semaphore(0);
        semaphore2 = new Semaphore(0);
        final Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        final com.fyusion.sdk.core.a.c cVar2 = new com.fyusion.sdk.core.a.c();
        final i iVar = new i(this.c.a());
        new Thread(new Runnable(this) {
            final /* synthetic */ g h;

            public void run() {
                if (iVar.a(com.fyusion.sdk.common.j.a.READ_ONLY, com.fyusion.sdk.common.j.b.NONE)) {
                    int i = i5;
                    loop0:
                    while (true) {
                        int i2 = i;
                        while (i2 <= numberOfProcessedFrames) {
                            if (!this.h.i) {
                                if (this.h.t[0]) {
                                    semaphore3.acquireUninterruptibly();
                                } else {
                                    this.h.s[0] = (Bitmap) cVar2.a(iVar.a(this.h.o, this.h.n, i2), com.fyusion.sdk.core.a.c.a.RGBA8888).a();
                                    this.h.s[0] = Bitmap.createBitmap(this.h.s[0], 0, 0, this.h.o, this.h.n, matrix, false);
                                    this.h.t[0] = true;
                                    i = i2 + 2;
                                    if (semaphore.availablePermits() == 0) {
                                        semaphore.release();
                                    }
                                }
                            } else {
                                return;
                            }
                        }
                        break loop0;
                    }
                    semaphore.release();
                }
            }
        }).start();
        final i iVar2 = iVar;
        final int i7 = i5;
        final int i8 = numberOfProcessedFrames;
        final com.fyusion.sdk.core.a.c cVar3 = cVar2;
        final Matrix matrix2 = matrix;
        new Thread(new Runnable(this) {
            final /* synthetic */ g h;

            public void run() {
                if (iVar2.a(com.fyusion.sdk.common.j.a.READ_ONLY, com.fyusion.sdk.common.j.b.NONE)) {
                    int i = i7;
                    loop0:
                    while (true) {
                        int i2 = i;
                        while (i2 <= i8) {
                            if (!this.h.i) {
                                if (this.h.t[1]) {
                                    semaphore2.acquireUninterruptibly();
                                } else {
                                    this.h.s[1] = (Bitmap) cVar3.a(iVar2.a(this.h.o, this.h.n, i2), com.fyusion.sdk.core.a.c.a.RGBA8888).a();
                                    this.h.s[1] = Bitmap.createBitmap(this.h.s[1], 0, 0, this.h.o, this.h.n, matrix2, false);
                                    this.h.t[1] = true;
                                    i = i2 + 2;
                                    if (semaphore2.availablePermits() == 0) {
                                        semaphore2.release();
                                    }
                                }
                            } else {
                                return;
                            }
                        }
                        break loop0;
                    }
                    semaphore2.release();
                }
            }
        }).start();
        this.B = absolutePath;
        a(absolutePath);
        if (this.w != null) {
            this.w.b();
        }
        Boolean valueOf = Boolean.valueOf(this.h.c);
        this.h.c = false;
        cVar.a(i6);
        int i9 = i5;
        int i10 = 0;
        int i11 = i5;
        int i12 = 0;
        FyuseSlice fyuseSlice2 = fyuseSlice;
        i5 = i6;
        while (i9 <= numberOfProcessedFrames) {
            if (!this.i) {
                if (!this.f) {
                    this.j = 0;
                    this.k = i5;
                    a(false);
                    break;
                }
                (i10 != 0 ? semaphore2 : semaphore).acquireUninterruptibly();
                if (this.t[i10]) {
                    this.m = this.s[i10];
                }
                if (this.m == null) {
                    return false;
                }
                int i13;
                this.g.a(this.m);
                this.t[i10] = false;
                if (i10 != 0) {
                    semaphore2.release();
                    i13 = 0;
                } else {
                    semaphore3.release();
                    i13 = 1;
                }
                this.w.a(a(i9));
                this.w.c();
                i10 = i12 + 1;
                i12 = i9 + 1;
                i11++;
                if (cVar != null) {
                    i9 = this.u + 1;
                    this.u = i9;
                    cVar.a(i9, this.e.getNumberOfProcessedFrames());
                }
                if (i11 > fyuseSlice2.getEnd_frame()) {
                    a(true);
                    i10 = 0;
                    if ((((long) (i5 + 1)) < fyuseSliceVec.size() ? 1 : null) == null) {
                        synchronized (g.class) {
                            b(true);
                            e();
                            this.m = null;
                            this.s = null;
                            this.t = null;
                            cVar.b(i5);
                        }
                        break;
                    }
                    synchronized (g.class) {
                        b(true);
                        e();
                        cVar.b(i5);
                    }
                    i5++;
                    fyuseSlice2 = fyuseSliceVec.get(i5);
                    if ((((long) i5) >= fyuseSliceVec.size() ? 1 : null) == null) {
                        String absolutePath2 = this.c.a(i5).getAbsolutePath();
                        this.B = absolutePath2;
                        a(absolutePath2);
                        if (this.w != null) {
                            this.w.b();
                            cVar.a(i5);
                        }
                    }
                } else if (i10 % 2 == 0 && i10 > 0) {
                    b(false);
                }
                i9 = i12;
                i12 = i10;
                i10 = i13;
            } else {
                e();
                return false;
            }
        }
        this.h.c = valueOf.booleanValue();
        return !this.i;
    }

    private void c() {
        FyuseProcessorParameters fyuseProcessorParameters = this.l.getFyuseProcessorParameters();
        if (fyuseProcessorParameters != null) {
            List<Key> keys = fyuseProcessorParameters.getKeys();
            Boolean valueOf = Boolean.valueOf(false);
            com.fyusion.sdk.processor.b.a aVar = null;
            for (Key key : keys) {
                if ("com.fyusion.sdk.key.share.resolution.full".equals(key.getName())) {
                    valueOf = (Boolean) fyuseProcessorParameters.get(key);
                }
                aVar = !"com.fyusion.sdk.key.share.encode.size.selector".equals(key.getName()) ? aVar : (com.fyusion.sdk.processor.b.a) fyuseProcessorParameters.get(key);
            }
            if (valueOf.booleanValue() && !com.fyusion.sdk.common.a.a().c("share", "fullres")) {
                this.q = aVar;
            }
        }
        if (this.q == null) {
            this.q = com.fyusion.sdk.processor.b.a.a;
        }
    }

    private MediaCodec d() {
        if (this.n >= k.b.b && com.fyusion.sdk.common.l.c()) {
            this.r = "video/hevc";
        }
        try {
            this.v = MediaCodec.createEncoderByType(this.r);
            h.b("UploadEncoder", "MIME: " + this.r);
            return this.v;
        } catch (Exception e) {
            h.b("UploadEncoder", "prepareEncoder failed creating encoder for MIME: " + this.r + " Error: " + e.getMessage());
            return null;
        }
    }

    private void e() {
        synchronized (this) {
            h.b("UploadEncoder", "releasing encoder objects");
            try {
                if (this.v != null) {
                    this.v.stop();
                    this.v.release();
                    this.v = null;
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            if (this.w != null) {
                this.w.a();
                this.w = null;
            }
            if (this.C != null) {
                try {
                    this.D.close();
                    this.C.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                this.C = null;
            }
            try {
                if (this.x != null) {
                    this.x.stop();
                    this.x.release();
                    this.x = null;
                }
            } catch (IllegalStateException e3) {
                e3.printStackTrace();
            }
        }
    }

    public void a() {
        Handler handler = new Handler(this.d.getMainLooper());
        if (this.l != null) {
            handler.post(new Runnable(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void run() {
                    n a = this.a.q.a((int) this.a.e.getProcessedSize().height);
                    this.a.e.setProcessedSize(new FyuseSize((double) a.a, (double) a.b));
                    this.a.a = new b(this.a, this.a.e.getStartFrame(), this.a.e.getEndFrame(), a.a, a.b, this.a.p);
                    this.a.a.start();
                }
            });
        }
    }

    public void b() {
        h.a("UploadEncoder", "cleanup() called.");
        this.e = null;
    }
}
