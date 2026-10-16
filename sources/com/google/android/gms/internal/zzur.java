package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzak;
import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
final class zzur {
    zzak zza;
    zzkk zzb;
    zztl zzc;
    long zzd;
    boolean zze;
    boolean zzf;
    private /* synthetic */ zzuq zzg;

    zzur(zzuq zzuqVar, zztk zztkVar) {
        this.zzg = zzuqVar;
        this.zza = zztkVar.zzb(zzuqVar.zzc);
        zztl zztlVar = new zztl();
        this.zzc = zztlVar;
        zzak zzakVar = this.zza;
        zzakVar.zza(new zztm(zztlVar));
        zzakVar.zza(new zztu(zztlVar));
        zzakVar.zza(new zztw(zztlVar));
        zzakVar.zza(new zzty(zztlVar));
        zzakVar.zza(new zzua(zztlVar));
    }

    zzur(zzuq zzuqVar, zztk zztkVar, zzkk zzkkVar) {
        this(zzuqVar, zztkVar);
        this.zzb = zzkkVar;
    }

    final boolean zza() {
        if (this.zze) {
            return false;
        }
        zzkk zzkkVar = this.zzb;
        if (zzkkVar == null) {
            zzkkVar = this.zzg.zzb;
        }
        this.zzf = this.zza.zzb(zzuo.zzb(zzkkVar));
        this.zze = true;
        this.zzd = zzbt.zzl().zza();
        return true;
    }
}
