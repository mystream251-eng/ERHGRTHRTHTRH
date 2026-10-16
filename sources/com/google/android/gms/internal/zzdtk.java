package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdtk {
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.internal.zzfie */
    @Deprecated
    public static final zzdtg zza(byte[] bArr) throws GeneralSecurityException {
        try {
            zzdwp zzdwpVarZza = zzdwp.zza(bArr);
            for (zzdwp.zzb zzbVar : zzdwpVarZza.zzc()) {
                if (zzbVar.zzc().zzd() == zzdwg.zzb.UNKNOWN_KEYMATERIAL || zzbVar.zzc().zzd() == zzdwg.zzb.SYMMETRIC || zzbVar.zzc().zzd() == zzdwg.zzb.ASYMMETRIC_PRIVATE) {
                    throw new GeneralSecurityException("keyset contains secret key material");
                }
            }
            return zzdtg.zza(zzdwpVarZza);
        } catch (zzfie unused) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }
}
