package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzake {
    private long zza;
    private long zzb = Long.MIN_VALUE;
    private Object zzc = new Object();

    public zzake(long j) {
        this.zza = j;
    }

    public final boolean zza() {
        synchronized (this.zzc) {
            long jZzb = zzbt.zzl().zzb();
            if (this.zzb + this.zza > jZzb) {
                return false;
            }
            this.zzb = jZzb;
            return true;
        }
    }
}
