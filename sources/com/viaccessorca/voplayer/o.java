package com.viaccessorca.voplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceHolder;

/* JADX INFO: loaded from: classes2.dex */
class o extends GLSurfaceView implements SurfaceHolder.Callback, Runnable {
    private static int i = 10;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f162a;
    private int b;
    private float c;
    private float d;
    private int e;
    private n f;
    private boolean g;
    private int h;

    protected o(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f162a = 0;
        this.b = 0;
        this.c = 0.0f;
        this.d = 0.0f;
        this.e = 0;
        this.g = false;
        this.h = 0;
        a(context);
    }

    private void a(Context context) {
        getHolder().addCallback(this);
        if (Build.VERSION.SDK_INT >= 17) {
            setSecure(true);
        }
        setZOrderMediaOverlay(true);
        try {
            this.e = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565).getPixel(0, 0);
        } catch (Exception unused) {
        }
        setEGLConfigChooser(8, 8, 8, 8, 0, 0);
        getHolder().setFormat(1);
        n nVar = new n(context);
        this.f = nVar;
        setRenderer(nVar);
        setRenderMode(0);
    }

    private void b() {
        n nVar = this.f;
        if (nVar != null) {
            nVar.a();
            requestRender();
            this.f = null;
        }
    }

    protected int a() {
        n nVar = this.f;
        if (nVar != null) {
            return nVar.b();
        }
        return 0;
    }

    protected synchronized void a(int i2, int i3) {
        n nVar = this.f;
        if (nVar != null) {
            nVar.a(i2, i3);
            if (20008 == i3) {
                this.h = 0;
            }
            notify();
        }
    }

    protected synchronized void a(int i2, int i3, int i4, float f, int i5, int i6) {
        n nVar;
        if (i3 <= 0 || i4 <= 0) {
            return;
        }
        int i7 = this.f162a;
        int i8 = this.b;
        float f2 = this.c;
        float f3 = this.d;
        int i9 = (int) (i3 * f);
        if (i2 == 0) {
            int i10 = i5 * i4;
            int i11 = i9 * i6;
            if (i10 > i11) {
                this.f162a = i11 / i4;
            } else {
                this.b = i10 / i9;
                this.f162a = i5;
            }
            this.c = (getWidth() - this.f162a) / 2;
            int height = getHeight();
            int i12 = this.b;
            float f4 = (height - i12) / 2;
            this.d = f4;
            if ((i7 == this.f162a || i8 != i12 || f2 != this.c || f3 != f4) && (nVar = this.f) != null) {
                nVar.a(this.c, this.d, this.f162a, this.b);
                notify();
            }
        }
        this.f162a = i5;
        this.b = i6;
        this.c = (getWidth() - this.f162a) / 2;
        int height2 = getHeight();
        int i13 = this.b;
        float f5 = (height2 - i13) / 2;
        this.d = f5;
        if (i7 == this.f162a) {
            nVar.a(this.c, this.d, this.f162a, this.b);
            notify();
        } else {
            nVar.a(this.c, this.d, this.f162a, this.b);
            notify();
        }
    }

    protected synchronized void a(int i2, int i3, int i4, int i5) {
        n nVar;
        int i6 = this.f162a;
        int i7 = this.b;
        float f = this.c;
        float f2 = this.d;
        this.c = 0.0f;
        this.d = 0.0f;
        if (i2 >= 0) {
            this.f162a = i4 - (i2 * 2);
        } else {
            this.f162a = i4;
            this.c = -i2;
        }
        if (i3 >= 0) {
            this.b = i5 - (i3 * 2);
        } else {
            this.b = i5;
            this.d = -i3;
        }
        if ((i6 != this.f162a || i7 != this.b || f != this.c || f2 != this.d) && (nVar = this.f) != null) {
            nVar.a(this.c, this.d, this.f162a, this.b);
            notify();
        }
    }

    protected synchronized void a(int i2, Bitmap bitmap, int i3) {
        if (this.e == bitmap.getPixel(0, 0)) {
            return;
        }
        if (i3 >= 0) {
            return;
        }
        n nVar = this.f;
        if (nVar != null) {
            nVar.a(i2, bitmap);
            notify();
        }
    }

    protected synchronized void a(byte[] bArr, int i2) {
        n nVar = this.f;
        if (nVar != null) {
            nVar.a(bArr, i2);
            this.h = i2;
            notify();
        }
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // java.lang.Runnable
    public synchronized void run() {
        long j;
        long j2;
        long j3 = 0;
        while (this.g) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                if (this.h > 0) {
                    if (j3 <= jCurrentTimeMillis) {
                        j = i;
                        j2 = jCurrentTimeMillis + j;
                    } else {
                        long j4 = j3;
                        j = j3 - jCurrentTimeMillis;
                        j2 = j4;
                    }
                    try {
                        j2 += (long) this.h;
                        wait(j);
                    } catch (InterruptedException unused) {
                    }
                    j3 = j2;
                } else {
                    wait();
                }
            } catch (InterruptedException unused2) {
            }
            requestRender();
        }
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        super.surfaceChanged(surfaceHolder, i2, i3, i4);
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        super.surfaceCreated(surfaceHolder);
        if (this.g) {
            return;
        }
        this.g = true;
        new Thread(this).start();
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceHolder.Callback
    public synchronized void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.g = false;
        b();
        notify();
        super.surfaceDestroyed(surfaceHolder);
    }
}
