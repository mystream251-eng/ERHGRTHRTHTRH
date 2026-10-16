package com.dotscreen.portal.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.farkit.updater.UpdateManagerKt;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.utils.CommonUtils;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UpdaterWorker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \u000b*\u0004\u0018\u00010\r0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/dotscreen/portal/utils/UpdaterWorker;", "Landroidx/work/Worker;", "context", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "DEBUG", "", "TAG", "", "kotlin.jvm.PlatformType", "packageManager", "Landroid/content/pm/PackageManager;", "doWork", "Landroidx/work/ListenableWorker$Result;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class UpdaterWorker extends Worker {
    private final boolean DEBUG;
    private final String TAG;
    private final PackageManager packageManager;
    private final WorkerParameters params;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdaterWorker(Context context, WorkerParameters params) {
        super(context, params);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(params, "params");
        this.params = params;
        this.DEBUG = true;
        this.TAG = UpdaterWorker.class.getSimpleName();
        this.packageManager = context.getPackageManager();
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() throws PackageManager.NameNotFoundException {
        String string = this.params.getInputData().getString(UpdateManagerKt.EXTRA_PACKAGE_NAME);
        if (this.DEBUG) {
            Log.d(this.TAG, "    packageName = " + string);
        }
        if (string != null) {
            PackageInfo pi = this.packageManager.getPackageInfo(string, 0);
            if (this.DEBUG) {
                Log.d(this.TAG, "    versionName = " + pi.versionName);
            }
            if (this.DEBUG) {
                String str = this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("    versionCode = ");
                Intrinsics.checkExpressionValueIsNotNull(pi, "pi");
                sb.append(pi.getLongVersionCode());
                Log.d(str, sb.toString());
            }
            UserService userService = CommonUtils.getBOService(getApplicationContext()).getUserService();
            if (userService == null) {
                Intrinsics.throwNpe();
            }
            if (userService == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
            }
            ((MegacableUserService) userService).notifyApplicationsUpdated().subscribe(new Action() { // from class: com.dotscreen.portal.utils.UpdaterWorker$doWork$1$1
                @Override // io.reactivex.functions.Action
                public final void run() {
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.utils.UpdaterWorker$doWork$1$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                }
            });
            ListenableWorker.Result resultSuccess = ListenableWorker.Result.success();
            Intrinsics.checkExpressionValueIsNotNull(resultSuccess, "Result.success()");
            return resultSuccess;
        }
        ListenableWorker.Result resultFailure = ListenableWorker.Result.failure();
        Intrinsics.checkExpressionValueIsNotNull(resultFailure, "Result.failure()");
        return resultFailure;
    }
}
