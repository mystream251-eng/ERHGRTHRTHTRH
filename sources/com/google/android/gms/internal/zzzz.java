package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzzz implements Runnable {
    private /* synthetic */ zzamd zza;
    private /* synthetic */ String zzb;
    private /* synthetic */ zzzy zzc;

    zzzz(zzzy zzzyVar, zzamd zzamdVar, String str) {
        this.zzc = zzzyVar;
        this.zza = zzamdVar;
        this.zzb = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzb(this.zzc.zzd.zzaz().get(this.zzb));
    }
}
