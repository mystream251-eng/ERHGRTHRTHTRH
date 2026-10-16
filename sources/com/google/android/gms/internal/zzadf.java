package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzadf implements Runnable {
    private /* synthetic */ zzada zza;

    zzadf(zzada zzadaVar) {
        this.zza = zzadaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.zzl != null) {
            this.zza.zzl.zza();
            this.zza.zzl = null;
        }
    }
}
