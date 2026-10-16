package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class zzww extends zzev implements zzwu {
    zzww(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
    }

    @Override // com.google.android.gms.internal.zzwu
    public final String zza() throws RemoteException {
        Parcel parcelZza = zza(2, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzwu
    public final void zza(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(9, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzwu
    public final void zza(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzex.zza(parcelA_, iObjectWrapper2);
        zzex.zza(parcelA_, iObjectWrapper3);
        zzb(22, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzwu
    public final List zzb() throws RemoteException {
        Parcel parcelZza = zza(3, a_());
        ArrayList arrayListZzb = zzex.zzb(parcelZza);
        parcelZza.recycle();
        return arrayListZzb;
    }

    @Override // com.google.android.gms.internal.zzwu
    public final void zzb(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(10, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzwu
    public final String zzc() throws RemoteException {
        Parcel parcelZza = zza(4, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzwu
    public final void zzc(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(14, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzwu
    public final zzqs zzd() throws RemoteException {
        Parcel parcelZza = zza(5, a_());
        zzqs zzqsVarZza = zzqt.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return zzqsVarZza;
    }

    @Override // com.google.android.gms.internal.zzwu
    public final String zze() throws RemoteException {
        Parcel parcelZza = zza(6, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzwu
    public final String zzf() throws RemoteException {
        Parcel parcelZza = zza(7, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzwu
    public final void zzg() throws RemoteException {
        zzb(8, a_());
    }

    @Override // com.google.android.gms.internal.zzwu
    public final boolean zzh() throws RemoteException {
        Parcel parcelZza = zza(11, a_());
        boolean zZza = zzex.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.zzwu
    public final boolean zzi() throws RemoteException {
        Parcel parcelZza = zza(12, a_());
        boolean zZza = zzex.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.zzwu
    public final Bundle zzj() throws RemoteException {
        Parcel parcelZza = zza(13, a_());
        Bundle bundle = (Bundle) zzex.zza(parcelZza, Bundle.CREATOR);
        parcelZza.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.zzwu
    public final IObjectWrapper zzk() throws RemoteException {
        Parcel parcelZza = zza(15, a_());
        IObjectWrapper iObjectWrapperZza = IObjectWrapper.zza.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperZza;
    }

    @Override // com.google.android.gms.internal.zzwu
    public final zzmm zzl() throws RemoteException {
        Parcel parcelZza = zza(16, a_());
        zzmm zzmmVarZza = zzmn.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return zzmmVarZza;
    }

    @Override // com.google.android.gms.internal.zzwu
    public final zzqo zzm() throws RemoteException {
        Parcel parcelZza = zza(19, a_());
        zzqo zzqoVarZza = zzqp.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return zzqoVarZza;
    }

    @Override // com.google.android.gms.internal.zzwu
    public final IObjectWrapper zzn() throws RemoteException {
        Parcel parcelZza = zza(20, a_());
        IObjectWrapper iObjectWrapperZza = IObjectWrapper.zza.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperZza;
    }

    @Override // com.google.android.gms.internal.zzwu
    public final IObjectWrapper zzo() throws RemoteException {
        Parcel parcelZza = zza(21, a_());
        IObjectWrapper iObjectWrapperZza = IObjectWrapper.zza.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperZza;
    }
}
