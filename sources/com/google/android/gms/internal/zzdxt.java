package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzdxt {
    static final /* synthetic */ int[] zza;
    static final /* synthetic */ int[] zzb;

    static {
        int[] iArr = new int[zzdxu.values().length];
        zzb = iArr;
        try {
            iArr[zzdxu.NIST_P256.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzb[zzdxu.NIST_P384.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zzb[zzdxu.NIST_P521.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        int[] iArr2 = new int[zzdxv.values().length];
        zza = iArr2;
        try {
            iArr2[zzdxv.UNCOMPRESSED.ordinal()] = 1;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zza[zzdxv.COMPRESSED.ordinal()] = 2;
        } catch (NoSuchFieldError unused5) {
        }
    }
}
