package com.google.android.exoplayer2.util;

import java.nio.ByteBuffer;
import java.util.Arrays;
import kotlin.UByte;

/* JADX INFO: loaded from: classes2.dex */
public final class NalUnitUtil {
    public static final int EXTENDED_SAR = 255;
    private static final int H264_NAL_UNIT_TYPE_SEI = 6;
    private static final int H264_NAL_UNIT_TYPE_SPS = 7;
    private static final int H265_NAL_UNIT_TYPE_PREFIX_SEI = 39;
    private static final String TAG = "NalUnitUtil";
    public static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    public static final float[] ASPECT_RATIO_IDC_VALUES = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    private static final Object scratchEscapePositionsLock = new Object();
    private static int[] scratchEscapePositions = new int[10];

    public static final class SpsData {
        public final int constraintsFlagsAndReservedZero2Bits;
        public final boolean deltaPicOrderAlwaysZeroFlag;
        public final boolean frameMbsOnlyFlag;
        public final int frameNumLength;
        public final int height;
        public final int levelIdc;
        public final int picOrderCntLsbLength;
        public final int picOrderCountType;
        public final float pixelWidthAspectRatio;
        public final int profileIdc;
        public final boolean separateColorPlaneFlag;
        public final int seqParameterSetId;
        public final int width;

        public SpsData(int i, int i2, int i3, int i4, int i5, int i6, float f, boolean z, boolean z2, int i7, int i8, int i9, boolean z3) {
            this.profileIdc = i;
            this.constraintsFlagsAndReservedZero2Bits = i2;
            this.levelIdc = i3;
            this.seqParameterSetId = i4;
            this.width = i5;
            this.height = i6;
            this.pixelWidthAspectRatio = f;
            this.separateColorPlaneFlag = z;
            this.frameMbsOnlyFlag = z2;
            this.frameNumLength = i7;
            this.picOrderCountType = i8;
            this.picOrderCntLsbLength = i9;
            this.deltaPicOrderAlwaysZeroFlag = z3;
        }
    }

    public static final class PpsData {
        public final boolean bottomFieldPicOrderInFramePresentFlag;
        public final int picParameterSetId;
        public final int seqParameterSetId;

        public PpsData(int i, int i2, boolean z) {
            this.picParameterSetId = i;
            this.seqParameterSetId = i2;
            this.bottomFieldPicOrderInFramePresentFlag = z;
        }
    }

