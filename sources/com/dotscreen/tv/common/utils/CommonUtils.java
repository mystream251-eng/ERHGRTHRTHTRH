package com.dotscreen.tv.common.utils;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.media.tv.TvContentRating;
import android.media.tv.TvContract;
import android.media.tv.TvInputInfo;
import android.media.tv.TvInputManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.android.tv.common.BuildConfig;
import com.android.tv.common.R;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.dotscreen.bokit.BOKitKt;
import com.dotscreen.bokit.BackendErrorException;
import com.dotscreen.bokit.OnRequestErrorListener;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.model.PlaybackInfoMimeType;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.farkit.network.RequestManagerKt;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.tv.common.bokit.ChannelExtraCaster;
import com.dotscreen.tv.common.bokit.MegacableLiveTVService;
import com.dotscreen.tv.common.bokit.MegacablePVRService;
import com.dotscreen.tv.common.bokit.MegacableServicePlugin;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.bokit.RightVBackendErrorException;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.dotscreen.tv.common.bokit.RightVRequestManager;
import com.dotscreen.tv.common.modules.CompassAnalyticsModule;
import com.dotscreen.tv.common.providers.LastChannelsProvider;
import com.dotscreen.tv.common.recording.GlobalRecordingManager;
import com.dotscreen.tv.common.recording.MegacableNPVRRecordingManager;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.media.tv.companionlibrary.model.Channel;
import com.google.android.media.tv.companionlibrary.model.InternalProviderData;
import com.google.android.media.tv.companionlibrary.model.Program;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.sentry.Sentry;
import io.sentry.SentryEvent;
import io.sentry.protocol.Message;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.BinaryOperator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CommonUtils {
    public static final String ACTION_CLOSE_LIVE_APP = "com.android.tv.common.CLOSE_LIVE_APP";
    public static final String ACTION_CLOSE_PORTAL_APP = "com.android.tv.common.CLOSE_PORTAL_APP";
    public static final String ACTION_DVB_SCAN_FINISHED = "com.android.tv.common.DVB_SCAN_FINISHED";
    public static final String ACTION_DVB_SCAN_STARTED = "com.android.tv.common.DVB_SCAN_STARTED";
    public static final String ACTION_HOUSEHOLD_CHANGED = "com.android.tv.common.household_changed";
    public static final String ACTION_REQUEST_EXCEPTION = "com.android.tv.common.REQUEST_EXCEPTION";
    public static final String ACTION_SCAN_STATUS_CHANGED = "com.android.tv.common.SCAN_STATUS_CHANGED";
    public static final String ACTION_SELECT_AUDIO_TRACK = "com.android.tv.common.SELECT_AUDIO_TRACK";
    public static final String ACTION_SELECT_CAPTION_TRACK = "com.android.tv.commonSELECT_CAPTION_TRACK";
    public static final String ACTION_UNLOCK_SCREEN = "com.android.tv.common.UNLOCK_SCREEN";
    public static final String ACTION_USER_CHANGED = "com.android.tv.common.user_changed";
    public static final String ACTION_USER_SUSPENDED = "com.android.tv.common.user_suspended";
    public static final String BACK_FROM_LIMITED_MODE_SENTRY_TITLE = "Limited_mode_duration";
    public static final String BACK_FROM_NO_INTERNET_MODE_SENTRY_TITLE = "No_internet_mode_duration";
    public static final String BACK_FROM_SYNC_FAILED_MODE_SENTRY_TITLE = "Sync_Failed_mode_duration";
    public static final String BUNDLE_KEY_REQUEST_EXCEPTION = "com.android.tv.common.bundle_key_request_exception";
    public static final String BUNDLE_KEY_REQUEST_URL = "com.android.tv.common.bundle_key_request_url";
    public static final String BUNDLE_KEY_SCAN_STATUS = "com.android.tv.common.bundle_key_scan_status";
    public static final int CAS_BAD_SIGNAL = 7;
    public static final int CAS_CONFIG_ERROR = 4;
    public static final int CAS_DECRYPT_RESUME = 6;
    public static final int CAS_ENTITLEMENT_ERROR = 1;
    public static final int CAS_GENERIC_ERROR = 3;
    public static final int CAS_HDCP_ERROR = 5;
    public static final int CAS_NETWORK_ERROR = 0;
    public static final int CAS_PROVISIONING_ERROR = 2;
    public static final String KEY_INTERACTIVE_BACKGROUND_URL = "interactive_background_url";
    public static final String KEY_INTERACTIVE_BANNER_URL = "interactive_banner_url";
    public static final String KEY_INTERACTIVE_LINK_URL = "interactive_link_url";
    public static final String KEY_OPTIMIZER_CHANNEL_ID = "optimizer_channel_id";
    public static final String KEY_RIGHTV_EXTERNAL_CHANNEL_ID = "rightv_external_channel_id";
    public static final String KEY_RIGHTV_HD_EXTERNAL_CHANNEL_ID = "rightv-hd-external-id";
    public static final String KEY_RIGHTV_SD_EXTERNAL_CHANNEL_ID = "rightv-sd-external-id";
    public static final String LIMITED_MODE_REASON_SENTRY_TITLE = "Limited_mode_root_cause";
    public static final String NETWORK_STATUS_EXTRA_KEY = "com.android.tv.common.ConnectivityStatus";
    public static final String NO_INTERNET_MODE_REASON_SENTRY_TITLE = "No_internet_mode_root_cause";
    public static final String OTA_CHECK_FAILED_SENTRY_TITLE = "OTA error: failed to check the update file";
    public static final String OTA_DOWNLOAD_FAILED_SENTRY_TITLE = "OTA error: failed to download";
    public static final String OTA_DOWNLOAD_WARNING_SENTRY_TITLE = "OTA error: download warning";
    public static final String OTA_INSTALLATION_FAILED_SENTRY_TITLE = "OTA error: failed to install";
    public static final int PLATFORM_OREGAN = 1;
    public static final int PLATFORM_SIMULATOR = 16;
    public static final int PLATFORM_SKYWORTH = 4;
    public static final int PLATFORM_TECHNICOLOR = 2;
    public static final int PLATFORM_TECHNICOLOR_2 = 6;
    public static final int PLATFORM_UNKNOWN = 0;
    public static final int PLATFORM_ZTE = 3;
    public static final int PLATFORM_ZTE_2 = 5;
    private static final String PREF_KEY_LAST_WATCHED_CHANNEL_ID = "last_watched_channel_id";
    private static final String PREF_KEY_LAST_WATCHED_CHANNEL_ID_FOR_INPUT = "last_watched_channel_id_for_input_";
    private static final String PREF_KEY_LAST_WATCHED_CHANNEL_PROFILE_ID = "last_watched_channel_profile_id";
    private static final String PREF_KEY_LAST_WATCHED_CHANNEL_URI = "last_watched_channel_uri";
    private static final String PREF_NAME_LAST_PROFILE_ID = "last_profile_id";
    private static final String PREF_NAME_LAST_WATCHED_CHANNEL = "last_watched_channel";
    public static final String SCAN_STATUS_ERROR = "com.android.tv.common.scan_status_error";
    public static final String SCAN_STATUS_FINISHED = "com.android.tv.common.scan_status_finished";
    public static final String SCAN_STATUS_SCANNED = "com.android.tv.common.scan_status_scanned";
    public static final String SCAN_STATUS_STARTED = "com.android.tv.common.scan_status_started";
    public static final double SENTRY_EVENT_SAMPLE_RATE = 0.1d;
    public static final String SYNC_FAILED_MODE_REASON_SENTRY_TITLE = "Sync_failed_mode_root_cause";
    public static final long TIF_NOT_RESPONDIG_TIMEOUT = 10000;
    public static final String TIS_BUNDLE_KEY_ERROR = "error";
    public static final String TIS_EVENT_EXTRA_ERROR = "event_extra_error";
    public static final String TOGGLE_BACKEND_STATE = "com.android.tv.common.TOGGLE_BACKEND_STATE";
    public static final String UPDATE_NETWORK_STATUS = "com.android.tv.common.UPDATE_NETWORK_STATUS";
    public static final int VIDEO_UNAVAILABLE_REASON_NOT_TUNED = -1;
    public static final int VIDEO_UNAVAILABLE_REASON_OREGAN_ACCESS_DENIED_NO_SUBSCRIPTION = 768;
    public static final int VIDEO_UNAVAILABLE_REASON_OREGAN_ACCESS_DENIED_UNKNOWN = 769;
    public static final int VIDEO_UNAVAILABLE_REASON_OREGAN_CONFIG_ERROR = 772;
    public static final int VIDEO_UNAVAILABLE_REASON_OREGAN_GENERAL_SERVER_ERROR = 770;
    public static final int VIDEO_UNAVAILABLE_REASON_OREGAN_HDCP_ERROR = 773;
    public static final int VIDEO_UNAVAILABLE_REASON_OREGAN_PROVISIONNING_ERROR = 771;
    public static final int VIDEO_UNAVAILABLE_REASON_RIGHTV_ACCESS_DENIED_GEO_BLOCKED = 514;
    public static final int VIDEO_UNAVAILABLE_REASON_RIGHTV_ACCESS_DENIED_NOT_SUBSCRIBED = 513;
    public static final int VIDEO_UNAVAILABLE_REASON_RIGHTV_ACCESS_DENIED_UNKNOWN = 512;
    public static final int VIDEO_UNAVAILABLE_REASON_RIGHTV_GENERAL_SERVER_ERROR = 516;
    public static final int VIDEO_UNAVAILABLE_REASON_RIGHTV_NOT_ALLOWED_OUT_OF_MEGACABLE_NETWORK = 515;
    public static final int VIDEO_UNAVAILABLE_REASON_TCH_ACCESS_DENIED_GEO_BLACKOUT = 260;
    public static final int VIDEO_UNAVAILABLE_REASON_TCH_ACCESS_DENIED_INIT_ISSUE = 257;
    public static final int VIDEO_UNAVAILABLE_REASON_TCH_ACCESS_DENIED_NO_SUBSCRIPTION = 258;
    public static final int VIDEO_UNAVAILABLE_REASON_TCH_ACCESS_DENIED_PIN_REQUIRED = 259;
    public static final int VIDEO_UNAVAILABLE_REASON_TCH_ACCESS_DENIED_UNKNOWN = 256;
    public static final int VIDEO_UNAVAILABLE_REASON_TCH_ACCESS_DENIED_USAGE_RULE_ISSUE = 261;
    private static final String TAG = CommonUtils.class.getSimpleName();
    private static final HashMap<String, Uri> TEST_STREAMS = new HashMap<>();
    public static long ZAP_EVENT_DELAY = 30;
    public static Map<String, Channel> cachedChannelMap = new HashMap();
    public static boolean TIFChannelMapIsCached = false;
    public static final String OPTIMIZER_URL = BuildConfig.BASE_URL;
    public static final String RIGHTV_URL = "https://www.xviewplus.com.mx/RTEFacade";
    public static final String COMPASS_URL = "https://www.xviewplus.com.mx/compass";
    public static final String SEARCH_URL = "https://www.xviewplus.com.mx/search/compass";
    public static final String PCW_URL = "https://www.xviewplus.com.mx/personalization/v1";
    public static final String OPTIMIZER_LINEUP = "default-megacable";
    public static final String CONFIG_URL = "https://www.xviewplus.com.mx/config";
    public static final String SESSION_MANAGEMENT_URL = "https://www.xviewplus.com.mx/SessionManagement";
    public static final String DRM_LICENCE_SERVER_URL = "https://www.xviewplus.com.mx/RTEFacade/widevinedrm";
    public static final String OTA_SERVER_URL = BuildConfig.UPDATER_CHECK_URL;
    public static final String IDPM_URL = "https://bss.rcs.megafon.com.mx:9041/idpm";
    public static final String TBX_EVENT_URL = "https://unity.tbxapis.com/v0/tracking";
    public static final String VRMX_IP = BuildConfig.VRMX_IP;
    public static final String VRMX_PORT = BuildConfig.VRMX_PORT;
    public static final String PADI_URL = BuildConfig.PADI_BASE_URL;
    public static final String TARGET_ADVERTISING_UIOP_IMG_API = "https://www.xviewplus.com.mx/api/ta-artworks";
    public static int CONTENT_END_EVENT_PERCENT = 95;
    private static BOService boService = null;
    private static RightVRequestManager requestManager = null;
    private static GlobalRecordingManager recordingManager = null;
    private static CompassAnalyticsModule compassAnalyticsModule = null;
    private static Single<Lineup> lineupCache = null;
    private static String currentBouquetName = null;
    private static Boolean internalIsDVBCSTB = null;

    /* JADX INFO: Access modifiers changed from: private */
    interface OnChannelMatchListener<T> {
        T onChannelMatch(Channel channel);
    }

    public static boolean isProdConfig() {
        return true;
    }

    public static boolean shouldHideInputId(String str) {
        return false;
    }

    public static class VOPlayerError extends Exception {
        private int mError;
        private int mExtra;

        public VOPlayerError(int i, int i2) {
            this.mError = i;
            this.mExtra = i2;
        }

        public int getError() {
            return this.mError;
        }

        public int getExtra() {
            return this.mExtra;
        }
    }

    public static String getResizedImageUrl(String str, int i, int i2) {
        Uri uri = Uri.parse(str);
        return OPTIMIZER_URL + "/api/tv/v1/" + i + "x" + i2 + "/" + uri.getHost() + uri.getPath();
    }

    public static boolean isSupportedTvInputId(Context context, String str) {
        TvInputInfo tvInputInfo = ((TvInputManager) context.getSystemService("tv_input")).getTvInputInfo(str);
        return (tvInputInfo == null || tvInputInfo.isHidden(context) || (!getOTTLiveInputId().equals(str) && !getInterctiveLiveInputId().equals(str) && !str.contains("net.oregan.tvinput") && !str.contains("com.technicolor.android.dtvinput"))) ? false : true;
    }

    public static String getOTTLiveInputId() {
        return new ComponentName("com.dotscreen.megacable.portal", "com.dotscreen.tvinput.OTTLiveTvInputService").flattenToString();
    }

    public static String getInterctiveLiveInputId() {
        return new ComponentName("com.dotscreen.megacable.portal", "com.dotscreen.tvinput.InteractiveLiveTvInputService").flattenToString();
    }

    public static Channel boKitChannelToChannel(com.dotscreen.bokit.model.Channel channel, PlaybackInfoMimeType playbackInfoMimeType) {
        String string;
        ChannelExtraCaster channelExtraCaster;
        Map<PlaybackInfoMimeType, String> urls = channel.getLiveStream().getPlaybackInfo().blockingGet().getUrls();
        if (playbackInfoMimeType != null) {
            string = urls.get(playbackInfoMimeType);
        } else if (urls.get(PlaybackInfoMimeType.HLS) != null) {
            string = urls.get(PlaybackInfoMimeType.HLS);
        } else if (urls.get(PlaybackInfoMimeType.DASH) != null) {
            string = urls.get(PlaybackInfoMimeType.DASH);
        } else if (urls.get(PlaybackInfoMimeType.SMOOTHSTREAMING) != null) {
            string = urls.get(PlaybackInfoMimeType.SMOOTHSTREAMING);
        } else {
            string = urls.get(PlaybackInfoMimeType.MULTICAST) != null ? urls.get(PlaybackInfoMimeType.MULTICAST) : null;
        }
        Uri uri = TEST_STREAMS.get(channel.getId());
        if (uri != null) {
            string = uri.toString();
        }
        InternalProviderData internalProviderData = new InternalProviderData();
        internalProviderData.setVideoType(2);
        internalProviderData.setVideoUrl(string);
        try {
            internalProviderData.put(KEY_OPTIMIZER_CHANNEL_ID, channel.getId());
            if ((channel instanceof RightVChannel) && (channelExtraCaster = ((RightVChannel) channel).getExtras().getCasters().get("interactive")) != null) {
                internalProviderData.put(KEY_INTERACTIVE_BANNER_URL, channelExtraCaster.getBanner());
                internalProviderData.put(KEY_INTERACTIVE_BACKGROUND_URL, channelExtraCaster.getBackground());
                internalProviderData.put(KEY_INTERACTIVE_LINK_URL, channelExtraCaster.getSrc());
            }
        } catch (InternalProviderData.ParseException e) {
            e.printStackTrace();
        }
        return new Channel.Builder().setDisplayNumber("" + channel.getZappingNumber()).setDisplayName(channel.getTitle()).setServiceType(TvContractCompat.Channels.SERVICE_TYPE_AUDIO_VIDEO).setInternalProviderData(internalProviderData).setOriginalNetworkId(channel.getZappingNumber()).build();
    }

    public static Program boKitAiringToCompanionProgram(Context context, Channel channel, TVAiring tVAiring) {
        Artwork stillArtwork = tVAiring.getStillArtwork();
        InternalProviderData internalProviderData = new InternalProviderData();
        internalProviderData.setVideoType(channel.getInternalProviderData().getVideoType());
        internalProviderData.setVideoUrl(channel.getInternalProviderData().getVideoUrl());
        Program.Builder internalProviderData2 = new Program.Builder().setTitle(tVAiring.getTitle()).setStartTimeUtcMillis(tVAiring.getStartDate().getTime()).setEndTimeUtcMillis(tVAiring.getEndDate().getTime()).setDescription(tVAiring.getSummary()).setPosterArtUri(stillArtwork.urlWithSize(new Point(1920, 1080), 1.0d).toString()).setThumbnailUri(stillArtwork.urlWithSize(new Point(1920, 1080), 1.0d).toString()).setChannelId(channel.getId()).setInternalProviderData(internalProviderData);
        TvContentRating tvContentRating = tVAiring.getMetadata().getParentalLevel().toTvContentRating();
        if (tvContentRating != null) {
            internalProviderData2.setContentRatings(new TvContentRating[]{tvContentRating});
        }
        return internalProviderData2.build();
    }

    public static void resetStatics() {
        boService = null;
        requestManager = null;
        lineupCache = null;
    }

    public static BOService getBOService(Context context) {
        if (boService == null) {
            try {
                BOKitKt.setDefaultConnectionTimeout(20000L);
                BOKitKt.setDefaultGraphQLConnectionTimeout(20000L);
                BOKitKt.setDefaultAiringsDaysCache(10);
                BOKitKt.setDefaultBitmapLRUCacheFactor(1);
                BOService bOService = new BOService(context, new URL(OPTIMIZER_URL), new MegacableServicePlugin(context, getRightVRequestManager(context)));
                boService = bOService;
                setRequestErrorListener(bOService);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return boService;
    }

    private static void setRequestErrorListener(BOService bOService) {
        bOService.addOnRequestErrorListener(new OnRequestErrorListener() { // from class: com.dotscreen.tv.common.utils.-$$Lambda$CommonUtils$p61OJ12i1JXuXrrPR-GC2N3Rpto
            @Override // com.dotscreen.bokit.OnRequestErrorListener, com.dotscreen.farkit.network.RequestManager.OnRequestErrorListener
            public final void onRequestError(Throwable th, URL url) {
                CommonUtils.lambda$setRequestErrorListener$0(th, url);
            }
        });
    }

    static /* synthetic */ void lambda$setRequestErrorListener$0(Throwable th, URL url) {
        JSONObject jSONObject;
        Log.w("ErrorRequestManager", "UIOP request error: " + th, th);
        if (th instanceof VolleyError) {
            VolleyError volleyError = (VolleyError) th;
            if (volleyError.networkResponse != null) {
                String str = new String(volleyError.networkResponse.data, Charset.forName(HttpHeaderParser.parseCharset(volleyError.networkResponse.headers)));
                try {
                    jSONObject = new JSONObject(str);
                } catch (JSONException unused) {
                    jSONObject = null;
                }
                SentryEvent sentryEvent = new SentryEvent();
                Message message = new Message();
                HashMap map = new HashMap();
                if (url != null) {
                    map.put(ImagesContract.URL, url);
                }
                map.put("serviceName", "UIOP");
                if (jSONObject != null) {
                    map.put("response", jSONObject);
                } else {
                    map.put("rawdata", str);
                }
                message.setMessage("UIOP request error");
                sentryEvent.setMessage(message);
                sentryEvent.setExtras(map);
                Sentry.captureEvent(sentryEvent, SentryReportHint.UIOptimizer);
            }
        }
    }

    public static CompassAnalyticsModule getCompassAnalyticsModule(Context context) {
        if (compassAnalyticsModule == null) {
            Log.d(TAG, "registerevent => create the module");
            compassAnalyticsModule = new CompassAnalyticsModule(context);
        }
        Log.d(TAG, "registerevent => return the module");
        return compassAnalyticsModule;
    }

    public static RightVRequestManager getRightVRequestManager(Context context) {
        if (requestManager == null) {
            RequestManagerKt.setDefaultConnectionTimeout(20000L);
            String packageName = context.getPackageName();
            Log.d(TAG, "getRightVRequestManager, callerPackageName = " + packageName);
            if ("com.dotscreen.megacable.portal".equals(packageName) || "com.dotscreen.megacable.livetv".equals(packageName)) {
                try {
                    requestManager = new RightVRequestManager(context.createPackageContext("com.dotscreen.megacable.portal", 2), RIGHTV_URL, COMPASS_URL, SEARCH_URL, PCW_URL, IDPM_URL, TBX_EVENT_URL, SESSION_MANAGEMENT_URL, PADI_URL, null, true);
                    Log.d(TAG, "getRightVRequestManager, created a shared RequestManager");
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (requestManager == null) {
                requestManager = new RightVRequestManager(context, RIGHTV_URL, COMPASS_URL, SEARCH_URL, PCW_URL, IDPM_URL, TBX_EVENT_URL, SESSION_MANAGEMENT_URL, PADI_URL, null, false);
                Log.d(TAG, "getRightVRequestManager, created a standard RequestManager");
            }
        }
        return requestManager;
    }

    public static GlobalRecordingManager getGlobalRecordingManager(Context context) {
        if (recordingManager == null) {
            recordingManager = new MegacableNPVRRecordingManager(context, (MegacablePVRService) getBOService(context).getPvrService());
        }
        return recordingManager;
    }

    public static BOService getBOServiceForcedNoConnection(Context context) {
        try {
            BOKitKt.setDefaultConnectionTimeout(20000L);
            BOKitKt.setDefaultGraphQLConnectionTimeout(20000L);
            BOKitKt.setDefaultAiringsDaysCache(10);
            BOKitKt.setDefaultBitmapLRUCacheFactor(1);
            MegacableServicePlugin megacableServicePlugin = new MegacableServicePlugin(context, getRightVRequestManager(context));
            Log.d(TAG, "forceNoConnection = true");
            getRightVRequestManager(context).setForceNoConnection(true);
            boService = new BOService(context, new URL(OPTIMIZER_URL), megacableServicePlugin);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return boService;
    }

    public static void invalidateLineupCache() {
        lineupCache = null;
        TIFCompanionLibraryExtensionKt.clearCache();
    }

    public static String getCurrentBouquetName() {
        return currentBouquetName;
    }

    public static Single<Lineup> getCurrentLineup(final BOService bOService) {
        if (bOService != null) {
            if (lineupCache == null) {
                final MegacableUserService megacableUserService = (MegacableUserService) bOService.getUserService();
                lineupCache = Single.defer(new Callable() { // from class: com.dotscreen.tv.common.utils.-$$Lambda$CommonUtils$K-0RiyM0vc0yTE7rYMoERW9Bwzk
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return CommonUtils.lambda$getCurrentLineup$2(megacableUserService);
                    }
                }).flatMap(new Function() { // from class: com.dotscreen.tv.common.utils.-$$Lambda$CommonUtils$J9StFDEkPJSZAEmEVi2nqk2VsJ0
                    @Override // io.reactivex.functions.Function
                    public final Object apply(Object obj) {
                        return bOService.getLiveTVService().lineupById((String) obj).toSingle();
                    }
                }).doOnError(new Consumer() { // from class: com.dotscreen.tv.common.utils.-$$Lambda$CommonUtils$qP5oXJSlUgmb53JRew4e3l8CPYw
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) throws Exception {
                        CommonUtils.lambda$getCurrentLineup$4((Throwable) obj);
                    }
                });
            }
            return lineupCache;
        }
        return Single.error(new IllegalArgumentException("Client could not be null"));
    }

    static /* synthetic */ SingleSource lambda$getCurrentLineup$2(MegacableUserService megacableUserService) throws Exception {
        String str = currentBouquetName;
        if (str != null) {
            return Single.just(str);
        }
        return megacableUserService.getBouquetList().map(new Function() { // from class: com.dotscreen.tv.common.utils.-$$Lambda$CommonUtils$GCKJPOKh69hpZyOQFRrwt94cJus
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return CommonUtils.lambda$null$1((JSONArray) obj);
            }
        });
    }

    static /* synthetic */ String lambda$null$1(JSONArray jSONArray) throws Exception {
        try {
            currentBouquetName = jSONArray.getJSONObject(0).getString("externalId");
        } catch (Exception e) {
            if (!isProdConfig()) {
                currentBouquetName = "OP1";
            } else {
                throw e;
            }
        }
        Log.d(TAG, "Bouquet name => " + currentBouquetName);
        return currentBouquetName;
    }

    static /* synthetic */ void lambda$getCurrentLineup$4(Throwable th) throws Exception {
        Log.d(TAG, "Error on getCurrentLineup");
        lineupCache = null;
    }

    public static Uri getLiveTvDeeplink(Context context, com.dotscreen.bokit.model.Channel channel, Boolean bool) {
        if (channel == null) {
            return getLiveTvDeeplink(context, "", bool);
        }
        return getLiveTvDeeplink(context, channel.getId(), bool);
    }

    public static void cacheTIFChannelList(final Context context) {
        Log.w(TAG, "cacheTIFChannelList");
        cachedChannelMap.clear();
        TIFChannelMapIsCached = false;
        Single.create(new SingleOnSubscribe() { // from class: com.dotscreen.tv.common.utils.-$$Lambda$CommonUtils$4VX5augGVzjkEaoN4huZSwGIb0k
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) throws Exception {
                CommonUtils.lambda$cacheTIFChannelList$5(context, singleEmitter);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BiConsumer() { // from class: com.dotscreen.tv.common.utils.-$$Lambda$CommonUtils$gWevTL4M9tZPevgIwU9bLSG4ycQ
            @Override // io.reactivex.functions.BiConsumer
            public final void accept(Object obj, Object obj2) throws Exception {
                CommonUtils.lambda$cacheTIFChannelList$6((Boolean) obj, (Throwable) obj2);
            }
        });
    }

    static /* synthetic */ void lambda$cacheTIFChannelList$5(Context context, SingleEmitter singleEmitter) throws Exception {
        Log.w(TAG, "cacheTIFChannelList, launch");
        if (context != null) {
            try {
                Cursor cursorQuery = context.getContentResolver().query(TvContractCompat.Channels.CONTENT_URI, Channel.PROJECTION, "(input_id IN " + getAvailableTVInputIdsQueryParam(context) + ")", null, null);
                if (cursorQuery != null) {
                    while (cursorQuery.moveToNext()) {
                        try {
                            Channel channelFromCursor = Channel.fromCursor(cursorQuery);
                            if (channelFromCursor != null) {
                                String optimizerChannelId = TIFCompanionLibraryExtensionKt.getOptimizerChannelId(channelFromCursor, getBOService(context));
                                Log.w(TAG, "cacheTIFChannelList, " + optimizerChannelId + " -> channel :" + channelFromCursor);
                                cachedChannelMap.put(optimizerChannelId, channelFromCursor);
                            }
                        } catch (Throwable th) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                if (cursorQuery != null) {
                                    try {
                                        cursorQuery.close();
                                    } catch (Throwable th3) {
                                        th.addSuppressed(th3);
                                    }
                                }
                                throw th2;
                            }
                        }
                    }
                    cursorQuery.close();
                    TIFChannelMapIsCached = true;
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Exception e) {
                TIFChannelMapIsCached = false;
                Log.w(TAG, "Error querying channels, during cache process", e);
            }
        }
        singleEmitter.onSuccess(true);
    }

    static /* synthetic */ void lambda$cacheTIFChannelList$6(Boolean bool, Throwable th) throws Exception {
        if (th != null) {
            Log.e(TAG, "cacheTIFChannelList => Channel loading goes wrong", th);
        } else {
            Log.w(TAG, "cacheTIFChannelList => done");
        }
    }

    private static <T> T matchChannel(Context context, String str, OnChannelMatchListener<T> onChannelMatchListener) {
        Log.v(TAG, "call matchChannel, cachedChannel is cached =>" + TIFChannelMapIsCached + "livecard, mainactivity");
        if (!TIFChannelMapIsCached) {
            try {
                Cursor cursorQuery = context.getContentResolver().query(TvContractCompat.Channels.CONTENT_URI, Channel.PROJECTION, "(input_id IN " + getAvailableTVInputIdsQueryParam(context) + ")", null, null);
                if (cursorQuery != null) {
                    while (cursorQuery.moveToNext()) {
                        try {
                            Channel channelFromCursor = Channel.fromCursor(cursorQuery);
                            T tOnChannelMatch = onChannelMatchListener.onChannelMatch(channelFromCursor);
                            Log.v(TAG, "" + channelFromCursor + " -> " + tOnChannelMatch);
                            if (tOnChannelMatch != null) {
                                if (cursorQuery != null) {
                                    cursorQuery.close();
                                }
                                return tOnChannelMatch;
                            }
                        } catch (Throwable th) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                if (cursorQuery != null) {
                                    try {
                                        cursorQuery.close();
                                    } catch (Throwable th3) {
                                        th.addSuppressed(th3);
                                    }
                                }
                                throw th2;
                            }
                        }
                    }
                    cursorQuery.close();
                }
                if (cursorQuery == null) {
                    return null;
                }
                cursorQuery.close();
                return null;
            } catch (Exception e) {
                Log.w(TAG, "Error querying channels", e);
                return null;
            }
        }
        return onChannelMatchListener.onChannelMatch(cachedChannelMap.get(str));
    }

    public static Uri getChannelUri(final Context context, final String str) {
        return (Uri) matchChannel(context, str, new OnChannelMatchListener() { // from class: com.dotscreen.tv.common.utils.-$$Lambda$CommonUtils$34mVWksDlWYyU1QHkyYlRfP62Ss
            @Override // com.dotscreen.tv.common.utils.CommonUtils.OnChannelMatchListener
            public final Object onChannelMatch(Channel channel) {
                return CommonUtils.lambda$getChannelUri$7(context, str, channel);
            }
        });
    }

    static /* synthetic */ Uri lambda$getChannelUri$7(Context context, String str, Channel channel) {
        if (channel == null || !str.equals(TIFCompanionLibraryExtensionKt.getOptimizerChannelId(channel, getBOService(context)))) {
            return null;
        }
        return TvContractCompat.buildChannelUri(channel.getId());
    }

    public static Channel getChannel(final Context context, final String str) {
        return (Channel) matchChannel(context, str, new OnChannelMatchListener() { // from class: com.dotscreen.tv.common.utils.-$$Lambda$CommonUtils$XhN2gBipFw7c-dnciwofG74w1go
            @Override // com.dotscreen.tv.common.utils.CommonUtils.OnChannelMatchListener
            public final Object onChannelMatch(Channel channel) {
                return CommonUtils.lambda$getChannel$8(context, str, channel);
            }
        });
    }

    static /* synthetic */ Channel lambda$getChannel$8(Context context, String str, Channel channel) {
        if (channel == null || !str.equals(TIFCompanionLibraryExtensionKt.getOptimizerChannelId(channel, getBOService(context)))) {
            return null;
        }
        return channel;
    }

    public static com.dotscreen.bokit.model.Channel getOptimizerChannel(Context context, Long l) {
        try {
            Cursor cursorQuery = context.getContentResolver().query(TvContractCompat.buildChannelUri(l.longValue()), Channel.PROJECTION, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        Channel channelFromCursor = Channel.fromCursor(cursorQuery);
                        cursorQuery.close();
                        com.dotscreen.bokit.model.Channel optimizerChannel = TIFCompanionLibraryExtensionKt.getOptimizerChannel(channelFromCursor, getBOService(context));
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return optimizerChannel;
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (cursorQuery != null) {
                            try {
                                cursorQuery.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        }
                        throw th2;
                    }
                }
            }
            if (cursorQuery == null) {
                return null;
            }
            cursorQuery.close();
            return null;
        } catch (Exception e) {
            Log.w(TAG, "Error querying channels", e);
            return null;
        }
    }

    public static Uri getLiveTvDeeplink(Context context, String str, Boolean bool) {
        if ("".equals(str)) {
            return TvContractCompat.buildChannelUri(0L);
        }
        if (context.getPackageName().equals("com.dotscreen.megacable.livetv")) {
            return getChannelUri(context, str);
        }
        return Uri.parse("megacable://com.dotscreen.megacable.livetv/watch?id=" + str + "&askPinCodeIfNeeded=" + bool);
    }

    public static Uri resourceToUri(Context context, int i) {
        return Uri.parse("android.resource://" + context.getResources().getResourcePackageName(i) + '/' + context.getResources().getResourceTypeName(i) + '/' + context.getResources().getResourceEntryName(i));
    }

    public static void setLastWatchedChannel(Context context, long j, String str, Boolean bool, String str2) {
        if (str2 != null) {
            DSLog.INSTANCE.m("saving last watch channel");
            context.getSharedPreferences(PREF_NAME_LAST_PROFILE_ID, 0).edit().putString(PREF_KEY_LAST_WATCHED_CHANNEL_PROFILE_ID, str2).apply();
        } else {
            str2 = getLastWatchedChannelProfileId(context);
            if (str2 == null) {
                DSLog.INSTANCE.e("failed saving last watch channel, profileID is null");
                return;
            }
            DSLog.INSTANCE.m("saving last watch channel");
        }
        context.getSharedPreferences(PREF_NAME_LAST_WATCHED_CHANNEL, 0).edit().putString(PREF_KEY_LAST_WATCHED_CHANNEL_URI, TvContractCompat.buildChannelUri(j).toString()).apply();
        if (!bool.booleanValue()) {
            if (j < 0) {
                throw new IllegalArgumentException("channelId should be equal to or larger than 0");
            }
            context.getSharedPreferences(PREF_NAME_LAST_WATCHED_CHANNEL, 0).edit().putLong(PREF_KEY_LAST_WATCHED_CHANNEL_ID, j).apply();
            context.getSharedPreferences(PREF_NAME_LAST_WATCHED_CHANNEL, 0).edit().putLong(PREF_KEY_LAST_WATCHED_CHANNEL_ID_FOR_INPUT + str, j).apply();
        }
        context.getContentResolver().delete(LastChannelsProvider.INSTANCE.getCONTENT_URI(), LastChannelsProvider.INSTANCE.getCOLUMN_CHANNEL_ID() + "=" + j + " and " + LastChannelsProvider.INSTANCE.getCOLUMN_PROFILE_ID() + "=\"" + str2 + "\"", null);
        if (str.equals(getInterctiveLiveInputId())) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(LastChannelsProvider.INSTANCE.getCOLUMN_CHANNEL_ID(), Long.valueOf(j));
        contentValues.put(LastChannelsProvider.INSTANCE.getCOLUMN_INPUT_ID(), str);
        contentValues.put(LastChannelsProvider.INSTANCE.getCOLUMN_PROFILE_ID(), str2);
        context.getContentResolver().insert(LastChannelsProvider.INSTANCE.getCONTENT_URI(), contentValues);
    }

    public static long getLastWatchedChannelId(Context context) {
        return context.getSharedPreferences(PREF_NAME_LAST_WATCHED_CHANNEL, 0).getLong(PREF_KEY_LAST_WATCHED_CHANNEL_ID, -1L);
    }

    public static long getLastWatchedChannelIdForInput(Context context, String str) {
        return context.getSharedPreferences(PREF_NAME_LAST_WATCHED_CHANNEL, 0).getLong(PREF_KEY_LAST_WATCHED_CHANNEL_ID_FOR_INPUT + str, -1L);
    }

    public static String getLastWatchedChannelUri(Context context) {
        return context.getSharedPreferences(PREF_NAME_LAST_WATCHED_CHANNEL, 0).getString(PREF_KEY_LAST_WATCHED_CHANNEL_URI, null);
    }

    public static String getLastWatchedChannelProfileId(Context context) {
        return context.getSharedPreferences(PREF_NAME_LAST_PROFILE_ID, 0).getString(PREF_KEY_LAST_WATCHED_CHANNEL_PROFILE_ID, null);
    }

    private static boolean isSimulator() {
        return (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith(EnvironmentCompat.MEDIA_UNKNOWN) || Build.HARDWARE.contains("goldfish") || Build.HARDWARE.contains("ranchu") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || Build.PRODUCT.contains("sdk_google") || Build.PRODUCT.contains("google_sdk") || Build.PRODUCT.contains("sdk") || Build.PRODUCT.contains("sdk_x86") || Build.PRODUCT.contains("vbox86p") || Build.PRODUCT.contains("emulator") || Build.PRODUCT.contains("simulator");
    }

    public static int getCurrentPlatform() {
        if (Build.MANUFACTURER.equalsIgnoreCase("SkyworthDigital")) {
            return 4;
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("Technicolor")) {
            return Build.DEVICE.equals("uiw4059meg") ? 6 : 2;
        }
        if (!Build.MANUFACTURER.equalsIgnoreCase("ZTE")) {
            return isSimulator() ? 16 : 0;
        }
        if (Build.DEVICE.equalsIgnoreCase("B866V2_ZTE")) {
            return 3;
        }
        return Build.DEVICE.equalsIgnoreCase("B866V2F") ? 5 : 1;
    }

    public static final boolean isNetflixChannel(com.dotscreen.bokit.model.Channel channel) {
        if (channel == null) {
            return false;
        }
        try {
            return (channel instanceof RightVChannel) && "Netflix".equals(((RightVChannel) channel).getExtras().getCasters().get("interactive").getSrc());
        } catch (Exception unused) {
            return false;
        }
    }

    public static final boolean isAmazonPrimeChannel(com.dotscreen.bokit.model.Channel channel) {
        if (channel == null) {
            return false;
        }
        try {
            return (channel instanceof RightVChannel) && "Amazon".equals(((RightVChannel) channel).getExtras().getCasters().get("interactive").getSrc());
        } catch (Exception unused) {
            return false;
        }
    }

    public static Intent patchInteractiveIntent(Intent intent) {
        if (!intent.getAction().equals("android.intent.action.VIEW") || !intent.getData().toString().contains("netflix")) {
            return intent;
        }
        Intent intent2 = new Intent(intent);
        intent2.addFlags(32);
        intent2.setPackage("com.netflix.ninja");
        Log.d("Netflix", "Netflix deeplink: " + intent2.getData());
        return intent2;
    }

    public static String getLanguageName(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return "UNK";
        }
        int identifier = context.getResources().getIdentifier("language_name_" + str, "string", context.getPackageName());
        if (identifier != 0) {
            return context.getString(identifier);
        }
        StringBuilder sb = new StringBuilder(new Locale(str).getDisplayName());
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }

    public static boolean isDVBCSTB(Context context) {
        Cursor cursorQuery;
        if (internalIsDVBCSTB == null) {
            try {
                internalIsDVBCSTB = Boolean.valueOf("DVB-C STB".equals(((MegacableLiveTVService) getBOService(context).getLiveTVService()).getModelExternalId().blockingGet()));
            } catch (Exception unused) {
                if (Build.VERSION.SDK_INT >= 21 && (cursorQuery = context.getContentResolver().query(TvContract.Channels.CONTENT_URI, new String[]{"_id", "package_name", "input_id"}, "type=?", new String[]{TvContractCompat.Channels.TYPE_DVB_C}, null)) != null) {
                    internalIsDVBCSTB = Boolean.valueOf(cursorQuery.getCount() != 0);
                    cursorQuery.close();
                }
            }
        }
        Boolean bool = internalIsDVBCSTB;
        return bool != null && bool.booleanValue();
    }

    public static boolean isTypeSTB(Context context, String str) {
        try {
            return str.equals(((MegacableLiveTVService) getBOService(context).getLiveTVService()).getModelExternalId().blockingGet());
        } catch (Exception unused) {
            return false;
        }
    }

    public static String getCurrentLocationCountryCode(Context context, double d, double d2) {
        if (!Geocoder.isPresent()) {
            return "";
        }
        try {
            List<Address> fromLocation = new Geocoder(context, Locale.ENGLISH).getFromLocation(d, d2, 1);
            Log.v("location", "getCountryCode = " + fromLocation.get(0).getCountryName());
            Log.v("location", "getCountryCode = " + fromLocation.get(0).getCountryCode());
            return fromLocation.get(0).getCountryCode();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String formatTISException(Context context, Throwable th, int i) {
        if (th == null) {
            return String.format("0x%04x", Integer.valueOf(i));
        }
        if (th instanceof RightVBackendErrorException) {
            BackendErrorException backendErrorException = (BackendErrorException) th;
            String str = backendErrorException.getRequestId() + "-" + backendErrorException.getBackendCode();
            try {
                return context.getString(MegacableError.INSTANCE.getErrorObject(backendErrorException.getUrl(), backendErrorException.getBackendCode()).getResId());
            } catch (IllegalArgumentException unused) {
                Log.v("formatTISException", "Unknown RightV error");
                return str;
            }
        }
        if (th instanceof BackendErrorException) {
            return "UIOP-" + ((BackendErrorException) th).getBackendCode();
        }
        if (th instanceof VOPlayerError) {
            VOPlayerError vOPlayerError = (VOPlayerError) th;
            BPKPlayerError bPKPlayerError = BPKPlayerError.INSTANCE.getBPKPlayerError(vOPlayerError.getExtra());
            DSVOPlayerError playerError = DSVOPlayerError.INSTANCE.getPlayerError(vOPlayerError.getExtra());
            if (bPKPlayerError != null) {
                return context.getString(bPKPlayerError.getResId());
            }
            if (playerError != null) {
                return context.getString(playerError.getResId());
            }
            return "VOP-BPK-" + vOPlayerError.getError() + "-" + String.format("0x%08x", Integer.valueOf(vOPlayerError.getExtra()));
        }
        return "EX-" + th.getClass().getSimpleName();
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0079  */
    /* JADX WARN: Code duplicated, block: B:28:0x0087  */
    public static String getVideoUnavailableMessage(Context context, int i, Throwable th) {
        Log.e("UnavailableMessage", "" + i);
        if (i != -1) {
            if (i == 0) {
                return context.getString(R.string.tvview_msg_try_again);
            }
            if (i != 1) {
                if (i == 2) {
                    Log.i(TAG, "DVBCable - VIDEO_UNAVAILABLE_REASON_WEAK_SIGNAL");
                    return context.getString(R.string.tvview_msg_weak_signal);
                }
                if (i != 3) {
                    if (i == 4) {
                        return context.getString(R.string.tvview_msg_no_video);
                    }
                    switch (i) {
                        case 256:
                            return context.getString(R.string.tvview_msg_unkown_error);
                        case 257:
                            return context.getString(R.string.tvview_msg_init_error);
                        case VIDEO_UNAVAILABLE_REASON_TCH_ACCESS_DENIED_NO_SUBSCRIPTION /* 258 */:
                            return context.getString(R.string.tvview_msg_not_subscribed_technical);
                        case VIDEO_UNAVAILABLE_REASON_TCH_ACCESS_DENIED_PIN_REQUIRED /* 259 */:
                            return context.getString(R.string.tvview_msg_pin_error);
                        case VIDEO_UNAVAILABLE_REASON_TCH_ACCESS_DENIED_GEO_BLACKOUT /* 260 */:
                            return context.getString(R.string.tvview_msg_geo_blocked);
                        case VIDEO_UNAVAILABLE_REASON_TCH_ACCESS_DENIED_USAGE_RULE_ISSUE /* 261 */:
                            return context.getString(R.string.tvview_msg_role_issue);
                        default:
                            switch (i) {
                                case 513:
                                    return context.getString(R.string.tvview_msg_not_subscribed_technical);
                                case VIDEO_UNAVAILABLE_REASON_RIGHTV_ACCESS_DENIED_GEO_BLOCKED /* 514 */:
                                    return context.getString(R.string.tvview_msg_geo_blocked);
                                case VIDEO_UNAVAILABLE_REASON_RIGHTV_NOT_ALLOWED_OUT_OF_MEGACABLE_NETWORK /* 515 */:
                                    return context.getString(R.string.tvview_msg_onnet_error);
                                case VIDEO_UNAVAILABLE_REASON_RIGHTV_GENERAL_SERVER_ERROR /* 516 */:
                                    return context.getString(R.string.tvview_msg_server_error);
                                default:
                                    switch (i) {
                                        case VIDEO_UNAVAILABLE_REASON_OREGAN_ACCESS_DENIED_NO_SUBSCRIPTION /* 768 */:
                                            return context.getString(R.string.tvview_msg_not_subscribed_oregan);
                                        case VIDEO_UNAVAILABLE_REASON_OREGAN_ACCESS_DENIED_UNKNOWN /* 769 */:
                                            return context.getString(R.string.tvview_msg_network_error_oregan);
                                        case VIDEO_UNAVAILABLE_REASON_OREGAN_GENERAL_SERVER_ERROR /* 770 */:
                                            return context.getString(R.string.tvview_msg_generic_error_oregan);
                                        case VIDEO_UNAVAILABLE_REASON_OREGAN_PROVISIONNING_ERROR /* 771 */:
                                            return context.getString(R.string.tvview_msg_provisionning_error_oregan);
                                        case VIDEO_UNAVAILABLE_REASON_OREGAN_CONFIG_ERROR /* 772 */:
                                            return context.getString(R.string.tvview_msg_config_error_oregan);
                                        case VIDEO_UNAVAILABLE_REASON_OREGAN_HDCP_ERROR /* 773 */:
                                            return context.getString(R.string.tvview_msg_hdcp_error_oregan);
                                        default:
                                            return formatTISException(context, th, i);
                                    }
                            }
                    }
                }
            }
        }
        return null;
    }

    public static String getAvailableTVInputIdsQueryParam(Context context) {
        return "(" + ((String) ((TvInputManager) context.getSystemService("tv_input")).getTvInputList().stream().map(new java.util.function.Function() { // from class: com.dotscreen.tv.common.utils.-$$Lambda$CommonUtils$itSyWYZGs3sQmHQXpwKbQzDpmRc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CommonUtils.lambda$getAvailableTVInputIdsQueryParam$9((TvInputInfo) obj);
            }
        }).reduce(new BinaryOperator() { // from class: com.dotscreen.tv.common.utils.-$$Lambda$CommonUtils$FXa0cqFvmGAV7t1bnRTuMET-yBQ
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return CommonUtils.lambda$getAvailableTVInputIdsQueryParam$10((String) obj, (String) obj2);
            }
        }).get()) + ")";
    }

    static /* synthetic */ String lambda$getAvailableTVInputIdsQueryParam$9(TvInputInfo tvInputInfo) {
        return "'" + tvInputInfo.getId() + "'";
    }

    static /* synthetic */ String lambda$getAvailableTVInputIdsQueryParam$10(String str, String str2) {
        return str + ", " + str2;
    }
}
