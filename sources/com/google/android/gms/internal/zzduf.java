package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes2.dex */
final class zzduf {
    public static zzdxu zza(zzdvw zzdvwVar) throws GeneralSecurityException {
        int i = zzdug.zzb[zzdvwVar.ordinal()];
        if (i == 1) {
            return zzdxu.NIST_P256;
        }
        if (i == 2) {
            return zzdxu.NIST_P384;
        }
        if (i == 3) {
            return zzdxu.NIST_P521;
        }
        String strValueOf = String.valueOf(zzdvwVar);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 20);
        sb.append("unknown curve type: ");
        sb.append(strValueOf);
        throw new GeneralSecurityException(sb.toString());
    }

    public static zzdxv zza(zzdvi zzdviVar) throws GeneralSecurityException {
        int i = zzdug.zzc[zzdviVar.ordinal()];
        if (i == 1) {
            return zzdxv.UNCOMPRESSED;
        }
        if (i == 2) {
            return zzdxv.COMPRESSED;
        }
        String strValueOf = String.valueOf(zzdviVar);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 22);
        sb.append("unknown point format: ");
        sb.append(strValueOf);
        throw new GeneralSecurityException(sb.toString());
    }

    public static String zza(zzdvy zzdvyVar) throws NoSuchAlgorithmException {
        int i = zzdug.zza[zzdvyVar.ordinal()];
        if (i == 1) {
            return "HmacSha1";
        }
        if (i == 2) {
            return "HmacSha256";
        }
        if (i == 3) {
            return "HmacSha512";
        }
        String strValueOf = String.valueOf(zzdvyVar);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 27);
        sb.append("hash unsupported for HMAC: ");
        sb.append(strValueOf);
        throw new NoSuchAlgorithmException(sb.toString());
    }

    public static void zza(zzdvo zzdvoVar) throws GeneralSecurityException {
        zzdxs.zza(zza(zzdvoVar.zzb().zzb()));
        zza(zzdvoVar.zzb().zzc());
        if (zzdvoVar.zzd() == zzdvi.UNKNOWN_FORMAT) {
            throw new GeneralSecurityException("unknown EC point format");
        }
        zzdtn.zza(zzdvoVar.zzc().zzb());
    }
}
