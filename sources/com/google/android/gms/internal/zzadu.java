package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.gmsg.zzt;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzadu implements zzt<Object> {
    private /* synthetic */ zzadt zza;

    zzadu(zzadt zzadtVar) {
        this.zza = zzadtVar;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final void zza(Object obj, Map<String, String> map) {
        synchronized (this.zza.zzb) {
            if (this.zza.zze.isDone()) {
                return;
            }
            if (this.zza.zzc.equals(map.get("request_id"))) {
                zzadz zzadzVar = new zzadz(1, map);
                String strZzf = zzadzVar.zzf();
                String strValueOf = String.valueOf(zzadzVar.zzb());
                StringBuilder sb = new StringBuilder(String.valueOf(strZzf).length() + 24 + String.valueOf(strValueOf).length());
                sb.append("Invalid ");
                sb.append(strZzf);
                sb.append(" request error: ");
                sb.append(strValueOf);
                zzahw.zze(sb.toString());
                this.zza.zze.zzb(zzadzVar);
            }
        }
    }
}
