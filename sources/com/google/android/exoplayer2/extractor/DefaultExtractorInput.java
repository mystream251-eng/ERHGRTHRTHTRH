package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultExtractorInput implements ExtractorInput {
    private static final int PEEK_MAX_FREE_SPACE = 524288;
    private static final int PEEK_MIN_FREE_SPACE_AFTER_RESIZE = 65536;
    private static final int SCRATCH_SPACE_SIZE = 4096;
    private final DataSource dataSource;
    private int peekBufferLength;
    private int peekBufferPosition;
    private long position;
    private final long streamLength;
    private byte[] peekBuffer = new byte[65536];
    private final byte[] scratchSpace = new byte[4096];

    public DefaultExtractorInput(DataSource dataSource, long j, long j2) {
        this.dataSource = dataSource;
        this.position = j;
        this.streamLength = j2;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public int read(byte[] bArr, int i, int i2) throws InterruptedException, IOException {
        int fromPeekBuffer = readFromPeekBuffer(bArr, i, i2);
        if (fromPeekBuffer == 0) {
            fromPeekBuffer = readFromDataSource(bArr, i, i2, 0, true);
        }
        commitBytesRead(fromPeekBuffer);
        return fromPeekBuffer;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public boolean readFully(byte[] bArr, int i, int i2, boolean z) throws InterruptedException, IOException {
        int fromPeekBuffer = readFromPeekBuffer(bArr, i, i2);
        while (fromPeekBuffer < i2 && fromPeekBuffer != -1) {
            fromPeekBuffer = readFromDataSource(bArr, i, i2, fromPeekBuffer, z);
        }
        commitBytesRead(fromPeekBuffer);
        return fromPeekBuffer != -1;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void readFully(byte[] bArr, int i, int i2) throws InterruptedException, IOException {
        readFully(bArr, i, i2, false);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public int skip(int i) throws InterruptedException, IOException {
        int iSkipFromPeekBuffer = skipFromPeekBuffer(i);
        if (iSkipFromPeekBuffer == 0) {
            byte[] bArr = this.scratchSpace;
            iSkipFromPeekBuffer = readFromDataSource(bArr, 0, Math.min(i, bArr.length), 0, true);
        }
        commitBytesRead(iSkipFromPeekBuffer);
        return iSkipFromPeekBuffer;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public boolean skipFully(int i, boolean z) throws InterruptedException, IOException {
        int iSkipFromPeekBuffer = skipFromPeekBuffer(i);
        while (iSkipFromPeekBuffer < i && iSkipFromPeekBuffer != -1) {
            iSkipFromPeekBuffer = readFromDataSource(this.scratchSpace, -iSkipFromPeekBuffer, Math.min(i, this.scratchSpace.length + iSkipFromPeekBuffer), iSkipFromPeekBuffer, z);
        }
        commitBytesRead(iSkipFromPeekBuffer);
        return iSkipFromPeekBuffer != -1;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void skipFully(int i) throws InterruptedException, IOException {
        skipFully(i, false);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public boolean peekFully(byte[] bArr, int i, int i2, boolean z) throws InterruptedException, IOException {
        if (!advancePeekPosition(i2, z)) {
            return false;
        }
        System.arraycopy(this.peekBuffer, this.peekBufferPosition - i2, bArr, i, i2);
        return true;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void peekFully(byte[] bArr, int i, int i2) throws InterruptedException, IOException {
        peekFully(bArr, i, i2, false);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public boolean advancePeekPosition(int i, boolean z) throws InterruptedException, IOException {
        ensureSpaceForPeek(i);
        int fromDataSource = this.peekBufferLength - this.peekBufferPosition;
        while (fromDataSource < i) {
            fromDataSource = readFromDataSource(this.peekBuffer, this.peekBufferPosition, i, fromDataSource, z);
            if (fromDataSource == -1) {
                return false;
            }
            this.peekBufferLength = this.peekBufferPosition + fromDataSource;
        }
        this.peekBufferPosition += i;
        return true;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void advancePeekPosition(int i) throws InterruptedException, IOException {
        advancePeekPosition(i, false);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void resetPeekPosition() {
        this.peekBufferPosition = 0;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public long getPeekPosition() {
        return this.position + ((long) this.peekBufferPosition);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public long getPosition() {
        return this.position;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public long getLength() {
        return this.streamLength;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: E extends java.lang.Throwable */
    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public <E extends Throwable> void setRetryPosition(long j, E e) throws Throwable {
        Assertions.checkArgument(j >= 0);
        this.position = j;
        throw e;
    }

    private void ensureSpaceForPeek(int i) {
        int i2 = this.peekBufferPosition + i;
        byte[] bArr = this.peekBuffer;
        if (i2 > bArr.length) {
            this.peekBuffer = Arrays.copyOf(this.peekBuffer, Util.constrainValue(bArr.length * 2, 65536 + i2, i2 + 524288));
        }
    }

    private int skipFromPeekBuffer(int i) {
        int iMin = Math.min(this.peekBufferLength, i);
        updatePeekBuffer(iMin);
        return iMin;
    }

    private int readFromPeekBuffer(byte[] bArr, int i, int i2) {
        int i3 = this.peekBufferLength;
        if (i3 == 0) {
            return 0;
        }
        int iMin = Math.min(i3, i2);
        System.arraycopy(this.peekBuffer, 0, bArr, i, iMin);
        updatePeekBuffer(iMin);
        return iMin;
    }

    private void updatePeekBuffer(int i) {
        int i2 = this.peekBufferLength - i;
        this.peekBufferLength = i2;
        this.peekBufferPosition = 0;
        byte[] bArr = this.peekBuffer;
        if (i2 < bArr.length - 524288) {
            bArr = new byte[i2 + 65536];
        }
        System.arraycopy(this.peekBuffer, i, bArr, 0, this.peekBufferLength);
        this.peekBuffer = bArr;
    }

    private int readFromDataSource(byte[] bArr, int i, int i2, int i3, boolean z) throws InterruptedException, IOException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        int i4 = this.dataSource.read(bArr, i + i3, i2 - i3);
        if (i4 != -1) {
            return i3 + i4;
        }
        if (i3 == 0 && z) {
            return -1;
        }
        throw new EOFException();
    }

    private void commitBytesRead(int i) {
        if (i != -1) {
            this.position += (long) i;
        }
    }
}
