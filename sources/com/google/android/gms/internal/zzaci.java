package com.google.android.gms.internal;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaci extends zzacr {
    private final WeakReference<zzabx> zza;

    public zzaci(zzabx zzabxVar) {
        this.zza = new WeakReference<>(zzabxVar);
    }

    @Override // com.google.android.gms.internal.zzacq
    public final void zza(zzacj zzacjVar) {
        zzabx zzabxVar = this.zza.get();
        if (zzabxVar != null) {
            zzabxVar.zza(zzacjVar);
        }
    }
}
