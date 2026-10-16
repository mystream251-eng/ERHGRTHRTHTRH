package org.tukaani.xz;

import java.io.InputStream;

/* JADX INFO: loaded from: classes3.dex */
public class DeltaOptions extends FilterOptions {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DISTANCE_MAX = 256;
    public static final int DISTANCE_MIN = 1;
    private int distance = 1;

    public DeltaOptions() {
    }

    public DeltaOptions(int i) throws UnsupportedOptionsException {
        setDistance(i);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new RuntimeException();
        }
    }

    @Override // org.tukaani.xz.FilterOptions
    public int getDecoderMemoryUsage() {
        return 1;
    }

    public int getDistance() {
        return this.distance;
    }

    @Override // org.tukaani.xz.FilterOptions
    public int getEncoderMemoryUsage() {
        return DeltaOutputStream.getMemoryUsage();
    }

    @Override // org.tukaani.xz.FilterOptions
    FilterEncoder getFilterEncoder() {
        return new DeltaEncoder(this);
    }

    @Override // org.tukaani.xz.FilterOptions
    public InputStream getInputStream(InputStream inputStream, ArrayCache arrayCache) {
        return new DeltaInputStream(inputStream, this.distance);
    }

    @Override // org.tukaani.xz.FilterOptions
    public FinishableOutputStream getOutputStream(FinishableOutputStream finishableOutputStream, ArrayCache arrayCache) {
        return new DeltaOutputStream(finishableOutputStream, this);
    }

    public void setDistance(int i) throws UnsupportedOptionsException {
        if (i >= 1 && i <= 256) {
            this.distance = i;
            return;
        }
        throw new UnsupportedOptionsException("Delta distance must be in the range [1, 256]: " + i);
    }
}
