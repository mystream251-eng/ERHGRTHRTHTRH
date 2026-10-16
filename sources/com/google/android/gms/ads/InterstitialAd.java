package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzkf;
import com.google.android.gms.internal.zzmy;

/* JADX INFO: loaded from: classes2.dex */
public final class InterstitialAd {
    private final zzmy zza;

    public InterstitialAd(Context context) {
        this.zza = new zzmy(context);
        zzbq.zza(context, "Context cannot be null");
    }

    public final AdListener getAdListener() {
        return this.zza.zza();
    }

    public final String getAdUnitId() {
        return this.zza.zzb();
    }

    public final String getMediationAdapterClassName() {
        return this.zza.zzg();
    }

    public final boolean isLoaded() {
        return this.zza.zze();
    }

    public final boolean isLoading() {
        return this.zza.zzf();
    }

    public final void loadAd(AdRequest adRequest) {
        this.zza.zza(adRequest.zza());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setAdListener(AdListener adListener) {
        this.zza.zza(adListener);
        if (adListener != 0 && (adListener instanceof zzkf)) {
            this.zza.zza((zzkf) adListener);
        } else if (adListener == 0) {
            this.zza.zza((zzkf) null);
        }
    }

    public final void setAdUnitId(String str) {
        this.zza.zza(str);
    }

    public final void setImmersiveMode(boolean z) {
        this.zza.zzb(z);
    }

    public final void show() {
        this.zza.zzh();
    }

    public final void zza(RewardedVideoAdListener rewardedVideoAdListener) {
        this.zza.zza(rewardedVideoAdListener);
    }

    public final void zza(boolean z) {
        this.zza.zza(true);
    }
}
