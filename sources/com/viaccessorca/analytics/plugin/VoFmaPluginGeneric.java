package com.viaccessorca.analytics.plugin;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.viaccessorca.analytics.fma.VoFmaController;
import com.viaccessorca.analytics.fma.VoFmaControllerListener;
import com.viaccessorca.analytics.fma.VoFmaEvent;
import com.viaccessorca.analytics.fma.VoFmaInitParameters;
import java.util.Date;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class VoFmaPluginGeneric implements VoFmaControllerListener {
    protected ContentType FmaContentType;
    private Activity activity;
    protected String assetId;
    protected Map<String, String> channelCustomParams;
    protected String channelId;
    protected String channelName;
    protected VoFmaEvent.EventKey contentKeyParam;
    protected Map<String, String> customTypeCustomParams;
    protected Map<String, String> episodeCustomParams;
    protected String episodeId;
    VoFmaController fmaInstance;
    protected String mAccountId;
    protected Context mApplicationContext;
    protected String mApplicationName;
    protected String mApplicationVersion;
    protected String mConfigurationUrl;
    protected String mDeviceCategory;
    protected String mDeviceId;
    protected boolean mFmaInitCompleted;
    protected VoFmaControllerListener mListener;
    protected Double mLivePosition;
    protected Double mPlaybackPosition;
    protected String mProfileId;
    protected Map<String, String> mediaCustomParams;
    protected Map<String, String> movieCustomParams;
    protected String name;
    protected VoFmaInitParameters param;
    protected Map<String, String> pauseCustomParams;
    protected Map<String, String> playCustomParams;
    protected String pluginName;
    protected String pluginVersion;
    protected Map<String, String> previewCustomParams;
    protected Map<String, String> programCustomParams;
    protected String programId;
    protected String programName;
    protected String providerId;
    protected Map<String, String> resumeCustomParams;
    protected Map<String, String> seekEndCustomParams;
    protected Map<String, String> seekStartCustomParams;
    protected Map<String, String> stopCustomParams;
    private boolean stopMonitoringRequired;
    protected int waitingPeriodForPause = 1000;
    protected Object player = null;
    protected boolean startAutoDetectionEnabled = true;
    protected boolean mbSecondaryContentSelected = false;

    public enum ContentType {
        NONE,
        LIVE,
        STARTOVER,
        CATCHUP,
        VOD,
        PREVIEW,
        NPVR,
        DOWNLOAD
    }

    private String getPlayerVersion() {
        return "Generic";
    }

    public int getLivePosition() {
        return 0;
    }

    public int getPlaybackPosition() {
        return 0;
    }

    protected VoFmaPluginGeneric() {
        this.fmaInstance = null;
        if (VoFmaController.getFmaController() != null) {
            this.fmaInstance = VoFmaController.getFmaController();
            this.mFmaInitCompleted = true;
        } else {
            this.mFmaInitCompleted = false;
        }
        this.param = new VoFmaInitParameters();
    }

    protected void init() {
        this.param.mListener = this;
        this.param.mConfigurationUrl = this.mConfigurationUrl;
        this.param.mApplicationName = this.mApplicationName;
        this.param.mApplicationVersion = this.mApplicationVersion;
        this.param.mDeviceID = this.mDeviceId;
        this.param.mDeviceCategory = this.mDeviceCategory;
        this.param.mApplicationContext = this.mApplicationContext;
        VoFmaController.initialize(this.param);
    }

    /* JADX INFO: renamed from: com.viaccessorca.analytics.plugin.VoFmaPluginGeneric$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$viaccessorca$analytics$plugin$VoFmaPluginGeneric$ContentType;

        static {
            int[] iArr = new int[ContentType.values().length];
            $SwitchMap$com$viaccessorca$analytics$plugin$VoFmaPluginGeneric$ContentType = iArr;
            try {
                iArr[ContentType.LIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$plugin$VoFmaPluginGeneric$ContentType[ContentType.STARTOVER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$plugin$VoFmaPluginGeneric$ContentType[ContentType.CATCHUP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$plugin$VoFmaPluginGeneric$ContentType[ContentType.VOD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$plugin$VoFmaPluginGeneric$ContentType[ContentType.PREVIEW.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$plugin$VoFmaPluginGeneric$ContentType[ContentType.NPVR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$plugin$VoFmaPluginGeneric$ContentType[ContentType.DOWNLOAD.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public void setContentTypeFromTypeString(ContentType contentType) {
        switch (AnonymousClass1.$SwitchMap$com$viaccessorca$analytics$plugin$VoFmaPluginGeneric$ContentType[contentType.ordinal()]) {
            case 1:
                this.contentKeyParam = VoFmaEvent.EventKey.STREAM_TIME;
                setChannel();
                break;
            case 2:
                this.contentKeyParam = VoFmaEvent.EventKey.STREAM_OFFSET;
                setChannel();
                setProgram();
                break;
            case 3:
                this.contentKeyParam = VoFmaEvent.EventKey.STREAM_OFFSET;
                setChannel();
                setProgram();
                break;
            case 4:
                this.contentKeyParam = VoFmaEvent.EventKey.STREAM_OFFSET;
                setEpisode();
                setMovie();
                break;
            case 5:
                this.contentKeyParam = VoFmaEvent.EventKey.STREAM_OFFSET;
                setPreview();
                break;
            case 6:
                this.contentKeyParam = VoFmaEvent.EventKey.STREAM_OFFSET;
                setChannel();
                setProgram();
                break;
            case 7:
                this.contentKeyParam = VoFmaEvent.EventKey.STREAM_OFFSET;
                setEpisode();
                setMovie();
                break;
            default:
                this.contentKeyParam = VoFmaEvent.EventKey.STREAM_OFFSET;
                break;
        }
    }

    public void startMonitoring(Object obj) {
        this.player = obj;
    }

    public void stopMonitoring() {
        if (this.fmaInstance != null && this.player != null) {
            sendStop();
        } else {
            Log.v("fma pre-integration", "stopMonitoring called before startMonitoring");
        }
        this.player = null;
    }

    protected void setChannel() {
        VoFmaEvent voFmaEvent = new VoFmaEvent();
        voFmaEvent.setType(VoFmaEvent.EventType.SET_CHANNEL);
        if (this.channelId != null) {
            voFmaEvent.setParameter(VoFmaEvent.EventKey.CHANNEL_ID, this.channelId);
        }
        if (this.channelName != null) {
            voFmaEvent.setParameter(VoFmaEvent.EventKey.CHANNEL_NAME, this.channelName);
        }
        Map<String, String> map = this.channelCustomParams;
        if (map == null || map.size() <= 0) {
            return;
        }
        for (String str : this.channelCustomParams.keySet()) {
            voFmaEvent.setCustomParameter(str, this.channelCustomParams.get(str));
        }
        this.channelCustomParams.clear();
    }

    public void setProgram() {
        VoFmaEvent voFmaEvent = new VoFmaEvent();
        voFmaEvent.setType(VoFmaEvent.EventType.SET_PROGRAM);
        if (this.programId != null) {
            voFmaEvent.setParameter(VoFmaEvent.EventKey.PROGRAM_ID, this.programId);
        }
        if (this.programName != null) {
            voFmaEvent.setParameter(VoFmaEvent.EventKey.PROGRAM_NAME, this.programName);
        }
        Map<String, String> map = this.programCustomParams;
        if (map == null || map.size() <= 0) {
            return;
        }
        for (String str : this.programCustomParams.keySet()) {
            voFmaEvent.setCustomParameter(str, this.programCustomParams.get(str));
        }
        this.programCustomParams.clear();
    }

    public void setPreview() {
        VoFmaEvent voFmaEvent = new VoFmaEvent();
        voFmaEvent.setType(VoFmaEvent.EventType.SET_PREVIEW);
        if (this.assetId != null) {
            voFmaEvent.setParameter(VoFmaEvent.EventKey.ASSET_ID, this.assetId);
        }
        if (this.name != null) {
            voFmaEvent.setParameter(VoFmaEvent.EventKey.NAME, this.name);
        }
        Map<String, String> map = this.previewCustomParams;
        if (map == null || map.size() <= 0) {
            return;
        }
        for (String str : this.previewCustomParams.keySet()) {
            voFmaEvent.setCustomParameter(str, this.previewCustomParams.get(str));
        }
        this.previewCustomParams.clear();
    }

    public void setMedia() {
        VoFmaEvent voFmaEvent = new VoFmaEvent();
        voFmaEvent.setType(VoFmaEvent.EventType.SET_MEDIA);
        if (this.assetId != null) {
            voFmaEvent.setParameter(VoFmaEvent.EventKey.ASSET_ID, this.assetId);
        }
        if (this.providerId != null) {
            voFmaEvent.setParameter(VoFmaEvent.EventKey.PROVIDER_ID, this.providerId);
        }
        Map<String, String> map = this.mediaCustomParams;
        if (map == null || map.size() <= 0) {
            return;
        }
        for (String str : this.mediaCustomParams.keySet()) {
            voFmaEvent.setCustomParameter(str, this.mediaCustomParams.get(str));
        }
        this.mediaCustomParams.clear();
    }

    protected void setEpisode() {
        VoFmaEvent voFmaEvent = new VoFmaEvent();
        voFmaEvent.setType(VoFmaEvent.EventType.SET_EPISODE);
        if (this.assetId != null) {
            voFmaEvent.setParameter(VoFmaEvent.EventKey.ASSET_ID, this.assetId);
        }
        if (this.providerId != null) {
            voFmaEvent.setParameter(VoFmaEvent.EventKey.PROVIDER_ID, this.providerId);
        }
        if (this.episodeId != null) {
            voFmaEvent.setParameter(VoFmaEvent.EventKey.EPISODE_ID, this.episodeId);
        }
        Map<String, String> map = this.episodeCustomParams;
        if (map == null || map.size() <= 0) {
            return;
        }
        for (String str : this.episodeCustomParams.keySet()) {
            voFmaEvent.setCustomParameter(str, this.episodeCustomParams.get(str));
        }
        this.episodeCustomParams.clear();
    }

    protected void setMovie() {
        VoFmaEvent voFmaEvent = new VoFmaEvent();
        voFmaEvent.setType(VoFmaEvent.EventType.SET_EPISODE);
        if (this.assetId != null) {
            voFmaEvent.setParameter(VoFmaEvent.EventKey.ASSET_ID, this.assetId);
        }
        if (this.providerId != null) {
            voFmaEvent.setParameter(VoFmaEvent.EventKey.PROVIDER_ID, this.providerId);
        }
        Map<String, String> map = this.movieCustomParams;
        if (map == null || map.size() <= 0) {
            return;
        }
        for (String str : this.movieCustomParams.keySet()) {
            voFmaEvent.setCustomParameter(str, this.movieCustomParams.get(str));
        }
        this.movieCustomParams.clear();
    }

    protected void setCustomType(String str) {
        VoFmaEvent voFmaEvent = new VoFmaEvent();
        voFmaEvent.setCustomType(str);
        Map<String, String> map = this.customTypeCustomParams;
        if (map == null || map.size() <= 0) {
            return;
        }
        for (String str2 : this.customTypeCustomParams.keySet()) {
            voFmaEvent.setCustomParameter(str2, this.customTypeCustomParams.get(str2));
        }
        this.customTypeCustomParams.clear();
    }

    protected void sendOpen() {
        if (this.mbSecondaryContentSelected) {
            return;
        }
        this.fmaInstance.setAccountID(this.mAccountId);
        this.fmaInstance.setProfileID(this.mProfileId);
    }

    protected void sendPlay() {
        String string;
        if (this.mbSecondaryContentSelected) {
            return;
        }
        try {
            VoFmaEvent voFmaEvent = new VoFmaEvent();
            voFmaEvent.setType(VoFmaEvent.EventType.PLAY);
            if (this.FmaContentType == ContentType.LIVE) {
                string = VoFmaEvent.generateTimeString(new Date().getTime() - ((long) (getLivePosition() - getPlaybackPosition())));
            } else {
                string = Integer.toString(getPlaybackPosition());
            }
            voFmaEvent.setParameter(this.contentKeyParam, string);
            if (this.playCustomParams == null || this.playCustomParams.size() <= 0) {
                return;
            }
            for (String str : this.playCustomParams.keySet()) {
                voFmaEvent.setCustomParameter(str, this.playCustomParams.get(str));
            }
            this.playCustomParams.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void sendStop() {
        String string;
        if (this.mbSecondaryContentSelected) {
            return;
        }
        try {
            VoFmaEvent voFmaEvent = new VoFmaEvent();
            voFmaEvent.setType(VoFmaEvent.EventType.STOP);
            if (this.FmaContentType == ContentType.LIVE) {
                string = VoFmaEvent.generateTimeString(new Date().getTime() - ((long) (getLivePosition() - getPlaybackPosition())));
            } else {
                string = Integer.toString(getPlaybackPosition());
            }
            voFmaEvent.setParameter(this.contentKeyParam, string);
            if (this.stopCustomParams == null || this.stopCustomParams.size() <= 0) {
                return;
            }
            for (String str : this.stopCustomParams.keySet()) {
                voFmaEvent.setCustomParameter(str, this.stopCustomParams.get(str));
            }
            this.stopCustomParams.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void sendPause() {
        String string;
        if (this.mbSecondaryContentSelected) {
            return;
        }
        try {
            VoFmaEvent voFmaEvent = new VoFmaEvent();
            voFmaEvent.setType(VoFmaEvent.EventType.PAUSE);
            if (this.FmaContentType == ContentType.LIVE) {
                string = VoFmaEvent.generateTimeString((new Date().getTime() - ((long) (getLivePosition() - getPlaybackPosition()))) - ((long) this.waitingPeriodForPause));
            } else {
                string = Integer.toString(getPlaybackPosition() - this.waitingPeriodForPause);
            }
            voFmaEvent.setParameter(this.contentKeyParam, string);
            if (this.pauseCustomParams == null || this.pauseCustomParams.size() <= 0) {
                return;
            }
            for (String str : this.pauseCustomParams.keySet()) {
                voFmaEvent.setCustomParameter(str, this.pauseCustomParams.get(str));
            }
            this.pauseCustomParams.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void sendResume() {
        String string;
        if (this.mbSecondaryContentSelected) {
            return;
        }
        try {
            VoFmaEvent voFmaEvent = new VoFmaEvent();
            voFmaEvent.setType(VoFmaEvent.EventType.RESUME);
            if (this.FmaContentType == ContentType.LIVE) {
                string = VoFmaEvent.generateTimeString(new Date().getTime() - ((long) (getLivePosition() - getPlaybackPosition())));
            } else {
                string = Integer.toString(getPlaybackPosition());
            }
            voFmaEvent.setParameter(this.contentKeyParam, string);
            if (this.resumeCustomParams == null || this.resumeCustomParams.size() <= 0) {
                return;
            }
            for (String str : this.resumeCustomParams.keySet()) {
                voFmaEvent.setCustomParameter(str, this.resumeCustomParams.get(str));
            }
            this.resumeCustomParams.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void sendSeekStart() {
        String string;
        if (this.mbSecondaryContentSelected) {
            return;
        }
        try {
            VoFmaEvent voFmaEvent = new VoFmaEvent();
            voFmaEvent.setType(VoFmaEvent.EventType.SEEK_START);
            if (this.FmaContentType == ContentType.LIVE) {
                string = VoFmaEvent.generateTimeString(new Date().getTime() - ((long) (getLivePosition() - getPlaybackPosition())));
            } else {
                string = Integer.toString(getPlaybackPosition());
            }
            voFmaEvent.setParameter(this.contentKeyParam, string);
            if (this.seekStartCustomParams == null || this.seekStartCustomParams.size() <= 0) {
                return;
            }
            for (String str : this.seekStartCustomParams.keySet()) {
                voFmaEvent.setCustomParameter(str, this.seekStartCustomParams.get(str));
            }
            this.seekStartCustomParams.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void sendSeekEnd() {
        String string;
        if (this.mbSecondaryContentSelected) {
            return;
        }
        try {
            VoFmaEvent voFmaEvent = new VoFmaEvent();
            voFmaEvent.setType(VoFmaEvent.EventType.SEEK_END);
            if (this.FmaContentType == ContentType.LIVE) {
                string = VoFmaEvent.generateTimeString(new Date().getTime() - ((long) (getLivePosition() - getPlaybackPosition())));
            } else {
                string = Integer.toString(getPlaybackPosition());
            }
            voFmaEvent.setParameter(this.contentKeyParam, string);
            if (this.seekEndCustomParams == null || this.seekEndCustomParams.size() <= 0) {
                return;
            }
            for (String str : this.seekEndCustomParams.keySet()) {
                voFmaEvent.setCustomParameter(str, this.seekEndCustomParams.get(str));
            }
            this.seekEndCustomParams.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final String getPluginVersion() {
        return this.pluginVersion;
    }

    public final String getPluginName() {
        return this.pluginName;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return this.activity;
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaControllerListener
    public void onInitCompleted() {
        this.mFmaInitCompleted = true;
        Log.v("fma", "init completed");
        this.fmaInstance = VoFmaController.getFmaController();
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaControllerListener
    public void onInitFailed() {
        Log.v("fma", "init failed");
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaControllerListener
    public void onConfigurationReloaded() {
        Log.v("fma", "init reloaded");
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaControllerListener
    public void onConfigurationReloadedFailed() {
        Log.v("fma", "init reloadfailred");
    }
}
