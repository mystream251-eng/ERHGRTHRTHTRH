package com.google.android.exoplayer2.mediacodec;

import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface MediaCodecSelector {
    public static final MediaCodecSelector DEFAULT = new MediaCodecSelector() { // from class: com.google.android.exoplayer2.mediacodec.MediaCodecSelector.1
        @Override // com.google.android.exoplayer2.mediacodec.MediaCodecSelector
        public List<MediaCodecInfo> getDecoderInfos(String str, boolean z) throws MediaCodecUtil.DecoderQueryException {
            List<MediaCodecInfo> decoderInfos = MediaCodecUtil.getDecoderInfos(str, z);
            if (decoderInfos.isEmpty()) {
                return Collections.emptyList();
            }
            return Collections.singletonList(decoderInfos.get(0));
        }

        @Override // com.google.android.exoplayer2.mediacodec.MediaCodecSelector
        public MediaCodecInfo getPassthroughDecoderInfo() throws MediaCodecUtil.DecoderQueryException {
            return MediaCodecUtil.getPassthroughDecoderInfo();
        }
    };
    public static final MediaCodecSelector DEFAULT_WITH_FALLBACK = new MediaCodecSelector() { // from class: com.google.android.exoplayer2.mediacodec.MediaCodecSelector.2
        @Override // com.google.android.exoplayer2.mediacodec.MediaCodecSelector
        public List<MediaCodecInfo> getDecoderInfos(String str, boolean z) throws MediaCodecUtil.DecoderQueryException {
            return MediaCodecUtil.getDecoderInfos(str, z);
        }

        @Override // com.google.android.exoplayer2.mediacodec.MediaCodecSelector
        public MediaCodecInfo getPassthroughDecoderInfo() throws MediaCodecUtil.DecoderQueryException {
            return MediaCodecUtil.getPassthroughDecoderInfo();
        }
    };

    List<MediaCodecInfo> getDecoderInfos(String str, boolean z) throws MediaCodecUtil.DecoderQueryException;

    MediaCodecInfo getPassthroughDecoderInfo() throws MediaCodecUtil.DecoderQueryException;
}
