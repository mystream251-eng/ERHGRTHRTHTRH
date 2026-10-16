package com.viaccessorca.voplayer;

/* JADX INFO: loaded from: classes2.dex */
public class VOAdSwitchPoint {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f56a;
    private final long b;
    private final int c;
    private final double d;

    public enum a {
        UNKNOWN,
        SCTE35,
        EventStream
    }

    public VOAdSwitchPoint(int i, long j, int i2, double d) {
        this.b = j;
        this.c = i2;
        this.d = d;
        this.f56a = i != 1 ? i != 2 ? a.UNKNOWN : a.EventStream : a.SCTE35;
    }

    public final double getCts() {
        return this.d;
    }

    public final int getDuration() {
        return this.c;
    }

    public final long getID() {
        return this.b;
    }

    public final a getType() {
        return this.f56a;
    }
}
