package org.tukaani.xz.index;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import org.tukaani.xz.CorruptedInputException;
import org.tukaani.xz.MemoryLimitException;
import org.tukaani.xz.SeekableInputStream;
import org.tukaani.xz.UnsupportedOptionsException;
import org.tukaani.xz.common.DecoderUtil;
import org.tukaani.xz.common.StreamFlags;

/* JADX INFO: loaded from: classes3.dex */
public class IndexDecoder extends IndexBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private long compressedOffset;
    private long largestBlockSize;
    private final int memoryUsage;
    private int recordOffset;
    private final StreamFlags streamFlags;
    private final long streamPadding;
    private final long[] uncompressed;
    private long uncompressedOffset;
    private final long[] unpadded;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    public IndexDecoder(SeekableInputStream seekableInputStream, StreamFlags streamFlags, long j, int i) throws IOException {
        String str;
        super(new CorruptedInputException("XZ Index is corrupt"));
        String str2 = i;
        String str3 = "XZ Index is corrupt";
        this.largestBlockSize = 0L;
        this.recordOffset = 0;
        this.compressedOffset = 0L;
        this.uncompressedOffset = 0L;
        this.streamFlags = streamFlags;
        this.streamPadding = j;
        long jPosition = (seekableInputStream.position() + streamFlags.backwardSize) - 4;
        CRC32 crc32 = new CRC32();
        CheckedInputStream checkedInputStream = new CheckedInputStream(seekableInputStream, crc32);
        if (checkedInputStream.read() != 0) {
            throw new CorruptedInputException("XZ Index is corrupt");
        }
        try {
            long jDecodeVLI = DecoderUtil.decodeVLI(checkedInputStream);
            try {
                if (jDecodeVLI >= streamFlags.backwardSize / 2) {
                    throw new CorruptedInputException("XZ Index is corrupt");
                }
                if (jDecodeVLI > 2147483647L) {
                    throw new UnsupportedOptionsException("XZ Index has over 2147483647 Records");
                }
                int i2 = ((int) (((16 * jDecodeVLI) + 1023) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID)) + 1;
                this.memoryUsage = i2;
                if (str2 >= 0 && i2 > str2) {
                    throw new MemoryLimitException(this.memoryUsage, str2);
                }
                int i3 = (int) jDecodeVLI;
                this.unpadded = new long[i3];
                this.uncompressed = new long[i3];
                int i4 = 0;
                while (i3 > 0) {
                    long jDecodeVLI2 = DecoderUtil.decodeVLI(checkedInputStream);
                    long jDecodeVLI3 = DecoderUtil.decodeVLI(checkedInputStream);
                    if (seekableInputStream.position() > jPosition) {
                        str = str3;
                        throw new CorruptedInputException(str);
                    }
                    String str4 = str3;
                    try {
                        this.unpadded[i4] = this.blocksSum + jDecodeVLI2;
                        str = str4;
                        try {
                            this.uncompressed[i4] = this.uncompressedSum + jDecodeVLI3;
                            i4++;
                            super.add(jDecodeVLI2, jDecodeVLI3);
                            if (this.largestBlockSize < jDecodeVLI3) {
                                this.largestBlockSize = jDecodeVLI3;
                            }
                            i3--;
                            str3 = str;
                        } catch (EOFException unused) {
                            str2 = str;
                        }
                    } catch (EOFException unused2) {
                        str2 = str4;
                    }
                    str2 = str;
                    throw new CorruptedInputException(str2);
                }
                String str5 = str3;
                int indexPaddingSize = getIndexPaddingSize();
                if (seekableInputStream.position() + ((long) indexPaddingSize) != jPosition) {
                    throw new CorruptedInputException(str5);
                }
                while (true) {
                    int i5 = indexPaddingSize - 1;
                    if (indexPaddingSize <= 0) {
                        long value = crc32.getValue();
                        for (int i6 = 0; i6 < 4; i6++) {
                            if (((value >>> (i6 * 8)) & 255) != seekableInputStream.read()) {
                                throw new CorruptedInputException(str5);
                            }
                        }
                        return;
                    }
                    if (checkedInputStream.read() != 0) {
                        throw new CorruptedInputException(str5);
                    }
                    indexPaddingSize = i5;
                }
            } catch (EOFException unused3) {
            }
        } catch (EOFException unused4) {
            str2 = "XZ Index is corrupt";
        }
    }

    @Override // org.tukaani.xz.index.IndexBase
    public /* bridge */ /* synthetic */ long getIndexSize() {
        return super.getIndexSize();
    }

    public long getLargestBlockSize() {
        return this.largestBlockSize;
    }

    public int getMemoryUsage() {
        return this.memoryUsage;
    }

    public int getRecordCount() {
        return (int) this.recordCount;
    }

    public StreamFlags getStreamFlags() {
        return this.streamFlags;
    }

    @Override // org.tukaani.xz.index.IndexBase
    public /* bridge */ /* synthetic */ long getStreamSize() {
        return super.getStreamSize();
    }

    public long getUncompressedSize() {
        return this.uncompressedSum;
    }

    public boolean hasRecord(int i) {
        int i2 = this.recordOffset;
        return i >= i2 && ((long) i) < ((long) i2) + this.recordCount;
    }

    public boolean hasUncompressedOffset(long j) {
        long j2 = this.uncompressedOffset;
        return j >= j2 && j < j2 + this.uncompressedSum;
    }

    public void locateBlock(BlockInfo blockInfo, long j) {
        long j2 = j - this.uncompressedOffset;
        int i = 0;
        int length = this.unpadded.length - 1;
        while (i < length) {
            int i2 = ((length - i) / 2) + i;
            if (this.uncompressed[i2] <= j2) {
                i = i2 + 1;
            } else {
                length = i2;
            }
        }
        setBlockInfo(blockInfo, this.recordOffset + i);
    }

    public void setBlockInfo(BlockInfo blockInfo, int i) {
        long j;
        blockInfo.index = this;
        blockInfo.blockNumber = i;
        int i2 = i - this.recordOffset;
        if (i2 == 0) {
            j = 0;
            blockInfo.compressedOffset = 0L;
        } else {
            int i3 = i2 - 1;
            blockInfo.compressedOffset = (this.unpadded[i3] + 3) & (-4);
            j = this.uncompressed[i3];
        }
        blockInfo.uncompressedOffset = j;
        blockInfo.unpaddedSize = this.unpadded[i2] - blockInfo.compressedOffset;
        blockInfo.uncompressedSize = this.uncompressed[i2] - blockInfo.uncompressedOffset;
        blockInfo.compressedOffset += this.compressedOffset + 12;
        blockInfo.uncompressedOffset += this.uncompressedOffset;
    }

    public void setOffsets(IndexDecoder indexDecoder) {
        this.recordOffset = indexDecoder.recordOffset + ((int) indexDecoder.recordCount);
        this.compressedOffset = indexDecoder.compressedOffset + indexDecoder.getStreamSize() + indexDecoder.streamPadding;
        this.uncompressedOffset = indexDecoder.uncompressedOffset + indexDecoder.uncompressedSum;
    }
}
