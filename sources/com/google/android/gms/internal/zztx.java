package com.google.android.gms.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
final class zztx implements zzuk {
    private /* synthetic */ zzoy zza;

    zztx(zztw zztwVar, zzoy zzoyVar) {
        this.zza = zzoyVar;
    }

    @Override // com.google.android.gms.internal.zzuk
    public final void zza(zzul zzulVar) throws RemoteException {
        if (zzulVar.zzc != null) {
            zzulVar.zzc.zza(this.zza);
        }
    }
}
