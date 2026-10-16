package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzps implements Runnable {
    private /* synthetic */ zzpr zza;

    zzps(zzpr zzprVar) {
        this.zza = zzprVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.zzp != null) {
            this.zza.zzp.zzg();
            this.zza.zzp.zzf();
        }
        zzpr.zza(this.zza, null);
    }
}
