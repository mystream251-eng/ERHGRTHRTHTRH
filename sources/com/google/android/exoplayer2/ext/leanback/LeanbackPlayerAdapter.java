package com.google.android.exoplayer2.ext.leanback;

import android.content.Context;
import android.os.Handler;
import android.util.Pair;
import android.view.Surface;
import android.view.SurfaceHolder;
import androidx.leanback.media.PlaybackGlueHost;
import androidx.leanback.media.PlayerAdapter;
import androidx.leanback.media.SurfaceHolderGlueHost;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ControlDispatcher;
import com.google.android.exoplayer2.DefaultControlDispatcher;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.util.ErrorMessageProvider;
import com.google.android.exoplayer2.video.VideoListener;

/* JADX INFO: loaded from: classes.dex */
public final class LeanbackPlayerAdapter extends PlayerAdapter implements Runnable {
    private final Context context;
    private ErrorMessageProvider<? super ExoPlaybackException> errorMessageProvider;
    private boolean hasSurface;
    private boolean lastNotifiedPreparedState;
    private PlaybackPreparer playbackPreparer;
    private final Player player;
    private SurfaceHolderGlueHost surfaceHolderGlueHost;
    private final int updatePeriodMs;
    private final Handler handler = new Handler();
    private final ComponentListener componentListener = new ComponentListener();
    private ControlDispatcher controlDispatcher = new DefaultControlDispatcher();

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.leanback");
    }

    public LeanbackPlayerAdapter(Context context, Player player, int i) {
        this.context = context;
        this.player = player;
        this.updatePeriodMs = i;
    }

    public void setPlaybackPreparer(PlaybackPreparer playbackPreparer) {
        this.playbackPreparer = playbackPreparer;
    }

    public void setControlDispatcher(ControlDispatcher controlDispatcher) {
        if (controlDispatcher == null) {
            controlDispatcher = new DefaultControlDispatcher();
        }
        this.controlDispatcher = controlDispatcher;
    }

    public void setErrorMessageProvider(ErrorMessageProvider<? super ExoPlaybackException> errorMessageProvider) {
        this.errorMessageProvider = errorMessageProvider;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.leanback.media.PlayerAdapter
    public void onAttachedToHost(PlaybackGlueHost playbackGlueHost) {
        if (playbackGlueHost instanceof SurfaceHolderGlueHost) {
            SurfaceHolderGlueHost surfaceHolderGlueHost = (SurfaceHolderGlueHost) playbackGlueHost;
            this.surfaceHolderGlueHost = surfaceHolderGlueHost;
            surfaceHolderGlueHost.setSurfaceHolderCallback(this.componentListener);
        }
        notifyStateChanged();
        this.player.addListener(this.componentListener);
        Player.VideoComponent videoComponent = this.player.getVideoComponent();
        if (videoComponent != null) {
            videoComponent.addVideoListener(this.componentListener);
        }
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void onDetachedFromHost() {
        this.player.removeListener(this.componentListener);
        Player.VideoComponent videoComponent = this.player.getVideoComponent();
        if (videoComponent != null) {
            videoComponent.removeVideoListener(this.componentListener);
        }
        SurfaceHolderGlueHost surfaceHolderGlueHost = this.surfaceHolderGlueHost;
        if (surfaceHolderGlueHost != null) {
            removeSurfaceHolderCallback(surfaceHolderGlueHost);
            this.surfaceHolderGlueHost = null;
        }
        this.hasSurface = false;
        PlayerAdapter.Callback callback = getCallback();
        callback.onBufferingStateChanged(this, false);
        callback.onPlayStateChanged(this);
        maybeNotifyPreparedStateChanged(callback);
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void setProgressUpdatingEnabled(boolean z) {
        this.handler.removeCallbacks(this);
        if (z) {
            this.handler.post(this);
        }
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public boolean isPlaying() {
        int playbackState = this.player.getPlaybackState();
        return (playbackState == 1 || playbackState == 4 || !this.player.getPlayWhenReady()) ? false : true;
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public long getDuration() {
        long duration = this.player.getDuration();
        if (duration == C.TIME_UNSET) {
            return -1L;
        }
        return duration;
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public long getCurrentPosition() {
        if (this.player.getPlaybackState() == 1) {
            return -1L;
        }
        return this.player.getCurrentPosition();
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void play() {
        if (this.player.getPlaybackState() == 1) {
            PlaybackPreparer playbackPreparer = this.playbackPreparer;
            if (playbackPreparer != null) {
                playbackPreparer.preparePlayback();
            }
        } else if (this.player.getPlaybackState() == 4) {
            ControlDispatcher controlDispatcher = this.controlDispatcher;
            Player player = this.player;
            controlDispatcher.dispatchSeekTo(player, player.getCurrentWindowIndex(), C.TIME_UNSET);
        }
        if (this.controlDispatcher.dispatchSetPlayWhenReady(this.player, true)) {
            getCallback().onPlayStateChanged(this);
        }
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void pause() {
        if (this.controlDispatcher.dispatchSetPlayWhenReady(this.player, false)) {
            getCallback().onPlayStateChanged(this);
        }
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void seekTo(long j) {
        ControlDispatcher controlDispatcher = this.controlDispatcher;
        Player player = this.player;
        controlDispatcher.dispatchSeekTo(player, player.getCurrentWindowIndex(), j);
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public long getBufferedPosition() {
        return this.player.getBufferedPosition();
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public boolean isPrepared() {
        return this.player.getPlaybackState() != 1 && (this.surfaceHolderGlueHost == null || this.hasSurface);
    }

    @Override // java.lang.Runnable
    public void run() {
        PlayerAdapter.Callback callback = getCallback();
        callback.onCurrentPositionChanged(this);
        callback.onBufferedPositionChanged(this);
        this.handler.postDelayed(this, this.updatePeriodMs);
    }

    void setVideoSurface(Surface surface) {
        this.hasSurface = surface != null;
        Player.VideoComponent videoComponent = this.player.getVideoComponent();
        if (videoComponent != null) {
            videoComponent.setVideoSurface(surface);
        }
        maybeNotifyPreparedStateChanged(getCallback());
    }

    void notifyStateChanged() {
        int playbackState = this.player.getPlaybackState();
        PlayerAdapter.Callback callback = getCallback();
        maybeNotifyPreparedStateChanged(callback);
        callback.onPlayStateChanged(this);
        callback.onBufferingStateChanged(this, playbackState == 2);
        if (playbackState == 4) {
            callback.onPlayCompleted(this);
        }
    }

    private void maybeNotifyPreparedStateChanged(PlayerAdapter.Callback callback) {
        boolean zIsPrepared = isPrepared();
        if (this.lastNotifiedPreparedState != zIsPrepared) {
            this.lastNotifiedPreparedState = zIsPrepared;
            callback.onPreparedStateChanged(this);
        }
    }

    private static void removeSurfaceHolderCallback(SurfaceHolderGlueHost surfaceHolderGlueHost) {
        surfaceHolderGlueHost.setSurfaceHolderCallback(null);
    }

    private final class ComponentListener implements Player.EventListener, SurfaceHolder.Callback, VideoListener {
        @Override // com.google.android.exoplayer2.video.VideoListener
        public void onRenderedFirstFrame() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }

        private ComponentListener() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            LeanbackPlayerAdapter.this.setVideoSurface(surfaceHolder.getSurface());
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            LeanbackPlayerAdapter.this.setVideoSurface(null);
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onPlayerStateChanged(boolean z, int i) {
            LeanbackPlayerAdapter.this.notifyStateChanged();
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onPlayerError(ExoPlaybackException exoPlaybackException) {
            PlayerAdapter.Callback callback = LeanbackPlayerAdapter.this.getCallback();
            if (LeanbackPlayerAdapter.this.errorMessageProvider != null) {
                Pair<Integer, String> errorMessage = LeanbackPlayerAdapter.this.errorMessageProvider.getErrorMessage(exoPlaybackException);
                callback.onError(LeanbackPlayerAdapter.this, ((Integer) errorMessage.first).intValue(), (String) errorMessage.second);
            } else {
                callback.onError(LeanbackPlayerAdapter.this, exoPlaybackException.type, LeanbackPlayerAdapter.this.context.getString(androidx.leanback.R.string.lb_media_player_error, Integer.valueOf(exoPlaybackException.type), Integer.valueOf(exoPlaybackException.rendererIndex)));
            }
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onTimelineChanged(Timeline timeline, Object obj, int i) {
            PlayerAdapter.Callback callback = LeanbackPlayerAdapter.this.getCallback();
            callback.onDurationChanged(LeanbackPlayerAdapter.this);
            callback.onCurrentPositionChanged(LeanbackPlayerAdapter.this);
            callback.onBufferedPositionChanged(LeanbackPlayerAdapter.this);
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onPositionDiscontinuity(int i) {
            PlayerAdapter.Callback callback = LeanbackPlayerAdapter.this.getCallback();
            callback.onCurrentPositionChanged(LeanbackPlayerAdapter.this);
            callback.onBufferedPositionChanged(LeanbackPlayerAdapter.this);
        }

        @Override // com.google.android.exoplayer2.video.VideoListener
        public void onVideoSizeChanged(int i, int i2, int i3, float f) {
            LeanbackPlayerAdapter.this.getCallback().onVideoSizeChanged(LeanbackPlayerAdapter.this, i, i2);
        }
    }
}
