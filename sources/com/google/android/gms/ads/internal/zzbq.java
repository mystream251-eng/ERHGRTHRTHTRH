package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzcu;
import com.google.android.gms.internal.zzcv;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
final class zzbq implements Callable<zzcv> {
    private /* synthetic */ zzbn zza;

    zzbq(zzbn zzbnVar) {
        this.zza = zzbnVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ zzcv call() throws Exception {
        return new zzcv(zzcu.zza(this.zza.zza.zza, this.zza.zzd, false));
    }
}
