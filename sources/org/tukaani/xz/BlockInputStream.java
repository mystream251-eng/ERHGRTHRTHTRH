package org.tukaani.xz;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.DecoderUtil;

/* JADX INFO: loaded from: classes3.dex */
class BlockInputStream extends InputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Check check;
    private long compressedSizeInHeader;
    private long compressedSizeLimit;
    private InputStream filterChain;
    private final int headerSize;
    private final CountingInputStream inCounted;
    private final DataInputStream inData;
    private long uncompressedSizeInHeader;
    private final boolean verifyCheck;
    private long uncompressedSize = 0;
    private boolean endReached = false;
    private final byte[] tempBuf = new byte[1];

    public BlockInputStream(InputStream inputStream, Check check, boolean z, int i, long j, long j2, ArrayCache arrayCache) throws IndexIndicatorException, IOException {
        String str;
        this.uncompressedSizeInHeader = -1L;
        this.compressedSizeInHeader = -1L;
        this.check = check;
        this.verifyCheck = z;
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.inData = dataInputStream;
        byte[] bArr = new byte[1024];
        dataInputStream.readFully(bArr, 0, 1);
        if (bArr[0] == 0) {
            throw new IndexIndicatorException();
        }
        int i2 = ((bArr[0] & UByte.MAX_VALUE) + 1) * 4;
        this.headerSize = i2;
        this.inData.readFully(bArr, 1, i2 - 1);
        int i3 = this.headerSize;
        String str2 = "XZ Block Header is corrupt";
        if (!DecoderUtil.isCRC32Valid(bArr, 0, i3 - 4, i3 - 4)) {
            throw new CorruptedInputException("XZ Block Header is corrupt");
        }
        if ((bArr[1] & 60) != 0) {
            throw new UnsupportedOptionsException("Unsupported options in XZ Block Header");
        }
        int i4 = (bArr[1] & 3) + 1;
        long[] jArr = new long[i4];
        byte[][] bArr2 = new byte[i4][];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, 2, this.headerSize - 6);
        try {
            this.compressedSizeLimit = (9223372036854775804L - ((long) this.headerSize)) - ((long) check.getSize());
            if ((bArr[1] & 64) != 0) {
                long jDecodeVLI = DecoderUtil.decodeVLI(byteArrayInputStream);
                this.compressedSizeInHeader = jDecodeVLI;
                if (jDecodeVLI == 0 || jDecodeVLI > this.compressedSizeLimit) {
                    throw new CorruptedInputException();
                }
                this.compressedSizeLimit = jDecodeVLI;
            }
            if ((bArr[1] & ByteCompanionObject.MIN_VALUE) != 0) {
                this.uncompressedSizeInHeader = DecoderUtil.decodeVLI(byteArrayInputStream);
            }
            int i5 = 0;
            while (i5 < i4) {
                jArr[i5] = DecoderUtil.decodeVLI(byteArrayInputStream);
                long jDecodeVLI2 = DecoderUtil.decodeVLI(byteArrayInputStream);
                str = str2;
                if (jDecodeVLI2 > byteArrayInputStream.available()) {
                    throw new CorruptedInputException();
                }
                try {
                    bArr2[i5] = new byte[(int) jDecodeVLI2];
                    byteArrayInputStream.read(bArr2[i5]);
                    i5++;
                    str2 = str;
                } catch (IOException unused) {
                }
                throw new CorruptedInputException(str);
            }
            for (int iAvailable = byteArrayInputStream.available(); iAvailable > 0; iAvailable--) {
                if (byteArrayInputStream.read() != 0) {
                    throw new UnsupportedOptionsException("Unsupported options in XZ Block Header");
                }
            }
            if (j != -1) {
                long size = this.headerSize + check.getSize();
                if (size >= j) {
                    throw new CorruptedInputException("XZ Index does not match a Block Header");
                }
                long j3 = j - size;
                if (j3 <= this.compressedSizeLimit) {
                    long j4 = this.compressedSizeInHeader;
                    if (j4 == -1 || j4 == j3) {
                        long j5 = this.uncompressedSizeInHeader;
                        if (j5 != -1 && j5 != j2) {
                            throw new CorruptedInputException("XZ Index does not match a Block Header");
                        }
                        this.compressedSizeLimit = j3;
                        this.compressedSizeInHeader = j3;
                        this.uncompressedSizeInHeader = j2;
                    }
                }
                throw new CorruptedInputException("XZ Index does not match a Block Header");
            }
            FilterDecoder[] filterDecoderArr = new FilterDecoder[i4];
            for (int i6 = 0; i6 < i4; i6++) {
                if (jArr[i6] == 33) {
                    filterDecoderArr[i6] = new LZMA2Decoder(bArr2[i6]);
                } else if (jArr[i6] == 3) {
                    filterDecoderArr[i6] = new DeltaDecoder(bArr2[i6]);
                } else {
                    if (!BCJDecoder.isBCJFilterID(jArr[i6])) {
                        throw new UnsupportedOptionsException("Unknown Filter ID " + jArr[i6]);
                    }
                    filterDecoderArr[i6] = new BCJDecoder(jArr[i6], bArr2[i6]);
                }
            }
            RawCoder.validate(filterDecoderArr);
            if (i >= 0) {
                int memoryUsage = 0;
                for (int i7 = 0; i7 < i4; i7++) {
                    memoryUsage += filterDecoderArr[i7].getMemoryUsage();
                }
                if (memoryUsage > i) {
                    throw new MemoryLimitException(memoryUsage, i);
                }
            }
            CountingInputStream countingInputStream = new CountingInputStream(inputStream);
            this.inCounted = countingInputStream;
            this.filterChain = countingInputStream;
            for (int i8 = i4 - 1; i8 >= 0; i8--) {
                this.filterChain = filterDecoderArr[i8].getInputStream(this.filterChain, arrayCache);
            }
        } catch (IOException unused2) {
            str = "XZ Block Header is corrupt";
        }
    }

    private void validate() throws IOException {
        long size = this.inCounted.getSize();
        long j = this.compressedSizeInHeader;
        if (j == -1 || j == size) {
            long j2 = this.uncompressedSizeInHeader;
            if (j2 == -1 || j2 == this.uncompressedSize) {
                while (true) {
                    long j3 = 1 + size;
                    if ((size & 3) == 0) {
                        byte[] bArr = new byte[this.check.getSize()];
                        this.inData.readFully(bArr);
                        if (!this.verifyCheck || Arrays.equals(this.check.finish(), bArr)) {
                            return;
                        }
                        throw new CorruptedInputException("Integrity check (" + this.check.getName() + ") does not match");
                    }
                    if (this.inData.readUnsignedByte() != 0) {
                        throw new CorruptedInputException();
                    }
                    size = j3;
                }
            }
        }
        throw new CorruptedInputException();
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.filterChain.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            this.filterChain.close();
        } catch (IOException unused) {
        }
        this.filterChain = null;
    }

    public long getUncompressedSize() {
        return this.uncompressedSize;
    }

    public long getUnpaddedSize() {
        return ((long) this.headerSize) + this.inCounted.getSize() + ((long) this.check.getSize());
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
        if (this.endReached) {
            return -1;
        }
        int i3 = this.filterChain.read(bArr, i, i2);
        if (i3 > 0) {
            if (this.verifyCheck) {
                this.check.update(bArr, i, i3);
            }
            this.uncompressedSize += (long) i3;
            long size = this.inCounted.getSize();
            if (size >= 0 && size <= this.compressedSizeLimit) {
                long j = this.uncompressedSize;
                if (j >= 0) {
                    long j2 = this.uncompressedSizeInHeader;
                    if (j2 == -1 || j <= j2) {
                        if (i3 < i2 || this.uncompressedSize == this.uncompressedSizeInHeader) {
                            if (this.filterChain.read() != -1) {
                                throw new CorruptedInputException();
                            }
                            validate();
                            this.endReached = true;
                        }
                    }
                }
            }
            throw new CorruptedInputException();
        }
        if (i3 == -1) {
            validate();
            this.endReached = true;
        }
        return i3;
    }
}
