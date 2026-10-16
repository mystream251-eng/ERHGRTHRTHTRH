package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdul {
    public static final zzdxd zza = (zzdxd) zzdxd.zzc().zza("TINK_MAC_1_0_0").zza(zzdta.zza("TinkMac", "Mac", "HmacKey", 0, true)).zzd();
    private static zzdxd zzb = (zzdxd) ((zzdxd.zza) zzdxd.zzc().zza(zza)).zza("TINK_MAC_1_1_0").zzd();

    static {
        try {
            zza();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzdtn.zza("TinkMac", new zzduk());
    }
}
