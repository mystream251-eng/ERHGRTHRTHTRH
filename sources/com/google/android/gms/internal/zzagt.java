package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzagt implements zzgv {
    private final Context zza;
    private final Object zzb;
    private String zzc;
    private boolean zzd;

    public zzagt(Context context, String str) {
        this.zza = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzc = str;
        this.zzd = false;
        this.zzb = new Object();
    }

    @Override // com.google.android.gms.internal.zzgv
    public final void zza(zzgu zzguVar) {
        zza(zzguVar.zza);
    }

    public final void zza(String str) {
        this.zzc = str;
    }

    public final void zza(boolean z) {
        if (zzbt.zzaa().zza(this.zza)) {
            synchronized (this.zzb) {
                if (this.zzd == z) {
                    return;
                }
                this.zzd = z;
                if (TextUtils.isEmpty(this.zzc)) {
                    return;
                }
                if (this.zzd) {
                    zzbt.zzaa().zza(this.zza, this.zzc);
                } else {
                    zzbt.zzaa().zzb(this.zza, this.zzc);
                }
            }
        }
    }
}
