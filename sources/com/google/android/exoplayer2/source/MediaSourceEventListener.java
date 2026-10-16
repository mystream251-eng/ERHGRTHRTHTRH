package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes2.dex */
public interface MediaSourceEventListener {
    void onDownstreamFormatChanged(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData);

    void onLoadCanceled(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData);

    void onLoadCompleted(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData);

    void onLoadError(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z);

    void onLoadStarted(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData);

    void onMediaPeriodCreated(int i, MediaSource.MediaPeriodId mediaPeriodId);

    void onMediaPeriodReleased(int i, MediaSource.MediaPeriodId mediaPeriodId);

    void onReadingStarted(int i, MediaSource.MediaPeriodId mediaPeriodId);

    void onUpstreamDiscarded(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData);

    public static final class LoadEventInfo {
        public final long bytesLoaded;
        public final DataSpec dataSpec;
        public final long elapsedRealtimeMs;
        public final long loadDurationMs;
        public final Map<String, List<String>> responseHeaders;
        public final Uri uri;

        public LoadEventInfo(DataSpec dataSpec, Uri uri, Map<String, List<String>> map, long j, long j2, long j3) {
            this.dataSpec = dataSpec;
            this.uri = uri;
            this.responseHeaders = map;
            this.elapsedRealtimeMs = j;
            this.loadDurationMs = j2;
            this.bytesLoaded = j3;
        }
    }

    public static final class MediaLoadData {
        public final int dataType;
        public final long mediaEndTimeMs;
        public final long mediaStartTimeMs;
        public final Format trackFormat;
        public final Object trackSelectionData;
        public final int trackSelectionReason;
        public final int trackType;

        public MediaLoadData(int i, int i2, Format format, int i3, Object obj, long j, long j2) {
            this.dataType = i;
            this.trackType = i2;
            this.trackFormat = format;
            this.trackSelectionReason = i3;
            this.trackSelectionData = obj;
            this.mediaStartTimeMs = j;
            this.mediaEndTimeMs = j2;
        }
    }

    public static final class EventDispatcher {
        private final CopyOnWriteArrayList<ListenerAndHandler> listenerAndHandlers;
        public final MediaSource.MediaPeriodId mediaPeriodId;
        private final long mediaTimeOffsetMs;
        public final int windowIndex;

        public EventDispatcher() {
            this(new CopyOnWriteArrayList(), 0, null, 0L);
        }

        private EventDispatcher(CopyOnWriteArrayList<ListenerAndHandler> copyOnWriteArrayList, int i, MediaSource.MediaPeriodId mediaPeriodId, long j) {
            this.listenerAndHandlers = copyOnWriteArrayList;
            this.windowIndex = i;
            this.mediaPeriodId = mediaPeriodId;
            this.mediaTimeOffsetMs = j;
        }

        public EventDispatcher withParameters(int i, MediaSource.MediaPeriodId mediaPeriodId, long j) {
            return new EventDispatcher(this.listenerAndHandlers, i, mediaPeriodId, j);
        }

        public void addEventListener(Handler handler, MediaSourceEventListener mediaSourceEventListener) {
            Assertions.checkArgument((handler == null || mediaSourceEventListener == null) ? false : true);
            this.listenerAndHandlers.add(new ListenerAndHandler(handler, mediaSourceEventListener));
        }

        public void removeEventListener(MediaSourceEventListener mediaSourceEventListener) {
            for (ListenerAndHandler listenerAndHandler : this.listenerAndHandlers) {
                if (listenerAndHandler.listener == mediaSourceEventListener) {
                    this.listenerAndHandlers.remove(listenerAndHandler);
                }
            }
        }

