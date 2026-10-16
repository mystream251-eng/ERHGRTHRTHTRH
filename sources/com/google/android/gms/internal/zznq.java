package com.google.android.gms.internal;

import android.os.Parcel;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zznq extends zzko {
    public zznq(zzko zzkoVar) {
        super(zzkoVar.zza, zzkoVar.zzb, zzkoVar.zzc, zzkoVar.zzd, zzkoVar.zze, zzkoVar.zzf, zzkoVar.zzg, zzkoVar.zzh, zzkoVar.zzi, zzkoVar.zzj);
    }

    @Override // com.google.android.gms.internal.zzko
    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zza, false);
        zzbgo.zza(parcel, 3, this.zzb);
        zzbgo.zza(parcel, 6, this.zze);
        zzbgo.zza(parcel, iZza);
    }
}
