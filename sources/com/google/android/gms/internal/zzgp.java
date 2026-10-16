package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.ads.internal.js.zzaj;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzgp implements zzt<zzaj> {
    private /* synthetic */ zzgh zza;

    zzgp(zzgh zzghVar) {
        this.zza = zzghVar;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(zzaj zzajVar, Map map) {
        zzaj zzajVar2 = zzajVar;
        if (this.zza.zza.zza((Map<String, String>) map)) {
            this.zza.zzc.zza(zzajVar2, map);
        }
    }
}
