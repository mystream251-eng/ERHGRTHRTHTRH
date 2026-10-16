package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzeo extends zzeu {
    private final zzdu zzd;

    public zzeo(zzdm zzdmVar, String str, String str2, zzba zzbaVar, int i, int i2, zzdu zzduVar) {
        super(zzdmVar, str, str2, zzbaVar, i, 53);
        this.zzd = zzduVar;
    }

    @Override // com.google.android.gms.internal.zzeu
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        if (this.zzd != null) {
            this.zzb.zzai = (Long) this.zzc.invoke(null, Long.valueOf(this.zzd.zza()));
        }
    }
}
