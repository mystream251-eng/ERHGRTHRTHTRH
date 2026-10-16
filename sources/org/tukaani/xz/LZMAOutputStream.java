package org.tukaani.xz;

import java.io.IOException;
import java.io.OutputStream;
import org.tukaani.xz.lz.LZEncoder;
import org.tukaani.xz.lzma.LZMAEncoder;
import org.tukaani.xz.rangecoder.RangeEncoderToStream;

/* JADX INFO: loaded from: classes3.dex */
public class LZMAOutputStream extends FinishableOutputStream {
    private final ArrayCache arrayCache;
    private long currentUncompressedSize;
    private IOException exception;
    private final long expectedUncompressedSize;
    private boolean finished;
    private LZEncoder lz;
    private LZMAEncoder lzma;
    private OutputStream out;
    private final int props;
    private final RangeEncoderToStream rc;
    private final byte[] tempBuf;
    private final boolean useEndMarker;

    public LZMAOutputStream(OutputStream outputStream, LZMA2Options lZMA2Options, long j) throws IOException {
        this(outputStream, lZMA2Options, j, ArrayCache.getDefaultCache());
    }

    public LZMAOutputStream(OutputStream outputStream, LZMA2Options lZMA2Options, long j, ArrayCache arrayCache) throws IOException {
        this(outputStream, lZMA2Options, true, j == -1, j, arrayCache);
    }

    public LZMAOutputStream(OutputStream outputStream, LZMA2Options lZMA2Options, boolean z) throws IOException {
        this(outputStream, lZMA2Options, z, ArrayCache.getDefaultCache());
    }

    public LZMAOutputStream(OutputStream outputStream, LZMA2Options lZMA2Options, boolean z, ArrayCache arrayCache) throws IOException {
        this(outputStream, lZMA2Options, false, z, -1L, arrayCache);
    }

    private LZMAOutputStream(OutputStream outputStream, LZMA2Options lZMA2Options, boolean z, boolean z2, long j, ArrayCache arrayCache) throws IOException {
        this.currentUncompressedSize = 0L;
        this.finished = false;
        this.exception = null;
        this.tempBuf = new byte[1];
        if (outputStream == null) {
            throw null;
        }
        if (j < -1) {
            throw new IllegalArgumentException("Invalid expected input size (less than -1)");
        }
        this.useEndMarker = z2;
        this.expectedUncompressedSize = j;
        this.arrayCache = arrayCache;
        this.out = outputStream;
        this.rc = new RangeEncoderToStream(outputStream);
        int dictSize = lZMA2Options.getDictSize();
        LZMAEncoder lZMAEncoder = LZMAEncoder.getInstance(this.rc, lZMA2Options.getLc(), lZMA2Options.getLp(), lZMA2Options.getPb(), lZMA2Options.getMode(), dictSize, 0, lZMA2Options.getNiceLen(), lZMA2Options.getMatchFinder(), lZMA2Options.getDepthLimit(), arrayCache);
        this.lzma = lZMAEncoder;
        this.lz = lZMAEncoder.getLZEncoder();
        byte[] presetDict = lZMA2Options.getPresetDict();
        if (presetDict != null && presetDict.length > 0) {
            if (z) {
                throw new UnsupportedOptionsException("Preset dictionary cannot be used in .lzma files (try a raw LZMA stream instead)");
            }
            this.lz.setPresetDict(dictSize, presetDict);
        }
        int pb = (((lZMA2Options.getPb() * 5) + lZMA2Options.getLp()) * 9) + lZMA2Options.getLc();
        this.props = pb;
        if (z) {
            outputStream.write(pb);
            int i = dictSize;
            for (int i2 = 0; i2 < 4; i2++) {
                outputStream.write(i & 255);
                i >>>= 8;
            }
            for (int i3 = 0; i3 < 8; i3++) {
                outputStream.write(((int) (j >>> (i3 * 8))) & 255);
            }
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.out != null) {
            try {
                finish();
            } catch (IOException unused) {
            }
            try {
                this.out.close();
            } catch (IOException e) {
                if (this.exception == null) {
                    this.exception = e;
                }
            }
            this.out = null;
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
    }

    @Override // org.tukaani.xz.FinishableOutputStream
    public void finish() throws IOException {
        if (this.finished) {
            return;
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        try {
            if (this.expectedUncompressedSize != -1 && this.expectedUncompressedSize != this.currentUncompressedSize) {
                throw new XZIOException("Expected uncompressed size (" + this.expectedUncompressedSize + ") doesn't equal the number of bytes written to the stream (" + this.currentUncompressedSize + ")");
            }
            this.lz.setFinishing();
            this.lzma.encodeForLZMA1();
            if (this.useEndMarker) {
                this.lzma.encodeLZMA1EndMarker();
            }
            this.rc.finish();
            this.finished = true;
            this.lzma.putArraysToCache(this.arrayCache);
            this.lzma = null;
            this.lz = null;
        } catch (IOException e) {
            this.exception = e;
            throw e;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        throw new XZIOException("LZMAOutputStream does not support flushing");
    }

    public int getProps() {
        return this.props;
    }

    public long getUncompressedSize() {
        return this.currentUncompressedSize;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.tempBuf;
        bArr[0] = (byte) i;
        write(bArr, 0, 1);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (i < 0 || i2 < 0 || (i3 = i + i2) < 0 || i3 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        if (this.finished) {
            throw new XZIOException("Stream finished or closed");
        }
        long j = this.expectedUncompressedSize;
        if (j != -1 && j - this.currentUncompressedSize < i2) {
            throw new XZIOException("Expected uncompressed input size (" + this.expectedUncompressedSize + " bytes) was exceeded");
        }
        this.currentUncompressedSize += (long) i2;
        while (i2 > 0) {
            try {
                int iFillWindow = this.lz.fillWindow(bArr, i, i2);
                i += iFillWindow;
                i2 -= iFillWindow;
                this.lzma.encodeForLZMA1();
            } catch (IOException e) {
                this.exception = e;
                throw e;
            }
        }
    }
}
