package com.google.android.gms.internal;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
public final class zzef implements Callable {
    private final zzdm zza;
    private final zzba zzb;

    public zzef(zzdm zzdmVar, zzba zzbaVar) {
        this.zza = zzdmVar;
        this.zzb = zzbaVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final Void call() throws Exception {
        if (this.zza.zzl() != null) {
            this.zza.zzl().get();
        }
        zzba zzbaVarZzk = this.zza.zzk();
        if (zzbaVarZzk == null) {
            return null;
        }
        try {
            synchronized (this.zzb) {
                zzfls.zza(this.zzb, zzfls.zza(zzbaVarZzk));
            }
            return null;
        } catch (zzflr unused) {
            return null;
        }
    }
}
