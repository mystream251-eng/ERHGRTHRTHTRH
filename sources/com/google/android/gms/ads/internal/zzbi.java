package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzro;

/* JADX INFO: loaded from: classes2.dex */
final class zzbi implements Runnable {
    private /* synthetic */ zzro zza;
    private /* synthetic */ zzbb zzb;

    zzbi(zzbb zzbbVar, zzro zzroVar) {
        this.zzb = zzbbVar;
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
