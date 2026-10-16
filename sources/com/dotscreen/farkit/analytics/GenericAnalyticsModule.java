package com.dotscreen.farkit.analytics;

import com.dotscreen.farkit.network.RequestManager;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import io.reactivex.disposables.CompositeDisposable;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GenericAnalyticsModule.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0000\b&\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016JN\u0010\u0019\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001c0\u001eH\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000e¨\u0006\u001f"}, d2 = {"Lcom/dotscreen/farkit/analytics/GenericAnalyticsModule;", "Lcom/dotscreen/farkit/analytics/AnalyticsModule;", "requestManager", "Lcom/dotscreen/farkit/network/RequestManager;", "(Lcom/dotscreen/farkit/network/RequestManager;)V", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "getCompositeDisposable", "()Lio/reactivex/disposables/CompositeDisposable;", ToolboxAnalyticsModuleKt.KEY_DRM_TYPE, "", "getDrmType", "()Ljava/lang/String;", "setDrmType", "(Ljava/lang/String;)V", "mimeType", "getMimeType", "setMimeType", ToolboxAnalyticsModuleKt.KEY_PLAY_BACK_URL, "getPlaybackUrl", "setPlaybackUrl", "onPlaybackEnd", "", "currentPosition", "", "onPlaybackRequested", "contentId", ToolboxAnalyticsModuleKt.KEY_CONTENT, "", "extras", "", "farkit_release"}, k = 1, mv = {1, 1, 16})
public abstract class GenericAnalyticsModule extends AnalyticsModule {
    private final CompositeDisposable compositeDisposable;
    private String drmType;
    private String mimeType;
    private String playbackUrl;

    /* JADX WARN: Multi-variable type inference failed */
    public GenericAnalyticsModule() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public GenericAnalyticsModule(RequestManager requestManager) {
        super(requestManager);
        this.compositeDisposable = new CompositeDisposable();
    }

    public /* synthetic */ GenericAnalyticsModule(RequestManager requestManager, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (RequestManager) null : requestManager);
    }

    public final CompositeDisposable getCompositeDisposable() {
        return this.compositeDisposable;
    }

    public final String getPlaybackUrl() {
        return this.playbackUrl;
    }

    public final void setPlaybackUrl(String str) {
        this.playbackUrl = str;
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    public final void setMimeType(String str) {
        this.mimeType = str;
    }

    public final String getDrmType() {
        return this.drmType;
    }

    public final void setDrmType(String str) {
        this.drmType = str;
    }

    @Override // com.dotscreen.farkit.analytics.AnalyticsModule, com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPlaybackRequested(String contentId, String playbackUrl, String mimeType, String drmType, Object content, Map<String, ? extends Object> extras) {
        Intrinsics.checkParameterIsNotNull(extras, "extras");
        setContentId(contentId);
        this.playbackUrl = playbackUrl;
        this.mimeType = mimeType;
        this.drmType = mimeType;
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPlaybackEnd(int currentPosition) {
        onClose(currentPosition);
    }
}
