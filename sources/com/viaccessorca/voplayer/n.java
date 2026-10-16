package com.viaccessorca.voplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import androidx.work.Data;
import java.util.HashMap;
import java.util.Iterator;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import javax.microedition.khronos.opengles.GL11Ext;

/* JADX INFO: loaded from: classes2.dex */
class n implements GLSurfaceView.Renderer {
    private Context b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HashMap<Integer, a> f159a = new HashMap<>();
    private int c = 0;
    private int d = 0;
    private float e = 0.0f;
    private float f = 0.0f;
    private boolean g = false;
    private int h = 0;
    private int[] i = null;
    private int j = 0;

    class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f160a;
        Bitmap d = null;
        b b = b.UNKNOWN;
        int c = -1;

        a() {
        }

        void a(int i, Bitmap bitmap) {
            Bitmap bitmap2 = this.d;
            if (bitmap2 != null) {
                bitmap2.recycle();
                this.d = null;
            }
            this.d = bitmap;
        }

        void a(Context context, GL10 gl10) {
            if (this.d != null) {
                if (this.c >= 0) {
                    b(gl10);
                }
                this.c = n.this.a(context, gl10, this.d);
                this.d.recycle();
                this.d = null;
            }
            int i = this.c;
            if (i >= 0) {
                gl10.glBindTexture(3553, i);
                ((GL11Ext) gl10).glDrawTexfOES(n.this.e, n.this.f, 0.0f, n.this.c, n.this.d);
                n.this.h = (int) System.currentTimeMillis();
            }
        }

        void a(GL10 gl10) {
            Bitmap bitmap = this.d;
            if (bitmap != null) {
                bitmap.recycle();
                this.d = null;
            }
        }

        void b(GL10 gl10) {
            int i = this.c;
            if (i >= 0) {
                gl10.glDeleteTextures(1, new int[]{i}, 0);
                this.c = -1;
            }
        }
    }

    private enum b {
        UNKNOWN,
        SHOW,
        HIDE,
        CLEAN
    }

    protected n(Context context) {
        this.b = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(Context context, GL10 gl10, Bitmap bitmap) {
        if (context == null || gl10 == null || bitmap == null) {
            return -1;
        }
        int[] iArr = new int[1];
        gl10.glGenTextures(1, iArr, 0);
        int i = iArr[0];
        gl10.glBindTexture(3553, i);
        gl10.glTexParameterf(3553, 10241, 9728.0f);
        gl10.glTexParameterf(3553, Data.MAX_DATA_BYTES, 9729.0f);
        gl10.glTexParameterf(3553, 10242, 33071.0f);
        gl10.glTexParameterf(3553, 10243, 33071.0f);
        gl10.glTexEnvf(8960, 8704, 7681.0f);
        GLUtils.texImage2D(3553, 0, 6408, bitmap, 0);
        ((GL11) gl10).glTexParameteriv(3553, 35741, new int[]{0, bitmap.getHeight(), bitmap.getWidth(), -bitmap.getHeight()}, 0);
        return i;
    }

    protected void a() {
        this.g = true;
    }

    protected void a(float f, float f2, int i, int i2) {
        synchronized (this.f159a) {
            this.e = f;
            this.f = f2;
            this.c = i;
            this.d = i2;
        }
    }

    protected void a(int i, int i2) {
        b bVar;
        if (i2 != 20008) {
            switch (i2) {
                case 20002:
                    bVar = b.HIDE;
                    break;
                case 20003:
                    bVar = b.SHOW;
                    break;
                case 20004:
                    bVar = b.CLEAN;
                    break;
                default:
                    bVar = b.UNKNOWN;
                    break;
            }
        } else {
            bVar = b.HIDE;
            this.i = null;
            i = -1;
        }
        synchronized (this.f159a) {
            try {
                if (-1 == i) {
                    Iterator<Integer> it = this.f159a.keySet().iterator();
                    while (it.hasNext()) {
                        a aVar = this.f159a.get(it.next());
                        if (b.UNKNOWN != bVar && bVar != aVar.b) {
                            aVar.b = bVar;
                            aVar.f160a = true;
                        }
                    }
                } else {
                    if (!this.f159a.containsKey(Integer.valueOf(i))) {
                        this.f159a.put(Integer.valueOf(i), new a());
                    }
                    a aVar2 = this.f159a.get(Integer.valueOf(i));
                    if (b.UNKNOWN != bVar && bVar != aVar2.b) {
                        aVar2.b = bVar;
                        aVar2.f160a = true;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    protected void a(int i, Bitmap bitmap) {
        synchronized (this.f159a) {
            if (this.f159a.containsKey(Integer.valueOf(i))) {
                this.f159a.get(Integer.valueOf(i)).a(i, bitmap);
            } else {
                a aVar = new a();
                aVar.a(i, bitmap);
                this.f159a.put(Integer.valueOf(i), aVar);
            }
        }
    }

    protected void a(byte[] bArr, int i) {
        this.i = new int[bArr.length];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            this.i[i2] = bArr[i2];
        }
        this.j = 0;
    }

    protected int b() {
        return this.h;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        synchronized (this.f159a) {
            gl10.glClear(16384);
            if (this.g) {
                gl10.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                Iterator<Integer> it = this.f159a.keySet().iterator();
                while (it.hasNext()) {
                    a aVar = this.f159a.get(it.next());
                    if (aVar != null) {
                        aVar.a(gl10);
                        aVar.b(gl10);
                    }
                }
                this.f159a.clear();
            } else {
                gl10.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                if (this.f159a != null) {
                    gl10.glMatrixMode(5888);
                    int length = -1;
                    for (Integer num : this.f159a.keySet()) {
                        a aVar2 = this.f159a.get(num);
                        if (this.i != null) {
                            if (num.intValue() == this.i[this.j]) {
                                aVar2.b = b.SHOW;
                                length = (this.j + 1) % this.i.length;
                            }
                        }
                        if (b.SHOW == aVar2.b) {
                            aVar2.a(this.b, gl10);
                        }
                        if (b.CLEAN == aVar2.b) {
                            aVar2.a(gl10);
                            aVar2.b(gl10);
                        }
                    }
                    if (-1 != length) {
                        this.j = length;
                    }
                }
            }
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        gl10.glViewport(0, 0, i, i2);
        gl10.glMatrixMode(5889);
        gl10.glLoadIdentity();
        gl10.glOrthof(0.0f, i, 0.0f, i2, 0.0f, 1.0f);
        gl10.glShadeModel(7424);
        gl10.glEnable(3042);
        gl10.glBlendFunc(1, 1);
        gl10.glEnable(3553);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        gl10.glHint(3152, 4353);
        gl10.glEnable(3413);
        gl10.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl10.glShadeModel(7424);
        gl10.glDisable(2929);
        gl10.glEnable(3553);
        gl10.glDisable(3024);
        gl10.glDisable(2896);
    }
}
