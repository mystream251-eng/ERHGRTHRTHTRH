package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzzk implements Runnable {
    private /* synthetic */ zzahd zza;
    private /* synthetic */ zzzi zzb;

    zzzk(zzzi zzziVar, zzahd zzahdVar) {
        this.zzb = zzziVar;
        this.zza = zzahdVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb.zzc) {
            zzzi zzziVar = this.zzb;
            zzziVar.zza.zzb(this.zza);
        }
    }
}
