package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.zzahw;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class zzv implements Runnable {
    private /* synthetic */ JSONObject zza;
    private /* synthetic */ zzu zzb;

    zzv(zzu zzuVar, JSONObject jSONObject) {
        this.zzb = zzuVar;
        this.zza = jSONObject;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zza("fetchHttpRequestCompleted", this.zza);
        zzahw.zzb("Dispatched http response.");
    }
}
