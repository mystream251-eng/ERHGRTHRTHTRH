package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzamq implements Runnable {
    private /* synthetic */ zzamp zza;

    zzamq(zzamp zzampVar) {
        this.zza = zzampVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.zzr != null) {
            this.zza.zzr.zzb();
        }
    }
}
