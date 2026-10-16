package com.google.android.exoplayer2.analytics;

import android.view.Surface;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.audio.AudioListener;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.drm.DefaultDrmSessionEventListener;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.video.VideoListener;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes.dex */
public class AnalyticsCollector implements Player.EventListener, MetadataOutput, AudioRendererEventListener, VideoRendererEventListener, MediaSourceEventListener, BandwidthMeter.EventListener, DefaultDrmSessionEventListener, VideoListener, AudioListener {
    private final Clock clock;
    private final CopyOnWriteArraySet<AnalyticsListener> listeners;
    private final MediaPeriodQueueTracker mediaPeriodQueueTracker;
    private Player player;
    private final Timeline.Window window;

    @Override // com.google.android.exoplayer2.video.VideoListener
    public final void onRenderedFirstFrame() {
    }

    public static class Factory {
        public AnalyticsCollector createAnalyticsCollector(Player player, Clock clock) {
            return new AnalyticsCollector(player, clock);
        }
    }

    protected AnalyticsCollector(Player player, Clock clock) {
        if (player != null) {
            this.player = player;
        }
        this.clock = (Clock) Assertions.checkNotNull(clock);
        this.listeners = new CopyOnWriteArraySet<>();
        this.mediaPeriodQueueTracker = new MediaPeriodQueueTracker();
        this.window = new Timeline.Window();
    }

    public void addListener(AnalyticsListener analyticsListener) {
        this.listeners.add(analyticsListener);
    }

    public void removeListener(AnalyticsListener analyticsListener) {
        this.listeners.remove(analyticsListener);
    }

    public void setPlayer(Player player) {
        Assertions.checkState(this.player == null);
        this.player = (Player) Assertions.checkNotNull(player);
    }

