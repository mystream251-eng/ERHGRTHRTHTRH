package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import androidx.work.Data;
import com.google.android.gms.ads.internal.zzbt;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzanl extends Thread implements SurfaceTexture.OnFrameAvailableListener, zzank {
    private static final float[] zza = {-1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f};
    private volatile boolean zzaa;
    private volatile boolean zzab;
    private final zzani zzb;
    private final float[] zzc;
    private final float[] zzd;
    private final float[] zze;
    private final float[] zzf;
    private final float[] zzg;
    private final float[] zzh;
    private final float[] zzi;
    private float zzj;
    private float zzk;
    private float zzl;
    private int zzm;
    private int zzn;
    private SurfaceTexture zzo;
    private SurfaceTexture zzp;
    private int zzq;
    private int zzr;
    private int zzs;
    private FloatBuffer zzt;
    private final CountDownLatch zzu;
    private final Object zzv;
    private EGL10 zzw;
    private EGLDisplay zzx;
    private EGLContext zzy;
    private EGLSurface zzz;

    public zzanl(Context context) {
        super("SphericalVideoProcessor");
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(zza.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.zzt = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.put(zza).position(0);
        this.zzc = new float[9];
        this.zzd = new float[9];
        this.zze = new float[9];
        this.zzf = new float[9];
        this.zzg = new float[9];
        this.zzh = new float[9];
        this.zzi = new float[9];
        this.zzj = Float.NaN;
        zzani zzaniVar = new zzani(context);
        this.zzb = zzaniVar;
        zzaniVar.zza(this);
        this.zzu = new CountDownLatch(1);
        this.zzv = new Object();
    }

    private static int zza(int i, String str) {
        int iGlCreateShader = GLES20.glCreateShader(i);
        zza("createShader");
        if (iGlCreateShader == 0) {
            return iGlCreateShader;
        }
        GLES20.glShaderSource(iGlCreateShader, str);
        zza("shaderSource");
        GLES20.glCompileShader(iGlCreateShader);
        zza("compileShader");
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(iGlCreateShader, 35713, iArr, 0);
        zza("getShaderiv");
        if (iArr[0] != 0) {
            return iGlCreateShader;
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Could not compile shader ");
        sb.append(i);
        sb.append(":");
        Log.e("SphericalVideoRenderer", sb.toString());
        Log.e("SphericalVideoRenderer", GLES20.glGetShaderInfoLog(iGlCreateShader));
        GLES20.glDeleteShader(iGlCreateShader);
        zza("deleteShader");
        return 0;
    }

    private static void zza(String str) {
        int iGlGetError = GLES20.glGetError();
        if (iGlGetError != 0) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21);
            sb.append(str);
            sb.append(": glError ");
            sb.append(iGlGetError);
            Log.e("SphericalVideoRenderer", sb.toString());
        }
    }

    private static void zza(float[] fArr, float f) {
        fArr[0] = 1.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        double d = f;
        fArr[4] = (float) Math.cos(d);
        fArr[5] = (float) (-Math.sin(d));
        fArr[6] = 0.0f;
        fArr[7] = (float) Math.sin(d);
        fArr[8] = (float) Math.cos(d);
    }

    private static void zza(float[] fArr, float[] fArr2, float[] fArr3) {
        fArr[0] = (fArr2[0] * fArr3[0]) + (fArr2[1] * fArr3[3]) + (fArr2[2] * fArr3[6]);
        fArr[1] = (fArr2[0] * fArr3[1]) + (fArr2[1] * fArr3[4]) + (fArr2[2] * fArr3[7]);
        fArr[2] = (fArr2[0] * fArr3[2]) + (fArr2[1] * fArr3[5]) + (fArr2[2] * fArr3[8]);
        fArr[3] = (fArr2[3] * fArr3[0]) + (fArr2[4] * fArr3[3]) + (fArr2[5] * fArr3[6]);
        fArr[4] = (fArr2[3] * fArr3[1]) + (fArr2[4] * fArr3[4]) + (fArr2[5] * fArr3[7]);
        fArr[5] = (fArr2[3] * fArr3[2]) + (fArr2[4] * fArr3[5]) + (fArr2[5] * fArr3[8]);
        fArr[6] = (fArr2[6] * fArr3[0]) + (fArr2[7] * fArr3[3]) + (fArr2[8] * fArr3[6]);
        fArr[7] = (fArr2[6] * fArr3[1]) + (fArr2[7] * fArr3[4]) + (fArr2[8] * fArr3[7]);
        fArr[8] = (fArr2[6] * fArr3[2]) + (fArr2[7] * fArr3[5]) + (fArr2[8] * fArr3[8]);
    }

    private static void zzb(float[] fArr, float f) {
        double d = f;
        fArr[0] = (float) Math.cos(d);
        fArr[1] = (float) (-Math.sin(d));
        fArr[2] = 0.0f;
        fArr[3] = (float) Math.sin(d);
        fArr[4] = (float) Math.cos(d);
        fArr[5] = 0.0f;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 1.0f;
    }

    private final void zzd() {
        while (this.zzs > 0) {
            this.zzo.updateTexImage();
            this.zzs--;
        }
        if (this.zzb.zza(this.zzc)) {
            if (Float.isNaN(this.zzj)) {
                float[] fArr = this.zzc;
                float[] fArr2 = {0.0f, 1.0f, 0.0f};
                float[] fArr3 = {(fArr[0] * fArr2[0]) + (fArr[1] * fArr2[1]) + (fArr[2] * fArr2[2]), (fArr[3] * fArr2[0]) + (fArr[4] * fArr2[1]) + (fArr[5] * fArr2[2]), (fArr[6] * fArr2[0]) + (fArr[7] * fArr2[1]) + (fArr[8] * fArr2[2])};
                this.zzj = -(((float) Math.atan2(fArr3[1], fArr3[0])) - 1.5707964f);
            }
            zzb(this.zzh, this.zzj + this.zzk);
        } else {
            zza(this.zzc, -1.5707964f);
            zzb(this.zzh, this.zzk);
        }
        zza(this.zzd, 1.5707964f);
        zza(this.zze, this.zzh, this.zzd);
        zza(this.zzf, this.zzc, this.zze);
        zza(this.zzg, this.zzl);
        zza(this.zzi, this.zzg, this.zzf);
        GLES20.glUniformMatrix3fv(this.zzr, 1, false, this.zzi, 0);
        GLES20.glDrawArrays(5, 0, 4);
        zza("drawArrays");
        GLES20.glFinish();
        this.zzw.eglSwapBuffers(this.zzx, this.zzz);
    }

    private final boolean zze() {
        EGLSurface eGLSurface = this.zzz;
        boolean zEglDestroyContext = false;
        if (eGLSurface != null && eGLSurface != EGL10.EGL_NO_SURFACE) {
            zEglDestroyContext = this.zzw.eglDestroySurface(this.zzx, this.zzz) | this.zzw.eglMakeCurrent(this.zzx, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT) | false;
            this.zzz = null;
        }
        EGLContext eGLContext = this.zzy;
        if (eGLContext != null) {
            zEglDestroyContext |= this.zzw.eglDestroyContext(this.zzx, eGLContext);
            this.zzy = null;
        }
        EGLDisplay eGLDisplay = this.zzx;
        if (eGLDisplay == null) {
            return zEglDestroyContext;
        }
        boolean zEglTerminate = zEglDestroyContext | this.zzw.eglTerminate(eGLDisplay);
        this.zzx = null;
        return zEglTerminate;
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.zzs++;
        synchronized (this.zzv) {
            this.zzv.notifyAll();
        }
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:8:0x0026  */
    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        boolean z;
        int iGlCreateProgram;
        if (this.zzp == null) {
            zzahw.zzc("SphericalVideoProcessor started with no output texture.");
            this.zzu.countDown();
            return;
        }
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        this.zzw = egl10;
        EGLDisplay eGLDisplayEglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        this.zzx = eGLDisplayEglGetDisplay;
        if (eGLDisplayEglGetDisplay == EGL10.EGL_NO_DISPLAY) {
            z = false;
        } else {
            if (this.zzw.eglInitialize(this.zzx, new int[2])) {
                int[] iArr = new int[1];
                EGLConfig[] eGLConfigArr = new EGLConfig[1];
                EGLConfig eGLConfig = (!this.zzw.eglChooseConfig(this.zzx, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12325, 16, 12344}, eGLConfigArr, 1, iArr) || iArr[0] <= 0) ? null : eGLConfigArr[0];
                if (eGLConfig == null) {
                    z = false;
                } else {
                    EGLContext eGLContextEglCreateContext = this.zzw.eglCreateContext(this.zzx, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
                    this.zzy = eGLContextEglCreateContext;
                    if (eGLContextEglCreateContext == null || eGLContextEglCreateContext == EGL10.EGL_NO_CONTEXT) {
                        z = false;
                    } else {
                        EGLSurface eGLSurfaceEglCreateWindowSurface = this.zzw.eglCreateWindowSurface(this.zzx, eGLConfig, this.zzp, null);
                        this.zzz = eGLSurfaceEglCreateWindowSurface;
                        if (eGLSurfaceEglCreateWindowSurface == null || eGLSurfaceEglCreateWindowSurface == EGL10.EGL_NO_SURFACE) {
                            z = false;
                        } else {
                            EGL10 egl11 = this.zzw;
                            EGLDisplay eGLDisplay = this.zzx;
                            EGLSurface eGLSurface = this.zzz;
                            if (egl11.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.zzy)) {
                                z = true;
                            } else {
                                z = false;
                            }
                        }
                    }
                }
            } else {
                z = false;
            }
        }
        zzny<String> zznyVar = zzoi.zzdb;
        int iZza = zza(35633, !((String) zzlc.zzf().zza(zznyVar)).equals(zznyVar.zzb()) ? (String) zzlc.zzf().zza(zznyVar) : "attribute highp vec3 aPosition;varying vec3 pos;void main() {  gl_Position = vec4(aPosition, 1.0);  pos = aPosition;}");
        if (iZza == 0) {
            iGlCreateProgram = 0;
        } else {
            zzny<String> zznyVar2 = zzoi.zzdc;
            int iZza2 = zza(35632, !((String) zzlc.zzf().zza(zznyVar2)).equals(zznyVar2.zzb()) ? (String) zzlc.zzf().zza(zznyVar2) : "#extension GL_OES_EGL_image_external : require\n#define INV_PI 0.3183\nprecision highp float;varying vec3 pos;uniform samplerExternalOES uSplr;uniform mat3 uVMat;uniform float uFOVx;uniform float uFOVy;void main() {  vec3 ray = vec3(pos.x * tan(uFOVx), pos.y * tan(uFOVy), -1);  ray = (uVMat * ray).xyz;  ray = normalize(ray);  vec2 texCrd = vec2(    0.5 + atan(ray.x, - ray.z) * INV_PI * 0.5, acos(ray.y) * INV_PI);  gl_FragColor = vec4(texture2D(uSplr, texCrd).xyz, 1.0);}");
            if (iZza2 == 0) {
                iGlCreateProgram = 0;
            } else {
                iGlCreateProgram = GLES20.glCreateProgram();
                zza("createProgram");
                if (iGlCreateProgram != 0) {
                    GLES20.glAttachShader(iGlCreateProgram, iZza);
                    zza("attachShader");
                    GLES20.glAttachShader(iGlCreateProgram, iZza2);
                    zza("attachShader");
                    GLES20.glLinkProgram(iGlCreateProgram);
                    zza("linkProgram");
                    int[] iArr2 = new int[1];
                    GLES20.glGetProgramiv(iGlCreateProgram, 35714, iArr2, 0);
                    zza("getProgramiv");
                    if (iArr2[0] != 1) {
                        Log.e("SphericalVideoRenderer", "Could not link program: ");
                        Log.e("SphericalVideoRenderer", GLES20.glGetProgramInfoLog(iGlCreateProgram));
                        GLES20.glDeleteProgram(iGlCreateProgram);
                        zza("deleteProgram");
                        iGlCreateProgram = 0;
                    } else {
                        GLES20.glValidateProgram(iGlCreateProgram);
                        zza("validateProgram");
                    }
                }
            }
        }
        this.zzq = iGlCreateProgram;
        GLES20.glUseProgram(iGlCreateProgram);
        zza("useProgram");
        int iGlGetAttribLocation = GLES20.glGetAttribLocation(this.zzq, "aPosition");
        GLES20.glVertexAttribPointer(iGlGetAttribLocation, 3, 5126, false, 12, (Buffer) this.zzt);
        zza("vertexAttribPointer");
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation);
        zza("enableVertexAttribArray");
        int[] iArr3 = new int[1];
        GLES20.glGenTextures(1, iArr3, 0);
        zza("genTextures");
        int i = iArr3[0];
        GLES20.glBindTexture(36197, i);
        zza("bindTextures");
        GLES20.glTexParameteri(36197, Data.MAX_DATA_BYTES, 9729);
        zza("texParameteri");
        GLES20.glTexParameteri(36197, 10241, 9729);
        zza("texParameteri");
        GLES20.glTexParameteri(36197, 10242, 33071);
        zza("texParameteri");
        GLES20.glTexParameteri(36197, 10243, 33071);
        zza("texParameteri");
        int iGlGetUniformLocation = GLES20.glGetUniformLocation(this.zzq, "uVMat");
        this.zzr = iGlGetUniformLocation;
        GLES20.glUniformMatrix3fv(iGlGetUniformLocation, 1, false, new float[]{1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f}, 0);
        boolean z2 = this.zzq != 0;
        if (!z || !z2) {
            String strValueOf = String.valueOf(GLUtils.getEGLErrorString(this.zzw.eglGetError()));
            String strConcat = strValueOf.length() != 0 ? "EGL initialization failed: ".concat(strValueOf) : new String("EGL initialization failed: ");
            zzahw.zzc(strConcat);
            zzbt.zzi().zza(new Throwable(strConcat), "SphericalVideoProcessor.run.1");
            zze();
            this.zzu.countDown();
            return;
        }
        SurfaceTexture surfaceTexture = new SurfaceTexture(i);
        this.zzo = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(this);
        this.zzu.countDown();
        this.zzb.zza();
        try {
            try {
                this.zzaa = true;
                while (!this.zzab) {
                    zzd();
                    if (this.zzaa) {
                        GLES20.glViewport(0, 0, this.zzn, this.zzm);
                        zza("viewport");
                        int iGlGetUniformLocation2 = GLES20.glGetUniformLocation(this.zzq, "uFOVx");
                        int iGlGetUniformLocation3 = GLES20.glGetUniformLocation(this.zzq, "uFOVy");
                        if (this.zzn > this.zzm) {
                            GLES20.glUniform1f(iGlGetUniformLocation2, 0.87266463f);
                            GLES20.glUniform1f(iGlGetUniformLocation3, (this.zzm * 0.87266463f) / this.zzn);
                        } else {
                            GLES20.glUniform1f(iGlGetUniformLocation2, (this.zzn * 0.87266463f) / this.zzm);
                            GLES20.glUniform1f(iGlGetUniformLocation3, 0.87266463f);
                        }
                        this.zzaa = false;
                    }
                    try {
                        synchronized (this.zzv) {
                            try {
                                if (!this.zzab && !this.zzaa && this.zzs == 0) {
                                    this.zzv.wait();
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    } catch (InterruptedException unused) {
                    }
                }
                this.zzb.zzb();
                this.zzo.setOnFrameAvailableListener(null);
                this.zzo = null;
                zze();
            } catch (Throwable th2) {
                this.zzb.zzb();
                this.zzo.setOnFrameAvailableListener(null);
                this.zzo = null;
                zze();
                throw th2;
            }
        } catch (IllegalStateException unused2) {
            zzahw.zze("SphericalVideoProcessor halted unexpectedly.");
            this.zzb.zzb();
            this.zzo.setOnFrameAvailableListener(null);
            this.zzo = null;
            zze();
        } catch (Throwable th3) {
            zzahw.zzb("SphericalVideoProcessor died.", th3);
            zzbt.zzi().zza(th3, "SphericalVideoProcessor.run.2");
            this.zzb.zzb();
            this.zzo.setOnFrameAvailableListener(null);
            this.zzo = null;
            zze();
        }
    }

    @Override // com.google.android.gms.internal.zzank
    public final void zza() {
        synchronized (this.zzv) {
            this.zzv.notifyAll();
        }
    }

    public final void zza(float f, float f2) {
        float f3;
        float f4;
        float f5;
        int i = this.zzn;
        int i2 = this.zzm;
        float f6 = f * 1.7453293f;
        if (i > i2) {
            f3 = f6 / i;
            f4 = f2 * 1.7453293f;
            f5 = i;
        } else {
            f3 = f6 / i2;
            f4 = f2 * 1.7453293f;
            f5 = i2;
        }
        this.zzk -= f3;
        float f7 = this.zzl - (f4 / f5);
        this.zzl = f7;
        if (f7 < -1.5707964f) {
            this.zzl = -1.5707964f;
        }
        if (this.zzl > 1.5707964f) {
            this.zzl = 1.5707964f;
        }
    }

    public final void zza(int i, int i2) {
        synchronized (this.zzv) {
            this.zzn = i;
            this.zzm = i2;
            this.zzaa = true;
            this.zzv.notifyAll();
        }
    }

    public final void zza(SurfaceTexture surfaceTexture, int i, int i2) {
        this.zzn = i;
        this.zzm = i2;
        this.zzp = surfaceTexture;
    }

    public final void zzb() {
        synchronized (this.zzv) {
            this.zzab = true;
            this.zzp = null;
            this.zzv.notifyAll();
        }
    }

    public final SurfaceTexture zzc() {
        if (this.zzp == null) {
            return null;
        }
        try {
            this.zzu.await();
        } catch (InterruptedException unused) {
        }
        return this.zzo;
    }
}
