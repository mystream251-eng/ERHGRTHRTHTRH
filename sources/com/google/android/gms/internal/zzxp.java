package com.google.android.gms.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
final class zzxp implements Runnable {
    private /* synthetic */ zzxi zza;

    zzxp(zzxi zzxiVar) {
        this.zza = zzxiVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zza.zza.zzd();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdOpened.", e);
        }
    }
}
