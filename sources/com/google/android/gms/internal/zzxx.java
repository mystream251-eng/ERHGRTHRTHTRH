package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.browser.customtabs.CustomTabsIntent;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.common.util.zzs;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzxx implements MediationInterstitialAdapter {
    private Activity zza;
    private MediationInterstitialListener zzb;
    private Uri zzc;

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public final void onDestroy() {
        zzaky.zzb("Destroying AdMobCustomTabsAdapter adapter.");
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public final void onPause() {
        zzaky.zzb("Pausing AdMobCustomTabsAdapter adapter.");
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public final void onResume() {
        zzaky.zzb("Resuming AdMobCustomTabsAdapter adapter.");
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public final void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzb = mediationInterstitialListener;
        if (mediationInterstitialListener == null) {
            zzaky.zze("Listener not set for mediation. Returning.");
            return;
        }
        if (!(context instanceof Activity)) {
            zzaky.zze("AdMobCustomTabs can only work with Activity context. Bailing out.");
            this.zzb.onAdFailedToLoad(this, 0);
            return;
        }
        if (!(zzs.zza() && zzpf.zza(context))) {
            zzaky.zze("Default browser does not support custom tabs. Bailing out.");
            this.zzb.onAdFailedToLoad(this, 0);
            return;
        }
        String string = bundle.getString("tab_url");
        if (TextUtils.isEmpty(string)) {
            zzaky.zze("The tab_url retrieved from mediation metadata is empty. Bailing out.");
            this.zzb.onAdFailedToLoad(this, 0);
        } else {
            this.zza = (Activity) context;
            this.zzc = Uri.parse(string);
            this.zzb.onAdLoaded(this);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public final void showInterstitial() {
        CustomTabsIntent customTabsIntentBuild = new CustomTabsIntent.Builder().build();
        customTabsIntentBuild.intent.setData(this.zzc);
        zzaij.zza.post(new zzxz(this, new AdOverlayInfoParcel(new zzc(customTabsIntentBuild.intent), null, new zzxy(this), null, new zzala(0, 0, false))));
        zzbt.zzi().zzf();
    }
}
