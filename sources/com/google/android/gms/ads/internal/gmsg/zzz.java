package com.google.android.gms.ads.internal.gmsg;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.internal.zzabh;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzz implements zzt<Object> {
    private final Context zza;

    public zzz(Context context) {
        this.zza = context;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final void zza(Object obj, Map<String, String> map) {
        if (zzbt.zzaa().zza(this.zza)) {
            zzbt.zzaa().zza(this.zza, map.get("eventName"), map.get("eventId"));
        }
    }
}
