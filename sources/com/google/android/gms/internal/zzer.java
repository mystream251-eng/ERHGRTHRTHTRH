package com.google.android.gms.internal;

import android.view.View;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzer extends zzeu {
    private final View zzd;

    public zzer(zzdm zzdmVar, String str, String str2, zzba zzbaVar, int i, int i2, View view) {
        super(zzdmVar, str, str2, zzbaVar, i, 57);
        this.zzd = view;
    }

    @Override // com.google.android.gms.internal.zzeu
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        if (this.zzd != null) {
            zzdt zzdtVar = new zzdt((String) this.zzc.invoke(null, this.zzd, this.zza.zza().getResources().getDisplayMetrics()));
            zzbc zzbcVar = new zzbc();
            zzbcVar.zza = zzdtVar.zza;
            zzbcVar.zzb = zzdtVar.zzb;
            zzbcVar.zzc = zzdtVar.zzc;
            this.zzb.zzam = zzbcVar;
        }
    }
}
