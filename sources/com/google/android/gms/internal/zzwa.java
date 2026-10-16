package com.google.android.gms.internal;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
final class zzwa implements Callable<zzvw> {
    private /* synthetic */ zzvt zza;
    private /* synthetic */ zzvz zzb;

    zzwa(zzvz zzvzVar, zzvt zzvtVar) {
        this.zzb = zzvzVar;
        this.zza = zzvtVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final zzvw call() throws Exception {
        synchronized (this.zzb.zzi) {
            if (this.zzb.zzj) {
                return null;
            }
            return this.zza.zza(this.zzb.zzf, this.zzb.zzg);
        }
    }
}
