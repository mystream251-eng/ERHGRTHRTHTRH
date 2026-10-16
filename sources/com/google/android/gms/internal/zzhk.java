package com.google.android.gms.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
final class zzhk implements Runnable {
    private /* synthetic */ zzhj zza;

    zzhk(zzhj zzhjVar) {
        this.zza = zzhjVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zza.zzc) {
            if (this.zza.zzd && this.zza.zze) {
                zzhj.zza(this.zza, false);
                zzahw.zzb("App went background");
                Iterator it = this.zza.zzf.iterator();
                while (it.hasNext()) {
                    try {
                        ((zzhl) it.next()).zza(false);
                    } catch (Exception e) {
                        zzahw.zzb("OnForegroundStateChangedListener threw exception.", e);
                    }
                }
            } else {
                zzahw.zzb("App is still foreground");
            }
        }
    }
}
