package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zza implements zzt<Object> {
    private final zzb zza;

    public zza(zzb zzbVar) {
        this.zza = zzbVar;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final void zza(Object obj, Map<String, String> map) {
        String str = map.get("name");
        if (str == null) {
            zzahw.zze("App event with no name parameter.");
        } else {
            this.zza.zza(str, map.get("info"));
        }
    }
}
