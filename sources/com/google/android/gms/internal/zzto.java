package com.google.android.gms.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
final class zzto implements zzuk {
    private /* synthetic */ int zza;

    zzto(zztm zztmVar, int i) {
        this.zza = i;
    }

    @Override // com.google.android.gms.internal.zzuk
    public final void zza(zzul zzulVar) throws RemoteException {
        if (zzulVar.zza != null) {
            zzulVar.zza.zza(this.zza);
        }
    }
}
