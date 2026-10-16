package com.google.android.gms.ads.internal.js;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class zzg implements Runnable {
    private /* synthetic */ String zza;
    private /* synthetic */ JSONObject zzb;
    private /* synthetic */ zze zzc;

    zzg(zze zzeVar, String str, JSONObject jSONObject) {
        this.zzc = zzeVar;
        this.zza = str;
        this.zzb = jSONObject;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza.zzb(this.zza, this.zzb);
    }
}
