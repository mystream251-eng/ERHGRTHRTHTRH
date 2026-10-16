package org.tukaani.xz.index;

/* JADX INFO: loaded from: classes3.dex */
class IndexRecord {
    final long uncompressed;
    final long unpadded;

    IndexRecord(long j, long j2) {
        this.unpadded = j;
        this.uncompressed = j2;
    }
}
