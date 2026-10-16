package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaof;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzl implements zzt<zzaof> {
    zzl() {
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(zzaof zzaofVar, Map map) {
        zzaof zzaofVar2 = zzaofVar;
        com.google.android.gms.ads.internal.overlay.zzd zzdVarZzr = zzaofVar2.zzr();
        if (zzdVarZzr != null) {
            zzdVarZzr.zza();
            return;
        }
        com.google.android.gms.ads.internal.overlay.zzd zzdVarZzs = zzaofVar2.zzs();
        if (zzdVarZzs != null) {
            zzdVarZzs.zza();
        } else {
            zzahw.zze("A GMSG tried to close something that wasn't an overlay.");
        }
    }
}
