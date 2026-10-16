package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzyf {
    private final zzaof zza;
    private final boolean zzb;
    private final String zzc;

    public zzyf(zzaof zzaofVar, Map<String, String> map) {
        this.zza = zzaofVar;
        this.zzc = map.get("forceOrientation");
        this.zzb = map.containsKey("allowOrientationChange") ? Boolean.parseBoolean(map.get("allowOrientationChange")) : true;
    }

    public final void zza() {
        int iZzc;
        if (this.zza == null) {
            zzahw.zze("AdWebView is null");
            return;
        }
        if ("portrait".equalsIgnoreCase(this.zzc)) {
            iZzc = zzbt.zzg().zzb();
        } else if ("landscape".equalsIgnoreCase(this.zzc)) {
            iZzc = zzbt.zzg().zza();
        } else {
            iZzc = this.zzb ? -1 : zzbt.zzg().zzc();
        }
        this.zza.zzb(iZzc);
    }
}
