package com.google.android.gms.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
final class zzalw<V> extends FutureTask<V> implements zzalt<V> {
    private final zzalu zza;

    zzalw(Runnable runnable, V v) {
        super(runnable, v);
        this.zza = new zzalu();
    }

    zzalw(Callable<V> callable) {
        super(callable);
        this.zza = new zzalu();
    }

    @Override // java.util.concurrent.FutureTask
    protected final void done() {
        this.zza.zza();
    }

    @Override // com.google.android.gms.internal.zzalt
    public final void zza(Runnable runnable, Executor executor) {
        this.zza.zza(runnable, executor);
    }
}
