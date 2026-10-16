package com.google.android.gms.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
final class zzuf implements zzuk {
    private /* synthetic */ zzaeu zza;

    zzuf(zzua zzuaVar, zzaeu zzaeuVar) {
        this.zza = zzaeuVar;
    }

    @Override // com.google.android.gms.internal.zzuk
    public final void zza(zzul zzulVar) throws RemoteException {
        if (zzulVar.zze != null) {
            zzulVar.zze.zza(this.zza);
        }
    }
}
