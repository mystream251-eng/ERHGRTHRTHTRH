package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
final class zzdq {
    static zzdte zza;

    static boolean zza() throws GeneralSecurityException {
        if (zza != null) {
            return true;
        }
        String str = (String) zzlc.zzf().zza(zzoi.zzed);
        if (str.length() == 0) {
            return false;
        }
        try {
            zzdtg zzdtgVarZza = zzdtk.zza(zzbv.zza(str, true));
            for (zzdwn zzdwnVar : zzdua.zza.zzb()) {
                if (zzdwnVar.zzc().isEmpty()) {
                    throw new GeneralSecurityException("Missing type_url.");
                }
                if (zzdwnVar.zzb().isEmpty()) {
                    throw new GeneralSecurityException("Missing primitive_name.");
                }
                if (zzdwnVar.zzf().isEmpty()) {
                    throw new GeneralSecurityException("Missing catalogue_name.");
                }
                zzdtn.zza(zzdwnVar.zzc(), zzdtn.zza(zzdwnVar.zzf()).zza(zzdwnVar.zzc(), zzdwnVar.zzb(), zzdwnVar.zzd()), zzdwnVar.zze());
            }
            zzdte zzdteVarZza = zzdud.zza(zzdtgVarZza);
            zza = zzdteVarZza;
            return zzdteVarZza != null;
        } catch (IllegalArgumentException | GeneralSecurityException unused) {
        }
    }
}
