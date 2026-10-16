package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzm extends com.google.android.gms.internal.common.zzb implements zzl {
    zzm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.ICertData");
    }

    @Override // com.google.android.gms.common.internal.zzl
    public final IObjectWrapper zzb() throws RemoteException {
        Parcel parcelZza = zza(1, a_());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.common.internal.zzl
    public final int zzc() throws RemoteException {
        Parcel parcelZza = zza(2, a_());
        int i = parcelZza.readInt();
        parcelZza.recycle();
        return i;
    }
}
