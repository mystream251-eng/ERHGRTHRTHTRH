package com.google.android.gms.ads.internal.js;

import com.google.android.gms.internal.zzamh;
import com.google.android.gms.internal.zzamj;

/* JADX INFO: loaded from: classes2.dex */
public final class zzaa extends zzamj<zzaj> {
    private final Object zza = new Object();
    private final zzae zzb;
    private boolean zzc;

    public zzaa(zzae zzaeVar) {
        this.zzb = zzaeVar;
    }

    public final void zza() {
        synchronized (this.zza) {
            if (this.zzc) {
                return;
            }
            this.zzc = true;
            zza(new zzab(this), new zzamh());
            zza(new zzac(this), new zzad(this));
        }
    }
}
