package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaeq extends zzaey {
    private final Context zza;
    private final Object zzb;
    private final zzala zzc;
    private final zzaer zzd;

    public zzaeq(Context context, zzv zzvVar, zzwf zzwfVar, zzala zzalaVar) {
        this(context, zzalaVar, new zzaer(context, zzvVar, zzko.zza(), zzwfVar, zzalaVar));
    }

    private zzaeq(Context context, zzala zzalaVar, zzaer zzaerVar) {
        this.zzb = new Object();
        this.zza = context;
        this.zzc = zzalaVar;
        this.zzd = zzaerVar;
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zza() {
        synchronized (this.zzb) {
            this.zzd.zzar();
        }
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zza(IObjectWrapper iObjectWrapper) {
        synchronized (this.zzb) {
            this.zzd.zzh();
        }
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zza(zzafc zzafcVar) {
        synchronized (this.zzb) {
            this.zzd.zza(zzafcVar);
        }
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zza(zzafi zzafiVar) {
        synchronized (this.zzb) {
            this.zzd.zza(zzafiVar);
        }
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zza(String str) {
        synchronized (this.zzb) {
            this.zzd.zza(str);
        }
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zza(boolean z) {
        synchronized (this.zzb) {
            this.zzd.zzb(z);
        }
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zzb(IObjectWrapper iObjectWrapper) {
        Context context;
        synchronized (this.zzb) {
            if (iObjectWrapper == null) {
                context = null;
            } else {
                try {
                    context = (Context) zzn.zza(iObjectWrapper);
                } catch (Exception e) {
                    zzahw.zzc("Unable to extract updated context.", e);
                }
            }
            if (context != null) {
                this.zzd.zza(context);
            }
            this.zzd.zzi();
        }
    }

    @Override // com.google.android.gms.internal.zzaex
    public final boolean zzb() {
        boolean zZzas;
        synchronized (this.zzb) {
            zZzas = this.zzd.zzas();
        }
        return zZzas;
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zzc() {
        zza((IObjectWrapper) null);
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zzc(IObjectWrapper iObjectWrapper) {
        synchronized (this.zzb) {
            this.zzd.zzb();
        }
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zzd() {
        zzb(null);
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zze() {
        zzc(null);
    }

    @Override // com.google.android.gms.internal.zzaex
    public final String zzf() {
        String strZzal;
        synchronized (this.zzb) {
            strZzal = this.zzd.zzal();
        }
        return strZzal;
    }
}
