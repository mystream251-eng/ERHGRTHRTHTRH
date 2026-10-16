package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.js.zzc;
import com.google.android.gms.ads.internal.js.zzn;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzvf {
    private static zzajm<zzc> zza = new zzvg();
    private static zzajm<zzc> zzb = new zzvh();
    private final zzn zzc;

    public zzvf(Context context, zzala zzalaVar, String str) {
        this.zzc = new zzn(context, zzalaVar, str, zza, zzb);
    }

    public final <I, O> zzux<I, O> zza(String str, zzva<I> zzvaVar, zzuz<O> zzuzVar) {
        return new zzvi(this.zzc, str, zzvaVar, zzuzVar);
    }
}
