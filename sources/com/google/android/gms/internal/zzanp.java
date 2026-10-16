package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
final class zzanp implements Runnable {
    private zzanb zza;
    private boolean zzb = false;

    zzanp(zzanb zzanbVar) {
        this.zza = zzanbVar;
    }

    private final void zzc() {
        zzaij.zza.removeCallbacks(this);
        zzaij.zza.postDelayed(this, 250L);
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzb) {
            return;
        }
        this.zza.zzo();
        zzc();
    }

    public final void zza() {
        this.zzb = true;
    }

    public final void zzb() {
        this.zzb = false;
        zzc();
    }
}
