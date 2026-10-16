package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzaj;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class zzgk implements zzami<zzaj> {
    private /* synthetic */ JSONObject zza;

    zzgk(zzgh zzghVar, JSONObject jSONObject) {
        this.zza = jSONObject;
    }

    @Override // com.google.android.gms.internal.zzami
    public final /* synthetic */ void zza(zzaj zzajVar) {
        zzajVar.zzb("AFMA_updateActiveView", this.zza);
    }
}
