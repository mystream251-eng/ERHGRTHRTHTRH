package com.dotscreen.tvinput.voPlayer;

import android.content.Context;
import android.media.PlaybackParams;
import android.media.tv.TvTrackInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import com.dotscreen.farkit.network.NetworkTools;
import com.dotscreen.tv.common.ISmartLibProvider;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.bokit.RightVPlaybackInfo;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.utils.BPKPlayerError;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.common.utils.Context_ExtensionKt;
import com.dotscreen.tv.common.utils.SentryReportHint;
import com.dotscreen.tvinput.OTTLiveTvInputService;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.media.tv.companionlibrary.TvPlayer;
import com.viaccessorca.drm.VOWidevineAgent;
import com.viaccessorca.voplayer.VOAudioTrack;
import com.viaccessorca.voplayer.VOPlayer;
import com.viaccessorca.voplayer.VOSubtitleTrack;
import com.viaccessorca.voplayer.VOTIFVideoOverlayView;
import com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended;
import io.sentry.Breadcrumb;
import io.sentry.Sentry;
import io.sentry.SentryEvent;
import io.sentry.SentryLevel;
import io.sentry.cache.EnvelopeCache;
import io.sentry.protocol.Message;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import tv.broadpeak.smartlib.SmartLib;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.session.streaming.StreamingSessionResult;

