package com.viaccessorca.analytics.dca;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.MimeTypes;
import com.viaccessorca.voplayer.VOPlaybackSessionReport;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes2.dex */
public class VoDcaBusinessLogic extends HandlerThread implements VoDcaDispatcherListener, VoDcaEventHandlerListener {
    protected static final String EVENT_INTERNAL_KEY = "_intr";
    protected static final String EVENT_INTERNAL_VALUE_AFTER = "After";
    protected static final String EVENT_INTERNAL_VALUE_ALREADY_SENT = "AlreadySent";
    protected static final String EVENT_INTERNAL_VALUE_BEFORE = "Before";
    protected static final String EVENT_INTERNAL_VALUE_FAKE_EVENT = "FakeEvent";
    protected static final String EVENT_INTERNAL_VALUE_REMOVE_P_S = "RemovePS";
    protected static final String EVENT_INTERNAL_VALUE_REMOVE_S_P = "RemoveSP";
    protected static final String EVENT_INTERNAL_VALUE_SEQUENCE = "Sequence";
    private static final int QUEUE_CAPACITY = 512;
    private static final String TAG = "VO_DCA";
    protected static final String VOPLAYER_CLASS_PATH = "com.viaccessorca.voplayer.VOPlayer";
    protected static final String VOPLAYER_CLASS_PATH_TEST = "com.viaccessorca.analytics.dca.VoPlayer";
    protected static final String VOPLAYER_GET_INSTANCE_METHOD = "getVOPlayerInstance";
    protected static final String VOPLAYER_GET_INSTANCE_METHOD_TEST = "getPlayerInstance";
    protected static final String VOPLAYER_LISTENER_CLASS_PATH = "com.viaccessorca.voplayer.VOPlayer$OnPreparedListener";
    protected static final String VOPLAYER_SET_METADATA_METHOD = "setAnalyticsMetadata";
    protected static final String VOPLAYER_SET_METADATA_METHOD_TEST = "setAnalyticsMetadata";
    private long EVENT_HANDLER_TICK_INTERVAL;
    private String analyticsChannelId;
    private String analyticsChannelName;
    private String analyticsContentId;
    private String analyticsContentName;
    private Map<String, String> analyticsMetadatas;
    private DcaDatabaseHelper dcaDataBase;
    private String mAccountID;
    private VoDcaConfiguration mConfiguration;
    private Object mConfigurationSync;
    private ContentType mContentType;
    private boolean mContinue;
    private VoDcaDispatcher mDispatcher;
    private VoDcaEventHandlerZapFilter mEventHandlerCatchupPauseResume;
    private VoDcaEventHandlerZapFilter mEventHandlerCatchupPlayStop;
    private VoDcaEventHandlerSeek mEventHandlerCatchupSeek;
    private VoDcaEventHandlerZapFilter mEventHandlerDownloadPauseResume;
    private VoDcaEventHandlerZapFilter mEventHandlerDownloadPlayStop;
    private VoDcaEventHandlerSeek mEventHandlerDownloadSeek;
    private VoDcaEventHandlerZapFilter mEventHandlerLivePauseResume;
    private VoDcaEventHandlerZapFilter mEventHandlerLivePlayStop;
    private VoDcaEventHandlerSeek mEventHandlerLiveSeek;
    private VoDcaEventHandlerZapFilter mEventHandlerNpvrPauseResume;
    private VoDcaEventHandlerZapFilter mEventHandlerNpvrPlayStop;
    private VoDcaEventHandlerSeek mEventHandlerNpvrSeek;
    private VoDcaEventHandlerZapFilter mEventHandlerPreviewPauseResume;
    private VoDcaEventHandlerZapFilter mEventHandlerPreviewPlayStop;
    private VoDcaEventHandlerSeek mEventHandlerPreviewSeek;
    private VoDcaEventHandlerProxy mEventHandlerProxy;
    private VoDcaEventHandlerZapFilter mEventHandlerStartoverPauseResume;
    private VoDcaEventHandlerZapFilter mEventHandlerStartoverPlayStop;
    private VoDcaEventHandlerSeek mEventHandlerStartoverSeek;
    private VoDcaEventHandlerZapFilter mEventHandlerVodPauseResume;
    private VoDcaEventHandlerZapFilter mEventHandlerVodPlayStop;
    private VoDcaEventHandlerSeek mEventHandlerVodSeek;
    private Handler mHandler;
    private VoDcaBusinessLogicListener mListener;
    private ContentType mNextContentType;
    private String mProfileID;
    private LinkedBlockingQueue<VoDcaEvent> mQueue;
    private Object mQueueSync;
    private boolean mReloadConfigurationFlag;
    private VoDcaEventHandlerThread mThread;
    private Object onPlayerPreparedListener;

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

