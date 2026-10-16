package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzed extends zzeu {
    private long zzd;

    public zzed(zzdm zzdmVar, String str, String str2, zzba zzbaVar, long j, int i, int i2) {
        super(zzdmVar, str, str2, zzbaVar, i, 25);
        this.zzd = j;
    }

    @Override // com.google.android.gms.internal.zzeu
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        long jLongValue = ((Long) this.zzc.invoke(null, new Object[0])).longValue();
        synchronized (this.zzb) {
            this.zzb.zzav = Long.valueOf(jLongValue);
            if (this.zzd != 0) {
                this.zzb.zzj = Long.valueOf(jLongValue - this.zzd);
                this.zzb.zzm = Long.valueOf(this.zzd);
            }
        }
    }
}
