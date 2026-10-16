package com.google.android.gms.internal;

import java.util.concurrent.Callable;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes2.dex */
final class zzoh<T> implements Callable<T> {
    private /* synthetic */ zzny zza;
    private /* synthetic */ zzog zzb;

    zzoh(zzog zzogVar, zzny zznyVar) {
        this.zzb = zzogVar;
        this.zza = zznyVar;
    }

    @Override // java.util.concurrent.Callable
    public final T call() {
        return (T) this.zza.zza(this.zzb.zzd);
    }
}
