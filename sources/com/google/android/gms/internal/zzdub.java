package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
final class zzdub implements zzdsz<zzdtd> {
    @Override // com.google.android.gms.internal.zzdsz
    public final zzdtf<zzdtd> zza(String str, String str2, int i) throws GeneralSecurityException {
        String lowerCase = str2.toLowerCase();
        byte b = -1;
        if (((lowerCase.hashCode() == 275448849 && lowerCase.equals("hybriddecrypt")) ? (byte) 0 : (byte) -1) != 0) {
            throw new GeneralSecurityException(String.format("No support for primitive '%s'.", str2));
        }
        if (str.hashCode() == -80133005 && str.equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey")) {
            b = 0;
        }
        if (b != 0) {
            throw new GeneralSecurityException(String.format("No support for primitive 'HybridEncrypt' with key type '%s'.", str));
        }
        zzdty zzdtyVar = new zzdty();
        if (i <= 0) {
            return zzdtyVar;
        }
        throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", str, Integer.valueOf(i)));
    }
}
