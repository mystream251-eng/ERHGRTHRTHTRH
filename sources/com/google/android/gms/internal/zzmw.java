package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzmw {
    private final zzwe zza;
    private final zzkn zzb;
    private final AtomicBoolean zzc;
    private final VideoController zzd;
    private zzle zze;
    private zzkf zzf;
    private AdListener zzg;
    private AdSize[] zzh;
    private AppEventListener zzi;
    private Correlator zzj;
    private zzlt zzk;
    private OnCustomRenderedAdLoadedListener zzl;
    private VideoOptions zzm;
    private String zzn;
    private ViewGroup zzo;
    private int zzp;
    private boolean zzq;

    public zzmw(ViewGroup viewGroup) {
        this(viewGroup, null, false, zzkn.zza, 0);
    }

    public zzmw(ViewGroup viewGroup, int i) {
        this(viewGroup, null, false, zzkn.zza, i);
    }

    public zzmw(ViewGroup viewGroup, AttributeSet attributeSet, boolean z) {
        this(viewGroup, attributeSet, z, zzkn.zza, 0);
    }

    public zzmw(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, int i) {
        this(viewGroup, attributeSet, false, zzkn.zza, i);
    }

    private zzmw(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, zzkn zzknVar, int i) {
        this(viewGroup, attributeSet, z, zzknVar, null, i);
    }

    private zzmw(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, zzkn zzknVar, zzlt zzltVar, int i) {
        this.zza = new zzwe();
        this.zzd = new VideoController();
        this.zze = new zzmx(this);
        this.zzo = viewGroup;
        this.zzb = zzknVar;
        this.zzk = null;
        this.zzc = new AtomicBoolean(false);
        this.zzp = i;
        if (attributeSet != null) {
            Context context = viewGroup.getContext();
            try {
                zzkr zzkrVar = new zzkr(context, attributeSet);
                this.zzh = zzkrVar.zza(z);
                this.zzn = zzkrVar.zza();
                if (viewGroup.isInEditMode()) {
                    zzako zzakoVarZza = zzlc.zza();
                    AdSize adSize = this.zzh[0];
                    int i2 = this.zzp;
                    zzko zzkoVar = new zzko(context, adSize);
                    zzkoVar.zzj = zza(i2);
                    zzakoVarZza.zza(viewGroup, zzkoVar, "Ads by Google");
                }
            } catch (IllegalArgumentException e) {
                zzlc.zza().zza(viewGroup, new zzko(context, AdSize.BANNER), e.getMessage(), e.getMessage());
            }
        }
    }

    private static zzko zza(Context context, AdSize[] adSizeArr, int i) {
        zzko zzkoVar = new zzko(context, adSizeArr);
        zzkoVar.zzj = zza(i);
        return zzkoVar;
    }

    private static boolean zza(int i) {
        return i == 1;
    }

    public final void zza() {
        try {
            if (this.zzk != null) {
                this.zzk.zzb();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to destroy AdView.", e);
        }
    }

    public final void zza(AdListener adListener) {
        this.zzg = adListener;
        this.zze.zza(adListener);
    }

    public final void zza(Correlator correlator) {
        this.zzj = correlator;
        try {
            if (this.zzk != null) {
                this.zzk.zza(correlator == null ? null : correlator.zza());
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set correlator.", e);
        }
    }

    public final void zza(VideoOptions videoOptions) {
        this.zzm = videoOptions;
        try {
            if (this.zzk != null) {
                this.zzk.zza(videoOptions == null ? null : new zzns(videoOptions));
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set video options.", e);
        }
    }

    public final void zza(AppEventListener appEventListener) {
        try {
            this.zzi = appEventListener;
            if (this.zzk != null) {
                this.zzk.zza(appEventListener != null ? new zzkq(appEventListener) : null);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set the AppEventListener.", e);
        }
    }

    public final void zza(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzl = onCustomRenderedAdLoadedListener;
        try {
            if (this.zzk != null) {
                this.zzk.zza(onCustomRenderedAdLoadedListener != null ? new zzpe(onCustomRenderedAdLoadedListener) : null);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set the onCustomRenderedAdLoadedListener.", e);
        }
    }

    public final void zza(zzkf zzkfVar) {
        try {
            this.zzf = zzkfVar;
            if (this.zzk != null) {
                this.zzk.zza(zzkfVar != null ? new zzkg(zzkfVar) : null);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set the AdClickListener.", e);
        }
    }

    public final void zza(zzmu zzmuVar) {
        try {
            if (this.zzk == null) {
                if ((this.zzh == null || this.zzn == null) && this.zzk == null) {
                    throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
                }
                Context context = this.zzo.getContext();
                zzko zzkoVarZza = zza(context, this.zzh, this.zzp);
                zzlt zzltVar = (zzlt) ("search_v2".equals(zzkoVarZza.zza) ? zzks.zza(context, false, (zzks.zza) new zzku(zzlc.zzb(), context, zzkoVarZza, this.zzn)) : zzks.zza(context, false, (zzks.zza) new zzkt(zzlc.zzb(), context, zzkoVarZza, this.zzn, this.zza)));
                this.zzk = zzltVar;
                zzltVar.zza(new zzkh(this.zze));
                if (this.zzf != null) {
                    this.zzk.zza(new zzkg(this.zzf));
                }
                if (this.zzi != null) {
                    this.zzk.zza(new zzkq(this.zzi));
                }
                if (this.zzl != null) {
                    this.zzk.zza(new zzpe(this.zzl));
                }
                if (this.zzj != null) {
                    this.zzk.zza(this.zzj.zza());
                }
                if (this.zzm != null) {
                    this.zzk.zza(new zzns(this.zzm));
                }
                this.zzk.zza(this.zzq);
                try {
                    IObjectWrapper iObjectWrapperZzc = this.zzk.zzc();
                    if (iObjectWrapperZzc != null) {
                        this.zzo.addView((View) zzn.zza(iObjectWrapperZzc));
                    }
                } catch (RemoteException e) {
                    zzaky.zzc("Failed to get an ad frame.", e);
                }
            }
            if (this.zzk.zzb(zzkn.zza(this.zzo.getContext(), zzmuVar))) {
                this.zza.zza(zzmuVar.zzj());
            }
        } catch (RemoteException e2) {
            zzaky.zzc("Failed to load ad.", e2);
        }
    }

    public final void zza(String str) {
        if (this.zzn != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
        }
        this.zzn = str;
    }

    public final void zza(boolean z) {
        this.zzq = z;
        try {
            if (this.zzk != null) {
                this.zzk.zza(z);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set manual impressions.", e);
        }
    }

    public final void zza(AdSize... adSizeArr) {
        if (this.zzh != null) {
            throw new IllegalStateException("The ad size can only be set once on AdView.");
        }
        zzb(adSizeArr);
    }

    public final boolean zza(zzlt zzltVar) {
        if (zzltVar == null) {
            return false;
        }
        try {
            IObjectWrapper iObjectWrapperZzc = zzltVar.zzc();
            if (iObjectWrapperZzc == null || ((View) zzn.zza(iObjectWrapperZzc)).getParent() != null) {
                return false;
            }
            this.zzo.addView((View) zzn.zza(iObjectWrapperZzc));
            this.zzk = zzltVar;
            return true;
        } catch (RemoteException e) {
            zzaky.zzc("Failed to get an ad frame.", e);
            return false;
        }
    }

    public final AdListener zzb() {
        return this.zzg;
    }

    public final void zzb(AdSize... adSizeArr) {
        this.zzh = adSizeArr;
        try {
            if (this.zzk != null) {
                this.zzk.zza(zza(this.zzo.getContext(), this.zzh, this.zzp));
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to set the ad size.", e);
        }
        this.zzo.requestLayout();
    }

    public final AdSize zzc() {
        zzko zzkoVarZzd;
        try {
            if (this.zzk != null && (zzkoVarZzd = this.zzk.zzd()) != null) {
                return zzkoVarZzd.zzb();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to get the current AdSize.", e);
        }
        AdSize[] adSizeArr = this.zzh;
        if (adSizeArr != null) {
            return adSizeArr[0];
        }
        return null;
    }

    public final AdSize[] zzd() {
        return this.zzh;
    }

    public final String zze() {
        zzlt zzltVar;
        if (this.zzn == null && (zzltVar = this.zzk) != null) {
            try {
                this.zzn = zzltVar.zzv();
            } catch (RemoteException e) {
                zzaky.zzc("Failed to get ad unit id.", e);
            }
        }
        return this.zzn;
    }

    public final AppEventListener zzf() {
        return this.zzi;
    }

    public final OnCustomRenderedAdLoadedListener zzg() {
        return this.zzl;
    }

    public final void zzh() {
        try {
            if (this.zzk != null) {
                this.zzk.zzh();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to call pause.", e);
        }
    }

    public final void zzi() {
        if (this.zzc.getAndSet(true)) {
            return;
        }
        try {
            if (this.zzk != null) {
                this.zzk.zzg();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to record impression.", e);
        }
    }

    public final void zzj() {
        try {
            if (this.zzk != null) {
                this.zzk.zzi();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to call resume.", e);
        }
    }

    public final String zzk() {
        try {
            if (this.zzk != null) {
                return this.zzk.zzam();
            }
            return null;
        } catch (RemoteException e) {
            zzaky.zzc("Failed to get the mediation adapter class name.", e);
            return null;
        }
    }

    public final boolean zzl() {
        try {
            if (this.zzk != null) {
                return this.zzk.zzk();
            }
            return false;
        } catch (RemoteException e) {
            zzaky.zzc("Failed to check if ad is loading.", e);
            return false;
        }
    }

    public final VideoController zzm() {
        return this.zzd;
    }

    public final zzmm zzn() {
        zzlt zzltVar = this.zzk;
        if (zzltVar == null) {
            return null;
        }
        try {
            return zzltVar.zzl();
        } catch (RemoteException e) {
            zzaky.zzc("Failed to retrieve VideoController.", e);
            return null;
        }
    }

    public final VideoOptions zzo() {
        return this.zzm;
    }
}
