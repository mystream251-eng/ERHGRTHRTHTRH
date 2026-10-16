package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
final class zzajh implements Runnable {
    private /* synthetic */ zzajc zza;

    zzajh(zzajc zzajcVar) {
        this.zza = zzajcVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzbt.zzo().zza(this.zza.zza, this.zza.zzc, this.zza.zzd, this.zza.zze);
    }
}
