package com.dotscreen.tvinput;

import android.app.ActivityManager;
import android.content.Context;
import android.media.tv.TvInputService;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.model.PlaybackInfo;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.bokit.NotSubscribedException;
import com.dotscreen.tv.common.bokit.RightVBackendErrorException;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.dotscreen.tv.common.bokit.RightVPlaybackInfo;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tvinput.voPlayer.VOTVPlayer;
import com.google.android.media.tv.companionlibrary.TvPlayer;
import com.google.android.media.tv.companionlibrary.model.Channel;
import com.google.android.media.tv.companionlibrary.model.Program;
import com.google.android.media.tv.companionlibrary.model.RecordedProgram;
import com.viaccessorca.voplayer.VOTIFVideoOverlayView;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: OTTLiveTvInputService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/dotscreen/tvinput/OTTLiveTvInputService;", "Lcom/dotscreen/tvinput/BaseMegaTvInputService;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "isSubscribed", "", "sessionsCount", "", "onCreateSession", "Landroid/media/tv/TvInputService$Session;", "inputId", "TvInputSessionImpl", "tvinput_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class OTTLiveTvInputService extends BaseMegaTvInputService {
    private final String TAG = OTTLiveTvInputService.class.getSimpleName();
    private boolean isSubscribed;
    private int sessionsCount;

    @Override // android.media.tv.TvInputService
    public TvInputService.Session onCreateSession(String inputId) {
        Log.d(this.TAG, "onCreateSession(), sessionCount = " + this.sessionsCount + ' ');
        this.sessionsCount = this.sessionsCount + 1;
        TvInputSessionImpl tvInputSessionImpl = new TvInputSessionImpl(this, this, inputId);
        tvInputSessionImpl.setOverlayViewEnabled(true);
        TvInputService.Session sessionSessionCreated = super.sessionCreated(tvInputSessionImpl);
        Intrinsics.checkExpressionValueIsNotNull(sessionSessionCreated, "super.sessionCreated(session)");
        return sessionSessionCreated;
    }

    /* JADX INFO: compiled from: OTTLiveTvInputService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J\n\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020!H\u0016J\b\u0010'\u001a\u00020(H\u0016J\u0012\u0010)\u001a\u00020!2\b\u0010*\u001a\u0004\u0018\u00010+H\u0017J\u001a\u0010,\u001a\u00020\u000e2\b\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010/\u001a\u000200H\u0016J\u0012\u00101\u001a\u00020\u000e2\b\u00102\u001a\u0004\u0018\u000103H\u0016J\b\u00104\u001a\u00020!H\u0016J\u001a\u00105\u001a\u00020\u000e2\u0006\u00106\u001a\u00020\u00102\b\u00107\u001a\u0004\u0018\u00010\u0006H\u0016J\u0010\u00108\u001a\u00020!2\u0006\u00109\u001a\u00020\u000eH\u0016J\u0010\u0010:\u001a\u00020!2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0012\u0010;\u001a\u00020\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J \u0010<\u001a\u00020!2\u0006\u0010=\u001a\u00020\u00102\u0006\u0010>\u001a\u00020\u00102\u0006\u0010?\u001a\u00020\u0010H\u0016J\u0010\u0010@\u001a\u00020\u000e2\u0006\u0010A\u001a\u00020BH\u0016J\u001c\u0010@\u001a\u00020\u000e2\b\u0010A\u001a\u0004\u0018\u00010B2\b\u0010C\u001a\u0004\u0018\u00010DH\u0016J\b\u0010E\u001a\u00020!H\u0002J\u0006\u0010F\u001a\u00020!J\u0010\u0010G\u001a\u00020!2\u0006\u0010H\u001a\u00020\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001f¨\u0006I"}, d2 = {"Lcom/dotscreen/tvinput/OTTLiveTvInputService$TvInputSessionImpl;", "Lcom/dotscreen/tvinput/BaseMegaTvInputService$Session;", "Lcom/dotscreen/tvinput/BaseMegaTvInputService;", "context", "Landroid/content/Context;", "inputId", "", "(Lcom/dotscreen/tvinput/OTTLiveTvInputService;Landroid/content/Context;Ljava/lang/String;)V", "currentChannelId", "handler", "Landroid/os/Handler;", "playbackInfoDisposable", "Lio/reactivex/disposables/Disposable;", "released", "", "retryCountOnPlayerError", "", "getRetryCountOnPlayerError", "()I", "setRetryCountOnPlayerError", "(I)V", "surface", "Landroid/view/Surface;", "surfaceHeight", "surfaceWidth", "tvPlayer", "Lcom/dotscreen/tvinput/voPlayer/VOTVPlayer;", "videoOverlay", "Lcom/viaccessorca/voplayer/VOTIFVideoOverlayView;", "volume", "", "Ljava/lang/Float;", "createPlayer", "", "playbackInfo", "Lcom/dotscreen/tv/common/bokit/RightVPlaybackInfo;", "getTvPlayer", "Lcom/google/android/media/tv/companionlibrary/TvPlayer;", "notifyVideoAvailable", "onCreateOverlayView", "Landroid/view/View;", "onPlayChannel", "channel", "Lcom/google/android/media/tv/companionlibrary/model/Channel;", "onPlayProgram", "program", "Lcom/google/android/media/tv/companionlibrary/model/Program;", "startPosMs", "", "onPlayRecordedProgram", "recordedProgram", "Lcom/google/android/media/tv/companionlibrary/model/RecordedProgram;", "onRelease", "onSelectTrack", "type", "trackId", "onSetCaptionEnabled", "enabled", "onSetStreamVolume", "onSetSurface", "onSurfaceChanged", "format", ToolboxAnalyticsModuleKt.M_WIDTH, ToolboxAnalyticsModuleKt.M_HEIGHT, "onTune", "channelUri", "Landroid/net/Uri;", "params", "Landroid/os/Bundle;", "releasePlayer", "restartCurrentChannel", "setOverlayViewEnabled", "enable", "tvinput_prodRelease"}, k = 1, mv = {1, 1, 16})
    public final class TvInputSessionImpl extends BaseMegaTvInputService.Session {
        private final Context context;
        private String currentChannelId;
        private final Handler handler;
        private final String inputId;
        private Disposable playbackInfoDisposable;
        private boolean released;
        private int retryCountOnPlayerError;
        private Surface surface;
        private int surfaceHeight;
        private int surfaceWidth;
        final /* synthetic */ OTTLiveTvInputService this$0;
        private VOTVPlayer tvPlayer;
        private VOTIFVideoOverlayView videoOverlay;
        private Float volume;

        @Override // com.dotscreen.tvinput.BaseMegaTvInputService.Session
        public boolean onPlayProgram(Program program, long startPosMs) {
            return true;
        }

        @Override // com.dotscreen.tvinput.BaseMegaTvInputService.Session
        public boolean onPlayRecordedProgram(RecordedProgram recordedProgram) {
            return false;
        }

        @Override // android.media.tv.TvInputService.Session
        public void onSetCaptionEnabled(boolean enabled) {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TvInputSessionImpl(OTTLiveTvInputService oTTLiveTvInputService, Context context, String str) {
            super(context, str);
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.this$0 = oTTLiveTvInputService;
            this.context = context;
            this.inputId = str;
            this.handler = new Handler();
        }

        public final int getRetryCountOnPlayerError() {
            return this.retryCountOnPlayerError;
        }

        public final void setRetryCountOnPlayerError(int i) {
            this.retryCountOnPlayerError = i;
        }

        @Override // com.dotscreen.tvinput.BaseMegaTvInputService.Session
        public TvPlayer getTvPlayer() {
            return this.tvPlayer;
        }

        @Override // android.media.tv.TvInputService.Session
        public void notifyVideoAvailable() {
            super.notifyVideoAvailable();
            this.retryCountOnPlayerError = 0;
        }

        @Override // android.media.tv.TvInputService.Session
        public void setOverlayViewEnabled(boolean enable) {
            Log.d(this.this$0.TAG, "setOverlayViewEnabled(" + enable + ')');
            super.setOverlayViewEnabled(enable);
        }

        @Override // android.media.tv.TvInputService.Session
        public View onCreateOverlayView() {
            Log.d(this.this$0.TAG, "onCreateOverlayView()");
            if (this.videoOverlay == null) {
                VOTIFVideoOverlayView vOTIFVideoOverlayView = new VOTIFVideoOverlayView(this.context);
                this.videoOverlay = vOTIFVideoOverlayView;
                VOTVPlayer vOTVPlayer = this.tvPlayer;
                if (vOTVPlayer != null) {
                    if (vOTIFVideoOverlayView == null) {
                        Intrinsics.throwNpe();
                    }
                    vOTVPlayer.setVideoOverlayView(vOTIFVideoOverlayView);
                }
            }
            VOTIFVideoOverlayView vOTIFVideoOverlayView2 = this.videoOverlay;
            if (vOTIFVideoOverlayView2 == null) {
                Intrinsics.throwNpe();
            }
            return vOTIFVideoOverlayView2;
        }

        @Override // com.dotscreen.tvinput.BaseMegaTvInputService.Session, android.media.tv.TvInputService.Session
        public boolean onSetSurface(Surface surface) {
            Log.d(this.this$0.TAG, "onSetSurface: " + surface);
            this.surface = surface;
            return super.onSetSurface(surface);
        }

        @Override // android.media.tv.TvInputService.Session
        public void onSurfaceChanged(int format, int width, int height) {
            Log.d(this.this$0.TAG, "onSurfaceChanged: " + format + " - " + width + " x " + height);
            this.surfaceWidth = width;
            this.surfaceHeight = height;
            VOTVPlayer vOTVPlayer = this.tvPlayer;
            if (vOTVPlayer != null) {
                vOTVPlayer.setSurfaceSize(width, height);
            }
            super.onSurfaceChanged(format, width, height);
        }

        @Override // com.dotscreen.tvinput.BaseMegaTvInputService.Session, android.media.tv.TvInputService.Session
        public void onSetStreamVolume(float volume) {
            Log.d(this.this$0.TAG, "onSetStreamVolume: " + volume);
            this.volume = Float.valueOf(volume);
            super.onSetStreamVolume(volume);
        }

        @Override // com.dotscreen.tvinput.BaseMegaTvInputService.Session
        public void onPlayChannel(final Channel channel) {
            Log.d(this.this$0.TAG, "onPlayChannel: " + channel);
            if (this.released) {
                Log.w(this.this$0.TAG, "onPlayChannel called after onRelease(), skipping !!");
                return;
            }
            Disposable disposable = this.playbackInfoDisposable;
            if (disposable != null) {
                disposable.dispose();
            }
            if (channel != null) {
                try {
                    if (!this.this$0.isSubscribed) {
                        throw new NotSubscribedException("Not subscribed to " + channel.getDisplayName());
                    }
                    Object obj = channel.getInternalProviderData().get(CommonUtils.KEY_RIGHTV_EXTERNAL_CHANNEL_ID);
                    if (obj == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                    }
                    String str = (String) obj;
                    if (!(!Intrinsics.areEqual(str, this.currentChannelId))) {
                        Log.d(this.this$0.TAG, "onPlayChannel: samechannelId");
                        notifyVideoAvailable();
                    } else {
                        notifyVideoUnavailable(3);
                        this.currentChannelId = str;
                        final BOService bOService = CommonUtils.getBOService(this.context);
                        this.playbackInfoDisposable = CommonUtils.getCurrentLineup(bOService).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tvinput.OTTLiveTvInputService$TvInputSessionImpl$onPlayChannel$1$1
                            @Override // io.reactivex.functions.Function
                            public final Observable<com.dotscreen.bokit.model.Channel> apply(Lineup it) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                return it.getItems();
                            }
                        }).filter(new Predicate<com.dotscreen.bokit.model.Channel>() { // from class: com.dotscreen.tvinput.OTTLiveTvInputService$TvInputSessionImpl$onPlayChannel$1$2
                            @Override // io.reactivex.functions.Predicate
                            public final boolean test(com.dotscreen.bokit.model.Channel it) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                return Intrinsics.areEqual(it.getId(), channel.getInternalProviderData().get(CommonUtils.KEY_OPTIMIZER_CHANNEL_ID));
                            }
                        }).firstElement().map(new Function<T, R>() { // from class: com.dotscreen.tvinput.OTTLiveTvInputService$TvInputSessionImpl$onPlayChannel$1$3
                            @Override // io.reactivex.functions.Function
                            public final RightVChannel apply(com.dotscreen.bokit.model.Channel it) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                return (RightVChannel) it;
                            }
                        }).flatMap(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tvinput.OTTLiveTvInputService$TvInputSessionImpl$onPlayChannel$1$4
                            @Override // io.reactivex.functions.Function
                            public final Maybe<PlaybackInfo> apply(RightVChannel it) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                UserService userService = bOService.getUserService();
                                if (userService != null) {
                                    return ((MegacableUserService) userService).basicLivePlaybackInfo(it.getExtras().getChannelExternalId());
                                }
                                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                            }
                        }).map(new Function<T, R>() { // from class: com.dotscreen.tvinput.OTTLiveTvInputService$TvInputSessionImpl$onPlayChannel$1$5
                            @Override // io.reactivex.functions.Function
                            public final RightVPlaybackInfo apply(PlaybackInfo it) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                return (RightVPlaybackInfo) it;
                            }
                        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<RightVPlaybackInfo>() { // from class: com.dotscreen.tvinput.OTTLiveTvInputService$TvInputSessionImpl$onPlayChannel$$inlined$let$lambda$1
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(RightVPlaybackInfo pi) throws IOException {
                                Log.d(this.this$0.this$0.TAG, "PlaybackInfos -> " + pi);
                                OTTLiveTvInputService.TvInputSessionImpl tvInputSessionImpl = this.this$0;
                                Intrinsics.checkExpressionValueIsNotNull(pi, "pi");
                                tvInputSessionImpl.createPlayer(pi);
                            }
                        }, new Consumer<Throwable>() { // from class: com.dotscreen.tvinput.OTTLiveTvInputService$TvInputSessionImpl$onPlayChannel$$inlined$let$lambda$2
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Throwable th) {
                                Log.w(this.this$0.this$0.TAG, "PlaybackInfos error " + th, th);
                                Throwable cause = th.getCause();
                                if ((cause != null ? cause instanceof RightVBackendErrorException : true) && th.getCause() != null) {
                                    Throwable cause2 = th.getCause();
                                    if (cause2 == null) {
                                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVBackendErrorException");
                                    }
                                    String backendCode = ((RightVBackendErrorException) cause2).getBackendCode();
                                    int iHashCode = backendCode.hashCode();
                                    if (iHashCode != -671584438) {
                                        if (iHashCode == 925924355) {
                                            if (backendCode.equals("GENERAL_SERVER_ERROR")) {
                                                this.this$0.notifyVideoUnavailable(CommonUtils.VIDEO_UNAVAILABLE_REASON_RIGHTV_GENERAL_SERVER_ERROR);
                                                return;
                                            }
                                        } else if (iHashCode == 985738276 && backendCode.equals("USER_NOT_SUBSCRIBED_TO_CHANNEL")) {
                                            this.this$0.notifyVideoUnavailable(513);
                                            return;
                                        }
                                    } else if (backendCode.equals("PLAYBACK_NOT_ALLOWED_OUT_OF_MEGACABLE_NETWORK")) {
                                        this.this$0.notifyVideoUnavailable(CommonUtils.VIDEO_UNAVAILABLE_REASON_RIGHTV_NOT_ALLOWED_OUT_OF_MEGACABLE_NETWORK);
                                        return;
                                    }
                                    OTTLiveTvInputService.TvInputSessionImpl tvInputSessionImpl = this.this$0;
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("error", th.getCause());
                                    tvInputSessionImpl.notifySessionEvent(CommonUtils.TIS_EVENT_EXTRA_ERROR, bundle);
                                    this.this$0.notifyVideoUnavailable(0);
                                    return;
                                }
                                OTTLiveTvInputService.TvInputSessionImpl tvInputSessionImpl2 = this.this$0;
                                Bundle bundle2 = new Bundle();
                                bundle2.putSerializable("error", th);
                                tvInputSessionImpl2.notifySessionEvent(CommonUtils.TIS_EVENT_EXTRA_ERROR, bundle2);
                                this.this$0.notifyVideoUnavailable(0);
                            }
                        }, new Action() { // from class: com.dotscreen.tvinput.OTTLiveTvInputService$TvInputSessionImpl$onPlayChannel$$inlined$let$lambda$3
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                Log.d(this.this$0.this$0.TAG, "PlaybackInfos no data");
                                this.this$0.notifyVideoUnavailable(513);
                            }
                        });
                    }
                } catch (Throwable th) {
                    Log.d(this.this$0.TAG, "onPlayChannel exception: ", th);
                    if (th instanceof NotSubscribedException) {
                        notifyVideoUnavailable(513);
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("error", th);
                    notifySessionEvent(CommonUtils.TIS_EVENT_EXTRA_ERROR, bundle);
                    notifyVideoUnavailable(0);
                }
            }
        }

        public final void restartCurrentChannel() {
            this.currentChannelId = (String) null;
            this.retryCountOnPlayerError++;
            onPlayChannel(getCurrentChannel());
        }

        @Override // com.dotscreen.tvinput.BaseMegaTvInputService.Session, android.media.tv.TvInputService.Session
        public void onRelease() {
            Log.d(this.this$0.TAG, "onRelease(), sessionCount = " + this.this$0.sessionsCount);
            this.released = true;
            this.currentChannelId = (String) null;
            Disposable disposable = this.playbackInfoDisposable;
            if (disposable != null) {
                disposable.dispose();
            }
            CommonUtils.getCompassAnalyticsModule(this.context).onCloseLiveStream();
            releasePlayer();
            super.onRelease();
            this.this$0.sessionsCount--;
        }

        @Override // android.media.tv.TvInputService.Session
        public boolean onTune(Uri channelUri, Bundle params) {
            String string;
            VOTVPlayer vOTVPlayer;
            if (params != null) {
                Log.v(this.this$0.TAG, "Bundle: " + params);
                Set<String> setKeySet = params.keySet();
                Intrinsics.checkExpressionValueIsNotNull(setKeySet, "bundle.keySet()");
                for (String key : setKeySet) {
                    Log.v(this.this$0.TAG, " haskey: " + key);
                    Intrinsics.checkExpressionValueIsNotNull(key, "key");
                    String strSubstringAfterLast = StringsKt.substringAfterLast(key, "DCA_DATA_", "");
                    if ((!StringsKt.isBlank(strSubstringAfterLast)) && (string = params.getString(key)) != null && (vOTVPlayer = this.tvPlayer) != null) {
                        vOTVPlayer.setAnalyticsData(strSubstringAfterLast, string);
                    }
                }
                this.this$0.isSubscribed = params.getBoolean("isSubscribed", false);
            }
            if (channelUri == null) {
                channelUri = Uri.parse("nullUri");
                Intrinsics.checkExpressionValueIsNotNull(channelUri, "Uri.parse(\"nullUri\")");
            }
            return onTune(channelUri);
        }

        @Override // com.dotscreen.tvinput.BaseMegaTvInputService.Session, android.media.tv.TvInputService.Session
        public boolean onTune(Uri channelUri) {
            Intrinsics.checkParameterIsNotNull(channelUri, "channelUri");
            DSLog.INSTANCE.printCurrentRAMUsage((ActivityManager) this.this$0.getApplicationContext().getSystemService("activity"));
            if (Intrinsics.areEqual(getCurrentChannelUri(), channelUri)) {
                notifyVideoAvailable();
                return true;
            }
            Uri uriBuild = channelUri.buildUpon().query("").build();
            this.retryCountOnPlayerError = 0;
            Log.d(this.this$0.TAG, "Tune to " + uriBuild);
            return super.onTune(uriBuild);
        }

        @Override // android.media.tv.TvInputService.Session
        public boolean onSelectTrack(int type, String trackId) {
            Log.d(this.this$0.TAG, "onSelectTrack(" + type + ", " + trackId + ')');
            Boolean boolValueOf = null;
            if (type == 0) {
                VOTVPlayer vOTVPlayer = this.tvPlayer;
                if (vOTVPlayer != null) {
                    boolValueOf = Boolean.valueOf(vOTVPlayer.setAudioTrack(trackId));
                }
            } else if (type == 2) {
                VOTVPlayer vOTVPlayer2 = this.tvPlayer;
                if (vOTVPlayer2 != null) {
                    boolValueOf = Boolean.valueOf(vOTVPlayer2.setSubtitleTrack(trackId));
                }
            } else {
                boolValueOf = false;
            }
            if (boolValueOf != null) {
                return boolValueOf.booleanValue();
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void createPlayer(RightVPlaybackInfo playbackInfo) throws IOException {
            Log.d(this.this$0.TAG, "createPlayer()");
            VOTVPlayer vOTVPlayer = this.tvPlayer;
            if (vOTVPlayer == null) {
                this.tvPlayer = new VOTVPlayer(this.context, playbackInfo, this.videoOverlay, this);
            } else if (vOTVPlayer != null) {
                vOTVPlayer.setPlaybackInfo(this.context, playbackInfo, this.videoOverlay);
            }
            VOTVPlayer vOTVPlayer2 = this.tvPlayer;
            if (vOTVPlayer2 != null) {
                vOTVPlayer2.setSurface(this.surface);
            }
            VOTVPlayer vOTVPlayer3 = this.tvPlayer;
            if (vOTVPlayer3 != null) {
                vOTVPlayer3.setSurfaceSize(this.surfaceWidth, this.surfaceHeight);
            }
            Float f = this.volume;
            if (f != null) {
                float fFloatValue = f.floatValue();
                VOTVPlayer vOTVPlayer4 = this.tvPlayer;
                if (vOTVPlayer4 != null) {
                    vOTVPlayer4.setVolume(fFloatValue);
                }
            }
        }

        private final void releasePlayer() {
            Log.d(this.this$0.TAG, "releasePlayer()");
            VOTVPlayer vOTVPlayer = this.tvPlayer;
            if (vOTVPlayer != null) {
                vOTVPlayer.release();
            }
            this.tvPlayer = (VOTVPlayer) null;
        }
    }
}
