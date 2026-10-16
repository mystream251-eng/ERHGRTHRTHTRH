package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;

/* JADX INFO: loaded from: classes2.dex */
public final class zzlq extends zzev implements zzlo {
    zzlq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }

    @Override // com.google.android.gms.internal.zzlo
    public final zzll zza() throws RemoteException {
        zzll zzlnVar;
        Parcel parcelZza = zza(1, a_());
        IBinder strongBinder = parcelZza.readStrongBinder();
        if (strongBinder == null) {
            zzlnVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoader");
            zzlnVar = iInterfaceQueryLocalInterface instanceof zzll ? (zzll) iInterfaceQueryLocalInterface : new zzln(strongBinder);
        }
        parcelZza.recycle();
        return zzlnVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzlo
    public final void zza(PublisherAdViewOptions publisherAdViewOptions) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, publisherAdViewOptions);
        zzb(9, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzlo
    public final void zza(zzli zzliVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzliVar);
        zzb(2, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzlo
    public final void zza(zzme zzmeVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzmeVar);
        zzb(7, parcelA_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzlo
    public final void zza(zzqh zzqhVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzqhVar);
        zzb(6, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzlo
    public final void zza(zzrs zzrsVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzrsVar);
        zzb(3, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzlo
    public final void zza(zzrv zzrvVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzrvVar);
        zzb(4, parcelA_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzlo
    public final void zza(zzse zzseVar, zzko zzkoVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzseVar);
        zzex.zza(parcelA_, zzkoVar);
        zzb(8, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzlo
    public final void zza(zzsh zzshVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzshVar);
        zzb(10, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzlo
    public final void zza(String str, zzsb zzsbVar, zzry zzryVar) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        zzex.zza(parcelA_, zzsbVar);
        zzex.zza(parcelA_, zzryVar);
        zzb(5, parcelA_);
    }
}
