package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class zzri extends zzev implements zzrg {
    zzri(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
    }

    @Override // com.google.android.gms.internal.zzrg
    public final String zza() throws RemoteException {
        Parcel parcelZza = zza(3, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final void zza(Bundle bundle) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, bundle);
        zzb(14, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzrg, com.google.android.gms.internal.zzpy
    public final List zzb() throws RemoteException {
        Parcel parcelZza = zza(4, a_());
        ArrayList arrayListZzb = zzex.zzb(parcelZza);
        parcelZza.recycle();
        return arrayListZzb;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final boolean zzb(Bundle bundle) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, bundle);
        Parcel parcelZza = zza(15, parcelA_);
        boolean zZza = zzex.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final String zzc() throws RemoteException {
        Parcel parcelZza = zza(5, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final void zzc(Bundle bundle) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, bundle);
        zzb(16, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzrg
    public final zzqs zzd() throws RemoteException {
        zzqs zzquVar;
        Parcel parcelZza = zza(6, a_());
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

    @Override // com.google.android.gms.internal.zzrg
    public final String zze() throws RemoteException {
        Parcel parcelZza = zza(7, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final double zzf() throws RemoteException {
        Parcel parcelZza = zza(8, a_());
        double d = parcelZza.readDouble();
        parcelZza.recycle();
        return d;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final String zzg() throws RemoteException {
        Parcel parcelZza = zza(9, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final String zzh() throws RemoteException {
        Parcel parcelZza = zza(10, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final zzmm zzi() throws RemoteException {
        Parcel parcelZza = zza(13, a_());
        zzmm zzmmVarZza = zzmn.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return zzmmVarZza;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final IObjectWrapper zzj() throws RemoteException {
        Parcel parcelZza = zza(2, a_());
        IObjectWrapper iObjectWrapperZza = IObjectWrapper.zza.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperZza;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final Bundle zzn() throws RemoteException {
        Parcel parcelZza = zza(11, a_());
        Bundle bundle = (Bundle) zzex.zza(parcelZza, Bundle.CREATOR);
        parcelZza.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final IObjectWrapper zzp() throws RemoteException {
        Parcel parcelZza = zza(18, a_());
        IObjectWrapper iObjectWrapperZza = IObjectWrapper.zza.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperZza;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final String zzq() throws RemoteException {
        Parcel parcelZza = zza(19, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final zzqo zzr() throws RemoteException {
        zzqo zzqqVar;
        Parcel parcelZza = zza(17, a_());
        IBinder strongBinder = parcelZza.readStrongBinder();
        if (strongBinder == null) {
            zzqqVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
            zzqqVar = iInterfaceQueryLocalInterface instanceof zzqo ? (zzqo) iInterfaceQueryLocalInterface : new zzqq(strongBinder);
        }
        parcelZza.recycle();
        return zzqqVar;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final void zzs() throws RemoteException {
        zzb(12, a_());
    }
}
