package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzou {
    private final Map<String, zzot> zza = new HashMap();
    private final zzov zzb;

    public zzou(zzov zzovVar) {
        this.zzb = zzovVar;
    }

    public final zzov zza() {
        return this.zzb;
    }

    public final void zza(String str, zzot zzotVar) {
        this.zza.put(str, zzotVar);
    }

    public final void zza(String str, String str2, long j) {
        zzov zzovVar = this.zzb;
        zzot zzotVar = this.zza.get(str2);
        String[] strArr = {str};
        if (zzovVar != null && zzotVar != null) {
            zzovVar.zza(zzotVar, j, strArr);
        }
        Map<String, zzot> map = this.zza;
        zzov zzovVar2 = this.zzb;
        map.put(str, zzovVar2 == null ? null : zzovVar2.zza(j));
    }
}
