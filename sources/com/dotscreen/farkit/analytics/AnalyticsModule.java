package com.dotscreen.farkit.analytics;

import com.dotscreen.farkit.network.RequestManager;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AnalyticsModule.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004JN\u0010\u0013\u001a\u00020\u00142\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\f2\b\u0010\u0017\u001a\u0004\u0018\u00010\f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00190\u001bH&J'\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010 \u001a\u0004\u0018\u00010\u001eH&¢\u0006\u0002\u0010!R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\""}, d2 = {"Lcom/dotscreen/farkit/analytics/AnalyticsModule;", "Lcom/dotscreen/farkit/analytics/PlaybackAnalyticsListener;", "requestManager", "Lcom/dotscreen/farkit/network/RequestManager;", "(Lcom/dotscreen/farkit/network/RequestManager;)V", "blockNextSeek", "", "getBlockNextSeek", "()Z", "setBlockNextSeek", "(Z)V", "contentId", "", "getContentId", "()Ljava/lang/String;", "setContentId", "(Ljava/lang/String;)V", "getRequestManager", "()Lcom/dotscreen/farkit/network/RequestManager;", "onPlaybackRequested", "", ToolboxAnalyticsModuleKt.KEY_PLAY_BACK_URL, "mimeType", ToolboxAnalyticsModuleKt.KEY_DRM_TYPE, ToolboxAnalyticsModuleKt.KEY_CONTENT, "", "extras", "", "onPlaybackStart", "currentPosition", "", "duration", "preBufferingTime", "(IILjava/lang/Integer;)V", "farkit_release"}, k = 1, mv = {1, 1, 16})
public abstract class AnalyticsModule implements PlaybackAnalyticsListener {
    private boolean blockNextSeek;
    private String contentId;
    private final RequestManager requestManager;

    /* JADX WARN: Multi-variable type inference failed */
    public AnalyticsModule() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public abstract void onPlaybackRequested(String contentId, String playbackUrl, String mimeType, String drmType, Object content, Map<String, ? extends Object> extras);

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public abstract void onPlaybackStart(int currentPosition, int duration, Integer preBufferingTime);

    public AnalyticsModule(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    public /* synthetic */ AnalyticsModule(RequestManager requestManager, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (RequestManager) null : requestManager);
    }

    public final RequestManager getRequestManager() {
        return this.requestManager;
    }

    public final String getContentId() {
        return this.contentId;
    }

    public final void setContentId(String str) {
        this.contentId = str;
    }

    public final boolean getBlockNextSeek() {
        return this.blockNextSeek;
    }

    public final void setBlockNextSeek(boolean z) {
        this.blockNextSeek = z;
    }
}
