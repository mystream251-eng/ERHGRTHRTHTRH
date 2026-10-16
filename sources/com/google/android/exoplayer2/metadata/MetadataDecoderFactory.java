package com.google.android.exoplayer2.metadata;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.metadata.emsg.EventMessageDecoder;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.metadata.scte35.SpliceInfoDecoder;
import com.google.android.exoplayer2.util.MimeTypes;

/* JADX INFO: loaded from: classes.dex */
public interface MetadataDecoderFactory {
    public static final MetadataDecoderFactory DEFAULT = new MetadataDecoderFactory() { // from class: com.google.android.exoplayer2.metadata.MetadataDecoderFactory.1
        @Override // com.google.android.exoplayer2.metadata.MetadataDecoderFactory
        public boolean supportsFormat(Format format) {
            String str = format.sampleMimeType;
            return MimeTypes.APPLICATION_ID3.equals(str) || MimeTypes.APPLICATION_EMSG.equals(str) || MimeTypes.APPLICATION_SCTE35.equals(str);
        }

        /* JADX WARN: Code duplicated, block: B:18:0x0036  */
        @Override // com.google.android.exoplayer2.metadata.MetadataDecoderFactory
        public MetadataDecoder createDecoder(Format format) {
            byte b;
            String str = format.sampleMimeType;
            int iHashCode = str.hashCode();
            if (iHashCode != -1248341703) {
                if (iHashCode != 1154383568) {
                    if (iHashCode == 1652648887 && str.equals(MimeTypes.APPLICATION_SCTE35)) {
                        b = 2;
                    } else {
                        b = -1;
                    }
                } else if (str.equals(MimeTypes.APPLICATION_EMSG)) {
                    b = 1;
                } else {
                    b = -1;
                }
            } else if (str.equals(MimeTypes.APPLICATION_ID3)) {
                b = 0;
            } else {
                b = -1;
            }
            if (b == 0) {
                return new Id3Decoder();
            }
            if (b == 1) {
                return new EventMessageDecoder();
            }
            if (b == 2) {
                return new SpliceInfoDecoder();
            }
            throw new IllegalArgumentException("Attempted to create decoder for unsupported format");
        }
    };

    MetadataDecoder createDecoder(Format format);

    boolean supportsFormat(Format format);
}
