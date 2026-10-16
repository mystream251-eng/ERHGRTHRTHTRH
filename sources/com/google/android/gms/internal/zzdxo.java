package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdxo implements zzdte {
    private static final byte[] zza = new byte[0];
    private final zzdxq zzb;
    private final String zzc;
    private final byte[] zzd;
    private final zzdxv zze;
    private final zzdxm zzf;

    public zzdxo(ECPublicKey eCPublicKey, byte[] bArr, String str, zzdxv zzdxvVar, zzdxm zzdxmVar) throws GeneralSecurityException {
        zzdxs.zza(eCPublicKey.getW(), eCPublicKey.getParams().getCurve());
        this.zzb = new zzdxq(eCPublicKey);
        this.zzd = bArr;
        this.zzc = str;
        this.zze = zzdxvVar;
        this.zzf = zzdxmVar;
    }

    @Override // com.google.android.gms.internal.zzdte
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        zzdxr zzdxrVarZza = this.zzb.zza(this.zzc, this.zzd, bArr2, this.zzf.zza(), this.zze);
        byte[] bArrZza = this.zzf.zza(zzdxrVarZza.zzb()).zza(bArr, zza);
        byte[] bArrZza2 = zzdxrVarZza.zza();
        return ByteBuffer.allocate(bArrZza2.length + bArrZza.length).put(bArrZza2).put(bArrZza).array();
    }
}
