package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.zzaof;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzr implements zzt<zzaof> {
    zzr() {
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(zzaof zzaofVar, Map map) {
        zzaof zzaofVar2 = zzaofVar;
        if (map.keySet().contains("start")) {
            zzaofVar2.zzv().zzh();
        } else if (map.keySet().contains("stop")) {
            zzaofVar2.zzv().zzi();
        } else if (map.keySet().contains("cancel")) {
            zzaofVar2.zzv().zzj();
        }
    }
}
