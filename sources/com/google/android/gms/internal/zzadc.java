package com.google.android.gms.internal;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class zzadc implements Runnable {
    final /* synthetic */ JSONObject zza;
    final /* synthetic */ String zzb;
    private /* synthetic */ zzada zzc;

    zzadc(zzada zzadaVar, JSONObject jSONObject, String str) {
        this.zzc = zzadaVar;
        this.zza = jSONObject;
        this.zzb = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zzl = zzada.zzd.zzb((zzcv) null);
        this.zzc.zzl.zza(new zzadd(this), new zzade(this));
    }
}
