package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzg;

/* JADX INFO: loaded from: classes2.dex */
final class zztj implements zzg {
    private /* synthetic */ zzamd zza;
    private /* synthetic */ zzte zzb;

    zztj(zzte zzteVar, zzamd zzamdVar) {
        this.zzb = zzteVar;
        this.zza = zzamdVar;
    }

    public final void zza(ConnectionResult connectionResult) {
        synchronized (this.zzb.zzd) {
            this.zza.zza(new RuntimeException("Connection failed."));
        }
    }
}
