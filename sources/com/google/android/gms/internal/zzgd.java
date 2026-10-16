package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.gmsg.zzt;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzgd implements zzgq {
    private final zzfv zza;
    private final zzaof zzb;
    private final zzt<zzaof> zzc = new zzge(this);
    private final zzt<zzaof> zzd = new zzgf(this);
    private final zzt<zzaof> zze = new zzgg(this);

    public zzgd(zzfv zzfvVar, zzaof zzaofVar) {
        this.zza = zzfvVar;
        this.zzb = zzaofVar;
        zzaofVar.zza("/updateActiveView", this.zzc);
        zzaofVar.zza("/untrackActiveViewUnit", this.zzd);
        zzaofVar.zza("/visibilityChanged", this.zze);
        String strValueOf = String.valueOf(this.zza.zza.zzd());
        zzahw.zzb(strValueOf.length() != 0 ? "Custom JS tracking ad unit: ".concat(strValueOf) : new String("Custom JS tracking ad unit: "));
    }

    @Override // com.google.android.gms.internal.zzgq
    public final void zza(JSONObject jSONObject, boolean z) {
        if (z) {
            this.zza.zzb(this);
        } else {
            this.zzb.zzb("AFMA_updateActiveView", jSONObject);
        }
    }

    @Override // com.google.android.gms.internal.zzgq
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.zzgq
    public final void zzb() {
        zzaof zzaofVar = this.zzb;
        zzaofVar.zzb("/visibilityChanged", this.zze);
        zzaofVar.zzb("/untrackActiveViewUnit", this.zzd);
        zzaofVar.zzb("/updateActiveView", this.zzc);
    }
}
