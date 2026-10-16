package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class zzcdf extends zzbgl {
    public static final Parcelable.Creator<zzcdf> CREATOR = new zzcdg();
    private int zza;
    private zzba zzb = null;
    private byte[] zzc;

    zzcdf(int i, byte[] bArr) {
        this.zza = i;
        this.zzc = bArr;
        zzb();
    }

    private final void zzb() {
        if (this.zzb != null || this.zzc == null) {
            if (this.zzb == null || this.zzc != null) {
                if (this.zzb != null && this.zzc != null) {
                    throw new IllegalStateException("Invalid internal representation - full");
                }
                if (this.zzb != null || this.zzc != null) {
                    throw new IllegalStateException("Impossible");
                }
                throw new IllegalStateException("Invalid internal representation - empty");
            }
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 1, this.zza);
        byte[] bArrZza = this.zzc;
        if (bArrZza == null) {
            bArrZza = zzfls.zza(this.zzb);
        }
        zzbgo.zza(parcel, 2, bArrZza, false);
        zzbgo.zza(parcel, iZza);
    }

    public final zzba zza() {
        if (!(this.zzb != null)) {
            try {
                this.zzb = zzfls.zza(new zzba(), this.zzc);
                this.zzc = null;
            } catch (zzflr e) {
                throw new IllegalStateException((Throwable) e);
            }
        }
        zzb();
        return this.zzb;
    }
}
