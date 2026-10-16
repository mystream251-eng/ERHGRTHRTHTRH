package com.google.android.gms.internal;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzei extends zzeu {
    private static volatile String zzd;
    private static final Object zze = new Object();

    public zzei(zzdm zzdmVar, String str, String str2, zzba zzbaVar, int i, int i2) {
        super(zzdmVar, str, str2, zzbaVar, i, 1);
    }

    @Override // com.google.android.gms.internal.zzeu
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        this.zzb.zza = ExifInterface.LONGITUDE_EAST;
        if (zzd == null) {
            synchronized (zze) {
                if (zzd == null) {
                    zzd = (String) this.zzc.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.zzb) {
            this.zzb.zza = zzd;
        }
    }
}
