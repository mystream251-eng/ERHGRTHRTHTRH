package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzpm;

/* JADX INFO: loaded from: classes2.dex */
final class zzbg implements Runnable {
    private /* synthetic */ zzpm zza;
    private /* synthetic */ zzbb zzb;

    zzbg(zzbb zzbbVar, zzpm zzpmVar) {
        this.zzb = zzbbVar;
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
