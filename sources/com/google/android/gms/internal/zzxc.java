package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.ads.reward.mediation.InitializableMediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzxc extends zzwj {
    private final MediationAdapter zza;
    private zzxd zzb;

    public zzxc(MediationAdapter mediationAdapter) {
        this.zza = mediationAdapter;
    }

    private final Bundle zza(String str, zzkk zzkkVar, String str2) throws RemoteException {
        String strValueOf = String.valueOf(str);
        zzaky.zze(strValueOf.length() != 0 ? "Server parameters: ".concat(strValueOf) : new String("Server parameters: "));
        try {
            Bundle bundle = new Bundle();
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                Bundle bundle2 = new Bundle();
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    bundle2.putString(next, jSONObject.getString(next));
                }
                bundle = bundle2;
            }
            if (this.zza instanceof AdMobAdapter) {
                bundle.putString("adJson", str2);
                if (zzkkVar != null) {
                    bundle.putInt("tagForChildDirectedTreatment", zzkkVar.zzg);
                }
            }
            return bundle;
        } catch (Throwable th) {
            zzaky.zzc("Could not get Server Parameters Bundle.", th);
            throw new RemoteException();
        }
    }

    private static boolean zza(zzkk zzkkVar) {
        if (zzkkVar.zzf) {
            return true;
        }
        zzlc.zza();
        return zzako.zza();
    }

    @Override // com.google.android.gms.internal.zzwi
    public final IObjectWrapper zza() throws RemoteException {
        MediationAdapter mediationAdapter = this.zza;
        if (!(mediationAdapter instanceof MediationBannerAdapter)) {
            String strValueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zze(strValueOf.length() != 0 ? "MediationAdapter is not a MediationBannerAdapter: ".concat(strValueOf) : new String("MediationAdapter is not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        try {
            return zzn.zza(((MediationBannerAdapter) mediationAdapter).getBannerView());
        } catch (Throwable th) {
            zzaky.zzc("Could not get banner view from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper) throws RemoteException {
        try {
            ((OnContextChangedListener) this.zza).onContextChanged((Context) zzn.zza(iObjectWrapper));
        } catch (Throwable th) {
            zzaky.zza("Could not inform adapter of changed context", th);
        }
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzafz zzafzVar, List<String> list) throws RemoteException {
        MediationAdapter mediationAdapter = this.zza;
        if (!(mediationAdapter instanceof InitializableMediationRewardedVideoAdAdapter)) {
            String strValueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zze(strValueOf.length() != 0 ? "MediationAdapter is not an InitializableMediationRewardedVideoAdAdapter: ".concat(strValueOf) : new String("MediationAdapter is not an InitializableMediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzb("Initialize rewarded video adapter.");
        try {
            InitializableMediationRewardedVideoAdAdapter initializableMediationRewardedVideoAdAdapter = (InitializableMediationRewardedVideoAdAdapter) this.zza;
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(zza(it.next(), (zzkk) null, (String) null));
            }
            initializableMediationRewardedVideoAdAdapter.initialize((Context) zzn.zza(iObjectWrapper), new zzagc(zzafzVar), arrayList);
        } catch (Throwable th) {
            zzaky.zzc("Could not initialize rewarded video adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkkVar, String str, zzafz zzafzVar, String str2) throws RemoteException {
        zzxb zzxbVar;
        Bundle bundle;
        MediationAdapter mediationAdapter = this.zza;
        if (!(mediationAdapter instanceof MediationRewardedVideoAdAdapter)) {
            String strValueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zze(strValueOf.length() != 0 ? "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(strValueOf) : new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzb("Initialize rewarded video adapter.");
        try {
            MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zza;
            Bundle bundleZza = zza(str2, zzkkVar, (String) null);
            if (zzkkVar != null) {
                zzxb zzxbVar2 = new zzxb(zzkkVar.zzb == -1 ? null : new Date(zzkkVar.zzb), zzkkVar.zzd, zzkkVar.zze != null ? new HashSet(zzkkVar.zze) : null, zzkkVar.zzk, zza(zzkkVar), zzkkVar.zzg, zzkkVar.zzr);
                bundle = zzkkVar.zzm != null ? zzkkVar.zzm.getBundle(mediationRewardedVideoAdAdapter.getClass().getName()) : null;
                zzxbVar = zzxbVar2;
            } else {
                zzxbVar = null;
                bundle = null;
            }
            mediationRewardedVideoAdAdapter.initialize((Context) zzn.zza(iObjectWrapper), zzxbVar, str, new zzagc(zzafzVar), bundleZza, bundle);
        } catch (Throwable th) {
            zzaky.zzc("Could not initialize rewarded video adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkkVar, String str, zzwl zzwlVar) throws RemoteException {
        zza(iObjectWrapper, zzkkVar, str, (String) null, zzwlVar);
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkkVar, String str, String str2, zzwl zzwlVar) throws RemoteException {
        MediationAdapter mediationAdapter = this.zza;
        if (!(mediationAdapter instanceof MediationInterstitialAdapter)) {
            String strValueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zze(strValueOf.length() != 0 ? "MediationAdapter is not a MediationInterstitialAdapter: ".concat(strValueOf) : new String("MediationAdapter is not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzb("Requesting interstitial ad from adapter.");
        try {
            MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter) this.zza;
            mediationInterstitialAdapter.requestInterstitialAd((Context) zzn.zza(iObjectWrapper), new zzxd(zzwlVar), zza(str, zzkkVar, str2), new zzxb(zzkkVar.zzb == -1 ? null : new Date(zzkkVar.zzb), zzkkVar.zzd, zzkkVar.zze != null ? new HashSet(zzkkVar.zze) : null, zzkkVar.zzk, zza(zzkkVar), zzkkVar.zzg, zzkkVar.zzr), zzkkVar.zzm != null ? zzkkVar.zzm.getBundle(mediationInterstitialAdapter.getClass().getName()) : null);
        } catch (Throwable th) {
            zzaky.zzc("Could not request interstitial ad from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkkVar, String str, String str2, zzwl zzwlVar, zzqh zzqhVar, List<String> list) throws RemoteException {
        MediationAdapter mediationAdapter = this.zza;
        if (!(mediationAdapter instanceof MediationNativeAdapter)) {
            String strValueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zze(strValueOf.length() != 0 ? "MediationAdapter is not a MediationNativeAdapter: ".concat(strValueOf) : new String("MediationAdapter is not a MediationNativeAdapter: "));
            throw new RemoteException();
        }
        try {
            MediationNativeAdapter mediationNativeAdapter = (MediationNativeAdapter) mediationAdapter;
            zzxg zzxgVar = new zzxg(zzkkVar.zzb == -1 ? null : new Date(zzkkVar.zzb), zzkkVar.zzd, zzkkVar.zze != null ? new HashSet(zzkkVar.zze) : null, zzkkVar.zzk, zza(zzkkVar), zzkkVar.zzg, zzqhVar, list, zzkkVar.zzr);
            Bundle bundle = zzkkVar.zzm != null ? zzkkVar.zzm.getBundle(mediationNativeAdapter.getClass().getName()) : null;
            this.zzb = new zzxd(zzwlVar);
            mediationNativeAdapter.requestNativeAd((Context) zzn.zza(iObjectWrapper), this.zzb, zza(str, zzkkVar, str2), zzxgVar, bundle);
        } catch (Throwable th) {
            zzaky.zzc("Could not request native ad from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzko zzkoVar, zzkk zzkkVar, String str, zzwl zzwlVar) throws RemoteException {
        zza(iObjectWrapper, zzkoVar, zzkkVar, str, null, zzwlVar);
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzko zzkoVar, zzkk zzkkVar, String str, String str2, zzwl zzwlVar) throws RemoteException {
        MediationAdapter mediationAdapter = this.zza;
        if (!(mediationAdapter instanceof MediationBannerAdapter)) {
            String strValueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zze(strValueOf.length() != 0 ? "MediationAdapter is not a MediationBannerAdapter: ".concat(strValueOf) : new String("MediationAdapter is not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzb("Requesting banner ad from adapter.");
        try {
            MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.zza;
            mediationBannerAdapter.requestBannerAd((Context) zzn.zza(iObjectWrapper), new zzxd(zzwlVar), zza(str, zzkkVar, str2), zzb.zza(zzkoVar.zze, zzkoVar.zzb, zzkoVar.zza), new zzxb(zzkkVar.zzb == -1 ? null : new Date(zzkkVar.zzb), zzkkVar.zzd, zzkkVar.zze != null ? new HashSet(zzkkVar.zze) : null, zzkkVar.zzk, zza(zzkkVar), zzkkVar.zzg, zzkkVar.zzr), zzkkVar.zzm != null ? zzkkVar.zzm.getBundle(mediationBannerAdapter.getClass().getName()) : null);
        } catch (Throwable th) {
            zzaky.zzc("Could not request banner ad from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(zzkk zzkkVar, String str) throws RemoteException {
        zza(zzkkVar, str, (String) null);
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(zzkk zzkkVar, String str, String str2) throws RemoteException {
        MediationAdapter mediationAdapter = this.zza;
        if (!(mediationAdapter instanceof MediationRewardedVideoAdAdapter)) {
            String strValueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zze(strValueOf.length() != 0 ? "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(strValueOf) : new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzb("Requesting rewarded video ad from adapter.");
        try {
            MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zza;
            mediationRewardedVideoAdAdapter.loadAd(new zzxb(zzkkVar.zzb == -1 ? null : new Date(zzkkVar.zzb), zzkkVar.zzd, zzkkVar.zze != null ? new HashSet(zzkkVar.zze) : null, zzkkVar.zzk, zza(zzkkVar), zzkkVar.zzg, zzkkVar.zzr), zza(str, zzkkVar, str2), zzkkVar.zzm != null ? zzkkVar.zzm.getBundle(mediationRewardedVideoAdAdapter.getClass().getName()) : null);
        } catch (Throwable th) {
            zzaky.zzc("Could not load rewarded video ad from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(boolean z) throws RemoteException {
        MediationAdapter mediationAdapter = this.zza;
        if (!(mediationAdapter instanceof OnImmersiveModeUpdatedListener)) {
            String strValueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zzd(strValueOf.length() != 0 ? "MediationAdapter is not an OnImmersiveModeUpdatedListener: ".concat(strValueOf) : new String("MediationAdapter is not an OnImmersiveModeUpdatedListener: "));
        } else {
            try {
                ((OnImmersiveModeUpdatedListener) mediationAdapter).onImmersiveModeUpdated(z);
            } catch (Throwable th) {
                zzaky.zzc("Could not set immersive mode.", th);
            }
        }
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zzb() throws RemoteException {
        MediationAdapter mediationAdapter = this.zza;
        if (!(mediationAdapter instanceof MediationInterstitialAdapter)) {
            String strValueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zze(strValueOf.length() != 0 ? "MediationAdapter is not a MediationInterstitialAdapter: ".concat(strValueOf) : new String("MediationAdapter is not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzb("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zza).showInterstitial();
        } catch (Throwable th) {
            zzaky.zzc("Could not show interstitial from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zzc() throws RemoteException {
        try {
            this.zza.onDestroy();
        } catch (Throwable th) {
            zzaky.zzc("Could not destroy adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zzd() throws RemoteException {
        try {
            this.zza.onPause();
        } catch (Throwable th) {
            zzaky.zzc("Could not pause adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zze() throws RemoteException {
        try {
            this.zza.onResume();
        } catch (Throwable th) {
            zzaky.zzc("Could not resume adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zzf() throws RemoteException {
        MediationAdapter mediationAdapter = this.zza;
        if (!(mediationAdapter instanceof MediationRewardedVideoAdAdapter)) {
            String strValueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zze(strValueOf.length() != 0 ? "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(strValueOf) : new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzb("Show rewarded video ad from adapter.");
        try {
            ((MediationRewardedVideoAdAdapter) this.zza).showVideo();
        } catch (Throwable th) {
            zzaky.zzc("Could not show rewarded video ad from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.zzwi
    public final boolean zzg() throws RemoteException {
        MediationAdapter mediationAdapter = this.zza;
        if (!(mediationAdapter instanceof MediationRewardedVideoAdAdapter)) {
            String strValueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zze(strValueOf.length() != 0 ? "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(strValueOf) : new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzb("Check if adapter is initialized.");
        try {
            return ((MediationRewardedVideoAdAdapter) this.zza).isInitialized();
        } catch (Throwable th) {
            zzaky.zzc("Could not check if adapter is initialized.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.zzwi
    public final zzwr zzh() {
        NativeAdMapper nativeAdMapperZza = this.zzb.zza();
        if (nativeAdMapperZza instanceof NativeAppInstallAdMapper) {
            return new zzxe((NativeAppInstallAdMapper) nativeAdMapperZza);
        }
        return null;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final zzwu zzi() {
        NativeAdMapper nativeAdMapperZza = this.zzb.zza();
        if (nativeAdMapperZza instanceof NativeContentAdMapper) {
            return new zzxf((NativeContentAdMapper) nativeAdMapperZza);
        }
        return null;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final Bundle zzj() {
        MediationAdapter mediationAdapter = this.zza;
        if (mediationAdapter instanceof zzaqj) {
            return ((zzaqj) mediationAdapter).zza();
        }
        String strValueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
        zzaky.zze(strValueOf.length() != 0 ? "MediationAdapter is not a v2 MediationBannerAdapter: ".concat(strValueOf) : new String("MediationAdapter is not a v2 MediationBannerAdapter: "));
        return new Bundle();
    }

    @Override // com.google.android.gms.internal.zzwi
    public final Bundle zzk() {
        MediationAdapter mediationAdapter = this.zza;
        if (mediationAdapter instanceof zzaqk) {
            return ((zzaqk) mediationAdapter).getInterstitialAdapterInfo();
        }
        String strValueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
        zzaky.zze(strValueOf.length() != 0 ? "MediationAdapter is not a v2 MediationInterstitialAdapter: ".concat(strValueOf) : new String("MediationAdapter is not a v2 MediationInterstitialAdapter: "));
        return new Bundle();
    }

    @Override // com.google.android.gms.internal.zzwi
    public final Bundle zzl() {
        return new Bundle();
    }

    @Override // com.google.android.gms.internal.zzwi
    public final boolean zzm() {
        return this.zza instanceof InitializableMediationRewardedVideoAdAdapter;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final zzro zzn() {
        NativeCustomTemplateAd nativeCustomTemplateAdZzc = this.zzb.zzc();
        if (nativeCustomTemplateAdZzc instanceof zzrr) {
            return ((zzrr) nativeCustomTemplateAdZzc).zza();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final zzmm zzo() {
        MediationAdapter mediationAdapter = this.zza;
        if (!(mediationAdapter instanceof com.google.android.gms.ads.mediation.zzb)) {
            return null;
        }
        try {
            return ((com.google.android.gms.ads.mediation.zzb) mediationAdapter).getVideoController();
        } catch (Throwable th) {
            zzaky.zzc("Could not get video controller.", th);
            return null;
        }
    }

    @Override // com.google.android.gms.internal.zzwi
    public final zzwx zzp() {
        zza zzaVarZzb = this.zzb.zzb();
        if (zzaVarZzb != null) {
            return new zzxw(zzaVarZzb);
        }
        return null;
    }
}
