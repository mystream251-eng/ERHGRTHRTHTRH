package com.google.android.gms.internal;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzxh<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> extends zzwj {
    private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> zza;
    private final NETWORK_EXTRAS zzb;

    public zzxh(MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter, NETWORK_EXTRAS network_extras) {
        this.zza = mediationAdapter;
        this.zzb = network_extras;
    }

    private final SERVER_PARAMETERS zza(String str, int i, String str2) throws RemoteException {
        HashMap map;
        try {
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                map = new HashMap(jSONObject.length());
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    map.put(next, jSONObject.getString(next));
                }
            } else {
                map = new HashMap(0);
            }
            Class<SERVER_PARAMETERS> serverParametersType = this.zza.getServerParametersType();
            if (serverParametersType == null) {
                return null;
            }
            SERVER_PARAMETERS server_parametersNewInstance = serverParametersType.newInstance();
            server_parametersNewInstance.load(map);
            return server_parametersNewInstance;
        } catch (Throwable th) {
            zzaky.zzc("Could not get MediationServerParameters.", th);
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
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zza;
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
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzafz zzafzVar, List<String> list) {
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkkVar, String str, zzafz zzafzVar, String str2) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkkVar, String str, zzwl zzwlVar) throws RemoteException {
        zza(iObjectWrapper, zzkkVar, str, (String) null, zzwlVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkkVar, String str, String str2, zzwl zzwlVar) throws RemoteException {
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zza;
        if (!(mediationAdapter instanceof MediationInterstitialAdapter)) {
            String strValueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zze(strValueOf.length() != 0 ? "MediationAdapter is not a MediationInterstitialAdapter: ".concat(strValueOf) : new String("MediationAdapter is not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzb("Requesting interstitial ad from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zza).requestInterstitialAd(new zzxi(zzwlVar), (Activity) zzn.zza(iObjectWrapper), zza(str, zzkkVar.zzg, str2), zzxu.zza(zzkkVar, zza(zzkkVar)), this.zzb);
        } catch (Throwable th) {
            zzaky.zzc("Could not request interstitial ad from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkkVar, String str, String str2, zzwl zzwlVar, zzqh zzqhVar, List<String> list) {
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzko zzkoVar, zzkk zzkkVar, String str, zzwl zzwlVar) throws RemoteException {
        zza(iObjectWrapper, zzkoVar, zzkkVar, str, null, zzwlVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzko zzkoVar, zzkk zzkkVar, String str, String str2, zzwl zzwlVar) throws RemoteException {
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zza;
        if (!(mediationAdapter instanceof MediationBannerAdapter)) {
            String strValueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzaky.zze(strValueOf.length() != 0 ? "MediationAdapter is not a MediationBannerAdapter: ".concat(strValueOf) : new String("MediationAdapter is not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        zzaky.zzb("Requesting banner ad from adapter.");
        try {
            ((MediationBannerAdapter) this.zza).requestBannerAd(new zzxi(zzwlVar), (Activity) zzn.zza(iObjectWrapper), zza(str, zzkkVar.zzg, str2), zzxu.zza(zzkoVar), zzxu.zza(zzkkVar, zza(zzkkVar)), this.zzb);
        } catch (Throwable th) {
            zzaky.zzc("Could not request banner ad from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(zzkk zzkkVar, String str) {
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(zzkk zzkkVar, String str, String str2) {
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(boolean z) {
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zzb() throws RemoteException {
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zza;
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
            this.zza.destroy();
        } catch (Throwable th) {
            zzaky.zzc("Could not destroy adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zzd() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zze() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zzf() {
    }

    @Override // com.google.android.gms.internal.zzwi
    public final boolean zzg() {
        return true;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final zzwr zzh() {
        return null;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final zzwu zzi() {
        return null;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final Bundle zzj() {
        return new Bundle();
    }

    @Override // com.google.android.gms.internal.zzwi
    public final Bundle zzk() {
        return new Bundle();
    }

    @Override // com.google.android.gms.internal.zzwi
    public final Bundle zzl() {
        return new Bundle();
    }

    @Override // com.google.android.gms.internal.zzwi
    public final boolean zzm() {
        return false;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final zzro zzn() {
        return null;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final zzmm zzo() {
        return null;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final zzwx zzp() {
        return null;
    }
}
