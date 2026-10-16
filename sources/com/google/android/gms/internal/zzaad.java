package com.google.android.gms.internal;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
final class zzaad implements Runnable {
    private /* synthetic */ AtomicInteger zza;
    private /* synthetic */ int zzb;
    private /* synthetic */ zzamd zzc;
    private /* synthetic */ List zzd;

    zzaad(AtomicInteger atomicInteger, int i, zzamd zzamdVar, List list) {
        this.zza = atomicInteger;
        this.zzb = i;
        this.zzc = zzamdVar;
        this.zzd = list;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.incrementAndGet() >= this.zzb) {
            try {
                this.zzc.zzb(zzzy.zzc(this.zzd));
            } catch (InterruptedException | ExecutionException e) {
                zzahw.zzc("Unable to convert list of futures to a future of list", e);
            }
        }
    }
}
