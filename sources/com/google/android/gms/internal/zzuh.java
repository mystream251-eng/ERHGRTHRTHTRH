package com.google.android.gms.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
final class zzuh implements zzuk {
    private /* synthetic */ int zza;

    zzuh(zzua zzuaVar, int i) {
        this.zza = i;
    }

    @Override // com.google.android.gms.internal.zzuk
    public final void zza(zzul zzulVar) throws RemoteException {
        if (zzulVar.zze != null) {
            zzulVar.zze.zza(this.zza);
        }
    }
}
