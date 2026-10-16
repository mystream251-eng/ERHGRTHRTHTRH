package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaki {
    private static zzaki zzb;
    String zza;

    private zzaki() {
    }

    public static zzaki zza() {
        if (zzb == null) {
            zzb = new zzaki();
        }
        return zzb;
    }
}
