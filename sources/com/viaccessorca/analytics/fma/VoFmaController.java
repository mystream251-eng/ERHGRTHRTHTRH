package com.viaccessorca.analytics.fma;

import android.os.Handler;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class VoFmaController implements VoFmaBusinessLogicListener {
    protected static final String Key_ACCOUNT_ID = "account_id";
    protected static final String Key_APPLICATION = "application";
    protected static final String Key_APPLICATION_VERSION = "application_version";
    protected static final String Key_ASSET_ID = "asset_id";
    protected static final String Key_BOOT_TIMESTAMP = "boot_timestamp";
    protected static final String Key_CHANNEL_ID = "channel_id";
    protected static final String Key_CHANNEL_NAME = "channel_name";
    protected static final String Key_CONTENT_OFFSET = "content_offset";
    protected static final String Key_CONTENT_TYPE = "content_type";
    protected static final String Key_CR_EVENT_MSG = "msg";
    protected static final String Key_CR_EVENT_SEVERITY = "severity";
    protected static final String Key_CR_EVENT_TITLE = "title";
    protected static final String Key_CUSTOM_EVENT_BOOT_STATUS = "BOOT_STATUS";
    protected static final String Key_CUSTOM_EVENT_LOGGING = "LOG_EVENT";
    protected static final String Key_CUSTOM_EVENT_NETWORK_STATUS = "NETWORK_STATUS";
    protected static final String Key_CUSTOM_EVENT_SERVICE_LIMITED = "SERVICE_LIMITED";
    protected static final String Key_CUSTOM_EVENT_SERVICE_ON = "SERVICE_ON";
    protected static final String Key_DEVICE_CATEGORY = "device_category";
    protected static final String Key_DEVICE_ID = "device_id";
    protected static final String Key_EPISODE_ID = "episode_id";
    protected static final String Key_EVENT_TIME = "timestamp";
    protected static final String Key_EVENT_TYPE = "event_type";
    protected static final String Key_FIRMWARE_VERSION = "firmware_version";
    protected static final String Key_FROM_CONTENT_OFFSET = "from_content_offset";
    protected static final String Key_FROM_PROGRAM_OFFSET = "from_program_offset";
    protected static final String Key_FROM_STREAM_TIME = "from_stream_time";
    protected static final String Key_IP_ADDRESS = "ip_address";
    protected static final String Key_LOG_EVENT = "log_event";
    protected static final String Key_NAME = "name";
    protected static final String Key_NETWORK_STATUS = "network_status";
    protected static final String Key_NETWORK_TYPE = "network_type";
    protected static final String Key_OPERATOR_NAME = "operator_name";
    protected static final String Key_ORIGIN = "origin";
    protected static final String Key_ORIGIN_PLATFORM = "origin_platform";
    protected static final String Key_ORIGIN_VERSION = "origin_version";
    protected static final String Key_PLAYER_ID = "vo_player_id";
    protected static final String Key_PROFILE_ID = "profile_id";
    protected static final String Key_PROGRAM_ID = "program_id";
    protected static final String Key_PROGRAM_NAME = "program_name";
    protected static final String Key_PROGRAM_OFFSET = "program_offset";
    protected static final String Key_PROVIDER_ID = "provider_id";
    protected static final String Key_ROOT_CAUSE_ANALYSIS = "root_cause_analysis";
    protected static final String Key_SERIAL_NUMBER = "serial_number";
    protected static final String Key_STREAM_OFFSET = "stream_offset";
    protected static final String Key_STREAM_TIME = "stream_time";
    protected static final String Key_TERMINAL_ID = "terminal_id";
    protected static final String Key_TEST_URL_STATUS = "test_url_status";
    protected static final String Key_TIMESTAMP = "timestamp";
    protected static final String Key_TIMESTAMP_OFF = "timestamp_off";
    protected static final String Key_TIMESTAMP_ON = "timestamp_on";
    protected static final String Key_TIME_ZONE = "time_zone";
    private static final String TAG = "VO_FMA";
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
    private static VoFmaController stSingleton;
    protected VoFmaControllerListener mListener;
    private boolean mInitialized = false;
    private VoFmaBusinessLogic mLogic = null;

    public enum VoFmaReturnCode {
        Success,
        failed,
        NotInitialized,
        AlreadyInitialized,
        MissingKeyValueInEvent,
        ConfigurationParsingFailed
    }

    public String getVersion() {
        return "1.2.4";
    }

    public static synchronized VoFmaReturnCode initialize(VoFmaInitParameters voFmaInitParameters) {
        if (stSingleton != null) {
            Log.w(TAG, "initialize() - Already initialized");
            return VoFmaReturnCode.AlreadyInitialized;
        }
        VoFmaController voFmaController = new VoFmaController();
        stSingleton = voFmaController;
        VoFmaReturnCode voFmaReturnCodeImplInit = voFmaController.implInit(voFmaInitParameters);
        if (voFmaReturnCodeImplInit == VoFmaReturnCode.Success) {
            Log.i(TAG, "FMA initialized successfully");
        }
        return voFmaReturnCodeImplInit;
    }

    public static synchronized VoFmaController getFmaController() {
        if (stSingleton != null && !stSingleton.mInitialized) {
            Log.w(TAG, "getFmaController() - Not initialized yet");
            return null;
        }
        return stSingleton;
    }

    public VoFmaReturnCode reloadConfiguration(String str) {
        if (!this.mInitialized) {
            Log.w(TAG, "reloadConfiguration() - Not initialized yet");
            return VoFmaReturnCode.NotInitialized;
        }
        if (str != null) {
            Log.d(TAG, "App ==> FMA: reloadConfiguration(\"" + str + "\")");
            this.mLogic.reloadConfiguration(str);
            return VoFmaReturnCode.Success;
        }
        return VoFmaReturnCode.failed;
    }

    public VoFmaReturnCode setAccountID(String str) {
        if (!this.mInitialized) {
            Log.w(TAG, "setAccountID() - Not initialized yet");
            return VoFmaReturnCode.NotInitialized;
        }
        if (str != null) {
            Log.d(TAG, "App ==> FMA: setAccountID(\"" + str + "\")");
        } else {
            Log.d(TAG, "App ==> FMA: setAccountID(null)");
        }
        this.mLogic.setAccountID(str);
        return VoFmaReturnCode.Success;
    }

    public VoFmaReturnCode setProfileID(String str) {
        if (!this.mInitialized) {
            Log.w(TAG, "setProfileID() - Not initialized yet");
            return VoFmaReturnCode.NotInitialized;
        }
        if (str != null) {
            Log.d(TAG, "App ==> FMA: setProfileID(\"" + str + "\")");
        } else {
            Log.d(TAG, "App ==> FMA: setProfileID(null)");
        }
        this.mLogic.setProfileID(str);
        return VoFmaReturnCode.Success;
    }

    private VoFmaController() {
    }

    private VoFmaReturnCode implInit(VoFmaInitParameters voFmaInitParameters) {
        setListener(voFmaInitParameters.mListener);
        VoFmaBusinessLogic voFmaBusinessLogic = new VoFmaBusinessLogic("FMA_Worker");
        this.mLogic = voFmaBusinessLogic;
        voFmaBusinessLogic.setListener(this);
        if (!this.mLogic.init(voFmaInitParameters, getVersion()).booleanValue()) {
            return VoFmaReturnCode.failed;
        }
        this.mLogic.start();
        return VoFmaReturnCode.Success;
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaBusinessLogicListener
    public void onInitBusinessLogicCompleted() {
        this.mInitialized = true;
        new Handler().postDelayed(new Runnable() { // from class: com.viaccessorca.analytics.fma.VoFmaController.1
            @Override // java.lang.Runnable
            public void run() {
                VoFmaController.this.mListener.onInitCompleted();
            }
        }, 0L);
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaBusinessLogicListener
    public void onConfigurationBusinessLogicReloaded() {
        new Handler().postDelayed(new Runnable() { // from class: com.viaccessorca.analytics.fma.VoFmaController.2
            @Override // java.lang.Runnable
            public void run() {
                VoFmaController.this.mListener.onConfigurationReloaded();
            }
        }, 0L);
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaBusinessLogicListener
    public void onInitBusinessLogicFailed() {
        this.mInitialized = true;
        new Handler().postDelayed(new Runnable() { // from class: com.viaccessorca.analytics.fma.VoFmaController.3
            @Override // java.lang.Runnable
            public void run() {
                VoFmaController.this.mListener.onInitFailed();
            }
        }, 0L);
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaBusinessLogicListener
    public void onConfigurationBusinessLogicReloadedFailed() {
        new Handler().postDelayed(new Runnable() { // from class: com.viaccessorca.analytics.fma.VoFmaController.4
            @Override // java.lang.Runnable
            public void run() {
                VoFmaController.this.mListener.onConfigurationReloadedFailed();
            }
        }, 0L);
    }

    public void setListener(VoFmaControllerListener voFmaControllerListener) {
        this.mListener = voFmaControllerListener;
    }

    public static synchronized VoFmaReturnCode debugReInitialize(VoFmaInitParameters voFmaInitParameters) {
        VoFmaController voFmaController;
        voFmaController = new VoFmaController();
        stSingleton = voFmaController;
        return voFmaController.implInit(voFmaInitParameters);
    }

    public VoFmaReturnCode logEvent(VoFmaEvent.VOPlayerSeverity vOPlayerSeverity, String str, String str2) {
        if (!this.mInitialized) {
            Log.w(TAG, "logEvent() - FMA Not initialized yet");
            return VoFmaReturnCode.NotInitialized;
        }
        if (str2 == null) {
            Log.e(TAG, "logEvent() - msg parameter is null");
            return VoFmaReturnCode.failed;
        }
        if (str == null) {
            Log.e(TAG, "logEvent() - title parameter is null");
            return VoFmaReturnCode.failed;
        }
        if (str2 == null) {
            Log.e(TAG, "logEvent() - msg parameter is null");
            return VoFmaReturnCode.failed;
        }
        return this.mLogic.logCrEvent(vOPlayerSeverity, str, str2);
    }
}
