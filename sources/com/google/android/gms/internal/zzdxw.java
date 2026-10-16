package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdxw implements zzdsy {
    private final zzdyi zza;
    private final zzdtj zzb;
    private final int zzc;

    public zzdxw(zzdyi zzdyiVar, zzdtj zzdtjVar, int i) {
        this.zza = zzdyiVar;
        this.zzb = zzdtjVar;
        this.zzc = i;
    }

    @Override // com.google.android.gms.internal.zzdsy
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArrZza = this.zza.zza(bArr);
        return zzdxi.zza(bArrZza, this.zzb.zza(zzdxi.zza(bArr2, bArrZza, Arrays.copyOf(ByteBuffer.allocate(8).putLong(((long) bArr2.length) * 8).array(), 8))));
    }
}
