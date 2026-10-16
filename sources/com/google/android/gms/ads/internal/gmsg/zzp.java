package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzp implements zzt<zzaof> {
    zzp() {
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(zzaof zzaofVar, Map map) {
        zzaof zzaofVar2 = zzaofVar;
        if (((Boolean) zzlc.zzf().zza(zzoi.zzdl)).booleanValue()) {
            zzaofVar2.zzd(!Boolean.parseBoolean((String) map.get("disabled")));
        }
    }
}
