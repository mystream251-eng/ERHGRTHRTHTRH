package com.google.android.gms.internal;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class zzaaz implements zzalf<zzaof> {
    private /* synthetic */ String zza;
    private /* synthetic */ JSONObject zzb;

    zzaaz(zzaar zzaarVar, String str, JSONObject jSONObject) {
        this.zza = str;
        this.zzb = jSONObject;
    }

    @Override // com.google.android.gms.internal.zzalf
    public final /* synthetic */ void zza(zzaof zzaofVar) {
        zzaofVar.zza(this.zza, this.zzb);
    }

    @Override // com.google.android.gms.internal.zzalf
    public final void zza(Throwable th) {
    }
}
