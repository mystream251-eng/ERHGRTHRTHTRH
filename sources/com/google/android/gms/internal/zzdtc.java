package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzdtc {
    static final /* synthetic */ int[] zza;

    static {
        int[] iArr = new int[zzdxb.values().length];
        zza = iArr;
        try {
            iArr[zzdxb.LEGACY.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zza[zzdxb.CRUNCHY.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zza[zzdxb.TINK.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            zza[zzdxb.RAW.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
