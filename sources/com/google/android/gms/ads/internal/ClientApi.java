package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzaeq;
import com.google.android.gms.internal.zzaex;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzlo;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.internal.zzmc;
import com.google.android.gms.internal.zzmh;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzqj;
import com.google.android.gms.internal.zzql;
import com.google.android.gms.internal.zzqw;
import com.google.android.gms.internal.zzrb;
import com.google.android.gms.internal.zzut;
import com.google.android.gms.internal.zzwf;
import com.google.android.gms.internal.zzyq;
import com.google.android.gms.internal.zzza;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public class ClientApi extends zzmc {
    @Override // com.google.android.gms.internal.zzmb
    public zzlo createAdLoaderBuilder(IObjectWrapper iObjectWrapper, String str, zzwf zzwfVar, int i) {
        Context context = (Context) com.google.android.gms.dynamic.zzn.zza(iObjectWrapper);
        zzbt.zze();
        return new zzaj(context, str, zzwfVar, new zzala(12210000, i, true, zzaij.zzk(context)), zzv.zza(context));
    }

    @Override // com.google.android.gms.internal.zzmb
    public zzyq createAdOverlay(IObjectWrapper iObjectWrapper) {
        Activity activity = (Activity) com.google.android.gms.dynamic.zzn.zza(iObjectWrapper);
        AdOverlayInfoParcel adOverlayInfoParcelZza = AdOverlayInfoParcel.zza(activity.getIntent());
        if (adOverlayInfoParcelZza == null) {
            return new com.google.android.gms.ads.internal.overlay.zzr(activity);
        }
        int i = adOverlayInfoParcelZza.zzk;
        if (i == 1) {
            return new com.google.android.gms.ads.internal.overlay.zzq(activity);
        }
        if (i == 2) {
            return new com.google.android.gms.ads.internal.overlay.zzx(activity);
        }
        if (i != 3) {
            return i != 4 ? new com.google.android.gms.ads.internal.overlay.zzr(activity) : new com.google.android.gms.ads.internal.overlay.zzs(activity, adOverlayInfoParcelZza);
        }
        return new com.google.android.gms.ads.internal.overlay.zzy(activity);
    }

    @Override // com.google.android.gms.internal.zzmb
    public zzlt createBannerAdManager(IObjectWrapper iObjectWrapper, zzko zzkoVar, String str, zzwf zzwfVar, int i) throws RemoteException {
        Context context = (Context) com.google.android.gms.dynamic.zzn.zza(iObjectWrapper);
        zzbt.zze();
        return new zzx(context, zzkoVar, str, zzwfVar, new zzala(12210000, i, true, zzaij.zzk(context)), zzv.zza(context));
    }

    @Override // com.google.android.gms.internal.zzmb
    public zzza createInAppPurchaseManager(IObjectWrapper iObjectWrapper) {
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x004b  */
    /* JADX WARN: Code duplicated, block: B:6:0x0036 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:7:0x0038  */
    @Override // com.google.android.gms.internal.zzmb
    public zzlt createInterstitialAdManager(IObjectWrapper iObjectWrapper, zzko zzkoVar, String str, zzwf zzwfVar, int i) throws RemoteException {
        Context context = (Context) com.google.android.gms.dynamic.zzn.zza(iObjectWrapper);
        zzoi.zza(context);
        zzbt.zze();
        boolean z = true;
        zzala zzalaVar = new zzala(12210000, i, true, zzaij.zzk(context));
        boolean zEquals = "reward_mb".equals(zzkoVar.zza);
        if (!zEquals) {
            if (!((Boolean) zzlc.zzf().zza(zzoi.zzcl)).booleanValue()) {
                if (zEquals) {
                    if (!((Boolean) zzlc.zzf().zza(zzoi.zzcm)).booleanValue()) {
                        z = false;
                    }
                } else {
                    z = false;
                }
            }
        } else if (zEquals) {
            if (!((Boolean) zzlc.zzf().zza(zzoi.zzcm)).booleanValue()) {
                z = false;
            }
        } else {
            z = false;
        }
        return z ? new zzut(context, str, zzwfVar, zzalaVar, zzv.zza(context)) : new zzak(context, zzkoVar, str, zzwfVar, zzalaVar, zzv.zza(context));
    }

    @Override // com.google.android.gms.internal.zzmb
    public zzqw createNativeAdViewDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        return new zzqj((FrameLayout) com.google.android.gms.dynamic.zzn.zza(iObjectWrapper), (FrameLayout) com.google.android.gms.dynamic.zzn.zza(iObjectWrapper2));
    }

    @Override // com.google.android.gms.internal.zzmb
    public zzrb createNativeAdViewHolderDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        return new zzql((View) com.google.android.gms.dynamic.zzn.zza(iObjectWrapper), (HashMap) com.google.android.gms.dynamic.zzn.zza(iObjectWrapper2), (HashMap) com.google.android.gms.dynamic.zzn.zza(iObjectWrapper3));
    }

    @Override // com.google.android.gms.internal.zzmb
    public zzaex createRewardedVideoAd(IObjectWrapper iObjectWrapper, zzwf zzwfVar, int i) {
        Context context = (Context) com.google.android.gms.dynamic.zzn.zza(iObjectWrapper);
        zzbt.zze();
        return new zzaeq(context, zzv.zza(context), zzwfVar, new zzala(12210000, i, true, zzaij.zzk(context)));
    }

    @Override // com.google.android.gms.internal.zzmb
    public zzlt createSearchAdManager(IObjectWrapper iObjectWrapper, zzko zzkoVar, String str, int i) throws RemoteException {
        Context context = (Context) com.google.android.gms.dynamic.zzn.zza(iObjectWrapper);
        zzbt.zze();
        return new zzbn(context, zzkoVar, str, new zzala(12210000, i, true, zzaij.zzk(context)));
    }

    @Override // com.google.android.gms.internal.zzmb
    public zzmh getMobileAdsSettingsManager(IObjectWrapper iObjectWrapper) {
        return null;
    }

    @Override // com.google.android.gms.internal.zzmb
    public zzmh getMobileAdsSettingsManagerWithClientJarVersion(IObjectWrapper iObjectWrapper, int i) {
        Context context = (Context) com.google.android.gms.dynamic.zzn.zza(iObjectWrapper);
        zzbt.zze();
        return zzax.zza(context, new zzala(12210000, i, true, zzaij.zzk(context)));
    }
}
