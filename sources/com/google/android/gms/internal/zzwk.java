package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class zzwk extends zzev implements zzwi {
    zzwk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    @Override // com.google.android.gms.internal.zzwi
    public final IObjectWrapper zza() throws RemoteException {
        Parcel parcelZza = zza(2, a_());
        IObjectWrapper iObjectWrapperZza = IObjectWrapper.zza.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperZza;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(21, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzafz zzafzVar, List<String> list) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzex.zza(parcelA_, zzafzVar);
        parcelA_.writeStringList(list);
        zzb(23, parcelA_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkkVar, String str, zzafz zzafzVar, String str2) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzex.zza(parcelA_, zzkkVar);
        parcelA_.writeString(str);
        zzex.zza(parcelA_, zzafzVar);
        parcelA_.writeString(str2);
        zzb(10, parcelA_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkkVar, String str, zzwl zzwlVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzex.zza(parcelA_, zzkkVar);
        parcelA_.writeString(str);
        zzex.zza(parcelA_, zzwlVar);
        zzb(3, parcelA_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkkVar, String str, String str2, zzwl zzwlVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzex.zza(parcelA_, zzkkVar);
        parcelA_.writeString(str);
        parcelA_.writeString(str2);
        zzex.zza(parcelA_, zzwlVar);
        zzb(7, parcelA_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkkVar, String str, String str2, zzwl zzwlVar, zzqh zzqhVar, List<String> list) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzex.zza(parcelA_, zzkkVar);
        parcelA_.writeString(str);
        parcelA_.writeString(str2);
        zzex.zza(parcelA_, zzwlVar);
        zzex.zza(parcelA_, zzqhVar);
        parcelA_.writeStringList(list);
        zzb(14, parcelA_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzko zzkoVar, zzkk zzkkVar, String str, zzwl zzwlVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzex.zza(parcelA_, zzkoVar);
        zzex.zza(parcelA_, zzkkVar);
        parcelA_.writeString(str);
        zzex.zza(parcelA_, zzwlVar);
        zzb(1, parcelA_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzwi
    public final void zza(IObjectWrapper iObjectWrapper, zzko zzkoVar, zzkk zzkkVar, String str, String str2, zzwl zzwlVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzex.zza(parcelA_, zzkoVar);
        zzex.zza(parcelA_, zzkkVar);
        parcelA_.writeString(str);
        parcelA_.writeString(str2);
        zzex.zza(parcelA_, zzwlVar);
        zzb(6, parcelA_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzwi
    public final void zza(zzkk zzkkVar, String str) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzkkVar);
        parcelA_.writeString(str);
        zzb(11, parcelA_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzwi
    public final void zza(zzkk zzkkVar, String str, String str2) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzkkVar);
        parcelA_.writeString(str);
        parcelA_.writeString(str2);
        zzb(20, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zza(boolean z) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, z);
        zzb(25, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zzb() throws RemoteException {
        zzb(4, a_());
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zzc() throws RemoteException {
        zzb(5, a_());
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zzd() throws RemoteException {
        zzb(8, a_());
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zze() throws RemoteException {
        zzb(9, a_());
    }

    @Override // com.google.android.gms.internal.zzwi
    public final void zzf() throws RemoteException {
        zzb(12, a_());
    }

    @Override // com.google.android.gms.internal.zzwi
    public final boolean zzg() throws RemoteException {
        Parcel parcelZza = zza(13, a_());
        boolean zZza = zzex.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final zzwr zzh() throws RemoteException {
        zzwr zzwtVar;
        Parcel parcelZza = zza(15, a_());
        IBinder strongBinder = parcelZza.readStrongBinder();
        if (strongBinder == null) {
            zzwtVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.INativeAppInstallAdMapper");
            zzwtVar = iInterfaceQueryLocalInterface instanceof zzwr ? (zzwr) iInterfaceQueryLocalInterface : new zzwt(strongBinder);
        }
        parcelZza.recycle();
        return zzwtVar;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final zzwu zzi() throws RemoteException {
        zzwu zzwwVar;
        Parcel parcelZza = zza(16, a_());
        IBinder strongBinder = parcelZza.readStrongBinder();
        if (strongBinder == null) {
            zzwwVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
            zzwwVar = iInterfaceQueryLocalInterface instanceof zzwu ? (zzwu) iInterfaceQueryLocalInterface : new zzww(strongBinder);
        }
        parcelZza.recycle();
        return zzwwVar;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final Bundle zzj() throws RemoteException {
        Parcel parcelZza = zza(17, a_());
        Bundle bundle = (Bundle) zzex.zza(parcelZza, Bundle.CREATOR);
        parcelZza.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final Bundle zzk() throws RemoteException {
        Parcel parcelZza = zza(18, a_());
        Bundle bundle = (Bundle) zzex.zza(parcelZza, Bundle.CREATOR);
        parcelZza.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final Bundle zzl() throws RemoteException {
        Parcel parcelZza = zza(19, a_());
        Bundle bundle = (Bundle) zzex.zza(parcelZza, Bundle.CREATOR);
        parcelZza.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final boolean zzm() throws RemoteException {
        Parcel parcelZza = zza(22, a_());
        boolean zZza = zzex.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final zzro zzn() throws RemoteException {
        Parcel parcelZza = zza(24, a_());
        zzro zzroVarZza = zzrp.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return zzroVarZza;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final zzmm zzo() throws RemoteException {
        Parcel parcelZza = zza(26, a_());
        zzmm zzmmVarZza = zzmn.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return zzmmVarZza;
    }

    @Override // com.google.android.gms.internal.zzwi
    public final zzwx zzp() throws RemoteException {
        zzwx zzwzVar;
        Parcel parcelZza = zza(27, a_());
        IBinder strongBinder = parcelZza.readStrongBinder();
        if (strongBinder == null) {
            zzwzVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IUnifiedNativeAdMapper");
            zzwzVar = iInterfaceQueryLocalInterface instanceof zzwx ? (zzwx) iInterfaceQueryLocalInterface : new zzwz(strongBinder);
        }
        parcelZza.recycle();
        return zzwzVar;
    }
}
