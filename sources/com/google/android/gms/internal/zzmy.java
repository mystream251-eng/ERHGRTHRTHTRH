package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzmy {
    private final zzwe zza;
    private final Context zzb;
    private final zzkn zzc;
    private AdListener zzd;
    private zzkf zze;
    private zzlt zzf;
    private String zzg;
    private AppEventListener zzh;
    private PublisherInterstitialAd zzi;
    private OnCustomRenderedAdLoadedListener zzj;
    private Correlator zzk;
    private RewardedVideoAdListener zzl;
    private boolean zzm;
    private boolean zzn;

    public zzmy(Context context) {
        this(context, zzkn.zza, null);
    }

    public zzmy(Context context, PublisherInterstitialAd publisherInterstitialAd) {
        this(context, zzkn.zza, publisherInterstitialAd);
    }

    private zzmy(Context context, zzkn zzknVar, PublisherInterstitialAd publisherInterstitialAd) {
        this.zza = new zzwe();
        this.zzb = context;
        this.zzc = zzknVar;
        this.zzi = publisherInterstitialAd;
    }

    private final void zzb(String str) {
        if (this.zzf != null) {
            return;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 63);
        sb.append("The ad unit ID must be set on InterstitialAd before ");
        sb.append(str);
        sb.append(" is called.");
        throw new IllegalStateException(sb.toString());
    }

    public final AdListener zza() {
        return this.zzd;
    }

    public final void zza(AdListener adListener) {
        try {
            this.zzd = adListener;
            if (this.zzf != null) {
                this.zzf.zza(adListener != null ? new zzkh(adListener) : null);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set the AdListener.", e);
        }
    }

    public final void zza(Correlator correlator) {
        this.zzk = correlator;
        try {
            if (this.zzf != null) {
                this.zzf.zza(correlator == null ? null : correlator.zza());
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set correlator.", e);
        }
    }

    public final void zza(AppEventListener appEventListener) {
        try {
            this.zzh = appEventListener;
            if (this.zzf != null) {
                this.zzf.zza(appEventListener != null ? new zzkq(appEventListener) : null);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set the AppEventListener.", e);
        }
    }

    public final void zza(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        try {
            this.zzj = onCustomRenderedAdLoadedListener;
            if (this.zzf != null) {
                this.zzf.zza(onCustomRenderedAdLoadedListener != null ? new zzpe(onCustomRenderedAdLoadedListener) : null);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set the OnCustomRenderedAdLoadedListener.", e);
        }
    }

    public final void zza(RewardedVideoAdListener rewardedVideoAdListener) {
        try {
            this.zzl = rewardedVideoAdListener;
            if (this.zzf != null) {
                this.zzf.zza(rewardedVideoAdListener != null ? new zzafh(rewardedVideoAdListener) : null);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set the AdListener.", e);
        }
    }

    public final void zza(zzkf zzkfVar) {
        try {
            this.zze = zzkfVar;
            if (this.zzf != null) {
                this.zzf.zza(zzkfVar != null ? new zzkg(zzkfVar) : null);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set the AdClickListener.", e);
        }
    }

    public final void zza(zzmu zzmuVar) {
        try {
            if (this.zzf == null) {
                if (this.zzg == null) {
                    zzb("loadAd");
                }
                zzko zzkoVarZza = this.zzm ? zzko.zza() : new zzko();
                zzks zzksVarZzb = zzlc.zzb();
                Context context = this.zzb;
                zzlt zzltVar = (zzlt) zzks.zza(context, false, (zzks.zza) new zzkv(zzksVarZzb, context, zzkoVarZza, this.zzg, this.zza));
                this.zzf = zzltVar;
                if (this.zzd != null) {
                    zzltVar.zza(new zzkh(this.zzd));
                }
                if (this.zze != null) {
                    this.zzf.zza(new zzkg(this.zze));
                }
                if (this.zzh != null) {
                    this.zzf.zza(new zzkq(this.zzh));
                }
                if (this.zzj != null) {
                    this.zzf.zza(new zzpe(this.zzj));
                }
                if (this.zzk != null) {
                    this.zzf.zza(this.zzk.zza());
                }
                if (this.zzl != null) {
                    this.zzf.zza(new zzafh(this.zzl));
                }
                this.zzf.zzb(this.zzn);
            }
            if (this.zzf.zzb(zzkn.zza(this.zzb, zzmuVar))) {
                this.zza.zza(zzmuVar.zzj());
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to load ad.", e);
        }
    }

    public final void zza(String str) {
        if (this.zzg != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
        }
        this.zzg = str;
    }

    public final void zza(boolean z) {
        this.zzm = true;
    }

    public final String zzb() {
        return this.zzg;
    }

    public final void zzb(boolean z) {
        try {
            this.zzn = z;
            if (this.zzf != null) {
                this.zzf.zzb(z);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set immersive mode", e);
        }
    }

    public final AppEventListener zzc() {
        return this.zzh;
    }

    public final OnCustomRenderedAdLoadedListener zzd() {
        return this.zzj;
    }

    public final boolean zze() {
        try {
            if (this.zzf == null) {
                return false;
            }
            return this.zzf.zze();
        } catch (RemoteException e) {
            zzaky.zzc("Failed to check if ad is ready.", e);
            return false;
        }
    }

    public final boolean zzf() {
        try {
            if (this.zzf == null) {
                return false;
            }
            return this.zzf.zzk();
        } catch (RemoteException e) {
            zzaky.zzc("Failed to check if ad is loading.", e);
            return false;
        }
    }

    public final String zzg() {
        try {
            if (this.zzf != null) {
                return this.zzf.zzam();
            }
            return null;
        } catch (RemoteException e) {
            zzaky.zzc("Failed to get the mediation adapter class name.", e);
            return null;
        }
    }

    public final void zzh() {
        try {
            zzb("show");
            this.zzf.zzan();
        } catch (RemoteException e) {
            zzaky.zzc("Failed to show interstitial.", e);
        }
    }
}
