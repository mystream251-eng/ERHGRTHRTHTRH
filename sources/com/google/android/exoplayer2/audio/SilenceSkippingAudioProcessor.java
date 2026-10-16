package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public final class SilenceSkippingAudioProcessor implements AudioProcessor {
    private static final long MINIMUM_SILENCE_DURATION_US = 150000;
    private static final long PADDING_SILENCE_US = 20000;
    private static final short SILENCE_THRESHOLD_LEVEL = 1024;
    private static final byte SILENCE_THRESHOLD_LEVEL_MSB = 4;
    private static final int STATE_MAYBE_SILENT = 1;
    private static final int STATE_NOISY = 0;
    private static final int STATE_SILENT = 2;
    private int bytesPerFrame;
    private boolean enabled;
    private boolean hasOutputNoise;
    private boolean inputEnded;
    private int maybeSilenceBufferSize;
    private int paddingSize;
    private long skippedFrames;
    private int state;
    private ByteBuffer buffer = EMPTY_BUFFER;
    private ByteBuffer outputBuffer = EMPTY_BUFFER;
    private int channelCount = -1;
    private int sampleRateHz = -1;
    private byte[] maybeSilenceBuffer = Util.EMPTY_BYTE_ARRAY;
    private byte[] paddingBuffer = Util.EMPTY_BYTE_ARRAY;

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int getOutputEncoding() {
        return 2;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
        flush();
    }

    public long getSkippedFrames() {
        return this.skippedFrames;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean configure(int i, int i2, int i3) throws AudioProcessor.UnhandledFormatException {
        if (i3 != 2) {
            throw new AudioProcessor.UnhandledFormatException(i, i2, i3);
        }
        if (this.sampleRateHz == i && this.channelCount == i2) {
            return false;
        }
        this.sampleRateHz = i;
        this.channelCount = i2;
        this.bytesPerFrame = i2 * 2;
        return true;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean isActive() {
        return this.sampleRateHz != -1 && this.enabled;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int getOutputChannelCount() {
        return this.channelCount;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int getOutputSampleRateHz() {
        return this.sampleRateHz;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        while (byteBuffer.hasRemaining() && !this.outputBuffer.hasRemaining()) {
            int i = this.state;
            if (i == 0) {
                processNoisy(byteBuffer);
            } else if (i == 1) {
                processMaybeSilence(byteBuffer);
            } else if (i == 2) {
                processSilence(byteBuffer);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void queueEndOfStream() {
        this.inputEnded = true;
        int i = this.maybeSilenceBufferSize;
        if (i > 0) {
            output(this.maybeSilenceBuffer, i);
        }
        if (this.hasOutputNoise) {
            return;
        }
        this.skippedFrames += (long) (this.paddingSize / this.bytesPerFrame);
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public ByteBuffer getOutput() {
        ByteBuffer byteBuffer = this.outputBuffer;
        this.outputBuffer = EMPTY_BUFFER;
        return byteBuffer;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean isEnded() {
        return this.inputEnded && this.outputBuffer == EMPTY_BUFFER;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void flush() {
        if (isActive()) {
            int iDurationUsToFrames = durationUsToFrames(MINIMUM_SILENCE_DURATION_US) * this.bytesPerFrame;
            if (this.maybeSilenceBuffer.length != iDurationUsToFrames) {
                this.maybeSilenceBuffer = new byte[iDurationUsToFrames];
            }
            int iDurationUsToFrames2 = durationUsToFrames(PADDING_SILENCE_US) * this.bytesPerFrame;
            this.paddingSize = iDurationUsToFrames2;
            if (this.paddingBuffer.length != iDurationUsToFrames2) {
                this.paddingBuffer = new byte[iDurationUsToFrames2];
            }
        }
        this.state = 0;
        this.outputBuffer = EMPTY_BUFFER;
        this.inputEnded = false;
        this.skippedFrames = 0L;
        this.maybeSilenceBufferSize = 0;
        this.hasOutputNoise = false;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void reset() {
        this.enabled = false;
        flush();
        this.buffer = EMPTY_BUFFER;
        this.channelCount = -1;
        this.sampleRateHz = -1;
        this.paddingSize = 0;
        this.maybeSilenceBuffer = Util.EMPTY_BYTE_ARRAY;
        this.paddingBuffer = Util.EMPTY_BYTE_ARRAY;
    }

    private void processNoisy(ByteBuffer byteBuffer) {
        int iLimit = byteBuffer.limit();
        byteBuffer.limit(Math.min(iLimit, byteBuffer.position() + this.maybeSilenceBuffer.length));
        int iFindNoiseLimit = findNoiseLimit(byteBuffer);
        if (iFindNoiseLimit == byteBuffer.position()) {
            this.state = 1;
        } else {
            byteBuffer.limit(iFindNoiseLimit);
            output(byteBuffer);
        }
        byteBuffer.limit(iLimit);
    }

    private void processMaybeSilence(ByteBuffer byteBuffer) {
        int iLimit = byteBuffer.limit();
        int iFindNoisePosition = findNoisePosition(byteBuffer);
        int iPosition = iFindNoisePosition - byteBuffer.position();
        byte[] bArr = this.maybeSilenceBuffer;
        int length = bArr.length;
        int i = this.maybeSilenceBufferSize;
        int i2 = length - i;
        if (iFindNoisePosition < iLimit && iPosition < i2) {
            output(bArr, i);
            this.maybeSilenceBufferSize = 0;
            this.state = 0;
            return;
        }
        int iMin = Math.min(iPosition, i2);
        byteBuffer.limit(byteBuffer.position() + iMin);
        byteBuffer.get(this.maybeSilenceBuffer, this.maybeSilenceBufferSize, iMin);
        int i3 = this.maybeSilenceBufferSize + iMin;
        this.maybeSilenceBufferSize = i3;
        byte[] bArr2 = this.maybeSilenceBuffer;
        if (i3 == bArr2.length) {
            if (this.hasOutputNoise) {
                output(bArr2, this.paddingSize);
                this.skippedFrames += (long) ((this.maybeSilenceBufferSize - (this.paddingSize * 2)) / this.bytesPerFrame);
            } else {
                this.skippedFrames += (long) ((i3 - this.paddingSize) / this.bytesPerFrame);
            }
            updatePaddingBuffer(byteBuffer, this.maybeSilenceBuffer, this.maybeSilenceBufferSize);
            this.maybeSilenceBufferSize = 0;
            this.state = 2;
        }
        byteBuffer.limit(iLimit);
    }

    private void processSilence(ByteBuffer byteBuffer) {
        int iLimit = byteBuffer.limit();
        int iFindNoisePosition = findNoisePosition(byteBuffer);
        byteBuffer.limit(iFindNoisePosition);
        this.skippedFrames += (long) (byteBuffer.remaining() / this.bytesPerFrame);
        updatePaddingBuffer(byteBuffer, this.paddingBuffer, this.paddingSize);
        if (iFindNoisePosition < iLimit) {
            output(this.paddingBuffer, this.paddingSize);
            this.state = 0;
            byteBuffer.limit(iLimit);
        }
    }

    private void output(byte[] bArr, int i) {
        prepareForOutput(i);
        this.buffer.put(bArr, 0, i);
        this.buffer.flip();
        this.outputBuffer = this.buffer;
    }

    private void output(ByteBuffer byteBuffer) {
        prepareForOutput(byteBuffer.remaining());
        this.buffer.put(byteBuffer);
        this.buffer.flip();
        this.outputBuffer = this.buffer;
    }

    private void prepareForOutput(int i) {
        if (this.buffer.capacity() < i) {
            this.buffer = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
        } else {
            this.buffer.clear();
        }
        if (i > 0) {
            this.hasOutputNoise = true;
        }
    }

    private void updatePaddingBuffer(ByteBuffer byteBuffer, byte[] bArr, int i) {
        int iMin = Math.min(byteBuffer.remaining(), this.paddingSize);
        int i2 = this.paddingSize - iMin;
        System.arraycopy(bArr, i - i2, this.paddingBuffer, 0, i2);
        byteBuffer.position(byteBuffer.limit() - iMin);
        byteBuffer.get(this.paddingBuffer, i2, iMin);
    }

    private int durationUsToFrames(long j) {
        return (int) ((j * ((long) this.sampleRateHz)) / 1000000);
    }

    private int findNoisePosition(ByteBuffer byteBuffer) {
        for (int iPosition = byteBuffer.position() + 1; iPosition < byteBuffer.limit(); iPosition += 2) {
            if (Math.abs((int) byteBuffer.get(iPosition)) > 4) {
                int i = this.bytesPerFrame;
                return i * (iPosition / i);
            }
        }
        return byteBuffer.limit();
    }

    private int findNoiseLimit(ByteBuffer byteBuffer) {
        for (int iLimit = byteBuffer.limit() - 1; iLimit >= byteBuffer.position(); iLimit -= 2) {
            if (Math.abs((int) byteBuffer.get(iLimit)) > 4) {
                int i = this.bytesPerFrame;
                return ((iLimit / i) * i) + i;
            }
        }
        return byteBuffer.position();
    }
}
