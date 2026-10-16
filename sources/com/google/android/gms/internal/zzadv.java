package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.gmsg.zzt;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzadv implements zzt<Object> {
    private /* synthetic */ zzadt zza;

    zzadv(zzadt zzadtVar) {
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
                String strZze = zzadzVar.zze();
                if (strZze == null) {
                    zzahw.zze("URL missing in loadAdUrl GMSG.");
                    return;
                }
                if (strZze.contains("%40mediation_adapters%40")) {
                    String strReplaceAll = strZze.replaceAll("%40mediation_adapters%40", zzahr.zza(this.zza.zza, map.get("check_adapters"), this.zza.zzd));
                    zzadzVar.zza(strReplaceAll);
                    String strValueOf = String.valueOf(strReplaceAll);
                    zzahw.zza(strValueOf.length() != 0 ? "Ad request URL modified to ".concat(strValueOf) : new String("Ad request URL modified to "));
                }
                this.zza.zze.zzb(zzadzVar);
            }
        }
    }
}
