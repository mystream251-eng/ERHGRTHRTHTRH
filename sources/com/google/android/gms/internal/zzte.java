package com.google.android.gms.internal;

import android.content.Context;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.ads.internal.zzbt;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzte implements zzm {
    private zzsx zza;
    private boolean zzb;
    private final Context zzc;
    private final Object zzd = new Object();

    public zzte(Context context) {
        this.zzc = context;
    }

    private final Future<ParcelFileDescriptor> zza(zzsy zzsyVar) {
        zztf zztfVar = new zztf(this);
        zztg zztgVar = new zztg(this, zztfVar, zzsyVar);
        zztj zztjVar = new zztj(this, zztfVar);
        synchronized (this.zzd) {
            zzsx zzsxVar = new zzsx(this.zzc, zzbt.zzt().zza(), zztgVar, zztjVar);
            this.zza = zzsxVar;
            zzsxVar.zzz();
        }
        return zztfVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza() {
        synchronized (this.zzd) {
            if (this.zza == null) {
                return;
            }
            this.zza.zzg();
            this.zza = null;
            Binder.flushPendingCommands();
        }
    }

    static /* synthetic */ boolean zza(zzte zzteVar, boolean z) {
        zzteVar.zzb = true;
        return true;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.internal.zza */
    public final zzp zza(zzr<?> zzrVar) throws zza, zzae {
        zzp zzpVar;
        zzsy zzsyVarZza = zzsy.zza(zzrVar);
        long jIntValue = ((Integer) zzlc.zzf().zza(zzoi.zzga)).intValue();
        long jZzb = zzbt.zzl().zzb();
        try {
            try {
                zzta zztaVarZza = new zzacv(zza(zzsyVarZza).get(jIntValue, TimeUnit.MILLISECONDS)).zza(zzta.CREATOR);
                if (zztaVarZza.zza) {
                    throw new zzae(zztaVarZza.zzb);
                }
                if (zztaVarZza.zze.length != zztaVarZza.zzf.length) {
                    zzpVar = null;
                } else {
                    HashMap map = new HashMap();
                    for (int i = 0; i < zztaVarZza.zze.length; i++) {
                        map.put(zztaVarZza.zze[i], zztaVarZza.zzf[i]);
                    }
                    zzpVar = new zzp(zztaVarZza.zzc, zztaVarZza.zzd, map, zztaVarZza.zzg, zztaVarZza.zzh);
                }
                long jZzb2 = zzbt.zzl().zzb() - jZzb;
                StringBuilder sb = new StringBuilder(52);
                sb.append("Http assets remote cache took ");
                sb.append(jZzb2);
                sb.append("ms");
                zzahw.zza(sb.toString());
                return zzpVar;
            } catch (InterruptedException | ExecutionException | TimeoutException unused) {
                long jZzb3 = zzbt.zzl().zzb() - jZzb;
                StringBuilder sb2 = new StringBuilder(52);
                sb2.append("Http assets remote cache took ");
                sb2.append(jZzb3);
                sb2.append("ms");
                zzahw.zza(sb2.toString());
                return null;
            }
        } catch (Throwable th) {
            long jZzb4 = zzbt.zzl().zzb() - jZzb;
            StringBuilder sb3 = new StringBuilder(52);
            sb3.append("Http assets remote cache took ");
            sb3.append(jZzb4);
            sb3.append("ms");
            zzahw.zza(sb3.toString());
            throw th;
        }
    }
}
