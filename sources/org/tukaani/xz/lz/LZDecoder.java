package org.tukaani.xz.lz;

import java.io.DataInputStream;
import java.io.IOException;
import kotlin.UByte;
import org.tukaani.xz.ArrayCache;
import org.tukaani.xz.CorruptedInputException;

/* JADX INFO: loaded from: classes3.dex */
public final class LZDecoder {
    private final byte[] buf;
    private final int bufSize;
    private int full;
    private int pos;
    private int start;
    private int limit = 0;
    private int pendingLen = 0;
    private int pendingDist = 0;

    public LZDecoder(int i, byte[] bArr, ArrayCache arrayCache) {
        this.start = 0;
        this.pos = 0;
        this.full = 0;
        this.bufSize = i;
        this.buf = arrayCache.getByteArray(i, false);
        if (bArr != null) {
            int iMin = Math.min(bArr.length, i);
            this.pos = iMin;
            this.full = iMin;
            this.start = iMin;
            System.arraycopy(bArr, bArr.length - iMin, this.buf, 0, iMin);
        }
    }

    public void copyUncompressed(DataInputStream dataInputStream, int i) throws IOException {
        int iMin = Math.min(this.bufSize - this.pos, i);
        dataInputStream.readFully(this.buf, this.pos, iMin);
        int i2 = this.pos + iMin;
        this.pos = i2;
        if (this.full < i2) {
            this.full = i2;
        }
    }

    public int flush(byte[] bArr, int i) {
        int i2 = this.pos;
        int i3 = i2 - this.start;
        if (i2 == this.bufSize) {
            this.pos = 0;
        }
        System.arraycopy(this.buf, this.start, bArr, i, i3);
        this.start = this.pos;
        return i3;
    }

    public int getByte(int i) {
        int i2 = this.pos;
        int i3 = (i2 - i) - 1;
        if (i >= i2) {
            i3 += this.bufSize;
        }
        return this.buf[i3] & UByte.MAX_VALUE;
    }

    public int getPos() {
        return this.pos;
    }

    public boolean hasPending() {
        return this.pendingLen > 0;
    }

    public boolean hasSpace() {
        return this.pos < this.limit;
    }

    public void putArraysToCache(ArrayCache arrayCache) {
        arrayCache.putArray(this.buf);
    }

    public void putByte(byte b) {
        byte[] bArr = this.buf;
        int i = this.pos;
        int i2 = i + 1;
        this.pos = i2;
        bArr[i] = b;
        if (this.full < i2) {
            this.full = i2;
        }
    }

    public void repeat(int i, int i2) throws IOException {
        if (i < 0 || i >= this.full) {
            throw new CorruptedInputException();
        }
        int iMin = Math.min(this.limit - this.pos, i2);
        this.pendingLen = i2 - iMin;
        this.pendingDist = i;
        int i3 = this.pos;
        int i4 = (i3 - i) - 1;
        if (i >= i3) {
            i4 += this.bufSize;
        }
        do {
            byte[] bArr = this.buf;
            int i5 = this.pos;
            this.pos = i5 + 1;
            int i6 = i4 + 1;
            bArr[i5] = bArr[i4];
            i4 = i6 == this.bufSize ? 0 : i6;
            iMin--;
        } while (iMin > 0);
        int i7 = this.full;
        int i8 = this.pos;
        if (i7 < i8) {
            this.full = i8;
        }
    }

    public void repeatPending() throws IOException {
        int i = this.pendingLen;
        if (i > 0) {
            repeat(this.pendingDist, i);
        }
    }

    public void reset() {
        this.start = 0;
        this.pos = 0;
        this.full = 0;
        this.limit = 0;
        this.buf[this.bufSize - 1] = 0;
    }

    public void setLimit(int i) {
        int i2 = this.bufSize;
        int i3 = this.pos;
        if (i2 - i3 <= i) {
            this.limit = i2;
        } else {
            this.limit = i3 + i;
        }
    }
}
