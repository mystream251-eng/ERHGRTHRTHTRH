package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.UByte;
import org.tukaani.xz.lz.LZDecoder;
import org.tukaani.xz.lzma.LZMADecoder;
import org.tukaani.xz.rangecoder.RangeDecoderFromBuffer;

/* JADX INFO: loaded from: classes3.dex */
public class LZMA2InputStream extends InputStream {
    private static final int COMPRESSED_SIZE_MAX = 65536;
    public static final int DICT_SIZE_MAX = 2147483632;
    public static final int DICT_SIZE_MIN = 4096;
    private final ArrayCache arrayCache;
    private boolean endReached;
    private IOException exception;
    private DataInputStream in;
    private boolean isLZMAChunk;
    private LZDecoder lz;
    private LZMADecoder lzma;
    private boolean needDictReset;
    private boolean needProps;
    private RangeDecoderFromBuffer rc;
    private final byte[] tempBuf;
    private int uncompressedSize;

    public LZMA2InputStream(InputStream inputStream, int i) {
        this(inputStream, i, null);
    }

    public LZMA2InputStream(InputStream inputStream, int i, byte[] bArr) {
        this(inputStream, i, bArr, ArrayCache.getDefaultCache());
    }

    LZMA2InputStream(InputStream inputStream, int i, byte[] bArr, ArrayCache arrayCache) {
        this.uncompressedSize = 0;
        this.isLZMAChunk = false;
        this.needDictReset = true;
        this.needProps = true;
        this.endReached = false;
        this.exception = null;
        this.tempBuf = new byte[1];
        if (inputStream == null) {
            throw null;
        }
        this.arrayCache = arrayCache;
        this.in = new DataInputStream(inputStream);
        this.rc = new RangeDecoderFromBuffer(65536, arrayCache);
        this.lz = new LZDecoder(getDictSize(i), bArr, arrayCache);
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        this.needDictReset = false;
    }

    private void decodeChunkHeader() throws IOException {
        int unsignedByte = this.in.readUnsignedByte();
        if (unsignedByte == 0) {
            this.endReached = true;
            putArraysToCache();
            return;
        }
        if (unsignedByte >= 224 || unsignedByte == 1) {
            this.needProps = true;
            this.needDictReset = false;
            this.lz.reset();
        } else if (this.needDictReset) {
            throw new CorruptedInputException();
        }
        if (unsignedByte < 128) {
            if (unsignedByte > 2) {
                throw new CorruptedInputException();
            }
            this.isLZMAChunk = false;
            this.uncompressedSize = this.in.readUnsignedShort() + 1;
            return;
        }
        this.isLZMAChunk = true;
        int i = (unsignedByte & 31) << 16;
        this.uncompressedSize = i;
        this.uncompressedSize = i + this.in.readUnsignedShort() + 1;
        int unsignedShort = this.in.readUnsignedShort() + 1;
        if (unsignedByte >= 192) {
            this.needProps = false;
            decodeProps();
        } else {
            if (this.needProps) {
                throw new CorruptedInputException();
            }
            if (unsignedByte >= 160) {
                this.lzma.reset();
            }
        }
        this.rc.prepareInputBuffer(this.in, unsignedShort);
    }

    private void decodeProps() throws IOException {
        int unsignedByte = this.in.readUnsignedByte();
        if (unsignedByte > 224) {
            throw new CorruptedInputException();
        }
        int i = unsignedByte / 45;
        int i2 = unsignedByte - ((i * 9) * 5);
        int i3 = i2 / 9;
        int i4 = i2 - (i3 * 9);
        if (i4 + i3 > 4) {
            throw new CorruptedInputException();
        }
        this.lzma = new LZMADecoder(this.lz, this.rc, i4, i3, i);
    }

    private static int getDictSize(int i) {
        if (i >= 4096 && i <= 2147483632) {
            return (i + 15) & (-16);
        }
        throw new IllegalArgumentException("Unsupported dictionary size " + i);
    }

    public static int getMemoryUsage(int i) {
        return (getDictSize(i) / 1024) + 104;
    }

    private void putArraysToCache() {
        LZDecoder lZDecoder = this.lz;
        if (lZDecoder != null) {
            lZDecoder.putArraysToCache(this.arrayCache);
            this.lz = null;
            this.rc.putArraysToCache(this.arrayCache);
            this.rc = null;
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        DataInputStream dataInputStream = this.in;
        if (dataInputStream == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException == null) {
            return this.isLZMAChunk ? this.uncompressedSize : Math.min(this.uncompressedSize, dataInputStream.available());
        }
        throw iOException;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.in != null) {
            putArraysToCache();
            try {
                this.in.close();
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
        int i4 = 0;
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
        if (this.endReached) {
            return -1;
        }
        while (i2 > 0) {
            try {
                if (this.uncompressedSize == 0) {
                    decodeChunkHeader();
                    if (this.endReached) {
                        if (i4 == 0) {
                            return -1;
                        }
                        return i4;
                    }
                }
                int iMin = Math.min(this.uncompressedSize, i2);
                if (this.isLZMAChunk) {
                    this.lz.setLimit(iMin);
                    this.lzma.decode();
                } else {
                    this.lz.copyUncompressed(this.in, iMin);
                }
                int iFlush = this.lz.flush(bArr, i);
                i += iFlush;
                i2 -= iFlush;
                i4 += iFlush;
                int i5 = this.uncompressedSize - iFlush;
                this.uncompressedSize = i5;
                if (i5 == 0 && (!this.rc.isFinished() || this.lz.hasPending())) {
                    throw new CorruptedInputException();
                }
            } catch (IOException e) {
                this.exception = e;
                throw e;
            }
        }
        return i4;
    }
}
