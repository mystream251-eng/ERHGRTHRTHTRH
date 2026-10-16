package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdxn implements zzdtd {
    private static final byte[] zza = new byte[0];
    private final ECPrivateKey zzb;
    private final zzdxp zzc;
    private final String zzd;
    private final byte[] zze;
    private final zzdxv zzf;
    private final zzdxm zzg;

    public zzdxn(ECPrivateKey eCPrivateKey, byte[] bArr, String str, zzdxv zzdxvVar, zzdxm zzdxmVar) throws GeneralSecurityException {
        this.zzb = eCPrivateKey;
        this.zzc = new zzdxp(eCPrivateKey);
        this.zze = bArr;
        this.zzd = str;
        this.zzf = zzdxvVar;
        this.zzg = zzdxmVar;
    }
}
