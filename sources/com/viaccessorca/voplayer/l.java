package com.viaccessorca.voplayer;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import com.viaccessorca.common.VOLibraryLoader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
class l extends RelativeLayout {
    private static int P;
    private boolean A;
    private String B;
    private int C;
    private boolean D;
    private boolean E;
    private boolean F;
    private TextView G;
    private j H;
    private ImageView I;
    private Bitmap J;
    private boolean K;
    private int L;
    private int M;
    protected Handler N;
    private HashMap<Integer, Integer> O;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f149a;
    private int b;
    private AttributeSet c;
    private VOSurfaceView d;
    private VOSurfaceViewAmlogic e;
    private View f;
    private VOSurfaceViewOpenGL g;
    private k h;
    private o i;
    private SurfaceView j;
    private SurfaceView k;
    private boolean l;
    private Bitmap m;
    private ImageView n;
    private TextView o;
    private TextView p;
    private boolean q;
    private Object r;
    private float s;
    private float t;
    private float u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 0) {
                if (i != 1) {
                    return;
                }
                l.this.t();
                return;
            }
            if (l.this.f != null) {
                l lVar = l.this;
                lVar.setPadding(((m) lVar.f).b(), ((m) l.this.f).d(), ((m) l.this.f).c(), ((m) l.this.f).a());
            } else if (l.this.h != null) {
                l lVar2 = l.this;
                lVar2.setPadding(lVar2.h.b(), l.this.h.d(), l.this.h.c(), l.this.h.a());
                l.this.h.e();
            }
            l.this.v();
            l.this.N.sendMessage(l.this.N.obtainMessage(1));
        }
    }

    class b implements SurfaceHolder.Callback {
        b(l lVar) {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            l.this.s();
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            l.this.s();
        }
    }

    class e implements SurfaceHolder.Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Handler f153a;

        e(Handler handler) {
            this.f153a = handler;
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            this.f153a.dispatchMessage(this.f153a.obtainMessage(12002, l.this.d));
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        }
    }

    class f implements TextureView.SurfaceTextureListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Handler f154a;

        f(Handler handler) {
            this.f154a = handler;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            l.this.b(this.f154a, surfaceTexture);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            return l.this.r == null;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            if (l.this.r == null) {
                l.this.b(this.f154a, surfaceTexture);
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            if (l.this.r == null) {
                l.this.b(this.f154a, surfaceTexture);
            }
        }
    }

    class g implements VOSurfaceViewOpenGL.g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Handler f155a;

        g(Handler handler) {
            this.f155a = handler;
        }

        @Override // com.viaccessorca.voplayer.VOSurfaceViewOpenGL.g
        public void onSurfaceTextureAvailable(Object obj, int i, int i2) {
            l.this.b(this.f155a, obj);
        }

        public boolean onSurfaceTextureDestroyed(Object obj) {
            return l.this.r == null;
        }

        public void onSurfaceTextureSizeChanged(Object obj, int i, int i2) {
            if (l.this.r == null) {
                l.this.b(this.f155a, obj);
            }
        }

        public void onSurfaceTextureUpdated(Object obj) {
            if (l.this.r == null) {
                l.this.b(this.f155a, obj);
            }
        }
    }

    class h implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        VOSurfaceViewOpenGL f156a;

        h() {
            this.f156a = l.this.g;
        }

        @Override // java.lang.Runnable
        public void run() {
            l.this.removeView(this.f156a);
        }
    }

    class i implements SurfaceHolder.Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Handler f157a;

        i(Handler handler) {
            this.f157a = handler;
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            this.f157a.dispatchMessage(this.f157a.obtainMessage(12000, l.this.d));
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        }
    }

    protected l(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f149a = null;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = false;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = true;
        this.r = null;
        this.s = 1.0f;
        this.t = 1.0f;
        this.u = 1.0f;
        this.x = -1;
        this.y = -1;
        this.A = false;
        this.B = null;
        this.C = 0;
        this.D = false;
        this.E = true;
        this.F = false;
        this.G = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = true;
        this.L = -1;
        this.M = -1;
        this.N = new a(Looper.getMainLooper());
        this.O = null;
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        P++;
        this.f149a = context;
        this.c = attributeSet;
        try {
            VOSystemInfo vOSystemInfo = new VOSystemInfo();
            VOSystemInfoRetriever.f(context, vOSystemInfo);
            this.L = vOSystemInfo.screenWidth;
            this.M = vOSystemInfo.screenHeight;
        } catch (Exception unused) {
            this.L = -1;
            this.M = -1;
        }
        v();
        VOLibraryLoader.b();
        if (Build.VERSION.SDK_INT < 19) {
            this.E = false;
        } else {
            this.E = true;
        }
        if (!this.l) {
            SurfaceView surfaceView = new SurfaceView(this.f149a, this.c);
            this.j = surfaceView;
            a(surfaceView);
            this.j.getHolder().addCallback(new b(this));
            addView(this.j, 0);
            this.l = true;
            try {
                ((Activity) this.f149a).getWindow().setFlags(16777216, 16777216);
            } catch (ClassCastException unused2) {
            }
        }
        if (this.k == null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -1;
            SurfaceView surfaceView2 = new SurfaceView(this.f149a, this.c);
            this.k = surfaceView2;
            surfaceView2.setLayoutParams(layoutParams);
            this.k.setAlpha(0.01f);
            this.k.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            addView(this.k, 0);
        }
    }

    private void a(View view) {
        if (1 != view.getHeight()) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
            layoutParams.width = Build.MODEL.startsWith("SEI-S90") ? -1 : 2;
            layoutParams.height = 1;
            view.setLayoutParams(layoutParams);
            if (Build.VERSION.SDK_INT > 10) {
                view.setAlpha(0.0f);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Handler handler, Object obj) {
        this.r = obj;
        handler.dispatchMessage(handler.obtainMessage(12001, null));
    }

    /* JADX WARN: Code duplicated, block: B:42:0x00cb  */
    private void b(View view) {
        if (((getWidth() - getPaddingLeft()) - getPaddingRight() == (view.getWidth() + view.getPaddingLeft()) + view.getPaddingRight() && (getHeight() - getPaddingTop()) - getPaddingBottom() == (view.getHeight() + view.getPaddingTop()) + view.getPaddingBottom()) ? false : true) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -1;
            view.setLayoutParams(layoutParams);
            if (Build.VERSION.SDK_INT > 10) {
                view.setAlpha(1.0f);
            }
        }
        View view2 = null;
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        while (i2 < getChildCount()) {
            View childAt = getChildAt(i2);
            if (childAt == view) {
                z2 = true;
            }
            if (true == z2) {
                if (childAt.getClass() == q.class || childAt.getClass() == o.class || childAt.getClass() == p.class) {
                    if (view2 != view) {
                        z = true;
                    }
                } else if (childAt.getClass() == VOSurfaceViewAmlogic.class || childAt.getClass() == VOSurfaceViewOpenGL.class || childAt.getClass() == k.class || childAt.getClass() == m.class || childAt.getClass() == q.class || childAt.getClass() == o.class || childAt.getClass() == p.class) {
                    z = true;
                } else {
                    arrayList.add(childAt);
                }
            }
            i2++;
            view2 = childAt;
        }
        if (z) {
            bringChildToFront(view);
            bringChildToFront(this.i);
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                bringChildToFront((View) arrayList.get(i3));
            }
        }
        arrayList.clear();
    }

    private void c(boolean z) {
        if (true != z) {
            VOSurfaceViewOpenGL vOSurfaceViewOpenGL = this.g;
            if (vOSurfaceViewOpenGL != null) {
                vOSurfaceViewOpenGL.a(false);
                a(this.g);
                this.g.onPause();
            }
            this.q = false;
            return;
        }
        this.q = true;
        View view = this.f;
        if (view != null) {
            view.setVisibility(4);
        }
        k kVar = this.h;
        if (kVar != null) {
            a(kVar);
        }
        VOSurfaceViewOpenGL vOSurfaceViewOpenGL2 = this.g;
        if (vOSurfaceViewOpenGL2 != null) {
            vOSurfaceViewOpenGL2.setAlpha(1.0f);
            this.g.a(true);
            b(this.g);
            this.g.onResume();
        }
    }

    private void p() {
        try {
            this.g = q() ? new VOSurfaceViewOpenGL(this.f149a, null) : new VOSurfaceViewOpenGL(this.f149a, this.c);
        } catch (Exception unused) {
        }
    }

    private boolean q() {
        return Build.VERSION.SDK_INT >= 29 && 1 == Settings.Global.getInt(this.f149a.getContentResolver(), "development_settings_enabled", 0);
    }

    private void r() {
        VOSurfaceViewAmlogic vOSurfaceViewAmlogic = this.e;
        if (vOSurfaceViewAmlogic != null) {
            vOSurfaceViewAmlogic.a(this.t);
            this.e.a(this.z, this.w, this.v);
        } else {
            View view = this.f;
            if (view != null) {
                ((m) view).a(this.t);
                ((m) this.f).a(getWidth(), getHeight());
                ((m) this.f).a(this.z, this.w, this.v);
            } else {
                k kVar = this.h;
                if (kVar != null) {
                    kVar.a(this.t);
                    int width = getWidth();
                    int height = getHeight();
                    if ((this.b != 1 || getHeight() >= getWidth()) && this.b != 1) {
                        getWidth();
                        getHeight();
                    }
                    this.h.a(width, height);
                    this.h.a(this.z, this.w, this.v);
                }
            }
        }
        this.N.removeCallbacksAndMessages(null);
        this.N.sendMessage(this.N.obtainMessage(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
        layout(getLeft(), getTop(), getRight(), getBottom());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        o oVar = this.i;
        if (oVar != null) {
            if (this.f == null && this.h == null) {
                oVar.a(this.z, this.w, this.v, this.t, getWidth(), getHeight());
            } else {
                this.i.a(getPaddingLeft(), getPaddingTop(), getWidth(), getHeight());
            }
        }
    }

    private void u() {
        this.z = 0;
        this.O = new HashMap<>(0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(11, -1);
        layoutParams.addRule(10, -1);
        layoutParams.width = -1;
        layoutParams.height = -1;
        if (this.H == null) {
            j jVar = new j(this.f149a);
            this.H = jVar;
            addView(jVar, layoutParams);
            this.H.setClickable(false);
            this.H.setFocusable(false);
            this.H.setEnabled(false);
            this.H.setBackgroundColor(0);
            this.H.clearFocus();
            this.H.setVisibility(8);
            this.H.a(this.B);
        }
        if (this.G == null) {
            TextView textView = new TextView(this.f149a);
            this.G = textView;
            textView.setVisibility(8);
            addView(this.G);
        }
        if (this.I == null) {
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams2.addRule(12, -1);
            layoutParams2.width = -1;
            layoutParams2.height = -2;
            ImageView imageView = new ImageView(this.f149a);
            this.I = imageView;
            imageView.setVisibility(8);
            this.I.setImageBitmap(null);
            this.I.setScaleType(ImageView.ScaleType.FIT_CENTER);
            addView(this.I, layoutParams2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:37:0x0079 A[PHI: r5
  0x0079: PHI (r5v7 int) = (r5v6 int), (r5v6 int), (r5v9 int) binds: [B:21:0x0048, B:23:0x004c, B:35:0x006d] A[DONT_GENERATE, DONT_INLINE]] */
    public void v() {
        int i2;
        int i3;
        int i4;
        int iRound;
        int i5;
        if (this.H == null) {
            return;
        }
        boolean z = this.f == null && this.h == null;
        this.C = 0;
        int height = getHeight();
        int width = getWidth();
        float f2 = height;
        float f3 = width;
        float f4 = f2 / f3;
        int i6 = this.v;
        if (i6 == 0 || (i3 = this.w) == 0) {
            i2 = 0;
        } else {
            float f5 = i6;
            float f6 = i3;
            float f7 = this.t;
            float f8 = (f5 / f6) / f7;
            float f9 = f2 / f5;
            float f10 = f3 / (f6 * f7);
            if (f9 < f10) {
                i4 = (int) (f6 * f9 * f7);
                i2 = (int) (f5 * f9);
            } else {
                int i7 = (int) (f6 * f10 * f7);
                int i8 = (int) (f5 * f10);
                i4 = i7;
                i2 = i8;
            }
            int i9 = this.M;
            if (i9 <= 0 || (i5 = this.L) <= 0) {
                iRound = 0;
            } else {
                int i10 = this.x;
                if (i10 > 0) {
                    i5 = i10;
                }
                int i11 = this.y;
                if (i11 > 0) {
                    i9 = i11;
                }
                if (this.b == 1) {
                    i9 = this.L;
                    i5 = this.M;
                }
                if (i2 > i9) {
                    this.C = Math.round((i2 - i9) / 2);
                    i2 = i9;
                }
                if (i4 > i5) {
                    iRound = Math.round((i4 - i5) / 2);
                    i4 = i5;
                } else {
                    iRound = 0;
                }
            }
            if (z) {
                if (this.z != 0) {
                    this.C = 0;
                    iRound = 0;
                } else if (f8 < f4) {
                    this.C = Math.round((height - i2) / 2);
                } else {
                    iRound = Math.round((width - i4) / 2);
                }
                TextView textView = this.G;
                int i12 = this.C;
                textView.setPadding(iRound, i12, iRound, i12);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                int i13 = this.C;
                layoutParams.setMargins(iRound, i13, iRound, i13);
                this.H.setLayoutParams(layoutParams);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.I.getLayoutParams();
                layoutParams2.setMargins(iRound, 0, iRound, this.C);
                this.I.setLayoutParams(layoutParams2);
                requestLayout();
            } else {
                int i14 = this.C;
                int i15 = getPaddingBottom() < 0 ? -getPaddingBottom() : i14;
                if (getPaddingTop() < 0) {
                    i14 = -getPaddingTop();
                }
                int i16 = getPaddingLeft() < 0 ? -getPaddingLeft() : iRound;
                if (getPaddingRight() < 0) {
                    iRound = -getPaddingRight();
                }
                RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams3.setMargins(i16, i14, iRound, i15);
                this.H.setLayoutParams(layoutParams3);
            }
        }
        this.G.setTextSize(0, (int) ((((double) i2) / 15.0d) * 0.85d));
        a(this.A);
    }

    protected final void a() {
        j jVar = this.H;
        if (jVar != null) {
            jVar.a();
            this.H.setVisibility(8);
        }
        TextView textView = this.G;
        if (textView != null) {
            textView.setVisibility(8);
        }
        ImageView imageView = this.I;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        HashMap<Integer, Integer> map = this.O;
        if (map != null) {
            map.clear();
        }
    }

    protected final void a(int i2) {
        this.H.a(i2);
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0078  */
    /* JADX WARN: Code duplicated, block: B:22:0x007f  */
    /* JADX WARN: Code duplicated, block: B:24:? A[RETURN, SYNTHETIC] */
    protected void a(int i2, int i3, int i4) {
        VOSurfaceView vOSurfaceView;
        if (i2 >= 0) {
            this.z = i2;
            this.v = i4;
            this.w = i3;
        }
        VOSurfaceViewAmlogic vOSurfaceViewAmlogic = this.e;
        boolean z = true;
        if (vOSurfaceViewAmlogic == null) {
            View view = this.f;
            if (view != null) {
                ((m) view).a(this.t);
                ((m) this.f).a(this.z, this.w, this.v);
            } else {
                k kVar = this.h;
                if (kVar != null) {
                    kVar.a(this.t);
                    this.h.a(this.z, this.w, this.v);
                } else {
                    VOSurfaceViewOpenGL vOSurfaceViewOpenGL = this.g;
                    if (vOSurfaceViewOpenGL != null) {
                        vOSurfaceViewOpenGL.a(this.t);
                        this.g.a(this.z, this.w, this.v);
                    }
                }
            }
            this.N.removeCallbacksAndMessages(null);
            this.N.sendMessage(this.N.obtainMessage(0));
            if (!z) {
                t();
            }
            vOSurfaceView = this.d;
            if (vOSurfaceView != null) {
                vOSurfaceView.f();
            }
        }
        vOSurfaceViewAmlogic.a(this.t);
        this.e.a(this.z, this.w, this.v);
        v();
        z = false;
        if (!z) {
            t();
        }
        vOSurfaceView = this.d;
        if (vOSurfaceView != null) {
            vOSurfaceView.f();
        }
    }

    protected final void a(int i2, int i3, VOSubtitle vOSubtitle) {
        com.viaccessorca.voplayer.i.d dVarB;
        b();
        if (2100 != i2) {
            if (2200 == i2) {
                Integer num = this.O.get(Integer.valueOf(i3));
                if (num != null) {
                    if (2 == num.intValue()) {
                        this.G.setVisibility(8);
                    } else if (5 == num.intValue()) {
                        this.I.setVisibility(8);
                        Bitmap bitmap = this.J;
                        if (bitmap != null) {
                            bitmap.recycle();
                            this.J = null;
                        }
                    } else {
                        this.H.b(i3);
                    }
                }
                this.O.remove(Integer.valueOf(i3));
                return;
            }
            return;
        }
        if (vOSubtitle == null) {
            this.G.setVisibility(8);
            this.H.setVisibility(8);
            this.I.setVisibility(8);
            return;
        }
        this.O.put(Integer.valueOf(i3), Integer.valueOf(vOSubtitle.getType()));
        if (vOSubtitle.getType() == 2) {
            com.viaccessorca.voplayer.i.a(vOSubtitle, this.G);
            this.H.setVisibility(8);
            this.I.setVisibility(8);
            this.G.setVisibility(0);
            return;
        }
        if (5 != vOSubtitle.getType()) {
            if (6 == vOSubtitle.getType()) {
                dVarB = com.viaccessorca.voplayer.i.b(vOSubtitle);
                if (vOSubtitle.getTtmlStylingBuffer() != null) {
                    this.H.a(i3, dVarB, vOSubtitle.getTtmlStylingBuffer());
                } else {
                    this.H.a(i3, dVarB);
                }
            } else if (3 == vOSubtitle.getType()) {
                com.viaccessorca.voplayer.i.d dVarB2 = com.viaccessorca.voplayer.i.b(vOSubtitle);
                VOSubtitleSettingsAttributes settingsAttributes = vOSubtitle.getSettingsAttributes();
                this.H.a(i3, dVarB2, settingsAttributes.getSnapToLine(), settingsAttributes.getLine());
            } else {
                dVarB = com.viaccessorca.voplayer.i.b(vOSubtitle);
                this.H.a(i3, dVarB);
            }
            this.G.setVisibility(8);
            this.I.setVisibility(8);
            this.H.setVisibility(0);
            return;
        }
        Bitmap bitmap2 = this.J;
        if (bitmap2 != null) {
            bitmap2.recycle();
            this.J = null;
        }
        int positionX2 = vOSubtitle.getSettingsAttributes().getPositionX2() - vOSubtitle.getSettingsAttributes().getPositionX1();
        int positionY2 = vOSubtitle.getSettingsAttributes().getPositionY2() - vOSubtitle.getSettingsAttributes().getPositionY1();
        this.J = Bitmap.createBitmap(positionX2, positionY2, Bitmap.Config.ARGB_8888);
        int i4 = positionX2 * positionY2;
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(vOSubtitle.getRAWText());
        byteBufferWrap.clear();
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i5 * 4;
            byte b2 = byteBufferWrap.get(i6);
            int i7 = i6 + 1;
            byte b3 = byteBufferWrap.get(i7);
            if (b2 == 0 && -120 == b3) {
                byteBufferWrap.put(i7, (byte) 0);
                byteBufferWrap.put(i6 + 2, (byte) 0);
                byteBufferWrap.put(i6 + 3, (byte) 0);
            }
        }
        this.J.copyPixelsFromBuffer(byteBufferWrap);
        int width = (int) ((((double) this.I.getWidth()) / ((double) positionX2)) * ((double) positionY2));
        ViewGroup.LayoutParams layoutParams = this.I.getLayoutParams();
        layoutParams.height = width;
        this.I.setLayoutParams(layoutParams);
        this.I.setImageBitmap(this.J);
        this.G.setVisibility(8);
        this.H.setVisibility(8);
        this.I.setVisibility(0);
    }

    /* JADX WARN: Code duplicated, block: B:21:0x004e  */
    /* JADX WARN: Code duplicated, block: B:23:0x0055  */
    /* JADX WARN: Code duplicated, block: B:26:0x0068  */
    /* JADX WARN: Code duplicated, block: B:27:0x006d  */
    protected void a(int i2, Handler handler) {
        boolean z;
        boolean z2;
        k kVar;
        View view;
        VOSurfaceViewOpenGL vOSurfaceViewOpenGL;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        boolean z3 = true;
        if (!VOLibraryLoader.a() || 6 != i2) {
            if (1 == i2) {
                if (this.f == null) {
                    a(handler, (Object) null);
                    z2 = false;
                    z = true;
                }
            } else if (7 == i2 && this.h == null) {
                b(handler);
                z = false;
                z2 = true;
            }
            if (this.g == null) {
                p();
                vOSurfaceViewOpenGL = this.g;
                if (vOSurfaceViewOpenGL != null) {
                    vOSurfaceViewOpenGL.setAlpha(this.u);
                    this.g.setLayoutParams(layoutParams);
                    addView(this.g, 0);
                }
            }
            if (8 == i2) {
                a(handler);
                z = true;
            } else {
                z3 = z2;
            }
            if (z3 && (view = this.f) != null) {
                view.setVisibility(4);
                this.f = null;
            }
            if (z && (kVar = this.h) != null) {
                kVar.setVisibility(4);
                this.h = null;
            }
            post(new c());
            setSecure(this.E);
        }
        if (this.e == null) {
            VOSurfaceViewAmlogic vOSurfaceViewAmlogic = new VOSurfaceViewAmlogic(this.f149a, this.c);
            this.e = vOSurfaceViewAmlogic;
            vOSurfaceViewAmlogic.setLayoutParams(layoutParams);
            addView(this.e, 0);
        }
        z2 = false;
        z = false;
        if (this.g == null) {
            p();
            vOSurfaceViewOpenGL = this.g;
            if (vOSurfaceViewOpenGL != null) {
                vOSurfaceViewOpenGL.setAlpha(this.u);
                this.g.setLayoutParams(layoutParams);
                addView(this.g, 0);
            }
        }
        if (8 == i2) {
            a(handler);
            z = true;
        } else {
            z3 = z2;
        }
        if (z3) {
            view.setVisibility(4);
            this.f = null;
        }
        if (z) {
            kVar.setVisibility(4);
            this.h = null;
        }
        post(new c());
        setSecure(this.E);
    }

    protected void a(Handler handler) {
        this.r = null;
        VOSurfaceViewOpenGL vOSurfaceViewOpenGL = this.g;
        if (vOSurfaceViewOpenGL != null) {
            vOSurfaceViewOpenGL.a(new g(handler));
            Object objA = this.g.a();
            if (objA != null) {
                b(handler, objA);
            }
        }
    }

    protected void a(Handler handler, Object obj) {
        View view = this.f;
        if (view != null) {
            removeView(view);
        }
        m mVar = new m(this.f149a);
        if (obj != null) {
            mVar.setSurfaceTexture((SurfaceTexture) obj);
        }
        mVar.setSurfaceTextureListener(new f(handler));
        if (mVar.isAvailable() && this.r == null) {
            b(handler, mVar.getSurfaceTexture());
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        mVar.setLayoutParams(layoutParams);
        mVar.setScaleX(1.00001f);
        addView(mVar, 0);
        this.f = mVar;
        r();
        s();
    }

    protected final void a(VOPlayer.AdInformation adInformation) {
        this.H.a(adInformation);
        this.H.setVisibility(0);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x005e  */
    /* JADX WARN: Code duplicated, block: B:26:0x00bd  */
    protected void a(VOPlayer.VOQuarterInfo vOQuarterInfo) {
        float f2;
        k kVar = this.h;
        if (kVar != null) {
            int iB = kVar.b();
            int iC = this.h.c();
            this.h.a();
            this.h.d();
            int width = this.d.getWidth() / 2;
            int height = this.d.getHeight() / 2;
            VOPlayer.VOPlayerQuarterDisplay vOPlayerQuarterDisplay = vOQuarterInfo.quarter;
            float f3 = 0.0f;
            float f4 = 1.0f;
            float f5 = 2.0f;
            if (vOPlayerQuarterDisplay != VOPlayer.VOPlayerQuarterDisplay.TOP_LEFT) {
                if (vOPlayerQuarterDisplay == VOPlayer.VOPlayerQuarterDisplay.TOP_RIGHT) {
                    f3 = (-width) + iC;
                } else {
                    if (vOPlayerQuarterDisplay == VOPlayer.VOPlayerQuarterDisplay.BOTTOM_LEFT) {
                        f3 = width - iB;
                    } else if (vOPlayerQuarterDisplay == VOPlayer.VOPlayerQuarterDisplay.BOTTOM_RIGHT) {
                        f3 = (-width) + iC;
                    } else if (vOPlayerQuarterDisplay == VOPlayer.VOPlayerQuarterDisplay.FULL) {
                        f2 = 0.0f;
                    } else {
                        f2 = 0.0f;
                        f5 = 1.0f;
                        f4 = 2.0f;
                    }
                    f2 = -height;
                    f5 = 1.0f;
                    f4 = 2.0f;
                }
                if (vOQuarterInfo.isAnimationEnable) {
                    ObjectAnimator.ofFloat(this.h, "scaleX", f5, f4).setDuration(vOQuarterInfo.animationDuration).start();
                    ObjectAnimator.ofFloat(this.h, "scaleY", f5, f4).setDuration(vOQuarterInfo.animationDuration).start();
                    ObjectAnimator.ofFloat(this.h, "translationX", f3).setDuration(vOQuarterInfo.animationDuration).start();
                    ObjectAnimator.ofFloat(this.h, "translationY", f2).setDuration(vOQuarterInfo.animationDuration).start();
                    return;
                }
                this.h.setScaleX(f4);
                this.h.setScaleY(f4);
                this.h.setTranslationX(f3);
                this.h.setTranslationY(f2);
            }
            f3 = width - iB;
            f2 = height;
            f5 = 1.0f;
            f4 = 2.0f;
            if (vOQuarterInfo.isAnimationEnable) {
                ObjectAnimator.ofFloat(this.h, "scaleX", f5, f4).setDuration(vOQuarterInfo.animationDuration).start();
                ObjectAnimator.ofFloat(this.h, "scaleY", f5, f4).setDuration(vOQuarterInfo.animationDuration).start();
                ObjectAnimator.ofFloat(this.h, "translationX", f3).setDuration(vOQuarterInfo.animationDuration).start();
                ObjectAnimator.ofFloat(this.h, "translationY", f2).setDuration(vOQuarterInfo.animationDuration).start();
                return;
            }
            this.h.setScaleX(f4);
            this.h.setScaleY(f4);
            this.h.setTranslationX(f3);
            this.h.setTranslationY(f2);
        }
    }

    protected void a(VOSurfaceView vOSurfaceView) {
        this.d = vOSurfaceView;
    }

    protected void a(String str) {
        this.B = str;
        j jVar = this.H;
        if (jVar != null) {
            jVar.a(str);
        }
    }

    protected void a(ByteBuffer byteBuffer, int i2, int i3, int i4, int i5) {
        View view = this.n;
        if (view != null) {
            removeView(view);
            this.n = null;
        }
        if (byteBuffer != null) {
            int iRound = Math.round(this.s * i2);
            int iRound2 = Math.round(this.s * i3);
            ImageView imageView = new ImageView(this.f149a);
            this.n = imageView;
            imageView.setImageBitmap(null);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
            layoutParams.width = iRound;
            layoutParams.height = iRound2;
            layoutParams.topMargin = (getHeight() * i4) / 100;
            layoutParams.leftMargin = (getWidth() * i5) / 100;
            addView(this.n, layoutParams);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
            this.m = bitmapCreateBitmap;
            bitmapCreateBitmap.copyPixelsFromBuffer(byteBuffer);
            this.n.setImageBitmap(this.m);
        }
    }

    protected void a(boolean z) {
        this.A = z;
        j jVar = this.H;
        if (jVar != null) {
            if (!z || this.C >= 50) {
                this.H.a(false);
            } else {
                jVar.a(true);
            }
        }
    }

    protected boolean a(float f2) {
        this.u = f2;
        SurfaceView surfaceView = this.k;
        if (surfaceView == null) {
            return true;
        }
        surfaceView.setAlpha(f2);
        return true;
    }

    protected boolean a(int i2, int i3) {
        o oVar = this.i;
        if (oVar == null) {
            return false;
        }
        oVar.a(i2, i3);
        return true;
    }

    protected boolean a(int i2, byte[] bArr, int i3, int i4, int i5, int i6) {
        if (this.i == null) {
            return false;
        }
        if (this.f != null) {
            throw new UnsupportedOperationException();
        }
        if (2 == i6) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i3, i4, Bitmap.Config.RGB_565);
            bitmapCreateBitmap.copyPixelsFromBuffer(ByteBuffer.wrap(bArr));
            this.i.a(i2, bitmapCreateBitmap, i5);
            return true;
        }
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap2.copyPixelsFromBuffer(ByteBuffer.wrap(bArr));
        this.i.a(i2, bitmapCreateBitmap2, -1);
        return true;
    }

    protected boolean a(byte[] bArr, int i2) {
        o oVar = this.i;
        if (oVar == null) {
            return false;
        }
        if (this.f != null) {
            throw new UnsupportedOperationException();
        }
        oVar.a(bArr, i2);
        return true;
    }

    protected void b() {
        if (this.H == null) {
            u();
            v();
        }
    }

    protected void b(float f2) {
        this.t = f2;
        if (0.0f == f2) {
            this.t = 1.0f;
        }
        v();
    }

    protected void b(int i2) {
        boolean z = true;
        try {
            if (1 == i2) {
                if (this.f != null) {
                    this.f.setVisibility(0);
                    z = false;
                }
            } else if (7 == i2) {
                if (this.h != null) {
                    b(this.h);
                    z = false;
                }
            } else if (6 == i2 && this.e != null) {
                this.e.setVisibility(0);
                z = false;
            }
            if (this.k != null) {
                this.k.setVisibility(4);
            }
            c(z);
            if (z) {
                b(false);
            }
            post(new d());
        } catch (Exception unused) {
        }
    }

    protected void b(int i2, int i3) {
        j jVar = this.H;
        if (jVar != null) {
            jVar.a(i2, i3);
        }
    }

    protected void b(Handler handler) {
        if (this.h == null) {
            this.h = q() ? new k(this.f149a, null) : new k(this.f149a, this.c);
            a(this.h);
            this.h.getHolder().addCallback(new e(handler));
            this.h.a(this.t);
            int width = getWidth();
            int height = getHeight();
            if ((this.b != 1 || getHeight() >= getWidth()) && this.b != 1) {
                getWidth();
                getHeight();
            }
            this.h.a(width, height);
            addView(this.h, 0);
            s();
        }
    }

    protected void b(String str) {
        if (this.p != null || str == null) {
            return;
        }
        int width = (getWidth() * 85) / 100;
        int height = (getHeight() * 98) / 100;
        int iRound = Math.round(this.s * width);
        int iRound2 = Math.round(this.s * height);
        this.p = new TextView(this.f149a);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
        layoutParams.width = iRound;
        layoutParams.height = iRound2;
        layoutParams.topMargin = (getHeight() * 2) / 100;
        layoutParams.leftMargin = (getWidth() * 2) / 100;
        this.p.setTypeface(Typeface.DEFAULT, 1);
        this.p.setTextSize(2, 12.0f);
        this.p.setTextColor(SupportMenu.CATEGORY_MASK);
        this.p.setShadowLayer(2.0f, 0.0f, 0.0f, ViewCompat.MEASURED_STATE_MASK);
        addView(this.p, layoutParams);
        this.p.setText(str);
    }

    protected void b(boolean z) {
        if (z != this.F) {
            this.F = z;
            c((String) null);
        }
    }

    protected void c() {
        if (this.i == null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -1;
            o oVar = new o(this.f149a, this.c);
            this.i = oVar;
            if (Build.VERSION.SDK_INT >= 17) {
                oVar.setSecure(this.E);
            }
            this.i.setLayoutParams(layoutParams);
            addView(this.i, 0);
            this.N.sendMessage(this.N.obtainMessage(1));
        }
    }

    protected void c(int i2, int i3) {
        this.x = i2;
        this.y = i3;
    }

    protected final void c(Handler handler) {
        if (handler == null) {
            return;
        }
        VOSurfaceViewOpenGL vOSurfaceViewOpenGL = this.g;
        if (vOSurfaceViewOpenGL != null) {
            vOSurfaceViewOpenGL.setVisibility(4);
            post(new h());
        }
        this.g = null;
        p();
        VOSurfaceViewOpenGL vOSurfaceViewOpenGL2 = this.g;
        if (vOSurfaceViewOpenGL2 != null) {
            vOSurfaceViewOpenGL2.setAlpha(this.u);
            this.g.getHolder().addCallback(new i(handler));
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        addView(this.g, 0, layoutParams);
        s();
        setSecure(this.E);
    }

    protected void c(String str) {
        if (this.F && str == null) {
            str = "DEMO";
        }
        View view = this.o;
        if (view != null && str == null) {
            removeView(view);
            this.o = null;
        } else if (this.o == null && str != null) {
            int width = (getWidth() * 85) / 100;
            int height = (getHeight() * 98) / 100;
            int iRound = Math.round(this.s * width);
            int iRound2 = Math.round(this.s * height);
            this.o = new TextView(this.f149a);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
            layoutParams.width = iRound;
            layoutParams.height = iRound2;
            layoutParams.topMargin = (getHeight() * 2) / 100;
            layoutParams.leftMargin = (getWidth() * 2) / 100;
            this.o.setTextSize(2, 10.0f);
            this.o.setTextColor(-1);
            this.o.setShadowLayer(1.0f, 0.0f, 0.0f, ViewCompat.MEASURED_STATE_MASK);
            this.o.setImportantForAccessibility(2);
            addView(this.o, layoutParams);
        }
        if (str != null) {
            this.o.setText(str);
        }
    }

    protected void d() {
        o oVar = this.i;
        if (oVar != null) {
            oVar.setVisibility(8);
            removeView(this.i);
            this.i = null;
        }
    }

    protected final void d(int i2, int i3) {
        this.v = i3;
        this.w = i2;
        v();
        VOSurfaceView vOSurfaceView = this.d;
        if (vOSurfaceView != null) {
            vOSurfaceView.f();
        }
    }

    protected void e() {
        View view = this.f;
        if (view != null) {
            removeView(view);
            this.f = null;
        }
        c(false);
    }

    protected VOSurfaceViewOpenGL f() {
        return this.g;
    }

    protected Object g() {
        return this.r;
    }

    public SurfaceHolder getHolder() {
        SurfaceView surfaceView = this.j;
        if (surfaceView != null) {
            return surfaceView.getHolder();
        }
        return null;
    }

    protected k h() {
        return this.h;
    }

    public void hidePlayerLayouts() {
        SurfaceView surfaceView = this.k;
        if (surfaceView != null) {
            surfaceView.setAlpha(this.u);
            this.k.setVisibility(0);
            VOSurfaceViewOpenGL vOSurfaceViewOpenGL = this.g;
            if (vOSurfaceViewOpenGL != null) {
                vOSurfaceViewOpenGL.a(false);
                this.g.setAlpha(this.u);
            }
        }
    }

    protected View i() {
        return this.f;
    }

    public boolean isSurfaceValid() {
        VOSurfaceViewOpenGL vOSurfaceViewOpenGL = this.g;
        if (vOSurfaceViewOpenGL != null) {
            return vOSurfaceViewOpenGL.e() && this.D;
        }
        return this.D;
    }

    protected int j() {
        return this.v;
    }

    protected float k() {
        return this.t;
    }

    protected int l() {
        return this.w;
    }

    protected boolean m() {
        return this.e != null;
    }

    protected int n() {
        o oVar = this.i;
        if (oVar != null) {
            return oVar.a();
        }
        return 0;
    }

    protected View o() {
        return this.i;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.D = true;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        this.b = configuration.orientation;
        super.onConfigurationChanged(configuration);
        if (this.K) {
            r();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.D = false;
        super.onDetachedFromWindow();
    }

    public void onPause() {
        VOSurfaceViewAmlogic vOSurfaceViewAmlogic = this.e;
        if (vOSurfaceViewAmlogic != null) {
            vOSurfaceViewAmlogic.onPause();
        }
        VOSurfaceViewOpenGL vOSurfaceViewOpenGL = this.g;
        if (vOSurfaceViewOpenGL != null) {
            vOSurfaceViewOpenGL.onPause();
        }
        k kVar = this.h;
        if (kVar != null) {
            kVar.onPause();
        }
    }

    public void onResume() {
        VOSurfaceViewAmlogic vOSurfaceViewAmlogic = this.e;
        if (vOSurfaceViewAmlogic != null) {
            vOSurfaceViewAmlogic.onResume();
        }
        VOSurfaceViewOpenGL vOSurfaceViewOpenGL = this.g;
        if (vOSurfaceViewOpenGL != null && this.q) {
            vOSurfaceViewOpenGL.onResume();
        }
        k kVar = this.h;
        if (kVar != null) {
            kVar.onResume();
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        r();
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.K = z;
        if (z) {
            r();
        }
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        VOSurfaceViewAmlogic vOSurfaceViewAmlogic = this.e;
        if (vOSurfaceViewAmlogic != null) {
            vOSurfaceViewAmlogic.setBackground(drawable);
        }
        k kVar = this.h;
        if (kVar != null) {
            kVar.setBackgroundDrawable(drawable);
        }
        VOSurfaceViewOpenGL vOSurfaceViewOpenGL = this.g;
        if (vOSurfaceViewOpenGL != null) {
            vOSurfaceViewOpenGL.setBackgroundDrawable(drawable);
        }
        super.setBackground(drawable);
    }

    public void setSecure(boolean z) {
        if (Build.VERSION.SDK_INT >= 17 && !Build.VERSION.RELEASE.equals("4.2")) {
            VOSurfaceViewAmlogic vOSurfaceViewAmlogic = this.e;
            if (vOSurfaceViewAmlogic != null) {
                vOSurfaceViewAmlogic.setSecure(z);
            }
            VOSurfaceViewOpenGL vOSurfaceViewOpenGL = this.g;
            if (vOSurfaceViewOpenGL != null) {
                vOSurfaceViewOpenGL.setSecure(z);
            }
            k kVar = this.h;
            if (kVar != null) {
                kVar.setSecure(z);
            }
            o oVar = this.i;
            if (oVar != null) {
                oVar.setSecure(z);
            }
        }
        try {
            if (z) {
                ((Activity) this.f149a).getWindow().setFlags(8192, 8192);
            } else {
                ((Activity) this.f149a).getWindow().clearFlags(8192);
            }
        } catch (ClassCastException unused) {
        }
        this.E = z;
    }

    @Override // android.view.View
    public String toString() {
        return l.class.getName() + " - super=" + super.toString();
    }
}
