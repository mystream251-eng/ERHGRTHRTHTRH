package com.google.android.gms.internal;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzea extends zzeu {
    private static volatile zzbw zzd;
    private static final Object zze = new Object();
    private zzax zzf;

    public zzea(zzdm zzdmVar, String str, String str2, zzba zzbaVar, int i, int i2, zzax zzaxVar) {
        super(zzdmVar, str, str2, zzbaVar, i, 27);
        this.zzf = null;
        this.zzf = zzaxVar;
    }

    private final String zzc() {
        try {
            if (this.zza.zzl() != null) {
                this.zza.zzl().get();
            }
            zzba zzbaVarZzk = this.zza.zzk();
            if (zzbaVarZzk == null || zzbaVarZzk.zzn == null) {
                return null;
            }
            return zzbaVarZzk.zzn;
        } catch (InterruptedException | ExecutionException unused) {
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0078  */
    /* JADX WARN: Code duplicated, block: B:32:0x007d  */
    @Override // com.google.android.gms.internal.zzeu
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        char c;
        boolean z;
        if (zzd == null || zzds.zzb(zzd.zza) || zzd.zza.equals(ExifInterface.LONGITUDE_EAST) || zzd.zza.equals("0000000000000000000000000000000000000000000000000000000000000000")) {
            synchronized (zze) {
                zzay zzayVar = null;
                if (zzds.zzb(null)) {
                    zzds.zzb(null);
                    Boolean bool = false;
                    if (bool.booleanValue()) {
                        if (this.zza.zzi()) {
                            if (((Boolean) zzlc.zzf().zza(zzoi.zzeh)).booleanValue()) {
                                if (((Boolean) zzlc.zzf().zza(zzoi.zzei)).booleanValue()) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                            } else {
                                z = false;
                            }
                        } else {
                            z = false;
                        }
                        if (z) {
                            c = 3;
                        } else {
                            c = 2;
                        }
                    } else {
                        c = 2;
                    }
                } else {
                    c = 4;
                }
                Method method = this.zzc;
                Object[] objArr = new Object[3];
                objArr[0] = this.zza.zza();
                objArr[1] = Boolean.valueOf(c == 2);
                objArr[2] = zzlc.zzf().zza(zzoi.zzea);
                zzbw zzbwVar = new zzbw((String) method.invoke(null, objArr));
                zzd = zzbwVar;
                if (zzds.zzb(zzbwVar.zza) || zzd.zza.equals(ExifInterface.LONGITUDE_EAST)) {
                    if (c == 3) {
                        String strZzc = zzc();
                        if (!zzds.zzb(strZzc)) {
                            zzd.zza = strZzc;
                        }
                    } else if (c == 4) {
                        zzd.zza = zzayVar.zza;
                    }
                }
            }
        }
        synchronized (this.zzb) {
            if (zzd != null) {
                this.zzb.zzn = zzd.zza;
                this.zzb.zzt = Long.valueOf(zzd.zzb);
                this.zzb.zzs = zzd.zzc;
                this.zzb.zzac = zzd.zzd;
                this.zzb.zzad = zzd.zze;
            }
        }
    }
}
