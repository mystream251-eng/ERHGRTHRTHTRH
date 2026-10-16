package com.google.android.gms.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
final class zzuj implements Runnable {
    private /* synthetic */ zzuk zza;
    private /* synthetic */ zzul zzb;

    zzuj(zztl zztlVar, zzuk zzukVar, zzul zzulVar) {
        this.zza = zzukVar;
        this.zzb = zzulVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zza.zza(this.zzb);
        } catch (RemoteException e) {
            zzahw.zzc("Could not propagate interstitial ad event.", e);
        }
    }
}
