package com.google.android.gms.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
final class zzxq implements Runnable {
    private /* synthetic */ zzxi zza;

    zzxq(zzxi zzxiVar) {
        this.zza = zzxiVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zza.zza.zze();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdLoaded.", e);
        }
    }
}
