package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzkk;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
final class zzbk implements Runnable {
    private /* synthetic */ WeakReference zza;
    private /* synthetic */ zzbj zzb;

    zzbk(zzbj zzbjVar, WeakReference weakReference) {
        this.zzb = zzbjVar;
        this.zza = weakReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzbj.zza(this.zzb, false);
        zza zzaVar = (zza) this.zza.get();
        if (zzaVar != null) {
            zzkk zzkkVar = this.zzb.zzc;
            if (zzaVar.zzc(zzkkVar)) {
                zzaVar.zzb(zzkkVar);
            } else {
                zzahw.zzd("Ad is not visible. Not refreshing ad.");
                zzaVar.zzd.zzb(zzkkVar);
            }
        }
    }
}
