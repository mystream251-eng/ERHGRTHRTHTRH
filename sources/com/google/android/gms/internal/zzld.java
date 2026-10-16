package com.google.android.gms.internal;

import java.util.Random;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzld extends zzmf {
    private long zzb;
    private Object zzc = new Object();
    private final Random zza = new Random();

    public zzld() {
        zza();
    }

    public final void zza() {
        synchronized (this.zzc) {
            int i = 3;
            long jNextInt = 0;
            while (true) {
                i--;
                if (i <= 0) {
                    break;
                }
                jNextInt = ((long) this.zza.nextInt()) + 2147483648L;
                if (jNextInt != this.zzb && jNextInt != 0) {
                    break;
                }
            }
            this.zzb = jNextInt;
        }
    }

    @Override // com.google.android.gms.internal.zzme
    public final long zzb() {
        return this.zzb;
    }
}
