package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.util.zzj;

/* JADX INFO: loaded from: classes2.dex */
final class zzabw implements zzaby {
    private /* synthetic */ Context zza;

    zzabw(Context context) {
        this.zza = context;
    }

    @Override // com.google.android.gms.internal.zzaby
    public final boolean zza(zzala zzalaVar) {
        zzlc.zza();
        boolean zZzc = zzako.zzc(this.zza);
        boolean z = ((Boolean) zzlc.zzf().zza(zzoi.zzgr)).booleanValue() && zzalaVar.zzd;
        if (zzabv.zzb(this.zza, zzalaVar.zzd) && zZzc && !z) {
            if (zzj.zzc(this.zza)) {
                if (!((Boolean) zzlc.zzf().zza(zzoi.zzad)).booleanValue()) {
                }
            }
            return false;
        }
        return true;
    }
}
