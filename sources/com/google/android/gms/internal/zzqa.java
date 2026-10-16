package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzt;
import java.lang.ref.WeakReference;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class zzqa implements zzt<Object> {
    private final WeakReference<zzpw> zza;
    private final String zzb;

    public zzqa(zzpw zzpwVar, String str) {
        this.zza = new WeakReference<>(zzpwVar);
        this.zzb = str;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final void zza(Object obj, Map<String, String> map) {
        zzpw zzpwVar;
        String str = map.get("ads_id");
        String str2 = map.get("eventName");
        if (TextUtils.isEmpty(str) || !this.zzb.equals(str)) {
            return;
        }
        try {
            Integer.parseInt(map.get("eventType"));
        } catch (Exception e) {
            zzahw.zzb("Parse Scion log event type error", e);
        }
        if ("_ai".equals(str2)) {
            zzpw zzpwVar2 = this.zza.get();
            if (zzpwVar2 != null) {
                zzpwVar2.zzq();
                return;
            }
            return;
        }
        if (!"_ac".equals(str2) || (zzpwVar = this.zza.get()) == null) {
            return;
        }
        zzpwVar.zzr();
    }
}
