package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.gmsg.zzt;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class zzadi implements zzt<Object> {
    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final void zza(Object obj, Map<String, String> map) {
        String str = map.get("request_id");
        String strValueOf = String.valueOf(map.get("errors"));
        zzahw.zze(strValueOf.length() != 0 ? "Invalid request: ".concat(strValueOf) : new String("Invalid request: "));
        zzada.zzf.zzb(str);
    }
}
