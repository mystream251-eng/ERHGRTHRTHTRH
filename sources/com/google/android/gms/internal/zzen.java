package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzen extends zzeu {
    public zzen(zzdm zzdmVar, String str, String str2, zzba zzbaVar, int i, int i2) {
        super(zzdmVar, str, str2, zzbaVar, i, 51);
    }

    @Override // com.google.android.gms.internal.zzeu
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zzb) {
            zzdl zzdlVar = new zzdl((String) this.zzc.invoke(null, new Object[0]));
            this.zzb.zzag = zzdlVar.zza;
            this.zzb.zzah = zzdlVar.zzb;
        }
    }
}
