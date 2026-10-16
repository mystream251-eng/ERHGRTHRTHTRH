package org.tukaani.xz.simple;

import android.support.v4.media.session.PlaybackStateCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class IA64 implements SimpleFilter {
    private static final int[] BRANCH_TABLE = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 6, 6, 0, 0, 7, 7, 4, 4, 0, 0, 4, 4, 0, 0};
    private final boolean isEncoder;
    private int pos;

    public IA64(boolean z, int i) {
        this.isEncoder = z;
        this.pos = i;
    }

    @Override // org.tukaani.xz.simple.SimpleFilter
    public int code(byte[] bArr, int i, int i2) {
        int i3;
        char c;
        char c2 = 16;
        int i4 = (i + i2) - 16;
        int i5 = i;
        while (i5 <= i4) {
            int i6 = BRANCH_TABLE[bArr[i5] & 31];
            int i7 = 5;
            int i8 = 0;
            while (i8 < 3) {
                if (((i6 >>> i8) & 1) == 0) {
                    c = c2;
                    i3 = i5;
                } else {
                    int i9 = i7 >>> 3;
                    int i10 = i7 & 7;
                    long j = 0;
                    int i11 = 0;
                    while (i11 < 6) {
                        j |= (((long) bArr[(i5 + i9) + i11]) & 255) << (i11 * 8);
                        i11++;
                        i5 = i5;
                    }
                    i3 = i5;
                    long j2 = j >>> i10;
                    if (((j2 >>> 37) & 15) == 5 && ((j2 >>> 9) & 7) == 0) {
                        int i12 = (((((int) (j2 >>> 36)) & 1) << 20) | ((int) ((j2 >>> 13) & 1048575))) << 4;
                        long j3 = (this.isEncoder ? i12 + ((this.pos + i3) - i) : i12 - ((this.pos + i3) - i)) >>> 4;
                        c = 16;
                        long j4 = ((((j2 & (-77309403137L)) | ((j3 & 1048575) << 13)) | ((j3 & PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) << 16)) << i10) | (((long) ((1 << i10) - 1)) & j);
                        for (int i13 = 0; i13 < 6; i13++) {
                            bArr[i3 + i9 + i13] = (byte) (j4 >>> (i13 * 8));
                        }
                    } else {
                        c = 16;
                    }
                }
                i8++;
                i7 += 41;
                c2 = c;
                i5 = i3;
            }
            i5 += 16;
        }
        int i14 = i5 - i;
        this.pos += i14;
        return i14;
    }
}
