package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzec extends zzeu {
    private static volatile Long zzd;
    private static final Object zze = new Object();

    public zzec(zzdm zzdmVar, String str, String str2, zzba zzbaVar, int i, int i2) {
        super(zzdmVar, str, str2, zzbaVar, i, 44);
    }

    @Override // com.google.android.gms.internal.zzeu
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        if (zzd == null) {
            synchronized (zze) {
                if (zzd == null) {
                    zzd = (Long) this.zzc.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.zzb) {
            this.zzb.zzaa = zzd;
        }
    }
}
