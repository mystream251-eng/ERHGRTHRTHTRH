package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzaa;

/* JADX INFO: loaded from: classes2.dex */
final class zzvk implements zzamg {
    private /* synthetic */ zzamd zza;
    private /* synthetic */ zzaa zzb;

    zzvk(zzvi zzviVar, zzamd zzamdVar, zzaa zzaaVar) {
        this.zza = zzamdVar;
        this.zzb = zzaaVar;
    }

    @Override // com.google.android.gms.internal.zzamg
    public final void zza() {
        this.zza.zza(new zzuw("Unable to obtain a JavascriptEngine."));
        this.zzb.zza();
    }
}
