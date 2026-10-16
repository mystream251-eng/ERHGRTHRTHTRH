package com.google.android.exoplayer2;

import android.util.Pair;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;

/* JADX INFO: loaded from: classes.dex */
final class MediaPeriodQueue {
    private static final int MAXIMUM_BUFFER_AHEAD_PERIODS = 100;
    private int length;
    private MediaPeriodHolder loading;
    private long nextWindowSequenceNumber;
    private Object oldFrontPeriodUid;
    private long oldFrontPeriodWindowSequenceNumber;
    private MediaPeriodHolder playing;
    private MediaPeriodHolder reading;
    private int repeatMode;
    private boolean shuffleModeEnabled;
    private final Timeline.Period period = new Timeline.Period();
    private final Timeline.Window window = new Timeline.Window();
    private Timeline timeline = Timeline.EMPTY;

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public boolean updateRepeatMode(int i) {
        this.repeatMode = i;
        return updateForPlaybackModeChange();
    }

    public boolean updateShuffleModeEnabled(boolean z) {
        this.shuffleModeEnabled = z;
        return updateForPlaybackModeChange();
    }

    public boolean isLoading(MediaPeriod mediaPeriod) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        return mediaPeriodHolder != null && mediaPeriodHolder.mediaPeriod == mediaPeriod;
    }

    public void reevaluateBuffer(long j) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        if (mediaPeriodHolder != null) {
            mediaPeriodHolder.reevaluateBuffer(j);
        }
    }

    public boolean shouldLoadNextMediaPeriod() {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        return mediaPeriodHolder == null || (!mediaPeriodHolder.info.isFinal && this.loading.isFullyBuffered() && this.loading.info.durationUs != C.TIME_UNSET && this.length < 100);
    }

    public MediaPeriodInfo getNextMediaPeriodInfo(long j, PlaybackInfo playbackInfo) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        if (mediaPeriodHolder == null) {
            return getFirstMediaPeriodInfo(playbackInfo);
        }
        return getFollowingMediaPeriodInfo(mediaPeriodHolder, j);
    }

    public MediaPeriod enqueueNextMediaPeriod(RendererCapabilities[] rendererCapabilitiesArr, TrackSelector trackSelector, Allocator allocator, MediaSource mediaSource, MediaPeriodInfo mediaPeriodInfo) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        MediaPeriodHolder mediaPeriodHolder2 = new MediaPeriodHolder(rendererCapabilitiesArr, mediaPeriodHolder == null ? mediaPeriodInfo.startPositionUs : mediaPeriodHolder.getRendererOffset() + this.loading.info.durationUs, trackSelector, allocator, mediaSource, mediaPeriodInfo);
        if (this.loading != null) {
            Assertions.checkState(hasPlayingPeriod());
            this.loading.next = mediaPeriodHolder2;
        }
        this.oldFrontPeriodUid = null;
        this.loading = mediaPeriodHolder2;
        this.length++;
        return mediaPeriodHolder2.mediaPeriod;
    }

    public MediaPeriodHolder getLoadingPeriod() {
        return this.loading;
    }

    public MediaPeriodHolder getPlayingPeriod() {
        return this.playing;
    }

    public MediaPeriodHolder getReadingPeriod() {
        return this.reading;
    }

    public MediaPeriodHolder getFrontPeriod() {
        return hasPlayingPeriod() ? this.playing : this.loading;
    }

    public boolean hasPlayingPeriod() {
        return this.playing != null;
    }

    public MediaPeriodHolder advanceReadingPeriod() {
        MediaPeriodHolder mediaPeriodHolder = this.reading;
        Assertions.checkState((mediaPeriodHolder == null || mediaPeriodHolder.next == null) ? false : true);
        MediaPeriodHolder mediaPeriodHolder2 = this.reading.next;
        this.reading = mediaPeriodHolder2;
        return mediaPeriodHolder2;
    }

    public MediaPeriodHolder advancePlayingPeriod() {
        MediaPeriodHolder mediaPeriodHolder = this.playing;
        if (mediaPeriodHolder != null) {
            if (mediaPeriodHolder == this.reading) {
                this.reading = mediaPeriodHolder.next;
            }
            this.playing.release();
            int i = this.length - 1;
            this.length = i;
            if (i == 0) {
                this.loading = null;
                this.oldFrontPeriodUid = this.playing.uid;
                this.oldFrontPeriodWindowSequenceNumber = this.playing.info.id.windowSequenceNumber;
            }
            this.playing = this.playing.next;
        } else {
            MediaPeriodHolder mediaPeriodHolder2 = this.loading;
            this.playing = mediaPeriodHolder2;
            this.reading = mediaPeriodHolder2;
        }
        return this.playing;
    }

    public boolean removeAfter(MediaPeriodHolder mediaPeriodHolder) {
        boolean z = false;
        Assertions.checkState(mediaPeriodHolder != null);
        this.loading = mediaPeriodHolder;
        while (mediaPeriodHolder.next != null) {
            mediaPeriodHolder = mediaPeriodHolder.next;
            if (mediaPeriodHolder == this.reading) {
                this.reading = this.playing;
                z = true;
            }
            mediaPeriodHolder.release();
            this.length--;
        }
        this.loading.next = null;
        return z;
    }

    public void clear(boolean z) {
        MediaPeriodHolder frontPeriod = getFrontPeriod();
        if (frontPeriod != null) {
            this.oldFrontPeriodUid = z ? frontPeriod.uid : null;
            this.oldFrontPeriodWindowSequenceNumber = frontPeriod.info.id.windowSequenceNumber;
            frontPeriod.release();
            removeAfter(frontPeriod);
        } else if (!z) {
            this.oldFrontPeriodUid = null;
        }
        this.playing = null;
        this.loading = null;
        this.reading = null;
        this.length = 0;
    }

    public boolean updateQueuedPeriods(MediaSource.MediaPeriodId mediaPeriodId, long j) {
        int indexOfPeriod = this.timeline.getIndexOfPeriod(mediaPeriodId.periodUid);
        MediaPeriodHolder mediaPeriodHolder = null;
        int nextPeriodIndex = indexOfPeriod;
        for (MediaPeriodHolder frontPeriod = getFrontPeriod(); frontPeriod != null; frontPeriod = frontPeriod.next) {
            if (mediaPeriodHolder == null) {
                frontPeriod.info = getUpdatedMediaPeriodInfo(frontPeriod.info);
            } else {
                if (nextPeriodIndex == -1 || !frontPeriod.uid.equals(this.timeline.getUidOfPeriod(nextPeriodIndex))) {
                    return true ^ removeAfter(mediaPeriodHolder);
                }
                MediaPeriodInfo followingMediaPeriodInfo = getFollowingMediaPeriodInfo(mediaPeriodHolder, j);
                if (followingMediaPeriodInfo == null) {
                    return true ^ removeAfter(mediaPeriodHolder);
                }
                frontPeriod.info = getUpdatedMediaPeriodInfo(frontPeriod.info);
                if (!canKeepMediaPeriodHolder(frontPeriod, followingMediaPeriodInfo)) {
                    return true ^ removeAfter(mediaPeriodHolder);
                }
            }
            if (frontPeriod.info.isLastInTimelinePeriod) {
                nextPeriodIndex = this.timeline.getNextPeriodIndex(nextPeriodIndex, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            }
            mediaPeriodHolder = frontPeriod;
        }
        return true;
    }

    public MediaPeriodInfo getUpdatedMediaPeriodInfo(MediaPeriodInfo mediaPeriodInfo) {
        long durationUs;
        boolean zIsLastInPeriod = isLastInPeriod(mediaPeriodInfo.id);
        boolean zIsLastInTimeline = isLastInTimeline(mediaPeriodInfo.id, zIsLastInPeriod);
        this.timeline.getPeriodByUid(mediaPeriodInfo.id.periodUid, this.period);
        if (mediaPeriodInfo.id.isAd()) {
            durationUs = this.period.getAdDurationUs(mediaPeriodInfo.id.adGroupIndex, mediaPeriodInfo.id.adIndexInAdGroup);
        } else {
            durationUs = mediaPeriodInfo.id.endPositionUs == Long.MIN_VALUE ? this.period.getDurationUs() : mediaPeriodInfo.id.endPositionUs;
        }
        return new MediaPeriodInfo(mediaPeriodInfo.id, mediaPeriodInfo.startPositionUs, mediaPeriodInfo.contentPositionUs, durationUs, zIsLastInPeriod, zIsLastInTimeline);
    }

    public MediaSource.MediaPeriodId resolveMediaPeriodIdForAds(Object obj, long j) {
        return resolveMediaPeriodIdForAds(obj, j, resolvePeriodIndexToWindowSequenceNumber(obj));
    }

    private MediaSource.MediaPeriodId resolveMediaPeriodIdForAds(Object obj, long j, long j2) {
        this.timeline.getPeriodByUid(obj, this.period);
        int adGroupIndexForPositionUs = this.period.getAdGroupIndexForPositionUs(j);
        if (adGroupIndexForPositionUs == -1) {
            int adGroupIndexAfterPositionUs = this.period.getAdGroupIndexAfterPositionUs(j);
            return new MediaSource.MediaPeriodId(obj, j2, adGroupIndexAfterPositionUs == -1 ? Long.MIN_VALUE : this.period.getAdGroupTimeUs(adGroupIndexAfterPositionUs));
        }
        return new MediaSource.MediaPeriodId(obj, adGroupIndexForPositionUs, this.period.getFirstAdIndexToPlay(adGroupIndexForPositionUs), j2);
    }

    private long resolvePeriodIndexToWindowSequenceNumber(Object obj) {
        int indexOfPeriod;
        int i = this.timeline.getPeriodByUid(obj, this.period).windowIndex;
        Object obj2 = this.oldFrontPeriodUid;
        if (obj2 != null && (indexOfPeriod = this.timeline.getIndexOfPeriod(obj2)) != -1 && this.timeline.getPeriod(indexOfPeriod, this.period).windowIndex == i) {
            return this.oldFrontPeriodWindowSequenceNumber;
        }
        for (MediaPeriodHolder frontPeriod = getFrontPeriod(); frontPeriod != null; frontPeriod = frontPeriod.next) {
            if (frontPeriod.uid.equals(obj)) {
                return frontPeriod.info.id.windowSequenceNumber;
            }
        }
        for (MediaPeriodHolder frontPeriod2 = getFrontPeriod(); frontPeriod2 != null; frontPeriod2 = frontPeriod2.next) {
            int indexOfPeriod2 = this.timeline.getIndexOfPeriod(frontPeriod2.uid);
            if (indexOfPeriod2 != -1 && this.timeline.getPeriod(indexOfPeriod2, this.period).windowIndex == i) {
                return frontPeriod2.info.id.windowSequenceNumber;
            }
        }
        long j = this.nextWindowSequenceNumber;
        this.nextWindowSequenceNumber = 1 + j;
        return j;
    }

    private boolean canKeepMediaPeriodHolder(MediaPeriodHolder mediaPeriodHolder, MediaPeriodInfo mediaPeriodInfo) {
        MediaPeriodInfo mediaPeriodInfo2 = mediaPeriodHolder.info;
        return mediaPeriodInfo2.startPositionUs == mediaPeriodInfo.startPositionUs && mediaPeriodInfo2.id.equals(mediaPeriodInfo.id);
    }

    private boolean updateForPlaybackModeChange() {
        MediaPeriodHolder frontPeriod = getFrontPeriod();
        if (frontPeriod == null) {
            return true;
        }
        int indexOfPeriod = this.timeline.getIndexOfPeriod(frontPeriod.uid);
        while (true) {
            indexOfPeriod = this.timeline.getNextPeriodIndex(indexOfPeriod, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            while (frontPeriod.next != null && !frontPeriod.info.isLastInTimelinePeriod) {
                frontPeriod = frontPeriod.next;
            }
            if (indexOfPeriod == -1 || frontPeriod.next == null || this.timeline.getIndexOfPeriod(frontPeriod.next.uid) != indexOfPeriod) {
                break;
            }
            frontPeriod = frontPeriod.next;
        }
        boolean zRemoveAfter = removeAfter(frontPeriod);
        frontPeriod.info = getUpdatedMediaPeriodInfo(frontPeriod.info);
        return (zRemoveAfter && hasPlayingPeriod()) ? false : true;
    }

    private MediaPeriodInfo getFirstMediaPeriodInfo(PlaybackInfo playbackInfo) {
        return getMediaPeriodInfo(playbackInfo.periodId, playbackInfo.contentPositionUs, playbackInfo.startPositionUs);
    }

    private MediaPeriodInfo getFollowingMediaPeriodInfo(MediaPeriodHolder mediaPeriodHolder, long j) {
        long jLongValue;
        Object obj;
        long j2;
        long j3;
        MediaPeriodInfo mediaPeriodInfo = mediaPeriodHolder.info;
        long rendererOffset = (mediaPeriodHolder.getRendererOffset() + mediaPeriodInfo.durationUs) - j;
        long j4 = 0;
        if (mediaPeriodInfo.isLastInTimelinePeriod) {
            int nextPeriodIndex = this.timeline.getNextPeriodIndex(this.timeline.getIndexOfPeriod(mediaPeriodInfo.id.periodUid), this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            if (nextPeriodIndex == -1) {
                return null;
            }
            int i = this.timeline.getPeriod(nextPeriodIndex, this.period, true).windowIndex;
            Object obj2 = this.period.uid;
            long j5 = mediaPeriodInfo.id.windowSequenceNumber;
            if (this.timeline.getWindow(i, this.window).firstPeriodIndex == nextPeriodIndex) {
                Pair<Object, Long> periodPosition = this.timeline.getPeriodPosition(this.window, this.period, i, C.TIME_UNSET, Math.max(0L, rendererOffset));
                if (periodPosition == null) {
                    return null;
                }
                Object obj3 = periodPosition.first;
                long jLongValue2 = ((Long) periodPosition.second).longValue();
                if (mediaPeriodHolder.next != null && mediaPeriodHolder.next.uid.equals(obj3)) {
                    j3 = mediaPeriodHolder.next.info.id.windowSequenceNumber;
                } else {
                    j3 = this.nextWindowSequenceNumber;
                    this.nextWindowSequenceNumber = 1 + j3;
                }
                j4 = jLongValue2;
                j2 = j3;
                obj = obj3;
            } else {
                obj = obj2;
                j2 = j5;
            }
            long j6 = j4;
            return getMediaPeriodInfo(resolveMediaPeriodIdForAds(obj, j6, j2), j6, j4);
        }
        MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodInfo.id;
        this.timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        if (!mediaPeriodId.isAd()) {
            if (mediaPeriodInfo.id.endPositionUs != Long.MIN_VALUE) {
                int adGroupIndexForPositionUs = this.period.getAdGroupIndexForPositionUs(mediaPeriodInfo.id.endPositionUs);
                if (adGroupIndexForPositionUs == -1) {
                    return getMediaPeriodInfoForContent(mediaPeriodId.periodUid, mediaPeriodInfo.id.endPositionUs, mediaPeriodId.windowSequenceNumber);
                }
                int firstAdIndexToPlay = this.period.getFirstAdIndexToPlay(adGroupIndexForPositionUs);
                if (this.period.isAdAvailable(adGroupIndexForPositionUs, firstAdIndexToPlay)) {
                    return getMediaPeriodInfoForAd(mediaPeriodId.periodUid, adGroupIndexForPositionUs, firstAdIndexToPlay, mediaPeriodInfo.id.endPositionUs, mediaPeriodId.windowSequenceNumber);
                }
                return null;
            }
            int adGroupCount = this.period.getAdGroupCount();
            if (adGroupCount == 0) {
                return null;
            }
            int i2 = adGroupCount - 1;
            if (this.period.getAdGroupTimeUs(i2) != Long.MIN_VALUE || this.period.hasPlayedAdGroup(i2)) {
                return null;
            }
            int firstAdIndexToPlay2 = this.period.getFirstAdIndexToPlay(i2);
            if (!this.period.isAdAvailable(i2, firstAdIndexToPlay2)) {
                return null;
            }
            return getMediaPeriodInfoForAd(mediaPeriodId.periodUid, i2, firstAdIndexToPlay2, this.period.getDurationUs(), mediaPeriodId.windowSequenceNumber);
        }
        int i3 = mediaPeriodId.adGroupIndex;
        int adCountInAdGroup = this.period.getAdCountInAdGroup(i3);
        if (adCountInAdGroup == -1) {
            return null;
        }
        int nextAdIndexToPlay = this.period.getNextAdIndexToPlay(i3, mediaPeriodId.adIndexInAdGroup);
        if (nextAdIndexToPlay < adCountInAdGroup) {
            if (this.period.isAdAvailable(i3, nextAdIndexToPlay)) {
                return getMediaPeriodInfoForAd(mediaPeriodId.periodUid, i3, nextAdIndexToPlay, mediaPeriodInfo.contentPositionUs, mediaPeriodId.windowSequenceNumber);
            }
            return null;
        }
        long j7 = mediaPeriodInfo.contentPositionUs;
        if (this.period.getAdGroupCount() == 1 && this.period.getAdGroupTimeUs(0) == 0) {
            Timeline timeline = this.timeline;
            Timeline.Window window = this.window;
            Timeline.Period period = this.period;
            Pair<Object, Long> periodPosition2 = timeline.getPeriodPosition(window, period, period.windowIndex, C.TIME_UNSET, Math.max(0L, rendererOffset));
            if (periodPosition2 == null) {
                return null;
            }
            jLongValue = ((Long) periodPosition2.second).longValue();
        } else {
            jLongValue = j7;
        }
        return getMediaPeriodInfoForContent(mediaPeriodId.periodUid, jLongValue, mediaPeriodId.windowSequenceNumber);
    }

    private MediaPeriodInfo getMediaPeriodInfo(MediaSource.MediaPeriodId mediaPeriodId, long j, long j2) {
        this.timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        if (mediaPeriodId.isAd()) {
            if (this.period.isAdAvailable(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup)) {
                return getMediaPeriodInfoForAd(mediaPeriodId.periodUid, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup, j, mediaPeriodId.windowSequenceNumber);
            }
            return null;
        }
        return getMediaPeriodInfoForContent(mediaPeriodId.periodUid, j2, mediaPeriodId.windowSequenceNumber);
    }

    private MediaPeriodInfo getMediaPeriodInfoForAd(Object obj, int i, int i2, long j, long j2) {
        MediaSource.MediaPeriodId mediaPeriodId = new MediaSource.MediaPeriodId(obj, i, i2, j2);
        boolean zIsLastInPeriod = isLastInPeriod(mediaPeriodId);
        boolean zIsLastInTimeline = isLastInTimeline(mediaPeriodId, zIsLastInPeriod);
        return new MediaPeriodInfo(mediaPeriodId, i2 == this.period.getFirstAdIndexToPlay(i) ? this.period.getAdResumePositionUs() : 0L, j, this.timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup), zIsLastInPeriod, zIsLastInTimeline);
    }

    private MediaPeriodInfo getMediaPeriodInfoForContent(Object obj, long j, long j2) {
        int adGroupIndexAfterPositionUs = this.period.getAdGroupIndexAfterPositionUs(j);
        long adGroupTimeUs = adGroupIndexAfterPositionUs == -1 ? Long.MIN_VALUE : this.period.getAdGroupTimeUs(adGroupIndexAfterPositionUs);
        MediaSource.MediaPeriodId mediaPeriodId = new MediaSource.MediaPeriodId(obj, j2, adGroupTimeUs);
        this.timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        boolean zIsLastInPeriod = isLastInPeriod(mediaPeriodId);
        return new MediaPeriodInfo(mediaPeriodId, j, C.TIME_UNSET, adGroupTimeUs == Long.MIN_VALUE ? this.period.getDurationUs() : adGroupTimeUs, zIsLastInPeriod, isLastInTimeline(mediaPeriodId, zIsLastInPeriod));
    }

    private boolean isLastInPeriod(MediaSource.MediaPeriodId mediaPeriodId) {
        int adGroupCount = this.timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).getAdGroupCount();
        if (adGroupCount == 0) {
            return true;
        }
        int i = adGroupCount - 1;
        boolean zIsAd = mediaPeriodId.isAd();
        if (this.period.getAdGroupTimeUs(i) != Long.MIN_VALUE) {
            return !zIsAd && mediaPeriodId.endPositionUs == Long.MIN_VALUE;
        }
        int adCountInAdGroup = this.period.getAdCountInAdGroup(i);
        if (adCountInAdGroup == -1) {
            return false;
        }
        if (zIsAd && mediaPeriodId.adGroupIndex == i && mediaPeriodId.adIndexInAdGroup == adCountInAdGroup + (-1)) {
            return true;
        }
        return !zIsAd && this.period.getFirstAdIndexToPlay(i) == adCountInAdGroup;
    }

    private boolean isLastInTimeline(MediaSource.MediaPeriodId mediaPeriodId, boolean z) {
        int indexOfPeriod = this.timeline.getIndexOfPeriod(mediaPeriodId.periodUid);
        return !this.timeline.getWindow(this.timeline.getPeriod(indexOfPeriod, this.period).windowIndex, this.window).isDynamic && this.timeline.isLastPeriod(indexOfPeriod, this.period, this.window, this.repeatMode, this.shuffleModeEnabled) && z;
    }
}
