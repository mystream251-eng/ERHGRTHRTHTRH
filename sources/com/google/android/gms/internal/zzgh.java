package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.ads.internal.js.zzaa;
import com.google.android.gms.ads.internal.js.zzaj;
import com.google.android.gms.ads.internal.js.zzn;
import com.google.android.gms.ads.internal.zzbt;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzgh implements zzgq {
    private final zzfv zza;
    private final Context zzb;
    private final zzz zzc;
    private zzaa zzd;
    private boolean zze;
    private final zzt<zzaj> zzf = new zzgm(this);
    private final zzt<zzaj> zzg = new zzgn(this);
    private final zzt<zzaj> zzh = new zzgo(this);
    private final zzt<zzaj> zzi = new zzgp(this);

    public zzgh(zzfv zzfvVar, zzn zznVar, Context context) {
        this.zza = zzfvVar;
        this.zzb = context;
        this.zzc = new zzz(this.zzb);
        zzaa zzaaVarZzb = zznVar.zzb((zzcv) null);
        this.zzd = zzaaVarZzb;
        zzaaVarZzb.zza(new zzgi(this), new zzgj(this));
        String strValueOf = String.valueOf(this.zza.zza.zzd());
        zzahw.zzb(strValueOf.length() != 0 ? "Core JS tracking ad unit: ".concat(strValueOf) : new String("Core JS tracking ad unit: "));
    }

    static /* synthetic */ boolean zza(zzgh zzghVar, boolean z) {
        zzghVar.zze = true;
        return true;
    }

    final void zza(zzaj zzajVar) {
        zzajVar.zza("/updateActiveView", this.zzf);
        zzajVar.zza("/untrackActiveViewUnit", this.zzg);
        zzajVar.zza("/visibilityChanged", this.zzh);
        if (zzbt.zzaa().zza(this.zzb)) {
            zzajVar.zza("/logScionEvent", this.zzi);
        }
    }

    @Override // com.google.android.gms.internal.zzgq
    public final void zza(JSONObject jSONObject, boolean z) {
        this.zzd.zza(new zzgk(this, jSONObject), new zzamh());
    }

    @Override // com.google.android.gms.internal.zzgq
    public final boolean zza() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.zzgq
    public final void zzb() {
        this.zzd.zza(new zzgl(this), new zzamh());
        this.zzd.zza();
    }

    final void zzb(zzaj zzajVar) {
        zzajVar.zzb("/visibilityChanged", this.zzh);
        zzajVar.zzb("/untrackActiveViewUnit", this.zzg);
        zzajVar.zzb("/updateActiveView", this.zzf);
        if (zzbt.zzaa().zza(this.zzb)) {
            zzajVar.zzb("/logScionEvent", this.zzi);
        }
    }
}
