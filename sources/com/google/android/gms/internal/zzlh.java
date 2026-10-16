package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzlh extends zzev implements zzlf {
    zzlh(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdClickListener");
    }

    @Override // com.google.android.gms.internal.zzlf
    public final void zza() throws RemoteException {
        zzb(1, a_());
    }
}
