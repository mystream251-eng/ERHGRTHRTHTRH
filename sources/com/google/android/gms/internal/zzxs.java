package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.AdRequest;

/* JADX INFO: loaded from: classes2.dex */
final class zzxs implements Runnable {
    private /* synthetic */ AdRequest.ErrorCode zza;
    private /* synthetic */ zzxi zzb;

    zzxs(zzxi zzxiVar, AdRequest.ErrorCode errorCode) {
        this.zzb = zzxiVar;
        this.zza = errorCode;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zzb.zza.zza(zzxu.zza(this.zza));
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdFailedToLoad.", e);
        }
    }
}
