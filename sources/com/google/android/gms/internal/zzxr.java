package com.google.android.gms.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
final class zzxr implements Runnable {
    private /* synthetic */ zzxi zza;

    zzxr(zzxi zzxiVar) {
        this.zza = zzxiVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zza.zza.zzb();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdClosed.", e);
        }
    }
}
