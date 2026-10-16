package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzeh extends zzeu {
    private long zzd;

    public zzeh(zzdm zzdmVar, String str, String str2, zzba zzbaVar, int i, int i2) {
        super(zzdmVar, str, str2, zzbaVar, i, 12);
        this.zzd = -1L;
    }

    @Override // com.google.android.gms.internal.zzeu
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        this.zzb.zzf = -1L;
        this.zzb.zzf = (Long) this.zzc.invoke(null, this.zza.zza());
    }
}
