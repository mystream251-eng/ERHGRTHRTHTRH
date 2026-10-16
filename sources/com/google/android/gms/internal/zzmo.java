package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzmo extends zzev implements zzmm {
    zzmo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IVideoController");
    }

    @Override // com.google.android.gms.internal.zzmm
    public final void zza() throws RemoteException {
        zzb(1, a_());
    }

    @Override // com.google.android.gms.internal.zzmm
    public final void zza(zzmp zzmpVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzmpVar);
        zzb(8, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzmm
    public final void zza(boolean z) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, z);
        zzb(3, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzmm
    public final void zzb() throws RemoteException {
        zzb(2, a_());
    }

    @Override // com.google.android.gms.internal.zzmm
    public final boolean zzc() throws RemoteException {
        Parcel parcelZza = zza(4, a_());
        boolean zZza = zzex.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.zzmm
    public final int zzd() throws RemoteException {
        Parcel parcelZza = zza(5, a_());
        int i = parcelZza.readInt();
        parcelZza.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.zzmm
    public final float zze() throws RemoteException {
        Parcel parcelZza = zza(6, a_());
        float f = parcelZza.readFloat();
        parcelZza.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.zzmm
    public final float zzf() throws RemoteException {
        Parcel parcelZza = zza(7, a_());
        float f = parcelZza.readFloat();
        parcelZza.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.zzmm
    public final float zzg() throws RemoteException {
        Parcel parcelZza = zza(9, a_());
        float f = parcelZza.readFloat();
        parcelZza.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.zzmm
    public final boolean zzh() throws RemoteException {
        Parcel parcelZza = zza(10, a_());
        boolean zZza = zzex.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.zzmm
    public final zzmp zzi() throws RemoteException {
        zzmp zzmrVar;
        Parcel parcelZza = zza(11, a_());
        IBinder strongBinder = parcelZza.readStrongBinder();
        if (strongBinder == null) {
            zzmrVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
            zzmrVar = iInterfaceQueryLocalInterface instanceof zzmp ? (zzmp) iInterfaceQueryLocalInterface : new zzmr(strongBinder);
        }
        parcelZza.recycle();
        return zzmrVar;
    }

    @Override // com.google.android.gms.internal.zzmm
    public final boolean zzj() throws RemoteException {
        Parcel parcelZza = zza(12, a_());
        boolean zZza = zzex.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }
}
