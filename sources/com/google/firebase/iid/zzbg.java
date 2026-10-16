package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbg {
    final Intent zza;
    private final TaskCompletionSource<Void> zzb = new TaskCompletionSource<>();

    zzbg(Intent intent) {
        this.zza = intent;
    }

    final Task<Void> zza() {
        return this.zzb.getTask();
    }

    final void zzb() {
        this.zzb.trySetResult(null);
    }

    final /* synthetic */ void zzc() {
        String action = this.zza.getAction();
        StringBuilder sb = new StringBuilder(String.valueOf(action).length() + 61);
        sb.append("Service took too long to process intent: ");
        sb.append(action);
        sb.append(" App may get closed.");
        Log.w("FirebaseInstanceId", sb.toString());
        zzb();
    }
}
