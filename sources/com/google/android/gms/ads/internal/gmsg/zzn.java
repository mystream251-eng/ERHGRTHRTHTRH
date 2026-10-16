package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.zzahw;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzn implements zzt<Object> {
    zzn() {
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final void zza(Object obj, Map<String, String> map) {
        String strValueOf = String.valueOf(map.get("string"));
        zzahw.zzd(strValueOf.length() != 0 ? "Received log message: ".concat(strValueOf) : new String("Received log message: "));
    }
}
