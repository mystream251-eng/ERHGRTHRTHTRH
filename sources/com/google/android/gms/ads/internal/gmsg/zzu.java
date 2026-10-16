package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaij;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class zzu implements Runnable {
    final /* synthetic */ com.google.android.gms.ads.internal.js.zza zza;
    private /* synthetic */ Map zzb;
    private /* synthetic */ HttpClient zzc;

    zzu(HttpClient httpClient, Map map, com.google.android.gms.ads.internal.js.zza zzaVar) {
        this.zzc = httpClient;
        this.zzb = map;
        this.zza = zzaVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        zzahw.zzb("Received Http request.");
        try {
            JSONObject jSONObjectSend = this.zzc.send(new JSONObject((String) this.zzb.get("http_request")));
            if (jSONObjectSend == null) {
                zzahw.zzc("Response should not be null.");
            } else {
                zzaij.zza.post(new zzv(this, jSONObjectSend));
            }
        } catch (Exception e) {
            zzahw.zzb("Error converting request to json.", e);
        }
    }
}
