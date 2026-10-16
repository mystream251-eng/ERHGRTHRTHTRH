package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzaa;
import com.google.android.gms.ads.internal.js.zzaj;

/* JADX INFO: loaded from: classes2.dex */
final class zzvj implements zzami<zzaj> {
    private /* synthetic */ zzaa zza;
    private /* synthetic */ Object zzb;
    private /* synthetic */ zzamd zzc;
    private /* synthetic */ zzvi zzd;

    zzvj(zzvi zzviVar, zzaa zzaaVar, Object obj, zzamd zzamdVar) {
        this.zzd = zzviVar;
        this.zza = zzaaVar;
        this.zzb = obj;
        this.zzc = zzamdVar;
    }

    @Override // com.google.android.gms.internal.zzami
    public final /* synthetic */ void zza(zzaj zzajVar) {
        this.zzd.zza(this.zza, zzajVar, this.zzb, this.zzc);
    }
}
