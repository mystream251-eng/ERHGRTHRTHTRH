package com.viaccessorca.voplayer;

import android.content.Context;
import android.view.TextureView;

/* JADX INFO: loaded from: classes2.dex */
class m extends TextureView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f158a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private float i;
    private int j;

    protected m(Context context) {
        super(context);
        this.f158a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = -1;
        this.h = -1;
        this.i = 1.0f;
        this.j = 0;
        if (getSurfaceTexture() != null) {
            getSurfaceTexture().detachFromGLContext();
        }
    }

    protected int a() {
        return this.c;
    }

    protected void a(float f) {
        this.i = f;
    }

    protected void a(int i, int i2) {
        this.f158a = i;
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
        if (i5 != 0) {
            if (i5 != 2) {
                this.d = 0;
                this.c = 0;
            } else {
                int i6 = this.b;
                int i7 = i4 * i6;
                int i8 = this.f158a;
                if (i7 > i8 * i3) {
                    this.d = 0;
                    this.c = 0;
                    int i9 = ((int) (((double) i8) - ((((double) i6) * ((double) i4)) / ((double) i3)))) / 2;
                    this.e = i9;
                    this.f = i9;
                } else {
                    int i10 = ((int) (((double) i6) - ((((double) i8) * ((double) i3)) / ((double) i4)))) / 2;
                    this.d = i10;
                    this.c = i10;
                }
            }
            this.e = 0;
            this.f = 0;
        } else {
            int i11 = this.f158a;
            int i12 = i11 * i3;
            int i13 = this.b;
            int i14 = i4 * i13;
            if (i12 > i14) {
                this.d = 0;
                this.c = 0;
                int i15 = (i11 - (i14 / i3)) / 2;
                this.e = i15;
                this.f = i15;
            } else {
                int i16 = (i13 - (i12 / i4)) / 2;
                this.d = i16;
                this.c = i16;
                this.e = 0;
                this.f = 0;
            }
        }
        setPadding(this.e, this.d, this.f, this.c);
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
}
