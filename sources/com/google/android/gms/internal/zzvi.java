package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.ads.internal.js.zzaa;
import com.google.android.gms.ads.internal.js.zzaj;
import com.google.android.gms.ads.internal.js.zzn;
import com.google.android.gms.ads.internal.zzbt;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzvi<I, O> implements zzux<I, O> {
    private final zzuz<O> zza;
    private final zzva<I> zzb;
    private final zzn zzc;
    private final String zzd;

    zzvi(zzn zznVar, String str, zzva<I> zzvaVar, zzuz<O> zzuzVar) {
        this.zzc = zznVar;
        this.zzd = str;
        this.zzb = zzvaVar;
        this.zza = zzuzVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzaa zzaaVar, zzaj zzajVar, I i, zzamd<O> zzamdVar) {
        try {
            zzbt.zze();
            String strZza = zzaij.zza();
            zzd.zzo.zza(strZza, new zzvl(this, zzaaVar, zzamdVar));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", strZza);
            jSONObject.put("args", this.zzb.zza(i));
            zzajVar.zzb(this.zzd, jSONObject);
        } catch (Exception e) {
            try {
                zzamdVar.zza(e);
                zzahw.zzb("Unable to invokeJavaScript", e);
            } finally {
                zzaaVar.zza();
            }
        }
    }

    @Override // com.google.android.gms.internal.zzald
    public final zzalt<O> zza(I i) throws Exception {
        return zzb(i);
    }

    @Override // com.google.android.gms.internal.zzux
    public final zzalt<O> zzb(I i) {
        zzamd zzamdVar = new zzamd();
        zzaa zzaaVarZzb = this.zzc.zzb((zzcv) null);
        zzaaVarZzb.zza(new zzvj(this, zzaaVarZzb, i, zzamdVar), new zzvk(this, zzamdVar, zzaaVarZzb));
        return zzamdVar;
    }
}
