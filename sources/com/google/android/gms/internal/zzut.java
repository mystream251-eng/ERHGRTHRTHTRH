package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzak;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzut extends zzlu {
    private final String zza;
    private boolean zzb;
    private final zztk zzc;
    private zzak zzd;
    private final zzul zze;

    public zzut(Context context, String str, zzwf zzwfVar, zzala zzalaVar, zzv zzvVar) {
        this(str, new zztk(context, zzwfVar, zzalaVar, zzvVar));
    }

    private zzut(String str, zztk zztkVar) {
        this.zza = str;
        this.zzc = zztkVar;
        this.zze = new zzul();
        zzbt.zzr().zza(zztkVar);
    }

    private final void zza() {
        if (this.zzd != null) {
            return;
        }
        zzak zzakVarZza = this.zzc.zza(this.zza);
        this.zzd = zzakVarZza;
        this.zze.zza(zzakVarZza);
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzafc zzafcVar) {
        this.zze.zze = zzafcVar;
        zzak zzakVar = this.zzd;
        if (zzakVar != null) {
            this.zze.zza(zzakVar);
        }
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzko zzkoVar) throws RemoteException {
        zzak zzakVar = this.zzd;
        if (zzakVar != null) {
            zzakVar.zza(zzkoVar);
        }
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzlf zzlfVar) throws RemoteException {
        this.zze.zzd = zzlfVar;
        zzak zzakVar = this.zzd;
        if (zzakVar != null) {
            this.zze.zza(zzakVar);
        }
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzli zzliVar) throws RemoteException {
        this.zze.zza = zzliVar;
        zzak zzakVar = this.zzd;
        if (zzakVar != null) {
            this.zze.zza(zzakVar);
        }
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzly zzlyVar) throws RemoteException {
        this.zze.zzb = zzlyVar;
        zzak zzakVar = this.zzd;
        if (zzakVar != null) {
            this.zze.zza(zzakVar);
        }
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzme zzmeVar) throws RemoteException {
        zza();
        zzak zzakVar = this.zzd;
        if (zzakVar != null) {
            zzakVar.zza(zzmeVar);
        }
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzms zzmsVar) {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzns zznsVar) {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzpb zzpbVar) throws RemoteException {
        this.zze.zzc = zzpbVar;
        zzak zzakVar = this.zzd;
        if (zzakVar != null) {
            this.zze.zza(zzakVar);
        }
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzyx zzyxVar) throws RemoteException {
        zzahw.zze("setInAppPurchaseListener is deprecated and should not be called.");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzzd zzzdVar, String str) throws RemoteException {
        zzahw.zze("setPlayStorePurchaseParams is deprecated and should not be called.");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(String str) {
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(boolean z) throws RemoteException {
        zza();
        zzak zzakVar = this.zzd;
        if (zzakVar != null) {
            zzakVar.zza(z);
        }
    }

    @Override // com.google.android.gms.internal.zzlt
    public final String zzal() throws RemoteException {
        zzak zzakVar = this.zzd;
        if (zzakVar != null) {
            return zzakVar.zzal();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final String zzam() throws RemoteException {
        zzak zzakVar = this.zzd;
        if (zzakVar != null) {
            return zzakVar.zzam();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzan() throws RemoteException {
        zzak zzakVar = this.zzd;
        if (zzakVar == null) {
            zzahw.zze("Interstitial ad must be loaded before showInterstitial().");
        } else {
            zzakVar.zzb(this.zzb);
            this.zzd.zzan();
        }
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzb() throws RemoteException {
        zzak zzakVar = this.zzd;
        if (zzakVar != null) {
            zzakVar.zzb();
        }
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzb(boolean z) {
        this.zzb = z;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final boolean zzb(zzkk zzkkVar) throws RemoteException {
        if (!zzuo.zza(zzkkVar).contains("gw")) {
            zza();
        }
        if (zzuo.zza(zzkkVar).contains("_skipMediation")) {
            zza();
        }
        if (zzkkVar.zzj != null) {
            zza();
        }
        zzak zzakVar = this.zzd;
        if (zzakVar != null) {
            return zzakVar.zzb(zzkkVar);
        }
        zzuo zzuoVarZzr = zzbt.zzr();
        if (zzuo.zza(zzkkVar).contains("_ad")) {
            zzuoVarZzr.zzb(zzkkVar, this.zza);
        }
        zzur zzurVarZza = zzuoVarZzr.zza(zzkkVar, this.zza);
        if (zzurVarZza == null) {
            zza();
            zzus.zza().zze();
            return this.zzd.zzb(zzkkVar);
        }
        if (zzurVarZza.zze) {
            zzus.zza().zzd();
        } else {
            zzurVarZza.zza();
            zzus.zza().zze();
        }
        this.zzd = zzurVarZza.zza;
        zzurVarZza.zzc.zza(this.zze);
        this.zze.zza(this.zzd);
        return zzurVarZza.zzf;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final IObjectWrapper zzc() throws RemoteException {
        zzak zzakVar = this.zzd;
        if (zzakVar != null) {
            return zzakVar.zzc();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final zzko zzd() throws RemoteException {
        zzak zzakVar = this.zzd;
        if (zzakVar != null) {
            return zzakVar.zzd();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final boolean zze() throws RemoteException {
        zzak zzakVar = this.zzd;
        return zzakVar != null && zzakVar.zze();
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzg() throws RemoteException {
        zzak zzakVar = this.zzd;
        if (zzakVar != null) {
            zzakVar.zzg();
        } else {
            zzahw.zze("Interstitial ad must be loaded before pingManualTrackingUrl().");
        }
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzh() throws RemoteException {
        zzak zzakVar = this.zzd;
        if (zzakVar != null) {
            zzakVar.zzh();
        }
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzi() throws RemoteException {
        zzak zzakVar = this.zzd;
        if (zzakVar != null) {
            zzakVar.zzi();
        }
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzj() throws RemoteException {
        zzak zzakVar = this.zzd;
        if (zzakVar != null) {
            zzakVar.zzj();
        }
    }

    @Override // com.google.android.gms.internal.zzlt
    public final boolean zzk() throws RemoteException {
        zzak zzakVar = this.zzd;
        return zzakVar != null && zzakVar.zzk();
    }

    @Override // com.google.android.gms.internal.zzlt
    public final zzmm zzl() {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final String zzv() {
        throw new IllegalStateException("getAdUnitId not implemented");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final zzly zzw() {
        throw new IllegalStateException("getIAppEventListener not implemented");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final zzli zzx() {
        throw new IllegalStateException("getIAdListener not implemented");
    }
}
