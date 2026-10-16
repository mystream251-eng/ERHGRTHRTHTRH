package com.google.android.gms.internal;

import android.graphics.SurfaceTexture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzang {
    private long zzb;
    private final long zza = TimeUnit.MILLISECONDS.toNanos(((Long) zzlc.zzf().zza(zzoi.zzt)).longValue());
    private boolean zzc = true;

    zzang() {
    }

    public final void zza() {
        this.zzc = true;
    }

    public final void zza(SurfaceTexture surfaceTexture, zzamy zzamyVar) {
        if (zzamyVar == null) {
            return;
        }
        long timestamp = surfaceTexture.getTimestamp();
        if (this.zzc || Math.abs(timestamp - this.zzb) >= this.zza) {
            this.zzc = false;
            this.zzb = timestamp;
            zzaij.zza.post(new zzanh(this, zzamyVar));
        }
    }
}
