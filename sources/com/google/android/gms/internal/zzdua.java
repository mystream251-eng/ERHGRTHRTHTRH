package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdua {
    public static final zzdxd zza = (zzdxd) ((zzdxd.zza) zzdxd.zzc().zza(zzdtp.zza)).zza(zzdta.zza("TinkHybridDecrypt", "HybridDecrypt", "EciesAeadHkdfPrivateKey", 0, true)).zza(zzdta.zza("TinkHybridEncrypt", "HybridEncrypt", "EciesAeadHkdfPublicKey", 0, true)).zza("TINK_HYBRID_1_0_0").zzd();
    private static zzdxd zzb = (zzdxd) ((zzdxd.zza) zzdxd.zzc().zza(zza)).zza("TINK_HYBRID_1_1_0").zzd();

    static {
        try {
            zzdtn.zza("TinkHybridEncrypt", new zzduc());
            zzdtn.zza("TinkHybridDecrypt", new zzdub());
            zzdtp.zza();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
