package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.text.cea.Cea708InitializationData;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultTsPayloadReaderFactory implements TsPayloadReader.Factory {
    private static final int DESCRIPTOR_TAG_CAPTION_SERVICE = 134;
    public static final int FLAG_ALLOW_NON_IDR_KEYFRAMES = 1;
    public static final int FLAG_DETECT_ACCESS_UNITS = 8;
    public static final int FLAG_IGNORE_AAC_STREAM = 2;
    public static final int FLAG_IGNORE_H264_STREAM = 4;
    public static final int FLAG_IGNORE_HDMV_DTS_STREAM = 64;
    public static final int FLAG_IGNORE_SPLICE_INFO_STREAM = 16;
    public static final int FLAG_OVERRIDE_CAPTION_DESCRIPTORS = 32;
    private final List<Format> closedCaptionFormats;
    private final int flags;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public DefaultTsPayloadReaderFactory() {
        this(0);
    }

    public DefaultTsPayloadReaderFactory(int i) {
        this(i, Collections.singletonList(Format.createTextSampleFormat(null, MimeTypes.APPLICATION_CEA608, 0, null)));
    }

    public DefaultTsPayloadReaderFactory(int i, List<Format> list) {
        this.flags = i;
        this.closedCaptionFormats = list;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.TsPayloadReader.Factory
    public SparseArray<TsPayloadReader> createInitialPayloadReaders() {
        return new SparseArray<>();
    }

    @Override // com.google.android.exoplayer2.extractor.ts.TsPayloadReader.Factory
    public TsPayloadReader createPayloadReader(int i, TsPayloadReader.EsInfo esInfo) {
        if (i == 2) {
            return new PesReader(new H262Reader(buildUserDataReader(esInfo)));
        }
        if (i == 3 || i == 4) {
            return new PesReader(new MpegAudioReader(esInfo.language));
        }
        if (i == 15) {
            if (isSet(2)) {
                return null;
            }
            return new PesReader(new AdtsReader(false, esInfo.language));
        }
        if (i == 17) {
            if (isSet(2)) {
                return null;
            }
            return new PesReader(new LatmReader(esInfo.language));
        }
        if (i == 21) {
            return new PesReader(new Id3Reader());
        }
        if (i == 27) {
            if (isSet(4)) {
                return null;
            }
            return new PesReader(new H264Reader(buildSeiReader(esInfo), isSet(1), isSet(8)));
        }
        if (i == 36) {
            return new PesReader(new H265Reader(buildSeiReader(esInfo)));
        }
        if (i != 89) {
            if (i != 138) {
                if (i != 129) {
                    if (i != 130) {
                        if (i == 134) {
                            if (isSet(16)) {
                                return null;
                            }
                            return new SectionReader(new SpliceInfoSectionReader());
                        }
                        if (i != 135) {
                            return null;
                        }
                    } else if (isSet(64)) {
                        return null;
                    }
                }
                return new PesReader(new Ac3Reader(esInfo.language));
            }
            return new PesReader(new DtsReader(esInfo.language));
        }
        return new PesReader(new DvbSubtitleReader(esInfo.dvbSubtitleInfos));
    }

    private SeiReader buildSeiReader(TsPayloadReader.EsInfo esInfo) {
        return new SeiReader(getClosedCaptionFormats(esInfo));
    }

    private UserDataReader buildUserDataReader(TsPayloadReader.EsInfo esInfo) {
        return new UserDataReader(getClosedCaptionFormats(esInfo));
    }

    private List<Format> getClosedCaptionFormats(TsPayloadReader.EsInfo esInfo) {
        String str;
        int i;
        List<byte[]> listBuildData;
        if (isSet(32)) {
            return this.closedCaptionFormats;
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(esInfo.descriptorBytes);
        List<Format> arrayList = this.closedCaptionFormats;
        while (parsableByteArray.bytesLeft() > 0) {
            int unsignedByte = parsableByteArray.readUnsignedByte();
            int position = parsableByteArray.getPosition() + parsableByteArray.readUnsignedByte();
            if (unsignedByte == 134) {
                arrayList = new ArrayList<>();
                int unsignedByte2 = parsableByteArray.readUnsignedByte() & 31;
                for (int i2 = 0; i2 < unsignedByte2; i2++) {
                    String string = parsableByteArray.readString(3);
                    int unsignedByte3 = parsableByteArray.readUnsignedByte();
                    boolean z = (unsignedByte3 & 128) != 0;
                    if (z) {
                        i = unsignedByte3 & 63;
                        str = MimeTypes.APPLICATION_CEA708;
                    } else {
                        str = MimeTypes.APPLICATION_CEA608;
                        i = 1;
                    }
                    byte unsignedByte4 = (byte) parsableByteArray.readUnsignedByte();
                    parsableByteArray.skipBytes(1);
                    if (z) {
                        listBuildData = Cea708InitializationData.buildData((unsignedByte4 & 64) != 0);
                    } else {
                        listBuildData = null;
                    }
                    arrayList.add(Format.createTextSampleFormat(null, str, null, -1, 0, string, i, null, Long.MAX_VALUE, listBuildData));
                }
            }
            parsableByteArray.setPosition(position);
        }
        return arrayList;
    }

    private boolean isSet(int i) {
        return (i & this.flags) != 0;
    }
}
