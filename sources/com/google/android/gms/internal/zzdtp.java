package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdtp {
    public static final zzdxd zza = (zzdxd) ((zzdxd.zza) zzdxd.zzc().zza(zzdul.zza)).zza(zzdta.zza("TinkAead", "Aead", "AesCtrHmacAeadKey", 0, true)).zza(zzdta.zza("TinkAead", "Aead", "AesEaxKey", 0, true)).zza(zzdta.zza("TinkAead", "Aead", "AesGcmKey", 0, true)).zza(zzdta.zza("TinkAead", "Aead", "ChaCha20Poly1305Key", 0, true)).zza(zzdta.zza("TinkAead", "Aead", "KmsAeadKey", 0, true)).zza(zzdta.zza("TinkAead", "Aead", "KmsEnvelopeAeadKey", 0, true)).zza("TINK_AEAD_1_0_0").zzd();
    private static zzdxd zzb = (zzdxd) ((zzdxd.zza) zzdxd.zzc().zza(zza)).zza("TINK_AEAD_1_1_0").zzd();

    static {
        try {
            zza();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzdtn.zza("TinkAead", new zzdto());
        zzdul.zza();
    }
}
