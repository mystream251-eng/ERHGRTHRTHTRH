package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;

/* JADX INFO: loaded from: classes2.dex */
final class zzafn implements Runnable {
    private /* synthetic */ zzwi zza;
    private /* synthetic */ zzkk zzb;
    private /* synthetic */ zzaft zzc;
    private /* synthetic */ zzafl zzd;

    zzafn(zzafl zzaflVar, zzwi zzwiVar, zzkk zzkkVar, zzaft zzaftVar) {
        this.zzd = zzaflVar;
        this.zza = zzwiVar;
        this.zzb = zzkkVar;
        this.zzc = zzaftVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zza.zza(zzn.zza(this.zzd.zzc), this.zzb, (String) null, this.zzc, this.zzd.zzg);
        } catch (RemoteException e) {
            String strValueOf = String.valueOf(this.zzd.zza);
            zzahw.zzc(strValueOf.length() != 0 ? "Fail to initialize adapter ".concat(strValueOf) : new String("Fail to initialize adapter "), e);
            zzafl zzaflVar = this.zzd;
            zzaflVar.zza(zzaflVar.zza, 0);
        }
    }
}
