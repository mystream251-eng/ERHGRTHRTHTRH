package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public class zzamd<T> implements zzalt<T> {
    private T zzb;
    private Throwable zzc;
    private boolean zzd;
    private boolean zze;
    private final Object zza = new Object();
    private final zzalu zzf = new zzalu();

    private final boolean zza() {
        return this.zzc != null || this.zzd;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        if (!z) {
            return false;
        }
        synchronized (this.zza) {
            if (zza()) {
                return false;
            }
            this.zze = true;
            this.zzd = true;
            this.zza.notifyAll();
            this.zzf.zza();
            return true;
        }
    }

    @Override // java.util.concurrent.Future
    public T get() throws ExecutionException, CancellationException, InterruptedException {
        T t;
        synchronized (this.zza) {
            if (!zza()) {
                try {
                    this.zza.wait();
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            if (this.zzc != null) {
                throw new ExecutionException(this.zzc);
            }
            if (this.zze) {
                throw new CancellationException("SettableFuture was cancelled.");
            }
            t = this.zzb;
        }
        return t;
    }

    @Override // java.util.concurrent.Future
    public T get(long j, TimeUnit timeUnit) throws ExecutionException, CancellationException, InterruptedException, TimeoutException {
        T t;
        synchronized (this.zza) {
            if (!zza()) {
                try {
                    long millis = timeUnit.toMillis(j);
                    if (millis != 0) {
                        this.zza.wait(millis);
                    }
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            if (this.zzc != null) {
                throw new ExecutionException(this.zzc);
            }
            if (!this.zzd) {
                throw new TimeoutException("SettableFuture timed out.");
            }
            if (this.zze) {
                throw new CancellationException("SettableFuture was cancelled.");
            }
            t = this.zzb;
        }
        return t;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        boolean z;
        synchronized (this.zza) {
            z = this.zze;
        }
        return z;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        boolean zZza;
        synchronized (this.zza) {
            zZza = zza();
        }
        return zZza;
    }

    @Override // com.google.android.gms.internal.zzalt
    public final void zza(Runnable runnable, Executor executor) {
        this.zzf.zza(runnable, executor);
    }

    public final void zza(Throwable th) {
        synchronized (this.zza) {
            if (this.zze) {
                return;
            }
            if (zza()) {
                zzbt.zzi().zza(new IllegalStateException("Provided SettableFuture with multiple values."), "SettableFuture.setException");
                return;
            }
            this.zzc = th;
            this.zza.notifyAll();
            this.zzf.zza();
        }
    }

    public final void zzb(T t) {
        synchronized (this.zza) {
            if (this.zze) {
                return;
            }
            if (zza()) {
                zzbt.zzi().zza(new IllegalStateException("Provided SettableFuture with multiple values."), "SettableFuture.set");
                return;
            }
            this.zzd = true;
            this.zzb = t;
            this.zza.notifyAll();
            this.zzf.zza();
        }
    }
}
