package com.google.android.gms.internal;

import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzaob implements Runnable {
    private /* synthetic */ String zza;
    private /* synthetic */ String zzb;
    private /* synthetic */ String zzc;
    private /* synthetic */ String zzd;
    private /* synthetic */ zzany zze;

    zzaob(zzany zzanyVar, String str, String str2, String str3, String str4) {
        this.zze = zzanyVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = str4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        HashMap map = new HashMap();
        map.put(NotificationCompat.CATEGORY_EVENT, "precacheCanceled");
        map.put("src", this.zza);
        if (!TextUtils.isEmpty(this.zzb)) {
            map.put("cachedSrc", this.zzb);
        }
        zzany zzanyVar = this.zze;
        map.put("type", zzany.zzb(this.zzc));
        map.put("reason", this.zzc);
        if (!TextUtils.isEmpty(this.zzd)) {
            map.put("message", this.zzd);
        }
        this.zze.zza("onPrecacheEvent", (Map<String, String>) map);
    }
}
