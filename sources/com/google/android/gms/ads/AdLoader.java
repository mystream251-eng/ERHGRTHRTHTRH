package com.google.android.gms.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzaky;
import com.google.android.gms.internal.zzkh;
import com.google.android.gms.internal.zzkn;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzll;
import com.google.android.gms.internal.zzlo;
import com.google.android.gms.internal.zzmu;
import com.google.android.gms.internal.zzqh;
import com.google.android.gms.internal.zzsq;
import com.google.android.gms.internal.zzsr;
import com.google.android.gms.internal.zzss;
import com.google.android.gms.internal.zzst;
import com.google.android.gms.internal.zzsu;
import com.google.android.gms.internal.zzsw;
import com.google.android.gms.internal.zzwe;

/* JADX INFO: loaded from: classes2.dex */
public class AdLoader {
    private final zzkn zza;
    private final Context zzb;
    private final zzll zzc;

    public static class Builder {
        private final Context zza;
        private final zzlo zzb;

        private Builder(Context context, zzlo zzloVar) {
            this.zza = context;
            this.zzb = zzloVar;
        }

        public Builder(Context context, String str) {
            this((Context) zzbq.zza(context, "context cannot be null"), zzlc.zzb().zza(context, str, new zzwe()));
        }

        public AdLoader build() {
            try {
                return new AdLoader(this.zza, this.zzb.zza());
            } catch (RemoteException e) {
                zzaky.zzb("Failed to build AdLoader.", e);
                return null;
            }
        }

        public Builder forAppInstallAd(NativeAppInstallAd.OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
            try {
                this.zzb.zza(new zzsq(onAppInstallAdLoadedListener));
            } catch (RemoteException e) {
                zzaky.zzc("Failed to add app install ad listener", e);
            }
            return this;
        }

        public Builder forContentAd(NativeContentAd.OnContentAdLoadedListener onContentAdLoadedListener) {
            try {
                this.zzb.zza(new zzsr(onContentAdLoadedListener));
            } catch (RemoteException e) {
                zzaky.zzc("Failed to add content ad listener", e);
            }
            return this;
        }

        public Builder forCustomTemplateAd(String str, NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener, NativeCustomTemplateAd.OnCustomClickListener onCustomClickListener) {
            try {
                this.zzb.zza(str, new zzst(onCustomTemplateAdLoadedListener), onCustomClickListener == null ? null : new zzss(onCustomClickListener));
            } catch (RemoteException e) {
                zzaky.zzc("Failed to add custom template ad listener", e);
            }
            return this;
        }

        public Builder forPublisherAdView(OnPublisherAdViewLoadedListener onPublisherAdViewLoadedListener, AdSize... adSizeArr) {
            if (adSizeArr == null || adSizeArr.length <= 0) {
                throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
            }
            try {
                this.zzb.zza(new zzsu(onPublisherAdViewLoadedListener), new zzko(this.zza, adSizeArr));
            } catch (RemoteException e) {
                zzaky.zzc("Failed to add publisher banner ad listener", e);
            }
            return this;
        }

        public Builder withAdListener(AdListener adListener) {
            try {
                this.zzb.zza(new zzkh(adListener));
            } catch (RemoteException e) {
                zzaky.zzc("Failed to set AdListener.", e);
            }
            return this;
        }

        public Builder withCorrelator(Correlator correlator) {
            zzbq.zza(correlator);
            try {
                this.zzb.zza(correlator.zza());
            } catch (RemoteException e) {
                zzaky.zzc("Failed to set correlator.", e);
            }
            return this;
        }

        public Builder withNativeAdOptions(NativeAdOptions nativeAdOptions) {
            try {
                this.zzb.zza(new zzqh(nativeAdOptions));
            } catch (RemoteException e) {
                zzaky.zzc("Failed to specify native ad options", e);
            }
            return this;
        }

        public Builder withPublisherAdViewOptions(PublisherAdViewOptions publisherAdViewOptions) {
            try {
                this.zzb.zza(publisherAdViewOptions);
            } catch (RemoteException e) {
                zzaky.zzc("Failed to specify DFP banner ad options", e);
            }
            return this;
        }

        public final Builder zza(UnifiedNativeAd.zza zzaVar) {
            try {
                this.zzb.zza(new zzsw(zzaVar));
            } catch (RemoteException e) {
                zzaky.zzc("Failed to add google native ad listener", e);
            }
            return this;
        }
    }

    AdLoader(Context context, zzll zzllVar) {
        this(context, zzllVar, zzkn.zza);
    }

    private AdLoader(Context context, zzll zzllVar, zzkn zzknVar) {
        this.zzb = context;
        this.zzc = zzllVar;
        this.zza = zzknVar;
    }

    private final void zza(zzmu zzmuVar) {
        try {
            this.zzc.zza(zzkn.zza(this.zzb, zzmuVar));
        } catch (RemoteException e) {
            zzaky.zzb("Failed to load ad.", e);
        }
    }

    @Deprecated
    public String getMediationAdapterClassName() {
        try {
            return this.zzc.zzb();
        } catch (RemoteException e) {
            zzaky.zzc("Failed to get the mediation adapter class name.", e);
            return null;
        }
    }

    public boolean isLoading() {
        try {
            return this.zzc.zzc();
        } catch (RemoteException e) {
            zzaky.zzc("Failed to check if ad is loading.", e);
            return false;
        }
    }

    public void loadAd(AdRequest adRequest) {
        zza(adRequest.zza());
    }

    public void loadAd(PublisherAdRequest publisherAdRequest) {
        zza(publisherAdRequest.zza());
    }

    public void loadAds(AdRequest adRequest, int i) {
        try {
            this.zzc.zza(zzkn.zza(this.zzb, adRequest.zza()), i);
        } catch (RemoteException e) {
            zzaky.zzb("Failed to load ads.", e);
        }
    }
}
