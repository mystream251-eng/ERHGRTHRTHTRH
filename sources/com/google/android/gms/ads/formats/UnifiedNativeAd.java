package com.google.android.gms.ads.formats;

import android.os.Bundle;
import com.google.android.gms.ads.VideoController;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class UnifiedNativeAd {

    public interface zza {
        void zza(UnifiedNativeAd unifiedNativeAd);
    }

    public abstract void performClick(Bundle bundle);

    public abstract boolean recordImpression(Bundle bundle);

    public abstract void reportTouchEvent(Bundle bundle);

    public abstract String zza();

    public abstract List<NativeAd.Image> zzb();

    public abstract String zzc();

    public abstract NativeAd.Image zzd();

    public abstract String zze();

    public abstract String zzf();

    public abstract Double zzg();

    public abstract String zzh();

    public abstract String zzi();

    public abstract VideoController zzj();

    public abstract Object zzk();
}
