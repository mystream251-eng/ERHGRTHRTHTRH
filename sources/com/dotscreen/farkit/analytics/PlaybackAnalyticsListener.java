package com.dotscreen.farkit.analytics;

import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;

/* JADX INFO: compiled from: PlaybackAnalyticsListener.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010$\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H&J\u001f\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005H&¢\u0006\u0002\u0010\u0013JX\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00012\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u001bH&J'\u0010\u001c\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u00052\b\u0010\u001e\u001a\u0004\u0018\u00010\u0005H&¢\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H&J\u0010\u0010!\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H&J\u0010\u0010\"\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H&¨\u0006#"}, d2 = {"Lcom/dotscreen/farkit/analytics/PlaybackAnalyticsListener;", "", "onBuffering", "", "percent", "", "onBufferingStateChanged", "start", "", "onClose", "currentPosition", "onCurrentPositionChanged", "", "onPause", "onPlaybackEnd", "onPlaybackError", "reason", "", "code", "(Ljava/lang/String;Ljava/lang/Integer;)V", "onPlaybackRequested", "contentId", ToolboxAnalyticsModuleKt.KEY_PLAY_BACK_URL, "mimeType", ToolboxAnalyticsModuleKt.KEY_DRM_TYPE, ToolboxAnalyticsModuleKt.KEY_CONTENT, "extras", "", "onPlaybackStart", "duration", "preBufferingTime", "(IILjava/lang/Integer;)V", "onResume", "onSeekEnded", "onSeekStarted", "farkit_release"}, k = 1, mv = {1, 1, 16})
public interface PlaybackAnalyticsListener {
    void onBuffering(int percent);

    void onBufferingStateChanged(boolean start);

    void onClose(int currentPosition);

    void onCurrentPositionChanged(long currentPosition);

    void onPause(int currentPosition);

    void onPlaybackEnd(int currentPosition);

    void onPlaybackError(String reason, Integer code);

    void onPlaybackRequested(String contentId, String playbackUrl, String mimeType, String drmType, Object content, Map<String, ? extends Object> extras);

    void onPlaybackStart(int currentPosition, int duration, Integer preBufferingTime);

    void onResume(int currentPosition);

    void onSeekEnded(int currentPosition);

    void onSeekStarted(int currentPosition);

    /* JADX INFO: compiled from: PlaybackAnalyticsListener.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static final class DefaultImpls {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void onPlaybackRequested$default(PlaybackAnalyticsListener playbackAnalyticsListener, String str, String str2, String str3, String str4, Object obj, Map map, int i, Object obj2) {
            if (obj2 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onPlaybackRequested");
            }
            playbackAnalyticsListener.onPlaybackRequested(str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (String) null : str4, (i & 16) == 0 ? obj : null, (i & 32) != 0 ? MapsKt.emptyMap() : map);
        }
    }
}
