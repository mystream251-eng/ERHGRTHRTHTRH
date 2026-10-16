package com.google.android.gms.internal;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzuy {
    private final Object zza = new Object();
    private zzvf zzb;

    public final zzvf zza(Context context, zzala zzalaVar) {
        zzvf zzvfVar;
        synchronized (this.zza) {
            if (this.zzb == null) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    context = applicationContext;
                }
                this.zzb = new zzvf(context, zzalaVar, (String) zzlc.zzf().zza(zzoi.zza));
            }
            zzvfVar = this.zzb;
        }
        return zzvfVar;
    }
}
