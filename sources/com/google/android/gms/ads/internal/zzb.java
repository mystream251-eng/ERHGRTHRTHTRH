package com.google.android.gms.ads.internal;

import android.os.Debug;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes2.dex */
final class zzb extends TimerTask {
    private /* synthetic */ CountDownLatch zza;
    private /* synthetic */ Timer zzb;
    private /* synthetic */ zza zzc;

    zzb(zza zzaVar, CountDownLatch countDownLatch, Timer timer) {
        this.zzc = zzaVar;
        this.zza = countDownLatch;
        this.zzb = timer;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        if (((Integer) zzlc.zzf().zza(zzoi.zzff)).intValue() != this.zza.getCount()) {
            zzahw.zzb("Stopping method tracing");
            Debug.stopMethodTracing();
            if (this.zza.getCount() == 0) {
                this.zzb.cancel();
                return;
            }
        }
        String strConcat = String.valueOf(this.zzc.zze.zzc.getPackageName()).concat("_adsTrace_");
        try {
            zzahw.zzb("Starting method tracing");
            this.zza.countDown();
            long jZza = zzbt.zzl().zza();
            StringBuilder sb = new StringBuilder(String.valueOf(strConcat).length() + 20);
            sb.append(strConcat);
            sb.append(jZza);
            Debug.startMethodTracing(sb.toString(), ((Integer) zzlc.zzf().zza(zzoi.zzfg)).intValue());
        } catch (Exception e) {
            zzahw.zzc("Exception occurred while starting method tracing.", e);
        }
    }
}
