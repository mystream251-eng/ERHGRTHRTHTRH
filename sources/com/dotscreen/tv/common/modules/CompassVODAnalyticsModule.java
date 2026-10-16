package com.dotscreen.tv.common.modules;

import android.content.Context;
import android.util.Log;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.services.VODCatalogService;
import com.dotscreen.farkit.analytics.GenericAnalyticsModule;
import com.dotscreen.tv.common.bokit.MegacableVODCatalogService;
import com.dotscreen.tv.common.utils.CommonUtils;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CompassVODAnalyticsModule.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000fH\u0016J\u0010\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u0016H\u0016J\u0010\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u000fH\u0016J\u0010\u0010\"\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u000fH\u0016J\u001f\u0010%\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020\u00062\b\u0010'\u001a\u0004\u0018\u00010\u000fH\u0016¢\u0006\u0002\u0010(JN\u0010)\u001a\u00020\u001c2\b\u0010*\u001a\u0004\u0018\u00010\u00062\b\u0010+\u001a\u0004\u0018\u00010\u00062\b\u0010,\u001a\u0004\u0018\u00010\u00062\b\u0010-\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010.2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020.00H\u0016J'\u00101\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u00102\u001a\u0004\u0018\u00010\u000fH\u0016¢\u0006\u0002\u00103J\u0010\u00104\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u000fH\u0016J\u0010\u00105\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u000fH\u0016J\u0010\u00106\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u000fH\u0016J\b\u00107\u001a\u00020\u001cH\u0002R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u00068"}, d2 = {"Lcom/dotscreen/tv/common/modules/CompassVODAnalyticsModule;", "Lcom/dotscreen/farkit/analytics/GenericAnalyticsModule;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "kotlin.jvm.PlatformType", ToolboxAnalyticsModuleKt.KEY_CONTENT, "Lcom/dotscreen/bokit/model/Media;", "getContent", "()Lcom/dotscreen/bokit/model/Media;", "setContent", "(Lcom/dotscreen/bokit/model/Media;)V", "duration", "", "getDuration", "()Ljava/lang/Integer;", "setDuration", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "emitted", "", "getEmitted", "()Z", "setEmitted", "(Z)V", "onBuffering", "", "percent", "onBufferingStateChanged", "start", "onClose", "currentPosition", "onCurrentPositionChanged", "", "onPause", "onPlaybackError", "reason", "code", "(Ljava/lang/String;Ljava/lang/Integer;)V", "onPlaybackRequested", "contentId", ToolboxAnalyticsModuleKt.KEY_PLAY_BACK_URL, "mimeType", ToolboxAnalyticsModuleKt.KEY_DRM_TYPE, "", "extras", "", "onPlaybackStart", "preBufferingTime", "(IILjava/lang/Integer;)V", "onResume", "onSeekEnded", "onSeekStarted", "postCompassEvent", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class CompassVODAnalyticsModule extends GenericAnalyticsModule {
    private final String TAG;
    private Media content;
    private final Context context;
    private Integer duration;
    private boolean emitted;

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onBuffering(int percent) {
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onBufferingStateChanged(boolean start) {
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onClose(int currentPosition) {
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPause(int currentPosition) {
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPlaybackError(String reason, Integer code) {
        Intrinsics.checkParameterIsNotNull(reason, "reason");
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onResume(int currentPosition) {
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onSeekEnded(int currentPosition) {
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onSeekStarted(int currentPosition) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CompassVODAnalyticsModule(Context context) {
        super(null, 1, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = CompassVODAnalyticsModule.class.getSimpleName();
    }

    public final Integer getDuration() {
        return this.duration;
    }

    public final void setDuration(Integer num) {
        this.duration = num;
    }

    public final Media getContent() {
        return this.content;
    }

    public final void setContent(Media media) {
        this.content = media;
    }

    public final boolean getEmitted() {
        return this.emitted;
    }

    public final void setEmitted(boolean z) {
        this.emitted = z;
    }

    @Override // com.dotscreen.farkit.analytics.GenericAnalyticsModule, com.dotscreen.farkit.analytics.AnalyticsModule, com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPlaybackRequested(String contentId, String playbackUrl, String mimeType, String drmType, Object content, Map<String, ? extends Object> extras) {
        Intrinsics.checkParameterIsNotNull(extras, "extras");
        super.onPlaybackRequested(contentId, playbackUrl, mimeType, drmType, content, extras);
        if (content instanceof Media) {
            this.content = (Media) content;
            return;
        }
        if (content != null) {
            Log.e(this.TAG, "Content is " + content.getClass().getSimpleName());
            return;
        }
        Log.e(this.TAG, "Content is null");
    }

    @Override // com.dotscreen.farkit.analytics.AnalyticsModule, com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPlaybackStart(int currentPosition, int duration, Integer preBufferingTime) {
        this.emitted = false;
        this.duration = Integer.valueOf(duration);
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onCurrentPositionChanged(long currentPosition) {
        Integer num = this.duration;
        if (num != null) {
            if (num == null) {
                Intrinsics.throwNpe();
            }
            if (currentPosition >= ((long) (0.95d * ((double) num.intValue())))) {
                postCompassEvent();
            }
        }
    }

    private final void postCompassEvent() {
        if (this.emitted) {
            return;
        }
        VODCatalogService vodCatalogService = CommonUtils.getBOService(this.context).getVodCatalogService();
        if (vodCatalogService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableVODCatalogService");
        }
        MegacableVODCatalogService megacableVODCatalogService = (MegacableVODCatalogService) vodCatalogService;
        Media media = this.content;
        if (media == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.bokit.model.BrowsableItem");
        }
        MegacableVODCatalogService.registerClick$default(megacableVODCatalogService, media, "content_end", null, null, 12, null).subscribe(new Action() { // from class: com.dotscreen.tv.common.modules.CompassVODAnalyticsModule.postCompassEvent.1
            @Override // io.reactivex.functions.Action
            public final void run() {
                Log.d(CompassVODAnalyticsModule.this.TAG, "registerLiveEvent => Emitted");
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.modules.CompassVODAnalyticsModule.postCompassEvent.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.w(CompassVODAnalyticsModule.this.TAG, "registerLiveEvent => Error: " + th);
            }
        });
        this.emitted = true;
    }
}
