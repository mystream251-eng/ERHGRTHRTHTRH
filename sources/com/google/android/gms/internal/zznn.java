package com.google.android.gms.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
final class zznn implements Runnable {
    private /* synthetic */ zznm zza;

    zznn(zznm zznmVar) {
        this.zza = zznmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.zza != null) {
            try {
                this.zza.zza.zza(1);
            } catch (RemoteException e) {
                zzaky.zzc("Could not notify onRewardedVideoAdFailedToLoad event.", e);
            }
        }
    }
}
