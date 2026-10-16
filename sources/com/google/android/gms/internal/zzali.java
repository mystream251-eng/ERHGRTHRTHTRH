package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzali {
    public static <T> zzalr<T> zza(Throwable th) {
        return new zzalr<>(th);
    }

    public static <T> zzals<T> zza(T t) {
        return new zzals<>(t);
    }

    public static <V> zzalt<V> zza(zzalt<V> zzaltVar, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        final zzamd zzamdVar = new zzamd();
        zza((zzalt) zzamdVar, (Future) zzaltVar);
        final ScheduledFuture<?> scheduledFutureSchedule = scheduledExecutorService.schedule(new Runnable(zzamdVar) { // from class: com.google.android.gms.internal.zzalm
            private final zzamd zza;

            {
                this.zza = zzamdVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(new TimeoutException());
            }
        }, j, timeUnit);
        zza((zzalt) zzaltVar, zzamdVar);
        zzamdVar.zza(new Runnable(scheduledFutureSchedule) { // from class: com.google.android.gms.internal.zzaln
            private final Future zza;

            {
                this.zza = scheduledFutureSchedule;
            }

            @Override // java.lang.Runnable
            public final void run() {
                Future future = this.zza;
                if (future.isDone()) {
                    return;
                }
                future.cancel(true);
            }
        }, zzaly.zzb);
        return zzamdVar;
    }

    public static <A, B> zzalt<B> zza(final zzalt<A> zzaltVar, final zzald<? super A, ? extends B> zzaldVar, Executor executor) {
        final zzamd zzamdVar = new zzamd();
        zzaltVar.zza(new Runnable(zzamdVar, zzaldVar, zzaltVar) { // from class: com.google.android.gms.internal.zzall
            private final zzamd zza;
            private final zzald zzb;
            private final zzalt zzc;

            {
                this.zza = zzamdVar;
                this.zzb = zzaldVar;
                this.zzc = zzaltVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzali.zza(this.zza, this.zzb, this.zzc);
            }
        }, executor);
        zza((zzalt) zzamdVar, (Future) zzaltVar);
        return zzamdVar;
    }

    public static <A, B> zzalt<B> zza(final zzalt<A> zzaltVar, final zzale<A, B> zzaleVar, Executor executor) {
        final zzamd zzamdVar = new zzamd();
        zzaltVar.zza(new Runnable(zzamdVar, zzaleVar, zzaltVar) { // from class: com.google.android.gms.internal.zzalk
            private final zzamd zza;
            private final zzale zzb;
            private final zzalt zzc;

            {
                this.zza = zzamdVar;
                this.zzb = zzaleVar;
                this.zzc = zzaltVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzamd zzamdVar2 = this.zza;
                try {
                    zzamdVar2.zzb(this.zzb.zza(this.zzc.get()));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    zzamdVar2.zza(e);
                } catch (CancellationException unused) {
                    zzamdVar2.cancel(true);
                } catch (ExecutionException e2) {
                    e = e2;
                    Throwable cause = e.getCause();
                    if (cause != null) {
                        e = cause;
                    }
                    zzamdVar2.zza(e);
                } catch (Exception e3) {
                    zzamdVar2.zza(e3);
                }
            }
        }, executor);
        zza((zzalt) zzamdVar, (Future) zzaltVar);
        return zzamdVar;
    }

    public static <V, X extends Throwable> zzalt<V> zza(final zzalt<? extends V> zzaltVar, final Class<X> cls, final zzald<? super X, ? extends V> zzaldVar, final Executor executor) {
        final zzamd zzamdVar = new zzamd();
        zza((zzalt) zzamdVar, (Future) zzaltVar);
        zzaltVar.zza(new Runnable(zzamdVar, zzaltVar, cls, zzaldVar, executor) { // from class: com.google.android.gms.internal.zzalo
            private final zzamd zza;
            private final zzalt zzb;
            private final Class zzc;
            private final zzald zzd;
            private final Executor zze;

            {
                this.zza = zzamdVar;
                this.zzb = zzaltVar;
                this.zzc = cls;
                this.zzd = zzaldVar;
                this.zze = executor;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzali.zza(this.zza, this.zzb, this.zzc, this.zzd, this.zze);
            }
        }, zzaly.zzb);
        return zzamdVar;
    }

    public static <T> T zza(Future<T> future, T t) {
        try {
            return future.get(((Long) zzlc.zzf().zza(zzoi.zzdr)).longValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e = e;
            future.cancel(true);
            zzahw.zzc("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            zzbt.zzi().zza(e, "Futures.resolveFuture");
            return t;
        } catch (Exception e2) {
            e = e2;
            future.cancel(true);
            zzahw.zzb("Error waiting for future.", e);
            zzbt.zzi().zza(e, "Futures.resolveFuture");
            return t;
        }
    }

    public static <T> T zza(Future<T> future, T t, long j, TimeUnit timeUnit) {
        try {
            return future.get(j, timeUnit);
        } catch (InterruptedException e) {
            e = e;
            future.cancel(true);
            zzahw.zzc("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            zzbt.zzi().zza(e, "Futures.resolveFuture");
            return t;
        } catch (Exception e2) {
            e = e2;
            future.cancel(true);
            zzahw.zzb("Error waiting for future.", e);
            zzbt.zzi().zza(e, "Futures.resolveFuture");
            return t;
        }
    }

    public static <V> void zza(final zzalt<V> zzaltVar, final zzalf<V> zzalfVar, Executor executor) {
        zzaltVar.zza(new Runnable(zzalfVar, zzaltVar) { // from class: com.google.android.gms.internal.zzalj
            private final zzalf zza;
            private final zzalt zzb;

            {
                this.zza = zzalfVar;
                this.zzb = zzaltVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzalf zzalfVar2 = this.zza;
                try {
                    zzalfVar2.zza(this.zzb.get());
                } catch (InterruptedException e) {
                    e = e;
                    Thread.currentThread().interrupt();
                    zzalfVar2.zza(e);
                } catch (ExecutionException e2) {
                    e = e2.getCause();
                    zzalfVar2.zza(e);
                } catch (Exception e3) {
                    e = e3;
                    zzalfVar2.zza(e);
                }
            }
        }, executor);
    }

    private static <V> void zza(final zzalt<? extends V> zzaltVar, final zzamd<V> zzamdVar) {
        zza((zzalt) zzamdVar, (Future) zzaltVar);
        zzaltVar.zza(new Runnable(zzamdVar, zzaltVar) { // from class: com.google.android.gms.internal.zzalp
            private final zzamd zza;
            private final zzalt zzb;

            {
                this.zza = zzamdVar;
                this.zzb = zzaltVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                Throwable e;
                zzamd zzamdVar2 = this.zza;
                try {
                    zzamdVar2.zzb(this.zzb.get());
                } catch (InterruptedException e2) {
                    e = e2;
                    Thread.currentThread().interrupt();
                    zzamdVar2.zza(e);
                } catch (ExecutionException e3) {
                    e = e3.getCause();
                    zzamdVar2.zza(e);
                } catch (Exception e4) {
                    zzamdVar2.zza(e4);
                }
            }
        }, zzaly.zzb);
    }

    private static <A, B> void zza(final zzalt<A> zzaltVar, final Future<B> future) {
        zzaltVar.zza(new Runnable(zzaltVar, future) { // from class: com.google.android.gms.internal.zzalq
            private final zzalt zza;
            private final Future zzb;

            {
                this.zza = zzaltVar;
                this.zzb = future;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzalt zzaltVar2 = this.zza;
                Future future2 = this.zzb;
                if (zzaltVar2.isCancelled()) {
                    future2.cancel(true);
                }
            }
        }, zzaly.zzb);
    }

    static final /* synthetic */ void zza(zzamd zzamdVar, zzald zzaldVar, zzalt zzaltVar) {
        if (zzamdVar.isCancelled()) {
            return;
        }
        try {
            zza(zzaldVar.zza(zzaltVar.get()), zzamdVar);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            zzamdVar.zza(e);
        } catch (CancellationException unused) {
            zzamdVar.cancel(true);
        } catch (ExecutionException e2) {
            zzamdVar.zza(e2.getCause());
        } catch (Exception e3) {
            zzamdVar.zza(e3);
        }
    }

    /* JADX WARN: Code duplicated, block: B:12:0x001e  */
    /* JADX WARN: Code duplicated, block: B:14:0x002a  */
    static final /* synthetic */ void zza(zzamd zzamdVar, zzalt zzaltVar, Class cls, zzald zzaldVar, Executor executor) {
        try {
            zzamdVar.zzb(zzaltVar.get());
        } catch (InterruptedException e) {
            e = e;
            Thread.currentThread().interrupt();
            if (cls.isInstance(e)) {
                zza(zza(zza(e), zzaldVar, executor), zzamdVar);
            } else {
                zzamdVar.zza(e);
            }
        } catch (ExecutionException e2) {
            e = e2.getCause();
            if (cls.isInstance(e)) {
                zza(zza(zza(e), zzaldVar, executor), zzamdVar);
            } else {
                zzamdVar.zza(e);
            }
        } catch (Exception e3) {
            e = e3;
            if (cls.isInstance(e)) {
                zza(zza(zza(e), zzaldVar, executor), zzamdVar);
            } else {
                zzamdVar.zza(e);
            }
        }
    }
}
