package com.google.android.gms.internal;

import android.os.Build;
import android.os.ConditionVariable;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* JADX INFO: loaded from: classes2.dex */
public class zzcp {
    protected volatile Boolean zzb;
    private zzdm zzc;
    private static final ConditionVariable zzd = new ConditionVariable();
    protected static volatile zziz zza = null;
    private static volatile Random zze = null;

    public zzcp(zzdm zzdmVar) {
        this.zzc = zzdmVar;
        zzdmVar.zzc().execute(new zzcq(this));
    }

    public static int zza() {
        try {
            return Build.VERSION.SDK_INT >= 21 ? ThreadLocalRandom.current().nextInt() : zzc().nextInt();
        } catch (RuntimeException unused) {
            return zzc().nextInt();
        }
    }

    private static Random zzc() {
        if (zze == null) {
            synchronized (zzcp.class) {
                if (zze == null) {
                    zze = new Random();
                }
            }
        }
        return zze;
    }

    public final void zza(int i, int i2, long j) throws IOException {
        try {
            zzd.block();
            if (!this.zzb.booleanValue() || zza == null) {
                return;
            }
            zzaw zzawVar = new zzaw();
            zzawVar.zza = this.zzc.zza.getPackageName();
            zzawVar.zzb = Long.valueOf(j);
            zzjb zzjbVarZza = zza.zza(zzfls.zza(zzawVar));
            zzjbVarZza.zza(i2);
            zzjbVarZza.zzb(i);
            zzjbVarZza.zza();
        } catch (Exception unused) {
        }
    }
}
