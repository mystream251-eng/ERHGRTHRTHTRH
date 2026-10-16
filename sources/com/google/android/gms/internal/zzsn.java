package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzsn extends UnifiedNativeAd {
    private final zzsk zza;
    private final zzqv zzc;
    private final NativeAd.AdChoicesInfo zze;
    private final List<NativeAd.Image> zzb = new ArrayList();
    private final VideoController zzd = new VideoController();

    public zzsn(zzsk zzskVar) {
        zzqv zzqvVar;
        zzqs zzquVar;
        IBinder iBinder;
        this.zza = zzskVar;
        zzqr zzqrVar = null;
        try {
            List listZzb = zzskVar.zzb();
            if (listZzb != null) {
                for (Object obj : listZzb) {
                    if (!(obj instanceof IBinder) || (iBinder = (IBinder) obj) == null) {
                        zzquVar = null;
                    } else {
                        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                        zzquVar = iInterfaceQueryLocalInterface instanceof zzqs ? (zzqs) iInterfaceQueryLocalInterface : new zzqu(iBinder);
                    }
                    if (zzquVar != null) {
                        this.zzb.add(new zzqv(zzquVar));
                    }
                }
            }
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get image.", e);
        }
        try {
            zzqs zzqsVarZzd = this.zza.zzd();
            zzqvVar = zzqsVarZzd != null ? new zzqv(zzqsVarZzd) : null;
        } catch (RemoteException e2) {
            zzaky.zzb("Failed to get image.", e2);
        }
        this.zzc = zzqvVar;
        try {
            if (this.zza.zzr() != null) {
                zzqrVar = new zzqr(this.zza.zzr());
            }
        } catch (RemoteException e3) {
            zzaky.zzb("Failed to get attribution info.", e3);
        }
        this.zze = zzqrVar;
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void performClick(Bundle bundle) {
        try {
            this.zza.zza(bundle);
        } catch (RemoteException e) {
            zzaky.zzb("Failed to perform click.", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final boolean recordImpression(Bundle bundle) {
        try {
            return this.zza.zzb(bundle);
        } catch (RemoteException e) {
            zzaky.zzb("Failed to record impression.", e);
            return false;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void reportTouchEvent(Bundle bundle) {
        try {
            this.zza.zzc(bundle);
        } catch (RemoteException e) {
            zzaky.zzb("Failed to report touch event.", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String zza() {
        try {
            return this.zza.zza();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get headline.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final List<NativeAd.Image> zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String zzc() {
        try {
            return this.zza.zzc();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get body.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final NativeAd.Image zzd() {
        return this.zzc;
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String zze() {
        try {
            return this.zza.zze();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get call to action.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String zzf() {
        try {
            return this.zza.zzf();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get attribution.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final Double zzg() {
        try {
            double dZzg = this.zza.zzg();
            if (dZzg == -1.0d) {
                return null;
            }
            return Double.valueOf(dZzg);
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get star rating.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String zzh() {
        try {
            return this.zza.zzh();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get store", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String zzi() {
        try {
            return this.zza.zzi();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get price.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final VideoController zzj() {
        try {
            if (this.zza.zzj() != null) {
                this.zzd.zza(this.zza.zzj());
            }
        } catch (RemoteException e) {
            zzaky.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzd;
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final Object zzk() {
        try {
            IObjectWrapper iObjectWrapperZzp = this.zza.zzp();
            if (iObjectWrapperZzp != null) {
                return zzn.zza(iObjectWrapperZzp);
            }
            return null;
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get mediated ad.", e);
            return null;
        }
    }
}
