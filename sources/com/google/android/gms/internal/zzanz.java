package com.google.android.gms.internal;

import androidx.core.app.NotificationCompat;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzanz implements Runnable {
    private /* synthetic */ String zza;
    private /* synthetic */ String zzb;
    private /* synthetic */ int zzc;
    private /* synthetic */ int zzd;
    private /* synthetic */ boolean zze = false;
    private /* synthetic */ zzany zzf;

    zzanz(zzany zzanyVar, String str, String str2, int i, int i2, boolean z) {
        this.zzf = zzanyVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = i;
        this.zzd = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        HashMap map = new HashMap();
        map.put(NotificationCompat.CATEGORY_EVENT, "precacheProgress");
        map.put("src", this.zza);
        map.put("cachedSrc", this.zzb);
        map.put("bytesLoaded", Integer.toString(this.zzc));
        map.put("totalBytes", Integer.toString(this.zzd));
        map.put("cacheReady", this.zze ? LocalSearchProvider.LIVE_CONTENTS : LocalSearchProvider.NO_LIVE_CONTENTS);
        this.zzf.zza("onPrecacheEvent", (Map<String, String>) map);
    }
}
