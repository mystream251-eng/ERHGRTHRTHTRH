package com.viaccessorca.voplayer;

import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes2.dex */
class VORenderer implements GLSurfaceView.Renderer {
    private VOSurfaceViewOpenGL c;
    private final long m329t7p6390828478437 = 0;
    private final long m329t7p6390828478438 = 0;
    private final long m329t7p6390828478439 = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f88a = false;
    private boolean b = true;

    VORenderer(VOSurfaceViewOpenGL vOSurfaceViewOpenGL) {
        this.c = null;
        if (vOSurfaceViewOpenGL == null) {
            throw new IllegalArgumentException();
        }
        this.c = vOSurfaceViewOpenGL;
    }

    private native void MTMxNzM2MUQwMTgwNEI5MzlGMThFNzIzN0E0MUM2RTc();

    private native int Mzc4MzVBQThGQTFFNDlFMjhCMzYxNDA0RDIyQzQyOTg(int i, int i2, int i3, boolean z);

    private native int NTEwQkNGREU1NTBBNEZDNTk3QUY4NTZCQ0E3MkZBODU(int i, int i2);

    private native void NjhBQTkwMjhFNTE1NDI3OUExMDU1NTU2OTFGOUUyNDU();

    private native int NkI4QzU3OTAyM0ZDNEMxMTg5NEU2QkMyMDYzMTRCNTU();

    private native void NzEyQ0U5QUE4M0VFNDk2QjhFNkFFRDE5NEVBRUI1RkI();

    private native void RjFENTExQTJCOUMyNEJFNjlBQURENzVERkQ3OEI1NzU();

    protected int a() {
        return NkI4QzU3OTAyM0ZDNEMxMTg5NEU2QkMyMDYzMTRCNTU();
    }

    protected int a(int i, int i2) {
        return NTEwQkNGREU1NTBBNEZDNTk3QUY4NTZCQ0E3MkZBODU(i, i2);
    }

    protected int a(int i, int i2, int i3, boolean z) {
        return Mzc4MzVBQThGQTFFNDlFMjhCMzYxNDA0RDIyQzQyOTg(i, i2, i3, z);
    }

    protected void a(boolean z) {
        this.b = z;
    }

    protected void b() {
        NjhBQTkwMjhFNTE1NDI3OUExMDU1NTU2OTFGOUUyNDU();
    }

    protected void c() {
        NzEyQ0U5QUE4M0VFNDk2QjhFNkFFRDE5NEVBRUI1RkI();
    }

    protected void d() {
        MTMxNzM2MUQwMTgwNEI5MzlGMThFNzIzN0E0MUM2RTc();
    }

    protected void e() {
        if (this.f88a) {
            return;
        }
        d();
        this.f88a = true;
    }

    protected void f() {
        RjFENTExQTJCOUMyNEJFNjlBQURENzVERkQ3OEI1NzU();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        if (this.b) {
            VOSurfaceViewOpenGL.h hVarD = this.c.d();
            if (hVarD != null) {
                a(hVarD.a(), hVarD.c(), hVarD.b(), hVarD.d());
            } else {
                a();
            }
        }
        if (this.b) {
            return;
        }
        gl10.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl10.glClear(16640);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        a(i, i2);
        this.c.a(i, i2);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        this.c.c();
    }
}
