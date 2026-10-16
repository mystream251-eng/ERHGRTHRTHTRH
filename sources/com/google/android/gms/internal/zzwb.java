package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzwb implements Runnable {
    private /* synthetic */ zzalt zza;
    private /* synthetic */ zzvz zzb;

    zzwb(zzvz zzvzVar, zzalt zzaltVar) {
        this.zzb = zzvzVar;
        this.zza = zzaltVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        for (zzalt zzaltVar : this.zzb.zzk.keySet()) {
            if (zzaltVar != this.zza) {
                ((zzvt) this.zzb.zzk.get(zzaltVar)).zza();
            }
        }
    }
}
