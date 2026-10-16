package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzmm;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpm;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zzpu;
import com.google.android.gms.internal.zzpv;
import com.google.android.gms.internal.zzpw;
import com.google.android.gms.internal.zzro;
import com.google.android.gms.internal.zzry;
import com.google.android.gms.internal.zzvq;
import com.google.android.gms.internal.zzwf;
import com.google.android.gms.internal.zzwr;
import com.google.android.gms.internal.zzwu;
import com.google.android.gms.internal.zzzm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzq extends zzd implements zzpw {
    private boolean zzj;
    private zzahd zzk;
    private boolean zzl;

    public zzq(Context context, zzv zzvVar, zzko zzkoVar, String str, zzwf zzwfVar, zzala zzalaVar) {
        super(context, zzkoVar, str, zzwfVar, zzalaVar, zzvVar);
        this.zzl = false;
    }

    private static zzahd zza(zzahe zzaheVar, int i) {
        return new zzahd(zzaheVar.zza.zzc, null, zzaheVar.zzb.zzc, i, zzaheVar.zzb.zze, zzaheVar.zzb.zzi, zzaheVar.zzb.zzk, zzaheVar.zzb.zzj, zzaheVar.zza.zzi, zzaheVar.zzb.zzg, null, null, null, zzaheVar.zzc, null, zzaheVar.zzb.zzh, zzaheVar.zzd, zzaheVar.zzb.zzf, zzaheVar.zzf, zzaheVar.zzg, zzaheVar.zzb.zzn, zzaheVar.zzh, null, zzaheVar.zzb.zzaa, zzaheVar.zzb.zzab, zzaheVar.zzb.zzab, zzaheVar.zzb.zzad, zzaheVar.zzb.zzae, null, zzaheVar.zzb.zzah, zzaheVar.zzb.zzal, zzaheVar.zzi, zzaheVar.zzb.zzao, zzaheVar.zzj);
    }

    private final zzvq zzat() {
        if (this.zze.zzj == null || !this.zze.zzj.zzm) {
            return null;
        }
        return this.zze.zzj.zzq;
    }

    private final boolean zzb(zzahd zzahdVar, zzahd zzahdVar2) {
        Handler handler;
        Runnable zztVar;
        zzb((List<String>) null);
        if (this.zze.zzd()) {
            try {
                zzwr zzwrVarZzh = zzahdVar2.zzo != null ? zzahdVar2.zzo.zzh() : null;
                zzwu zzwuVarZzi = zzahdVar2.zzo != null ? zzahdVar2.zzo.zzi() : null;
                zzro zzroVarZzn = zzahdVar2.zzo != null ? zzahdVar2.zzo.zzn() : null;
                String strZzc = zzc(zzahdVar2);
                if (zzwrVarZzh == null || this.zze.zzq == null) {
                    if (zzwuVarZzi != null && this.zze.zzr != null) {
                        zzpm zzpmVar = new zzpm(zzwuVarZzi.zza(), zzwuVarZzi.zzb(), zzwuVarZzi.zzc(), zzwuVarZzi.zzd() != null ? zzwuVarZzi.zzd() : null, zzwuVarZzi.zze(), zzwuVarZzi.zzf(), null, zzwuVarZzi.zzj(), zzwuVarZzi.zzl(), zzwuVarZzi.zzn() != null ? (View) com.google.android.gms.dynamic.zzn.zza(zzwuVarZzi.zzn()) : null, zzwuVarZzi.zzo(), strZzc);
                        zzpmVar.zza(new zzpu(this.zze.zzc, this, this.zze.zzd, zzwuVarZzi, zzpmVar));
                        handler = zzaij.zza;
                        zztVar = new zzt(this, zzpmVar);
                    } else {
                        if (zzroVarZzn == null || this.zze.zzu == null || this.zze.zzu.get(zzroVarZzn.zzl()) == null) {
                            zzahw.zze("No matching mapper/listener for retrieved native ad template.");
                            zza(0);
                            return false;
                        }
                        zzaij.zza.post(new zzu(this, zzroVarZzn));
                    }
                    return super.zza(zzahdVar, zzahdVar2);
                }
                zzpk zzpkVar = new zzpk(zzwrVarZzh.zza(), zzwrVarZzh.zzb(), zzwrVarZzh.zzc(), zzwrVarZzh.zzd() != null ? zzwrVarZzh.zzd() : null, zzwrVarZzh.zze(), zzwrVarZzh.zzf(), zzwrVarZzh.zzg(), zzwrVarZzh.zzh(), null, zzwrVarZzh.zzl(), zzwrVarZzh.zzm(), zzwrVarZzh.zzp() != null ? (View) com.google.android.gms.dynamic.zzn.zza(zzwrVarZzh.zzp()) : null, zzwrVarZzh.zzq(), strZzc);
                zzpkVar.zza(new zzpu(this.zze.zzc, this, this.zze.zzd, zzwrVarZzh, zzpkVar));
                handler = zzaij.zza;
                zztVar = new zzs(this, zzpkVar);
                handler.post(zztVar);
                return super.zza(zzahdVar, zzahdVar2);
            } catch (RemoteException e) {
                zzahw.zzc("Failed to get native ad mapper", e);
            }
        } else {
            zzahw.zze("Native ad does not have custom rendering mode.");
        }
        zza(0);
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final boolean zzc(zzahd zzahdVar, zzahd zzahdVar2) {
        View viewZza = zzar.zza(zzahdVar2);
        if (viewZza == null) {
            return false;
        }
        View nextView = this.zze.zzf.getNextView();
        if (nextView != 0) {
            if (nextView instanceof zzaof) {
                ((zzaof) nextView).destroy();
            }
            this.zze.zzf.removeView(nextView);
        }
        if (!zzar.zzb(zzahdVar2)) {
            try {
                zza(viewZza);
            } catch (Throwable th) {
                zzbt.zzi().zza(th, "AdLoaderManager.swapBannerViews");
                zzahw.zzc("Could not add mediation view to view hierarchy.", th);
                return false;
            }
        }
        if (this.zze.zzf.getChildCount() > 1) {
            this.zze.zzf.showNext();
        }
        if (zzahdVar != null) {
            View nextView2 = this.zze.zzf.getNextView();
            if (nextView2 != null) {
                this.zze.zzf.removeView(nextView2);
            }
            this.zze.zzc();
        }
        this.zze.zzf.setMinimumWidth(zzd().zzf);
        this.zze.zzf.setMinimumHeight(zzd().zzc);
        this.zze.zzf.requestLayout();
        this.zze.zzf.setVisibility(0);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0026  */
    /* JADX WARN: Code duplicated, block: B:13:0x0031  */
    /* JADX WARN: Code duplicated, block: B:15:0x0035  */
    @Override // com.google.android.gms.ads.internal.zza
    public final void zza(zzahe zzaheVar, zzov zzovVar) {
        zzahd zzahdVarZza;
        this.zzk = null;
        if (zzaheVar.zze == -2) {
            if (!zzaheVar.zzb.zzg) {
                zzahw.zze("partialAdState is not mediation");
                zzahdVarZza = zza(zzaheVar, 0);
            }
            if (this.zzk != null) {
                zzaij.zza.post(new zzr(this));
                return;
            }
            if (zzaheVar.zzd != null) {
                this.zze.zzi = zzaheVar.zzd;
            }
            this.zze.zzag = 0;
            zzbu zzbuVar = this.zze;
            zzbt.zzd();
            zzbuVar.zzh = zzzm.zza(this.zze.zzc, this, zzaheVar, this.zze.zzd, null, this.zzi, this, zzovVar);
        }
        zzahdVarZza = zza(zzaheVar, zzaheVar.zze);
        this.zzk = zzahdVarZza;
        if (this.zzk != null) {
            zzaij.zza.post(new zzr(this));
            return;
        }
        if (zzaheVar.zzd != null) {
            this.zze.zzi = zzaheVar.zzd;
        }
        this.zze.zzag = 0;
        zzbu zzbuVar2 = this.zze;
        zzbt.zzd();
        zzbuVar2.zzh = zzzm.zza(this.zze.zzc, this, zzaheVar, this.zze.zzd, null, this.zzi, this, zzovVar);
    }

    @Override // com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final void zza(zzpb zzpbVar) {
        throw new IllegalStateException("CustomRendering is not supported by AdLoaderManager.");
    }

    @Override // com.google.android.gms.internal.zzpw
    public final void zza(zzpt zzptVar) {
        zzahw.zze("Unexpected call to AdLoaderManager method");
    }

    @Override // com.google.android.gms.internal.zzpw
    public final void zza(zzpv zzpvVar) {
        zzahw.zze("Unexpected call to AdLoaderManager method");
    }

    @Override // com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final void zza(boolean z) {
        com.google.android.gms.common.internal.zzbq.zzb("setManualImpressionsEnabled must be called from the main thread.");
        this.zzj = z;
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.ads.internal.zza
    protected final boolean zza(zzahd zzahdVar, zzahd zzahdVar2) {
        String str;
        boolean z;
        if (!this.zze.zzd()) {
            throw new IllegalStateException("AdLoader API does not support custom rendering.");
        }
        if (zzahdVar2.zzm) {
            if (zzahdVar2.zzn != null && zzahdVar2.zzn.zza()) {
                if (this.zze.zzd() && this.zze.zzf != null) {
                    this.zze.zzf.zza().zzc(zzahdVar2.zzz);
                }
                if (!super.zza(zzahdVar, zzahdVar2)) {
                    z = false;
                } else if (!this.zze.zzd() || zzc(zzahdVar, zzahdVar2)) {
                    if (!this.zze.zze()) {
                        super.zza(zzahdVar2, false);
                    }
                    z = true;
                } else {
                    zza(0);
                    z = false;
                }
                if (!z) {
                    return false;
                }
                this.zzl = true;
            } else if (zzahdVar2.zzn == null || !zzahdVar2.zzn.zzb()) {
                zza(0);
                str = "Response is neither banner nor native.";
            } else if (!zzb(zzahdVar, zzahdVar2)) {
                return false;
            }
            zzc(new ArrayList(Arrays.asList(2)));
            return true;
        }
        zza(0);
        str = "newState is not mediation.";
        zzahw.zze(str);
        return false;
    }

    @Override // com.google.android.gms.ads.internal.zzd
    protected final boolean zza(zzkk zzkkVar, zzahd zzahdVar, boolean z) {
        return false;
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.internal.zzvr
    public final void zzae() {
        if (this.zze.zzj == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zze.zzj.zzp) || this.zze.zzj.zzn == null || !this.zze.zzj.zzn.zzb()) {
            super.zzae();
        } else {
            zzr();
        }
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.internal.zzvr
    public final void zzaj() {
        if (this.zze.zzj == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zze.zzj.zzp) || this.zze.zzj.zzn == null || !this.zze.zzj.zzn.zzb()) {
            super.zzaj();
        } else {
            zzq();
        }
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.internal.zzlt
    public final void zzan() {
        throw new IllegalStateException("Interstitial is not supported by AdLoaderManager.");
    }

    @Override // com.google.android.gms.internal.zzpw
    public final void zzaq() {
        zzahw.zze("Unexpected call to AdLoaderManager method");
    }

    @Override // com.google.android.gms.internal.zzpw
    public final boolean zzar() {
        if (zzat() != null) {
            return zzat().zzo;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.zzpw
    public final boolean zzas() {
        if (zzat() != null) {
            return zzat().zzp;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.zzpw
    public final zzry zzb(String str) {
        com.google.android.gms.common.internal.zzbq.zzb("getOnCustomClickListener must be called on the main UI thread.");
        return this.zze.zzt.get(str);
    }

    public final void zzb(List<String> list) {
        com.google.android.gms.common.internal.zzbq.zzb("setNativeTemplates must be called on the main UI thread.");
        this.zze.zzad = list;
    }

    @Override // com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final boolean zzb(zzkk zzkkVar) {
        zzkk zzkkVar2 = zzkkVar;
        if (this.zze.zzz != null && this.zze.zzz.size() == 1 && this.zze.zzz.get(0).intValue() == 2) {
            zzahw.zzc("Requesting only banner Ad from AdLoader or calling loadAd on returned banner is not yet supported");
            zza(0);
            return false;
        }
        if (this.zze.zzy == null) {
            return super.zzb(zzkkVar);
        }
        if (zzkkVar2.zzh != this.zzj) {
            zzkkVar2 = new zzkk(zzkkVar2.zza, zzkkVar2.zzb, zzkkVar2.zzc, zzkkVar2.zzd, zzkkVar2.zze, zzkkVar2.zzf, zzkkVar2.zzg, zzkkVar2.zzh || this.zzj, zzkkVar2.zzi, zzkkVar2.zzj, zzkkVar2.zzk, zzkkVar2.zzl, zzkkVar2.zzm, zzkkVar2.zzn, zzkkVar2.zzo, zzkkVar2.zzp, zzkkVar2.zzq, zzkkVar2.zzr);
        }
        return super.zzb(zzkkVar2);
    }

    public final void zzc(List<Integer> list) {
        com.google.android.gms.common.internal.zzbq.zzb("setAllowedAdTypes must be called on the main UI thread.");
        this.zze.zzz = list;
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final void zzh() {
        if (!this.zzl) {
            throw new IllegalStateException("Native Ad does not support pause().");
        }
        super.zzh();
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final void zzi() {
        if (!this.zzl) {
            throw new IllegalStateException("Native Ad does not support resume().");
        }
        super.zzi();
    }

    @Override // com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final zzmm zzl() {
        return null;
    }

    @Override // com.google.android.gms.ads.internal.zza
    protected final void zzp() {
        super.zzp();
        zzahd zzahdVar = this.zze.zzj;
        if (zzahdVar == null || zzahdVar.zzn == null || !zzahdVar.zzn.zza() || this.zze.zzy == null) {
            return;
        }
        try {
            this.zze.zzy.zza(this, com.google.android.gms.dynamic.zzn.zza(this.zze.zzc));
        } catch (RemoteException e) {
            zzahw.zzc("Could not call PublisherAdViewLoadedListener.onPublisherAdViewLoaded().", e);
        }
    }
}
