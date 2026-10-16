package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
final class zzduk implements zzdsz<zzdtj> {
    @Override // com.google.android.gms.internal.zzdsz
    public final zzdtf<zzdtj> zza(String str, String str2, int i) throws GeneralSecurityException {
        String lowerCase = str2.toLowerCase();
        byte b = -1;
        if (((lowerCase.hashCode() == 107855 && lowerCase.equals("mac")) ? (byte) 0 : (byte) -1) != 0) {
            throw new GeneralSecurityException(String.format("No support for primitive '%s'.", str2));
        }
        if (str.hashCode() == 836622442 && str.equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
            b = 0;
        }
        if (b != 0) {
            throw new GeneralSecurityException(String.format("No support for primitive 'Mac' with key type '%s'.", str));
        }
        zzdui zzduiVar = new zzdui();
        if (i <= 0) {
            return zzduiVar;
        }
        throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", str, Integer.valueOf(i)));
    }
}
