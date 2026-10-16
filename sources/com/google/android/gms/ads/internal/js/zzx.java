package com.google.android.gms.ads.internal.js;

import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzamg;

/* JADX INFO: loaded from: classes2.dex */
final class zzx implements zzamg {
    private /* synthetic */ zzae zza;
    private /* synthetic */ zzn zzb;

    zzx(zzn zznVar, zzae zzaeVar) {
        this.zzb = zznVar;
        this.zza = zzaeVar;
    }

    @Override // com.google.android.gms.internal.zzamg
    public final void zza() {
        synchronized (this.zzb.zza) {
            this.zzb.zzh = 1;
            zzahw.zza("Failed loading new engine. Marking new engine destroyable.");
            this.zza.zzc();
        }
    }
}
