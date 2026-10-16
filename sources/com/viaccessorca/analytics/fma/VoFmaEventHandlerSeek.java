package com.viaccessorca.analytics.fma;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class VoFmaEventHandlerSeek extends VoFmaEventHandler {
    private static final String TAG = "VO_FMA";
    protected String mAssetId;
    protected String mAssetIdNext;
    protected String mChannelId;
    protected String mChannelIdNext;
    protected String mChannelName;
    protected String mChannelNameNext;
    protected boolean mChannelRequired;
    protected boolean mContinue;
    protected String mConvertFromField;
    protected String mConvertToField;
    private boolean mEnableHandleEvents;
    private boolean mEnableSendDuringPause;
    protected String mEpisodeId;
    protected String mEpisodeIdNext;
    protected String mEventBreak_Pause;
    protected String mEventBreak_Stop;
    protected int mEventIn_Off;
    protected int mEventIn_On;
    protected String mEventOut;
    private String mFromTimeField;
    private boolean mInPauseState;
    protected String mInternalAssetId;
    protected String mInternalAssetIdNext;
    protected String mName;
    protected String mNameNext;
    protected boolean mPreviewInfoRequired;
    protected String mProgramId;
    protected String mProgramIdNext;
    protected String mProgramName;
    protected String mProgramNameNext;
    protected boolean mProgramRequired;
    protected String mProviderId;
    protected String mProviderIdNext;
    protected VoFmaEventQueue mQueue;
    private SessionState mState;
    protected int mStateConfirmationPeriod;
    private Object mSync;
    private String mToTimeField;
    protected boolean mVodInfoRequired;

    private enum CleanupState {
        Init,
        On,
        Off
    }

    private enum SessionState {
        Idle,
        On,
        Off
    }

    public VoFmaEventHandlerSeek(VoFmaDispatcher voFmaDispatcher, VoFmaConfiguration voFmaConfiguration) {
        super(voFmaDispatcher, voFmaConfiguration);
        this.mQueue = new VoFmaEventQueue();
        this.mContinue = true;
        this.mState = SessionState.Idle;
        this.mChannelRequired = false;
        this.mChannelId = null;
        this.mChannelIdNext = null;
        this.mChannelName = null;
        this.mChannelNameNext = null;
        this.mProgramRequired = false;
        this.mProgramId = null;
        this.mProgramIdNext = null;
        this.mProgramName = null;
        this.mProgramNameNext = null;
        this.mProgramRequired = false;
        this.mProgramId = null;
        this.mProgramIdNext = null;
        this.mProgramName = null;
        this.mProgramNameNext = null;
        this.mVodInfoRequired = false;
        this.mAssetId = null;
        this.mAssetIdNext = null;
        this.mProviderId = null;
        this.mProviderIdNext = null;
        this.mEpisodeId = null;
        this.mEpisodeIdNext = null;
        this.mEventIn_On = 0;
        this.mEventIn_Off = 0;
        this.mEventOut = null;
        this.mStateConfirmationPeriod = 0;
        this.mSync = new Object();
        this.mEnableHandleEvents = false;
        this.mEnableSendDuringPause = false;
        this.mInPauseState = false;
        this.mFromTimeField = null;
        this.mToTimeField = null;
        this.mConvertFromField = null;
        this.mConvertToField = null;
    }

    public void setConvertFromToField(String str, String str2) {
        this.mConvertFromField = str;
        this.mConvertToField = str2;
    }

    public void setFromToTimeFields(String str, String str2) {
        this.mFromTimeField = str;
        this.mToTimeField = str2;
    }

    public void setEnableSendDuringPause(boolean z) {
        this.mEnableSendDuringPause = z;
    }

    public void setInPauseState(boolean z) {
        this.mInPauseState = z;
    }

    public void setEnableHandleEvents(boolean z) {
        this.mEnableHandleEvents = z;
    }

    public void setInputEvents(int i, int i2) {
        this.mEventIn_On = i;
        this.mEventIn_Off = i2;
    }

    public void setOutputEvent(String str) {
        this.mEventOut = str;
    }

    public void setBreakEvents(String str, String str2) {
        this.mEventBreak_Stop = str;
        this.mEventBreak_Pause = str2;
    }

    public void setChannelRequired(boolean z) {
        this.mChannelRequired = z;
    }

    public void setProgramRequired(boolean z) {
        this.mProgramRequired = z;
    }

    public void setVodInfoRequired(boolean z) {
        this.mVodInfoRequired = z;
    }

    public void setPreviewInfoRequired(boolean z) {
        this.mPreviewInfoRequired = z;
    }

    public void setStateConfirmationPeriod(int i) {
        this.mStateConfirmationPeriod = i;
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaEventHandler
    public long tick() {
        return checkAndDispatch();
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaEventHandler
    public void handleEvent(VoFmaEvent voFmaEvent) {
        synchronized (this.mSync) {
            addEvent(voFmaEvent);
        }
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaEventHandler
    public void stop() {
        this.mContinue = false;
    }

    private long checkAndDispatch() {
        VoFmaEventQueue.EventNode eventNodeFirst;
        VoFmaEventQueue.EventNode eventNode;
        synchronized (this.mSync) {
            if (this.mQueue.first() == null) {
                return 0L;
            }
            long jHandleSkipAndInsert = handleSkipAndInsert();
            if (jHandleSkipAndInsert > 0) {
                return jHandleSkipAndInsert;
            }
            cleanUpQueue();
            VoFmaEventQueue.EventNode eventNodeFirst2 = this.mQueue.first();
            while (this.mContinue && eventNodeFirst2 != null) {
                if (eventNodeFirst2.mEvent.getType() == this.mEventIn_On) {
                    long currentTime = (eventNodeFirst2.mEvent.getCurrentTime() + ((long) (this.mConfiguration.mMax_StateConfirmationPeriod * 1000))) - System.currentTimeMillis();
                    if (currentTime > 0) {
                        return currentTime;
                    }
                    synchronized (this.mSync) {
                        eventNode = eventNodeFirst2.mNext;
                    }
                    if (eventNode == null) {
                        return 0L;
                    }
                    long currentTime2 = (eventNode.mEvent.getCurrentTime() + ((long) (this.mConfiguration.mMax_StateConfirmationPeriod * 1000))) - System.currentTimeMillis();
                    if (currentTime2 > 0) {
                        return currentTime2;
                    }
                    sendEvent(eventNodeFirst2.mEvent, eventNode.mEvent);
                    synchronized (this.mSync) {
                        this.mQueue.delete(eventNodeFirst2);
                        this.mQueue.delete(eventNode);
                    }
                } else {
                    synchronized (this.mSync) {
                        this.mQueue.delete(eventNodeFirst2);
                    }
                }
                synchronized (this.mSync) {
                    eventNodeFirst = this.mQueue.first();
                }
                eventNodeFirst2 = eventNodeFirst;
            }
            return 0L;
        }
    }

    private long handleSkipAndInsert() {
        String value;
        long j;
        long j2;
        VoFmaEventQueue.EventNode eventNode;
        VoFmaEventQueue.EventNode eventNodeFirst = this.mQueue.first();
        while (eventNodeFirst != null) {
            VoFmaEventQueue.EventNode eventNode2 = eventNodeFirst.mNext;
            if (eventNode2 == null) {
                long currentTime = (eventNodeFirst.mEvent.getCurrentTime() + ((long) (this.mConfiguration.mMax_StateConfirmationPeriod * 1000))) - System.currentTimeMillis();
                if (currentTime > 0) {
                    return currentTime;
                }
            }
            eventNodeFirst = eventNode2;
        }
        VoFmaEventQueue.EventNode eventNodeFirst2 = this.mQueue.first();
        while (eventNodeFirst2 != null) {
            VoFmaEventQueue.EventNode eventNode3 = eventNodeFirst2.mNext;
            if (eventNodeFirst2.mEvent.getValue("_intr") != null && (value = eventNodeFirst2.mEvent.getValue("Sequence")) != null && value.equals("RemovePS")) {
                String value2 = eventNodeFirst2.mEvent.getValue("Before");
                String value3 = eventNodeFirst2.mEvent.getValue("After");
                if (value2 == null || value3 == null) {
                    j = 0;
                    j2 = 0;
                } else {
                    j = Long.parseLong(value2);
                    j2 = Long.parseLong(value3);
                }
                VoFmaEventQueue.EventNode eventNode4 = eventNodeFirst2.mPrev;
                if (eventNode4 != null) {
                    if (eventNode4.mEvent.getType() == 6) {
                        if (eventNode4.mTime > j && eventNode4.mTime < j2) {
                            this.mQueue.delete(eventNode4);
                            this.mQueue.delete(eventNodeFirst2);
                        }
                    } else if (eventNode4.mEvent.getType() == 5 && (eventNode = eventNode4.mPrev) != null && eventNode.mEvent.getType() == 6 && eventNode4.mTime > j && eventNode4.mTime < j2) {
                        this.mQueue.delete(eventNode);
                        this.mQueue.delete(eventNode4);
                        this.mQueue.delete(eventNodeFirst2);
                    }
                }
            }
            eventNodeFirst2 = eventNode3;
        }
        VoFmaEventQueue.EventNode eventNodeFirst3 = this.mQueue.first();
        while (eventNodeFirst3 != null) {
            VoFmaEventQueue.EventNode eventNode5 = eventNodeFirst3.mNext;
            if (eventNodeFirst3.mEvent.getValue("_intr") != null) {
                this.mQueue.delete(eventNodeFirst3);
            }
            eventNodeFirst3 = eventNode5;
        }
        return 0L;
    }

    private boolean isSameBroafmast(VoFmaEvent voFmaEvent, VoFmaEvent voFmaEvent2) {
        try {
            if (!this.mChannelRequired && !this.mProgramRequired) {
                if (!this.mVodInfoRequired) {
                    return this.mPreviewInfoRequired && voFmaEvent.getValue("asset_id").equals(voFmaEvent2.getValue("asset_id")) && voFmaEvent.getValue("name").equals(voFmaEvent2.getValue("name"));
                }
                if (!voFmaEvent.getValue("asset_id").equals(voFmaEvent2.getValue("asset_id")) || !voFmaEvent.getValue("provider_id").equals(voFmaEvent2.getValue("provider_id"))) {
                    return false;
                }
                if (voFmaEvent.getValue("episode_id") != null && voFmaEvent2.getValue("episode_id") != null) {
                    if (!voFmaEvent.getValue("episode_id").equals(voFmaEvent2.getValue("episode_id"))) {
                        return false;
                    }
                } else if ((voFmaEvent.getValue("episode_id") != null && voFmaEvent2.getValue("episode_id") == null) || (voFmaEvent.getValue("episode_id") == null && voFmaEvent2.getValue("episode_id") != null)) {
                    return false;
                }
                return true;
            }
            boolean z = !this.mChannelRequired || voFmaEvent.getValue("channel_id").equals(voFmaEvent2.getValue("channel_id"));
            if (z && this.mProgramRequired) {
                return voFmaEvent.getValue("program_id").equals(voFmaEvent2.getValue("program_id"));
            }
            return z;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    private void cleanUpQueue() {
        VoFmaEventQueue.EventNode eventNode;
        VoFmaEventQueue.EventNode eventNode2;
        CleanupState cleanupState;
        VoFmaEventQueue.EventNode eventNodeFirst = this.mQueue.first();
        while (eventNodeFirst != null && eventNodeFirst.mNext != null) {
            VoFmaEventQueue.EventNode eventNode3 = eventNodeFirst.mNext;
            VoFmaEventQueue.EventNode eventNode4 = eventNode3.mNext;
            if (eventNodeFirst.mEvent.getType() == this.mEventIn_On && eventNode3.mEvent.getType() == this.mEventIn_On && isSameBroafmast(eventNodeFirst.mEvent, eventNode3.mEvent)) {
                this.mQueue.delete(eventNodeFirst);
            }
            eventNodeFirst = eventNode3;
        }
        VoFmaEventQueue.EventNode eventNodeFirst2 = this.mQueue.first();
        while (eventNodeFirst2 != null && eventNodeFirst2.mNext != null) {
            VoFmaEventQueue.EventNode eventNode5 = eventNodeFirst2.mNext;
            VoFmaEventQueue.EventNode eventNode6 = eventNode5.mNext;
            if (eventNodeFirst2.mEvent.getType() == this.mEventIn_Off && eventNode5.mEvent.getType() == this.mEventIn_Off && isSameBroafmast(eventNodeFirst2.mEvent, eventNode5.mEvent)) {
                this.mQueue.delete(eventNode5);
            } else {
                eventNodeFirst2 = eventNode5;
            }
        }
        CleanupState cleanupState2 = CleanupState.Init;
        VoFmaEventQueue.EventNode eventNodeFirst3 = this.mQueue.first();
        while (eventNodeFirst3 != null) {
            int i = AnonymousClass1.$SwitchMap$com$viaccessorca$analytics$fma$VoFmaEventHandlerSeek$CleanupState[cleanupState2.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    eventNode = eventNodeFirst3.mNext;
                    if (eventNode != null) {
                        if (eventNode.mEvent.getType() == this.mEventIn_Off) {
                            cleanupState2 = CleanupState.Off;
                        } else {
                            String value = eventNode.mEvent.getValue("event_type");
                            if (value.equals(this.mEventBreak_Stop) || value.equals(this.mEventBreak_Pause)) {
                                eventNode2 = eventNode.mNext;
                                this.mQueue.delete(eventNodeFirst3);
                                this.mQueue.delete(eventNode);
                                cleanupState = CleanupState.Init;
                                CleanupState cleanupState3 = cleanupState;
                                eventNodeFirst3 = eventNode2;
                                cleanupState2 = cleanupState3;
                            } else {
                                this.mQueue.delete(eventNode);
                                cleanupState2 = CleanupState.On;
                            }
                        }
                    }
                    eventNodeFirst3 = eventNode;
                } else if (i == 3) {
                    eventNode = eventNodeFirst3.mNext;
                    if (eventNode == null) {
                        eventNodeFirst3 = eventNode;
                    } else if (eventNode.mEvent.getType() == this.mEventIn_On) {
                        if (isSameBroafmast(eventNodeFirst3.mEvent, eventNode.mEvent) && eventNode.mEvent.getCurrentTime() - eventNodeFirst3.mEvent.getCurrentTime() < this.mStateConfirmationPeriod * 1000) {
                            eventNode2 = eventNode.mNext;
                            this.mQueue.delete(eventNodeFirst3);
                            this.mQueue.delete(eventNode);
                            cleanupState = CleanupState.On;
                        } else {
                            eventNode2 = eventNode.mNext;
                            cleanupState = CleanupState.Init;
                        }
                        CleanupState cleanupState4 = cleanupState;
                        eventNodeFirst3 = eventNode2;
                        cleanupState2 = cleanupState4;
                    } else {
                        this.mQueue.delete(eventNode);
                        cleanupState2 = CleanupState.Off;
                    }
                }
            } else if (eventNodeFirst3.mEvent.getType() != this.mEventIn_On) {
                return;
            } else {
                cleanupState2 = CleanupState.On;
            }
        }
    }

    /* JADX INFO: renamed from: com.viaccessorca.analytics.fma.VoFmaEventHandlerSeek$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEventHandlerSeek$CleanupState;

        static {
            int[] iArr = new int[CleanupState.values().length];
            $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEventHandlerSeek$CleanupState = iArr;
            try {
                iArr[CleanupState.Init.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEventHandlerSeek$CleanupState[CleanupState.On.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEventHandlerSeek$CleanupState[CleanupState.Off.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void sendEvent(VoFmaEvent voFmaEvent, VoFmaEvent voFmaEvent2) {
        synchronized (this.mSync) {
            voFmaEvent2.setCustomType(this.mEventOut);
            String value = voFmaEvent.getValue(this.mToTimeField);
            if (value == null) {
                Log.e(TAG, "Missing " + this.mToTimeField + " in event!!");
                return;
            }
            voFmaEvent2.setCustomParameter(this.mFromTimeField, value);
            if (!this.mConfiguration.eventFieldValidate(voFmaEvent2)) {
                Log.e(TAG, "Event was not sent! Field validation failed");
                return;
            }
            if (!this.mConfiguration.mParsingSuccess) {
                Log.e(TAG, "Event was not sent! Error in given configuration");
                return;
            }
            String urlByEventType = this.mConfiguration.getUrlByEventType(voFmaEvent2.getValue("event_type"));
            if (urlByEventType != null) {
                if (urlByEventType.toLowerCase().indexOf("null".toLowerCase()) == -1) {
                    this.mDispatcher.sendEvent(voFmaEvent2, urlByEventType);
                    return;
                }
                Log.w(TAG, "URL not valid : " + urlByEventType + ", Event " + voFmaEvent2.getValue("event_type") + " dropped.");
                return;
            }
            Log.w(TAG, "Event Type " + voFmaEvent2.getValue("event_type") + " is not supported in the configuration. Event dropped.");
        }
    }

    private void addEvent(VoFmaEvent voFmaEvent) {
        if (this.mEnableHandleEvents) {
            if (this.mEnableSendDuringPause || !this.mInPauseState) {
                if (voFmaEvent.getType() == this.mEventIn_On) {
                    if (this.mChannelRequired && this.mChannelIdNext == null) {
                        return;
                    }
                    if (this.mProgramRequired && this.mProgramIdNext == null) {
                        return;
                    }
                    if (this.mVodInfoRequired && (this.mAssetIdNext == null || this.mProviderIdNext == null)) {
                        return;
                    }
                    if (this.mPreviewInfoRequired && (this.mInternalAssetIdNext == null || this.mNameNext == null)) {
                        return;
                    }
                    this.mChannelId = this.mChannelIdNext;
                    this.mChannelName = this.mChannelNameNext;
                    this.mProgramId = this.mProgramIdNext;
                    this.mProgramName = this.mProgramNameNext;
                    this.mAssetId = this.mAssetIdNext;
                    this.mProviderId = this.mProviderIdNext;
                    this.mEpisodeId = this.mEpisodeIdNext;
                    this.mInternalAssetId = this.mInternalAssetIdNext;
                    this.mName = this.mNameNext;
                }
                if (voFmaEvent.getType() == this.mEventIn_Off) {
                    if (this.mChannelRequired && this.mChannelId == null) {
                        return;
                    }
                    if (this.mProgramRequired && this.mProgramId == null) {
                        return;
                    }
                    if (this.mVodInfoRequired && (this.mAssetIdNext == null || this.mProviderIdNext == null)) {
                        return;
                    }
                    if (this.mPreviewInfoRequired && (this.mInternalAssetIdNext == null || this.mNameNext == null)) {
                        return;
                    }
                }
                if (this.mChannelRequired && this.mChannelId != null) {
                    voFmaEvent.setParameter(VoFmaEvent.EventKey.CHANNEL_ID, this.mChannelId);
                    if (this.mChannelName != null) {
                        voFmaEvent.setParameter(VoFmaEvent.EventKey.CHANNEL_NAME, this.mChannelName);
                    }
                }
                if (this.mProgramRequired && this.mProgramId != null) {
                    voFmaEvent.setParameter(VoFmaEvent.EventKey.PROGRAM_ID, this.mProgramId);
                    if (this.mProgramName != null) {
                        voFmaEvent.setParameter(VoFmaEvent.EventKey.PROGRAM_NAME, this.mProgramName);
                    }
                }
                if (this.mVodInfoRequired) {
                    if (this.mAssetId != null) {
                        voFmaEvent.setParameter(VoFmaEvent.EventKey.ASSET_ID, this.mAssetId);
                    }
                    if (this.mProviderId != null) {
                        voFmaEvent.setParameter(VoFmaEvent.EventKey.PROVIDER_ID, this.mProviderId);
                    }
                    if (this.mEpisodeId != null) {
                        voFmaEvent.setParameter(VoFmaEvent.EventKey.EPISODE_ID, this.mEpisodeId);
                    }
                }
                if (this.mPreviewInfoRequired) {
                    if (this.mInternalAssetId != null) {
                        voFmaEvent.setParameter(VoFmaEvent.EventKey.ASSET_ID, this.mInternalAssetId);
                    }
                    if (this.mName != null) {
                        voFmaEvent.setParameter(VoFmaEvent.EventKey.NAME, this.mName);
                    }
                }
                if (voFmaEvent.keyExists(this.mConvertFromField)) {
                    voFmaEvent.setCustomParameter(this.mConvertToField, voFmaEvent.getValue(this.mConvertFromField));
                    voFmaEvent.keyDelete(this.mConvertFromField);
                }
                this.mQueue.add(voFmaEvent);
            }
        }
    }

    protected void setChannel(String str, String str2) {
        synchronized (this.mSync) {
            this.mChannelIdNext = str;
            this.mChannelNameNext = str2;
        }
    }

    protected void setProgram(String str, String str2) {
        synchronized (this.mSync) {
            this.mProgramIdNext = str;
            this.mProgramNameNext = str2;
        }
    }

    protected void setVodAsset(String str) {
        synchronized (this.mSync) {
            this.mAssetIdNext = str;
        }
    }

    protected void setVodProvider(String str) {
        synchronized (this.mSync) {
            this.mProviderIdNext = str;
        }
    }

    protected void setVodEpisode(String str) {
        synchronized (this.mSync) {
            this.mEpisodeIdNext = str;
        }
    }

    protected void setPreviewAsset(String str) {
        synchronized (this.mSync) {
            this.mInternalAssetIdNext = str;
        }
    }

    protected void setPreviewName(String str) {
        synchronized (this.mSync) {
            this.mNameNext = str;
        }
    }
}