    public static int unescapeStream(byte[] bArr, int i) {
        int i2;
        synchronized (scratchEscapePositionsLock) {
            int iFindNextUnescapeIndex = 0;
            int i3 = 0;
            while (iFindNextUnescapeIndex < i) {
                try {
                    iFindNextUnescapeIndex = findNextUnescapeIndex(bArr, iFindNextUnescapeIndex, i);
                    if (iFindNextUnescapeIndex < i) {
                        if (scratchEscapePositions.length <= i3) {
                            scratchEscapePositions = Arrays.copyOf(scratchEscapePositions, scratchEscapePositions.length * 2);
                        }
                        scratchEscapePositions[i3] = iFindNextUnescapeIndex;
                        iFindNextUnescapeIndex += 3;
                        i3++;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            i2 = i - i3;
            int i4 = 0;
            int i5 = 0;
            for (int i6 = 0; i6 < i3; i6++) {
                int i7 = scratchEscapePositions[i6] - i5;
                System.arraycopy(bArr, i5, bArr, i4, i7);
                int i8 = i4 + i7;
                int i9 = i8 + 1;
                bArr[i8] = 0;
                i4 = i9 + 1;
                bArr[i9] = 0;
                i5 += i7 + 3;
            }
            System.arraycopy(bArr, i5, bArr, i4, i2 - i4);
        }
        return i2;
    }

    public static void discardToSps(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i + 1;
            if (i3 < iPosition) {
                int i4 = byteBuffer.get(i) & UByte.MAX_VALUE;
                if (i2 == 3) {
                    if (i4 == 1 && (byteBuffer.get(i3) & 31) == 7) {
                        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
                        byteBufferDuplicate.position(i - 3);
                        byteBufferDuplicate.limit(iPosition);
                        byteBuffer.position(0);
                        byteBuffer.put(byteBufferDuplicate);
                        return;
                    }
                } else if (i4 == 0) {
                    i2++;
                }
                if (i4 != 0) {
                    i2 = 0;
                }
                i = i3;
            } else {
                byteBuffer.clear();
                return;
            }
        }
    }

    public static boolean isNalUnitSei(String str, byte b) {
        if (MimeTypes.VIDEO_H264.equals(str) && (b & 31) == 6) {
            return true;
        }
        return MimeTypes.VIDEO_H265.equals(str) && ((b & 126) >> 1) == 39;
    }

    public static int getNalUnitType(byte[] bArr, int i) {
        return bArr[i + 3] & 31;
    }

    public static int getH265NalUnitType(byte[] bArr, int i) {
        return (bArr[i + 3] & 126) >> 1;
    }

    /* JADX WARN: Code duplicated, block: B:56:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:59:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:61:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:62:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:64:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:65:0x0102  */
    /* JADX WARN: Code duplicated, block: B:67:0x0107  */
    /* JADX WARN: Code duplicated, block: B:84:0x0162 A[PHI: r1
  0x0162: PHI (r1v8 float) = (r1v7 float), (r1v7 float), (r1v7 float), (r1v7 float), (r1v7 float), (r1v10 float) binds: [B:71:0x011f, B:73:0x0125, B:83:0x014c, B:77:0x013b, B:78:0x013d, B:79:0x013f] A[DONT_GENERATE, DONT_INLINE]] */
    public static SpsData parseSpsNalUnit(byte[] bArr, int i, int i2) {
        int unsignedExpGolombCodedInt;
        boolean bit;
        int unsignedExpGolombCodedInt2;
        boolean z;
        boolean bit2;
        int i3;
        int i4;
        float f;
        float f2;
        int i5;
        int i6;
        int i7;
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i, i2);
        parsableNalUnitBitArray.skipBits(8);
        int bits = parsableNalUnitBitArray.readBits(8);
        int bits2 = parsableNalUnitBitArray.readBits(8);
        int bits3 = parsableNalUnitBitArray.readBits(8);
        int unsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int i8 = 1;
        if (bits == 100 || bits == 110 || bits == 122 || bits == 244 || bits == 44 || bits == 83 || bits == 86 || bits == 118 || bits == 128 || bits == 138) {
            unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            bit = unsignedExpGolombCodedInt == 3 ? parsableNalUnitBitArray.readBit() : false;
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.skipBit();
            if (parsableNalUnitBitArray.readBit()) {
                int i9 = unsignedExpGolombCodedInt != 3 ? 8 : 12;
                int i10 = 0;
                while (i10 < i9) {
                    if (parsableNalUnitBitArray.readBit()) {
                        skipScalingList(parsableNalUnitBitArray, i10 < 6 ? 16 : 64);
                    }
                    i10++;
                }
            }
        } else {
            unsignedExpGolombCodedInt = 1;
            bit = false;
        }
        int unsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 4;
        int unsignedExpGolombCodedInt5 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (unsignedExpGolombCodedInt5 == 0) {
            unsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 4;
        } else {
            if (unsignedExpGolombCodedInt5 == 1) {
                boolean bit3 = parsableNalUnitBitArray.readBit();
                parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                long unsignedExpGolombCodedInt6 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                for (int i11 = 0; i11 < unsignedExpGolombCodedInt6; i11++) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                }
                z = bit3;
                unsignedExpGolombCodedInt2 = 0;
            } else {
                unsignedExpGolombCodedInt2 = 0;
            }
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.skipBit();
            int unsignedExpGolombCodedInt7 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
            int unsignedExpGolombCodedInt8 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
            bit2 = parsableNalUnitBitArray.readBit();
            int i12 = (2 - (bit2 ? 1 : 0)) * unsignedExpGolombCodedInt8;
            if (!bit2) {
                parsableNalUnitBitArray.skipBit();
            }
            parsableNalUnitBitArray.skipBit();
            i3 = unsignedExpGolombCodedInt7 * 16;
            i4 = i12 * 16;
            if (parsableNalUnitBitArray.readBit()) {
                int unsignedExpGolombCodedInt9 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int unsignedExpGolombCodedInt10 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int unsignedExpGolombCodedInt11 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int unsignedExpGolombCodedInt12 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                if (unsignedExpGolombCodedInt == 0) {
                    i7 = 2 - (bit2 ? 1 : 0);
                } else {
                    if (unsignedExpGolombCodedInt == 3) {
                        i6 = 1;
                        i5 = 1;
                    } else {
                        i5 = 2;
                        i6 = 1;
                    }
                    if (unsignedExpGolombCodedInt == i6) {
                        i6 = 2;
                    }
                    i7 = (2 - (bit2 ? 1 : 0)) * i6;
                    i8 = i5;
                }
                i3 -= (unsignedExpGolombCodedInt9 + unsignedExpGolombCodedInt10) * i8;
                i4 -= (unsignedExpGolombCodedInt11 + unsignedExpGolombCodedInt12) * i7;
            }
            int i13 = i4;
            int i14 = i3;
            f = 1.0f;
            if (parsableNalUnitBitArray.readBit() || !parsableNalUnitBitArray.readBit()) {
                f2 = f;
            } else {
                int bits4 = parsableNalUnitBitArray.readBits(8);
                if (bits4 == 255) {
                    int bits5 = parsableNalUnitBitArray.readBits(16);
                    int bits6 = parsableNalUnitBitArray.readBits(16);
                    if (bits5 != 0 && bits6 != 0) {
                        f = bits5 / bits6;
                    }
                } else {
                    float[] fArr = ASPECT_RATIO_IDC_VALUES;
                    if (bits4 < fArr.length) {
                        f2 = fArr[bits4];
                    } else {
                        Log.w(TAG, "Unexpected aspect_ratio_idc value: " + bits4);
                    }
                }
                f2 = f;
            }
            return new SpsData(bits, bits2, bits3, unsignedExpGolombCodedInt3, i14, i13, f2, bit, bit2, unsignedExpGolombCodedInt4, unsignedExpGolombCodedInt5, unsignedExpGolombCodedInt2, z);
        }
        z = false;
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.skipBit();
        int unsignedExpGolombCodedInt13 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        int unsignedExpGolombCodedInt14 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        bit2 = parsableNalUnitBitArray.readBit();
        int i15 = (2 - (bit2 ? 1 : 0)) * unsignedExpGolombCodedInt14;
        if (!bit2) {
            parsableNalUnitBitArray.skipBit();
        }
        parsableNalUnitBitArray.skipBit();
        i3 = unsignedExpGolombCodedInt13 * 16;
        i4 = i15 * 16;
        if (parsableNalUnitBitArray.readBit()) {
            int unsignedExpGolombCodedInt15 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt16 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt17 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt18 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            if (unsignedExpGolombCodedInt == 0) {
                i7 = 2 - (bit2 ? 1 : 0);
            } else {
                if (unsignedExpGolombCodedInt == 3) {
                    i6 = 1;
                    i5 = 1;
                } else {
                    i5 = 2;
                    i6 = 1;
                }
                if (unsignedExpGolombCodedInt == i6) {
                    i6 = 2;
                }
                i7 = (2 - (bit2 ? 1 : 0)) * i6;
                i8 = i5;
            }
            i3 -= (unsignedExpGolombCodedInt15 + unsignedExpGolombCodedInt16) * i8;
            i4 -= (unsignedExpGolombCodedInt17 + unsignedExpGolombCodedInt18) * i7;
        }
        int i16 = i4;
        int i17 = i3;
        f = 1.0f;
        if (parsableNalUnitBitArray.readBit()) {
            f2 = f;
        } else {
            f2 = f;
        }
        return new SpsData(bits, bits2, bits3, unsignedExpGolombCodedInt3, i17, i16, f2, bit, bit2, unsignedExpGolombCodedInt4, unsignedExpGolombCodedInt5, unsignedExpGolombCodedInt2, z);
    }

