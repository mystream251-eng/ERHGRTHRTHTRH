package org.tukaani.xz.lz;

/* JADX INFO: loaded from: classes3.dex */
public final class Matches {
    public int count = 0;
    public final int[] dist;
    public final int[] len;

    Matches(int i) {
        this.len = new int[i];
        this.dist = new int[i];
    }
}
