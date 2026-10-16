package com.google.android.gms.ads.internal.gmsg;

import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzw implements zzt<Object> {
    private final zzx zza;

    public zzw(zzx zzxVar) {
        this.zza = zzxVar;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final void zza(Object obj, Map<String, String> map) {
        boolean zEquals = LocalSearchProvider.LIVE_CONTENTS.equals(map.get("transparentBackground"));
        boolean zEquals2 = LocalSearchProvider.LIVE_CONTENTS.equals(map.get("blur"));
        float f = 0.0f;
        try {
            if (map.get("blurRadius") != null) {
                f = Float.parseFloat(map.get("blurRadius"));
            }
        } catch (NumberFormatException e) {
            zzahw.zzb("Fail to parse float", e);
        }
        this.zza.zzd(zEquals);
        this.zza.zza(zEquals2, f);
    }
}
