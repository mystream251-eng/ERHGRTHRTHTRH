package com.google.android.gms.internal;

import androidx.core.app.NotificationCompat;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzaoa implements Runnable {
    private /* synthetic */ String zza;
    private /* synthetic */ String zzb;
    private /* synthetic */ int zzc;
    private /* synthetic */ zzany zzd;

    zzaoa(zzany zzanyVar, String str, String str2, int i) {
        this.zzd = zzanyVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        HashMap map = new HashMap();
        map.put(NotificationCompat.CATEGORY_EVENT, "precacheComplete");
        map.put("src", this.zza);
        map.put("cachedSrc", this.zzb);
        map.put("totalBytes", Integer.toString(this.zzc));
        this.zzd.zza("onPrecacheEvent", (Map<String, String>) map);
    }
}
