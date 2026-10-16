package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzem extends zzeu {
    private final StackTraceElement[] zzd;

    public zzem(zzdm zzdmVar, String str, String str2, zzba zzbaVar, int i, int i2, StackTraceElement[] stackTraceElementArr) {
        super(zzdmVar, str, str2, zzbaVar, i, 45);
        this.zzd = stackTraceElementArr;
    }

    @Override // com.google.android.gms.internal.zzeu
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        if (this.zzd != null) {
            zzdk zzdkVar = new zzdk((String) this.zzc.invoke(null, this.zzd));
            synchronized (this.zzb) {
                this.zzb.zzab = zzdkVar.zza;
                if (zzdkVar.zzb.booleanValue()) {
                    this.zzb.zzaj = Integer.valueOf(zzdkVar.zzc.booleanValue() ? 0 : 1);
                }
            }
        }
    }
}
