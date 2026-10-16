package com.google.android.gms.internal;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
final class zzalr<T> implements zzalt<T> {
    private final Throwable zza;
    private final zzalu zzb;

    zzalr(Throwable th) {
        this.zza = th;
        zzalu zzaluVar = new zzalu();
        this.zzb = zzaluVar;
        zzaluVar.zza();
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public final T get() throws ExecutionException {
        throw new ExecutionException(this.zza);
    }

    @Override // java.util.concurrent.Future
    public final T get(long j, TimeUnit timeUnit) throws ExecutionException {
        throw new ExecutionException(this.zza);
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
