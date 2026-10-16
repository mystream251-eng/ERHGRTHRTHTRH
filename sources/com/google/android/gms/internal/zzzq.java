package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzzq implements Runnable {
    private /* synthetic */ zzahd zza;
    private /* synthetic */ zzzp zzb;

    zzzq(zzzp zzzpVar, zzahd zzahdVar) {
        this.zzb = zzzpVar;
        this.zza = zzahdVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zzb(this.zza);
    }
}
