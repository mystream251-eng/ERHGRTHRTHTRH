package com.google.firebase.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzw implements zzx {
    private final IBinder zza;

    zzw(IBinder iBinder) {
        this.zza = iBinder;
    }

    @Override // com.google.firebase.iid.zzx
    public final void zza(Message message) throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInterfaceToken("com.google.android.gms.iid.IMessengerCompat");
        parcelObtain.writeInt(1);
        message.writeToParcel(parcelObtain, 0);
        try {
            this.zza.transact(1, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.zza;
    }
}
