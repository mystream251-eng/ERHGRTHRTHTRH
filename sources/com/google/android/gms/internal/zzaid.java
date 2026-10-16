package com.google.android.gms.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaid {
    public static final ThreadPoolExecutor zza = new ThreadPoolExecutor(20, 20, 1, TimeUnit.MINUTES, new LinkedBlockingQueue(), zza("Default"));
    private static final ThreadPoolExecutor zzb = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue(), zza("Loader"));

    static {
        zza.allowCoreThreadTimeOut(true);
        zzb.allowCoreThreadTimeOut(true);
    }

    public static zzalt<Void> zza(int i, Runnable runnable) {
        ThreadPoolExecutor threadPoolExecutor;
        Callable zzaifVar;
        if (i == 1) {
            threadPoolExecutor = zzb;
            zzaifVar = new zzaie(runnable);
        } else {
            threadPoolExecutor = zza;
            zzaifVar = new zzaif(runnable);
        }
        return zza(threadPoolExecutor, zzaifVar);
    }

    public static zzalt<Void> zza(Runnable runnable) {
        return zza(0, runnable);
    }

    public static <T> zzalt<T> zza(ExecutorService executorService, Callable<T> callable) {
        zzamd zzamdVar = new zzamd();
        try {
            zzamdVar.zza(new zzaih(zzamdVar, executorService.submit(new zzaig(zzamdVar, callable))), zzaly.zza);
        } catch (RejectedExecutionException e) {
            zzahw.zzc("Thread execution is rejected.", e);
            zzamdVar.zza(e);
        }
        return zzamdVar;
    }

    private static ThreadFactory zza(String str) {
        return new zzaii(str);
    }
}
