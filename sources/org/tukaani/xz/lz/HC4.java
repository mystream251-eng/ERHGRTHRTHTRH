package org.tukaani.xz.lz;

import org.tukaani.xz.ArrayCache;

/* JADX INFO: loaded from: classes3.dex */
final class HC4 extends LZEncoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final int[] chain;
    private int cyclicPos;
    private final int cyclicSize;
    private final int depthLimit;
    private final Hash234 hash;
    private int lzPos;
    private final Matches matches;

    HC4(int i, int i2, int i3, int i4, int i5, int i6, ArrayCache arrayCache) {
        super(i, i2, i3, i4, i5, arrayCache);
        this.cyclicPos = -1;
        this.hash = new Hash234(i, arrayCache);
        int i7 = i + 1;
        this.cyclicSize = i7;
        this.chain = arrayCache.getIntArray(i7, false);
        this.lzPos = this.cyclicSize;
        this.matches = new Matches(i4 - 1);
        this.depthLimit = i6 <= 0 ? (i4 / 4) + 4 : i6;
    }

    static int getMemoryUsage(int i) {
        return Hash234.getMemoryUsage(i) + (i / 256) + 10;
    }

    private int movePos() {
        int iMovePos = movePos(4, 4);
        if (iMovePos != 0) {
            int i = this.lzPos + 1;
            this.lzPos = i;
            if (i == Integer.MAX_VALUE) {
                int i2 = Integer.MAX_VALUE - this.cyclicSize;
                this.hash.normalize(i2);
                normalize(this.chain, this.cyclicSize, i2);
                this.lzPos -= i2;
            }
            int i3 = this.cyclicPos + 1;
            this.cyclicPos = i3;
            if (i3 == this.cyclicSize) {
                this.cyclicPos = 0;
            }
        }
        return iMovePos;
    }

    @Override // org.tukaani.xz.lz.LZEncoder
    public Matches getMatches() {
        int i;
        this.matches.count = 0;
        int i2 = this.matchLenMax;
        int i3 = this.niceLen;
        int iMovePos = movePos();
        if (iMovePos < i2) {
            if (iMovePos == 0) {
                return this.matches;
            }
            i2 = iMovePos;
            if (i3 > iMovePos) {
                i3 = i2;
            }
        }
        this.hash.calcHashes(this.buf, this.readPos);
        int hash2Pos = this.lzPos - this.hash.getHash2Pos();
        int hash3Pos = this.lzPos - this.hash.getHash3Pos();
        int hash4Pos = this.hash.getHash4Pos();
        this.hash.updateTables(this.lzPos);
        this.chain[this.cyclicPos] = hash4Pos;
        int i4 = 2;
        if (hash2Pos >= this.cyclicSize || this.buf[this.readPos - hash2Pos] != this.buf[this.readPos]) {
            i4 = 0;
        } else {
            this.matches.len[0] = 2;
            this.matches.dist[0] = hash2Pos - 1;
            this.matches.count = 1;
        }
        if (hash2Pos != hash3Pos && hash3Pos < this.cyclicSize && this.buf[this.readPos - hash3Pos] == this.buf[this.readPos]) {
            int[] iArr = this.matches.dist;
            Matches matches = this.matches;
            int i5 = matches.count;
            matches.count = i5 + 1;
            iArr[i5] = hash3Pos - 1;
            hash2Pos = hash3Pos;
            i4 = 3;
        }
        if (this.matches.count > 0) {
            while (i4 < i2 && this.buf[(this.readPos + i4) - hash2Pos] == this.buf[this.readPos + i4]) {
                i4++;
            }
            this.matches.len[this.matches.count - 1] = i4;
            if (i4 >= i3) {
                return this.matches;
            }
        }
        int i6 = i4 >= 3 ? i4 : 3;
        int i7 = this.depthLimit;
        while (true) {
            int i8 = this.lzPos - hash4Pos;
            int i9 = i7 - 1;
            if (i7 == 0 || i8 >= (i = this.cyclicSize)) {
                break;
            }
            int[] iArr2 = this.chain;
            int i10 = this.cyclicPos;
            int i11 = i10 - i8;
            if (i8 <= i10) {
                i = 0;
            }
            int i12 = iArr2[i11 + i];
            if (this.buf[(this.readPos + i6) - i8] == this.buf[this.readPos + i6] && this.buf[this.readPos - i8] == this.buf[this.readPos]) {
                int i13 = 0;
                do {
                    i13++;
                    if (i13 >= i2) {
                        break;
                    }
                } while (this.buf[(this.readPos + i13) - i8] == this.buf[this.readPos + i13]);
                if (i13 > i6) {
                    this.matches.len[this.matches.count] = i13;
                    this.matches.dist[this.matches.count] = i8 - 1;
                    this.matches.count++;
                    if (i13 >= i3) {
                        return this.matches;
                    }
                    i6 = i13;
                } else {
                    continue;
                }
            }
            hash4Pos = i12;
            i7 = i9;
        }
        return this.matches;
    }

    @Override // org.tukaani.xz.lz.LZEncoder
    public void putArraysToCache(ArrayCache arrayCache) {
        arrayCache.putArray(this.chain);
        this.hash.putArraysToCache(arrayCache);
        super.putArraysToCache(arrayCache);
    }

    @Override // org.tukaani.xz.lz.LZEncoder
    public void skip(int i) {
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                return;
            }
            if (movePos() != 0) {
                this.hash.calcHashes(this.buf, this.readPos);
                this.chain[this.cyclicPos] = this.hash.getHash4Pos();
                this.hash.updateTables(this.lzPos);
            }
            i = i2;
        }
    }
}
