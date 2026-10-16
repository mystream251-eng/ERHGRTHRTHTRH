package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;

/* JADX INFO: loaded from: classes2.dex */
final class zzdxj extends zzdxk {
    zzdxj(byte[] bArr, int i) throws InvalidKeyException {
        super(bArr, i);
    }

    @Override // com.google.android.gms.internal.zzdym
    final int zza() {
        return 12;
    }

    @Override // com.google.android.gms.internal.zzdxk
    final int[] zza(byte[] bArr, int i) {
        int[] iArr = new int[16];
        System.arraycopy(zzdym.zza, 0, iArr, 0, zzdxk.zza.length);
        int[] iArrZza = zzdxk.zza(ByteBuffer.wrap(this.zzb.zza()));
        System.arraycopy(iArrZza, 0, iArr, 4, iArrZza.length);
        iArr[12] = i;
        System.arraycopy(zza(ByteBuffer.wrap(bArr)), 0, iArr, 13, 3);
        return iArr;
    }
}
