package com.google.android.exoplayer2.source;

import androidx.core.os.EnvironmentCompat;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class ClippingMediaSource extends CompositeMediaSource<Void> {
    private final boolean allowDynamicClippingUpdates;
    private IllegalClippingException clippingError;
    private ClippingTimeline clippingTimeline;
    private final boolean enableInitialDiscontinuity;
    private final long endUs;
    private Object manifest;
    private final ArrayList<ClippingMediaPeriod> mediaPeriods;
    private final MediaSource mediaSource;
    private long periodEndUs;
    private long periodStartUs;
    private final boolean relativeToDefaultPosition;
    private final long startUs;
    private final Timeline.Window window;

    public static final class IllegalClippingException extends IOException {
        public static final int REASON_INVALID_PERIOD_COUNT = 0;
        public static final int REASON_NOT_SEEKABLE_TO_START = 1;
        public static final int REASON_START_EXCEEDS_END = 2;
        public final int reason;

        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface Reason {
        }

        private static String getReasonDescription(int i) {
            if (i == 0) {
                return "invalid period count";
            }
            if (i != 1) {
                return i != 2 ? EnvironmentCompat.MEDIA_UNKNOWN : "start exceeds end";
            }
            return "not seekable to start";
        }

        public IllegalClippingException(int i) {
            super("Illegal clipping: " + getReasonDescription(i));
            this.reason = i;
        }
    }

    public ClippingMediaSource(MediaSource mediaSource, long j, long j2) {
        this(mediaSource, j, j2, true, false, false);
    }

    @Deprecated
    public ClippingMediaSource(MediaSource mediaSource, long j, long j2, boolean z) {
        this(mediaSource, j, j2, z, false, false);
    }

    public ClippingMediaSource(MediaSource mediaSource, long j) {
        this(mediaSource, 0L, j, true, false, true);
    }

    public ClippingMediaSource(MediaSource mediaSource, long j, long j2, boolean z, boolean z2, boolean z3) {
        Assertions.checkArgument(j >= 0);
        this.mediaSource = (MediaSource) Assertions.checkNotNull(mediaSource);
        this.startUs = j;
        this.endUs = j2;
        this.enableInitialDiscontinuity = z;
        this.allowDynamicClippingUpdates = z2;
        this.relativeToDefaultPosition = z3;
        this.mediaPeriods = new ArrayList<>();
        this.window = new Timeline.Window();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public Object getTag() {
        return this.mediaSource.getTag();
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.BaseMediaSource
    public void prepareSourceInternal(TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        prepareChildSource(null, this.mediaSource);
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() throws IOException {
        IllegalClippingException illegalClippingException = this.clippingError;
        if (illegalClippingException != null) {
            throw illegalClippingException;
        }
        super.maybeThrowSourceInfoRefreshError();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        ClippingMediaPeriod clippingMediaPeriod = new ClippingMediaPeriod(this.mediaSource.createPeriod(mediaPeriodId, allocator, j), this.enableInitialDiscontinuity, this.periodStartUs, this.periodEndUs);
        this.mediaPeriods.add(clippingMediaPeriod);
        return clippingMediaPeriod;
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        Assertions.checkState(this.mediaPeriods.remove(mediaPeriod));
        this.mediaSource.releasePeriod(((ClippingMediaPeriod) mediaPeriod).mediaPeriod);
        if (!this.mediaPeriods.isEmpty() || this.allowDynamicClippingUpdates) {
            return;
        }
        refreshClippedTimeline(this.clippingTimeline.timeline);
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.BaseMediaSource
    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        this.clippingError = null;
        this.clippingTimeline = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.source.CompositeMediaSource
    /* JADX INFO: renamed from: onChildSourceInfoRefreshed, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void lambda$prepareChildSource$0$CompositeMediaSource(Void r1, MediaSource mediaSource, Timeline timeline, Object obj) {
        if (this.clippingError != null) {
            return;
        }
        this.manifest = obj;
        refreshClippedTimeline(timeline);
    }

    private void refreshClippedTimeline(Timeline timeline) {
        long j;
        long j2;
        timeline.getWindow(0, this.window);
        long positionInFirstPeriodUs = this.window.getPositionInFirstPeriodUs();
        if (this.clippingTimeline == null || this.mediaPeriods.isEmpty() || this.allowDynamicClippingUpdates) {
            long j3 = this.startUs;
            long j4 = this.endUs;
            if (this.relativeToDefaultPosition) {
                long defaultPositionUs = this.window.getDefaultPositionUs();
                j3 += defaultPositionUs;
                j4 += defaultPositionUs;
            }
            this.periodStartUs = positionInFirstPeriodUs + j3;
            this.periodEndUs = this.endUs != Long.MIN_VALUE ? positionInFirstPeriodUs + j4 : Long.MIN_VALUE;
            int size = this.mediaPeriods.size();
            for (int i = 0; i < size; i++) {
                this.mediaPeriods.get(i).updateClipping(this.periodStartUs, this.periodEndUs);
            }
            j = j3;
            j2 = j4;
        } else {
            long j5 = this.periodStartUs - positionInFirstPeriodUs;
            j2 = this.endUs != Long.MIN_VALUE ? this.periodEndUs - positionInFirstPeriodUs : Long.MIN_VALUE;
            j = j5;
        }
        try {
            ClippingTimeline clippingTimeline = new ClippingTimeline(timeline, j, j2);
            this.clippingTimeline = clippingTimeline;
            refreshSourceInfo(clippingTimeline, this.manifest);
        } catch (IllegalClippingException e) {
            this.clippingError = e;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.source.CompositeMediaSource
    public long getMediaTimeForChildMediaTime(Void r7, long j) {
        if (j == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        long jUsToMs = C.usToMs(this.startUs);
        long jMax = Math.max(0L, j - jUsToMs);
        long j2 = this.endUs;
        return j2 != Long.MIN_VALUE ? Math.min(C.usToMs(j2) - jUsToMs, jMax) : jMax;
    }

    private static final class ClippingTimeline extends ForwardingTimeline {
        private final long durationUs;
        private final long endUs;
        private final boolean isDynamic;
        private final long startUs;

        public ClippingTimeline(Timeline timeline, long j, long j2) throws IllegalClippingException {
            super(timeline);
            boolean z = false;
            if (timeline.getPeriodCount() != 1) {
                throw new IllegalClippingException(0);
            }
            Timeline.Window window = timeline.getWindow(0, new Timeline.Window());
            long jMax = Math.max(0L, j);
            long jMax2 = j2 == Long.MIN_VALUE ? window.durationUs : Math.max(0L, j2);
            if (window.durationUs != C.TIME_UNSET) {
                jMax2 = jMax2 > window.durationUs ? window.durationUs : jMax2;
                if (jMax != 0 && !window.isSeekable) {
                    throw new IllegalClippingException(1);
                }
                if (jMax > jMax2) {
                    throw new IllegalClippingException(2);
                }
            }
            this.startUs = jMax;
            this.endUs = jMax2;
            this.durationUs = jMax2 == C.TIME_UNSET ? -9223372036854775807L : jMax2 - jMax;
            if (window.isDynamic && (jMax2 == C.TIME_UNSET || (window.durationUs != C.TIME_UNSET && jMax2 == window.durationUs))) {
                z = true;
            }
            this.isDynamic = z;
        }

        @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
        public Timeline.Window getWindow(int i, Timeline.Window window, boolean z, long j) {
            this.timeline.getWindow(0, window, z, 0L);
            window.positionInFirstPeriodUs += this.startUs;
            window.durationUs = this.durationUs;
            window.isDynamic = this.isDynamic;
            if (window.defaultPositionUs != C.TIME_UNSET) {
                window.defaultPositionUs = Math.max(window.defaultPositionUs, this.startUs);
                long j2 = this.endUs;
                long jMin = window.defaultPositionUs;
                if (j2 != C.TIME_UNSET) {
                    jMin = Math.min(jMin, this.endUs);
                }
                window.defaultPositionUs = jMin;
                window.defaultPositionUs -= this.startUs;
            }
            long jUsToMs = C.usToMs(this.startUs);
            if (window.presentationStartTimeMs != C.TIME_UNSET) {
                window.presentationStartTimeMs += jUsToMs;
            }
            if (window.windowStartTimeMs != C.TIME_UNSET) {
                window.windowStartTimeMs += jUsToMs;
            }
            return window;
        }

        @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            this.timeline.getPeriod(0, period, z);
            long positionInWindowUs = period.getPositionInWindowUs() - this.startUs;
            long j = this.durationUs;
            return period.set(period.id, period.uid, 0, j == C.TIME_UNSET ? -9223372036854775807L : j - positionInWindowUs, positionInWindowUs);
        }
    }
}
