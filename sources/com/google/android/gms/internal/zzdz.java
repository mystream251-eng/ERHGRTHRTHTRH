package com.google.android.gms.internal;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdz extends zzeu {
    private static volatile String zzd;
    private static final Object zze = new Object();

    public zzdz(zzdm zzdmVar, String str, String str2, zzba zzbaVar, int i, int i2) {
        super(zzdmVar, str, str2, zzbaVar, i, 29);
    }

    @Override // com.google.android.gms.internal.zzeu
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        this.zzb.zzo = ExifInterface.LONGITUDE_EAST;
        if (zzd == null) {
            synchronized (zze) {
                if (zzd == null) {
                    zzd = (String) this.zzc.invoke(null, this.zza.zza());
                }
            }
        }
        synchronized (this.zzb) {
            this.zzb.zzo = zzbv.zza(zzd.getBytes(), true);
        }
    }
}
