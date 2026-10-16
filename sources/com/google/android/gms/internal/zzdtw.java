package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdtw implements zzdsy {
    private static final byte[] zza = new byte[0];
    private final zzdwl zzb;
    private final zzdsy zzc;

    public zzdtw(zzdwl zzdwlVar, zzdsy zzdsyVar) {
        this.zzb = zzdwlVar;
        this.zzc = zzdsyVar;
    }

    @Override // com.google.android.gms.internal.zzdsy
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArrZzq = zzdtn.zzb(this.zzb).zzq();
        byte[] bArrZza = this.zzc.zza(bArrZzq, zza);
        byte[] bArrZza2 = ((zzdsy) zzdtn.zza(this.zzb.zzb(), bArrZzq)).zza(bArr, bArr2);
        return ByteBuffer.allocate(bArrZza.length + 4 + bArrZza2.length).putInt(bArrZza.length).put(bArrZza).put(bArrZza2).array();
    }
}
