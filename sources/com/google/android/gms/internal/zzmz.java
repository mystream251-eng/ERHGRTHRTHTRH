package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.zzn;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzmz {
    private static zzmz zza;
    private static final Object zzb = new Object();
    private zzmh zzc;
    private RewardedVideoAd zzd;

    private zzmz() {
    }

    public static zzmz zza() {
        zzmz zzmzVar;
        synchronized (zzb) {
            if (zza == null) {
                zza = new zzmz();
            }
            zzmzVar = zza;
        }
        return zzmzVar;
    }

    public final RewardedVideoAd zza(Context context) {
        synchronized (zzb) {
            if (this.zzd != null) {
                return this.zzd;
            }
            zzafk zzafkVar = new zzafk(context, (zzaex) zzks.zza(context, false, (zzks.zza) new zzla(zzlc.zzb(), context, new zzwe())));
            this.zzd = zzafkVar;
            return zzafkVar;
        }
    }

    public final void zza(float f) {
        zzbq.zzb(0.0f <= f && f <= 1.0f, "The app volume must be a value between 0 and 1 inclusive.");
        zzbq.zza(this.zzc != null, "MobileAds.initialize() must be called prior to setting the app volume.");
        try {
            this.zzc.zza(f);
        } catch (RemoteException e) {
            zzaky.zzb("Unable to set app volume.", e);
        }
    }

    public final void zza(Context context, String str) {
        zzbq.zza(this.zzc != null, "MobileAds.initialize() must be called prior to opening debug menu.");
        try {
            this.zzc.zza(zzn.zza(context), str);
        } catch (RemoteException e) {
            zzaky.zzb("Unable to open debug menu.", e);
        }
    }

    public final void zza(Context context, String str, zznb zznbVar) {
        synchronized (zzb) {
            if (this.zzc != null) {
                return;
            }
            if (context == null) {
                throw new IllegalArgumentException("Context cannot be null.");
            }
            try {
                zzmh zzmhVar = (zzmh) zzks.zza(context, false, (zzks.zza) new zzkx(zzlc.zzb(), context));
                this.zzc = zzmhVar;
                zzmhVar.zza();
                if (str != null) {
                    this.zzc.zza(str, zzn.zza(new zzna(this, context)));
                }
            } catch (RemoteException e) {
                zzaky.zzc("MobileAdsSettingManager initialization failed", e);
            }
        }
    }

    public final void zza(boolean z) {
        zzbq.zza(this.zzc != null, "MobileAds.initialize() must be called prior to setting app muted state.");
        try {
            this.zzc.zza(z);
        } catch (RemoteException e) {
            zzaky.zzb("Unable to set app mute state.", e);
        }
    }

    public final float zzb() {
        zzmh zzmhVar = this.zzc;
        if (zzmhVar == null) {
            return 1.0f;
        }
        try {
            return zzmhVar.zzb();
        } catch (RemoteException e) {
            zzaky.zzb("Unable to get app volume.", e);
            return 1.0f;
        }
    }

    public final boolean zzc() {
        zzmh zzmhVar = this.zzc;
        if (zzmhVar == null) {
            return false;
        }
        try {
            return zzmhVar.zzc();
        } catch (RemoteException e) {
            zzaky.zzb("Unable to get app mute state.", e);
            return false;
        }
    }
}
