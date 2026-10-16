package com.google.android.gms.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
final class zzwd implements Runnable {
    private /* synthetic */ zzvw zza;

    zzwd(zzwc zzwcVar, zzvw zzvwVar) {
        this.zza = zzvwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zza.zzc.zzc();
        } catch (RemoteException e) {
            zzahw.zzc("Could not destroy mediation adapter.", e);
        }
    }
}
