package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzrn extends NativeContentAd {
    private final zzrk zza;
    private final zzqv zzc;
    private final NativeAd.AdChoicesInfo zze;
    private final List<NativeAd.Image> zzb = new ArrayList();
    private final VideoController zzd = new VideoController();

    public zzrn(zzrk zzrkVar) {
        zzqv zzqvVar;
        zzqs zzquVar;
        IBinder iBinder;
        this.zza = zzrkVar;
        zzqr zzqrVar = null;
        try {
            List listZzb = zzrkVar.zzb();
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
            zzqs zzqsVarZzf = this.zza.zzf();
            zzqvVar = zzqsVarZzf != null ? new zzqv(zzqsVarZzf) : null;
        } catch (RemoteException e2) {
            zzaky.zzb("Failed to get image.", e2);
        }
        this.zzc = zzqvVar;
        try {
            if (this.zza.zzp() != null) {
                zzqrVar = new zzqr(this.zza.zzp());
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

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final void destroy() {
        try {
            this.zza.zzq();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to destroy", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final NativeAd.AdChoicesInfo getAdChoicesInfo() {
        return this.zze;
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final CharSequence getAdvertiser() {
        try {
            return this.zza.zzh();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get attribution.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final CharSequence getBody() {
        try {
            return this.zza.zze();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get body.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final CharSequence getCallToAction() {
        try {
            return this.zza.zzg();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get call to action.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final Bundle getExtras() {
        try {
            return this.zza.zzn();
        } catch (RemoteException e) {
            zzaky.zzc("Failed to get extras", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final CharSequence getHeadline() {
        try {
            return this.zza.zza();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get headline.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final List<NativeAd.Image> getImages() {
        return this.zzb;
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final NativeAd.Image getLogo() {
        return this.zzc;
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final CharSequence getMediationAdapterClassName() {
        try {
            return this.zza.zzd();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get mediation adapter class name.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
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
