package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
public enum zzdvy implements zzfia {
    UNKNOWN_HASH(0),
    SHA1(1),
    SHA224(2),
    SHA256(3),
    SHA512(4),
    UNRECOGNIZED(-1);

    private static final zzfib<zzdvy> zzg = new zzfib<zzdvy>() { // from class: com.google.android.gms.internal.zzdvz
    };
    private final int zzh;

    zzdvy(int i) {
        this.zzh = i;
    }

    public static zzdvy zza(int i) {
        if (i == 0) {
            return UNKNOWN_HASH;
        }
        if (i == 1) {
            return SHA1;
        }
        if (i == 2) {
            return SHA224;
        }
        if (i == 3) {
            return SHA256;
        }
        if (i != 4) {
            return null;
        }
        return SHA512;
    }

    public final int zza() {
        if (this != UNRECOGNIZED) {
            return this.zzh;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
