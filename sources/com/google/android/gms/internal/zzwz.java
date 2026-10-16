package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class zzwz extends zzev implements zzwx {
    zzwz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.IUnifiedNativeAdMapper");
    }

    @Override // com.google.android.gms.internal.zzwx
    public final String zza() throws RemoteException {
        Parcel parcelZza = zza(2, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final void zza(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(20, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzwx
    public final void zza(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzex.zza(parcelA_, iObjectWrapper2);
        zzex.zza(parcelA_, iObjectWrapper3);
        zzb(21, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzwx
    public final List zzb() throws RemoteException {
        Parcel parcelZza = zza(3, a_());
        ArrayList arrayListZzb = zzex.zzb(parcelZza);
        parcelZza.recycle();
        return arrayListZzb;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final void zzb(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(22, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzwx
    public final String zzc() throws RemoteException {
        Parcel parcelZza = zza(4, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final zzqs zzd() throws RemoteException {
        Parcel parcelZza = zza(5, a_());
        zzqs zzqsVarZza = zzqt.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return zzqsVarZza;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final String zze() throws RemoteException {
        Parcel parcelZza = zza(6, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final String zzf() throws RemoteException {
        Parcel parcelZza = zza(7, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final double zzg() throws RemoteException {
        Parcel parcelZza = zza(8, a_());
        double d = parcelZza.readDouble();
        parcelZza.recycle();
        return d;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final String zzh() throws RemoteException {
        Parcel parcelZza = zza(9, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final String zzi() throws RemoteException {
        Parcel parcelZza = zza(10, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final zzmm zzj() throws RemoteException {
        Parcel parcelZza = zza(11, a_());
        zzmm zzmmVarZza = zzmn.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return zzmmVarZza;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final zzqo zzk() throws RemoteException {
        Parcel parcelZza = zza(12, a_());
        zzqo zzqoVarZza = zzqp.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return zzqoVarZza;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final IObjectWrapper zzl() throws RemoteException {
        Parcel parcelZza = zza(13, a_());
        IObjectWrapper iObjectWrapperZza = IObjectWrapper.zza.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperZza;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final IObjectWrapper zzm() throws RemoteException {
        Parcel parcelZza = zza(14, a_());
        IObjectWrapper iObjectWrapperZza = IObjectWrapper.zza.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperZza;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final IObjectWrapper zzn() throws RemoteException {
        Parcel parcelZza = zza(15, a_());
        IObjectWrapper iObjectWrapperZza = IObjectWrapper.zza.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperZza;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final Bundle zzo() throws RemoteException {
        Parcel parcelZza = zza(16, a_());
        Bundle bundle = (Bundle) zzex.zza(parcelZza, Bundle.CREATOR);
        parcelZza.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final boolean zzp() throws RemoteException {
        Parcel parcelZza = zza(17, a_());
        boolean zZza = zzex.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final boolean zzq() throws RemoteException {
        Parcel parcelZza = zza(18, a_());
        boolean zZza = zzex.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final void zzr() throws RemoteException {
        zzb(19, a_());
    }
}
