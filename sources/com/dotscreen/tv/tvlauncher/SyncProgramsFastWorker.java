package com.dotscreen.tv.tvlauncher;

import android.content.Context;
import androidx.tvprovider.media.tv.Channel;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import java.time.Duration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SyncProgramsFastWorker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/dotscreen/tv/tvlauncher/SyncProgramsFastWorker;", "Landroidx/work/Worker;", "context", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "TAG", "", "kotlin.jvm.PlatformType", "doWork", "Landroidx/work/ListenableWorker$Result;", "onStopped", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class SyncProgramsFastWorker extends Worker {
    private final String TAG;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SyncProgramsFastWorker(Context context, WorkerParameters params) {
        super(context, params);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(params, "params");
        this.TAG = SyncProgramsFastWorker.class.getSimpleName();
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        long j = getInputData().getLong(SyncProgramsWorkerKt.DATA_CHANNEL_ID_KEY, -1L);
        if (j > 0) {
            Context applicationContext = getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
            TVLauncherHelper tVLauncherHelper = new TVLauncherHelper(applicationContext);
            Channel channel = tVLauncherHelper.channel(j);
            if (channel != null && channel.isBrowsable()) {
                tVLauncherHelper.touchChannel(j);
            }
            TVLauncherUtils.scheduleSyncingProgramsFast(j, Duration.ofSeconds(5L));
        }
        ListenableWorker.Result resultSuccess = ListenableWorker.Result.success();
        Intrinsics.checkExpressionValueIsNotNull(resultSuccess, "Result.success()");
        return resultSuccess;
    }

    @Override // androidx.work.ListenableWorker
    public void onStopped() {
        super.onStopped();
    }
}
