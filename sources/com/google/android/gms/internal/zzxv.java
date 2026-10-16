package com.google.android.gms.internal;

import com.google.ads.AdRequest;

/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzxv {
    static final /* synthetic */ int[] zza;
    private static /* synthetic */ int[] zzb;

    static {
        int[] iArr = new int[AdRequest.ErrorCode.values().length];
        zza = iArr;
        try {
            iArr[AdRequest.ErrorCode.INTERNAL_ERROR.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zza[AdRequest.ErrorCode.INVALID_REQUEST.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zza[AdRequest.ErrorCode.NETWORK_ERROR.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            zza[AdRequest.ErrorCode.NO_FILL.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        int[] iArr2 = new int[AdRequest.Gender.values().length];
        zzb = iArr2;
        try {
            iArr2[AdRequest.Gender.FEMALE.ordinal()] = 1;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            zzb[AdRequest.Gender.MALE.ordinal()] = 2;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            zzb[AdRequest.Gender.UNKNOWN.ordinal()] = 3;
        } catch (NoSuchFieldError unused7) {
        }
    }
}
