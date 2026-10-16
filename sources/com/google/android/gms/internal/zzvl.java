package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.gmsg.zzae;
import com.google.android.gms.ads.internal.js.zzaa;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class zzvl implements zzae {
    private final zzaa zza;
    private final zzamd zzb;
    private /* synthetic */ zzvi zzc;

    public zzvl(zzvi zzviVar, zzaa zzaaVar, zzamd zzamdVar) {
        this.zzc = zzviVar;
        this.zza = zzaaVar;
        this.zzb = zzamdVar;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzae
    public final void zza(String str) {
        try {
            if (str == null) {
                this.zzb.zza(new zzuw());
            } else {
                this.zzb.zza(new zzuw(str));
            }
        } catch (IllegalStateException unused) {
        } finally {
            this.zza.zza();
        }
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzae
    public final void zza(JSONObject jSONObject) {
        try {
            try {
                this.zzb.zzb(this.zzc.zza.zza(jSONObject));
            } catch (IllegalStateException unused) {
            } catch (JSONException e) {
                this.zzb.zzb(e);
            }
        } finally {
            this.zza.zza();
        }
    }
}
