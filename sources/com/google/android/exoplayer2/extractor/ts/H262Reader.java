package com.google.android.exoplayer2.extractor.ts;

import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Arrays;
import java.util.Collections;
import kotlin.UByte;

/* JADX INFO: loaded from: classes.dex */
public final class H262Reader implements ElementaryStreamReader {
    private static final double[] FRAME_RATE_VALUES = {23.976023976023978d, 24.0d, 25.0d, 29.97002997002997d, 30.0d, 50.0d, 59.94005994005994d, 60.0d};
    private static final int START_EXTENSION = 181;
    private static final int START_GROUP = 184;
    private static final int START_PICTURE = 0;
    private static final int START_SEQUENCE_HEADER = 179;
    private static final int START_USER_DATA = 178;
    private final CsdBuffer csdBuffer;
    private String formatId;
    private long frameDurationUs;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs;
    private final boolean[] prefixFlags;
    private boolean sampleHasPicture;
    private boolean sampleIsKeyframe;
    private long samplePosition;
    private long sampleTimeUs;
    private boolean startedFirstSample;
    private long totalBytesWritten;
    private final NalUnitTargetBuffer userData;
    private final ParsableByteArray userDataParsable;
    private final UserDataReader userDataReader;

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }

    public H262Reader() {
        this(null);
    }

    public H262Reader(UserDataReader userDataReader) {
        this.userDataReader = userDataReader;
        this.prefixFlags = new boolean[4];
        this.csdBuffer = new CsdBuffer(128);
        if (userDataReader != null) {
            this.userData = new NalUnitTargetBuffer(START_USER_DATA, 128);
            this.userDataParsable = new ParsableByteArray();
        } else {
            this.userData = null;
            this.userDataParsable = null;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.csdBuffer.reset();
        if (this.userDataReader != null) {
            this.userData.reset();
        }
        this.totalBytesWritten = 0L;
        this.startedFirstSample = false;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 2);
        UserDataReader userDataReader = this.userDataReader;
        if (userDataReader != null) {
            userDataReader.createTracks(extractorOutput, trackIdGenerator);
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, int i) {
        this.pesTimeUs = j;
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
    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        int i;
        int position = parsableByteArray.getPosition();
        int iLimit = parsableByteArray.limit();
        byte[] bArr = parsableByteArray.data;
        this.totalBytesWritten += (long) parsableByteArray.bytesLeft();
        this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
        while (true) {
            int iFindNalUnit = NalUnitUtil.findNalUnit(bArr, position, iLimit, this.prefixFlags);
            if (iFindNalUnit == iLimit) {
                break;
            }
            int i2 = iFindNalUnit + 3;
            int i3 = parsableByteArray.data[i2] & UByte.MAX_VALUE;
            int i4 = iFindNalUnit - position;
            if (!this.hasOutputFormat) {
                if (i4 > 0) {
                    this.csdBuffer.onData(bArr, position, iFindNalUnit);
                }
                if (this.csdBuffer.onStartCode(i3, i4 < 0 ? -i4 : 0)) {
                    Pair<Format, Long> csdBuffer = parseCsdBuffer(this.csdBuffer, this.formatId);
                    this.output.format((Format) csdBuffer.first);
                    this.frameDurationUs = ((Long) csdBuffer.second).longValue();
                    this.hasOutputFormat = true;
                }
            }
            if (this.userDataReader != null) {
                if (i4 > 0) {
                    this.userData.appendToNalUnit(bArr, position, iFindNalUnit);
                    i = 0;
                } else {
                    i = -i4;
                }
                if (this.userData.endNalUnit(i)) {
                    this.userDataParsable.reset(this.userData.nalData, NalUnitUtil.unescapeStream(this.userData.nalData, this.userData.nalLength));
                    this.userDataReader.consume(this.sampleTimeUs, this.userDataParsable);
                }
                if (i3 == START_USER_DATA && parsableByteArray.data[iFindNalUnit + 2] == 1) {
                    this.userData.startNalUnit(i3);
                }
            }
            if (i3 == 0 || i3 == START_SEQUENCE_HEADER) {
                int i5 = iLimit - iFindNalUnit;
                if (this.startedFirstSample && this.sampleHasPicture && this.hasOutputFormat) {
                    this.output.sampleMetadata(this.sampleTimeUs, this.sampleIsKeyframe ? 1 : 0, ((int) (this.totalBytesWritten - this.samplePosition)) - i5, i5, null);
                }
                if (!this.startedFirstSample || this.sampleHasPicture) {
                    this.samplePosition = this.totalBytesWritten - ((long) i5);
                    long j = this.pesTimeUs;
                    if (j == C.TIME_UNSET) {
                        j = this.startedFirstSample ? this.sampleTimeUs + this.frameDurationUs : 0L;
                    }
                    this.sampleTimeUs = j;
                    this.sampleIsKeyframe = false;
                    this.pesTimeUs = C.TIME_UNSET;
                    this.startedFirstSample = true;
                }
                this.sampleHasPicture = i3 == 0;
            } else if (i3 == START_GROUP) {
                this.sampleIsKeyframe = true;
            }
            position = i2;
        }
        if (!this.hasOutputFormat) {
            this.csdBuffer.onData(bArr, position, iLimit);
        }
        if (this.userDataReader != null) {
            this.userData.appendToNalUnit(bArr, position, iLimit);
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x006b  */
    /* JADX WARN: Code duplicated, block: B:17:0x0070  */
    /* JADX WARN: Code duplicated, block: B:19:0x0081  */
    private static Pair<Format, Long> parseCsdBuffer(CsdBuffer csdBuffer, String str) {
        float f;
        int i;
        float f2;
        long j;
        int i2;
        double[] dArr;
        double d;
        int i3;
        int i4;
        byte[] bArrCopyOf = Arrays.copyOf(csdBuffer.data, csdBuffer.length);
        int i5 = bArrCopyOf[4] & UByte.MAX_VALUE;
        int i6 = bArrCopyOf[5] & UByte.MAX_VALUE;
        int i7 = (i5 << 4) | (i6 >> 4);
        int i8 = ((i6 & 15) << 8) | (bArrCopyOf[6] & UByte.MAX_VALUE);
        int i9 = (bArrCopyOf[7] & 240) >> 4;
        if (i9 == 2) {
            f = i8 * 4;
            i = i7 * 3;
        } else {
            if (i9 != 3) {
                if (i9 != 4) {
                    f2 = 1.0f;
                } else {
                    f = i8 * 121;
                    i = i7 * 100;
                }
                Format formatCreateVideoSampleFormat = Format.createVideoSampleFormat(str, MimeTypes.VIDEO_MPEG2, null, -1, -1, i7, i8, -1.0f, Collections.singletonList(bArrCopyOf), -1, f2, null);
                j = 0;
                i2 = (bArrCopyOf[7] & 15) - 1;
                if (i2 >= 0) {
                    dArr = FRAME_RATE_VALUES;
                    if (i2 < dArr.length) {
                        d = dArr[i2];
                        int i10 = csdBuffer.sequenceExtensionPosition + 9;
                        i3 = (bArrCopyOf[i10] & 96) >> 5;
                        i4 = bArrCopyOf[i10] & 31;
                        if (i3 != i4) {
                            d *= (((double) i3) + 1.0d) / ((double) (i4 + 1));
                        }
                        j = (long) (1000000.0d / d);
                    }
                }
                return Pair.create(formatCreateVideoSampleFormat, Long.valueOf(j));
            }
            f = i8 * 16;
            i = i7 * 9;
        }
        f2 = f / i;
        Format formatCreateVideoSampleFormat2 = Format.createVideoSampleFormat(str, MimeTypes.VIDEO_MPEG2, null, -1, -1, i7, i8, -1.0f, Collections.singletonList(bArrCopyOf), -1, f2, null);
        j = 0;
        i2 = (bArrCopyOf[7] & 15) - 1;
        if (i2 >= 0) {
            dArr = FRAME_RATE_VALUES;
            if (i2 < dArr.length) {
                d = dArr[i2];
                int i11 = csdBuffer.sequenceExtensionPosition + 9;
                i3 = (bArrCopyOf[i11] & 96) >> 5;
                i4 = bArrCopyOf[i11] & 31;
                if (i3 != i4) {
                    d *= (((double) i3) + 1.0d) / ((double) (i4 + 1));
                }
                j = (long) (1000000.0d / d);
            }
        }
        return Pair.create(formatCreateVideoSampleFormat2, Long.valueOf(j));
    }

    private static final class CsdBuffer {
        private static final byte[] START_CODE = {0, 0, 1};
        public byte[] data;
        private boolean isFilling;
        public int length;
        public int sequenceExtensionPosition;

        public CsdBuffer(int i) {
            this.data = new byte[i];
        }

        public void reset() {
            this.isFilling = false;
            this.length = 0;
            this.sequenceExtensionPosition = 0;
        }

        public boolean onStartCode(int i, int i2) {
            if (this.isFilling) {
                int i3 = this.length - i2;
                this.length = i3;
                if (this.sequenceExtensionPosition == 0 && i == H262Reader.START_EXTENSION) {
                    this.sequenceExtensionPosition = i3;
                } else {
                    this.isFilling = false;
                    return true;
                }
            } else if (i == H262Reader.START_SEQUENCE_HEADER) {
                this.isFilling = true;
            }
            byte[] bArr = START_CODE;
            onData(bArr, 0, bArr.length);
            return false;
        }

        public void onData(byte[] bArr, int i, int i2) {
            if (this.isFilling) {
                int i3 = i2 - i;
                byte[] bArr2 = this.data;
                int length = bArr2.length;
                int i4 = this.length;
                if (length < i4 + i3) {
                    this.data = Arrays.copyOf(bArr2, (i4 + i3) * 2);
                }
                System.arraycopy(bArr, i, this.data, this.length, i3);
                this.length += i3;
            }
        }
    }
}
