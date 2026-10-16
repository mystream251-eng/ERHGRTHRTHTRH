package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.dynamic.zzn;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzrr implements NativeCustomTemplateAd {
    private static WeakHashMap<IBinder, zzrr> zza = new WeakHashMap<>();
    private final zzro zzb;
    private final MediaView zzc;
    private final VideoController zzd = new VideoController();

    private zzrr(zzro zzroVar) {
        Context context;
        this.zzb = zzroVar;
        MediaView mediaView = null;
        try {
            context = (Context) zzn.zza(zzroVar.zze());
        } catch (RemoteException | NullPointerException e) {
            zzaky.zzb("Unable to inflate MediaView.", e);
            context = null;
        }
        if (context != null) {
            MediaView mediaView2 = new MediaView(context);
            try {
                if (this.zzb.zza(zzn.zza(mediaView2))) {
                    mediaView = mediaView2;
                }
            } catch (RemoteException e2) {
                zzaky.zzb("Unable to render video in MediaView.", e2);
            }
        }
        this.zzc = mediaView;
    }

    public static zzrr zza(zzro zzroVar) {
        synchronized (zza) {
            zzrr zzrrVar = zza.get(zzroVar.asBinder());
            if (zzrrVar != null) {
                return zzrrVar;
            }
            zzrr zzrrVar2 = new zzrr(zzroVar);
            zza.put(zzroVar.asBinder(), zzrrVar2);
            return zzrrVar2;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final void destroy() {
        try {
            this.zzb.zzf();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to destroy ad.", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final List<String> getAvailableAssetNames() {
        try {
            return this.zzb.zza();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get available asset names.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final String getCustomTemplateId() {
        try {
            return this.zzb.zzl();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get custom template id.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final NativeAd.Image getImage(String str) {
        try {
            zzqs zzqsVarZzb = this.zzb.zzb(str);
            if (zzqsVarZzb != null) {
                return new zzqv(zzqsVarZzb);
            }
            return null;
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get image.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final CharSequence getText(String str) {
        try {
            return this.zzb.zza(str);
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get string.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final VideoController getVideoController() {
        try {
            zzmm zzmmVarZzc = this.zzb.zzc();
            if (zzmmVarZzc != null) {
                this.zzd.zza(zzmmVarZzc);
            }
        } catch (RemoteException e) {
            zzaky.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzd;
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final MediaView getVideoMediaView() {
        return this.zzc;
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final void performClick(String str) {
        try {
            this.zzb.zzc(str);
        } catch (RemoteException e) {
            zzaky.zzb("Failed to perform click.", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final void recordImpression() {
        try {
            this.zzb.zzd();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to record impression.", e);
        }
    }

    public final zzro zza() {
        return this.zzb;
    }
}
