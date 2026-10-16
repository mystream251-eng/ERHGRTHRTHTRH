package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
final class zzcdb implements zzf, zzg {
    private zzcdc zza;
    private final String zzb;
    private final String zzc;
    private final LinkedBlockingQueue<zzba> zzd;
    private final HandlerThread zze;

    public zzcdb(Context context, String str, String str2) {
        this.zzb = str;
        this.zzc = str2;
        HandlerThread handlerThread = new HandlerThread("GassClient");
        this.zze = handlerThread;
        handlerThread.start();
        this.zza = new zzcdc(context, this.zze.getLooper(), this, this);
        this.zzd = new LinkedBlockingQueue<>();
        this.zza.zzz();
    }

    private final zzcdh zza() {
        try {
            return this.zza.zzi();
        } catch (DeadObjectException | IllegalStateException unused) {
            return null;
        }
    }

    private final void zzb() {
        zzcdc zzcdcVar = this.zza;
        if (zzcdcVar != null) {
            if (zzcdcVar.zzs() || this.zza.zzt()) {
                this.zza.zzg();
            }
        }
    }

    private static zzba zzc() {
        zzba zzbaVar = new zzba();
        zzbaVar.zzk = Long.valueOf(PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID);
        return zzbaVar;
    }

    public final void zza(int i) {
        try {
            this.zzd.put(zzc());
        } catch (InterruptedException unused) {
        }
    }

    public final void zza(Bundle bundle) {
        zzcdh zzcdhVarZza = zza();
        if (zzcdhVarZza != null) {
            try {
                try {
                    this.zzd.put(zzcdhVarZza.zza(new zzcdd(this.zzb, this.zzc)).zza());
                } catch (Throwable unused) {
                    this.zzd.put(zzc());
                }
            } catch (InterruptedException unused2) {
            } finally {
                zzb();
                this.zze.quit();
            }
        }
    }

    public final void zza(ConnectionResult connectionResult) {
        try {
            this.zzd.put(zzc());
        } catch (InterruptedException unused) {
        }
    }

    public final zzba zzb(int i) {
        zzba zzbaVarPoll;
        try {
            zzbaVarPoll = this.zzd.poll(5000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            zzbaVarPoll = null;
        }
        return zzbaVarPoll == null ? zzc() : zzbaVarPoll;
    }
}
