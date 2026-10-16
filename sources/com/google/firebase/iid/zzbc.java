package com.google.firebase.iid;

import android.os.Binder;
import android.os.Process;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbc extends Binder {
    private final zzbe zza;

    public zzbc(zzbe zzbeVar) {
        this.zza = zzbeVar;
    }

    final void zza(final zzbg zzbgVar) {
        if (Binder.getCallingUid() != Process.myUid()) {
            throw new SecurityException("Binding only allowed within app");
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "service received new intent via bind strategy");
        }
        this.zza.zza(zzbgVar.zza).addOnCompleteListener(zzh.zza(), new OnCompleteListener(zzbgVar) { // from class: com.google.firebase.iid.zzbf
            private final zzbg zza;

            {
                this.zza = zzbgVar;
            }

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.zza.zzb();
            }
        });
    }
}
