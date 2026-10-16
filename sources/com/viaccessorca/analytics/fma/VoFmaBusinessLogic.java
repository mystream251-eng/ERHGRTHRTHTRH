package com.viaccessorca.analytics.fma;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.MimeTypes;
import com.viaccessorca.voplayer.VOPlaybackSessionReport;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes2.dex */
public class VoFmaBusinessLogic extends HandlerThread implements VoFmaDispatcherListener, VoFmaEventHandlerListener {
    protected static final String EVENT_INTERNAL_KEY = "_intr";
    protected static final String EVENT_INTERNAL_VALUE_AFTER = "After";
    protected static final String EVENT_INTERNAL_VALUE_ALREADY_SENT = "AlreadySent";
    protected static final String EVENT_INTERNAL_VALUE_BEFORE = "Before";
    protected static final String EVENT_INTERNAL_VALUE_FAKE_EVENT = "FakeEvent";
    protected static final String EVENT_INTERNAL_VALUE_REMOVE_P_S = "RemovePS";
    protected static final String EVENT_INTERNAL_VALUE_REMOVE_S_P = "RemoveSP";
    protected static final String EVENT_INTERNAL_VALUE_SEQUENCE = "Sequence";
    private static final int QUEUE_CAPACITY = 512;
    private static final String TAG = "VO_FMA";
    protected static final String VOPLAYER_CLASS_PATH = "com.viaccessorca.voplayer.VOPlayer";
    protected static final String VOPLAYER_CLASS_PATH_TEST = "com.viaccessorca.analytics.fma.VoPlayer";
    protected static final String VOPLAYER_GET_INSTANCE_METHOD = "getVOPlayerInstance";
    protected static final String VOPLAYER_GET_INSTANCE_METHOD_TEST = "getPlayerInstance";
    protected static final String VOPLAYER_SET_METADATA_METHOD = "setAnalyticsMetadata";
    protected static final String VOPLAYER_SET_METADATA_METHOD_TEST = "setAnalyticsMetadata";
    private long EVENT_HANDLER_TICK_INTERVAL;
    private String analyticsChannelId;
    private String analyticsChannelName;
    private String analyticsContentId;
    private String analyticsContentName;
    private Map<String, String> analyticsMetadatas;
    private FmaDatabaseHelper fmaDataBase;
    private String mAccountID;
    private String mConfigUrl;
    private VoFmaConfiguration mConfiguration;
    private Object mConfigurationSync;
    private ContentType mContentType;
    private boolean mContinue;
    private VoFmaDispatcher mDispatcher;
    private VoFmaEventHandlerZapFilter mEventHandlerCatchupPauseResume;
    private VoFmaEventHandlerZapFilter mEventHandlerCatchupPlayStop;
    private VoFmaEventHandlerSeek mEventHandlerCatchupSeek;
    private VoFmaEventHandlerZapFilter mEventHandlerDownloadPauseResume;
    private VoFmaEventHandlerZapFilter mEventHandlerDownloadPlayStop;
    private VoFmaEventHandlerSeek mEventHandlerDownloadSeek;
    private VoFmaEventHandlerZapFilter mEventHandlerLivePauseResume;
    private VoFmaEventHandlerZapFilter mEventHandlerLivePlayStop;
    private VoFmaEventHandlerSeek mEventHandlerLiveSeek;
    private VoFmaEventHandlerZapFilter mEventHandlerNpvrPauseResume;
    private VoFmaEventHandlerZapFilter mEventHandlerNpvrPlayStop;
    private VoFmaEventHandlerSeek mEventHandlerNpvrSeek;
    private VoFmaEventHandlerZapFilter mEventHandlerPreviewPauseResume;
    private VoFmaEventHandlerZapFilter mEventHandlerPreviewPlayStop;
    private VoFmaEventHandlerSeek mEventHandlerPreviewSeek;
    private VoFmaEventHandlerProxy mEventHandlerProxy;
    private VoFmaEventHandlerZapFilter mEventHandlerStartoverPauseResume;
    private VoFmaEventHandlerZapFilter mEventHandlerStartoverPlayStop;
    private VoFmaEventHandlerSeek mEventHandlerStartoverSeek;
    private VoFmaEventHandlerZapFilter mEventHandlerVodPauseResume;
    private VoFmaEventHandlerZapFilter mEventHandlerVodPlayStop;
    private VoFmaEventHandlerSeek mEventHandlerVodSeek;
    private Handler mHandler;
    private VoFmaBusinessLogicListener mListener;
    private ContentType mNextContentType;
    private String mProfileID;
    private LinkedBlockingQueue<VoFmaEvent> mQueue;
    private Object mQueueSync;
    private boolean mReloadConfigurationFlag;
    Timer mReloadConfigurationTimer;
    private VoFmaEventHandlerThread mThread;

    private enum ContentType {
        NONE,
        LIVE,
        STARTOVER,
        CATCHUP,
        VOD,
        PREVIEW,
        NPVR,
        DOWNLOAD
    }

