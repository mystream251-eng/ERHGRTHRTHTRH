package com.dotscreen.tvinput;

import android.content.Context;
import android.util.Log;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.dotscreen.bokit.internal.tools.TimeHelper;
import com.dotscreen.farkit.network.RequestManager;
import com.dotscreen.tv.common.INetRequestProvider;
import com.dotscreen.tv.common.utils.CommonUtils;
import io.reactivex.Single;
import java.net.URL;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: SyncTVChannelsWorker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0017R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/tvinput/ForceSyncCheckWorker;", "Landroidx/work/Worker;", "context", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "getContext", "()Landroid/content/Context;", "doWork", "Landroidx/work/ListenableWorker$Result;", "tvinput_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ForceSyncCheckWorker extends Worker {
    private final Context context;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ForceSyncCheckWorker(Context context, WorkerParameters params) {
        super(context, params);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(params, "params");
        this.context = context;
    }

    protected final Context getContext() {
        return this.context;
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        JSONObject jSONObject;
        JSONObject jSONObjectOptJSONObject;
        Log.d("ForceSyncCheckWorker", "doWork (SyncTVChannelsWorker)");
        Object applicationContext = this.context.getApplicationContext();
        if (!(applicationContext instanceof INetRequestProvider)) {
            applicationContext = null;
        }
        INetRequestProvider iNetRequestProvider = (INetRequestProvider) applicationContext;
        RequestManager requestManager = iNetRequestProvider != null ? iNetRequestProvider.getRequestManager() : null;
        URL url = new URL(CommonUtils.CONFIG_URL + "/channel-scan.json");
        if (requestManager != null) {
            try {
                Single jSONObject$default = RequestManager.getJSONObject$default(requestManager, 0, url, null, 4, null);
                if (jSONObject$default != null && (jSONObject = (JSONObject) jSONObject$default.blockingGet()) != null && jSONObject.getInt("version") == 1 && (jSONObjectOptJSONObject = jSONObject.optJSONObject("dvb")) != null) {
                    long jOptLong = jSONObjectOptJSONObject.optLong("forceScanAt", 0L) * ((long) 1000);
                    long lastDVBScanTime = SyncTVChannelsWorkerKt.getLastDVBScanTime(this.context);
                    long time = TimeHelper.INSTANCE.now().getTime();
                    Log.d("ForceSyncCheckWorker", "forceScanAt: " + new Date(jOptLong));
                    Log.d("ForceSyncCheckWorker", "lastScanTime: " + new Date(lastDVBScanTime));
                    Log.d("ForceSyncCheckWorker", "Now: " + new Date(time));
                    if (lastDVBScanTime + 1 <= jOptLong && time >= jOptLong) {
                        SyncTVChannelsWorkerKt.scheduleSyncingTVChannels$default(this.context, null, true, false, 10, null);
                    }
                }
            } catch (Exception e) {
                Log.w("ForceSyncCheckWorker", "Exception", e);
                SyncTVChannelsWorkerKt.scheduleForceSyncCheck(this.context);
                ListenableWorker.Result resultFailure = ListenableWorker.Result.failure();
                Intrinsics.checkExpressionValueIsNotNull(resultFailure, "Result.failure()");
                return resultFailure;
            }
        }
        SyncTVChannelsWorkerKt.scheduleForceSyncCheck(this.context);
        ListenableWorker.Result resultSuccess = ListenableWorker.Result.success();
        Intrinsics.checkExpressionValueIsNotNull(resultSuccess, "Result.success()");
        return resultSuccess;
    }
}
