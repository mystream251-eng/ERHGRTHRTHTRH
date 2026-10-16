package com.dotscreen.tv.common.modules;

import android.os.Handler;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.tvprovider.media.tv.TvContractCompat;
import androidx.work.PeriodicWorkRequest;
import com.dotscreen.farkit.analytics.GenericAnalyticsModule;
import com.dotscreen.farkit.network.RequestManager;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.bokit.MegacableUtilsKt;
import com.dotscreen.tv.common.bokit.RightVRequestManager;
import com.dotscreen.tv.common.bokit.RightvContentIdData;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Function;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: ToolboxAnalyticsModule.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u00017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000bH\u0016J\u0010\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0012H\u0016J\u0010\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u000bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u000bH\u0016J\u001f\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\b2\b\u0010!\u001a\u0004\u0018\u00010\u000bH\u0016¢\u0006\u0002\u0010\"JN\u0010#\u001a\u00020\u00162\b\u0010$\u001a\u0004\u0018\u00010\b2\b\u0010%\u001a\u0004\u0018\u00010\b2\b\u0010&\u001a\u0004\u0018\u00010\b2\b\u0010'\u001a\u0004\u0018\u00010\b2\b\u0010(\u001a\u0004\u0018\u00010)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020)0+H\u0016J'\u0010,\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010-\u001a\u0004\u0018\u00010\u000bH\u0016¢\u0006\u0002\u0010.J\u0010\u0010/\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u000bH\u0016J\u0010\u00100\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u000bH\u0016J\u0010\u00101\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u000bH\u0016J&\u00102\u001a\u00020\u00162\u0006\u00103\u001a\u0002042\u0014\b\u0002\u00105\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020)06H\u0002R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u00068"}, d2 = {"Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule;", "Lcom/dotscreen/farkit/analytics/GenericAnalyticsModule;", "requestManager", "Lcom/dotscreen/tv/common/bokit/RightVRequestManager;", "userService", "Lcom/dotscreen/tv/common/bokit/MegacableUserService;", "(Lcom/dotscreen/tv/common/bokit/RightVRequestManager;Lcom/dotscreen/tv/common/bokit/MegacableUserService;)V", "TAG", "", "kotlin.jvm.PlatformType", "duration", "", "Ljava/lang/Integer;", "mHandler", "Landroid/os/Handler;", "mRunnable", "Ljava/lang/Runnable;", "toolboxContent", "", "getUserService", "()Lcom/dotscreen/tv/common/bokit/MegacableUserService;", "onBuffering", "", "percent", "onBufferingStateChanged", "start", "onClose", "currentPosition", "onCurrentPositionChanged", "", "onPause", "onPlaybackError", "reason", "code", "(Ljava/lang/String;Ljava/lang/Integer;)V", "onPlaybackRequested", "contentId", ToolboxAnalyticsModuleKt.KEY_PLAY_BACK_URL, "mimeType", ToolboxAnalyticsModuleKt.KEY_DRM_TYPE, ToolboxAnalyticsModuleKt.KEY_CONTENT, "", "extras", "", "onPlaybackStart", "preBufferingTime", "(IILjava/lang/Integer;)V", "onResume", "onSeekEnded", "onSeekStarted", "postToolboxEvents", NotificationCompat.CATEGORY_EVENT, "Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents;", "params", "", "ToolboxEvents", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ToolboxAnalyticsModule extends GenericAnalyticsModule {
    private final String TAG;
    private Integer duration;
    private Handler mHandler;
    private Runnable mRunnable;
    private boolean toolboxContent;
    private final MegacableUserService userService;

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onBuffering(int percent) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ToolboxAnalyticsModule(RightVRequestManager requestManager, MegacableUserService userService) {
        super(requestManager);
        Intrinsics.checkParameterIsNotNull(requestManager, "requestManager");
        Intrinsics.checkParameterIsNotNull(userService, "userService");
        this.userService = userService;
        this.TAG = ToolboxAnalyticsModule.class.getSimpleName();
    }

    public static final /* synthetic */ Runnable access$getMRunnable$p(ToolboxAnalyticsModule toolboxAnalyticsModule) {
        Runnable runnable = toolboxAnalyticsModule.mRunnable;
        if (runnable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRunnable");
        }
        return runnable;
    }

    public final MegacableUserService getUserService() {
        return this.userService;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x002a  */
    @Override // com.dotscreen.farkit.analytics.GenericAnalyticsModule, com.dotscreen.farkit.analytics.AnalyticsModule, com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPlaybackRequested(String contentId, String playbackUrl, String mimeType, String drmType, Object content, Map<String, ? extends Object> extras) {
        Intrinsics.checkParameterIsNotNull(extras, "extras");
        super.onPlaybackRequested(contentId, playbackUrl, mimeType, drmType, content, extras);
        if (contentId != null) {
            if (contentId.length() > 0) {
                ToolboxEvents.Companion companion = ToolboxEvents.INSTANCE;
                RightvContentIdData rightvContentIdDataExtractContentId = MegacableUtilsKt.extractContentId(contentId);
                companion.setContentId(rightvContentIdDataExtractContentId != null ? rightvContentIdDataExtractContentId.getContentId() : null);
            } else {
                ToolboxEvents.INSTANCE.setContentId(contentId);
            }
        } else {
            ToolboxEvents.INSTANCE.setContentId(contentId);
        }
        ToolboxEvents.INSTANCE.setMimeType(mimeType);
        ToolboxEvents.INSTANCE.setPlaybackUrl(playbackUrl);
        ToolboxEvents.INSTANCE.setDrmType(drmType);
        ToolboxEvents.INSTANCE.setStartTime(Long.valueOf(new Date().getTime()));
        if (extras.containsKey("toolboxContent")) {
            Object obj = extras.get("toolboxContent");
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
            }
            this.toolboxContent = ((Boolean) obj).booleanValue();
        }
        if (extras.containsKey("hbo")) {
            ToolboxEvents.Companion companion2 = ToolboxEvents.INSTANCE;
            Object obj2 = extras.get("hbo");
            if (obj2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
            }
            companion2.setHboToken((String) obj2);
        }
        this.mHandler = new Handler();
        Runnable runnable = new Runnable() { // from class: com.dotscreen.tv.common.modules.ToolboxAnalyticsModule.onPlaybackRequested.1
            @Override // java.lang.Runnable
            public final void run() {
                ToolboxAnalyticsModule.postToolboxEvents$default(ToolboxAnalyticsModule.this, ToolboxEvents.STREAMING_PROGRESS, null, 2, null);
                Handler handler = ToolboxAnalyticsModule.this.mHandler;
                if (handler != null) {
                    handler.postDelayed(ToolboxAnalyticsModule.access$getMRunnable$p(ToolboxAnalyticsModule.this), PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS);
                }
            }
        };
        this.mRunnable = runnable;
        Handler handler = this.mHandler;
        if (handler != null) {
            if (runnable == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mRunnable");
            }
            handler.postDelayed(runnable, PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS);
        }
    }

    @Override // com.dotscreen.farkit.analytics.AnalyticsModule, com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPlaybackStart(int currentPosition, int duration, Integer preBufferingTime) {
        this.duration = Integer.valueOf(duration);
        ToolboxEvents.INSTANCE.setDuration(Integer.valueOf(duration));
        ToolboxEvents.INSTANCE.setPreBufferingTime(preBufferingTime);
        postToolboxEvents$default(this, ToolboxEvents.START, null, 2, null);
        postToolboxEvents$default(this, ToolboxEvents.START_BUFFERING, null, 2, null);
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPause(int currentPosition) {
        postToolboxEvents$default(this, ToolboxEvents.PAUSE, null, 2, null);
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onResume(int currentPosition) {
        postToolboxEvents$default(this, ToolboxEvents.RESUME, null, 2, null);
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onSeekStarted(int currentPosition) {
        ToolboxEvents.INSTANCE.setScrubbing(true);
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onSeekEnded(int currentPosition) {
        ToolboxEvents.INSTANCE.setScrubbing(false);
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onBufferingStateChanged(boolean start) {
        if (start) {
            ToolboxEvents.INSTANCE.setBufferStart(ToolboxEvents.INSTANCE.getPosition());
            ToolboxEvents.INSTANCE.setBufferingDuration(Long.valueOf(new Date().getTime()));
            return;
        }
        if (ToolboxEvents.INSTANCE.getBufferStart() != null) {
            ToolboxEvents.Companion companion = ToolboxEvents.INSTANCE;
            long time = new Date().getTime();
            Long bufferingDuration = ToolboxEvents.INSTANCE.getBufferingDuration();
            companion.setBufferingDuration(Long.valueOf(time - (bufferingDuration != null ? bufferingDuration.longValue() : new Date().getTime())));
            ToolboxEvents.Companion companion2 = ToolboxEvents.INSTANCE;
            companion2.setBufferCount(companion2.getBufferCount() + 1);
            postToolboxEvents$default(this, ToolboxEvents.RE_BUFFERING, null, 2, null);
            ToolboxEvents.INSTANCE.setBufferStart((Integer) null);
            ToolboxEvents.INSTANCE.setBufferingDuration((Long) null);
        }
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onPlaybackError(String reason, Integer code) {
        Intrinsics.checkParameterIsNotNull(reason, "reason");
        ToolboxEvents toolboxEvents = ToolboxEvents.PLAYBACKERROR;
        Pair[] pairArr = new Pair[2];
        pairArr[0] = new Pair("code", String.valueOf(code != null ? code.intValue() : -1));
        pairArr[1] = new Pair(TvContractCompat.Channels.COLUMN_DESCRIPTION, reason);
        postToolboxEvents(toolboxEvents, MapsKt.mutableMapOf(pairArr));
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onClose(int currentPosition) {
        Handler handler = this.mHandler;
        if (handler != null) {
            if (handler == null) {
                Intrinsics.throwNpe();
            }
            Runnable runnable = this.mRunnable;
            if (runnable == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mRunnable");
            }
            handler.removeCallbacks(runnable);
            postToolboxEvents$default(this, ToolboxEvents.CLOSE, null, 2, null);
        }
    }

    @Override // com.dotscreen.farkit.analytics.PlaybackAnalyticsListener
    public void onCurrentPositionChanged(long currentPosition) {
        ToolboxEvents.INSTANCE.setPosition(Integer.valueOf((int) currentPosition));
        Integer num = this.duration;
        if (num != null) {
            if (num == null) {
                Intrinsics.throwNpe();
            }
            if (currentPosition == ((long) (0.99d * ((double) num.intValue())))) {
                postToolboxEvents$default(this, ToolboxEvents.ENDED, null, 2, null);
                return;
            }
            Integer num2 = this.duration;
            if (num2 == null) {
                Intrinsics.throwNpe();
            }
            if (currentPosition == ((long) (0.95d * ((double) num2.intValue())))) {
                postToolboxEvents$default(this, ToolboxEvents.COMPLETE, null, 2, null);
                return;
            }
            Integer num3 = this.duration;
            if (num3 == null) {
                Intrinsics.throwNpe();
            }
            if (currentPosition == ((long) (0.75d * ((double) num3.intValue())))) {
                postToolboxEvents$default(this, ToolboxEvents.THIRDQUARTILE, null, 2, null);
                return;
            }
            Integer num4 = this.duration;
            if (num4 == null) {
                Intrinsics.throwNpe();
            }
            if (currentPosition == ((long) (0.5d * ((double) num4.intValue())))) {
                postToolboxEvents$default(this, ToolboxEvents.MIDPOINT, null, 2, null);
                return;
            }
            Integer num5 = this.duration;
            if (num5 == null) {
                Intrinsics.throwNpe();
            }
            if (currentPosition == ((long) (0.25d * ((double) num5.intValue())))) {
                postToolboxEvents$default(this, ToolboxEvents.FIRSTQUARTILE, null, 2, null);
                return;
            }
            Integer num6 = this.duration;
            if (num6 == null) {
                Intrinsics.throwNpe();
            }
            if (currentPosition == ((long) (0.1d * ((double) num6.intValue())))) {
                postToolboxEvents$default(this, ToolboxEvents.PROGRESSMARK, null, 2, null);
            }
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: ToolboxAnalyticsModule.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\b\u0017\b\u0086\u0001\u0018\u0000 \u001e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004J\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\bJ&\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\n\u001a\u00020\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006J(\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\bH&J\u0014\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\bj\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001d¨\u0006\u001f"}, d2 = {"Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents;", "", "(Ljava/lang/String;I)V", "commonParams", "", "", "", "contentParams", "", "eventMap", "eventName", "value", "getEventsParams", "args", "playbackParams", "LOGIN", "START", "CLOSE", "PAUSE", "RESUME", "PROGRESSMARK", "FIRSTQUARTILE", "MIDPOINT", "THIRDQUARTILE", "COMPLETE", "ENDED", "START_BUFFERING", "RE_BUFFERING", "PLAYBACKERROR", "STREAMING_PROGRESS", "Companion", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class ToolboxEvents {
        private static final /* synthetic */ ToolboxEvents[] $VALUES;
        public static final ToolboxEvents CLOSE;
        public static final ToolboxEvents COMPLETE;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE;
        public static final ToolboxEvents ENDED;
        public static final ToolboxEvents FIRSTQUARTILE;
        public static final ToolboxEvents LOGIN;
        public static final ToolboxEvents MIDPOINT;
        public static final ToolboxEvents PAUSE;
        public static final ToolboxEvents PLAYBACKERROR;
        public static final ToolboxEvents PROGRESSMARK;
        public static final ToolboxEvents RESUME;
        public static final ToolboxEvents RE_BUFFERING;
        public static final ToolboxEvents START;
        public static final ToolboxEvents START_BUFFERING;
        public static final ToolboxEvents STREAMING_PROGRESS;
        public static final ToolboxEvents THIRDQUARTILE;
        private static Integer bitRate;
        private static int bufferCount;
        private static Integer bufferStart;
        private static Long bufferingDuration;
        private static String contentId;
        private static String drmType;
        private static Integer duration;
        private static String hboToken;
        private static String mimeType;
        private static String playbackUrl;
        private static Integer position;
        private static Integer preBufferingTime;
        private static final Map<String, Integer> resolution;
        private static boolean scrubbing;
        private static Long startTime;
        private static Long timeSpent;

        public static ToolboxEvents valueOf(String str) {
            return (ToolboxEvents) Enum.valueOf(ToolboxEvents.class, str);
        }

        public static ToolboxEvents[] values() {
            return (ToolboxEvents[]) $VALUES.clone();
        }

        public abstract Map<String, Object> getEventsParams(Map<String, Object> args);

        /* JADX INFO: compiled from: ToolboxAnalyticsModule.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents$LOGIN;", "Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents;", "getEventsParams", "", "", "", "args", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
        static final class LOGIN extends ToolboxEvents {
            LOGIN(String str, int i) {
                super(str, i, null);
            }

            @Override // com.dotscreen.tv.common.modules.ToolboxAnalyticsModule.ToolboxEvents
            public Map<String, Object> getEventsParams(Map<String, Object> args) {
                Intrinsics.checkParameterIsNotNull(args, "args");
                Map<String, Object> mapMutableMapOf = MapsKt.mutableMapOf(new Pair(ToolboxAnalyticsModuleKt.KEY_EVENTS, CollectionsKt.listOf(ToolboxEvents.eventMap$default(this, ToolboxAnalyticsModuleKt.EVENT_LOGIN, null, 2, null))), new Pair(ToolboxAnalyticsModuleKt.KEY_USER, MapsKt.mapOf(new Pair(ToolboxAnalyticsModuleKt.EVENT_PROFILE_ID, args.get(ToolboxAnalyticsModuleKt.EVENT_PROFILE_ID)))));
                mapMutableMapOf.putAll(commonParams());
                return mapMutableMapOf;
            }
        }

        private ToolboxEvents(String str, int i) {
            super(str, i);
        }

        public /* synthetic */ ToolboxEvents(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, i);
        }

        /* JADX INFO: compiled from: ToolboxAnalyticsModule.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents$START;", "Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents;", "getEventsParams", "", "", "", "args", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
        static final class START extends ToolboxEvents {
            START(String str, int i) {
                super(str, i, null);
            }

            @Override // com.dotscreen.tv.common.modules.ToolboxAnalyticsModule.ToolboxEvents
            public Map<String, Object> getEventsParams(Map<String, Object> args) {
                Intrinsics.checkParameterIsNotNull(args, "args");
                Map<String, Object> mapMutableMapOf = MapsKt.mutableMapOf(new Pair(ToolboxAnalyticsModuleKt.KEY_CONTENT, contentParams()), new Pair(ToolboxAnalyticsModuleKt.KEY_EVENTS, CollectionsKt.listOf(ToolboxEvents.eventMap$default(this, "start", null, 2, null))));
                mapMutableMapOf.put(ToolboxAnalyticsModuleKt.KEY_PLAYBACK, playbackParams());
                mapMutableMapOf.putAll(commonParams());
                return mapMutableMapOf;
            }
        }

        /* JADX INFO: compiled from: ToolboxAnalyticsModule.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents$CLOSE;", "Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents;", "getEventsParams", "", "", "", "args", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
        static final class CLOSE extends ToolboxEvents {
            CLOSE(String str, int i) {
                super(str, i, null);
            }

            @Override // com.dotscreen.tv.common.modules.ToolboxAnalyticsModule.ToolboxEvents
            public Map<String, Object> getEventsParams(Map<String, Object> args) {
                Intrinsics.checkParameterIsNotNull(args, "args");
                Map<String, Object> mapMutableMapOf = MapsKt.mutableMapOf(new Pair(ToolboxAnalyticsModuleKt.KEY_CONTENT, contentParams()), new Pair(ToolboxAnalyticsModuleKt.KEY_EVENTS, CollectionsKt.listOf(ToolboxEvents.eventMap$default(this, ToolboxAnalyticsModuleKt.EVENT_CLOSE, null, 2, null))));
                mapMutableMapOf.put(ToolboxAnalyticsModuleKt.KEY_PLAYBACK, playbackParams());
                mapMutableMapOf.putAll(commonParams());
                return mapMutableMapOf;
            }
        }

        /* JADX INFO: compiled from: ToolboxAnalyticsModule.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents$PAUSE;", "Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents;", "getEventsParams", "", "", "", "args", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
        static final class PAUSE extends ToolboxEvents {
            PAUSE(String str, int i) {
                super(str, i, null);
            }

            @Override // com.dotscreen.tv.common.modules.ToolboxAnalyticsModule.ToolboxEvents
            public Map<String, Object> getEventsParams(Map<String, Object> args) {
                Intrinsics.checkParameterIsNotNull(args, "args");
                Map<String, Object> mapMutableMapOf = MapsKt.mutableMapOf(new Pair(ToolboxAnalyticsModuleKt.KEY_CONTENT, contentParams()), new Pair(ToolboxAnalyticsModuleKt.KEY_EVENTS, CollectionsKt.listOf(ToolboxEvents.eventMap$default(this, ToolboxAnalyticsModuleKt.EVENT_PAUSE, null, 2, null))));
                mapMutableMapOf.put(ToolboxAnalyticsModuleKt.KEY_PLAYBACK, playbackParams());
                mapMutableMapOf.putAll(commonParams());
                return mapMutableMapOf;
            }
        }

        /* JADX INFO: compiled from: ToolboxAnalyticsModule.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents$RESUME;", "Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents;", "getEventsParams", "", "", "", "args", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
        static final class RESUME extends ToolboxEvents {
            RESUME(String str, int i) {
                super(str, i, null);
            }

            @Override // com.dotscreen.tv.common.modules.ToolboxAnalyticsModule.ToolboxEvents
            public Map<String, Object> getEventsParams(Map<String, Object> args) {
                Intrinsics.checkParameterIsNotNull(args, "args");
                Map<String, Object> mapMutableMapOf = MapsKt.mutableMapOf(new Pair(ToolboxAnalyticsModuleKt.KEY_CONTENT, contentParams()), new Pair(ToolboxAnalyticsModuleKt.KEY_EVENTS, CollectionsKt.listOf(ToolboxEvents.eventMap$default(this, ToolboxAnalyticsModuleKt.EVENT_RESUME, null, 2, null))));
                mapMutableMapOf.putAll(commonParams());
                mapMutableMapOf.put(ToolboxAnalyticsModuleKt.KEY_PLAYBACK, playbackParams());
                return mapMutableMapOf;
            }
        }

        /* JADX INFO: compiled from: ToolboxAnalyticsModule.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents$PROGRESSMARK;", "Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents;", "getEventsParams", "", "", "", "args", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
        static final class PROGRESSMARK extends ToolboxEvents {
            PROGRESSMARK(String str, int i) {
                super(str, i, null);
            }

            @Override // com.dotscreen.tv.common.modules.ToolboxAnalyticsModule.ToolboxEvents
            public Map<String, Object> getEventsParams(Map<String, Object> args) {
                Intrinsics.checkParameterIsNotNull(args, "args");
                Map<String, Object> mapMutableMapOf = MapsKt.mutableMapOf(new Pair(ToolboxAnalyticsModuleKt.KEY_CONTENT, contentParams()), new Pair(ToolboxAnalyticsModuleKt.KEY_EVENTS, CollectionsKt.listOf(eventMap(ToolboxAnalyticsModuleKt.EVENT_PROGRESSMARK, 10))));
                mapMutableMapOf.put(ToolboxAnalyticsModuleKt.KEY_PLAYBACK, playbackParams());
                mapMutableMapOf.putAll(commonParams());
                return mapMutableMapOf;
            }
        }

        /* JADX INFO: compiled from: ToolboxAnalyticsModule.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents$FIRSTQUARTILE;", "Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents;", "getEventsParams", "", "", "", "args", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
        static final class FIRSTQUARTILE extends ToolboxEvents {
            FIRSTQUARTILE(String str, int i) {
                super(str, i, null);
            }

            @Override // com.dotscreen.tv.common.modules.ToolboxAnalyticsModule.ToolboxEvents
            public Map<String, Object> getEventsParams(Map<String, Object> args) {
                Intrinsics.checkParameterIsNotNull(args, "args");
                Map<String, Object> mapMutableMapOf = MapsKt.mutableMapOf(new Pair(ToolboxAnalyticsModuleKt.KEY_CONTENT, contentParams()), new Pair(ToolboxAnalyticsModuleKt.KEY_EVENTS, CollectionsKt.listOf(eventMap(ToolboxAnalyticsModuleKt.EVENT_FIRSTQUARTILE, 25))));
                mapMutableMapOf.put(ToolboxAnalyticsModuleKt.KEY_PLAYBACK, playbackParams());
                mapMutableMapOf.putAll(commonParams());
                return mapMutableMapOf;
            }
        }

        /* JADX INFO: compiled from: ToolboxAnalyticsModule.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents$MIDPOINT;", "Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents;", "getEventsParams", "", "", "", "args", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
        static final class MIDPOINT extends ToolboxEvents {
            MIDPOINT(String str, int i) {
                super(str, i, null);
            }

            @Override // com.dotscreen.tv.common.modules.ToolboxAnalyticsModule.ToolboxEvents
            public Map<String, Object> getEventsParams(Map<String, Object> args) {
                Intrinsics.checkParameterIsNotNull(args, "args");
                Map<String, Object> mapMutableMapOf = MapsKt.mutableMapOf(new Pair(ToolboxAnalyticsModuleKt.KEY_CONTENT, contentParams()), new Pair(ToolboxAnalyticsModuleKt.KEY_EVENTS, CollectionsKt.listOf(eventMap(ToolboxAnalyticsModuleKt.EVENT_MIDPOINT, 50))));
                mapMutableMapOf.put(ToolboxAnalyticsModuleKt.KEY_PLAYBACK, playbackParams());
                mapMutableMapOf.putAll(commonParams());
                return mapMutableMapOf;
            }
        }

        /* JADX INFO: compiled from: ToolboxAnalyticsModule.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents$THIRDQUARTILE;", "Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents;", "getEventsParams", "", "", "", "args", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
        static final class THIRDQUARTILE extends ToolboxEvents {
            THIRDQUARTILE(String str, int i) {
                super(str, i, null);
            }

            @Override // com.dotscreen.tv.common.modules.ToolboxAnalyticsModule.ToolboxEvents
            public Map<String, Object> getEventsParams(Map<String, Object> args) {
                Intrinsics.checkParameterIsNotNull(args, "args");
                Map<String, Object> mapMutableMapOf = MapsKt.mutableMapOf(new Pair(ToolboxAnalyticsModuleKt.KEY_CONTENT, contentParams()), new Pair(ToolboxAnalyticsModuleKt.KEY_EVENTS, CollectionsKt.listOf(eventMap(ToolboxAnalyticsModuleKt.EVENT_THIRDQUARTILE, 75))));
                mapMutableMapOf.put(ToolboxAnalyticsModuleKt.KEY_PLAYBACK, playbackParams());
                mapMutableMapOf.putAll(commonParams());
                return mapMutableMapOf;
            }
        }

        /* JADX INFO: compiled from: ToolboxAnalyticsModule.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents$COMPLETE;", "Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents;", "getEventsParams", "", "", "", "args", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
        static final class COMPLETE extends ToolboxEvents {
            COMPLETE(String str, int i) {
                super(str, i, null);
            }

            @Override // com.dotscreen.tv.common.modules.ToolboxAnalyticsModule.ToolboxEvents
            public Map<String, Object> getEventsParams(Map<String, Object> args) {
                Intrinsics.checkParameterIsNotNull(args, "args");
                Map<String, Object> mapMutableMapOf = MapsKt.mutableMapOf(new Pair(ToolboxAnalyticsModuleKt.KEY_CONTENT, contentParams()), new Pair(ToolboxAnalyticsModuleKt.KEY_EVENTS, CollectionsKt.listOf(eventMap("complete", 95))));
                mapMutableMapOf.put(ToolboxAnalyticsModuleKt.KEY_PLAYBACK, playbackParams());
                mapMutableMapOf.putAll(commonParams());
                return mapMutableMapOf;
            }
        }

        /* JADX INFO: compiled from: ToolboxAnalyticsModule.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents$ENDED;", "Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents;", "getEventsParams", "", "", "", "args", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
        static final class ENDED extends ToolboxEvents {
            ENDED(String str, int i) {
                super(str, i, null);
            }

            @Override // com.dotscreen.tv.common.modules.ToolboxAnalyticsModule.ToolboxEvents
            public Map<String, Object> getEventsParams(Map<String, Object> args) {
                Intrinsics.checkParameterIsNotNull(args, "args");
                Map<String, Object> mapMutableMapOf = MapsKt.mutableMapOf(new Pair(ToolboxAnalyticsModuleKt.KEY_CONTENT, contentParams()), new Pair(ToolboxAnalyticsModuleKt.KEY_EVENTS, CollectionsKt.listOf(eventMap(ToolboxAnalyticsModuleKt.EVENT_ENDED, 99))));
                mapMutableMapOf.put(ToolboxAnalyticsModuleKt.KEY_PLAYBACK, playbackParams());
                mapMutableMapOf.putAll(commonParams());
                return mapMutableMapOf;
            }
        }

        /* JADX INFO: compiled from: ToolboxAnalyticsModule.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents$START_BUFFERING;", "Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents;", "getEventsParams", "", "", "", "args", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
        static final class START_BUFFERING extends ToolboxEvents {
            START_BUFFERING(String str, int i) {
                super(str, i, null);
            }

            @Override // com.dotscreen.tv.common.modules.ToolboxAnalyticsModule.ToolboxEvents
            public Map<String, Object> getEventsParams(Map<String, Object> args) {
                Intrinsics.checkParameterIsNotNull(args, "args");
                Map<String, Object> mapMutableMapOf = MapsKt.mutableMapOf(new Pair(ToolboxAnalyticsModuleKt.KEY_CONTENT, contentParams()), new Pair(ToolboxAnalyticsModuleKt.KEY_EVENTS, CollectionsKt.listOf(ToolboxEvents.eventMap$default(this, ToolboxAnalyticsModuleKt.EVENT_START_BUFFERING, null, 2, null))));
                Integer preBufferingTime = ToolboxEvents.INSTANCE.getPreBufferingTime();
                mapMutableMapOf.put(ToolboxAnalyticsModuleKt.KEY_BUFFER_STATS, MapsKt.mapOf(new Pair("secondsToLoad", preBufferingTime != null ? Integer.valueOf(preBufferingTime.intValue() / 1000) : null)));
                mapMutableMapOf.put(ToolboxAnalyticsModuleKt.KEY_PLAYBACK, playbackParams());
                mapMutableMapOf.putAll(commonParams());
                return mapMutableMapOf;
            }
        }

        /* JADX INFO: compiled from: ToolboxAnalyticsModule.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents$RE_BUFFERING;", "Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents;", "getEventsParams", "", "", "", "args", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
        static final class RE_BUFFERING extends ToolboxEvents {
            RE_BUFFERING(String str, int i) {
                super(str, i, null);
            }

            @Override // com.dotscreen.tv.common.modules.ToolboxAnalyticsModule.ToolboxEvents
            public Map<String, Object> getEventsParams(Map<String, Object> args) {
                Intrinsics.checkParameterIsNotNull(args, "args");
                Map<String, Object> mapMutableMapOf = MapsKt.mutableMapOf(new Pair(ToolboxAnalyticsModuleKt.KEY_CONTENT, contentParams()), new Pair(ToolboxAnalyticsModuleKt.KEY_EVENTS, CollectionsKt.listOf(ToolboxEvents.eventMap$default(this, ToolboxAnalyticsModuleKt.EVENT_RE_BUFFERING, null, 2, null))));
                Pair[] pairArr = new Pair[5];
                Integer bufferStart = ToolboxEvents.INSTANCE.getBufferStart();
                pairArr[0] = new Pair("currentTime", bufferStart != null ? Integer.valueOf(bufferStart.intValue() / 1000) : null);
                pairArr[1] = new Pair("readyState", 1);
                pairArr[2] = new Pair("secondsToLoad", ToolboxEvents.INSTANCE.getBufferingDuration());
                pairArr[3] = new Pair("bufferCount", Integer.valueOf(ToolboxEvents.INSTANCE.getBufferCount()));
                pairArr[4] = new Pair("scrubbing", Boolean.valueOf(ToolboxEvents.INSTANCE.getScrubbing()));
                mapMutableMapOf.put(ToolboxAnalyticsModuleKt.KEY_BUFFER_STATS, MapsKt.mapOf(pairArr));
                mapMutableMapOf.put(ToolboxAnalyticsModuleKt.KEY_PLAYBACK, playbackParams());
                mapMutableMapOf.putAll(commonParams());
                return mapMutableMapOf;
            }
        }

        /* JADX INFO: compiled from: ToolboxAnalyticsModule.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents$PLAYBACKERROR;", "Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents;", "getEventsParams", "", "", "", "args", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
        static final class PLAYBACKERROR extends ToolboxEvents {
            PLAYBACKERROR(String str, int i) {
                super(str, i, null);
            }

            @Override // com.dotscreen.tv.common.modules.ToolboxAnalyticsModule.ToolboxEvents
            public Map<String, Object> getEventsParams(Map<String, Object> args) {
                Intrinsics.checkParameterIsNotNull(args, "args");
                Map<String, Object> mapMutableMapOf = MapsKt.mutableMapOf(new Pair(ToolboxAnalyticsModuleKt.KEY_CONTENT, contentParams()), new Pair(ToolboxAnalyticsModuleKt.KEY_EVENTS, CollectionsKt.listOf(eventMap(ToolboxAnalyticsModuleKt.EVENT_PLAYBACK_ERROR, args))));
                mapMutableMapOf.put(ToolboxAnalyticsModuleKt.KEY_PLAYBACK, playbackParams());
                mapMutableMapOf.putAll(commonParams());
                return mapMutableMapOf;
            }
        }

        /* JADX INFO: compiled from: ToolboxAnalyticsModule.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents$STREAMING_PROGRESS;", "Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents;", "getEventsParams", "", "", "", "args", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
        static final class STREAMING_PROGRESS extends ToolboxEvents {
            STREAMING_PROGRESS(String str, int i) {
                super(str, i, null);
            }

            @Override // com.dotscreen.tv.common.modules.ToolboxAnalyticsModule.ToolboxEvents
            public Map<String, Object> getEventsParams(Map<String, Object> args) {
                Intrinsics.checkParameterIsNotNull(args, "args");
                Map<String, Object> mapMutableMapOf = MapsKt.mutableMapOf(new Pair(ToolboxAnalyticsModuleKt.KEY_CONTENT, contentParams()), new Pair(ToolboxAnalyticsModuleKt.KEY_EVENTS, CollectionsKt.listOf(ToolboxEvents.eventMap$default(this, ToolboxAnalyticsModuleKt.EVENT_STREAMING_PROGRESS, null, 2, null))));
                mapMutableMapOf.put(ToolboxAnalyticsModuleKt.KEY_PLAYBACK, playbackParams());
                mapMutableMapOf.putAll(commonParams());
                return mapMutableMapOf;
            }
        }

        /* JADX INFO: compiled from: ToolboxAnalyticsModule.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\n\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001c\"\u0004\b!\u0010\u001eR \u0010\"\u001a\u0004\u0018\u00010\u00048FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b#\u0010\u0006\"\u0004\b$\u0010\bR\u001c\u0010%\u001a\u0004\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001c\"\u0004\b'\u0010\u001eR\u001c\u0010(\u001a\u0004\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001c\"\u0004\b*\u0010\u001eR\u001c\u0010+\u001a\u0004\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u001c\"\u0004\b-\u0010\u001eR\u001e\u0010.\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b/\u0010\u0006\"\u0004\b0\u0010\bR\u001e\u00101\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b2\u0010\u0006\"\u0004\b3\u0010\bR\u001f\u00104\u001a\u0010\u0012\u0004\u0012\u00020\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u000405¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u001a\u00108\u001a\u000209X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001e\u0010>\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b?\u0010\u0015\"\u0004\b@\u0010\u0017R\u001e\u0010A\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\bB\u0010\u0015\"\u0004\bC\u0010\u0017¨\u0006D"}, d2 = {"Lcom/dotscreen/tv/common/modules/ToolboxAnalyticsModule$ToolboxEvents$Companion;", "", "()V", "bitRate", "", "getBitRate", "()Ljava/lang/Integer;", "setBitRate", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "bufferCount", "getBufferCount", "()I", "setBufferCount", "(I)V", "bufferStart", "getBufferStart", "setBufferStart", "bufferingDuration", "", "getBufferingDuration", "()Ljava/lang/Long;", "setBufferingDuration", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "contentId", "", "getContentId", "()Ljava/lang/String;", "setContentId", "(Ljava/lang/String;)V", ToolboxAnalyticsModuleKt.KEY_DRM_TYPE, "getDrmType", "setDrmType", "duration", "getDuration", "setDuration", "hboToken", "getHboToken", "setHboToken", "mimeType", "getMimeType", "setMimeType", ToolboxAnalyticsModuleKt.KEY_PLAY_BACK_URL, "getPlaybackUrl", "setPlaybackUrl", "position", "getPosition", "setPosition", "preBufferingTime", "getPreBufferingTime", "setPreBufferingTime", "resolution", "", "getResolution", "()Ljava/util/Map;", "scrubbing", "", "getScrubbing", "()Z", "setScrubbing", "(Z)V", "startTime", "getStartTime", "setStartTime", "timeSpent", "getTimeSpent", "setTimeSpent", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final Long getStartTime() {
                return ToolboxEvents.startTime;
            }

            public final void setStartTime(Long l) {
                ToolboxEvents.startTime = l;
            }

            public final void setDuration(Integer num) {
                ToolboxEvents.duration = num;
            }

            public final Integer getDuration() {
                Integer num = ToolboxEvents.duration;
                if (num != null) {
                    return Integer.valueOf(num.intValue() / 1000);
                }
                return null;
            }

            public final String getContentId() {
                return ToolboxEvents.contentId;
            }

            public final void setContentId(String str) {
                ToolboxEvents.contentId = str;
            }

            public final String getMimeType() {
                return ToolboxEvents.mimeType;
            }

            public final void setMimeType(String str) {
                ToolboxEvents.mimeType = str;
            }

            public final String getPlaybackUrl() {
                return ToolboxEvents.playbackUrl;
            }

            public final void setPlaybackUrl(String str) {
                ToolboxEvents.playbackUrl = str;
            }

            public final String getHboToken() {
                return ToolboxEvents.hboToken;
            }

            public final void setHboToken(String str) {
                ToolboxEvents.hboToken = str;
            }

            public final String getDrmType() {
                return ToolboxEvents.drmType;
            }

            public final void setDrmType(String str) {
                ToolboxEvents.drmType = str;
            }

            public final Integer getPosition() {
                return ToolboxEvents.position;
            }

            public final void setPosition(Integer num) {
                ToolboxEvents.position = num;
            }

            public final Long getTimeSpent() {
                return ToolboxEvents.timeSpent;
            }

            public final void setTimeSpent(Long l) {
                ToolboxEvents.timeSpent = l;
            }

            public final Integer getBitRate() {
                return ToolboxEvents.bitRate;
            }

            public final void setBitRate(Integer num) {
                ToolboxEvents.bitRate = num;
            }

            public final Map<String, Integer> getResolution() {
                return ToolboxEvents.resolution;
            }

            public final Integer getPreBufferingTime() {
                return ToolboxEvents.preBufferingTime;
            }

            public final void setPreBufferingTime(Integer num) {
                ToolboxEvents.preBufferingTime = num;
            }

            public final Integer getBufferStart() {
                return ToolboxEvents.bufferStart;
            }

            public final void setBufferStart(Integer num) {
                ToolboxEvents.bufferStart = num;
            }

            public final Long getBufferingDuration() {
                return ToolboxEvents.bufferingDuration;
            }

            public final void setBufferingDuration(Long l) {
                ToolboxEvents.bufferingDuration = l;
            }

            public final int getBufferCount() {
                return ToolboxEvents.bufferCount;
            }

            public final void setBufferCount(int i) {
                ToolboxEvents.bufferCount = i;
            }

            public final boolean getScrubbing() {
                return ToolboxEvents.scrubbing;
            }

            public final void setScrubbing(boolean z) {
                ToolboxEvents.scrubbing = z;
            }
        }

        static {
            LOGIN login = new LOGIN("LOGIN", 0);
            LOGIN = login;
            START start = new START("START", 1);
            START = start;
            CLOSE close = new CLOSE("CLOSE", 2);
            CLOSE = close;
            PAUSE pause = new PAUSE("PAUSE", 3);
            PAUSE = pause;
            RESUME resume = new RESUME("RESUME", 4);
            RESUME = resume;
            PROGRESSMARK progressmark = new PROGRESSMARK("PROGRESSMARK", 5);
            PROGRESSMARK = progressmark;
            FIRSTQUARTILE firstquartile = new FIRSTQUARTILE("FIRSTQUARTILE", 6);
            FIRSTQUARTILE = firstquartile;
            MIDPOINT midpoint = new MIDPOINT("MIDPOINT", 7);
            MIDPOINT = midpoint;
            THIRDQUARTILE thirdquartile = new THIRDQUARTILE("THIRDQUARTILE", 8);
            THIRDQUARTILE = thirdquartile;
            COMPLETE complete = new COMPLETE("COMPLETE", 9);
            COMPLETE = complete;
            ENDED ended = new ENDED("ENDED", 10);
            ENDED = ended;
            START_BUFFERING start_buffering = new START_BUFFERING("START_BUFFERING", 11);
            START_BUFFERING = start_buffering;
            RE_BUFFERING re_buffering = new RE_BUFFERING("RE_BUFFERING", 12);
            RE_BUFFERING = re_buffering;
            PLAYBACKERROR playbackerror = new PLAYBACKERROR("PLAYBACKERROR", 13);
            PLAYBACKERROR = playbackerror;
            STREAMING_PROGRESS streaming_progress = new STREAMING_PROGRESS("STREAMING_PROGRESS", 14);
            STREAMING_PROGRESS = streaming_progress;
            $VALUES = new ToolboxEvents[]{login, start, close, pause, resume, progressmark, firstquartile, midpoint, thirdquartile, complete, ended, start_buffering, re_buffering, playbackerror, streaming_progress};
            INSTANCE = new Companion(null);
            resolution = MapsKt.mapOf(new Pair(ToolboxAnalyticsModuleKt.M_WIDTH, null), new Pair(ToolboxAnalyticsModuleKt.M_HEIGHT, null));
        }

        public final Map<String, String> contentParams() {
            Map<String, String> mapMutableMapOf = MapsKt.mutableMapOf(new Pair("id", contentId), new Pair(ToolboxAnalyticsModuleKt.KEY_FORMAT_TYPE, mimeType), new Pair(ToolboxAnalyticsModuleKt.KEY_PLAY_BACK_URL, playbackUrl));
            String str = drmType;
            if (str != null) {
                mapMutableMapOf.put(ToolboxAnalyticsModuleKt.KEY_DRM_TYPE, str);
            }
            return mapMutableMapOf;
        }

        public final Map<String, Object> playbackParams() {
            Pair[] pairArr = new Pair[3];
            Integer num = position;
            pairArr[0] = new Pair("position", num != null ? Integer.valueOf(num.intValue() / 1000) : null);
            Long l = timeSpent;
            pairArr[1] = new Pair("timeSpent", l != null ? Long.valueOf(l.longValue() / ((long) 1000)) : null);
            Integer num2 = bitRate;
            pairArr[2] = new Pair("bitrate", Integer.valueOf(num2 != null ? num2.intValue() : -1));
            Map<String, Object> mapMutableMapOf = MapsKt.mutableMapOf(pairArr);
            if (resolution.get(ToolboxAnalyticsModuleKt.M_WIDTH) != null) {
                mapMutableMapOf.put("resolution", resolution);
            }
            return mapMutableMapOf;
        }

        public static /* synthetic */ Map eventMap$default(ToolboxEvents toolboxEvents, String str, Object obj, int i, Object obj2) {
            if ((i & 2) != 0) {
                obj = null;
            }
            return toolboxEvents.eventMap(str, obj);
        }

        public final Map<String, Object> eventMap(String eventName, Object value) {
            Intrinsics.checkParameterIsNotNull(eventName, "eventName");
            Map<String, Object> mapMutableMapOf = MapsKt.mutableMapOf(new Pair("name", eventName), new Pair("number", 1));
            if (value != null) {
                mapMutableMapOf.put("value", value);
            }
            return mapMutableMapOf;
        }

        public final Map<String, Object> commonParams() {
            String str;
            boolean z = true;
            Map<String, Object> mapMutableMapOf = MapsKt.mutableMapOf(new Pair("version", 2));
            String str2 = hboToken;
            if (str2 != null && str2.length() != 0) {
                z = false;
            }
            if (!z && (str = hboToken) != null) {
                mapMutableMapOf.put(ToolboxAnalyticsModuleKt.KEY_AUTH_HBO, str);
            }
            return mapMutableMapOf;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void postToolboxEvents$default(ToolboxAnalyticsModule toolboxAnalyticsModule, ToolboxEvents toolboxEvents, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            map = new LinkedHashMap();
        }
        toolboxAnalyticsModule.postToolboxEvents(toolboxEvents, map);
    }

    private final void postToolboxEvents(final ToolboxEvents event, final Map<String, Object> params) {
        if (this.toolboxContent) {
            ToolboxEvents.Companion companion = ToolboxEvents.INSTANCE;
            long time = new Date().getTime();
            Long startTime = ToolboxEvents.INSTANCE.getStartTime();
            companion.setTimeSpent(Long.valueOf(time - (startTime != null ? startTime.longValue() : new Date().getTime())));
            Disposable disposableSubscribe = this.userService.getToolboxToken().flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.modules.ToolboxAnalyticsModule$postToolboxEvents$disposable$1
                @Override // io.reactivex.functions.Function
                public final Single<JSONObject> apply(String it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    RequestManager requestManager = this.this$0.getRequestManager();
                    if (requestManager != null) {
                        return RightVRequestManager.getTbxEventJSONObject$default((RightVRequestManager) requestManager, null, 0, event.getEventsParams(params), it, 3, null);
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVRequestManager");
                }
            }).subscribe(new BiConsumer<JSONObject, Throwable>() { // from class: com.dotscreen.tv.common.modules.ToolboxAnalyticsModule$postToolboxEvents$disposable$2
                @Override // io.reactivex.functions.BiConsumer
                public final void accept(JSONObject jSONObject, Throwable th) {
                    if (th == null) {
                        Log.d(this.this$0.TAG, "TbxEvents " + event.name() + " => " + jSONObject);
                        return;
                    }
                    th.printStackTrace();
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "userService.toolboxToken…entReturn\")\n            }");
            getCompositeDisposable().add(disposableSubscribe);
        }
    }
}
