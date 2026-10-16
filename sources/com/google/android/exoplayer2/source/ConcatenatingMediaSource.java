package com.google.android.exoplayer2.source;

import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class ConcatenatingMediaSource extends CompositeMediaSource<MediaSourceHolder> {
    private static final int MSG_ADD = 0;
    private static final int MSG_MOVE = 2;
    private static final int MSG_ON_COMPLETION = 5;
    private static final int MSG_REMOVE = 1;
    private static final int MSG_SET_SHUFFLE_ORDER = 3;
    private static final int MSG_UPDATE_TIMELINE = 4;
    private final boolean isAtomic;
    private final Map<MediaPeriod, MediaSourceHolder> mediaSourceByMediaPeriod;
    private final Map<Object, MediaSourceHolder> mediaSourceByUid;
    private final List<MediaSourceHolder> mediaSourceHolders;
    private final List<MediaSourceHolder> mediaSourcesPublic;
    private Set<HandlerAndRunnable> nextTimelineUpdateOnCompletionActions;
    private final Set<HandlerAndRunnable> pendingOnCompletionActions;
    private final Timeline.Period period;
    private int periodCount;
    private Handler playbackThreadHandler;
    private ShuffleOrder shuffleOrder;
    private boolean timelineUpdateScheduled;
    private final boolean useLazyPreparation;
    private final Timeline.Window window;
    private int windowCount;

    @Override // com.google.android.exoplayer2.source.MediaSource
    public Object getTag() {
        return null;
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() throws IOException {
    }

    public ConcatenatingMediaSource(MediaSource... mediaSourceArr) {
        this(false, mediaSourceArr);
    }

    public ConcatenatingMediaSource(boolean z, MediaSource... mediaSourceArr) {
        this(z, new ShuffleOrder.DefaultShuffleOrder(0), mediaSourceArr);
    }

    public ConcatenatingMediaSource(boolean z, ShuffleOrder shuffleOrder, MediaSource... mediaSourceArr) {
        this(z, false, shuffleOrder, mediaSourceArr);
    }

    public ConcatenatingMediaSource(boolean z, boolean z2, ShuffleOrder shuffleOrder, MediaSource... mediaSourceArr) {
        for (MediaSource mediaSource : mediaSourceArr) {
            Assertions.checkNotNull(mediaSource);
        }
        this.shuffleOrder = shuffleOrder.getLength() > 0 ? shuffleOrder.cloneAndClear() : shuffleOrder;
        this.mediaSourceByMediaPeriod = new IdentityHashMap();
        this.mediaSourceByUid = new HashMap();
        this.mediaSourcesPublic = new ArrayList();
        this.mediaSourceHolders = new ArrayList();
        this.nextTimelineUpdateOnCompletionActions = new HashSet();
        this.pendingOnCompletionActions = new HashSet();
        this.isAtomic = z;
        this.useLazyPreparation = z2;
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        addMediaSources(Arrays.asList(mediaSourceArr));
    }

    public final synchronized void addMediaSource(MediaSource mediaSource) {
        addMediaSource(this.mediaSourcesPublic.size(), mediaSource);
    }

    public final synchronized void addMediaSource(MediaSource mediaSource, Handler handler, Runnable runnable) {
        addMediaSource(this.mediaSourcesPublic.size(), mediaSource, handler, runnable);
    }

    public final synchronized void addMediaSource(int i, MediaSource mediaSource) {
        addPublicMediaSources(i, Collections.singletonList(mediaSource), null, null);
    }

    public final synchronized void addMediaSource(int i, MediaSource mediaSource, Handler handler, Runnable runnable) {
        addPublicMediaSources(i, Collections.singletonList(mediaSource), handler, runnable);
    }

    public final synchronized void addMediaSources(Collection<MediaSource> collection) {
        addPublicMediaSources(this.mediaSourcesPublic.size(), collection, null, null);
    }

    public final synchronized void addMediaSources(Collection<MediaSource> collection, Handler handler, Runnable runnable) {
        addPublicMediaSources(this.mediaSourcesPublic.size(), collection, handler, runnable);
    }

    public final synchronized void addMediaSources(int i, Collection<MediaSource> collection) {
        addPublicMediaSources(i, collection, null, null);
    }

    public final synchronized void addMediaSources(int i, Collection<MediaSource> collection, Handler handler, Runnable runnable) {
        addPublicMediaSources(i, collection, handler, runnable);
    }

    public final synchronized void removeMediaSource(int i) {
        removePublicMediaSources(i, i + 1, null, null);
    }

    public final synchronized void removeMediaSource(int i, Handler handler, Runnable runnable) {
        removePublicMediaSources(i, i + 1, handler, runnable);
    }

    public final synchronized void removeMediaSourceRange(int i, int i2) {
        removePublicMediaSources(i, i2, null, null);
    }

    public final synchronized void removeMediaSourceRange(int i, int i2, Handler handler, Runnable runnable) {
        removePublicMediaSources(i, i2, handler, runnable);
    }

    public final synchronized void moveMediaSource(int i, int i2) {
        movePublicMediaSource(i, i2, null, null);
    }

    public final synchronized void moveMediaSource(int i, int i2, Handler handler, Runnable runnable) {
        movePublicMediaSource(i, i2, handler, runnable);
    }

    public final synchronized void clear() {
        removeMediaSourceRange(0, getSize());
    }

    public final synchronized void clear(Handler handler, Runnable runnable) {
        removeMediaSourceRange(0, getSize(), handler, runnable);
    }

    public final synchronized int getSize() {
        return this.mediaSourcesPublic.size();
    }

    public final synchronized MediaSource getMediaSource(int i) {
        return this.mediaSourcesPublic.get(i).mediaSource;
    }

    public final synchronized void setShuffleOrder(ShuffleOrder shuffleOrder) {
        setPublicShuffleOrder(shuffleOrder, null, null);
    }

    public final synchronized void setShuffleOrder(ShuffleOrder shuffleOrder, Handler handler, Runnable runnable) {
        setPublicShuffleOrder(shuffleOrder, handler, runnable);
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.BaseMediaSource
    public final synchronized void prepareSourceInternal(TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        this.playbackThreadHandler = new Handler(new Handler.Callback() { // from class: com.google.android.exoplayer2.source.-$$Lambda$ConcatenatingMediaSource$fl0myfoK2raBckmHYwV9YTd0eeo
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.f$0.handleMessage(message);
            }
        });
        if (this.mediaSourcesPublic.isEmpty()) {
            updateTimelineAndScheduleOnCompletionActions();
        } else {
            this.shuffleOrder = this.shuffleOrder.cloneAndInsert(0, this.mediaSourcesPublic.size());
            addMediaSourcesInternal(0, this.mediaSourcesPublic);
            scheduleTimelineUpdate();
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public final MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        MediaSourceHolder mediaSourceHolder = this.mediaSourceByUid.get(getMediaSourceHolderUid(mediaPeriodId.periodUid));
        if (mediaSourceHolder == null) {
            mediaSourceHolder = new MediaSourceHolder(new DummyMediaSource());
            mediaSourceHolder.hasStartedPreparing = true;
        }
        DeferredMediaPeriod deferredMediaPeriod = new DeferredMediaPeriod(mediaSourceHolder.mediaSource, mediaPeriodId, allocator, j);
        this.mediaSourceByMediaPeriod.put(deferredMediaPeriod, mediaSourceHolder);
        mediaSourceHolder.activeMediaPeriods.add(deferredMediaPeriod);
        if (!mediaSourceHolder.hasStartedPreparing) {
            mediaSourceHolder.hasStartedPreparing = true;
            prepareChildSource(mediaSourceHolder, mediaSourceHolder.mediaSource);
        } else if (mediaSourceHolder.isPrepared) {
            deferredMediaPeriod.createPeriod(mediaPeriodId.copyWithPeriodUid(getChildPeriodUid(mediaSourceHolder, mediaPeriodId.periodUid)));
        }
        return deferredMediaPeriod;
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public final void releasePeriod(MediaPeriod mediaPeriod) {
        MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) Assertions.checkNotNull(this.mediaSourceByMediaPeriod.remove(mediaPeriod));
        ((DeferredMediaPeriod) mediaPeriod).releasePeriod();
        mediaSourceHolder.activeMediaPeriods.remove(mediaPeriod);
        maybeReleaseChildSource(mediaSourceHolder);
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.BaseMediaSource
    public final synchronized void releaseSourceInternal() {
        super.releaseSourceInternal();
        this.mediaSourceHolders.clear();
        this.mediaSourceByUid.clear();
        this.shuffleOrder = this.shuffleOrder.cloneAndClear();
        this.windowCount = 0;
        this.periodCount = 0;
        if (this.playbackThreadHandler != null) {
            this.playbackThreadHandler.removeCallbacksAndMessages(null);
            this.playbackThreadHandler = null;
        }
        this.timelineUpdateScheduled = false;
        this.nextTimelineUpdateOnCompletionActions.clear();
        dispatchOnCompletionActions(this.pendingOnCompletionActions);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.source.CompositeMediaSource
    /* JADX INFO: renamed from: onChildSourceInfoRefreshed, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final void lambda$prepareChildSource$0$CompositeMediaSource(MediaSourceHolder mediaSourceHolder, MediaSource mediaSource, Timeline timeline, Object obj) {
        updateMediaSourceInternal(mediaSourceHolder, timeline);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.source.CompositeMediaSource
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSourceHolder mediaSourceHolder, MediaSource.MediaPeriodId mediaPeriodId) {
        for (int i = 0; i < mediaSourceHolder.activeMediaPeriods.size(); i++) {
            if (mediaSourceHolder.activeMediaPeriods.get(i).id.windowSequenceNumber == mediaPeriodId.windowSequenceNumber) {
                return mediaPeriodId.copyWithPeriodUid(getPeriodUid(mediaSourceHolder, mediaPeriodId.periodUid));
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.source.CompositeMediaSource
    public int getWindowIndexForChildWindowIndex(MediaSourceHolder mediaSourceHolder, int i) {
        return i + mediaSourceHolder.firstWindowIndexInChild;
    }

    private void addPublicMediaSources(int i, Collection<MediaSource> collection, Handler handler, Runnable runnable) {
        Assertions.checkArgument((handler == null) == (runnable == null));
        Handler handler2 = this.playbackThreadHandler;
        Iterator<MediaSource> it = collection.iterator();
        while (it.hasNext()) {
            Assertions.checkNotNull(it.next());
        }
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator<MediaSource> it2 = collection.iterator();
        while (it2.hasNext()) {
            arrayList.add(new MediaSourceHolder(it2.next()));
        }
        this.mediaSourcesPublic.addAll(i, arrayList);
        if (handler2 != null && !collection.isEmpty()) {
            handler2.obtainMessage(0, new MessageData(i, arrayList, createOnCompletionAction(handler, runnable))).sendToTarget();
        } else {
            if (runnable == null || handler == null) {
                return;
            }
            handler.post(runnable);
        }
    }

    private void removePublicMediaSources(int i, int i2, Handler handler, Runnable runnable) {
        Assertions.checkArgument((handler == null) == (runnable == null));
        Handler handler2 = this.playbackThreadHandler;
        Util.removeRange(this.mediaSourcesPublic, i, i2);
        if (handler2 != null) {
            handler2.obtainMessage(1, new MessageData(i, Integer.valueOf(i2), createOnCompletionAction(handler, runnable))).sendToTarget();
        } else {
            if (runnable == null || handler == null) {
                return;
            }
            handler.post(runnable);
        }
    }

    private void movePublicMediaSource(int i, int i2, Handler handler, Runnable runnable) {
        Assertions.checkArgument((handler == null) == (runnable == null));
        Handler handler2 = this.playbackThreadHandler;
        List<MediaSourceHolder> list = this.mediaSourcesPublic;
        list.add(i2, list.remove(i));
        if (handler2 != null) {
            handler2.obtainMessage(2, new MessageData(i, Integer.valueOf(i2), createOnCompletionAction(handler, runnable))).sendToTarget();
        } else {
            if (runnable == null || handler == null) {
                return;
            }
            handler.post(runnable);
        }
    }

    private void setPublicShuffleOrder(ShuffleOrder shuffleOrder, Handler handler, Runnable runnable) {
        Assertions.checkArgument((handler == null) == (runnable == null));
        Handler handler2 = this.playbackThreadHandler;
        if (handler2 != null) {
            int size = getSize();
            if (shuffleOrder.getLength() != size) {
                shuffleOrder = shuffleOrder.cloneAndClear().cloneAndInsert(0, size);
            }
            handler2.obtainMessage(3, new MessageData(0, shuffleOrder, createOnCompletionAction(handler, runnable))).sendToTarget();
            return;
        }
        if (shuffleOrder.getLength() > 0) {
            shuffleOrder = shuffleOrder.cloneAndClear();
        }
        this.shuffleOrder = shuffleOrder;
        if (runnable == null || handler == null) {
            return;
        }
        handler.post(runnable);
    }

    private HandlerAndRunnable createOnCompletionAction(Handler handler, Runnable runnable) {
        if (handler == null || runnable == null) {
            return null;
        }
        HandlerAndRunnable handlerAndRunnable = new HandlerAndRunnable(handler, runnable);
        this.pendingOnCompletionActions.add(handlerAndRunnable);
        return handlerAndRunnable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            MessageData messageData = (MessageData) Util.castNonNull(message.obj);
            this.shuffleOrder = this.shuffleOrder.cloneAndInsert(messageData.index, ((Collection) messageData.customData).size());
            addMediaSourcesInternal(messageData.index, (Collection) messageData.customData);
            scheduleTimelineUpdate(messageData.onCompletionAction);
        } else if (i == 1) {
            MessageData messageData2 = (MessageData) Util.castNonNull(message.obj);
            int i2 = messageData2.index;
            int iIntValue = ((Integer) messageData2.customData).intValue();
            if (i2 == 0 && iIntValue == this.shuffleOrder.getLength()) {
                this.shuffleOrder = this.shuffleOrder.cloneAndClear();
            } else {
                this.shuffleOrder = this.shuffleOrder.cloneAndRemove(i2, iIntValue);
            }
            for (int i3 = iIntValue - 1; i3 >= i2; i3--) {
                removeMediaSourceInternal(i3);
            }
            scheduleTimelineUpdate(messageData2.onCompletionAction);
        } else if (i == 2) {
            MessageData messageData3 = (MessageData) Util.castNonNull(message.obj);
            ShuffleOrder shuffleOrderCloneAndRemove = this.shuffleOrder.cloneAndRemove(messageData3.index, messageData3.index + 1);
            this.shuffleOrder = shuffleOrderCloneAndRemove;
            this.shuffleOrder = shuffleOrderCloneAndRemove.cloneAndInsert(((Integer) messageData3.customData).intValue(), 1);
            moveMediaSourceInternal(messageData3.index, ((Integer) messageData3.customData).intValue());
            scheduleTimelineUpdate(messageData3.onCompletionAction);
        } else if (i == 3) {
            MessageData messageData4 = (MessageData) Util.castNonNull(message.obj);
            this.shuffleOrder = (ShuffleOrder) messageData4.customData;
            scheduleTimelineUpdate(messageData4.onCompletionAction);
        } else if (i == 4) {
            updateTimelineAndScheduleOnCompletionActions();
        } else if (i == 5) {
            dispatchOnCompletionActions((Set) Util.castNonNull(message.obj));
        } else {
            throw new IllegalStateException();
        }
        return true;
    }

    private void scheduleTimelineUpdate() {
        scheduleTimelineUpdate(null);
    }

    private void scheduleTimelineUpdate(HandlerAndRunnable handlerAndRunnable) {
        if (!this.timelineUpdateScheduled) {
            getPlaybackThreadHandlerOnPlaybackThread().obtainMessage(4).sendToTarget();
            this.timelineUpdateScheduled = true;
        }
        if (handlerAndRunnable != null) {
            this.nextTimelineUpdateOnCompletionActions.add(handlerAndRunnable);
        }
    }

    private void updateTimelineAndScheduleOnCompletionActions() {
        this.timelineUpdateScheduled = false;
        Set<HandlerAndRunnable> set = this.nextTimelineUpdateOnCompletionActions;
        this.nextTimelineUpdateOnCompletionActions = new HashSet();
        refreshSourceInfo(new ConcatenatedTimeline(this.mediaSourceHolders, this.windowCount, this.periodCount, this.shuffleOrder, this.isAtomic), null);
        getPlaybackThreadHandlerOnPlaybackThread().obtainMessage(5, set).sendToTarget();
    }

    private Handler getPlaybackThreadHandlerOnPlaybackThread() {
        return (Handler) Assertions.checkNotNull(this.playbackThreadHandler);
    }

    private synchronized void dispatchOnCompletionActions(Set<HandlerAndRunnable> set) {
        Iterator<HandlerAndRunnable> it = set.iterator();
        while (it.hasNext()) {
            it.next().dispatch();
        }
        this.pendingOnCompletionActions.removeAll(set);
    }

    private void addMediaSourcesInternal(int i, Collection<MediaSourceHolder> collection) {
        Iterator<MediaSourceHolder> it = collection.iterator();
        while (it.hasNext()) {
            addMediaSourceInternal(i, it.next());
            i++;
        }
    }

    private void addMediaSourceInternal(int i, MediaSourceHolder mediaSourceHolder) {
        if (i > 0) {
            MediaSourceHolder mediaSourceHolder2 = this.mediaSourceHolders.get(i - 1);
            mediaSourceHolder.reset(i, mediaSourceHolder2.firstWindowIndexInChild + mediaSourceHolder2.timeline.getWindowCount(), mediaSourceHolder2.firstPeriodIndexInChild + mediaSourceHolder2.timeline.getPeriodCount());
        } else {
            mediaSourceHolder.reset(i, 0, 0);
        }
        correctOffsets(i, 1, mediaSourceHolder.timeline.getWindowCount(), mediaSourceHolder.timeline.getPeriodCount());
        this.mediaSourceHolders.add(i, mediaSourceHolder);
        this.mediaSourceByUid.put(mediaSourceHolder.uid, mediaSourceHolder);
        if (this.useLazyPreparation) {
            return;
        }
        mediaSourceHolder.hasStartedPreparing = true;
        prepareChildSource(mediaSourceHolder, mediaSourceHolder.mediaSource);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0080  */
    private void updateMediaSourceInternal(MediaSourceHolder mediaSourceHolder, Timeline timeline) {
        long j;
        if (mediaSourceHolder == null) {
            throw new IllegalArgumentException();
        }
        DeferredTimeline deferredTimeline = mediaSourceHolder.timeline;
        if (deferredTimeline.getTimeline() == timeline) {
            return;
        }
        int windowCount = timeline.getWindowCount() - deferredTimeline.getWindowCount();
        int periodCount = timeline.getPeriodCount() - deferredTimeline.getPeriodCount();
        if (windowCount != 0 || periodCount != 0) {
            correctOffsets(mediaSourceHolder.childIndex + 1, 0, windowCount, periodCount);
        }
        if (mediaSourceHolder.isPrepared) {
            mediaSourceHolder.timeline = deferredTimeline.cloneWithUpdatedTimeline(timeline);
        } else if (!timeline.isEmpty()) {
            Assertions.checkState(mediaSourceHolder.activeMediaPeriods.size() <= 1);
            DeferredMediaPeriod deferredMediaPeriod = mediaSourceHolder.activeMediaPeriods.isEmpty() ? null : mediaSourceHolder.activeMediaPeriods.get(0);
            timeline.getWindow(0, this.window);
            long defaultPositionUs = this.window.getDefaultPositionUs();
            if (deferredMediaPeriod != null) {
                long preparePositionUs = deferredMediaPeriod.getPreparePositionUs();
                if (preparePositionUs != 0) {
                    j = preparePositionUs;
                } else {
                    j = defaultPositionUs;
                }
            } else {
                j = defaultPositionUs;
            }
            Pair<Object, Long> periodPosition = timeline.getPeriodPosition(this.window, this.period, 0, j);
            Object obj = periodPosition.first;
            long jLongValue = ((Long) periodPosition.second).longValue();
            mediaSourceHolder.timeline = DeferredTimeline.createWithRealTimeline(timeline, obj);
            if (deferredMediaPeriod != null) {
                deferredMediaPeriod.overridePreparePositionUs(jLongValue);
                deferredMediaPeriod.createPeriod(deferredMediaPeriod.id.copyWithPeriodUid(getChildPeriodUid(mediaSourceHolder, deferredMediaPeriod.id.periodUid)));
            }
        } else {
            mediaSourceHolder.timeline = DeferredTimeline.createWithRealTimeline(timeline, DeferredTimeline.DUMMY_ID);
        }
        mediaSourceHolder.isPrepared = true;
        scheduleTimelineUpdate();
    }

    private void removeMediaSourceInternal(int i) {
        MediaSourceHolder mediaSourceHolderRemove = this.mediaSourceHolders.remove(i);
        this.mediaSourceByUid.remove(mediaSourceHolderRemove.uid);
        DeferredTimeline deferredTimeline = mediaSourceHolderRemove.timeline;
        correctOffsets(i, -1, -deferredTimeline.getWindowCount(), -deferredTimeline.getPeriodCount());
        mediaSourceHolderRemove.isRemoved = true;
        maybeReleaseChildSource(mediaSourceHolderRemove);
    }

    private void moveMediaSourceInternal(int i, int i2) {
        int iMin = Math.min(i, i2);
        int iMax = Math.max(i, i2);
        int windowCount = this.mediaSourceHolders.get(iMin).firstWindowIndexInChild;
        int periodCount = this.mediaSourceHolders.get(iMin).firstPeriodIndexInChild;
        List<MediaSourceHolder> list = this.mediaSourceHolders;
        list.add(i2, list.remove(i));
        while (iMin <= iMax) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(iMin);
            mediaSourceHolder.firstWindowIndexInChild = windowCount;
            mediaSourceHolder.firstPeriodIndexInChild = periodCount;
            windowCount += mediaSourceHolder.timeline.getWindowCount();
            periodCount += mediaSourceHolder.timeline.getPeriodCount();
            iMin++;
        }
    }

    private void correctOffsets(int i, int i2, int i3, int i4) {
        this.windowCount += i3;
        this.periodCount += i4;
        while (i < this.mediaSourceHolders.size()) {
            this.mediaSourceHolders.get(i).childIndex += i2;
            this.mediaSourceHolders.get(i).firstWindowIndexInChild += i3;
            this.mediaSourceHolders.get(i).firstPeriodIndexInChild += i4;
            i++;
        }
    }

    private void maybeReleaseChildSource(MediaSourceHolder mediaSourceHolder) {
        if (mediaSourceHolder.isRemoved && mediaSourceHolder.hasStartedPreparing && mediaSourceHolder.activeMediaPeriods.isEmpty()) {
            releaseChildSource(mediaSourceHolder);
        }
    }

    private static Object getMediaSourceHolderUid(Object obj) {
        return ConcatenatedTimeline.getChildTimelineUidFromConcatenatedUid(obj);
    }

    private static Object getChildPeriodUid(MediaSourceHolder mediaSourceHolder, Object obj) {
        Object childPeriodUidFromConcatenatedUid = ConcatenatedTimeline.getChildPeriodUidFromConcatenatedUid(obj);
        return childPeriodUidFromConcatenatedUid.equals(DeferredTimeline.DUMMY_ID) ? mediaSourceHolder.timeline.replacedId : childPeriodUidFromConcatenatedUid;
    }

    private static Object getPeriodUid(MediaSourceHolder mediaSourceHolder, Object obj) {
        if (mediaSourceHolder.timeline.replacedId.equals(obj)) {
            obj = DeferredTimeline.DUMMY_ID;
        }
        return ConcatenatedTimeline.getConcatenatedUid(mediaSourceHolder.uid, obj);
    }

    static final class MediaSourceHolder implements Comparable<MediaSourceHolder> {
        public int childIndex;
        public int firstPeriodIndexInChild;
        public int firstWindowIndexInChild;
        public boolean hasStartedPreparing;
        public boolean isPrepared;
        public boolean isRemoved;
        public final MediaSource mediaSource;
        public DeferredTimeline timeline;
        public List<DeferredMediaPeriod> activeMediaPeriods = new ArrayList();
        public final Object uid = new Object();

        public MediaSourceHolder(MediaSource mediaSource) {
            this.mediaSource = mediaSource;
            this.timeline = DeferredTimeline.createWithDummyTimeline(mediaSource.getTag());
        }

        public void reset(int i, int i2, int i3) {
            this.childIndex = i;
            this.firstWindowIndexInChild = i2;
            this.firstPeriodIndexInChild = i3;
            this.hasStartedPreparing = false;
            this.isPrepared = false;
            this.isRemoved = false;
            this.activeMediaPeriods.clear();
        }

        @Override // java.lang.Comparable
        public int compareTo(MediaSourceHolder mediaSourceHolder) {
            return this.firstPeriodIndexInChild - mediaSourceHolder.firstPeriodIndexInChild;
        }
    }

    private static final class MessageData<T> {
        public final T customData;
        public final int index;
        public final HandlerAndRunnable onCompletionAction;

        public MessageData(int i, T t, HandlerAndRunnable handlerAndRunnable) {
            this.index = i;
            this.customData = t;
            this.onCompletionAction = handlerAndRunnable;
        }
    }

    private static final class ConcatenatedTimeline extends AbstractConcatenatedTimeline {
        private final HashMap<Object, Integer> childIndexByUid;
        private final int[] firstPeriodInChildIndices;
        private final int[] firstWindowInChildIndices;
        private final int periodCount;
        private final Timeline[] timelines;
        private final Object[] uids;
        private final int windowCount;

        public ConcatenatedTimeline(Collection<MediaSourceHolder> collection, int i, int i2, ShuffleOrder shuffleOrder, boolean z) {
            super(z, shuffleOrder);
            this.windowCount = i;
            this.periodCount = i2;
            int size = collection.size();
            this.firstPeriodInChildIndices = new int[size];
            this.firstWindowInChildIndices = new int[size];
            this.timelines = new Timeline[size];
            this.uids = new Object[size];
            this.childIndexByUid = new HashMap<>();
            int i3 = 0;
            for (MediaSourceHolder mediaSourceHolder : collection) {
                this.timelines[i3] = mediaSourceHolder.timeline;
                this.firstPeriodInChildIndices[i3] = mediaSourceHolder.firstPeriodIndexInChild;
                this.firstWindowInChildIndices[i3] = mediaSourceHolder.firstWindowIndexInChild;
                this.uids[i3] = mediaSourceHolder.uid;
                this.childIndexByUid.put(this.uids[i3], Integer.valueOf(i3));
                i3++;
            }
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        protected int getChildIndexByPeriodIndex(int i) {
            return Util.binarySearchFloor(this.firstPeriodInChildIndices, i + 1, false, false);
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        protected int getChildIndexByWindowIndex(int i) {
            return Util.binarySearchFloor(this.firstWindowInChildIndices, i + 1, false, false);
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        protected int getChildIndexByChildUid(Object obj) {
            Integer num = this.childIndexByUid.get(obj);
            if (num == null) {
                return -1;
            }
            return num.intValue();
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        protected Timeline getTimelineByChildIndex(int i) {
            return this.timelines[i];
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        protected int getFirstPeriodIndexByChildIndex(int i) {
            return this.firstPeriodInChildIndices[i];
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        protected int getFirstWindowIndexByChildIndex(int i) {
            return this.firstWindowInChildIndices[i];
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        protected Object getChildUidByChildIndex(int i) {
            return this.uids[i];
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getWindowCount() {
            return this.windowCount;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getPeriodCount() {
            return this.periodCount;
        }
    }

    private static final class DeferredTimeline extends ForwardingTimeline {
        private static final Object DUMMY_ID = new Object();
        private final Object replacedId;

        public static DeferredTimeline createWithDummyTimeline(Object obj) {
            return new DeferredTimeline(new DummyTimeline(obj), DUMMY_ID);
        }

        public static DeferredTimeline createWithRealTimeline(Timeline timeline, Object obj) {
            return new DeferredTimeline(timeline, obj);
        }

        private DeferredTimeline(Timeline timeline, Object obj) {
            super(timeline);
            this.replacedId = obj;
        }

        public DeferredTimeline cloneWithUpdatedTimeline(Timeline timeline) {
            return new DeferredTimeline(timeline, this.replacedId);
        }

        public Timeline getTimeline() {
            return this.timeline;
        }

        @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            this.timeline.getPeriod(i, period, z);
            if (Util.areEqual(period.uid, this.replacedId)) {
                period.uid = DUMMY_ID;
            }
            return period;
        }

        @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
        public int getIndexOfPeriod(Object obj) {
            Timeline timeline = this.timeline;
            if (DUMMY_ID.equals(obj)) {
                obj = this.replacedId;
            }
            return timeline.getIndexOfPeriod(obj);
        }

        @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
        public Object getUidOfPeriod(int i) {
            Object uidOfPeriod = this.timeline.getUidOfPeriod(i);
            return Util.areEqual(uidOfPeriod, this.replacedId) ? DUMMY_ID : uidOfPeriod;
        }
    }

    private static final class DummyTimeline extends Timeline {
        private final Object tag;

        @Override // com.google.android.exoplayer2.Timeline
        public int getPeriodCount() {
            return 1;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getWindowCount() {
            return 1;
        }

        public DummyTimeline(Object obj) {
            this.tag = obj;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public Timeline.Window getWindow(int i, Timeline.Window window, boolean z, long j) {
            return window.set(this.tag, C.TIME_UNSET, C.TIME_UNSET, false, true, 0L, C.TIME_UNSET, 0, 0, 0L);
        }

        @Override // com.google.android.exoplayer2.Timeline
        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            return period.set(0, DeferredTimeline.DUMMY_ID, 0, C.TIME_UNSET, 0L);
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getIndexOfPeriod(Object obj) {
            return obj == DeferredTimeline.DUMMY_ID ? 0 : -1;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public Object getUidOfPeriod(int i) {
            return DeferredTimeline.DUMMY_ID;
        }
    }

    private static final class DummyMediaSource extends BaseMediaSource {
        @Override // com.google.android.exoplayer2.source.MediaSource
        public Object getTag() {
            return null;
        }

        @Override // com.google.android.exoplayer2.source.MediaSource
        public void maybeThrowSourceInfoRefreshError() throws IOException {
        }

        @Override // com.google.android.exoplayer2.source.BaseMediaSource
        protected void prepareSourceInternal(TransferListener transferListener) {
        }

        @Override // com.google.android.exoplayer2.source.MediaSource
        public void releasePeriod(MediaPeriod mediaPeriod) {
        }

        @Override // com.google.android.exoplayer2.source.BaseMediaSource
        protected void releaseSourceInternal() {
        }

        private DummyMediaSource() {
        }

        @Override // com.google.android.exoplayer2.source.MediaSource
        public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
            throw new UnsupportedOperationException();
        }
    }

    private static final class HandlerAndRunnable {
        private final Handler handler;
        private final Runnable runnable;

        public HandlerAndRunnable(Handler handler, Runnable runnable) {
            this.handler = handler;
            this.runnable = runnable;
        }

        public void dispatch() {
            this.handler.post(this.runnable);
        }
    }
}
