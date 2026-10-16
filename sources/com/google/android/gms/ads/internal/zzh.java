package com.google.android.gms.ads.internal;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
final class zzh implements Callable<String> {
    private /* synthetic */ zzd zza;

    zzh(zzd zzdVar) {
        this.zza = zzdVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        return this.zza.zze.zzd.zza().zza(this.zza.zze.zzc);
    }
}
