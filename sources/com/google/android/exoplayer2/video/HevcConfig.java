package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class HevcConfig {
    public final List<byte[]> initializationData;
    public final int nalUnitLengthFieldLength;

    public static HevcConfig parse(ParsableByteArray parsableByteArray) throws ParserException {
        try {
            parsableByteArray.skipBytes(21);
            int unsignedByte = parsableByteArray.readUnsignedByte() & 3;
            int unsignedByte2 = parsableByteArray.readUnsignedByte();
            int position = parsableByteArray.getPosition();
            int i = 0;
            for (int i2 = 0; i2 < unsignedByte2; i2++) {
                parsableByteArray.skipBytes(1);
                int unsignedShort = parsableByteArray.readUnsignedShort();
                for (int i3 = 0; i3 < unsignedShort; i3++) {
                    int unsignedShort2 = parsableByteArray.readUnsignedShort();
                    i += unsignedShort2 + 4;
                    parsableByteArray.skipBytes(unsignedShort2);
                }
            }
            parsableByteArray.setPosition(position);
            byte[] bArr = new byte[i];
            int i4 = 0;
            for (int i5 = 0; i5 < unsignedByte2; i5++) {
                parsableByteArray.skipBytes(1);
                int unsignedShort3 = parsableByteArray.readUnsignedShort();
                for (int i6 = 0; i6 < unsignedShort3; i6++) {
                    int unsignedShort4 = parsableByteArray.readUnsignedShort();
                    System.arraycopy(NalUnitUtil.NAL_START_CODE, 0, bArr, i4, NalUnitUtil.NAL_START_CODE.length);
                    int length = i4 + NalUnitUtil.NAL_START_CODE.length;
                    System.arraycopy(parsableByteArray.data, parsableByteArray.getPosition(), bArr, length, unsignedShort4);
                    i4 = length + unsignedShort4;
                    parsableByteArray.skipBytes(unsignedShort4);
                }
            }
            return new HevcConfig(i == 0 ? null : Collections.singletonList(bArr), unsignedByte + 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParserException("Error parsing HEVC config", e);
        }
    }

    private HevcConfig(List<byte[]> list, int i) {
        this.initializationData = list;
        this.nalUnitLengthFieldLength = i;
    }
}