        public void mediaPeriodCreated() {
            final MediaSource.MediaPeriodId mediaPeriodId = (MediaSource.MediaPeriodId) Assertions.checkNotNull(this.mediaPeriodId);
            for (ListenerAndHandler listenerAndHandler : this.listenerAndHandlers) {
                final MediaSourceEventListener mediaSourceEventListener = listenerAndHandler.listener;
                postOrRun(listenerAndHandler.handler, new Runnable() { // from class: com.google.android.exoplayer2.source.-$$Lambda$MediaSourceEventListener$EventDispatcher$N-EOPAK5UK0--YMNjezq7UM3UNI
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$mediaPeriodCreated$0$MediaSourceEventListener$EventDispatcher(mediaSourceEventListener, mediaPeriodId);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$mediaPeriodCreated$0$MediaSourceEventListener$EventDispatcher(MediaSourceEventListener mediaSourceEventListener, MediaSource.MediaPeriodId mediaPeriodId) {
            mediaSourceEventListener.onMediaPeriodCreated(this.windowIndex, mediaPeriodId);
        }

        public void mediaPeriodReleased() {
            final MediaSource.MediaPeriodId mediaPeriodId = (MediaSource.MediaPeriodId) Assertions.checkNotNull(this.mediaPeriodId);
            for (ListenerAndHandler listenerAndHandler : this.listenerAndHandlers) {
                final MediaSourceEventListener mediaSourceEventListener = listenerAndHandler.listener;
                postOrRun(listenerAndHandler.handler, new Runnable() { // from class: com.google.android.exoplayer2.source.-$$Lambda$MediaSourceEventListener$EventDispatcher$zyck4ebRbqvR6eQIjdzRcIBkRbI
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$mediaPeriodReleased$1$MediaSourceEventListener$EventDispatcher(mediaSourceEventListener, mediaPeriodId);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$mediaPeriodReleased$1$MediaSourceEventListener$EventDispatcher(MediaSourceEventListener mediaSourceEventListener, MediaSource.MediaPeriodId mediaPeriodId) {
            mediaSourceEventListener.onMediaPeriodReleased(this.windowIndex, mediaPeriodId);
        }

        public void loadStarted(DataSpec dataSpec, int i, long j) {
            loadStarted(dataSpec, i, -1, null, 0, null, C.TIME_UNSET, C.TIME_UNSET, j);
        }

        public void loadStarted(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3) {
            loadStarted(new LoadEventInfo(dataSpec, dataSpec.uri, Collections.emptyMap(), j3, 0L, 0L), new MediaLoadData(i, i2, format, i3, obj, adjustMediaTime(j), adjustMediaTime(j2)));
        }

        public void loadStarted(final LoadEventInfo loadEventInfo, final MediaLoadData mediaLoadData) {
            for (ListenerAndHandler listenerAndHandler : this.listenerAndHandlers) {
                final MediaSourceEventListener mediaSourceEventListener = listenerAndHandler.listener;
                postOrRun(listenerAndHandler.handler, new Runnable() { // from class: com.google.android.exoplayer2.source.-$$Lambda$MediaSourceEventListener$EventDispatcher$WQKVpIh5ilpOizOGmbnyUThugMU
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$loadStarted$2$MediaSourceEventListener$EventDispatcher(mediaSourceEventListener, loadEventInfo, mediaLoadData);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$loadStarted$2$MediaSourceEventListener$EventDispatcher(MediaSourceEventListener mediaSourceEventListener, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            mediaSourceEventListener.onLoadStarted(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData);
        }

        public void loadCompleted(DataSpec dataSpec, Uri uri, Map<String, List<String>> map, int i, long j, long j2, long j3) {
            loadCompleted(dataSpec, uri, map, i, -1, null, 0, null, C.TIME_UNSET, C.TIME_UNSET, j, j2, j3);
        }

        public void loadCompleted(DataSpec dataSpec, Uri uri, Map<String, List<String>> map, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
            loadCompleted(new LoadEventInfo(dataSpec, uri, map, j3, j4, j5), new MediaLoadData(i, i2, format, i3, obj, adjustMediaTime(j), adjustMediaTime(j2)));
        }

        public void loadCompleted(final LoadEventInfo loadEventInfo, final MediaLoadData mediaLoadData) {
            for (ListenerAndHandler listenerAndHandler : this.listenerAndHandlers) {
                final MediaSourceEventListener mediaSourceEventListener = listenerAndHandler.listener;
                postOrRun(listenerAndHandler.handler, new Runnable() { // from class: com.google.android.exoplayer2.source.-$$Lambda$MediaSourceEventListener$EventDispatcher$IejPnkXyHgj2V1iyO1dqtBKfihI
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$loadCompleted$3$MediaSourceEventListener$EventDispatcher(mediaSourceEventListener, loadEventInfo, mediaLoadData);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$loadCompleted$3$MediaSourceEventListener$EventDispatcher(MediaSourceEventListener mediaSourceEventListener, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            mediaSourceEventListener.onLoadCompleted(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData);
        }

        public void loadCanceled(DataSpec dataSpec, Uri uri, Map<String, List<String>> map, int i, long j, long j2, long j3) {
            loadCanceled(dataSpec, uri, map, i, -1, null, 0, null, C.TIME_UNSET, C.TIME_UNSET, j, j2, j3);
        }

        public void loadCanceled(DataSpec dataSpec, Uri uri, Map<String, List<String>> map, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
            loadCanceled(new LoadEventInfo(dataSpec, uri, map, j3, j4, j5), new MediaLoadData(i, i2, format, i3, obj, adjustMediaTime(j), adjustMediaTime(j2)));
        }

        public void loadCanceled(final LoadEventInfo loadEventInfo, final MediaLoadData mediaLoadData) {
            for (ListenerAndHandler listenerAndHandler : this.listenerAndHandlers) {
                final MediaSourceEventListener mediaSourceEventListener = listenerAndHandler.listener;
                postOrRun(listenerAndHandler.handler, new Runnable() { // from class: com.google.android.exoplayer2.source.-$$Lambda$MediaSourceEventListener$EventDispatcher$1-VoN1d1C8yHbFOrB_mXtUwAn3M
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$loadCanceled$4$MediaSourceEventListener$EventDispatcher(mediaSourceEventListener, loadEventInfo, mediaLoadData);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$loadCanceled$4$MediaSourceEventListener$EventDispatcher(MediaSourceEventListener mediaSourceEventListener, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            mediaSourceEventListener.onLoadCanceled(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData);
        }

        public void loadError(DataSpec dataSpec, Uri uri, Map<String, List<String>> map, int i, long j, long j2, long j3, IOException iOException, boolean z) {
            loadError(dataSpec, uri, map, i, -1, null, 0, null, C.TIME_UNSET, C.TIME_UNSET, j, j2, j3, iOException, z);
        }

        public void loadError(DataSpec dataSpec, Uri uri, Map<String, List<String>> map, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5, IOException iOException, boolean z) {
            loadError(new LoadEventInfo(dataSpec, uri, map, j3, j4, j5), new MediaLoadData(i, i2, format, i3, obj, adjustMediaTime(j), adjustMediaTime(j2)), iOException, z);
        }

        public void loadError(final LoadEventInfo loadEventInfo, final MediaLoadData mediaLoadData, final IOException iOException, final boolean z) {
            for (ListenerAndHandler listenerAndHandler : this.listenerAndHandlers) {
                final MediaSourceEventListener mediaSourceEventListener = listenerAndHandler.listener;
                postOrRun(listenerAndHandler.handler, new Runnable() { // from class: com.google.android.exoplayer2.source.-$$Lambda$MediaSourceEventListener$EventDispatcher$0X-TAsNqR4TUW1yA_ZD1_p3oT84
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$loadError$5$MediaSourceEventListener$EventDispatcher(mediaSourceEventListener, loadEventInfo, mediaLoadData, iOException, z);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$loadError$5$MediaSourceEventListener$EventDispatcher(MediaSourceEventListener mediaSourceEventListener, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
            mediaSourceEventListener.onLoadError(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData, iOException, z);
        }

        public void readingStarted() {
            final MediaSource.MediaPeriodId mediaPeriodId = (MediaSource.MediaPeriodId) Assertions.checkNotNull(this.mediaPeriodId);
            for (ListenerAndHandler listenerAndHandler : this.listenerAndHandlers) {
                final MediaSourceEventListener mediaSourceEventListener = listenerAndHandler.listener;
                postOrRun(listenerAndHandler.handler, new Runnable() { // from class: com.google.android.exoplayer2.source.-$$Lambda$MediaSourceEventListener$EventDispatcher$PV8wmqGm7vRMJNlt--V3zhXfxiE
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$readingStarted$6$MediaSourceEventListener$EventDispatcher(mediaSourceEventListener, mediaPeriodId);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$readingStarted$6$MediaSourceEventListener$EventDispatcher(MediaSourceEventListener mediaSourceEventListener, MediaSource.MediaPeriodId mediaPeriodId) {
            mediaSourceEventListener.onReadingStarted(this.windowIndex, mediaPeriodId);
        }

        public void upstreamDiscarded(int i, long j, long j2) {
            upstreamDiscarded(new MediaLoadData(1, i, null, 3, null, adjustMediaTime(j), adjustMediaTime(j2)));
        }

        public void upstreamDiscarded(final MediaLoadData mediaLoadData) {
            final MediaSource.MediaPeriodId mediaPeriodId = (MediaSource.MediaPeriodId) Assertions.checkNotNull(this.mediaPeriodId);
            for (ListenerAndHandler listenerAndHandler : this.listenerAndHandlers) {
                final MediaSourceEventListener mediaSourceEventListener = listenerAndHandler.listener;
                postOrRun(listenerAndHandler.handler, new Runnable() { // from class: com.google.android.exoplayer2.source.-$$Lambda$MediaSourceEventListener$EventDispatcher$BtPa14lQQTv1oUeMy_9QaCysWHY
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$upstreamDiscarded$7$MediaSourceEventListener$EventDispatcher(mediaSourceEventListener, mediaPeriodId, mediaLoadData);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$upstreamDiscarded$7$MediaSourceEventListener$EventDispatcher(MediaSourceEventListener mediaSourceEventListener, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
            mediaSourceEventListener.onUpstreamDiscarded(this.windowIndex, mediaPeriodId, mediaLoadData);
        }

        public void downstreamFormatChanged(int i, Format format, int i2, Object obj, long j) {
            downstreamFormatChanged(new MediaLoadData(1, i, format, i2, obj, adjustMediaTime(j), C.TIME_UNSET));
        }

        public void downstreamFormatChanged(final MediaLoadData mediaLoadData) {
            for (ListenerAndHandler listenerAndHandler : this.listenerAndHandlers) {
                final MediaSourceEventListener mediaSourceEventListener = listenerAndHandler.listener;
                postOrRun(listenerAndHandler.handler, new Runnable() { // from class: com.google.android.exoplayer2.source.-$$Lambda$MediaSourceEventListener$EventDispatcher$ES4FdQzWtupQEe6zuV_1M9-f9xU
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$downstreamFormatChanged$8$MediaSourceEventListener$EventDispatcher(mediaSourceEventListener, mediaLoadData);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$downstreamFormatChanged$8$MediaSourceEventListener$EventDispatcher(MediaSourceEventListener mediaSourceEventListener, MediaLoadData mediaLoadData) {
            mediaSourceEventListener.onDownstreamFormatChanged(this.windowIndex, this.mediaPeriodId, mediaLoadData);
        }

        private long adjustMediaTime(long j) {
            long jUsToMs = C.usToMs(j);
            return jUsToMs == C.TIME_UNSET ? C.TIME_UNSET : this.mediaTimeOffsetMs + jUsToMs;
        }

        private void postOrRun(Handler handler, Runnable runnable) {
            if (handler.getLooper() == Looper.myLooper()) {
                runnable.run();
            } else {
                handler.post(runnable);
            }
        }

        private static final class ListenerAndHandler {
            public final Handler handler;
            public final MediaSourceEventListener listener;

            public ListenerAndHandler(Handler handler, MediaSourceEventListener mediaSourceEventListener) {
                this.handler = handler;
                this.listener = mediaSourceEventListener;
            }
        }
    }
}