    public static PpsData parsePpsNalUnit(byte[] bArr, int i, int i2) {
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i, i2);
        parsableNalUnitBitArray.skipBits(8);
        int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int unsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.skipBit();
        return new PpsData(unsignedExpGolombCodedInt, unsignedExpGolombCodedInt2, parsableNalUnitBitArray.readBit());
    }

    /* JADX WARN: Code restructure failed: missing block: B:69:0x0097, code lost:
    
        r8 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int findNalUnit(byte[] r7, int r8, int r9, boolean[] r10) {
        /*
            int r0 = r9 - r8
            r1 = 0
            r2 = 1
            if (r0 < 0) goto L8
            r3 = r2
            goto L9
        L8:
            r3 = r1
        L9:
            com.google.android.exoplayer2.util.Assertions.checkState(r3)
            if (r0 != 0) goto Lf
            return r9
        Lf:
            r3 = 2
            if (r10 == 0) goto L40
            boolean r4 = r10[r1]
            if (r4 == 0) goto L1c
            clearPrefixFlags(r10)
            int r8 = r8 + (-3)
            return r8
        L1c:
            if (r0 <= r2) goto L2b
            boolean r4 = r10[r2]
            if (r4 == 0) goto L2b
            r4 = r7[r8]
            if (r4 != r2) goto L2b
            clearPrefixFlags(r10)
            int r8 = r8 - r3
            return r8
        L2b:
            if (r0 <= r3) goto L40
            boolean r4 = r10[r3]
            if (r4 == 0) goto L40
            r4 = r7[r8]
            if (r4 != 0) goto L40
            int r4 = r8 + 1
            r4 = r7[r4]
            if (r4 != r2) goto L40
            clearPrefixFlags(r10)
            int r8 = r8 - r2
            return r8
        L40:
            int r4 = r9 + (-1)
            int r8 = r8 + r3
        L43:
            if (r8 >= r4) goto L67
            r5 = r7[r8]
            r5 = r5 & 254(0xfe, float:3.56E-43)
            if (r5 == 0) goto L4c
            goto L64
        L4c:
            int r5 = r8 + (-2)
            r6 = r7[r5]
            if (r6 != 0) goto L62
            int r6 = r8 + (-1)
            r6 = r7[r6]
            if (r6 != 0) goto L62
            r6 = r7[r8]
            if (r6 != r2) goto L62
            if (r10 == 0) goto L61
            clearPrefixFlags(r10)
        L61:
            return r5
        L62:
            int r8 = r8 + (-2)
        L64:
            int r8 = r8 + 3
            goto L43
        L67:
            if (r10 == 0) goto Lbb
            if (r0 <= r3) goto L7e
            int r8 = r9 + (-3)
            r8 = r7[r8]
            if (r8 != 0) goto L7c
            int r8 = r9 + (-2)
            r8 = r7[r8]
            if (r8 != 0) goto L7c
            r8 = r7[r4]
            if (r8 != r2) goto L7c
            goto L97
        L7c:
            r8 = r1
            goto L98
        L7e:
            if (r0 != r3) goto L8f
            boolean r8 = r10[r3]
            if (r8 == 0) goto L7c
            int r8 = r9 + (-2)
            r8 = r7[r8]
            if (r8 != 0) goto L7c
            r8 = r7[r4]
            if (r8 != r2) goto L7c
            goto L97
        L8f:
            boolean r8 = r10[r2]
            if (r8 == 0) goto L7c
            r8 = r7[r4]
            if (r8 != r2) goto L7c
        L97:
            r8 = r2
        L98:
            r10[r1] = r8
            if (r0 <= r2) goto La7
            int r8 = r9 + (-2)
            r8 = r7[r8]
            if (r8 != 0) goto Lb1
            r8 = r7[r4]
            if (r8 != 0) goto Lb1
            goto Laf
        La7:
            boolean r8 = r10[r3]
            if (r8 == 0) goto Lb1
            r8 = r7[r4]
            if (r8 != 0) goto Lb1
        Laf:
            r8 = r2
            goto Lb2
        Lb1:
            r8 = r1
        Lb2:
            r10[r2] = r8
            r7 = r7[r4]
            if (r7 != 0) goto Lb9
            r1 = r2
        Lb9:
            r10[r3] = r1
        Lbb:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.NalUnitUtil.findNalUnit(byte[], int, int, boolean[]):int");
    }

    public static void clearPrefixFlags(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    private static int findNextUnescapeIndex(byte[] bArr, int i, int i2) {
        while (i < i2 - 2) {
            if (bArr[i] == 0 && bArr[i + 1] == 0 && bArr[i + 2] == 3) {
                return i;
            }
            i++;
        }
        return i2;
    }

    private static void skipScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray, int i) {
        int signedExpGolombCodedInt = 8;
        int i2 = 8;
        for (int i3 = 0; i3 < i; i3++) {
            if (signedExpGolombCodedInt != 0) {
                signedExpGolombCodedInt = ((parsableNalUnitBitArray.readSignedExpGolombCodedInt() + i2) + 256) % 256;
            }
            if (signedExpGolombCodedInt != 0) {
                i2 = signedExpGolombCodedInt;
            }
        }
    }

    private NalUnitUtil() {
    }
}
