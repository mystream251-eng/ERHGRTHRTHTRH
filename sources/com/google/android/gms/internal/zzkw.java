package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;

/* JADX INFO: loaded from: classes2.dex */
final class zzkw extends zzks.zza<zzlo> {
    private /* synthetic */ Context zza;
    private /* synthetic */ String zzb;
    private /* synthetic */ zzwf zzc;
    private /* synthetic */ zzks zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzkw(zzks zzksVar, Context context, String str, zzwf zzwfVar) {
        super();
        this.zzd = zzksVar;
        this.zza = context;
        this.zzb = str;
        this.zzc = zzwfVar;
    }

    @Override // com.google.android.gms.internal.zzks.zza
    public final /* synthetic */ zzlo zza() throws RemoteException {
        zzlo zzloVarZza = this.zzd.zzd.zza(this.zza, this.zzb, this.zzc);
        if (zzloVarZza != null) {
            return zzloVarZza;
        }
        zzks zzksVar = this.zzd;
        zzks.zza(this.zza, "native_ad");
        return new zznd();
    }

    @Override // com.google.android.gms.internal.zzks.zza
    public final /* synthetic */ zzlo zza(zzmb zzmbVar) throws RemoteException {
        return zzmbVar.createAdLoaderBuilder(zzn.zza(this.zza), this.zzb, this.zzc, 12210000);
    }
}
