package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zztd extends zzev implements zztc {
    zztd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.httpcache.IHttpAssetsCacheService");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zztc
    public final ParcelFileDescriptor zza(zzsy zzsyVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzsyVar);
        Parcel parcelZza = zza(1, parcelA_);
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) zzex.zza(parcelZza, ParcelFileDescriptor.CREATOR);
        parcelZza.recycle();
        return parcelFileDescriptor;
    }
}
