package com.google.android.exoplayer2;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Pair;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.HandlerWrapper;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
final class ExoPlayerImplInternal implements Handler.Callback, MediaPeriod.Callback, TrackSelector.InvalidationListener, MediaSource.SourceInfoRefreshListener, DefaultMediaClock.PlaybackParameterListener, PlayerMessage.Sender {
    private static final int IDLE_INTERVAL_MS = 1000;
    private static final int MSG_DO_SOME_WORK = 2;
    public static final int MSG_ERROR = 2;
    private static final int MSG_PERIOD_PREPARED = 9;
    public static final int MSG_PLAYBACK_INFO_CHANGED = 0;
    public static final int MSG_PLAYBACK_PARAMETERS_CHANGED = 1;
    private static final int MSG_PLAYBACK_PARAMETERS_CHANGED_INTERNAL = 16;
    private static final int MSG_PREPARE = 0;
    private static final int MSG_REFRESH_SOURCE_INFO = 8;
    private static final int MSG_RELEASE = 7;
    private static final int MSG_SEEK_TO = 3;
    private static final int MSG_SEND_MESSAGE = 14;
    private static final int MSG_SEND_MESSAGE_TO_TARGET_THREAD = 15;
    private static final int MSG_SET_PLAYBACK_PARAMETERS = 4;
    private static final int MSG_SET_PLAY_WHEN_READY = 1;
    private static final int MSG_SET_REPEAT_MODE = 12;
    private static final int MSG_SET_SEEK_PARAMETERS = 5;
    private static final int MSG_SET_SHUFFLE_ENABLED = 13;
    private static final int MSG_SOURCE_CONTINUE_LOADING_REQUESTED = 10;
    private static final int MSG_STOP = 6;
    private static final int MSG_TRACK_SELECTION_INVALIDATED = 11;
    private static final int PREPARING_SOURCE_INTERVAL_MS = 10;
    private static final int RENDERING_INTERVAL_MS = 10;
    private static final String TAG = "ExoPlayerImplInternal";
    private final long backBufferDurationUs;
    private final BandwidthMeter bandwidthMeter;
    private final Clock clock;
    private final TrackSelectorResult emptyTrackSelectorResult;
    private Renderer[] enabledRenderers;
    private final Handler eventHandler;
    private final HandlerWrapper handler;
    private final HandlerThread internalPlaybackThread;
    private final LoadControl loadControl;
    private final DefaultMediaClock mediaClock;
    private MediaSource mediaSource;
    private int nextPendingMessageIndex;
    private SeekPosition pendingInitialSeekPosition;
    private final ArrayList<PendingMessageInfo> pendingMessages;
    private int pendingPrepareCount;
    private final Timeline.Period period;
    private boolean playWhenReady;
    private PlaybackInfo playbackInfo;
    private boolean rebuffering;
    private boolean released;
    private final RendererCapabilities[] rendererCapabilities;
    private long rendererPositionUs;
    private final Renderer[] renderers;
    private int repeatMode;
    private final boolean retainBackBufferFromKeyframe;
    private boolean shuffleModeEnabled;
    private final TrackSelector trackSelector;
    private final Timeline.Window window;
    private final MediaPeriodQueue queue = new MediaPeriodQueue();
    private SeekParameters seekParameters = SeekParameters.DEFAULT;
    private final PlaybackInfoUpdate playbackInfoUpdate = new PlaybackInfoUpdate();

    public ExoPlayerImplInternal(Renderer[] rendererArr, TrackSelector trackSelector, TrackSelectorResult trackSelectorResult, LoadControl loadControl, BandwidthMeter bandwidthMeter, boolean z, int i, boolean z2, Handler handler, Clock clock) {
        this.renderers = rendererArr;
        this.trackSelector = trackSelector;
        this.emptyTrackSelectorResult = trackSelectorResult;
        this.loadControl = loadControl;
        this.bandwidthMeter = bandwidthMeter;
        this.playWhenReady = z;
        this.repeatMode = i;
        this.shuffleModeEnabled = z2;
        this.eventHandler = handler;
        this.clock = clock;
        this.backBufferDurationUs = loadControl.getBackBufferDurationUs();
        this.retainBackBufferFromKeyframe = loadControl.retainBackBufferFromKeyframe();
        this.playbackInfo = PlaybackInfo.createDummy(C.TIME_UNSET, trackSelectorResult);
        this.rendererCapabilities = new RendererCapabilities[rendererArr.length];
        for (int i2 = 0; i2 < rendererArr.length; i2++) {
            rendererArr[i2].setIndex(i2);
            this.rendererCapabilities[i2] = rendererArr[i2].getCapabilities();
        }
        this.mediaClock = new DefaultMediaClock(this, clock);
        this.pendingMessages = new ArrayList<>();
        this.enabledRenderers = new Renderer[0];
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        trackSelector.init(this, bandwidthMeter);
        HandlerThread handlerThread = new HandlerThread("ExoPlayerImplInternal:Handler", -16);
        this.internalPlaybackThread = handlerThread;
        handlerThread.start();
        this.handler = clock.createHandler(this.internalPlaybackThread.getLooper(), this);
    }

    public void prepare(MediaSource mediaSource, boolean z, boolean z2) {
        this.handler.obtainMessage(0, z ? 1 : 0, z2 ? 1 : 0, mediaSource).sendToTarget();
    }

    public void setPlayWhenReady(boolean z) {
        this.handler.obtainMessage(1, z ? 1 : 0, 0).sendToTarget();
    }

    public void setRepeatMode(int i) {
        this.handler.obtainMessage(12, i, 0).sendToTarget();
    }

    public void setShuffleModeEnabled(boolean z) {
        this.handler.obtainMessage(13, z ? 1 : 0, 0).sendToTarget();
    }

    public void seekTo(Timeline timeline, int i, long j) {
        this.handler.obtainMessage(3, new SeekPosition(timeline, i, j)).sendToTarget();
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.handler.obtainMessage(4, playbackParameters).sendToTarget();
    }

    public void setSeekParameters(SeekParameters seekParameters) {
        this.handler.obtainMessage(5, seekParameters).sendToTarget();
    }

    public void stop(boolean z) {
        this.handler.obtainMessage(6, z ? 1 : 0, 0).sendToTarget();
    }

    @Override // com.google.android.exoplayer2.PlayerMessage.Sender
    public synchronized void sendMessage(PlayerMessage playerMessage) {
        if (this.released) {
            Log.w(TAG, "Ignoring messages sent after release.");
            playerMessage.markAsProcessed(false);
        } else {
            this.handler.obtainMessage(14, playerMessage).sendToTarget();
        }
    }

