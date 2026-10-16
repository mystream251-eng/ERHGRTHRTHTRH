package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
public enum zzdxb implements zzfia {
    UNKNOWN_PREFIX(0),
    TINK(1),
    LEGACY(2),
    RAW(3),
    CRUNCHY(4),
    UNRECOGNIZED(-1);

    private static final zzfib<zzdxb> zzg = new zzfib<zzdxb>() { // from class: com.google.android.gms.internal.zzdxc
    };
    private final int zzh;

    zzdxb(int i) {
        this.zzh = i;
    }

    public static zzdxb zza(int i) {
        if (i == 0) {
            return UNKNOWN_PREFIX;
        }
        if (i == 1) {
            return TINK;
        }
        if (i == 2) {
            return LEGACY;
        }
        if (i == 3) {
            return RAW;
        }
        if (i != 4) {
            return null;
        }
        return CRUNCHY;
    }

    public final int zza() {
        if (this != UNRECOGNIZED) {
            return this.zzh;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
