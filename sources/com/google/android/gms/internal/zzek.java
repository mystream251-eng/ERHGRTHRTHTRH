package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class zzek extends zzeu {
    private List<Long> zzd;

    public zzek(zzdm zzdmVar, String str, String str2, zzba zzbaVar, int i, int i2) {
        super(zzdmVar, str, str2, zzbaVar, i, 31);
        this.zzd = null;
    }

    @Override // com.google.android.gms.internal.zzeu
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        this.zzb.zzp = -1L;
        this.zzb.zzq = -1L;
        if (this.zzd == null) {
            this.zzd = (List) this.zzc.invoke(null, this.zza.zza());
        }
        List<Long> list = this.zzd;
        if (list == null || list.size() != 2) {
            return;
        }
        synchronized (this.zzb) {
            this.zzb.zzp = Long.valueOf(this.zzd.get(0).longValue());
            this.zzb.zzq = Long.valueOf(this.zzd.get(1).longValue());
        }
    }
}
