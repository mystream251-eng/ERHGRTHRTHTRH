package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzpo;

/* JADX INFO: loaded from: classes2.dex */
final class zzbh implements Runnable {
    private /* synthetic */ String zza;
    private /* synthetic */ zzahd zzb;
    private /* synthetic */ zzbb zzc;

    zzbh(zzbb zzbbVar, String str, zzahd zzahdVar) {
        this.zzc = zzbbVar;
        this.zza = str;
        this.zzb = zzahdVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zzc.zze.zzu.get(this.zza).zza((zzpo) this.zzb.zzab);
        } catch (RemoteException e) {
            zzahw.zzc("Could not call onCustomTemplateAdLoadedListener.onCustomTemplateAdLoaded().", e);
        }
    }
}
