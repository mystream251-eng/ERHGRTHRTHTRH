package com.dotscreen.portal.utils.analytics;

import android.content.Context;
import android.util.Log;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.farkit.analytics.GenericAnalyticsModule;
import com.dotscreen.farkit.network.RequestManager;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.bokit.CatchupMedia;
import com.dotscreen.tv.common.bokit.NPVRMedia;
import com.dotscreen.tv.common.bokit.StartOverMedia;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.utils.DCAInitComponentKt;
import com.viaccessorca.analytics.dca.VoDcaEvent;
import com.viaccessorca.voplayer.VOPlaybackSessionReport;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DCAAnalyticsModule.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\rH\u0016J\u0010\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0018H\u0016J\u0010\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0018H\u0016J\u0010\u0010 \u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0018H\u0016J\u001f\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\b2\b\u0010#\u001a\u0004\u0018\u00010\u0018H\u0016¢\u0006\u0002\u0010$JN\u0010%\u001a\u00020\u00162\b\u0010&\u001a\u0004\u0018\u00010\b2\b\u0010'\u001a\u0004\u0018\u00010\b2\b\u0010(\u001a\u0004\u0018\u00010\b2\b\u0010)\u001a\u0004\u0018\u00010\b2\b\u0010*\u001a\u0004\u0018\u00010+2\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020+0-H\u0016J'\u0010.\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u00182\u0006\u0010/\u001a\u00020\u00182\b\u00100\u001a\u0004\u0018\u00010\u0018H\u0016¢\u0006\u0002\u00101J\u0010\u00102\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0018H\u0016J\u0010\u00103\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0018H\u0016J\u0010\u00104\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0018H\u0016R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011¨\u00065"}, d2 = {"Lcom/dotscreen/portal/utils/analytics/DCAAnalyticsModule;", "Lcom/dotscreen/farkit/analytics/GenericAnalyticsModule;", "context", "Landroid/content/Context;", "requestManager", "Lcom/dotscreen/farkit/network/RequestManager;", "(Landroid/content/Context;Lcom/dotscreen/farkit/network/RequestManager;)V", "TAG", "", "kotlin.jvm.PlatformType", "getContext", "()Landroid/content/Context;", "seekInProgress", "", "getSeekInProgress", "()Z", "setSeekInProgress", "(Z)V", "supportedContent", "getSupportedContent", "setSupportedContent", "onBuffering", "", "percent", "", "onBufferingStateChanged", "start", "onClose", "currentPosition", "onCurrentPositionChanged", "", "onPause", "onPlaybackEnd", "onPlaybackError", "reason", "code", "(Ljava/lang/String;Ljava/lang/Integer;)V", "onPlaybackRequested", "contentId", ToolboxAnalyticsModuleKt.KEY_PLAY_BACK_URL, "mimeType", ToolboxAnalyticsModuleKt.KEY_DRM_TYPE, ToolboxAnalyticsModuleKt.KEY_CONTENT, "", "extras", "", "onPlaybackStart", "duration", "preBufferingTime", "(IILjava/lang/Integer;)V", "onResume", "onSeekEnded", "onSeekStarted", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class DCAAnalyticsModule extends GenericAnalyticsModule {
    private final String TAG;
    private final Context context;
    private boolean seekInProgress;
    private boolean supportedContent;

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onBuffering(int percent) {
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onBufferingStateChanged(boolean start) {
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onCurrentPositionChanged(long currentPosition) {
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPlaybackError(String reason, Integer code) {
        Intrinsics.checkParameterIsNotNull(reason, "reason");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DCAAnalyticsModule(Context context, RequestManager requestManager) {
        super(requestManager);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(requestManager, "requestManager");
        this.context = context;
        this.TAG = DCAAnalyticsModule.class.getSimpleName();
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean getSupportedContent() {
        return this.supportedContent;
    }

    public final void setSupportedContent(boolean z) {
        this.supportedContent = z;
    }

    public final boolean getSeekInProgress() {
        return this.seekInProgress;
    }

    public final void setSeekInProgress(boolean z) {
        this.seekInProgress = z;
    }

    @Override // com.dotscreen.farkit.analytics.GenericAnalyticsModule, com.dotscreen.farkit.analytics.AnalyticsModule, com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPlaybackRequested(String contentId, String playbackUrl, String mimeType, String drmType, Object content, Map<String, ? extends Object> extras) {
        Intrinsics.checkParameterIsNotNull(extras, "extras");
        super.onPlaybackRequested(contentId, playbackUrl, mimeType, drmType, content, extras);
        if (content == null || !(content instanceof Media)) {
            return;
        }
        if (content instanceof StartOverMedia) {
            StartOverMedia startOverMedia = (StartOverMedia) content;
            this.supportedContent = DCAInitComponentKt.submitContent(this, "STARTOVER", startOverMedia.getChannelExternalId(), startOverMedia.getProgramExternalId(), new Function0<Unit>() { // from class: com.dotscreen.portal.utils.analytics.DCAAnalyticsModule.onPlaybackRequested.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Context context = DCAAnalyticsModule.this.getContext();
                    if (context == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                    }
                    PortalApplication.initDcaLib$default((PortalApplication) context, null, 1, null);
                }
            });
            return;
        }
        if (content instanceof CatchupMedia) {
            CatchupMedia catchupMedia = (CatchupMedia) content;
            this.supportedContent = DCAInitComponentKt.submitContent(this, "CATCHUP", catchupMedia.getChannelExternalId(), catchupMedia.getProgramExternalId(), new Function0<Unit>() { // from class: com.dotscreen.portal.utils.analytics.DCAAnalyticsModule.onPlaybackRequested.2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Context context = DCAAnalyticsModule.this.getContext();
                    if (context == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                    }
                    PortalApplication.initDcaLib$default((PortalApplication) context, null, 1, null);
                }
            });
            return;
        }
        boolean z = false;
        if (content instanceof NPVRMedia) {
            try {
                DCAInitComponentKt.submitEvent(this, VoDcaEvent.EventType.OPEN, VoDcaEvent.EventKey.CONTENT_TYPE, "NPVR");
            } catch (Exception unused) {
                Context context = this.context;
                if (context == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                }
                PortalApplication.initDcaLib$default((PortalApplication) context, null, 1, null);
            }
            this.supportedContent = false;
            return;
        }
        try {
            DCAInitComponentKt.submitEvent(this, VoDcaEvent.EventType.OPEN, VoDcaEvent.EventKey.CONTENT_TYPE, VOPlaybackSessionReport.SESSION_TYPE_VOD);
            z = true;
        } catch (Exception unused2) {
            Context context2 = this.context;
            if (context2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            PortalApplication.initDcaLib$default((PortalApplication) context2, null, 1, null);
        }
        this.supportedContent = z;
    }

    @Override // com.dotscreen.farkit.analytics.AnalyticsModule, com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPlaybackStart(int currentPosition, int duration, Integer preBufferingTime) {
        if (this.supportedContent) {
            try {
                DCAInitComponentKt.submitEvent(this, VoDcaEvent.EventType.PLAY, VoDcaEvent.EventKey.PROGRAM_OFFSET, String.valueOf(currentPosition));
            } catch (Exception unused) {
                this.supportedContent = false;
            }
        }
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPause(int currentPosition) {
        Log.v(this.TAG, "DCA - eventPause called");
        if (!this.supportedContent || this.seekInProgress) {
            return;
        }
        try {
            Log.v(this.TAG, "DCA - eventPause emitted");
            DCAInitComponentKt.submitEvent(this, VoDcaEvent.EventType.PAUSE, VoDcaEvent.EventKey.PROGRAM_OFFSET, String.valueOf(currentPosition));
        } catch (Exception unused) {
            this.supportedContent = false;
        }
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onResume(int currentPosition) {
        if (this.supportedContent) {
            try {
                DCAInitComponentKt.submitEvent(this, VoDcaEvent.EventType.RESUME, VoDcaEvent.EventKey.PROGRAM_OFFSET, String.valueOf(currentPosition));
            } catch (Exception unused) {
                this.supportedContent = false;
            }
        }
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onSeekStarted(int currentPosition) {
        Log.v(this.TAG, "DCA - eventSeekStart called");
        if (this.supportedContent) {
            this.seekInProgress = true;
            try {
                Log.v(this.TAG, "DCA - eventSeekStart emitted");
                DCAInitComponentKt.submitEvent(this, VoDcaEvent.EventType.SEEK_START, VoDcaEvent.EventKey.PROGRAM_OFFSET, String.valueOf(currentPosition));
            } catch (Exception unused) {
                this.supportedContent = false;
            }
        }
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onSeekEnded(int currentPosition) {
        Log.v(this.TAG, "DCA - eventSeekEnded called");
        if (this.supportedContent) {
            try {
                Log.v(this.TAG, "DCA - eventSeekEnded emitted");
                this.seekInProgress = false;
                DCAInitComponentKt.submitEvent(this, VoDcaEvent.EventType.SEEK_END, VoDcaEvent.EventKey.PROGRAM_OFFSET, String.valueOf(currentPosition));
            } catch (Exception unused) {
                this.supportedContent = false;
            }
        }
    }

    @Override // com.dotscreen.farkit.analytics.GenericAnalyticsModule, com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPlaybackEnd(int currentPosition) {
        if (this.supportedContent) {
            try {
                DCAInitComponentKt.submitEvent(this, VoDcaEvent.EventType.STOP, VoDcaEvent.EventKey.PROGRAM_OFFSET, String.valueOf(currentPosition));
            } catch (Exception unused) {
                this.supportedContent = false;
            }
        }
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onClose(int currentPosition) {
        if (this.supportedContent) {
            DCAInitComponentKt.submitEvent(this, VoDcaEvent.EventType.STOP, VoDcaEvent.EventKey.PROGRAM_OFFSET, String.valueOf(currentPosition));
        }
    }
}
