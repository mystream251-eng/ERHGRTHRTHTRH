package com.dotscreen.tv.common.modules;

import android.content.Context;
import android.util.Log;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.services.LiveTVService;
import com.dotscreen.tv.common.bokit.MegacableLiveTVService;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.dotscreen.tv.common.utils.CommonUtils;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CompassAnalyticsModule.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0001R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/dotscreen/tv/common/modules/CompassAnalyticsModule;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "kotlin.jvm.PlatformType", "lastEvent", "Lio/reactivex/disposables/Disposable;", "onCloseLiveStream", "", "onZap", "channel", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class CompassAnalyticsModule {
    private final String TAG;
    private final Context context;
    private Disposable lastEvent;

    public CompassAnalyticsModule(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = CompassAnalyticsModule.class.getSimpleName();
    }

    public final void onZap(final Object channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Disposable disposable = this.lastEvent;
        if (disposable != null) {
            disposable.dispose();
        }
        if (channel instanceof RightVChannel) {
            Log.d(this.TAG, "registerLiveEvent => Subscribe");
            this.lastEvent = Single.timer(CommonUtils.ZAP_EVENT_DELAY, TimeUnit.SECONDS).flatMapCompletable(new Function<Long, CompletableSource>() { // from class: com.dotscreen.tv.common.modules.CompassAnalyticsModule.onZap.1
                @Override // io.reactivex.functions.Function
                public final Completable apply(Long it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Log.d(CompassAnalyticsModule.this.TAG, "registerLiveEvent => registerLiveEvent");
                    LiveTVService liveTVService = CommonUtils.getBOService(CompassAnalyticsModule.this.context).getLiveTVService();
                    if (liveTVService != null) {
                        return MegacableLiveTVService.registerLiveEvent$default((MegacableLiveTVService) liveTVService, (Channel) channel, null, 2, null);
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
                }
            }).subscribe(new Action() { // from class: com.dotscreen.tv.common.modules.CompassAnalyticsModule.onZap.2
                @Override // io.reactivex.functions.Action
                public final void run() {
                    Log.d(CompassAnalyticsModule.this.TAG, "registerLiveEvent => Emitted");
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.modules.CompassAnalyticsModule.onZap.3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    Log.w(CompassAnalyticsModule.this.TAG, "registerLiveEvent => Error: " + th);
                }
            });
        }
    }

    public final void onCloseLiveStream() {
        Log.d(this.TAG, "registerevent => cancel");
        Disposable disposable = this.lastEvent;
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
