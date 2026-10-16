package org.tukaani.xz;

import java.io.IOException;
import java.io.InputStream;
import kotlin.UByte;
import org.tukaani.xz.simple.SimpleFilter;

/* JADX INFO: loaded from: classes3.dex */
class SimpleInputStream extends InputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int FILTER_BUF_SIZE = 4096;
    private InputStream in;
    private final SimpleFilter simpleFilter;
    private final byte[] filterBuf = new byte[4096];
    private int pos = 0;
    private int filtered = 0;
    private int unfiltered = 0;
    private boolean endReached = false;
    private IOException exception = null;
    private final byte[] tempBuf = new byte[1];

    SimpleInputStream(InputStream inputStream, SimpleFilter simpleFilter) {
        if (inputStream == null) {
            throw null;
        }
        this.in = inputStream;
        this.simpleFilter = simpleFilter;
    }

    static int getMemoryUsage() {
        return 5;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException == null) {
            return this.filtered;
        }
        throw iOException;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.in;
        if (inputStream != null) {
            try {
                inputStream.close();
            } finally {
                this.in = null;
            }
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.tempBuf, 0, 1) == -1) {
            return -1;
        }
        return this.tempBuf[0] & UByte.MAX_VALUE;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (i < 0 || i2 < 0 || (i3 = i + i2) < 0 || i3 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return 0;
        }
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        int i4 = 0;
        while (true) {
            try {
                int iMin = Math.min(this.filtered, i2);
                System.arraycopy(this.filterBuf, this.pos, bArr, i, iMin);
                int i5 = this.pos + iMin;
                this.pos = i5;
                int i6 = this.filtered - iMin;
                this.filtered = i6;
                i += iMin;
                i2 -= iMin;
                i4 += iMin;
                if (i5 + i6 + this.unfiltered == 4096) {
                    System.arraycopy(this.filterBuf, i5, this.filterBuf, 0, i6 + this.unfiltered);
                    this.pos = 0;
                }
                if (i2 == 0 || this.endReached) {
                    break;
                    break;
                }
                int i7 = this.in.read(this.filterBuf, this.pos + this.filtered + this.unfiltered, 4096 - ((this.pos + this.filtered) + this.unfiltered));
                if (i7 == -1) {
                    this.endReached = true;
                    this.filtered = this.unfiltered;
                    this.unfiltered = 0;
                } else {
                    int i8 = this.unfiltered + i7;
                    this.unfiltered = i8;
                    int iCode = this.simpleFilter.code(this.filterBuf, this.pos, i8);
                    this.filtered = iCode;
                    this.unfiltered -= iCode;
                }
            } catch (IOException e) {
                this.exception = e;
                throw e;
            }
        }
        if (i4 > 0) {
            return i4;
        }
        return -1;
    }
}
