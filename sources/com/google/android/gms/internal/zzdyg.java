package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdyg {
    public static byte[] zza(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, int i) throws GeneralSecurityException {
        Mac macZza = zzdxx.zzb.zza(str);
        if (i > macZza.getMacLength() * 255) {
            throw new GeneralSecurityException("size too large");
        }
        if (bArr2 == null || bArr2.length == 0) {
            macZza.init(new SecretKeySpec(new byte[macZza.getMacLength()], str));
        } else {
            macZza.init(new SecretKeySpec(bArr2, str));
        }
        byte[] bArr4 = new byte[i];
        macZza.init(new SecretKeySpec(macZza.doFinal(bArr), str));
        byte[] bArrDoFinal = new byte[0];
        int i2 = 1;
        int length = 0;
        while (true) {
            macZza.update(bArrDoFinal);
            macZza.update(bArr3);
            macZza.update((byte) i2);
            bArrDoFinal = macZza.doFinal();
            if (bArrDoFinal.length + length >= i) {
                System.arraycopy(bArrDoFinal, 0, bArr4, length, i - length);
                return bArr4;
            }
            System.arraycopy(bArrDoFinal, 0, bArr4, length, bArrDoFinal.length);
            length += bArrDoFinal.length;
            i2++;
        }
    }
}
