package com.google.android.gms.internal;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaft extends zzaga {
    private volatile zzafr zza;
    private volatile zzafu zzb;
    private volatile zzafs zzc;

    public zzaft(zzafs zzafsVar) {
        this.zzc = zzafsVar;
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zza(IObjectWrapper iObjectWrapper) {
        if (this.zza != null) {
            this.zza.zzg();
        }
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zza(IObjectWrapper iObjectWrapper, int i) {
        if (this.zza != null) {
            this.zza.zza(i);
        }
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zza(IObjectWrapper iObjectWrapper, zzagd zzagdVar) {
        if (this.zzc != null) {
            this.zzc.a_(zzagdVar);
        }
    }

    public final void zza(zzafr zzafrVar) {
        this.zza = zzafrVar;
    }

    public final void zza(zzafu zzafuVar) {
        this.zzb = zzafuVar;
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zzb(IObjectWrapper iObjectWrapper) {
        if (this.zzb != null) {
            this.zzb.zza(zzn.zza(iObjectWrapper).getClass().getName());
        }
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zzb(IObjectWrapper iObjectWrapper, int i) {
        if (this.zzb != null) {
            this.zzb.zza(zzn.zza(iObjectWrapper).getClass().getName(), i);
        }
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zzc(IObjectWrapper iObjectWrapper) {
        if (this.zzc != null) {
            this.zzc.f_();
        }
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zzd(IObjectWrapper iObjectWrapper) {
        if (this.zzc != null) {
            this.zzc.g_();
        }
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zze(IObjectWrapper iObjectWrapper) {
        if (this.zzc != null) {
            this.zzc.i_();
        }
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zzf(IObjectWrapper iObjectWrapper) {
        if (this.zzc != null) {
            this.zzc.j_();
        }
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zzg(IObjectWrapper iObjectWrapper) {
        if (this.zzc != null) {
            this.zzc.k_();
        }
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zzh(IObjectWrapper iObjectWrapper) {
        if (this.zzc != null) {
            this.zzc.h_();
        }
    }
}
