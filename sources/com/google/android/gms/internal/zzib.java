package com.google.android.gms.internal;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import kotlin.UByte;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzib extends zzhs {
    private MessageDigest zzb;
    private final int zzc;
    private final int zzd;

    public zzib(int i) {
        int i2 = i / 8;
        this.zzc = i % 8 > 0 ? i2 + 1 : i2;
        this.zzd = i;
    }

    @Override // com.google.android.gms.internal.zzhs
    public final byte[] zza(String str) {
        synchronized (this.zza) {
            MessageDigest messageDigestZza = zza();
            this.zzb = messageDigestZza;
            if (messageDigestZza == null) {
                return new byte[0];
            }
            messageDigestZza.reset();
            this.zzb.update(str.getBytes(Charset.forName("UTF-8")));
            byte[] bArrDigest = this.zzb.digest();
            int length = bArrDigest.length > this.zzc ? this.zzc : bArrDigest.length;
            byte[] bArr = new byte[length];
            System.arraycopy(bArrDigest, 0, bArr, 0, length);
            if (this.zzd % 8 > 0) {
                long j = 0;
                for (int i = 0; i < length; i++) {
                    if (i > 0) {
                        j <<= 8;
                    }
                    j += (long) (bArr[i] & UByte.MAX_VALUE);
                }
                long j2 = j >>> (8 - (this.zzd % 8));
                for (int i2 = this.zzc - 1; i2 >= 0; i2--) {
                    bArr[i2] = (byte) (255 & j2);
                    j2 >>>= 8;
                }
            }
            return bArr;
        }
    }
}
