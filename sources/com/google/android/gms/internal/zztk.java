package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzak;
import com.google.android.gms.ads.internal.zzv;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zztk {
    private final Context zza;
    private final zzwf zzb;
    private final zzala zzc;
    private final zzv zzd;

    zztk(Context context, zzwf zzwfVar, zzala zzalaVar, zzv zzvVar) {
        this.zza = context;
        this.zzb = zzwfVar;
        this.zzc = zzalaVar;
        this.zzd = zzvVar;
    }

    public final Context zza() {
        return this.zza.getApplicationContext();
    }

    public final zzak zza(String str) {
        return new zzak(this.zza, new zzko(), str, this.zzb, this.zzc, this.zzd);
    }

    public final zzak zzb(String str) {
        return new zzak(this.zza.getApplicationContext(), new zzko(), str, this.zzb, this.zzc, this.zzd);
    }

    public final zztk zzb() {
        return new zztk(this.zza.getApplicationContext(), this.zzb, this.zzc, this.zzd);
    }
}