    public final void notifySeekStarted() {
        if (this.mediaPeriodQueueTracker.isSeeking()) {
            return;
        }
        AnalyticsListener.EventTime eventTimeGeneratePlayingMediaPeriodEventTime = generatePlayingMediaPeriodEventTime();
        this.mediaPeriodQueueTracker.onSeekStarted();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onSeekStarted(eventTimeGeneratePlayingMediaPeriodEventTime);
        }
    }

    public final void resetForNewMediaSource() {
        for (MediaPeriodInfo mediaPeriodInfo : new ArrayList(this.mediaPeriodQueueTracker.mediaPeriodInfoQueue)) {
            onMediaPeriodReleased(mediaPeriodInfo.windowIndex, mediaPeriodInfo.mediaPeriodId);
        }
    }

    @Override // com.google.android.exoplayer2.metadata.MetadataOutput
    public final void onMetadata(Metadata metadata) {
        AnalyticsListener.EventTime eventTimeGeneratePlayingMediaPeriodEventTime = generatePlayingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onMetadata(eventTimeGeneratePlayingMediaPeriodEventTime, metadata);
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioRendererEventListener
    public final void onAudioEnabled(DecoderCounters decoderCounters) {
        AnalyticsListener.EventTime eventTimeGeneratePlayingMediaPeriodEventTime = generatePlayingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onDecoderEnabled(eventTimeGeneratePlayingMediaPeriodEventTime, 1, decoderCounters);
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioRendererEventListener
    public final void onAudioDecoderInitialized(String str, long j, long j2) {
        AnalyticsListener.EventTime eventTimeGenerateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onDecoderInitialized(eventTimeGenerateReadingMediaPeriodEventTime, 1, str, j2);
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioRendererEventListener
    public final void onAudioInputFormatChanged(Format format) {
        AnalyticsListener.EventTime eventTimeGenerateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onDecoderInputFormatChanged(eventTimeGenerateReadingMediaPeriodEventTime, 1, format);
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioRendererEventListener
    public final void onAudioSinkUnderrun(int i, long j, long j2) {
        AnalyticsListener.EventTime eventTimeGenerateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onAudioUnderrun(eventTimeGenerateReadingMediaPeriodEventTime, i, j, j2);
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioRendererEventListener
    public final void onAudioDisabled(DecoderCounters decoderCounters) {
        AnalyticsListener.EventTime eventTimeGenerateLastReportedPlayingMediaPeriodEventTime = generateLastReportedPlayingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onDecoderDisabled(eventTimeGenerateLastReportedPlayingMediaPeriodEventTime, 1, decoderCounters);
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioRendererEventListener
    public final void onAudioSessionId(int i) {
        AnalyticsListener.EventTime eventTimeGenerateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onAudioSessionId(eventTimeGenerateReadingMediaPeriodEventTime, i);
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioListener
    public void onAudioAttributesChanged(AudioAttributes audioAttributes) {
        AnalyticsListener.EventTime eventTimeGenerateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onAudioAttributesChanged(eventTimeGenerateReadingMediaPeriodEventTime, audioAttributes);
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioListener
    public void onVolumeChanged(float f) {
        AnalyticsListener.EventTime eventTimeGenerateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onVolumeChanged(eventTimeGenerateReadingMediaPeriodEventTime, f);
        }
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public final void onVideoEnabled(DecoderCounters decoderCounters) {
        AnalyticsListener.EventTime eventTimeGeneratePlayingMediaPeriodEventTime = generatePlayingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onDecoderEnabled(eventTimeGeneratePlayingMediaPeriodEventTime, 2, decoderCounters);
        }
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public final void onVideoDecoderInitialized(String str, long j, long j2) {
        AnalyticsListener.EventTime eventTimeGenerateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onDecoderInitialized(eventTimeGenerateReadingMediaPeriodEventTime, 2, str, j2);
        }
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public final void onVideoInputFormatChanged(Format format) {
        AnalyticsListener.EventTime eventTimeGenerateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onDecoderInputFormatChanged(eventTimeGenerateReadingMediaPeriodEventTime, 2, format);
        }
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public final void onDroppedFrames(int i, long j) {
        AnalyticsListener.EventTime eventTimeGenerateLastReportedPlayingMediaPeriodEventTime = generateLastReportedPlayingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onDroppedVideoFrames(eventTimeGenerateLastReportedPlayingMediaPeriodEventTime, i, j);
        }
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public final void onVideoDisabled(DecoderCounters decoderCounters) {
        AnalyticsListener.EventTime eventTimeGenerateLastReportedPlayingMediaPeriodEventTime = generateLastReportedPlayingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onDecoderDisabled(eventTimeGenerateLastReportedPlayingMediaPeriodEventTime, 2, decoderCounters);
        }
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public final void onRenderedFirstFrame(Surface surface) {
        AnalyticsListener.EventTime eventTimeGenerateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onRenderedFirstFrame(eventTimeGenerateReadingMediaPeriodEventTime, surface);
        }
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public final void onVideoSizeChanged(int i, int i2, int i3, float f) {
        AnalyticsListener.EventTime eventTimeGenerateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onVideoSizeChanged(eventTimeGenerateReadingMediaPeriodEventTime, i, i2, i3, f);
        }
    }

    @Override // com.google.android.exoplayer2.video.VideoListener
    public void onSurfaceSizeChanged(int i, int i2) {
        AnalyticsListener.EventTime eventTimeGenerateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onSurfaceSizeChanged(eventTimeGenerateReadingMediaPeriodEventTime, i, i2);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public final void onMediaPeriodCreated(int i, MediaSource.MediaPeriodId mediaPeriodId) {
        this.mediaPeriodQueueTracker.onMediaPeriodCreated(i, mediaPeriodId);
        AnalyticsListener.EventTime eventTimeGenerateMediaPeriodEventTime = generateMediaPeriodEventTime(i, mediaPeriodId);
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onMediaPeriodCreated(eventTimeGenerateMediaPeriodEventTime);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public final void onMediaPeriodReleased(int i, MediaSource.MediaPeriodId mediaPeriodId) {
        AnalyticsListener.EventTime eventTimeGenerateMediaPeriodEventTime = generateMediaPeriodEventTime(i, mediaPeriodId);
        if (this.mediaPeriodQueueTracker.onMediaPeriodReleased(mediaPeriodId)) {
            Iterator<AnalyticsListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onMediaPeriodReleased(eventTimeGenerateMediaPeriodEventTime);
            }
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public final void onLoadStarted(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaSourceEventListener.LoadEventInfo loadEventInfo, MediaSourceEventListener.MediaLoadData mediaLoadData) {
        AnalyticsListener.EventTime eventTimeGenerateMediaPeriodEventTime = generateMediaPeriodEventTime(i, mediaPeriodId);
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onLoadStarted(eventTimeGenerateMediaPeriodEventTime, loadEventInfo, mediaLoadData);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public final void onLoadCompleted(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaSourceEventListener.LoadEventInfo loadEventInfo, MediaSourceEventListener.MediaLoadData mediaLoadData) {
        AnalyticsListener.EventTime eventTimeGenerateMediaPeriodEventTime = generateMediaPeriodEventTime(i, mediaPeriodId);
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onLoadCompleted(eventTimeGenerateMediaPeriodEventTime, loadEventInfo, mediaLoadData);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public final void onLoadCanceled(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaSourceEventListener.LoadEventInfo loadEventInfo, MediaSourceEventListener.MediaLoadData mediaLoadData) {
        AnalyticsListener.EventTime eventTimeGenerateMediaPeriodEventTime = generateMediaPeriodEventTime(i, mediaPeriodId);
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onLoadCanceled(eventTimeGenerateMediaPeriodEventTime, loadEventInfo, mediaLoadData);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public final void onLoadError(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaSourceEventListener.LoadEventInfo loadEventInfo, MediaSourceEventListener.MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        AnalyticsListener.EventTime eventTimeGenerateMediaPeriodEventTime = generateMediaPeriodEventTime(i, mediaPeriodId);
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onLoadError(eventTimeGenerateMediaPeriodEventTime, loadEventInfo, mediaLoadData, iOException, z);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public final void onReadingStarted(int i, MediaSource.MediaPeriodId mediaPeriodId) {
        this.mediaPeriodQueueTracker.onReadingStarted(mediaPeriodId);
        AnalyticsListener.EventTime eventTimeGenerateMediaPeriodEventTime = generateMediaPeriodEventTime(i, mediaPeriodId);
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onReadingStarted(eventTimeGenerateMediaPeriodEventTime);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public final void onUpstreamDiscarded(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaSourceEventListener.MediaLoadData mediaLoadData) {
        AnalyticsListener.EventTime eventTimeGenerateMediaPeriodEventTime = generateMediaPeriodEventTime(i, mediaPeriodId);
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onUpstreamDiscarded(eventTimeGenerateMediaPeriodEventTime, mediaLoadData);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public final void onDownstreamFormatChanged(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaSourceEventListener.MediaLoadData mediaLoadData) {
        AnalyticsListener.EventTime eventTimeGenerateMediaPeriodEventTime = generateMediaPeriodEventTime(i, mediaPeriodId);
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onDownstreamFormatChanged(eventTimeGenerateMediaPeriodEventTime, mediaLoadData);
        }
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public final void onTimelineChanged(Timeline timeline, Object obj, int i) {
        this.mediaPeriodQueueTracker.onTimelineChanged(timeline);
        AnalyticsListener.EventTime eventTimeGeneratePlayingMediaPeriodEventTime = generatePlayingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onTimelineChanged(eventTimeGeneratePlayingMediaPeriodEventTime, i);
        }
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public final void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        AnalyticsListener.EventTime eventTimeGeneratePlayingMediaPeriodEventTime = generatePlayingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onTracksChanged(eventTimeGeneratePlayingMediaPeriodEventTime, trackGroupArray, trackSelectionArray);
        }
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public final void onLoadingChanged(boolean z) {
        AnalyticsListener.EventTime eventTimeGeneratePlayingMediaPeriodEventTime = generatePlayingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onLoadingChanged(eventTimeGeneratePlayingMediaPeriodEventTime, z);
        }
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public final void onPlayerStateChanged(boolean z, int i) {
        AnalyticsListener.EventTime eventTimeGeneratePlayingMediaPeriodEventTime = generatePlayingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onPlayerStateChanged(eventTimeGeneratePlayingMediaPeriodEventTime, z, i);
        }
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public final void onRepeatModeChanged(int i) {
        AnalyticsListener.EventTime eventTimeGeneratePlayingMediaPeriodEventTime = generatePlayingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onRepeatModeChanged(eventTimeGeneratePlayingMediaPeriodEventTime, i);
        }
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public final void onShuffleModeEnabledChanged(boolean z) {
        AnalyticsListener.EventTime eventTimeGeneratePlayingMediaPeriodEventTime = generatePlayingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onShuffleModeChanged(eventTimeGeneratePlayingMediaPeriodEventTime, z);
        }
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public final void onPlayerError(ExoPlaybackException exoPlaybackException) {
        AnalyticsListener.EventTime eventTimeGeneratePlayingMediaPeriodEventTime;
        if (exoPlaybackException.type == 0) {
            eventTimeGeneratePlayingMediaPeriodEventTime = generateLoadingMediaPeriodEventTime();
        } else {
            eventTimeGeneratePlayingMediaPeriodEventTime = generatePlayingMediaPeriodEventTime();
        }
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onPlayerError(eventTimeGeneratePlayingMediaPeriodEventTime, exoPlaybackException);
        }
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public final void onPositionDiscontinuity(int i) {
        this.mediaPeriodQueueTracker.onPositionDiscontinuity(i);
        AnalyticsListener.EventTime eventTimeGeneratePlayingMediaPeriodEventTime = generatePlayingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onPositionDiscontinuity(eventTimeGeneratePlayingMediaPeriodEventTime, i);
        }
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public final void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        AnalyticsListener.EventTime eventTimeGeneratePlayingMediaPeriodEventTime = generatePlayingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onPlaybackParametersChanged(eventTimeGeneratePlayingMediaPeriodEventTime, playbackParameters);
        }
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public final void onSeekProcessed() {
        if (this.mediaPeriodQueueTracker.isSeeking()) {
            this.mediaPeriodQueueTracker.onSeekProcessed();
            AnalyticsListener.EventTime eventTimeGeneratePlayingMediaPeriodEventTime = generatePlayingMediaPeriodEventTime();
            Iterator<AnalyticsListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onSeekProcessed(eventTimeGeneratePlayingMediaPeriodEventTime);
            }
        }
    }

    @Override // com.google.android.exoplayer2.upstream.BandwidthMeter.EventListener
    public final void onBandwidthSample(int i, long j, long j2) {
        AnalyticsListener.EventTime eventTimeGenerateLoadingMediaPeriodEventTime = generateLoadingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onBandwidthEstimate(eventTimeGenerateLoadingMediaPeriodEventTime, i, j, j2);
        }
    }

    @Override // com.google.android.exoplayer2.drm.DefaultDrmSessionEventListener
    public final void onDrmSessionAcquired() {
        AnalyticsListener.EventTime eventTimeGenerateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onDrmSessionAcquired(eventTimeGenerateReadingMediaPeriodEventTime);
        }
    }

    @Override // com.google.android.exoplayer2.drm.DefaultDrmSessionEventListener
    public final void onDrmKeysLoaded() {
        AnalyticsListener.EventTime eventTimeGenerateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onDrmKeysLoaded(eventTimeGenerateReadingMediaPeriodEventTime);
        }
    }

    @Override // com.google.android.exoplayer2.drm.DefaultDrmSessionEventListener
    public final void onDrmSessionManagerError(Exception exc) {
        AnalyticsListener.EventTime eventTimeGenerateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onDrmSessionManagerError(eventTimeGenerateReadingMediaPeriodEventTime, exc);
        }
    }

    @Override // com.google.android.exoplayer2.drm.DefaultDrmSessionEventListener
    public final void onDrmKeysRestored() {
        AnalyticsListener.EventTime eventTimeGenerateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onDrmKeysRestored(eventTimeGenerateReadingMediaPeriodEventTime);
        }
    }

    @Override // com.google.android.exoplayer2.drm.DefaultDrmSessionEventListener
    public final void onDrmKeysRemoved() {
        AnalyticsListener.EventTime eventTimeGenerateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onDrmKeysRemoved(eventTimeGenerateReadingMediaPeriodEventTime);
        }
    }

    @Override // com.google.android.exoplayer2.drm.DefaultDrmSessionEventListener
    public final void onDrmSessionReleased() {
        AnalyticsListener.EventTime eventTimeGenerateLastReportedPlayingMediaPeriodEventTime = generateLastReportedPlayingMediaPeriodEventTime();
        Iterator<AnalyticsListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onDrmSessionReleased(eventTimeGenerateLastReportedPlayingMediaPeriodEventTime);
        }
    }

    protected Set<AnalyticsListener> getListeners() {
        return Collections.unmodifiableSet(this.listeners);
    }

    @RequiresNonNull({"player"})
    protected AnalyticsListener.EventTime generateEventTime(Timeline timeline, int i, MediaSource.MediaPeriodId mediaPeriodId) {
        if (timeline.isEmpty()) {
            mediaPeriodId = null;
        }
        MediaSource.MediaPeriodId mediaPeriodId2 = mediaPeriodId;
        long jElapsedRealtime = this.clock.elapsedRealtime();
        boolean z = timeline == this.player.getCurrentTimeline() && i == this.player.getCurrentWindowIndex();
        long defaultPositionMs = 0;
        if (mediaPeriodId2 != null && mediaPeriodId2.isAd()) {
            if (z && this.player.getCurrentAdGroupIndex() == mediaPeriodId2.adGroupIndex && this.player.getCurrentAdIndexInAdGroup() == mediaPeriodId2.adIndexInAdGroup) {
                defaultPositionMs = this.player.getCurrentPosition();
            }
        } else if (z) {
            defaultPositionMs = this.player.getContentPosition();
        } else if (!timeline.isEmpty()) {
            defaultPositionMs = timeline.getWindow(i, this.window).getDefaultPositionMs();
        }
        return new AnalyticsListener.EventTime(jElapsedRealtime, timeline, i, mediaPeriodId2, defaultPositionMs, this.player.getCurrentPosition(), this.player.getTotalBufferedDuration());
    }

    private AnalyticsListener.EventTime generateEventTime(MediaPeriodInfo mediaPeriodInfo) {
        Assertions.checkNotNull(this.player);
        if (mediaPeriodInfo == null) {
            int currentWindowIndex = this.player.getCurrentWindowIndex();
            MediaPeriodInfo mediaPeriodInfoTryResolveWindowIndex = this.mediaPeriodQueueTracker.tryResolveWindowIndex(currentWindowIndex);
            if (mediaPeriodInfoTryResolveWindowIndex == null) {
                Timeline currentTimeline = this.player.getCurrentTimeline();
                if (!(currentWindowIndex < currentTimeline.getWindowCount())) {
                    currentTimeline = Timeline.EMPTY;
                }
                return generateEventTime(currentTimeline, currentWindowIndex, null);
            }
            mediaPeriodInfo = mediaPeriodInfoTryResolveWindowIndex;
        }
        return generateEventTime(mediaPeriodInfo.timeline, mediaPeriodInfo.windowIndex, mediaPeriodInfo.mediaPeriodId);
    }

    private AnalyticsListener.EventTime generateLastReportedPlayingMediaPeriodEventTime() {
        return generateEventTime(this.mediaPeriodQueueTracker.getLastReportedPlayingMediaPeriod());
    }

    private AnalyticsListener.EventTime generatePlayingMediaPeriodEventTime() {
        return generateEventTime(this.mediaPeriodQueueTracker.getPlayingMediaPeriod());
    }

    private AnalyticsListener.EventTime generateReadingMediaPeriodEventTime() {
        return generateEventTime(this.mediaPeriodQueueTracker.getReadingMediaPeriod());
    }

    private AnalyticsListener.EventTime generateLoadingMediaPeriodEventTime() {
        return generateEventTime(this.mediaPeriodQueueTracker.getLoadingMediaPeriod());
    }

    private AnalyticsListener.EventTime generateMediaPeriodEventTime(int i, MediaSource.MediaPeriodId mediaPeriodId) {
        Assertions.checkNotNull(this.player);
        if (mediaPeriodId != null) {
            MediaPeriodInfo mediaPeriodInfo = this.mediaPeriodQueueTracker.getMediaPeriodInfo(mediaPeriodId);
            if (mediaPeriodInfo != null) {
                return generateEventTime(mediaPeriodInfo);
            }
            return generateEventTime(Timeline.EMPTY, i, mediaPeriodId);
        }
        Timeline currentTimeline = this.player.getCurrentTimeline();
        if (!(i < currentTimeline.getWindowCount())) {
            currentTimeline = Timeline.EMPTY;
        }
        return generateEventTime(currentTimeline, i, null);
    }

    private static final class MediaPeriodQueueTracker {
        private boolean isSeeking;
        private MediaPeriodInfo lastReportedPlayingMediaPeriod;
        private MediaPeriodInfo readingMediaPeriod;
        private final ArrayList<MediaPeriodInfo> mediaPeriodInfoQueue = new ArrayList<>();
        private final HashMap<MediaSource.MediaPeriodId, MediaPeriodInfo> mediaPeriodIdToInfo = new HashMap<>();
        private final Timeline.Period period = new Timeline.Period();
        private Timeline timeline = Timeline.EMPTY;

        public MediaPeriodInfo getPlayingMediaPeriod() {
            if (this.mediaPeriodInfoQueue.isEmpty() || this.timeline.isEmpty() || this.isSeeking) {
                return null;
            }
            return this.mediaPeriodInfoQueue.get(0);
        }

        public MediaPeriodInfo getLastReportedPlayingMediaPeriod() {
            return this.lastReportedPlayingMediaPeriod;
        }

        public MediaPeriodInfo getReadingMediaPeriod() {
            return this.readingMediaPeriod;
        }

        public MediaPeriodInfo getLoadingMediaPeriod() {
            if (this.mediaPeriodInfoQueue.isEmpty()) {
                return null;
            }
            ArrayList<MediaPeriodInfo> arrayList = this.mediaPeriodInfoQueue;
            return arrayList.get(arrayList.size() - 1);
        }

        public MediaPeriodInfo getMediaPeriodInfo(MediaSource.MediaPeriodId mediaPeriodId) {
            return this.mediaPeriodIdToInfo.get(mediaPeriodId);
        }

        public boolean isSeeking() {
            return this.isSeeking;
        }

        public MediaPeriodInfo tryResolveWindowIndex(int i) {
            MediaPeriodInfo mediaPeriodInfo = null;
            for (int i2 = 0; i2 < this.mediaPeriodInfoQueue.size(); i2++) {
                MediaPeriodInfo mediaPeriodInfo2 = this.mediaPeriodInfoQueue.get(i2);
                int indexOfPeriod = this.timeline.getIndexOfPeriod(mediaPeriodInfo2.mediaPeriodId.periodUid);
                if (indexOfPeriod != -1 && this.timeline.getPeriod(indexOfPeriod, this.period).windowIndex == i) {
                    if (mediaPeriodInfo != null) {
                        return null;
                    }
                    mediaPeriodInfo = mediaPeriodInfo2;
                }
            }
            return mediaPeriodInfo;
        }

        public void onPositionDiscontinuity(int i) {
            updateLastReportedPlayingMediaPeriod();
        }

        public void onTimelineChanged(Timeline timeline) {
            for (int i = 0; i < this.mediaPeriodInfoQueue.size(); i++) {
                MediaPeriodInfo mediaPeriodInfoUpdateMediaPeriodInfoToNewTimeline = updateMediaPeriodInfoToNewTimeline(this.mediaPeriodInfoQueue.get(i), timeline);
                this.mediaPeriodInfoQueue.set(i, mediaPeriodInfoUpdateMediaPeriodInfoToNewTimeline);
                this.mediaPeriodIdToInfo.put(mediaPeriodInfoUpdateMediaPeriodInfoToNewTimeline.mediaPeriodId, mediaPeriodInfoUpdateMediaPeriodInfoToNewTimeline);
            }
            MediaPeriodInfo mediaPeriodInfo = this.readingMediaPeriod;
            if (mediaPeriodInfo != null) {
                this.readingMediaPeriod = updateMediaPeriodInfoToNewTimeline(mediaPeriodInfo, timeline);
            }
            this.timeline = timeline;
            updateLastReportedPlayingMediaPeriod();
        }

        public void onSeekStarted() {
            this.isSeeking = true;
        }

        public void onSeekProcessed() {
            this.isSeeking = false;
            updateLastReportedPlayingMediaPeriod();
        }

        public void onMediaPeriodCreated(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            MediaPeriodInfo mediaPeriodInfo = new MediaPeriodInfo(mediaPeriodId, this.timeline.getIndexOfPeriod(mediaPeriodId.periodUid) != -1 ? this.timeline : Timeline.EMPTY, i);
            this.mediaPeriodInfoQueue.add(mediaPeriodInfo);
            this.mediaPeriodIdToInfo.put(mediaPeriodId, mediaPeriodInfo);
            if (this.mediaPeriodInfoQueue.size() != 1 || this.timeline.isEmpty()) {
                return;
            }
            updateLastReportedPlayingMediaPeriod();
        }

        public boolean onMediaPeriodReleased(MediaSource.MediaPeriodId mediaPeriodId) {
            MediaPeriodInfo mediaPeriodInfoRemove = this.mediaPeriodIdToInfo.remove(mediaPeriodId);
            if (mediaPeriodInfoRemove == null) {
                return false;
            }
            this.mediaPeriodInfoQueue.remove(mediaPeriodInfoRemove);
            MediaPeriodInfo mediaPeriodInfo = this.readingMediaPeriod;
            if (mediaPeriodInfo == null || !mediaPeriodId.equals(mediaPeriodInfo.mediaPeriodId)) {
                return true;
            }
            this.readingMediaPeriod = this.mediaPeriodInfoQueue.isEmpty() ? null : this.mediaPeriodInfoQueue.get(0);
            return true;
        }

        public void onReadingStarted(MediaSource.MediaPeriodId mediaPeriodId) {
            this.readingMediaPeriod = this.mediaPeriodIdToInfo.get(mediaPeriodId);
        }

        private void updateLastReportedPlayingMediaPeriod() {
            if (this.mediaPeriodInfoQueue.isEmpty()) {
                return;
            }
            this.lastReportedPlayingMediaPeriod = this.mediaPeriodInfoQueue.get(0);
        }

        private MediaPeriodInfo updateMediaPeriodInfoToNewTimeline(MediaPeriodInfo mediaPeriodInfo, Timeline timeline) {
            int indexOfPeriod = timeline.getIndexOfPeriod(mediaPeriodInfo.mediaPeriodId.periodUid);
            if (indexOfPeriod == -1) {
                return mediaPeriodInfo;
            }
            return new MediaPeriodInfo(mediaPeriodInfo.mediaPeriodId, timeline, timeline.getPeriod(indexOfPeriod, this.period).windowIndex);
        }
    }

    private static final class MediaPeriodInfo {
        public final MediaSource.MediaPeriodId mediaPeriodId;
        public final Timeline timeline;
        public final int windowIndex;

        public MediaPeriodInfo(MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline, int i) {
            this.mediaPeriodId = mediaPeriodId;
            this.timeline = timeline;
            this.windowIndex = i;
        }
    }
}
