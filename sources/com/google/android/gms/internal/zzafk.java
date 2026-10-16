package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.dynamic.zzn;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzafk implements RewardedVideoAd {
    private final zzaex zza;
    private final Context zzb;
    private final Object zzc = new Object();
    private RewardedVideoAdListener zzd;
    private String zze;

    public zzafk(Context context, zzaex zzaexVar) {
        this.zza = zzaexVar;
        this.zzb = context;
    }

    private final void zza(String str, zzmu zzmuVar) {
        synchronized (this.zzc) {
            if (this.zza == null) {
                return;
            }
            try {
                this.zza.zza(new zzafi(zzkn.zza(this.zzb, zzmuVar), str));
            } catch (RemoteException e) {
                zzaky.zzc("Could not forward loadAd to RewardedVideoAd", e);
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void destroy() {
        destroy(null);
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void destroy(Context context) {
        synchronized (this.zzc) {
            if (this.zza == null) {
                return;
            }
            try {
                this.zza.zzc(zzn.zza(context));
            } catch (RemoteException e) {
                zzaky.zzc("Could not forward destroy to RewardedVideoAd", e);
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final String getMediationAdapterClassName() {
        try {
            if (this.zza != null) {
                return this.zza.zzf();
            }
            return null;
        } catch (RemoteException e) {
            zzaky.zzc("Failed to get the mediation adapter class name.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final RewardedVideoAdListener getRewardedVideoAdListener() {
        RewardedVideoAdListener rewardedVideoAdListener;
        synchronized (this.zzc) {
            rewardedVideoAdListener = this.zzd;
        }
        return rewardedVideoAdListener;
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final String getUserId() {
        String str;
        synchronized (this.zzc) {
            str = this.zze;
        }
        return str;
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final boolean isLoaded() {
        synchronized (this.zzc) {
            if (this.zza == null) {
                return false;
            }
            try {
                return this.zza.zzb();
            } catch (RemoteException e) {
                zzaky.zzc("Could not forward isLoaded to RewardedVideoAd", e);
                return false;
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void loadAd(String str, AdRequest adRequest) {
        zza(str, adRequest.zza());
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void loadAd(String str, PublisherAdRequest publisherAdRequest) {
        zza(str, publisherAdRequest.zza());
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void pause() {
        pause(null);
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void pause(Context context) {
        synchronized (this.zzc) {
            if (this.zza == null) {
                return;
            }
            try {
                this.zza.zza(zzn.zza(context));
            } catch (RemoteException e) {
                zzaky.zzc("Could not forward pause to RewardedVideoAd", e);
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void resume() {
        resume(null);
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void resume(Context context) {
        synchronized (this.zzc) {
            if (this.zza == null) {
                return;
            }
            try {
                this.zza.zzb(zzn.zza(context));
            } catch (RemoteException e) {
                zzaky.zzc("Could not forward resume to RewardedVideoAd", e);
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void setImmersiveMode(boolean z) {
        synchronized (this.zzc) {
            if (this.zza != null) {
                try {
                    this.zza.zza(z);
                } catch (RemoteException e) {
                    zzaky.zzc("Could not forward setImmersiveMode to RewardedVideoAd", e);
                }
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        synchronized (this.zzc) {
            this.zzd = rewardedVideoAdListener;
            if (this.zza != null) {
                try {
                    this.zza.zza(new zzafh(rewardedVideoAdListener));
                } catch (RemoteException e) {
                    zzaky.zzc("Could not forward setRewardedVideoAdListener to RewardedVideoAd", e);
                }
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void setUserId(String str) {
        synchronized (this.zzc) {
            this.zze = str;
            if (this.zza != null) {
                try {
                    this.zza.zza(str);
                } catch (RemoteException e) {
                    zzaky.zzc("Could not forward setUserId to RewardedVideoAd", e);
                }
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void show() {
        synchronized (this.zzc) {
            if (this.zza == null) {
                return;
            }
            try {
                this.zza.zza();
            } catch (RemoteException e) {
                zzaky.zzc("Could not forward show to RewardedVideoAd", e);
            }
        }
    }
}
