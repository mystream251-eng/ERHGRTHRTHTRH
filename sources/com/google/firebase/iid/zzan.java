package com.google.firebase.iid;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
abstract class zzan<T> {
    final int zza;
    final TaskCompletionSource<T> zzb = new TaskCompletionSource<>();
    final int zzc;
    final Bundle zzd;

    zzan(int i, int i2, Bundle bundle) {
        this.zza = i;
        this.zzc = i2;
        this.zzd = bundle;
    }

    abstract void zza(Bundle bundle);

    abstract boolean zza();

    final void zza(T t) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String strValueOf = String.valueOf(this);
            String strValueOf2 = String.valueOf(t);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 16 + String.valueOf(strValueOf2).length());
            sb.append("Finishing ");
            sb.append(strValueOf);
            sb.append(" with ");
            sb.append(strValueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.zzb.setResult(t);
    }

    final void zza(zzam zzamVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String strValueOf = String.valueOf(this);
            String strValueOf2 = String.valueOf(zzamVar);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 14 + String.valueOf(strValueOf2).length());
            sb.append("Failing ");
            sb.append(strValueOf);
            sb.append(" with ");
            sb.append(strValueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.zzb.setException(zzamVar);
    }

    public String toString() {
        int i = this.zzc;
        int i2 = this.zza;
        boolean zZza = zza();
        StringBuilder sb = new StringBuilder(55);
        sb.append("Request { what=");
        sb.append(i);
        sb.append(" id=");
        sb.append(i2);
        sb.append(" oneWay=");
        sb.append(zZza);
        sb.append("}");
        return sb.toString();
    }
}
