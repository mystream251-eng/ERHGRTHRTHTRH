package com.google.android.gms.internal;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;

/* JADX INFO: loaded from: classes2.dex */
final class zzlb extends zzks.zza<zzyq> {
    private /* synthetic */ Activity zza;
    private /* synthetic */ zzks zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzlb(zzks zzksVar, Activity activity) {
        super();
        this.zzb = zzksVar;
        this.zza = activity;
    }

    @Override // com.google.android.gms.internal.zzks.zza
    public final /* synthetic */ zzyq zza() throws RemoteException {
        zzyq zzyqVarZza = this.zzb.zzh.zza(this.zza);
        if (zzyqVarZza != null) {
            return zzyqVarZza;
        }
        zzks zzksVar = this.zzb;
        zzks.zza(this.zza, "ad_overlay");
        return null;
    }

    @Override // com.google.android.gms.internal.zzks.zza
    public final /* synthetic */ zzyq zza(zzmb zzmbVar) throws RemoteException {
        return zzmbVar.createAdOverlay(zzn.zza(this.zza));
    }
}
