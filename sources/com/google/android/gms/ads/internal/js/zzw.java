package com.google.android.gms.ads.internal.js;

import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzami;

/* JADX INFO: loaded from: classes2.dex */
final class zzw implements zzami<zzc> {
    private /* synthetic */ zzae zza;
    private /* synthetic */ zzn zzb;

    zzw(zzn zznVar, zzae zzaeVar) {
        this.zzb = zznVar;
        this.zza = zzaeVar;
    }

    @Override // com.google.android.gms.internal.zzami
    public final /* synthetic */ void zza(zzc zzcVar) {
        synchronized (this.zzb.zza) {
            this.zzb.zzh = 0;
            if (this.zzb.zzg != null && this.zza != this.zzb.zzg) {
                zzahw.zza("New JS engine is loaded, marking previous one as destroyable.");
                this.zzb.zzg.zzc();
            }
            this.zzb.zzg = this.zza;
        }
    }
}
