package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.UByte;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.DecoderUtil;
import org.tukaani.xz.common.StreamFlags;
import org.tukaani.xz.index.BlockInfo;
import org.tukaani.xz.index.IndexDecoder;

/* JADX INFO: loaded from: classes3.dex */
public class SeekableXZInputStream extends SeekableInputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final ArrayCache arrayCache;
    private int blockCount;
    private BlockInputStream blockDecoder;
    private Check check;
    private int checkTypes;
    private final BlockInfo curBlockInfo;
    private long curPos;
    private boolean endReached;
    private IOException exception;
    private SeekableInputStream in;
    private int indexMemoryUsage;
    private long largestBlockSize;
    private final int memoryLimit;
    private final BlockInfo queriedBlockInfo;
    private boolean seekNeeded;
    private long seekPos;
    private final ArrayList<IndexDecoder> streams;
    private final byte[] tempBuf;
    private long uncompressedSize;
    private final boolean verifyCheck;

    public SeekableXZInputStream(SeekableInputStream seekableInputStream) throws IOException {
        this(seekableInputStream, -1);
    }

    public SeekableXZInputStream(SeekableInputStream seekableInputStream, int i) throws IOException {
        this(seekableInputStream, i, true);
    }

    public SeekableXZInputStream(SeekableInputStream seekableInputStream, int i, ArrayCache arrayCache) throws IOException {
        this(seekableInputStream, i, true, arrayCache);
    }

    public SeekableXZInputStream(SeekableInputStream seekableInputStream, int i, boolean z) throws IOException {
        this(seekableInputStream, i, z, ArrayCache.getDefaultCache());
    }

    public SeekableXZInputStream(SeekableInputStream seekableInputStream, int i, boolean z, ArrayCache arrayCache) throws IOException {
        long j;
        this.indexMemoryUsage = 0;
        this.streams = new ArrayList<>();
        this.checkTypes = 0;
        long j2 = 0;
        this.uncompressedSize = 0L;
        this.largestBlockSize = 0L;
        this.blockCount = 0;
        this.blockDecoder = null;
        this.curPos = 0L;
        this.seekNeeded = false;
        this.endReached = false;
        this.exception = null;
        int i2 = 1;
        this.tempBuf = new byte[1];
        this.arrayCache = arrayCache;
        this.verifyCheck = z;
        this.in = seekableInputStream;
        DataInputStream dataInputStream = new DataInputStream(seekableInputStream);
        seekableInputStream.seek(0L);
        byte[] bArr = new byte[XZ.HEADER_MAGIC.length];
        dataInputStream.readFully(bArr);
        if (!Arrays.equals(bArr, XZ.HEADER_MAGIC)) {
            throw new XZFormatException();
        }
        long length = seekableInputStream.length();
        if ((3 & length) != 0) {
            throw new CorruptedInputException("XZ file size is not a multiple of 4 bytes");
        }
        byte[] bArr2 = new byte[12];
        int memoryUsage = i;
        while (true) {
            long j3 = j2;
            while (true) {
                if (length <= j2) {
                    this.memoryLimit = memoryUsage;
                    ArrayList<IndexDecoder> arrayList = this.streams;
                    IndexDecoder indexDecoder = arrayList.get(arrayList.size() - 1);
                    int size = this.streams.size() - 2;
                    while (size >= 0) {
                        IndexDecoder indexDecoder2 = this.streams.get(size);
                        indexDecoder2.setOffsets(indexDecoder);
                        size--;
                        indexDecoder = indexDecoder2;
                    }
                    ArrayList<IndexDecoder> arrayList2 = this.streams;
                    IndexDecoder indexDecoder3 = arrayList2.get(arrayList2.size() - 1);
                    this.curBlockInfo = new BlockInfo(indexDecoder3);
                    this.queriedBlockInfo = new BlockInfo(indexDecoder3);
                    return;
                }
                if (length < 12) {
                    throw new CorruptedInputException();
                }
                j = length - 12;
                seekableInputStream.seek(j);
                dataInputStream.readFully(bArr2);
                if (bArr2[8] != 0 || bArr2[9] != 0 || bArr2[10] != 0 || bArr2[11] != 0) {
                    break;
                }
                j3 += 4;
                length -= 4;
                j2 = 0;
            }
            StreamFlags streamFlagsDecodeStreamFooter = DecoderUtil.decodeStreamFooter(bArr2);
            if (streamFlagsDecodeStreamFooter.backwardSize >= j) {
                throw new CorruptedInputException("Backward Size in XZ Stream Footer is too big");
            }
            this.check = Check.getInstance(streamFlagsDecodeStreamFooter.checkType);
            this.checkTypes |= i2 << streamFlagsDecodeStreamFooter.checkType;
            seekableInputStream.seek(j - streamFlagsDecodeStreamFooter.backwardSize);
            try {
                IndexDecoder indexDecoder4 = new IndexDecoder(seekableInputStream, streamFlagsDecodeStreamFooter, j3, memoryUsage);
                this.indexMemoryUsage += indexDecoder4.getMemoryUsage();
                memoryUsage = memoryUsage >= 0 ? memoryUsage - indexDecoder4.getMemoryUsage() : memoryUsage;
                if (this.largestBlockSize < indexDecoder4.getLargestBlockSize()) {
                    this.largestBlockSize = indexDecoder4.getLargestBlockSize();
                }
                long streamSize = indexDecoder4.getStreamSize() - 12;
                if (j < streamSize) {
                    throw new CorruptedInputException("XZ Index indicates too big compressed size for the XZ Stream");
                }
                length = j - streamSize;
                seekableInputStream.seek(length);
                dataInputStream.readFully(bArr2);
                if (!DecoderUtil.areStreamFlagsEqual(DecoderUtil.decodeStreamHeader(bArr2), streamFlagsDecodeStreamFooter)) {
                    throw new CorruptedInputException("XZ Stream Footer does not match Stream Header");
                }
                long uncompressedSize = this.uncompressedSize + indexDecoder4.getUncompressedSize();
                this.uncompressedSize = uncompressedSize;
                if (uncompressedSize < 0) {
                    throw new UnsupportedOptionsException("XZ file is too big");
                }
                int recordCount = this.blockCount + indexDecoder4.getRecordCount();
                this.blockCount = recordCount;
                if (recordCount < 0) {
                    throw new UnsupportedOptionsException("XZ file has over 2147483647 Blocks");
                }
                this.streams.add(indexDecoder4);
                j2 = 0;
                i2 = 1;
            } catch (MemoryLimitException e) {
                int memoryNeeded = e.getMemoryNeeded();
                int i3 = this.indexMemoryUsage;
                throw new MemoryLimitException(memoryNeeded + i3, memoryUsage + i3);
            }
        }
    }

    public SeekableXZInputStream(SeekableInputStream seekableInputStream, ArrayCache arrayCache) throws IOException {
        this(seekableInputStream, -1, arrayCache);
    }

    private void initBlockDecoder() throws IOException {
        try {
            if (this.blockDecoder != null) {
                this.blockDecoder.close();
                this.blockDecoder = null;
            }
            this.blockDecoder = new BlockInputStream(this.in, this.check, this.verifyCheck, this.memoryLimit, this.curBlockInfo.unpaddedSize, this.curBlockInfo.uncompressedSize, this.arrayCache);
        } catch (IndexIndicatorException unused) {
            throw new CorruptedInputException();
        } catch (MemoryLimitException e) {
            int memoryNeeded = e.getMemoryNeeded();
            int i = this.indexMemoryUsage;
            throw new MemoryLimitException(memoryNeeded + i, this.memoryLimit + i);
        }
    }

    private void locateBlockByNumber(BlockInfo blockInfo, int i) {
        if (i < 0 || i >= this.blockCount) {
            throw new IndexOutOfBoundsException("Invalid XZ Block number: " + i);
        }
        if (blockInfo.blockNumber == i) {
            return;
        }
        int i2 = 0;
        while (true) {
            IndexDecoder indexDecoder = this.streams.get(i2);
            if (indexDecoder.hasRecord(i)) {
                indexDecoder.setBlockInfo(blockInfo, i);
                return;
            }
            i2++;
        }
    }

    private void locateBlockByPos(BlockInfo blockInfo, long j) {
        if (j < 0 || j >= this.uncompressedSize) {
            throw new IndexOutOfBoundsException("Invalid uncompressed position: " + j);
        }
        int i = 0;
        while (true) {
            IndexDecoder indexDecoder = this.streams.get(i);
            if (indexDecoder.hasUncompressedOffset(j)) {
                indexDecoder.locateBlock(blockInfo, j);
                return;
            }
            i++;
        }
    }

    private void seek() throws IOException {
        if (!this.seekNeeded) {
            if (this.curBlockInfo.hasNext()) {
                this.curBlockInfo.setNext();
                initBlockDecoder();
                return;
            }
            this.seekPos = this.curPos;
        }
        this.seekNeeded = false;
        long j = this.seekPos;
        if (j >= this.uncompressedSize) {
            this.curPos = j;
            BlockInputStream blockInputStream = this.blockDecoder;
            if (blockInputStream != null) {
                blockInputStream.close();
                this.blockDecoder = null;
            }
            this.endReached = true;
            return;
        }
        this.endReached = false;
        locateBlockByPos(this.curBlockInfo, j);
        if (this.curPos <= this.curBlockInfo.uncompressedOffset || this.curPos > this.seekPos) {
            this.in.seek(this.curBlockInfo.compressedOffset);
            this.check = Check.getInstance(this.curBlockInfo.getCheckType());
            initBlockDecoder();
            this.curPos = this.curBlockInfo.uncompressedOffset;
        }
        long j2 = this.seekPos;
        long j3 = this.curPos;
        if (j2 > j3) {
            long j4 = j2 - j3;
            if (this.blockDecoder.skip(j4) != j4) {
                throw new CorruptedInputException();
            }
            this.curPos = this.seekPos;
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        BlockInputStream blockInputStream;
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        if (this.endReached || this.seekNeeded || (blockInputStream = this.blockDecoder) == null) {
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

    public int getBlockCheckType(int i) {
        locateBlockByNumber(this.queriedBlockInfo, i);
        return this.queriedBlockInfo.getCheckType();
    }

    public long getBlockCompPos(int i) {
        locateBlockByNumber(this.queriedBlockInfo, i);
        return this.queriedBlockInfo.compressedOffset;
    }

    public long getBlockCompSize(int i) {
        locateBlockByNumber(this.queriedBlockInfo, i);
        return (this.queriedBlockInfo.unpaddedSize + 3) & (-4);
    }

    public int getBlockCount() {
        return this.blockCount;
    }

    public int getBlockNumber(long j) {
        locateBlockByPos(this.queriedBlockInfo, j);
        return this.queriedBlockInfo.blockNumber;
    }

    public long getBlockPos(int i) {
        locateBlockByNumber(this.queriedBlockInfo, i);
        return this.queriedBlockInfo.uncompressedOffset;
    }

    public long getBlockSize(int i) {
        locateBlockByNumber(this.queriedBlockInfo, i);
        return this.queriedBlockInfo.uncompressedSize;
    }

    public int getCheckTypes() {
        return this.checkTypes;
    }

    public int getIndexMemoryUsage() {
        return this.indexMemoryUsage;
    }

    public long getLargestBlockSize() {
        return this.largestBlockSize;
    }

    public int getStreamCount() {
        return this.streams.size();
    }

    @Override // org.tukaani.xz.SeekableInputStream
    public long length() {
        return this.uncompressedSize;
    }

    @Override // org.tukaani.xz.SeekableInputStream
    public long position() throws IOException {
        if (this.in != null) {
            return this.seekNeeded ? this.seekPos : this.curPos;
        }
        throw new XZIOException("Stream closed");
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
        try {
            if (this.seekNeeded) {
                seek();
            }
            if (this.endReached) {
                return -1;
            }
            while (i2 > 0) {
                if (this.blockDecoder == null) {
                    seek();
                    if (this.endReached) {
                        break;
                    }
                }
                int i5 = this.blockDecoder.read(bArr, i, i2);
                if (i5 > 0) {
                    this.curPos += (long) i5;
                    i4 += i5;
                    i += i5;
                    i2 -= i5;
                } else if (i5 == -1) {
                    this.blockDecoder = null;
                }
            }
        } catch (IOException e) {
            e = e;
            if (e instanceof EOFException) {
                e = new CorruptedInputException();
            }
            this.exception = e;
            if (i4 == 0) {
                throw e;
            }
        }
        return i4;
    }

    @Override // org.tukaani.xz.SeekableInputStream
    public void seek(long j) throws IOException {
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        if (j >= 0) {
            this.seekPos = j;
            this.seekNeeded = true;
        } else {
            throw new XZIOException("Negative seek position: " + j);
        }
    }

    public void seekToBlock(int i) throws IOException {
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        if (i >= 0 && i < this.blockCount) {
            this.seekPos = getBlockPos(i);
            this.seekNeeded = true;
        } else {
            throw new XZIOException("Invalid XZ Block number: " + i);
        }
    }
}
