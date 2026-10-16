package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzams implements Runnable {
    private /* synthetic */ String zza;
    private /* synthetic */ String zzb;
    private /* synthetic */ zzamp zzc;

    zzams(zzamp zzampVar, String str, String str2) {
        this.zzc = zzampVar;
        this.zza = str;
        this.zzb = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzc.zzr != null) {
            this.zzc.zzr.zza(this.zza, this.zzb);
        }
    }
}
