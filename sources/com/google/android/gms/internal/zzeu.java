package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzeu implements Callable {
    protected final zzdm zza;
    protected final zzba zzb;
    protected Method zzc;
    private String zzd = getClass().getSimpleName();
    private String zze;
    private String zzf;
    private int zzg;
    private int zzh;

    public zzeu(zzdm zzdmVar, String str, String str2, zzba zzbaVar, int i, int i2) {
        this.zza = zzdmVar;
        this.zze = str;
        this.zzf = str2;
        this.zzb = zzbaVar;
        this.zzg = i;
        this.zzh = i2;
    }

    protected abstract void zza() throws IllegalAccessException, InvocationTargetException;

    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public Void call() throws Exception {
        try {
            long jNanoTime = System.nanoTime();
            Method methodZza = this.zza.zza(this.zze, this.zzf);
            this.zzc = methodZza;
            if (methodZza == null) {
                return null;
            }
            zza();
            zzcp zzcpVarZzh = this.zza.zzh();
            if (zzcpVarZzh != null && this.zzg != Integer.MIN_VALUE) {
                zzcpVarZzh.zza(this.zzh, this.zzg, (System.nanoTime() - jNanoTime) / 1000);
            }
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
        return null;
    }
}
