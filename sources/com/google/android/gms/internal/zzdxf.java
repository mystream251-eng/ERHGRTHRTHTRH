package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdxf implements zzdyi {
    private final SecretKeySpec zza;
    private final int zzb;
    private final int zzc;

    public zzdxf(byte[] bArr, int i) throws GeneralSecurityException {
        this.zza = new SecretKeySpec(bArr, "AES");
        int blockSize = zzdxx.zza.zza("AES/CTR/NoPadding").getBlockSize();
        this.zzc = blockSize;
        if (i < 12 || i > blockSize) {
            throw new GeneralSecurityException("invalid IV size");
        }
        this.zzb = i;
    }

    @Override // com.google.android.gms.internal.zzdyi
    public final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        int length = bArr.length;
        int i = this.zzb;
        if (length > Integer.MAX_VALUE - i) {
            int i2 = Integer.MAX_VALUE - this.zzb;
            StringBuilder sb = new StringBuilder(43);
            sb.append("plaintext length can not exceed ");
            sb.append(i2);
            throw new GeneralSecurityException(sb.toString());
        }
        byte[] bArr2 = new byte[bArr.length + i];
        byte[] bArrZza = zzdyl.zza(i);
        System.arraycopy(bArrZza, 0, bArr2, 0, this.zzb);
        int length2 = bArr.length;
        int i3 = this.zzb;
        Cipher cipherZza = zzdxx.zza.zza("AES/CTR/NoPadding");
        byte[] bArr3 = new byte[this.zzc];
        System.arraycopy(bArrZza, 0, bArr3, 0, this.zzb);
        cipherZza.init(1, this.zza, new IvParameterSpec(bArr3));
        if (cipherZza.doFinal(bArr, 0, length2, bArr2, i3) == length2) {
            return bArr2;
        }
        throw new GeneralSecurityException("stored output's length does not match input's length");
    }
}
