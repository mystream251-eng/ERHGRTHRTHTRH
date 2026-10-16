package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzadb implements Runnable {
    private /* synthetic */ zzahe zza;
    private /* synthetic */ zzada zzb;

    zzadb(zzada zzadaVar, zzahe zzaheVar) {
        this.zzb = zzadaVar;
        this.zza = zzaheVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzh.zza(this.zza);
        if (this.zzb.zzl != null) {
            this.zzb.zzl.zza();
            this.zzb.zzl = null;
        }
    }
}
