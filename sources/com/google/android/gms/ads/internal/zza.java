package com.google.android.gms.ads.internal;

import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzabj;
import com.google.android.gms.internal.zzaep;
import com.google.android.gms.internal.zzafc;
import com.google.android.gms.internal.zzagd;
import com.google.android.gms.internal.zzagx;
import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzahf;
import com.google.android.gms.internal.zzahn;
import com.google.android.gms.internal.zzahp;
import com.google.android.gms.internal.zzahu;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzako;
import com.google.android.gms.internal.zzaqa;
import com.google.android.gms.internal.zzfu;
import com.google.android.gms.internal.zziu;
import com.google.android.gms.internal.zziw;
import com.google.android.gms.internal.zzkf;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzkl;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzlf;
import com.google.android.gms.internal.zzli;
import com.google.android.gms.internal.zzlu;
import com.google.android.gms.internal.zzly;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmm;
import com.google.android.gms.internal.zzms;
import com.google.android.gms.internal.zznq;
import com.google.android.gms.internal.zzns;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzot;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzyx;
import com.google.android.gms.internal.zzzd;
import com.google.android.gms.internal.zzzn;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public abstract class zza extends zzlu implements com.google.android.gms.ads.internal.gmsg.zzb, com.google.android.gms.ads.internal.overlay.zzt, zzabj, zzahn, zzkf, zzzn {
    protected zzov zza;
    protected zzot zzb;
    protected boolean zzc = false;
    protected final zzbj zzd = new zzbj(this);
    protected final zzbu zze;
    protected transient zzkk zzf;
    protected final zzfu zzg;
    protected final zzv zzh;
    private zzot zzi;

    zza(zzbu zzbuVar, zzbj zzbjVar, zzv zzvVar) {
        this.zze = zzbuVar;
        this.zzh = zzvVar;
        zzbt.zze().zzb(this.zze.zzc);
        zzbt.zze().zzc(this.zze.zzc);
        zzahu.zza(this.zze.zzc);
        zzbt.zzab().zza(this.zze.zzc);
        zzbt.zzi().zza(this.zze.zzc, this.zze.zze);
        zzbt.zzk().zza(this.zze.zzc);
        this.zzg = zzbt.zzi().zzg();
        zzbt.zzh().zza(this.zze.zzc);
        zzbt.zzad().zza(this.zze.zzc);
        if (((Boolean) zzlc.zzf().zza(zzoi.zzfd)).booleanValue()) {
            Timer timer = new Timer();
            timer.schedule(new zzb(this, new CountDownLatch(((Integer) zzlc.zzf().zza(zzoi.zzff)).intValue()), timer), 0L, ((Long) zzlc.zzf().zza(zzoi.zzfe)).longValue());
        }
    }

    protected static boolean zza(zzkk zzkkVar) {
        Bundle bundle = zzkkVar.zzm.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        return bundle == null || !bundle.containsKey("gw");
    }

    private static long zzb(String str) {
        String str2;
        int iIndexOf = str.indexOf("ufe");
        int iIndexOf2 = str.indexOf(44, iIndexOf);
        if (iIndexOf2 == -1) {
            iIndexOf2 = str.length();
        }
        try {
            return Long.parseLong(str.substring(iIndexOf + 4, iIndexOf2));
        } catch (IndexOutOfBoundsException unused) {
            str2 = "Invalid index for Url fetch time in CSI latency info.";
            zzahw.zze(str2);
            return -1L;
        } catch (NumberFormatException unused2) {
            str2 = "Cannot find valid format of Url fetch time in CSI latency info.";
            zzahw.zze(str2);
            return -1L;
        }
    }

    @Override // com.google.android.gms.internal.zzkf
    public void onAdClicked() {
        if (this.zze.zzj == null) {
            zzahw.zze("Ad state was null when trying to ping click URLs.");
            return;
        }
        zzahw.zzb("Pinging click URLs.");
        if (this.zze.zzl != null) {
            this.zze.zzl.zzb();
        }
        if (this.zze.zzj.zzc != null) {
            zzbt.zze();
            zzaij.zza(this.zze.zzc, this.zze.zze.zza, zza(this.zze.zzj.zzc));
        }
        if (this.zze.zzm != null) {
            try {
                this.zze.zzm.zza();
            } catch (RemoteException e) {
                zzahw.zzc("Could not notify onAdClicked event.", e);
            }
        }
    }

    public final zzv zza() {
        return this.zzh;
    }

    protected final List<String> zza(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(zzagx.zza(it.next(), this.zze.zzc));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void zza(int i) {
        zza(i, false);
    }

    protected void zza(int i, boolean z) {
        StringBuilder sb = new StringBuilder(30);
        sb.append("Failed to load ad: ");
        sb.append(i);
        zzahw.zze(sb.toString());
        this.zzc = z;
        if (this.zze.zzn != null) {
            try {
                this.zze.zzn.zza(i);
            } catch (RemoteException e) {
                zzahw.zzc("Could not call AdListener.onAdFailedToLoad().", e);
            }
        }
        if (this.zze.zzab != null) {
            try {
                this.zze.zzab.zza(i);
            } catch (RemoteException e2) {
                zzahw.zzc("Could not call RewardedVideoAdListener.onRewardedVideoAdFailedToLoad().", e2);
            }
        }
    }

    protected final void zza(View view) {
        zzbv zzbvVar = this.zze.zzf;
        if (zzbvVar != null) {
            zzbvVar.addView(view, zzbt.zzg().zzd());
        }
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzafc zzafcVar) {
        com.google.android.gms.common.internal.zzbq.zzb("setRewardedVideoAdListener can only be called from the UI thread.");
        this.zze.zzab = zzafcVar;
    }

    protected final void zza(zzagd zzagdVar) {
        if (this.zze.zzab == null) {
            return;
        }
        String str = "";
        int i = 1;
        if (zzagdVar != null) {
            try {
                str = zzagdVar.zza;
                i = zzagdVar.zzb;
            } catch (RemoteException e) {
                zzahw.zzc("Could not call RewardedVideoAdListener.onRewarded().", e);
                return;
            }
        }
        this.zze.zzab.zza(new zzaep(str, i));
    }

    @Override // com.google.android.gms.internal.zzabj
    public final void zza(zzahe zzaheVar) {
        if (zzaheVar.zzb.zzm != -1 && !TextUtils.isEmpty(zzaheVar.zzb.zzw)) {
            long jZzb = zzb(zzaheVar.zzb.zzw);
            if (jZzb != -1) {
                this.zza.zza(this.zza.zza(zzaheVar.zzb.zzm + jZzb), "stc");
            }
        }
        this.zza.zza(zzaheVar.zzb.zzw);
        this.zza.zza(this.zzb, "arf");
        this.zzi = this.zza.zza();
        this.zza.zza("gqi", zzaheVar.zzb.zzx);
        this.zze.zzg = null;
        this.zze.zzk = zzaheVar;
        zzaheVar.zzi.zza(new zzc(this, zzaheVar));
        zzaheVar.zzi.zza(zziw.zza.zzb.AD_LOADED);
        zza(zzaheVar, this.zza);
    }

    protected abstract void zza(zzahe zzaheVar, zzov zzovVar);

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzko zzkoVar) {
        com.google.android.gms.common.internal.zzbq.zzb("setAdSize must be called on the main UI thread.");
        this.zze.zzi = zzkoVar;
        if (this.zze.zzj != null && this.zze.zzj.zzb != null && this.zze.zzag == 0) {
            this.zze.zzj.zzb.zza(zzaqa.zza(zzkoVar));
        }
        if (this.zze.zzf == null) {
            return;
        }
        if (this.zze.zzf.getChildCount() > 1) {
            this.zze.zzf.removeView(this.zze.zzf.getNextView());
        }
        this.zze.zzf.setMinimumWidth(zzkoVar.zzf);
        this.zze.zzf.setMinimumHeight(zzkoVar.zzc);
        this.zze.zzf.requestLayout();
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzlf zzlfVar) {
        com.google.android.gms.common.internal.zzbq.zzb("setAdListener must be called on the main UI thread.");
        this.zze.zzm = zzlfVar;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzli zzliVar) {
        com.google.android.gms.common.internal.zzbq.zzb("setAdListener must be called on the main UI thread.");
        this.zze.zzn = zzliVar;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzly zzlyVar) {
        com.google.android.gms.common.internal.zzbq.zzb("setAppEventListener must be called on the main UI thread.");
        this.zze.zzo = zzlyVar;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzme zzmeVar) {
        com.google.android.gms.common.internal.zzbq.zzb("setCorrelationIdProvider must be called on the main UI thread");
        this.zze.zzp = zzmeVar;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzms zzmsVar) {
        com.google.android.gms.common.internal.zzbq.zzb("setIconAdOptions must be called on the main UI thread.");
        this.zze.zzx = zzmsVar;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzns zznsVar) {
        com.google.android.gms.common.internal.zzbq.zzb("setVideoOptions must be called on the main UI thread.");
        this.zze.zzw = zznsVar;
    }

    public final void zza(zzot zzotVar) {
        this.zza = new zzov(((Boolean) zzlc.zzf().zza(zzoi.zzaj)).booleanValue(), "load_ad", this.zze.zzi.zza);
        this.zzi = new zzot(-1L, null, null);
        if (zzotVar == null) {
            this.zzb = new zzot(-1L, null, null);
        } else {
            this.zzb = new zzot(zzotVar.zza(), zzotVar.zzb(), zzotVar.zzc());
        }
    }

    @Override // com.google.android.gms.internal.zzlt
    public void zza(zzpb zzpbVar) {
        throw new IllegalStateException("setOnCustomRenderedAdLoadedListener is not supported for current ad type");
    }

    @Override // com.google.android.gms.internal.zzlt
    public void zza(zzyx zzyxVar) {
        zzahw.zze("setInAppPurchaseListener is deprecated and should not be called.");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzzd zzzdVar, String str) {
        zzahw.zze("setPlayStorePurchaseParams is deprecated and should not be called.");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(String str) {
        com.google.android.gms.common.internal.zzbq.zzb("setUserId must be called on the main UI thread.");
        this.zze.zzac = str;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzb
    public final void zza(String str, String str2) {
        if (this.zze.zzo != null) {
            try {
                this.zze.zzo.zza(str, str2);
            } catch (RemoteException e) {
                zzahw.zzc("Could not call the AppEventListener.", e);
            }
        }
    }

    @Override // com.google.android.gms.internal.zzahn
    public final void zza(HashSet<zzahf> hashSet) {
        this.zze.zza(hashSet);
    }

    @Override // com.google.android.gms.internal.zzlt
    public void zza(boolean z) {
        zzahw.zze("Attempt to call setManualImpressionsEnabled for an unsupported ad type.");
    }

    boolean zza(zzahd zzahdVar) {
        return false;
    }

    protected abstract boolean zza(zzahd zzahdVar, zzahd zzahdVar2);

    protected abstract boolean zza(zzkk zzkkVar, zzov zzovVar);

    @Override // com.google.android.gms.internal.zzlt
    public void zzb() {
        com.google.android.gms.common.internal.zzbq.zzb("destroy must be called on the main UI thread.");
        this.zzd.zza();
        this.zzg.zzb(this.zze.zzj);
        zzbu zzbuVar = this.zze;
        if (zzbuVar.zzf != null) {
            zzbuVar.zzf.zzb();
        }
        zzbuVar.zzn = null;
        zzbuVar.zzo = null;
        zzbuVar.zzaa = null;
        zzbuVar.zzp = null;
        zzbuVar.zza(false);
        if (zzbuVar.zzf != null) {
            zzbuVar.zzf.removeAllViews();
        }
        zzbuVar.zzb();
        zzbuVar.zzc();
        zzbuVar.zzj = null;
    }

    @Override // com.google.android.gms.internal.zzzn
    public void zzb(zzahd zzahdVar) {
        zziu zziuVar;
        zziw.zza.zzb zzbVar;
        this.zza.zza(this.zzi, "awr");
        this.zze.zzh = null;
        if (zzahdVar.zzd != -2 && zzahdVar.zzd != 3 && this.zze.zza() != null) {
            zzbt.zzj().zza(this.zze.zza());
        }
        if (zzahdVar.zzd == -1) {
            this.zzc = false;
            return;
        }
        if (zza(zzahdVar)) {
            zzahw.zzb("Ad refresh scheduled.");
        }
        if (zzahdVar.zzd != -2) {
            if (zzahdVar.zzd == 3) {
                zziuVar = zzahdVar.zzah;
                zzbVar = zziw.zza.zzb.AD_FAILED_TO_LOAD_NO_FILL;
            } else {
                zziuVar = zzahdVar.zzah;
                zzbVar = zziw.zza.zzb.AD_FAILED_TO_LOAD;
            }
            zziuVar.zza(zzbVar);
            zza(zzahdVar.zzd);
            return;
        }
        if (this.zze.zzae == null) {
            this.zze.zzae = new zzahp(this.zze.zzb);
        }
        if (this.zze.zzf != null) {
            this.zze.zzf.zza().zzd(zzahdVar.zzaa);
        }
        this.zzg.zza(this.zze.zzj);
        if (zza(this.zze.zzj, zzahdVar)) {
            this.zze.zzj = zzahdVar;
            zzbu zzbuVar = this.zze;
            if (zzbuVar.zzl != null) {
                if (zzbuVar.zzj != null) {
                    zzbuVar.zzl.zza(zzbuVar.zzj.zzx);
                    zzbuVar.zzl.zzb(zzbuVar.zzj.zzy);
                    zzbuVar.zzl.zzb(zzbuVar.zzj.zzm);
                }
                zzbuVar.zzl.zza(zzbuVar.zzi.zzd);
            }
            zzov zzovVar = this.zza;
            boolean zZza = this.zze.zzj.zza();
            String str = LocalSearchProvider.LIVE_CONTENTS;
            zzovVar.zza("is_mraid", zZza ? LocalSearchProvider.LIVE_CONTENTS : LocalSearchProvider.NO_LIVE_CONTENTS);
            this.zza.zza("is_mediation", this.zze.zzj.zzm ? LocalSearchProvider.LIVE_CONTENTS : LocalSearchProvider.NO_LIVE_CONTENTS);
            if (this.zze.zzj.zzb != null && this.zze.zzj.zzb.zzv() != null) {
                zzov zzovVar2 = this.zza;
                if (!this.zze.zzj.zzb.zzv().zzf()) {
                    str = LocalSearchProvider.NO_LIVE_CONTENTS;
                }
                zzovVar2.zza("is_delay_pl", str);
            }
            this.zza.zza(this.zzb, "ttc");
            if (zzbt.zzi().zzb() != null) {
                zzbt.zzi().zzb().zza(this.zza);
            }
            zzu();
            if (this.zze.zzd()) {
                zzp();
            }
        }
        if (zzahdVar.zzag != null) {
            zzbt.zze().zza(this.zze.zzc, zzahdVar.zzag);
        }
    }

    @Override // com.google.android.gms.internal.zzlt
    public void zzb(boolean z) {
        throw new IllegalStateException("onImmersiveModeUpdated is not supported for current ad type");
    }

    @Override // com.google.android.gms.internal.zzlt
    public boolean zzb(zzkk zzkkVar) {
        String string;
        com.google.android.gms.common.internal.zzbq.zzb("loadAd must be called on the main UI thread.");
        zzbt.zzk().zza();
        if (((Boolean) zzlc.zzf().zza(zzoi.zzcg)).booleanValue()) {
            zzkkVar = zzkkVar.zza();
            if (((Boolean) zzlc.zzf().zza(zzoi.zzch)).booleanValue()) {
                zzkkVar.zzc.putBoolean(AdMobAdapter.NEW_BUNDLE, true);
            }
        }
        if (com.google.android.gms.common.util.zzj.zzc(this.zze.zzc) && zzkkVar.zzk != null) {
            zzkkVar = new zzkl(zzkkVar).zza(null).zza();
        }
        if (this.zze.zzg != null || this.zze.zzh != null) {
            zzahw.zze(this.zzf != null ? "Aborting last ad request since another ad request is already in progress. The current request object will still be cached for future refreshes." : "Loading already in progress, saving this object for future refreshes.");
            this.zzf = zzkkVar;
            return false;
        }
        zzahw.zzd("Starting ad request.");
        zza((zzot) null);
        this.zzb = this.zza.zza();
        if (zzkkVar.zzf) {
            string = "This request is sent from a test device.";
        } else {
            zzlc.zza();
            String strZza = zzako.zza(this.zze.zzc);
            StringBuilder sb = new StringBuilder(String.valueOf(strZza).length() + 71);
            sb.append("Use AdRequest.Builder.addTestDevice(\"");
            sb.append(strZza);
            sb.append("\") to get test ads on this device.");
            string = sb.toString();
        }
        zzahw.zzd(string);
        this.zzd.zza(zzkkVar);
        boolean zZza = zza(zzkkVar, this.zza);
        this.zzc = zZza;
        return zZza;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final IObjectWrapper zzc() {
        com.google.android.gms.common.internal.zzbq.zzb("getAdFrame must be called on the main UI thread.");
        return com.google.android.gms.dynamic.zzn.zza(this.zze.zzf);
    }

    protected void zzc(boolean z) {
        zzahw.zzd("Ad finished loading.");
        this.zzc = z;
        if (this.zze.zzn != null) {
            try {
                this.zze.zzn.zzc();
            } catch (RemoteException e) {
                zzahw.zzc("Could not call AdListener.onAdLoaded().", e);
            }
        }
        if (this.zze.zzab != null) {
            try {
                this.zze.zzab.zza();
            } catch (RemoteException e2) {
                zzahw.zzc("Could not call RewardedVideoAdListener.onRewardedVideoAdLoaded().", e2);
            }
        }
    }

    protected boolean zzc(zzkk zzkkVar) {
        if (this.zze.zzf == null) {
            return false;
        }
        Object parent = this.zze.zzf.getParent();
        if (!(parent instanceof View)) {
            return false;
        }
        View view = (View) parent;
        return zzbt.zze().zza(view, view.getContext());
    }

    @Override // com.google.android.gms.internal.zzlt
    public final zzko zzd() {
        com.google.android.gms.common.internal.zzbq.zzb("getAdSize must be called on the main UI thread.");
        if (this.zze.zzi == null) {
            return null;
        }
        return new zznq(this.zze.zzi);
    }

    @Override // com.google.android.gms.internal.zzlt
    public final boolean zze() {
        com.google.android.gms.common.internal.zzbq.zzb("isLoaded must be called on the main UI thread.");
        return this.zze.zzg == null && this.zze.zzh == null && this.zze.zzj != null;
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzt
    public final void zzf() {
        zzn();
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzg() {
        com.google.android.gms.common.internal.zzbq.zzb("recordManualImpression must be called on the main UI thread.");
        if (this.zze.zzj == null) {
            zzahw.zze("Ad state was null when trying to ping manual tracking URLs.");
            return;
        }
        zzahw.zzb("Pinging manual tracking URLs.");
        if (this.zze.zzj.zzae) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (this.zze.zzj.zzf != null) {
            arrayList.addAll(this.zze.zzj.zzf);
        }
        if (this.zze.zzj.zzn != null && this.zze.zzj.zzn.zzh != null) {
            arrayList.addAll(this.zze.zzj.zzn.zzh);
        }
        if (arrayList.isEmpty()) {
            return;
        }
        zzbt.zze();
        zzaij.zza(this.zze.zzc, this.zze.zze.zza, arrayList);
        this.zze.zzj.zzae = true;
    }

    @Override // com.google.android.gms.internal.zzlt
    public void zzh() {
        com.google.android.gms.common.internal.zzbq.zzb("pause must be called on the main UI thread.");
    }

    @Override // com.google.android.gms.internal.zzlt
    public void zzi() {
        com.google.android.gms.common.internal.zzbq.zzb("resume must be called on the main UI thread.");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzj() {
        com.google.android.gms.common.internal.zzbq.zzb("stopLoading must be called on the main UI thread.");
        this.zzc = false;
        this.zze.zza(true);
    }

    @Override // com.google.android.gms.internal.zzlt
    public final boolean zzk() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.zzlt
    public zzmm zzl() {
        return null;
    }

    protected void zzm() {
        zzahw.zzd("Ad closing.");
        if (this.zze.zzn != null) {
            try {
                this.zze.zzn.zza();
            } catch (RemoteException e) {
                zzahw.zzc("Could not call AdListener.onAdClosed().", e);
            }
        }
        if (this.zze.zzab != null) {
            try {
                this.zze.zzab.zzd();
            } catch (RemoteException e2) {
                zzahw.zzc("Could not call RewardedVideoAdListener.onRewardedVideoAdClosed().", e2);
            }
        }
    }

    protected final void zzn() {
        zzahw.zzd("Ad leaving application.");
        if (this.zze.zzn != null) {
            try {
                this.zze.zzn.zzb();
            } catch (RemoteException e) {
                zzahw.zzc("Could not call AdListener.onAdLeftApplication().", e);
            }
        }
        if (this.zze.zzab != null) {
            try {
                this.zze.zzab.zze();
            } catch (RemoteException e2) {
                zzahw.zzc("Could not call  RewardedVideoAdListener.onRewardedVideoAdLeftApplication().", e2);
            }
        }
    }

    protected final void zzo() {
        zzahw.zzd("Ad opening.");
        if (this.zze.zzn != null) {
            try {
                this.zze.zzn.zzd();
            } catch (RemoteException e) {
                zzahw.zzc("Could not call AdListener.onAdOpened().", e);
            }
        }
        if (this.zze.zzab != null) {
            try {
                this.zze.zzab.zzb();
            } catch (RemoteException e2) {
                zzahw.zzc("Could not call RewardedVideoAdListener.onRewardedVideoAdOpened().", e2);
            }
        }
    }

    protected void zzp() {
        zzc(false);
    }

    public final void zzq() {
        zzahw.zzd("Ad impression.");
        if (this.zze.zzn != null) {
            try {
                this.zze.zzn.zzf();
            } catch (RemoteException e) {
                zzahw.zzc("Could not call AdListener.onAdImpression().", e);
            }
        }
    }

    public final void zzr() {
        zzahw.zzd("Ad clicked.");
        if (this.zze.zzn != null) {
            try {
                this.zze.zzn.zze();
            } catch (RemoteException e) {
                zzahw.zzc("Could not call AdListener.onAdClicked().", e);
            }
        }
    }

    protected final void zzs() {
        if (this.zze.zzab == null) {
            return;
        }
        try {
            this.zze.zzab.zzc();
        } catch (RemoteException e) {
            zzahw.zzc("Could not call RewardedVideoAdListener.onVideoStarted().", e);
        }
    }

    protected final void zzt() {
        if (this.zze.zzab == null) {
            return;
        }
        try {
            this.zze.zzab.zzf();
        } catch (RemoteException e) {
            zzahw.zzc("Could not call RewardedVideoAdListener.onRewardedVideoCompleted().", e);
        }
    }

    public final void zzu() {
        zzahd zzahdVar = this.zze.zzj;
        if (zzahdVar == null || TextUtils.isEmpty(zzahdVar.zzaa) || zzahdVar.zzaf || !zzbt.zzo().zzb()) {
            return;
        }
        zzahw.zzb("Sending troubleshooting signals to the server.");
        zzbt.zzo().zzb(this.zze.zzc, this.zze.zze.zza, zzahdVar.zzaa, this.zze.zzb);
        zzahdVar.zzaf = true;
    }

    @Override // com.google.android.gms.internal.zzlt
    public String zzv() {
        return this.zze.zzb;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final zzly zzw() {
        return this.zze.zzo;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final zzli zzx() {
        return this.zze.zzn;
    }
}
