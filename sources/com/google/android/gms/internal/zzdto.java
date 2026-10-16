package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
final class zzdto implements zzdsz<zzdsy> {
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.google.android.gms.internal.zzdsz
    public final zzdtf<zzdsy> zza(String str, String str2, int i) throws GeneralSecurityException {
        zzdtf<zzdsy> zzdtqVar;
        String lowerCase = str2.toLowerCase();
        byte b = -1;
        if (((lowerCase.hashCode() == 2989895 && lowerCase.equals("aead")) ? (byte) 0 : (byte) -1) != 0) {
            throw new GeneralSecurityException(String.format("No support for primitive '%s'.", str2));
        }
        switch (str.hashCode()) {
            case 360753376:
                if (str.equals("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key")) {
                    b = 3;
                }
                break;
            case 1215885937:
                if (str.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
                    b = 0;
                }
                break;
            case 1469984853:
                if (str.equals("type.googleapis.com/google.crypto.tink.KmsAeadKey")) {
                    b = 4;
                }
                break;
            case 1797113348:
                if (str.equals("type.googleapis.com/google.crypto.tink.AesEaxKey")) {
                    b = 1;
                }
                break;
            case 1855890991:
                if (str.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
                    b = 2;
                }
                break;
            case 2079211877:
                if (str.equals("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey")) {
                    b = 5;
                }
                break;
        }
        if (b == 0) {
            zzdtqVar = new zzdtq();
        } else if (b == 1) {
            zzdtqVar = new zzdts();
        } else if (b == 2) {
            zzdtqVar = new zzdtt();
        } else if (b == 3) {
            zzdtqVar = new zzdtu();
        } else if (b == 4) {
            zzdtqVar = new zzdtv();
        } else {
            if (b != 5) {
                throw new GeneralSecurityException(String.format("No support for primitive 'Aead' with key type '%s'.", str));
            }
            zzdtqVar = new zzdtx();
        }
        if (zzdtqVar.zza() >= i) {
            return zzdtqVar;
        }
        throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", str, Integer.valueOf(i)));
    }
}
