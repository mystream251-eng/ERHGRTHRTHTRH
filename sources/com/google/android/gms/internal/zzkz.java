package com.google.android.gms.internal;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.dynamic.zzn;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
final class zzkz extends zzks.zza<zzrb> {
    private /* synthetic */ View zza;
    private /* synthetic */ HashMap zzb;
    private /* synthetic */ HashMap zzc;
    private /* synthetic */ zzks zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzkz(zzks zzksVar, View view, HashMap map, HashMap map2) {
        super();
        this.zzd = zzksVar;
        this.zza = view;
        this.zzb = map;
        this.zzc = map2;
    }

    @Override // com.google.android.gms.internal.zzks.zza
    public final /* synthetic */ zzrb zza() throws RemoteException {
        zzrb zzrbVarZza = this.zzd.zzi.zza(this.zza, this.zzb, this.zzc);
        if (zzrbVarZza != null) {
            return zzrbVarZza;
        }
        zzks zzksVar = this.zzd;
        zzks.zza(this.zza.getContext(), "native_ad_view_holder_delegate");
        return new zznl();
    }

    @Override // com.google.android.gms.internal.zzks.zza
    public final /* synthetic */ zzrb zza(zzmb zzmbVar) throws RemoteException {
        return zzmbVar.createNativeAdViewHolderDelegate(zzn.zza(this.zza), zzn.zza(this.zzb), zzn.zza(this.zzc));
    }
}
