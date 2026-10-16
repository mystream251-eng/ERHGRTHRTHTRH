package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzrj extends NativeAppInstallAd {
    private final zzrg zza;
    private final zzqv zzc;
    private final NativeAd.AdChoicesInfo zze;
    private final List<NativeAd.Image> zzb = new ArrayList();
    private final VideoController zzd = new VideoController();

    public zzrj(zzrg zzrgVar) {
        zzqv zzqvVar;
        zzqs zzquVar;
        IBinder iBinder;
        this.zza = zzrgVar;
        zzqr zzqrVar = null;
        try {
            List listZzb = zzrgVar.zzb();
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

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.ads.formats.NativeAd
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final IObjectWrapper zza() {
        try {
            return this.zza.zzj();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to retrieve native ad engine.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final void destroy() {
        try {
            this.zza.zzs();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to destroy", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final NativeAd.AdChoicesInfo getAdChoicesInfo() {
        return this.zze;
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final CharSequence getBody() {
        try {
            return this.zza.zzc();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get body.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final CharSequence getCallToAction() {
        try {
            return this.zza.zze();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get call to action.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final Bundle getExtras() {
        try {
            return this.zza.zzn();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get extras", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final CharSequence getHeadline() {
        try {
            return this.zza.zza();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get headline.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final NativeAd.Image getIcon() {
        return this.zzc;
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final List<NativeAd.Image> getImages() {
        return this.zzb;
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final CharSequence getMediationAdapterClassName() {
        try {
            return this.zza.zzq();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get mediation adapter class name.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final CharSequence getPrice() {
        try {
            return this.zza.zzh();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get price.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final Double getStarRating() {
        try {
            double dZzf = this.zza.zzf();
            if (dZzf == -1.0d) {
                return null;
            }
            return Double.valueOf(dZzf);
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get star rating.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final CharSequence getStore() {
        try {
            return this.zza.zzg();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get store", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final VideoController getVideoController() {
        try {
            if (this.zza.zzi() != null) {
                this.zzd.zza(this.zza.zzi());
            }
        } catch (RemoteException e) {
            zzaky.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzd;
    }

    @Override // com.google.android.gms.ads.formats.NativeAd
    public final void performClick(Bundle bundle) {
        try {
            this.zza.zza(bundle);
        } catch (RemoteException e) {
            zzaky.zzb("Failed to perform click.", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAd
    public final boolean recordImpression(Bundle bundle) {
        try {
            return this.zza.zzb(bundle);
        } catch (RemoteException e) {
            zzaky.zzb("Failed to record impression.", e);
            return false;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAd
    public final void reportTouchEvent(Bundle bundle) {
        try {
            this.zza.zzc(bundle);
        } catch (RemoteException e) {
            zzaky.zzb("Failed to report touch event.", e);
        }
    }
}
