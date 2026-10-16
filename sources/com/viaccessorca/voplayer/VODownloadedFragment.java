package com.viaccessorca.voplayer;

/* JADX INFO: loaded from: classes2.dex */
public class VODownloadedFragment {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f66a;
    private int b;
    private int c;
    private int d;
    private int e;

    public VODownloadedFragment(String str, int i, int i2, int i3, int i4) {
        this.f66a = null;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f66a = str;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
    }

    public int getBitrate() {
        return this.e;
    }

    public int getDownloadDuration() {
        return this.c;
    }

    public int getDownloadSize() {
        return this.b;
    }

    public int getFragmentDuration() {
        return this.d;
    }

    public String getUrl() {
        return this.f66a;
    }
}
