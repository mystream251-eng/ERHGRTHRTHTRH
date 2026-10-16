package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzik extends zzbgl {
    public static final Parcelable.Creator<zzik> CREATOR = new zzil();
    private ParcelFileDescriptor zza;

    public zzik() {
        this(null);
    }

    public zzik(ParcelFileDescriptor parcelFileDescriptor) {
        this.zza = parcelFileDescriptor;
    }

    private synchronized ParcelFileDescriptor zzc() {
        return this.zza;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, zzc(), i, false);
        zzbgo.zza(parcel, iZza);
    }

    public final synchronized boolean zza() {
        return this.zza != null;
    }

    public final synchronized InputStream zzb() {
        if (this.zza == null) {
            return null;
        }
        ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(this.zza);
        this.zza = null;
        return autoCloseInputStream;
    }
}
