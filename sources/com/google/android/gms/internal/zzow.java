package com.google.android.gms.internal;

import android.view.View;
import com.google.android.gms.ads.internal.zzae;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzow extends zzoz {
    private final zzae zza;
    private final String zzb;
    private final String zzc;

    public zzow(zzae zzaeVar, String str, String str2) {
        this.zza = zzaeVar;
        this.zzb = str;
        this.zzc = str2;
    }

    @Override // com.google.android.gms.internal.zzoy
    public final String zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.zzoy
    public final void zza(IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper == null) {
            return;
        }
        this.zza.zzb((View) zzn.zza(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.zzoy
    public final String zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.zzoy
    public final void zzc() {
        this.zza.zzaq();
    }

    @Override // com.google.android.gms.internal.zzoy
    public final void zzd() {
        this.zza.zzar();
    }
}