    private class VoFmaEventHandlerThread extends Thread {
        private VoFmaEventHandlerThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (VoFmaBusinessLogic.this.mContinue) {
                long j = VoFmaBusinessLogic.this.EVENT_HANDLER_TICK_INTERVAL;
                synchronized (VoFmaBusinessLogic.this.mConfigurationSync) {
                    long jTick = VoFmaBusinessLogic.this.mEventHandlerProxy.tick();
                    if (jTick > 0 && j > jTick) {
                        j = jTick;
                    }
                    long jTick2 = VoFmaBusinessLogic.this.mEventHandlerLivePlayStop.tick();
                    if (jTick2 > 0 && j > jTick2) {
                        j = jTick2;
                    }
                    long jTick3 = VoFmaBusinessLogic.this.mEventHandlerStartoverPlayStop.tick();
                    if (jTick3 > 0 && j > jTick3) {
                        j = jTick3;
                    }
                    long jTick4 = VoFmaBusinessLogic.this.mEventHandlerCatchupPlayStop.tick();
                    if (jTick4 > 0 && j > jTick4) {
                        j = jTick4;
                    }
                    long jTick5 = VoFmaBusinessLogic.this.mEventHandlerVodPlayStop.tick();
                    if (jTick5 > 0 && j > jTick5) {
                        j = jTick5;
                    }
                    long jTick6 = VoFmaBusinessLogic.this.mEventHandlerPreviewPlayStop.tick();
                    if (jTick6 > 0 && j > jTick6) {
                        j = jTick6;
                    }
                    long jTick7 = VoFmaBusinessLogic.this.mEventHandlerNpvrPlayStop.tick();
                    if (jTick7 > 0 && j > jTick7) {
                        j = jTick7;
                    }
                    long jTick8 = VoFmaBusinessLogic.this.mEventHandlerDownloadPlayStop.tick();
                    if (jTick8 > 0 && j > jTick8) {
                        j = jTick8;
                    }
                    long jTick9 = VoFmaBusinessLogic.this.mEventHandlerLivePauseResume.tick();
                    if (jTick9 > 0 && j > jTick9) {
                        j = jTick9;
                    }
                    long jTick10 = VoFmaBusinessLogic.this.mEventHandlerStartoverPauseResume.tick();
                    if (jTick10 > 0 && j > jTick10) {
                        j = jTick10;
                    }
                    long jTick11 = VoFmaBusinessLogic.this.mEventHandlerCatchupPauseResume.tick();
                    if (jTick11 > 0 && j > jTick11) {
                        j = jTick11;
                    }
                    long jTick12 = VoFmaBusinessLogic.this.mEventHandlerVodPauseResume.tick();
                    if (jTick12 > 0 && j > jTick12) {
                        j = jTick12;
                    }
                    long jTick13 = VoFmaBusinessLogic.this.mEventHandlerPreviewPauseResume.tick();
                    if (jTick13 > 0 && j > jTick13) {
                        j = jTick13;
                    }
                    long jTick14 = VoFmaBusinessLogic.this.mEventHandlerNpvrPauseResume.tick();
                    if (jTick14 > 0 && j > jTick14) {
                        j = jTick14;
                    }
                    long jTick15 = VoFmaBusinessLogic.this.mEventHandlerDownloadPauseResume.tick();
                    if (jTick15 > 0 && j > jTick15) {
                        j = jTick15;
                    }
                    long jTick16 = VoFmaBusinessLogic.this.mEventHandlerLiveSeek.tick();
                    if (jTick16 > 0 && j > jTick16) {
                        j = jTick16;
                    }
                    long jTick17 = VoFmaBusinessLogic.this.mEventHandlerStartoverSeek.tick();
                    if (jTick17 > 0 && j > jTick17) {
                        j = jTick17;
                    }
                    long jTick18 = VoFmaBusinessLogic.this.mEventHandlerCatchupSeek.tick();
                    if (jTick18 > 0 && j > jTick18) {
                        j = jTick18;
                    }
                    long jTick19 = VoFmaBusinessLogic.this.mEventHandlerVodSeek.tick();
                    if (jTick19 > 0 && j > jTick19) {
                        j = jTick19;
                    }
                    long jTick20 = VoFmaBusinessLogic.this.mEventHandlerPreviewSeek.tick();
                    if (jTick20 > 0 && j > jTick20) {
                        j = jTick20;
                    }
                    long jTick21 = VoFmaBusinessLogic.this.mEventHandlerNpvrSeek.tick();
                    if (jTick21 > 0 && j > jTick21) {
                        j = jTick21;
                    }
                    long jTick22 = VoFmaBusinessLogic.this.mEventHandlerDownloadSeek.tick();
                    if (jTick22 > 0 && j > jTick22) {
                        j = jTick22;
                    }
                }
                try {
                    Thread.sleep(j);
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    protected VoFmaBusinessLogic(String str) {
        super(str);
        this.EVENT_HANDLER_TICK_INTERVAL = 10000L;
        this.mContinue = true;
        this.mReloadConfigurationFlag = false;
        this.mQueue = new LinkedBlockingQueue<>(512);
        this.mQueueSync = new Object();
        this.mConfigurationSync = new Object();
        this.mContentType = ContentType.NONE;
        new Timer();
    }

    protected void setListener(VoFmaBusinessLogicListener voFmaBusinessLogicListener) {
        this.mListener = voFmaBusinessLogicListener;
    }

    protected void setAccountID(String str) {
        this.mAccountID = str;
    }

    protected void setTickInterval(long j) {
        this.EVENT_HANDLER_TICK_INTERVAL = j;
    }

    protected void rebootDeviceDbg() {
        this.mDispatcher.rebootDeviceDbg();
    }

    protected void setNetworkAvailabilityDbg(Boolean bool) {
        this.mDispatcher.setNetworkAvailabilityDbg(bool.booleanValue());
    }

    protected void setServiceStatusDbg(Boolean bool) {
        this.mDispatcher.setServiceStatusDbg(bool.booleanValue());
    }

    protected void stopRunningTimersDbg() {
        this.mDispatcher.stopRunningTimers();
    }

    protected void setProfileID(String str) {
        this.mProfileID = str;
    }

    protected Boolean init(VoFmaInitParameters voFmaInitParameters, String str) {
        this.mConfiguration = new VoFmaConfiguration();
        if (voFmaInitParameters.mDeviceID == null) {
            Log.v(TAG, "Mandatory DeviceId param is missing");
            return false;
        }
        if (voFmaInitParameters.mApplicationName == null) {
            Log.v(TAG, "Mandatory DeviceId param is missing");
            return false;
        }
        if (voFmaInitParameters.mApplicationVersion == null) {
            Log.v(TAG, "Mandatory DeviceId param is missing");
            return false;
        }
        if (voFmaInitParameters.mConfigurationUrl == null) {
            Log.v(TAG, "Mandatory mConfigurationUrl param is missing");
            return false;
        }
        this.mConfiguration.mDeviceID = voFmaInitParameters.mDeviceID;
        this.mConfiguration.mApplicationName = voFmaInitParameters.mApplicationName;
        this.mConfiguration.mApplicationVersion = voFmaInitParameters.mApplicationVersion;
        this.mConfiguration.mFmaVersion = str;
        this.mConfigUrl = voFmaInitParameters.mConfigurationUrl;
        if (voFmaInitParameters.mDeviceCategory != null) {
            this.mConfiguration.mDeviceCategory = voFmaInitParameters.mDeviceCategory;
        } else {
            this.mConfiguration.mDeviceCategory = "";
        }
        VoFmaDispatcher voFmaDispatcher = new VoFmaDispatcher(voFmaInitParameters.mApplicationContext);
        this.mDispatcher = voFmaDispatcher;
        voFmaDispatcher.setListener(this);
        this.mDispatcher.setParamsForMonitoring(voFmaInitParameters, str);
        if (this.mDispatcher.getConfiguration(voFmaInitParameters.mConfigurationUrl) != VoFmaController.VoFmaReturnCode.Success) {
            return false;
        }
        this.mEventHandlerProxy = new VoFmaEventHandlerProxy(this.mDispatcher, this.mConfiguration);
        VoFmaEventHandlerZapFilter voFmaEventHandlerZapFilter = new VoFmaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerLivePlayStop = voFmaEventHandlerZapFilter;
        voFmaEventHandlerZapFilter.setListener(this);
        this.mEventHandlerLivePlayStop.setInputEvents(3, 13);
        this.mEventHandlerLivePlayStop.setOutputEvents("LIVE_PLAY", "LIVE_STOP");
        this.mEventHandlerLivePlayStop.setChannelRequired(true);
        this.mEventHandlerLivePlayStop.setProgramRequired(false);
        this.mEventHandlerLivePlayStop.setEnableHandleEvents(this.mConfiguration.mLivePlayStop_StateConfirmationPeriod != -1);
        VoFmaEventHandlerZapFilter voFmaEventHandlerZapFilter2 = new VoFmaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerStartoverPlayStop = voFmaEventHandlerZapFilter2;
        voFmaEventHandlerZapFilter2.setListener(this);
        this.mEventHandlerStartoverPlayStop.setInputEvents(3, 13);
        this.mEventHandlerStartoverPlayStop.setOutputEvents("STARTOVER_PLAY", "STARTOVER_STOP");
        this.mEventHandlerStartoverPlayStop.setChannelRequired(true);
        this.mEventHandlerStartoverPlayStop.setProgramRequired(true);
        this.mEventHandlerStartoverPlayStop.setEnableHandleEvents(this.mConfiguration.mStartoverPlayStop_StateConfirmationPeriod != -1);
        this.mEventHandlerStartoverPlayStop.setConvertFromToField("stream_offset", "program_offset");
        VoFmaEventHandlerZapFilter voFmaEventHandlerZapFilter3 = new VoFmaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerCatchupPlayStop = voFmaEventHandlerZapFilter3;
        voFmaEventHandlerZapFilter3.setListener(this);
        this.mEventHandlerCatchupPlayStop.setInputEvents(3, 13);
        this.mEventHandlerCatchupPlayStop.setOutputEvents("CATCHUP_PLAY", "CATCHUP_STOP");
        this.mEventHandlerCatchupPlayStop.setChannelRequired(true);
        this.mEventHandlerCatchupPlayStop.setProgramRequired(true);
        this.mEventHandlerCatchupPlayStop.setEnableHandleEvents(this.mConfiguration.mCatchupPlayStop_StateConfirmationPeriod != -1);
        this.mEventHandlerCatchupPlayStop.setConvertFromToField("stream_offset", "program_offset");
        VoFmaEventHandlerZapFilter voFmaEventHandlerZapFilter4 = new VoFmaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerVodPlayStop = voFmaEventHandlerZapFilter4;
        voFmaEventHandlerZapFilter4.setListener(this);
        this.mEventHandlerVodPlayStop.setInputEvents(3, 13);
        this.mEventHandlerVodPlayStop.setOutputEvents("VOD_PLAY", "VOD_STOP");
        this.mEventHandlerVodPlayStop.setChannelRequired(false);
        this.mEventHandlerVodPlayStop.setProgramRequired(false);
        this.mEventHandlerVodPlayStop.setVodInfoRequired(true);
        this.mEventHandlerVodPlayStop.setEnableHandleEvents(this.mConfiguration.mVodPlayStop_StateConfirmationPeriod != -1);
        this.mEventHandlerVodPlayStop.setConvertFromToField("stream_offset", "content_offset");
        VoFmaEventHandlerZapFilter voFmaEventHandlerZapFilter5 = new VoFmaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerPreviewPlayStop = voFmaEventHandlerZapFilter5;
        voFmaEventHandlerZapFilter5.setListener(this);
        this.mEventHandlerPreviewPlayStop.setInputEvents(3, 13);
        this.mEventHandlerPreviewPlayStop.setOutputEvents("PREVIEW_PLAY", "PREVIEW_STOP");
        this.mEventHandlerPreviewPlayStop.setChannelRequired(false);
        this.mEventHandlerPreviewPlayStop.setProgramRequired(false);
        this.mEventHandlerPreviewPlayStop.setPreviewInfoRequired(true);
        this.mEventHandlerPreviewPlayStop.setEnableHandleEvents(this.mConfiguration.mPreviewPlayStop_StateConfirmationPeriod != -1);
        this.mEventHandlerPreviewPlayStop.setConvertFromToField("stream_offset", "content_offset");
        VoFmaEventHandlerZapFilter voFmaEventHandlerZapFilter6 = new VoFmaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerNpvrPlayStop = voFmaEventHandlerZapFilter6;
        voFmaEventHandlerZapFilter6.setListener(this);
        this.mEventHandlerNpvrPlayStop.setInputEvents(3, 13);
        this.mEventHandlerNpvrPlayStop.setOutputEvents("NPVR_PLAY", "NPVR_STOP");
        this.mEventHandlerNpvrPlayStop.setChannelRequired(true);
        this.mEventHandlerNpvrPlayStop.setProgramRequired(true);
        this.mEventHandlerNpvrPlayStop.setEnableHandleEvents(this.mConfiguration.mNpvrPlayStop_StateConfirmationPeriod != -1);
        this.mEventHandlerNpvrPlayStop.setConvertFromToField("stream_offset", "program_offset");
        VoFmaEventHandlerZapFilter voFmaEventHandlerZapFilter7 = new VoFmaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerDownloadPlayStop = voFmaEventHandlerZapFilter7;
        voFmaEventHandlerZapFilter7.setListener(this);
        this.mEventHandlerDownloadPlayStop.setInputEvents(3, 13);
        this.mEventHandlerDownloadPlayStop.setOutputEvents("DOWNLOAD_PLAY", "DOWNLOAD_STOP");
        this.mEventHandlerDownloadPlayStop.setChannelRequired(false);
        this.mEventHandlerDownloadPlayStop.setProgramRequired(false);
        this.mEventHandlerDownloadPlayStop.setVodInfoRequired(true);
        this.mEventHandlerDownloadPlayStop.setEnableHandleEvents(this.mConfiguration.mDownloadPlayStop_StateConfirmationPeriod != -1);
        this.mEventHandlerDownloadPlayStop.setConvertFromToField("stream_offset", "content_offset");
        VoFmaEventHandlerZapFilter voFmaEventHandlerZapFilter8 = new VoFmaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerLivePauseResume = voFmaEventHandlerZapFilter8;
        voFmaEventHandlerZapFilter8.setInputEvents(2, 4);
        this.mEventHandlerLivePauseResume.setOutputEvents("LIVE_PAUSE", "LIVE_RESUME");
        this.mEventHandlerLivePauseResume.setResetEvent("LIVE_PLAY");
        this.mEventHandlerLivePauseResume.setChannelRequired(true);
        this.mEventHandlerLivePauseResume.setProgramRequired(false);
        this.mEventHandlerLivePauseResume.setEnableHandleEvents(false);
        VoFmaEventHandlerZapFilter voFmaEventHandlerZapFilter9 = new VoFmaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerStartoverPauseResume = voFmaEventHandlerZapFilter9;
        voFmaEventHandlerZapFilter9.setInputEvents(2, 4);
        this.mEventHandlerStartoverPauseResume.setOutputEvents("STARTOVER_PAUSE", "STARTOVER_RESUME");
        this.mEventHandlerStartoverPauseResume.setResetEvent("STARTOVER_PLAY");
        this.mEventHandlerStartoverPauseResume.setChannelRequired(true);
        this.mEventHandlerStartoverPauseResume.setProgramRequired(true);
        this.mEventHandlerStartoverPauseResume.setEnableHandleEvents(false);
        this.mEventHandlerStartoverPauseResume.setConvertFromToField("stream_offset", "program_offset");
        VoFmaEventHandlerZapFilter voFmaEventHandlerZapFilter10 = new VoFmaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerCatchupPauseResume = voFmaEventHandlerZapFilter10;
        voFmaEventHandlerZapFilter10.setInputEvents(2, 4);
        this.mEventHandlerCatchupPauseResume.setOutputEvents("CATCHUP_PAUSE", "CATCHUP_RESUME");
        this.mEventHandlerCatchupPauseResume.setResetEvent("CATCHUP_PLAY");
        this.mEventHandlerCatchupPauseResume.setChannelRequired(true);
        this.mEventHandlerCatchupPauseResume.setProgramRequired(true);
        this.mEventHandlerCatchupPauseResume.setEnableHandleEvents(false);
        this.mEventHandlerCatchupPauseResume.setConvertFromToField("stream_offset", "program_offset");
        VoFmaEventHandlerZapFilter voFmaEventHandlerZapFilter11 = new VoFmaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerVodPauseResume = voFmaEventHandlerZapFilter11;
        voFmaEventHandlerZapFilter11.setInputEvents(2, 4);
        this.mEventHandlerVodPauseResume.setOutputEvents("VOD_PAUSE", "VOD_RESUME");
        this.mEventHandlerVodPauseResume.setResetEvent("VOD_PLAY");
        this.mEventHandlerVodPauseResume.setChannelRequired(false);
        this.mEventHandlerVodPauseResume.setProgramRequired(false);
        this.mEventHandlerVodPauseResume.setVodInfoRequired(true);
        this.mEventHandlerVodPauseResume.setEnableHandleEvents(false);
        this.mEventHandlerVodPauseResume.setConvertFromToField("stream_offset", "content_offset");
        VoFmaEventHandlerZapFilter voFmaEventHandlerZapFilter12 = new VoFmaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerPreviewPauseResume = voFmaEventHandlerZapFilter12;
        voFmaEventHandlerZapFilter12.setInputEvents(2, 4);
        this.mEventHandlerPreviewPauseResume.setOutputEvents("PREVIEW_PAUSE", "PREVIEW_RESUME");
        this.mEventHandlerPreviewPauseResume.setResetEvent("PREVIEW_PLAY");
        this.mEventHandlerPreviewPauseResume.setChannelRequired(false);
        this.mEventHandlerPreviewPauseResume.setProgramRequired(false);
        this.mEventHandlerPreviewPauseResume.setPreviewInfoRequired(true);
        this.mEventHandlerPreviewPauseResume.setEnableHandleEvents(false);
        this.mEventHandlerPreviewPauseResume.setConvertFromToField("stream_offset", "content_offset");
        VoFmaEventHandlerZapFilter voFmaEventHandlerZapFilter13 = new VoFmaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerNpvrPauseResume = voFmaEventHandlerZapFilter13;
        voFmaEventHandlerZapFilter13.setInputEvents(2, 4);
        this.mEventHandlerNpvrPauseResume.setOutputEvents("NPVR_PAUSE", "NPVR_RESUME");
        this.mEventHandlerNpvrPauseResume.setResetEvent("NPVR_PLAY");
        this.mEventHandlerNpvrPauseResume.setChannelRequired(true);
        this.mEventHandlerNpvrPauseResume.setProgramRequired(true);
        this.mEventHandlerNpvrPauseResume.setEnableHandleEvents(false);
        this.mEventHandlerNpvrPauseResume.setConvertFromToField("stream_offset", "program_offset");
        VoFmaEventHandlerZapFilter voFmaEventHandlerZapFilter14 = new VoFmaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerDownloadPauseResume = voFmaEventHandlerZapFilter14;
        voFmaEventHandlerZapFilter14.setInputEvents(2, 4);
        this.mEventHandlerDownloadPauseResume.setOutputEvents("DOWNLOAD_PAUSE", "DOWNLOAD_RESUME");
        this.mEventHandlerDownloadPauseResume.setResetEvent("DOWNLOAD_PLAY");
        this.mEventHandlerDownloadPauseResume.setChannelRequired(false);
        this.mEventHandlerDownloadPauseResume.setProgramRequired(false);
        this.mEventHandlerDownloadPauseResume.setVodInfoRequired(true);
        this.mEventHandlerDownloadPauseResume.setEnableHandleEvents(false);
        this.mEventHandlerDownloadPauseResume.setConvertFromToField("stream_offset", "content_offset");
        VoFmaEventHandlerSeek voFmaEventHandlerSeek = new VoFmaEventHandlerSeek(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerLiveSeek = voFmaEventHandlerSeek;
        voFmaEventHandlerSeek.setInputEvents(6, 5);
        this.mEventHandlerLiveSeek.setOutputEvent("LIVE_SEEK");
        this.mEventHandlerLiveSeek.setBreakEvents("LIVE_PLAY", "LIVE_PAUSE");
        this.mEventHandlerLiveSeek.setChannelRequired(true);
        this.mEventHandlerLiveSeek.setProgramRequired(false);
        this.mEventHandlerLiveSeek.setFromToTimeFields("from_stream_time", "stream_time");
        this.mEventHandlerLiveSeek.setEnableHandleEvents(false);
        VoFmaEventHandlerSeek voFmaEventHandlerSeek2 = new VoFmaEventHandlerSeek(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerStartoverSeek = voFmaEventHandlerSeek2;
        voFmaEventHandlerSeek2.setInputEvents(6, 5);
        this.mEventHandlerStartoverSeek.setOutputEvent("STARTOVER_SEEK");
        this.mEventHandlerStartoverSeek.setBreakEvents("STARTOVER_PLAY", "STARTOVER_PAUSE");
        this.mEventHandlerStartoverSeek.setChannelRequired(true);
        this.mEventHandlerStartoverSeek.setProgramRequired(true);
        this.mEventHandlerStartoverSeek.setFromToTimeFields("from_program_offset", "program_offset");
        this.mEventHandlerStartoverSeek.setEnableHandleEvents(false);
        this.mEventHandlerStartoverSeek.setConvertFromToField("stream_offset", "program_offset");
        VoFmaEventHandlerSeek voFmaEventHandlerSeek3 = new VoFmaEventHandlerSeek(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerCatchupSeek = voFmaEventHandlerSeek3;
        voFmaEventHandlerSeek3.setInputEvents(6, 5);
        this.mEventHandlerCatchupSeek.setOutputEvent("CATCHUP_SEEK");
        this.mEventHandlerCatchupSeek.setBreakEvents("CATCHUP_PLAY", "CATCHUP_PAUSE");
        this.mEventHandlerCatchupSeek.setChannelRequired(true);
        this.mEventHandlerCatchupSeek.setProgramRequired(true);
        this.mEventHandlerCatchupSeek.setFromToTimeFields("from_program_offset", "program_offset");
        this.mEventHandlerCatchupSeek.setEnableHandleEvents(false);
        this.mEventHandlerCatchupSeek.setConvertFromToField("stream_offset", "program_offset");
        VoFmaEventHandlerSeek voFmaEventHandlerSeek4 = new VoFmaEventHandlerSeek(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerVodSeek = voFmaEventHandlerSeek4;
        voFmaEventHandlerSeek4.setInputEvents(6, 5);
        this.mEventHandlerVodSeek.setOutputEvent("VOD_SEEK");
        this.mEventHandlerVodSeek.setBreakEvents("VOD_PLAY", "VOD_PAUSE");
        this.mEventHandlerVodSeek.setChannelRequired(false);
        this.mEventHandlerVodSeek.setProgramRequired(false);
        this.mEventHandlerVodSeek.setVodInfoRequired(true);
        this.mEventHandlerVodSeek.setFromToTimeFields("from_content_offset", "content_offset");
        this.mEventHandlerVodSeek.setEnableHandleEvents(false);
        this.mEventHandlerVodSeek.setConvertFromToField("stream_offset", "content_offset");
        VoFmaEventHandlerSeek voFmaEventHandlerSeek5 = new VoFmaEventHandlerSeek(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerPreviewSeek = voFmaEventHandlerSeek5;
        voFmaEventHandlerSeek5.setInputEvents(6, 5);
        this.mEventHandlerPreviewSeek.setOutputEvent("PREVIEW_SEEK");
        this.mEventHandlerPreviewSeek.setBreakEvents("PREVIEW_PLAY", "PREVIEW_PAUSE");
        this.mEventHandlerPreviewSeek.setChannelRequired(false);
        this.mEventHandlerPreviewSeek.setProgramRequired(false);
        this.mEventHandlerPreviewSeek.setPreviewInfoRequired(true);
        this.mEventHandlerPreviewSeek.setFromToTimeFields("from_content_offset", "content_offset");
        this.mEventHandlerPreviewSeek.setEnableHandleEvents(false);
        this.mEventHandlerPreviewSeek.setConvertFromToField("stream_offset", "content_offset");
        VoFmaEventHandlerSeek voFmaEventHandlerSeek6 = new VoFmaEventHandlerSeek(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerNpvrSeek = voFmaEventHandlerSeek6;
        voFmaEventHandlerSeek6.setInputEvents(6, 5);
        this.mEventHandlerNpvrSeek.setOutputEvent("NPVR_SEEK");
        this.mEventHandlerNpvrSeek.setBreakEvents("NPVR_PLAY", "NPVR_PAUSE");
        this.mEventHandlerNpvrSeek.setChannelRequired(true);
        this.mEventHandlerNpvrSeek.setProgramRequired(true);
        this.mEventHandlerNpvrSeek.setFromToTimeFields("from_program_offset", "program_offset");
        this.mEventHandlerNpvrSeek.setEnableHandleEvents(false);
        this.mEventHandlerNpvrSeek.setConvertFromToField("stream_offset", "program_offset");
        VoFmaEventHandlerSeek voFmaEventHandlerSeek7 = new VoFmaEventHandlerSeek(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerDownloadSeek = voFmaEventHandlerSeek7;
        voFmaEventHandlerSeek7.setInputEvents(6, 5);
        this.mEventHandlerDownloadSeek.setOutputEvent("DOWNLOAD_SEEK");
        this.mEventHandlerDownloadSeek.setBreakEvents("DOWNLOAD_PLAY", "DOWNLOAD_PAUSE");
        this.mEventHandlerDownloadSeek.setChannelRequired(false);
        this.mEventHandlerDownloadSeek.setProgramRequired(false);
        this.mEventHandlerDownloadSeek.setVodInfoRequired(true);
        this.mEventHandlerDownloadSeek.setFromToTimeFields("from_content_offset", "content_offset");
        this.mEventHandlerDownloadSeek.setEnableHandleEvents(false);
        this.mEventHandlerDownloadSeek.setConvertFromToField("stream_offset", "content_offset");
        VoFmaEventHandlerThread voFmaEventHandlerThread = new VoFmaEventHandlerThread();
        this.mThread = voFmaEventHandlerThread;
        voFmaEventHandlerThread.start();
        return true;
    }

    protected void reloadConfiguration(String str) {
        Log.d(TAG, "ReloadConfiguration(\"" + str + "\")");
        this.mReloadConfigurationFlag = true;
        if (this.mDispatcher.getConfiguration(str) != VoFmaController.VoFmaReturnCode.Success) {
            Log.v(TAG, "Failed to reload configuration due to an error in parsing configuration");
        }
        ReloadConfigTimer();
    }

    protected void AutoReloadConfiguration(String str) {
        Log.d(TAG, "ReloadConfiguration(\"" + str + "\")");
        this.mReloadConfigurationFlag = true;
        if (this.mDispatcher.getConfiguration(str) != VoFmaController.VoFmaReturnCode.Success) {
            Log.v(TAG, "Failed to reload configuration due to an error in parsing configuration");
        }
    }

    protected VoFmaController.VoFmaReturnCode logEvent(VoFmaEvent voFmaEvent) {
        voFmaEvent.setCurrentTime();
        voFmaEvent.setCustomParameter(FmaDatabaseHelper.CONST_TIMESTAMP, voFmaEvent.getTime());
        voFmaEvent.setCustomParameter("operator_name", this.mConfiguration.mOperatorName);
        voFmaEvent.setCustomParameter(TtmlNode.ATTR_TTS_ORIGIN, "FMA");
        voFmaEvent.setCustomParameter("origin_version", this.mConfiguration.mFmaVersion);
        voFmaEvent.setCustomParameter(MimeTypes.BASE_TYPE_APPLICATION, this.mConfiguration.mApplicationName);
        voFmaEvent.setCustomParameter("application_version", this.mConfiguration.mApplicationVersion);
        voFmaEvent.setCustomParameter("device_category", this.mConfiguration.mDeviceCategory);
        voFmaEvent.setCustomParameter("origin_platform", "Android");
        voFmaEvent.setCustomParameter("device_id", this.mConfiguration.mDeviceID);
        String str = this.mAccountID;
        if (str != null) {
            voFmaEvent.setCustomParameter("account_id", str);
            String str2 = this.mProfileID;
            if (str2 != null) {
                voFmaEvent.setCustomParameter("profile_id", str2);
            }
        }
        try {
            synchronized (this.mQueueSync) {
                this.mQueue.add(voFmaEvent);
                this.mQueueSync.notify();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return VoFmaController.VoFmaReturnCode.Success;
    }

    @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
    public void run() {
        while (this.mContinue) {
            synchronized (this.mQueueSync) {
                VoFmaEvent voFmaEventPoll = this.mQueue.poll();
                if (voFmaEventPoll == null) {
                    try {
                        this.mQueueSync.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        routeEvent(voFmaEventPoll);
                    }
                }
                routeEvent(voFmaEventPoll);
            }
        }
    }

    protected void routeEvent(VoFmaEvent voFmaEvent) {
        Object playerInstance;
        String value = voFmaEvent.getValue(EVENT_INTERNAL_KEY);
        if (value != null) {
            String value2 = voFmaEvent.getValue("event_type");
            if (value2 != null) {
                if (value2.equals("LIVE_STOP") || value2.equals("LIVE_PAUSE")) {
                    if (value.equals(EVENT_INTERNAL_VALUE_FAKE_EVENT)) {
                        VoFmaEvent voFmaEvent2 = new VoFmaEvent(voFmaEvent);
                        this.mEventHandlerLivePauseResume.setEnableHandleEvents(this.mConfiguration.mLivePauseResume_StateConfirmationPeriod != -1);
                        this.mEventHandlerLivePauseResume.handleEvent(voFmaEvent2);
                    }
                    this.mEventHandlerLiveSeek.setEnableHandleEvents(this.mConfiguration.mLiveSeek_StateConfirmationPeriod != -1);
                    this.mEventHandlerLiveSeek.handleEvent(voFmaEvent);
                    return;
                }
                if (value2.equals("STARTOVER_STOP") || value2.equals("STARTOVER_PAUSE")) {
                    if (value.equals(EVENT_INTERNAL_VALUE_FAKE_EVENT)) {
                        VoFmaEvent voFmaEvent3 = new VoFmaEvent(voFmaEvent);
                        this.mEventHandlerStartoverPauseResume.setEnableHandleEvents(this.mConfiguration.mStartoverPauseResume_StateConfirmationPeriod != -1);
                        this.mEventHandlerStartoverPauseResume.handleEvent(voFmaEvent3);
                    }
                    this.mEventHandlerStartoverSeek.setEnableHandleEvents(this.mConfiguration.mStartoverSeek_StateConfirmationPeriod != -1);
                    this.mEventHandlerStartoverSeek.handleEvent(voFmaEvent);
                    return;
                }
                if (value2.equals("CATCHUP_STOP") || value2.equals("CATCHUP_PAUSE")) {
                    if (value.equals(EVENT_INTERNAL_VALUE_FAKE_EVENT)) {
                        VoFmaEvent voFmaEvent4 = new VoFmaEvent(voFmaEvent);
                        this.mEventHandlerCatchupPauseResume.setEnableHandleEvents(this.mConfiguration.mCatchupPauseResume_StateConfirmationPeriod != -1);
                        this.mEventHandlerCatchupPauseResume.handleEvent(voFmaEvent4);
                    }
                    this.mEventHandlerCatchupSeek.setEnableHandleEvents(this.mConfiguration.mCatchupSeek_StateConfirmationPeriod != -1);
                    this.mEventHandlerCatchupSeek.handleEvent(voFmaEvent);
                    return;
                }
                if (value2.equals("VOD_STOP") || value2.equals("VOD_PAUSE")) {
                    if (value.equals(EVENT_INTERNAL_VALUE_FAKE_EVENT)) {
                        VoFmaEvent voFmaEvent5 = new VoFmaEvent(voFmaEvent);
                        this.mEventHandlerVodPauseResume.setEnableHandleEvents(this.mConfiguration.mVodPauseResume_StateConfirmationPeriod != -1);
                        this.mEventHandlerVodPauseResume.handleEvent(voFmaEvent5);
                    }
                    this.mEventHandlerVodSeek.setEnableHandleEvents(this.mConfiguration.mVodSeek_StateConfirmationPeriod != -1);
                    this.mEventHandlerVodSeek.handleEvent(voFmaEvent);
                    return;
                }
                if (value2.equals("PREVIEW_STOP") || value2.equals("PREVIEW_PAUSE")) {
                    if (value.equals(EVENT_INTERNAL_VALUE_FAKE_EVENT)) {
                        VoFmaEvent voFmaEvent6 = new VoFmaEvent(voFmaEvent);
                        this.mEventHandlerPreviewPauseResume.setEnableHandleEvents(this.mConfiguration.mPreviewPauseResume_StateConfirmationPeriod != -1);
                        this.mEventHandlerPreviewPauseResume.handleEvent(voFmaEvent6);
                    }
                    this.mEventHandlerPreviewSeek.setEnableHandleEvents(true);
                    this.mEventHandlerPreviewSeek.handleEvent(voFmaEvent);
                    return;
                }
                if (value2.equals("NPVR_STOP") || value2.equals("NPVR_PAUSE")) {
                    if (value.equals(EVENT_INTERNAL_VALUE_FAKE_EVENT)) {
                        VoFmaEvent voFmaEvent7 = new VoFmaEvent(voFmaEvent);
                        this.mEventHandlerNpvrPauseResume.setEnableHandleEvents(this.mConfiguration.mNpvrPauseResume_StateConfirmationPeriod != -1);
                        this.mEventHandlerNpvrPauseResume.handleEvent(voFmaEvent7);
                    }
                    this.mEventHandlerNpvrSeek.setEnableHandleEvents(this.mConfiguration.mNpvrSeek_StateConfirmationPeriod != -1);
                    this.mEventHandlerNpvrSeek.handleEvent(voFmaEvent);
                    return;
                }
                if (value2.equals("DOWNLOAD_STOP") || value2.equals("DOWNLOAD_PAUSE")) {
                    if (value.equals(EVENT_INTERNAL_VALUE_FAKE_EVENT)) {
                        VoFmaEvent voFmaEvent8 = new VoFmaEvent(voFmaEvent);
                        this.mEventHandlerDownloadPauseResume.setEnableHandleEvents(this.mConfiguration.mDownloadPauseResume_StateConfirmationPeriod != -1);
                        this.mEventHandlerDownloadPauseResume.handleEvent(voFmaEvent8);
                    }
                    this.mEventHandlerDownloadSeek.setEnableHandleEvents(this.mConfiguration.mDownloadSeek_StateConfirmationPeriod != -1);
                    this.mEventHandlerDownloadSeek.handleEvent(voFmaEvent);
                    return;
                }
                if (value2.equals("LIVE_PLAY")) {
                    this.mEventHandlerLivePauseResume.handleEvent(voFmaEvent);
                    return;
                }
                if (value2.equals("STARTOVER_PLAY")) {
                    this.mEventHandlerStartoverPauseResume.handleEvent(voFmaEvent);
                    return;
                }
                if (value2.equals("CATCHUP_PLAY")) {
                    this.mEventHandlerCatchupPauseResume.handleEvent(voFmaEvent);
                    return;
                }
                if (value2.equals("VOD_PLAY")) {
                    this.mEventHandlerVodPauseResume.handleEvent(voFmaEvent);
                    return;
                }
                if (value2.equals("PREVIEW_PLAY")) {
                    this.mEventHandlerPreviewPauseResume.handleEvent(voFmaEvent);
                    return;
                } else if (value2.equals("NPVR_PLAY")) {
                    this.mEventHandlerNpvrPauseResume.handleEvent(voFmaEvent);
                    return;
                } else {
                    if (value2.equals("DOWNLOAD_PLAY")) {
                        this.mEventHandlerDownloadPauseResume.handleEvent(voFmaEvent);
                        return;
                    }
                    return;
                }
            }
            return;
        }
        if (voFmaEvent.getType() == 7) {
            this.analyticsChannelId = voFmaEvent.getValue("channel_id");
            this.analyticsChannelName = voFmaEvent.getValue("channel_name");
            this.mEventHandlerLivePlayStop.setChannel(voFmaEvent.getValue("channel_id"), voFmaEvent.getValue("channel_name"));
            this.mEventHandlerStartoverPlayStop.setChannel(voFmaEvent.getValue("channel_id"), voFmaEvent.getValue("channel_name"));
            this.mEventHandlerCatchupPlayStop.setChannel(voFmaEvent.getValue("channel_id"), voFmaEvent.getValue("channel_name"));
            this.mEventHandlerNpvrPlayStop.setChannel(voFmaEvent.getValue("channel_id"), voFmaEvent.getValue("channel_name"));
            this.mEventHandlerLivePauseResume.setChannel(voFmaEvent.getValue("channel_id"), voFmaEvent.getValue("channel_name"));
            this.mEventHandlerStartoverPauseResume.setChannel(voFmaEvent.getValue("channel_id"), voFmaEvent.getValue("channel_name"));
            this.mEventHandlerCatchupPauseResume.setChannel(voFmaEvent.getValue("channel_id"), voFmaEvent.getValue("channel_name"));
            this.mEventHandlerNpvrPauseResume.setChannel(voFmaEvent.getValue("channel_id"), voFmaEvent.getValue("channel_name"));
            this.mEventHandlerLiveSeek.setChannel(voFmaEvent.getValue("channel_id"), voFmaEvent.getValue("channel_name"));
            this.mEventHandlerStartoverSeek.setChannel(voFmaEvent.getValue("channel_id"), voFmaEvent.getValue("channel_name"));
            this.mEventHandlerCatchupSeek.setChannel(voFmaEvent.getValue("channel_id"), voFmaEvent.getValue("channel_name"));
            this.mEventHandlerNpvrSeek.setChannel(voFmaEvent.getValue("channel_id"), voFmaEvent.getValue("channel_name"));
            return;
        }
        if (voFmaEvent.getType() == 12) {
            this.mEventHandlerStartoverPlayStop.setProgram(voFmaEvent.getValue("program_id"), voFmaEvent.getValue("program_name"));
            this.mEventHandlerCatchupPlayStop.setProgram(voFmaEvent.getValue("program_id"), voFmaEvent.getValue("program_name"));
            this.mEventHandlerNpvrPlayStop.setProgram(voFmaEvent.getValue("program_id"), voFmaEvent.getValue("program_name"));
            this.mEventHandlerStartoverPauseResume.setProgram(voFmaEvent.getValue("program_id"), voFmaEvent.getValue("program_name"));
            this.mEventHandlerCatchupPauseResume.setProgram(voFmaEvent.getValue("program_id"), voFmaEvent.getValue("program_name"));
            this.mEventHandlerNpvrPauseResume.setProgram(voFmaEvent.getValue("program_id"), voFmaEvent.getValue("program_name"));
            this.mEventHandlerStartoverSeek.setProgram(voFmaEvent.getValue("program_id"), voFmaEvent.getValue("program_name"));
            this.mEventHandlerCatchupSeek.setProgram(voFmaEvent.getValue("program_id"), voFmaEvent.getValue("program_name"));
            this.mEventHandlerNpvrSeek.setProgram(voFmaEvent.getValue("program_id"), voFmaEvent.getValue("program_name"));
            return;
        }
        if (voFmaEvent.getType() == 9 || voFmaEvent.getType() == 8 || voFmaEvent.getType() == 10) {
            if (voFmaEvent.keyExists("asset_id")) {
                this.analyticsContentId = voFmaEvent.getValue("asset_id");
                this.mEventHandlerVodPlayStop.setVodAsset(voFmaEvent.getValue("asset_id"));
                this.mEventHandlerDownloadPlayStop.setVodAsset(voFmaEvent.getValue("asset_id"));
                this.mEventHandlerVodPauseResume.setVodAsset(voFmaEvent.getValue("asset_id"));
                this.mEventHandlerDownloadPauseResume.setVodAsset(voFmaEvent.getValue("asset_id"));
                this.mEventHandlerVodSeek.setVodAsset(voFmaEvent.getValue("asset_id"));
                this.mEventHandlerDownloadSeek.setVodAsset(voFmaEvent.getValue("asset_id"));
            }
            if (voFmaEvent.keyExists("provider_id")) {
                this.mEventHandlerVodPlayStop.setVodProvider(voFmaEvent.getValue("provider_id"));
                this.mEventHandlerDownloadPlayStop.setVodProvider(voFmaEvent.getValue("provider_id"));
                this.mEventHandlerVodPauseResume.setVodProvider(voFmaEvent.getValue("provider_id"));
                this.mEventHandlerDownloadPauseResume.setVodProvider(voFmaEvent.getValue("provider_id"));
                this.mEventHandlerVodSeek.setVodProvider(voFmaEvent.getValue("provider_id"));
                this.mEventHandlerDownloadSeek.setVodProvider(voFmaEvent.getValue("provider_id"));
            }
            if (voFmaEvent.getType() == 10) {
                this.mEventHandlerVodPlayStop.setVodEpisode(null);
                this.mEventHandlerDownloadPlayStop.setVodEpisode(null);
                this.mEventHandlerVodPauseResume.setVodEpisode(null);
                this.mEventHandlerDownloadPauseResume.setVodEpisode(null);
                this.mEventHandlerVodSeek.setVodEpisode(null);
                this.mEventHandlerDownloadSeek.setVodEpisode(null);
                return;
            }
            if (voFmaEvent.keyExists("episode_id")) {
                this.mEventHandlerVodPlayStop.setVodEpisode(voFmaEvent.getValue("episode_id"));
                this.mEventHandlerDownloadPlayStop.setVodEpisode(voFmaEvent.getValue("episode_id"));
                this.mEventHandlerVodPauseResume.setVodEpisode(voFmaEvent.getValue("episode_id"));
                this.mEventHandlerDownloadPauseResume.setVodEpisode(voFmaEvent.getValue("episode_id"));
                this.mEventHandlerVodSeek.setVodEpisode(voFmaEvent.getValue("episode_id"));
                this.mEventHandlerDownloadSeek.setVodEpisode(voFmaEvent.getValue("episode_id"));
                return;
            }
            return;
        }
        if (voFmaEvent.getType() == 11) {
            if (voFmaEvent.keyExists("asset_id")) {
                this.analyticsContentId = voFmaEvent.getValue("asset_id");
                this.mEventHandlerPreviewPlayStop.setPreviewAsset(voFmaEvent.getValue("asset_id"));
                this.mEventHandlerPreviewPauseResume.setPreviewAsset(voFmaEvent.getValue("asset_id"));
                this.mEventHandlerPreviewSeek.setPreviewAsset(voFmaEvent.getValue("asset_id"));
            }
            if (voFmaEvent.keyExists("name")) {
                this.analyticsContentName = voFmaEvent.getValue("name");
                this.mEventHandlerPreviewPlayStop.setPreviewName(voFmaEvent.getValue("name"));
                this.mEventHandlerPreviewPauseResume.setPreviewName(voFmaEvent.getValue("name"));
                this.mEventHandlerPreviewSeek.setPreviewName(voFmaEvent.getValue("name"));
                return;
            }
            return;
        }
        if (voFmaEvent.getType() == 1) {
            if (voFmaEvent.keyExists("content_type")) {
                if (voFmaEvent.getValue("content_type").compareTo(VOPlaybackSessionReport.SESSION_TYPE_LIVE) == 0) {
                    this.mNextContentType = ContentType.LIVE;
                } else if (voFmaEvent.getValue("content_type").compareTo("STARTOVER") == 0) {
                    this.mNextContentType = ContentType.STARTOVER;
                } else if (voFmaEvent.getValue("content_type").compareTo("CATCHUP") == 0) {
                    this.mNextContentType = ContentType.CATCHUP;
                } else if (voFmaEvent.getValue("content_type").compareTo(VOPlaybackSessionReport.SESSION_TYPE_VOD) == 0) {
                    this.mNextContentType = ContentType.VOD;
                } else if (voFmaEvent.getValue("content_type").compareTo("PREVIEW") == 0) {
                    this.mNextContentType = ContentType.PREVIEW;
                } else if (voFmaEvent.getValue("content_type").compareTo("NPVR") == 0) {
                    this.mNextContentType = ContentType.NPVR;
                } else if (voFmaEvent.getValue("content_type").compareTo("DOWNLOAD") == 0) {
                    this.mNextContentType = ContentType.DOWNLOAD;
                } else {
                    this.mNextContentType = ContentType.NONE;
                }
            }
            Class clsVoPlayerClass = voPlayerClass();
            if (clsVoPlayerClass == null || (playerInstance = getPlayerInstance(clsVoPlayerClass)) == null) {
                return;
            }
            try {
                Method declaredMethod = clsVoPlayerClass.getDeclaredMethod("setAnalyticsMetadata", String.class, String.class);
                if (this.analyticsChannelId != null) {
                    declaredMethod.invoke(playerInstance, "CHANNEL_ID", this.analyticsChannelId);
                }
                if (this.analyticsChannelName != null) {
                    declaredMethod.invoke(playerInstance, "CHANNEL_NAME", this.analyticsChannelName);
                }
                if (this.analyticsContentId != null) {
                    declaredMethod.invoke(playerInstance, "CONTENT_ID", this.analyticsContentId);
                }
                if (this.analyticsContentName != null) {
                    declaredMethod.invoke(playerInstance, "CONTENT_NAME", this.analyticsContentName);
                }
                declaredMethod.invoke(playerInstance, "OPERATOR_NAME", this.mConfiguration.mOperatorName);
                declaredMethod.invoke(playerInstance, "ACCOUNT_ID", this.mAccountID);
                declaredMethod.invoke(playerInstance, "PROFILE_ID", this.mProfileID);
                declaredMethod.invoke(playerInstance, "APPLICATION_NAME", this.mConfiguration.mApplicationName);
                declaredMethod.invoke(playerInstance, "APPLICATION_VERSION", this.mConfiguration.mApplicationVersion);
                return;
            } catch (IllegalAccessException e) {
                Log.e(TAG, "Error occured while calling setAnalyticsMetadatas api");
                e.printStackTrace();
                return;
            } catch (NoSuchMethodException e2) {
                Log.e(TAG, "Set Metadata api does not exist in player class ");
                e2.printStackTrace();
                return;
            } catch (InvocationTargetException e3) {
                Log.e(TAG, "Error occured while calling setAnalyticsMetadatas api");
                e3.printStackTrace();
                return;
            }
        }
        if (voFmaEvent.getType() == 3) {
            ContentType contentType = this.mNextContentType;
            this.mContentType = contentType;
            if (contentType == ContentType.LIVE) {
                this.mEventHandlerLivePlayStop.handleEvent(voFmaEvent);
                this.mEventHandlerLivePauseResume.setEnableHandleEvents(this.mConfiguration.mLivePauseResume_StateConfirmationPeriod != -1);
                this.mEventHandlerLiveSeek.setEnableHandleEvents(this.mConfiguration.mLiveSeek_StateConfirmationPeriod != -1);
                return;
            }
            if (this.mContentType == ContentType.STARTOVER) {
                this.mEventHandlerStartoverPlayStop.handleEvent(voFmaEvent);
                this.mEventHandlerStartoverPauseResume.setEnableHandleEvents(this.mConfiguration.mStartoverPauseResume_StateConfirmationPeriod != -1);
                this.mEventHandlerStartoverSeek.setEnableHandleEvents(this.mConfiguration.mStartoverSeek_StateConfirmationPeriod != -1);
                return;
            }
            if (this.mContentType == ContentType.CATCHUP) {
                this.mEventHandlerCatchupPlayStop.handleEvent(voFmaEvent);
                this.mEventHandlerCatchupPauseResume.setEnableHandleEvents(this.mConfiguration.mCatchupPauseResume_StateConfirmationPeriod != -1);
                this.mEventHandlerCatchupSeek.setEnableHandleEvents(this.mConfiguration.mCatchupSeek_StateConfirmationPeriod != -1);
                return;
            }
            if (this.mContentType == ContentType.VOD) {
                this.mEventHandlerVodPlayStop.handleEvent(voFmaEvent);
                this.mEventHandlerVodPauseResume.setEnableHandleEvents(this.mConfiguration.mVodPauseResume_StateConfirmationPeriod != -1);
                this.mEventHandlerVodSeek.setEnableHandleEvents(this.mConfiguration.mVodSeek_StateConfirmationPeriod != -1);
                return;
            }
            if (this.mContentType == ContentType.PREVIEW) {
                this.mEventHandlerPreviewPlayStop.handleEvent(voFmaEvent);
                this.mEventHandlerPreviewPauseResume.setEnableHandleEvents(this.mConfiguration.mPreviewPauseResume_StateConfirmationPeriod != -1);
                this.mEventHandlerPreviewSeek.setEnableHandleEvents(this.mConfiguration.mPreviewSeek_StateConfirmationPeriod != -1);
                return;
            } else if (this.mContentType == ContentType.NPVR) {
                this.mEventHandlerNpvrPlayStop.handleEvent(voFmaEvent);
                this.mEventHandlerNpvrPauseResume.setEnableHandleEvents(this.mConfiguration.mNpvrPauseResume_StateConfirmationPeriod != -1);
                this.mEventHandlerNpvrSeek.setEnableHandleEvents(this.mConfiguration.mNpvrSeek_StateConfirmationPeriod != -1);
                return;
            } else {
                if (this.mContentType == ContentType.DOWNLOAD) {
                    this.mEventHandlerDownloadPlayStop.handleEvent(voFmaEvent);
                    this.mEventHandlerDownloadPauseResume.setEnableHandleEvents(this.mConfiguration.mDownloadPauseResume_StateConfirmationPeriod != -1);
                    this.mEventHandlerDownloadSeek.setEnableHandleEvents(this.mConfiguration.mDownloadSeek_StateConfirmationPeriod != -1);
                    return;
                }
                return;
            }
        }
        if (voFmaEvent.getType() == 13) {
            if (this.mContentType == ContentType.LIVE) {
                this.mEventHandlerLivePlayStop.handleEvent(voFmaEvent);
                this.mEventHandlerLivePauseResume.setEnableHandleEvents(false);
                this.mEventHandlerLiveSeek.setEnableHandleEvents(false);
                this.mEventHandlerLiveSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.STARTOVER) {
                this.mEventHandlerStartoverPlayStop.handleEvent(voFmaEvent);
                this.mEventHandlerStartoverPauseResume.setEnableHandleEvents(false);
                this.mEventHandlerStartoverSeek.setEnableHandleEvents(false);
                this.mEventHandlerStartoverSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.CATCHUP) {
                this.mEventHandlerCatchupPlayStop.handleEvent(voFmaEvent);
                this.mEventHandlerCatchupPauseResume.setEnableHandleEvents(false);
                this.mEventHandlerCatchupSeek.setEnableHandleEvents(false);
                this.mEventHandlerCatchupSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.VOD) {
                this.mEventHandlerVodPlayStop.handleEvent(voFmaEvent);
                this.mEventHandlerVodPauseResume.setEnableHandleEvents(false);
                this.mEventHandlerVodSeek.setEnableHandleEvents(false);
                this.mEventHandlerVodSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.PREVIEW) {
                this.mEventHandlerPreviewPlayStop.handleEvent(voFmaEvent);
                this.mEventHandlerPreviewPauseResume.setEnableHandleEvents(false);
                this.mEventHandlerPreviewSeek.setEnableHandleEvents(false);
                this.mEventHandlerPreviewSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.NPVR) {
                this.mEventHandlerNpvrPlayStop.handleEvent(voFmaEvent);
                this.mEventHandlerNpvrPauseResume.setEnableHandleEvents(false);
                this.mEventHandlerNpvrSeek.setEnableHandleEvents(false);
                this.mEventHandlerNpvrSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.DOWNLOAD) {
                this.mEventHandlerDownloadPlayStop.handleEvent(voFmaEvent);
                this.mEventHandlerDownloadPauseResume.setEnableHandleEvents(false);
                this.mEventHandlerDownloadSeek.setEnableHandleEvents(false);
                this.mEventHandlerDownloadSeek.setInPauseState(false);
                return;
            }
            return;
        }
        if (voFmaEvent.getType() == 2) {
            if (this.mContentType == ContentType.LIVE) {
                this.mEventHandlerLivePauseResume.handleEvent(voFmaEvent);
                this.mEventHandlerLiveSeek.setInPauseState(true);
                return;
            }
            if (this.mContentType == ContentType.STARTOVER) {
                this.mEventHandlerStartoverPauseResume.handleEvent(voFmaEvent);
                this.mEventHandlerStartoverSeek.setInPauseState(true);
                return;
            }
            if (this.mContentType == ContentType.CATCHUP) {
                this.mEventHandlerCatchupPauseResume.handleEvent(voFmaEvent);
                this.mEventHandlerCatchupSeek.setInPauseState(true);
                return;
            }
            if (this.mContentType == ContentType.VOD) {
                this.mEventHandlerVodPauseResume.handleEvent(voFmaEvent);
                this.mEventHandlerVodSeek.setInPauseState(true);
                return;
            }
            if (this.mContentType == ContentType.PREVIEW) {
                this.mEventHandlerPreviewPauseResume.handleEvent(voFmaEvent);
                this.mEventHandlerPreviewSeek.setInPauseState(true);
                return;
            } else if (this.mContentType == ContentType.NPVR) {
                this.mEventHandlerNpvrPauseResume.handleEvent(voFmaEvent);
                this.mEventHandlerNpvrSeek.setInPauseState(true);
                return;
            } else {
                if (this.mContentType == ContentType.DOWNLOAD) {
                    this.mEventHandlerDownloadPauseResume.handleEvent(voFmaEvent);
                    this.mEventHandlerDownloadSeek.setInPauseState(true);
                    return;
                }
                return;
            }
        }
        if (voFmaEvent.getType() == 4) {
            if (this.mContentType == ContentType.LIVE) {
                this.mEventHandlerLivePauseResume.handleEvent(voFmaEvent);
                this.mEventHandlerLiveSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.STARTOVER) {
                this.mEventHandlerStartoverPauseResume.handleEvent(voFmaEvent);
                this.mEventHandlerStartoverSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.CATCHUP) {
                this.mEventHandlerCatchupPauseResume.handleEvent(voFmaEvent);
                this.mEventHandlerCatchupSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.VOD) {
                this.mEventHandlerVodPauseResume.handleEvent(voFmaEvent);
                this.mEventHandlerVodSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.PREVIEW) {
                this.mEventHandlerPreviewPauseResume.handleEvent(voFmaEvent);
                this.mEventHandlerPreviewSeek.setInPauseState(false);
                return;
            } else if (this.mContentType == ContentType.NPVR) {
                this.mEventHandlerNpvrPauseResume.handleEvent(voFmaEvent);
                this.mEventHandlerNpvrSeek.setInPauseState(false);
                return;
            } else {
                if (this.mContentType == ContentType.DOWNLOAD) {
                    this.mEventHandlerDownloadPauseResume.handleEvent(voFmaEvent);
                    this.mEventHandlerDownloadSeek.setInPauseState(false);
                    return;
                }
                return;
            }
        }
        if (voFmaEvent.getType() == 6) {
            if (this.mContentType == ContentType.LIVE) {
                this.mEventHandlerLiveSeek.handleEvent(voFmaEvent);
                return;
            }
            if (this.mContentType == ContentType.STARTOVER) {
                this.mEventHandlerStartoverSeek.handleEvent(voFmaEvent);
                return;
            }
            if (this.mContentType == ContentType.CATCHUP) {
                this.mEventHandlerCatchupSeek.handleEvent(voFmaEvent);
                return;
            }
            if (this.mContentType == ContentType.VOD) {
                this.mEventHandlerVodSeek.handleEvent(voFmaEvent);
                return;
            }
            if (this.mContentType == ContentType.PREVIEW) {
                this.mEventHandlerPreviewSeek.handleEvent(voFmaEvent);
                return;
            } else if (this.mContentType == ContentType.NPVR) {
                this.mEventHandlerNpvrSeek.handleEvent(voFmaEvent);
                return;
            } else {
                if (this.mContentType == ContentType.DOWNLOAD) {
                    this.mEventHandlerDownloadSeek.handleEvent(voFmaEvent);
                    return;
                }
                return;
            }
        }
        if (voFmaEvent.getType() == 5) {
            if (this.mContentType == ContentType.LIVE) {
                this.mEventHandlerLiveSeek.handleEvent(voFmaEvent);
                return;
            }
            if (this.mContentType == ContentType.STARTOVER) {
                this.mEventHandlerStartoverSeek.handleEvent(voFmaEvent);
                return;
            }
            if (this.mContentType == ContentType.CATCHUP) {
                this.mEventHandlerCatchupSeek.handleEvent(voFmaEvent);
                return;
            }
            if (this.mContentType == ContentType.VOD) {
                this.mEventHandlerVodSeek.handleEvent(voFmaEvent);
                return;
            }
            if (this.mContentType == ContentType.PREVIEW) {
                this.mEventHandlerPreviewSeek.handleEvent(voFmaEvent);
                return;
            } else if (this.mContentType == ContentType.NPVR) {
                this.mEventHandlerNpvrSeek.handleEvent(voFmaEvent);
                return;
            } else {
                if (this.mContentType == ContentType.DOWNLOAD) {
                    this.mEventHandlerDownloadSeek.handleEvent(voFmaEvent);
                    return;
                }
                return;
            }
        }
        this.mEventHandlerProxy.handleEvent(voFmaEvent);
    }

    protected void implStop() {
        this.mContinue = true;
        synchronized (this.mQueueSync) {
            this.mQueueSync.notify();
        }
        this.mEventHandlerProxy.stop();
        this.mEventHandlerLivePlayStop.stop();
        this.mEventHandlerStartoverPlayStop.stop();
        this.mEventHandlerCatchupPlayStop.stop();
        this.mEventHandlerVodPlayStop.stop();
        this.mEventHandlerPreviewPlayStop.stop();
        this.mEventHandlerNpvrPlayStop.stop();
        this.mEventHandlerDownloadPlayStop.stop();
        this.mEventHandlerLivePauseResume.stop();
        this.mEventHandlerStartoverPauseResume.stop();
        this.mEventHandlerCatchupPauseResume.stop();
        this.mEventHandlerVodPauseResume.stop();
        this.mEventHandlerPreviewPauseResume.stop();
        this.mEventHandlerNpvrPauseResume.stop();
        this.mEventHandlerDownloadPauseResume.stop();
        this.mEventHandlerLiveSeek.stop();
        this.mEventHandlerStartoverSeek.stop();
        this.mEventHandlerCatchupSeek.stop();
        this.mEventHandlerVodSeek.stop();
        this.mEventHandlerPreviewSeek.stop();
        this.mEventHandlerNpvrSeek.stop();
        this.mEventHandlerDownloadSeek.stop();
        this.mDispatcher.stop();
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaDispatcherListener
    public void onConfigurationReceived(VoFmaConfiguration voFmaConfiguration) {
        synchronized (this.mConfigurationSync) {
            this.mConfiguration.mOperatorName = voFmaConfiguration.mOperatorName;
            this.mConfiguration.mReloadConfigurationPeriod = voFmaConfiguration.mReloadConfigurationPeriod;
            this.mConfiguration.mParsingSuccess = voFmaConfiguration.mParsingSuccess;
            this.mConfiguration.mLivePlayStop_StateConfirmationPeriod = voFmaConfiguration.mLivePlayStop_StateConfirmationPeriod;
            this.mConfiguration.mLivePauseResume_StateConfirmationPeriod = voFmaConfiguration.mLivePauseResume_StateConfirmationPeriod;
            this.mConfiguration.mLiveSeek_StateConfirmationPeriod = voFmaConfiguration.mLiveSeek_StateConfirmationPeriod;
            this.mConfiguration.mStartoverPlayStop_StateConfirmationPeriod = voFmaConfiguration.mStartoverPlayStop_StateConfirmationPeriod;
            this.mConfiguration.mStartoverPauseResume_StateConfirmationPeriod = voFmaConfiguration.mStartoverPauseResume_StateConfirmationPeriod;
            this.mConfiguration.mStartoverSeek_StateConfirmationPeriod = voFmaConfiguration.mStartoverSeek_StateConfirmationPeriod;
            this.mConfiguration.mCatchupPlayStop_StateConfirmationPeriod = voFmaConfiguration.mCatchupPlayStop_StateConfirmationPeriod;
            this.mConfiguration.mCatchupPauseResume_StateConfirmationPeriod = voFmaConfiguration.mCatchupPauseResume_StateConfirmationPeriod;
            this.mConfiguration.mCatchupSeek_StateConfirmationPeriod = voFmaConfiguration.mCatchupSeek_StateConfirmationPeriod;
            this.mConfiguration.mVodPlayStop_StateConfirmationPeriod = voFmaConfiguration.mVodPlayStop_StateConfirmationPeriod;
            this.mConfiguration.mVodPauseResume_StateConfirmationPeriod = voFmaConfiguration.mVodPauseResume_StateConfirmationPeriod;
            this.mConfiguration.mVodSeek_StateConfirmationPeriod = voFmaConfiguration.mVodSeek_StateConfirmationPeriod;
            this.mConfiguration.mPreviewPlayStop_StateConfirmationPeriod = voFmaConfiguration.mPreviewPlayStop_StateConfirmationPeriod;
            this.mConfiguration.mPreviewPauseResume_StateConfirmationPeriod = voFmaConfiguration.mPreviewPauseResume_StateConfirmationPeriod;
            this.mConfiguration.mPreviewSeek_StateConfirmationPeriod = voFmaConfiguration.mPreviewSeek_StateConfirmationPeriod;
            this.mConfiguration.mNpvrPlayStop_StateConfirmationPeriod = voFmaConfiguration.mNpvrPlayStop_StateConfirmationPeriod;
            this.mConfiguration.mNpvrPauseResume_StateConfirmationPeriod = voFmaConfiguration.mNpvrPauseResume_StateConfirmationPeriod;
            this.mConfiguration.mNpvrSeek_StateConfirmationPeriod = voFmaConfiguration.mNpvrSeek_StateConfirmationPeriod;
            this.mConfiguration.mDownloadPlayStop_StateConfirmationPeriod = voFmaConfiguration.mDownloadPlayStop_StateConfirmationPeriod;
            this.mConfiguration.mDownloadPauseResume_StateConfirmationPeriod = voFmaConfiguration.mDownloadPauseResume_StateConfirmationPeriod;
            this.mConfiguration.mDownloadSeek_StateConfirmationPeriod = voFmaConfiguration.mDownloadSeek_StateConfirmationPeriod;
            this.mConfiguration.mMappingEventTypes = voFmaConfiguration.mMappingEventTypes;
            this.mConfiguration.mMappingEventUrls = voFmaConfiguration.mMappingEventUrls;
            this.mConfiguration.mEventFieldValidateNames = voFmaConfiguration.mEventFieldValidateNames;
            this.mConfiguration.mEventFieldsValidatePerEvent = voFmaConfiguration.mEventFieldsValidatePerEvent;
            this.mConfiguration.mLiveSeek_SendDuringPause = voFmaConfiguration.mLiveSeek_SendDuringPause;
            this.mConfiguration.mStartoverSeek_SendDuringPause = voFmaConfiguration.mStartoverSeek_SendDuringPause;
            this.mConfiguration.mCatchupSeek_SendDuringPause = voFmaConfiguration.mCatchupSeek_SendDuringPause;
            this.mConfiguration.mVodSeek_SendDuringPause = voFmaConfiguration.mVodSeek_SendDuringPause;
            this.mConfiguration.mPreviewSeek_SendDuringPause = voFmaConfiguration.mPreviewSeek_SendDuringPause;
            this.mConfiguration.mNpvrSeek_SendDuringPause = voFmaConfiguration.mNpvrSeek_SendDuringPause;
            this.mConfiguration.mDownloadSeek_SendDuringPause = voFmaConfiguration.mDownloadSeek_SendDuringPause;
            this.mConfiguration.mFlushPeriod = voFmaConfiguration.mFlushPeriod;
            this.mConfiguration.mMaxStackSize = voFmaConfiguration.mMaxStackSize;
            this.mConfiguration.mLifeSpan = voFmaConfiguration.mLifeSpan;
            this.mConfiguration.mServiceReportUrl = voFmaConfiguration.mServiceReportUrl;
            this.mConfiguration.mServicePeriodOn = voFmaConfiguration.mServicePeriodOn;
            this.mConfiguration.mServicePeriodOff_MaxPeriod = voFmaConfiguration.mServicePeriodOff_MaxPeriod;
            this.mConfiguration.mServicePeriodOff_MinPeriod = voFmaConfiguration.mServicePeriodOff_MinPeriod;
            this.mConfiguration.mMonitoringServiceRequired = voFmaConfiguration.mMonitoringServiceRequired;
            this.mConfiguration.mNetworkReportUrl = voFmaConfiguration.mNetworkReportUrl;
            this.mConfiguration.mNetworkPeriodOn = voFmaConfiguration.mNetworkPeriodOn;
            this.mConfiguration.mCheckTestUrl = voFmaConfiguration.mCheckTestUrl;
            this.mConfiguration.mNetworkPeriodOff_MaxPeriod = voFmaConfiguration.mNetworkPeriodOff_MaxPeriod;
            this.mConfiguration.mNetworkPeriodOff_MinPeriod = voFmaConfiguration.mNetworkPeriodOff_MinPeriod;
            this.mConfiguration.mMonitoringNetworkRequired = voFmaConfiguration.mMonitoringNetworkRequired;
            this.mConfiguration.mMonitoringBootTimeRequired = voFmaConfiguration.mMonitoringBootTimeRequired;
            this.mConfiguration.mMonitoringLoggingRequired = voFmaConfiguration.mMonitoringLoggingRequired;
            this.mConfiguration.mBootReportUrl = voFmaConfiguration.mBootReportUrl;
            this.mConfiguration.mLoggingReportUrl = voFmaConfiguration.mLoggingReportUrl;
            this.mConfiguration.mMonitoredUrlsList = voFmaConfiguration.mMonitoredUrlsList;
            this.mDispatcher.setConfForMonitoring(this.mConfiguration);
            this.mConfiguration.calcMaxconfirmationPeriod();
        }
        if (this.mDispatcher.mParsingRes == VoFmaController.VoFmaReturnCode.Success || this.mDispatcher.mParsingRes == VoFmaController.VoFmaReturnCode.AlreadyInitialized) {
            if (!this.mReloadConfigurationFlag) {
                new Handler().postDelayed(new Runnable() { // from class: com.viaccessorca.analytics.fma.VoFmaBusinessLogic.1
                    @Override // java.lang.Runnable
                    public void run() {
                        VoFmaBusinessLogic.this.mListener.onInitBusinessLogicCompleted();
                        VoFmaBusinessLogic.this.ReloadConfigTimer();
                    }
                }, 0L);
                return;
            } else {
                this.mReloadConfigurationFlag = false;
                new Handler().postDelayed(new Runnable() { // from class: com.viaccessorca.analytics.fma.VoFmaBusinessLogic.2
                    @Override // java.lang.Runnable
                    public void run() {
                        VoFmaBusinessLogic.this.mListener.onConfigurationBusinessLogicReloaded();
                    }
                }, 0L);
                return;
            }
        }
        new Handler().postDelayed(new Runnable() { // from class: com.viaccessorca.analytics.fma.VoFmaBusinessLogic.3
            @Override // java.lang.Runnable
            public void run() {
                VoFmaBusinessLogic.this.mListener.onInitBusinessLogicFailed();
            }
        }, 0L);
    }

    public Class voPlayerClass() {
        try {
            Class<?> cls = Class.forName(VOPLAYER_CLASS_PATH);
            Log.v(TAG, "VOPlayer class found, Both FMA and VOPlayer integrated in this application ");
            return cls;
        } catch (Exception unused) {
            Log.v(TAG, "VOPlayer class not found, No pre integration required");
            return null;
        }
    }

    public Object getPlayerInstance(Class cls) {
        try {
            return cls.getDeclaredMethod(VOPLAYER_GET_INSTANCE_METHOD, new Class[0]).invoke(cls, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Error occured while calling getPlayerInstance api");
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e2) {
            Log.e(TAG, "getPlayerInstance static api does not exist in player class ");
            e2.printStackTrace();
            return null;
        } catch (SecurityException e3) {
            Log.e(TAG, "Error occured while calling getPlayerInstance api");
            e3.printStackTrace();
            return null;
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "Error occured while calling getPlayerInstance api");
            e4.printStackTrace();
            return null;
        }
    }

    public Map<String, String> getAnalyticsData() {
        HashMap map = new HashMap();
        this.analyticsMetadatas = map;
        String str = this.analyticsChannelId;
        if (str != null) {
            map.put("CHANNEL_ID", str);
        }
        String str2 = this.analyticsChannelName;
        if (str2 != null) {
            this.analyticsMetadatas.put("CHANNEL_NAME", str2);
        }
        String str3 = this.analyticsContentId;
        if (str3 != null) {
            this.analyticsMetadatas.put("CONTENT_ID", str3);
        }
        String str4 = this.analyticsContentName;
        if (str4 != null) {
            this.analyticsMetadatas.put("CONTENT_NAME", str4);
        }
        this.analyticsMetadatas.put("OPERATOR_NAME", this.mConfiguration.mOperatorName);
        this.analyticsMetadatas.put("ACCOUNT_ID", this.mAccountID);
        this.analyticsMetadatas.put("PROFILE_ID", this.mProfileID);
        this.analyticsMetadatas.put("APPLICATION_NAME", this.mConfiguration.mApplicationName);
        this.analyticsMetadatas.put("APPLICATION_VERSION", this.mConfiguration.mApplicationVersion);
        return this.analyticsMetadatas;
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaDispatcherListener
    public void onError() {
        if (!this.mReloadConfigurationFlag) {
            new Handler().postDelayed(new Runnable() { // from class: com.viaccessorca.analytics.fma.VoFmaBusinessLogic.4
                @Override // java.lang.Runnable
                public void run() {
                    VoFmaBusinessLogic.this.mListener.onInitBusinessLogicFailed();
                }
            }, 0L);
        } else {
            this.mReloadConfigurationFlag = false;
            new Handler().postDelayed(new Runnable() { // from class: com.viaccessorca.analytics.fma.VoFmaBusinessLogic.5
                @Override // java.lang.Runnable
                public void run() {
                    VoFmaBusinessLogic.this.mListener.onConfigurationBusinessLogicReloadedFailed();
                }
            }, 0L);
        }
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaDispatcherListener
    public void onEventSent(VoFmaEvent voFmaEvent) {
        voFmaEvent.setCustomParameter(EVENT_INTERNAL_KEY, EVENT_INTERNAL_VALUE_ALREADY_SENT);
        try {
            synchronized (this.mQueueSync) {
                try {
                    this.mQueue.add(voFmaEvent);
                    this.mQueueSync.notify();
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaEventHandlerListener
    public void onInternalEvent(VoFmaEvent voFmaEvent) {
        voFmaEvent.setCustomParameter(EVENT_INTERNAL_KEY, EVENT_INTERNAL_VALUE_FAKE_EVENT);
        try {
            synchronized (this.mQueueSync) {
                try {
                    this.mQueue.add(voFmaEvent);
                    this.mQueueSync.notify();
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ReloadConfigTimer() {
        Log.v(TAG, "Reloading configuration timer");
        Timer timer = this.mReloadConfigurationTimer;
        if (timer != null) {
            timer.cancel();
        }
        Timer timer2 = new Timer();
        this.mReloadConfigurationTimer = timer2;
        timer2.schedule(new TimerTask() { // from class: com.viaccessorca.analytics.fma.VoFmaBusinessLogic.6
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                VoFmaBusinessLogic voFmaBusinessLogic = VoFmaBusinessLogic.this;
                voFmaBusinessLogic.AutoReloadConfiguration(voFmaBusinessLogic.mConfigUrl);
            }
        }, this.mConfiguration.mReloadConfigurationPeriod * 1000, this.mConfiguration.mReloadConfigurationPeriod * 1000);
    }

    protected VoFmaController.VoFmaReturnCode logCrEvent(VoFmaEvent.VOPlayerSeverity vOPlayerSeverity, String str, String str2) {
        this.mDispatcher.logCrEvent(vOPlayerSeverity, str, str2);
        return VoFmaController.VoFmaReturnCode.Success;
    }
}
