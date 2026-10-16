package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.UByte;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.DecoderUtil;
import org.tukaani.xz.common.StreamFlags;
import org.tukaani.xz.index.IndexHash;

/* JADX INFO: loaded from: classes3.dex */
public class SingleXZInputStream extends InputStream {
    private final ArrayCache arrayCache;
    private BlockInputStream blockDecoder;
    private final Check check;
    private boolean endReached;
    private IOException exception;
    private InputStream in;
    private final IndexHash indexHash;
    private final int memoryLimit;
    private final StreamFlags streamHeaderFlags;
    private final byte[] tempBuf;
    private final boolean verifyCheck;

    public SingleXZInputStream(InputStream inputStream) throws IOException {
        this(inputStream, -1);
    }

    public SingleXZInputStream(InputStream inputStream, int i) throws IOException {
        this(inputStream, i, true);
    }

    public SingleXZInputStream(InputStream inputStream, int i, ArrayCache arrayCache) throws IOException {
        this(inputStream, i, true, arrayCache);
    }

    public SingleXZInputStream(InputStream inputStream, int i, boolean z) throws IOException {
        this(inputStream, i, z, ArrayCache.getDefaultCache());
    }

    public SingleXZInputStream(InputStream inputStream, int i, boolean z, ArrayCache arrayCache) throws IOException {
        this(inputStream, i, z, readStreamHeader(inputStream), arrayCache);
    }

    SingleXZInputStream(InputStream inputStream, int i, boolean z, byte[] bArr, ArrayCache arrayCache) throws IOException {
        this.blockDecoder = null;
        this.indexHash = new IndexHash();
        this.endReached = false;
        this.exception = null;
        this.tempBuf = new byte[1];
        this.arrayCache = arrayCache;
        this.in = inputStream;
        this.memoryLimit = i;
        this.verifyCheck = z;
        StreamFlags streamFlagsDecodeStreamHeader = DecoderUtil.decodeStreamHeader(bArr);
        this.streamHeaderFlags = streamFlagsDecodeStreamHeader;
        this.check = Check.getInstance(streamFlagsDecodeStreamHeader.checkType);
    }

    public SingleXZInputStream(InputStream inputStream, ArrayCache arrayCache) throws IOException {
        this(inputStream, -1, arrayCache);
    }

    private static byte[] readStreamHeader(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[12];
        new DataInputStream(inputStream).readFully(bArr);
        return bArr;
    }

    private void validateStreamFooter() throws IOException {
        byte[] bArr = new byte[12];
        new DataInputStream(this.in).readFully(bArr);
        StreamFlags streamFlagsDecodeStreamFooter = DecoderUtil.decodeStreamFooter(bArr);
        if (!DecoderUtil.areStreamFlagsEqual(this.streamHeaderFlags, streamFlagsDecodeStreamFooter) || this.indexHash.getIndexSize() != streamFlagsDecodeStreamFooter.backwardSize) {
            throw new CorruptedInputException("XZ Stream Footer does not match Stream Header");
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        BlockInputStream blockInputStream = this.blockDecoder;
        if (blockInputStream == null) {
            return 0;
        }
        return blockInputStream.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        close(true);
    }

    public void close(boolean z) throws IOException {
        if (this.in != null) {
            BlockInputStream blockInputStream = this.blockDecoder;
            if (blockInputStream != null) {
                blockInputStream.close();
                this.blockDecoder = null;
            }
            if (z) {
                try {
                    this.in.close();
                } finally {
                    this.in = null;
                }
            }
        }
    }

    public String getCheckName() {
        return this.check.getName();
    }

    public int getCheckType() {
        return this.streamHeaderFlags.checkType;
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
        if (this.endReached) {
            return -1;
        }
        int i4 = i2;
        int i5 = 0;
        int i6 = i;
        while (i4 > 0) {
            try {
                if (this.blockDecoder == null) {
                    try {
                        this.blockDecoder = new BlockInputStream(this.in, this.check, this.verifyCheck, this.memoryLimit, -1L, -1L, this.arrayCache);
                    } catch (IndexIndicatorException unused) {
                        this.indexHash.validate(this.in);
                        validateStreamFooter();
                        this.endReached = true;
                        if (i5 > 0) {
                            return i5;
                        }
                        return -1;
                    }
                }
                int i7 = this.blockDecoder.read(bArr, i6, i4);
                if (i7 > 0) {
                    i5 += i7;
                    i6 += i7;
                    i4 -= i7;
                } else if (i7 == -1) {
                    this.indexHash.add(this.blockDecoder.getUnpaddedSize(), this.blockDecoder.getUncompressedSize());
                    this.blockDecoder = null;
                }
            } catch (IOException e) {
                this.exception = e;
                if (i5 == 0) {
                    throw e;
                }
            }
        }
        return i5;
    }
}
