package com.dotscreen.farkit.analytics;

import kotlin.Metadata;

/* JADX INFO: compiled from: LivePlaybackAnalyticsListener.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0001H&J \u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0001H&J\u001c\u0010\n\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0001H&¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/farkit/analytics/LivePlaybackAnalyticsListener;", "", "onCloseLiveStream", "", "onOpenLiveStream", "onStartLivePlayback", "onZap", "channel", "channelId", "", "setChannel", "farkit_release"}, k = 1, mv = {1, 1, 16})
public interface LivePlaybackAnalyticsListener {
    void onCloseLiveStream();

    void onOpenLiveStream();

    void onStartLivePlayback();

    void onZap(Object channel);

    void onZap(String channelId, Object channel);

    void setChannel(String channelId, Object channel);

    /* JADX INFO: compiled from: LivePlaybackAnalyticsListener.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static final class DefaultImpls {
        public static /* synthetic */ void setChannel$default(LivePlaybackAnalyticsListener livePlaybackAnalyticsListener, String str, Object obj, int i, Object obj2) {
            if (obj2 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setChannel");
            }
            if ((i & 2) != 0) {
                obj = null;
            }
            livePlaybackAnalyticsListener.setChannel(str, obj);
        }

        public static /* synthetic */ void onZap$default(LivePlaybackAnalyticsListener livePlaybackAnalyticsListener, String str, Object obj, int i, Object obj2) {
            if (obj2 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onZap");
            }
            if ((i & 1) != 0) {
                str = (String) null;
            }
            if ((i & 2) != 0) {
                obj = null;
            }
            livePlaybackAnalyticsListener.onZap(str, obj);
        }
    }
}
