package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.gmsg.zzt;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzaaa implements zzt<Object> {
    private /* synthetic */ zzpo zza;
    private /* synthetic */ zzzy zzb;

    zzaaa(zzzy zzzyVar, zzpo zzpoVar) {
        this.zzb = zzzyVar;
        this.zza = zzpoVar;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final void zza(Object obj, Map<String, String> map) {
        this.zzb.zza(this.zza, map.get("asset"));
    }
}
