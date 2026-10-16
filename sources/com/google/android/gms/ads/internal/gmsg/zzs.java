package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.zzaof;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzs implements zzt<zzaof> {
    zzs() {
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(zzaof zzaofVar, Map map) {
        zzaof zzaofVar2 = zzaofVar;
        if (map.keySet().contains("start")) {
            zzaofVar2.zze(true);
        }
        if (map.keySet().contains("stop")) {
            zzaofVar2.zze(false);
        }
    }
}
