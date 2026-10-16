package com.viaccessorca.analytics.dca;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class VoDcaEventHandlerZapFilter extends VoDcaEventHandler {
    private static final String TAG = "VO_DCA";
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
    protected String mEpisodeId;
    protected String mEpisodeIdNext;
    protected int mEventIn_Off;
    protected int mEventIn_On;
    protected String mEventOut_Off;
    protected String mEventOut_On;
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
    protected VoDcaEventQueue mQueue;
    protected String mResetEvent;
    private SessionState mState;
    protected int mStateConfirmationPeriod;
    private Object mSync;
    protected boolean mVodInfoRequired;

    private enum CleanupState {
        Init,
        On,
        Off,
        On_After_Off
    }

    private enum SessionState {
        Idle,
        On,
        Off
    }

    public VoDcaEventHandlerZapFilter(VoDcaDispatcher voDcaDispatcher, VoDcaConfiguration voDcaConfiguration) {
        super(voDcaDispatcher, voDcaConfiguration);
        this.mQueue = new VoDcaEventQueue();
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
        this.mVodInfoRequired = false;
        this.mAssetId = null;
        this.mAssetIdNext = null;
        this.mProviderId = null;
        this.mProviderIdNext = null;
        this.mEpisodeId = null;
        this.mEpisodeIdNext = null;
        this.mPreviewInfoRequired = false;
        this.mInternalAssetId = null;
        this.mInternalAssetIdNext = null;
        this.mName = null;
        this.mNameNext = null;
        this.mEventIn_On = 0;
        this.mEventIn_Off = 0;
        this.mEventOut_On = null;
        this.mEventOut_Off = null;
        this.mResetEvent = null;
        this.mStateConfirmationPeriod = 0;
        this.mEnableHandleEvents = false;
        this.mSync = new Object();
        this.mConvertFromField = null;
        this.mConvertToField = null;
    }

    public void setConvertFromToField(String str, String str2) {
        this.mConvertFromField = str;
        this.mConvertToField = str2;
    }

    public void setEnableHandleEvents(boolean z) {
        this.mEnableHandleEvents = z;
    }

    public void setInputEvents(int i, int i2) {
        this.mEventIn_On = i;
        this.mEventIn_Off = i2;
    }

    public void setOutputEvents(String str, String str2) {
        this.mEventOut_On = str;
        this.mEventOut_Off = str2;
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

    public void setResetEvent(String str) {
        this.mResetEvent = str;
    }

    @Override // com.viaccessorca.analytics.dca.VoDcaEventHandler
    public long tick() {
        return checkAndDispatch();
    }

    @Override // com.viaccessorca.analytics.dca.VoDcaEventHandler
    public void handleEvent(VoDcaEvent voDcaEvent) {
        synchronized (this.mSync) {
            addEvent(voDcaEvent);
        }
    }

    @Override // com.viaccessorca.analytics.dca.VoDcaEventHandler
    public void stop() {
        this.mContinue = false;
    }

    private long checkAndDispatch() {
        synchronized (this.mSync) {
            if (this.mQueue.isEmpty()) {
                return 0L;
            }
            this.mQueue.first();
            long jCleanUpQueue = cleanUpQueue();
            if (jCleanUpQueue > 0) {
                return jCleanUpQueue;
            }
            long jHandleSkipAndInsert = handleSkipAndInsert();
            if (jHandleSkipAndInsert > 0) {
                return jHandleSkipAndInsert;
            }
            long jCleanUpQueue2 = cleanUpQueue();
            if (jCleanUpQueue2 > 0) {
                return jCleanUpQueue2;
            }
            VoDcaEventQueue.EventNode eventNodeFirst = this.mQueue.first();
            while (this.mContinue && eventNodeFirst != null) {
                long currentTime = (eventNodeFirst.mEvent.getCurrentTime() + ((long) (this.mStateConfirmationPeriod * 1000))) - System.currentTimeMillis();
                if (currentTime > 0) {
                    return currentTime;
                }
                if (eventNodeFirst != null) {
                    if ((eventNodeFirst.mEvent.getCurrentTime() + ((long) (this.mStateConfirmationPeriod * 1000))) - System.currentTimeMillis() <= 0) {
                        sendEvent(eventNodeFirst.mEvent);
                        synchronized (this.mSync) {
                            this.mQueue.delete(eventNodeFirst);
                        }
                    }
                }
                synchronized (this.mSync) {
                    eventNodeFirst = this.mQueue.first();
                }
            }
            return 0L;
        }
    }

    private long handleSkipAndInsert() {
        String value;
        VoDcaEventQueue.EventNode eventNode;
        long j;
        long j2;
        VoDcaEventQueue.EventNode eventNode2;
        VoDcaEventQueue.EventNode eventNodeFirst = this.mQueue.first();
        while (eventNodeFirst != null) {
            VoDcaEventQueue.EventNode eventNode3 = eventNodeFirst.mNext;
            if (eventNode3 == null) {
                long currentTime = (eventNodeFirst.mEvent.getCurrentTime() + ((long) (this.mConfiguration.mMax_StateConfirmationPeriod * 1000))) - System.currentTimeMillis();
                if (currentTime > 0) {
                    return currentTime;
                }
            }
            eventNodeFirst = eventNode3;
        }
        VoDcaEventQueue.EventNode eventNodeFirst2 = this.mQueue.first();
        while (eventNodeFirst2 != null) {
            VoDcaEventQueue.EventNode eventNode4 = eventNodeFirst2.mNext;
            if (eventNodeFirst2.mEvent.getValue("_intr") != null && (value = eventNodeFirst2.mEvent.getValue("Sequence")) != null) {
                if (value.equals("RemovePS")) {
                    String value2 = eventNodeFirst2.mEvent.getValue("Before");
                    String value3 = eventNodeFirst2.mEvent.getValue("After");
                    if (value2 == null || value3 == null) {
                        j = 0;
                        j2 = 0;
                    } else {
                        j2 = Long.parseLong(value2);
                        j = Long.parseLong(value3);
                    }
                    VoDcaEventQueue.EventNode eventNode5 = eventNodeFirst2.mPrev;
                    if (eventNode5 != null) {
                        if (eventNode5.mEvent.getType() == 2) {
                            if (eventNode5.mTime > j2 && eventNode5.mTime < j) {
                                this.mQueue.delete(eventNode5);
                                this.mQueue.delete(eventNodeFirst2);
                            }
                        } else if (eventNode5.mEvent.getType() == 4 && (eventNode2 = eventNode5.mPrev) != null && eventNode2.mEvent.getType() == 2 && eventNode5.mTime > j2 && eventNode5.mTime < j) {
                            this.mQueue.delete(eventNode2);
                            this.mQueue.delete(eventNode5);
                            this.mQueue.delete(eventNodeFirst2);
                        }
                    }
                } else if (value.equals("RemoveSP") && (((eventNode = eventNodeFirst2.mPrev) == null && this.mState == SessionState.On) || (eventNode != null && eventNode.mEvent.getType() == 2))) {
                    eventNodeFirst2.mEvent.keyDelete("_intr");
                    eventNodeFirst2.mEvent.keyDelete("Sequence");
                    eventNodeFirst2.mEvent.keyDelete("Before");
                    eventNodeFirst2.mEvent.keyDelete("After");
                    eventNodeFirst2.mEvent.setType(VoDcaEvent.EventType.RESUME);
                }
            }
            eventNodeFirst2 = eventNode4;
        }
        return 0L;
    }

    private boolean isSameBroadcast(VoDcaEvent voDcaEvent, VoDcaEvent voDcaEvent2) {
        try {
            if (!this.mChannelRequired && !this.mProgramRequired) {
                if (!this.mVodInfoRequired) {
                    return this.mPreviewInfoRequired && voDcaEvent.getValue("assetId").equals(voDcaEvent2.getValue("assetId")) && voDcaEvent.getValue("name").equals(voDcaEvent2.getValue("name"));
                }
                if (!voDcaEvent.getValue("assetId").equals(voDcaEvent2.getValue("assetId")) || !voDcaEvent.getValue("providerId").equals(voDcaEvent2.getValue("providerId"))) {
                    return false;
                }
                if (voDcaEvent.getValue("episodeId") != null && voDcaEvent2.getValue("episodeId") != null) {
                    if (!voDcaEvent.getValue("episodeId").equals(voDcaEvent2.getValue("episodeId"))) {
                        return false;
                    }
                } else if ((voDcaEvent.getValue("episodeId") != null && voDcaEvent2.getValue("episodeId") == null) || (voDcaEvent.getValue("episodeId") == null && voDcaEvent2.getValue("episodeId") != null)) {
                    return false;
                }
                return true;
            }
            boolean z = !this.mChannelRequired || voDcaEvent.getValue("channelId").equals(voDcaEvent2.getValue("channelId"));
            if (z && this.mProgramRequired) {
                return voDcaEvent.getValue("programId").equals(voDcaEvent2.getValue("programId"));
            }
            return z;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    private long cleanUpQueue() {
        VoDcaEventQueue.EventNode eventNode;
        VoDcaEventQueue.EventNode eventNode2;
        CleanupState cleanupState;
        VoDcaEventQueue.EventNode eventNodeFirst = this.mQueue.first();
        while (eventNodeFirst != null && eventNodeFirst.mNext != null) {
            VoDcaEventQueue.EventNode eventNode3 = eventNodeFirst.mNext;
            VoDcaEventQueue.EventNode eventNode4 = eventNode3.mNext;
            if (eventNodeFirst.mEvent.getType() == this.mEventIn_On && eventNode3.mEvent.getType() == this.mEventIn_On && isSameBroadcast(eventNodeFirst.mEvent, eventNode3.mEvent)) {
                long currentTime = (eventNode3.mEvent.getCurrentTime() + ((long) (this.mConfiguration.mMax_StateConfirmationPeriod * 1000))) - System.currentTimeMillis();
                if (currentTime > 0) {
                    return currentTime;
                }
                this.mQueue.delete(eventNode3);
            } else {
                eventNodeFirst = eventNode3;
            }
        }
        VoDcaEventQueue.EventNode eventNodeFirst2 = this.mQueue.first();
        while (eventNodeFirst2 != null && eventNodeFirst2.mNext != null) {
            VoDcaEventQueue.EventNode eventNode5 = eventNodeFirst2.mNext;
            VoDcaEventQueue.EventNode eventNode6 = eventNode5.mNext;
            if (eventNodeFirst2.mEvent.getType() == this.mEventIn_Off && eventNode5.mEvent.getType() == this.mEventIn_Off && isSameBroadcast(eventNodeFirst2.mEvent, eventNode5.mEvent)) {
                long currentTime2 = (eventNode5.mEvent.getCurrentTime() + ((long) (this.mConfiguration.mMax_StateConfirmationPeriod * 1000))) - System.currentTimeMillis();
                if (currentTime2 > 0) {
                    return currentTime2;
                }
                this.mQueue.delete(eventNode5);
            } else {
                eventNodeFirst2 = eventNode5;
            }
        }
        CleanupState cleanupState2 = CleanupState.Init;
        VoDcaEventQueue.EventNode eventNodeFirst3 = this.mQueue.first();
        VoDcaEventQueue.EventNode eventNode7 = null;
        while (eventNodeFirst3 != null) {
            int i = AnonymousClass1.$SwitchMap$com$viaccessorca$analytics$dca$VoDcaEventHandlerZapFilter$CleanupState[cleanupState2.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    eventNode = eventNodeFirst3.mNext;
                    if (eventNode == null) {
                        eventNodeFirst3 = eventNode;
                    } else {
                        if (eventNode.mEvent.getType() != this.mEventIn_Off) {
                            return 0L;
                        }
                        if (eventNode.mEvent.getCurrentTime() - eventNodeFirst3.mEvent.getCurrentTime() < this.mStateConfirmationPeriod * 1000) {
                            eventNode2 = eventNode.mNext;
                            notifyDeletePair("RemovePS", eventNodeFirst3.mEvent, eventNode.mEvent);
                            this.mQueue.delete(eventNodeFirst3);
                            this.mQueue.delete(eventNode);
                            cleanupState = CleanupState.Init;
                            CleanupState cleanupState3 = cleanupState;
                            eventNodeFirst3 = eventNode2;
                            cleanupState2 = cleanupState3;
                        } else {
                            cleanupState2 = CleanupState.Off;
                            eventNodeFirst3 = eventNode;
                            eventNode7 = eventNodeFirst3;
                        }
                    }
                } else if (i == 3) {
                    eventNode = eventNodeFirst3.mNext;
                    if (eventNode != null) {
                        if (eventNode.mEvent.getType() != this.mEventIn_On) {
                            return 0L;
                        }
                        if (eventNode.mEvent.getCurrentTime() - eventNodeFirst3.mEvent.getCurrentTime() < this.mStateConfirmationPeriod * 1000) {
                            if (isSameBroadcast(eventNodeFirst3.mEvent, eventNode.mEvent)) {
                                eventNode2 = eventNode.mNext;
                                notifyDeletePair("RemoveSP", eventNodeFirst3.mEvent, eventNode.mEvent);
                                this.mQueue.delete(eventNodeFirst3);
                                this.mQueue.delete(eventNode);
                                cleanupState = CleanupState.Init;
                                CleanupState cleanupState4 = cleanupState;
                                eventNodeFirst3 = eventNode2;
                                cleanupState2 = cleanupState4;
                            } else {
                                cleanupState2 = CleanupState.On_After_Off;
                                eventNode7 = eventNodeFirst3;
                            }
                        } else {
                            cleanupState2 = CleanupState.On;
                        }
                    }
                    eventNodeFirst3 = eventNode;
                } else if (i != 4) {
                    continue;
                } else {
                    eventNode = eventNodeFirst3.mNext;
                    if (isSameBroadcast(eventNodeFirst3.mEvent, eventNode7.mEvent)) {
                        notifyDeletePair("RemoveSP", eventNode7.mEvent, eventNodeFirst3.mEvent);
                        this.mQueue.delete(eventNode7);
                        this.mQueue.delete(eventNodeFirst3);
                        cleanupState2 = CleanupState.Init;
                    } else {
                        eventNode = eventNodeFirst3.mNext;
                        if (eventNode != null) {
                            if (eventNode.mEvent.getType() != this.mEventIn_Off) {
                                return 0L;
                            }
                            if (eventNode.mEvent.getCurrentTime() - eventNodeFirst3.mEvent.getCurrentTime() < this.mStateConfirmationPeriod * 1000) {
                                eventNode2 = eventNode.mNext;
                                notifyDeletePair("RemovePS", eventNodeFirst3.mEvent, eventNode.mEvent);
                                this.mQueue.delete(eventNodeFirst3);
                                this.mQueue.delete(eventNode);
                                cleanupState = CleanupState.On_After_Off;
                                CleanupState cleanupState5 = cleanupState;
                                eventNodeFirst3 = eventNode2;
                                cleanupState2 = cleanupState5;
                            } else {
                                cleanupState2 = CleanupState.Init;
                            }
                        }
                    }
                    eventNodeFirst3 = eventNode;
                }
            } else if (eventNodeFirst3.mEvent.getType() == this.mEventIn_On) {
                cleanupState2 = CleanupState.On;
            } else if (eventNodeFirst3.mEvent.getType() == this.mEventIn_Off) {
                cleanupState2 = CleanupState.Off;
                eventNode7 = eventNodeFirst3;
            } else {
                eventNode2 = eventNodeFirst3.mNext;
                cleanupState = CleanupState.Init;
                CleanupState cleanupState6 = cleanupState;
                eventNodeFirst3 = eventNode2;
                cleanupState2 = cleanupState6;
            }
        }
        return 0L;
    }

    /* JADX INFO: renamed from: com.viaccessorca.analytics.dca.VoDcaEventHandlerZapFilter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$viaccessorca$analytics$dca$VoDcaEventHandlerZapFilter$CleanupState;

        static {
            int[] iArr = new int[CleanupState.values().length];
            $SwitchMap$com$viaccessorca$analytics$dca$VoDcaEventHandlerZapFilter$CleanupState = iArr;
            try {
                iArr[CleanupState.Init.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$dca$VoDcaEventHandlerZapFilter$CleanupState[CleanupState.On.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$dca$VoDcaEventHandlerZapFilter$CleanupState[CleanupState.Off.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$dca$VoDcaEventHandlerZapFilter$CleanupState[CleanupState.On_After_Off.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void notifyDeletePair(String str, VoDcaEvent voDcaEvent, VoDcaEvent voDcaEvent2) {
        if (this.mListener != null) {
            VoDcaEvent voDcaEvent3 = new VoDcaEvent(voDcaEvent2);
            voDcaEvent3.setCustomType(this.mEventOut_Off).setCustomParameter("Sequence", str).setCustomParameter("Before", String.valueOf(voDcaEvent.getCurrentTime())).setCustomParameter("After", String.valueOf(voDcaEvent2.getCurrentTime()));
            this.mListener.onInternalEvent(voDcaEvent3);
        }
    }

    private void sendEvent(VoDcaEvent voDcaEvent) {
        if (voDcaEvent.getValue("_intr") != null) {
            String value = voDcaEvent.getValue("eventType");
            String str = this.mResetEvent;
            if (str == null || !value.equals(str)) {
                return;
            }
            this.mState = SessionState.Idle;
            return;
        }
        synchronized (this.mSync) {
            if (this.mState == SessionState.On && voDcaEvent.getType() == this.mEventIn_On) {
                return;
            }
            if ((this.mState == SessionState.Idle || this.mState == SessionState.Off) && voDcaEvent.getType() == this.mEventIn_Off) {
                return;
            }
            if (voDcaEvent.getType() == this.mEventIn_On) {
                voDcaEvent.setCustomType(this.mEventOut_On);
                this.mState = SessionState.On;
            } else if (voDcaEvent.getType() == this.mEventIn_Off) {
                voDcaEvent.setCustomType(this.mEventOut_Off);
                this.mState = SessionState.Off;
            }
            if (!this.mConfiguration.eventFieldValidate(voDcaEvent)) {
                Log.e(TAG, "Event was not sent! Field validation failed");
                return;
            }
            if (!this.mConfiguration.mParsingSuccess) {
                Log.e(TAG, "Event was not sent! Error in given configuration");
                return;
            }
            String urlByEventType = this.mConfiguration.getUrlByEventType(voDcaEvent.getValue("eventType"));
            if (urlByEventType != null) {
                if (urlByEventType.toLowerCase().indexOf("null".toLowerCase()) == -1) {
                    this.mDispatcher.sendEvent(voDcaEvent, urlByEventType);
                    return;
                }
                Log.w(TAG, "URL not valid : " + urlByEventType + ", Event " + voDcaEvent.getValue("eventType") + " dropped.");
                return;
            }
            Log.w(TAG, "Event Type " + voDcaEvent.getValue("eventType") + " is not supported in the configuration. Event dropped.");
        }
    }

    private void addEvent(VoDcaEvent voDcaEvent) {
        if (this.mEnableHandleEvents) {
            if (voDcaEvent.getType() == this.mEventIn_On) {
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
            if (voDcaEvent.getType() == this.mEventIn_Off) {
                String value = voDcaEvent.getValue("_intrReason");
                if (value != null && value.compareTo("insert") == 0) {
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
                if (this.mChannelRequired && this.mChannelId == null) {
                    return;
                }
                if (this.mProgramRequired && this.mProgramId == null) {
                    return;
                }
                if (this.mVodInfoRequired && (this.mAssetId == null || this.mProviderId == null)) {
                    return;
                }
                if (this.mPreviewInfoRequired && (this.mInternalAssetId == null || this.mName == null)) {
                    return;
                }
            }
            if (this.mChannelRequired && this.mChannelId != null) {
                voDcaEvent.setParameter(VoDcaEvent.EventKey.CHANNEL_ID, this.mChannelId);
                if (this.mChannelName != null) {
                    voDcaEvent.setParameter(VoDcaEvent.EventKey.CHANNEL_NAME, this.mChannelName);
                }
            }
            if (this.mProgramRequired && this.mProgramId != null) {
                voDcaEvent.setParameter(VoDcaEvent.EventKey.PROGRAM_ID, this.mProgramId);
                if (this.mProgramName != null) {
                    voDcaEvent.setParameter(VoDcaEvent.EventKey.PROGRAM_NAME, this.mProgramName);
                }
            }
            if (this.mVodInfoRequired) {
                if (this.mAssetId != null) {
                    voDcaEvent.setParameter(VoDcaEvent.EventKey.ASSET_ID, this.mAssetId);
                }
                if (this.mProviderId != null) {
                    voDcaEvent.setParameter(VoDcaEvent.EventKey.PROVIDER_ID, this.mProviderId);
                }
                if (this.mEpisodeId != null) {
                    voDcaEvent.setParameter(VoDcaEvent.EventKey.EPISODE_ID, this.mEpisodeId);
                }
            }
            if (this.mPreviewInfoRequired) {
                if (this.mInternalAssetId != null) {
                    voDcaEvent.setParameter(VoDcaEvent.EventKey.ASSET_ID, this.mInternalAssetId);
                }
                if (this.mName != null) {
                    voDcaEvent.setParameter(VoDcaEvent.EventKey.NAME, this.mName);
                }
            }
            String str = this.mConvertFromField;
            if (str != null && voDcaEvent.keyExists(str)) {
                voDcaEvent.setCustomParameter(this.mConvertToField, voDcaEvent.getValue(this.mConvertFromField));
                voDcaEvent.keyDelete(this.mConvertFromField);
            }
            this.mQueue.add(voDcaEvent);
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
