package com.viaccessorca.voplayer;

/* JADX INFO: loaded from: classes2.dex */
public class VOCachedRange {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f61a;
    private int b;
    private int c;
    private DataType d;

    public enum DataType {
        AUDIO,
        VIDEO
    }

    protected VOCachedRange(int i, int i2, int i3, boolean z) {
        this.f61a = i;
        this.b = i2;
        this.c = i3;
        this.d = z ? DataType.AUDIO : DataType.VIDEO;
    }

    public int getBitrate() {
        return this.c;
    }

    public DataType getDataType() {
        return this.d;
    }

    public int getEndTime() {
        return this.b;
    }

    public int getStartTime() {
        return this.f61a;
    }
}
