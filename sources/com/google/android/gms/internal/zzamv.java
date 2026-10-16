package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzamv implements Runnable {
    private /* synthetic */ zzamp zza;

    zzamv(zzamp zzampVar) {
        this.zza = zzampVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.zzr != null) {
            this.zza.zzr.zzd();
            this.zza.zzr.zzf();
        }
    }
}
