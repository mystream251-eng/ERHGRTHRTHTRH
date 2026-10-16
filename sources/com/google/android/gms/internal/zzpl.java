package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzpl implements Runnable {
    private /* synthetic */ zzpk zza;

    zzpl(zzpk zzpkVar) {
        this.zza = zzpkVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.zzp != null) {
            this.zza.zzp.zzg();
            this.zza.zzp.zzf();
        }
        zzpk.zza(this.zza, null);
    }
}
