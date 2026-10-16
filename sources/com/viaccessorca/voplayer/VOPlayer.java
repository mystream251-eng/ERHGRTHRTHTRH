package com.viaccessorca.voplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaCrypto;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.view.Choreographer;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.util.MimeTypes;
import com.viaccessorca.common.VOLibraryLoader;
import com.viaccessorca.common.VOLogger;
import com.viaccessorca.common.VOUtils;
import com.viaccessorca.drm.VOMediaDrmAgent;
import com.viaccessorca.drm.VOPlayreadyAgent;
import com.viaccessorca.drm.VOVerimatrixAgent;
import com.viaccessorca.drm.VOViaccessAgent;
import com.viaccessorca.drm.VOWidevineAgent;
import com.viaccessorca.drm.impl.DrmAgent;
import com.viaccessorca.drm.impl.VOBaseMediaDrmAgent;
import com.viaccessorca.drm.impl.VerimatrixAgent;
import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.exceptions.VOStatusCode;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.math.BigInteger;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class VOPlayer {
    public static final int AUDIO_BOOSTER_EXTREME = 2;
    public static final int AUDIO_BOOSTER_GENTLE = 0;
    public static final int AUDIO_BOOSTER_MEDIUM = 1;
    public static final int AUDIO_BOOSTER_OFF = -1;
    public static final int CERTIFICATE_TYPE_DER = 1;
    public static final int CERTIFICATE_TYPE_ENG = 2;
    public static final int CERTIFICATE_TYPE_PEM = 0;
    public static final int CODECS_TYPE_AML = 6;
    public static final int CODECS_TYPE_DEFAULT = 0;
    public static final int CODECS_TYPE_HW_MC_NT = 2;
    public static final int CODECS_TYPE_HW_MC_TU = 1;
    public static final int CODECS_TYPE_HW_MC_TU_GL = 8;
    public static final int CODECS_TYPE_HW_MC_TU_TV = 7;
    public static final int CODECS_TYPE_HW_SF_NT = 4;
    public static final int CODECS_TYPE_HW_SF_TU = 3;
    public static final int CODECS_TYPE_SW = 5;
    public static final int CODECS_TYPE_UNKNOWN = -1;
    public static final int DISPLAY_MODE_CROP = 2;
    public static final int DISPLAY_MODE_FIT = 0;
    public static final int DISPLAY_MODE_STRETCH = 1;
    private static WeakReference I1 = null;
    private static WeakReference[] J1 = null;
    public static final int MEDIA_ERROR_BAD_LICENSE = -2113929212;
    public static final int MEDIA_ERROR_DRM = 3100;
    public static final int MEDIA_ERROR_DRM_BAD_ARGUMENTS = 3103;
    public static final int MEDIA_ERROR_DRM_FAIL = 3102;
    public static final int MEDIA_ERROR_DRM_INIT_PARAMS_NOT_SET = 3107;
    public static final int MEDIA_ERROR_DRM_LICENSE_EXPIRED = 3111;
    public static final int MEDIA_ERROR_DRM_LICENSE_NOT_FOUND = 3112;
    public static final int MEDIA_ERROR_DRM_MISSING_PERMISSION = 3104;
    public static final int MEDIA_ERROR_DRM_NETWORK_COMMUNICATION_FAILURE = 3105;
    public static final int MEDIA_ERROR_DRM_NOT_AUTHORIZED = 3108;
    public static final int MEDIA_ERROR_DRM_NOT_INITIALIZED = 3110;
    public static final int MEDIA_ERROR_DRM_NOT_SUPPORTED = 3101;
    public static final int MEDIA_ERROR_DRM_SERVER_NOT_REACHABLE = 3109;
    public static final int MEDIA_ERROR_DRM_TRUSTED_TIME_NOT_SET = 3106;
    protected static final int MEDIA_ERROR_FAILED_TO_PARSE_VMAP = 11012;
    public static final int MEDIA_ERROR_ILLEGAL_STATE = 8002;
    public static final int MEDIA_ERROR_MISSING_LICENSE_TIMEOUT = 8006;
    public static final int MEDIA_ERROR_NETWORK_BAD_URL = 4004;
    public static final int MEDIA_ERROR_NETWORK_HTTP_BAD_GATEWAY = 4012;
    public static final int MEDIA_ERROR_NETWORK_HTTP_CONFLICT = 4008;
    public static final int MEDIA_ERROR_NETWORK_HTTP_FORBIDDEN = 4006;
    public static final int MEDIA_ERROR_NETWORK_HTTP_GATEWAY_TIMEOUT = 4014;
    public static final int MEDIA_ERROR_NETWORK_HTTP_GONE = 4009;
    public static final int MEDIA_ERROR_NETWORK_HTTP_INTERNAL_SERVER_ERROR = 4010;
    public static final int MEDIA_ERROR_NETWORK_HTTP_NOT_IMPLEMENTED = 4011;
    public static final int MEDIA_ERROR_NETWORK_HTTP_REQUEST_TIMEOUT = 4007;
    public static final int MEDIA_ERROR_NETWORK_HTTP_SERVICE_UNAVAILABLE = 4013;
    public static final int MEDIA_ERROR_NETWORK_HTTP_UNAUTHORIZED = 4005;
    public static final int MEDIA_ERROR_NETWORK_HTTP_VERSION_NOT_SUPPORTED = 4015;
    public static final int MEDIA_ERROR_NO_ATERNATE_SUPPORTED = 1009;
    public static final int MEDIA_ERROR_SECURITY = 13000;
    public static final int MEDIA_ERROR_SECURITY_CASTING = 13002;
    public static final int MEDIA_ERROR_SECURITY_RECORDING = 13001;
    public static final int MEDIA_ERROR_UNKNOWN = 1;
    public static final int MEDIA_INFO_AD_SEEK_FORBIDDEN = 8007;
    public static final int MEDIA_INFO_AD_SWITCH_POINT_DETECTED = 11004;
    public static final int MEDIA_INFO_AD_SWITCH_POINT_TRIGGERED = 11005;
    public static final int MEDIA_INFO_AES_KEYS_NOT_VALID = 1011;
    public static final int MEDIA_INFO_ALTERNATE_CHANGE = 1002;
    public static final int MEDIA_INFO_BAD_INTERLEAVING = 800;
    public static final int MEDIA_INFO_BENCHMARK = 6000;
    public static final int MEDIA_INFO_BENCHMARK_DONE = 6002;
    public static final int MEDIA_INFO_BENCHMARK_FAIL = 6003;
    public static final int MEDIA_INFO_BENCHMARK_STARTED = 6001;
    public static final int MEDIA_INFO_CHROMECAST = 19000;
    public static final int MEDIA_INFO_CHROMECAST_ERROR_PLAYBACK = 19001;
    public static final int MEDIA_INFO_CONTENT_INSERTION = 10000;
    public static final int MEDIA_INFO_CURRENT_BITRATE = 1001;
    public static final int MEDIA_INFO_DASH_EVENT = 1018;
    public static final int MEDIA_INFO_DRM = 3000;
    public static final int MEDIA_INFO_DRM_PERSONALIZATION_BEGIN = 3004;
    public static final int MEDIA_INFO_DRM_PERSONALIZATION_END = 3005;
    public static final int MEDIA_INFO_DRM_RIGHTS_ACQUISITION_BEGIN = 3001;
    public static final int MEDIA_INFO_DRM_RIGHTS_ACQUISITION_END = 3002;
    public static final int MEDIA_INFO_DRM_SESSION_LOCKED = 3007;
    public static final int MEDIA_INFO_DRM_SESSION_UNLOCKED = 3008;
    public static final int MEDIA_INFO_DRM_SESSION_WILL_EXPIRE = 3009;
    public static final int MEDIA_INFO_EVENT_PLAYLIST_START = 1004;
    public static final int MEDIA_INFO_EVENT_PLAYLIST_STOP = 1005;
    public static final int MEDIA_INFO_FIRST_FRAME_DISPLAYED = 8003;
    public static final int MEDIA_INFO_FRAGMENT_TYPE_AUDIO = 1;
    public static final int MEDIA_INFO_FRAGMENT_TYPE_DATA = 3;
    public static final int MEDIA_INFO_FRAGMENT_TYPE_TEXT = 2;
    public static final int MEDIA_INFO_FRAGMENT_TYPE_UNKNOWN = -1;
    public static final int MEDIA_INFO_FRAGMENT_TYPE_VIDEO = 0;
    public static final int MEDIA_INFO_GENERIC = 8000;
    public static final int MEDIA_INFO_GENERIC_HACKING_DETECTED = 8001;
    public static final int MEDIA_INFO_HD_DECODING_NOT_SUPPORTED = 1008;
    public static final int MEDIA_INFO_HTTP_STREAMING = 1000;
    public static final int MEDIA_INFO_METADATA_UPDATE = 802;
    public static final int MEDIA_INFO_NETWORK = 4000;
    public static final int MEDIA_INFO_NETWORK_DOWN = 4001;
    public static final int MEDIA_INFO_NETWORK_NO_FILE = 4002;
    public static final int MEDIA_INFO_NETWORK_UP = 4003;
    public static final int MEDIA_INFO_NEW_CHUNK_DOWNLOADED = 1100;
    public static final int MEDIA_INFO_NEW_DRM_HEADER_DETECTED = 1400;
    public static final int MEDIA_INFO_NEW_HEADER_AVAILABLE = 1200;
    public static final int MEDIA_INFO_NEW_METADATA_ID3 = 1007;
    public static final int MEDIA_INFO_NEW_SUBTITLE_TRACK = 2003;
    public static final int MEDIA_INFO_NOT_SEEKABLE = 801;
    public static final int MEDIA_INFO_OPEN_SUBTITLE_FILE_FAILURE = 2006;
    public static final int MEDIA_INFO_PERIOD_SWITCH_CHANGED = 1017;
    public static final int MEDIA_INFO_PERIOD_SWITCH_DETECTED = 1016;
    public static final int MEDIA_INFO_PRIMARY_CONTENT_STARTED = 10001;
    public static final int MEDIA_INFO_SAFETV = 11100;
    public static final int MEDIA_INFO_SAFETV_PRIMARY_CONTENT_AVAILABLE = 11101;
    public static final int MEDIA_INFO_SAFETV_SECONDARY_CONTENT_STARTED = 11102;
    public static final int MEDIA_INFO_SCRUBBING = 17000;
    public static final int MEDIA_INFO_SCRUBBING_AVAILABLE = 17001;
    public static final int MEDIA_INFO_SCRUBBING_COMPLETE = 17002;
    public static final int MEDIA_INFO_SCRUBBING_FOUND = 17004;
    public static final int MEDIA_INFO_SCRUBBING_LEGACY_AVAILABLE = 8004;
    public static final int MEDIA_INFO_SCRUBBING_NOT_AVAILABLE = 17003;
    public static final int MEDIA_INFO_SECONDARY_CONTENT_FINISHED = 10003;
    public static final int MEDIA_INFO_SECONDARY_CONTENT_STARTED = 10002;
    public static final int MEDIA_INFO_SEEKABLE_RANGE_CHANGED = 1013;
    public static final int MEDIA_INFO_START_REBUFFERING = 1019;
    public static final int MEDIA_INFO_SUBTITLE = 2000;
    public static final int MEDIA_INFO_SUBTITLE_BEGIN = 2100;
    public static final int MEDIA_INFO_SUBTITLE_END = 2200;
    public static final int MEDIA_INFO_UNKNOWN = 1;
    public static final int MEDIA_INFO_VAST = 11000;
    public static final int MEDIA_INFO_VAST_ADS_END = 11017;
    public static final int MEDIA_INFO_VAST_ADS_PRELOADED = 11013;
    public static final int MEDIA_INFO_VAST_ADS_START = 11016;
    public static final int MEDIA_INFO_VAST_ADVERTISEMENT = 11001;
    protected static final int MEDIA_INFO_VAST_CSAI_VMAP_USED = 11011;
    public static final int MEDIA_INFO_VAST_NON_LINEAR_BEGIN = 11007;
    public static final int MEDIA_INFO_VAST_NON_LINEAR_END = 11008;
    public static final int MEDIA_INFO_VAST_NOT_ADVERTISEMENT = 11002;
    public static final int MEDIA_INFO_VAST_SERVER_COOKIES = 11006;
    public static final int MEDIA_INFO_VAST_SKIPPABLE_IN_SEC = 11003;
    protected static final int MEDIA_INFO_VAST_SSAI_VMAP_URL = 11010;
    public static final int MEDIA_INFO_VAST_TUNNEL_END = 11018;
    public static final int MEDIA_INFO_VAST_TUNNEL_PRELOADED = 11014;
    public static final int MEDIA_INFO_VAST_TUNNEL_START = 11015;
    public static final int MEDIA_INFO_VIDEOEXPERIENCEHD = 7000;
    public static final int MEDIA_INFO_VIDEOEXPERIENCEHD_DISABLE = 7002;
    public static final int MEDIA_INFO_VIDEOEXPERIENCEHD_ENABLE = 7001;
    public static final int MEDIA_INFO_VIDEO_TRACK_LAGGING = 700;
    public static final int MEDIA_INFO_WAO = 20000;
    public static final int MEDIA_INFO_WATERMARKING = 21000;
    public static final int MEDIA_INFO_WATERMARKING_PROVISIONNING_URL_NEEDED = 21001;
    public static final int MEDIA_PLAYBACK_READY = 8010;
    public static final int MEDIA_PLAYBACK_SPEED_CHANGED = 1300;
    public static final int MEDIA_PLAYER_STATE_CHANGE = 8008;
    public static final int MEDIA_SEEK_POSITION_READY = 8009;
    static Boolean R1 = null;
    private static boolean S1 = false;
    public static final int SEEKTO_CHUNK_ACCURATE = 0;
    public static final int SEEKTO_IFRAME_ACCURATE = 1;
    public static final int SEEKTO_THUMBNAIL_ACCURATE = 2;
    private static String T1 = null;
    private static String U1 = null;
    private static String V1 = null;
    public static final int VIDEO_EXPERIENCE_HD = 1;
    public static final int VIDEO_EXPERIENCE_OFF = 0;
    public static final int VIDEO_EXPERIENCE_SIDE_BY_SIDE = 2;
    private static String W1;
    private static String X1;
    private static final String[] Y1;
    private static final String[] Z1;
    private int A;
    private com.viaccessorca.drm.impl.d A0;
    private int B;
    private com.viaccessorca.drm.impl.e B0;
    private int C;
    private com.viaccessorca.drm.impl.f C0;
    private int D;
    private com.viaccessorca.drm.impl.m D0;
    private PreferredVideoCodecType E;
    private com.viaccessorca.drm.impl.o E0;
    private String F;
    private VerimatrixAgent F0;
    private boolean G;
    private int G0;
    private boolean H;
    private boolean H0;
    private boolean I;
    private p I0;
    private int J;
    private e J0;
    private int K;
    private boolean K0;
    private boolean L;
    private h L0;
    private i M;
    private boolean M0;
    private boolean N;
    private int N0;
    private String O;
    private boolean O0;
    private m P;
    private String P0;
    private boolean Q;
    private boolean Q0;
    private boolean R;
    private String R0;
    private VOSubtitleFile[] S;
    private int S0;
    private boolean T;
    private int T0;
    private VOFingerprintData U;
    private String U0;
    private String V;
    private String V0;
    private long W;
    private String W0;
    private int X;
    private String X0;
    private int Y;
    private String Y0;
    VOAlternateInfo[] Z;
    private String Z0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f73a;
    private int a0;
    private String a1;
    private int b;
    private boolean b0;
    private VOAdBannerManager b1;
    private int c;
    private boolean c0;
    private Object c1;
    private int d;
    private boolean d0;
    private String d1;
    private VOSurfaceView e;
    private boolean e0;
    private String e1;
    private VOTIFVideoOverlayView f;
    private WifiInfo f0;
    private boolean f1;
    private VOSurfaceView g;
    private int g0;
    private boolean g1;
    private boolean h;
    private String h0;
    private AdRequestOptions h1;
    private boolean i;
    private VOVRManager i0;
    private boolean i1;
    protected f j;
    private VOSafeTVManager j0;
    com.viaccessorca.voplayer.e j1;
    private o k;
    private double k0;
    private Handler k1;
    private long l;
    private final Object l0;
    private g l1;
    private PowerManager.WakeLock m;
    private final Object m0;
    private boolean m1;
    private Object m329t7p6390828478403;
    private Context m329t7p639082847841;
    private String m329t7p6390828478415;
    private long m329t7p639082847842;
    private VOSurfaceViewOpenGL m329t7p639082847843;
    private Surface m329t7p639082847844;
    private long m329t7p639082847845;
    private long m329t7p639082847846;
    private long m329t7p639082847847;
    private String m329t7p639082847848;
    private String m329t7p639082847849;
    private boolean n;
    private final Object n0;
    private boolean n1;
    private boolean o;
    private final Object o0;
    private VOLifecycleObserver o1;
    private float p;
    private boolean p0;
    private Lifecycle p1;
    private VOSystemInfo q;
    private boolean q0;
    private boolean q1;
    private int r;
    private boolean r0;
    private CopyOnWriteArraySet<OnPreparedListener> r1;
    private boolean s;
    private MediaCrypto s0;
    private CopyOnWriteArraySet<OnCompletionListener> s1;
    private int t;
    private MediaCrypto t0;
    private CopyOnWriteArraySet<OnBufferingUpdateListener> t1;
    private boolean u;
    private String u0;
    private CopyOnWriteArraySet<OnDownloadUpdateListener> u1;
    private boolean v;
    private String[] v0;
    private CopyOnWriteArraySet<OnSeekCompleteListener> v1;
    private int w;
    private String[] w0;
    private CopyOnWriteArraySet<OnVideoSizeChangedListener> w1;
    private boolean x;
    private DrmAgent.EDrmType x0;
    private CopyOnWriteArraySet<OnErrorListener> x1;
    private int y;
    private String y0;
    private CopyOnWriteArraySet<OnInformationListener> y1;
    private int z;
    private EDrmEngineType z0;
    private boolean z1;
    private static Vector<VOPlayer> B1 = new Vector<>();
    private static AudioManager C1 = null;
    private static String D1 = null;
    private static boolean E1 = false;
    private static DRMAgentVersion F1 = DRMAgentVersion.UNKNOWN;
    private static boolean G1 = false;
    private static boolean H1 = false;
    private static String m329t7p6390828478410 = null;
    private static String m329t7p6390828478411 = null;
    private static int m329t7p6390828478412 = -1;
    private static boolean m329t7p6390828478413 = false;
    private static boolean m329t7p6390828478414 = false;
    private static String mKeepFieldClearSDKFaasUrlStatic = null;
    private static int K1 = 2;
    private static int L1 = 3;
    private static int M1 = 5;
    private static int N1 = 6;
    private static int O1 = 7;
    private static int P1 = 8;
    private static int Q1 = 10;
    private static int A1 = 0;

    public static class AbrControls {
        public static final int ABR_CONTROLS_MAX_BUFFER_LEVEL_MAX = 300;
        public static final int ABR_CONTROLS_MAX_BUFFER_LEVEL_MIN = 20;
        public static final int ABR_CONTROL_LIVE_DISCONTINUITY_POLICY_EDGE = 1;
        public static final int ABR_CONTROL_LIVE_DISCONTINUITY_POLICY_NONE = 0;
        public static final int ABR_CONTROL_LIVE_DISCONTINUITY_POLICY_QUEUE = 2;
        public int m_iMaxBufferValue = -1;
        public int m_iLatencyValue = -1;
        public boolean m_bDisableHlsv4AudioOnly = false;
        public boolean m_bUseHlsAverageBandwidth = false;
        public int m_uiLiveTimelineDiscontinuityPolicy = 0;
    }

    public static class AdBannerParameters {
        public boolean visibility = false;
        public boolean closable = false;
        public int duration = -1;
        public String requestURL = null;
        OnAdBannerEventListener eventListener = null;

        public enum AdBannerEventType {
            AD_BANNER_EVENT_CHANGED,
            AD_BANNER_EVENT_CLICKED,
            AD_BANNER_EVENT_REPORTED,
            AD_BANNER_EVENT_DIMENSIONS_CHANGED
        }

        public interface OnAdBannerEventListener {
            void onAdBannerEvent(AdBannerEventType adBannerEventType, Object obj);
        }

        public void removeOnAdBannerEventListener(OnAdBannerEventListener onAdBannerEventListener) {
            this.eventListener = null;
        }

        public void setOnAdBannerEventListener(OnAdBannerEventListener onAdBannerEventListener) {
            this.eventListener = onAdBannerEventListener;
        }
    }

    public static class AdInformation {
        public int adId;
        public boolean closable;
        public int expandedHeight;
        public int expandedWidth;
        public int height;
        public boolean maintainAspectRatio;
        public String mediaUri;
        public String mimeType;
        public boolean scalable;
        public int width;

        public AdInformation(String str, String str2, int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, boolean z3) {
            this.adId = i;
            this.mimeType = str;
            this.mediaUri = str2;
            this.width = i2;
            this.height = i3;
            this.expandedWidth = i4;
            this.expandedHeight = i5;
            this.maintainAspectRatio = z;
            this.scalable = z2;
            this.closable = z3;
        }
    }

    public static class AdRequestOptions {
        public boolean nonLinearClosable = false;
        public int nonLinearDurationOverride = -1;
        public boolean vmapAutoRefresh = false;
        public int vmapAutoRefreshPeriod = -1;
        public int vmapSkipOffsetOverrideStatus = -1;
        public String vmapSkipOffsetOverrideValue = null;
        public int vmapSeekBackwardPlayUpTo = 0;
        public int vmapSeekForwardPlayUpTo = -1;
        public boolean vastReportLastProgressEvent = false;
        public boolean vastDefineUrlOnly = false;
        public boolean waitCallback = false;
        public boolean disableTunnelIfAdUnavailable = false;
        public boolean disableTunnelIfAdRedirect = false;
    }

    public enum AdRequestType {
        AD_REQUEST_VAST_URI,
        AD_REQUEST_VAST_BUFFER,
        AD_REQUEST_VMAP_URI,
        AD_REQUEST_VMAP_BUFFER,
        AD_REQUEST_MAST_URI,
        AD_REQUEST_MAST_BUFFER
    }

    public static class AdTunnelInformation {
        public int contentId;
        public int duration;
        public String mimeType;
        public int numberAds;
        public int numberVideoAds;
        public int startPosition;
        public int totalDuration;
        public String tunnelType;

        public AdTunnelInformation(String str, int i, int i2, int i3, int i4, int i5, String str2, int i6) {
            this.tunnelType = str;
            this.totalDuration = i;
            this.numberAds = i2;
            this.numberVideoAds = i3;
            this.startPosition = i4;
            this.contentId = i5;
            this.mimeType = str2;
            this.duration = i6;
        }
    }

    public enum AudioPresenter {
        OPENSL,
        AUDIOTRACK
    }

    public enum BandwidthThreshold {
        SCRUBBING_BANDWIDTH_LOW,
        SCRUBBING_BANDWIDTH_MEDIUM,
        SCRUBBING_BANDWIDTH_HIGH
    }

    public static class CustomMode {
        public static final String ADAPTIVE_BITRATE_SENSITIVITY = "AdaptiveBitrateSensitivity";
        public static final String AD_BANNER_MODE = "AdBanner";
        public static final int ALTERNATE = 1;
        public static final String ALTERNATIVE_MEDIACODEC_MODE = "AlternativeMediaCodecTime";
        public static final int AUTOMATIC = 0;
        public static final String AUTO_PAUSE_IF_HDMI_UNPLUGGED = "AutoPauseIfHdmiUnplugged";
        public static final String CODEC_TYPE = "CodecType";
        public static final String CUSTOM_DNS_SERVERS = "CustomDNSServers";
        public static final int DEFAULT = 0;
        public static final int DISABLED = 2;
        public static final String DISABLE_MAX_PLAYABLE_HEIGHT_MODE = "DisableMaxPlayableHeight";
        public static final String DOLBY_AUDIO = "DolbyAudio";
        public static final int ENABLE = 1;
        public static final String FAAS_FORCE_ASYNCHRONOUS_REQUEST = "FaasForceAsynchronousRequest";
        public static final String FAAS_OFFLINE_MODE = "FaasOfflineMode";
        public static final int FORCED = 1;
        public static final String FORCE_KEEP_LATENCY = "ForceKeepLatency";
        public static final String FORCE_MSS_UPDATE_MANIFEST_MODE = "SmoothStreamingUpdateManifest";
        public static final String FORCE_TEMPORARY_REDIRECT_AS_PERMANENT = "TemporaryRedirectAsPermanent";
        public static final int HIGH = 2;
        public static final int IPV4 = 1;
        public static final int IPV6 = 2;
        public static final String LIVE_LATENCY = "LiveLatency";
        public static final int LOW = 1;
        public static final String LOW_LATENCY_CMAF = "LowLatencyCMAF";
        public static final String MANIFEST_DOWNLOAD_TIMEOUT = "ManifestDownloadTimeout";
        public static final String PREFERRED_IP_RESOLVE_TYPE = "PreferredIPResolveType";
        public static final String SCRUBBING_MODE = "ScrubbingMode";
    }

    public enum DRMAgentVersion {
        UNKNOWN,
        VERSION_1,
        VERSION_2
    }

    public enum DebugLevel {
        DBG_LVL_NO,
        DBG_LVL_ERROR,
        DBG_LVL_WARNING,
        DBG_LVL_INFO,
        DBG_LVL_ENTRY,
        DBG_LVL_DEV
    }

    public enum DebugModule {
        DBG_MODULE_LVMC_AUDIO,
        DBG_MODULE_LVMC_JNI,
        DBG_MODULE_LVMC_VIDEO,
        DBG_MODULE_NET_ENG,
        DBG_MODULE_NET_ENG_AUCB,
        DBG_MODULE_VIDEO_SINK,
        DBG_MODULE_VPS,
        DBG_MODULE_VPS_API,
        DBG_MODULE_VPS_SUB,
        DBG_MODULE_VPSIL,
        DBG_MODULE_VPSIL_CB,
        DBG_MODULE_VPSIL_OTHERS,
        DBG_MODULE_VPSIL_UTILS,
        DBG_MODULE_VIDEO_DECODER,
        DBG_MODULE_VIDEO_PRESENTER,
        DBG_MODULE_VIDEO_RENDERER,
        DBG_MODULE_DRM,
        DBG_MODULE_LVVTB_VIDEO,
        DBG_MODULE_AUDIO_PRESENTER,
        DBG_MODULE_QPPROXY,
        DBG_MODULE_AUDIO_SERVICE,
        DBG_MODULE_PLAYER_GENERIC,
        DBG_MODULE_OFFLINE_VIEWING,
        DBG_MODULE_SUBTITLE_DECODER,
        DBG_MODULE_SSM,
        DBG_MODULE_VAST,
        DBG_MODULE_VMAP,
        DBG_MODULE_DRM_AGENT,
        DBG_MODULE_PGDL_ENG,
        DBG_MODULE_HTTP_STACK,
        DBG_MODULE_M4TOOL_URI,
        DBG_MODULE_AUDIO_DECODER,
        DBG_MODULE_MSH,
        DBG_MODULE_VMX,
        DBG_MODULE_DEPACK,
        DBG_MODULE_DEMUX,
        DBG_MODULE_SUBTITLE_READER,
        DBG_MODULE_HTTPSTR_READER,
        DBG_MODULE_HSDM,
        DBG_MODULE_M4TOOL_CIRCBUF,
        DBG_MODULE_3GP_READER,
        DBG_MODULE_AUTHENTEC,
        DBG_MODULE_WAO,
        DBG_MODULE_MULTIVIEW_CONTROLLER,
        DBG_MODULE_IPTV_STACK,
        DBG_MODULE_NET_ENG_CACHE,
        DBG_MODULE_CRASH_REPORT,
        DBG_MODULE_PLAYBACK_SESSION_REPORT,
        DBG_MODULE_REALTIME_EVENT_REPORT,
        DBG_MODULE_BITSTREAM_PARSER
    }

    public enum DebugOption {
        DBG_OPT_FORCE_MIN_INFO,
        DBG_OPT_OUTPUT_TO_FILE,
        DBG_OPT_SHOW_PLAYER_INFO,
        DBG_OPT_DISABLE_PROGUARD_CHECK,
        DBG_OPT_FORCE_VOPLAYERAPP_REDIRECTION
    }

    public enum EDrmEngineType {
        DefaultEngine,
        Embedded,
        MediaDrm
    }

    public enum EDrmType {
        PLAYREADY,
        WIDEVINE,
        VODRM,
        FAIRPLAY,
        VERIMATRIX
    }

    public enum EUniqueIdentifierType {
        VOPLAYER_ID,
        LEGACY_CSP_ID,
        LEGACY_QUICKPLAYER_ID,
        DEFAULT_ID,
        VOPLAYER_EXTENDED_ID,
        VOPLAYER_LONG_ID
    }

    public enum HttpHeaderFileType {
        VOMEDIA_HTTP_HEADER_FILE_TYPE_NONE,
        VOMEDIA_INFO_HEADER_FILE_TYPE_PLAYLIST,
        VOMEDIA_INFO_HEADER_FILE_TYPE_FRAGMENT,
        VOMEDIA_INFO_HEADER_FILE_TYPE_ALL
    }

    public class IPTVStatistics {
        public int nbRTPDiscontinuity;
        public int nbRTPPacketLost;
        public int nbRTPPacketReceived;

        public IPTVStatistics(VOPlayer vOPlayer) {
            this.nbRTPPacketReceived = 0;
            this.nbRTPPacketLost = 0;
            this.nbRTPDiscontinuity = 0;
            this.nbRTPPacketReceived = 0;
            this.nbRTPPacketLost = 0;
            this.nbRTPDiscontinuity = 0;
        }

        public int getAvaragePacketLoss() {
            int i = this.nbRTPDiscontinuity;
            if (i > 0) {
                return this.nbRTPPacketLost / i;
            }
            return 0;
        }

        public float getLossPurcentage() {
            int i = this.nbRTPPacketReceived;
            if (i > 0) {
                return (this.nbRTPPacketLost * 100.0f) / i;
            }
            return -1.0f;
        }

        public void setup(int i, int i2, int i3) {
            this.nbRTPPacketReceived = i;
            this.nbRTPPacketLost = i2;
            this.nbRTPDiscontinuity = i3;
        }
    }

    public interface OnBufferingUpdateListener {
        void onBufferingUpdate(VOPlayer vOPlayer, int i);
    }

    public interface OnCompletionListener {
        void onCompletion(VOPlayer vOPlayer);
    }

    public interface OnDownloadUpdateListener {
        void onDownloadUpdate(VOPlayer vOPlayer, int i);
    }

    public interface OnErrorListener {
        boolean onError(VOPlayer vOPlayer, int i, int i2);
    }

    public interface OnInformationListener {
        boolean onInformation(VOPlayer vOPlayer, int i, int i2, Object obj);
    }

    public interface OnPreparedListener {
        void onPrepared(VOPlayer vOPlayer);
    }

    public interface OnSeekCompleteListener {
        void onSeekComplete(VOPlayer vOPlayer);
    }

    public interface OnVideoSizeChangedListener {
        void onVideoSizeChanged(VOPlayer vOPlayer, int i, int i2);
    }

    public enum PlayerState {
        IDLE,
        INITIALIZED,
        PREPARING,
        PREPARED,
        PLAYING,
        PLAYBACK_COMPLETE,
        PAUSED,
        ERROR,
        RELEASED,
        CASTING
    }

    public enum PreferredVideoCodecType {
        VOPREFERRED_VIDEO_CODEC_TYPE_DEFAULT,
        VOPREFERRED_VIDEO_CODEC_TYPE_MPEG2,
        VOPREFERRED_VIDEO_CODEC_TYPE_AVC,
        VOPREFERRED_VIDEO_CODEC_TYPE_HEVC,
        VOPREFERRED_VIDEO_CODEC_TYPE_ALL
    }

    public enum ProxyType {
        PROXY_TYPE_NONE,
        PROXY_TYPE_HTTP,
        PROXY_TYPE_SOCKS_V4,
        PROXY_TYPE_SOCKS_V5,
        PROXY_TYPE_HTTPS
    }

    public static class ScrubbingConfiguration {
        public HashMap<BandwidthThreshold, Integer> bandwidthThreshold;
        public boolean isRedirectAsPermanent;
        public long liveStartPosition;
        public HashMap<ThreadPriority, Integer> threadPriority;
    }

    public static class ScrubbingSpriteInfo {
        public int mInterval;
        public int mSpriteColums;
        public int mSpriteLines;
    }

    public static class StateInfo {
        public PlayerState newState;
        public PlayerState oldState;

        public StateInfo(int i, int i2) {
            PlayerState playerState = PlayerState.IDLE;
            this.oldState = playerState;
            this.newState = playerState;
            this.oldState = PlayerState.values()[i];
            this.newState = PlayerState.values()[i2];
        }

        public StateInfo(PlayerState playerState, PlayerState playerState2) {
            PlayerState playerState3 = PlayerState.IDLE;
            this.oldState = playerState3;
            this.newState = playerState3;
            this.oldState = playerState;
            this.newState = playerState2;
        }
    }

    public enum ThreadPriority {
        SCRUBBING_THREAD_LOW,
        SCRUBBING_THREAD_MEDIUM_LOW,
        SCRUBBING_THREAD_MEDIUM,
        SCRUBBING_THREAD_HIGH
    }

    public static class ThumbnailContext {
        public long duration;
        public int nbThumbnailTracks;
        public int startNumber;
        public ThumbnailTrackInfo[] thumbnailTrackInfo;

        public ThumbnailContext(ThumbnailTrackInfo[] thumbnailTrackInfoArr, long j, int i, int i2) {
            this.thumbnailTrackInfo = thumbnailTrackInfoArr;
            this.duration = j;
            this.startNumber = i;
            this.nbThumbnailTracks = i2;
        }
    }

    public static class ThumbnailTrackInfo {
        public double frameRate;
        public int height;
        public String name;
        public String thumbnailURL;
        public String value;
        public int width;

        public ThumbnailTrackInfo(String str, String str2, String str3, int i, int i2, double d) {
            this.thumbnailURL = str;
            this.value = str2;
            this.name = str3;
            this.height = i;
            this.width = i2;
            this.frameRate = d;
        }
    }

    private class VOLifecycleObserver implements LifecycleEventObserver {
        private VOLifecycleObserver() {
        }

        /* synthetic */ VOLifecycleObserver(VOPlayer vOPlayer, a aVar) {
            this();
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            VOPlayer vOPlayer;
            boolean z;
            if (Lifecycle.Event.ON_STOP == event) {
                vOPlayer = VOPlayer.this;
                z = true;
            } else {
                if (Lifecycle.Event.ON_START != event) {
                    return;
                }
                vOPlayer = VOPlayer.this;
                z = false;
            }
            vOPlayer.c(z);
        }
    }

    public enum VOMediaType {
        VOMediaTypeUnkwown,
        VOMediaTypeVideo,
        VOMediaTypeAudio
    }

    public static class VOMultiViewPlayerConfig {
        public int synchroDelay = 0;
    }

    public enum VOPlayerEnabledReports {
        REPORT_NONE,
        REPORT_CRASHES,
        REPORT_ERRORS,
        REPORT_ALL
    }

    public enum VOPlayerQuarterDisplay {
        FULL,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }

    public enum VOPlayerSeverity {
        SEVERITY_INFO,
        SEVERITY_WARNING,
        SEVERITY_ERROR
    }

    public enum VOPlayerType {
        TYPE_FOLLOWER,
        TYPE_LEADER,
        TYPE_UNKNOWN
    }

    public static class VOQuarterInfo {
        public int animationDuration;
        public boolean isAnimationEnable;
        public VOPlayerQuarterDisplay quarter;
    }

    public enum VOVolumeType {
        VOVolumeTypePlayer,
        VOVolumeTypeDevice
    }

    public enum VoContentProtocolType {
        VOCONTENT_UNKNOWN,
        VOCONTENT_HTTP_HLS,
        VOCONTENT_HTTP_DASH,
        VOCONTENT_HTTP_SMOOTHSTREAMING,
        VOCONTENT_IPTV_RTP
    }

    class a implements VOSurfaceViewOpenGL.f {
        a() {
        }

        @Override // com.viaccessorca.voplayer.VOSurfaceViewOpenGL.f
        public void onRendererDeleted() {
            try {
                if (VOPlayer.this.H) {
                    return;
                }
                VOPlayer.this.NzQxOUJFMDY1OTQ4NDU3NUJFNjMwNzlGRTA2Qjk0QjI();
            } catch (Exception unused) {
            }
        }
    }

    class b implements Comparator<VOPerformancePoint> {
        b(VOPlayer vOPlayer) {
        }

        @Override // java.util.Comparator
        public int compare(VOPerformancePoint vOPerformancePoint, VOPerformancePoint vOPerformancePoint2) {
            if (vOPerformancePoint == null || vOPerformancePoint2 == null) {
                throw null;
            }
            return ((((vOPerformancePoint.getWidth() + 15) / 16) * ((vOPerformancePoint.getHeight() + 15) / 16)) * vOPerformancePoint.getFrameRate()) - ((((vOPerformancePoint2.getWidth() + 15) / 16) * ((vOPerformancePoint2.getHeight() + 15) / 16)) * vOPerformancePoint2.getFrameRate());
        }
    }

    static /* synthetic */ class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f76a;
        static final /* synthetic */ int[] b;
        static final /* synthetic */ int[] c;
        static final /* synthetic */ int[] d;
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[EDrmType.values().length];
            e = iArr;
            try {
                iArr[EDrmType.VERIMATRIX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[EDrmType.WIDEVINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[EDrmType.VODRM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                e[EDrmType.PLAYREADY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[VOMediaType.values().length];
            d = iArr2;
            try {
                iArr2[VOMediaType.VOMediaTypeUnkwown.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                d[VOMediaType.VOMediaTypeAudio.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                d[VOMediaType.VOMediaTypeVideo.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr3 = new int[DrmAgent.EDrmType.values().length];
            c = iArr3;
            try {
                iArr3[DrmAgent.EDrmType.DRM_TYPE_PLAYREADY.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                c[DrmAgent.EDrmType.DRM_TYPE_UNKNOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                c[DrmAgent.EDrmType.DRM_TYPE_WIDEVINE.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                c[DrmAgent.EDrmType.DRM_TYPE_VODRM.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                c[DrmAgent.EDrmType.DRM_TYPE_VERIMATRIX.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
            int[] iArr4 = new int[EDrmEngineType.values().length];
            b = iArr4;
            try {
                iArr4[EDrmEngineType.MediaDrm.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                b[EDrmEngineType.DefaultEngine.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                b[EDrmEngineType.Embedded.ordinal()] = 3;
            } catch (NoSuchFieldError unused15) {
            }
            int[] iArr5 = new int[VOStatusCode.values().length];
            f76a = iArr5;
            try {
                iArr5[VOStatusCode.ERROR_BAD_ARGUMENTS.ordinal()] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f76a[VOStatusCode.ERROR_DRM_CONTENT_NOT_RECOGNIZED.ordinal()] = 2;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f76a[VOStatusCode.ERROR_CONTENT_TYPE_NOT_SUPPORTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f76a[VOStatusCode.ERROR_FILE_ACCESS.ordinal()] = 4;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f76a[VOStatusCode.ERROR_INVALID_FORMAT.ordinal()] = 5;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f76a[VOStatusCode.ERROR_SECURITY_MISSING_PERMISSION.ordinal()] = 6;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f76a[VOStatusCode.ERROR_SECURITY_OS_TAMPERED.ordinal()] = 7;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f76a[VOStatusCode.ERROR_NETWORK_COMMUNICATION_FAILURE.ordinal()] = 8;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f76a[VOStatusCode.ERROR_NETWORK_SERVER_NOT_REACHABLE.ordinal()] = 9;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                f76a[VOStatusCode.ERROR_NETWORK_NO_INTERNET_CONNECTION.ordinal()] = 10;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                f76a[VOStatusCode.ERROR_DRM_TRUSTED_TIME_NOT_SET.ordinal()] = 11;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                f76a[VOStatusCode.ERROR_NOT_SUPPORTED.ordinal()] = 12;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                f76a[VOStatusCode.ERROR_NOT_INITIALIZED.ordinal()] = 13;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                f76a[VOStatusCode.ERROR_DRM_PROVISIONING_NOT_SET.ordinal()] = 14;
            } catch (NoSuchFieldError unused29) {
            }
        }
    }

    private enum d {
        WMK_SURFACE_CHECKED,
        ENABLE_WMK_FOR_CLEAR_CONTENT
    }

    private class e extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f78a = false;

        public e() {
        }

        private synchronized boolean a() {
            return this.f78a;
        }

        private boolean b() {
            if (a()) {
                return false;
            }
            ByteBuffer byteBufferF = VOPlayer.this.F();
            if (byteBufferF == null) {
                byteBufferF = VOPlayer.this.D();
            }
            if (byteBufferF == null) {
                return false;
            }
            byte[] bArr = new byte[byteBufferF.remaining()];
            byteBufferF.get(bArr);
            if (VOPlayer.this.w0 == null) {
                return false;
            }
            boolean z = false;
            for (int i = 0; i < VOPlayer.this.w0.length; i++) {
                String strReplace = VOPlayer.this.w0[i];
                if (strReplace.contains("${KEYID}")) {
                    strReplace = strReplace.replace("${KEYID}", VOUtils.bytesToUUID(bArr).toString().toUpperCase());
                    z = true;
                }
                int iLastIndexOf = strReplace.lastIndexOf("}#b64");
                while (iLastIndexOf > 0) {
                    int iLastIndexOf2 = strReplace.lastIndexOf("#b64{", iLastIndexOf);
                    if (iLastIndexOf2 <= 0) {
                        break;
                    }
                    int i2 = iLastIndexOf2 + 5;
                    strReplace = strReplace.replace(strReplace.substring(i2 - 5, iLastIndexOf + 5), new String(Base64.encode(strReplace.substring(i2, iLastIndexOf).getBytes(), 2)));
                    iLastIndexOf = strReplace.lastIndexOf("}#b64");
                    z = true;
                }
                if (z) {
                    VOPlayer.this.w0[i] = strReplace;
                }
            }
            return z;
        }

        public synchronized void cancel() {
            this.f78a = true;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ByteBuffer byteBufferAllocateDirect;
            ByteBuffer byteBufferAllocateDirect2;
            ByteBuffer byteBufferAllocateDirect3;
            ByteBuffer byteBufferAllocateDirect4;
            if (a()) {
                return;
            }
            VOBaseMediaDrmAgent currentMediaDrmAgent = VOPlayer.this.getCurrentMediaDrmAgent();
            if (a()) {
                return;
            }
            if (b()) {
                if (VOPlayer.this.c1 != null) {
                    ((com.viaccessorca.voplayer.b) VOPlayer.this.c1).b(VOPlayer.this.w0);
                }
                if (currentMediaDrmAgent != null) {
                    currentMediaDrmAgent.setLicenseAcquisitionCustomHeaders(VOPlayer.this.w0);
                }
            }
            int iARjY0MjlBNEREMzlFM0Y2QTk3QjgMDA5MkY1NEFCNTQ = VOPlayer.this.ARjY0MjlBNEREMzlFM0Y2QTk3QjgMDA5MkY1NEFCNTQ();
            for (int i = 0; i < iARjY0MjlBNEREMzlFM0Y2QTk3QjgMDA5MkY1NEFCNTQ; i++) {
                com.viaccessorca.voplayer.a aVar = new com.viaccessorca.voplayer.a();
                int iARjY0MjlBNEREMzlFM05MkY1NEFCNTQY2QTk3QjgMDA = VOPlayer.this.ARjY0MjlBNEREMzlFM05MkY1NEFCNTQY2QTk3QjgMDA(i);
                if (iARjY0MjlBNEREMzlFM05MkY1NEFCNTQY2QTk3QjgMDA > 0 && (byteBufferAllocateDirect4 = ByteBuffer.allocateDirect(iARjY0MjlBNEREMzlFM05MkY1NEFCNTQY2QTk3QjgMDA)) != null) {
                    VOPlayer.this.AEREMzlFM05MkY1RjY0MjlBNNEFCNTQY2QTk3QjgMDA(byteBufferAllocateDirect4, iARjY0MjlBNEREMzlFM05MkY1NEFCNTQY2QTk3QjgMDA, i);
                    aVar.setAudioHeader(byteBufferAllocateDirect4);
                }
                int iAEREMzlFM05MkY3QjgMDA1RjY0MjlBNNEFCNTQY2QTk = VOPlayer.this.AEREMzlFM05MkY3QjgMDA1RjY0MjlBNNEFCNTQY2QTk(i);
                if (iAEREMzlFM05MkY3QjgMDA1RjY0MjlBNNEFCNTQY2QTk > 0 && (byteBufferAllocateDirect3 = ByteBuffer.allocateDirect(iAEREMzlFM05MkY3QjgMDA1RjY0MjlBNNEFCNTQY2QTk)) != null) {
                    VOPlayer.this.JgMDA1RjY0Mjl4EREMzlFM05MkY3QBNNEFCNTQY2QTk(byteBufferAllocateDirect3, iAEREMzlFM05MkY3QjgMDA1RjY0MjlBNNEFCNTQY2QTk, i);
                    aVar.setVideoHeader(byteBufferAllocateDirect3);
                }
                int iY2QTMDA1RjY0MjkMzlFM05MkY3QBNNEFCNTQjgl4ERE = VOPlayer.this.Y2QTMDA1RjY0MjkMzlFM05MkY3QBNNEFCNTQjgl4ERE(i);
                if (iY2QTMDA1RjY0MjkMzlFM05MkY3QBNNEFCNTQjgl4ERE > 0 && (byteBufferAllocateDirect2 = ByteBuffer.allocateDirect(iY2QTMDA1RjY0MjkMzlFM05MkY3QBNNEFCNTQjgl4ERE)) != null) {
                    VOPlayer.this.MjkMzlFM05MkY3QBNNEFCNTQjY2QTMDA1RjY0gl4ERE(byteBufferAllocateDirect2, iY2QTMDA1RjY0MjkMzlFM05MkY3QBNNEFCNTQjgl4ERE, i);
                    aVar.setAudioKID(byteBufferAllocateDirect2);
                }
                int iJgl4EREMzlFM05MkY3QBNNEFCNTQY2QTMDA1RjY0Mjk = VOPlayer.this.Jgl4EREMzlFM05MkY3QBNNEFCNTQY2QTMDA1RjY0Mjk(i);
                if (iJgl4EREMzlFM05MkY3QBNNEFCNTQY2QTMDA1RjY0Mjk > 0 && (byteBufferAllocateDirect = ByteBuffer.allocateDirect(iJgl4EREMzlFM05MkY3QBNNEFCNTQY2QTMDA1RjY0Mjk)) != null) {
                    VOPlayer.this.MzlFM05MkY3QBNNEFCNTQY2QTMDA1RjY0Mjkjgl4ERE(byteBufferAllocateDirect, iJgl4EREMzlFM05MkY3QBNNEFCNTQY2QTMDA1RjY0Mjk, i);
                    aVar.setVideoKID(byteBufferAllocateDirect);
                }
                if (a()) {
                    return;
                }
                currentMediaDrmAgent.b(aVar);
            }
            if (currentMediaDrmAgent != null) {
                VOPlayer.this.K0 = currentMediaDrmAgent.k() != VOBaseMediaDrmAgent.c.UNKNOWN;
                currentMediaDrmAgent.a(VOPlayer.this.L0);
                try {
                    if (a()) {
                        return;
                    }
                    currentMediaDrmAgent.n();
                } catch (VOException unused) {
                }
            }
        }
    }

    protected class f extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private VOPlayer f79a;

        protected f(VOPlayer vOPlayer, Looper looper, String str) {
            super(looper);
            this.f79a = vOPlayer;
        }

        private synchronized void a() {
            VOBaseMediaDrmAgent currentMediaDrmAgent = VOPlayer.this.getCurrentMediaDrmAgent();
            if (currentMediaDrmAgent != null && !currentMediaDrmAgent.isSupported()) {
                VOPlayer.this.g(3101);
                return;
            }
            VOPlayer.this.L0.setMediaCryptoPending(true);
            if (VOPlayer.this.J0 == null) {
                VOPlayer vOPlayer = VOPlayer.this;
                vOPlayer.J0 = vOPlayer.new e();
            }
            if (VOPlayer.this.J0 != null) {
                VOPlayer.this.J0.start();
            }
            if (VOPlayer.this.I0 == null) {
                VOPlayer vOPlayer2 = VOPlayer.this;
                vOPlayer2.I0 = vOPlayer2.new p(this.f79a);
                VOPlayer.this.I0.start();
            }
        }

        /* JADX WARN: Code duplicated, block: B:105:0x027f  */
        @Override // android.os.Handler
        public void handleMessage(Message message) throws Exception {
            boolean zOnError;
            VOBaseMediaDrmAgent currentMediaDrmAgent;
            int i;
            int i2;
            int i3;
            Object obj;
            VOPlayer vOPlayer;
            int i4;
            int iMTkxRTBBQjcwREQ5NDI4Q0JBRTREMTA5MjFDNTZCMDISize;
            ByteBuffer byteBufferAllocateDirect;
            Message messageObtain = message;
            if (this.f79a.m329t7p639082847842 == 0) {
                return;
            }
            int i5 = messageObtain.what;
            int i6 = 0;
            if (i5 == 100) {
                VOPlayer.this.M = i.CREATED;
                synchronized (VOPlayer.this.l0) {
                    VOPlayer.this.l0.notify();
                }
                VOPlayer.this.a(messageObtain.arg1, messageObtain.arg2);
                VOPlayer.this.c(this.f79a, messageObtain.arg1, messageObtain.arg2);
                if (3100 == messageObtain.arg1 && 3112 == messageObtain.arg2 && EDrmEngineType.MediaDrm == VOPlayer.this.z0 && (currentMediaDrmAgent = VOPlayer.this.getCurrentMediaDrmAgent()) != null) {
                    currentMediaDrmAgent.f();
                }
                if (VOPlayer.this.x1 != null) {
                    Iterator it = VOPlayer.this.x1.iterator();
                    zOnError = false;
                    while (it.hasNext()) {
                        zOnError = ((OnErrorListener) it.next()).onError(this.f79a, messageObtain.arg1, messageObtain.arg2);
                    }
                } else {
                    zOnError = false;
                }
                if (VOPlayer.this.s1 != null && !zOnError) {
                    Iterator it2 = VOPlayer.this.s1.iterator();
                    while (it2.hasNext()) {
                        ((OnCompletionListener) it2.next()).onCompletion(this.f79a);
                    }
                }
                VOPlayer.this.g(false);
                return;
            }
            a aVar = null;
            if (i5 != 200) {
                if (i5 == 14000) {
                    if (14001 == messageObtain.arg1) {
                        VOPlayer.this.n1 = true;
                        return;
                    }
                    return;
                }
                switch (i5) {
                    case 1:
                        VOPlayer.this.M = i.PREPARED;
                        VOPlayer.this.R();
                        VOPlayer vOPlayer2 = VOPlayer.this;
                        vOPlayer2.Z = vOPlayer2.getAlternatesInfo();
                        VOPlayer vOPlayer3 = VOPlayer.this;
                        VOAlternateInfo[] vOAlternateInfoArr = vOPlayer3.Z;
                        if (vOAlternateInfoArr != null) {
                            vOPlayer3.Y = vOAlternateInfoArr.length;
                            VOPlayer.this.X = 1;
                            int httpStreamingAlternateBitrate = VOPlayer.this.getHttpStreamingAlternateBitrate();
                            while (i6 < VOPlayer.this.Y) {
                                if (VOPlayer.this.Z[i6].getBitrate() < httpStreamingAlternateBitrate) {
                                    VOPlayer.g0(VOPlayer.this);
                                }
                                i6++;
                            }
                        }
                        synchronized (VOPlayer.this.l0) {
                            VOPlayer.this.l0.notify();
                            break;
                        }
                        if (VOPlayer.this.r1 != null) {
                            Iterator it3 = VOPlayer.this.r1.iterator();
                            while (it3.hasNext()) {
                                ((OnPreparedListener) it3.next()).onPrepared(this.f79a);
                            }
                        }
                        if (VOPlayer.this.e != null) {
                            try {
                                VOPlayer.this.e.a(VOPlayer.this.b());
                                break;
                            } catch (IllegalArgumentException | NullPointerException unused) {
                            }
                            VOPlayer.this.e.a(VOPlayer.this.w, VOPlayer.this.getVideoWidth(), VOPlayer.this.getVideoHeight());
                            VOPlayer.this.j();
                        }
                        if (VOPlayer.this.f != null) {
                            VOPlayer.this.f.a(VOPlayer.this.w, VOPlayer.this.getVideoWidth(), VOPlayer.this.getVideoHeight());
                            VOPlayer.this.f.setVideoPixelAspectRatio(VOPlayer.this.b());
                        }
                        if (VOPlayer.R1.booleanValue()) {
                            VOPlayer.this.k();
                            return;
                        }
                        return;
                    case 2:
                        VOPlayer.this.M = i.STOPPED;
                        if (VOPlayer.this.s1 != null) {
                            Iterator it4 = VOPlayer.this.s1.iterator();
                            while (it4.hasNext()) {
                                ((OnCompletionListener) it4.next()).onCompletion(this.f79a);
                            }
                        }
                        VOPlayer.this.g(false);
                        return;
                    case 3:
                        if (VOPlayer.this.t1 != null) {
                            Iterator it5 = VOPlayer.this.t1.iterator();
                            while (it5.hasNext()) {
                                ((OnBufferingUpdateListener) it5.next()).onBufferingUpdate(this.f79a, messageObtain.arg1);
                            }
                            return;
                        }
                        return;
                    case 4:
                        VOPlayer.this.K();
                        if (VOPlayer.this.v1 != null) {
                            Iterator it6 = VOPlayer.this.v1.iterator();
                            while (it6.hasNext()) {
                                ((OnSeekCompleteListener) it6.next()).onSeekComplete(this.f79a);
                            }
                            return;
                        }
                        return;
                    case 5:
                        if (VOPlayer.this.w1 != null) {
                            Iterator it7 = VOPlayer.this.w1.iterator();
                            while (it7.hasNext()) {
                                ((OnVideoSizeChangedListener) it7.next()).onVideoSizeChanged(this.f79a, messageObtain.arg1, messageObtain.arg2);
                            }
                        }
                        if (VOPlayer.this.e != null) {
                            VOPlayer.this.e.c(messageObtain.arg1, messageObtain.arg2);
                        }
                        if (VOPlayer.this.f != null) {
                            VOPlayer.this.f.b(messageObtain.arg1, messageObtain.arg2);
                            return;
                        }
                        return;
                    case 6:
                        if (VOPlayer.this.u1 != null) {
                            Iterator it8 = VOPlayer.this.u1.iterator();
                            while (it8.hasNext()) {
                                ((OnDownloadUpdateListener) it8.next()).onDownloadUpdate(this.f79a, messageObtain.arg1);
                            }
                            return;
                        }
                        return;
                    default:
                        switch (i5) {
                            case VOPlayer.MEDIA_INFO_VAST_ADVERTISEMENT /* 11001 */:
                                VOPlayer.this.e();
                                if (i.PREOPENING != VOPlayer.this.M) {
                                    return;
                                }
                                if (16 > Build.VERSION.SDK_INT || VOPlayer.this.e == null) {
                                    i6 = 1;
                                } else {
                                    TextureView textureView = (TextureView) VOPlayer.this.e.j();
                                    if ((VOPlayer.this.P() || textureView == null) && (VOPlayer.this.O() || textureView != null)) {
                                        i6 = 1;
                                    } else {
                                        new k(VOPlayer.this, aVar).start();
                                    }
                                }
                                if (i6 == 0) {
                                    return;
                                }
                                break;
                            case VOPlayer.MEDIA_INFO_VAST_NOT_ADVERTISEMENT /* 11002 */:
                                break;
                            case VOPlayer.MEDIA_INFO_VAST_SKIPPABLE_IN_SEC /* 11003 */:
                                VOPlayer vOPlayer4 = VOPlayer.this;
                                vOPlayer4.QzExOEMzRjhFNkY3NDY3MUE1QzQ0N0E0RDc3OTMxQjk(vOPlayer4.l, VOPlayer.this.k.f86a);
                                return;
                            case VOPlayer.MEDIA_INFO_AD_SWITCH_POINT_DETECTED /* 11004 */:
                                VOPlayer.this.j();
                                return;
                            default:
                                switch (i5) {
                                    case 12000:
                                        Object obj2 = messageObtain.obj;
                                        if (obj2 != null) {
                                            this.f79a.a((VOSurfaceView) obj2, 1);
                                            return;
                                        }
                                        return;
                                    case 12001:
                                        VOPlayer.this.QzUwODQ2RThFM0YwNDVBQTk0RTRDRUY0RTcyNkM5RTM();
                                        if (VOPlayer.this.r0) {
                                            synchronized (VOPlayer.this.m0) {
                                                VOPlayer.this.m0.notify();
                                                break;
                                            }
                                            return;
                                        }
                                        break;
                                    case 12002:
                                        VOPlayer.this.QzUwODQ2RThFM0YwNDVBQTk0RTRDRUY0RTcyNkM5RTM();
                                        if (VOPlayer.this.q0) {
                                            synchronized (VOPlayer.this.n0) {
                                                VOPlayer.this.n0.notify();
                                                break;
                                            }
                                            return;
                                        }
                                        break;
                                    default:
                                        return;
                                }
                                VOPlayer vOPlayer5 = VOPlayer.this;
                                vOPlayer5.a(vOPlayer5.e, 0);
                                return;
                        }
                        VOPlayer.this.f();
                        return;
                }
            }
            int i7 = messageObtain.arg2;
            if (8003 == i7 || 10001 == i7 || 10002 == i7 || 11001 == i7 || 11002 == i7) {
                VOPlayer vOPlayer6 = VOPlayer.this;
                vOPlayer6.t = vOPlayer6.getCodecsType();
                VOPlayer vOPlayer7 = VOPlayer.this;
                vOPlayer7.f(vOPlayer7.t);
                if (8003 == messageObtain.arg2) {
                    VOPlayer.this.s();
                }
            } else {
                int i8 = messageObtain.arg1;
                if (1200 == i8) {
                    if (VOPlayer.this.y1 != null) {
                        for (OnInformationListener onInformationListener : VOPlayer.this.y1) {
                            Object obj3 = messageObtain.obj;
                            if (obj3 != null) {
                                onInformationListener.onInformation(this.f79a, messageObtain.arg1, messageObtain.arg2, obj3);
                            }
                        }
                    }
                } else if (1300 == i8) {
                    VOPlayer.this.k0 = ((double) i7) / 100.0d;
                    if (VOPlayer.this.y1 != null) {
                        for (OnInformationListener onInformationListener2 : VOPlayer.this.y1) {
                            if (messageObtain.obj != null) {
                                onInformationListener2.onInformation(this.f79a, messageObtain.arg1, 0, null);
                            }
                        }
                    }
                } else if (1400 == i8) {
                    if (VOPlayer.this.G0 != 0) {
                        VOPlayer vOPlayer8 = VOPlayer.this;
                        vOPlayer8.g(vOPlayer8.G0);
                        return;
                    }
                    if (EDrmEngineType.MediaDrm != VOPlayer.this.z0) {
                        return;
                    }
                    VOBaseMediaDrmAgent currentMediaDrmAgent2 = VOPlayer.this.getCurrentMediaDrmAgent();
                    if (currentMediaDrmAgent2 != null) {
                        if (!currentMediaDrmAgent2.m()) {
                            return;
                        }
                        com.viaccessorca.voplayer.a aVar2 = new com.viaccessorca.voplayer.a();
                        int i9 = messageObtain.arg2;
                        if (i9 == 0) {
                            aVar2.setVideoHeader(VOPlayer.this.G());
                            aVar2.setVideoKID(VOPlayer.this.F());
                        } else {
                            if (i9 != 1) {
                                return;
                            }
                            aVar2.setAudioHeader(VOPlayer.this.E());
                            aVar2.setAudioKID(VOPlayer.this.D());
                        }
                        if (currentMediaDrmAgent2.b(aVar2)) {
                            try {
                                currentMediaDrmAgent2.n();
                            } catch (VOException unused2) {
                            }
                        }
                    }
                    if (VOPlayer.this.y1 != null) {
                        Iterator it9 = VOPlayer.this.y1.iterator();
                        while (it9.hasNext()) {
                            ((OnInformationListener) it9.next()).onInformation(this.f79a, messageObtain.arg1, messageObtain.arg2, null);
                        }
                    }
                } else if (8008 == i7) {
                    if (VOPlayer.this.y1 != null) {
                        for (OnInformationListener onInformationListener3 : VOPlayer.this.y1) {
                            Object obj4 = messageObtain.obj;
                            if (obj4 != null) {
                                int[] iArr = (int[]) obj4;
                                onInformationListener3.onInformation(this.f79a, messageObtain.arg1, messageObtain.arg2, new StateInfo(iArr[0], iArr[1]));
                                if (0 < VOPlayer.this.W) {
                                    VOPlayer.this.W = System.currentTimeMillis() + 500;
                                    VOPlayer.this.f(true);
                                }
                            }
                        }
                    }
                } else if (11102 == i7) {
                    Object obj5 = messageObtain.obj;
                    i6 = obj5 != null ? ((int[]) obj5)[0] : 0;
                    if (VOPlayer.this.y1 != null) {
                        Iterator it10 = VOPlayer.this.y1.iterator();
                        while (it10.hasNext()) {
                            ((OnInformationListener) it10.next()).onInformation(this.f79a, messageObtain.arg1, messageObtain.arg2, Integer.valueOf(i6));
                        }
                    }
                } else {
                    if (8005 == i7) {
                        VOPlayer.this.M = i.PLAYING;
                        if (VOPlayer.R1.booleanValue()) {
                            VOPlayer.this.k();
                            return;
                        }
                        return;
                    }
                    if (2004 == i7) {
                        if (VOPlayer.this.e != null && (iMTkxRTBBQjcwREQ5NDI4Q0JBRTREMTA5MjFDNTZCMDISize = VOPlayer.this.MTkxRTBBQjcwREQ5NDI4Q0JBRTREMTA5MjFDNTZCMDISize()) > 0 && (byteBufferAllocateDirect = ByteBuffer.allocateDirect(iMTkxRTBBQjcwREQ5NDI4Q0JBRTREMTA5MjFDNTZCMDISize)) != null) {
                            VOPlayer.this.U.a(byteBufferAllocateDirect, iMTkxRTBBQjcwREQ5NDI4Q0JBRTREMTA5MjFDNTZCMDISize);
                            VOPlayer vOPlayer9 = VOPlayer.this;
                            if (vOPlayer9.MTkxRTBBQjcwREQ5NDI4Q0JBRTREMTA5MjFDNTZCMDI(vOPlayer9.U)) {
                                VOPlayer vOPlayer10 = VOPlayer.this;
                                vOPlayer10.a(vOPlayer10.U);
                            }
                        }
                        this.f79a.j.removeMessages(2004);
                        return;
                    }
                    if (2005 == i7) {
                        VOPlayer.this.U.a(null, 0);
                        VOPlayer.this.a((VOFingerprintData) null);
                        this.f79a.j.removeMessages(2005);
                        return;
                    }
                    if (3003 == i7) {
                        VOPlayer.this.H0 = true;
                        if (VOPlayer.this.G0 != 0) {
                            vOPlayer = VOPlayer.this;
                            i4 = vOPlayer.G0;
                        } else if (EDrmEngineType.MediaDrm != VOPlayer.this.z0) {
                            vOPlayer = VOPlayer.this;
                            i4 = VOPlayer.MEDIA_ERROR_DRM_INIT_PARAMS_NOT_SET;
                        } else if (VOUtils.SDK_INT < 18) {
                            vOPlayer = VOPlayer.this;
                            i4 = 3101;
                        } else {
                            a();
                        }
                        vOPlayer.g(i4);
                    } else if (1001 == i7) {
                        VOPlayer.this.MTc2NkVBOTEwNTVGNDgzOEJENDNBRTYwODI0MzRDMzE();
                        if (0 < VOPlayer.this.W && VOPlayer.this.W <= System.currentTimeMillis()) {
                            VOPlayer.this.W = System.currentTimeMillis() + 500;
                            VOPlayer.this.f(true);
                        }
                    } else if (1002 == i7) {
                        VOPlayer.this.X = 1;
                        VOPlayer vOPlayer11 = VOPlayer.this;
                        vOPlayer11.Z = vOPlayer11.getAlternatesInfo();
                        VOPlayer vOPlayer12 = VOPlayer.this;
                        VOAlternateInfo[] vOAlternateInfoArr2 = vOPlayer12.Z;
                        if (vOAlternateInfoArr2 != null) {
                            vOPlayer12.Y = vOAlternateInfoArr2.length;
                            VOPlayer.this.X = 1;
                            int httpStreamingAlternateBitrate2 = VOPlayer.this.getHttpStreamingAlternateBitrate();
                            while (i6 < VOPlayer.this.Y) {
                                if (VOPlayer.this.Z[i6].getBitrate() < httpStreamingAlternateBitrate2) {
                                    VOPlayer.g0(VOPlayer.this);
                                }
                                i6++;
                            }
                        }
                    } else if (!(VOPlayer.this.e == null && VOPlayer.this.f == null) && VOPlayer.this.Q && (2100 == (i2 = messageObtain.arg1) || 2200 == i2)) {
                        Message messageObtain2 = Message.obtain(null, 0, VOPlayer.MEDIA_INFO_SUBTITLE_BEGIN, messageObtain.arg2);
                        Message messageObtain3 = Message.obtain(null, 0, VOPlayer.MEDIA_INFO_SUBTITLE_END, messageObtain.arg2);
                        VOPlayer.this.P.removeMessages(0, messageObtain2);
                        VOPlayer.this.P.removeMessages(0, messageObtain3);
                        messageObtain = Message.obtain(null, 0, messageObtain.arg1, messageObtain.arg2);
                        VOPlayer.this.P.sendMessage(messageObtain);
                    } else if (8004 == messageObtain.arg2 && VOPlayer.this.j1 == null) {
                        messageObtain.arg1 = 17000;
                        messageObtain.arg2 = VOPlayer.MEDIA_INFO_SCRUBBING_AVAILABLE;
                    } else if (VOPlayer.this.e == null || !(11007 == (i3 = messageObtain.arg2) || 11008 == i3)) {
                        int i10 = messageObtain.arg2;
                        if (1010 == i10 || 1006 == i10) {
                            com.viaccessorca.voplayer.e eVar = VOPlayer.this.j1;
                            if (eVar != null) {
                                eVar.a(false);
                            }
                        } else if (1005 == i10) {
                            com.viaccessorca.voplayer.e eVar2 = VOPlayer.this.j1;
                            if (eVar2 != null) {
                                eVar2.a(true);
                            }
                            VOPlayer.this.g1 = false;
                        } else if (1004 == i10) {
                            VOPlayer.this.g1 = true;
                        } else if (20005 == i10) {
                            VOPlayer.this.a(messageObtain.obj);
                        } else if (1018 == i10) {
                            if (VOPlayer.this.y1 != null) {
                                for (OnInformationListener onInformationListener4 : VOPlayer.this.y1) {
                                    Object obj6 = messageObtain.obj;
                                    if (obj6 != null) {
                                        onInformationListener4.onInformation(this.f79a, messageObtain.arg1, messageObtain.arg2, obj6);
                                    }
                                }
                            }
                        } else if (17000 == messageObtain.arg1 && 17004 == i10) {
                            if (messageObtain.obj == null || !VOPlayer.this.Q()) {
                                return;
                            }
                            com.viaccessorca.voplayer.e eVar3 = VOPlayer.this.j1;
                            if (eVar3 != null && eVar3.b() == com.viaccessorca.voplayer.e.j.CONFIGURED) {
                                VOPlayer.this.j1.a((ThumbnailContext) messageObtain.obj);
                                VOPlayer.this.startThumbnailProcessing();
                            } else {
                                if (VOPlayer.this.j1 != null) {
                                    return;
                                }
                                VOPlayer.this.j1 = new com.viaccessorca.voplayer.e(this.f79a, new ScrubbingConfiguration(), true);
                                VOPlayer.this.j1.a((ThumbnailContext) messageObtain.obj);
                            }
                        } else {
                            int i11 = messageObtain.arg2;
                            if (4001 == i11 || 4002 == i11) {
                                VOPlayer.this.g0++;
                            }
                        }
                    } else {
                        VOPlayer.this.e.a();
                        if (11007 == messageObtain.arg2 && messageObtain.obj != null) {
                            VOPlayer.this.e.a((AdInformation) messageObtain.obj);
                        }
                        if (11008 == messageObtain.arg2 && (obj = messageObtain.obj) != null) {
                            VOPlayer.this.e.a(((AdInformation) obj).adId);
                        }
                    }
                }
            }
            if (11000 == messageObtain.arg1) {
                VOPlayer.this.internalOnVASTEvent(messageObtain.arg2, messageObtain.obj);
            }
            if (VOPlayer.this.y1 != null) {
                int i12 = messageObtain.arg2;
                if (1010 == i12 || 1006 == i12) {
                    Iterator it11 = VOPlayer.this.y1.iterator();
                    while (it11.hasNext()) {
                        ((OnInformationListener) it11.next()).onInformation(this.f79a, messageObtain.arg1, 1013, null);
                    }
                } else if (3003 != i12 && 20000 != (i = messageObtain.arg1) && 1200 != i && 1300 != i && 8008 != i12 && 11102 != i12 && 1400 != i) {
                    if (messageObtain.obj != null && 11000 == i && (i12 == 11013 || i12 == 11014 || i12 == 11015 || i12 == 11018 || i12 == 11016 || i12 == 11017)) {
                        Iterator it12 = VOPlayer.this.y1.iterator();
                        while (it12.hasNext()) {
                            ((OnInformationListener) it12.next()).onInformation(this.f79a, messageObtain.arg1, messageObtain.arg2, (AdTunnelInformation) messageObtain.obj);
                        }
                    } else {
                        Iterator it13 = VOPlayer.this.y1.iterator();
                        while (it13.hasNext()) {
                            ((OnInformationListener) it13.next()).onInformation(this.f79a, messageObtain.arg1, messageObtain.arg2, null);
                        }
                    }
                }
            }
            VOPlayer.this.b(messageObtain.arg1, messageObtain.arg2);
        }
    }

    private static final class g extends BroadcastReceiver {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<VOPlayer> f80a;
        Context b;
        boolean c = true;

        public g(VOPlayer vOPlayer) {
            this.b = null;
            if (vOPlayer != null) {
                String unused = vOPlayer.f73a;
                this.f80a = new WeakReference<>(vOPlayer);
                Context context = vOPlayer.m329t7p639082847841;
                if (context != null) {
                    this.b = context.getApplicationContext();
                }
                Context context2 = this.b;
                if (context2 != null) {
                    Intent intentRegisterReceiver = context2.registerReceiver(this, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG"));
                    if (intentRegisterReceiver != null) {
                        onReceive(this.b, intentRegisterReceiver);
                    } else {
                        boolean z = vOPlayer.q.isTV;
                    }
                }
            }
        }

        public void dispose() {
            Context context = this.b;
            if (context != null) {
                try {
                    context.unregisterReceiver(this);
                } catch (IllegalArgumentException unused) {
                }
                this.b = null;
            }
            this.f80a.clear();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean z = intent.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 1) != 0;
            if (z != this.c) {
                this.c = z;
                VOPlayer vOPlayer = this.f80a.get();
                if (vOPlayer != null) {
                    vOPlayer.b(this.c);
                }
            }
        }
    }

    private class h implements VOBaseMediaDrmAgent.OnMediaDrmAgentListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f81a;

        private h() {
            this.f81a = false;
        }

        /* synthetic */ h(VOPlayer vOPlayer, a aVar) {
            this();
        }

        public synchronized boolean isMediaCryptoPending() {
            return this.f81a;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.viaccessorca.drm.impl.VOBaseMediaDrmAgent.OnMediaDrmAgentListener
        public synchronized void onDrmEvent(int i) {
            f fVar;
            int i2;
            switch (i) {
                case 0:
                    if (VOPlayer.this.K0) {
                        fVar = VOPlayer.this.j;
                        i2 = 3002;
                        VOPlayer.this.j.sendMessage(fVar.obtainMessage(200, 3000, i2, null));
                    } else {
                        try {
                            VOPlayer.this.y0 = VOPlayer.this.H();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (isMediaCryptoPending()) {
                            setMediaCryptoPending(false);
                            VOBaseMediaDrmAgent currentMediaDrmAgent = VOPlayer.this.getCurrentMediaDrmAgent();
                            if (currentMediaDrmAgent != null) {
                                VOPlayer.this.a(currentMediaDrmAgent.i());
                                VOPlayer.this.s0 = currentMediaDrmAgent.a(VOMediaDrmAgent.EContentType.VIDEO);
                                VOPlayer.this.t0 = currentMediaDrmAgent.a(VOMediaDrmAgent.EContentType.AUDIO);
                                VOPlayer.this.r();
                            }
                        }
                    }
                    break;
                case 1:
                    if (isMediaCryptoPending()) {
                        VOPlayer.this.g(3102);
                    }
                    break;
                case 2:
                    fVar = VOPlayer.this.j;
                    i2 = 3001;
                    VOPlayer.this.j.sendMessage(fVar.obtainMessage(200, 3000, i2, null));
                    break;
                case 3:
                    if (!VOPlayer.this.K0) {
                        fVar = VOPlayer.this.j;
                        i2 = 3002;
                        VOPlayer.this.j.sendMessage(fVar.obtainMessage(200, 3000, i2, null));
                    }
                    break;
                case 4:
                    if (isMediaCryptoPending()) {
                        VOPlayer.this.g(VOPlayer.MEDIA_ERROR_DRM_SERVER_NOT_REACHABLE);
                    }
                    break;
                case 5:
                    if (isMediaCryptoPending()) {
                        VOPlayer.this.g(VOPlayer.MEDIA_ERROR_DRM_NETWORK_COMMUNICATION_FAILURE);
                    }
                    break;
                case 6:
                    fVar = VOPlayer.this.j;
                    i2 = VOPlayer.MEDIA_INFO_DRM_SESSION_LOCKED;
                    VOPlayer.this.j.sendMessage(fVar.obtainMessage(200, 3000, i2, null));
                    break;
                case 7:
                    fVar = VOPlayer.this.j;
                    i2 = VOPlayer.MEDIA_INFO_DRM_SESSION_UNLOCKED;
                    VOPlayer.this.j.sendMessage(fVar.obtainMessage(200, 3000, i2, null));
                    break;
                case 8:
                    if (!VOPlayer.this.K0) {
                        fVar = VOPlayer.this.j;
                        i2 = VOPlayer.MEDIA_INFO_DRM_SESSION_WILL_EXPIRE;
                        VOPlayer.this.j.sendMessage(fVar.obtainMessage(200, 3000, i2, null));
                    }
                    break;
                default:
                    break;
            }
        }

        public synchronized void setMediaCryptoPending(boolean z) {
            this.f81a = z;
        }
    }

    private enum i {
        CREATED,
        PREOPENING,
        OPENING,
        PREPARED,
        PLAYING,
        PAUSED,
        BUFFERING,
        STOPPED,
        RELEASING
    }

    private class j extends Thread {
        private j() {
        }

        /* synthetic */ j(VOPlayer vOPlayer, a aVar) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            f fVar;
            if (VOPlayer.this.g() && (fVar = VOPlayer.this.j) != null) {
                VOPlayer.this.j.sendMessage(fVar.obtainMessage(VOPlayer.MEDIA_INFO_VAST_ADVERTISEMENT));
            } else if (i.PREOPENING == VOPlayer.this.M) {
                VOPlayer.this.M = i.CREATED;
            }
        }
    }

    private class k extends Thread {
        private k() {
        }

        /* synthetic */ k(VOPlayer vOPlayer, a aVar) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            f fVar;
            if (VOPlayer.this.h() && (fVar = VOPlayer.this.j) != null) {
                VOPlayer.this.j.sendMessage(fVar.obtainMessage(VOPlayer.MEDIA_INFO_VAST_NOT_ADVERTISEMENT));
            } else if (i.PREOPENING == VOPlayer.this.M) {
                VOPlayer.this.M = i.CREATED;
            }
        }
    }

    private static class l {
        int m329t7p6390828478431LengthInSeconds;
        boolean m329t7p6390828478468;
        boolean m329t7p6390828478469;
        int m329t7p6390828478470;
        int m329t7p6390828478472;
        int mKeepFieldClearCacheEnabledStatus;

        private l() {
        }

        /* synthetic */ l(a aVar) {
            this();
        }
    }

    private static final class m extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<VOPlayer> f85a;

        public m(VOPlayer vOPlayer) {
            this.f85a = new WeakReference<>(vOPlayer);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            VOPlayer vOPlayer = this.f85a.get();
            if (vOPlayer != null) {
                try {
                    vOPlayer.a(message);
                } catch (Throwable unused) {
                }
            }
        }
    }

    private static class n {
        public int m329t7p6390828478466 = 0;
    }

    private static class o implements Choreographer.FrameCallback, Handler.Callback {
        private static CopyOnWriteArraySet<f> f;
        private final Handler b;
        private final HandlerThread c;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public volatile long f86a = 0;
        private Choreographer d = null;
        private int e = 0;

        static {
            new o();
            f = new CopyOnWriteArraySet<>();
        }

        private o() {
            if (16 > VOUtils.SDK_INT) {
                this.b = null;
                this.c = null;
                return;
            }
            HandlerThread handlerThread = new HandlerThread("ChoreographerOwner:Handler");
            this.c = handlerThread;
            handlerThread.start();
            Handler handler = new Handler(this.c.getLooper(), this);
            this.b = handler;
            handler.sendEmptyMessage(0);
        }

        private void a() {
            int i = this.e + 1;
            this.e = i;
            if (i == 1) {
                this.d.postFrameCallback(this);
            }
        }

        private void b() {
            this.d = Choreographer.getInstance();
        }

        private void c() {
            int i = this.e - 1;
            this.e = i;
            if (i == 0) {
                this.d.removeFrameCallback(this);
                this.f86a = 0L;
            }
        }

        protected void a(f fVar) {
            if (16 <= VOUtils.SDK_INT) {
                f.remove(fVar);
                this.b.sendEmptyMessage(2);
            }
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            this.f86a = j;
            for (f fVar : f) {
                if (fVar != null) {
                    fVar.sendMessage(fVar.obtainMessage(VOPlayer.MEDIA_INFO_VAST_SKIPPABLE_IN_SEC));
                }
            }
            this.d.removeFrameCallback(this);
            this.d.postFrameCallbackDelayed(this, 500L);
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                b();
                return true;
            }
            if (i == 1) {
                a();
                return true;
            }
            if (i != 2) {
                return false;
            }
            c();
            return true;
        }
    }

    private class p extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f87a = false;
        protected VOPlayer b;

        public p(VOPlayer vOPlayer) {
            this.b = null;
            this.b = vOPlayer;
        }

        private synchronized boolean a() {
            return this.f87a;
        }

        public synchronized void cancel() {
            this.f87a = true;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (!a() && c.b[VOPlayer.this.z0.ordinal()] == 1 && c.c[VOPlayer.this.x0.ordinal()] == 1 && VOPlayer.Nzg5N0Y2MTU5OTY0NEQ0Mzk3NEM4NTM4MjRCMUQwQjI(VOPlayer.N1) && !a()) {
                com.viaccessorca.drm.impl.d dVar = new com.viaccessorca.drm.impl.d(VOPlayer.this.m329t7p639082847841, this.b);
                try {
                    if (dVar.verifyPersonalization()) {
                        return;
                    }
                    dVar.setPersonalizationURL("https://perso.purpledrm.com/PersoServer/Personalization");
                    if (a()) {
                        return;
                    }
                    dVar.performPersonalization();
                } catch (VOException unused) {
                }
            }
        }
    }

    static {
        if (true == VOLibraryLoader.b()) {
            NDYwNDFFQkEzOEQzNDY1NEIxMTI0NjlFNTc0NjNFQUE();
        }
        R1 = false;
        S1 = false;
        T1 = "Cp1252";
        U1 = "UTF-16LE";
        V1 = "UTF-8";
        W1 = "UTF-8";
        X1 = "Cp1252";
        Y1 = new String[]{"Default - Unknown", "cs-CZ", "da-DK", "de-AT", "de-BE", "de-CH", "de-DE", "de-LI", "de-LU", "el-GR", "en-AU", "en-BE", "en-BW", "en-BZ", "en-CA", "en-GB", "en-HK", "en-IE", "en-IN", "en-JM", "en-MH", "en-MT", "en-NA", "en-NZ", "en-PH", "en-PK", "en-SG", "en-TT", "en-US", "en-US2", "en-VI", "en-ZA", "en-ZW", "es-ES", "es-US", "fr-BE", "fr-CA", "fr-CH", "fr-FR", "fr-LU", "fr-MC", "it-CH", "it-IT", "ja-JP", "ko-KR", "nb-NO", "nl-BE", "nl-NL", "pl-PL", "pt-BR", "pt-PT", "ru-RU", "sv-SE", "tr-TR", "zh-CN", "zh-HANS", "zh-HANT", "zh-TW"};
        Z1 = new String[]{"UTF-8", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "MS932", "MS949", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "Cp1252", "gb2312", "gb2312", "big5", "big5"};
    }

    private VOPlayer() {
        this.b = -1;
        this.c = -1;
        this.d = -1;
        this.m329t7p639082847842 = 0L;
        this.e = null;
        this.f = null;
        this.g = null;
        this.m329t7p639082847843 = null;
        this.m329t7p6390828478403 = null;
        this.h = true;
        this.i = false;
        this.m329t7p639082847845 = 0L;
        this.m329t7p639082847846 = 0L;
        this.m329t7p639082847847 = 0L;
        this.k = null;
        this.l = 0L;
        this.m = null;
        this.p = 1.0f;
        this.q = null;
        this.r = 0;
        this.s = false;
        this.t = -1;
        this.u = false;
        this.v = false;
        this.w = 0;
        this.x = false;
        this.y = -1;
        this.z = -1;
        this.A = -1;
        this.B = -1;
        this.C = -1;
        this.D = -1;
        this.E = PreferredVideoCodecType.VOPREFERRED_VIDEO_CODEC_TYPE_DEFAULT;
        this.m329t7p639082847848 = null;
        this.m329t7p639082847849 = null;
        this.F = null;
        this.G = false;
        this.H = false;
        this.I = false;
        this.J = -1;
        this.K = -1;
        this.L = false;
        this.m329t7p639082847841 = null;
        this.M = i.CREATED;
        this.N = false;
        this.P = null;
        this.Q = false;
        this.R = false;
        this.S = null;
        this.T = true;
        this.U = null;
        this.V = null;
        this.W = 0L;
        this.X = 0;
        this.Y = 0;
        this.Z = null;
        this.a0 = 0;
        this.b0 = false;
        this.c0 = false;
        this.d0 = false;
        this.e0 = true;
        this.f0 = null;
        this.g0 = 0;
        this.h0 = null;
        this.i0 = null;
        this.j0 = null;
        this.k0 = 1.0d;
        this.l0 = new Object();
        this.m0 = new Object();
        this.n0 = new Object();
        this.o0 = new Object();
        this.p0 = false;
        this.q0 = false;
        this.r0 = false;
        this.m329t7p6390828478415 = null;
        this.s0 = null;
        this.t0 = null;
        this.u0 = null;
        this.v0 = null;
        this.w0 = null;
        this.x0 = DrmAgent.EDrmType.DRM_TYPE_UNKNOWN;
        this.y0 = null;
        this.z0 = EDrmEngineType.DefaultEngine;
        this.A0 = null;
        this.B0 = null;
        this.C0 = null;
        this.D0 = null;
        this.E0 = null;
        this.F0 = null;
        this.G0 = 0;
        this.H0 = false;
        this.I0 = null;
        this.J0 = null;
        this.K0 = false;
        this.L0 = null;
        this.M0 = false;
        this.N0 = 0;
        this.O0 = false;
        this.P0 = null;
        this.Q0 = false;
        this.R0 = null;
        this.S0 = 0;
        this.T0 = 0;
        this.U0 = null;
        this.V0 = null;
        this.W0 = null;
        this.X0 = null;
        this.Y0 = null;
        this.Z0 = null;
        this.a1 = null;
        this.b1 = null;
        this.c1 = null;
        this.d1 = null;
        this.e1 = null;
        this.f1 = true;
        this.g1 = false;
        this.h1 = null;
        this.i1 = false;
        this.j1 = null;
        this.k1 = null;
        this.l1 = null;
        this.m1 = false;
        this.n1 = false;
        this.o1 = null;
        this.p1 = null;
        this.q1 = false;
        this.z1 = false;
    }

    public VOPlayer(Context context) throws Exception {
        DebugOption debugOption;
        boolean z;
        this.b = -1;
        this.c = -1;
        this.d = -1;
        this.m329t7p639082847842 = 0L;
        a aVar = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.m329t7p639082847843 = null;
        this.m329t7p6390828478403 = null;
        this.h = true;
        this.i = false;
        this.m329t7p639082847845 = 0L;
        this.m329t7p639082847846 = 0L;
        this.m329t7p639082847847 = 0L;
        this.k = null;
        this.l = 0L;
        this.m = null;
        this.p = 1.0f;
        this.q = null;
        this.r = 0;
        this.s = false;
        this.t = -1;
        this.u = false;
        this.v = false;
        this.w = 0;
        this.x = false;
        this.y = -1;
        this.z = -1;
        this.A = -1;
        this.B = -1;
        this.C = -1;
        this.D = -1;
        this.E = PreferredVideoCodecType.VOPREFERRED_VIDEO_CODEC_TYPE_DEFAULT;
        this.m329t7p639082847848 = null;
        this.m329t7p639082847849 = null;
        this.F = null;
        this.G = false;
        this.H = false;
        this.I = false;
        this.J = -1;
        this.K = -1;
        this.L = false;
        this.m329t7p639082847841 = null;
        this.M = i.CREATED;
        this.N = false;
        this.P = null;
        this.Q = false;
        this.R = false;
        this.S = null;
        this.T = true;
        this.U = null;
        this.V = null;
        this.W = 0L;
        this.X = 0;
        this.Y = 0;
        this.Z = null;
        this.a0 = 0;
        this.b0 = false;
        this.c0 = false;
        this.d0 = false;
        this.e0 = true;
        this.f0 = null;
        this.g0 = 0;
        this.h0 = null;
        this.i0 = null;
        this.j0 = null;
        this.k0 = 1.0d;
        this.l0 = new Object();
        this.m0 = new Object();
        this.n0 = new Object();
        this.o0 = new Object();
        this.p0 = false;
        this.q0 = false;
        this.r0 = false;
        this.m329t7p6390828478415 = null;
        this.s0 = null;
        this.t0 = null;
        this.u0 = null;
        this.v0 = null;
        this.w0 = null;
        this.x0 = DrmAgent.EDrmType.DRM_TYPE_UNKNOWN;
        this.y0 = null;
        this.z0 = EDrmEngineType.DefaultEngine;
        this.A0 = null;
        this.B0 = null;
        this.C0 = null;
        this.D0 = null;
        this.E0 = null;
        this.F0 = null;
        this.G0 = 0;
        this.H0 = false;
        this.I0 = null;
        this.J0 = null;
        this.K0 = false;
        this.L0 = null;
        this.M0 = false;
        this.N0 = 0;
        this.O0 = false;
        this.P0 = null;
        this.Q0 = false;
        this.R0 = null;
        this.S0 = 0;
        this.T0 = 0;
        this.U0 = null;
        this.V0 = null;
        this.W0 = null;
        this.X0 = null;
        this.Y0 = null;
        this.Z0 = null;
        this.a1 = null;
        this.b1 = null;
        this.c1 = null;
        this.d1 = null;
        this.e1 = null;
        this.f1 = true;
        this.g1 = false;
        this.h1 = null;
        this.i1 = false;
        this.j1 = null;
        this.k1 = null;
        this.l1 = null;
        this.m1 = false;
        this.n1 = false;
        this.o1 = null;
        this.p1 = null;
        this.q1 = false;
        this.z1 = false;
        StringBuilder sb = new StringBuilder();
        sb.append("VOPlayer#");
        int i2 = A1;
        A1 = i2 + 1;
        sb.append(i2);
        this.f73a = new String(sb.toString());
        Boolean boolInit = VOCrashReporter.init(context);
        R1 = boolInit;
        if (boolInit.booleanValue()) {
            VOCrashReporter.logEvent(this.f73a, "java5");
            VOCrashReporter.sendPendingCrashReport();
        }
        Looper mainLooper = Looper.getMainLooper();
        if (mainLooper != null) {
            this.j = new f(this, mainLooper, this.f73a + "$EventHandler");
            this.k1 = new Handler(mainLooper);
        } else {
            this.j = null;
        }
        if (this.j == null) {
            throw new IllegalStateException();
        }
        WeakReference weakReference = new WeakReference(this);
        I1 = weakReference;
        WeakReference[] weakReferenceArr = J1;
        if (weakReferenceArr != null) {
            WeakReference[] weakReferenceArr2 = (WeakReference[]) Arrays.copyOf(weakReferenceArr, weakReferenceArr.length + 1);
            J1 = weakReferenceArr2;
            weakReferenceArr2[weakReferenceArr2.length - 1] = I1;
        } else {
            J1 = new WeakReference[]{weakReference};
        }
        VOLogger.setForceMinPriority(false);
        if (Nzg5N0Y2MTU5OTY0NEQ0Mzk3NEM4NTM4MjRCMUQwQjI(L1)) {
            this.d0 = false;
            debugOption = DebugOption.DBG_OPT_SHOW_PLAYER_INFO;
            z = false;
        } else {
            this.d0 = true;
            debugOption = DebugOption.DBG_OPT_SHOW_PLAYER_INFO;
            z = true;
        }
        setDebugOption(debugOption, z);
        a(context, this.d0);
        ODI1MEMzNDZDN0JBNDBERUJFRkUyNjM4QUFGN0I2OUI(I1, context.getApplicationInfo().dataDir);
        C1 = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        this.r1 = new CopyOnWriteArraySet<>();
        this.t1 = new CopyOnWriteArraySet<>();
        this.u1 = new CopyOnWriteArraySet<>();
        this.s1 = new CopyOnWriteArraySet<>();
        this.v1 = new CopyOnWriteArraySet<>();
        this.x1 = new CopyOnWriteArraySet<>();
        this.y1 = new CopyOnWriteArraySet<>();
        this.w1 = new CopyOnWriteArraySet<>();
        this.U = new VOFingerprintData();
        this.H = false;
        VOSystemInfo systemInfo = VOSystemInfoRetriever.getSystemInfo(context);
        this.q = systemInfo;
        systemInfo.codecTypeException = -1;
        this.h1 = new AdRequestOptions();
        this.v = true;
        try {
            Class.forName("com.google.android.gms.cast.Cast");
        } catch (Exception unused) {
            this.v = false;
        }
        this.f1 = true;
        if (!VOPlatformAnalyzer.g()) {
            forceMultithreadDecoding(false);
        }
        this.m329t7p639082847841 = context;
        if (context != null) {
            t();
            a(context);
            l(context);
            MjcxNTYzREU2MjlDNDEwM0FDMzgwQjUxRDM4RUU3Qjg(D1);
            m(context);
            QzA0MkYyRDlCQ0E2NDg1ODgxNjBGNTE1NjIwOTkxQTM(context, D1, DrmAgent.useAndroidXDependency());
            RjNGRjZGMzAxMkVBNEZGNUFGNTUwMjVFOTExRkE4NzY(context.getPackageName());
            QjZGMzUzQTM2QjlFNEYxMEJEN0I1MUJCQzM2M0JFMkUK(System.getProperty("http.agent"));
        }
        try {
            DrmAgent.initialize(context);
        } catch (VOException e2) {
            if (VOStatusCode.ERROR_SECURITY_MISSING_PERMISSION == e2.getStatus() || VOStatusCode.ERROR_OPERATION_ABORTED == e2.getStatus()) {
                a(e2);
            }
        }
        this.l1 = new g(this);
        this.L0 = new h(this, aVar);
        VOCrashReporter.logEvent(this.f73a, "java9");
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0043  */
    private void A() {
        String str;
        AssetManager assets;
        com.viaccessorca.drm.impl.d dVar = this.A0;
        if (dVar == null || this.m329t7p639082847841 == null || dVar.getPersonalizationServerUrl() == null || !this.A0.getPersonalizationServerUrl().contains("debug://localpersonalization")) {
            str = null;
        } else {
            str = this.m329t7p639082847841.getFilesDir() + "/provisioning.dat";
            if (new File(str).exists()) {
                str = null;
            }
        }
        if (str == null || (assets = this.m329t7p639082847841.getAssets()) == null) {
            return;
        }
        try {
            InputStream inputStreamOpen = assets.open("PlayReady/provisioning.dat");
            File file = new File(str);
            file.getParentFile().mkdir();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int i2 = inputStreamOpen.read(bArr);
                if (i2 <= 0) {
                    fileOutputStream.close();
                    inputStreamOpen.close();
                    return;
                }
                fileOutputStream.write(bArr, 0, i2);
            }
        } catch (IOException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native void AEREMzlFM05MkY1RjY0MjlBNNEFCNTQY2QTk3QjgMDA(Buffer buffer, int i2, int i3);

    /* JADX INFO: Access modifiers changed from: private */
    public native int AEREMzlFM05MkY3QjgMDA1RjY0MjlBNNEFCNTQY2QTk(int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public native int ARjY0MjlBNEREMzlFM05MkY1NEFCNTQY2QTk3QjgMDA(int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public native int ARjY0MjlBNEREMzlFM0Y2QTk3QjgMDA5MkY1NEFCNTQ();

    private void B() {
        if (this.e == null || !QTlBRjkxNkNGQTZENDJGNUI0QjMzQUEwMTY4NjBGMjY()) {
            return;
        }
        this.e.b();
    }

    private void C() {
        VOSurfaceView vOSurfaceView = this.e;
        if (vOSurfaceView != null) {
            vOSurfaceView.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ByteBuffer D() {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(16);
        if (byteBufferAllocateDirect != null) {
            MzhGMzI0MjQ2NDZBNDdGMzgwQzM4QUE5NDYwMERCRTE(byteBufferAllocateDirect);
        }
        return byteBufferAllocateDirect;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ByteBuffer E() {
        int iMDA5MkY4RjY0MjlBNEREMzlFM0Y2QTk3Qjg1NEFCNTQSize = MDA5MkY4RjY0MjlBNEREMzlFM0Y2QTk3Qjg1NEFCNTQSize();
        if (iMDA5MkY4RjY0MjlBNEREMzlFM0Y2QTk3Qjg1NEFCNTQSize <= 0) {
            return null;
        }
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(iMDA5MkY4RjY0MjlBNEREMzlFM0Y2QTk3Qjg1NEFCNTQSize);
        if (byteBufferAllocateDirect != null) {
            MDA5MkY4RjY0MjlBNEREMzlFM0Y2QTk3Qjg1NEFCNTQ(byteBufferAllocateDirect, iMDA5MkY4RjY0MjlBNEREMzlFM0Y2QTk3Qjg1NEFCNTQSize);
        }
        return byteBufferAllocateDirect;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ByteBuffer F() {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(16);
        if (byteBufferAllocateDirect != null) {
            MDY4MUQ4MjQ0OUM0NDMwRUFBOUY3MDE5QzU0NjE5Mzg(byteBufferAllocateDirect);
        }
        return byteBufferAllocateDirect;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ByteBuffer G() {
        int iN0YyQjVENTM3NTc1NDkzRkFCQ0FDOUM4MkYwNzY1M0ESize = N0YyQjVENTM3NTc1NDkzRkFCQ0FDOUM4MkYwNzY1M0ESize();
        if (iN0YyQjVENTM3NTc1NDkzRkFCQ0FDOUM4MkYwNzY1M0ESize <= 0) {
            return null;
        }
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(iN0YyQjVENTM3NTc1NDkzRkFCQ0FDOUM4MkYwNzY1M0ESize);
        if (byteBufferAllocateDirect != null) {
            N0YyQjVENTM3NTc1NDkzRkFCQ0FDOUM4MkYwNzY1M0E(byteBufferAllocateDirect, iN0YyQjVENTM3NTc1NDkzRkFCQ0FDOUM4MkYwNzY1M0ESize);
        }
        return byteBufferAllocateDirect;
    }

    private native Object GyQUM4NkVGNDg3RDIy0OTYwMEJCQTU25NDMTMKTZhNEO();

    /* JADX INFO: Access modifiers changed from: private */
    public String H() throws VOException {
        String lowerCase;
        StringBuilder sb;
        String strA;
        String str;
        String str2;
        try {
            lowerCase = (Nzg5N0Y2MTU5OTY0NEQ0Mzk3NEM4NTM4MjRCMUQwQjI(M1) ? retrieveSdkInternalVersion(this.m329t7p639082847841) : DrmAgent.getDrmVersion(this.m329t7p639082847841)).toLowerCase();
        } catch (Exception unused) {
            lowerCase = "";
        }
        int i2 = c.c[this.x0.ordinal()];
        String maxSecurityLevelString = "None";
        if (i2 == 1) {
            if (EDrmEngineType.MediaDrm == this.z0) {
                if (Build.VERSION.SDK_INT >= 18) {
                    String securityLevelString = getAgentPlayready(EDrmEngineType.MediaDrm).getSecurityLevelString();
                    maxSecurityLevelString = getAgentPlayready(EDrmEngineType.MediaDrm).getMaxSecurityLevelString();
                    strA = securityLevelString;
                } else {
                    strA = "None";
                }
                sb = new StringBuilder();
                sb.append("Device:");
                sb.append(maxSecurityLevelString);
                sb.append(",Session:");
            } else {
                sb = new StringBuilder();
                sb.append("Embedded:");
                strA = a(lowerCase);
            }
            sb.append(strA);
            return "\nDRM: PlayReady -> Security level : " + sb.toString();
        }
        if (i2 == 2) {
            return "\nDRM: Clear content";
        }
        if (i2 == 3) {
            if (Build.VERSION.SDK_INT >= 18) {
                String securityLevelString2 = getAgentWidevine().getSecurityLevelString();
                maxSecurityLevelString = getAgentWidevine().getMaxSecurityLevelString();
                str = securityLevelString2;
            } else {
                str = "None";
            }
            return "\nDRM: Widevine -> Security level, Device:" + maxSecurityLevelString + ",Session:" + str;
        }
        if (i2 != 4) {
            if (i2 != 5) {
                return "\nDRM: " + VOPlaybackSessionReport.UNKNOWN_SVALUE;
            }
            return "\nDRM: Verimatrix -> Security level : Software";
        }
        int iValueOf = -1;
        int iValueOf2 = -1;
        if (Build.VERSION.SDK_INT >= 18) {
            String securityLevelString3 = getAgentVodrm().getSecurityLevelString();
            String maxSecurityLevelString2 = getAgentVodrm().getMaxSecurityLevelString();
            try {
                iValueOf = Integer.valueOf(getAgentVodrm().getContentMaturityRatingThreshold());
                iValueOf2 = Integer.valueOf(getAgentVodrm().getMaturityRatingThreshold());
            } catch (VOException unused2) {
            }
            maxSecurityLevelString = maxSecurityLevelString2;
            str2 = securityLevelString3;
        } else {
            str2 = "None";
        }
        return "\nDRM: voDrm -> Security level, Device:" + maxSecurityLevelString + ",Session:" + str2 + ",sessionMR:" + iValueOf2 + ",contentMR:" + iValueOf;
    }

    private static String I() {
        return Build.VERSION.RELEASE + Build.VERSION.INCREMENTAL + Build.VERSION.SDK_INT;
    }

    private void J() {
        VOSurfaceView vOSurfaceView = this.e;
        if (vOSurfaceView != null) {
            try {
                vOSurfaceView.hidePlayerLayouts();
            } catch (Exception unused) {
            }
        }
    }

    private native void JarSoAlignedRkJDRjJEMkFGRTZDNDgzOUIwMEVFRjgwNTQ4Q0ZDNTc090210();

    /* JADX INFO: Access modifiers changed from: private */
    public native void JgMDA1RjY0Mjl4EREMzlFM05MkY3QBNNEFCNTQY2QTk(Buffer buffer, int i2, int i3);

    /* JADX INFO: Access modifiers changed from: private */
    public native int Jgl4EREMzlFM05MkY3QBNNEFCNTQY2QTMDA1RjY0Mjk(int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public void K() {
    }

    private boolean L() {
        if (this.v) {
            return d(this.m329t7p639082847841);
        }
        return false;
    }

    private Boolean M() {
        return Boolean.valueOf(getStreamUri() != null && (getStreamUri().toLowerCase().startsWith("rtp://") || getStreamUri().toLowerCase().startsWith("udp://")));
    }

    private native int M0E2Q0E3Q0IyMkVBNDFFNDhEMTFFNkExOEIyNzBGNDk();

    private native void M0E3RUFBMzFCRTU2NERCQzhGNkU3MEQ4MDVCN0Q2OUI(String str);

    private native void M0VGN0REMzk0Q0EwNERFNkI4QTY5MjkzNzNDRjk4MTg(int i2);

    private native void MDA5MkY4RjY0MjlBNEREMzlFM0Y2QTk3Qjg1NEFCNTQ(Buffer buffer, int i2);

    private native int MDA5MkY4RjY0MjlBNEREMzlFM0Y2QTk3Qjg1NEFCNTQSize();

    private native void MDFDRUQ1OEI5NDE3NEY4QkI2MTg5Rjg0REE2QkMwRkU(int i2, boolean z) throws UnsupportedOperationException, IllegalArgumentException;

    private native int MDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUY();

    private native int MDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUYAbsolute();

    private native void MDQzMUY3MjVCNjI4NDIzMUFCRjg5RDQ1RDE2NEM2NkM(String str, int i2) throws UnsupportedOperationException, IOException;

    private native void MDY2RjAyREQzOUMwNDg3RkIwRjEyQjQ3MjQ3NDZBODQ(boolean z) throws IllegalStateException;

    private native void MDY4MUQ4MjQ0OUM0NDMwRUFBOUY3MDE5QzU0NjE5Mzg(Buffer buffer);

    private native void MDdBNTA0OTY3QzdCNEZFRThBMjYzNEZEQzBENzY5Mzk(String str);

    private native void MEMzOTI0NkE5NThENDMxRTg0QUU4RkNBNkE4MkVCOEE(int i2);

    private native void MEUyQTZFQzM5NEY3NEVGMDlGRERGQ0YxN0QyMUFFM0U(boolean z) throws UnsupportedOperationException;

    private native void MTUwRTdENjdCMDg5NERFMjg3NzBDRjcxQkUwRjg5NTI(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native void MTc2NkVBOTEwNTVGNDgzOEJENDNBRTYwODI0MzRDMzE();

    private native boolean MTdCN0I4NEM0QkRENEMzNTg4NjQ3ODA4NUEwM0Y3Qjc();

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean MTkxRTBBQjcwREQ5NDI4Q0JBRTREMTA5MjFDNTZCMDI(Object obj);

    /* JADX INFO: Access modifiers changed from: private */
    public native int MTkxRTBBQjcwREQ5NDI4Q0JBRTREMTA5MjFDNTZCMDISize();

    private native int MUJERjY4RTEwQjdBNDhDMjkzNzEwNTg2OUQ0NDA0NTQ();

    private native void MUU0OEQ3QTlEMjlCNDNCQTg3NTBENzg5NjhFMzM5NjE(int i2);

    private native void MUUyOTVGQzNGNzhFNEE0RkEzQjU0QkYyREU3Mzk1N0E(int i2, boolean z, int i3);

    private native boolean MUY4MjFGQkJFQjUwNDA3OUE3REE5Mzg2NjkwOUM1NTc();

    private native void MjBCNDAwNEREMDYxNEMxRUI3NjlGQUExRjg4RjIyNjc(boolean z) throws UnsupportedOperationException;

    private native int MjNENjlBNTMzNDcyNEZFQUI1QTI3NjJDNzQ3OEMwMUQ();

    private native void MjNFRDdDRUNGODkzNDBENjhCRUM3RDBGNjM4M0I3ODMK(String str);

    private native int MjQ2NTAyQzU0NzNCNDg1N0FFMUM3NTQ5M0EwMzM3OEU(boolean z) throws UnsupportedOperationException;

    private native void MjcxNTYzREU2MjlDNDEwM0FDMzgwQjUxRDM4RUU3Qjg(String str);

    private native void MjgxMDZENTk1Q0JDNDhERUE4Q0JDNTNBOUFDQjVCRDA(int i2, String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native void MjkMzlFM05MkY3QBNNEFCNTQjY2QTMDA1RjY0gl4ERE(Buffer buffer, int i2, int i3);

    private native void MkI0Nzc4NDA3ODZFNDgzQUIyQjY1NzM1N0QzRTkyNTI(Buffer buffer, int i2, int i3, int i4);

    private native void MzM0NjhCMzY2ODYxNEIwN0E4OTRBRTY2OTg3RjgwMjA(String str);

    private native void MzQ5MkI2OTQxQ0UwNDgyQUExQjg3QjFDNUZGQzgxODM(int i2, int i3, int i4);

    private native void MzQxMjk3MTVEMUE3NENCNEFCQzM3N0E3MUJDMjQ4OTA(String str, int i2);

    private native void MzU4NDEyOURDMDQzNDQ0Qzg3RkU1OTdFREM1RjczNjI(float f2, float f3, float f4);

    private native void MzY3QkUxRkE2RDc3NDFDMTg3RDdBNUIzQ0U3QzIyQzA(int i2);

    private native void MzhGMzI0MjQ2NDZBNDdGMzgwQzM4QUE5NDYwMERCRTE(Buffer buffer);

    private native void MzkwNDdDNERCNjlBNDM1MTg1RDIzMzNBRTg5MEI4OUQ(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native void MzlFM05MkY3QBNNEFCNTQY2QTMDA1RjY0Mjkjgl4ERE(Buffer buffer, int i2, int i3);

    private boolean N() {
        VOSurfaceView vOSurfaceView = this.e;
        return (vOSurfaceView != null ? vOSurfaceView.h() : null) != null;
    }

    private native void N0I3ODcxNjA5NUNFNEVGRTgwQThCNkZBNjU4RDBBM0E(boolean z);

    private native void N0NEREQxRkM4NkFBNEZDNTk4RTM5MUNGMDIwMDVENjE(int i2);

    private native Object[] N0U2M0I0RDFBQkQzNEE5N0IwNUI4NzFDREMzNDk3RjUK();

    private native void N0U3NzVGQTlGMkEzNERBRDgyQTY5OTU4MUM2NDEzREU(boolean z);

    private static native boolean N0UxMEYyODI1QkE0NDNCN0JDMTExQzZCQjhEODE4QjY(String str, String str2, int i2, int i3, boolean z);

    private native void N0Y2QjA5ODZDNUI2NEMwMjhGRUE5OTRDQzFEQUIxN0I(Buffer buffer, int i2);

    private native int N0Y2QjA5ODZDNUI2NEMwMjhGRUE5OTRDQzFEQUIxN0ISize();

    private native void N0YyQjVENTM3NTc1NDkzRkFCQ0FDOUM4MkYwNzY1M0E(Buffer buffer, int i2);

    private native int N0YyQjVENTM3NTc1NDkzRkFCQ0FDOUM4MkYwNzY1M0ESize();

    private native void N0ZFN0EwOUQzQzQ4NEExNEI4MDQzNjIxMkRDRTIxQzc(boolean z, int i2);

    private native int NDJBMUIzNDI3NDFFNEQ4NUEwQURFQzE2NTM0MUY3OEM() throws UnsupportedOperationException;

    private native void NDMwNzdCOEZBN0VENDgxNEFBNzA2QUVCQTQ3NEUxQUM(String str, int i2, String str2, int i3);

    private native boolean NDQ0MzM0NjU1QjlFNDVDRUE1M0NEQUIzMEQ4OEY3QzU();

    private native void NDUxODFCQ0M1QUZBNEM0MkEwMzI3REE1ODgwRTMyMjg(String str);

    private static final native void NDYwNDFFQkEzOEQzNDY1NEIxMTI0NjlFNTc0NjNFQUE();

    private native void NDZEQTU2RTQxQkEyNDYzQzkyMTY4MjJEQjQ2N0IzMjY(String str);

    private native void NDc2RTQxOEFCQTFDNDY0QjlFNUE2MzkyMzRBRjBERDkK(String str, String str2);

    private native void NEIzRUM2MUNENjkwNDQ5MEFDOUZBNjk4NzkwMEQ0MUM(float f2);

    private native int NTMwOTE1NkVDQTZENEU2REJBOEJGMTFDODVBQTM0MDg(String str) throws UnsupportedOperationException;

    private native void NTMxMzM4NzdFNzRBNDVGQjhEODczNjMwMEY5NzQwRTM(int i2);

    private native boolean NTNGMkU0RDIxQTBCNDRGREI3QkExREEyRUQyNkExN0IK(String str, String str2);

    private native void NUQ0MDk2QjI3RjBBNEIwMjkwN0Q5OUQ5MUUwNkM3NDg();

    private native void NUQwODJBOEQxREZDNEU3MjhCOUU0MTk4OTdGMjVGOTA(int i2);

    private native void NUU5OTFGOUQ0OUVENDQxMTkyNjA5QTJEOENBNzE0NDE(int i2, int i3) throws UnsupportedOperationException, IllegalArgumentException;

    private native float NUUwODM1RUMxRUUyNDA3QzhGOTBDRjAxMURBRTlGRTQ();

    private native void NjFEMDk1RTE1QTE2NEY5Qzg4ODJBNzVBMkE3MzEzNDc(int i2, int i3);

    private native void NjFiZmY5MzkwNmRkNDFhMWI1MzFjY2UxZTk3ZWU1ODIK(boolean z);

    private native void NjczNjY1OUMyMTY4NEExMUJDRjk2NjY4RjJBNTY2MTMK(boolean z, Object obj);

    private native String NjhFOTY0RERBMEFCNDJGNzgzOUJGRUFERTk1RkE4RTAK();

    private native void Njk2N0E1RjM1ODcxNDlCQkE2MDMyNjIzQTM5NjRFQzYK(Object obj);

    private native String NjkwRDhBRkYzM0EwNDlFQUE1NEVBMkRBMTE5ODZDRTgK();

    private native void NkE1NTg1QjBGMUYwNDU3NEFDMTRDNTM3RkMyNkZBN0E(int i2);

    private native void NkM0MkM1RDBEM0E5NEYzMTlGQjI0Q0FEQ0RDQjZCNjg(String str);

    private native void NkVGM0VCOEMyNDJENDQwNjk0OENDNUU5Q0I1RTc0MTM(Object obj) throws UnsupportedOperationException, IllegalArgumentException;

    private native void NkY0MkY3QzM3QTdBNDk2NkI2OTQ3MTcyNzAwOTQ1OUE(String str);

    private native void NkY4MUJEMDcwQzVDNDZGQ0E3NDRGNTNFOTczNDIzMzA(String str);

    private native void NkZBMkVFQTgxOUY3NDNEQzgwODkwNTZBNzk4NjcxMjk(int i2, int i3);

    private native int NzI3MDVFQzVBMzc3NEE5NTk4REY4M0NGNkM4QTdBQzIK();

    private native int NzIxQjI5RTQ5RjY2NDczMThFQUNEOUNCMDIyMjE2MjE();

    private native int NzM2NjgyQUM4NkVGNDg3RDk1MTJDQjczNjAwQTk5RDUK();

    private native int NzMzM0I3MkJDMjczNEEyQUEyRjIyRTI0QjNEQkU2QTc(int i2);

    private native int NzQ4OTQ5MTlDOTFENDQ1MDg4QzE4QTNBNUI2M0NDNEU();

    /* JADX INFO: Access modifiers changed from: private */
    public native void NzQxOUJFMDY1OTQ4NDU3NUJFNjMwNzlGRTA2Qjk0QjI();

    /* JADX INFO: Access modifiers changed from: private */
    public static native boolean Nzg5N0Y2MTU5OTY0NEQ0Mzk3NEM4NTM4MjRCMUQwQjI(int i2);

    private native void NzhDNzRDNjlFRTQxNDVBNUIzNUVGQTFBQTlGRTQ3Qzk();

    private native void NzhDNzRDNjlFRTQxNDVBNUIzNUVGQTFBQTlGRTQ3QzkAt(double d2, int i2);

    private native int Nzk2RDVGQTMzNjE3NDUxNjg1MjE2RDVCQkM2NDU3NDI(int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public boolean O() {
        VOSurfaceView vOSurfaceView = this.e;
        return (vOSurfaceView == null || vOSurfaceView.i() == null || this.e.i().getHolder() == null || this.e.i().getHolder().getSurface() == null || true != this.e.i().getHolder().getSurface().isValid()) ? false : true;
    }

    private native void ODE3MjU3ODA4QzY5NEFFM0I0NzhFRUQyOTgzN0VCRjA(String str);

    private final native void ODI1MEMzNDZDN0JBNDBERUJFRkUyNjM4QUFGN0I2OUI(Object obj, String str);

    private native int ODQ4MUVGQkIzQTVCNEJBRjg4NTNDM0YzOUIyNTVDNEM();

    private native void ODU5MDJDRUI1REJGNENBN0I1Mzk0MDcxNzNGRjc1Qzg();

    private native void ODVGQkJCOEUzODE1NEFGRkFCRUMzNTA0OTkzOERGRjU(String str);

    private native void ODYxNjhCNEVDRkZENDMzRjk0QkUwNkIyNjc4NjU2REY();

    private native long ODZEMEI1MzlBNjAzNEQyQjg4QjkxQzI3REIyOUE0NUIK();

    private native int ODdGQTQzQTNGOUFGNEIwRUE0QzdEMzZCODI5ODVEODM();

    private native void ODg4NDEyNzc4NTRFNEE4NkJDRTAyRjFBMERFM0Y5ODI(boolean z);

    private final native void ODkxRUU3ODZDMTJBNEZCN0I0QTM5NUQxQTkyRkM3OUE();

    private native void OEIyMzE0MkJDRUU5NENDMjkyNTc4NkM2RTExNTMzRTg(Object obj);

    private native int OENCQjg0N0ZDMTY4NEE0RDlGMTUyRDFCMjQwRjM4NEY();

    private native void OTEzNURFQkRGMEEyNDQ2NkE3NUZENTRCREI3QjNGRjY();

    private native void OTMxQTk4RTkwRTU5NEE3RDk1MzIzRjEwOTNBRjBBNkI(String str);

    private native void OTRDMTE1ODY2NzMwNEMyMkJEREVDNUQ3RTdBQ0UxNDE(int i2) throws IllegalStateException;

    private native void OTUyNTMxQkU1MzI4NDJDRjkyM0U2NDA2MzNBMDE3NUM(int i2);

    private native void OTY2RUFGQUI2NTVGNDQ5MDkxNTZDNUZEM0M2QUZGMzM(int i2) throws UnsupportedOperationException;

    private native void OThBQTI5QkYzNkI5NDJGNDkxRERGNzM3Qjg1RjAzODE(Object obj, int i2);

    private native String OTkyRUY4MTZGMDEzNEZEMjhGNDY5RTExNDIwRDAzMEYK();

    private native boolean OUM1NEQwMUQwRTVCNDM5MEI5M0E1MTFCQ0RDM0VEM0U() throws UnsupportedOperationException;

    private native void OURENjYyODJFNDg0NEVGNUI4RkM5MkU1NkIyQzkzQTY(boolean z);

    private native void OUZDQUI4QkQ4Rjg5NDFDM0EzQ0IzQzIyQTUxMUEyNzgK(boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public boolean P() {
        TextureView textureView;
        VOSurfaceView vOSurfaceView = this.e;
        if (vOSurfaceView == null || (textureView = (TextureView) vOSurfaceView.j()) == null) {
            return false;
        }
        return textureView.isAvailable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean Q() {
        return i(this.m329t7p639082847841);
    }

    private native void Q0M0MTM5NDMyRDUyNDY5NDg4NEUxQjRBMDhDNTkyRkQ(FileDescriptor fileDescriptor, long j2, long j3) throws IOException, IllegalArgumentException;

    private native void Q0M0MTM5NDMyRDUyNDY5NDg4NEUxQjRBMDhDNTkyRkQ(String str) throws IOException, IllegalArgumentException;

    private native void Q0M0MTM5NDMyRDUyNDY5NDg4NEUxQjRBMDhDNTkyRkQ(String str, Map<String, String> map) throws IOException, IllegalArgumentException;

    private native int[] Q0M3QUY2MjFGQkM5NEQ3MDgzQjE1NDMzN0RBMDcxOEU();

    private native void Q0NGM0E1N0MxOTFBNEM1OEJCOUY5MzEwOEEwQThFNDE();

    private native void Q0U2MjIxQkFBNjY3NEY2OEJCMERBRDc5MTlFOEZCMzY(String str, String str2);

    private native void Q0U5NzJGRUEwMkQ4NDczNTlGNTVGOEYzMDZFRTkwRkYK(int i2, int i3);

    private native int Q0ZCRThGNDg1MDI3NEVERUFEMzZENzk3MjMwNUY5MDU();

    private native void QTM3MEQ4OTNEODM1NEUwQ0I5NUVGMjYyMUE2QzBGM0EK(Object obj) throws UnsupportedOperationException;

    private native int QTYyMjcyNzI1ODY2NDUxMjlCNThDRjI4NTAzQjlENzM();

    private native void QTcyRDExOEU2QUU2NDRFRDg3RDRCREM3OTZGOTA2QUI(String str, int i2, int i3, String str2, String str3);

    private native boolean QTkwMDRDQTRDRDc0NDA2REIzRDM2RDM4MzM2MUYwQUQ();

    private native boolean QTlBRjkxNkNGQTZENDJGNUI0QjMzQUEwMTY4NjBGMjY();

    private native Object[] QUQ3Qzg3NEREOENFNDZEN0E2RUQ3NTIzM0Y2MjEwRDU();

    private native void QjJCQjVEQTZEMjQwNDg5MkFGMTRGQzg5M0QxNDAwMzc(boolean z);

    private native void QjJDQTI0RUMyQkMzNEJCOTlDRDRDNzlCMTdBM0U3MDIK(int i2);

    private static native void QjM0NUNDMjRFMEJDNDQzNzgzREI4OUVFMEQzQzM0NkQ(String str);

    private native void QjNBRDRGMTEwMTZCNDg2NDhDQTU1RTE4MTFCQ0U4QTM(String str);

    private native void QjZGMzUzQTM2QjlFNEYxMEJEN0I1MUJCQzM2M0JFMkUK(String str);

    private native void QjczODBFMTYxRDJCNDBFOUI4M0RFOTlGMjk1Rjk4NjcK(int i2) throws UnsupportedOperationException;

    private native void QjczODQ2ODQwOTU3NDNBMkE0OThENDA1Mzg1QURCMzU();

    private native void QjczODQ2ODQwOTU3NDNBMkE0OThENDA1Mzg1QURCMzUContentProtectionDrmObject();

    private native void QkNBOTk5NTU2MzNBNEY1RDg2NzEzMEZEMEI0QTdEQ0Y(int i2) throws UnsupportedOperationException;

    private native boolean QkUxQTcyRjI0QjU4NDMzM0IyNzlBNkExMUZDNEZFMUU(Object obj, int i2);

    private native void QkVGQjU2QzZBMkE2NDgyRTgwQUYxRjEzOUQ5OTAxNDg(Object obj, Object obj2, boolean z, boolean z2);

    private native void QzA0MkYyRDlCQ0E2NDg1ODgxNjBGNTE1NjIwOTkxQTM(Context context, String str, boolean z);

    private native void QzA1NkYwQUNGNkVGNEEyREI5ODBDMUI5MENCNzQ3Mjk();

    private native void QzAyQUI0NjcxMkU5NDhFMkI4QkE1MjM5QTIyNjg0NUU();

    private native Object[] QzE3NEU1Q0ZGNEI3NDE4RUIxMzUwM0YxRkZERjFFN0E();

    /* JADX INFO: Access modifiers changed from: private */
    public native void QzExOEMzRjhFNkY3NDY3MUE1QzQ0N0E0RDc3OTMxQjk(long j2, long j3);

    private native int QzJERjMwRDY2RjQ5NDA2MkI3Mjg5REY0OTYwMEJCRTgK();

    private native int QzMwQ0ExQjYyQTFCNDc5NDkyOEIwNzNDNzE1NEQxOTI();

    private native void QzQzREE3REVGRDcxNDRENzhFQ0MxQzA3RTQwOTg2QzkK();

    /* JADX INFO: Access modifiers changed from: private */
    public native void QzUwODQ2RThFM0YwNDVBQTk0RTRDRUY0RTcyNkM5RTM();

    private final native void QzdGRkY0NjFENTdBNDQ5RDk1NkRGQjRGMjAyOTYwOTc(Object obj, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public void R() throws Exception {
        t();
        VOPlayerType playerType = VOPlayerType.TYPE_UNKNOWN;
        if (this.z1) {
            playerType = getPlayerType();
        }
        if (VOPlayerType.TYPE_FOLLOWER != playerType) {
            setVolume(this.p, VOVolumeType.VOVolumeTypePlayer);
        }
    }

    private native void RDEyNzE2QTUzN0VENDdEODg5OEUxRjYwNTg4MjhENjI(boolean z);

    private native int RDFDRjA1NDYzNDRENDM5RkExOTU3NkY5NjU2OEMzNTcK();

    private native int RDNEMkM2NUU0REYyNDhFNTlFMUY3NzI0OUQzRTcwMkY(Object obj, int i2, boolean z);

    private native boolean RDNEQkFDODc4RjI2NEI5QUEzMUI0NDBCMjNFQTI5NjE(Object obj, int i2);

    private native Object[] RDNEQkFDODc4RjI2NEI5QUEzMUI0NDBCMjNFQTI5NjETracks() throws UnsupportedOperationException;

    private native void RDQ4NTRCNzI5MjM2NENFNkI2MTI2MkZDNUUzMUNDNzA(int i2) throws UnsupportedOperationException;

    private final native void RDU0MjZFNDJBMDRBNEYyNEFFQUI2MDFEOTIxQjY1Mzg(double d2);

    private native void RERGRDQ5MjE4RTZCNDcxNEJGODU4QzQ1NkZFRUY2Nzk(String str, Object obj) throws UnsupportedOperationException, IllegalArgumentException;

    private native Object REY2MkUyNDE4NTU0NDFFOTlCM0E1NDYzRTMxNTdERDg(int i2);

    private native void REZFQTRFNUFCRTA3NEFGRkJBQTlGMDcxNEFFOUM0MUE(int i2);

    private native int RTMzNjdGRjI1NEE4NENENjk5QUFDM0Y1RkQ3QkQzQkE();

    private native void RTRENzMyMjFEQzEwNDI2QTk3MTgwRTcxQjQxQUM4NUMK(boolean z, int i2) throws IllegalStateException, IOException;

    private native int RTU3REI2NzU3RTQ4NDgxODg0N0RGRkFCQTIwNEFDQTA();

    private native void RTVDNUYxOEY5QjU5NEIyQUIwQkUyMkRBMzA2NUY5Rjg();

    private native void RThBQTg4OURFMzk3NEVFQkFFNEQ5MzY0RENDQURGNTkK(boolean z);

    private native void RUExNTAyMThDNDVCNDE2NUI1QzhFQUU2MDgxNDdCOTgK(boolean z);

    private native void RUI2NkNCMjhBOEY2NDQ1REFDQ0M2RjkyRkI1Qzk1NzQ(String str, int i2, Object obj) throws IOException, IllegalArgumentException;

    private native void RUJDNDhENUIxOTA5NDM4MjkwRDQ3NkExNzA4QTdBOTQ(String str);

    private native void RUM2MUFGQURBRTFGNEEzMEEwOTZEMTNBMUFENEEwRkY(int i2, int i3, int i4);

    private native void RUMwMjQ5QkY3QUNGNDBGRkI0RjZGMzk2MEUzMTA5MTU(int i2) throws UnsupportedOperationException;

    private native void RUQ3QTZDRTFBRDAyNDFBNDk2RDZDMTE0Q0U1MEM2QkI();

    private native boolean RURBNkY0M0FBRUYzNEUwMkIwQTA5NDFFM0YzM0VFN0I() throws UnsupportedOperationException;

    private native int[] RUVBQ0EzNjlBRkU1NEEyNTk2NjgxOTI1NTZCQjU0MUM();

    private native int RjAyNzJGMjIwODYwNDFCQ0E4MDcwRUE5OUIxQjA5M0U();

    private native boolean RjBEMEVFQzFERURDNEQ1QUI2OTE5NUEwM0FCRUVFNUU();

    private native int RjE2QzkzM0YyOTI4NDhCNEFDMzhCQzExMzgxQUE2QUEK();

    private native void RjM3RURFNzE2NTRENDBEREI5MkIzRTBCQjk0QkMyOUM();

    private native void RjNGRjZGMzAxMkVBNEZGNUFGNTUwMjVFOTExRkE4NzY(String str);

    private native void RjQ2QkJGQ0U5MkY3NDMwMjkzREY2MjNDMDU2NUJDOUU(int i2, float f2, int i3, int i4, int i5);

    private native void RjVDMDU0MDhDMzlFNDA0QTg4OTQ1N0ExQkUxQTk1Q0E(String str);

    private native void RjdEOEIyOUY2N0QwNEYwM0I5RUY0RTVGMTJFQ0VCQzg(String str, String str2);

    private native int Rjg1NkNBRDFCQ0JGNDI4NDg4MEJFNkY3RTNFQjYyQkY();

    private native void RjhBMEQwQkMyNDhGNEI1RDgyRDc1QjI2MjMwREQxMEQK(int i2);

    private native void RkI3RjYyOUNCMkZCNDVFRDk1RTgwRTFEMEYxQ0E2QkYK(Object obj);

    private native int RkYxQzg4QUUwNkMwNDk0QTg3MUFGNkU4MDc0QUQzRTc(String str, int i2) throws UnsupportedOperationException;

    private native Object[] TDhEDQUI4QkQIzQzIyQTUxMUQ0IzQzIyQTDhERUE4QJD();

    /* JADX INFO: Access modifiers changed from: private */
    public native int Y2QTMDA1RjY0MjkMzlFM05MkY3QBNNEFCNTQjgl4ERE(int i2);

    private native void YTVhZTU2NzE0NGM4NGNiZmEwZWRkYWUwNWFkMjMwZWQK(float f2, float f3, float f4);

    private native void YTgwNjNmMjZiOGU2NDhlOGI2MmMxMGQxNmJiYTZhNjcK(String str);

    private int a(DrmAgent.EDrmType eDrmType) {
        VOMediaDrmAgent agentVodrm;
        if (DrmAgent.EDrmType.DRM_TYPE_PLAYREADY == eDrmType) {
            if (this.C0 == null && this.A0 == null) {
                return 0;
            }
            int securityLevel = getAgentPlayready(this.z0).getSecurityLevel();
            if (securityLevel != 0) {
                return securityLevel;
            }
            agentVodrm = getAgentPlayready(this.z0);
        } else if (DrmAgent.EDrmType.DRM_TYPE_WIDEVINE == eDrmType && this.E0 != null) {
            int securityLevel2 = getAgentWidevine().getSecurityLevel();
            if (securityLevel2 != 0) {
                return securityLevel2;
            }
            agentVodrm = getAgentWidevine();
        } else {
            if (DrmAgent.EDrmType.DRM_TYPE_VODRM != eDrmType || this.D0 == null) {
                DrmAgent.EDrmType eDrmType2 = DrmAgent.EDrmType.DRM_TYPE_VERIMATRIX;
                return 0;
            }
            int securityLevel3 = getAgentVodrm().getSecurityLevel();
            if (securityLevel3 != 0) {
                return securityLevel3;
            }
            agentVodrm = getAgentVodrm();
        }
        return agentVodrm.getMaxSecurityLevel();
    }

    private Boolean a(Boolean bool) {
        boolean zBooleanValue = bool.booleanValue();
        if (!VOSubtitle.class.getName().contains("ubtitl")) {
            zBooleanValue = true;
        }
        return (zBooleanValue && VOPlayer.class.getName().contains("layer")) ? false : true;
    }

    private String a(Context context, String str) {
        if (str == null || !str.contains("/android_asset/") || context == null) {
            return str;
        }
        File filesDir = context.getFilesDir();
        if (!E1) {
            String strReplaceAll = str.replaceAll("file:///android_asset/", "").replaceAll("/android_asset/", "");
            try {
                AssetManager assets = context.getAssets();
                String[] list = assets.list((strReplaceAll.length() <= 0 || strReplaceAll.charAt(strReplaceAll.length() - 1) != '/') ? strReplaceAll : strReplaceAll.substring(0, strReplaceAll.length() - 1));
                if (list.length > 0) {
                    for (String str2 : list) {
                        if (str2 != null && str2.startsWith("dc=")) {
                            try {
                                InputStream inputStreamOpen = assets.open(strReplaceAll + str2);
                                File file = new File(filesDir.getAbsolutePath() + "/tmpforqp/" + str2);
                                file.getParentFile().mkdir();
                                FileOutputStream fileOutputStream = new FileOutputStream(file);
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int i2 = inputStreamOpen.read(bArr);
                                    if (i2 <= 0) {
                                        break;
                                    }
                                    fileOutputStream.write(bArr, 0, i2);
                                }
                                fileOutputStream.close();
                                inputStreamOpen.close();
                            } catch (IOException unused) {
                            }
                        }
                    }
                }
                E1 = true;
            } catch (IOException unused2) {
                return null;
            }
        }
        return filesDir.getAbsolutePath() + "/tmpforqp";
    }

    private String a(String str) {
        boolean z = str.contains("_pr_") || str.contains("_wvpr_") || str.contains("_prwv_") || str.contains("_plr_");
        boolean zContains = str.contains("_general_");
        if (z) {
            return zContains ? "SL150" : "SL2000";
        }
        return "NA";
    }

    private void a(double d2) {
        if (this.Q0 || this.g1) {
            int[] httpStreamingSeekableRange = getHttpStreamingSeekableRange();
            ((com.viaccessorca.voplayer.b) this.c1).b(httpStreamingSeekableRange[1] - httpStreamingSeekableRange[0]);
        }
        if (-1.0d == d2) {
            d2 = ((com.viaccessorca.voplayer.b) this.c1).d();
        }
        ((com.viaccessorca.voplayer.b) this.c1).a(d2, (VOAudioTrack[]) QzE3NEU1Q0ZGNEI3NDE4RUIxMzUwM0YxRkZERjFFN0E(), this.d1, (VOSubtitleTrack[]) RDNEQkFDODc4RjI2NEI5QUEzMUI0NDBCMjNFQTI5NjETracks(), this.e1, getDuration() == 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, int i3) {
        if (this.e == null || !QTlBRjkxNkNGQTZENDJGNUI0QjMzQUEwMTY4NjBGMjY()) {
            return;
        }
        this.e.a(-1, 20004);
    }

    private static void a(Context context) {
        if (context == null) {
            return;
        }
        String str = D1;
        if ((str != null ? new File(str).exists() : false) && D1 != null) {
            return;
        }
        try {
            InputStream inputStreamOpen = context.getAssets().open("quickplayer.lic");
            String str2 = context.getFilesDir().getAbsolutePath() + "/quickplayer.lic";
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
            byte[] bArr = new byte[1024];
            while (true) {
                int i2 = inputStreamOpen.read(bArr);
                if (i2 <= 0) {
                    fileOutputStream.close();
                    inputStreamOpen.close();
                    D1 = str2;
                    return;
                }
                fileOutputStream.write(bArr, 0, i2);
            }
        } catch (IOException unused) {
        }
    }

    private void a(Context context, boolean z) {
        boolean zIsOsTampered;
        String uniqueIdentifier;
        String strValueOf = String.valueOf(b(context));
        try {
            zIsOsTampered = isOsTampered(context);
        } catch (VOException e2) {
            e2.printStackTrace();
            zIsOsTampered = false;
        }
        boolean z2 = zIsOsTampered;
        try {
            uniqueIdentifier = getUniqueIdentifier(context);
        } catch (VOException unused) {
            uniqueIdentifier = VOPlaybackSessionReport.UNKNOWN_SVALUE;
        }
        new VOPlaybackSessionReport(context, z, z2, uniqueIdentifier, strValueOf);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Message message) {
        int i2 = message.arg1;
        if (2201 == i2) {
            VOSurfaceView vOSurfaceView = this.e;
            if (vOSurfaceView != null) {
                vOSurfaceView.clearAllSubtitles();
            }
            VOTIFVideoOverlayView vOTIFVideoOverlayView = this.f;
            if (vOTIFVideoOverlayView != null) {
                vOTIFVideoOverlayView.clearAllSubtitles();
                return;
            }
            return;
        }
        if (this.Q) {
            VOSubtitle subtitle = null;
            if (2100 == i2) {
                try {
                    subtitle = getSubtitle(message.arg2);
                } catch (UnsupportedEncodingException unused) {
                }
            }
            VOSurfaceView vOSurfaceView2 = this.e;
            if (vOSurfaceView2 != null) {
                vOSurfaceView2.a(message.arg1, message.arg2, subtitle);
            }
            VOTIFVideoOverlayView vOTIFVideoOverlayView2 = this.f;
            if (vOTIFVideoOverlayView2 != null) {
                vOTIFVideoOverlayView2.a(message.arg1, message.arg2, subtitle);
            }
        }
    }

    private void a(DrmAgent.EDrmType eDrmType, EDrmEngineType eDrmEngineType) {
        this.x0 = eDrmType;
        MzQ5MkI2OTQxQ0UwNDgyQUExQjg3QjFDNUZGQzgxODM(eDrmType.ordinal(), eDrmEngineType.ordinal(), a(eDrmType));
        Object obj = this.c1;
        if (obj != null) {
            ((com.viaccessorca.voplayer.b) obj).a(eDrmType);
        }
    }

    private void a(VOException vOException) {
        int i2 = this.G0;
        if (i2 == 0) {
            switch (c.f76a[vOException.getStatus().ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    i2 = 3103;
                    break;
                case 6:
                    i2 = 3104;
                    break;
                case 7:
                    i2 = MEDIA_INFO_GENERIC_HACKING_DETECTED;
                    break;
                case 8:
                case 9:
                case 10:
                    i2 = MEDIA_ERROR_DRM_NETWORK_COMMUNICATION_FAILURE;
                    break;
                case 11:
                    i2 = MEDIA_ERROR_DRM_TRUSTED_TIME_NOT_SET;
                    break;
                case 12:
                    i2 = 3101;
                    break;
                case 13:
                case 14:
                    i2 = MEDIA_ERROR_DRM_INIT_PARAMS_NOT_SET;
                    break;
                default:
                    i2 = 3102;
                    break;
            }
        }
        if (true == this.H0) {
            g(i2);
        } else {
            this.G0 = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(VOFingerprintData vOFingerprintData) {
        ByteBuffer byteBufferA;
        int iE;
        int iB;
        int iD;
        int iC;
        ByteBuffer byteBufferA2;
        int iE2;
        int iB2;
        int iD2;
        int iC2;
        VOSurfaceView vOSurfaceView = this.e;
        if (vOSurfaceView != null) {
            if (vOFingerprintData != null) {
                try {
                    byteBufferA2 = vOFingerprintData.a();
                    iE2 = vOFingerprintData.e();
                    iB2 = vOFingerprintData.b();
                    iD2 = vOFingerprintData.d();
                    iC2 = vOFingerprintData.c();
                } catch (Exception unused) {
                    return;
                }
            } else {
                byteBufferA2 = null;
                iE2 = 0;
                iB2 = 0;
                iD2 = 0;
                iC2 = 0;
            }
            vOSurfaceView.a(byteBufferA2, iE2, iB2, iD2, iC2);
            return;
        }
        VOTIFVideoOverlayView vOTIFVideoOverlayView = this.f;
        if (vOTIFVideoOverlayView != null) {
            if (vOFingerprintData != null) {
                byteBufferA = vOFingerprintData.a();
                iE = vOFingerprintData.e();
                iB = vOFingerprintData.b();
                iD = vOFingerprintData.d();
                iC = vOFingerprintData.c();
            } else {
                byteBufferA = null;
                iE = 0;
                iB = 0;
                iD = 0;
                iC = 0;
            }
            vOTIFVideoOverlayView.a(byteBufferA, iE, iB, iD, iC);
        }
    }

    private void a(d dVar) {
        N0NEREQxRkM4NkFBNEZDNTk4RTM5MUNGMDIwMDVENjE(dVar.ordinal());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:80:0x0102  */
    public void a(VOSurfaceView vOSurfaceView, int i2) {
        boolean z;
        boolean z2;
        Surface surface;
        if (!this.x && vOSurfaceView != null) {
            this.e = vOSurfaceView;
            return;
        }
        if (vOSurfaceView != null) {
            boolean z3 = false;
            int iRDNEMkM2NUU0REYyNDhFNTlFMUY3NzI0OUQzRTcwMkY = RDNEMkM2NUU0REYyNDhFNTlFMUY3NzI0OUQzRTcwMkY(this.q, this.r, false);
            vOSurfaceView.isSurfaceValid();
            this.e = vOSurfaceView;
            if (vOSurfaceView != null && this.c0) {
                vOSurfaceView.a(true);
            }
            if (this.e != null) {
                e(this.n1);
            }
            if (this.e.e()) {
                u();
            }
            if (i2 == 0 || 1 == i2) {
                if (!vOSurfaceView.isSurfaceValid() || vOSurfaceView.g() == null || true == S1) {
                    try {
                        vOSurfaceView.c(this.j);
                        try {
                            S1 = false;
                            this.m329t7p639082847843 = null;
                        } catch (IllegalArgumentException unused) {
                        }
                        z = true;
                    } catch (IllegalArgumentException unused2) {
                        z = false;
                    }
                } else {
                    VOSurfaceViewOpenGL vOSurfaceViewOpenGLG = this.e.g();
                    this.m329t7p639082847843 = vOSurfaceViewOpenGLG;
                    vOSurfaceViewOpenGLG.a(new a());
                    VOSurfaceView vOSurfaceView2 = this.g;
                    z = (vOSurfaceView2 == null || vOSurfaceView2 == this.e) ? false : true;
                    if (8 != iRDNEMkM2NUU0REYyNDhFNTlFMUY3NzI0OUQzRTcwMkY) {
                        z2 = true;
                    }
                }
                z2 = false;
            } else {
                z = false;
                z2 = false;
            }
            if (i2 == 0) {
                VOSystemInfo vOSystemInfo = this.q;
                if (16 > vOSystemInfo.androidVersion || !QkUxQTcyRjI0QjU4NDMzM0IyNzlBNkExMUZDNEZFMUU(vOSystemInfo, this.r)) {
                    z3 = z2;
                } else if (7 == iRDNEMkM2NUU0REYyNDhFNTlFMUY3NzI0OUQzRTcwMkY) {
                    this.m329t7p6390828478403 = null;
                    if (vOSurfaceView.i() == null) {
                        vOSurfaceView.b(this.j);
                    }
                    if (O()) {
                        surface = vOSurfaceView.i().getHolder().getSurface();
                        this.m329t7p639082847844 = surface;
                        z3 = true;
                    }
                } else if (1 == iRDNEMkM2NUU0REYyNDhFNTlFMUY3NzI0OUQzRTcwMkY) {
                    Object obj = this.m329t7p6390828478403;
                    if (obj != null) {
                        try {
                            vOSurfaceView.a(this.j, obj);
                        } catch (IllegalArgumentException | NullPointerException unused3) {
                        }
                    } else {
                        try {
                            SurfaceTexture surfaceTexture = ((TextureView) vOSurfaceView.j()).getSurfaceTexture();
                            this.m329t7p6390828478403 = surfaceTexture;
                            if (surfaceTexture == null) {
                                return;
                            }
                        } catch (NullPointerException unused4) {
                            return;
                        }
                    }
                    if (P()) {
                        surface = new Surface((SurfaceTexture) this.m329t7p6390828478403);
                        this.m329t7p639082847844 = surface;
                        z3 = true;
                    }
                } else {
                    if (8 != iRDNEMkM2NUU0REYyNDhFNTlFMUY3NzI0OUQzRTcwMkY) {
                        return;
                    }
                    VOSurfaceViewOpenGL vOSurfaceViewOpenGL = this.m329t7p639082847843;
                    if (vOSurfaceViewOpenGL != null) {
                        VOSurfaceViewOpenGL.h hVarD = vOSurfaceViewOpenGL.d();
                        if (hVarD == null) {
                            try {
                                vOSurfaceView.a(this.j);
                            } catch (IllegalArgumentException | NullPointerException unused5) {
                            }
                        } else {
                            this.m329t7p6390828478403 = hVarD;
                            surface = new Surface((SurfaceTexture) this.m329t7p6390828478403);
                            this.m329t7p639082847844 = surface;
                            z3 = true;
                        }
                    }
                }
            } else {
                z3 = z2;
            }
            if (z) {
                try {
                    this.e.b(this.t);
                } catch (IllegalArgumentException unused6) {
                }
            }
            if (z3) {
                OThBQTI5QkYzNkI5NDJGNDkxRERGNzM3Qjg1RjAzODE(this.q, this.r);
                VOSurfaceView vOSurfaceView3 = this.g;
                if (vOSurfaceView3 != null && vOSurfaceView3 != this.e) {
                    try {
                        vOSurfaceView3.d();
                        if (i2 == 0 || 1 == i2) {
                            this.H = true;
                        }
                    } catch (Exception unused7) {
                    }
                }
                this.g = this.e;
            }
            changeDisplayMode(this.w);
        } else {
            VOSurfaceView vOSurfaceView4 = this.e;
            if (vOSurfaceView4 != null) {
                if (vOSurfaceView4.e()) {
                    u();
                    this.e = null;
                }
                this.e = null;
                if (i2 == 0 || 1 == i2) {
                    this.m329t7p639082847843 = null;
                }
                this.m329t7p639082847844 = null;
                OThBQTI5QkYzNkI5NDJGNDkxRERGNzM3Qjg1RjAzODE(this.q, this.r);
            }
        }
        u();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Object obj) {
        VOSurfaceView vOSurfaceView = this.e;
        if (vOSurfaceView == null) {
            q();
            return;
        }
        boolean z = false;
        int i2 = ((int[]) obj)[0] & 268435455;
        int iL = vOSurfaceView.l() & 268435455;
        if (iL < i2 && 4150 < i2 - iL) {
            q();
            return;
        }
        View view = null;
        for (int childCount = this.e.k().getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.e.k().getChildAt(childCount);
            if (view != null) {
                if (childAt.getClass() != VOSurfaceViewAmlogic.class && childAt.getClass() != VOSurfaceViewOpenGL.class && childAt.getClass() != com.viaccessorca.voplayer.k.class && childAt.getClass() != com.viaccessorca.voplayer.m.class) {
                    q();
                    return;
                } else if (view.getWidth() < (childAt.getWidth() * 70) / 100) {
                    q();
                    return;
                } else if (view.getHeight() < (childAt.getHeight() * 70) / 100) {
                    q();
                    return;
                }
            }
            if (childAt.getClass() != q.class && childAt.getClass() != com.viaccessorca.voplayer.o.class && childAt.getClass() != com.viaccessorca.voplayer.p.class) {
                view = null;
            } else if (z) {
                q();
                return;
            } else if (childAt.getVisibility() != 0) {
                q();
                return;
            } else {
                z = true;
                view = childAt;
            }
        }
        if (z) {
            a(d.WMK_SURFACE_CHECKED);
        } else {
            q();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Map<UUID, Integer> map) {
        if (map == null || map.size() <= 0) {
            return;
        }
        this.p0 = true;
        for (Map.Entry<UUID, Integer> entry : map.entrySet()) {
            if (entry.getKey() != null) {
                nativeMP_setContentProtectionKeyIdStatus(VOUtils.uuidToBytesArray(entry.getKey()), entry.getValue().intValue());
            }
        }
        this.p0 = false;
        synchronized (this.o0) {
            this.o0.notifyAll();
        }
    }

    private void a(String[] strArr) {
        this.v0 = strArr;
        Object obj = this.c1;
        if (obj != null) {
            ((com.viaccessorca.voplayer.b) obj).a(strArr);
        }
        com.viaccessorca.drm.impl.d dVar = this.A0;
        if (dVar == null || strArr == null) {
            return;
        }
        MzkwNDdDNERCNjlBNDM1MTg1RDIzMzNBRTg5MEI4OUQ(dVar.generateCookiesHeader(strArr));
    }

    private static synchronized boolean a(Context context, boolean z, boolean z2, boolean z3) {
        File filesDir;
        boolean zIsPlatformBenchmarkUpToDate = isPlatformBenchmarkUpToDate(context);
        if (!zIsPlatformBenchmarkUpToDate && (filesDir = context.getFilesDir()) != null) {
            File file = new File(filesDir.getAbsolutePath() + "/ExperienceHwH264.txt");
            if (file.exists()) {
                file.delete();
            }
            File file2 = new File(filesDir.getAbsolutePath() + "/ExperienceHwHevc.txt");
            if (file2.exists()) {
                file2.delete();
            }
            File file3 = new File(filesDir.getAbsolutePath() + "/ExperienceSwH264.txt");
            if (file3.exists()) {
                file3.delete();
            }
            File file4 = new File(filesDir.getAbsolutePath() + "/ExperienceSwHevc.txt");
            if (file4.exists()) {
                file4.delete();
            }
        }
        if (zIsPlatformBenchmarkUpToDate && !z) {
            return true;
        }
        B1.size();
        if (!z2 && B1.size() > 0) {
            return false;
        }
        File filesDir2 = context.getFilesDir();
        if (filesDir2 == null) {
            return false;
        }
        VOSystemInfo systemInfo = VOSystemInfoRetriever.getSystemInfo(context);
        retrieveSdkInternalVersion(context);
        if (!N0UxMEYyODI1QkE0NDNCN0JDMTExQzZCQjhEODE4QjY("lib.so", filesDir2.getAbsolutePath() + "/", systemInfo.screenWidth, systemInfo.screenHeight, z3)) {
            return false;
        }
        j(context);
        return true;
    }

    private static boolean a(VOPlayer vOPlayer, int i2, Object obj) {
        VOSurfaceView vOSurfaceView;
        VOSurfaceView vOSurfaceView2;
        switch (i2) {
            case 20001:
                if (obj != null && (vOSurfaceView = vOPlayer.e) != null && !vOPlayer.u) {
                    Object[] objArr = (Object[]) obj;
                    if (!vOSurfaceView.a(((int[]) objArr[0])[0], (byte[]) objArr[2], ((int[]) objArr[1])[0], ((int[]) objArr[1])[1], ((int[]) objArr[1])[2], ((int[]) objArr[1])[3])) {
                        vOPlayer.q();
                    }
                }
                return true;
            case 20002:
            case 20003:
            case 20004:
            case 20008:
                VOSurfaceView vOSurfaceView3 = vOPlayer.e;
                if (vOSurfaceView3 != null && !vOPlayer.u && !vOSurfaceView3.a(((int[]) obj)[0], i2)) {
                    vOPlayer.q();
                }
                return true;
            case 20005:
            case 20006:
            default:
                return false;
            case 20007:
                if (obj != null && (vOSurfaceView2 = vOPlayer.e) != null && !vOPlayer.u) {
                    Object[] objArr2 = (Object[]) obj;
                    if (!vOSurfaceView2.a((byte[]) objArr2[1], ((int[]) objArr2[0])[0])) {
                        vOPlayer.q();
                    }
                }
                return true;
        }
    }

    private static boolean a(String str, String[] strArr) {
        for (String str2 : strArr) {
            if (str.contains(str2)) {
                return true;
            }
        }
        return false;
    }

    private static int b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    private String b(String str) {
        if (str == null || !str.contains("/android_asset/")) {
            return str;
        }
        String strReplaceAll = str.replaceAll("file:///android_asset/", "").replaceAll("/android_asset/", "");
        File filesDir = this.m329t7p639082847841.getFilesDir();
        try {
            InputStream inputStreamOpen = this.m329t7p639082847841.getAssets().open(strReplaceAll);
            String str2 = filesDir.getAbsolutePath() + "/FaasDbOfflineCopy.zip";
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
            byte[] bArr = new byte[1024];
            while (true) {
                int i2 = inputStreamOpen.read(bArr);
                if (i2 <= 0) {
                    fileOutputStream.close();
                    inputStreamOpen.close();
                    return str2;
                }
                fileOutputStream.write(bArr, 0, i2);
            }
        } catch (IOException unused) {
            return str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2, int i3) {
        if (i2 == 1000 && i3 == 1002) {
            VOSurfaceView vOSurfaceView = this.e;
            if (vOSurfaceView != null) {
                try {
                    vOSurfaceView.a(b());
                } catch (IllegalArgumentException | NullPointerException unused) {
                }
                this.e.a(this.w, getVideoWidth(), getVideoHeight());
                return;
            }
            VOTIFVideoOverlayView vOTIFVideoOverlayView = this.f;
            if (vOTIFVideoOverlayView != null) {
                vOTIFVideoOverlayView.setVideoPixelAspectRatio(b());
                this.f.a(this.w, getVideoWidth(), getVideoHeight());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        OUZDQUI4QkQ4Rjg5NDFDM0EzQ0IzQzIyQTUxMUEyNzgK(z);
    }

    private void b(String[] strArr) {
        this.w0 = strArr;
        Object obj = this.c1;
        if (obj != null) {
            ((com.viaccessorca.voplayer.b) obj).b(strArr);
        }
        if (this.A0 == null || strArr == null) {
            return;
        }
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (strArr[i2] != null) {
                RjVDMDU0MDhDMzlFNDA0QTg4OTQ1N0ExQkUxQTk1Q0E(strArr[i2]);
            }
        }
    }

    private int c(Context context) {
        int i2 = this.b;
        if (true == Nzg5N0Y2MTU5OTY0NEQ0Mzk3NEM4NTM4MjRCMUQwQjI(K1) || true == this.b0) {
            return i2;
        }
        int i3 = VOSystemInfoRetriever.getSystemInfo(context).screenHeight;
        if (720 <= i3) {
            return i3;
        }
        return 640;
    }

    private String c(String str) {
        com.viaccessorca.drm.impl.f fVar = new com.viaccessorca.drm.impl.f(this.m329t7p639082847841, this);
        return "Plugin:" + (fVar.isSupported() ? fVar.getMaxSecurityLevelString() : "NA") + " Native:" + a(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(VOPlayer vOPlayer, int i2, int i3) {
        if (vOPlayer == null) {
            return;
        }
        VOCrashReporter.sendErrorReport(vOPlayer, "0x" + Integer.toHexString(i2) + " & 0x" + Integer.toHexString(i3), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        if (z) {
            this.u = true;
            if (!isCasting()) {
                MDY2RjAyREQzOUMwNDg3RkIwRjEyQjQ3MjQ3NDZBODQ(true);
            }
            C();
            return;
        }
        this.u = false;
        if (isCasting()) {
            return;
        }
        MDY2RjAyREQzOUMwNDg3RkIwRjEyQjQ3MjQ3NDZBODQ(false);
    }

    private Bitmap d(int i2) {
        int iRTMzNjdGRjI1NEE4NENENjk5QUFDM0Y1RkQ3QkQzQkE = RTMzNjdGRjI1NEE4NENENjk5QUFDM0Y1RkQ3QkQzQkE();
        int iMjNENjlBNTMzNDcyNEZFQUI1QTI3NjJDNzQ3OEMwMUQ = MjNENjlBNTMzNDcyNEZFQUI1QTI3NjJDNzQ3OEMwMUQ();
        if (iRTMzNjdGRjI1NEE4NENENjk5QUFDM0Y1RkQ3QkQzQkE <= 0 || iMjNENjlBNTMzNDcyNEZFQUI1QTI3NjJDNzQ3OEMwMUQ <= 0) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iRTMzNjdGRjI1NEE4NENENjk5QUFDM0Y1RkQ3QkQzQkE, iMjNENjlBNTMzNDcyNEZFQUI1QTI3NjJDNzQ3OEMwMUQ, Bitmap.Config.RGB_565);
        ShortBuffer shortBufferAsShortBuffer = ByteBuffer.allocateDirect(iRTMzNjdGRjI1NEE4NENENjk5QUFDM0Y1RkQ3QkQzQkE * iMjNENjlBNTMzNDcyNEZFQUI1QTI3NjJDNzQ3OEMwMUQ * 2).asShortBuffer();
        shortBufferAsShortBuffer.position(0);
        MkI0Nzc4NDA3ODZFNDgzQUIyQjY1NzM1N0QzRTkyNTI(shortBufferAsShortBuffer, iRTMzNjdGRjI1NEE4NENENjk5QUFDM0Y1RkQ3QkQzQkE, iMjNENjlBNTMzNDcyNEZFQUI1QTI3NjJDNzQ3OEMwMUQ, i2);
        shortBufferAsShortBuffer.position(0);
        bitmapCreateBitmap.copyPixelsFromBuffer(shortBufferAsShortBuffer);
        return bitmapCreateBitmap;
    }

    private String d(String str) {
        com.viaccessorca.drm.impl.m mVar = new com.viaccessorca.drm.impl.m(this.m329t7p639082847841, this);
        return "Plugin:" + (mVar.isSupported() ? mVar.getMaxSecurityLevelString() : "NA") + " Native:" + (str.contains("vodrm") ? new com.viaccessorca.drm.impl.l(this.m329t7p639082847841, this).getMaxSecurityLevelString() : "NA");
    }

    private void d(boolean z) {
        try {
            this.T = z;
            MEUyQTZFQzM5NEY3NEVGMDlGRERGQ0YxN0QyMUFFM0U(z);
        } catch (IllegalStateException | UnsupportedOperationException unused) {
        }
    }

    private static boolean d(Context context) {
        k(context);
        return m329t7p6390828478413;
    }

    protected static String e(Context context) {
        k(context);
        return m329t7p6390828478411;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        String licenseAcquisitionURL;
        if (this.N) {
            this.M = i.CREATED;
            return;
        }
        a(this.x0, this.z0);
        try {
            this.y0 = H();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            if (c.b[this.z0.ordinal()] != 1) {
                if (this.x0 == DrmAgent.EDrmType.DRM_TYPE_PLAYREADY) {
                    if (this.A0 != null) {
                        h(this.A0.getLicenseAcquisitionURL());
                        f(this.A0.getLicenseAcquisitionCustomData());
                        g(this.A0.getLicenseAcquisitionURLParameters());
                        a(this.A0.getLicenseAcquisitionCookies());
                        OTMxQTk4RTkwRTU5NEE3RDk1MzIzRjEwOTNBRjBBNkI(this.A0.getPersonalizationServerUrl());
                        NDUxODFCQ0M1QUZBNEM0MkEwMzI3REE1ODgwRTMyMjg(this.A0.getPersonalizationAppVersion());
                        RjdEOEIyOUY2N0QwNEYwM0I5RUY0RTVGMTJFQ0VCQzg(this.A0.getPersonalizationPublicData(), null);
                        b(this.A0.getLicenseAcquisitionCustomHeaders());
                    } else if (this.B0 != null) {
                        h(this.B0.b());
                        f(this.B0.a());
                        g(this.B0.c());
                        e(this.B0.d());
                    }
                }
                if (this.F0 != null) {
                    i(this.F0.a());
                    j(this.F0.b());
                }
            } else {
                VOBaseMediaDrmAgent currentMediaDrmAgent = getCurrentMediaDrmAgent();
                if (currentMediaDrmAgent != null) {
                    String licenseAcquisitionURLParameters = currentMediaDrmAgent.getLicenseAcquisitionURLParameters();
                    if (licenseAcquisitionURLParameters == null || licenseAcquisitionURLParameters.length() <= 0) {
                        licenseAcquisitionURL = currentMediaDrmAgent.getLicenseAcquisitionURL();
                    } else {
                        licenseAcquisitionURL = currentMediaDrmAgent.getLicenseAcquisitionURL() + licenseAcquisitionURLParameters;
                    }
                    h(licenseAcquisitionURL);
                    a(currentMediaDrmAgent.getLicenseAcquisitionCookies());
                    b(currentMediaDrmAgent.getLicenseAcquisitionCustomHeaders());
                }
            }
            if (this.N) {
                this.M = i.CREATED;
                return;
            }
            VOSurfaceView vOSurfaceView = this.e;
            if (vOSurfaceView != null) {
                try {
                    vOSurfaceView.a(RDNEMkM2NUU0REYyNDhFNTlFMUY3NzI0OUQzRTcwMkY(this.q, this.r, false), this.j);
                } catch (Exception unused) {
                }
            }
            if (this.N) {
                this.M = i.CREATED;
            }
        } catch (Exception unused2) {
            h(MEDIA_ERROR_ILLEGAL_STATE);
        }
    }

    private void e(String str) {
        NkM0MkM1RDBEM0E5NEYzMTlGQjI0Q0FEQ0RDQjZCNjg(a(this.m329t7p639082847841, str));
    }

    private void e(boolean z) {
        if ((this.e == null && this.f == null) ? false : true) {
            if (z) {
                try {
                    if (this.m329t7p639082847841 != null) {
                        String str = this.m329t7p639082847841.getPackageName() + " IS NOT REGISTERED";
                        if (this.e != null) {
                            this.e.a(str);
                        }
                        if (this.f != null) {
                            this.f.a(str);
                            return;
                        }
                        return;
                    }
                } catch (Exception unused) {
                    return;
                }
            }
            if (this.e != null) {
                this.e.a((String) null);
            }
            if (this.f != null) {
                this.f.a(null);
            }
        }
    }

    private boolean e(int i2) {
        switch (i2) {
            case 1:
            case 2:
            case 7:
            case 8:
                return Build.VERSION.SDK_INT >= 16;
            case 3:
            case 4:
                return 19 >= Build.VERSION.SDK_INT;
            case 5:
            default:
                return true;
            case 6:
                return VOLibraryLoader.a();
        }
    }

    protected static String f(Context context) {
        k(context);
        return mKeepFieldClearSDKFaasUrlStatic;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (!this.N) {
            try {
                if (!this.i) {
                    a(this.e, 0);
                }
                if (this.s && this.h) {
                    J();
                }
                this.M = i.OPENING;
                if (R1.booleanValue()) {
                    k();
                }
                if (this.e != null && QTlBRjkxNkNGQTZENDJGNUI0QjMzQUEwMTY4NjBGMjY()) {
                    try {
                        this.e.a(-1, 20004);
                    } catch (Exception unused) {
                    }
                }
                ODU5MDJDRUI1REJGNENBN0I1Mzk0MDcxNzNGRjc1Qzg();
            } catch (IllegalStateException e2) {
                int iIntValue = MEDIA_ERROR_ILLEGAL_STATE;
                try {
                    String[] strArrSplit = e2.getMessage().split("status=0x");
                    if (strArrSplit != null) {
                        iIntValue = new BigInteger(strArrSplit[1], 16).intValue();
                    }
                } catch (Exception unused2) {
                }
                h(iIntValue);
            }
        }
        if (i.PREOPENING == this.M) {
            this.M = i.CREATED;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i2) {
        VOSurfaceView vOSurfaceView = this.e;
        if (vOSurfaceView != null) {
            try {
                vOSurfaceView.b(i2);
            } catch (Exception unused) {
            }
        }
    }

    private void f(String str) {
        this.Z0 = str;
        if (str != null) {
            if (isCasting()) {
                ((com.viaccessorca.voplayer.b) this.c1).c(str);
            } else {
                RUJDNDhENUIxOTA5NDM4MjkwRDQ3NkExNzA4QTdBOTQ(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:25:0x0176 A[Catch: Exception -> 0x0198, TryCatch #0 {Exception -> 0x0198, blocks: (B:11:0x0012, B:13:0x0018, B:14:0x001c, B:16:0x0020, B:22:0x016e, B:23:0x0172, B:25:0x0176, B:26:0x017b, B:28:0x017f, B:17:0x0031, B:19:0x012c, B:21:0x0162, B:29:0x0185, B:31:0x018a, B:32:0x018f, B:34:0x0193), top: B:37:0x0010 }] */
    /* JADX WARN: Code duplicated, block: B:28:0x017f A[Catch: Exception -> 0x0198, TryCatch #0 {Exception -> 0x0198, blocks: (B:11:0x0012, B:13:0x0018, B:14:0x001c, B:16:0x0020, B:22:0x016e, B:23:0x0172, B:25:0x0176, B:26:0x017b, B:28:0x017f, B:17:0x0031, B:19:0x012c, B:21:0x0162, B:29:0x0185, B:31:0x018a, B:32:0x018f, B:34:0x0193), top: B:37:0x0010 }] */
    /* JADX WARN: Code duplicated, block: B:39:? A[RETURN, SYNTHETIC] */
    public void f(boolean z) {
        String string;
        StringBuilder sb;
        String str = this.y0;
        if ((this.e == null && this.f == null) ? false : true) {
            try {
                if (!z) {
                    if (this.e != null) {
                        this.e.b((String) null);
                    }
                    if (this.f != null) {
                        this.f.b(null);
                        return;
                    }
                    return;
                }
                VOPlayerType playerType = VOPlayerType.TYPE_UNKNOWN;
                if (this.z1) {
                    playerType = getPlayerType();
                }
                if (VOPlayerType.TYPE_FOLLOWER != playerType) {
                    string = "url:" + getStreamURL() + "\nmaxSwBr:" + this.B + "kbps,maxHwBr:" + this.C + "kbps,maxPlayableHeight:" + this.D + v() + "\nr:" + getVideoWidth() + "x" + getVideoHeight() + ",alt:" + (getHttpStreamingAlternateBitrate() / 1024) + "kbps(" + this.X + "/" + this.Y + "),bw:" + (getHttpStreamingCurrentBitrate() / 1024) + "kbps,buf:" + getCurrentBufferLevel() + "ms,speed:" + getPlaybackSpeed() + ",pts:" + getCurrentPTS() + ",latency:" + getCurrentLiveLatency() + "ms\ndispMode:" + this.w + " State : " + getState() + ",codecType:" + this.V + "\ndemoL:" + this.c0 + ",debugN:" + this.d0 + ",error network:" + this.g0 + NjkwRDhBRkYzM0EwNDlFQUE1NEVBMkRBMTE5ODZDRTgK();
                    if (this.r1 != null) {
                        string = (string + "\nacodec : " + getCodecName(VOMediaType.VOMediaTypeAudio)) + ", vcodec : " + getCodecName(VOMediaType.VOMediaTypeVideo);
                    }
                    if (str != null) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(string);
                        sb2.append(str);
                        sb = sb2;
                    }
                    if (this.e != null) {
                        this.e.b(string);
                    }
                    if (this.f != null) {
                        this.f.b(string);
                    }
                }
                sb = new StringBuilder();
                sb.append("DEMO:");
                sb.append(this.f73a);
                string = sb.toString();
                if (this.e != null) {
                    this.e.b(string);
                }
                if (this.f != null) {
                    this.f.b(string);
                }
            } catch (Exception unused) {
            }
        }
    }

    protected static String g(Context context) {
        String strRetrieveSdkInternalVersion = retrieveSdkInternalVersion(context);
        if (strRetrieveSdkInternalVersion.contains("VOPlayer-")) {
            strRetrieveSdkInternalVersion = strRetrieveSdkInternalVersion.split("VOPlayer-")[1];
        }
        return strRetrieveSdkInternalVersion.replace(".", "_");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(int i2) {
        M0VGN0REMzk0Q0EwNERFNkI4QTY5MjkzNzNDRjk4MTg(i2);
        this.G0 = 0;
    }

    private void g(String str) {
        this.a1 = str;
        if (str != null) {
            MTUwRTdENjdCMDg5NERFMjg3NzBDRjcxQkUwRjg5NTI(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(boolean z) {
        PowerManager.WakeLock wakeLock = this.m;
        if (wakeLock != null) {
            if (z && !wakeLock.isHeld()) {
                this.m.acquire();
            } else if (!z && this.m.isHeld()) {
                this.m.release();
            }
        }
        this.o = z;
        u();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:100:0x013e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:101:0x013f  */
    /* JADX WARN: Code duplicated, block: B:103:0x0146 A[ADDED_TO_REGION, RETURN] */
    /* JADX WARN: Code duplicated, block: B:104:0x0147 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:68:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:75:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:82:0x0100 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:83:0x0101  */
    /* JADX WARN: Code duplicated, block: B:85:0x0107  */
    /* JADX WARN: Code duplicated, block: B:88:0x0111  */
    /* JADX WARN: Code duplicated, block: B:91:0x011b  */
    /* JADX WARN: Code duplicated, block: B:94:0x0125  */
    /* JADX WARN: Code duplicated, block: B:97:0x0136 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:98:0x0137  */
    public boolean g() throws Exception {
        int i2;
        int i3;
        int i4;
        VOSystemInfo vOSystemInfo;
        VOSystemInfo vOSystemInfo2;
        VOSystemInfo vOSystemInfo3;
        VOSystemInfo vOSystemInfo4;
        boolean z;
        f fVar;
        int i5;
        boolean z2 = this.N;
        if (z2 || z2) {
            return false;
        }
        i();
        if (this.N) {
            return false;
        }
        if (!isPlatformBenchmarkUpToDate(this.m329t7p639082847841)) {
            f fVar2 = this.j;
            fVar2.sendMessage(Message.obtain(fVar2, 200, MEDIA_INFO_BENCHMARK, MEDIA_INFO_BENCHMARK_STARTED));
            if (a(this.m329t7p639082847841, false, true, this.I)) {
                fVar = this.j;
                i5 = MEDIA_INFO_BENCHMARK_DONE;
            } else {
                fVar = this.j;
                i5 = MEDIA_INFO_BENCHMARK_FAIL;
            }
            fVar.sendMessage(Message.obtain(fVar, 200, MEDIA_INFO_BENCHMARK, i5));
        }
        if (this.N) {
            return false;
        }
        A();
        if (this.N) {
            return false;
        }
        VOFragmentationManager.getCodecTypeException(this.m329t7p639082847841, true);
        if (this.N) {
            return false;
        }
        this.q.codecTypeException = -1;
        this.r = -1;
        VOSystemInfo systemInfo = VOSystemInfoRetriever.getSystemInfo(this.m329t7p639082847841);
        this.q = systemInfo;
        if (!this.i) {
            int i6 = this.a0;
            if (i6 <= 0 || !e(i6)) {
                if (EDrmEngineType.MediaDrm != this.z0) {
                    if (this.q.isEmulator && VOUtils.SDK_INT < 23) {
                        i2 = 5;
                    } else if (true != this.q.isTV || !e(7) || ((i4 = this.q.codecTypeException) != 0 && e(i4))) {
                        VOVRManager vOVRManager = this.i0;
                        if (vOVRManager != null && VOVRManager.Mode.OFF != vOVRManager.getMode()) {
                            i2 = 8;
                            if (e(8) && !this.i && (7 == (i3 = this.q.codecTypeException) || 1 == i3 || i3 == 0)) {
                            }
                        }
                    }
                }
                if (QTlBRjkxNkNGQTZENDJGNUI0QjMzQUEwMTY4NjBGMjY() && (1 == this.q.codecTypeException || 1 == this.r)) {
                    this.r = 7;
                }
                if (!Nzg5N0Y2MTU5OTY0NEQ0Mzk3NEM4NTM4MjRCMUQwQjI(P1) && (this.F0 != null || this.x0 == DrmAgent.EDrmType.DRM_TYPE_VERIMATRIX)) {
                    g(3101);
                }
                if (this.N) {
                    return false;
                }
                vOSystemInfo = this.q;
                if (vOSystemInfo.m329t7p6390828478425 == null) {
                    vOSystemInfo.m329t7p6390828478425 = vOSystemInfo.performancePointsForH264;
                }
                vOSystemInfo2 = this.q;
                if (vOSystemInfo2.m329t7p6390828478424 == null) {
                    vOSystemInfo2.m329t7p6390828478424 = vOSystemInfo2.performancePointsForSecureH264;
                }
                vOSystemInfo3 = this.q;
                if (vOSystemInfo3.m329t7p6390828478427 == null) {
                    vOSystemInfo3.m329t7p6390828478427 = vOSystemInfo3.performancePointsForHEVC;
                }
                vOSystemInfo4 = this.q;
                if (vOSystemInfo4.m329t7p6390828478426 == null) {
                    vOSystemInfo4.m329t7p6390828478426 = vOSystemInfo4.performancePointsForSecureHEVC;
                }
                QzdGRkY0NjFENTdBNDQ5RDk1NkRGQjRGMjAyOTYwOTc(this.q, this.r);
                this.x = true;
                if (this.N) {
                    return false;
                }
                OTEzNURFQkRGMEEyNDQ2NkE3NUZENTRCREI3QjNGRjY();
                if (this.N) {
                    return false;
                }
                p();
                z = this.N;
                if (!z || z) {
                    return false;
                }
                t();
                return true;
            }
            i2 = this.a0;
            this.r = i2;
            if (QTlBRjkxNkNGQTZENDJGNUI0QjMzQUEwMTY4NjBGMjY()) {
                this.r = 7;
            }
            if (!Nzg5N0Y2MTU5OTY0NEQ0Mzk3NEM4NTM4MjRCMUQwQjI(P1)) {
                g(3101);
            }
            if (this.N) {
                return false;
            }
            vOSystemInfo = this.q;
            if (vOSystemInfo.m329t7p6390828478425 == null) {
                vOSystemInfo.m329t7p6390828478425 = vOSystemInfo.performancePointsForH264;
            }
            vOSystemInfo2 = this.q;
            if (vOSystemInfo2.m329t7p6390828478424 == null) {
                vOSystemInfo2.m329t7p6390828478424 = vOSystemInfo2.performancePointsForSecureH264;
            }
            vOSystemInfo3 = this.q;
            if (vOSystemInfo3.m329t7p6390828478427 == null) {
                vOSystemInfo3.m329t7p6390828478427 = vOSystemInfo3.performancePointsForHEVC;
            }
            vOSystemInfo4 = this.q;
            if (vOSystemInfo4.m329t7p6390828478426 == null) {
                vOSystemInfo4.m329t7p6390828478426 = vOSystemInfo4.performancePointsForSecureHEVC;
            }
            QzdGRkY0NjFENTdBNDQ5RDk1NkRGQjRGMjAyOTYwOTc(this.q, this.r);
            this.x = true;
            if (this.N) {
                return false;
            }
            OTEzNURFQkRGMEEyNDQ2NkE3NUZENTRCREI3QjNGRjY();
            if (this.N) {
                return false;
            }
            p();
            z = this.N;
            if (!z) {
                return false;
            }
            t();
            return true;
        }
        systemInfo.codecTypeException = 7;
        this.r = 7;
        if (QTlBRjkxNkNGQTZENDJGNUI0QjMzQUEwMTY4NjBGMjY()) {
            this.r = 7;
        }
        if (!Nzg5N0Y2MTU5OTY0NEQ0Mzk3NEM4NTM4MjRCMUQwQjI(P1)) {
            g(3101);
        }
        if (this.N) {
            return false;
        }
        vOSystemInfo = this.q;
        if (vOSystemInfo.m329t7p6390828478425 == null) {
            vOSystemInfo.m329t7p6390828478425 = vOSystemInfo.performancePointsForH264;
        }
        vOSystemInfo2 = this.q;
        if (vOSystemInfo2.m329t7p6390828478424 == null) {
            vOSystemInfo2.m329t7p6390828478424 = vOSystemInfo2.performancePointsForSecureH264;
        }
        vOSystemInfo3 = this.q;
        if (vOSystemInfo3.m329t7p6390828478427 == null) {
            vOSystemInfo3.m329t7p6390828478427 = vOSystemInfo3.performancePointsForHEVC;
        }
        vOSystemInfo4 = this.q;
        if (vOSystemInfo4.m329t7p6390828478426 == null) {
            vOSystemInfo4.m329t7p6390828478426 = vOSystemInfo4.performancePointsForSecureHEVC;
        }
        QzdGRkY0NjFENTdBNDQ5RDk1NkRGQjRGMjAyOTYwOTc(this.q, this.r);
        this.x = true;
        if (this.N) {
            return false;
        }
        OTEzNURFQkRGMEEyNDQ2NkE3NUZENTRCREI3QjNGRjY();
        if (this.N) {
            return false;
        }
        p();
        z = this.N;
        if (!z) {
            return false;
        }
        t();
        return true;
    }

    static /* synthetic */ int g0(VOPlayer vOPlayer) {
        int i2 = vOPlayer.X;
        vOPlayer.X = i2 + 1;
        return i2;
    }

    public static ArrayList<EDrmType> getAvaliableDrmAgents() {
        EDrmType eDrmType;
        ArrayList<EDrmType> arrayList = new ArrayList<>();
        for (EDrmType eDrmType2 : EDrmType.values()) {
            int i2 = c.e[eDrmType2.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 == 4) {
                            try {
                                if (Nzg5N0Y2MTU5OTY0NEQ0Mzk3NEM4NTM4MjRCMUQwQjI(N1) || Nzg5N0Y2MTU5OTY0NEQ0Mzk3NEM4NTM4MjRCMUQwQjI(O1) || VOBaseMediaDrmAgent.a(com.viaccessorca.drm.impl.f.f0)) {
                                    eDrmType = EDrmType.PLAYREADY;
                                    arrayList.add(eDrmType);
                                }
                            } catch (Exception unused) {
                            }
                        }
                    } else if (VOBaseMediaDrmAgent.a(com.viaccessorca.drm.impl.m.g0)) {
                        eDrmType = EDrmType.VODRM;
                        arrayList.add(eDrmType);
                    }
                } else if (VOBaseMediaDrmAgent.a(com.viaccessorca.drm.impl.o.f0)) {
                    eDrmType = EDrmType.WIDEVINE;
                    arrayList.add(eDrmType);
                }
            } else if (Nzg5N0Y2MTU5OTY0NEQ0Mzk3NEM4NTM4MjRCMUQwQjI(P1)) {
                eDrmType = EDrmType.VERIMATRIX;
                arrayList.add(eDrmType);
            }
        }
        return arrayList;
    }

    public static DRMAgentVersion getDRMAgentVersion() {
        if (DRMAgentVersion.UNKNOWN == F1) {
            F1 = Nzg5N0Y2MTU5OTY0NEQ0Mzk3NEM4NTM4MjRCMUQwQjI(M1) ? DRMAgentVersion.VERSION_1 : DRMAgentVersion.VERSION_2;
        }
        return F1;
    }

    public static int getSDKExpirationTime(Context context) {
        k(context);
        VOCrashReporter.init(context);
        return m329t7p6390828478412;
    }

    public static String getSDKLicenseOption(Context context) {
        return h(context);
    }

    public static String getSdkVersion(Context context) throws VOException {
        StringBuilder sb;
        String str;
        if (DRMAgentVersion.VERSION_1 == getDRMAgentVersion()) {
            return retrieveSdkInternalVersion(context);
        }
        k(context);
        String drmVersion = DrmAgent.getDrmVersion(context);
        if (Nzg5N0Y2MTU5OTY0NEQ0Mzk3NEM4NTM4MjRCMUQwQjI(L1)) {
            sb = new StringBuilder();
            sb.append(drmVersion);
            str = "_Release";
        } else {
            sb = new StringBuilder();
            sb.append(drmVersion);
            str = "_Debug";
        }
        sb.append(str);
        return sb.toString();
    }

    public static String getUniqueIdentifier(Context context) throws VOException {
        return getUniqueIdentifier(context, EUniqueIdentifierType.DEFAULT_ID);
    }

    public static String getUniqueIdentifier(Context context, EUniqueIdentifierType eUniqueIdentifierType) throws VOException {
        try {
            a(context);
            return DrmAgent.getDeviceID(context, eUniqueIdentifierType);
        } catch (UnsatisfiedLinkError e2) {
            throw new VOException(VOStatusCode.ERROR_NOT_INITIALIZED, e2.getMessage());
        }
    }

    public static VOPlayer getVOPlayerInstance() {
        WeakReference weakReference = I1;
        if (weakReference == null) {
            WeakReference[] weakReferenceArr = J1;
            if (weakReferenceArr == null) {
                return null;
            }
            weakReference = weakReferenceArr[weakReferenceArr.length - 1];
        }
        return (VOPlayer) weakReference.get();
    }

    protected static String h(Context context) {
        String strRetrieveSdkInternalVersion = retrieveSdkInternalVersion(context);
        if (strRetrieveSdkInternalVersion.contains("VOPlayer-")) {
            String str = strRetrieveSdkInternalVersion.split("VOPlayer-")[1];
            if (strRetrieveSdkInternalVersion.contains("plr_")) {
                str = "plr_" + str;
            }
            if (strRetrieveSdkInternalVersion.contains("wv_")) {
                str = "wv_" + str;
            }
            if (strRetrieveSdkInternalVersion.contains("vsc_")) {
                str = "vsc_" + str;
            }
            if (strRetrieveSdkInternalVersion.contains("vmx_")) {
                str = "vmx_" + str;
            }
            if (strRetrieveSdkInternalVersion.contains("vo_")) {
                String str2 = "vo_" + str;
            }
            strRetrieveSdkInternalVersion.contains("mrl_");
        }
        return strRetrieveSdkInternalVersion.replace(".", "_");
    }

    private void h(int i2) {
        f fVar = this.j;
        if (fVar != null) {
            boolean z = true;
            Message messageObtainMessage = fVar.obtainMessage(100);
            switch (i2) {
                case -2121990136:
                    messageObtainMessage.arg1 = MEDIA_INFO_NETWORK;
                    i2 = MEDIA_INFO_NETWORK_NO_FILE;
                    messageObtainMessage.arg2 = i2;
                    break;
                case -2121989880:
                    messageObtainMessage.arg1 = MEDIA_INFO_NETWORK;
                    i2 = MEDIA_ERROR_NETWORK_BAD_URL;
                    messageObtainMessage.arg2 = i2;
                    break;
                case MEDIA_ERROR_BAD_LICENSE /* -2113929212 */:
                case -2113929211:
                    z = false;
                    break;
                case MEDIA_ERROR_ILLEGAL_STATE /* 8002 */:
                    messageObtainMessage.arg1 = 8000;
                    i2 = MEDIA_ERROR_ILLEGAL_STATE;
                    messageObtainMessage.arg2 = i2;
                    break;
                default:
                    messageObtainMessage.arg1 = 8000;
                    messageObtainMessage.arg2 = i2;
                    break;
            }
            if (z) {
                this.j.sendMessage(messageObtainMessage);
            }
        }
    }

    private void h(String str) {
        this.u0 = str;
        if (str != null) {
            if (isCasting()) {
                ((com.viaccessorca.voplayer.b) this.c1).d(str);
            } else {
                NkY0MkY3QzM3QTdBNDk2NkI2OTQ3MTcyNzAwOTQ1OUE(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean h() {
        VOSurfaceView vOSurfaceView;
        if (this.N) {
            return false;
        }
        if (16 <= Build.VERSION.SDK_INT && (vOSurfaceView = this.e) != null) {
            if (((TextureView) vOSurfaceView.j()) != null) {
                synchronized (this.m0) {
                    for (int i2 = 0; !P() && i2 < 50; i2++) {
                        try {
                            this.m0.wait(100L);
                        } catch (InterruptedException unused) {
                        }
                    }
                }
            } else if (this.e.i() != null) {
                this.q0 = true;
                synchronized (this.n0) {
                    for (int i3 = 0; !O() && i3 < 50; i3++) {
                        try {
                            this.n0.wait(100L);
                        } catch (InterruptedException unused2) {
                        }
                    }
                }
                this.q0 = false;
            } else if (8 == RDNEMkM2NUU0REYyNDhFNTlFMUY3NzI0OUQzRTcwMkY(this.q, this.r, false)) {
                synchronized (this.m0) {
                    this.r0 = true;
                    for (int i4 = 0; !N() && i4 < 50; i4++) {
                        try {
                            this.m0.wait(100L);
                        } catch (InterruptedException unused3) {
                        }
                    }
                    this.r0 = false;
                }
            }
        }
        return !this.N;
    }

    private void i() {
        BufferedReader bufferedReader;
        if (Nzg5N0Y2MTU5OTY0NEQ0Mzk3NEM4NTM4MjRCMUQwQjI(L1) || G1) {
            return;
        }
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/voplayer_debuglogs.cfg"));
        } catch (Exception unused) {
            bufferedReader = null;
        }
        if (bufferedReader == null) {
            try {
                bufferedReader2 = new BufferedReader(new FileReader(this.m329t7p639082847841.getFilesDir() + "/voplayer_debuglogs.cfg"));
            } catch (Exception unused2) {
            }
        } else {
            bufferedReader2 = bufferedReader;
        }
        if (bufferedReader2 != null) {
            try {
                HashMap map = new HashMap(0);
                Pattern patternCompile = Pattern.compile("(\\d+);(\\d+)");
                while (true) {
                    String line = bufferedReader2.readLine();
                    if (line == null) {
                        break;
                    }
                    try {
                        Matcher matcher = patternCompile.matcher(line);
                        if (matcher != null) {
                            matcher.find();
                            map.put(DebugModule.values()[Integer.valueOf(matcher.group(1)).intValue()], DebugLevel.values()[Integer.valueOf(matcher.group(2)).intValue()]);
                        }
                    } catch (Exception unused3) {
                    }
                }
                bufferedReader2.close();
                setDebugLevel(map);
                H1 = true;
            } catch (Exception unused4) {
            }
        }
        G1 = true;
    }

    private void i(String str) {
        this.P0 = str;
        if (str != null) {
            ODE3MjU3ODA4QzY5NEFFM0I0NzhFRUQyOTgzN0VCRjA(str);
        }
    }

    private static boolean i(Context context) {
        k(context);
        return m329t7p6390828478414;
    }

    public static boolean isOsTampered(Context context) throws VOException {
        k(context);
        if (DRMAgentVersion.VERSION_2 == getDRMAgentVersion()) {
            return DrmAgent.isOsTampered(context);
        }
        return false;
    }

    public static boolean isPlatformBenchmarkUpToDate(Context context) {
        return (n(context) || o(context) || p(context)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        VOSurfaceView vOSurfaceView = this.e;
        int[] iArrQ0M3QUY2MjFGQkM5NEQ3MDgzQjE1NDMzN0RBMDcxOEU = null;
        if (vOSurfaceView != null && true == this.R) {
            vOSurfaceView.a();
            try {
                iArrQ0M3QUY2MjFGQkM5NEQ3MDgzQjE1NDMzN0RBMDcxOEU = Q0M3QUY2MjFGQkM5NEQ3MDgzQjE1NDMzN0RBMDcxOEU();
            } catch (IllegalStateException unused) {
            }
            if (iArrQ0M3QUY2MjFGQkM5NEQ3MDgzQjE1NDMzN0RBMDcxOEU == null || iArrQ0M3QUY2MjFGQkM5NEQ3MDgzQjE1NDMzN0RBMDcxOEU.length != 2 || iArrQ0M3QUY2MjFGQkM5NEQ3MDgzQjE1NDMzN0RBMDcxOEU[0] == 0 || iArrQ0M3QUY2MjFGQkM5NEQ3MDgzQjE1NDMzN0RBMDcxOEU[1] == 0) {
                return;
            }
            this.e.b(iArrQ0M3QUY2MjFGQkM5NEQ3MDgzQjE1NDMzN0RBMDcxOEU[0], iArrQ0M3QUY2MjFGQkM5NEQ3MDgzQjE1NDMzN0RBMDcxOEU[1]);
            return;
        }
        VOTIFVideoOverlayView vOTIFVideoOverlayView = this.f;
        if (vOTIFVideoOverlayView == null || true != this.R) {
            return;
        }
        vOTIFVideoOverlayView.a();
        try {
            iArrQ0M3QUY2MjFGQkM5NEQ3MDgzQjE1NDMzN0RBMDcxOEU = Q0M3QUY2MjFGQkM5NEQ3MDgzQjE1NDMzN0RBMDcxOEU();
        } catch (IllegalStateException unused2) {
        }
        if (iArrQ0M3QUY2MjFGQkM5NEQ3MDgzQjE1NDMzN0RBMDcxOEU == null || iArrQ0M3QUY2MjFGQkM5NEQ3MDgzQjE1NDMzN0RBMDcxOEU.length != 2 || iArrQ0M3QUY2MjFGQkM5NEQ3MDgzQjE1NDMzN0RBMDcxOEU[0] == 0 || iArrQ0M3QUY2MjFGQkM5NEQ3MDgzQjE1NDMzN0RBMDcxOEU[1] == 0) {
            return;
        }
        this.f.a(iArrQ0M3QUY2MjFGQkM5NEQ3MDgzQjE1NDMzN0RBMDcxOEU[0], iArrQ0M3QUY2MjFGQkM5NEQ3MDgzQjE1NDMzN0RBMDcxOEU[1]);
    }

    private static void j(Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("VOPlayerBenchmarkPrefs", 0).edit();
        int iB = b(context);
        String strI = I();
        editorEdit.putInt("lastAppVersionCode", iB);
        editorEdit.putString("lastOSSignature", strI);
        editorEdit.putString("lastSDKVersion", g(context));
        editorEdit.commit();
    }

    private void j(String str) {
        this.h0 = str;
        if (str != null) {
            ODVGQkJCOEUzODE1NEFGRkFCRUMzNTA0OTkzOERGRjU(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        try {
            String str = "INFO [ url: " + getStreamURL() + "\nmSwBr: " + this.B + "kbps, mHwBr: " + this.C + "kbps, mPlH: " + this.D + "\nr: " + getVideoWidth() + "x" + getVideoHeight() + ", alt: " + (getHttpStreamingAlternateBitrate() / 1024) + " kbps (" + this.X + "/" + this.Y + "), bw:" + (getHttpStreamingCurrentBitrate() / 1024) + "kbps, buf: " + getCurrentBufferLevel() + "ms, sp: " + getPlaybackSpeed() + "\ndM: " + this.w + ", St: " + getState() + ", cT: " + this.V + "\ndL: " + this.c0 + ", dN: " + this.d0 + NjkwRDhBRkYzM0EwNDlFQUE1NEVBMkRBMTE5ODZDRTgK();
            if (this.r1 != null) {
                str = (str + "ac: " + getCodecName(VOMediaType.VOMediaTypeAudio)) + ", vc: " + getCodecName(VOMediaType.VOMediaTypeVideo);
            }
            String str2 = this.y0;
            if (str2 != null) {
                str = (((str + str2) + "\nDCr: " + this.a1) + "\nLUrl: " + this.u0) + "\nLCdt: " + this.Z0;
                if (this.x0 == DrmAgent.EDrmType.DRM_TYPE_VERIMATRIX) {
                    str = (str + "\nVCn: " + this.h0) + "\nVBa: " + this.P0;
                }
            }
            VOCrashReporter.setReportHeader(str + " ]\n");
        } catch (Exception unused) {
        }
    }

    private static void k(Context context) {
        if (m329t7p6390828478410 == null) {
            a(context);
            QjM0NUNDMjRFMEJDNDQzNzgzREI4OUVFMEQzQzM0NkQ(D1);
            String str = m329t7p6390828478410;
            if (str != null) {
                m329t7p6390828478410 = str.trim();
            }
        }
    }

    private void l() {
        DrmAgent drmAgent;
        VOBaseMediaDrmAgent currentMediaDrmAgent = getCurrentMediaDrmAgent();
        if (currentMediaDrmAgent != null) {
            currentMediaDrmAgent.d();
        }
        int i2 = c.c[this.x0.ordinal()];
        if (i2 == 1) {
            drmAgent = this.A0;
        } else if (i2 != 3) {
            drmAgent = i2 != 4 ? null : this.D0;
        } else {
            drmAgent = this.E0;
        }
        if (drmAgent != null) {
            drmAgent.release();
        }
        this.x0 = DrmAgent.EDrmType.DRM_TYPE_UNKNOWN;
        this.z0 = EDrmEngineType.DefaultEngine;
        this.A0 = null;
        this.B0 = null;
        this.C0 = null;
        this.D0 = null;
        this.E0 = null;
        this.y0 = null;
    }

    private void l(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            String absolutePath = filesDir.getAbsolutePath();
            this.F = absolutePath;
            MDdBNTA0OTY3QzdCNEZFRThBMjYzNEZEQzBENzY5Mzk(absolutePath);
        }
    }

    public static void logEvent(VOPlayerSeverity vOPlayerSeverity, String str, String str2) {
        VOCrashReporter.logEvent(vOPlayerSeverity, str, str2);
    }

    private void m() {
        this.S = null;
    }

    private void m(Context context) {
        if (this.B < 0) {
            int recommendedMaxBitrate = VODeviceAdaptation.getRecommendedMaxBitrate(context, false);
            this.B = recommendedMaxBitrate;
            if (recommendedMaxBitrate == -1) {
                this.B = 49999;
            }
        }
        if (this.C < 0) {
            int recommendedMaxBitrate2 = VODeviceAdaptation.getRecommendedMaxBitrate(context, true);
            this.C = recommendedMaxBitrate2;
            if (recommendedMaxBitrate2 == -1) {
                this.C = 99999;
            }
        }
        int iC = c(context);
        this.D = iC;
        RUM2MUFGQURBRTFGNEEzMEEwOTZEMTNBMUFENEEwRkY(this.B, this.C, iC);
    }

    private static void m2yugdva7tpoe4A1(Object obj, int i2, int i3, int i4, Object obj2) {
        VOPlayer vOPlayer;
        f fVar;
        if (obj == null || (vOPlayer = (VOPlayer) ((WeakReference) obj).get()) == null || a(vOPlayer, i4, obj2) || (fVar = vOPlayer.j) == null) {
            return;
        }
        vOPlayer.j.sendMessage(fVar.obtainMessage(i2, i3, i4, obj2));
    }

    private void n() {
        f fVar = this.j;
        if (fVar == null || true != this.R) {
            return;
        }
        fVar.removeMessages(MEDIA_INFO_AD_SWITCH_POINT_DETECTED);
        this.j.sendMessage(this.j.obtainMessage(MEDIA_INFO_AD_SWITCH_POINT_DETECTED));
    }

    private static boolean n(Context context) {
        return context.getSharedPreferences("VOPlayerBenchmarkPrefs", 0).getInt("lastAppVersionCode", -1) != b(context);
    }

    private native String nativeMP_getRealtimeEventReport();

    private native void nativeMP_setContentProtectionKeyIdStatus(byte[] bArr, int i2);

    private native void nativeMP_setRealtimeEventReportBackendURL(String str);

    private native void nativeMP_setUpdateRightsMode(int i2);

    private void o() {
        int iQTYyMjcyNzI1ODY2NDUxMjlCNThDRjI4NTAzQjlENzM;
        int iRjg1NkNBRDFCQ0JGNDI4NDg4MEJFNkY3RTNFQjYyQkY;
        try {
            iQTYyMjcyNzI1ODY2NDUxMjlCNThDRjI4NTAzQjlENzM = QTYyMjcyNzI1ODY2NDUxMjlCNThDRjI4NTAzQjlENzM();
            iRjg1NkNBRDFCQ0JGNDI4NDg4MEJFNkY3RTNFQjYyQkY = Rjg1NkNBRDFCQ0JGNDI4NDg4MEJFNkY3RTNFQjYyQkY();
        } catch (Exception unused) {
            iQTYyMjcyNzI1ODY2NDUxMjlCNThDRjI4NTAzQjlENzM = -1;
            iRjg1NkNBRDFCQ0JGNDI4NDg4MEJFNkY3RTNFQjYyQkY = -1;
        }
        if (-1 == iQTYyMjcyNzI1ODY2NDUxMjlCNThDRjI4NTAzQjlENzM) {
            iQTYyMjcyNzI1ODY2NDUxMjlCNThDRjI4NTAzQjlENzM = this.J;
        }
        if (-1 == iRjg1NkNBRDFCQ0JGNDI4NDg4MEJFNkY3RTNFQjYyQkY) {
            iRjg1NkNBRDFCQ0JGNDI4NDg4MEJFNkY3RTNFQjYyQkY = this.K;
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.m329t7p639082847841);
        this.d = defaultSharedPreferences.getInt("SUCCESS_BUFFER", -2);
        this.c = defaultSharedPreferences.getInt("FAIL_BUFFER", -2);
        if (-1 == iQTYyMjcyNzI1ODY2NDUxMjlCNThDRjI4NTAzQjlENzM && -1 == iRjg1NkNBRDFCQ0JGNDI4NDg4MEJFNkY3RTNFQjYyQkY) {
            return;
        }
        if (iQTYyMjcyNzI1ODY2NDUxMjlCNThDRjI4NTAzQjlENzM == 0 && iRjg1NkNBRDFCQ0JGNDI4NDg4MEJFNkY3RTNFQjYyQkY == 0) {
            return;
        }
        if (this.d == iQTYyMjcyNzI1ODY2NDUxMjlCNThDRjI4NTAzQjlENzM && this.c == iRjg1NkNBRDFCQ0JGNDI4NDg4MEJFNkY3RTNFQjYyQkY) {
            return;
        }
        VOSystemInfoRetriever.a(false);
        VOFragmentationManager.sendExperienceReport(this.m329t7p639082847841, g(this.m329t7p639082847841), iRjg1NkNBRDFCQ0JGNDI4NDg4MEJFNkY3RTNFQjYyQkY, iQTYyMjcyNzI1ODY2NDUxMjlCNThDRjI4NTAzQjlENzM);
    }

    private static boolean o(Context context) {
        return !context.getSharedPreferences("VOPlayerBenchmarkPrefs", 0).getString("lastOSSignature", "").equalsIgnoreCase(I());
    }

    private void p() {
        VOSystemInfoRetriever.a(false);
        String strG = g(this.m329t7p639082847841);
        if (true == VOFragmentationManager.isFragmentationReportRequired(this.m329t7p639082847841, strG)) {
            VOFragmentationManager.sendFragmentationReport(this.m329t7p639082847841, strG);
        }
    }

    private static boolean p(Context context) {
        String string = context.getSharedPreferences("VOPlayerBenchmarkPrefs", 0).getString("lastSDKVersion", "");
        k(context);
        return !string.equals(g(context));
    }

    private void q() {
        M0VGN0REMzk0Q0EwNERFNkI4QTY5MjkzNzNDRjk4MTg(20006);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        this.p0 = true;
        MediaCrypto mediaCrypto = this.s0;
        boolean zRequiresSecureDecoderComponent = mediaCrypto != null ? mediaCrypto.requiresSecureDecoderComponent(MimeTypes.VIDEO_H264) : false;
        MediaCrypto mediaCrypto2 = this.t0;
        QkVGQjU2QzZBMkE2NDgyRTgwQUYxRjEzOUQ5OTAxNDg(this.s0, this.t0, zRequiresSecureDecoderComponent, mediaCrypto2 != null ? mediaCrypto2.requiresSecureDecoderComponent(MimeTypes.AUDIO_AAC) : false);
        this.p0 = false;
        synchronized (this.o0) {
            this.o0.notifyAll();
        }
    }

    public static WeakReference[] remove(WeakReference[] weakReferenceArr, int i2) {
        if (weakReferenceArr == null || i2 < 0 || i2 >= weakReferenceArr.length) {
            return weakReferenceArr;
        }
        WeakReference[] weakReferenceArr2 = new WeakReference[weakReferenceArr.length - 1];
        int i3 = 0;
        for (int i4 = 0; i4 < weakReferenceArr.length; i4++) {
            if (i4 != i2) {
                weakReferenceArr2[i3] = weakReferenceArr[i4];
                i3++;
            }
        }
        return weakReferenceArr2;
    }

    protected static String retrieveSdkInternalVersion(Context context) {
        if (m329t7p6390828478410 == null) {
            k(context);
            String str = m329t7p6390828478410;
            if (str != null) {
                m329t7p6390828478410 = str.trim();
            }
        }
        return m329t7p6390828478410;
    }

    public static synchronized boolean runPlatformBenchmark(Context context, boolean z, boolean z2) {
        return a(context, z, false, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        Q0U5NzJGRUEwMkQ4NDczNTlGNTVGOEYzMDZFRTkwRkYK(this.x0.ordinal(), a(this.x0));
    }

    public static void setEnabledReports(Context context, VOPlayerEnabledReports vOPlayerEnabledReports) {
        VOCrashReporter.init(context);
        VOCrashReporter.setEnabledReports(vOPlayerEnabledReports);
    }

    /* JADX WARN: Code duplicated, block: B:41:0x0090  */
    /* JADX WARN: Code duplicated, block: B:51:? A[RETURN, SYNTHETIC] */
    private void t() throws Exception {
        String ssid;
        String str;
        String str2 = null;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.m329t7p639082847841.getSystemService("connectivity");
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
            NetworkInfo networkInfo3 = connectivityManager.getNetworkInfo(9);
            if (networkInfo3 == null || !networkInfo3.isAvailable() || !networkInfo3.isConnected()) {
                if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
                    ssid = null;
                    for (NetworkInfo networkInfo4 : connectivityManager.getAllNetworkInfo()) {
                        try {
                            if (networkInfo4.getType() == 1) {
                                WifiManager wifiManager = (WifiManager) this.m329t7p639082847841.getApplicationContext().getSystemService("wifi");
                                if (networkInfo4.getState() == NetworkInfo.State.CONNECTED) {
                                    WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                                    this.f0 = connectionInfo;
                                    ssid = connectionInfo.getSSID();
                                } else {
                                    continue;
                                }
                            }
                        } catch (Exception e2) {
                            e = e2;
                            if (this.d0) {
                                throw e;
                            }
                            if (str2 != null) {
                                NDc2RTQxOEFCQTFDNDY0QjlFNUE2MzkyMzRBRjBERDkK(str2, ssid);
                            }
                        }
                    }
                    str2 = VOPlaybackSessionReport.NETWORK_TYPE_WIFI;
                } else if (networkInfo2 != null && networkInfo2.isAvailable() && networkInfo2.isConnected()) {
                    str = VOPlaybackSessionReport.NETWORK_TYPE_CELLULAR;
                } else {
                    ssid = null;
                }
                if (str2 != null) {
                    NDc2RTQxOEFCQTFDNDY0QjlFNUE2MzkyMzRBRjBERDkK(str2, ssid);
                }
            }
            str = VOPlaybackSessionReport.NETWORK_TYPE_ETHERNET;
            ssid = null;
            str2 = str;
        } catch (Exception e3) {
            e = e3;
            ssid = null;
        }
        if (str2 != null) {
            NDc2RTQxOEFCQTFDNDY0QjlFNUE2MzkyMzRBRjBERDkK(str2, ssid);
        }
    }

    private void u() {
        VOSurfaceView vOSurfaceView = this.e;
        if (vOSurfaceView != null) {
            vOSurfaceView.setKeepScreenOn(this.n && this.o);
        }
    }

    public static void updateCrashReportEnablementStatus(Context context) {
        VOCrashReporter.init(context);
        VOCrashReporter.updateCrashReportEnablementStatus();
    }

    private String v() throws Exception {
        StringBuilder sb;
        String str;
        this.f0 = null;
        t();
        WifiInfo wifiInfo = this.f0;
        if (wifiInfo == null) {
            return null;
        }
        if (wifiInfo.getSSID() != "<unknown ssid>") {
            sb = new StringBuilder();
            sb.append("\nWIFI: SSID:");
            sb.append(this.f0.getSSID());
            str = " RSSI:";
        } else {
            sb = new StringBuilder();
            str = "\nWIFI: RSSI:";
        }
        sb.append(str);
        sb.append(this.f0.getRssi());
        sb.append("(dbm) LinkSpeed:");
        sb.append(this.f0.getLinkSpeed());
        sb.append("Mbps");
        return sb.toString();
    }

    private void x() {
    }

    private void y() {
        e eVar = this.J0;
        if (eVar != null) {
            eVar.cancel();
            this.J0 = null;
        }
        if (this.p0) {
            try {
                synchronized (this.o0) {
                    this.o0.wait(AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
                }
            } catch (InterruptedException unused) {
            }
        }
        p pVar = this.I0;
        if (pVar != null) {
            pVar.cancel();
            this.I0 = null;
        }
    }

    private void z() {
        m mVar = this.P;
        if (mVar != null) {
            mVar.removeMessages(0);
            VOSurfaceView vOSurfaceView = this.e;
            if (vOSurfaceView != null) {
                vOSurfaceView.clearAllSubtitles();
            }
            VOTIFVideoOverlayView vOTIFVideoOverlayView = this.f;
            if (vOTIFVideoOverlayView != null) {
                vOTIFVideoOverlayView.clearAllSubtitles();
            }
        }
        this.R = false;
    }

    protected void a(float f2, float f3, float f4) {
        YTVhZTU2NzE0NGM4NGNiZmEwZWRkYWUwNWFkMjMwZWQK(f2, f3, f4);
    }

    protected void a(int i2) {
        MEMzOTI0NkE5NThENDMxRTg0QUU4RkNBNkE4MkVCOEE(i2);
    }

    protected void a(int i2, float f2, int i3, int i4, int i5) {
        RjQ2QkJGQ0U5MkY3NDMwMjkzREY2MjNDMDU2NUJDOUU(i2, f2, i3, i4, i5);
    }

    protected void a(VOSafeTVManager.PlaybackParameters playbackParameters) {
        l lVar = new l(null);
        lVar.m329t7p6390828478468 = playbackParameters.disableNetworkError;
        lVar.m329t7p6390828478469 = playbackParameters.insertSafeTVContentOnRebuffering;
        lVar.m329t7p6390828478431LengthInSeconds = playbackParameters.bufferLengthInSeconds;
        lVar.m329t7p6390828478470 = playbackParameters.qualityHeight;
        lVar.m329t7p6390828478472 = playbackParameters.cachingSizeInKB;
        lVar.mKeepFieldClearCacheEnabledStatus = playbackParameters.cacheEnabledStatus;
        RkI3RjYyOUNCMkZCNDVFRDk1RTgwRTFEMEYxQ0E2QkYK(lVar);
    }

    protected void a(VOSafeTVManager.SecondaryContent secondaryContent) {
        Njk2N0E1RjM1ODcxNDlCQkE2MDMyNjIzQTM5NjRFQzYK(secondaryContent);
    }

    protected void a(boolean z) {
        NjFiZmY5MzkwNmRkNDFhMWI1MzFjY2UxZTk3ZWU1ODIK(z);
    }

    protected void a(boolean z, int i2) {
        N0ZFN0EwOUQzQzQ4NEExNEI4MDQzNjIxMkRDRTIxQzc(z, i2);
    }

    protected VOCachedRange[] a() {
        return (VOCachedRange[]) N0U2M0I0RDFBQkQzNEE5N0IwNUI4NzFDREMzNDk3RjUK();
    }

    protected void abaa(boolean z) {
        if (z) {
            a(d.ENABLE_WMK_FOR_CLEAR_CONTENT);
        }
    }

    public void adSetRequest(String str, AdRequestType adRequestType) throws IOException, IllegalArgumentException {
        if (this.i1) {
            try {
                String str2 = "adonly://" + str.substring(str.lastIndexOf("/"));
                this.O = str2;
                Q0M0MTM5NDMyRDUyNDY5NDg4NEUxQjRBMDhDNTkyRkQ(str2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        RUI2NkNCMjhBOEY2NDQ1REFDQ0M2RjkyRkI1Qzk1NzQ(str, adRequestType.ordinal(), this.h1);
    }

    public void addSecondaryContent(VOSecondaryContent vOSecondaryContent) {
        OEIyMzE0MkJDRUU5NENDMjkyNTc4NkM2RTExNTMzRTg(vOSecondaryContent);
    }

    protected float b() {
        return NUUwODM1RUMxRUUyNDA3QzhGOTBDRjAxMURBRTlGRTQ();
    }

    protected void b(float f2, float f3, float f4) {
        MzU4NDEyOURDMDQzNDQ0Qzg3RkU1OTdFREM1RjczNjI(f2, f3, f4);
    }

    protected void b(int i2) {
        OTUyNTMxQkU1MzI4NDJDRjkyM0U2NDA2MzNBMDE3NUM(i2);
    }

    protected boolean c() {
        return MUY4MjFGQkJFQjUwNDA3OUE3REE5Mzg2NjkwOUM1NTc();
    }

    public boolean canVideoExperienceBeEnabled() throws UnsupportedOperationException {
        return OUM1NEQwMUQwRTVCNDM5MEI5M0E1MTFCQ0RDM0VEM0U();
    }

    public void cancelSecondaryContent() {
        JarSoAlignedRkJDRjJEMkFGRTZDNDgzOUIwMEVFRjgwNTQ4Q0ZDNTc090210();
    }

    public void changeDisplayMode(int i2) {
        if (QTlBRjkxNkNGQTZENDJGNUI0QjMzQUEwMTY4NjBGMjY() && 2 == i2) {
            i2 = 1;
        }
        REZFQTRFNUFCRTA3NEFGRkJBQTlGMDcxNEFFOUM0MUE(i2);
        VOSurfaceView vOSurfaceView = this.e;
        if (vOSurfaceView != null) {
            try {
                vOSurfaceView.a(b());
            } catch (IllegalArgumentException | NullPointerException unused) {
            }
            this.e.a(i2, getVideoWidth(), getVideoHeight());
        }
        VOTIFVideoOverlayView vOTIFVideoOverlayView = this.f;
        if (vOTIFVideoOverlayView != null) {
            vOTIFVideoOverlayView.setVideoPixelAspectRatio(b());
            this.f.a(i2, getVideoWidth(), getVideoHeight());
        }
        this.w = i2;
    }

    public void closeSubtitleFile() throws UnsupportedOperationException {
        z();
        RTVDNUYxOEY5QjU5NEIyQUIwQkUyMkRBMzA2NUY5Rjg();
        m();
    }

    protected void d() {
        QzQzREE3REVGRDcxNDRENzhFQ0MxQzA3RTQwOTg2QzkK();
    }

    public int decodeFrame(int i2) {
        return NzMzM0I3MkJDMjczNEEyQUEyRjIyRTI0QjNEQkU2QTc(i2);
    }

    public void enableAdOnly(boolean z) {
        this.i1 = z;
    }

    public void enableSubtitlesOSD(boolean z) {
        if (z) {
            this.Q = true;
        } else {
            this.Q = false;
            z();
        }
    }

    protected void finalize() {
        this.M = i.RELEASING;
        this.N = true;
        y();
        B1.remove(this);
        g gVar = this.l1;
        if (gVar != null) {
            gVar.dispose();
            this.l1 = null;
        }
        o oVar = this.k;
        if (oVar != null) {
            oVar.a(this.j);
            this.k = null;
        }
        ODkxRUU3ODZDMTJBNEZCN0I0QTM5NUQxQTkyRkM3OUE();
        x();
    }

    public void forceDisplayAspectRatio(float f2) {
        NEIzRUM2MUNENjkwNDQ5MEFDOUZBNjk4NzkwMEQ0MUM(f2);
        VOSurfaceView vOSurfaceView = this.e;
        if (vOSurfaceView != null) {
            try {
                vOSurfaceView.a(b());
            } catch (IllegalArgumentException | NullPointerException unused) {
            }
        }
        VOTIFVideoOverlayView vOTIFVideoOverlayView = this.f;
        if (vOTIFVideoOverlayView != null) {
            vOTIFVideoOverlayView.setVideoPixelAspectRatio(b());
        }
    }

    public void forceMultithreadDecoding(boolean z) {
        ODg4NDEyNzc4NTRFNEE4NkJDRTAyRjFBMERFM0Y5ODI(z);
    }

    public VOAdSwitchPoint[] getAdSwitchPoints() {
        return (VOAdSwitchPoint[]) TDhEDQUI4QkQIzQzIyQTUxMUQ0IzQzIyQTDhERUE4QJD();
    }

    public VOPlayreadyAgent getAgentPlayready() {
        return getAgentPlayready(EDrmEngineType.Embedded);
    }

    public VOPlayreadyAgent getAgentPlayready(EDrmEngineType eDrmEngineType) {
        VOPlayreadyAgent vOPlayreadyAgent;
        int i2 = c.b[eDrmEngineType.ordinal()];
        if (i2 == 1) {
            if (this.C0 == null) {
                this.C0 = new com.viaccessorca.drm.impl.f(this.m329t7p639082847841, this);
            }
            vOPlayreadyAgent = this.C0;
        } else if (i2 != 2 && i2 != 3) {
            vOPlayreadyAgent = null;
        } else if (Nzg5N0Y2MTU5OTY0NEQ0Mzk3NEM4NTM4MjRCMUQwQjI(O1)) {
            if (this.B0 == null) {
                this.B0 = new com.viaccessorca.drm.impl.e(this.m329t7p639082847841);
            }
            vOPlayreadyAgent = this.B0;
        } else {
            if (this.A0 == null) {
                this.A0 = new com.viaccessorca.drm.impl.d(this.m329t7p639082847841, this);
            }
            vOPlayreadyAgent = this.A0;
        }
        if (vOPlayreadyAgent != null) {
            this.x0 = DrmAgent.EDrmType.DRM_TYPE_PLAYREADY;
            this.z0 = eDrmEngineType;
        }
        return vOPlayreadyAgent;
    }

    public VOVerimatrixAgent getAgentVerimatrix() {
        String uniqueIdentifier;
        try {
            uniqueIdentifier = getUniqueIdentifier(this.m329t7p639082847841);
        } catch (VOException e2) {
            e2.printStackTrace();
            uniqueIdentifier = null;
        }
        VerimatrixAgent verimatrixAgentA = VerimatrixAgent.a(this, this.F, uniqueIdentifier);
        this.F0 = verimatrixAgentA;
        this.x0 = DrmAgent.EDrmType.DRM_TYPE_VERIMATRIX;
        return verimatrixAgentA;
    }

    public VOViaccessAgent getAgentVodrm() {
        if (this.D0 == null) {
            com.viaccessorca.drm.impl.m mVar = new com.viaccessorca.drm.impl.m(this.m329t7p639082847841, this);
            this.D0 = mVar;
            this.z0 = EDrmEngineType.MediaDrm;
            mVar.b(this.X0);
        }
        this.x0 = DrmAgent.EDrmType.DRM_TYPE_VODRM;
        return this.D0;
    }

    public VOWidevineAgent getAgentWidevine() {
        if (this.E0 == null) {
            com.viaccessorca.drm.impl.o oVar = new com.viaccessorca.drm.impl.o(this.m329t7p639082847841, this);
            this.E0 = oVar;
            this.z0 = EDrmEngineType.MediaDrm;
            oVar.b(this.X0);
        }
        this.x0 = DrmAgent.EDrmType.DRM_TYPE_WIDEVINE;
        return this.E0;
    }

    public VOAlternateInfo[] getAlternatesInfo() {
        return (VOAlternateInfo[]) QUQ3Qzg3NEREOENFNDZEN0E2RUQ3NTIzM0Y2MjEwRDU();
    }

    public VOAudioTrack[] getAudioTracks() {
        return isCasting() ? ((com.viaccessorca.voplayer.b) this.c1).b() : (VOAudioTrack[]) QzE3NEU1Q0ZGNEI3NDE4RUIxMzUwM0YxRkZERjFFN0E();
    }

    /* JADX WARN: Code duplicated, block: B:12:0x001d  */
    public String getCodecName(VOMediaType vOMediaType) {
        String str;
        int i2 = c.d[vOMediaType.ordinal()];
        if (i2 == 1) {
            str = "Media type Unknow";
        } else {
            if (i2 == 2) {
                NTMxMzM4NzdFNzRBNDVGQjhEODczNjMwMEY5NzQwRTM(1);
            } else if (i2 != 3) {
                str = "Media type Unknow";
            } else {
                NTMxMzM4NzdFNzRBNDVGQjhEODczNjMwMEY5NzQwRTM(0);
            }
            str = this.m329t7p639082847849;
        }
        return (str == null || str.equals("")) ? "voplayer.sw.unknow" : str;
    }

    protected int getCodecTypeForce() {
        return this.r;
    }

    public int getCodecsType() {
        String str;
        if (i.RELEASING == this.M) {
            return -1;
        }
        int iRDNEMkM2NUU0REYyNDhFNTlFMUY3NzI0OUQzRTcwMkY = RDNEMkM2NUU0REYyNDhFNTlFMUY3NzI0OUQzRTcwMkY(this.q, this.r, true);
        switch (iRDNEMkM2NUU0REYyNDhFNTlFMUY3NzI0OUQzRTcwMkY) {
            case 1:
                str = "HW/MC_TU";
                break;
            case 2:
                str = "HW/MC_NT";
                break;
            case 3:
                str = "HW/SF_TU";
                break;
            case 4:
                str = "HW/SF_NT";
                break;
            case 5:
                str = "SW";
                break;
            case 6:
                str = "HW/AML";
                break;
            case 7:
                str = "HW/MC_TU_TV";
                break;
            case 8:
                str = "HW/MC_TU_GL";
                break;
            default:
                str = null;
                break;
        }
        this.V = str;
        return iRDNEMkM2NUU0REYyNDhFNTlFMUY3NzI0OUQzRTcwMkY;
    }

    public int getCurrentBufferLevel() {
        return QzMwQ0ExQjYyQTFCNDc5NDkyOEIwNzNDNzE1NEQxOTI();
    }

    public int getCurrentLiveLatency() {
        return NzI3MDVFQzVBMzc3NEE5NTk4REY4M0NGNkM4QTdBQzIK();
    }

    public VOBaseMediaDrmAgent getCurrentMediaDrmAgent() {
        int i2 = c.c[this.x0.ordinal()];
        if (i2 == 1) {
            return this.C0;
        }
        if (i2 == 3) {
            return this.E0;
        }
        if (i2 != 4) {
            return null;
        }
        return this.D0;
    }

    public long getCurrentPTS() {
        return ODZEMEI1MzlBNjAzNEQyQjg4QjkxQzI3REIyOUE0NUIK();
    }

    public int getCurrentPosition() {
        int iMDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUY;
        Object obj;
        if (isCasting()) {
            iMDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUY = ((com.viaccessorca.voplayer.b) this.c1).c();
        } else {
            try {
                iMDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUY = MDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUY();
            } catch (IllegalStateException unused) {
                iMDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUY = 0;
            }
        }
        if (iMDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUY > 0 && (obj = this.c1) != null) {
            ((com.viaccessorca.voplayer.b) obj).b(iMDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUY);
        }
        return iMDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUY;
    }

    public int getCurrentPositionAbsolute() {
        int iMDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUYAbsolute;
        Object obj;
        if (isCasting()) {
            iMDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUYAbsolute = ((com.viaccessorca.voplayer.b) this.c1).c();
        } else {
            try {
                iMDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUYAbsolute = MDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUYAbsolute();
            } catch (IllegalStateException unused) {
                iMDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUYAbsolute = 0;
            }
        }
        if (iMDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUYAbsolute > 0 && (obj = this.c1) != null) {
            ((com.viaccessorca.voplayer.b) obj).b(iMDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUYAbsolute);
        }
        return iMDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUYAbsolute;
    }

    public int getCurrentUTCTime() {
        return ODQ4MUVGQkIzQTVCNEJBRjg4NTNDM0YzOUIyNTVDNEM();
    }

    public ArrayList<Map.Entry<EDrmType, String>> getDRMAgentCapabilities(Context context) throws VOException {
        ArrayList<Map.Entry<EDrmType, String>> arrayList = new ArrayList<>();
        arrayList.add(new AbstractMap.SimpleEntry(EDrmType.FAIRPLAY, "None"));
        arrayList.add(new AbstractMap.SimpleEntry(EDrmType.WIDEVINE, Build.VERSION.SDK_INT >= 18 ? getAgentWidevine().getMaxSecurityLevelString() : "None"));
        try {
            String lowerCase = getSdkVersion(this.m329t7p639082847841).toLowerCase();
            arrayList.add(new AbstractMap.SimpleEntry(EDrmType.VODRM, d(lowerCase)));
            arrayList.add(new AbstractMap.SimpleEntry(EDrmType.PLAYREADY, c(lowerCase)));
            arrayList.add(new AbstractMap.SimpleEntry(EDrmType.VERIMATRIX, lowerCase.contains("_vmx") ? ExifInterface.TAG_SOFTWARE : "None"));
        } catch (Exception unused) {
            arrayList.add(new AbstractMap.SimpleEntry(EDrmType.VODRM, VOPlaybackSessionReport.UNKNOWN_SVALUE));
            arrayList.add(new AbstractMap.SimpleEntry(EDrmType.PLAYREADY, VOPlaybackSessionReport.UNKNOWN_SVALUE));
            arrayList.add(new AbstractMap.SimpleEntry(EDrmType.VERIMATRIX, VOPlaybackSessionReport.UNKNOWN_SVALUE));
        }
        return arrayList;
    }

    public VOPerformancePoint[] getDefaultPerformancePoints(String str, boolean z) {
        if (MimeTypes.VIDEO_H264.equalsIgnoreCase(str)) {
            VOSystemInfo vOSystemInfo = this.q;
            return z ? vOSystemInfo.performancePointsForSecureH264 : vOSystemInfo.performancePointsForH264;
        }
        if (!MimeTypes.VIDEO_H265.equalsIgnoreCase(str)) {
            throw new IllegalArgumentException();
        }
        VOSystemInfo vOSystemInfo2 = this.q;
        return z ? vOSystemInfo2.performancePointsForSecureHEVC : vOSystemInfo2.performancePointsForHEVC;
    }

    public VODownloadedFragment getDownloadedFragment(int i2) {
        VODownloadedFragment vODownloadedFragment = (VODownloadedFragment) REY2MkUyNDE4NTU0NDFFOTlCM0E1NDYzRTMxNTdERDg(i2);
        if (vODownloadedFragment == null) {
            return null;
        }
        return vODownloadedFragment;
    }

    public int getDuration() {
        return M0E2Q0E3Q0IyMkVBNDFFNDhEMTFFNkExOEIyNzBGNDk();
    }

    public int getExperienceMetricsFail() throws UnsupportedOperationException {
        return Rjg1NkNBRDFCQ0JGNDI4NDg4MEJFNkY3RTNFQjYyQkY();
    }

    public int getExperienceMetricsSuccess() throws UnsupportedOperationException {
        return QTYyMjcyNzI1ODY2NDUxMjlCNThDRjI4NTAzQjlENzM();
    }

    public Bitmap getFrameAt(int i2) {
        com.viaccessorca.voplayer.e eVar = this.j1;
        return eVar != null ? eVar.a(i2) : d(i2);
    }

    public int getHttpStreamingAlternateBitrate() {
        return MUJERjY4RTEwQjdBNDhDMjkzNzEwNTg2OUQ0NDA0NTQ();
    }

    public int getHttpStreamingCurrentBitrate() {
        return RTU3REI2NzU3RTQ4NDgxODg0N0RGRkFCQTIwNEFDQTA();
    }

    public int getHttpStreamingEdgePosition() {
        int[] httpStreamingSeekableRangeExtended = getHttpStreamingSeekableRangeExtended();
        if (httpStreamingSeekableRangeExtended == null || 3 > httpStreamingSeekableRangeExtended.length) {
            return 0;
        }
        return httpStreamingSeekableRangeExtended[2];
    }

    public ByteBuffer getHttpStreamingMetadataId3() {
        int iN0Y2QjA5ODZDNUI2NEMwMjhGRUE5OTRDQzFEQUIxN0ISize = N0Y2QjA5ODZDNUI2NEMwMjhGRUE5OTRDQzFEQUIxN0ISize();
        if (iN0Y2QjA5ODZDNUI2NEMwMjhGRUE5OTRDQzFEQUIxN0ISize <= 0) {
            return null;
        }
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(iN0Y2QjA5ODZDNUI2NEMwMjhGRUE5OTRDQzFEQUIxN0ISize);
        if (byteBufferAllocateDirect != null) {
            N0Y2QjA5ODZDNUI2NEMwMjhGRUE5OTRDQzFEQUIxN0I(byteBufferAllocateDirect, iN0Y2QjA5ODZDNUI2NEMwMjhGRUE5OTRDQzFEQUIxN0ISize);
        }
        return byteBufferAllocateDirect;
    }

    public int[] getHttpStreamingSeekableRange() {
        return Arrays.copyOfRange(getHttpStreamingSeekableRangeExtended(), 0, 2);
    }

    protected int[] getHttpStreamingSeekableRangeExtended() {
        int[] iArrRUVBQ0EzNjlBRkU1NEEyNTk2NjgxOTI1NTZCQjU0MUM = RUVBQ0EzNjlBRkU1NEEyNTk2NjgxOTI1NTZCQjU0MUM();
        if (iArrRUVBQ0EzNjlBRkU1NEEyNTk2NjgxOTI1NTZCQjU0MUM != null && -1 != iArrRUVBQ0EzNjlBRkU1NEEyNTk2NjgxOTI1NTZCQjU0MUM[0]) {
            return iArrRUVBQ0EzNjlBRkU1NEEyNTk2NjgxOTI1NTZCQjU0MUM;
        }
        int[] iArr = {0, 0, 0};
        int duration = getDuration();
        iArr[2] = duration;
        iArr[1] = duration;
        return iArr;
    }

    public IPTVStatistics getIPTVStatistics() {
        return (IPTVStatistics) GyQUM4NkVGNDg3RDIy0OTYwMEJCQTU25NDMTMKTZhNEO();
    }

    public VoContentProtocolType getMainContentProtocol() {
        if (M().booleanValue()) {
            return VoContentProtocolType.VOCONTENT_IPTV_RTP;
        }
        if (getStreamUri() != null && getStreamUri().toLowerCase().contains(".mpd")) {
            return VoContentProtocolType.VOCONTENT_HTTP_DASH;
        }
        if (getStreamUri() == null || !getStreamUri().toLowerCase().contains(".m3u8")) {
            return (getStreamUri() == null || !getStreamUri().toLowerCase().contains("/manifest")) ? VoContentProtocolType.VOCONTENT_UNKNOWN : VoContentProtocolType.VOCONTENT_HTTP_SMOOTHSTREAMING;
        }
        return VoContentProtocolType.VOCONTENT_HTTP_HLS;
    }

    VOPlaybackSessionReport getNonFilteredPlaybackSessionReport() {
        VOPlaybackSessionReport vOPlaybackSessionReport = new VOPlaybackSessionReport();
        if (!QTkwMDRDQTRDRDc0NDA2REIzRDM2RDM4MzM2MUYwQUQ()) {
            return null;
        }
        vOPlaybackSessionReport.c = NjhFOTY0RERBMEFCNDJGNzgzOUJGRUFERTk1RkE4RTAK();
        vOPlaybackSessionReport.d = OTkyRUY4MTZGMDEzNEZEMjhGNDY5RTExNDIwRDAzMEYK();
        return vOPlaybackSessionReport;
    }

    public VOPerformancePoint[] getPerformancePoints(String str, boolean z) {
        VOPerformancePoint[] vOPerformancePointArr;
        if (MimeTypes.VIDEO_H264.equalsIgnoreCase(str)) {
            VOSystemInfo vOSystemInfo = this.q;
            vOPerformancePointArr = z ? vOSystemInfo.m329t7p6390828478424 : vOSystemInfo.m329t7p6390828478425;
        } else {
            if (!MimeTypes.VIDEO_H265.equalsIgnoreCase(str)) {
                throw new IllegalArgumentException();
            }
            VOSystemInfo vOSystemInfo2 = this.q;
            vOPerformancePointArr = z ? vOSystemInfo2.m329t7p6390828478426 : vOSystemInfo2.m329t7p6390828478427;
        }
        return vOPerformancePointArr != null ? vOPerformancePointArr : getDefaultPerformancePoints(str, z);
    }

    public VOPlaybackSessionReport getPlaybackSessionReport() {
        VOPlaybackSessionReport nonFilteredPlaybackSessionReport;
        String str;
        if (!QTkwMDRDQTRDRDc0NDA2REIzRDM2RDM4MzM2MUYwQUQ() || (nonFilteredPlaybackSessionReport = getNonFilteredPlaybackSessionReport()) == null || (str = (String) nonFilteredPlaybackSessionReport.getValue("SES_SESSION_ID")) == null || str.length() == 0) {
            return null;
        }
        try {
            if (((int) ((Float) nonFilteredPlaybackSessionReport.getValue(VOPlaybackSessionReport.SES_SESSION_DURATION)).floatValue()) >= 0 && ((int) ((Float) nonFilteredPlaybackSessionReport.getValue(VOPlaybackSessionReport.SES_CONTENT_DURATION)).floatValue()) >= -1 && ((Integer) nonFilteredPlaybackSessionReport.getValue(VOPlaybackSessionReport.STR_BANDWIDTH_AVG)).intValue() >= 0 && ((Integer) nonFilteredPlaybackSessionReport.getValue(VOPlaybackSessionReport.SVA_MEDIA_BITRATE_AVG)).intValue() >= 0 && ((Integer) nonFilteredPlaybackSessionReport.getValue(VOPlaybackSessionReport.STR_ALTERNATE_SWITCH_DN)).intValue() >= 0 && ((Integer) nonFilteredPlaybackSessionReport.getValue(VOPlaybackSessionReport.STR_ALTERNATE_SWITCH_UP)).intValue() >= 0 && ((Integer) nonFilteredPlaybackSessionReport.getValue(VOPlaybackSessionReport.STR_REBUFFERING_NB)).intValue() >= 0 && ((int) ((Float) nonFilteredPlaybackSessionReport.getValue(VOPlaybackSessionReport.STR_REBUFFERING_TIME_AVG)).floatValue()) >= 0) {
                return nonFilteredPlaybackSessionReport;
            }
            return null;
        } catch (Exception unused) {
        }
    }

    public double getPlaybackSpeed() {
        return this.k0;
    }

    public VOPlayerType getPlayerType() {
        PlayerState state = getState();
        return (PlayerState.PREPARED == state || PlayerState.PLAYING == state || PlayerState.PAUSED == state) ? VOPlayerType.values()[NzM2NjgyQUM4NkVGNDg3RDk1MTJDQjczNjAwQTk5RDUK()] : VOPlayerType.TYPE_UNKNOWN;
    }

    public int getPrimaryCurrentPosition() {
        return RjE2QzkzM0YyOTI4NDhCNEFDMzhCQzExMzgxQUE2QUEK();
    }

    public int getPrimaryDuration() {
        return NzQ4OTQ5MTlDOTFENDQ1MDg4QzE4QTNBNUI2M0NDNEU();
    }

    protected int getPsrId() {
        return RDFDRjA1NDYzNDRENDM5RkExOTU3NkY5NjU2OEMzNTcK();
    }

    public String getRealtimeEventReport() {
        return nativeMP_getRealtimeEventReport();
    }

    public VOSafeTVManager getSafeTVManager() {
        if (this.j0 == null) {
            this.j0 = new VOSafeTVManager();
        }
        this.j0.a(this);
        return this.j0;
    }

    public PlayerState getState() {
        return isCasting() ? PlayerState.CASTING : PlayerState.values()[ODdGQTQzQTNGOUFGNEIwRUE0QzdEMzZCODI5ODVEODM()];
    }

    public String getStreamURL() {
        QzA1NkYwQUNGNkVGNEEyREI5ODBDMUI5MENCNzQ3Mjk();
        return this.m329t7p639082847848;
    }

    public String getStreamUri() {
        return this.O;
    }

    public VOSubtitle getSubtitle(int i2) throws UnsupportedEncodingException {
        VOSubtitle vOSubtitle = new VOSubtitle("emptySub");
        if (!RDNEQkFDODc4RjI2NEI5QUEzMUI0NDBCMjNFQTI5NjE(vOSubtitle, i2)) {
            return null;
        }
        int format = vOSubtitle.getFormat();
        boolean z = true;
        if (format == 0 || format == 1) {
            if (X1.equals("UTF-8") && new String(vOSubtitle.getRAWText(), "UTF-8").getBytes().length != vOSubtitle.getRAWText().length) {
                z = false;
            }
            byte[] rAWText = vOSubtitle.getRAWText();
            if (z) {
                vOSubtitle.setText(rAWText, X1);
            } else {
                vOSubtitle.setText(rAWText, "Cp1252");
            }
        } else if (format == 2) {
            vOSubtitle.setText(vOSubtitle.getRAWText(), "UTF-8");
        } else if (format != 3) {
            vOSubtitle.setText(vOSubtitle.getRAWText(), "unicode");
        } else {
            vOSubtitle.setText(vOSubtitle.getRAWText(), "UTF-16LE");
        }
        return vOSubtitle;
    }

    public VOSubtitleFile[] getSubtitleFiles() {
        return this.S;
    }

    public VOSubtitleTrack[] getSubtitleTracks() {
        return isCasting() ? ((com.viaccessorca.voplayer.b) this.c1).e() : (VOSubtitleTrack[]) RDNEQkFDODc4RjI2NEI5QUEzMUI0NDBCMjNFQTI5NjETracks();
    }

    public Rect getTIFLayoutSurface(int i2, int i3) {
        return VOUtils.getMediaCodecSurfacePosition(M().booleanValue() ? -1 : this.w, getVideoWidth(), getVideoHeight(), b(), i2, i3);
    }

    public VOVRManager getVRManager() {
        if (this.i0 == null) {
            this.i0 = new VOVRManager(this);
        }
        this.i0.a(this);
        return this.i0;
    }

    public int getVideoExperienceMode() throws UnsupportedOperationException {
        return NDJBMUIzNDI3NDFFNEQ4NUEwQURFQzE2NTM0MUY3OEM();
    }

    public int getVideoHeight() {
        return Q0ZCRThGNDg1MDI3NEVERUFEMzZENzk3MjMwNUY5MDU();
    }

    public int getVideoWidth() {
        return RjAyNzJGMjIwODYwNDFCQ0E4MDcwRUE5OUIxQjA5M0U();
    }

    public float getVolume(VOVolumeType vOVolumeType) {
        if (VOVolumeType.VOVolumeTypeDevice == vOVolumeType) {
            AudioManager audioManager = C1;
            if (audioManager != null) {
                return C1.getStreamVolume(3) / audioManager.getStreamMaxVolume(3);
            }
        } else if (VOVolumeType.VOVolumeTypePlayer == vOVolumeType) {
            return this.p;
        }
        return 1.0f;
    }

    protected void internalOnVASTEvent(int i2, Object obj) {
    }

    public boolean isABRAutomaticMode() {
        return this.e0;
    }

    public boolean isCasting() {
        if (this.v) {
            return com.viaccessorca.voplayer.b.t();
        }
        return false;
    }

    public boolean isHttpStreamingAlternateAudioOnly() {
        return MTdCN0I4NEM0QkRENEMzNTg4NjQ3ODA4NUEwM0Y3Qjc();
    }

    public boolean isLooping() {
        return RjBEMEVFQzFERURDNEQ1QUI2OTE5NUEwM0FCRUVFNUU();
    }

    public boolean isPlaying() {
        return (this.c1 == null || !isCasting()) ? c() : ((com.viaccessorca.voplayer.b) this.c1).f();
    }

    public boolean isPrimaryContentPlaying() {
        return NDQ0MzM0NjU1QjlFNDVDRUE1M0NEQUIzMEQ4OEY3QzU();
    }

    public VOSubtitleFile openSubtitleFile(String str) throws UnsupportedOperationException, IOException {
        return openSubtitleFile(str, "", "");
    }

    public VOSubtitleFile openSubtitleFile(String str, String str2, String str3) throws UnsupportedOperationException, IOException {
        int length;
        z();
        VOSubtitleFile[] vOSubtitleFileArr = this.S;
        if (vOSubtitleFileArr == null) {
            length = 1;
        } else {
            length = vOSubtitleFileArr.length < Q1 ? vOSubtitleFileArr.length + 1 : -1;
        }
        if (length < 0) {
            throw new IllegalStateException("Exception 30004");
        }
        VOSubtitleFile vOSubtitleFile = new VOSubtitleFile(str, length, str2, str3, new WeakReference(this));
        MDQzMUY3MjVCNjI4NDIzMUFCRjg5RDQ1RDE2NEM2NkM(str, vOSubtitleFile.getFileId());
        X1 = T1;
        this.R = true;
        n();
        VOSubtitleFile[] vOSubtitleFileArr2 = new VOSubtitleFile[length];
        int i2 = 0;
        while (true) {
            int i3 = length - 1;
            if (i2 >= i3) {
                vOSubtitleFileArr2[i3] = vOSubtitleFile;
                this.S = vOSubtitleFileArr2;
                return vOSubtitleFile;
            }
            vOSubtitleFileArr2[i2] = this.S[i2];
            i2++;
        }
    }

    public void pause() {
        VOCrashReporter.logEvent(this.f73a, "java2");
        g(false);
        if (isCasting()) {
            ((com.viaccessorca.voplayer.b) this.c1).i();
        } else {
            RjM3RURFNzE2NTRENDBEREI5MkIzRTBCQjk0QkMyOUM();
        }
    }

    public void prepare() throws IOException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException();
        }
        prepareAsync();
        try {
            synchronized (this.l0) {
                try {
                    this.l0.wait();
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (InterruptedException unused) {
        }
    }

    public void prepareAsync() {
        HashMap map = new HashMap();
        try {
            map.put("p1", this.O);
            map.put("p2", this.x0.toString());
            map.put("p3", this.u0);
            map.put("p4", this.Z0);
        } catch (Exception unused) {
            map.put("px", "error to retreive them...");
        }
        VOCrashReporter.logEvent(this.f73a, "java7", map);
        PlayerState state = getState();
        if (PlayerState.INITIALIZED == state || PlayerState.IDLE == state || PlayerState.CASTING == state) {
            redirectStreamPlaybackIfRequired(this.O);
            if (this.O == null) {
                throw new IllegalStateException("Exception 30003");
            }
            if (this.p1 == null && !this.q1 && QTlBRjkxNkNGQTZENDJGNUI0QjMzQUEwMTY4NjBGMjY()) {
                throw new IllegalStateException("Exception 30005");
            }
            if (!this.L) {
                if (!a(Boolean.valueOf(QTlBRjkxNkNGQTZENDJGNUI0QjMzQUEwMTY4NjBGMjY() && Nzg5N0Y2MTU5OTY0NEQ0Mzk3NEM4NTM4MjRCMUQwQjI(L1))).booleanValue()) {
                    throw new IllegalStateException("Proguard mapping failed!");
                }
            }
            String strRetrieveSdkInternalVersion = retrieveSdkInternalVersion(this.m329t7p639082847841);
            if (strRetrieveSdkInternalVersion != null && strRetrieveSdkInternalVersion.contains("demo_")) {
                this.c0 = true;
                VOSurfaceView vOSurfaceView = this.e;
                if (vOSurfaceView != null) {
                    vOSurfaceView.a(true);
                }
            }
            B1.remove(this);
            B1.add(this);
            if (true == this.M0) {
                try {
                    setDataSource(this.O);
                } catch (IOException unused2) {
                }
                setVideoView(this.g);
                setScreenOnWhilePlaying(this.n);
                setAudioStreamType(this.N0);
                setLooping(this.O0);
                setHttpStreamingEventPlaylistEnabled(this.Q0);
                setHttpStreamingTypicalBitrate(this.y);
                setHttpStreamingMinimumBitrate(this.A);
                setHttpStreamingMaximumBitrate(this.z);
                setHttpStreamingPreferredVideoCodecType(this.E);
                d(this.T);
                setProxyParameters(this.R0, this.S0, ProxyType.values()[this.T0], this.U0, this.V0);
                setCookie(this.W0);
                setUserAgent(this.X0);
                setAdUserAgent(this.Y0);
                j(this.h0);
                i(this.P0);
                a(this.x0, this.z0);
                g(this.a1);
                h(this.u0);
                a(this.v0);
                b(this.w0);
                f(this.Z0);
                this.M0 = false;
            }
            d(this.T);
            if (this.m1) {
                setSmartLibDetected(true);
            }
            if (this.j != null) {
                this.M = i.PREOPENING;
                new j(this, null).start();
            }
        }
    }

    public void prepareChromeCast() {
        if (isCasting()) {
            RjM3RURFNzE2NTRENDBEREI5MkIzRTBCQjk0QkMyOUM();
            a(this.x0, this.z0);
            g(this.a1);
            h(this.u0);
            a(this.v0);
            b(this.w0);
            f(this.Z0);
            int iMDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUY = MDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUY();
            if (this.f1) {
                startAt(iMDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUY);
                return;
            }
            ((com.viaccessorca.voplayer.b) this.c1).b(iMDFGNkRBNEE1RDZCNEY4NEE1NTJFNkIwQ0QwQjM2RUY);
            CopyOnWriteArraySet<OnPreparedListener> copyOnWriteArraySet = this.r1;
            if (copyOnWriteArraySet != null) {
                Iterator<OnPreparedListener> it = copyOnWriteArraySet.iterator();
                while (it.hasNext()) {
                    it.next().onPrepared(this);
                }
            }
        }
    }

    protected void redirectStreamPlaybackIfRequired(String str) {
    }

    public void release() {
        WeakReference[] weakReferenceArr;
        VOCrashReporter.logEvent(this.f73a, "java4");
        setLifecycle(null);
        isCasting();
        g gVar = this.l1;
        if (gVar != null) {
            gVar.dispose();
        }
        VOAdBannerManager vOAdBannerManager = this.b1;
        if (vOAdBannerManager != null) {
            vOAdBannerManager.reset();
        }
        o oVar = this.k;
        if (oVar != null) {
            oVar.a(this.j);
            this.k = null;
        }
        if (this.e != null && QTlBRjkxNkNGQTZENDJGNUI0QjMzQUEwMTY4NjBGMjY()) {
            this.e.a(-1, 20004);
        }
        g(false);
        u();
        this.r1.clear();
        this.t1.clear();
        this.u1.clear();
        this.s1.clear();
        this.v1.clear();
        this.x1.clear();
        this.y1.clear();
        this.w1.clear();
        this.e = null;
        this.m329t7p639082847843 = null;
        this.N = true;
        this.M = i.RELEASING;
        B1.remove(this);
        x();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            weakReferenceArr = J1;
            if (i2 >= weakReferenceArr.length) {
                break;
            }
            if (weakReferenceArr[i2] == I1) {
                i3 = i2;
            }
            i2++;
        }
        J1 = remove(weakReferenceArr, i3);
        I1 = null;
        this.U.a(null, 0);
        this.H = false;
        o();
        z();
        NUQ0MDk2QjI3RjBBNEIwMjkwN0Q5OUQ5MUUwNkM3NDg();
        m();
        this.K0 = false;
        y();
        l();
        if (this.m329t7p6390828478403 != null) {
            this.m329t7p6390828478403 = null;
        }
        Object obj = this.c1;
        if (obj != null) {
            ((com.viaccessorca.voplayer.b) obj).j();
            this.c1 = null;
        }
        this.g1 = false;
        com.viaccessorca.voplayer.e eVar = this.j1;
        if (eVar != null) {
            eVar.d();
            this.j1 = null;
        }
        VOCrashReporter.logEvent(this.f73a, "java8");
    }

    public void removeOnBufferingUpdateListener(OnBufferingUpdateListener onBufferingUpdateListener) {
        this.t1.remove(onBufferingUpdateListener);
    }

    public void removeOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.s1.remove(onCompletionListener);
    }

    public void removeOnDownloadUpdateListener(OnDownloadUpdateListener onDownloadUpdateListener) {
        this.u1.remove(onDownloadUpdateListener);
    }

    public void removeOnErrorListener(OnErrorListener onErrorListener) {
        this.x1.remove(onErrorListener);
    }

    public void removeOnInformationListener(OnInformationListener onInformationListener) {
        this.y1.remove(onInformationListener);
    }

    public void removeOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.r1.remove(onPreparedListener);
    }

    public void removeOnSeekCompleteListener(OnSeekCompleteListener onSeekCompleteListener) {
        this.v1.remove(onSeekCompleteListener);
    }

    public void removeOnVideoSizeChangedListener(OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.w1.remove(onVideoSizeChangedListener);
    }

    public void reset() {
        this.N = true;
        setLifecycle(null);
        if (isCasting()) {
            ((com.viaccessorca.voplayer.b) this.c1).k();
        }
        VOAdBannerManager vOAdBannerManager = this.b1;
        if (vOAdBannerManager != null) {
            vOAdBannerManager.reset();
            this.b1 = null;
        }
        o oVar = this.k;
        if (oVar != null) {
            oVar.a(this.j);
            this.k = null;
        }
        f fVar = this.j;
        if (fVar != null) {
            if (fVar.hasMessages(MEDIA_INFO_VAST_ADVERTISEMENT)) {
                this.M = i.CREATED;
            }
            if (this.j.hasMessages(MEDIA_INFO_VAST_NOT_ADVERTISEMENT)) {
                this.M = i.CREATED;
            }
            if (this.j.hasMessages(1)) {
                this.M = i.CREATED;
            }
            this.j.removeCallbacksAndMessages(null);
        }
        for (int i2 = 0; i.PREOPENING == this.M && i2 < 1000; i2 += 50) {
            try {
                Thread.sleep(50L);
            } catch (InterruptedException unused) {
            }
        }
        z();
        this.Q = false;
        this.N = false;
        this.s = true;
        g(false);
        this.Z = null;
        this.d1 = null;
        this.J = QTYyMjcyNzI1ODY2NDUxMjlCNThDRjI4NTAzQjlENzM();
        this.K = Rjg1NkNBRDFCQ0JGNDI4NDg4MEJFNkY3RTNFQjYyQkY();
        QjczODQ2ODQwOTU3NDNBMkE0OThENDA1Mzg1QURCMzU();
        m();
        if (this.h) {
            J();
        }
        a((VOSurfaceView) null, 0);
        this.H = false;
        this.g = null;
        this.x = false;
        this.t = -1;
        this.W = 0L;
        this.V = null;
        this.X = 0;
        this.Y = 0;
        this.Z = null;
        this.g0 = 0;
        this.j.removeCallbacksAndMessages(null);
        Handler handler = this.k1;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.T = true;
        this.a0 = 0;
        this.z1 = false;
        this.K0 = false;
        y();
        l();
        this.u0 = null;
        this.v0 = null;
        this.w0 = null;
        this.Z0 = null;
        this.a1 = null;
        this.h0 = null;
        this.P0 = null;
        this.H0 = false;
        this.G0 = 0;
        AdRequestOptions adRequestOptions = this.h1;
        adRequestOptions.nonLinearClosable = false;
        adRequestOptions.nonLinearDurationOverride = -1;
        adRequestOptions.vmapAutoRefresh = false;
        adRequestOptions.vmapAutoRefreshPeriod = -1;
        adRequestOptions.vmapSkipOffsetOverrideStatus = -1;
        adRequestOptions.vmapSkipOffsetOverrideValue = null;
        adRequestOptions.vmapSeekBackwardPlayUpTo = 0;
        adRequestOptions.vmapSeekForwardPlayUpTo = -1;
        adRequestOptions.vastReportLastProgressEvent = false;
        adRequestOptions.vastDefineUrlOnly = false;
        x();
        String str = this.F;
        if (str != null) {
            MDdBNTA0OTY3QzdCNEZFRThBMjYzNEZEQzBENzY5Mzk(str);
        }
        String str2 = D1;
        if (str2 != null) {
            MjcxNTYzREU2MjlDNDEwM0FDMzgwQjUxRDM4RUU3Qjg(str2);
        }
        QzA0MkYyRDlCQ0E2NDg1ODgxNjBGNTE1NjIwOTkxQTM(this.m329t7p639082847841, D1, DrmAgent.useAndroidXDependency());
        RjNGRjZGMzAxMkVBNEZGNUFGNTUwMjVFOTExRkE4NzY(this.m329t7p639082847841.getPackageName());
        if (!VOPlatformAnalyzer.g()) {
            forceMultithreadDecoding(false);
        }
        m(this.m329t7p639082847841);
        VOLogger.setForceMinPriority(false);
        if (Nzg5N0Y2MTU5OTY0NEQ0Mzk3NEM4NTM4MjRCMUQwQjI(L1)) {
            setDebugOption(DebugOption.DBG_OPT_SHOW_PLAYER_INFO, false);
        } else {
            setDebugOption(DebugOption.DBG_OPT_SHOW_PLAYER_INFO, true);
        }
        this.U.a(null, 0);
        com.viaccessorca.voplayer.e eVar = this.j1;
        if (eVar != null) {
            eVar.d();
        }
    }

    public void seekTo(int i2) {
        VOCrashReporter.logEvent(this.f73a, "java3: " + i2);
        if (isCasting()) {
            ((com.viaccessorca.voplayer.b) this.c1).a(i2);
        } else {
            seekTo(i2, 0);
        }
    }

    public void seekTo(int i2, int i3) {
        if (isCasting()) {
            ((com.viaccessorca.voplayer.b) this.c1).a(i2);
            return;
        }
        com.viaccessorca.voplayer.e eVar = this.j1;
        if (eVar != null && 2 == i3 && (eVar.b() == com.viaccessorca.voplayer.e.j.AVAILABLE || this.j1.b() == com.viaccessorca.voplayer.e.j.COMPLETE)) {
            int iA = this.j1.a();
            if (iA != 0) {
                i2 = iA;
            }
            i3 = 1;
        } else if (2 == i3) {
            i3 = 0;
        }
        NjFEMDk1RTE1QTE2NEY5Qzg4ODJBNzVBMkE3MzEzNDc(i2, i3);
    }

    public void setAdCallbackForTunnelSkipping(boolean z) {
        AdRequestOptions adRequestOptions = this.h1;
        if (adRequestOptions != null) {
            adRequestOptions.waitCallback = z;
        }
    }

    public void setAdSeekToAllowed(boolean z) {
        OURENjYyODJFNDg0NEVGNUI4RkM5MkU1NkIyQzkzQTY(z);
    }

    public void setAdSkipPrevention(boolean z) {
        RUExNTAyMThDNDVCNDE2NUI1QzhFQUU2MDgxNDdCOTgK(z);
    }

    public void setAdUserAgent(String str) {
        this.Y0 = str;
        if (str != null) {
            MjNFRDdDRUNGODkzNDBENjhCRUM3RDBGNjM4M0I3ODMK(str);
        }
    }

    public int setAlternateByIndex(int i2) throws UnsupportedOperationException {
        int iNzk2RDVGQTMzNjE3NDUxNjg1MjE2RDVCQkM2NDU3NDI = Nzk2RDVGQTMzNjE3NDUxNjg1MjE2RDVCQkM2NDU3NDI(i2);
        this.e0 = -1 == i2;
        return iNzk2RDVGQTMzNjE3NDUxNjg1MjE2RDVCQkM2NDU3NDI;
    }

    public void setAnalyticsMetadata(String str, String str2) throws IllegalStateException, IllegalArgumentException {
        String str3;
        if (str == null || str.length() < 6) {
            throw new IllegalStateException();
        }
        if (str2 == null || str2.length() < 1) {
            throw new IllegalStateException();
        }
        if (!a(str, new String[]{"CHANNEL_ID", "CHANNEL_NAME", "CONTENT_ID", "CONTENT_NAME", "OPERATOR_NAME", "ACCOUNT_ID", "PROFILE_ID", "APPLICATION_NAME", "APPLICATION_VERSION", "CDN_ID", "SERIAL_NUMBER_ID"})) {
            if (!str.equals("APP_PAUSE_NB_USER#") && !str.equals("SES_SESSION_TYPE#")) {
                throw new IllegalArgumentException();
            }
            if (!NTNGMkU0RDIxQTBCNDRGREI3QkExREEyRUQyNkExN0IK(str, str2)) {
                throw new IllegalArgumentException();
            }
            return;
        }
        if (str.equals("CONTENT_ID") || str.equals("CHANNEL_ID")) {
            str3 = "SES_CONTENT_ID#";
        } else if (str.equals("CONTENT_NAME") || str.equals("CHANNEL_NAME")) {
            str3 = "SES_CONTENT_NAME#";
        } else if (str.equals("OPERATOR_NAME")) {
            str3 = "MDA_DEVICE_OPERATOR#";
        } else if (str.equals("ACCOUNT_ID")) {
            str3 = "SES_SESSION_ACCOUNT-ID#";
        } else if (str.equals("PROFILE_ID")) {
            str3 = "SES_SESSION_USER-ID#";
        } else if (str.equals("APPLICATION_NAME")) {
            str3 = "MDA_APPLICATION_NAME";
        } else if (str.equals("APPLICATION_VERSION")) {
            str3 = "MDA_APPLICATION_VERSION#";
        } else if (str.equals("CDN_ID")) {
            str3 = "SES_CDN_SESSION-ID#";
        } else {
            str3 = str.equals("SERIAL_NUMBER_ID") ? "SES_SESSION_SERIAL-ID#" : "";
        }
        if (!NTNGMkU0RDIxQTBCNDRGREI3QkExREEyRUQyNkExN0IK(str3, str2)) {
            throw new IllegalArgumentException();
        }
    }

    public void setApplicationInBackground(boolean z) {
        this.q1 = true;
        c(z);
    }

    public void setAudioBoosterMode(int i2) throws UnsupportedOperationException {
        if (-1 == i2) {
            MUUyOTVGQzNGNzhFNEE0RkEzQjU0QkYyREU3Mzk1N0E(1, false, 0);
        } else {
            MUUyOTVGQzNGNzhFNEE0RkEzQjU0QkYyREU3Mzk1N0E(1, true, i2);
        }
    }

    public void setAudioPresenter(AudioPresenter audioPresenter) throws UnsupportedOperationException, IllegalStateException {
        if (Build.VERSION.SDK_INT < 19 && audioPresenter == AudioPresenter.AUDIOTRACK) {
            throw new UnsupportedOperationException("min sdkVersion supported:19");
        }
        QjJDQTI0RUMyQkMzNEJCOTlDRDRDNzlCMTdBM0U3MDIK(audioPresenter.ordinal());
    }

    public void setAudioStreamType(int i2) {
        this.N0 = i2;
        NkE1NTg1QjBGMUYwNDU3NEFDMTRDNTM3RkMyNkZBN0E(i2);
    }

    public int setAudioTrack(String str) throws UnsupportedOperationException {
        this.d1 = str;
        if (str != null) {
            return isCasting() ? ((com.viaccessorca.voplayer.b) this.c1).a(str) : NTMwOTE1NkVDQTZENEU2REJBOEJGMTFDODVBQTM0MDg(str);
        }
        return -1;
    }

    public void setCastChannelName(String str) {
        if (L()) {
            Object obj = this.c1;
            if (obj == null) {
                this.c1 = new com.viaccessorca.voplayer.b(str, this);
            } else {
                ((com.viaccessorca.voplayer.b) obj).b(str);
            }
        }
    }

    public void setCastContext(Object obj) {
        if (L()) {
            Object obj2 = this.c1;
            if (obj2 == null) {
                this.c1 = new com.viaccessorca.voplayer.b(obj, this);
            } else {
                ((com.viaccessorca.voplayer.b) obj2).a(obj);
            }
        }
    }

    public void setCastMetadata(Object obj) {
        Object obj2 = this.c1;
        if (obj2 != null) {
            ((com.viaccessorca.voplayer.b) obj2).b(obj);
        }
    }

    public void setChromeCastAlternateParameters(String str, String str2, DrmAgent.EDrmType eDrmType, String[] strArr, String[] strArr2, String str3, VOCCastExternalSubtitle[] vOCCastExternalSubtitleArr) {
        Object obj = this.c1;
        if (obj != null) {
            ((com.viaccessorca.voplayer.b) obj).a(str, str2, eDrmType, strArr, strArr2, str3, vOCCastExternalSubtitleArr);
        }
    }

    public void setChromeCastMode(boolean z) {
        this.f1 = z;
    }

    public void setClearVideoViewAtReset(boolean z) {
        this.h = z;
    }

    public void setCookie(String str) {
        this.W0 = str;
        if (str != null) {
            M0E3RUFBMzFCRTU2NERCQzhGNkU3MEQ4MDVCN0Q2OUI(str);
        }
    }

    /* JADX WARN: Code duplicated, block: B:91:0x0189 A[PHI: r6
  0x0189: PHI (r6v30 int) = (r6v29 int), (r6v35 int) binds: [B:66:0x014d, B:89:0x0186] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x01e0, code lost:
    
        if (r16.q.isTV != false) goto L111;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setCustomMode(java.util.Map<java.lang.String, java.lang.Object> r17) throws java.lang.UnsupportedOperationException, java.lang.IllegalArgumentException {
        /*
            Method dump skipped, instruction units count: 524
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.viaccessorca.voplayer.VOPlayer.setCustomMode(java.util.Map):void");
    }

    public void setDataSource(Context context, Uri uri) throws IOException, SecurityException, IllegalArgumentException {
        String scheme = uri.getScheme();
        setDataSource((scheme == null || scheme.equals("file")) ? uri.getPath() : uri.toString());
    }

    public void setDataSource(String str) throws IOException, IllegalArgumentException {
        VOCrashReporter.logEvent(this.f73a, "java6: " + str);
        if (str == null) {
            throw new IllegalArgumentException("Exception 30000");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Exception 30001");
        }
        str.contains(" ");
        this.O = str;
        Q0M0MTM5NDMyRDUyNDY5NDg4NEUxQjRBMDhDNTkyRkQ(str);
        Object obj = this.c1;
        if (obj != null) {
            ((com.viaccessorca.voplayer.b) obj).e(str);
        }
    }

    public void setDebugLevel(Map<DebugModule, DebugLevel> map) throws UnsupportedOperationException, IllegalArgumentException {
        if (H1 || map == null) {
            return;
        }
        for (DebugModule debugModule : map.keySet()) {
            NUU5OTFGOUQ0OUVENDQxMTkyNjA5QTJEOENBNzE0NDE(debugModule.ordinal(), map.get(debugModule).ordinal());
        }
    }

    public void setDebugOption(DebugOption debugOption, Boolean bool) throws UnsupportedOperationException, IllegalArgumentException {
        if (DebugOption.DBG_OPT_SHOW_PLAYER_INFO == debugOption) {
            if (bool.booleanValue()) {
                this.W = System.currentTimeMillis();
                return;
            } else {
                this.W = 0L;
                f(false);
                return;
            }
        }
        if (DebugOption.DBG_OPT_DISABLE_PROGUARD_CHECK == debugOption) {
            this.L = bool.booleanValue();
        } else {
            if (DebugOption.DBG_OPT_FORCE_VOPLAYERAPP_REDIRECTION == debugOption) {
                bool.booleanValue();
                return;
            }
            if (DebugOption.DBG_OPT_FORCE_MIN_INFO == debugOption) {
                VOLogger.setForceMinPriority(bool.booleanValue());
            }
            MDFDRUQ1OEI5NDE3NEY4QkI2MTg5Rjg0REE2QkMwRkU(debugOption.ordinal(), bool.booleanValue());
        }
    }

    public void setDisableTunnelIfAdRedirect(boolean z) {
        AdRequestOptions adRequestOptions = this.h1;
        if (adRequestOptions != null) {
            adRequestOptions.disableTunnelIfAdRedirect = z;
        }
    }

    public void setDisableTunnelIfAdUnavailable(boolean z) {
        AdRequestOptions adRequestOptions = this.h1;
        if (adRequestOptions != null) {
            adRequestOptions.disableTunnelIfAdUnavailable = z;
        }
    }

    public void setHttpAuthentication(String str, String str2) {
        Q0U2MjIxQkFBNjY3NEY2OEJCMERBRDc5MTlFOEZCMzY(str, str2);
    }

    public void setHttpCustomHeader(String str) {
        NkY4MUJEMDcwQzVDNDZGQ0E3NDRGNTNFOTczNDIzMzA(str);
    }

    public void setHttpStreamingAbrControlSettings(AbrControls abrControls) throws UnsupportedOperationException, IllegalArgumentException {
        if (abrControls != null) {
            NkVGM0VCOEMyNDJENDQwNjk0OENDNUU5Q0I1RTc0MTM(abrControls);
        }
    }

    public void setHttpStreamingEventPlaylistEnabled(boolean z) throws UnsupportedOperationException {
        this.Q0 = z;
        MjBCNDAwNEREMDYxNEMxRUI3NjlGQUExRjg4RjIyNjc(z);
    }

    public void setHttpStreamingMaximumBitrate(int i2) throws UnsupportedOperationException {
        this.z = i2;
        int i3 = this.y;
        if (i3 > i2 && i2 >= 0 && i3 >= 0) {
            this.y = i2;
            MUU0OEQ3QTlEMjlCNDNCQTg3NTBENzg5NjhFMzM5NjE(i2);
        }
        int i4 = this.z;
        if (i4 >= 0) {
            OTY2RUFGQUI2NTVGNDQ5MDkxNTZDNUZEM0M2QUZGMzM(i4);
        }
    }

    public void setHttpStreamingMetadataId3Enabled(boolean z) {
        RDEyNzE2QTUzN0VENDdEODg5OEUxRjYwNTg4MjhENjI(z);
    }

    public void setHttpStreamingMinimumBitrate(int i2) throws UnsupportedOperationException {
        this.A = i2;
        int i3 = this.y;
        if (i3 < i2 && i2 >= 0 && i3 >= 0) {
            this.y = i2;
            MUU0OEQ3QTlEMjlCNDNCQTg3NTBENzg5NjhFMzM5NjE(i2);
        }
        int i4 = this.A;
        if (i4 >= 0) {
            RUMwMjQ5QkY3QUNGNDBGRkI0RjZGMzk2MEUzMTA5MTU(i4);
        }
    }

    public void setHttpStreamingOutputHttpHeaderFiltering(HttpHeaderFileType httpHeaderFileType, String str) {
        MjgxMDZENTk1Q0JDNDhERUE4Q0JDNTNBOUFDQjVCRDA(httpHeaderFileType.ordinal(), str);
    }

    public void setHttpStreamingPreferredVideoCodecType(PreferredVideoCodecType preferredVideoCodecType) {
        this.E = preferredVideoCodecType;
        QjczODBFMTYxRDJCNDBFOUI4M0RFOTlGMjk1Rjk4NjcK(preferredVideoCodecType.ordinal());
    }

    public void setHttpStreamingTypicalBitrate(int i2) {
        this.y = i2;
        MUU0OEQ3QTlEMjlCNDNCQTg3NTBENzg5NjhFMzM5NjE(i2);
    }

    public void setLifecycle(Lifecycle lifecycle) {
        VOLifecycleObserver vOLifecycleObserver;
        Lifecycle lifecycle2 = this.p1;
        if (lifecycle2 != null && (vOLifecycleObserver = this.o1) != null && lifecycle != lifecycle2) {
            lifecycle2.removeObserver(vOLifecycleObserver);
        }
        this.p1 = lifecycle;
        if (lifecycle != null) {
            if (this.o1 == null) {
                this.o1 = new VOLifecycleObserver(this, null);
            }
            this.p1.addObserver(this.o1);
        }
    }

    public void setLooping(boolean z) {
        this.O0 = z;
        N0I3ODcxNjA5NUNFNEVGRTgwQThCNkZBNjU4RDBBM0E(z);
    }

    public int setMuted(boolean z) {
        if (isCasting()) {
            return 0;
        }
        return MjQ2NTAyQzU0NzNCNDg1N0FFMUM3NTQ5M0EwMzM3OEU(z);
    }

    protected void setNetworkEmulator(int i2) {
        MzY3QkUxRkE2RDc3NDFDMTg3RDdBNUIzQ0U3QzIyQzA(i2);
    }

    public void setNonLinearClosable(boolean z) {
        AdRequestOptions adRequestOptions = this.h1;
        if (adRequestOptions != null) {
            adRequestOptions.nonLinearClosable = z;
        }
    }

    public void setNonLinearDurationOverride(int i2) {
        AdRequestOptions adRequestOptions = this.h1;
        if (adRequestOptions != null) {
            adRequestOptions.nonLinearDurationOverride = i2;
        }
    }

    public void setOnBufferingUpdateListener(OnBufferingUpdateListener onBufferingUpdateListener) {
        this.t1.add(onBufferingUpdateListener);
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.s1.add(onCompletionListener);
    }

    public void setOnDownloadUpdateListener(OnDownloadUpdateListener onDownloadUpdateListener) {
        this.u1.add(onDownloadUpdateListener);
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.x1.add(onErrorListener);
    }

    public void setOnInformationListener(OnInformationListener onInformationListener) {
        this.y1.add(onInformationListener);
    }

    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.r1.add(onPreparedListener);
    }

    public void setOnSeekCompleteListener(OnSeekCompleteListener onSeekCompleteListener) {
        this.v1.add(onSeekCompleteListener);
    }

    public void setOnVideoSizeChangedListener(OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.w1.add(onVideoSizeChangedListener);
    }

    public void setPerformancePoints(String str, boolean z, VOPerformancePoint[] vOPerformancePointArr) {
        VOPerformancePoint[] vOPerformancePointArr2;
        if (vOPerformancePointArr != null) {
            vOPerformancePointArr2 = (VOPerformancePoint[]) Arrays.copyOf(vOPerformancePointArr, vOPerformancePointArr.length);
            Arrays.sort(vOPerformancePointArr2, new b(this));
        } else {
            vOPerformancePointArr2 = null;
        }
        if (MimeTypes.VIDEO_H264.equalsIgnoreCase(str)) {
            VOSystemInfo vOSystemInfo = this.q;
            if (z) {
                vOSystemInfo.m329t7p6390828478424 = vOPerformancePointArr2;
                return;
            } else {
                vOSystemInfo.m329t7p6390828478425 = vOPerformancePointArr2;
                return;
            }
        }
        if (!MimeTypes.VIDEO_H265.equalsIgnoreCase(str)) {
            throw new IllegalArgumentException();
        }
        VOSystemInfo vOSystemInfo2 = this.q;
        if (z) {
            vOSystemInfo2.m329t7p6390828478426 = vOPerformancePointArr2;
        } else {
            vOSystemInfo2.m329t7p6390828478427 = vOPerformancePointArr2;
        }
    }

    public void setPlatformAudioDecodingEnabled(boolean z) throws UnsupportedOperationException {
        N0U3NzVGQTlGMkEzNERBRDgyQTY5OTU4MUM2NDEzREU(z);
        this.G = z;
    }

    public void setPlaybackSpeed(double d2) {
        this.k0 = d2;
        RDU0MjZFNDJBMDRBNEYyNEFFQUI2MDFEOTIxQjY1Mzg(d2);
    }

    public void setPlayerType(VOPlayerType vOPlayerType, VOMultiViewPlayerConfig vOMultiViewPlayerConfig) throws IllegalArgumentException {
        PlayerState state = getState();
        if (PlayerState.INITIALIZED != state && PlayerState.PLAYING != state && PlayerState.PAUSED != state && PlayerState.PREPARED != state) {
            throw new IllegalArgumentException();
        }
        boolean z = VOPlayerType.TYPE_LEADER == vOPlayerType;
        this.z1 = true;
        n nVar = null;
        if (vOMultiViewPlayerConfig != null) {
            nVar = new n();
            nVar.m329t7p6390828478466 = vOMultiViewPlayerConfig.synchroDelay;
        }
        NjczNjY1OUMyMTY4NEExMUJDRjk2NjY4RjJBNTY2MTMK(z, nVar);
    }

    public void setProxyParameters(String str, int i2, ProxyType proxyType, String str2, String str3) {
        this.R0 = str;
        this.S0 = i2;
        int iOrdinal = proxyType.ordinal();
        this.T0 = iOrdinal;
        this.U0 = str2;
        this.V0 = str3;
        if (str != null) {
            QTcyRDExOEU2QUU2NDRFRDg3RDRCREM3OTZGOTA2QUI(this.R0, this.S0, iOrdinal, str2, str3);
        }
    }

    public void setQuarterDisplay(VOQuarterInfo vOQuarterInfo) {
        VOSurfaceView vOSurfaceView = this.e;
        if (vOSurfaceView != null) {
            vOSurfaceView.setQuarterDisplay(vOQuarterInfo);
        }
    }

    public void setRealtimeEventReportBackendURL(String str) {
        nativeMP_setRealtimeEventReportBackendURL(str);
    }

    public void setSSLCACert(String str, int i2) {
        MzQxMjk3MTVEMUE3NENCNEFCQzM3N0E3MUJDMjQ4OTA(str, i2);
    }

    public void setSSLClientCert(String str, int i2, String str2, int i3) {
        NDMwNzdCOEZBN0VENDgxNEFBNzA2QUVCQTQ3NEUxQUM(str, i2, str2, i3);
    }

    public void setScreenOnWhilePlaying(boolean z) {
        if (this.n != z) {
            this.n = z;
            u();
        }
    }

    protected void setSmartLibDetected(boolean z) {
        this.m1 = z;
        RThBQTg4OURFMzk3NEVFQkFFNEQ5MzY0RENDQURGNTkK(z);
    }

    public int setSubtitleTrack(String str, int i2) throws UnsupportedOperationException {
        String str2;
        z();
        this.e1 = str;
        if (isCasting()) {
            return ((com.viaccessorca.voplayer.b) this.c1).a(str, i2);
        }
        if (1 != i2) {
            m();
        }
        int iRkYxQzg4QUUwNkMwNDk0QTg3MUFGNkU4MDc0QUQzRTc = RkYxQzg4QUUwNkMwNDk0QTg3MUFGNkU4MDc0QUQzRTc(str, i2);
        if (iRkYxQzg4QUUwNkMwNDk0QTg3MUFGNkU4MDc0QUQzRTc == 0) {
            this.R = true;
            n();
            if (i2 == 1) {
                X1 = T1;
                if (str != null) {
                    int i3 = 0;
                    while (true) {
                        String[] strArr = Y1;
                        if (i3 >= strArr.length) {
                            break;
                        }
                        if (strArr[i3].toLowerCase(Locale.getDefault()).equals(str.toLowerCase(Locale.getDefault()))) {
                            str2 = Z1[i3];
                        } else {
                            i3++;
                        }
                    }
                }
            } else if (i2 == 2) {
                str2 = U1;
            } else if (i2 == 3) {
                str2 = V1;
            } else {
                str2 = i2 == 6 ? W1 : T1;
            }
            X1 = str2;
            break;
        }
        return iRkYxQzg4QUUwNkMwNDk0QTg3MUFGNkU4MDc0QUQzRTc;
    }

    public void setSubtitleVisibility(boolean z) {
        if (!z) {
            if (isCasting()) {
                ((com.viaccessorca.voplayer.b) this.c1).l();
            }
            z();
        }
        a(z, -1);
    }

    public void setTIFVideoOverlayView(VOTIFVideoOverlayView vOTIFVideoOverlayView) {
        if (this.P == null) {
            this.P = new m(this);
        }
        this.f = vOTIFVideoOverlayView;
    }

    public void setTIFVideoSurface(Surface surface) {
        this.i = true;
        this.m329t7p639082847844 = surface;
        this.r = 7;
        OThBQTI5QkYzNkI5NDJGNDkxRERGNzM3Qjg1RjAzODE(this.q, 7);
        RUQ3QTZDRTFBRDAyNDFBNDk2RDZDMTE0Q0U1MEM2QkI();
    }

    public void setThumbnailUrl(String str, ScrubbingSpriteInfo scrubbingSpriteInfo, ScrubbingConfiguration scrubbingConfiguration) {
        if (Q()) {
            if (this.j1 == null) {
                i iVar = this.M;
                this.j1 = (iVar == i.PREPARED || iVar == i.PLAYING || iVar == i.PAUSED || iVar == i.BUFFERING || iVar == i.STOPPED) ? new com.viaccessorca.voplayer.e(this, scrubbingConfiguration, true) : new com.viaccessorca.voplayer.e(this, scrubbingConfiguration, false);
            }
            this.j1.a(str, scrubbingSpriteInfo);
        }
    }

    public void setUpdateRightsMode(int i2) {
        nativeMP_setUpdateRightsMode(i2);
    }

    public void setUserAgent(String str) {
        this.X0 = str;
        if (str != null) {
            MzM0NjhCMzY2ODYxNEIwN0E4OTRBRTY2OTg3RjgwMjA(str);
        }
        com.viaccessorca.drm.impl.o oVar = this.E0;
        if (oVar != null) {
            oVar.b(str);
        }
    }

    public void setVastDefineUrlOnly(boolean z) {
        AdRequestOptions adRequestOptions = this.h1;
        if (adRequestOptions != null) {
            adRequestOptions.vastDefineUrlOnly = z;
        }
    }

    public void setVastReportLastProgressEvent(boolean z) {
        AdRequestOptions adRequestOptions = this.h1;
        if (adRequestOptions != null) {
            adRequestOptions.vastReportLastProgressEvent = z;
        }
    }

    public void setVideoExperienceBenchmarkEnabled(boolean z) {
        this.I = z;
    }

    public void setVideoExperienceConfig(int i2, int i3) throws UnsupportedOperationException {
        if ((i2 == 0 || i2 == 1 || i2 == 2) && i3 >= 0 && i3 <= 100) {
            RDQ4NTRCNzI5MjM2NENFNkI2MTI2MkZDNUUzMUNDNzA((i2 << 8) | i3);
        }
    }

    public void setVideoExperienceMode(int i2) throws UnsupportedOperationException {
        if (i2 == 0 || i2 == 1 || i2 == 2) {
            QkNBOTk5NTU2MzNBNEY1RDg2NzEzMEZEMEI0QTdEQ0Y(i2);
        }
    }

    public void setVideoView(VOSurfaceView vOSurfaceView) {
        if (this.P == null) {
            this.P = new m(this);
        }
        a(vOSurfaceView, 0);
        if (vOSurfaceView == null) {
            a((VOFingerprintData) null);
        }
        VOAdBannerManager vOAdBannerManager = this.b1;
        if (vOAdBannerManager != null) {
            vOAdBannerManager.setVideoView(vOSurfaceView);
        }
        n();
    }

    public void setVmapAutoRefresh(boolean z, int i2) {
        AdRequestOptions adRequestOptions = this.h1;
        if (adRequestOptions != null) {
            adRequestOptions.vmapAutoRefresh = z;
            if (true == z) {
                adRequestOptions.vmapAutoRefreshPeriod = i2;
            } else {
                adRequestOptions.vmapAutoRefreshPeriod = -1;
            }
        }
    }

    public void setVmapSeekBackwardPlayUpTo(int i2) {
        AdRequestOptions adRequestOptions = this.h1;
        if (adRequestOptions != null) {
            adRequestOptions.vmapSeekBackwardPlayUpTo = i2;
        }
    }

    public void setVmapSeekForwardPlayUpTo(int i2) {
        AdRequestOptions adRequestOptions = this.h1;
        if (adRequestOptions != null) {
            adRequestOptions.vmapSeekForwardPlayUpTo = i2;
        }
    }

    public void setVmapSkipOffsetOverride(int i2, String str) {
        AdRequestOptions adRequestOptions = this.h1;
        if (adRequestOptions != null) {
            if (i2 >= 0 && i2 <= 2 && str != null) {
                adRequestOptions.vmapSkipOffsetOverrideStatus = i2;
                adRequestOptions.vmapSkipOffsetOverrideValue = str;
            } else {
                AdRequestOptions adRequestOptions2 = this.h1;
                adRequestOptions2.vmapSkipOffsetOverrideStatus = -1;
                adRequestOptions2.vmapSkipOffsetOverrideValue = null;
            }
        }
    }

    public void setVolume(float f2, VOVolumeType vOVolumeType) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        } else if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        if (VOVolumeType.VOVolumeTypeDevice != vOVolumeType) {
            if (VOVolumeType.VOVolumeTypePlayer == vOVolumeType) {
                this.p = f2;
                RjhBMEQwQkMyNDhGNEI1RDgyRDc1QjI2MjMwREQxMEQK((int) (f2 * 100.0f));
                return;
            }
            return;
        }
        AudioManager audioManager = C1;
        if (audioManager != null) {
            C1.setStreamVolume(3, (int) (f2 * audioManager.getStreamMaxVolume(3)), 0);
        }
    }

    public void setWakeMode(Context context, int i2) {
        boolean z;
        PowerManager.WakeLock wakeLock = this.m;
        if (wakeLock != null) {
            if (wakeLock.isHeld()) {
                z = true;
                this.m.release();
            } else {
                z = false;
            }
            this.m = null;
        } else {
            z = false;
        }
        PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(i2 | 536870912, VOPlayer.class.getName());
        this.m = wakeLockNewWakeLock;
        wakeLockNewWakeLock.setReferenceCounted(false);
        if (z) {
            this.m.acquire();
        }
    }

    public void setWatermarkingProvisionningUrl(String str) {
        YTgwNjNmMjZiOGU2NDhlOGI2MmMxMGQxNmJiYTZhNjcK(str);
    }

    public void signalFullScreen(boolean z) {
        QjJCQjVEQTZEMjQwNDg5MkFGMTRGQzg5M0QxNDAwMzc(z);
    }

    public void skipAdTunnel(boolean z, int i2) throws IllegalStateException, IOException {
        RTRENzMyMjFEQzEwNDI2QTk3MTgwRTcxQjQxQUM4NUMK(z, i2);
    }

    public void skipMedia() {
        ODYxNjhCNEVDRkZENDMzRjk0QkUwNkIyNjc4NjU2REY();
    }

    public void start() {
        int[] httpStreamingSeekableRange;
        VOCrashReporter.logEvent(this.f73a, "java1");
        g(true);
        if (isCasting()) {
            a(-1.0d);
        } else {
            B();
            NzhDNzRDNjlFRTQxNDVBNUIzNUVGQTFBQTlGRTQ3Qzk();
        }
        if (M().booleanValue() && (httpStreamingSeekableRange = getHttpStreamingSeekableRange()) != null && httpStreamingSeekableRange.length == 2) {
            seekTo(httpStreamingSeekableRange[1] - httpStreamingSeekableRange[0]);
        }
    }

    public void startAt(double d2) {
        startAt(d2, 0);
    }

    public void startAt(double d2, int i2) {
        g(true);
        if (2 == i2) {
            i2 = 0;
        }
        if (isCasting()) {
            a(d2);
        } else {
            B();
            NzhDNzRDNjlFRTQxNDVBNUIzNUVGQTFBQTlGRTQ3QzkAt(d2, i2);
        }
    }

    public void startThumbnailProcessing() {
        com.viaccessorca.voplayer.e eVar;
        boolean z;
        if (!Q() || (eVar = this.j1) == null) {
            return;
        }
        i iVar = this.M;
        if (iVar == i.PREPARED || iVar == i.PLAYING || iVar == i.PAUSED || iVar == i.BUFFERING || iVar == i.STOPPED) {
            eVar = this.j1;
            z = true;
        } else {
            z = false;
        }
        eVar.b(z);
    }

    public void stop() {
        this.N = true;
        f fVar = this.j;
        if (fVar != null) {
            if (fVar.hasMessages(MEDIA_INFO_VAST_ADVERTISEMENT)) {
                this.M = i.CREATED;
            }
            if (this.j.hasMessages(MEDIA_INFO_VAST_NOT_ADVERTISEMENT)) {
                this.M = i.CREATED;
            }
            if (this.j.hasMessages(1)) {
                this.M = i.CREATED;
            }
            this.j.removeCallbacksAndMessages(null);
        }
        for (int i2 = 0; i.PREOPENING == this.M && i2 < 1000; i2 += 50) {
            try {
                Thread.sleep(50L);
            } catch (InterruptedException unused) {
            }
        }
        z();
        this.N = false;
        this.J = QTYyMjcyNzI1ODY2NDUxMjlCNThDRjI4NTAzQjlENzM();
        this.K = Rjg1NkNBRDFCQ0JGNDI4NDg4MEJFNkY3RTNFQjYyQkY();
        g(false);
        QjczODQ2ODQwOTU3NDNBMkE0OThENDA1Mzg1QURCMzU();
        m();
        y();
        a((VOSurfaceView) null, 0);
        String str = this.F;
        if (str != null) {
            MDdBNTA0OTY3QzdCNEZFRThBMjYzNEZEQzBENzY5Mzk(str);
        }
        String str2 = D1;
        if (str2 != null) {
            MjcxNTYzREU2MjlDNDEwM0FDMzgwQjUxRDM4RUU3Qjg(str2);
            QzA0MkYyRDlCQ0E2NDg1ODgxNjBGNTE1NjIwOTkxQTM(this.m329t7p639082847841, D1, DrmAgent.useAndroidXDependency());
        }
        RjNGRjZGMzAxMkVBNEZGNUFGNTUwMjVFOTExRkE4NzY(this.m329t7p639082847841.getPackageName());
        N0U3NzVGQTlGMkEzNERBRDgyQTY5OTU4MUM2NDEzREU(this.G);
        RjNGRjZGMzAxMkVBNEZGNUFGNTUwMjVFOTExRkE4NzY(this.m329t7p639082847841.getPackageName());
        N0U3NzVGQTlGMkEzNERBRDgyQTY5OTU4MUM2NDEzREU(this.G);
        this.M0 = true;
    }

    public String vastClickThrough() {
        Q0NGM0E1N0MxOTFBNEM1OEJCOUY5MzEwOEEwQThFNDE();
        return this.m329t7p6390828478415;
    }
}
