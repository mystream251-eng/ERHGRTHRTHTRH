package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzel extends zzeu {
    public zzel(zzdm zzdmVar, String str, String str2, zzba zzbaVar, int i, int i2) {
        super(zzdmVar, str, str2, zzbaVar, i, 61);
    }

    @Override // com.google.android.gms.internal.zzeu
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        long jLongValue = ((Long) this.zzc.invoke(null, this.zza.zza(), Boolean.valueOf(this.zza.zzj()))).longValue();
        synchronized (this.zzb) {
            this.zzb.zzap = Long.valueOf(jLongValue);
        }
    }
}
