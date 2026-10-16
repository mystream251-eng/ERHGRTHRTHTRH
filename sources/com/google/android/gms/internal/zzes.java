package com.google.android.gms.internal;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class zzes {
    private static String zza = zzes.class.getSimpleName();
    private final zzdm zzb;
    private final String zzc;
    private final String zzd;
    private final Class<?>[] zzg;
    private final int zze = 2;
    private volatile Method zzf = null;
    private CountDownLatch zzh = new CountDownLatch(1);

    public zzes(zzdm zzdmVar, String str, String str2, Class<?>... clsArr) {
        this.zzb = zzdmVar;
        this.zzc = str;
        this.zzd = str2;
        this.zzg = clsArr;
        zzdmVar.zzc().submit(new zzet(this));
    }

    private final String zza(byte[] bArr, String str) throws zzcy, UnsupportedEncodingException {
        return new String(this.zzb.zze().zza(bArr, str), "UTF-8");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb() {
        try {
            try {
                Class clsLoadClass = this.zzb.zzd().loadClass(zza(this.zzb.zzf(), this.zzc));
                if (clsLoadClass != null) {
                    this.zzf = clsLoadClass.getMethod(zza(this.zzb.zzf(), this.zzd), this.zzg);
                    Method method = this.zzf;
                }
            } catch (zzcy | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException unused) {
            }
        } finally {
            this.zzh.countDown();
        }
    }

    public final Method zza() {
        if (this.zzf != null) {
            return this.zzf;
        }
        try {
            if (this.zzh.await(2L, TimeUnit.SECONDS)) {
                return this.zzf;
            }
            return null;
        } catch (InterruptedException unused) {
            return null;
        }
    }
}
