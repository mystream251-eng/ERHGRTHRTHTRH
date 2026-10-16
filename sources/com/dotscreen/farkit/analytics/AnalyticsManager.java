package com.dotscreen.farkit.analytics;

import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnalyticsManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\b\u000f\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\fJ\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\fH\u0016J\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0014H\u0016J\b\u0010\u0019\u001a\u00020\u0010H\u0016J\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u000eH\u0016J\b\u0010\u001b\u001a\u00020\u0010H\u0016J\u0010\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0014H\u0016J\u0010\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0014H\u0016J\u001f\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0014H\u0016¢\u0006\u0002\u0010\"JN\u0010#\u001a\u00020\u00102\b\u0010$\u001a\u0004\u0018\u00010 2\b\u0010%\u001a\u0004\u0018\u00010 2\b\u0010&\u001a\u0004\u0018\u00010 2\b\u0010'\u001a\u0004\u0018\u00010 2\b\u0010(\u001a\u0004\u0018\u00010)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020)0+H\u0016J'\u0010,\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010-\u001a\u00020\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u0014H\u0016¢\u0006\u0002\u0010.J\u0010\u0010/\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0014H\u0016J\u0010\u00100\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0014H\u0016J\u0010\u00101\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0014H\u0016J\b\u00102\u001a\u00020\u0010H\u0016J\u0010\u00103\u001a\u00020\u00102\u0006\u00104\u001a\u00020)H\u0016J\u001c\u00103\u001a\u00020\u00102\b\u00105\u001a\u0004\u0018\u00010 2\b\u00104\u001a\u0004\u0018\u00010)H\u0016J\u000e\u00106\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u0004J\u000e\u00108\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u0006J\u001a\u00109\u001a\u00020\u00102\u0006\u00105\u001a\u00020 2\b\u00104\u001a\u0004\u0018\u00010)H\u0016R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/dotscreen/farkit/analytics/AnalyticsManager;", "Lcom/dotscreen/farkit/analytics/PlaybackAnalyticsListener;", "Lcom/dotscreen/farkit/analytics/LivePlaybackAnalyticsListener;", "module", "Lcom/dotscreen/farkit/analytics/AnalyticsModule;", "liveModule", "Lcom/dotscreen/farkit/analytics/LiveAnalyticsModule;", "(Lcom/dotscreen/farkit/analytics/AnalyticsModule;Lcom/dotscreen/farkit/analytics/LiveAnalyticsModule;)V", "liveModules", "", "modules", "playbackStart", "", "preBufferingTime", "", "blockNextSeek", "", "block", "onBuffering", "percent", "", "onBufferingStateChanged", "start", "onClose", "currentPosition", "onCloseLiveStream", "onCurrentPositionChanged", "onOpenLiveStream", "onPause", "onPlaybackEnd", "onPlaybackError", "reason", "", "code", "(Ljava/lang/String;Ljava/lang/Integer;)V", "onPlaybackRequested", "contentId", ToolboxAnalyticsModuleKt.KEY_PLAY_BACK_URL, "mimeType", ToolboxAnalyticsModuleKt.KEY_DRM_TYPE, ToolboxAnalyticsModuleKt.KEY_CONTENT, "", "extras", "", "onPlaybackStart", "duration", "(IILjava/lang/Integer;)V", "onResume", "onSeekEnded", "onSeekStarted", "onStartLivePlayback", "onZap", "channel", "channelId", "registerAnalyticsModule", "newModule", "registerLiveAnalyticsModule", "setChannel", "farkit_release"}, k = 1, mv = {1, 1, 16})
public final class AnalyticsManager implements PlaybackAnalyticsListener, LivePlaybackAnalyticsListener {
    private final List<LiveAnalyticsModule> liveModules;
    private final List<AnalyticsModule> modules;
    private boolean playbackStart;
    private long preBufferingTime;

    /* JADX WARN: Multi-variable type inference failed */
    public AnalyticsManager() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public AnalyticsManager(AnalyticsModule analyticsModule, LiveAnalyticsModule liveAnalyticsModule) {
        this.modules = new ArrayList();
        this.liveModules = new ArrayList();
        this.preBufferingTime = -1L;
        if (analyticsModule != null) {
            this.modules.add(analyticsModule);
        }
        if (liveAnalyticsModule != null) {
            this.liveModules.add(liveAnalyticsModule);
        }
    }

