package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;

/* JADX INFO: loaded from: classes2.dex */
final class zzkt extends zzks.zza<zzlt> {
    private /* synthetic */ Context zza;
    private /* synthetic */ zzko zzb;
    private /* synthetic */ String zzc;
    private /* synthetic */ zzwf zzd;
    private /* synthetic */ zzks zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzkt(zzks zzksVar, Context context, zzko zzkoVar, String str, zzwf zzwfVar) {
        super();
        this.zze = zzksVar;
        this.zza = context;
        this.zzb = zzkoVar;
        this.zzc = str;
        this.zzd = zzwfVar;
    }

    @Override // com.google.android.gms.internal.zzks.zza
    public final /* synthetic */ zzlt zza() throws RemoteException {
        zzlt zzltVarZza = this.zze.zzc.zza(this.zza, this.zzb, this.zzc, this.zzd, 1);
        if (zzltVarZza != null) {
            return zzltVarZza;
        }
        zzks zzksVar = this.zze;
        zzks.zza(this.zza, "banner");
        return new zznh();
    }

    @Override // com.google.android.gms.internal.zzks.zza
    public final /* synthetic */ zzlt zza(zzmb zzmbVar) throws RemoteException {
        return zzmbVar.createBannerAdManager(zzn.zza(this.zza), this.zzb, this.zzc, this.zzd, 12210000);
    }
}
