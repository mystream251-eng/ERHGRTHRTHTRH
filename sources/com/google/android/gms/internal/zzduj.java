package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzduj {
    static final /* synthetic */ int[] zza;

    static {
        int[] iArr = new int[zzdvy.values().length];
        zza = iArr;
        try {
            iArr[zzdvy.SHA1.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zza[zzdvy.SHA256.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zza[zzdvy.SHA512.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}
