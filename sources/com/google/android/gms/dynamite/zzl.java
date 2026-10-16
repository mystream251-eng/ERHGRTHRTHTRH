package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzl extends com.google.android.gms.internal.common.zzb implements zzm {
    zzl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
    }

    @Override // com.google.android.gms.dynamite.zzm
    public final IObjectWrapper zza(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2) throws RemoteException {
        Parcel parcelA_ = a_();
        com.google.android.gms.internal.common.zzd.zza(parcelA_, iObjectWrapper);
        parcelA_.writeString(str);
        parcelA_.writeInt(i);
        com.google.android.gms.internal.common.zzd.zza(parcelA_, iObjectWrapper2);
        Parcel parcelZza = zza(2, parcelA_);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.dynamite.zzm
    public final IObjectWrapper zzb(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2) throws RemoteException {
        Parcel parcelA_ = a_();
        com.google.android.gms.internal.common.zzd.zza(parcelA_, iObjectWrapper);
        parcelA_.writeString(str);
        parcelA_.writeInt(i);
        com.google.android.gms.internal.common.zzd.zza(parcelA_, iObjectWrapper2);
        Parcel parcelZza = zza(3, parcelA_);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperAsInterface;
    }
}
