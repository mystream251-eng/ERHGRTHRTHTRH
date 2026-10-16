package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public final class zzlv extends zzev implements zzlt {
    zzlv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdManager");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzafc zzafcVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzafcVar);
        zzb(24, parcelA_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzko zzkoVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzkoVar);
        zzb(13, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzlf zzlfVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzlfVar);
        zzb(20, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzli zzliVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzliVar);
        zzb(7, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzly zzlyVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzlyVar);
        zzb(8, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzme zzmeVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzmeVar);
        zzb(21, parcelA_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzms zzmsVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzmsVar);
        zzb(30, parcelA_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzns zznsVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zznsVar);
        zzb(29, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzpb zzpbVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzpbVar);
        zzb(19, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzyx zzyxVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzyxVar);
        zzb(14, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzzd zzzdVar, String str) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzzdVar);
        parcelA_.writeString(str);
        zzb(15, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(String str) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        zzb(25, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(boolean z) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, z);
        zzb(22, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzlt
    public final String zzal() throws RemoteException {
        Parcel parcelZza = zza(18, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final String zzam() throws RemoteException {
        Parcel parcelZza = zza(35, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzan() throws RemoteException {
        zzb(9, a_());
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzb() throws RemoteException {
        zzb(2, a_());
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzb(boolean z) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, z);
        zzb(34, parcelA_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzlt
    public final boolean zzb(zzkk zzkkVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzkkVar);
        Parcel parcelZza = zza(4, parcelA_);
        boolean zZza = zzex.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final IObjectWrapper zzc() throws RemoteException {
        Parcel parcelZza = zza(1, a_());
        IObjectWrapper iObjectWrapperZza = IObjectWrapper.zza.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperZza;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final zzko zzd() throws RemoteException {
        Parcel parcelZza = zza(12, a_());
        zzko zzkoVar = (zzko) zzex.zza(parcelZza, zzko.CREATOR);
        parcelZza.recycle();
        return zzkoVar;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final boolean zze() throws RemoteException {
        Parcel parcelZza = zza(3, a_());
        boolean zZza = zzex.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzg() throws RemoteException {
        zzb(11, a_());
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzh() throws RemoteException {
        zzb(5, a_());
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzi() throws RemoteException {
        zzb(6, a_());
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzj() throws RemoteException {
        zzb(10, a_());
    }

    @Override // com.google.android.gms.internal.zzlt
    public final boolean zzk() throws RemoteException {
        Parcel parcelZza = zza(23, a_());
        boolean zZza = zzex.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final zzmm zzl() throws RemoteException {
        zzmm zzmoVar;
        Parcel parcelZza = zza(26, a_());
        IBinder strongBinder = parcelZza.readStrongBinder();
        if (strongBinder == null) {
            zzmoVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoController");
            zzmoVar = iInterfaceQueryLocalInterface instanceof zzmm ? (zzmm) iInterfaceQueryLocalInterface : new zzmo(strongBinder);
        }
        parcelZza.recycle();
        return zzmoVar;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final String zzv() throws RemoteException {
        Parcel parcelZza = zza(31, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final zzly zzw() throws RemoteException {
        zzly zzmaVar;
        Parcel parcelZza = zza(32, a_());
        IBinder strongBinder = parcelZza.readStrongBinder();
        if (strongBinder == null) {
            zzmaVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener");
            zzmaVar = iInterfaceQueryLocalInterface instanceof zzly ? (zzly) iInterfaceQueryLocalInterface : new zzma(strongBinder);
        }
        parcelZza.recycle();
        return zzmaVar;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final zzli zzx() throws RemoteException {
        zzli zzlkVar;
        Parcel parcelZza = zza(33, a_());
        IBinder strongBinder = parcelZza.readStrongBinder();
        if (strongBinder == null) {
            zzlkVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
            zzlkVar = iInterfaceQueryLocalInterface instanceof zzli ? (zzli) iInterfaceQueryLocalInterface : new zzlk(strongBinder);
        }
        parcelZza.recycle();
        return zzlkVar;
    }
}
