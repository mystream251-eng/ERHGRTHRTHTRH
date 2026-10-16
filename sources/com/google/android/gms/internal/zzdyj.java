package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdyj implements zzdtj {
    private Mac zza;
    private final int zzb;
    private final String zzc;
    private final Key zzd;

    public zzdyj(String str, Key key, int i) throws GeneralSecurityException {
        if (i < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
        }
        byte b = -1;
        int iHashCode = str.hashCode();
        if (iHashCode != -1823053428) {
            if (iHashCode != 392315118) {
                if (iHashCode == 392317873 && str.equals("HMACSHA512")) {
                    b = 2;
                }
            } else if (str.equals("HMACSHA256")) {
                b = 1;
            }
        } else if (str.equals("HMACSHA1")) {
            b = 0;
        }
        if (b != 0) {
            if (b != 1) {
                if (b != 2) {
                    String strValueOf = String.valueOf(str);
                    throw new NoSuchAlgorithmException(strValueOf.length() != 0 ? "unknown Hmac algorithm: ".concat(strValueOf) : new String("unknown Hmac algorithm: "));
                }
                if (i > 64) {
                    throw new InvalidAlgorithmParameterException("tag size too big");
                }
            } else if (i > 32) {
                throw new InvalidAlgorithmParameterException("tag size too big");
            }
        } else if (i > 20) {
            throw new InvalidAlgorithmParameterException("tag size too big");
        }
        this.zzc = str;
        this.zzb = i;
        this.zzd = key;
        Mac macZza = zzdxx.zzb.zza(str);
        this.zza = macZza;
        macZza.init(key);
    }

    @Override // com.google.android.gms.internal.zzdtj
    public final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        Mac macZza;
        try {
            macZza = (Mac) this.zza.clone();
        } catch (CloneNotSupportedException unused) {
            macZza = zzdxx.zzb.zza(this.zzc);
            macZza.init(this.zzd);
        }
        macZza.update(bArr);
        byte[] bArr2 = new byte[this.zzb];
        System.arraycopy(macZza.doFinal(), 0, bArr2, 0, this.zzb);
        return bArr2;
    }
}
