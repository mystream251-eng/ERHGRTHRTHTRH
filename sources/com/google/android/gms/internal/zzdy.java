package com.google.android.gms.internal;

import android.provider.Settings;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdy extends zzeu {
    public zzdy(zzdm zzdmVar, String str, String str2, zzba zzbaVar, int i, int i2) {
        super(zzdmVar, str, str2, zzbaVar, i, 49);
    }

    @Override // com.google.android.gms.internal.zzeu
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        this.zzb.zzaf = 2;
        try {
            int i = 1;
            boolean zBooleanValue = ((Boolean) this.zzc.invoke(null, this.zza.zza())).booleanValue();
            zzba zzbaVar = this.zzb;
            if (!zBooleanValue) {
                i = 0;
            }
            zzbaVar.zzaf = Integer.valueOf(i);
        } catch (InvocationTargetException e) {
            if (!(e.getTargetException() instanceof Settings.SettingNotFoundException)) {
                throw e;
            }
        }
    }
}
