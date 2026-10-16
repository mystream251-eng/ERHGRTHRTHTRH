package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdtg {
    private zzdwp zza;

    private zzdtg(zzdwp zzdwpVar) {
        this.zza = zzdwpVar;
    }

    static final zzdtg zza(zzdwp zzdwpVar) throws GeneralSecurityException {
        if (zzdwpVar == null || zzdwpVar.zzd() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
        return new zzdtg(zzdwpVar);
    }

    public final String toString() {
        zzdwp zzdwpVar = this.zza;
        zzdwr.zza zzaVarZza = zzdwr.zzb().zza(zzdwpVar.zzb());
        for (zzdwp.zzb zzbVar : zzdwpVar.zzc()) {
            zzaVarZza.zza((zzdwr.zzb) zzdwr.zzb.zzb().zza(zzbVar.zzc().zzb()).zza(zzbVar.zzd()).zza(zzbVar.zzf()).zza(zzbVar.zze()).zzd());
        }
        return ((zzdwr) zzaVarZza.zzd()).toString();
    }

    final zzdwp zza() {
        return this.zza;
    }
}
