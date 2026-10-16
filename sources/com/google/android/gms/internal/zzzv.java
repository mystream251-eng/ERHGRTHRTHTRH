package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzzv implements Runnable {
    private /* synthetic */ zzahd zza;
    private /* synthetic */ zzzu zzb;

    zzzv(zzzu zzzuVar, zzahd zzahdVar) {
        this.zzb = zzzuVar;
        this.zza = zzahdVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zzb(this.zza);
    }
}
