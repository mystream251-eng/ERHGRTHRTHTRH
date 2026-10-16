package com.google.android.gms.internal;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzals<T> implements zzalt<T> {
    private final T zza;
    private final zzalu zzb;

    zzals(T t) {
        this.zza = t;
        zzalu zzaluVar = new zzalu();
        this.zzb = zzaluVar;
        zzaluVar.zza();
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public final T get() {
        return this.zza;
    }

    @Override // java.util.concurrent.Future
    public final T get(long j, TimeUnit timeUnit) {
        return this.zza;
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return true;
    }

    @Override // com.google.android.gms.internal.zzalt
    public final void zza(Runnable runnable, Executor executor) {
        this.zzb.zza(runnable, executor);
    }
}
