package com.google.android.gms.internal;

import android.content.Context;
import android.view.TextureView;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public abstract class zzamz extends TextureView implements zzanr {
    protected final zzang zza;
    protected final zzanq zzb;

    public zzamz(Context context) {
        super(context);
        this.zza = new zzang();
        this.zzb = new zzanq(context, this);
    }

    public abstract String zza();

    public abstract void zza(float f, float f2);

    public abstract void zza(int i);

    public abstract void zza(zzamy zzamyVar);

    public abstract void zza(String str);

    public abstract void zzb();

    public abstract void zzc();

    public abstract void zzd();

    public abstract int zze();

    public abstract int zzf();

    public abstract int zzg();

    public abstract int zzh();

    public abstract void zzi();
}
