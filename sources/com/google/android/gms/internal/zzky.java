package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.zzn;

/* JADX INFO: loaded from: classes2.dex */
final class zzky extends zzks.zza<zzqw> {
    private /* synthetic */ FrameLayout zza;
    private /* synthetic */ FrameLayout zzb;
    private /* synthetic */ Context zzc;
    private /* synthetic */ zzks zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzky(zzks zzksVar, FrameLayout frameLayout, FrameLayout frameLayout2, Context context) {
        super();
        this.zzd = zzksVar;
        this.zza = frameLayout;
        this.zzb = frameLayout2;
        this.zzc = context;
    }

    @Override // com.google.android.gms.internal.zzks.zza
    public final /* synthetic */ zzqw zza() throws RemoteException {
        zzqw zzqwVarZza = this.zzd.zzf.zza(this.zzc, this.zza, this.zzb);
        if (zzqwVarZza != null) {
            return zzqwVarZza;
        }
        zzks zzksVar = this.zzd;
        zzks.zza(this.zzc, "native_ad_view_delegate");
        return new zznk();
    }

    @Override // com.google.android.gms.internal.zzks.zza
    public final /* synthetic */ zzqw zza(zzmb zzmbVar) throws RemoteException {
        return zzmbVar.createNativeAdViewDelegate(zzn.zza(this.zza), zzn.zza(this.zzb));
    }
}
