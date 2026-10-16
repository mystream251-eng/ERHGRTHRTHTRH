package com.google.android.gms.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
final class zztw extends zzpc {
    private /* synthetic */ zztl zza;

    zztw(zztl zztlVar) {
        this.zza = zztlVar;
    }

    @Override // com.google.android.gms.internal.zzpb
    public final void zza(zzoy zzoyVar) throws RemoteException {
        this.zza.zza.add(new zztx(this, zzoyVar));
    }
}
