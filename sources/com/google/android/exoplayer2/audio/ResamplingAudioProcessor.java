package com.google.android.exoplayer2.audio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UByte;

/* JADX INFO: loaded from: classes.dex */
final class ResamplingAudioProcessor implements AudioProcessor {
    private boolean inputEnded;
    private int sampleRateHz = -1;
    private int channelCount = -1;
    private int encoding = 0;
    private ByteBuffer buffer = EMPTY_BUFFER;
    private ByteBuffer outputBuffer = EMPTY_BUFFER;

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int getOutputEncoding() {
        return 2;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean configure(int i, int i2, int i3) throws AudioProcessor.UnhandledFormatException {
        if (i3 != 3 && i3 != 2 && i3 != Integer.MIN_VALUE && i3 != 1073741824) {
            throw new AudioProcessor.UnhandledFormatException(i, i2, i3);
        }
        if (this.sampleRateHz == i && this.channelCount == i2 && this.encoding == i3) {
            return false;
        }
        this.sampleRateHz = i;
        this.channelCount = i2;
        this.encoding = i3;
        return true;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean isActive() {
        int i = this.encoding;
        return (i == 0 || i == 2) ? false : true;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int getOutputChannelCount() {
        return this.channelCount;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int getOutputSampleRateHz() {
        return this.sampleRateHz;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x002b  */
    /* JADX WARN: Code duplicated, block: B:14:0x003a  */
    /* JADX WARN: Code duplicated, block: B:17:0x0043 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:18:0x0045 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:19:0x0047 A[ADDED_TO_REGION, LOOP:0: B:19:0x0047->B:20:0x0049, LOOP_START, PHI: r0
  0x0047: PHI (r0v6 int) = (r0v0 int), (r0v7 int) binds: [B:18:0x0045, B:20:0x0049] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:20:0x0049 A[LOOP:0: B:19:0x0047->B:20:0x0049, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:21:0x0062  */
    /* JADX WARN: Code duplicated, block: B:23:0x0068 A[ADDED_TO_REGION, LOOP:1: B:23:0x0068->B:24:0x006a, LOOP_START, PHI: r0
  0x0068: PHI (r0v4 int) = (r0v0 int), (r0v5 int) binds: [B:17:0x0043, B:24:0x006a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:24:0x006a A[LOOP:1: B:23:0x0068->B:24:0x006a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:25:0x0081 A[ADDED_TO_REGION, LOOP:2: B:25:0x0081->B:26:0x0083, LOOP_START, PHI: r0
  0x0081: PHI (r0v1 int) = (r0v0 int), (r0v2 int) binds: [B:16:0x0041, B:26:0x0083] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:26:0x0083 A[LOOP:2: B:25:0x0081->B:26:0x0083, LOOP_END] */
    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        int i;
        int i2;
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit();
        int i3 = iLimit - iPosition;
        int i4 = this.encoding;
        if (i4 == Integer.MIN_VALUE) {
            i3 /= 3;
        } else {
            if (i4 != 3) {
                if (i4 == 1073741824) {
                    i = i3 / 2;
                } else {
                    throw new IllegalStateException();
                }
            }
            if (this.buffer.capacity() < i) {
                this.buffer = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
            } else {
                this.buffer.clear();
            }
            i2 = this.encoding;
            if (i2 != Integer.MIN_VALUE) {
                while (iPosition < iLimit) {
                    this.buffer.put(byteBuffer.get(iPosition + 1));
                    this.buffer.put(byteBuffer.get(iPosition + 2));
                    iPosition += 3;
                }
            } else if (i2 != 3) {
                while (iPosition < iLimit) {
                    this.buffer.put((byte) 0);
                    this.buffer.put((byte) ((byteBuffer.get(iPosition) & UByte.MAX_VALUE) - 128));
                    iPosition++;
                }
            } else {
                if (i2 == 1073741824) {
                    throw new IllegalStateException();
                }
                while (iPosition < iLimit) {
                    this.buffer.put(byteBuffer.get(iPosition + 2));
                    this.buffer.put(byteBuffer.get(iPosition + 3));
                    iPosition += 4;
                }
            }
            byteBuffer.position(byteBuffer.limit());
            this.buffer.flip();
            this.outputBuffer = this.buffer;
        }
        i = i3 * 2;
        if (this.buffer.capacity() < i) {
            this.buffer = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
        } else {
            this.buffer.clear();
        }
        i2 = this.encoding;
        if (i2 != Integer.MIN_VALUE) {
            while (iPosition < iLimit) {
                this.buffer.put(byteBuffer.get(iPosition + 1));
                this.buffer.put(byteBuffer.get(iPosition + 2));
                iPosition += 3;
            }
        } else if (i2 != 3) {
            while (iPosition < iLimit) {
                this.buffer.put((byte) 0);
                this.buffer.put((byte) ((byteBuffer.get(iPosition) & UByte.MAX_VALUE) - 128));
                iPosition++;
            }
        } else {
            if (i2 == 1073741824) {
                throw new IllegalStateException();
            }
            while (iPosition < iLimit) {
                this.buffer.put(byteBuffer.get(iPosition + 2));
                this.buffer.put(byteBuffer.get(iPosition + 3));
                iPosition += 4;
            }
        }
        byteBuffer.position(byteBuffer.limit());
        this.buffer.flip();
        this.outputBuffer = this.buffer;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void queueEndOfStream() {
        this.inputEnded = true;
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
        this.outputBuffer = EMPTY_BUFFER;
        this.inputEnded = false;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void reset() {
        flush();
        this.sampleRateHz = -1;
        this.channelCount = -1;
        this.encoding = 0;
        this.buffer = EMPTY_BUFFER;
    }
}
