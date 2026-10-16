package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdxh implements zzdsy {
    private final SecretKey zza;

    public zzdxh(byte[] bArr) {
        this.zza = new SecretKeySpec(bArr, "AES");
    }

    @Override // com.google.android.gms.internal.zzdsy
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length > 2147483619) {
            throw new GeneralSecurityException("plaintext too long");
        }
        byte[] bArr3 = new byte[bArr.length + 12 + 16];
        byte[] bArrZza = zzdyl.zza(12);
        System.arraycopy(bArrZza, 0, bArr3, 0, 12);
        Cipher cipherZza = zzdxx.zza.zza("AES/GCM/NoPadding");
        cipherZza.init(1, this.zza, new GCMParameterSpec(128, bArrZza));
        cipherZza.updateAAD(bArr2);
        cipherZza.doFinal(bArr, 0, bArr.length, bArr3, 12);
        return bArr3;
    }
}
