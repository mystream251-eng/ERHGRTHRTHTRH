package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;

/* JADX INFO: loaded from: classes2.dex */
final class zzla extends zzks.zza<zzaex> {
    private /* synthetic */ Context zza;
    private /* synthetic */ zzwf zzb;
    private /* synthetic */ zzks zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzla(zzks zzksVar, Context context, zzwf zzwfVar) {
        super();
        this.zzc = zzksVar;
        this.zza = context;
        this.zzb = zzwfVar;
    }

    @Override // com.google.android.gms.internal.zzks.zza
    public final /* synthetic */ zzaex zza() throws RemoteException {
        zzaex zzaexVarZza = this.zzc.zzg.zza(this.zza, this.zzb);
        if (zzaexVarZza != null) {
            return zzaexVarZza;
        }
        zzks zzksVar = this.zzc;
        zzks.zza(this.zza, "rewarded_video");
        return new zznm();
    }

    @Override // com.google.android.gms.internal.zzks.zza
    public final /* synthetic */ zzaex zza(zzmb zzmbVar) throws RemoteException {
        return zzmbVar.createRewardedVideoAd(zzn.zza(this.zza), this.zzb, 12210000);
    }
}
