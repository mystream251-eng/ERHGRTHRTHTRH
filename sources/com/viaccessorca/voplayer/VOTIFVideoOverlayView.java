package com.viaccessorca.voplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import java.nio.ByteBuffer;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class VOTIFVideoOverlayView extends RelativeLayout {
    private static int u;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f109a;
    private VOSurfaceView b;
    private Bitmap c;
    private ImageView d;
    private TextView e;
    private TextView f;
    private float g;
    private float h;
    private int i;
    private int j;
    private int k;
    private boolean l;
    private String m;
    private int n;
    private boolean o;
    private TextView p;
    private j q;
    private ImageView r;
    private Bitmap s;
    private HashMap<Integer, Integer> t;

    public VOTIFVideoOverlayView(Context context) {
        super(context);
        this.f109a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 1.0f;
        this.h = 1.0f;
        this.l = false;
        this.m = null;
        this.n = 0;
        this.o = false;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        a(context, (AttributeSet) null);
    }

    public VOTIFVideoOverlayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f109a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 1.0f;
        this.h = 1.0f;
        this.l = false;
        this.m = null;
        this.n = 0;
        this.o = false;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        u++;
        this.f109a = context;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.f109a.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.g = displayMetrics.density;
    }

    private void b() {
        this.k = 0;
        this.t = new HashMap<>(0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(11, -1);
        layoutParams.addRule(10, -1);
        layoutParams.width = -1;
        layoutParams.height = -1;
        if (this.q == null) {
            j jVar = new j(this.f109a);
            this.q = jVar;
            addView(jVar, layoutParams);
            this.q.setClickable(false);
            this.q.setFocusable(false);
            this.q.setEnabled(false);
            this.q.setBackgroundColor(0);
            this.q.clearFocus();
            this.q.setVisibility(8);
            this.q.a(this.m);
        }
        if (this.p == null) {
            TextView textView = new TextView(this.f109a);
            this.p = textView;
            textView.setVisibility(8);
            addView(this.p);
        }
        if (this.r == null) {
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams2.addRule(12, -1);
            layoutParams2.width = -1;
            layoutParams2.height = -2;
            ImageView imageView = new ImageView(this.f109a);
            this.r = imageView;
            imageView.setVisibility(8);
            this.r.setImageBitmap(null);
            this.r.setScaleType(ImageView.ScaleType.FIT_CENTER);
            addView(this.r, layoutParams2);
        }
    }

    private void c() {
        int i;
        int i2;
        int i3;
        if (this.q == null) {
            return;
        }
        this.n = 0;
        int height = getHeight();
        int width = getWidth();
        float f = height;
        float f2 = width;
        float f3 = f / f2;
        int i4 = this.i;
        if (i4 == 0 || (i2 = this.j) == 0) {
            i = 0;
        } else {
            float f4 = i4;
            float f5 = i2;
            float f6 = this.h;
            float f7 = (f4 / f5) / f6;
            float f8 = f / f4;
            float f9 = f2 / (f5 * f6);
            if (f8 < f9) {
                i3 = (int) (f5 * f8 * f6);
                i = (int) (f4 * f8);
            } else {
                int i5 = (int) (f5 * f9 * f6);
                int i6 = (int) (f4 * f9);
                i3 = i5;
                i = i6;
            }
            if (this.k != 0) {
                this.n = 0;
            } else if (f7 < f3) {
                this.n = Math.round((height - i) / 2);
            } else {
                Math.round((width - i3) / 2);
            }
        }
        this.p.setTextSize(0, (int) ((((double) i) / 15.0d) * 0.85d));
        setUIOnTop(this.l);
    }

    protected void a() {
        if (this.q == null) {
            b();
            c();
        }
    }

    protected void a(int i, int i2) {
        j jVar = this.q;
        if (jVar != null) {
            jVar.a(i, i2);
        }
    }

    protected void a(int i, int i2, int i3) {
        if (i >= 0) {
            this.k = i;
            this.i = i3;
            this.j = i2;
        }
        c();
        VOSurfaceView vOSurfaceView = this.b;
        if (vOSurfaceView != null) {
            vOSurfaceView.f();
        }
    }

    protected final void a(int i, int i2, VOSubtitle vOSubtitle) {
        i.d dVarB;
        a();
        if (2100 != i) {
            if (2200 == i) {
                Integer num = this.t.get(Integer.valueOf(i2));
                if (num != null) {
                    if (2 == num.intValue()) {
                        this.p.setVisibility(8);
                    } else if (5 == num.intValue()) {
                        this.r.setVisibility(8);
                        Bitmap bitmap = this.s;
                        if (bitmap != null) {
                            bitmap.recycle();
                            this.s = null;
                        }
                    } else {
                        this.q.b(i2);
                    }
                }
                this.t.remove(Integer.valueOf(i2));
                return;
            }
            return;
        }
        if (vOSubtitle == null) {
            this.p.setVisibility(8);
            this.q.setVisibility(8);
            this.r.setVisibility(8);
            return;
        }
        this.t.put(Integer.valueOf(i2), Integer.valueOf(vOSubtitle.getType()));
        if (vOSubtitle.getType() == 2) {
            i.a(vOSubtitle, this.p);
            this.q.setVisibility(8);
            this.r.setVisibility(8);
            this.p.setVisibility(0);
            return;
        }
        if (5 != vOSubtitle.getType()) {
            if (6 == vOSubtitle.getType()) {
                dVarB = i.b(vOSubtitle);
                if (vOSubtitle.getTtmlStylingBuffer() != null) {
                    this.q.a(i2, dVarB, vOSubtitle.getTtmlStylingBuffer());
                } else {
                    this.q.a(i2, dVarB);
                }
            } else if (3 == vOSubtitle.getType()) {
                i.d dVarB2 = i.b(vOSubtitle);
                VOSubtitleSettingsAttributes settingsAttributes = vOSubtitle.getSettingsAttributes();
                this.q.a(i2, dVarB2, settingsAttributes.getSnapToLine(), settingsAttributes.getLine());
            } else {
                dVarB = i.b(vOSubtitle);
                this.q.a(i2, dVarB);
            }
            this.p.setVisibility(8);
            this.r.setVisibility(8);
            this.q.setVisibility(0);
            return;
        }
        Bitmap bitmap2 = this.s;
        if (bitmap2 != null) {
            bitmap2.recycle();
            this.s = null;
        }
        int positionX2 = vOSubtitle.getSettingsAttributes().getPositionX2() - vOSubtitle.getSettingsAttributes().getPositionX1();
        int positionY2 = vOSubtitle.getSettingsAttributes().getPositionY2() - vOSubtitle.getSettingsAttributes().getPositionY1();
        this.s = Bitmap.createBitmap(positionX2, positionY2, Bitmap.Config.ARGB_8888);
        int i3 = positionX2 * positionY2;
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(vOSubtitle.getRAWText());
        byteBufferWrap.clear();
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i4 * 4;
            byte b = byteBufferWrap.get(i5);
            int i6 = i5 + 1;
            byte b2 = byteBufferWrap.get(i6);
            if (b == 0 && -120 == b2) {
                byteBufferWrap.put(i6, (byte) 0);
                byteBufferWrap.put(i5 + 2, (byte) 0);
                byteBufferWrap.put(i5 + 3, (byte) 0);
            }
        }
        this.s.copyPixelsFromBuffer(byteBufferWrap);
        int width = (int) ((((double) this.r.getWidth()) / ((double) positionX2)) * ((double) positionY2));
        ViewGroup.LayoutParams layoutParams = this.r.getLayoutParams();
        layoutParams.height = width;
        this.r.setLayoutParams(layoutParams);
        this.r.setImageBitmap(this.s);
        this.p.setVisibility(8);
        this.q.setVisibility(8);
        this.r.setVisibility(0);
    }

    protected void a(String str) {
        if (this.f != null || str == null) {
            return;
        }
        int width = (getWidth() * 85) / 100;
        int height = (getHeight() * 98) / 100;
        int iRound = Math.round(this.g * width);
        int iRound2 = Math.round(this.g * height);
        this.f = new TextView(this.f109a);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
        layoutParams.width = iRound;
        layoutParams.height = iRound2;
        layoutParams.topMargin = (getHeight() * 2) / 100;
        layoutParams.leftMargin = (getWidth() * 2) / 100;
        this.f.setTypeface(Typeface.DEFAULT, 1);
        this.f.setTextSize(2, 12.0f);
        this.f.setTextColor(SupportMenu.CATEGORY_MASK);
        this.f.setShadowLayer(2.0f, 0.0f, 0.0f, ViewCompat.MEASURED_STATE_MASK);
        addView(this.f, layoutParams);
        this.f.setText(str);
    }

    protected void a(ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
        View view = this.d;
        if (view != null) {
            removeView(view);
            this.d = null;
        }
        if (byteBuffer != null) {
            int iRound = Math.round(this.g * i);
            int iRound2 = Math.round(this.g * i2);
            ImageView imageView = new ImageView(this.f109a);
            this.d = imageView;
            imageView.setImageBitmap(null);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
            layoutParams.width = iRound;
            layoutParams.height = iRound2;
            layoutParams.topMargin = (getHeight() * i3) / 100;
            layoutParams.leftMargin = (getWidth() * i4) / 100;
            addView(this.d, layoutParams);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            this.c = bitmapCreateBitmap;
            bitmapCreateBitmap.copyPixelsFromBuffer(byteBuffer);
            this.d.setImageBitmap(this.c);
        }
    }

    protected final void b(int i, int i2) {
        this.i = i2;
        this.j = i;
        c();
        VOSurfaceView vOSurfaceView = this.b;
        if (vOSurfaceView != null) {
            vOSurfaceView.f();
        }
    }

    protected void b(String str) {
        if (this.o && str == null) {
            str = "DEMO";
        }
        View view = this.e;
        if (view != null && str == null) {
            removeView(view);
            this.e = null;
        } else if (this.e == null && str != null) {
            int width = (getWidth() * 85) / 100;
            int height = (getHeight() * 98) / 100;
            int iRound = Math.round(this.g * width);
            int iRound2 = Math.round(this.g * height);
            this.e = new TextView(this.f109a);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
            layoutParams.width = iRound;
            layoutParams.height = iRound2;
            layoutParams.topMargin = (getHeight() * 2) / 100;
            layoutParams.leftMargin = (getWidth() * 2) / 100;
            this.e.setTextSize(2, 10.0f);
            this.e.setTextColor(-1);
            this.e.setShadowLayer(1.0f, 0.0f, 0.0f, ViewCompat.MEASURED_STATE_MASK);
            addView(this.e, layoutParams);
        }
        if (str != null) {
            this.e.setText(str);
        }
    }

    public final void clearAllSubtitles() {
        j jVar = this.q;
        if (jVar != null) {
            jVar.a();
            this.q.setVisibility(8);
        }
        TextView textView = this.p;
        if (textView != null) {
            textView.setVisibility(8);
        }
        ImageView imageView = this.r;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        HashMap<Integer, Integer> map = this.t;
        if (map != null) {
            map.clear();
        }
    }

    public void setSubtitleCustomCSSStyling(String str) {
        this.m = str;
        j jVar = this.q;
        if (jVar != null) {
            jVar.a(str);
        }
    }

    public void setUIOnTop(boolean z) {
        this.l = z;
        j jVar = this.q;
        if (jVar != null) {
            if (!z || this.n >= 50) {
                this.q.a(false);
            } else {
                jVar.a(true);
            }
        }
    }

    protected void setVideoPixelAspectRatio(float f) {
        this.h = f;
        if (0.0f == f) {
            this.h = 1.0f;
        }
        c();
    }
}
