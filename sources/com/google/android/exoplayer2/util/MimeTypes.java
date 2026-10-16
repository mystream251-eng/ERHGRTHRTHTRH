package com.google.android.exoplayer2.util;

import android.text.TextUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class MimeTypes {
    public static final String APPLICATION_CAMERA_MOTION = "application/x-camera-motion";
    public static final String APPLICATION_CEA608 = "application/cea-608";
    public static final String APPLICATION_CEA708 = "application/cea-708";
    public static final String APPLICATION_DVBSUBS = "application/dvbsubs";
    public static final String APPLICATION_EMSG = "application/x-emsg";
    public static final String APPLICATION_EXIF = "application/x-exif";
    public static final String APPLICATION_ID3 = "application/id3";
    public static final String APPLICATION_M3U8 = "application/x-mpegURL";
    public static final String APPLICATION_MP4 = "application/mp4";
    public static final String APPLICATION_MP4CEA608 = "application/x-mp4-cea-608";
    public static final String APPLICATION_MP4VTT = "application/x-mp4-vtt";
    public static final String APPLICATION_MPD = "application/dash+xml";
    public static final String APPLICATION_PGS = "application/pgs";
    public static final String APPLICATION_RAWCC = "application/x-rawcc";
    public static final String APPLICATION_SCTE35 = "application/x-scte35";
    public static final String APPLICATION_SS = "application/vnd.ms-sstr+xml";
    public static final String APPLICATION_SUBRIP = "application/x-subrip";
    public static final String APPLICATION_TTML = "application/ttml+xml";
    public static final String APPLICATION_TX3G = "application/x-quicktime-tx3g";
    public static final String APPLICATION_VOBSUB = "application/vobsub";
    public static final String APPLICATION_WEBM = "application/webm";
    public static final String AUDIO_AAC = "audio/mp4a-latm";
    public static final String AUDIO_AC3 = "audio/ac3";
    public static final String AUDIO_ALAC = "audio/alac";
    public static final String AUDIO_ALAW = "audio/g711-alaw";
    public static final String AUDIO_AMR_NB = "audio/3gpp";
    public static final String AUDIO_AMR_WB = "audio/amr-wb";
    public static final String AUDIO_DTS = "audio/vnd.dts";
    public static final String AUDIO_DTS_EXPRESS = "audio/vnd.dts.hd;profile=lbr";
    public static final String AUDIO_DTS_HD = "audio/vnd.dts.hd";
    public static final String AUDIO_E_AC3 = "audio/eac3";
    public static final String AUDIO_E_AC3_JOC = "audio/eac3-joc";
    public static final String AUDIO_FLAC = "audio/flac";
    public static final String AUDIO_MLAW = "audio/g711-mlaw";
    public static final String AUDIO_MP4 = "audio/mp4";
    public static final String AUDIO_MPEG = "audio/mpeg";
    public static final String AUDIO_MPEG_L1 = "audio/mpeg-L1";
    public static final String AUDIO_MPEG_L2 = "audio/mpeg-L2";
    public static final String AUDIO_MSGSM = "audio/gsm";
    public static final String AUDIO_OPUS = "audio/opus";
    public static final String AUDIO_RAW = "audio/raw";
    public static final String AUDIO_TRUEHD = "audio/true-hd";
    public static final String AUDIO_UNKNOWN = "audio/x-unknown";
    public static final String AUDIO_VORBIS = "audio/vorbis";
    public static final String AUDIO_WEBM = "audio/webm";
    public static final String BASE_TYPE_APPLICATION = "application";
    public static final String BASE_TYPE_AUDIO = "audio";
    public static final String BASE_TYPE_TEXT = "text";
    public static final String BASE_TYPE_VIDEO = "video";
    public static final String TEXT_SSA = "text/x-ssa";
    public static final String TEXT_VTT = "text/vtt";
    public static final String VIDEO_H263 = "video/3gpp";
    public static final String VIDEO_H264 = "video/avc";
    public static final String VIDEO_H265 = "video/hevc";
    public static final String VIDEO_MP4 = "video/mp4";
    public static final String VIDEO_MP4V = "video/mp4v-es";
    public static final String VIDEO_MPEG = "video/mpeg";
    public static final String VIDEO_MPEG2 = "video/mpeg2";
    public static final String VIDEO_UNKNOWN = "video/x-unknown";
    public static final String VIDEO_VC1 = "video/wvc1";
    public static final String VIDEO_VP8 = "video/x-vnd.on2.vp8";
    public static final String VIDEO_VP9 = "video/x-vnd.on2.vp9";
    public static final String VIDEO_WEBM = "video/webm";
    private static final ArrayList<CustomMimeType> customMimeTypes = new ArrayList<>();

    public static String getMimeTypeFromMp4ObjectType(int i) {
        if (i == 32) {
            return VIDEO_MP4V;
        }
        if (i == 33) {
            return VIDEO_H264;
        }
        if (i == 35) {
            return VIDEO_H265;
        }
        if (i == 64) {
            return AUDIO_AAC;
        }
        if (i == 163) {
            return VIDEO_VC1;
        }
        if (i == 177) {
            return VIDEO_VP9;
        }
        if (i == 165) {
            return AUDIO_AC3;
        }
        if (i == 166) {
            return AUDIO_E_AC3;
        }
        switch (i) {
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
                return VIDEO_MPEG2;
            case 102:
            case 103:
            case 104:
                return AUDIO_AAC;
            case 105:
            case 107:
                return AUDIO_MPEG;
            case 106:
                return VIDEO_MPEG;
            default:
                switch (i) {
                    case 169:
                    case 172:
                        return AUDIO_DTS;
                    case 170:
                    case 171:
                        return AUDIO_DTS_HD;
                    case 173:
                        return AUDIO_OPUS;
                    default:
                        return null;
                }
        }
    }

    public static void registerCustomMimeType(String str, String str2, int i) {
        CustomMimeType customMimeType = new CustomMimeType(str, str2, i);
        int size = customMimeTypes.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (str.equals(customMimeTypes.get(i2).mimeType)) {
                customMimeTypes.remove(i2);
                break;
            }
        }
        customMimeTypes.add(customMimeType);
    }

    public static boolean isAudio(String str) {
        return BASE_TYPE_AUDIO.equals(getTopLevelType(str));
    }

    public static boolean isVideo(String str) {
        return BASE_TYPE_VIDEO.equals(getTopLevelType(str));
    }

    public static boolean isText(String str) {
        return "text".equals(getTopLevelType(str));
    }

    public static boolean isApplication(String str) {
        return BASE_TYPE_APPLICATION.equals(getTopLevelType(str));
    }

    public static String getVideoMediaMimeType(String str) {
        if (str == null) {
            return null;
        }
        for (String str2 : Util.splitCodecs(str)) {
            String mediaMimeType = getMediaMimeType(str2);
            if (mediaMimeType != null && isVideo(mediaMimeType)) {
                return mediaMimeType;
            }
        }
        return null;
    }

    public static String getAudioMediaMimeType(String str) {
        if (str == null) {
            return null;
        }
        for (String str2 : Util.splitCodecs(str)) {
            String mediaMimeType = getMediaMimeType(str2);
            if (mediaMimeType != null && isAudio(mediaMimeType)) {
                return mediaMimeType;
            }
        }
        return null;
    }

    public static String getMediaMimeType(String str) {
        String mimeTypeFromMp4ObjectType = null;
        if (str == null) {
            return null;
        }
        String lowerInvariant = Util.toLowerInvariant(str.trim());
        if (lowerInvariant.startsWith("avc1") || lowerInvariant.startsWith("avc3")) {
            return VIDEO_H264;
        }
        if (lowerInvariant.startsWith("hev1") || lowerInvariant.startsWith("hvc1")) {
            return VIDEO_H265;
        }
        if (lowerInvariant.startsWith("vp9") || lowerInvariant.startsWith("vp09")) {
            return VIDEO_VP9;
        }
        if (lowerInvariant.startsWith("vp8") || lowerInvariant.startsWith("vp08")) {
            return VIDEO_VP8;
        }
        if (lowerInvariant.startsWith("mp4a")) {
            if (lowerInvariant.startsWith("mp4a.")) {
                String strSubstring = lowerInvariant.substring(5);
                if (strSubstring.length() >= 2) {
                    try {
                        mimeTypeFromMp4ObjectType = getMimeTypeFromMp4ObjectType(Integer.parseInt(Util.toUpperInvariant(strSubstring.substring(0, 2)), 16));
                    } catch (NumberFormatException unused) {
                    }
                }
            }
            return mimeTypeFromMp4ObjectType == null ? AUDIO_AAC : mimeTypeFromMp4ObjectType;
        }
        if (lowerInvariant.startsWith("ac-3") || lowerInvariant.startsWith("dac3")) {
            return AUDIO_AC3;
        }
        if (lowerInvariant.startsWith("ec-3") || lowerInvariant.startsWith("dec3")) {
            return AUDIO_E_AC3;
        }
        if (lowerInvariant.startsWith("ec+3")) {
            return AUDIO_E_AC3_JOC;
        }
        if (lowerInvariant.startsWith("dtsc") || lowerInvariant.startsWith("dtse")) {
            return AUDIO_DTS;
        }
        if (lowerInvariant.startsWith("dtsh") || lowerInvariant.startsWith("dtsl")) {
            return AUDIO_DTS_HD;
        }
        if (lowerInvariant.startsWith("opus")) {
            return AUDIO_OPUS;
        }
        if (lowerInvariant.startsWith("vorbis")) {
            return AUDIO_VORBIS;
        }
        return lowerInvariant.startsWith("flac") ? AUDIO_FLAC : getCustomMimeTypeForCodec(lowerInvariant);
    }

    public static int getTrackType(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (isAudio(str)) {
            return 1;
        }
        if (isVideo(str)) {
            return 2;
        }
        if (isText(str) || APPLICATION_CEA608.equals(str) || APPLICATION_CEA708.equals(str) || APPLICATION_MP4CEA608.equals(str) || "application/x-subrip".equals(str) || APPLICATION_TTML.equals(str) || APPLICATION_TX3G.equals(str) || APPLICATION_MP4VTT.equals(str) || APPLICATION_RAWCC.equals(str) || APPLICATION_VOBSUB.equals(str) || APPLICATION_PGS.equals(str) || APPLICATION_DVBSUBS.equals(str)) {
            return 3;
        }
        if (APPLICATION_ID3.equals(str) || APPLICATION_EMSG.equals(str) || APPLICATION_SCTE35.equals(str)) {
            return 4;
        }
        if (APPLICATION_CAMERA_MOTION.equals(str)) {
            return 5;
        }
        return getTrackTypeForCustomMimeType(str);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:23:0x004a  */
    public static int getEncoding(String str) {
        byte b;
        switch (str) {
            case "audio/eac3-joc":
                b = 2;
                break;
            case "audio/vnd.dts":
                b = 3;
                break;
            case "audio/ac3":
                b = 0;
                break;
            case "audio/eac3":
                b = 1;
                break;
            case "audio/vnd.dts.hd":
                b = 4;
                break;
            case "audio/true-hd":
                b = 5;
                break;
            default:
                b = -1;
                break;
        }
        if (b == 0) {
            return 5;
        }
        if (b == 1 || b == 2) {
            return 6;
        }
        if (b == 3) {
            return 7;
        }
        if (b != 4) {
            return b != 5 ? 0 : 14;
        }
        return 8;
    }

    public static int getTrackTypeOfCodec(String str) {
        return getTrackType(getMediaMimeType(str));
    }

    private static String getTopLevelType(String str) {
        if (str == null) {
            return null;
        }
        int iIndexOf = str.indexOf(47);
        if (iIndexOf == -1) {
            throw new IllegalArgumentException("Invalid mime type: " + str);
        }
        return str.substring(0, iIndexOf);
    }

    private static String getCustomMimeTypeForCodec(String str) {
        int size = customMimeTypes.size();
        for (int i = 0; i < size; i++) {
            CustomMimeType customMimeType = customMimeTypes.get(i);
            if (str.startsWith(customMimeType.codecPrefix)) {
                return customMimeType.mimeType;
            }
        }
        return null;
    }

    private static int getTrackTypeForCustomMimeType(String str) {
        int size = customMimeTypes.size();
        for (int i = 0; i < size; i++) {
            CustomMimeType customMimeType = customMimeTypes.get(i);
            if (str.equals(customMimeType.mimeType)) {
                return customMimeType.trackType;
            }
        }
        return -1;
    }

    private MimeTypes() {
    }

    private static final class CustomMimeType {
        public final String codecPrefix;
        public final String mimeType;
        public final int trackType;

        public CustomMimeType(String str, String str2, int i) {
            this.mimeType = str;
            this.codecPrefix = str2;
            this.trackType = i;
        }
    }
}
