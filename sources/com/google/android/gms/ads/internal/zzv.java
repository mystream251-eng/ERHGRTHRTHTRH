package com.google.android.gms.ads.internal;

import android.content.Context;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzagl;
import com.google.android.gms.internal.zzagm;
import com.google.android.gms.internal.zzagr;
import com.google.android.gms.internal.zzana;
import com.google.android.gms.internal.zzanf;
import com.google.android.gms.internal.zzans;
import com.google.android.gms.internal.zzaod;
import com.google.android.gms.internal.zziz;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzv {
    public final zzaod zza;
    public final zzana zzb;
    public final zzagr zzc;
    public final zziz zzd;

    private zzv(zzaod zzaodVar, zzana zzanaVar, zzagr zzagrVar, zziz zzizVar) {
        this.zza = zzaodVar;
        this.zzb = zzanaVar;
        this.zzc = zzagrVar;
        this.zzd = zzizVar;
    }

    public static zzv zza(Context context) {
        return new zzv(new zzans(), new zzanf(), new zzagl(new zzagm()), new zziz(context));
    }
}
