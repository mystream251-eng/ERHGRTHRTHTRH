package com.dotscreen.tvinput;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.dotscreen.bokit.model.Channel;
import io.reactivex.Completable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SyncTVChannelsWorker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\t\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/tvinput/ZTESyncTVChannelsWorker;", "Lcom/dotscreen/tvinput/SyncTVChannelsWorker;", "context", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "installDVBChannels", "Lio/reactivex/Completable;", "installIPChannels", "channels", "", "Lcom/dotscreen/bokit/model/Channel;", "tvinput_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ZTESyncTVChannelsWorker extends SyncTVChannelsWorker {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ZTESyncTVChannelsWorker(Context context, WorkerParameters params) {
        super(context, params);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(params, "params");
    }

    @Override // com.dotscreen.tvinput.SyncTVChannelsWorker
    public Completable installDVBChannels() {
        Completable completableError = Completable.error(new UnsupportedOperationException("DVB Scan"));
        Intrinsics.checkExpressionValueIsNotNull(completableError, "Completable.error(Unsupp…ionException(\"DVB Scan\"))");
        return completableError;
    }

    @Override // com.dotscreen.tvinput.SyncTVChannelsWorker
    public Completable installIPChannels(List<? extends Channel> channels) {
        Intrinsics.checkParameterIsNotNull(channels, "channels");
        Completable completableComplete = Completable.complete();
        Intrinsics.checkExpressionValueIsNotNull(completableComplete, "Completable.complete()");
        return completableComplete;
    }
}
