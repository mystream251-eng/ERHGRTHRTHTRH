package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
final class zzadp implements Runnable {
    private /* synthetic */ zzacf zza;
    private /* synthetic */ zzacq zzb;
    private /* synthetic */ zzadn zzc;

    zzadp(zzadn zzadnVar, zzacf zzacfVar, zzacq zzacqVar) {
        this.zzc = zzadnVar;
        this.zza = zzacfVar;
        this.zzb = zzacqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzacj zzacjVar;
        try {
            zzacjVar = this.zzc.zza(this.zza);
        } catch (Exception e) {
            zzbt.zzi().zza(e, "AdRequestServiceImpl.loadAdAsync");
            zzahw.zzc("Could not fetch ad response due to an Exception.", e);
            zzacjVar = null;
        }
        if (zzacjVar == null) {
            zzacjVar = new zzacj(0);
        }
        try {
            this.zzb.zza(zzacjVar);
        } catch (RemoteException e2) {
            zzahw.zzc("Fail to forward ad response.", e2);
        }
    }
}
