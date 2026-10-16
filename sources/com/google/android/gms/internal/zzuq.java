package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
final class zzuq {
    private final LinkedList<zzur> zza;
    private zzkk zzb;
    private final String zzc;
    private final int zzd;
    private boolean zze;

    zzuq(zzkk zzkkVar, String str, int i) {
        zzbq.zza(zzkkVar);
        zzbq.zza(str);
        this.zza = new LinkedList<>();
        this.zzb = zzkkVar;
        this.zzc = str;
        this.zzd = i;
    }

    final zzkk zza() {
        return this.zzb;
    }

    final zzur zza(zzkk zzkkVar) {
        if (zzkkVar != null) {
            this.zzb = zzkkVar;
        }
        return this.zza.remove();
    }

    final void zza(zztk zztkVar, zzkk zzkkVar) {
        this.zza.add(new zzur(this, zztkVar, zzkkVar));
    }

    final boolean zza(zztk zztkVar) {
        zzur zzurVar = new zzur(this, zztkVar);
        this.zza.add(zzurVar);
        return zzurVar.zza();
    }

    final int zzb() {
        return this.zzd;
    }

    final String zzc() {
        return this.zzc;
    }

    final int zzd() {
        return this.zza.size();
    }

    final int zze() {
        Iterator<zzur> it = this.zza.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().zze) {
                i++;
            }
        }
        return i;
    }

    final int zzf() {
        Iterator<zzur> it = this.zza.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().zza()) {
                i++;
            }
        }
        return i;
    }

    final void zzg() {
        this.zze = true;
    }

    final boolean zzh() {
        return this.zze;
    }
}
