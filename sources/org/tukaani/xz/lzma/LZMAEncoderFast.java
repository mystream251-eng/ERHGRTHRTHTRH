package org.tukaani.xz.lzma;

import org.tukaani.xz.ArrayCache;
import org.tukaani.xz.lz.LZEncoder;
import org.tukaani.xz.lz.Matches;
import org.tukaani.xz.rangecoder.RangeEncoder;

/* JADX INFO: loaded from: classes3.dex */
final class LZMAEncoderFast extends LZMAEncoder {
    private static final int EXTRA_SIZE_AFTER = 272;
    private static final int EXTRA_SIZE_BEFORE = 1;
    private Matches matches;

    LZMAEncoderFast(RangeEncoder rangeEncoder, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, ArrayCache arrayCache) {
        super(rangeEncoder, LZEncoder.getInstance(i4, Math.max(i5, 1), EXTRA_SIZE_AFTER, i6, 273, i7, i8, arrayCache), i, i2, i3, i4, i6);
        this.matches = null;
    }

    private boolean changePair(int i, int i2) {
        return i < (i2 >>> 7);
    }

    static int getMemoryUsage(int i, int i2, int i3) {
        return LZEncoder.getMemoryUsage(i, Math.max(i2, 1), EXTRA_SIZE_AFTER, 273, i3);
    }

    @Override // org.tukaani.xz.lzma.LZMAEncoder
    int getNextSymbol() {
        int i;
        int i2;
        int i3;
        int i4;
        if (this.readAhead == -1) {
            this.matches = getMatches();
        }
        this.back = -1;
        int iMin = Math.min(this.lz.getAvail(), 273);
        if (iMin < 2) {
            return 1;
        }
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < 4; i7++) {
            int matchLen = this.lz.getMatchLen(this.reps[i7], iMin);
            if (matchLen >= 2) {
                if (matchLen >= this.niceLen) {
                    this.back = i7;
                    skip(matchLen - 1);
                    return matchLen;
                }
                if (matchLen > i5) {
                    i6 = i7;
                    i5 = matchLen;
                }
            }
        }
        if (this.matches.count > 0) {
            i = this.matches.len[this.matches.count - 1];
            i2 = this.matches.dist[this.matches.count - 1];
            if (i >= this.niceLen) {
                this.back = i2 + 4;
                i3 = i - 1;
            } else {
                while (this.matches.count > 1 && i == this.matches.len[this.matches.count - 2] + 1 && changePair(this.matches.dist[this.matches.count - 2], i2)) {
                    this.matches.count--;
                    i = this.matches.len[this.matches.count - 1];
                    i2 = this.matches.dist[this.matches.count - 1];
                }
                if (i == 2 && i2 >= 128) {
                    i = 1;
                }
            }
            skip(i3);
            return i;
        }
        i = 0;
        i2 = 0;
        if (i5 >= 2 && (i5 + 1 >= i || ((i5 + 2 >= i && i2 >= 512) || (i5 + 3 >= i && i2 >= 32768)))) {
            this.back = i6;
            skip(i5 - 1);
            return i5;
        }
        if (i < 2 || iMin <= 2) {
            return 1;
        }
        Matches matches = getMatches();
        this.matches = matches;
        if (matches.count > 0) {
            int i8 = this.matches.len[this.matches.count - 1];
            int i9 = this.matches.dist[this.matches.count - 1];
            if ((i8 >= i && i9 < i2) || ((i8 == (i4 = i + 1) && !changePair(i2, i9)) || i8 > i4 || (i8 + 1 >= i && i >= 3 && changePair(i9, i2)))) {
                return 1;
            }
        }
        int iMax = Math.max(i - 1, 2);
        for (int i10 = 0; i10 < 4; i10++) {
            if (this.lz.getMatchLen(this.reps[i10], iMax) == iMax) {
                return 1;
            }
        }
        this.back = i2 + 4;
        i3 = i - 2;
        skip(i3);
        return i;
    }
}
