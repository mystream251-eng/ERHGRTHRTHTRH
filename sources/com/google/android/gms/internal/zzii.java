package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.internal.zzf;

/* JADX INFO: loaded from: classes2.dex */
final class zzii implements zzf {
    private /* synthetic */ zzif zza;

    zzii(zzif zzifVar) {
        this.zza = zzifVar;
    }

    public final void zza(int i) {
        synchronized (this.zza.zzb) {
            this.zza.zze = null;
            this.zza.zzb.notifyAll();
        }
    }

    public final void zza(Bundle bundle) {
        synchronized (this.zza.zzb) {
            try {
                if (this.zza.zzc != null) {
                    this.zza.zze = this.zza.zzc.c_();
                }
            } catch (DeadObjectException e) {
                zzahw.zzb("Unable to obtain a cache service instance.", e);
                this.zza.zzc();
            }
            this.zza.zzb.notifyAll();
        }
    }
}
