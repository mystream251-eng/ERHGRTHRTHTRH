package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
public enum zzdwj implements zzfia {
    UNKNOWN_STATUS(0),
    ENABLED(1),
    DISABLED(2),
    DESTROYED(3),
    UNRECOGNIZED(-1);

    private static final zzfib<zzdwj> zzf = new zzfib<zzdwj>() { // from class: com.google.android.gms.internal.zzdwk
    };
    private final int zzg;

    zzdwj(int i) {
        this.zzg = i;
    }

    public static zzdwj zza(int i) {
        if (i == 0) {
            return UNKNOWN_STATUS;
        }
        if (i == 1) {
            return ENABLED;
        }
        if (i == 2) {
            return DISABLED;
        }
        if (i != 3) {
            return null;
        }
        return DESTROYED;
    }

    public final int zza() {
        if (this != UNRECOGNIZED) {
            return this.zzg;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
