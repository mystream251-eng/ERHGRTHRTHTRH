package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzeq extends zzeu {
    public zzeq(zzdm zzdmVar, String str, String str2, zzba zzbaVar, int i, int i2) {
        super(zzdmVar, str, str2, zzbaVar, i, 48);
    }

    @Override // com.google.android.gms.internal.zzeu
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        zzba zzbaVar;
        int i;
        this.zzb.zzae = 2;
        boolean zBooleanValue = ((Boolean) this.zzc.invoke(null, this.zza.zza())).booleanValue();
        synchronized (this.zzb) {
            try {
                if (zBooleanValue) {
                    zzbaVar = this.zzb;
                    i = 1;
                } else {
                    zzbaVar = this.zzb;
                    i = 0;
                }
                zzbaVar.zzae = i;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
