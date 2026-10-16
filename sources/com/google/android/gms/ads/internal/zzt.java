package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzpm;

/* JADX INFO: loaded from: classes2.dex */
final class zzt implements Runnable {
    private /* synthetic */ zzpm zza;
    private /* synthetic */ zzq zzb;

    zzt(zzq zzqVar, zzpm zzpmVar) {
        this.zzb = zzqVar;
        this.zza = zzpmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (this.zzb.zze.zzr != null) {
                this.zzb.zze.zzr.zza(this.zza);
            }
        } catch (RemoteException e) {
            zzahw.zzc("Could not call OnContentAdLoadedListener.onContentAdLoaded().", e);
        }
    }
}
