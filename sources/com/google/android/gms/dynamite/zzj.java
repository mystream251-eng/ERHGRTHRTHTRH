package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzj extends com.google.android.gms.internal.common.zzb implements zzk {
    zzj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoader");
    }

    @Override // com.google.android.gms.dynamite.zzk
    public final IObjectWrapper zza(IObjectWrapper iObjectWrapper, String str, int i) throws RemoteException {
        Parcel parcelA_ = a_();
        com.google.android.gms.internal.common.zzd.zza(parcelA_, iObjectWrapper);
        parcelA_.writeString(str);
        parcelA_.writeInt(i);
        Parcel parcelZza = zza(2, parcelA_);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.dynamite.zzk
    public final int zza(IObjectWrapper iObjectWrapper, String str, boolean z) throws RemoteException {
        Parcel parcelA_ = a_();
        com.google.android.gms.internal.common.zzd.zza(parcelA_, iObjectWrapper);
        parcelA_.writeString(str);
        com.google.android.gms.internal.common.zzd.zza(parcelA_, z);
        Parcel parcelZza = zza(3, parcelA_);
        int i = parcelZza.readInt();
        parcelZza.recycle();
        return i;
    }

    @Override // com.google.android.gms.dynamite.zzk
    public final IObjectWrapper zzb(IObjectWrapper iObjectWrapper, String str, int i) throws RemoteException {
        Parcel parcelA_ = a_();
        com.google.android.gms.internal.common.zzd.zza(parcelA_, iObjectWrapper);
        parcelA_.writeString(str);
        parcelA_.writeInt(i);
        Parcel parcelZza = zza(4, parcelA_);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.dynamite.zzk
    public final int zzb(IObjectWrapper iObjectWrapper, String str, boolean z) throws RemoteException {
        Parcel parcelA_ = a_();
        com.google.android.gms.internal.common.zzd.zza(parcelA_, iObjectWrapper);
        parcelA_.writeString(str);
        com.google.android.gms.internal.common.zzd.zza(parcelA_, z);
        Parcel parcelZza = zza(5, parcelA_);
        int i = parcelZza.readInt();
        parcelZza.recycle();
        return i;
    }

    @Override // com.google.android.gms.dynamite.zzk
    public final int zzb() throws RemoteException {
        Parcel parcelZza = zza(6, a_());
        int i = parcelZza.readInt();
        parcelZza.recycle();
        return i;
    }
}