    public /* synthetic */ AnalyticsManager(AnalyticsModule analyticsModule, LiveAnalyticsModule liveAnalyticsModule, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (AnalyticsModule) null : analyticsModule, (i & 2) != 0 ? (LiveAnalyticsModule) null : liveAnalyticsModule);
    }

    public final void registerAnalyticsModule(AnalyticsModule newModule) {
        Intrinsics.checkParameterIsNotNull(newModule, "newModule");
        this.modules.add(newModule);
    }

    public final void registerLiveAnalyticsModule(LiveAnalyticsModule newModule) {
        Intrinsics.checkParameterIsNotNull(newModule, "newModule");
        this.liveModules.add(newModule);
    }

    public final void blockNextSeek(boolean block) {
        Iterator<T> it = this.modules.iterator();
        while (it.hasNext()) {
            ((AnalyticsModule) it.next()).setBlockNextSeek(block);
        }
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPlaybackRequested(String contentId, String playbackUrl, String mimeType, String drmType, Object content, Map<String, ? extends Object> extras) {
        Intrinsics.checkParameterIsNotNull(extras, "extras");
        this.preBufferingTime = new Date().getTime();
        Iterator<T> it = this.modules.iterator();
        while (it.hasNext()) {
            ((AnalyticsModule) it.next()).onPlaybackRequested(contentId, playbackUrl, mimeType, drmType, content, extras);
        }
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPlaybackStart(int currentPosition, int duration, Integer preBufferingTime) {
        this.preBufferingTime = new Date().getTime() - this.preBufferingTime;
        this.playbackStart = true;
        Iterator<T> it = this.modules.iterator();
        while (it.hasNext()) {
            ((AnalyticsModule) it.next()).onPlaybackStart(currentPosition, duration, Integer.valueOf((int) this.preBufferingTime));
        }
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPause(int currentPosition) {
        Iterator<T> it = this.modules.iterator();
        while (it.hasNext()) {
            ((AnalyticsModule) it.next()).onPause(currentPosition);
        }
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onResume(int currentPosition) {
        if (this.playbackStart) {
            Iterator<T> it = this.modules.iterator();
            while (it.hasNext()) {
                ((AnalyticsModule) it.next()).onResume(currentPosition);
            }
        }
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onSeekStarted(int currentPosition) {
        Iterator<T> it = this.modules.iterator();
        while (it.hasNext()) {
            ((AnalyticsModule) it.next()).onSeekStarted(currentPosition);
        }
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onSeekEnded(int currentPosition) {
        Iterator<T> it = this.modules.iterator();
        while (it.hasNext()) {
            ((AnalyticsModule) it.next()).onSeekEnded(currentPosition);
        }
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onBufferingStateChanged(boolean start) {
        Iterator<T> it = this.modules.iterator();
        while (it.hasNext()) {
            ((AnalyticsModule) it.next()).onBufferingStateChanged(start);
        }
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onBuffering(int percent) {
        Iterator<T> it = this.modules.iterator();
        while (it.hasNext()) {
            ((AnalyticsModule) it.next()).onBuffering(percent);
        }
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPlaybackError(String reason, Integer code) {
        Intrinsics.checkParameterIsNotNull(reason, "reason");
        Iterator<T> it = this.modules.iterator();
        while (it.hasNext()) {
            ((AnalyticsModule) it.next()).onPlaybackError(reason, code);
        }
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPlaybackEnd(int currentPosition) {
        Iterator<T> it = this.modules.iterator();
        while (it.hasNext()) {
            ((AnalyticsModule) it.next()).onPlaybackEnd(currentPosition);
        }
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onClose(int currentPosition) {
        Iterator<T> it = this.modules.iterator();
        while (it.hasNext()) {
            ((AnalyticsModule) it.next()).onClose(currentPosition);
        }
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onCurrentPositionChanged(long currentPosition) {
        Iterator<T> it = this.modules.iterator();
        while (it.hasNext()) {
            ((AnalyticsModule) it.next()).onCurrentPositionChanged(currentPosition);
        }
    }

    @Override // com.dotscreen.farkit.analytics.LivePlaybackAnalyticsListener
    public void setChannel(String channelId, Object channel) {
        Intrinsics.checkParameterIsNotNull(channelId, "channelId");
        Iterator<T> it = this.liveModules.iterator();
        while (it.hasNext()) {
            ((LiveAnalyticsModule) it.next()).setChannel(channelId, channel);
        }
    }

    @Override // com.dotscreen.farkit.analytics.LivePlaybackAnalyticsListener
    public void onZap(Object channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Iterator<T> it = this.liveModules.iterator();
        while (it.hasNext()) {
            ((LiveAnalyticsModule) it.next()).onZap(channel);
        }
    }

    @Override // com.dotscreen.farkit.analytics.LivePlaybackAnalyticsListener
    public void onZap(String channelId, Object channel) {
        Iterator<T> it = this.liveModules.iterator();
        while (it.hasNext()) {
            ((LiveAnalyticsModule) it.next()).onZap(channelId, channel);
        }
    }

    @Override // com.dotscreen.farkit.analytics.LivePlaybackAnalyticsListener
    public void onOpenLiveStream() {
        Iterator<T> it = this.liveModules.iterator();
        while (it.hasNext()) {
            ((LiveAnalyticsModule) it.next()).onOpenLiveStream();
        }
    }

    @Override // com.dotscreen.farkit.analytics.LivePlaybackAnalyticsListener
    public void onStartLivePlayback() {
        Iterator<T> it = this.liveModules.iterator();
        while (it.hasNext()) {
            ((LiveAnalyticsModule) it.next()).onStartLivePlayback();
        }
    }

    @Override // com.dotscreen.farkit.analytics.LivePlaybackAnalyticsListener
    public void onCloseLiveStream() {
        Iterator<T> it = this.liveModules.iterator();
        while (it.hasNext()) {
            ((LiveAnalyticsModule) it.next()).onCloseLiveStream();
        }
    }
}
