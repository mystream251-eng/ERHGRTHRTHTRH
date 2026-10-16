package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
public enum zzdvw implements zzfia {
    UNKNOWN_CURVE(0),
    NIST_P224(1),
    NIST_P256(2),
    NIST_P384(3),
    NIST_P521(4),
    UNRECOGNIZED(-1);

    private static final zzfib<zzdvw> zzg = new zzfib<zzdvw>() { // from class: com.google.android.gms.internal.zzdvx
    };
    private final int zzh;

    zzdvw(int i) {
        this.zzh = i;
    }

    public static zzdvw zza(int i) {
        if (i == 0) {
            return UNKNOWN_CURVE;
        }
        if (i == 1) {
            return NIST_P224;
        }
        if (i == 2) {
            return NIST_P256;
        }
        if (i == 3) {
            return NIST_P384;
        }
        if (i != 4) {
            return null;
        }
        return NIST_P521;
    }

    public final int zza() {
        if (this != UNRECOGNIZED) {
            return this.zzh;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
