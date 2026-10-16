package com.viaccessorca.voplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public class VOSurfaceView extends RelativeLayout {
    private static int l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f94a;
    private AttributeSet b;
    private l c;
    private ImageView d;
    private ImageView e;
    private ImageView f;
    private VOAdBannerManager g;
    private Bitmap h;
    private boolean i;
    private boolean j;
    protected Handler k;

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            VOSurfaceView.this.f();
        }
    }

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            VOSurfaceView.this.g.eventClickedLink();
        }
    }

    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            VOSurfaceView.this.j = true;
            VOSurfaceView.this.k.removeMessages(0);
            VOSurfaceView.this.k.sendMessage(Message.obtain((Handler) null, (Runnable) null));
        }
    }

    class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            VOSurfaceView.this.g.eventClickedReport();
        }
    }

    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            VOSurfaceView.this.m();
        }
    }

    public VOSurfaceView(Context context) {
        super(context);
        this.f94a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = false;
        this.j = true;
        this.k = new a(Looper.getMainLooper());
        a(context, (AttributeSet) null);
    }

    public VOSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f94a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = false;
        this.j = true;
        this.k = new a(Looper.getMainLooper());
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        l++;
        this.f94a = context;
        this.b = attributeSet;
        l lVar = this.c;
        if (lVar != null) {
            removeView(lVar);
            this.c = null;
        }
        l lVar2 = new l(this.f94a, this.b);
        this.c = lVar2;
        lVar2.setBackground(getBackground());
        this.c.a(this);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        this.c.setLayoutParams(layoutParams);
        addView(this.c, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        ImageView imageView;
        int i;
        boolean z;
        int i2;
        int i3;
        if (!this.i || this.j) {
            View view = this.e;
            if (view != null) {
                removeView(view);
                imageView = null;
                this.e = null;
            } else {
                imageView = null;
            }
            View view2 = this.f;
            if (view2 != null) {
                removeView(view2);
                this.f = imageView;
            }
            View view3 = this.d;
            if (view3 != null) {
                removeView(view3);
                this.d = imageView;
            }
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -1;
            this.c.setLayoutParams(layoutParams);
            return;
        }
        int width = getWidth();
        int height = getHeight();
        int iL = this.c.l();
        int iJ = this.c.j();
        float fK = this.c.k();
        if (iJ == 0 || iL == 0 || 0.0f == fK) {
            return;
        }
        float f = height;
        float f2 = iJ;
        float f3 = f / f2;
        float f4 = iL;
        float f5 = width / (f4 * fK);
        int iMin = (int) (f4 * Math.min(f3, f5) * fK);
        int iMin2 = (int) (f2 * Math.min(f3, f5));
        float f6 = iMin;
        float f7 = f6 / iMin2;
        boolean z2 = false;
        if (iMin2 < height) {
            i = height;
            if (height - iMin2 > (((double) f6) * 11.0d) / 140.0d) {
                z = true;
                z2 = true;
            } else {
                z = true;
            }
        } else {
            i = height;
            z = false;
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
        RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
        RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
        if (!z2) {
            if (z) {
                iMin2 = (int) (((double) f) / (((double) ((11.0f * f7) / 140.0f)) + 1.0d));
                iMin = (int) (f7 * iMin2);
            } else {
                int i4 = ((iMin * 140) * iMin2) / ((iMin * 11) + (iMin2 * 140));
                iMin2 = (iMin2 * i4) / iMin;
                iMin = i4;
            }
        }
        layoutParams2.width = iMin;
        layoutParams2.height = iMin2;
        layoutParams3.width = iMin;
        int i5 = layoutParams2.width;
        int i6 = ((i5 * 11) / 140) + 1;
        layoutParams3.height = i6;
        if (z2) {
            layoutParams2.topMargin = (i - (layoutParams2.height + i6)) / 2;
        } else {
            layoutParams2.leftMargin = (width - i5) / 2;
        }
        layoutParams3.topMargin = layoutParams2.height + layoutParams2.topMargin;
        layoutParams3.leftMargin = layoutParams2.leftMargin;
        this.c.setLayoutParams(layoutParams2);
        if (this.d == null) {
            ImageView imageView2 = new ImageView(this.f94a);
            this.d = imageView2;
            imageView2.setImageBitmap(null);
            addView(this.d, layoutParams3);
            this.d.setScaleType(ImageView.ScaleType.FIT_XY);
            this.d.setOnClickListener(new b());
        }
        this.d.setLayoutParams(layoutParams3);
        this.d.setImageBitmap(this.h);
        int i7 = layoutParams3.height / 5;
        layoutParams4.width = i7;
        layoutParams4.height = i7;
        layoutParams4.topMargin = layoutParams3.topMargin + 2;
        layoutParams4.leftMargin = ((layoutParams3.leftMargin + layoutParams3.width) - i7) - 2;
        if (this.g.isClosable()) {
            if (this.e == null) {
                ImageView imageView3 = new ImageView(this.f94a);
                this.e = imageView3;
                imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
                addView(this.e, layoutParams4);
                try {
                    this.e.setImageDrawable(Drawable.createFromStream(getContext().getAssets().open("closeAdBannerButton.png"), "closeAdBannerButton.png"));
                } catch (Exception unused) {
                }
                this.e.setOnClickListener(new c());
            }
            this.e.setLayoutParams(layoutParams4);
            i2 = layoutParams3.height / 5;
            layoutParams5.width = i2;
            layoutParams5.height = i2;
            layoutParams5.topMargin = layoutParams3.topMargin + 2;
            i3 = ((layoutParams3.leftMargin + layoutParams3.width) - layoutParams4.width) - 2;
        } else {
            i2 = layoutParams3.height / 5;
            layoutParams5.width = i2;
            layoutParams5.height = i2;
            layoutParams5.topMargin = layoutParams3.topMargin + 2;
            i3 = layoutParams3.leftMargin + layoutParams3.width;
        }
        layoutParams5.leftMargin = (i3 - i2) - 2;
        if (this.f == null) {
            ImageView imageView4 = new ImageView(this.f94a);
            this.f = imageView4;
            imageView4.setScaleType(ImageView.ScaleType.FIT_XY);
            addView(this.f, layoutParams5);
            try {
                this.f.setImageDrawable(Drawable.createFromStream(getContext().getAssets().open("reportAdBannerButton.png"), "reportAdBannerButton.png"));
            } catch (Exception unused2) {
            }
            this.f.setOnClickListener(new d());
        }
        this.f.setLayoutParams(layoutParams5);
        this.g.eventDimensionsUpdate(layoutParams2.height, layoutParams2.width, layoutParams2.topMargin, layoutParams2.leftMargin, layoutParams3.height, layoutParams3.width, layoutParams3.topMargin, layoutParams3.leftMargin);
    }

    protected void a() {
        l lVar = this.c;
        if (lVar != null) {
            lVar.b();
        }
    }

    protected void a(float f) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.b(f);
        }
    }

    protected final void a(int i) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.a(i);
        }
    }

    protected void a(int i, int i2, int i3) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.a(i, i2, i3);
        }
    }

    protected final void a(int i, int i2, VOSubtitle vOSubtitle) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.a(i, i2, vOSubtitle);
        }
    }

    protected void a(int i, Handler handler) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.a(i, handler);
        }
    }

    protected void a(Bitmap bitmap) {
        if (bitmap != null) {
            this.h = bitmap;
            this.j = false;
            this.k.removeMessages(0);
            this.k.sendMessage(Message.obtain((Handler) null, (Runnable) null));
        }
    }

    protected void a(Handler handler) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.a(handler);
        }
    }

    protected void a(Handler handler, Object obj) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.a(handler, obj);
        }
    }

    protected final void a(VOPlayer.AdInformation adInformation) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.a(adInformation);
        }
    }

    protected void a(String str) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.b(str);
        }
    }

    protected void a(ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.a(byteBuffer, i, i2, i3, i4);
        }
    }

    protected void a(boolean z) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.b(z);
        }
    }

    protected boolean a(int i, int i2) {
        l lVar = this.c;
        if (lVar != null) {
            return lVar.a(i, i2);
        }
        return false;
    }

    protected boolean a(int i, byte[] bArr, int i2, int i3, int i4, int i5) {
        l lVar = this.c;
        if (lVar != null) {
            return lVar.a(i, bArr, i2, i3, i4, i5);
        }
        return true;
    }

    protected boolean a(byte[] bArr, int i) {
        l lVar = this.c;
        if (lVar != null) {
            return lVar.a(bArr, i);
        }
        return true;
    }

    protected int ab() {
        l lVar = this.c;
        if (lVar != null) {
            return lVar.n();
        }
        return 0;
    }

    protected View ac() {
        l lVar = this.c;
        if (lVar != null) {
            return lVar.o();
        }
        return null;
    }

    protected void b() {
        l lVar = this.c;
        if (lVar != null) {
            lVar.c();
        }
    }

    protected void b(int i) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.b(i);
        }
    }

    protected void b(int i, int i2) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.b(i, i2);
        }
    }

    protected void b(Handler handler) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.b(handler);
        }
    }

    protected void b(String str) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.c(str);
        }
    }

    protected void c() {
        l lVar = this.c;
        if (lVar != null) {
            lVar.d();
        }
    }

    protected final void c(int i, int i2) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.d(i, i2);
        }
    }

    protected final void c(Handler handler) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.c(handler);
        }
    }

    public final void clearAllSubtitles() {
        l lVar = this.c;
        if (lVar != null) {
            lVar.a();
        }
    }

    protected void d() {
        l lVar = this.c;
        if (lVar != null) {
            lVar.e();
        }
    }

    protected boolean e() {
        l lVar = this.c;
        if (lVar != null) {
            return lVar.m();
        }
        return false;
    }

    public void enableAdBanner(boolean z) {
        this.i = z;
        f();
    }

    protected void f() {
        m();
    }

    protected VOSurfaceViewOpenGL g() {
        l lVar = this.c;
        if (lVar != null) {
            return lVar.f();
        }
        return null;
    }

    public SurfaceHolder getHolder() {
        l lVar = this.c;
        if (lVar != null) {
            return lVar.getHolder();
        }
        return null;
    }

    protected Object h() {
        l lVar = this.c;
        if (lVar != null) {
            return lVar.g();
        }
        return null;
    }

    public void hidePlayerLayouts() {
        l lVar = this.c;
        if (lVar != null) {
            lVar.hidePlayerLayouts();
        }
    }

    protected k i() {
        l lVar = this.c;
        if (lVar != null) {
            return lVar.h();
        }
        return null;
    }

    public boolean isSurfaceValid() {
        l lVar = this.c;
        if (lVar != null) {
            return lVar.isSurfaceValid();
        }
        return false;
    }

    protected View j() {
        l lVar = this.c;
        if (lVar != null) {
            return lVar.i();
        }
        return null;
    }

    protected RelativeLayout k() {
        return this.c;
    }

    protected int l() {
        l lVar = this.c;
        if (lVar != null) {
            return lVar.n();
        }
        return 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void onPause() {
    }

    public void onResume() {
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        post(new e());
    }

    public boolean setAdBannerManager(VOAdBannerManager vOAdBannerManager) {
        this.g = vOAdBannerManager;
        return true;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        super.setBackground(drawable);
        l lVar = this.c;
        if (lVar != null) {
            lVar.setBackground(drawable);
        }
    }

    public boolean setBlackFrameAlpha(float f) {
        if (this.c == null) {
            return true;
        }
        if (0.0f == f) {
            f = 0.01f;
        } else if (1.0f < f) {
            f = 1.0f;
        }
        this.c.a(f);
        return true;
    }

    protected void setQuarterDisplay(VOPlayer.VOQuarterInfo vOQuarterInfo) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.a(vOQuarterInfo);
        }
    }

    public boolean setSecure(boolean z) {
        l lVar = this.c;
        if (lVar == null) {
            return true;
        }
        lVar.setSecure(z);
        return true;
    }

    public boolean setSubtitleCustomCSSStyling(String str) {
        l lVar = this.c;
        if (lVar == null) {
            return true;
        }
        lVar.a(str);
        return true;
    }

    public void setSubtitleMaxDisplayAreaSize(int i, int i2) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.c(i, i2);
        }
    }

    public boolean setUIOnTop(boolean z) {
        l lVar = this.c;
        if (lVar == null) {
            return true;
        }
        lVar.a(z);
        return true;
    }

    @Override // android.view.View
    public String toString() {
        return VOSurfaceView.class.getName() + " - super=" + super.toString();
    }
}
