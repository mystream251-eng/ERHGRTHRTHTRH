package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.ParsableNalUnitBitArray;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class H264Reader implements ElementaryStreamReader {
    private static final int NAL_UNIT_TYPE_PPS = 8;
    private static final int NAL_UNIT_TYPE_SEI = 6;
    private static final int NAL_UNIT_TYPE_SPS = 7;
    private final boolean allowNonIdrKeyframes;
    private final boolean detectAccessUnits;
    private String formatId;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs;
    private boolean randomAccessIndicator;
    private SampleReader sampleReader;
    private final SeiReader seiReader;
    private long totalBytesWritten;
    private final boolean[] prefixFlags = new boolean[3];
    private final NalUnitTargetBuffer sps = new NalUnitTargetBuffer(7, 128);
    private final NalUnitTargetBuffer pps = new NalUnitTargetBuffer(8, 128);
    private final NalUnitTargetBuffer sei = new NalUnitTargetBuffer(6, 128);
    private final ParsableByteArray seiWrapper = new ParsableByteArray();

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }

    public H264Reader(SeiReader seiReader, boolean z, boolean z2) {
        this.seiReader = seiReader;
        this.allowNonIdrKeyframes = z;
        this.detectAccessUnits = z2;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.sps.reset();
        this.pps.reset();
        this.sei.reset();
        this.sampleReader.reset();
        this.totalBytesWritten = 0L;
        this.randomAccessIndicator = false;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 2);
        this.sampleReader = new SampleReader(this.output, this.allowNonIdrKeyframes, this.detectAccessUnits);
        this.seiReader.createTracks(extractorOutput, trackIdGenerator);
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, int i) {
        this.pesTimeUs = j;
        this.randomAccessIndicator |= (i & 2) != 0;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition();
        int iLimit = parsableByteArray.limit();
        byte[] bArr = parsableByteArray.data;
        this.totalBytesWritten += (long) parsableByteArray.bytesLeft();
        this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
        while (true) {
            int iFindNalUnit = NalUnitUtil.findNalUnit(bArr, position, iLimit, this.prefixFlags);
            if (iFindNalUnit == iLimit) {
                nalUnitData(bArr, position, iLimit);
                return;
            }
            int nalUnitType = NalUnitUtil.getNalUnitType(bArr, iFindNalUnit);
            int i = iFindNalUnit - position;
            if (i > 0) {
                nalUnitData(bArr, position, iFindNalUnit);
            }
            int i2 = iLimit - iFindNalUnit;
            long j = this.totalBytesWritten - ((long) i2);
            endNalUnit(j, i2, i < 0 ? -i : 0, this.pesTimeUs);
            startNalUnit(j, nalUnitType, this.pesTimeUs);
            position = iFindNalUnit + 3;
        }
    }

    private void startNalUnit(long j, int i, long j2) {
        if (!this.hasOutputFormat || this.sampleReader.needsSpsPps()) {
            this.sps.startNalUnit(i);
            this.pps.startNalUnit(i);
        }
        this.sei.startNalUnit(i);
        this.sampleReader.startNalUnit(j, i, j2);
    }

    private void nalUnitData(byte[] bArr, int i, int i2) {
        if (!this.hasOutputFormat || this.sampleReader.needsSpsPps()) {
            this.sps.appendToNalUnit(bArr, i, i2);
            this.pps.appendToNalUnit(bArr, i, i2);
        }
        this.sei.appendToNalUnit(bArr, i, i2);
        this.sampleReader.appendToNalUnit(bArr, i, i2);
    }

    private void endNalUnit(long j, int i, int i2, long j2) {
        if (!this.hasOutputFormat || this.sampleReader.needsSpsPps()) {
            this.sps.endNalUnit(i2);
            this.pps.endNalUnit(i2);
            if (!this.hasOutputFormat) {
                if (this.sps.isCompleted() && this.pps.isCompleted()) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(Arrays.copyOf(this.sps.nalData, this.sps.nalLength));
                    arrayList.add(Arrays.copyOf(this.pps.nalData, this.pps.nalLength));
                    NalUnitUtil.SpsData spsNalUnit = NalUnitUtil.parseSpsNalUnit(this.sps.nalData, 3, this.sps.nalLength);
                    NalUnitUtil.PpsData ppsNalUnit = NalUnitUtil.parsePpsNalUnit(this.pps.nalData, 3, this.pps.nalLength);
                    this.output.format(Format.createVideoSampleFormat(this.formatId, MimeTypes.VIDEO_H264, CodecSpecificDataUtil.buildAvcCodecString(spsNalUnit.profileIdc, spsNalUnit.constraintsFlagsAndReservedZero2Bits, spsNalUnit.levelIdc), -1, -1, spsNalUnit.width, spsNalUnit.height, -1.0f, arrayList, -1, spsNalUnit.pixelWidthAspectRatio, null));
                    this.hasOutputFormat = true;
                    this.sampleReader.putSps(spsNalUnit);
                    this.sampleReader.putPps(ppsNalUnit);
                    this.sps.reset();
                    this.pps.reset();
                }
            } else if (this.sps.isCompleted()) {
                this.sampleReader.putSps(NalUnitUtil.parseSpsNalUnit(this.sps.nalData, 3, this.sps.nalLength));
                this.sps.reset();
            } else if (this.pps.isCompleted()) {
                this.sampleReader.putPps(NalUnitUtil.parsePpsNalUnit(this.pps.nalData, 3, this.pps.nalLength));
                this.pps.reset();
            }
        }
        if (this.sei.endNalUnit(i2)) {
            this.seiWrapper.reset(this.sei.nalData, NalUnitUtil.unescapeStream(this.sei.nalData, this.sei.nalLength));
            this.seiWrapper.setPosition(4);
            this.seiReader.consume(j2, this.seiWrapper);
        }
        if (this.sampleReader.endNalUnit(j, i, this.hasOutputFormat, this.randomAccessIndicator)) {
            this.randomAccessIndicator = false;
        }
    }

    private static final class SampleReader {
        private static final int DEFAULT_BUFFER_SIZE = 128;
        private static final int NAL_UNIT_TYPE_AUD = 9;
        private static final int NAL_UNIT_TYPE_IDR = 5;
        private static final int NAL_UNIT_TYPE_NON_IDR = 1;
        private static final int NAL_UNIT_TYPE_PARTITION_A = 2;
        private final boolean allowNonIdrKeyframes;
        private int bufferLength;
        private final boolean detectAccessUnits;
        private boolean isFilling;
        private long nalUnitStartPosition;
        private long nalUnitTimeUs;
        private int nalUnitType;
        private final TrackOutput output;
        private SliceHeaderData previousSliceHeader;
        private boolean readingSample;
        private boolean sampleIsKeyframe;
        private long samplePosition;
        private long sampleTimeUs;
        private SliceHeaderData sliceHeader;
        private final SparseArray<NalUnitUtil.SpsData> sps = new SparseArray<>();
        private final SparseArray<NalUnitUtil.PpsData> pps = new SparseArray<>();
        private byte[] buffer = new byte[128];
        private final ParsableNalUnitBitArray bitArray = new ParsableNalUnitBitArray(this.buffer, 0, 0);

        public SampleReader(TrackOutput trackOutput, boolean z, boolean z2) {
            this.output = trackOutput;
            this.allowNonIdrKeyframes = z;
            this.detectAccessUnits = z2;
            this.previousSliceHeader = new SliceHeaderData();
            this.sliceHeader = new SliceHeaderData();
            reset();
        }

        public boolean needsSpsPps() {
            return this.detectAccessUnits;
        }

        public void putSps(NalUnitUtil.SpsData spsData) {
            this.sps.append(spsData.seqParameterSetId, spsData);
        }

        public void putPps(NalUnitUtil.PpsData ppsData) {
            this.pps.append(ppsData.picParameterSetId, ppsData);
        }

        public void reset() {
            this.isFilling = false;
            this.readingSample = false;
            this.sliceHeader.clear();
        }

        public void startNalUnit(long j, int i, long j2) {
            this.nalUnitType = i;
            this.nalUnitTimeUs = j2;
            this.nalUnitStartPosition = j;
            if (!this.allowNonIdrKeyframes || i != 1) {
                if (!this.detectAccessUnits) {
                    return;
                }
                int i2 = this.nalUnitType;
                if (i2 != 5 && i2 != 1 && i2 != 2) {
                    return;
                }
            }
            SliceHeaderData sliceHeaderData = this.previousSliceHeader;
            this.previousSliceHeader = this.sliceHeader;
            this.sliceHeader = sliceHeaderData;
            sliceHeaderData.clear();
            this.bufferLength = 0;
            this.isFilling = true;
        }

        /* JADX WARN: Code duplicated, block: B:53:0x00ff  */
        /* JADX WARN: Code duplicated, block: B:54:0x0102  */
        /* JADX WARN: Code duplicated, block: B:56:0x0106  */
        /* JADX WARN: Code duplicated, block: B:58:0x010e A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:59:0x010f  */
        /* JADX WARN: Code duplicated, block: B:60:0x0118  */
        /* JADX WARN: Code duplicated, block: B:63:0x011e  */
        /* JADX WARN: Code duplicated, block: B:65:0x0128 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:66:0x0129  */
        /* JADX WARN: Code duplicated, block: B:74:0x0152  */
        public void appendToNalUnit(byte[] bArr, int i, int i2) {
            boolean z;
            boolean z2;
            boolean bit;
            boolean z3;
            int unsignedExpGolombCodedInt;
            int i3;
            int signedExpGolombCodedInt;
            int i4;
            int signedExpGolombCodedInt2;
            int bits;
            if (this.isFilling) {
                int i5 = i2 - i;
                byte[] bArr2 = this.buffer;
                int length = bArr2.length;
                int i6 = this.bufferLength;
                if (length < i6 + i5) {
                    this.buffer = Arrays.copyOf(bArr2, (i6 + i5) * 2);
                }
                System.arraycopy(bArr, i, this.buffer, this.bufferLength, i5);
                int i7 = this.bufferLength + i5;
                this.bufferLength = i7;
                this.bitArray.reset(this.buffer, 0, i7);
                if (this.bitArray.canReadBits(8)) {
                    this.bitArray.skipBit();
                    int bits2 = this.bitArray.readBits(2);
                    this.bitArray.skipBits(5);
                    if (this.bitArray.canReadExpGolombCodedNum()) {
                        this.bitArray.readUnsignedExpGolombCodedInt();
                        if (this.bitArray.canReadExpGolombCodedNum()) {
                            int unsignedExpGolombCodedInt2 = this.bitArray.readUnsignedExpGolombCodedInt();
                            if (!this.detectAccessUnits) {
                                this.isFilling = false;
                                this.sliceHeader.setSliceType(unsignedExpGolombCodedInt2);
                                return;
                            }
                            if (this.bitArray.canReadExpGolombCodedNum()) {
                                int unsignedExpGolombCodedInt3 = this.bitArray.readUnsignedExpGolombCodedInt();
                                if (this.pps.indexOfKey(unsignedExpGolombCodedInt3) < 0) {
                                    this.isFilling = false;
                                    return;
                                }
                                NalUnitUtil.PpsData ppsData = this.pps.get(unsignedExpGolombCodedInt3);
                                NalUnitUtil.SpsData spsData = this.sps.get(ppsData.seqParameterSetId);
                                if (spsData.separateColorPlaneFlag) {
                                    if (!this.bitArray.canReadBits(2)) {
                                        return;
                                    } else {
                                        this.bitArray.skipBits(2);
                                    }
                                }
                                if (this.bitArray.canReadBits(spsData.frameNumLength)) {
                                    int bits3 = this.bitArray.readBits(spsData.frameNumLength);
                                    if (!spsData.frameMbsOnlyFlag) {
                                        if (this.bitArray.canReadBits(1)) {
                                            boolean bit2 = this.bitArray.readBit();
                                            if (!bit2) {
                                                z = bit2;
                                                z2 = false;
                                            } else {
                                                if (!this.bitArray.canReadBits(1)) {
                                                    return;
                                                }
                                                z = bit2;
                                                z2 = true;
                                                bit = this.bitArray.readBit();
                                            }
                                            if (this.nalUnitType == 5) {
                                                z3 = true;
                                            } else {
                                                z3 = false;
                                            }
                                            if (z3) {
                                                unsignedExpGolombCodedInt = 0;
                                            } else if (!this.bitArray.canReadExpGolombCodedNum()) {
                                                return;
                                            } else {
                                                unsignedExpGolombCodedInt = this.bitArray.readUnsignedExpGolombCodedInt();
                                            }
                                            if (spsData.picOrderCountType == 0) {
                                                if (this.bitArray.canReadBits(spsData.picOrderCntLsbLength)) {
                                                    bits = this.bitArray.readBits(spsData.picOrderCntLsbLength);
                                                    if (ppsData.bottomFieldPicOrderInFramePresentFlag || z) {
                                                        i3 = bits;
                                                        signedExpGolombCodedInt = 0;
                                                    } else {
                                                        if (!this.bitArray.canReadExpGolombCodedNum()) {
                                                            return;
                                                        }
                                                        signedExpGolombCodedInt = this.bitArray.readSignedExpGolombCodedInt();
                                                        i3 = bits;
                                                        i4 = 0;
                                                    }
                                                    signedExpGolombCodedInt2 = i4;
                                                    this.sliceHeader.setAll(spsData, bits2, unsignedExpGolombCodedInt2, bits3, unsignedExpGolombCodedInt3, z, z2, bit, z3, unsignedExpGolombCodedInt, i3, signedExpGolombCodedInt, i4, signedExpGolombCodedInt2);
                                                    this.isFilling = false;
                                                }
                                                return;
                                            }
                                            if (spsData.picOrderCountType == 1 || spsData.deltaPicOrderAlwaysZeroFlag) {
                                                i3 = 0;
                                                signedExpGolombCodedInt = 0;
                                            } else {
                                                if (!this.bitArray.canReadExpGolombCodedNum()) {
                                                    return;
                                                }
                                                int signedExpGolombCodedInt3 = this.bitArray.readSignedExpGolombCodedInt();
                                                if (!ppsData.bottomFieldPicOrderInFramePresentFlag || z) {
                                                    i4 = signedExpGolombCodedInt3;
                                                    i3 = 0;
                                                    signedExpGolombCodedInt = 0;
                                                    signedExpGolombCodedInt2 = 0;
                                                } else {
                                                    if (!this.bitArray.canReadExpGolombCodedNum()) {
                                                        return;
                                                    }
                                                    signedExpGolombCodedInt2 = this.bitArray.readSignedExpGolombCodedInt();
                                                    i4 = signedExpGolombCodedInt3;
                                                    i3 = 0;
                                                    signedExpGolombCodedInt = 0;
                                                }
                                            }
                                            this.sliceHeader.setAll(spsData, bits2, unsignedExpGolombCodedInt2, bits3, unsignedExpGolombCodedInt3, z, z2, bit, z3, unsignedExpGolombCodedInt, i3, signedExpGolombCodedInt, i4, signedExpGolombCodedInt2);
                                            this.isFilling = false;
                                            i4 = signedExpGolombCodedInt;
                                            signedExpGolombCodedInt2 = i4;
                                            this.sliceHeader.setAll(spsData, bits2, unsignedExpGolombCodedInt2, bits3, unsignedExpGolombCodedInt3, z, z2, bit, z3, unsignedExpGolombCodedInt, i3, signedExpGolombCodedInt, i4, signedExpGolombCodedInt2);
                                            this.isFilling = false;
                                        }
                                        return;
                                    }
                                    z = false;
                                    z2 = false;
                                    bit = z2;
                                    if (this.nalUnitType == 5) {
                                        z3 = true;
                                    } else {
                                        z3 = false;
                                    }
                                    if (z3) {
                                        unsignedExpGolombCodedInt = 0;
                                    } else if (!this.bitArray.canReadExpGolombCodedNum()) {
                                        return;
                                    } else {
                                        unsignedExpGolombCodedInt = this.bitArray.readUnsignedExpGolombCodedInt();
                                    }
                                    if (spsData.picOrderCountType == 0) {
                                        if (this.bitArray.canReadBits(spsData.picOrderCntLsbLength)) {
                                            return;
                                        }
                                        bits = this.bitArray.readBits(spsData.picOrderCntLsbLength);
                                        if (ppsData.bottomFieldPicOrderInFramePresentFlag) {
                                        }
                                        i3 = bits;
                                        signedExpGolombCodedInt = 0;
                                    } else {
                                        if (spsData.picOrderCountType == 1) {
                                        }
                                        i3 = 0;
                                        signedExpGolombCodedInt = 0;
                                    }
                                    i4 = signedExpGolombCodedInt;
                                    signedExpGolombCodedInt2 = i4;
                                    this.sliceHeader.setAll(spsData, bits2, unsignedExpGolombCodedInt2, bits3, unsignedExpGolombCodedInt3, z, z2, bit, z3, unsignedExpGolombCodedInt, i3, signedExpGolombCodedInt, i4, signedExpGolombCodedInt2);
                                    this.isFilling = false;
                                }
                            }
                        }
                    }
                }
            }
        }

        public boolean endNalUnit(long j, int i, boolean z, boolean z2) {
            boolean z3 = false;
            if (this.nalUnitType == 9 || (this.detectAccessUnits && this.sliceHeader.isFirstVclNalUnitOfPicture(this.previousSliceHeader))) {
                if (z && this.readingSample) {
                    outputSample(i + ((int) (j - this.nalUnitStartPosition)));
                }
                this.samplePosition = this.nalUnitStartPosition;
                this.sampleTimeUs = this.nalUnitTimeUs;
                this.sampleIsKeyframe = false;
                this.readingSample = true;
            }
            if (this.allowNonIdrKeyframes) {
                z2 = this.sliceHeader.isISlice();
            }
            boolean z4 = this.sampleIsKeyframe;
            int i2 = this.nalUnitType;
            if (i2 == 5 || (z2 && i2 == 1)) {
                z3 = true;
            }
            boolean z5 = z4 | z3;
            this.sampleIsKeyframe = z5;
            return z5;
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        private void outputSample(int i) {
            boolean z = this.sampleIsKeyframe;
            this.output.sampleMetadata(this.sampleTimeUs, z ? 1 : 0, (int) (this.nalUnitStartPosition - this.samplePosition), i, null);
        }

        private static final class SliceHeaderData {
            private static final int SLICE_TYPE_ALL_I = 7;
            private static final int SLICE_TYPE_I = 2;
            private boolean bottomFieldFlag;
            private boolean bottomFieldFlagPresent;
            private int deltaPicOrderCnt0;
            private int deltaPicOrderCnt1;
            private int deltaPicOrderCntBottom;
            private boolean fieldPicFlag;
            private int frameNum;
            private boolean hasSliceType;
            private boolean idrPicFlag;
            private int idrPicId;
            private boolean isComplete;
            private int nalRefIdc;
            private int picOrderCntLsb;
            private int picParameterSetId;
            private int sliceType;
            private NalUnitUtil.SpsData spsData;

            private SliceHeaderData() {
            }

            public void clear() {
                this.hasSliceType = false;
                this.isComplete = false;
            }

            public void setSliceType(int i) {
                this.sliceType = i;
                this.hasSliceType = true;
            }

            public void setAll(NalUnitUtil.SpsData spsData, int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, boolean z4, int i5, int i6, int i7, int i8, int i9) {
                this.spsData = spsData;
                this.nalRefIdc = i;
                this.sliceType = i2;
                this.frameNum = i3;
                this.picParameterSetId = i4;
                this.fieldPicFlag = z;
                this.bottomFieldFlagPresent = z2;
                this.bottomFieldFlag = z3;
                this.idrPicFlag = z4;
                this.idrPicId = i5;
                this.picOrderCntLsb = i6;
                this.deltaPicOrderCntBottom = i7;
                this.deltaPicOrderCnt0 = i8;
                this.deltaPicOrderCnt1 = i9;
                this.isComplete = true;
                this.hasSliceType = true;
            }

            public boolean isISlice() {
                int i;
                return this.hasSliceType && ((i = this.sliceType) == 7 || i == 2);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public boolean isFirstVclNalUnitOfPicture(SliceHeaderData sliceHeaderData) {
                boolean z;
                boolean z2;
                if (this.isComplete) {
                    if (!sliceHeaderData.isComplete || this.frameNum != sliceHeaderData.frameNum || this.picParameterSetId != sliceHeaderData.picParameterSetId || this.fieldPicFlag != sliceHeaderData.fieldPicFlag) {
                        return true;
                    }
                    if (this.bottomFieldFlagPresent && sliceHeaderData.bottomFieldFlagPresent && this.bottomFieldFlag != sliceHeaderData.bottomFieldFlag) {
                        return true;
                    }
                    int i = this.nalRefIdc;
                    int i2 = sliceHeaderData.nalRefIdc;
                    if (i != i2 && (i == 0 || i2 == 0)) {
                        return true;
                    }
                    if (this.spsData.picOrderCountType == 0 && sliceHeaderData.spsData.picOrderCountType == 0 && (this.picOrderCntLsb != sliceHeaderData.picOrderCntLsb || this.deltaPicOrderCntBottom != sliceHeaderData.deltaPicOrderCntBottom)) {
                        return true;
                    }
                    if ((this.spsData.picOrderCountType == 1 && sliceHeaderData.spsData.picOrderCountType == 1 && (this.deltaPicOrderCnt0 != sliceHeaderData.deltaPicOrderCnt0 || this.deltaPicOrderCnt1 != sliceHeaderData.deltaPicOrderCnt1)) || (z = this.idrPicFlag) != (z2 = sliceHeaderData.idrPicFlag)) {
                        return true;
                    }
                    if (z && z2 && this.idrPicId != sliceHeaderData.idrPicId) {
                        return true;
                    }
                }
                return false;
            }
        }
    }
}