    private class VoDcaEventHandlerThread extends Thread {
        private VoDcaEventHandlerThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (VoDcaBusinessLogic.this.mContinue) {
                long j = VoDcaBusinessLogic.this.EVENT_HANDLER_TICK_INTERVAL;
                synchronized (VoDcaBusinessLogic.this.mConfigurationSync) {
                    long jTick = VoDcaBusinessLogic.this.mEventHandlerProxy.tick();
                    if (jTick > 0 && j > jTick) {
                        j = jTick;
                    }
                    long jTick2 = VoDcaBusinessLogic.this.mEventHandlerLivePlayStop.tick();
                    if (jTick2 > 0 && j > jTick2) {
                        j = jTick2;
                    }
                    long jTick3 = VoDcaBusinessLogic.this.mEventHandlerStartoverPlayStop.tick();
                    if (jTick3 > 0 && j > jTick3) {
                        j = jTick3;
                    }
                    long jTick4 = VoDcaBusinessLogic.this.mEventHandlerCatchupPlayStop.tick();
                    if (jTick4 > 0 && j > jTick4) {
                        j = jTick4;
                    }
                    long jTick5 = VoDcaBusinessLogic.this.mEventHandlerVodPlayStop.tick();
                    if (jTick5 > 0 && j > jTick5) {
                        j = jTick5;
                    }
                    long jTick6 = VoDcaBusinessLogic.this.mEventHandlerPreviewPlayStop.tick();
                    if (jTick6 > 0 && j > jTick6) {
                        j = jTick6;
                    }
                    long jTick7 = VoDcaBusinessLogic.this.mEventHandlerNpvrPlayStop.tick();
                    if (jTick7 > 0 && j > jTick7) {
                        j = jTick7;
                    }
                    long jTick8 = VoDcaBusinessLogic.this.mEventHandlerDownloadPlayStop.tick();
                    if (jTick8 > 0 && j > jTick8) {
                        j = jTick8;
                    }
                    long jTick9 = VoDcaBusinessLogic.this.mEventHandlerLivePauseResume.tick();
                    if (jTick9 > 0 && j > jTick9) {
                        j = jTick9;
                    }
                    long jTick10 = VoDcaBusinessLogic.this.mEventHandlerStartoverPauseResume.tick();
                    if (jTick10 > 0 && j > jTick10) {
                        j = jTick10;
                    }
                    long jTick11 = VoDcaBusinessLogic.this.mEventHandlerCatchupPauseResume.tick();
                    if (jTick11 > 0 && j > jTick11) {
                        j = jTick11;
                    }
                    long jTick12 = VoDcaBusinessLogic.this.mEventHandlerVodPauseResume.tick();
                    if (jTick12 > 0 && j > jTick12) {
                        j = jTick12;
                    }
                    long jTick13 = VoDcaBusinessLogic.this.mEventHandlerPreviewPauseResume.tick();
                    if (jTick13 > 0 && j > jTick13) {
                        j = jTick13;
                    }
                    long jTick14 = VoDcaBusinessLogic.this.mEventHandlerNpvrPauseResume.tick();
                    if (jTick14 > 0 && j > jTick14) {
                        j = jTick14;
                    }
                    long jTick15 = VoDcaBusinessLogic.this.mEventHandlerDownloadPauseResume.tick();
                    if (jTick15 > 0 && j > jTick15) {
                        j = jTick15;
                    }
                    long jTick16 = VoDcaBusinessLogic.this.mEventHandlerLiveSeek.tick();
                    if (jTick16 > 0 && j > jTick16) {
                        j = jTick16;
                    }
                    long jTick17 = VoDcaBusinessLogic.this.mEventHandlerStartoverSeek.tick();
                    if (jTick17 > 0 && j > jTick17) {
                        j = jTick17;
                    }
                    long jTick18 = VoDcaBusinessLogic.this.mEventHandlerCatchupSeek.tick();
                    if (jTick18 > 0 && j > jTick18) {
                        j = jTick18;
                    }
                    long jTick19 = VoDcaBusinessLogic.this.mEventHandlerVodSeek.tick();
                    if (jTick19 > 0 && j > jTick19) {
                        j = jTick19;
                    }
                    long jTick20 = VoDcaBusinessLogic.this.mEventHandlerPreviewSeek.tick();
                    if (jTick20 > 0 && j > jTick20) {
                        j = jTick20;
                    }
                    long jTick21 = VoDcaBusinessLogic.this.mEventHandlerNpvrSeek.tick();
                    if (jTick21 > 0 && j > jTick21) {
                        j = jTick21;
                    }
                    long jTick22 = VoDcaBusinessLogic.this.mEventHandlerDownloadSeek.tick();
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

    protected VoDcaBusinessLogic(String str) {
        super(str);
        this.EVENT_HANDLER_TICK_INTERVAL = 10000L;
        this.onPlayerPreparedListener = null;
        this.mContinue = true;
        this.mReloadConfigurationFlag = false;
        this.mQueue = new LinkedBlockingQueue<>(512);
        this.mQueueSync = new Object();
        this.mConfigurationSync = new Object();
        this.mContentType = ContentType.NONE;
    }

    protected void setListener(VoDcaBusinessLogicListener voDcaBusinessLogicListener) {
        this.mListener = voDcaBusinessLogicListener;
    }

    protected void setAccountID(String str) {
        this.mAccountID = str;
    }

    protected void setTickInterval(long j) {
        this.EVENT_HANDLER_TICK_INTERVAL = j;
    }

    protected void setNetworkAvailabilityDbg(Boolean bool) {
        this.mDispatcher.setNetworkAvailabilityDbg(bool.booleanValue());
    }

    protected void setProfileID(String str) {
        this.mProfileID = str;
    }

    protected Boolean init(VoDcaInitParameters voDcaInitParameters, String str) {
        this.mConfiguration = new VoDcaConfiguration();
        if (voDcaInitParameters.mDeviceID == null) {
            Log.v(TAG, "Mandatory DeviceId param is missing");
            return false;
        }
        if (voDcaInitParameters.mApplicationName == null) {
            Log.v(TAG, "Mandatory DeviceId param is missing");
            return false;
        }
        if (voDcaInitParameters.mApplicationVersion == null) {
            Log.v(TAG, "Mandatory DeviceId param is missing");
            return false;
        }
        if (voDcaInitParameters.mConfigurationUrl == null) {
            Log.v(TAG, "Mandatory mConfigurationUrl param is missing");
            return false;
        }
        this.mConfiguration.mDeviceID = voDcaInitParameters.mDeviceID;
        this.mConfiguration.mApplicationName = voDcaInitParameters.mApplicationName;
        this.mConfiguration.mApplicationVersion = voDcaInitParameters.mApplicationVersion;
        this.mConfiguration.mDcaVersion = str;
        if (voDcaInitParameters.mDeviceCategory != null) {
            this.mConfiguration.mDeviceCategory = voDcaInitParameters.mDeviceCategory;
        } else {
            this.mConfiguration.mDeviceCategory = "";
        }
        VoDcaDispatcher voDcaDispatcher = new VoDcaDispatcher(voDcaInitParameters.mApplicationContext);
        this.mDispatcher = voDcaDispatcher;
        voDcaDispatcher.setListener(this);
        this.mDispatcher.getConfiguration(voDcaInitParameters.mConfigurationUrl);
        this.mEventHandlerProxy = new VoDcaEventHandlerProxy(this.mDispatcher, this.mConfiguration);
        VoDcaEventHandlerZapFilter voDcaEventHandlerZapFilter = new VoDcaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerLivePlayStop = voDcaEventHandlerZapFilter;
        voDcaEventHandlerZapFilter.setListener(this);
        this.mEventHandlerLivePlayStop.setInputEvents(3, 13);
        this.mEventHandlerLivePlayStop.setOutputEvents("LIVE_PLAY", "LIVE_STOP");
        this.mEventHandlerLivePlayStop.setChannelRequired(true);
        this.mEventHandlerLivePlayStop.setProgramRequired(false);
        this.mEventHandlerLivePlayStop.setEnableHandleEvents(this.mConfiguration.mLivePlayStop_StateConfirmationPeriod != -1);
        VoDcaEventHandlerZapFilter voDcaEventHandlerZapFilter2 = new VoDcaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerStartoverPlayStop = voDcaEventHandlerZapFilter2;
        voDcaEventHandlerZapFilter2.setListener(this);
        this.mEventHandlerStartoverPlayStop.setInputEvents(3, 13);
        this.mEventHandlerStartoverPlayStop.setOutputEvents("STARTOVER_PLAY", "STARTOVER_STOP");
        this.mEventHandlerStartoverPlayStop.setChannelRequired(true);
        this.mEventHandlerStartoverPlayStop.setProgramRequired(true);
        this.mEventHandlerStartoverPlayStop.setEnableHandleEvents(this.mConfiguration.mStartoverPlayStop_StateConfirmationPeriod != -1);
        this.mEventHandlerStartoverPlayStop.setConvertFromToField("streamOffset", "programOffset");
        VoDcaEventHandlerZapFilter voDcaEventHandlerZapFilter3 = new VoDcaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerCatchupPlayStop = voDcaEventHandlerZapFilter3;
        voDcaEventHandlerZapFilter3.setListener(this);
        this.mEventHandlerCatchupPlayStop.setInputEvents(3, 13);
        this.mEventHandlerCatchupPlayStop.setOutputEvents("CATCHUP_PLAY", "CATCHUP_STOP");
        this.mEventHandlerCatchupPlayStop.setChannelRequired(true);
        this.mEventHandlerCatchupPlayStop.setProgramRequired(true);
        this.mEventHandlerCatchupPlayStop.setEnableHandleEvents(this.mConfiguration.mCatchupPlayStop_StateConfirmationPeriod != -1);
        this.mEventHandlerCatchupPlayStop.setConvertFromToField("streamOffset", "programOffset");
        VoDcaEventHandlerZapFilter voDcaEventHandlerZapFilter4 = new VoDcaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerVodPlayStop = voDcaEventHandlerZapFilter4;
        voDcaEventHandlerZapFilter4.setListener(this);
        this.mEventHandlerVodPlayStop.setInputEvents(3, 13);
        this.mEventHandlerVodPlayStop.setOutputEvents("VOD_PLAY", "VOD_STOP");
        this.mEventHandlerVodPlayStop.setChannelRequired(false);
        this.mEventHandlerVodPlayStop.setProgramRequired(false);
        this.mEventHandlerVodPlayStop.setVodInfoRequired(true);
        this.mEventHandlerVodPlayStop.setEnableHandleEvents(this.mConfiguration.mVodPlayStop_StateConfirmationPeriod != -1);
        this.mEventHandlerVodPlayStop.setConvertFromToField("streamOffset", "contentOffset");
        VoDcaEventHandlerZapFilter voDcaEventHandlerZapFilter5 = new VoDcaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerPreviewPlayStop = voDcaEventHandlerZapFilter5;
        voDcaEventHandlerZapFilter5.setListener(this);
        this.mEventHandlerPreviewPlayStop.setInputEvents(3, 13);
        this.mEventHandlerPreviewPlayStop.setOutputEvents("PREVIEW_PLAY", "PREVIEW_STOP");
        this.mEventHandlerPreviewPlayStop.setChannelRequired(false);
        this.mEventHandlerPreviewPlayStop.setProgramRequired(false);
        this.mEventHandlerPreviewPlayStop.setPreviewInfoRequired(true);
        this.mEventHandlerPreviewPlayStop.setEnableHandleEvents(this.mConfiguration.mPreviewPlayStop_StateConfirmationPeriod != -1);
        this.mEventHandlerPreviewPlayStop.setConvertFromToField("streamOffset", "contentOffset");
        VoDcaEventHandlerZapFilter voDcaEventHandlerZapFilter6 = new VoDcaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerNpvrPlayStop = voDcaEventHandlerZapFilter6;
        voDcaEventHandlerZapFilter6.setListener(this);
        this.mEventHandlerNpvrPlayStop.setInputEvents(3, 13);
        this.mEventHandlerNpvrPlayStop.setOutputEvents("NPVR_PLAY", "NPVR_STOP");
        this.mEventHandlerNpvrPlayStop.setChannelRequired(true);
        this.mEventHandlerNpvrPlayStop.setProgramRequired(true);
        this.mEventHandlerNpvrPlayStop.setEnableHandleEvents(this.mConfiguration.mNpvrPlayStop_StateConfirmationPeriod != -1);
        this.mEventHandlerNpvrPlayStop.setConvertFromToField("streamOffset", "programOffset");
        VoDcaEventHandlerZapFilter voDcaEventHandlerZapFilter7 = new VoDcaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerDownloadPlayStop = voDcaEventHandlerZapFilter7;
        voDcaEventHandlerZapFilter7.setListener(this);
        this.mEventHandlerDownloadPlayStop.setInputEvents(3, 13);
        this.mEventHandlerDownloadPlayStop.setOutputEvents("DOWNLOAD_PLAY", "DOWNLOAD_STOP");
        this.mEventHandlerDownloadPlayStop.setChannelRequired(false);
        this.mEventHandlerDownloadPlayStop.setProgramRequired(false);
        this.mEventHandlerDownloadPlayStop.setVodInfoRequired(true);
        this.mEventHandlerDownloadPlayStop.setEnableHandleEvents(this.mConfiguration.mDownloadPlayStop_StateConfirmationPeriod != -1);
        this.mEventHandlerDownloadPlayStop.setConvertFromToField("streamOffset", "contentOffset");
        VoDcaEventHandlerZapFilter voDcaEventHandlerZapFilter8 = new VoDcaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerLivePauseResume = voDcaEventHandlerZapFilter8;
        voDcaEventHandlerZapFilter8.setInputEvents(2, 4);
        this.mEventHandlerLivePauseResume.setOutputEvents("LIVE_PAUSE", "LIVE_RESUME");
        this.mEventHandlerLivePauseResume.setResetEvent("LIVE_PLAY");
        this.mEventHandlerLivePauseResume.setChannelRequired(true);
        this.mEventHandlerLivePauseResume.setProgramRequired(false);
        this.mEventHandlerLivePauseResume.setEnableHandleEvents(false);
        VoDcaEventHandlerZapFilter voDcaEventHandlerZapFilter9 = new VoDcaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerStartoverPauseResume = voDcaEventHandlerZapFilter9;
        voDcaEventHandlerZapFilter9.setInputEvents(2, 4);
        this.mEventHandlerStartoverPauseResume.setOutputEvents("STARTOVER_PAUSE", "STARTOVER_RESUME");
        this.mEventHandlerStartoverPauseResume.setResetEvent("STARTOVER_PLAY");
        this.mEventHandlerStartoverPauseResume.setChannelRequired(true);
        this.mEventHandlerStartoverPauseResume.setProgramRequired(true);
        this.mEventHandlerStartoverPauseResume.setEnableHandleEvents(false);
        this.mEventHandlerStartoverPauseResume.setConvertFromToField("streamOffset", "programOffset");
        VoDcaEventHandlerZapFilter voDcaEventHandlerZapFilter10 = new VoDcaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerCatchupPauseResume = voDcaEventHandlerZapFilter10;
        voDcaEventHandlerZapFilter10.setInputEvents(2, 4);
        this.mEventHandlerCatchupPauseResume.setOutputEvents("CATCHUP_PAUSE", "CATCHUP_RESUME");
        this.mEventHandlerCatchupPauseResume.setResetEvent("CATCHUP_PLAY");
        this.mEventHandlerCatchupPauseResume.setChannelRequired(true);
        this.mEventHandlerCatchupPauseResume.setProgramRequired(true);
        this.mEventHandlerCatchupPauseResume.setEnableHandleEvents(false);
        this.mEventHandlerCatchupPauseResume.setConvertFromToField("streamOffset", "programOffset");
        VoDcaEventHandlerZapFilter voDcaEventHandlerZapFilter11 = new VoDcaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerVodPauseResume = voDcaEventHandlerZapFilter11;
        voDcaEventHandlerZapFilter11.setInputEvents(2, 4);
        this.mEventHandlerVodPauseResume.setOutputEvents("VOD_PAUSE", "VOD_RESUME");
        this.mEventHandlerVodPauseResume.setResetEvent("VOD_PLAY");
        this.mEventHandlerVodPauseResume.setChannelRequired(false);
        this.mEventHandlerVodPauseResume.setProgramRequired(false);
        this.mEventHandlerVodPauseResume.setVodInfoRequired(true);
        this.mEventHandlerVodPauseResume.setEnableHandleEvents(false);
        this.mEventHandlerVodPauseResume.setConvertFromToField("streamOffset", "contentOffset");
        VoDcaEventHandlerZapFilter voDcaEventHandlerZapFilter12 = new VoDcaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerPreviewPauseResume = voDcaEventHandlerZapFilter12;
        voDcaEventHandlerZapFilter12.setInputEvents(2, 4);
        this.mEventHandlerPreviewPauseResume.setOutputEvents("PREVIEW_PAUSE", "PREVIEW_RESUME");
        this.mEventHandlerPreviewPauseResume.setResetEvent("PREVIEW_PLAY");
        this.mEventHandlerPreviewPauseResume.setChannelRequired(false);
        this.mEventHandlerPreviewPauseResume.setProgramRequired(false);
        this.mEventHandlerPreviewPauseResume.setPreviewInfoRequired(true);
        this.mEventHandlerPreviewPauseResume.setEnableHandleEvents(false);
        this.mEventHandlerPreviewPauseResume.setConvertFromToField("streamOffset", "contentOffset");
        VoDcaEventHandlerZapFilter voDcaEventHandlerZapFilter13 = new VoDcaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerNpvrPauseResume = voDcaEventHandlerZapFilter13;
        voDcaEventHandlerZapFilter13.setInputEvents(2, 4);
        this.mEventHandlerNpvrPauseResume.setOutputEvents("NPVR_PAUSE", "NPVR_RESUME");
        this.mEventHandlerNpvrPauseResume.setResetEvent("NPVR_PLAY");
        this.mEventHandlerNpvrPauseResume.setChannelRequired(true);
        this.mEventHandlerNpvrPauseResume.setProgramRequired(true);
        this.mEventHandlerNpvrPauseResume.setEnableHandleEvents(false);
        this.mEventHandlerNpvrPauseResume.setConvertFromToField("streamOffset", "programOffset");
        VoDcaEventHandlerZapFilter voDcaEventHandlerZapFilter14 = new VoDcaEventHandlerZapFilter(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerDownloadPauseResume = voDcaEventHandlerZapFilter14;
        voDcaEventHandlerZapFilter14.setInputEvents(2, 4);
        this.mEventHandlerDownloadPauseResume.setOutputEvents("DOWNLOAD_PAUSE", "DOWNLOAD_RESUME");
        this.mEventHandlerDownloadPauseResume.setResetEvent("DOWNLOAD_PLAY");
        this.mEventHandlerDownloadPauseResume.setChannelRequired(false);
        this.mEventHandlerDownloadPauseResume.setProgramRequired(false);
        this.mEventHandlerDownloadPauseResume.setVodInfoRequired(true);
        this.mEventHandlerDownloadPauseResume.setEnableHandleEvents(false);
        this.mEventHandlerDownloadPauseResume.setConvertFromToField("streamOffset", "contentOffset");
        VoDcaEventHandlerSeek voDcaEventHandlerSeek = new VoDcaEventHandlerSeek(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerLiveSeek = voDcaEventHandlerSeek;
        voDcaEventHandlerSeek.setInputEvents(6, 5);
        this.mEventHandlerLiveSeek.setOutputEvent("LIVE_SEEK");
        this.mEventHandlerLiveSeek.setBreakEvents("LIVE_PLAY", "LIVE_PAUSE");
        this.mEventHandlerLiveSeek.setChannelRequired(true);
        this.mEventHandlerLiveSeek.setProgramRequired(false);
        this.mEventHandlerLiveSeek.setFromToTimeFields("fromStreamTime", "streamTime");
        this.mEventHandlerLiveSeek.setEnableHandleEvents(false);
        VoDcaEventHandlerSeek voDcaEventHandlerSeek2 = new VoDcaEventHandlerSeek(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerStartoverSeek = voDcaEventHandlerSeek2;
        voDcaEventHandlerSeek2.setInputEvents(6, 5);
        this.mEventHandlerStartoverSeek.setOutputEvent("STARTOVER_SEEK");
        this.mEventHandlerStartoverSeek.setBreakEvents("STARTOVER_PLAY", "STARTOVER_PAUSE");
        this.mEventHandlerStartoverSeek.setChannelRequired(true);
        this.mEventHandlerStartoverSeek.setProgramRequired(true);
        this.mEventHandlerStartoverSeek.setFromToTimeFields("fromProgramOffset", "programOffset");
        this.mEventHandlerStartoverSeek.setEnableHandleEvents(false);
        this.mEventHandlerStartoverSeek.setConvertFromToField("streamOffset", "programOffset");
        VoDcaEventHandlerSeek voDcaEventHandlerSeek3 = new VoDcaEventHandlerSeek(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerCatchupSeek = voDcaEventHandlerSeek3;
        voDcaEventHandlerSeek3.setInputEvents(6, 5);
        this.mEventHandlerCatchupSeek.setOutputEvent("CATCHUP_SEEK");
        this.mEventHandlerCatchupSeek.setBreakEvents("CATCHUP_PLAY", "CATCHUP_PAUSE");
        this.mEventHandlerCatchupSeek.setChannelRequired(true);
        this.mEventHandlerCatchupSeek.setProgramRequired(true);
        this.mEventHandlerCatchupSeek.setFromToTimeFields("fromProgramOffset", "programOffset");
        this.mEventHandlerCatchupSeek.setEnableHandleEvents(false);
        this.mEventHandlerCatchupSeek.setConvertFromToField("streamOffset", "programOffset");
        VoDcaEventHandlerSeek voDcaEventHandlerSeek4 = new VoDcaEventHandlerSeek(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerVodSeek = voDcaEventHandlerSeek4;
        voDcaEventHandlerSeek4.setInputEvents(6, 5);
        this.mEventHandlerVodSeek.setOutputEvent("VOD_SEEK");
        this.mEventHandlerVodSeek.setBreakEvents("VOD_PLAY", "VOD_PAUSE");
        this.mEventHandlerVodSeek.setChannelRequired(false);
        this.mEventHandlerVodSeek.setProgramRequired(false);
        this.mEventHandlerVodSeek.setVodInfoRequired(true);
        this.mEventHandlerVodSeek.setFromToTimeFields("fromContentOffset", "contentOffset");
        this.mEventHandlerVodSeek.setEnableHandleEvents(false);
        this.mEventHandlerVodSeek.setConvertFromToField("streamOffset", "contentOffset");
        VoDcaEventHandlerSeek voDcaEventHandlerSeek5 = new VoDcaEventHandlerSeek(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerPreviewSeek = voDcaEventHandlerSeek5;
        voDcaEventHandlerSeek5.setInputEvents(6, 5);
        this.mEventHandlerPreviewSeek.setOutputEvent("PREVIEW_SEEK");
        this.mEventHandlerPreviewSeek.setBreakEvents("PREVIEW_PLAY", "PREVIEW_PAUSE");
        this.mEventHandlerPreviewSeek.setChannelRequired(false);
        this.mEventHandlerPreviewSeek.setProgramRequired(false);
        this.mEventHandlerPreviewSeek.setPreviewInfoRequired(true);
        this.mEventHandlerPreviewSeek.setFromToTimeFields("fromContentOffset", "contentOffset");
        this.mEventHandlerPreviewSeek.setEnableHandleEvents(false);
        this.mEventHandlerPreviewSeek.setConvertFromToField("streamOffset", "contentOffset");
        VoDcaEventHandlerSeek voDcaEventHandlerSeek6 = new VoDcaEventHandlerSeek(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerNpvrSeek = voDcaEventHandlerSeek6;
        voDcaEventHandlerSeek6.setInputEvents(6, 5);
        this.mEventHandlerNpvrSeek.setOutputEvent("NPVR_SEEK");
        this.mEventHandlerNpvrSeek.setBreakEvents("NPVR_PLAY", "NPVR_PAUSE");
        this.mEventHandlerNpvrSeek.setChannelRequired(true);
        this.mEventHandlerNpvrSeek.setProgramRequired(true);
        this.mEventHandlerNpvrSeek.setFromToTimeFields("fromProgramOffset", "programOffset");
        this.mEventHandlerNpvrSeek.setEnableHandleEvents(false);
        this.mEventHandlerNpvrSeek.setConvertFromToField("streamOffset", "programOffset");
        VoDcaEventHandlerSeek voDcaEventHandlerSeek7 = new VoDcaEventHandlerSeek(this.mDispatcher, this.mConfiguration);
        this.mEventHandlerDownloadSeek = voDcaEventHandlerSeek7;
        voDcaEventHandlerSeek7.setInputEvents(6, 5);
        this.mEventHandlerDownloadSeek.setOutputEvent("DOWNLOAD_SEEK");
        this.mEventHandlerDownloadSeek.setBreakEvents("DOWNLOAD_PLAY", "DOWNLOAD_PAUSE");
        this.mEventHandlerDownloadSeek.setChannelRequired(false);
        this.mEventHandlerDownloadSeek.setProgramRequired(false);
        this.mEventHandlerDownloadSeek.setVodInfoRequired(true);
        this.mEventHandlerDownloadSeek.setFromToTimeFields("fromContentOffset", "contentOffset");
        this.mEventHandlerDownloadSeek.setEnableHandleEvents(false);
        this.mEventHandlerDownloadSeek.setConvertFromToField("streamOffset", "contentOffset");
        VoDcaEventHandlerThread voDcaEventHandlerThread = new VoDcaEventHandlerThread();
        this.mThread = voDcaEventHandlerThread;
        voDcaEventHandlerThread.start();
        return true;
    }

    protected void reloadConfiguration(String str) {
        this.mReloadConfigurationFlag = true;
        this.mDispatcher.getConfiguration(str);
    }

    protected VoDcaController.VoDcaReturnCode logEvent(VoDcaEvent voDcaEvent) {
        voDcaEvent.setCurrentTime();
        voDcaEvent.setCustomParameter("eventTime", voDcaEvent.getTime());
        voDcaEvent.setCustomParameter("operatorName", this.mConfiguration.mOperatorName);
        voDcaEvent.setCustomParameter(TtmlNode.ATTR_TTS_ORIGIN, "DCA");
        voDcaEvent.setCustomParameter("originVersion", this.mConfiguration.mDcaVersion);
        voDcaEvent.setCustomParameter(MimeTypes.BASE_TYPE_APPLICATION, this.mConfiguration.mApplicationName);
        voDcaEvent.setCustomParameter("applicationVersion", this.mConfiguration.mApplicationVersion);
        voDcaEvent.setCustomParameter("deviceCategory", this.mConfiguration.mDeviceCategory);
        voDcaEvent.setCustomParameter("originPlatform", "Android");
        voDcaEvent.setCustomParameter("deviceId", this.mConfiguration.mDeviceID);
        String str = this.mAccountID;
        if (str != null) {
            voDcaEvent.setCustomParameter("accountId", str);
            String str2 = this.mProfileID;
            if (str2 != null) {
                voDcaEvent.setCustomParameter(ToolboxAnalyticsModuleKt.EVENT_PROFILE_ID, str2);
            }
        }
        try {
            synchronized (this.mQueueSync) {
                this.mQueue.add(voDcaEvent);
                this.mQueueSync.notify();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return VoDcaController.VoDcaReturnCode.Success;
    }

    @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
    public void run() {
        while (this.mContinue) {
            synchronized (this.mQueueSync) {
                VoDcaEvent voDcaEventPoll = this.mQueue.poll();
                if (voDcaEventPoll == null) {
                    try {
                        this.mQueueSync.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        routeEvent(voDcaEventPoll);
                    }
                }
                routeEvent(voDcaEventPoll);
            }
        }
    }

    protected void routeEvent(VoDcaEvent voDcaEvent) {
        Object playerInstance;
        String value = voDcaEvent.getValue(EVENT_INTERNAL_KEY);
        if (value != null) {
            String value2 = voDcaEvent.getValue("eventType");
            if (value2 != null) {
                if (value2.equals("LIVE_STOP") || value2.equals("LIVE_PAUSE")) {
                    if (value.equals(EVENT_INTERNAL_VALUE_FAKE_EVENT)) {
                        VoDcaEvent voDcaEvent2 = new VoDcaEvent(voDcaEvent);
                        this.mEventHandlerLivePauseResume.setEnableHandleEvents(this.mConfiguration.mLivePauseResume_StateConfirmationPeriod != -1);
                        this.mEventHandlerLivePauseResume.handleEvent(voDcaEvent2);
                    }
                    this.mEventHandlerLiveSeek.setEnableHandleEvents(this.mConfiguration.mLiveSeek_StateConfirmationPeriod != -1);
                    this.mEventHandlerLiveSeek.handleEvent(voDcaEvent);
                    return;
                }
                if (value2.equals("STARTOVER_STOP") || value2.equals("STARTOVER_PAUSE")) {
                    if (value.equals(EVENT_INTERNAL_VALUE_FAKE_EVENT)) {
                        VoDcaEvent voDcaEvent3 = new VoDcaEvent(voDcaEvent);
                        this.mEventHandlerStartoverPauseResume.setEnableHandleEvents(this.mConfiguration.mStartoverPauseResume_StateConfirmationPeriod != -1);
                        this.mEventHandlerStartoverPauseResume.handleEvent(voDcaEvent3);
                    }
                    this.mEventHandlerStartoverSeek.setEnableHandleEvents(this.mConfiguration.mStartoverSeek_StateConfirmationPeriod != -1);
                    this.mEventHandlerStartoverSeek.handleEvent(voDcaEvent);
                    return;
                }
                if (value2.equals("CATCHUP_STOP") || value2.equals("CATCHUP_PAUSE")) {
                    if (value.equals(EVENT_INTERNAL_VALUE_FAKE_EVENT)) {
                        VoDcaEvent voDcaEvent4 = new VoDcaEvent(voDcaEvent);
                        this.mEventHandlerCatchupPauseResume.setEnableHandleEvents(this.mConfiguration.mCatchupPauseResume_StateConfirmationPeriod != -1);
                        this.mEventHandlerCatchupPauseResume.handleEvent(voDcaEvent4);
                    }
                    this.mEventHandlerCatchupSeek.setEnableHandleEvents(this.mConfiguration.mCatchupSeek_StateConfirmationPeriod != -1);
                    this.mEventHandlerCatchupSeek.handleEvent(voDcaEvent);
                    return;
                }
                if (value2.equals("VOD_STOP") || value2.equals("VOD_PAUSE")) {
                    if (value.equals(EVENT_INTERNAL_VALUE_FAKE_EVENT)) {
                        VoDcaEvent voDcaEvent5 = new VoDcaEvent(voDcaEvent);
                        this.mEventHandlerVodPauseResume.setEnableHandleEvents(this.mConfiguration.mVodPauseResume_StateConfirmationPeriod != -1);
                        this.mEventHandlerVodPauseResume.handleEvent(voDcaEvent5);
                    }
                    this.mEventHandlerVodSeek.setEnableHandleEvents(this.mConfiguration.mVodSeek_StateConfirmationPeriod != -1);
                    this.mEventHandlerVodSeek.handleEvent(voDcaEvent);
                    return;
                }
                if (value2.equals("PREVIEW_STOP") || value2.equals("PREVIEW_PAUSE")) {
                    if (value.equals(EVENT_INTERNAL_VALUE_FAKE_EVENT)) {
                        VoDcaEvent voDcaEvent6 = new VoDcaEvent(voDcaEvent);
                        this.mEventHandlerPreviewPauseResume.setEnableHandleEvents(this.mConfiguration.mPreviewPauseResume_StateConfirmationPeriod != -1);
                        this.mEventHandlerPreviewPauseResume.handleEvent(voDcaEvent6);
                    }
                    this.mEventHandlerPreviewSeek.setEnableHandleEvents(true);
                    this.mEventHandlerPreviewSeek.handleEvent(voDcaEvent);
                    return;
                }
                if (value2.equals("NPVR_STOP") || value2.equals("NPVR_PAUSE")) {
                    if (value.equals(EVENT_INTERNAL_VALUE_FAKE_EVENT)) {
                        VoDcaEvent voDcaEvent7 = new VoDcaEvent(voDcaEvent);
                        this.mEventHandlerNpvrPauseResume.setEnableHandleEvents(this.mConfiguration.mNpvrPauseResume_StateConfirmationPeriod != -1);
                        this.mEventHandlerNpvrPauseResume.handleEvent(voDcaEvent7);
                    }
                    this.mEventHandlerNpvrSeek.setEnableHandleEvents(this.mConfiguration.mNpvrSeek_StateConfirmationPeriod != -1);
                    this.mEventHandlerNpvrSeek.handleEvent(voDcaEvent);
                    return;
                }
                if (value2.equals("DOWNLOAD_STOP") || value2.equals("DOWNLOAD_PAUSE")) {
                    if (value.equals(EVENT_INTERNAL_VALUE_FAKE_EVENT)) {
                        VoDcaEvent voDcaEvent8 = new VoDcaEvent(voDcaEvent);
                        this.mEventHandlerDownloadPauseResume.setEnableHandleEvents(this.mConfiguration.mDownloadPauseResume_StateConfirmationPeriod != -1);
                        this.mEventHandlerDownloadPauseResume.handleEvent(voDcaEvent8);
                    }
                    this.mEventHandlerDownloadSeek.setEnableHandleEvents(this.mConfiguration.mDownloadSeek_StateConfirmationPeriod != -1);
                    this.mEventHandlerDownloadSeek.handleEvent(voDcaEvent);
                    return;
                }
                if (value2.equals("LIVE_PLAY")) {
                    this.mEventHandlerLivePauseResume.handleEvent(voDcaEvent);
                    return;
                }
                if (value2.equals("STARTOVER_PLAY")) {
                    this.mEventHandlerStartoverPauseResume.handleEvent(voDcaEvent);
                    return;
                }
                if (value2.equals("CATCHUP_PLAY")) {
                    this.mEventHandlerCatchupPauseResume.handleEvent(voDcaEvent);
                    return;
                }
                if (value2.equals("VOD_PLAY")) {
                    this.mEventHandlerVodPauseResume.handleEvent(voDcaEvent);
                    return;
                }
                if (value2.equals("PREVIEW_PLAY")) {
                    this.mEventHandlerPreviewPauseResume.handleEvent(voDcaEvent);
                    return;
                } else if (value2.equals("NPVR_PLAY")) {
                    this.mEventHandlerNpvrPauseResume.handleEvent(voDcaEvent);
                    return;
                } else {
                    if (value2.equals("DOWNLOAD_PLAY")) {
                        this.mEventHandlerDownloadPauseResume.handleEvent(voDcaEvent);
                        return;
                    }
                    return;
                }
            }
            return;
        }
        if (voDcaEvent.getType() == 7) {
            this.analyticsChannelId = voDcaEvent.getValue("channelId");
            this.analyticsChannelName = voDcaEvent.getValue("channelName");
            this.mEventHandlerLivePlayStop.setChannel(voDcaEvent.getValue("channelId"), voDcaEvent.getValue("channelName"));
            this.mEventHandlerStartoverPlayStop.setChannel(voDcaEvent.getValue("channelId"), voDcaEvent.getValue("channelName"));
            this.mEventHandlerCatchupPlayStop.setChannel(voDcaEvent.getValue("channelId"), voDcaEvent.getValue("channelName"));
            this.mEventHandlerNpvrPlayStop.setChannel(voDcaEvent.getValue("channelId"), voDcaEvent.getValue("channelName"));
            this.mEventHandlerLivePauseResume.setChannel(voDcaEvent.getValue("channelId"), voDcaEvent.getValue("channelName"));
            this.mEventHandlerStartoverPauseResume.setChannel(voDcaEvent.getValue("channelId"), voDcaEvent.getValue("channelName"));
            this.mEventHandlerCatchupPauseResume.setChannel(voDcaEvent.getValue("channelId"), voDcaEvent.getValue("channelName"));
            this.mEventHandlerNpvrPauseResume.setChannel(voDcaEvent.getValue("channelId"), voDcaEvent.getValue("channelName"));
            this.mEventHandlerLiveSeek.setChannel(voDcaEvent.getValue("channelId"), voDcaEvent.getValue("channelName"));
            this.mEventHandlerStartoverSeek.setChannel(voDcaEvent.getValue("channelId"), voDcaEvent.getValue("channelName"));
            this.mEventHandlerCatchupSeek.setChannel(voDcaEvent.getValue("channelId"), voDcaEvent.getValue("channelName"));
            this.mEventHandlerNpvrSeek.setChannel(voDcaEvent.getValue("channelId"), voDcaEvent.getValue("channelName"));
            return;
        }
        if (voDcaEvent.getType() == 12) {
            this.mEventHandlerStartoverPlayStop.setProgram(voDcaEvent.getValue("programId"), voDcaEvent.getValue("programName"));
            this.mEventHandlerCatchupPlayStop.setProgram(voDcaEvent.getValue("programId"), voDcaEvent.getValue("programName"));
            this.mEventHandlerNpvrPlayStop.setProgram(voDcaEvent.getValue("programId"), voDcaEvent.getValue("programName"));
            this.mEventHandlerStartoverPauseResume.setProgram(voDcaEvent.getValue("programId"), voDcaEvent.getValue("programName"));
            this.mEventHandlerCatchupPauseResume.setProgram(voDcaEvent.getValue("programId"), voDcaEvent.getValue("programName"));
            this.mEventHandlerNpvrPauseResume.setProgram(voDcaEvent.getValue("programId"), voDcaEvent.getValue("programName"));
            this.mEventHandlerStartoverSeek.setProgram(voDcaEvent.getValue("programId"), voDcaEvent.getValue("programName"));
            this.mEventHandlerCatchupSeek.setProgram(voDcaEvent.getValue("programId"), voDcaEvent.getValue("programName"));
            this.mEventHandlerNpvrSeek.setProgram(voDcaEvent.getValue("programId"), voDcaEvent.getValue("programName"));
            return;
        }
        if (voDcaEvent.getType() == 9 || voDcaEvent.getType() == 8 || voDcaEvent.getType() == 10) {
            if (voDcaEvent.keyExists("assetId")) {
                this.analyticsContentId = voDcaEvent.getValue("assetId");
                this.mEventHandlerVodPlayStop.setVodAsset(voDcaEvent.getValue("assetId"));
                this.mEventHandlerDownloadPlayStop.setVodAsset(voDcaEvent.getValue("assetId"));
                this.mEventHandlerVodPauseResume.setVodAsset(voDcaEvent.getValue("assetId"));
                this.mEventHandlerDownloadPauseResume.setVodAsset(voDcaEvent.getValue("assetId"));
                this.mEventHandlerVodSeek.setVodAsset(voDcaEvent.getValue("assetId"));
                this.mEventHandlerDownloadSeek.setVodAsset(voDcaEvent.getValue("assetId"));
            }
            if (voDcaEvent.keyExists("providerId")) {
                this.mEventHandlerVodPlayStop.setVodProvider(voDcaEvent.getValue("providerId"));
                this.mEventHandlerDownloadPlayStop.setVodProvider(voDcaEvent.getValue("providerId"));
                this.mEventHandlerVodPauseResume.setVodProvider(voDcaEvent.getValue("providerId"));
                this.mEventHandlerDownloadPauseResume.setVodProvider(voDcaEvent.getValue("providerId"));
                this.mEventHandlerVodSeek.setVodProvider(voDcaEvent.getValue("providerId"));
                this.mEventHandlerDownloadSeek.setVodProvider(voDcaEvent.getValue("providerId"));
            }
            if (voDcaEvent.getType() == 10) {
                this.mEventHandlerVodPlayStop.setVodEpisode(null);
                this.mEventHandlerDownloadPlayStop.setVodEpisode(null);
                this.mEventHandlerVodPauseResume.setVodEpisode(null);
                this.mEventHandlerDownloadPauseResume.setVodEpisode(null);
                this.mEventHandlerVodSeek.setVodEpisode(null);
                this.mEventHandlerDownloadSeek.setVodEpisode(null);
                return;
            }
            if (voDcaEvent.keyExists("episodeId")) {
                this.mEventHandlerVodPlayStop.setVodEpisode(voDcaEvent.getValue("episodeId"));
                this.mEventHandlerDownloadPlayStop.setVodEpisode(voDcaEvent.getValue("episodeId"));
                this.mEventHandlerVodPauseResume.setVodEpisode(voDcaEvent.getValue("episodeId"));
                this.mEventHandlerDownloadPauseResume.setVodEpisode(voDcaEvent.getValue("episodeId"));
                this.mEventHandlerVodSeek.setVodEpisode(voDcaEvent.getValue("episodeId"));
                this.mEventHandlerDownloadSeek.setVodEpisode(voDcaEvent.getValue("episodeId"));
                return;
            }
            return;
        }
        if (voDcaEvent.getType() == 11) {
            if (voDcaEvent.keyExists("assetId")) {
                this.analyticsContentId = voDcaEvent.getValue("assetId");
                this.mEventHandlerPreviewPlayStop.setPreviewAsset(voDcaEvent.getValue("assetId"));
                this.mEventHandlerPreviewPauseResume.setPreviewAsset(voDcaEvent.getValue("assetId"));
                this.mEventHandlerPreviewSeek.setPreviewAsset(voDcaEvent.getValue("assetId"));
            }
            if (voDcaEvent.keyExists("name")) {
                this.analyticsContentName = voDcaEvent.getValue("name");
                this.mEventHandlerPreviewPlayStop.setPreviewName(voDcaEvent.getValue("name"));
                this.mEventHandlerPreviewPauseResume.setPreviewName(voDcaEvent.getValue("name"));
                this.mEventHandlerPreviewSeek.setPreviewName(voDcaEvent.getValue("name"));
                return;
            }
            return;
        }
        if (voDcaEvent.getType() == 1) {
            if (voDcaEvent.keyExists("contentType")) {
                if (voDcaEvent.getValue("contentType").compareTo(VOPlaybackSessionReport.SESSION_TYPE_LIVE) == 0) {
                    this.mNextContentType = ContentType.LIVE;
                } else if (voDcaEvent.getValue("contentType").compareTo("STARTOVER") == 0) {
                    this.mNextContentType = ContentType.STARTOVER;
                } else if (voDcaEvent.getValue("contentType").compareTo("CATCHUP") == 0) {
                    this.mNextContentType = ContentType.CATCHUP;
                } else if (voDcaEvent.getValue("contentType").compareTo(VOPlaybackSessionReport.SESSION_TYPE_VOD) == 0) {
                    this.mNextContentType = ContentType.VOD;
                } else if (voDcaEvent.getValue("contentType").compareTo("PREVIEW") == 0) {
                    this.mNextContentType = ContentType.PREVIEW;
                } else if (voDcaEvent.getValue("contentType").compareTo("NPVR") == 0) {
                    this.mNextContentType = ContentType.NPVR;
                } else if (voDcaEvent.getValue("contentType").compareTo("DOWNLOAD") == 0) {
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
        if (voDcaEvent.getType() == 3) {
            ContentType contentType = this.mNextContentType;
            this.mContentType = contentType;
            if (contentType == ContentType.LIVE) {
                this.mEventHandlerLivePlayStop.handleEvent(voDcaEvent);
                this.mEventHandlerLivePauseResume.setEnableHandleEvents(this.mConfiguration.mLivePauseResume_StateConfirmationPeriod != -1);
                this.mEventHandlerLiveSeek.setEnableHandleEvents(this.mConfiguration.mLiveSeek_StateConfirmationPeriod != -1);
                return;
            }
            if (this.mContentType == ContentType.STARTOVER) {
                this.mEventHandlerStartoverPlayStop.handleEvent(voDcaEvent);
                this.mEventHandlerStartoverPauseResume.setEnableHandleEvents(this.mConfiguration.mStartoverPauseResume_StateConfirmationPeriod != -1);
                this.mEventHandlerStartoverSeek.setEnableHandleEvents(this.mConfiguration.mStartoverSeek_StateConfirmationPeriod != -1);
                return;
            }
            if (this.mContentType == ContentType.CATCHUP) {
                this.mEventHandlerCatchupPlayStop.handleEvent(voDcaEvent);
                this.mEventHandlerCatchupPauseResume.setEnableHandleEvents(this.mConfiguration.mCatchupPauseResume_StateConfirmationPeriod != -1);
                this.mEventHandlerCatchupSeek.setEnableHandleEvents(this.mConfiguration.mCatchupSeek_StateConfirmationPeriod != -1);
                return;
            }
            if (this.mContentType == ContentType.VOD) {
                this.mEventHandlerVodPlayStop.handleEvent(voDcaEvent);
                this.mEventHandlerVodPauseResume.setEnableHandleEvents(this.mConfiguration.mVodPauseResume_StateConfirmationPeriod != -1);
                this.mEventHandlerVodSeek.setEnableHandleEvents(this.mConfiguration.mVodSeek_StateConfirmationPeriod != -1);
                return;
            }
            if (this.mContentType == ContentType.PREVIEW) {
                this.mEventHandlerPreviewPlayStop.handleEvent(voDcaEvent);
                this.mEventHandlerPreviewPauseResume.setEnableHandleEvents(this.mConfiguration.mPreviewPauseResume_StateConfirmationPeriod != -1);
                this.mEventHandlerPreviewSeek.setEnableHandleEvents(this.mConfiguration.mPreviewSeek_StateConfirmationPeriod != -1);
                return;
            } else if (this.mContentType == ContentType.NPVR) {
                this.mEventHandlerNpvrPlayStop.handleEvent(voDcaEvent);
                this.mEventHandlerNpvrPauseResume.setEnableHandleEvents(this.mConfiguration.mNpvrPauseResume_StateConfirmationPeriod != -1);
                this.mEventHandlerNpvrSeek.setEnableHandleEvents(this.mConfiguration.mNpvrSeek_StateConfirmationPeriod != -1);
                return;
            } else {
                if (this.mContentType == ContentType.DOWNLOAD) {
                    this.mEventHandlerDownloadPlayStop.handleEvent(voDcaEvent);
                    this.mEventHandlerDownloadPauseResume.setEnableHandleEvents(this.mConfiguration.mDownloadPauseResume_StateConfirmationPeriod != -1);
                    this.mEventHandlerDownloadSeek.setEnableHandleEvents(this.mConfiguration.mDownloadSeek_StateConfirmationPeriod != -1);
                    return;
                }
                return;
            }
        }
        if (voDcaEvent.getType() == 13) {
            if (this.mContentType == ContentType.LIVE) {
                this.mEventHandlerLivePlayStop.handleEvent(voDcaEvent);
                this.mEventHandlerLivePauseResume.setEnableHandleEvents(false);
                this.mEventHandlerLiveSeek.setEnableHandleEvents(false);
                this.mEventHandlerLiveSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.STARTOVER) {
                this.mEventHandlerStartoverPlayStop.handleEvent(voDcaEvent);
                this.mEventHandlerStartoverPauseResume.setEnableHandleEvents(false);
                this.mEventHandlerStartoverSeek.setEnableHandleEvents(false);
                this.mEventHandlerStartoverSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.CATCHUP) {
                this.mEventHandlerCatchupPlayStop.handleEvent(voDcaEvent);
                this.mEventHandlerCatchupPauseResume.setEnableHandleEvents(false);
                this.mEventHandlerCatchupSeek.setEnableHandleEvents(false);
                this.mEventHandlerCatchupSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.VOD) {
                this.mEventHandlerVodPlayStop.handleEvent(voDcaEvent);
                this.mEventHandlerVodPauseResume.setEnableHandleEvents(false);
                this.mEventHandlerVodSeek.setEnableHandleEvents(false);
                this.mEventHandlerVodSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.PREVIEW) {
                this.mEventHandlerPreviewPlayStop.handleEvent(voDcaEvent);
                this.mEventHandlerPreviewPauseResume.setEnableHandleEvents(false);
                this.mEventHandlerPreviewSeek.setEnableHandleEvents(false);
                this.mEventHandlerPreviewSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.NPVR) {
                this.mEventHandlerNpvrPlayStop.handleEvent(voDcaEvent);
                this.mEventHandlerNpvrPauseResume.setEnableHandleEvents(false);
                this.mEventHandlerNpvrSeek.setEnableHandleEvents(false);
                this.mEventHandlerNpvrSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.DOWNLOAD) {
                this.mEventHandlerDownloadPlayStop.handleEvent(voDcaEvent);
                this.mEventHandlerDownloadPauseResume.setEnableHandleEvents(false);
                this.mEventHandlerDownloadSeek.setEnableHandleEvents(false);
                this.mEventHandlerDownloadSeek.setInPauseState(false);
                return;
            }
            return;
        }
        if (voDcaEvent.getType() == 2) {
            if (this.mContentType == ContentType.LIVE) {
                this.mEventHandlerLivePauseResume.handleEvent(voDcaEvent);
                this.mEventHandlerLiveSeek.setInPauseState(true);
                return;
            }
            if (this.mContentType == ContentType.STARTOVER) {
                this.mEventHandlerStartoverPauseResume.handleEvent(voDcaEvent);
                this.mEventHandlerStartoverSeek.setInPauseState(true);
                return;
            }
            if (this.mContentType == ContentType.CATCHUP) {
                this.mEventHandlerCatchupPauseResume.handleEvent(voDcaEvent);
                this.mEventHandlerCatchupSeek.setInPauseState(true);
                return;
            }
            if (this.mContentType == ContentType.VOD) {
                this.mEventHandlerVodPauseResume.handleEvent(voDcaEvent);
                this.mEventHandlerVodSeek.setInPauseState(true);
                return;
            }
            if (this.mContentType == ContentType.PREVIEW) {
                this.mEventHandlerPreviewPauseResume.handleEvent(voDcaEvent);
                this.mEventHandlerPreviewSeek.setInPauseState(true);
                return;
            } else if (this.mContentType == ContentType.NPVR) {
                this.mEventHandlerNpvrPauseResume.handleEvent(voDcaEvent);
                this.mEventHandlerNpvrSeek.setInPauseState(true);
                return;
            } else {
                if (this.mContentType == ContentType.DOWNLOAD) {
                    this.mEventHandlerDownloadPauseResume.handleEvent(voDcaEvent);
                    this.mEventHandlerDownloadSeek.setInPauseState(true);
                    return;
                }
                return;
            }
        }
        if (voDcaEvent.getType() == 4) {
            if (this.mContentType == ContentType.LIVE) {
                this.mEventHandlerLivePauseResume.handleEvent(voDcaEvent);
                this.mEventHandlerLiveSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.STARTOVER) {
                this.mEventHandlerStartoverPauseResume.handleEvent(voDcaEvent);
                this.mEventHandlerStartoverSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.CATCHUP) {
                this.mEventHandlerCatchupPauseResume.handleEvent(voDcaEvent);
                this.mEventHandlerCatchupSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.VOD) {
                this.mEventHandlerVodPauseResume.handleEvent(voDcaEvent);
                this.mEventHandlerVodSeek.setInPauseState(false);
                return;
            }
            if (this.mContentType == ContentType.PREVIEW) {
                this.mEventHandlerPreviewPauseResume.handleEvent(voDcaEvent);
                this.mEventHandlerPreviewSeek.setInPauseState(false);
                return;
            } else if (this.mContentType == ContentType.NPVR) {
                this.mEventHandlerNpvrPauseResume.handleEvent(voDcaEvent);
                this.mEventHandlerNpvrSeek.setInPauseState(false);
                return;
            } else {
                if (this.mContentType == ContentType.DOWNLOAD) {
                    this.mEventHandlerDownloadPauseResume.handleEvent(voDcaEvent);
                    this.mEventHandlerDownloadSeek.setInPauseState(false);
                    return;
                }
                return;
            }
        }
        if (voDcaEvent.getType() == 6) {
            if (this.mContentType == ContentType.LIVE) {
                this.mEventHandlerLiveSeek.handleEvent(voDcaEvent);
                return;
            }
            if (this.mContentType == ContentType.STARTOVER) {
                this.mEventHandlerStartoverSeek.handleEvent(voDcaEvent);
                return;
            }
            if (this.mContentType == ContentType.CATCHUP) {
                this.mEventHandlerCatchupSeek.handleEvent(voDcaEvent);
                return;
            }
            if (this.mContentType == ContentType.VOD) {
                this.mEventHandlerVodSeek.handleEvent(voDcaEvent);
                return;
            }
            if (this.mContentType == ContentType.PREVIEW) {
                this.mEventHandlerPreviewSeek.handleEvent(voDcaEvent);
                return;
            } else if (this.mContentType == ContentType.NPVR) {
                this.mEventHandlerNpvrSeek.handleEvent(voDcaEvent);
                return;
            } else {
                if (this.mContentType == ContentType.DOWNLOAD) {
                    this.mEventHandlerDownloadSeek.handleEvent(voDcaEvent);
                    return;
                }
                return;
            }
        }
        if (voDcaEvent.getType() == 5) {
            if (this.mContentType == ContentType.LIVE) {
                this.mEventHandlerLiveSeek.handleEvent(voDcaEvent);
                return;
            }
            if (this.mContentType == ContentType.STARTOVER) {
                this.mEventHandlerStartoverSeek.handleEvent(voDcaEvent);
                return;
            }
            if (this.mContentType == ContentType.CATCHUP) {
                this.mEventHandlerCatchupSeek.handleEvent(voDcaEvent);
                return;
            }
            if (this.mContentType == ContentType.VOD) {
                this.mEventHandlerVodSeek.handleEvent(voDcaEvent);
                return;
            }
            if (this.mContentType == ContentType.PREVIEW) {
                this.mEventHandlerPreviewSeek.handleEvent(voDcaEvent);
                return;
            } else if (this.mContentType == ContentType.NPVR) {
                this.mEventHandlerNpvrSeek.handleEvent(voDcaEvent);
                return;
            } else {
                if (this.mContentType == ContentType.DOWNLOAD) {
                    this.mEventHandlerDownloadSeek.handleEvent(voDcaEvent);
                    return;
                }
                return;
            }
        }
        this.mEventHandlerProxy.handleEvent(voDcaEvent);
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

    @Override // com.viaccessorca.analytics.dca.VoDcaDispatcherListener
    public void onConfigurationReceived(VoDcaConfiguration voDcaConfiguration) {
        synchronized (this.mConfigurationSync) {
            this.mConfiguration.mOperatorName = voDcaConfiguration.mOperatorName;
            this.mConfiguration.mParsingSuccess = voDcaConfiguration.mParsingSuccess;
            this.mConfiguration.mLivePlayStop_StateConfirmationPeriod = voDcaConfiguration.mLivePlayStop_StateConfirmationPeriod;
            this.mConfiguration.mLivePauseResume_StateConfirmationPeriod = voDcaConfiguration.mLivePauseResume_StateConfirmationPeriod;
            this.mConfiguration.mLiveSeek_StateConfirmationPeriod = voDcaConfiguration.mLiveSeek_StateConfirmationPeriod;
            this.mConfiguration.mStartoverPlayStop_StateConfirmationPeriod = voDcaConfiguration.mStartoverPlayStop_StateConfirmationPeriod;
            this.mConfiguration.mStartoverPauseResume_StateConfirmationPeriod = voDcaConfiguration.mStartoverPauseResume_StateConfirmationPeriod;
            this.mConfiguration.mStartoverSeek_StateConfirmationPeriod = voDcaConfiguration.mStartoverSeek_StateConfirmationPeriod;
            this.mConfiguration.mCatchupPlayStop_StateConfirmationPeriod = voDcaConfiguration.mCatchupPlayStop_StateConfirmationPeriod;
            this.mConfiguration.mCatchupPauseResume_StateConfirmationPeriod = voDcaConfiguration.mCatchupPauseResume_StateConfirmationPeriod;
            this.mConfiguration.mCatchupSeek_StateConfirmationPeriod = voDcaConfiguration.mCatchupSeek_StateConfirmationPeriod;
            this.mConfiguration.mVodPlayStop_StateConfirmationPeriod = voDcaConfiguration.mVodPlayStop_StateConfirmationPeriod;
            this.mConfiguration.mVodPauseResume_StateConfirmationPeriod = voDcaConfiguration.mVodPauseResume_StateConfirmationPeriod;
            this.mConfiguration.mVodSeek_StateConfirmationPeriod = voDcaConfiguration.mVodSeek_StateConfirmationPeriod;
            this.mConfiguration.mPreviewPlayStop_StateConfirmationPeriod = voDcaConfiguration.mPreviewPlayStop_StateConfirmationPeriod;
            this.mConfiguration.mPreviewPauseResume_StateConfirmationPeriod = voDcaConfiguration.mPreviewPauseResume_StateConfirmationPeriod;
            this.mConfiguration.mPreviewSeek_StateConfirmationPeriod = voDcaConfiguration.mPreviewSeek_StateConfirmationPeriod;
            this.mConfiguration.mNpvrPlayStop_StateConfirmationPeriod = voDcaConfiguration.mNpvrPlayStop_StateConfirmationPeriod;
            this.mConfiguration.mNpvrPauseResume_StateConfirmationPeriod = voDcaConfiguration.mNpvrPauseResume_StateConfirmationPeriod;
            this.mConfiguration.mNpvrSeek_StateConfirmationPeriod = voDcaConfiguration.mNpvrSeek_StateConfirmationPeriod;
            this.mConfiguration.mDownloadPlayStop_StateConfirmationPeriod = voDcaConfiguration.mDownloadPlayStop_StateConfirmationPeriod;
            this.mConfiguration.mDownloadPauseResume_StateConfirmationPeriod = voDcaConfiguration.mDownloadPauseResume_StateConfirmationPeriod;
            this.mConfiguration.mDownloadSeek_StateConfirmationPeriod = voDcaConfiguration.mDownloadSeek_StateConfirmationPeriod;
            this.mConfiguration.mMappingEventTypes = voDcaConfiguration.mMappingEventTypes;
            this.mConfiguration.mMappingEventUrls = voDcaConfiguration.mMappingEventUrls;
            this.mConfiguration.mEventFieldValidateNames = voDcaConfiguration.mEventFieldValidateNames;
            this.mConfiguration.mEventFieldsValidatePerEvent = voDcaConfiguration.mEventFieldsValidatePerEvent;
            this.mConfiguration.mLiveSeek_SendDuringPause = voDcaConfiguration.mLiveSeek_SendDuringPause;
            this.mConfiguration.mStartoverSeek_SendDuringPause = voDcaConfiguration.mStartoverSeek_SendDuringPause;
            this.mConfiguration.mCatchupSeek_SendDuringPause = voDcaConfiguration.mCatchupSeek_SendDuringPause;
            this.mConfiguration.mVodSeek_SendDuringPause = voDcaConfiguration.mVodSeek_SendDuringPause;
            this.mConfiguration.mPreviewSeek_SendDuringPause = voDcaConfiguration.mPreviewSeek_SendDuringPause;
            this.mConfiguration.mNpvrSeek_SendDuringPause = voDcaConfiguration.mNpvrSeek_SendDuringPause;
            this.mConfiguration.mDownloadSeek_SendDuringPause = voDcaConfiguration.mDownloadSeek_SendDuringPause;
            this.mConfiguration.calcMaxconfirmationPeriod();
            this.mEventHandlerLivePlayStop.setStateConfirmationPeriod(this.mConfiguration.mLivePlayStop_StateConfirmationPeriod);
            boolean z = true;
            this.mEventHandlerLivePlayStop.setEnableHandleEvents(this.mConfiguration.mLivePlayStop_StateConfirmationPeriod != -1);
            this.mEventHandlerStartoverPlayStop.setStateConfirmationPeriod(this.mConfiguration.mStartoverPlayStop_StateConfirmationPeriod);
            this.mEventHandlerStartoverPlayStop.setEnableHandleEvents(this.mConfiguration.mStartoverPlayStop_StateConfirmationPeriod != -1);
            this.mEventHandlerCatchupPlayStop.setStateConfirmationPeriod(this.mConfiguration.mCatchupPlayStop_StateConfirmationPeriod);
            this.mEventHandlerCatchupPlayStop.setEnableHandleEvents(this.mConfiguration.mCatchupPlayStop_StateConfirmationPeriod != -1);
            this.mEventHandlerVodPlayStop.setStateConfirmationPeriod(this.mConfiguration.mVodPlayStop_StateConfirmationPeriod);
            this.mEventHandlerVodPlayStop.setEnableHandleEvents(this.mConfiguration.mVodPlayStop_StateConfirmationPeriod != -1);
            this.mEventHandlerPreviewPlayStop.setStateConfirmationPeriod(this.mConfiguration.mPreviewPlayStop_StateConfirmationPeriod);
            this.mEventHandlerPreviewPlayStop.setEnableHandleEvents(this.mConfiguration.mPreviewPlayStop_StateConfirmationPeriod != -1);
            this.mEventHandlerNpvrPlayStop.setStateConfirmationPeriod(this.mConfiguration.mNpvrPlayStop_StateConfirmationPeriod);
            this.mEventHandlerNpvrPlayStop.setEnableHandleEvents(this.mConfiguration.mNpvrPlayStop_StateConfirmationPeriod != -1);
            this.mEventHandlerDownloadPlayStop.setStateConfirmationPeriod(this.mConfiguration.mDownloadPlayStop_StateConfirmationPeriod);
            VoDcaEventHandlerZapFilter voDcaEventHandlerZapFilter = this.mEventHandlerDownloadPlayStop;
            if (this.mConfiguration.mDownloadPlayStop_StateConfirmationPeriod == -1) {
                z = false;
            }
            voDcaEventHandlerZapFilter.setEnableHandleEvents(z);
            this.mEventHandlerLivePauseResume.setStateConfirmationPeriod(this.mConfiguration.mLivePauseResume_StateConfirmationPeriod);
            this.mEventHandlerStartoverPauseResume.setStateConfirmationPeriod(this.mConfiguration.mStartoverPauseResume_StateConfirmationPeriod);
            this.mEventHandlerCatchupPauseResume.setStateConfirmationPeriod(this.mConfiguration.mCatchupPauseResume_StateConfirmationPeriod);
            this.mEventHandlerVodPauseResume.setStateConfirmationPeriod(this.mConfiguration.mVodPauseResume_StateConfirmationPeriod);
            this.mEventHandlerPreviewPauseResume.setStateConfirmationPeriod(this.mConfiguration.mPreviewPauseResume_StateConfirmationPeriod);
            this.mEventHandlerNpvrPauseResume.setStateConfirmationPeriod(this.mConfiguration.mNpvrPauseResume_StateConfirmationPeriod);
            this.mEventHandlerDownloadPauseResume.setStateConfirmationPeriod(this.mConfiguration.mDownloadPauseResume_StateConfirmationPeriod);
            this.mEventHandlerLiveSeek.setStateConfirmationPeriod(this.mConfiguration.mLiveSeek_StateConfirmationPeriod);
            this.mEventHandlerStartoverSeek.setStateConfirmationPeriod(this.mConfiguration.mStartoverSeek_StateConfirmationPeriod);
            this.mEventHandlerCatchupSeek.setStateConfirmationPeriod(this.mConfiguration.mCatchupSeek_StateConfirmationPeriod);
            this.mEventHandlerVodSeek.setStateConfirmationPeriod(this.mConfiguration.mVodSeek_StateConfirmationPeriod);
            this.mEventHandlerPreviewSeek.setStateConfirmationPeriod(this.mConfiguration.mPreviewSeek_StateConfirmationPeriod);
            this.mEventHandlerNpvrSeek.setStateConfirmationPeriod(this.mConfiguration.mNpvrSeek_StateConfirmationPeriod);
            this.mEventHandlerDownloadSeek.setStateConfirmationPeriod(this.mConfiguration.mDownloadSeek_StateConfirmationPeriod);
            this.mEventHandlerLiveSeek.setEnableSendDuringPause(this.mConfiguration.mLiveSeek_SendDuringPause);
            this.mEventHandlerStartoverSeek.setEnableSendDuringPause(this.mConfiguration.mStartoverSeek_SendDuringPause);
            this.mEventHandlerCatchupSeek.setEnableSendDuringPause(this.mConfiguration.mCatchupSeek_SendDuringPause);
            this.mEventHandlerVodSeek.setEnableSendDuringPause(this.mConfiguration.mVodSeek_SendDuringPause);
            this.mEventHandlerPreviewSeek.setEnableSendDuringPause(this.mConfiguration.mPreviewSeek_SendDuringPause);
            this.mEventHandlerNpvrSeek.setEnableSendDuringPause(this.mConfiguration.mNpvrSeek_SendDuringPause);
            this.mEventHandlerDownloadSeek.setEnableSendDuringPause(this.mConfiguration.mDownloadSeek_SendDuringPause);
        }
        if (!this.mReloadConfigurationFlag) {
            new Handler().postDelayed(new Runnable() { // from class: com.viaccessorca.analytics.dca.VoDcaBusinessLogic.1
                @Override // java.lang.Runnable
                public void run() {
                    VoDcaBusinessLogic.this.mListener.onInitBusinessLogicCompleted();
                }
            }, 0L);
        } else {
            this.mReloadConfigurationFlag = false;
            new Handler().postDelayed(new Runnable() { // from class: com.viaccessorca.analytics.dca.VoDcaBusinessLogic.2
                @Override // java.lang.Runnable
                public void run() {
                    VoDcaBusinessLogic.this.mListener.onConfigurationBusinessLogicReloaded();
                }
            }, 0L);
        }
    }

    public Class voPlayerClass() {
        try {
            Class<?> cls = Class.forName(VOPLAYER_CLASS_PATH);
            Log.v(TAG, "VOPlayer class found, Both DCA and VOPlayer integrated in this application ");
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

    @Override // com.viaccessorca.analytics.dca.VoDcaDispatcherListener
    public void onError() {
        if (!this.mReloadConfigurationFlag) {
            new Handler().postDelayed(new Runnable() { // from class: com.viaccessorca.analytics.dca.VoDcaBusinessLogic.3
                @Override // java.lang.Runnable
                public void run() {
                    VoDcaBusinessLogic.this.mListener.onInitBusinessLogicFailed();
                }
            }, 0L);
        } else {
            this.mReloadConfigurationFlag = false;
            new Handler().postDelayed(new Runnable() { // from class: com.viaccessorca.analytics.dca.VoDcaBusinessLogic.4
                @Override // java.lang.Runnable
                public void run() {
                    VoDcaBusinessLogic.this.mListener.onConfigurationBusinessLogicReloadedFailed();
                }
            }, 0L);
        }
    }

    @Override // com.viaccessorca.analytics.dca.VoDcaDispatcherListener
    public void onEventSent(VoDcaEvent voDcaEvent) {
        voDcaEvent.setCustomParameter(EVENT_INTERNAL_KEY, EVENT_INTERNAL_VALUE_ALREADY_SENT);
        try {
            synchronized (this.mQueueSync) {
                try {
                    this.mQueue.add(voDcaEvent);
                    this.mQueueSync.notify();
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.viaccessorca.analytics.dca.VoDcaEventHandlerListener
    public void onInternalEvent(VoDcaEvent voDcaEvent) {
        voDcaEvent.setCustomParameter(EVENT_INTERNAL_KEY, EVENT_INTERNAL_VALUE_FAKE_EVENT);
        try {
            synchronized (this.mQueueSync) {
                try {
                    this.mQueue.add(voDcaEvent);
                    this.mQueueSync.notify();
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
