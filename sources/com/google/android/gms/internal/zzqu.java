package com.google.android.gms.internal;

import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public final class zzqu extends zzev implements zzqs {
    zzqu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.INativeAdImage");
    }

    @Override // com.google.android.gms.internal.zzqs
    public final IObjectWrapper zza() throws RemoteException {
        Parcel parcelZza = zza(1, a_());
        IObjectWrapper iObjectWrapperZza = IObjectWrapper.zza.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperZza;
    }

    @Override // com.google.android.gms.internal.zzqs
    public final Uri zzb() throws RemoteException {
        Parcel parcelZza = zza(2, a_());
        Uri uri = (Uri) zzex.zza(parcelZza, Uri.CREATOR);
        parcelZza.recycle();
        return uri;
    }

    @Override // com.google.android.gms.internal.zzqs
    public final double zzc() throws RemoteException {
        Parcel parcelZza = zza(3, a_());
        double d = parcelZza.readDouble();
        parcelZza.recycle();
        return d;
    }
}
