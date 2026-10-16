package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.internal.zzbq;
import java.util.Arrays;
import java.util.Collections;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaer extends zzd implements zzafs {
    private static zzaer zzj;
    private boolean zzk;
    private boolean zzl;
    private zzagt zzm;
    private final zzaeo zzn;

    public zzaer(Context context, zzv zzvVar, zzko zzkoVar, zzwf zzwfVar, zzala zzalaVar) {
        super(context, zzkoVar, null, zzwfVar, zzalaVar, zzvVar);
        zzj = this;
        this.zzm = new zzagt(context, null);
        this.zzn = new zzaeo(this.zze, this.zzi, this, this);
    }

    public static zzaer zzaq() {
        return zzj;
    }

    private static zzahe zzb(zzahe zzaheVar) {
        zzahw.zza("Creating mediation ad response for non-mediated rewarded ad.");
        try {
            JSONObject jSONObjectZza = zzads.zza(zzaheVar.zzb);
            jSONObjectZza.remove("impression_urls");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, zzaheVar.zza.zze);
            return new zzahe(zzaheVar.zza, zzaheVar.zzb, new zzvq(Arrays.asList(new zzvp(jSONObjectZza.toString(), null, Arrays.asList("com.google.ads.mediation.admob.AdMobAdapter"), null, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), jSONObject.toString(), null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList(), null, -1L)), ((Long) zzlc.zzf().zza(zzoi.zzdt)).longValue(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false, "", -1L, 0, 1, null, 0, -1, -1L, false), zzaheVar.zzd, zzaheVar.zze, zzaheVar.zzf, zzaheVar.zzg, zzaheVar.zzh, zzaheVar.zzi, null);
        } catch (JSONException e) {
            zzahw.zzb("Unable to generate ad state for non-mediated rewarded video.", e);
            return new zzahe(zzaheVar.zza, zzaheVar.zzb, null, zzaheVar.zzd, 0, zzaheVar.zzf, zzaheVar.zzg, zzaheVar.zzh, zzaheVar.zzi, null);
        }
    }

    @Override // com.google.android.gms.internal.zzafs
    public final void a_(zzagd zzagdVar) {
        zzagd zzagdVarZza = this.zzn.zza(zzagdVar);
        if (zzbt.zzaa().zze(this.zze.zzc) && zzagdVarZza != null) {
            zzbt.zzaa().zza(this.zze.zzc, zzbt.zzaa().zzj(this.zze.zzc), this.zze.zzb, zzagdVarZza.zza, zzagdVarZza.zzb);
        }
        zza(zzagdVarZza);
    }

    @Override // com.google.android.gms.internal.zzafs
    public final void f_() {
        if (zzbt.zzaa().zze(this.zze.zzc)) {
            this.zzm.zza(true);
        }
        zza(this.zze.zzj, false);
        zzo();
    }

    @Override // com.google.android.gms.internal.zzafs
    public final void g_() {
        this.zzn.zzf();
        zzs();
    }

    @Override // com.google.android.gms.internal.zzafs
    public final void h_() {
        this.zzn.zzg();
        zzt();
    }

    @Override // com.google.android.gms.internal.zzafs
    public final void i_() {
        if (zzbt.zzaa().zze(this.zze.zzc)) {
            this.zzm.zza(false);
        }
        zzm();
    }

    @Override // com.google.android.gms.internal.zzafs
    public final void j_() {
        onAdClicked();
    }

    @Override // com.google.android.gms.internal.zzafs
    public final void k_() {
        zzn();
    }

    public final void zza(Context context) {
        this.zzn.zza(context);
    }

    public final void zza(zzafi zzafiVar) {
        zzbq.zzb("loadAd must be called on the main UI thread.");
        if (TextUtils.isEmpty(zzafiVar.zzb)) {
            zzahw.zze("Invalid ad unit id. Aborting.");
            zzaij.zza.post(new zzaes(this));
            return;
        }
        this.zzk = false;
        this.zze.zzb = zzafiVar.zzb;
        this.zzm.zza(zzafiVar.zzb);
        super.zzb(zzafiVar.zza);
    }

    @Override // com.google.android.gms.ads.internal.zza
    public final void zza(zzahe zzaheVar, zzov zzovVar) {
        if (zzaheVar.zze != -2) {
            zzaij.zza.post(new zzaet(this, zzaheVar));
            return;
        }
        this.zze.zzk = zzaheVar;
        if (zzaheVar.zzc == null) {
            this.zze.zzk = zzb(zzaheVar);
        }
        this.zzn.zzb();
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.ads.internal.zza
    public final boolean zza(zzahd zzahdVar, zzahd zzahdVar2) {
        return zzaeo.zza(zzahdVar, zzahdVar2);
    }

    @Override // com.google.android.gms.ads.internal.zzd
    protected final boolean zza(zzkk zzkkVar, zzahd zzahdVar, boolean z) {
        return false;
    }

    public final void zzar() {
        zzbq.zzb("showAd must be called on the main UI thread.");
        if (zzas()) {
            this.zzn.zza(this.zzl);
        } else {
            zzahw.zze("The reward video has not loaded.");
        }
    }

    public final boolean zzas() {
        zzbq.zzb("isLoaded must be called on the main UI thread.");
        return this.zze.zzg == null && this.zze.zzh == null && this.zze.zzj != null;
    }

    public final zzafy zzb(String str) {
        return this.zzn.zza(str);
    }

    @Override // com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final void zzb() {
        this.zzn.zze();
    }

    @Override // com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final void zzb(boolean z) {
        zzbq.zzb("setImmersiveMode must be called on the main UI thread.");
        this.zzl = z;
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final void zzh() {
        this.zzn.zzc();
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final void zzi() {
        this.zzn.zzd();
    }

    @Override // com.google.android.gms.ads.internal.zza
    protected final void zzm() {
        this.zze.zzj = null;
        super.zzm();
    }
}
