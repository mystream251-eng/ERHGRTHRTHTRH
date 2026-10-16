package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzyj {
    private final boolean zza;
    private final boolean zzb;
    private final boolean zzc;
    private final boolean zzd;
    private final boolean zze;

    private zzyj(zzyl zzylVar) {
        this.zza = zzylVar.zza;
        this.zzb = zzylVar.zzb;
        this.zzc = zzylVar.zzc;
        this.zzd = zzylVar.zzd;
        this.zze = zzylVar.zze;
    }

    public final JSONObject zza() {
        try {
            return new JSONObject().put("sms", this.zza).put("tel", this.zzb).put("calendar", this.zzc).put("storePicture", this.zzd).put("inlineVideo", this.zze);
        } catch (JSONException e) {
            zzahw.zzb("Error occured while obtaining the MRAID capabilities.", e);
            return null;
        }
    }
}
