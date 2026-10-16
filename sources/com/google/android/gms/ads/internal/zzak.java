package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.Window;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzacj;
import com.google.android.gms.internal.zzads;
import com.google.android.gms.internal.zzaeo;
import com.google.android.gms.internal.zzagd;
import com.google.android.gms.internal.zzagq;
import com.google.android.gms.internal.zzagt;
import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzaip;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzaop;
import com.google.android.gms.internal.zzapu;
import com.google.android.gms.internal.zzapx;
import com.google.android.gms.internal.zzaqa;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzvp;
import com.google.android.gms.internal.zzvq;
import com.google.android.gms.internal.zzwf;
import java.util.Collections;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzak extends zzi implements com.google.android.gms.ads.internal.gmsg.zzag, com.google.android.gms.ads.internal.gmsg.zzx {
    private transient boolean zzj;
    private int zzk;
    private boolean zzl;
    private float zzm;
    private boolean zzn;
    private zzagt zzo;
    private String zzp;
    private final String zzq;
    private final zzaeo zzr;

    public zzak(Context context, zzko zzkoVar, String str, zzwf zzwfVar, zzala zzalaVar, zzv zzvVar) {
        super(context, zzkoVar, str, zzwfVar, zzalaVar, zzvVar);
        this.zzk = -1;
        boolean z = false;
        this.zzj = false;
        if (zzkoVar != null && "reward_mb".equals(zzkoVar.zza)) {
            z = true;
        }
        this.zzq = z ? "/Rewarded" : "/Interstitial";
        this.zzr = z ? new zzaeo(this.zze, this.zzi, new zzam(this), this) : null;
    }

    private final void zza(Bundle bundle) {
        zzbt.zze().zzb(this.zze.zzc, this.zze.zze.zza, "gmob-apps", bundle, false);
    }

    private static zzahe zzb(zzahe zzaheVar) {
        try {
            String string = zzads.zza(zzaheVar.zzb).toString();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, zzaheVar.zza.zze);
            zzvp zzvpVar = new zzvp(string, null, Collections.singletonList("com.google.ads.mediation.admob.AdMobAdapter"), null, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), jSONObject.toString(), null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList(), null, -1L);
            zzacj zzacjVar = zzaheVar.zzb;
            zzvq zzvqVar = new zzvq(Collections.singletonList(zzvpVar), ((Long) zzlc.zzf().zza(zzoi.zzdt)).longValue(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), zzacjVar.zzah, zzacjVar.zzai, "", -1L, 0, 1, null, 0, -1, -1L, false);
            return new zzahe(zzaheVar.zza, new zzacj(zzaheVar.zza, zzacjVar.zza, zzacjVar.zzb, Collections.emptyList(), Collections.emptyList(), zzacjVar.zzf, true, zzacjVar.zzh, Collections.emptyList(), zzacjVar.zzj, zzacjVar.zzk, zzacjVar.zzl, zzacjVar.zzm, zzacjVar.zzn, zzacjVar.zzo, zzacjVar.zzp, null, zzacjVar.zzr, zzacjVar.zzs, zzacjVar.zzt, zzacjVar.zzu, zzacjVar.zzv, zzacjVar.zzx, zzacjVar.zzy, zzacjVar.zzz, null, Collections.emptyList(), Collections.emptyList(), zzacjVar.zzad, zzacjVar.zzae, zzacjVar.zzaf, zzacjVar.zzag, zzacjVar.zzah, zzacjVar.zzai, zzacjVar.zzaj, null, zzacjVar.zzal, zzacjVar.zzam, zzacjVar.zzan, zzacjVar.zzao, 0), zzvqVar, zzaheVar.zzd, zzaheVar.zze, zzaheVar.zzf, zzaheVar.zzg, null, zzaheVar.zzi, null);
        } catch (JSONException e) {
            zzahw.zzb("Unable to generate ad state for an interstitial ad with pooling.", e);
            return zzaheVar;
        }
    }

    private final boolean zze(boolean z) {
        return this.zzr != null && z;
    }

    @Override // com.google.android.gms.ads.internal.zzi
    protected final zzaof zza(zzahe zzaheVar, zzw zzwVar, zzagq zzagqVar) throws zzaop {
        zzaof zzaofVarZza = zzbt.zzf().zza(this.zze.zzc, zzaqa.zza(this.zze.zzi), this.zze.zzi.zza, false, false, this.zze.zzd, this.zze.zze, this.zza, this, this.zzh, zzaheVar.zzi);
        zzaofVarZza.zzv().zza(this, null, this, this, ((Boolean) zzlc.zzf().zza(zzoi.zzbe)).booleanValue(), this, zzwVar, null, zzagqVar);
        zza(zzaofVarZza);
        zzaofVarZza.zza(zzaheVar.zza.zzv);
        zzaofVarZza.zzv().zza("/reward", new com.google.android.gms.ads.internal.gmsg.zzaf(this));
        return zzaofVarZza;
    }

    @Override // com.google.android.gms.ads.internal.zzi, com.google.android.gms.ads.internal.zza
    public final void zza(zzahe zzaheVar, zzov zzovVar) {
        if (zzaheVar.zze != -2) {
            super.zza(zzaheVar, zzovVar);
            return;
        }
        if (zze(zzaheVar.zzc != null)) {
            this.zzr.zzb();
            return;
        }
        if (!((Boolean) zzlc.zzf().zza(zzoi.zzcl)).booleanValue()) {
            super.zza(zzaheVar, zzovVar);
            return;
        }
        boolean z = !zzaheVar.zzb.zzg;
        if (zza(zzaheVar.zza.zzc) && z) {
            this.zze.zzk = zzb(zzaheVar);
        }
        super.zza(this.zze.zzk, zzovVar);
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzx
    public final void zza(boolean z, float f) {
        this.zzl = z;
        this.zzm = f;
    }

    @Override // com.google.android.gms.ads.internal.zzi, com.google.android.gms.ads.internal.zzd, com.google.android.gms.ads.internal.zza
    public final boolean zza(zzahd zzahdVar, zzahd zzahdVar2) {
        if (zze(zzahdVar2.zzm)) {
            return zzaeo.zza(zzahdVar, zzahdVar2);
        }
        if (!super.zza(zzahdVar, zzahdVar2)) {
            return false;
        }
        if (this.zze.zzd() || this.zze.zzaf == null || zzahdVar2.zzj == null) {
            return true;
        }
        this.zzg.zza(this.zze.zzi, zzahdVar2, this.zze.zzaf);
        return true;
    }

    @Override // com.google.android.gms.ads.internal.zzd
    protected final boolean zza(zzkk zzkkVar, zzahd zzahdVar, boolean z) {
        if (this.zze.zzd() && zzahdVar.zzb != null) {
            zzbt.zzg();
            zzaip.zza(zzahdVar.zzb);
        }
        return this.zzd.zze();
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.ads.internal.zza
    public final boolean zza(zzkk zzkkVar, zzov zzovVar) {
        if (this.zze.zzj != null) {
            zzahw.zze("An interstitial is already loading. Aborting.");
            return false;
        }
        if (this.zzo == null && zza(zzkkVar) && zzbt.zzaa().zzd(this.zze.zzc) && !TextUtils.isEmpty(this.zze.zzb)) {
            this.zzo = new zzagt(this.zze.zzc, this.zze.zzb);
        }
        return super.zza(zzkkVar, zzovVar);
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.ads.internal.overlay.zzn
    public final void zzaa() {
        zzapu zzapuVarZzv;
        zzak();
        super.zzaa();
        if (this.zze.zzj != null && this.zze.zzj.zzb != null && (zzapuVarZzv = this.zze.zzj.zzb.zzv()) != null) {
            zzapuVarZzv.zzg();
        }
        if (zzbt.zzaa().zzd(this.zze.zzc) && this.zze.zzj != null && this.zze.zzj.zzb != null) {
            zzbt.zzaa().zzc(this.zze.zzj.zzb.getContext(), this.zzp);
        }
        zzagt zzagtVar = this.zzo;
        if (zzagtVar != null) {
            zzagtVar.zza(true);
        }
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.internal.zzlt
    public final void zzan() {
        Bitmap bitmapZzh;
        com.google.android.gms.common.internal.zzbq.zzb("showInterstitial must be called on the main UI thread.");
        if (zze(this.zze.zzj != null && this.zze.zzj.zzm)) {
            this.zzr.zza(this.zzn);
            return;
        }
        if (zzbt.zzaa().zzd(this.zze.zzc)) {
            String strZzg = zzbt.zzaa().zzg(this.zze.zzc);
            this.zzp = strZzg;
            String strValueOf = String.valueOf(strZzg);
            String strValueOf2 = String.valueOf(this.zzq);
            this.zzp = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
        }
        if (this.zze.zzj == null) {
            zzahw.zze("The interstitial has not loaded.");
            return;
        }
        if (((Boolean) zzlc.zzf().zza(zzoi.zzdj)).booleanValue()) {
            String packageName = (this.zze.zzc.getApplicationContext() != null ? this.zze.zzc.getApplicationContext() : this.zze.zzc).getPackageName();
            if (!this.zzj) {
                zzahw.zze("It is not recommended to show an interstitial before onAdLoaded completes.");
                Bundle bundle = new Bundle();
                bundle.putString("appid", packageName);
                bundle.putString("action", "show_interstitial_before_load_finish");
                zza(bundle);
            }
            zzbt.zze();
            if (!zzaij.zzg(this.zze.zzc)) {
                zzahw.zze("It is not recommended to show an interstitial when app is not in foreground.");
                Bundle bundle2 = new Bundle();
                bundle2.putString("appid", packageName);
                bundle2.putString("action", "show_interstitial_app_not_in_foreground");
                zza(bundle2);
            }
        }
        if (this.zze.zze()) {
            return;
        }
        if (this.zze.zzj.zzm && this.zze.zzj.zzo != null) {
            try {
                if (((Boolean) zzlc.zzf().zza(zzoi.zzcj)).booleanValue()) {
                    this.zze.zzj.zzo.zza(this.zzn);
                }
                this.zze.zzj.zzo.zzb();
                return;
            } catch (RemoteException e) {
                zzahw.zzc("Could not show interstitial.", e);
                zzav();
                return;
            }
        }
        if (this.zze.zzj.zzb == null) {
            zzahw.zze("The interstitial failed to load.");
            return;
        }
        if (this.zze.zzj.zzb.zzy()) {
            zzahw.zze("The interstitial is already showing.");
            return;
        }
        this.zze.zzj.zzb.zzb(true);
        this.zze.zza(this.zze.zzj.zzb.zzm());
        if (this.zze.zzj.zzj != null) {
            this.zzg.zza(this.zze.zzi, this.zze.zzj);
        }
        final zzahd zzahdVar = this.zze.zzj;
        if (zzahdVar.zza()) {
            new zzgr(this.zze.zzc, zzahdVar.zzb.zzm()).zza(zzahdVar.zzb);
        } else {
            zzahdVar.zzb.zzv().zza(new zzapx(this, zzahdVar) { // from class: com.google.android.gms.ads.internal.zzal
                private final zzak zza;
                private final zzahd zzb;

                {
                    this.zza = this;
                    this.zzb = zzahdVar;
                }

                @Override // com.google.android.gms.internal.zzapx
                public final void zza() {
                    zzak zzakVar = this.zza;
                    zzahd zzahdVar2 = this.zzb;
                    new zzgr(zzakVar.zze.zzc, zzahdVar2.zzb.zzm()).zza(zzahdVar2.zzb);
                }
            });
        }
        if (this.zze.zzah) {
            zzbt.zze();
            bitmapZzh = zzaij.zzh(this.zze.zzc);
        } else {
            bitmapZzh = null;
        }
        this.zzk = zzbt.zzx().zza(bitmapZzh);
        if (((Boolean) zzlc.zzf().zza(zzoi.zzek)).booleanValue() && bitmapZzh != null) {
            new zzan(this, this.zzk).zzh();
            return;
        }
        zzap zzapVar = new zzap(this.zze.zzah, zzau(), false, 0.0f, -1, this.zzn, this.zze.zzj.zzai);
        int iZzz = this.zze.zzj.zzb.zzz();
        if (iZzz == -1) {
            iZzz = this.zze.zzj.zzg;
        }
        AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel(this, this, this, this.zze.zzj.zzb, iZzz, this.zze.zze, this.zze.zzj.zzz, zzapVar);
        zzbt.zzc();
        com.google.android.gms.ads.internal.overlay.zzl.zza(this.zze.zzc, adOverlayInfoParcel, true);
    }

    @Override // com.google.android.gms.internal.zzyo
    public final void zzat() {
        com.google.android.gms.ads.internal.overlay.zzd zzdVarZzr = this.zze.zzj.zzb.zzr();
        if (zzdVarZzr != null) {
            zzdVarZzr.zza();
        }
    }

    protected final boolean zzau() {
        Window window;
        if ((this.zze.zzc instanceof Activity) && (window = ((Activity) this.zze.zzc).getWindow()) != null && window.getDecorView() != null) {
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            window.getDecorView().getGlobalVisibleRect(rect, null);
            window.getDecorView().getWindowVisibleDisplayFrame(rect2);
            if (rect.bottom != 0 && rect2.bottom != 0 && rect.top == rect2.top) {
                return true;
            }
        }
        return false;
    }

    public final void zzav() {
        zzbt.zzx().zzb(Integer.valueOf(this.zzk));
        if (this.zze.zzd()) {
            this.zze.zzb();
            this.zze.zzj = null;
            this.zze.zzah = false;
            this.zzj = false;
        }
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzag
    public final void zzaw() {
        if (zze(this.zze.zzj != null && this.zze.zzj.zzm)) {
            this.zzr.zzf();
            zzs();
            return;
        }
        if (this.zze.zzj != null && this.zze.zzj.zzv != null) {
            zzbt.zze();
            zzaij.zza(this.zze.zzc, this.zze.zze.zza, this.zze.zzj.zzv);
        }
        zzs();
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzag
    public final void zzax() {
        if (zze(this.zze.zzj != null && this.zze.zzj.zzm)) {
            this.zzr.zzg();
        }
        zzt();
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzag
    public final void zzb(zzagd zzagdVar) {
        if (zze(this.zze.zzj != null && this.zze.zzj.zzm)) {
            zza(this.zzr.zza(zzagdVar));
            return;
        }
        if (this.zze.zzj != null) {
            if (this.zze.zzj.zzw != null) {
                zzbt.zze();
                zzaij.zza(this.zze.zzc, this.zze.zze.zza, this.zze.zzj.zzw);
            }
            if (this.zze.zzj.zzu != null) {
                zzagdVar = this.zze.zzj.zzu;
            }
        }
        zza(zzagdVar);
    }

    @Override // com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final void zzb(boolean z) {
        com.google.android.gms.common.internal.zzbq.zzb("setImmersiveMode must be called on the main UI thread.");
        this.zzn = z;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzx
    public final void zzd(boolean z) {
        this.zze.zzah = z;
    }

    @Override // com.google.android.gms.ads.internal.zza
    protected final void zzm() {
        zzav();
        super.zzm();
    }

    @Override // com.google.android.gms.ads.internal.zzi, com.google.android.gms.ads.internal.zza
    protected final void zzp() {
        super.zzp();
        this.zzj = true;
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.ads.internal.overlay.zzn
    public final void zzz() {
        super.zzz();
        this.zzg.zza(this.zze.zzj);
        zzagt zzagtVar = this.zzo;
        if (zzagtVar != null) {
            zzagtVar.zza(false);
        }
    }
}
