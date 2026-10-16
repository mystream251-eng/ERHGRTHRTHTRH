package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbt;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaen implements zzadr {
    private zzux<JSONObject, JSONObject> zza;
    private zzux<JSONObject, JSONObject> zzb;

    public zzaen(Context context) {
        this.zza = zzbt.zzs().zza(context, zzala.zza()).zza("google.afma.request.getAdDictionary", zzvc.zza, zzvc.zza);
        this.zzb = zzbt.zzs().zza(context, zzala.zza()).zza("google.afma.sdkConstants.getSdkConstants", zzvc.zza, zzvc.zza);
    }

    @Override // com.google.android.gms.internal.zzadr
    public final zzux<JSONObject, JSONObject> zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.zzadr
    public final zzux<JSONObject, JSONObject> zzb() {
        return this.zzb;
    }
}
