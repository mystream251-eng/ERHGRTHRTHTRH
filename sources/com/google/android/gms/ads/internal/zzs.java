package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzpk;

/* JADX INFO: loaded from: classes2.dex */
final class zzs implements Runnable {
    private /* synthetic */ zzpk zza;
    private /* synthetic */ zzq zzb;

    zzs(zzq zzqVar, zzpk zzpkVar) {
        this.zzb = zzqVar;
        this.zza = zzpkVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (this.zzb.zze.zzq != null) {
                this.zzb.zze.zzq.zza(this.zza);
            }
        } catch (RemoteException e) {
            zzahw.zzc("Could not call OnAppInstallAdLoadedListener.onAppInstallAdLoaded().", e);
        }
    }
}
