package com.google.android.gms.internal;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaly {
    public static final Executor zza = new zzalz();
    public static final Executor zzb = new zzama();
    private static zzalx zzc = zza(zza);
    private static zzalx zzd = zza(zzb);

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.internal.zzalx, com.google.android.gms.internal.zzamb] */
    private static zzalx zza(Executor executor) {
        return new zzamb(executor, null);
    }
}
