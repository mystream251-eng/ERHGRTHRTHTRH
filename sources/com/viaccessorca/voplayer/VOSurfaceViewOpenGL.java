package com.viaccessorca.voplayer;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import androidx.work.Data;
import com.viaccessorca.common.VOLibraryLoader;
import java.nio.IntBuffer;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

/* JADX INFO: loaded from: classes2.dex */
class VOSurfaceViewOpenGL extends GLSurfaceView implements SurfaceHolder.Callback {
    private static boolean i = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f101a;
    private boolean b;
    private h c;
    private final Runnable d;
    d e;
    GLSurfaceView.EGLWindowSurfaceFactory f;
    private f g;
    private g h;
    private VORenderer m329t7p6390828478440;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (VOSurfaceViewOpenGL.this.c == null) {
                return;
            }
            VOSurfaceViewOpenGL.this.c.updateTexImage();
        }
    }

    class b implements SurfaceTexture.OnFrameAvailableListener {
        b() {
        }

        @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
            VOSurfaceViewOpenGL vOSurfaceViewOpenGL = VOSurfaceViewOpenGL.this;
            vOSurfaceViewOpenGL.queueEvent(vOSurfaceViewOpenGL.d);
        }
    }

    private static class c implements GLSurfaceView.EGLConfigChooser {
        private static int[] h = {12324, 4, 12323, 4, 12322, 4, 12352, 4, 12344};

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected int f104a;
        protected int b;
        protected int c;
        protected int d;
        protected int e;
        protected int f;
        private int[] g = new int[1];

        public c(int i, int i2, int i3, int i4, int i5, int i6) {
            this.f104a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = i6;
        }

        private int a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.g) ? this.g[0] : i2;
        }

        @Override // android.opengl.GLSurfaceView.EGLConfigChooser
        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            egl10.eglChooseConfig(eGLDisplay, h, null, 0, iArr);
            int i = iArr[0];
            if (i <= 0) {
                throw new IllegalArgumentException("No configs match configSpec");
            }
            EGLConfig[] eGLConfigArr = new EGLConfig[i];
            egl10.eglChooseConfig(eGLDisplay, h, eGLConfigArr, i, iArr);
            return chooseConfig(egl10, eGLDisplay, eGLConfigArr);
        }

        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int iA = a(egl10, eGLDisplay, eGLConfig, 12325, 0);
                int iA2 = a(egl10, eGLDisplay, eGLConfig, 12326, 0);
                if (iA >= this.e && iA2 >= this.f) {
                    int iA3 = a(egl10, eGLDisplay, eGLConfig, 12324, 0);
                    int iA4 = a(egl10, eGLDisplay, eGLConfig, 12323, 0);
                    int iA5 = a(egl10, eGLDisplay, eGLConfig, 12322, 0);
                    int iA6 = a(egl10, eGLDisplay, eGLConfig, 12321, 0);
                    if (iA3 == this.f104a && iA4 == this.b && iA5 == this.c && iA6 == this.d) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }
    }

    private static class d implements GLSurfaceView.EGLContextFactory {
        private static int b = 12440;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f105a;

        private d() {
            this.f105a = false;
        }

        /* synthetic */ d(a aVar) {
            this();
        }

        @Override // android.opengl.GLSurfaceView.EGLContextFactory
        public EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            EGLContext eGLContextEglCreateContext = egl10.eglCreateContext(eGLDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, VOSurfaceViewOpenGL.i ? new int[]{b, 2, 12992, 1, 12344} : new int[]{b, 2, 12344});
            this.f105a = false;
            return eGLContextEglCreateContext;
        }

        @Override // android.opengl.GLSurfaceView.EGLContextFactory
        public void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            this.f105a = true;
            egl10.eglDestroyContext(eGLDisplay, eGLContext);
        }

        public boolean isDestroyed() {
            return this.f105a;
        }
    }

    private static final class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private VORenderer f106a;

        e(VORenderer vORenderer) {
            this.f106a = vORenderer;
        }

        @Override // java.lang.Runnable
        public void run() {
            VORenderer vORenderer = this.f106a;
            if (vORenderer != null) {
                vORenderer.b();
            }
        }
    }

    protected interface f {
        void onRendererDeleted();
    }

    interface g {
        void onSurfaceTextureAvailable(Object obj, int i, int i2);
    }

    class h extends SurfaceTexture {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f107a;
        private int b;
        private int c;
        private int d;
        private float e;
        private boolean f;

        h(VOSurfaceViewOpenGL vOSurfaceViewOpenGL, int i) {
            super(i);
            this.f107a = 0;
            this.c = 0;
            this.d = 0;
            this.f = VOSurfaceViewOpenGL.i;
            this.f107a = i;
        }

        int a() {
            return this.f107a;
        }

        int b() {
            return this.d;
        }

        int c() {
            return this.c;
        }

        boolean d() {
            return this.f;
        }
    }

    private static class i implements GLSurfaceView.EGLWindowSurfaceFactory {
        private i() {
        }

        /* synthetic */ i(a aVar) {
            this();
        }

        @Override // android.opengl.GLSurfaceView.EGLWindowSurfaceFactory
        public EGLSurface createWindowSurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
            EGLSurface eGLSurfaceEglCreateWindowSurface = null;
            try {
                eGLSurfaceEglCreateWindowSurface = egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, VOSurfaceViewOpenGL.i ? new int[]{12992, 1, 12344} : null);
                VOSurfaceViewOpenGL.checkGlError();
                return eGLSurfaceEglCreateWindowSurface;
            } catch (IllegalArgumentException unused) {
                return eGLSurfaceEglCreateWindowSurface;
            }
        }

        @Override // android.opengl.GLSurfaceView.EGLWindowSurfaceFactory
        public void destroySurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
            egl10.eglDestroySurface(eGLDisplay, eGLSurface);
        }
    }

    static {
        VOLibraryLoader.b();
    }

    protected VOSurfaceViewOpenGL(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f101a = false;
        this.b = false;
        this.m329t7p6390828478440 = new VORenderer(this);
        a aVar = null;
        this.c = null;
        this.d = new a();
        this.e = new d(aVar);
        this.f = new i(aVar);
        a(context);
    }

    private boolean b(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo().reqGlEsVersion >= 131072;
    }

    public static void checkGlError() {
        int i2 = 0;
        String str = null;
        while (true) {
            int iGlGetError = GLES20.glGetError();
            if (iGlGetError == 0) {
                break;
            }
            str = "glError " + GLU.gluErrorString(iGlGetError);
            i2 = iGlGetError;
        }
        if (i2 != 0) {
            throw new RuntimeException(str);
        }
    }

    private void g() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, IntBuffer.wrap(iArr));
        GLES20.glBindTexture(36197, iArr[0]);
        if (i) {
            GLES20.glTexParameteri(36197, 35834, 1);
        } else {
            GLES20.glTexParameteri(36197, 10241, 9729);
            GLES20.glTexParameteri(36197, Data.MAX_DATA_BYTES, 9729);
            GLES20.glTexParameteri(36197, 10242, 33071);
            GLES20.glTexParameteri(36197, 10243, 33071);
        }
        checkGlError();
        h hVar = new h(this, iArr[0]);
        this.c = hVar;
        hVar.setOnFrameAvailableListener(new b());
    }

    Object a() {
        boolean z;
        g gVar;
        synchronized (this) {
            z = true;
            if (this.c != null) {
                z = false;
            } else if (this.f101a) {
                g();
            } else {
                this.b = true;
                z = false;
            }
        }
        if (z && (gVar = this.h) != null) {
            gVar.onSurfaceTextureAvailable(this.c, -1, -1);
        }
        return this.c;
    }

    void a(float f2) {
        h hVar = this.c;
        if (hVar != null) {
            hVar.e = f2;
        }
    }

    void a(int i2, int i3) {
    }

    void a(int i2, int i3, int i4) {
        h hVar = this.c;
        if (hVar != null) {
            hVar.b = i2;
            this.c.c = i3;
            this.c.d = i4;
        }
    }

    protected void a(Context context) {
        b(context);
        getHolder().setFormat(-3);
        setEGLConfigChooser(new c(8, 8, 8, 8, 0, 0));
        setEGLContextFactory(this.e);
        setEGLWindowSurfaceFactory(this.f);
        setRenderer(this.m329t7p6390828478440);
        this.m329t7p6390828478440.e();
        setRenderMode(0);
        getHolder().addCallback(this);
    }

    protected void a(f fVar) {
        this.g = fVar;
    }

    void a(g gVar) {
        this.h = gVar;
    }

    protected void a(boolean z) {
        VORenderer vORenderer = this.m329t7p6390828478440;
        if (vORenderer != null) {
            vORenderer.a(z);
        }
    }

    protected synchronized void b() {
        f fVar = this.g;
        if (fVar != null) {
            fVar.onRendererDeleted();
        }
        VORenderer vORenderer = this.m329t7p6390828478440;
        if (vORenderer != null) {
            queueEvent(new e(vORenderer));
            this.m329t7p6390828478440 = null;
        }
    }

    void c() {
        synchronized (this) {
            this.f101a = true;
            if (this.b) {
                g();
                this.b = false;
                if (this.h != null) {
                    this.h.onSurfaceTextureAvailable(this.c, -1, -1);
                }
            }
        }
    }

    h d() {
        return this.c;
    }

    protected boolean e() {
        return this.m329t7p6390828478440 != null;
    }

    @Deprecated
    public void onCreate() {
    }

    @Deprecated
    public void onDestroy() {
    }

    @Override // android.opengl.GLSurfaceView
    public void onPause() {
        setRenderMode(0);
        VORenderer vORenderer = this.m329t7p6390828478440;
        if (vORenderer != null) {
            vORenderer.f();
        }
    }

    @Override // android.opengl.GLSurfaceView
    public void onResume() {
        setRenderMode(1);
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        super.surfaceChanged(surfaceHolder, i2, i3, i4);
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        super.surfaceCreated(surfaceHolder);
        VORenderer vORenderer = this.m329t7p6390828478440;
        if (vORenderer != null) {
            vORenderer.d();
        }
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (this.m329t7p6390828478440 != null) {
            if (true == this.e.isDestroyed()) {
                this.m329t7p6390828478440.c();
            } else {
                b();
            }
        }
        this.c = null;
        super.surfaceDestroyed(surfaceHolder);
    }
}
