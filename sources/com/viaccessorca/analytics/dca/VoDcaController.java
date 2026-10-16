package com.viaccessorca.analytics.dca;

import android.os.Handler;
import android.util.Log;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class VoDcaController implements VoDcaBusinessLogicListener {
    protected static final String Key_ACCOUNT_ID = "accountId";
    protected static final String Key_APPLICATION = "application";
    protected static final String Key_APPLICATION_VERSION = "applicationVersion";
    protected static final String Key_ASSET_ID = "assetId";
    protected static final String Key_CHANNEL_ID = "channelId";
    protected static final String Key_CHANNEL_NAME = "channelName";
    protected static final String Key_CONTENT_OFFSET = "contentOffset";
    protected static final String Key_CONTENT_TYPE = "contentType";
    protected static final String Key_DEVICE_CATEGORY = "deviceCategory";
    protected static final String Key_DEVICE_ID = "deviceId";
    protected static final String Key_EPISODE_ID = "episodeId";
    protected static final String Key_EVENT_TIME = "eventTime";
    protected static final String Key_EVENT_TYPE = "eventType";
    protected static final String Key_FROM_CONTENT_OFFSET = "fromContentOffset";
    protected static final String Key_FROM_PROGRAM_OFFSET = "fromProgramOffset";
    protected static final String Key_FROM_STREAM_TIME = "fromStreamTime";
    protected static final String Key_NAME = "name";
    protected static final String Key_OPERATOR_NAME = "operatorName";
    protected static final String Key_ORIGIN = "origin";
    protected static final String Key_ORIGIN_PLATFORM = "originPlatform";
    protected static final String Key_ORIGIN_VERSION = "originVersion";
    protected static final String Key_PROFILE_ID = "profileId";
    protected static final String Key_PROGRAM_ID = "programId";
    protected static final String Key_PROGRAM_NAME = "programName";
    protected static final String Key_PROGRAM_OFFSET = "programOffset";
    protected static final String Key_PROVIDER_ID = "providerId";
    protected static final String Key_STREAM_OFFSET = "streamOffset";
    protected static final String Key_STREAM_TIME = "streamTime";
    private static final String TAG = "VO_DCA";
    protected static final String TypeStr_OPEN = "OPEN";
    protected static final String TypeStr_PAUSE = "PAUSE";
    protected static final String TypeStr_PLAY = "PLAY";
    protected static final String TypeStr_RESUME = "RESUME";
    protected static final String TypeStr_SEEK_END = "SEEK_END";
    protected static final String TypeStr_SEEK_START = "SEEK_START";
    protected static final String TypeStr_SET_CHANNEL = "SET_CHANNEL";
    protected static final String TypeStr_SET_EPISODE = "SET_EPISODE";
    protected static final String TypeStr_SET_MEDIA = "SET_MEDIA";
    protected static final String TypeStr_SET_MOVIE = "SET_MOVIE";
    protected static final String TypeStr_SET_PREVIEW = "SET_PREVIEW";
    protected static final String TypeStr_SET_PROGRAM = "SET_PROGRAM";
    protected static final String TypeStr_STOP = "STOP";
    protected static final int Type_CUSTOM = 0;
    protected static final int Type_OPEN = 1;
    protected static final int Type_PAUSE = 2;
    protected static final int Type_PLAY = 3;
    protected static final int Type_RESUME = 4;
    protected static final int Type_SEEK_END = 5;
    protected static final int Type_SEEK_START = 6;
    protected static final int Type_SET_CHANNEL = 7;
    protected static final int Type_SET_EPISODE = 8;
    protected static final int Type_SET_MEDIA = 9;
    protected static final int Type_SET_MOVIE = 10;
    protected static final int Type_SET_PREVIEW = 11;
    protected static final int Type_SET_PROGRAM = 12;
    protected static final int Type_STOP = 13;
    private static VoDcaController stSingleton;
    protected VoDcaControllerListener mListener;
    private boolean mInitialized = false;
    private VoDcaBusinessLogic mLogic = null;

    public enum VoDcaReturnCode {
        Success,
        failed,
        NotInitialized,
        AlreadyInitialized,
        MissingKeyValueInEvent,
        ConfigurationParsingFailed
    }

    public String getVersion() {
        return "2.5.2";
    }

    public void onDestroy() {
    }

    public static synchronized VoDcaReturnCode initialize(VoDcaInitParameters voDcaInitParameters) {
        if (stSingleton != null) {
            Log.w(TAG, "initialize() - Already initialized");
            return VoDcaReturnCode.AlreadyInitialized;
        }
        VoDcaController voDcaController = new VoDcaController();
        stSingleton = voDcaController;
        return voDcaController.implInit(voDcaInitParameters);
    }

    public static synchronized VoDcaController getDcaController() {
        if (stSingleton != null && !stSingleton.mInitialized) {
            Log.w(TAG, "getDcaController() - Not initialized yet");
            return null;
        }
        return stSingleton;
    }

    public VoDcaReturnCode reloadConfiguration(String str) {
        if (!this.mInitialized) {
            Log.w(TAG, "reloadConfiguration() - Not initialized yet");
            return VoDcaReturnCode.NotInitialized;
        }
        if (str != null) {
            Log.d(TAG, "App ==> DCA: reloadConfiguration(\"" + str + "\")");
            this.mLogic.reloadConfiguration(str);
            return VoDcaReturnCode.Success;
        }
        return VoDcaReturnCode.failed;
    }

    public VoDcaReturnCode setAccountID(String str) {
        if (!this.mInitialized) {
            Log.w(TAG, "setAccountID() - Not initialized yet");
            return VoDcaReturnCode.NotInitialized;
        }
        if (str != null) {
            Log.d(TAG, "App ==> DCA: setAccountID(\"" + str + "\")");
        } else {
            Log.d(TAG, "App ==> DCA: setAccountID(null)");
        }
        this.mLogic.setAccountID(str);
        return VoDcaReturnCode.Success;
    }

    public VoDcaReturnCode setProfileID(String str) {
        if (!this.mInitialized) {
            Log.w(TAG, "setProfileID() - Not initialized yet");
            return VoDcaReturnCode.NotInitialized;
        }
        if (str != null) {
            Log.d(TAG, "App ==> DCA: setProfileID(\"" + str + "\")");
        } else {
            Log.d(TAG, "App ==> DCA: setProfileID(null)");
        }
        this.mLogic.setProfileID(str);
        return VoDcaReturnCode.Success;
    }

    public Boolean setTickInterval(long j) {
        this.mLogic.setTickInterval(j);
        return true;
    }

    public Boolean setNetworkAvailability(Boolean bool) {
        this.mLogic.setNetworkAvailabilityDbg(bool);
        return true;
    }

    public VoDcaReturnCode logEvent(VoDcaEvent voDcaEvent) {
        if (!this.mInitialized) {
            Log.w(TAG, "logEvent() - Not initialized yet");
            return VoDcaReturnCode.NotInitialized;
        }
        if (voDcaEvent == null) {
            Log.e(TAG, "logEvent() - event parameter is null");
            return VoDcaReturnCode.failed;
        }
        Log.d(TAG, "App ==> DCA: " + voDcaEvent.getJsonObject());
        return this.mLogic.logEvent(voDcaEvent);
    }

    private VoDcaController() {
    }

    private VoDcaReturnCode implInit(VoDcaInitParameters voDcaInitParameters) {
        setListener(voDcaInitParameters.mListener);
        VoDcaBusinessLogic voDcaBusinessLogic = new VoDcaBusinessLogic("DCA_Worker");
        this.mLogic = voDcaBusinessLogic;
        voDcaBusinessLogic.setListener(this);
        if (!this.mLogic.init(voDcaInitParameters, getVersion()).booleanValue()) {
            return VoDcaReturnCode.failed;
        }
        this.mLogic.start();
        return VoDcaReturnCode.Success;
    }

    @Override // com.viaccessorca.analytics.dca.VoDcaBusinessLogicListener
    public void onInitBusinessLogicCompleted() {
        this.mInitialized = true;
        new Handler().postDelayed(new Runnable() { // from class: com.viaccessorca.analytics.dca.VoDcaController.1
            @Override // java.lang.Runnable
            public void run() {
                VoDcaController.this.mListener.onInitCompleted();
            }
        }, 0L);
    }

    @Override // com.viaccessorca.analytics.dca.VoDcaBusinessLogicListener
    public void onConfigurationBusinessLogicReloaded() {
        new Handler().postDelayed(new Runnable() { // from class: com.viaccessorca.analytics.dca.VoDcaController.2
            @Override // java.lang.Runnable
            public void run() {
                VoDcaController.this.mListener.onConfigurationReloaded();
            }
        }, 0L);
    }

    @Override // com.viaccessorca.analytics.dca.VoDcaBusinessLogicListener
    public void onInitBusinessLogicFailed() {
        this.mInitialized = true;
        new Handler().postDelayed(new Runnable() { // from class: com.viaccessorca.analytics.dca.VoDcaController.3
            @Override // java.lang.Runnable
            public void run() {
                VoDcaController.this.mListener.onInitFailed();
            }
        }, 0L);
    }

    @Override // com.viaccessorca.analytics.dca.VoDcaBusinessLogicListener
    public void onConfigurationBusinessLogicReloadedFailed() {
        new Handler().postDelayed(new Runnable() { // from class: com.viaccessorca.analytics.dca.VoDcaController.4
            @Override // java.lang.Runnable
            public void run() {
                VoDcaController.this.mListener.onConfigurationReloadedFailed();
            }
        }, 0L);
    }

    public void setListener(VoDcaControllerListener voDcaControllerListener) {
        this.mListener = voDcaControllerListener;
    }

    public static synchronized VoDcaReturnCode debugReInitialize(VoDcaInitParameters voDcaInitParameters) {
        VoDcaController voDcaController;
        voDcaController = new VoDcaController();
        stSingleton = voDcaController;
        return voDcaController.implInit(voDcaInitParameters);
    }

    public Map<String, String> getAnalyticsData() {
        if (!this.mInitialized) {
            Log.v(TAG, "Trying to get analytics metadatas before initializing the DCA ");
            return null;
        }
        return this.mLogic.getAnalyticsData();
    }
}
