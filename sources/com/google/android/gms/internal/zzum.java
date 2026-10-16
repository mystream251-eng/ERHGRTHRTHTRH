package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzbt;
import java.util.Random;

/* JADX INFO: loaded from: classes2.dex */
final class zzum extends zzlj {
    private final zzli zza;

    zzum(zzli zzliVar) {
        this.zza = zzliVar;
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zza() throws RemoteException {
        if (zzuv.zza()) {
            int iIntValue = ((Integer) zzlc.zzf().zza(zzoi.zzct)).intValue();
            int iIntValue2 = ((Integer) zzlc.zzf().zza(zzoi.zzcu)).intValue();
            if (iIntValue <= 0 || iIntValue2 < 0) {
                zzbt.zzr().zza();
            } else {
                zzaij.zza.postDelayed(zzun.zza, iIntValue + new Random().nextInt(iIntValue2 + 1));
            }
        }
        this.zza.zza();
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zza(int i) throws RemoteException {
        this.zza.zza(i);
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zzb() throws RemoteException {
        this.zza.zzb();
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zzc() throws RemoteException {
        this.zza.zzc();
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zzd() throws RemoteException {
        this.zza.zzd();
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zze() throws RemoteException {
        this.zza.zze();
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zzf() throws RemoteException {
        this.zza.zzf();
    }
}