    public synchronized void release() {
        if (this.released) {
            return;
        }
        this.handler.sendEmptyMessage(7);
        boolean z = false;
        while (!this.released) {
            try {
                wait();
            } catch (InterruptedException unused) {
                z = true;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    public Looper getPlaybackLooper() {
        return this.internalPlaybackThread.getLooper();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource.SourceInfoRefreshListener
    public void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline, Object obj) {
        this.handler.obtainMessage(8, new MediaSourceRefreshInfo(mediaSource, timeline, obj)).sendToTarget();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(9, mediaPeriod).sendToTarget();
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(10, mediaPeriod).sendToTarget();
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelector.InvalidationListener
    public void onTrackSelectionsInvalidated() {
        this.handler.sendEmptyMessage(11);
    }

    @Override // com.google.android.exoplayer2.DefaultMediaClock.PlaybackParameterListener
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        this.handler.obtainMessage(16, playbackParameters).sendToTarget();
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        try {
            switch (message.what) {
                case 0:
                    prepareInternal((MediaSource) message.obj, message.arg1 != 0, message.arg2 != 0);
                    break;
                case 1:
                    setPlayWhenReadyInternal(message.arg1 != 0);
                    break;
                case 2:
                    doSomeWork();
                    break;
                case 3:
                    seekToInternal((SeekPosition) message.obj);
                    break;
                case 4:
                    setPlaybackParametersInternal((PlaybackParameters) message.obj);
                    break;
                case 5:
                    setSeekParametersInternal((SeekParameters) message.obj);
                    break;
                case 6:
                    stopInternal(message.arg1 != 0, true);
                    break;
                case 7:
                    releaseInternal();
                    return true;
                case 8:
                    handleSourceInfoRefreshed((MediaSourceRefreshInfo) message.obj);
                    break;
                case 9:
                    handlePeriodPrepared((MediaPeriod) message.obj);
                    break;
                case 10:
                    handleContinueLoadingRequested((MediaPeriod) message.obj);
                    break;
                case 11:
                    reselectTracksInternal();
                    break;
                case 12:
                    setRepeatModeInternal(message.arg1);
                    break;
                case 13:
                    setShuffleModeEnabledInternal(message.arg1 != 0);
                    break;
                case 14:
                    sendMessageInternal((PlayerMessage) message.obj);
                    break;
                case 15:
                    sendMessageToTargetThread((PlayerMessage) message.obj);
                    break;
                case 16:
                    handlePlaybackParameters((PlaybackParameters) message.obj);
                    break;
                default:
                    return false;
            }
            maybeNotifyPlaybackInfoChanged();
        } catch (ExoPlaybackException e) {
            Log.e(TAG, "Playback error.", e);
            stopInternal(false, false);
            this.eventHandler.obtainMessage(2, e).sendToTarget();
            maybeNotifyPlaybackInfoChanged();
        } catch (IOException e2) {
            Log.e(TAG, "Source error.", e2);
            stopInternal(false, false);
            this.eventHandler.obtainMessage(2, ExoPlaybackException.createForSource(e2)).sendToTarget();
            maybeNotifyPlaybackInfoChanged();
        } catch (RuntimeException e3) {
            Log.e(TAG, "Internal runtime error.", e3);
            stopInternal(false, false);
            this.eventHandler.obtainMessage(2, ExoPlaybackException.createForUnexpected(e3)).sendToTarget();
            maybeNotifyPlaybackInfoChanged();
        }
        return true;
    }

    private void setState(int i) {
        if (this.playbackInfo.playbackState != i) {
            this.playbackInfo = this.playbackInfo.copyWithPlaybackState(i);
        }
    }

    private void setIsLoading(boolean z) {
        if (this.playbackInfo.isLoading != z) {
            this.playbackInfo = this.playbackInfo.copyWithIsLoading(z);
        }
    }

    private void maybeNotifyPlaybackInfoChanged() {
        if (this.playbackInfoUpdate.hasPendingUpdate(this.playbackInfo)) {
            this.eventHandler.obtainMessage(0, this.playbackInfoUpdate.operationAcks, this.playbackInfoUpdate.positionDiscontinuity ? this.playbackInfoUpdate.discontinuityReason : -1, this.playbackInfo).sendToTarget();
            this.playbackInfoUpdate.reset(this.playbackInfo);
        }
    }

    private void prepareInternal(MediaSource mediaSource, boolean z, boolean z2) {
        this.pendingPrepareCount++;
        resetInternal(true, z, z2);
        this.loadControl.onPrepared();
        this.mediaSource = mediaSource;
        setState(2);
        mediaSource.prepareSource(this, this.bandwidthMeter.getTransferListener());
        this.handler.sendEmptyMessage(2);
    }

    private void setPlayWhenReadyInternal(boolean z) throws ExoPlaybackException {
        this.rebuffering = false;
        this.playWhenReady = z;
        if (!z) {
            stopRenderers();
            updatePlaybackPositions();
        } else if (this.playbackInfo.playbackState == 3) {
            startRenderers();
            this.handler.sendEmptyMessage(2);
        } else if (this.playbackInfo.playbackState == 2) {
            this.handler.sendEmptyMessage(2);
        }
    }

    private void setRepeatModeInternal(int i) throws ExoPlaybackException {
        this.repeatMode = i;
        if (!this.queue.updateRepeatMode(i)) {
            seekToCurrentPosition(true);
        }
        handleLoadingMediaPeriodChanged(false);
    }

    private void setShuffleModeEnabledInternal(boolean z) throws ExoPlaybackException {
        this.shuffleModeEnabled = z;
        if (!this.queue.updateShuffleModeEnabled(z)) {
            seekToCurrentPosition(true);
        }
        handleLoadingMediaPeriodChanged(false);
    }

    private void seekToCurrentPosition(boolean z) throws ExoPlaybackException {
        MediaSource.MediaPeriodId mediaPeriodId = this.queue.getPlayingPeriod().info.id;
        long jSeekToPeriodPosition = seekToPeriodPosition(mediaPeriodId, this.playbackInfo.positionUs, true);
        if (jSeekToPeriodPosition != this.playbackInfo.positionUs) {
            PlaybackInfo playbackInfo = this.playbackInfo;
            this.playbackInfo = playbackInfo.copyWithNewPosition(mediaPeriodId, jSeekToPeriodPosition, playbackInfo.contentPositionUs, getTotalBufferedDurationUs());
            if (z) {
                this.playbackInfoUpdate.setPositionDiscontinuity(4);
            }
        }
    }

    private void startRenderers() throws ExoPlaybackException {
        this.rebuffering = false;
        this.mediaClock.start();
        for (Renderer renderer : this.enabledRenderers) {
            renderer.start();
        }
    }

    private void stopRenderers() throws ExoPlaybackException {
        this.mediaClock.stop();
        for (Renderer renderer : this.enabledRenderers) {
            ensureStopped(renderer);
        }
    }

    private void updatePlaybackPositions() throws ExoPlaybackException {
        if (this.queue.hasPlayingPeriod()) {
            MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
            long discontinuity = playingPeriod.mediaPeriod.readDiscontinuity();
            if (discontinuity != C.TIME_UNSET) {
                resetRendererPosition(discontinuity);
                if (discontinuity != this.playbackInfo.positionUs) {
                    PlaybackInfo playbackInfo = this.playbackInfo;
                    this.playbackInfo = playbackInfo.copyWithNewPosition(playbackInfo.periodId, discontinuity, this.playbackInfo.contentPositionUs, getTotalBufferedDurationUs());
                    this.playbackInfoUpdate.setPositionDiscontinuity(4);
                }
            } else {
                long jSyncAndGetPositionUs = this.mediaClock.syncAndGetPositionUs();
                this.rendererPositionUs = jSyncAndGetPositionUs;
                long periodTime = playingPeriod.toPeriodTime(jSyncAndGetPositionUs);
                maybeTriggerPendingMessages(this.playbackInfo.positionUs, periodTime);
                this.playbackInfo.positionUs = periodTime;
            }
            MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
            this.playbackInfo.bufferedPositionUs = loadingPeriod.getBufferedPositionUs();
            this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
        }
    }

    private void doSomeWork() throws ExoPlaybackException, IOException {
        long jUptimeMillis = this.clock.uptimeMillis();
        updatePeriods();
        if (!this.queue.hasPlayingPeriod()) {
            maybeThrowPeriodPrepareError();
            scheduleNextWork(jUptimeMillis, 10L);
            return;
        }
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        TraceUtil.beginSection("doSomeWork");
        updatePlaybackPositions();
        long jElapsedRealtime = SystemClock.elapsedRealtime() * 1000;
        playingPeriod.mediaPeriod.discardBuffer(this.playbackInfo.positionUs - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
        boolean z = true;
        boolean z2 = true;
        for (Renderer renderer : this.enabledRenderers) {
            renderer.render(this.rendererPositionUs, jElapsedRealtime);
            z2 = z2 && renderer.isEnded();
            boolean z3 = renderer.isReady() || renderer.isEnded() || rendererWaitingForNextStream(renderer);
            if (!z3) {
                renderer.maybeThrowStreamError();
            }
            z = z && z3;
        }
        if (!z) {
            maybeThrowPeriodPrepareError();
        }
        long j = playingPeriod.info.durationUs;
        if (z2 && ((j == C.TIME_UNSET || j <= this.playbackInfo.positionUs) && playingPeriod.info.isFinal)) {
            setState(4);
            stopRenderers();
        } else if (this.playbackInfo.playbackState == 2 && shouldTransitionToReadyState(z)) {
            setState(3);
            if (this.playWhenReady) {
                startRenderers();
            }
        } else if (this.playbackInfo.playbackState == 3 && (this.enabledRenderers.length != 0 ? !z : !isTimelineReady())) {
            this.rebuffering = this.playWhenReady;
            setState(2);
            stopRenderers();
        }
        if (this.playbackInfo.playbackState == 2) {
            for (Renderer renderer2 : this.enabledRenderers) {
                renderer2.maybeThrowStreamError();
            }
        }
        if ((this.playWhenReady && this.playbackInfo.playbackState == 3) || this.playbackInfo.playbackState == 2) {
            scheduleNextWork(jUptimeMillis, 10L);
        } else if (this.enabledRenderers.length != 0 && this.playbackInfo.playbackState != 4) {
            scheduleNextWork(jUptimeMillis, 1000L);
        } else {
            this.handler.removeMessages(2);
        }
        TraceUtil.endSection();
    }

    private void scheduleNextWork(long j, long j2) {
        this.handler.removeMessages(2);
        this.handler.sendEmptyMessageAtTime(2, j + j2);
    }

    /* JADX WARN: Code duplicated, block: B:47:0x00d7 A[DONT_GENERATE] */
    /* JADX WARN: Code duplicated, block: B:56:? A[RETURN, SYNTHETIC] */
    private void seekToInternal(SeekPosition seekPosition) throws ExoPlaybackException {
        long jLongValue;
        boolean z;
        MediaSource.MediaPeriodId dummyFirstMediaPeriodId;
        long j;
        long j2;
        PlaybackInfo playbackInfoCopyWithNewPosition;
        long adjustedSeekPositionUs;
        boolean z2 = true;
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        Pair<Object, Long> pairResolveSeekPosition = resolveSeekPosition(seekPosition, true);
        if (pairResolveSeekPosition == null) {
            dummyFirstMediaPeriodId = this.playbackInfo.getDummyFirstMediaPeriodId(this.shuffleModeEnabled, this.window);
            z = true;
            jLongValue = -9223372036854775807L;
            j = -9223372036854775807L;
        } else {
            Object obj = pairResolveSeekPosition.first;
            long jLongValue2 = ((Long) pairResolveSeekPosition.second).longValue();
            MediaSource.MediaPeriodId mediaPeriodIdResolveMediaPeriodIdForAds = this.queue.resolveMediaPeriodIdForAds(obj, jLongValue2);
            if (mediaPeriodIdResolveMediaPeriodIdForAds.isAd()) {
                z = true;
                jLongValue = 0;
            } else {
                jLongValue = ((Long) pairResolveSeekPosition.second).longValue();
                z = seekPosition.windowPositionUs == C.TIME_UNSET;
            }
            dummyFirstMediaPeriodId = mediaPeriodIdResolveMediaPeriodIdForAds;
            j = jLongValue2;
        }
        try {
            if (this.mediaSource == null || this.pendingPrepareCount > 0) {
                this.pendingInitialSeekPosition = seekPosition;
            } else {
                if (jLongValue == C.TIME_UNSET) {
                    setState(4);
                    resetInternal(false, true, false);
                } else {
                    if (dummyFirstMediaPeriodId.equals(this.playbackInfo.periodId)) {
                        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
                        adjustedSeekPositionUs = (playingPeriod == null || jLongValue == 0) ? jLongValue : playingPeriod.mediaPeriod.getAdjustedSeekPositionUs(jLongValue, this.seekParameters);
                        if (C.usToMs(adjustedSeekPositionUs) == C.usToMs(this.playbackInfo.positionUs)) {
                            playbackInfoCopyWithNewPosition = this.playbackInfo.copyWithNewPosition(dummyFirstMediaPeriodId, this.playbackInfo.positionUs, j, getTotalBufferedDurationUs());
                            if (z) {
                                return;
                            } else {
                                return;
                            }
                        }
                    } else {
                        adjustedSeekPositionUs = jLongValue;
                    }
                    long jSeekToPeriodPosition = seekToPeriodPosition(dummyFirstMediaPeriodId, adjustedSeekPositionUs);
                    if (jLongValue == jSeekToPeriodPosition) {
                        z2 = false;
                    }
                    z |= z2;
                    j2 = jSeekToPeriodPosition;
                }
                playbackInfoCopyWithNewPosition = this.playbackInfo.copyWithNewPosition(dummyFirstMediaPeriodId, j2, j, getTotalBufferedDurationUs());
            }
            j2 = jLongValue;
            playbackInfoCopyWithNewPosition = this.playbackInfo.copyWithNewPosition(dummyFirstMediaPeriodId, j2, j, getTotalBufferedDurationUs());
        } finally {
            this.playbackInfo = this.playbackInfo.copyWithNewPosition(dummyFirstMediaPeriodId, jLongValue, j, getTotalBufferedDurationUs());
            if (z) {
                this.playbackInfoUpdate.setPositionDiscontinuity(2);
            }
        }
    }

    private long seekToPeriodPosition(MediaSource.MediaPeriodId mediaPeriodId, long j) throws ExoPlaybackException {
        return seekToPeriodPosition(mediaPeriodId, j, this.queue.getPlayingPeriod() != this.queue.getReadingPeriod());
    }

    private long seekToPeriodPosition(MediaSource.MediaPeriodId mediaPeriodId, long j, boolean z) throws ExoPlaybackException {
        stopRenderers();
        this.rebuffering = false;
        setState(2);
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        MediaPeriodHolder mediaPeriodHolderAdvancePlayingPeriod = playingPeriod;
        while (mediaPeriodHolderAdvancePlayingPeriod != null) {
            if (mediaPeriodId.equals(mediaPeriodHolderAdvancePlayingPeriod.info.id) && mediaPeriodHolderAdvancePlayingPeriod.prepared) {
                this.queue.removeAfter(mediaPeriodHolderAdvancePlayingPeriod);
                break;
            }
            mediaPeriodHolderAdvancePlayingPeriod = this.queue.advancePlayingPeriod();
        }
        if (playingPeriod != mediaPeriodHolderAdvancePlayingPeriod || z) {
            for (Renderer renderer : this.enabledRenderers) {
                disableRenderer(renderer);
            }
            this.enabledRenderers = new Renderer[0];
            playingPeriod = null;
        }
        if (mediaPeriodHolderAdvancePlayingPeriod != null) {
            updatePlayingPeriodRenderers(playingPeriod);
            if (mediaPeriodHolderAdvancePlayingPeriod.hasEnabledTracks) {
                long jSeekToUs = mediaPeriodHolderAdvancePlayingPeriod.mediaPeriod.seekToUs(j);
                mediaPeriodHolderAdvancePlayingPeriod.mediaPeriod.discardBuffer(jSeekToUs - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
                j = jSeekToUs;
            }
            resetRendererPosition(j);
            maybeContinueLoading();
        } else {
            this.queue.clear(true);
            this.playbackInfo = this.playbackInfo.copyWithTrackInfo(TrackGroupArray.EMPTY, this.emptyTrackSelectorResult);
            resetRendererPosition(j);
        }
        handleLoadingMediaPeriodChanged(false);
        this.handler.sendEmptyMessage(2);
        return j;
    }

    private void resetRendererPosition(long j) throws ExoPlaybackException {
        if (this.queue.hasPlayingPeriod()) {
            j = this.queue.getPlayingPeriod().toRendererTime(j);
        }
        this.rendererPositionUs = j;
        this.mediaClock.resetPosition(j);
        for (Renderer renderer : this.enabledRenderers) {
            renderer.resetPosition(this.rendererPositionUs);
        }
    }

    private void setPlaybackParametersInternal(PlaybackParameters playbackParameters) {
        this.mediaClock.setPlaybackParameters(playbackParameters);
    }

    private void setSeekParametersInternal(SeekParameters seekParameters) {
        this.seekParameters = seekParameters;
    }

    private void stopInternal(boolean z, boolean z2) {
        resetInternal(true, z, z);
        this.playbackInfoUpdate.incrementPendingOperationAcks(this.pendingPrepareCount + (z2 ? 1 : 0));
        this.pendingPrepareCount = 0;
        this.loadControl.onStopped();
        setState(1);
    }

    private void releaseInternal() {
        resetInternal(true, true, true);
        this.loadControl.onReleased();
        setState(1);
        this.internalPlaybackThread.quit();
        synchronized (this) {
            this.released = true;
            notifyAll();
        }
    }

    private void resetInternal(boolean z, boolean z2, boolean z3) {
        MediaSource mediaSource;
        this.handler.removeMessages(2);
        this.rebuffering = false;
        this.mediaClock.stop();
        this.rendererPositionUs = 0L;
        for (Renderer renderer : this.enabledRenderers) {
            try {
                disableRenderer(renderer);
            } catch (ExoPlaybackException | RuntimeException e) {
                Log.e(TAG, "Stop failed.", e);
            }
        }
        this.enabledRenderers = new Renderer[0];
        this.queue.clear(!z2);
        setIsLoading(false);
        if (z2) {
            this.pendingInitialSeekPosition = null;
        }
        if (z3) {
            this.queue.setTimeline(Timeline.EMPTY);
            Iterator<PendingMessageInfo> it = this.pendingMessages.iterator();
            while (it.hasNext()) {
                it.next().message.markAsProcessed(false);
            }
            this.pendingMessages.clear();
            this.nextPendingMessageIndex = 0;
        }
        MediaSource.MediaPeriodId dummyFirstMediaPeriodId = z2 ? this.playbackInfo.getDummyFirstMediaPeriodId(this.shuffleModeEnabled, this.window) : this.playbackInfo.periodId;
        long j = C.TIME_UNSET;
        long j2 = z2 ? -9223372036854775807L : this.playbackInfo.positionUs;
        if (!z2) {
            j = this.playbackInfo.contentPositionUs;
        }
        this.playbackInfo = new PlaybackInfo(z3 ? Timeline.EMPTY : this.playbackInfo.timeline, z3 ? null : this.playbackInfo.manifest, dummyFirstMediaPeriodId, j2, j, this.playbackInfo.playbackState, false, z3 ? TrackGroupArray.EMPTY : this.playbackInfo.trackGroups, z3 ? this.emptyTrackSelectorResult : this.playbackInfo.trackSelectorResult, dummyFirstMediaPeriodId, j2, 0L, j2);
        if (!z || (mediaSource = this.mediaSource) == null) {
            return;
        }
        mediaSource.releaseSource(this);
        this.mediaSource = null;
    }

    private void sendMessageInternal(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (playerMessage.getPositionMs() == C.TIME_UNSET) {
            sendMessageToTarget(playerMessage);
            return;
        }
        if (this.mediaSource == null || this.pendingPrepareCount > 0) {
            this.pendingMessages.add(new PendingMessageInfo(playerMessage));
            return;
        }
        PendingMessageInfo pendingMessageInfo = new PendingMessageInfo(playerMessage);
        if (resolvePendingMessagePosition(pendingMessageInfo)) {
            this.pendingMessages.add(pendingMessageInfo);
            Collections.sort(this.pendingMessages);
        } else {
            playerMessage.markAsProcessed(false);
        }
    }

    private void sendMessageToTarget(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (playerMessage.getHandler().getLooper() == this.handler.getLooper()) {
            deliverMessage(playerMessage);
            if (this.playbackInfo.playbackState == 3 || this.playbackInfo.playbackState == 2) {
                this.handler.sendEmptyMessage(2);
                return;
            }
            return;
        }
        this.handler.obtainMessage(15, playerMessage).sendToTarget();
    }

    private void sendMessageToTargetThread(final PlayerMessage playerMessage) {
        playerMessage.getHandler().post(new Runnable() { // from class: com.google.android.exoplayer2.-$$Lambda$ExoPlayerImplInternal$XwFxncwlyfAWA4k618O8BNtCsr0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$sendMessageToTargetThread$0$ExoPlayerImplInternal(playerMessage);
            }
        });
    }

    public /* synthetic */ void lambda$sendMessageToTargetThread$0$ExoPlayerImplInternal(PlayerMessage playerMessage) {
        try {
            deliverMessage(playerMessage);
        } catch (ExoPlaybackException e) {
            Log.e(TAG, "Unexpected error delivering message on external thread.", e);
            throw new RuntimeException(e);
        }
    }

    private void deliverMessage(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (playerMessage.isCanceled()) {
            return;
        }
        try {
            playerMessage.getTarget().handleMessage(playerMessage.getType(), playerMessage.getPayload());
        } finally {
            playerMessage.markAsProcessed(true);
        }
    }

    private void resolvePendingMessagePositions() {
        for (int size = this.pendingMessages.size() - 1; size >= 0; size--) {
            if (!resolvePendingMessagePosition(this.pendingMessages.get(size))) {
                this.pendingMessages.get(size).message.markAsProcessed(false);
                this.pendingMessages.remove(size);
            }
        }
        Collections.sort(this.pendingMessages);
    }

    private boolean resolvePendingMessagePosition(PendingMessageInfo pendingMessageInfo) {
        if (pendingMessageInfo.resolvedPeriodUid == null) {
            Pair<Object, Long> pairResolveSeekPosition = resolveSeekPosition(new SeekPosition(pendingMessageInfo.message.getTimeline(), pendingMessageInfo.message.getWindowIndex(), C.msToUs(pendingMessageInfo.message.getPositionMs())), false);
            if (pairResolveSeekPosition == null) {
                return false;
            }
            pendingMessageInfo.setResolvedPosition(this.playbackInfo.timeline.getIndexOfPeriod(pairResolveSeekPosition.first), ((Long) pairResolveSeekPosition.second).longValue(), pairResolveSeekPosition.first);
            return true;
        }
        int indexOfPeriod = this.playbackInfo.timeline.getIndexOfPeriod(pendingMessageInfo.resolvedPeriodUid);
        if (indexOfPeriod == -1) {
            return false;
        }
        pendingMessageInfo.resolvedPeriodIndex = indexOfPeriod;
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x003e, code lost:
    
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0077, code lost:
    
        r1 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void maybeTriggerPendingMessages(long r6, long r8) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.maybeTriggerPendingMessages(long, long):void");
    }

    private void ensureStopped(Renderer renderer) throws ExoPlaybackException {
        if (renderer.getState() == 2) {
            renderer.stop();
        }
    }

    private void disableRenderer(Renderer renderer) throws ExoPlaybackException {
        this.mediaClock.onRendererDisabled(renderer);
        ensureStopped(renderer);
        renderer.disable();
    }

    private void reselectTracksInternal() throws ExoPlaybackException {
        if (this.queue.hasPlayingPeriod()) {
            float f = this.mediaClock.getPlaybackParameters().speed;
            MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
            boolean z = true;
            for (MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod(); playingPeriod != null && playingPeriod.prepared; playingPeriod = playingPeriod.next) {
                if (!playingPeriod.selectTracks(f)) {
                    if (playingPeriod == readingPeriod) {
                        z = false;
                    }
                } else {
                    if (z) {
                        MediaPeriodHolder playingPeriod2 = this.queue.getPlayingPeriod();
                        boolean zRemoveAfter = this.queue.removeAfter(playingPeriod2);
                        boolean[] zArr = new boolean[this.renderers.length];
                        long jApplyTrackSelection = playingPeriod2.applyTrackSelection(this.playbackInfo.positionUs, zRemoveAfter, zArr);
                        if (this.playbackInfo.playbackState != 4 && jApplyTrackSelection != this.playbackInfo.positionUs) {
                            PlaybackInfo playbackInfo = this.playbackInfo;
                            this.playbackInfo = playbackInfo.copyWithNewPosition(playbackInfo.periodId, jApplyTrackSelection, this.playbackInfo.contentPositionUs, getTotalBufferedDurationUs());
                            this.playbackInfoUpdate.setPositionDiscontinuity(4);
                            resetRendererPosition(jApplyTrackSelection);
                        }
                        boolean[] zArr2 = new boolean[this.renderers.length];
                        int i = 0;
                        int i2 = 0;
                        while (true) {
                            Renderer[] rendererArr = this.renderers;
                            if (i >= rendererArr.length) {
                                break;
                            }
                            Renderer renderer = rendererArr[i];
                            zArr2[i] = renderer.getState() != 0;
                            SampleStream sampleStream = playingPeriod2.sampleStreams[i];
                            if (sampleStream != null) {
                                i2++;
                            }
                            if (zArr2[i]) {
                                if (sampleStream != renderer.getStream()) {
                                    disableRenderer(renderer);
                                } else if (zArr[i]) {
                                    renderer.resetPosition(this.rendererPositionUs);
                                }
                            }
                            i++;
                        }
                        this.playbackInfo = this.playbackInfo.copyWithTrackInfo(playingPeriod2.trackGroups, playingPeriod2.trackSelectorResult);
                        enableRenderers(zArr2, i2);
                    } else {
                        this.queue.removeAfter(playingPeriod);
                        if (playingPeriod.prepared) {
                            playingPeriod.applyTrackSelection(Math.max(playingPeriod.info.startPositionUs, playingPeriod.toPeriodTime(this.rendererPositionUs)), false);
                        }
                    }
                    handleLoadingMediaPeriodChanged(true);
                    if (this.playbackInfo.playbackState != 4) {
                        maybeContinueLoading();
                        updatePlaybackPositions();
                        this.handler.sendEmptyMessage(2);
                        return;
                    }
                    return;
                }
            }
        }
    }

    private void updateTrackSelectionPlaybackSpeed(float f) {
        for (MediaPeriodHolder frontPeriod = this.queue.getFrontPeriod(); frontPeriod != null; frontPeriod = frontPeriod.next) {
            if (frontPeriod.trackSelectorResult != null) {
                for (TrackSelection trackSelection : frontPeriod.trackSelectorResult.selections.getAll()) {
                    if (trackSelection != null) {
                        trackSelection.onPlaybackSpeed(f);
                    }
                }
            }
        }
    }

    private boolean shouldTransitionToReadyState(boolean z) {
        if (this.enabledRenderers.length == 0) {
            return isTimelineReady();
        }
        if (!z) {
            return false;
        }
        if (!this.playbackInfo.isLoading) {
            return true;
        }
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        return (loadingPeriod.isFullyBuffered() && loadingPeriod.info.isFinal) || this.loadControl.shouldStartPlayback(getTotalBufferedDurationUs(), this.mediaClock.getPlaybackParameters().speed, this.rebuffering);
    }

    private boolean isTimelineReady() {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        long j = playingPeriod.info.durationUs;
        return j == C.TIME_UNSET || this.playbackInfo.positionUs < j || (playingPeriod.next != null && (playingPeriod.next.prepared || playingPeriod.next.info.id.isAd()));
    }

    private void maybeThrowSourceInfoRefreshError() throws IOException {
        if (this.queue.getLoadingPeriod() != null) {
            for (Renderer renderer : this.enabledRenderers) {
                if (!renderer.hasReadStreamToEnd()) {
                    return;
                }
            }
        }
        this.mediaSource.maybeThrowSourceInfoRefreshError();
    }

    private void maybeThrowPeriodPrepareError() throws IOException {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (loadingPeriod == null || loadingPeriod.prepared) {
            return;
        }
        if (readingPeriod == null || readingPeriod.next == loadingPeriod) {
            for (Renderer renderer : this.enabledRenderers) {
                if (!renderer.hasReadStreamToEnd()) {
                    return;
                }
            }
            loadingPeriod.mediaPeriod.maybeThrowPrepareError();
        }
    }

    private void handleSourceInfoRefreshed(MediaSourceRefreshInfo mediaSourceRefreshInfo) throws ExoPlaybackException {
        if (mediaSourceRefreshInfo.source != this.mediaSource) {
            return;
        }
        Timeline timeline = this.playbackInfo.timeline;
        Timeline timeline2 = mediaSourceRefreshInfo.timeline;
        Object obj = mediaSourceRefreshInfo.manifest;
        this.queue.setTimeline(timeline2);
        this.playbackInfo = this.playbackInfo.copyWithTimeline(timeline2, obj);
        resolvePendingMessagePositions();
        int i = this.pendingPrepareCount;
        if (i > 0) {
            this.playbackInfoUpdate.incrementPendingOperationAcks(i);
            this.pendingPrepareCount = 0;
            SeekPosition seekPosition = this.pendingInitialSeekPosition;
            if (seekPosition == null) {
                if (this.playbackInfo.startPositionUs == C.TIME_UNSET) {
                    if (timeline2.isEmpty()) {
                        handleSourceInfoRefreshEndedPlayback();
                        return;
                    }
                    Pair<Object, Long> periodPosition = getPeriodPosition(timeline2, timeline2.getFirstWindowIndex(this.shuffleModeEnabled), C.TIME_UNSET);
                    Object obj2 = periodPosition.first;
                    long jLongValue = ((Long) periodPosition.second).longValue();
                    MediaSource.MediaPeriodId mediaPeriodIdResolveMediaPeriodIdForAds = this.queue.resolveMediaPeriodIdForAds(obj2, jLongValue);
                    this.playbackInfo = this.playbackInfo.resetToNewPosition(mediaPeriodIdResolveMediaPeriodIdForAds, mediaPeriodIdResolveMediaPeriodIdForAds.isAd() ? 0L : jLongValue, jLongValue);
                    return;
                }
                return;
            }
            try {
                Pair<Object, Long> pairResolveSeekPosition = resolveSeekPosition(seekPosition, true);
                this.pendingInitialSeekPosition = null;
                if (pairResolveSeekPosition == null) {
                    handleSourceInfoRefreshEndedPlayback();
                    return;
                }
                Object obj3 = pairResolveSeekPosition.first;
                long jLongValue2 = ((Long) pairResolveSeekPosition.second).longValue();
                MediaSource.MediaPeriodId mediaPeriodIdResolveMediaPeriodIdForAds2 = this.queue.resolveMediaPeriodIdForAds(obj3, jLongValue2);
                this.playbackInfo = this.playbackInfo.resetToNewPosition(mediaPeriodIdResolveMediaPeriodIdForAds2, mediaPeriodIdResolveMediaPeriodIdForAds2.isAd() ? 0L : jLongValue2, jLongValue2);
                return;
            } catch (IllegalSeekPositionException e) {
                this.playbackInfo = this.playbackInfo.resetToNewPosition(this.playbackInfo.getDummyFirstMediaPeriodId(this.shuffleModeEnabled, this.window), C.TIME_UNSET, C.TIME_UNSET);
                throw e;
            }
        }
        if (timeline.isEmpty()) {
            if (timeline2.isEmpty()) {
                return;
            }
            Pair<Object, Long> periodPosition2 = getPeriodPosition(timeline2, timeline2.getFirstWindowIndex(this.shuffleModeEnabled), C.TIME_UNSET);
            Object obj4 = periodPosition2.first;
            long jLongValue3 = ((Long) periodPosition2.second).longValue();
            MediaSource.MediaPeriodId mediaPeriodIdResolveMediaPeriodIdForAds3 = this.queue.resolveMediaPeriodIdForAds(obj4, jLongValue3);
            this.playbackInfo = this.playbackInfo.resetToNewPosition(mediaPeriodIdResolveMediaPeriodIdForAds3, mediaPeriodIdResolveMediaPeriodIdForAds3.isAd() ? 0L : jLongValue3, jLongValue3);
            return;
        }
        MediaPeriodHolder frontPeriod = this.queue.getFrontPeriod();
        long j = this.playbackInfo.contentPositionUs;
        Object obj5 = frontPeriod == null ? this.playbackInfo.periodId.periodUid : frontPeriod.uid;
        if (timeline2.getIndexOfPeriod(obj5) == -1) {
            Object objResolveSubsequentPeriod = resolveSubsequentPeriod(obj5, timeline, timeline2);
            if (objResolveSubsequentPeriod == null) {
                handleSourceInfoRefreshEndedPlayback();
                return;
            }
            Pair<Object, Long> periodPosition3 = getPeriodPosition(timeline2, timeline2.getPeriodByUid(objResolveSubsequentPeriod, this.period).windowIndex, C.TIME_UNSET);
            Object obj6 = periodPosition3.first;
            long jLongValue4 = ((Long) periodPosition3.second).longValue();
            MediaSource.MediaPeriodId mediaPeriodIdResolveMediaPeriodIdForAds4 = this.queue.resolveMediaPeriodIdForAds(obj6, jLongValue4);
            if (frontPeriod != null) {
                while (frontPeriod.next != null) {
                    frontPeriod = frontPeriod.next;
                    if (frontPeriod.info.id.equals(mediaPeriodIdResolveMediaPeriodIdForAds4)) {
                        frontPeriod.info = this.queue.getUpdatedMediaPeriodInfo(frontPeriod.info);
                    }
                }
            }
            this.playbackInfo = this.playbackInfo.copyWithNewPosition(mediaPeriodIdResolveMediaPeriodIdForAds4, seekToPeriodPosition(mediaPeriodIdResolveMediaPeriodIdForAds4, mediaPeriodIdResolveMediaPeriodIdForAds4.isAd() ? 0L : jLongValue4), jLongValue4, getTotalBufferedDurationUs());
            return;
        }
        MediaSource.MediaPeriodId mediaPeriodId = this.playbackInfo.periodId;
        if (mediaPeriodId.isAd()) {
            MediaSource.MediaPeriodId mediaPeriodIdResolveMediaPeriodIdForAds5 = this.queue.resolveMediaPeriodIdForAds(obj5, j);
            if (!mediaPeriodIdResolveMediaPeriodIdForAds5.equals(mediaPeriodId)) {
                this.playbackInfo = this.playbackInfo.copyWithNewPosition(mediaPeriodIdResolveMediaPeriodIdForAds5, seekToPeriodPosition(mediaPeriodIdResolveMediaPeriodIdForAds5, mediaPeriodIdResolveMediaPeriodIdForAds5.isAd() ? 0L : j), j, getTotalBufferedDurationUs());
                return;
            }
        }
        if (!this.queue.updateQueuedPeriods(mediaPeriodId, this.rendererPositionUs)) {
            seekToCurrentPosition(false);
        }
        handleLoadingMediaPeriodChanged(false);
    }

    private void handleSourceInfoRefreshEndedPlayback() {
        setState(4);
        resetInternal(false, true, false);
    }

    private Object resolveSubsequentPeriod(Object obj, Timeline timeline, Timeline timeline2) {
        int indexOfPeriod = timeline.getIndexOfPeriod(obj);
        int periodCount = timeline.getPeriodCount();
        int nextPeriodIndex = indexOfPeriod;
        int indexOfPeriod2 = -1;
        for (int i = 0; i < periodCount && indexOfPeriod2 == -1; i++) {
            nextPeriodIndex = timeline.getNextPeriodIndex(nextPeriodIndex, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            if (nextPeriodIndex == -1) {
                break;
            }
            indexOfPeriod2 = timeline2.getIndexOfPeriod(timeline.getUidOfPeriod(nextPeriodIndex));
        }
        if (indexOfPeriod2 == -1) {
            return null;
        }
        return timeline2.getUidOfPeriod(indexOfPeriod2);
    }

    private Pair<Object, Long> resolveSeekPosition(SeekPosition seekPosition, boolean z) {
        int indexOfPeriod;
        Timeline timeline = this.playbackInfo.timeline;
        Timeline timeline2 = seekPosition.timeline;
        if (timeline.isEmpty()) {
            return null;
        }
        if (timeline2.isEmpty()) {
            timeline2 = timeline;
        }
        try {
            Pair<Object, Long> periodPosition = timeline2.getPeriodPosition(this.window, this.period, seekPosition.windowIndex, seekPosition.windowPositionUs);
            if (timeline == timeline2 || (indexOfPeriod = timeline.getIndexOfPeriod(periodPosition.first)) != -1) {
                return periodPosition;
            }
            if (!z || resolveSubsequentPeriod(periodPosition.first, timeline2, timeline) == null) {
                return null;
            }
            return getPeriodPosition(timeline, timeline.getPeriod(indexOfPeriod, this.period).windowIndex, C.TIME_UNSET);
        } catch (IndexOutOfBoundsException unused) {
            throw new IllegalSeekPositionException(timeline, seekPosition.windowIndex, seekPosition.windowPositionUs);
        }
    }

    private Pair<Object, Long> getPeriodPosition(Timeline timeline, int i, long j) {
        return timeline.getPeriodPosition(this.window, this.period, i, j);
    }

    private void updatePeriods() throws ExoPlaybackException, IOException {
        MediaSource mediaSource = this.mediaSource;
        if (mediaSource == null) {
            return;
        }
        if (this.pendingPrepareCount > 0) {
            mediaSource.maybeThrowSourceInfoRefreshError();
            return;
        }
        maybeUpdateLoadingPeriod();
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        int i = 0;
        if (loadingPeriod == null || loadingPeriod.isFullyBuffered()) {
            setIsLoading(false);
        } else if (!this.playbackInfo.isLoading) {
            maybeContinueLoading();
        }
        if (!this.queue.hasPlayingPeriod()) {
            return;
        }
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        boolean z = false;
        while (this.playWhenReady && playingPeriod != readingPeriod && this.rendererPositionUs >= playingPeriod.next.getStartPositionRendererTime()) {
            if (z) {
                maybeNotifyPlaybackInfoChanged();
            }
            int i2 = playingPeriod.info.isLastInTimelinePeriod ? 0 : 3;
            MediaPeriodHolder mediaPeriodHolderAdvancePlayingPeriod = this.queue.advancePlayingPeriod();
            updatePlayingPeriodRenderers(playingPeriod);
            this.playbackInfo = this.playbackInfo.copyWithNewPosition(mediaPeriodHolderAdvancePlayingPeriod.info.id, mediaPeriodHolderAdvancePlayingPeriod.info.startPositionUs, mediaPeriodHolderAdvancePlayingPeriod.info.contentPositionUs, getTotalBufferedDurationUs());
            this.playbackInfoUpdate.setPositionDiscontinuity(i2);
            updatePlaybackPositions();
            z = true;
            playingPeriod = mediaPeriodHolderAdvancePlayingPeriod;
        }
        if (readingPeriod.info.isFinal) {
            while (true) {
                Renderer[] rendererArr = this.renderers;
                if (i >= rendererArr.length) {
                    return;
                }
                Renderer renderer = rendererArr[i];
                SampleStream sampleStream = readingPeriod.sampleStreams[i];
                if (sampleStream != null && renderer.getStream() == sampleStream && renderer.hasReadStreamToEnd()) {
                    renderer.setCurrentStreamFinal();
                }
                i++;
            }
        } else {
            if (readingPeriod.next == null) {
                return;
            }
            int i3 = 0;
            while (true) {
                Renderer[] rendererArr2 = this.renderers;
                if (i3 < rendererArr2.length) {
                    Renderer renderer2 = rendererArr2[i3];
                    SampleStream sampleStream2 = readingPeriod.sampleStreams[i3];
                    if (renderer2.getStream() != sampleStream2) {
                        return;
                    }
                    if (sampleStream2 != null && !renderer2.hasReadStreamToEnd()) {
                        return;
                    } else {
                        i3++;
                    }
                } else {
                    if (!readingPeriod.next.prepared) {
                        maybeThrowPeriodPrepareError();
                        return;
                    }
                    TrackSelectorResult trackSelectorResult = readingPeriod.trackSelectorResult;
                    MediaPeriodHolder mediaPeriodHolderAdvanceReadingPeriod = this.queue.advanceReadingPeriod();
                    TrackSelectorResult trackSelectorResult2 = mediaPeriodHolderAdvanceReadingPeriod.trackSelectorResult;
                    boolean z2 = mediaPeriodHolderAdvanceReadingPeriod.mediaPeriod.readDiscontinuity() != C.TIME_UNSET;
                    int i4 = 0;
                    while (true) {
                        Renderer[] rendererArr3 = this.renderers;
                        if (i4 >= rendererArr3.length) {
                            return;
                        }
                        Renderer renderer3 = rendererArr3[i4];
                        if (trackSelectorResult.isRendererEnabled(i4)) {
                            if (z2) {
                                renderer3.setCurrentStreamFinal();
                            } else if (!renderer3.isCurrentStreamFinal()) {
                                TrackSelection trackSelection = trackSelectorResult2.selections.get(i4);
                                boolean zIsRendererEnabled = trackSelectorResult2.isRendererEnabled(i4);
                                boolean z3 = this.rendererCapabilities[i4].getTrackType() == 6;
                                RendererConfiguration rendererConfiguration = trackSelectorResult.rendererConfigurations[i4];
                                RendererConfiguration rendererConfiguration2 = trackSelectorResult2.rendererConfigurations[i4];
                                if (zIsRendererEnabled && rendererConfiguration2.equals(rendererConfiguration) && !z3) {
                                    renderer3.replaceStream(getFormats(trackSelection), mediaPeriodHolderAdvanceReadingPeriod.sampleStreams[i4], mediaPeriodHolderAdvanceReadingPeriod.getRendererOffset());
                                } else {
                                    renderer3.setCurrentStreamFinal();
                                }
                            }
                        }
                        i4++;
                    }
                }
            }
        }
    }

    private void maybeUpdateLoadingPeriod() throws IOException {
        this.queue.reevaluateBuffer(this.rendererPositionUs);
        if (this.queue.shouldLoadNextMediaPeriod()) {
            MediaPeriodInfo nextMediaPeriodInfo = this.queue.getNextMediaPeriodInfo(this.rendererPositionUs, this.playbackInfo);
            if (nextMediaPeriodInfo == null) {
                maybeThrowSourceInfoRefreshError();
                return;
            }
            this.queue.enqueueNextMediaPeriod(this.rendererCapabilities, this.trackSelector, this.loadControl.getAllocator(), this.mediaSource, nextMediaPeriodInfo).prepare(this, nextMediaPeriodInfo.startPositionUs);
            setIsLoading(true);
            handleLoadingMediaPeriodChanged(false);
        }
    }

    private void handlePeriodPrepared(MediaPeriod mediaPeriod) throws ExoPlaybackException {
        if (this.queue.isLoading(mediaPeriod)) {
            MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
            loadingPeriod.handlePrepared(this.mediaClock.getPlaybackParameters().speed);
            updateLoadControlTrackSelection(loadingPeriod.trackGroups, loadingPeriod.trackSelectorResult);
            if (!this.queue.hasPlayingPeriod()) {
                resetRendererPosition(this.queue.advancePlayingPeriod().info.startPositionUs);
                updatePlayingPeriodRenderers(null);
            }
            maybeContinueLoading();
        }
    }

    private void handleContinueLoadingRequested(MediaPeriod mediaPeriod) {
        if (this.queue.isLoading(mediaPeriod)) {
            this.queue.reevaluateBuffer(this.rendererPositionUs);
            maybeContinueLoading();
        }
    }

    private void handlePlaybackParameters(PlaybackParameters playbackParameters) throws ExoPlaybackException {
        this.eventHandler.obtainMessage(1, playbackParameters).sendToTarget();
        updateTrackSelectionPlaybackSpeed(playbackParameters.speed);
        for (Renderer renderer : this.renderers) {
            if (renderer != null) {
                renderer.setOperatingRate(playbackParameters.speed);
            }
        }
    }

    private void maybeContinueLoading() {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        long nextLoadPositionUs = loadingPeriod.getNextLoadPositionUs();
        if (nextLoadPositionUs == Long.MIN_VALUE) {
            setIsLoading(false);
            return;
        }
        boolean zShouldContinueLoading = this.loadControl.shouldContinueLoading(getTotalBufferedDurationUs(nextLoadPositionUs), this.mediaClock.getPlaybackParameters().speed);
        setIsLoading(zShouldContinueLoading);
        if (zShouldContinueLoading) {
            loadingPeriod.continueLoading(this.rendererPositionUs);
        }
    }

    private void updatePlayingPeriodRenderers(MediaPeriodHolder mediaPeriodHolder) throws ExoPlaybackException {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod == null || mediaPeriodHolder == playingPeriod) {
            return;
        }
        boolean[] zArr = new boolean[this.renderers.length];
        int i = 0;
        int i2 = 0;
        while (true) {
            Renderer[] rendererArr = this.renderers;
            if (i < rendererArr.length) {
                Renderer renderer = rendererArr[i];
                zArr[i] = renderer.getState() != 0;
                if (playingPeriod.trackSelectorResult.isRendererEnabled(i)) {
                    i2++;
                }
                if (zArr[i] && (!playingPeriod.trackSelectorResult.isRendererEnabled(i) || (renderer.isCurrentStreamFinal() && renderer.getStream() == mediaPeriodHolder.sampleStreams[i]))) {
                    disableRenderer(renderer);
                }
                i++;
            } else {
                this.playbackInfo = this.playbackInfo.copyWithTrackInfo(playingPeriod.trackGroups, playingPeriod.trackSelectorResult);
                enableRenderers(zArr, i2);
                return;
            }
        }
    }

    private void enableRenderers(boolean[] zArr, int i) throws ExoPlaybackException {
        this.enabledRenderers = new Renderer[i];
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        int i2 = 0;
        for (int i3 = 0; i3 < this.renderers.length; i3++) {
            if (playingPeriod.trackSelectorResult.isRendererEnabled(i3)) {
                enableRenderer(i3, zArr[i3], i2);
                i2++;
            }
        }
    }

    private void enableRenderer(int i, boolean z, int i2) throws ExoPlaybackException {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        Renderer renderer = this.renderers[i];
        this.enabledRenderers[i2] = renderer;
        if (renderer.getState() == 0) {
            RendererConfiguration rendererConfiguration = playingPeriod.trackSelectorResult.rendererConfigurations[i];
            Format[] formats = getFormats(playingPeriod.trackSelectorResult.selections.get(i));
            boolean z2 = this.playWhenReady && this.playbackInfo.playbackState == 3;
            renderer.enable(rendererConfiguration, formats, playingPeriod.sampleStreams[i], this.rendererPositionUs, !z && z2, playingPeriod.getRendererOffset());
            this.mediaClock.onRendererEnabled(renderer);
            if (z2) {
                renderer.start();
            }
        }
    }

    private boolean rendererWaitingForNextStream(Renderer renderer) {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        return readingPeriod.next != null && readingPeriod.next.prepared && renderer.hasReadStreamToEnd();
    }

    private void handleLoadingMediaPeriodChanged(boolean z) {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        MediaSource.MediaPeriodId mediaPeriodId = loadingPeriod == null ? this.playbackInfo.periodId : loadingPeriod.info.id;
        boolean z2 = !this.playbackInfo.loadingMediaPeriodId.equals(mediaPeriodId);
        if (z2) {
            this.playbackInfo = this.playbackInfo.copyWithLoadingMediaPeriodId(mediaPeriodId);
        }
        PlaybackInfo playbackInfo = this.playbackInfo;
        playbackInfo.bufferedPositionUs = loadingPeriod == null ? playbackInfo.positionUs : loadingPeriod.getBufferedPositionUs();
        this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
        if ((z2 || z) && loadingPeriod != null && loadingPeriod.prepared) {
            updateLoadControlTrackSelection(loadingPeriod.trackGroups, loadingPeriod.trackSelectorResult);
        }
    }

    private long getTotalBufferedDurationUs() {
        return getTotalBufferedDurationUs(this.playbackInfo.bufferedPositionUs);
    }

    private long getTotalBufferedDurationUs(long j) {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        if (loadingPeriod == null) {
            return 0L;
        }
        return j - loadingPeriod.toPeriodTime(this.rendererPositionUs);
    }

    private void updateLoadControlTrackSelection(TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult) {
        this.loadControl.onTracksSelected(this.renderers, trackGroupArray, trackSelectorResult.selections);
    }

    private static Format[] getFormats(TrackSelection trackSelection) {
        int length = trackSelection != null ? trackSelection.length() : 0;
        Format[] formatArr = new Format[length];
        for (int i = 0; i < length; i++) {
            formatArr[i] = trackSelection.getFormat(i);
        }
        return formatArr;
    }

    private static final class SeekPosition {
        public final Timeline timeline;
        public final int windowIndex;
        public final long windowPositionUs;

        public SeekPosition(Timeline timeline, int i, long j) {
            this.timeline = timeline;
            this.windowIndex = i;
            this.windowPositionUs = j;
        }
    }

    private static final class PendingMessageInfo implements Comparable<PendingMessageInfo> {
        public final PlayerMessage message;
        public int resolvedPeriodIndex;
        public long resolvedPeriodTimeUs;
        public Object resolvedPeriodUid;

        public PendingMessageInfo(PlayerMessage playerMessage) {
            this.message = playerMessage;
        }

        public void setResolvedPosition(int i, long j, Object obj) {
            this.resolvedPeriodIndex = i;
            this.resolvedPeriodTimeUs = j;
            this.resolvedPeriodUid = obj;
        }

        @Override // java.lang.Comparable
        public int compareTo(PendingMessageInfo pendingMessageInfo) {
            if ((this.resolvedPeriodUid == null) != (pendingMessageInfo.resolvedPeriodUid == null)) {
                return this.resolvedPeriodUid != null ? -1 : 1;
            }
            if (this.resolvedPeriodUid == null) {
                return 0;
            }
            int i = this.resolvedPeriodIndex - pendingMessageInfo.resolvedPeriodIndex;
            return i != 0 ? i : Util.compareLong(this.resolvedPeriodTimeUs, pendingMessageInfo.resolvedPeriodTimeUs);
        }
    }

    private static final class MediaSourceRefreshInfo {
        public final Object manifest;
        public final MediaSource source;
        public final Timeline timeline;

        public MediaSourceRefreshInfo(MediaSource mediaSource, Timeline timeline, Object obj) {
            this.source = mediaSource;
            this.timeline = timeline;
            this.manifest = obj;
        }
    }

    private static final class PlaybackInfoUpdate {
        private int discontinuityReason;
        private PlaybackInfo lastPlaybackInfo;
        private int operationAcks;
        private boolean positionDiscontinuity;

        private PlaybackInfoUpdate() {
        }

        public boolean hasPendingUpdate(PlaybackInfo playbackInfo) {
            return playbackInfo != this.lastPlaybackInfo || this.operationAcks > 0 || this.positionDiscontinuity;
        }

        public void reset(PlaybackInfo playbackInfo) {
            this.lastPlaybackInfo = playbackInfo;
            this.operationAcks = 0;
            this.positionDiscontinuity = false;
        }

        public void incrementPendingOperationAcks(int i) {
            this.operationAcks += i;
        }

        public void setPositionDiscontinuity(int i) {
            if (this.positionDiscontinuity && this.discontinuityReason != 4) {
                Assertions.checkArgument(i == 4);
            } else {
                this.positionDiscontinuity = true;
                this.discontinuityReason = i;
            }
        }
    }
}
