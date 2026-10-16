package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;

/* JADX INFO: loaded from: classes2.dex */
final class zzku extends zzks.zza<zzlt> {
    private /* synthetic */ Context zza;
    private /* synthetic */ zzko zzb;
    private /* synthetic */ String zzc;
    private /* synthetic */ zzks zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzku(zzks zzksVar, Context context, zzko zzkoVar, String str) {
        super();
        this.zzd = zzksVar;
        this.zza = context;
        this.zzb = zzkoVar;
        this.zzc = str;
    }

    @Override // com.google.android.gms.internal.zzks.zza
    public final /* synthetic */ zzlt zza() throws RemoteException {
        zzlt zzltVarZza = this.zzd.zzc.zza(this.zza, this.zzb, this.zzc, null, 3);
        if (zzltVarZza != null) {
            return zzltVarZza;
        }
        zzks zzksVar = this.zzd;
        zzks.zza(this.zza, "search");
        return new zznh();
    }

    @Override // com.google.android.gms.internal.zzks.zza
    public final /* synthetic */ zzlt zza(zzmb zzmbVar) throws RemoteException {
        return zzmbVar.createSearchAdManager(zzn.zza(this.zza), this.zzb, this.zzc, 12210000);
    }
}
