package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.UByte;
import okio.Utf8;

/* JADX INFO: loaded from: classes.dex */
final class OpusReader extends StreamReader {
    private static final int DEFAULT_SEEK_PRE_ROLL_SAMPLES = 3840;
    private static final int OPUS_CODE = Util.getIntegerCodeForString("Opus");
    private static final byte[] OPUS_SIGNATURE = {79, 112, 117, 115, 72, 101, 97, 100};
    private static final int SAMPLE_RATE = 48000;
    private boolean headerRead;

    OpusReader() {
    }

    public static boolean verifyBitstreamType(ParsableByteArray parsableByteArray) {
        int iBytesLeft = parsableByteArray.bytesLeft();
        byte[] bArr = OPUS_SIGNATURE;
        if (iBytesLeft < bArr.length) {
            return false;
        }
        byte[] bArr2 = new byte[bArr.length];
        parsableByteArray.readBytes(bArr2, 0, bArr.length);
        return Arrays.equals(bArr2, OPUS_SIGNATURE);
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.StreamReader
    protected void reset(boolean z) {
        super.reset(z);
        if (z) {
            this.headerRead = false;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.StreamReader
    protected long preparePayload(ParsableByteArray parsableByteArray) {
        return convertTimeToGranule(getPacketDurationUs(parsableByteArray.data));
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.StreamReader
    protected boolean readHeaders(ParsableByteArray parsableByteArray, long j, StreamReader.SetupData setupData) {
        if (!this.headerRead) {
            byte[] bArrCopyOf = Arrays.copyOf(parsableByteArray.data, parsableByteArray.limit());
            int i = bArrCopyOf[9] & UByte.MAX_VALUE;
            int i2 = ((bArrCopyOf[11] & UByte.MAX_VALUE) << 8) | (bArrCopyOf[10] & UByte.MAX_VALUE);
            ArrayList arrayList = new ArrayList(3);
            arrayList.add(bArrCopyOf);
            putNativeOrderLong(arrayList, i2);
            putNativeOrderLong(arrayList, DEFAULT_SEEK_PRE_ROLL_SAMPLES);
            setupData.format = Format.createAudioSampleFormat(null, MimeTypes.AUDIO_OPUS, null, -1, -1, i, SAMPLE_RATE, arrayList, null, 0, null);
            this.headerRead = true;
            return true;
        }
        boolean z = parsableByteArray.readInt() == OPUS_CODE;
        parsableByteArray.setPosition(0);
        return z;
    }

    private void putNativeOrderLong(List<byte[]> list, int i) {
        list.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong((((long) i) * C.NANOS_PER_SECOND) / 48000).array());
    }

    private long getPacketDurationUs(byte[] bArr) {
        int i;
        int i2 = bArr[0] & UByte.MAX_VALUE;
        int i3 = i2 & 3;
        int i4 = 2;
        if (i3 == 0) {
            i4 = 1;
        } else if (i3 != 1 && i3 != 2) {
            i4 = bArr[1] & Utf8.REPLACEMENT_BYTE;
        }
        int i5 = i2 >> 3;
        int i6 = i5 & 3;
        if (i5 >= 16) {
            i = 2500 << i6;
        } else if (i5 >= 12) {
            i = 10000 << (i6 & 1);
        } else {
            i = i6 == 3 ? 60000 : 10000 << i6;
        }
        return ((long) i4) * ((long) i);
    }
}
