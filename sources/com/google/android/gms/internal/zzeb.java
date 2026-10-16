package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzeb extends zzeu {
    public zzeb(zzdm zzdmVar, String str, String str2, zzba zzbaVar, int i, int i2) {
        super(zzdmVar, str, str2, zzbaVar, i, 5);
    }

    @Override // com.google.android.gms.internal.zzeu
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        this.zzb.zzd = -1L;
        this.zzb.zze = -1L;
        int[] iArr = (int[]) this.zzc.invoke(null, this.zza.zza());
        synchronized (this.zzb) {
            this.zzb.zzd = Long.valueOf(iArr[0]);
            this.zzb.zze = Long.valueOf(iArr[1]);
            if (((Boolean) zzlc.zzf().zza(zzoi.zzdw)).booleanValue() && iArr[2] != Integer.MIN_VALUE) {
                this.zzb.zzan = Long.valueOf(iArr[2]);
            }
        }
    }
}
