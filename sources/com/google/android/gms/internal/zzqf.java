package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.gmsg.zzt;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzqf implements zzt<Object> {
    private /* synthetic */ zzaan zza;
    private /* synthetic */ zzqb zzb;

    zzqf(zzqb zzqbVar, zzaan zzaanVar) {
        this.zzb = zzqbVar;
        this.zza = zzaanVar;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final void zza(Object obj, Map<String, String> map) {
        zzaof zzaofVar = (zzaof) this.zzb.zza.get();
        if (zzaofVar == null) {
            this.zza.zzb("/hideOverlay", this);
        } else {
            zzaofVar.zzm().setVisibility(8);
        }
    }
}
