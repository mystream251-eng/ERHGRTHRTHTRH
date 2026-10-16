package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzro;

/* JADX INFO: loaded from: classes2.dex */
final class zzu implements Runnable {
    private /* synthetic */ zzro zza;
    private /* synthetic */ zzq zzb;

    zzu(zzq zzqVar, zzro zzroVar) {
        this.zzb = zzqVar;
        this.zza = zzroVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zzb.zze.zzu.get(this.zza.zzl()).zza(this.zza);
        } catch (RemoteException e) {
            zzahw.zzc("Could not call onCustomTemplateAdLoadedListener.onCustomTemplateAdLoaded().", e);
        }
    }
}
