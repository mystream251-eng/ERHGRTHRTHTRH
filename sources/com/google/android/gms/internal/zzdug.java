package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzdug {
    static final /* synthetic */ int[] zza;
    static final /* synthetic */ int[] zzb;
    static final /* synthetic */ int[] zzc;

    static {
        int[] iArr = new int[zzdvi.values().length];
        zzc = iArr;
        try {
            iArr[zzdvi.UNCOMPRESSED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzc[zzdvi.COMPRESSED.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        int[] iArr2 = new int[zzdvw.values().length];
        zzb = iArr2;
        try {
            iArr2[zzdvw.NIST_P256.ordinal()] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            zzb[zzdvw.NIST_P384.ordinal()] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zzb[zzdvw.NIST_P521.ordinal()] = 3;
        } catch (NoSuchFieldError unused5) {
        }
        int[] iArr3 = new int[zzdvy.values().length];
        zza = iArr3;
        try {
            iArr3[zzdvy.SHA1.ordinal()] = 1;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            zza[zzdvy.SHA256.ordinal()] = 2;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            zza[zzdvy.SHA512.ordinal()] = 3;
        } catch (NoSuchFieldError unused8) {
        }
    }
}
