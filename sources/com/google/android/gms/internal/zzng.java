package com.google.android.gms.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
final class zzng implements Runnable {
    private /* synthetic */ zznf zza;

    zzng(zznf zznfVar) {
        this.zza = zznfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.zza.zza != null) {
            try {
                this.zza.zza.zza.zza(1);
            } catch (RemoteException e) {
                zzaky.zzc("Could not notify onAdFailedToLoad event.", e);
            }
        }
    }
}
