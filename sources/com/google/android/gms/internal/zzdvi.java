package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
public enum zzdvi implements zzfia {
    UNKNOWN_FORMAT(0),
    UNCOMPRESSED(1),
    COMPRESSED(2),
    UNRECOGNIZED(-1);

    private static final zzfib<zzdvi> zze = new zzfib<zzdvi>() { // from class: com.google.android.gms.internal.zzdvj
    };
    private final int zzf;

    zzdvi(int i) {
        this.zzf = i;
    }

    public static zzdvi zza(int i) {
        if (i == 0) {
            return UNKNOWN_FORMAT;
        }
        if (i == 1) {
            return UNCOMPRESSED;
        }
        if (i != 2) {
            return null;
        }
        return COMPRESSED;
    }

    public final int zza() {
        if (this != UNRECOGNIZED) {
            return this.zzf;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
