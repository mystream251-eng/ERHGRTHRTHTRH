package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzf;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes2.dex */
final class zztg implements zzf {
    final /* synthetic */ zzte zza;
    private /* synthetic */ zzamd zzb;
    private /* synthetic */ zzsy zzc;

    zztg(zzte zzteVar, zzamd zzamdVar, zzsy zzsyVar) {
        this.zza = zzteVar;
        this.zzb = zzamdVar;
        this.zzc = zzsyVar;
    }

    public final void zza(int i) {
    }

    public final void zza(Bundle bundle) {
        synchronized (this.zza.zzd) {
            if (this.zza.zzb) {
                return;
            }
            zzte.zza(this.zza, true);
            final zzsx zzsxVar = this.zza.zza;
            if (zzsxVar == null) {
                return;
            }
            final zzamd zzamdVar = this.zzb;
            final zzsy zzsyVar = this.zzc;
            final zzalt<Void> zzaltVarZza = zzaid.zza(new Runnable(this, zzsxVar, zzamdVar, zzsyVar) { // from class: com.google.android.gms.internal.zzth
                private final zztg zza;
                private final zzsx zzb;
                private final zzamd zzc;
                private final zzsy zzd;

                {
                    this.zza = this;
                    this.zzb = zzsxVar;
                    this.zzc = zzamdVar;
                    this.zzd = zzsyVar;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    zztg zztgVar = this.zza;
                    zzsx zzsxVar2 = this.zzb;
                    zzamd zzamdVar2 = this.zzc;
                    try {
                        zzamdVar2.zzb(zzsxVar2.d_().zza(this.zzd));
                    } catch (RemoteException e) {
                        zzahw.zzb("Unable to obtain a cache service instance.", e);
                        zzamdVar2.zza(e);
                        zztgVar.zza.zza();
                    }
                }
            });
            zzamd zzamdVar2 = this.zzb;
            final zzamd zzamdVar3 = this.zzb;
            zzamdVar2.zza(new Runnable(zzamdVar3, zzaltVarZza) { // from class: com.google.android.gms.internal.zzti
                private final zzamd zza;
                private final Future zzb;

                {
                    this.zza = zzamdVar3;
                    this.zzb = zzaltVarZza;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    zzamd zzamdVar4 = this.zza;
                    Future future = this.zzb;
                    if (zzamdVar4.isCancelled()) {
                        future.cancel(true);
                    }
                }
            }, zzaly.zzb);
        }
    }
}
