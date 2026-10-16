package com.viaccessorca.voplayer;

/* JADX INFO: loaded from: classes2.dex */
public class VOSubtitleSettingsAttributes {
    public static final int AlignEnd = 2;
    public static final int AlignLeft = 3;
    public static final int AlignMiddle = 1;
    public static final int AlignRight = 4;
    public static final int AlignStart = 0;
    public static final int VerticalTypeHorizontal = 0;
    public static final int VerticalTypeVertical_LR = 1;
    public static final int VerticalTypeVertical_RL = 2;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f92a;
    private int b;
    private boolean c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;

    public VOSubtitleSettingsAttributes(int i, int i2, boolean z, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.f92a = i;
        this.b = i2;
        this.c = z;
        this.d = i3;
        this.e = i4;
        this.f = i5;
        this.g = i6;
        this.h = i7;
        this.i = i8;
        this.j = i9;
    }

    public int getAlignType() {
        return this.f;
    }

    public int getLine() {
        return this.b;
    }

    public int getPosition() {
        return this.d;
    }

    public int getPositionX1() {
        return this.g;
    }

    public int getPositionX2() {
        return this.i;
    }

    public int getPositionY1() {
        return this.h;
    }

    public int getPositionY2() {
        return this.j;
    }

    public int getSize() {
        return this.e;
    }

    public boolean getSnapToLine() {
        return this.c;
    }

    public int getVerticalType() {
        return this.f92a;
    }
}
