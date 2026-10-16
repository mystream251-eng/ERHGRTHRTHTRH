package com.viaccessorca.voplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

/* JADX INFO: loaded from: classes2.dex */
class k extends SurfaceView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f148a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private float i;
    private int j;

    protected k(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f148a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = -1;
        this.h = -1;
        this.i = 1.0f;
        this.j = 0;
    }

    protected int a() {
        return this.c;
    }

    protected void a(float f) {
        this.i = f;
    }

    protected void a(int i, int i2) {
        this.f148a = i;
        this.b = i2;
        a(this.j, this.g, this.h);
    }

    protected void a(int i, int i2, int i3) {
        if (i2 <= 0 || i3 <= 0) {
            return;
        }
        if (i >= 0) {
            this.j = i;
        }
        this.g = i2;
        this.h = i3;
        int i4 = (int) (i2 * this.i);
        int i5 = this.j;
        if (i5 == 0) {
            int i6 = this.f148a;
            int i7 = i6 * i3;
            int i8 = this.b;
            int i9 = i4 * i8;
            if (i7 > i9) {
                this.d = 0;
                this.c = 0;
                int i10 = (i6 - (i9 / i3)) / 2;
                this.e = i10;
                this.f = i10;
                return;
            }
            int i11 = (i8 - (i7 / i4)) / 2;
            this.d = i11;
            this.c = i11;
        } else if (i5 != 2) {
            this.d = 0;
            this.c = 0;
        } else {
            int i12 = this.b;
            int i13 = i4 * i12;
            int i14 = this.f148a;
            if (i13 > i14 * i3) {
                this.d = 0;
                this.c = 0;
                int i15 = ((int) (((double) i14) - ((((double) i12) * ((double) i4)) / ((double) i3)))) / 2;
                this.e = i15;
                this.f = i15;
                return;
            }
            int i16 = ((int) (((double) i12) - ((((double) i14) * ((double) i3)) / ((double) i4)))) / 2;
            this.d = i16;
            this.c = i16;
        }
        this.e = 0;
        this.f = 0;
    }

    protected int b() {
        return this.e;
    }

    protected int c() {
        return this.f;
    }

    protected int d() {
        return this.d;
    }

    protected void e() {
        if (getHolder() != null) {
            getHolder().setSizeFromLayout();
        }
    }

    public void onCreate() {
    }

    public void onDestroy() {
    }

    public void onPause() {
    }

    public void onResume() {
    }
}
