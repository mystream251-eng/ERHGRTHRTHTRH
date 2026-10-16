package com.dotscreen.megacable.portal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import com.dotscreen.farkit.updater.UpdateManagerKt;
import com.dotscreen.portal.PortalApplicationKt;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ClearedReceiver.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcom/dotscreen/megacable/portal/ClearedReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ClearedReceiver extends BroadcastReceiver {
    private final String TAG = ClearedReceiver.class.getSimpleName();

    public final String getTAG() {
        return this.TAG;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, Intent intent) {
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onReceive intent : ");
        sb.append(intent);
        sb.append(", extras : ");
        sb.append(intent != null ? intent.getExtras() : null);
        Log.d(str, sb.toString());
        if (intent == null || !Intrinsics.areEqual("android.intent.action.PACKAGE_DATA_CLEARED", intent.getAction())) {
            return;
        }
        int intExtra = intent.getIntExtra("android.intent.extra.UID", 0);
        Log.d(this.TAG, "onReceive uid : " + intExtra + ", packageName : " + intent.getStringExtra(UpdateManagerKt.EXTRA_PACKAGE_NAME) + ", context: " + context);
        if (Process.myUid() != intExtra || context == null) {
            return;
        }
        new Timer().schedule(new TimerTask() { // from class: com.dotscreen.megacable.portal.ClearedReceiver$onReceive$$inlined$run$lambda$1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                PortalApplicationKt.scheduleUpdaterCheck(context);
                PortalApplicationKt.scheduleCoreCheck(context);
            }
        }, 500L);
    }
}
