package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzamc<T> extends zzamd<T> {
    private final T zza;

    private zzamc(T t) {
        this.zza = t;
    }

    public static <T> zzamc<T> zza(T t) {
        return new zzamc<>(t);
    }

    public final void zza() {
        zzb(this.zza);
    }
}
