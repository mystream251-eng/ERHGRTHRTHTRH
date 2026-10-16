package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzg;

/* JADX INFO: loaded from: classes2.dex */
final class zzij implements zzg {
    private /* synthetic */ zzif zza;

    zzij(zzif zzifVar) {
        this.zza = zzifVar;
    }

    public final void zza(ConnectionResult connectionResult) {
        synchronized (this.zza.zzb) {
            this.zza.zze = null;
            if (this.zza.zzc != null) {
                zzif.zza(this.zza, (zzim) null);
            }
            this.zza.zzb.notifyAll();
        }
    }
}