/* JADX INFO: compiled from: VOTVPlayer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006B+\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\u0010\r\u001a\u00060\u000eR\u00020\u000f¢\u0006\u0002\u0010\u0010J\b\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020%H\u0016J\u0006\u0010'\u001a\u00020(J\"\u0010)\u001a\u00020#2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\u001a\u0010*\u001a\u00020#2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010+\u001a\u00020,H\u0016J\"\u0010-\u001a\u00020\u001b2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010.\u001a\u00020,2\u0006\u0010/\u001a\u00020,H\u0016J,\u00100\u001a\u00020\u001b2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u00101\u001a\u00020,2\u0006\u0010/\u001a\u00020,2\b\u00102\u001a\u0004\u0018\u000103H\u0017J\u0012\u00104\u001a\u00020#2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\"\u00105\u001a\u00020#2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u00106\u001a\u00020,2\u0006\u00107\u001a\u00020,H\u0016J\b\u00108\u001a\u00020#H\u0016J\b\u00109\u001a\u00020#H\u0016J\u0012\u0010:\u001a\u00020#2\b\u0010;\u001a\u0004\u0018\u00010\u0016H\u0016J\u0006\u0010<\u001a\u00020#J\u0010\u0010=\u001a\u00020#2\u0006\u0010>\u001a\u00020%H\u0016J\u0016\u0010?\u001a\u00020#2\u0006\u0010@\u001a\u00020\u00122\u0006\u0010A\u001a\u00020\u0012J\u0010\u0010B\u001a\u00020\u001b2\b\u0010C\u001a\u0004\u0018\u00010\u0012J \u0010D\u001a\u00020#2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0012\u0010E\u001a\u00020#2\b\u0010F\u001a\u0004\u0018\u00010GH\u0016J\u0010\u0010H\u001a\u00020\u001b2\b\u0010C\u001a\u0004\u0018\u00010\u0012J\u0012\u0010I\u001a\u00020#2\b\u0010J\u001a\u0004\u0018\u00010KH\u0016J\u0016\u0010L\u001a\u00020#2\u0006\u0010M\u001a\u00020,2\u0006\u0010N\u001a\u00020,J\u000e\u0010O\u001a\u00020#2\u0006\u0010P\u001a\u00020\fJ\u0010\u0010Q\u001a\u00020#2\u0006\u0010R\u001a\u00020SH\u0016J\u0012\u0010T\u001a\u00020#2\b\u0010;\u001a\u0004\u0018\u00010\u0016H\u0016R\u0016\u0010\u0011\u001a\n \u0013*\u0004\u0018\u00010\u00120\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00060\u000eR\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006U"}, d2 = {"Lcom/dotscreen/tvinput/voPlayer/VOTVPlayer;", "Lcom/google/android/media/tv/companionlibrary/TvPlayer;", "Lcom/viaccessorca/voplayer/VOPlayer$OnPreparedListener;", "Lcom/viaccessorca/voplayer/VOPlayer$OnInformationListener;", "Lcom/viaccessorca/voplayer/VOPlayer$OnBufferingUpdateListener;", "Lcom/viaccessorca/voplayer/VOPlayer$OnErrorListener;", "Lcom/viaccessorca/voplayer/VOPlayer$OnVideoSizeChangedListener;", "context", "Landroid/content/Context;", "playbackInfo", "Lcom/dotscreen/tv/common/bokit/RightVPlaybackInfo;", "videoOverlay", "Lcom/viaccessorca/voplayer/VOTIFVideoOverlayView;", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "Lcom/dotscreen/tvinput/OTTLiveTvInputService$TvInputSessionImpl;", "Lcom/dotscreen/tvinput/OTTLiveTvInputService;", "(Landroid/content/Context;Lcom/dotscreen/tv/common/bokit/RightVPlaybackInfo;Lcom/viaccessorca/voplayer/VOTIFVideoOverlayView;Lcom/dotscreen/tvinput/OTTLiveTvInputService$TvInputSessionImpl;)V", "TAG", "", "kotlin.jvm.PlatformType", "callbacks", "", "Lcom/google/android/media/tv/companionlibrary/TvPlayer$Callback;", "getContext", "()Landroid/content/Context;", "mContext", "muted", "", "prepared", "shoudlMuteOnPrepared", "surfaceSize", "Landroid/util/Size;", "voPlayer", "Lcom/viaccessorca/voplayer/VOPlayer;", "getAllTracks", "", "getCurrentPosition", "", "getDuration", "getState", "Lcom/viaccessorca/voplayer/VOPlayer$PlayerState;", "initPlayer", "onBufferingUpdate", "percentage", "", "onError", "error", "extra", "onInformation", "what", "obj", "", "onPrepared", "onVideoSizeChanged", "videoWidth", "videoHeight", ToolboxAnalyticsModuleKt.EVENT_PAUSE, "play", "registerCallback", "callback", "release", "seekTo", "position", "setAnalyticsData", "key", "value", "setAudioTrack", "id", "setPlaybackInfo", "setPlaybackParams", "params", "Landroid/media/PlaybackParams;", "setSubtitleTrack", "setSurface", "surface", "Landroid/view/Surface;", "setSurfaceSize", ToolboxAnalyticsModuleKt.M_WIDTH, ToolboxAnalyticsModuleKt.M_HEIGHT, "setVideoOverlayView", "overlayView", "setVolume", "volume", "", "unregisterCallback", "tvinput_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class VOTVPlayer implements TvPlayer, VOPlayer.OnPreparedListener, VOPlayer.OnInformationListener, VOPlayer.OnBufferingUpdateListener, VOPlayer.OnErrorListener, VOPlayer.OnVideoSizeChangedListener {
    private final String TAG;
    private Set<TvPlayer.Callback> callbacks;
    private final Context context;
    private Context mContext;
    private boolean muted;
    private boolean prepared;
    private final OTTLiveTvInputService.TvInputSessionImpl session;
    private boolean shoudlMuteOnPrepared;
    private Size surfaceSize;
    private VOPlayer voPlayer;

    @Override // com.google.android.media.tv.companionlibrary.TvPlayer
    public void setPlaybackParams(PlaybackParams params) {
    }

    public VOTVPlayer(Context context, RightVPlaybackInfo playbackInfo, VOTIFVideoOverlayView vOTIFVideoOverlayView, OTTLiveTvInputService.TvInputSessionImpl session) throws IOException {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(playbackInfo, "playbackInfo");
        Intrinsics.checkParameterIsNotNull(session, "session");
        this.context = context;
        this.session = session;
        this.callbacks = new LinkedHashSet();
        String simpleName = VOTVPlayer.class.getSimpleName();
        this.TAG = simpleName;
        Log.v(simpleName, "init");
        this.session.notifyVideoUnavailable(3);
        Context context2 = this.context;
        this.mContext = context2;
        Object applicationContext = context2.getApplicationContext();
        ISmartLibProvider iSmartLibProvider = (ISmartLibProvider) (applicationContext instanceof ISmartLibProvider ? applicationContext : null);
        if (iSmartLibProvider != null) {
            iSmartLibProvider.reinitSmartLibIfNeeded("Smartlib not init at player start for live");
            Object smartLibCoreEngine = iSmartLibProvider.getSmartLibCoreEngine();
            CoreEngine coreEngine = (CoreEngine) (smartLibCoreEngine instanceof CoreEngine ? smartLibCoreEngine : null);
            if (coreEngine != null) {
                CoreEngine.f187a = coreEngine;
            }
            Object smartLib = iSmartLibProvider.getSmartLib();
            SmartLib smartLib2 = (SmartLib) (smartLib instanceof SmartLib ? smartLib : null);
            if (smartLib2 != null) {
                SmartLib.f176a = smartLib2;
            }
        }
        CoreEngine coreEngine2 = CoreEngine.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(coreEngine2, "CoreEngine.getInstance()");
        if (coreEngine2.isInitialized()) {
            Log.v("SmartLib", "VOPlayer created as VOPlayerBpkExtended");
            Sentry.addBreadcrumb("VOPlayer created as VOPlayerBpkExtended");
        } else {
            Log.v("SmartLib", "VOPlayer created as VOPlayerBpkExtended but smartlib not initialized");
            Sentry.addBreadcrumb("VOPlayer created as VOPlayerBpkExtended(even if smartlib was not initialized)");
        }
        this.voPlayer = new VOPlayerBpkExtended(this.context);
        String mACAddress$default = NetworkTools.getMACAddress$default(NetworkTools.INSTANCE, "eth0", null, 2, null);
        if (mACAddress$default != null) {
            Log.d(this.TAG, "BPK UUID -> ETH MAC:" + mACAddress$default);
            VOPlayerBpkExtended.setSmartLibUUID(mACAddress$default);
        } else {
            Log.d(this.TAG, "BPK UUID - setSmartLibUUID() failed");
        }
        initPlayer(this.context, playbackInfo, vOTIFVideoOverlayView);
    }

    public final Context getContext() {
        return this.context;
    }

    public final void setPlaybackInfo(Context context, RightVPlaybackInfo playbackInfo, VOTIFVideoOverlayView videoOverlay) throws IOException {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(playbackInfo, "playbackInfo");
        this.session.notifyVideoUnavailable(3);
        VOPlayer vOPlayer = this.voPlayer;
        if (vOPlayer != null) {
            vOPlayer.reset();
        }
        Sentry.addBreadcrumb("VOPlayer reset");
        initPlayer(context, playbackInfo, videoOverlay);
    }

    private final void initPlayer(Context context, RightVPlaybackInfo playbackInfo, VOTIFVideoOverlayView videoOverlay) throws IOException {
        String serialNumber;
        VOPlayer vOPlayer;
        VOWidevineAgent agentWidevine;
        VOWidevineAgent agentWidevine2;
        VOWidevineAgent agentWidevine3;
        VOPlayerBpkExtended.setSmartLibLogLevel(1);
        new LinkedHashMap();
        if (videoOverlay != null) {
            setVideoOverlayView(videoOverlay);
        }
        CoreEngine coreEngine = CoreEngine.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(coreEngine, "CoreEngine.getInstance()");
        if (coreEngine.isInitialized() && (this.voPlayer instanceof VOPlayerBpkExtended)) {
            Log.v(this.TAG, "VOPlayerBpkExtended.setDataSourceWithSmartLib(" + playbackInfo.getUrl() + ')');
            Breadcrumb breadcrumb = new Breadcrumb();
            breadcrumb.setMessage("VOPlayerBpkExtended.setDataSourceWithSmartLib");
            breadcrumb.setData(ImagesContract.URL, playbackInfo.getUrl());
            Sentry.addBreadcrumb(breadcrumb);
            VOPlayer vOPlayer2 = this.voPlayer;
            if (!(vOPlayer2 instanceof VOPlayerBpkExtended)) {
                vOPlayer2 = null;
            }
            VOPlayerBpkExtended vOPlayerBpkExtended = (VOPlayerBpkExtended) vOPlayer2;
            if (vOPlayerBpkExtended != null) {
                vOPlayerBpkExtended.setDataSource(playbackInfo.getUrl());
            }
        } else {
            Log.v(this.TAG, "VOPlayer.setDataSource(" + playbackInfo.getUrl() + ')');
            Breadcrumb breadcrumb2 = new Breadcrumb();
            breadcrumb2.setMessage("VOPlayer.setDataSource");
            breadcrumb2.setData(ImagesContract.URL, playbackInfo.getUrl());
            Sentry.addBreadcrumb(breadcrumb2);
            VOPlayer vOPlayer3 = this.voPlayer;
            if (vOPlayer3 != null) {
                vOPlayer3.setDataSource(context, Uri.parse(playbackInfo.getUrl()));
            }
        }
        String casToken = playbackInfo.getCasToken();
        if (casToken != null) {
            if (casToken.length() > 0) {
                VOPlayer vOPlayer4 = this.voPlayer;
                if (vOPlayer4 != null && (agentWidevine3 = vOPlayer4.getAgentWidevine()) != null) {
                    agentWidevine3.setLicenseAcquisitionURL(playbackInfo.getLicenceUrl() + "?token=" + Uri.encode(playbackInfo.getCasToken(), "-._~/?"));
                }
                VOPlayer vOPlayer5 = this.voPlayer;
                if (vOPlayer5 != null && (agentWidevine2 = vOPlayer5.getAgentWidevine()) != null) {
                    agentWidevine2.setLicenseAcquisitionType(VOWidevineAgent.EDrmLicenseAcquisitionType.PERSISTENT);
                }
                VOPlayer vOPlayer6 = this.voPlayer;
                if (vOPlayer6 != null && (agentWidevine = vOPlayer6.getAgentWidevine()) != null) {
                    agentWidevine.setLicenseValidityThreshold(28800L);
                }
            }
        }
        VOPlayer vOPlayer7 = this.voPlayer;
        if (vOPlayer7 != null) {
            vOPlayer7.setOnPreparedListener(this);
        }
        VOPlayer vOPlayer8 = this.voPlayer;
        if (vOPlayer8 != null) {
            vOPlayer8.setOnBufferingUpdateListener(this);
        }
        VOPlayer vOPlayer9 = this.voPlayer;
        if (vOPlayer9 != null) {
            vOPlayer9.setOnInformationListener(this);
        }
        VOPlayer vOPlayer10 = this.voPlayer;
        if (vOPlayer10 != null) {
            vOPlayer10.setOnErrorListener(this);
        }
        VOPlayer vOPlayer11 = this.voPlayer;
        if (vOPlayer11 != null) {
            vOPlayer11.setScreenOnWhilePlaying(true);
        }
        VOPlayer vOPlayer12 = this.voPlayer;
        if (vOPlayer12 != null) {
            vOPlayer12.setOnVideoSizeChangedListener(this);
        }
        VOPlayer vOPlayer13 = this.voPlayer;
        if (vOPlayer13 != null) {
            vOPlayer13.setHttpStreamingTypicalBitrate(2048000);
        }
        this.muted = false;
        try {
            MegacableUserService megacableUserService = (MegacableUserService) CommonUtils.getBOService(context).getUserService();
            if (megacableUserService != null && (serialNumber = megacableUserService.getSerialNumber()) != null && (vOPlayer = this.voPlayer) != null) {
                vOPlayer.setAnalyticsMetadata("SERIAL_NUMBER_ID", serialNumber);
            }
            VOPlayer.AbrControls abrControls = new VOPlayer.AbrControls();
            abrControls.m_uiLiveTimelineDiscontinuityPolicy = 1;
            try {
                VOPlayer vOPlayer14 = this.voPlayer;
                if (vOPlayer14 == null) {
                    Intrinsics.throwNpe();
                }
                vOPlayer14.setHttpStreamingAbrControlSettings(abrControls);
            } catch (IllegalArgumentException unused) {
                Log.w(this.TAG, "ABR controls are not in range : " + abrControls);
            } catch (UnsupportedOperationException unused2) {
                Log.w(this.TAG, "ABR control feature not available");
            }
            VOPlayer vOPlayer15 = this.voPlayer;
            if (vOPlayer15 != null) {
                vOPlayer15.prepareAsync();
            }
        } catch (Throwable th) {
            Sentry.captureException(th);
            OTTLiveTvInputService.TvInputSessionImpl tvInputSessionImpl = this.session;
            Bundle bundle = new Bundle();
            bundle.putSerializable("error", th);
            tvInputSessionImpl.notifySessionEvent(CommonUtils.TIS_EVENT_EXTRA_ERROR, bundle);
            this.session.notifyVideoUnavailable(0);
        }
    }

    public final void release() {
        VOPlayer vOPlayer = this.voPlayer;
        if (vOPlayer != null) {
            vOPlayer.reset();
        }
        VOPlayer vOPlayer2 = this.voPlayer;
        if (vOPlayer2 != null) {
            vOPlayer2.release();
        }
        Sentry.clearBreadcrumbs();
        this.voPlayer = (VOPlayer) null;
    }

    @Override // com.google.android.media.tv.companionlibrary.TvPlayer
    public long getDuration() {
        VOPlayer vOPlayer;
        if (!this.prepared || (vOPlayer = this.voPlayer) == null) {
            return 0L;
        }
        return vOPlayer.getDuration();
    }

    @Override // com.google.android.media.tv.companionlibrary.TvPlayer
    public void pause() {
        VOPlayer vOPlayer;
        if (!this.prepared || (vOPlayer = this.voPlayer) == null) {
            return;
        }
        vOPlayer.pause();
    }

    @Override // com.google.android.media.tv.companionlibrary.TvPlayer
    public void seekTo(long position) {
        VOPlayer vOPlayer;
        if (!this.prepared || (vOPlayer = this.voPlayer) == null) {
            return;
        }
        vOPlayer.seekTo((int) position);
    }

    @Override // com.google.android.media.tv.companionlibrary.TvPlayer
    public void registerCallback(TvPlayer.Callback callback) {
        if (callback != null) {
            this.callbacks.add(callback);
        }
    }

    @Override // com.google.android.media.tv.companionlibrary.TvPlayer
    public void unregisterCallback(TvPlayer.Callback callback) {
        if (callback != null) {
            this.callbacks.remove(callback);
        }
    }

    @Override // com.google.android.media.tv.companionlibrary.TvPlayer
    public long getCurrentPosition() {
        VOPlayer vOPlayer;
        if (!this.prepared || (vOPlayer = this.voPlayer) == null) {
            return 0L;
        }
        return vOPlayer.getCurrentPosition();
    }

    @Override // com.google.android.media.tv.companionlibrary.TvPlayer
    public void play() {
        VOPlayer vOPlayer;
        if (!this.prepared || (vOPlayer = this.voPlayer) == null) {
            return;
        }
        vOPlayer.start();
    }

    @Override // com.google.android.media.tv.companionlibrary.TvPlayer
    public void setSurface(Surface surface) {
        Log.d(this.TAG, "setSurface: " + surface);
        VOPlayer vOPlayer = this.voPlayer;
        if (vOPlayer != null) {
            vOPlayer.setTIFVideoSurface(surface);
        }
    }

    @Override // com.google.android.media.tv.companionlibrary.TvPlayer
    public void setVolume(float volume) {
        Log.d(this.TAG, "setVolume: " + volume + " (prepared: " + this.prepared + ", muted: " + this.muted + ')');
        boolean z = volume == 0.0f;
        if (this.prepared) {
            if (this.muted != z) {
                VOPlayer vOPlayer = this.voPlayer;
                if (vOPlayer != null) {
                    vOPlayer.setMuted(z);
                }
                this.muted = z;
                return;
            }
            return;
        }
        this.shoudlMuteOnPrepared = z;
    }

    @Override // com.viaccessorca.voplayer.VOPlayer.OnPreparedListener
    public void onPrepared(VOPlayer voPlayer) {
        Log.d(this.TAG, "onPrepared: " + voPlayer);
        if (voPlayer != null) {
            voPlayer.start();
        }
        this.session.notifyVideoAvailable();
        getAllTracks();
        if (this.shoudlMuteOnPrepared) {
            if (voPlayer != null) {
                voPlayer.setMuted(true);
            }
            this.muted = true;
        }
        this.prepared = true;
    }

    @Override // com.viaccessorca.voplayer.VOPlayer.OnErrorListener
    public boolean onError(VOPlayer voPlayer, int error, int extra) {
        String streamURL;
        Log.w(this.TAG, "onError: " + error + ": " + extra);
        SentryEvent sentryEvent = new SentryEvent();
        Message message = new Message();
        message.setMessage("VOPlayer error");
        sentryEvent.setMessage(message);
        if (voPlayer != null && (streamURL = voPlayer.getStreamURL()) != null) {
            sentryEvent.setExtra(ImagesContract.URL, streamURL);
        }
        sentryEvent.setExtra("error", Integer.valueOf(error));
        sentryEvent.setExtra("extra", Integer.valueOf(extra));
        sentryEvent.setTag("voplayer.error.extra", String.valueOf(extra));
        if (extra == 4001) {
            Log.v(this.TAG, " -> Restart NanoCdn");
            Context_ExtensionKt.restartNanoCdnSession(this.context);
        }
        if (VOTVPlayerKt.RECOVERABLE_ERROR_CODES.contains(new Pair(Integer.valueOf(error), Integer.valueOf(extra))) && this.session.getRetryCountOnPlayerError() < 2) {
            Log.v(this.TAG, " -> Error recovered");
            this.session.restartCurrentChannel();
            sentryEvent.setExtra("recovered", true);
            sentryEvent.setLevel(SentryLevel.WARNING);
        } else {
            Log.v(this.TAG, " -> Unrecoverable error");
            sentryEvent.setExtra("recovered", false);
            sentryEvent.setLevel(SentryLevel.ERROR);
            OTTLiveTvInputService.TvInputSessionImpl tvInputSessionImpl = this.session;
            Bundle bundle = new Bundle();
            bundle.putSerializable("error", new CommonUtils.VOPlayerError(error, extra));
            tvInputSessionImpl.notifySessionEvent(CommonUtils.TIS_EVENT_EXTRA_ERROR, bundle);
            this.session.notifyVideoUnavailable(extra);
        }
        Sentry.captureEvent(sentryEvent, SentryReportHint.VOPlayer);
        return true;
    }

    @Override // com.viaccessorca.voplayer.VOPlayer.OnInformationListener
    public boolean onInformation(VOPlayer voPlayer, int what, int extra, Object obj) {
        Log.v(this.TAG, "onInformation: " + what + " : " + extra + " Error");
        if (what == 3000) {
            if (extra != 3009) {
                return true;
            }
            this.session.restartCurrentChannel();
            Log.v(this.TAG, "onInformation: " + what + " : " + extra + " channel restarted before DRM session expire");
            return true;
        }
        if (what != 25000) {
            return true;
        }
        if (25001 != extra) {
            if (25002 != extra) {
                return true;
            }
            Log.i(this.TAG, "Smartlib get URL sucess");
            return true;
        }
        if (obj != null) {
            StreamingSessionResult streamingSessionResult = (StreamingSessionResult) obj;
            if (this.mContext != null) {
                BPKPlayerError bPKPlayerError = BPKPlayerError.INSTANCE.getBPKPlayerError(streamingSessionResult.getErrorCode());
                Log.v(this.TAG, "onInformation: " + what + " : " + extra + " Error -> " + streamingSessionResult.getErrorCode());
                if (bPKPlayerError != null) {
                    OTTLiveTvInputService.TvInputSessionImpl tvInputSessionImpl = this.session;
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("error", new CommonUtils.VOPlayerError(VOPlayerBpkExtended.MEDIA_INFO_SMARTLIB_GET_URL_ERROR, streamingSessionResult.getErrorCode()));
                    tvInputSessionImpl.notifySessionEvent(CommonUtils.TIS_EVENT_EXTRA_ERROR, bundle);
                    this.session.notifyVideoUnavailable(streamingSessionResult.getErrorCode());
                    if (voPlayer != null) {
                        voPlayer.pause();
                    }
                }
            }
            Log.e(this.TAG, "Get URL return an error with code:" + streamingSessionResult.getErrorCode() + " and message:" + streamingSessionResult.getErrorMessage());
            return true;
        }
        throw new TypeCastException("null cannot be cast to non-null type tv.broadpeak.smartlib.session.streaming.StreamingSessionResult");
    }

    @Override // com.viaccessorca.voplayer.VOPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(VOPlayer voPlayer, int percentage) {
        Log.w(this.TAG, "onBufferingUpdate: " + percentage);
        if (percentage == 100) {
            this.session.notifyVideoAvailable();
        } else {
            this.session.notifyVideoUnavailable(3);
        }
    }

    @Override // com.viaccessorca.voplayer.VOPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(VOPlayer voPlayer, int videoWidth, int videoHeight) {
        Log.d(this.TAG, "onVideoSizeChanged: " + videoWidth + " x " + videoHeight);
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("    surfaceSize: ");
        Size size = this.surfaceSize;
        sb.append(size != null ? Integer.valueOf(size.getWidth()) : null);
        sb.append(" x ");
        Size size2 = this.surfaceSize;
        sb.append(size2 != null ? Integer.valueOf(size2.getHeight()) : null);
        Log.d(str, sb.toString());
    }

    public final VOPlayer.PlayerState getState() {
        VOPlayer.PlayerState state;
        VOPlayer vOPlayer = this.voPlayer;
        return (vOPlayer == null || (state = vOPlayer.getState()) == null) ? VOPlayer.PlayerState.RELEASED : state;
    }

    public final void setVideoOverlayView(VOTIFVideoOverlayView overlayView) {
        Intrinsics.checkParameterIsNotNull(overlayView, "overlayView");
        overlayView.setSubtitleCustomCSSStyling(".text-sub { color: white !important;font-size: x-large !important;-webkit-text-stroke: 1px black !important;font-family: Arial, Helvetica, sans-serif !important;font-weight: bold !important;height: auto !important;} .text-webvtt { background-color:transparent;}");
        overlayView.setUIOnTop(true);
        VOPlayer vOPlayer = this.voPlayer;
        if (vOPlayer != null) {
            vOPlayer.enableSubtitlesOSD(true);
        }
        VOPlayer vOPlayer2 = this.voPlayer;
        if (vOPlayer2 != null) {
            vOPlayer2.setTIFVideoOverlayView(null);
        }
        VOPlayer vOPlayer3 = this.voPlayer;
        if (vOPlayer3 != null) {
            vOPlayer3.setTIFVideoOverlayView(overlayView);
        }
    }

    public final boolean setAudioTrack(String id) {
        VOPlayer vOPlayer = this.voPlayer;
        if (vOPlayer == null || vOPlayer.setAudioTrack(id) != 0) {
            return false;
        }
        this.session.notifyTrackSelected(0, id);
        return true;
    }

    public final boolean setSubtitleTrack(String id) {
        VOSubtitleTrack it;
        VOSubtitleTrack[] subtitleTracks;
        if (id == null) {
            VOPlayer vOPlayer = this.voPlayer;
            if (vOPlayer != null) {
                vOPlayer.setSubtitleVisibility(false);
            }
            this.session.notifyTrackSelected(2, null);
            return true;
        }
        VOPlayer vOPlayer2 = this.voPlayer;
        if (vOPlayer2 == null || (subtitleTracks = vOPlayer2.getSubtitleTracks()) == null) {
            it = null;
        } else {
            int length = subtitleTracks.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    it = subtitleTracks[i];
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    if (Intrinsics.areEqual(it.getName(), id)) {
                        break;
                    }
                    i++;
                } else {
                    throw new NoSuchElementException("Array contains no element matching the predicate.");
                }
            }
        }
        VOPlayer vOPlayer3 = this.voPlayer;
        if (vOPlayer3 != null) {
            if (vOPlayer3.setSubtitleTrack(it != null ? it.getName() : null, it != null ? it.getSubtitleType() : 0) == 0) {
                VOPlayer vOPlayer4 = this.voPlayer;
                if (vOPlayer4 != null) {
                    vOPlayer4.setSubtitleVisibility(true);
                }
                this.session.notifyTrackSelected(2, id);
                return true;
            }
        }
        VOPlayer vOPlayer5 = this.voPlayer;
        if (vOPlayer5 != null) {
            vOPlayer5.setSubtitleVisibility(false);
        }
        return false;
    }

    private final void getAllTracks() {
        String id;
        VOSubtitleTrack[] subtitleTracks;
        VOAudioTrack[] audioTracks;
        ArrayList arrayList = new ArrayList();
        String id2 = (String) null;
        VOPlayer vOPlayer = this.voPlayer;
        if (vOPlayer == null || (audioTracks = vOPlayer.getAudioTracks()) == null) {
            id = id2;
        } else {
            id = id2;
            for (VOAudioTrack track : audioTracks) {
                Intrinsics.checkExpressionValueIsNotNull(track, "track");
                TvTrackInfo.Builder builder = new TvTrackInfo.Builder(0, track.getName());
                builder.setLanguage(track.getLanguage());
                TvTrackInfo trackInfo = builder.build();
                arrayList.add(trackInfo);
                if (track.IsSelected()) {
                    Intrinsics.checkExpressionValueIsNotNull(trackInfo, "trackInfo");
                    id = trackInfo.getId();
                }
            }
        }
        VOPlayer vOPlayer2 = this.voPlayer;
        if (vOPlayer2 != null && (subtitleTracks = vOPlayer2.getSubtitleTracks()) != null) {
            for (VOSubtitleTrack track2 : subtitleTracks) {
                Intrinsics.checkExpressionValueIsNotNull(track2, "track");
                TvTrackInfo.Builder builder2 = new TvTrackInfo.Builder(2, track2.getName());
                builder2.setLanguage(track2.getLanguage());
                TvTrackInfo trackInfo2 = builder2.build();
                arrayList.add(trackInfo2);
                if (track2.IsSelected()) {
                    Intrinsics.checkExpressionValueIsNotNull(trackInfo2, "trackInfo");
                    id2 = trackInfo2.getId();
                }
            }
        }
        this.session.notifyTracksChanged(arrayList);
        if (id != null) {
            this.session.notifyTrackSelected(0, id);
        }
        if (id2 != null) {
            this.session.notifyTrackSelected(2, id2);
        }
    }

    public final void setSurfaceSize(int width, int height) {
        Log.d(this.TAG, "setSurfaceSize(" + width + ", " + height + ')');
        this.surfaceSize = new Size(width, height);
    }

    public final void setAnalyticsData(String key, String value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        VOPlayer vOPlayer = this.voPlayer;
        if (vOPlayer != null) {
            vOPlayer.setAnalyticsMetadata(key, value);
        }
    }
}
