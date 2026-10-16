package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes2.dex */
final class zzajt<T> implements zzald<Throwable, T> {
    private /* synthetic */ zzajx zza;

    zzajt(zzajr zzajrVar, zzajx zzajxVar) {
        this.zza = zzajxVar;
    }

    @Override // com.google.android.gms.internal.zzald
    public final /* synthetic */ zzalt zza(Throwable th) throws Exception {
        Throwable th2 = th;
        zzahw.zzb("Error occurred while dispatching http response in getter.", th2);
        zzbt.zzi().zza(th2, "HttpGetter.deliverResponse.1");
        return zzali.zza(this.zza.zza());
    }
}
