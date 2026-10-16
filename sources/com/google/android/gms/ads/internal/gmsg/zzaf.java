package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzagd;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaf implements zzt<Object> {
    private final zzag zza;

    public zzaf(zzag zzagVar) {
        this.zza = zzagVar;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final void zza(Object obj, Map<String, String> map) {
        String str = map.get("action");
        if ("grant".equals(str)) {
            zzagd zzagdVar = null;
            try {
                int i = Integer.parseInt(map.get("amount"));
                String str2 = map.get("type");
                if (!TextUtils.isEmpty(str2)) {
                    zzagdVar = new zzagd(str2, i);
                }
            } catch (NumberFormatException e) {
                zzahw.zzc("Unable to parse reward amount.", e);
            }
            this.zza.zzb(zzagdVar);
            return;
        }
        if ("video_start".equals(str)) {
            this.zza.zzaw();
            return;
        }
        if ("video_complete".equals(str)) {
            if (((Boolean) zzlc.zzf().zza(zzoi.zzbt)).booleanValue()) {
                if (((Boolean) zzlc.zzf().zza(zzoi.zzbt)).booleanValue()) {
                    this.zza.zzax();
                }
            }
        }
    }
}
