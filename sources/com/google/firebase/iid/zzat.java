package com.google.firebase.iid;

import android.util.Log;
import android.util.Pair;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
final class zzat {
    private final Executor zza;
    private final Map<Pair<String, String>, Task<InstanceIdResult>> zzb = new ArrayMap();

    zzat(Executor executor) {
        this.zza = executor;
    }

    final synchronized Task<InstanceIdResult> zza(String str, String str2, zzav zzavVar) {
        final Pair<String, String> pair = new Pair<>(str, str2);
        Task<InstanceIdResult> task = this.zzb.get(pair);
        if (task != null) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String strValueOf = String.valueOf(pair);
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 29);
                sb.append("Joining ongoing request for: ");
                sb.append(strValueOf);
                Log.d("FirebaseInstanceId", sb.toString());
            }
            return task;
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String strValueOf2 = String.valueOf(pair);
            StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf2).length() + 24);
            sb2.append("Making new request for: ");
            sb2.append(strValueOf2);
            Log.d("FirebaseInstanceId", sb2.toString());
        }
        Task taskContinueWithTask = zzavVar.zza().continueWithTask(this.zza, new Continuation(this, pair) { // from class: com.google.firebase.iid.zzas
            private final zzat zza;
            private final Pair zzb;

            {
                this.zza = this;
                this.zzb = pair;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task2) {
                return this.zza.zza(this.zzb, task2);
            }
        });
        this.zzb.put(pair, (Task<InstanceIdResult>) taskContinueWithTask);
        return taskContinueWithTask;
    }

    final /* synthetic */ Task zza(Pair pair, Task task) throws Exception {
        synchronized (this) {
            this.zzb.remove(pair);
        }
        return task;
    }
}
