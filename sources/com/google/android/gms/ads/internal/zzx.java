package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Rect;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.exifinterface.media.ExifInterface;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzagq;
import com.google.android.gms.internal.zzagt;
import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzaml;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzaop;
import com.google.android.gms.internal.zzaou;
import com.google.android.gms.internal.zzapu;
import com.google.android.gms.internal.zzapx;
import com.google.android.gms.internal.zzapy;
import com.google.android.gms.internal.zzaqa;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zziw;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzmm;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzwf;
import com.google.android.gms.internal.zzwr;
import com.google.android.gms.internal.zzwu;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzx extends zzi implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private boolean zzj;
    private WeakReference<Object> zzk;

    public zzx(Context context, zzko zzkoVar, String str, zzwf zzwfVar, zzala zzalaVar, zzv zzvVar) {
        super(context, zzkoVar, str, zzwfVar, zzalaVar, zzvVar);
        this.zzk = new WeakReference<>(null);
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
    private final boolean zzb(zzahd zzahdVar, zzahd zzahdVar2) {
        if (zzahdVar2.zzm) {
            View viewZza = zzar.zza(zzahdVar2);
            if (viewZza == null) {
                zzahw.zze("Could not get mediation view");
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
                    if (zzbt.zzaa().zzb(this.zze.zzc)) {
                        new zzgr(this.zze.zzc, viewZza).zza(new zzagt(this.zze.zzc, this.zze.zzb));
                    }
                    if (zzahdVar2.zzt != null) {
                        this.zze.zzf.setMinimumWidth(zzahdVar2.zzt.zzf);
                        this.zze.zzf.setMinimumHeight(zzahdVar2.zzt.zzc);
                    }
                    zza(viewZza);
                } catch (Exception e) {
                    zzbt.zzi().zza(e, "BannerAdManager.swapViews");
                    zzahw.zzc("Could not add mediation view to view hierarchy.", e);
                    return false;
                }
            }
        } else if (zzahdVar2.zzt != null && zzahdVar2.zzb != null) {
            zzahdVar2.zzb.zza(zzaqa.zza(zzahdVar2.zzt));
            this.zze.zzf.removeAllViews();
            this.zze.zzf.setMinimumWidth(zzahdVar2.zzt.zzf);
            this.zze.zzf.setMinimumHeight(zzahdVar2.zzt.zzc);
            zza(zzahdVar2.zzb.zzm());
        }
        if (this.zze.zzf.getChildCount() > 1) {
            this.zze.zzf.showNext();
        }
        if (zzahdVar != null) {
            View nextView2 = this.zze.zzf.getNextView();
            if (nextView2 instanceof zzaof) {
                ((zzaof) nextView2).destroy();
            } else if (nextView2 != 0) {
                this.zze.zzf.removeView(nextView2);
            }
            this.zze.zzc();
        }
        this.zze.zzf.setVisibility(0);
        return true;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        zzd(this.zze.zzj);
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public final void onScrollChanged() {
        zzd(this.zze.zzj);
    }

    @Override // com.google.android.gms.ads.internal.zzi
    protected final zzaof zza(zzahe zzaheVar, zzw zzwVar, zzagq zzagqVar) throws zzaop {
        AdSize adSizeZzb;
        zzko zzkoVar;
        if (this.zze.zzi.zzg == null && this.zze.zzi.zzi) {
            zzbu zzbuVar = this.zze;
            if (zzaheVar.zzb.zzy) {
                zzkoVar = this.zze.zzi;
            } else {
                String str = zzaheVar.zzb.zzl;
                if (str != null) {
                    String[] strArrSplit = str.split("[xX]");
                    strArrSplit[0] = strArrSplit[0].trim();
                    strArrSplit[1] = strArrSplit[1].trim();
                    adSizeZzb = new AdSize(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]));
                } else {
                    adSizeZzb = this.zze.zzi.zzb();
                }
                zzkoVar = new zzko(this.zze.zzc, adSizeZzb);
            }
            zzbuVar.zzi = zzkoVar;
        }
        return super.zza(zzaheVar, zzwVar, zzagqVar);
    }

    @Override // com.google.android.gms.ads.internal.zzd
    protected final void zza(zzahd zzahdVar, boolean z) {
        super.zza(zzahdVar, z);
        if (zzar.zzb(zzahdVar)) {
            zzab zzabVar = new zzab(this);
            if (zzahdVar == null || !zzar.zzb(zzahdVar)) {
                return;
            }
            zzaof zzaofVar = zzahdVar.zzb;
            View viewZzm = zzaofVar != null ? zzaofVar.zzm() : null;
            if (viewZzm == null) {
                zzahw.zze("AdWebView is null");
                return;
            }
            try {
                List<String> list = zzahdVar.zzn != null ? zzahdVar.zzn.zzq : null;
                if (list != null && !list.isEmpty()) {
                    zzwr zzwrVarZzh = zzahdVar.zzo != null ? zzahdVar.zzo.zzh() : null;
                    zzwu zzwuVarZzi = zzahdVar.zzo != null ? zzahdVar.zzo.zzi() : null;
                    if (list.contains(ExifInterface.GPS_MEASUREMENT_2D) && zzwrVarZzh != null) {
                        zzwrVarZzh.zzb(com.google.android.gms.dynamic.zzn.zza(viewZzm));
                        if (!zzwrVarZzh.zzj()) {
                            zzwrVarZzh.zzi();
                        }
                        zzaofVar.zzv().zza("/nativeExpressViewClicked", zzar.zza(zzwrVarZzh, (zzwu) null, zzabVar));
                        return;
                    }
                    if (!list.contains(LocalSearchProvider.LIVE_CONTENTS) || zzwuVarZzi == null) {
                        zzahw.zze("No matching template id and mapper");
                        return;
                    }
                    zzwuVarZzi.zzb(com.google.android.gms.dynamic.zzn.zza(viewZzm));
                    if (!zzwuVarZzi.zzh()) {
                        zzwuVarZzi.zzg();
                    }
                    zzaofVar.zzv().zza("/nativeExpressViewClicked", zzar.zza((zzwr) null, zzwuVarZzi, zzabVar));
                    return;
                }
                zzahw.zze("No template ids present in mediation response");
            } catch (RemoteException e) {
                zzahw.zzc("Error occurred while recording impression and registering for clicks", e);
            }
        }
    }

    @Override // com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final void zza(boolean z) {
        com.google.android.gms.common.internal.zzbq.zzb("setManualImpressionsEnabled must be called from the main thread.");
        this.zzj = z;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x007b  */
    @Override // com.google.android.gms.ads.internal.zzi, com.google.android.gms.ads.internal.zzd, com.google.android.gms.ads.internal.zza
    public final boolean zza(zzahd zzahdVar, final zzahd zzahdVar2) {
        if (!super.zza(zzahdVar, zzahdVar2)) {
            return false;
        }
        if (this.zze.zzd() && !zzb(zzahdVar, zzahdVar2)) {
            if (zzahdVar2.zzah != null) {
                zzahdVar2.zzah.zza(zziw.zza.zzb.AD_FAILED_TO_LOAD);
            }
            zza(0);
            return false;
        }
        View viewZzm = null;
        if (zzahdVar2.zzk) {
            zzd(zzahdVar2);
            zzbt.zzz();
            zzaml.zza((View) this.zze.zzf, (ViewTreeObserver.OnGlobalLayoutListener) this);
            zzbt.zzz();
            zzaml.zza((View) this.zze.zzf, (ViewTreeObserver.OnScrollChangedListener) this);
            if (!zzahdVar2.zzl) {
                final zzaa zzaaVar = new zzaa(this);
                zzapu zzapuVarZzv = zzahdVar2.zzb != null ? zzahdVar2.zzb.zzv() : null;
                if (zzapuVarZzv != null) {
                    zzapuVarZzv.zza(new zzapy(zzahdVar2, zzaaVar) { // from class: com.google.android.gms.ads.internal.zzy
                        private final zzahd zza;
                        private final Runnable zzb;

                        {
                            this.zza = zzahdVar2;
                            this.zzb = zzaaVar;
                        }

                        @Override // com.google.android.gms.internal.zzapy
                        public final void zza() {
                            zzahd zzahdVar3 = this.zza;
                            Runnable runnable = this.zzb;
                            if (zzahdVar3.zzl) {
                                return;
                            }
                            zzbt.zze();
                            zzaij.zza(runnable);
                        }
                    });
                }
            }
        } else if (this.zze.zze()) {
            if (((Boolean) zzlc.zzf().zza(zzoi.zzep)).booleanValue()) {
                zza(zzahdVar2, false);
            }
        } else {
            zza(zzahdVar2, false);
        }
        if (zzahdVar2.zzb != null) {
            zzaou zzaouVarZzb = zzahdVar2.zzb.zzb();
            zzapu zzapuVarZzv2 = zzahdVar2.zzb.zzv();
            if (zzapuVarZzv2 != null) {
                zzapuVarZzv2.zzg();
            }
            if (this.zze.zzw != null && zzaouVarZzb != null) {
                zzaouVarZzb.zza(this.zze.zzw);
            }
        }
        if (this.zze.zzd()) {
            if (zzahdVar2.zzb != null) {
                if (zzahdVar2.zzj != null) {
                    this.zzg.zza(this.zze.zzi, zzahdVar2);
                }
                viewZzm = zzahdVar2.zzb.zzm();
                final zzgr zzgrVar = new zzgr(this.zze.zzc, viewZzm);
                if (zzbt.zzaa().zzb(this.zze.zzc) && zza(zzahdVar2.zza) && !TextUtils.isEmpty(this.zze.zzb)) {
                    zzgrVar.zza(new zzagt(this.zze.zzc, this.zze.zzb));
                }
                if (zzahdVar2.zza()) {
                    zzgrVar.zza(zzahdVar2.zzb);
                } else {
                    zzahdVar2.zzb.zzv().zza(new zzapx(zzgrVar, zzahdVar2) { // from class: com.google.android.gms.ads.internal.zzz
                        private final zzgr zza;
                        private final zzahd zzb;

                        {
                            this.zza = zzgrVar;
                            this.zzb = zzahdVar2;
                        }

                        @Override // com.google.android.gms.internal.zzapx
                        public final void zza() {
                            this.zza.zza(this.zzb.zzb);
                        }
                    });
                }
            }
        } else if (this.zze.zzaf != null && zzahdVar2.zzj != null) {
            this.zzg.zza(this.zze.zzi, zzahdVar2, this.zze.zzaf);
            viewZzm = this.zze.zzaf;
        }
        if (zzahdVar2.zzm) {
            return true;
        }
        this.zze.zza(viewZzm);
        return true;
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.internal.zzlt
    public final void zzan() {
        throw new IllegalStateException("Interstitial is NOT supported by BannerAdManager.");
    }

    @Override // com.google.android.gms.internal.zzyo
    public final void zzat() {
        this.zzd.zzd();
    }

    @Override // com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final boolean zzb(zzkk zzkkVar) {
        zzkk zzkkVar2 = zzkkVar;
        if (zzkkVar2.zzh != this.zzj) {
            zzkkVar2 = new zzkk(zzkkVar2.zza, zzkkVar2.zzb, zzkkVar2.zzc, zzkkVar2.zzd, zzkkVar2.zze, zzkkVar2.zzf, zzkkVar2.zzg, zzkkVar2.zzh || this.zzj, zzkkVar2.zzi, zzkkVar2.zzj, zzkkVar2.zzk, zzkkVar2.zzl, zzkkVar2.zzm, zzkkVar2.zzn, zzkkVar2.zzo, zzkkVar2.zzp, zzkkVar2.zzq, zzkkVar2.zzr);
        }
        return super.zzb(zzkkVar2);
    }

    final void zzd(zzahd zzahdVar) {
        if (zzahdVar == null || zzahdVar.zzl || this.zze.zzf == null || !zzbt.zze().zza(this.zze.zzf, this.zze.zzc) || !this.zze.zzf.getGlobalVisibleRect(new Rect(), null)) {
            return;
        }
        if (zzahdVar != null && zzahdVar.zzb != null && zzahdVar.zzb.zzv() != null) {
            zzahdVar.zzb.zzv().zza((zzapy) null);
        }
        zza(zzahdVar, false);
        zzahdVar.zzl = true;
    }

    @Override // com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final zzmm zzl() {
        com.google.android.gms.common.internal.zzbq.zzb("getVideoController must be called from the main thread.");
        if (this.zze.zzj == null || this.zze.zzj.zzb == null) {
            return null;
        }
        return this.zze.zzj.zzb.zzb();
    }

    @Override // com.google.android.gms.ads.internal.zzd
    protected final boolean zzy() {
        boolean z;
        zzbt.zze();
        if (zzaij.zza(this.zze.zzc, this.zze.zzc.getPackageName(), "android.permission.INTERNET")) {
            z = true;
        } else {
            zzlc.zza().zza(this.zze.zzf, this.zze.zzi, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            z = false;
        }
        zzbt.zze();
        if (!zzaij.zza(this.zze.zzc)) {
            zzlc.zza().zza(this.zze.zzf, this.zze.zzi, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            z = false;
        }
        if (!z && this.zze.zzf != null) {
            this.zze.zzf.setVisibility(0);
        }
        return z;
    }
}
