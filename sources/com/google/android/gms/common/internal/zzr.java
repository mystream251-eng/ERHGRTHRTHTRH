package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzr extends com.google.android.gms.internal.common.zzb implements zzq {
    zzr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
    }

    @Override // com.google.android.gms.common.internal.zzq
    public final boolean zza(com.google.android.gms.common.zzj zzjVar, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        com.google.android.gms.internal.common.zzd.zza(parcelA_, zzjVar);
        com.google.android.gms.internal.common.zzd.zza(parcelA_, iObjectWrapper);
        Parcel parcelZza = zza(5, parcelA_);
        boolean zZza = com.google.android.gms.internal.common.zzd.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }
}
