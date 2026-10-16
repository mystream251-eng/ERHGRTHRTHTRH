package com.google.android.gms.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
final class zztu extends zzlz {
    private /* synthetic */ zztl zza;

    zztu(zztl zztlVar) {
        this.zza = zztlVar;
    }

    @Override // com.google.android.gms.internal.zzly
    public final void zza(String str, String str2) throws RemoteException {
        this.zza.zza.add(new zztv(this, str, str2));
    }
}
