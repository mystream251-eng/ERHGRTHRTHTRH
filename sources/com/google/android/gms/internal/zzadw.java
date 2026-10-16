package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.gmsg.zzt;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzadw implements zzt<Object> {
    private /* synthetic */ zzadt zza;

    zzadw(zzadt zzadtVar) {
        this.zza = zzadtVar;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final void zza(Object obj, Map<String, String> map) {
        synchronized (this.zza.zzb) {
            if (this.zza.zze.isDone()) {
                return;
            }
            zzadz zzadzVar = new zzadz(-2, map);
            if (this.zza.zzc.equals(zzadzVar.zzh())) {
                this.zza.zze.zzb(zzadzVar);
            }
        }
    }
}
