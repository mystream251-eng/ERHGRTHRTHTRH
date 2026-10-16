package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzej extends zzeu {
    public zzej(zzdm zzdmVar, String str, String str2, zzba zzbaVar, int i, int i2) {
        super(zzdmVar, str, str2, zzbaVar, i, 3);
    }

    @Override // com.google.android.gms.internal.zzeu
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zzb) {
            zzcz zzczVar = new zzcz((String) this.zzc.invoke(null, this.zza.zza()));
            synchronized (this.zzb) {
                this.zzb.zzc = Long.valueOf(zzczVar.zza);
                this.zzb.zzao = Long.valueOf(zzczVar.zzb);
            }
        }
    }
}
