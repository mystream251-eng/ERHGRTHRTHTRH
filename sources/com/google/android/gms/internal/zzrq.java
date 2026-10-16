package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class zzrq extends zzev implements zzro {
    zzrq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
    }

    @Override // com.google.android.gms.internal.zzro
    public final String zza(String str) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        Parcel parcelZza = zza(1, parcelA_);
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzro
    public final List<String> zza() throws RemoteException {
        Parcel parcelZza = zza(3, a_());
        ArrayList<String> arrayListCreateStringArrayList = parcelZza.createStringArrayList();
        parcelZza.recycle();
        return arrayListCreateStringArrayList;
    }

    @Override // com.google.android.gms.internal.zzro
    public final boolean zza(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        Parcel parcelZza = zza(10, parcelA_);
        boolean zZza = zzex.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.zzro
    public final IObjectWrapper zzb() throws RemoteException {
        Parcel parcelZza = zza(11, a_());
        IObjectWrapper iObjectWrapperZza = IObjectWrapper.zza.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperZza;
    }

    @Override // com.google.android.gms.internal.zzro
    public final zzqs zzb(String str) throws RemoteException {
        zzqs zzquVar;
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        Parcel parcelZza = zza(2, parcelA_);
        IBinder strongBinder = parcelZza.readStrongBinder();
        if (strongBinder == null) {
            zzquVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
            zzquVar = iInterfaceQueryLocalInterface instanceof zzqs ? (zzqs) iInterfaceQueryLocalInterface : new zzqu(strongBinder);
        }
        parcelZza.recycle();
        return zzquVar;
    }

    @Override // com.google.android.gms.internal.zzro
    public final zzmm zzc() throws RemoteException {
        Parcel parcelZza = zza(7, a_());
        zzmm zzmmVarZza = zzmn.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return zzmmVarZza;
    }

    @Override // com.google.android.gms.internal.zzro
    public final void zzc(String str) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        zzb(5, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzro
    public final void zzd() throws RemoteException {
        zzb(6, a_());
    }

    @Override // com.google.android.gms.internal.zzro
    public final IObjectWrapper zze() throws RemoteException {
        Parcel parcelZza = zza(9, a_());
        IObjectWrapper iObjectWrapperZza = IObjectWrapper.zza.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperZza;
    }

    @Override // com.google.android.gms.internal.zzro
    public final void zzf() throws RemoteException {
        zzb(8, a_());
    }

    @Override // com.google.android.gms.internal.zzro, com.google.android.gms.internal.zzpx
    public final String zzl() throws RemoteException {
        Parcel parcelZza = zza(4, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }
}
