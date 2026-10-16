package com.google.android.gms.internal;

import android.content.Context;
import com.viaccessorca.common.VOUtils;
import java.io.File;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzajr {
    private static zzv zza;
    private static final Object zzb = new Object();

    @Deprecated
    private static zzajx<Void> zzc = new zzajs();

    public zzajr(Context context) {
        zza(context);
    }

    private static zzv zza(Context context) {
        zzv zzvVar;
        zzv zzvVar2;
        synchronized (zzb) {
            if (zza == null) {
                Context applicationContext = context.getApplicationContext();
                zzoi.zza(applicationContext);
                if (((Boolean) zzlc.zzf().zza(zzoi.zzfy)).booleanValue()) {
                    zzvVar2 = zzajl.zza(applicationContext);
                } else {
                    zzvVar2 = new zzv(new zzam(new File(applicationContext.getCacheDir(), "volley")), new zzaj(new zzas()));
                    zzvVar2.zza();
                }
                zza = zzvVar2;
            }
            zzvVar = zza;
        }
        return zzvVar;
    }

    public final zzalt<String> zza(int i, String str, Map<String, String> map, byte[] bArr) {
        zzajy zzajyVar = new zzajy(null);
        zzajv zzajvVar = new zzajv(this, str, zzajyVar);
        zzaks zzaksVar = new zzaks(null);
        zzajw zzajwVar = new zzajw(this, i, str, zzajyVar, zzajvVar, bArr, map, zzaksVar);
        if (zzaks.zzc()) {
            try {
                zzaksVar.zza(str, VOUtils.HTTP_GET, zzajwVar.zzf(), zzajwVar.zzg());
            } catch (zza e) {
                zzahw.zze(e.getMessage());
            }
        }
        zza.zza(zzajwVar);
        return zzajyVar;
    }

    @Deprecated
    public final <T> zzalt<T> zza(String str, zzajx<T> zzajxVar) {
        zzamd zzamdVar = new zzamd();
        zza.zza(new zzajz(str, zzamdVar));
        return zzali.zza(zzali.zza(zzamdVar, new zzaju(this, zzajxVar), zzaly.zzb), Throwable.class, new zzajt(this, zzajxVar), zzaly.zzb);
    }

    public final zzalt<String> zza(String str, Map<String, String> map) {
        return zza(0, str, map, null);
    }
}
