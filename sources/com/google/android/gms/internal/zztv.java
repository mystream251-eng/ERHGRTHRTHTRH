package com.google.android.gms.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
final class zztv implements zzuk {
    private /* synthetic */ String zza;
    private /* synthetic */ String zzb;

    zztv(zztu zztuVar, String str, String str2) {
        this.zza = str;
        this.zzb = str2;
    }

    @Override // com.google.android.gms.internal.zzuk
    public final void zza(zzul zzulVar) throws RemoteException {
        if (zzulVar.zzb != null) {
            zzulVar.zzb.zza(this.zza, this.zzb);
        }
    }
}
