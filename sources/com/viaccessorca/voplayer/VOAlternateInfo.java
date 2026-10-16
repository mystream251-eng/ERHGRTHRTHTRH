package com.viaccessorca.voplayer;

/* JADX INFO: loaded from: classes2.dex */
public class VOAlternateInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f58a;
    private int b;
    private int c;
    private double d;
    private boolean e;

    public VOAlternateInfo(int i, int i2, int i3, double d, boolean z) {
        this.f58a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0.0d;
        this.e = false;
        this.f58a = i;
        this.b = i2;
        this.c = i3;
        this.d = d;
        this.e = z;
    }

    public boolean IsSelected() {
        return this.e;
    }

    public int getBitrate() {
        return this.c;
    }

    public double getFrameRate() {
        return this.d;
    }

    public int getVideoHeight() {
        return this.b;
    }

    public int getVideoWidth() {
        return this.f58a;
    }
}
