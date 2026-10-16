package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;

/* JADX INFO: loaded from: classes2.dex */
final class zzkx extends zzks.zza<zzmh> {
    private /* synthetic */ Context zza;
    private /* synthetic */ zzks zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzkx(zzks zzksVar, Context context) {
        super();
        this.zzb = zzksVar;
        this.zza = context;
    }

    @Override // com.google.android.gms.internal.zzks.zza
    public final /* synthetic */ zzmh zza() throws RemoteException {
        zzmh zzmhVarZza = this.zzb.zze.zza(this.zza);
        if (zzmhVarZza != null) {
            return zzmhVarZza;
        }
        zzks zzksVar = this.zzb;
        zzks.zza(this.zza, "mobile_ads_settings");
        return new zznj();
    }

    @Override // com.google.android.gms.internal.zzks.zza
    public final /* synthetic */ zzmh zza(zzmb zzmbVar) throws RemoteException {
        return zzmbVar.getMobileAdsSettingsManagerWithClientJarVersion(zzn.zza(this.zza), 12210000);
    }
}
