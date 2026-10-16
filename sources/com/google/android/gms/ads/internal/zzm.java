package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzahd;

/* JADX INFO: loaded from: classes2.dex */
final class zzm implements Runnable {
    private /* synthetic */ zzl zza;

    zzm(zzl zzlVar) {
        this.zza = zzlVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzc.zzb(new zzahd(this.zza.zza, null, null, null, null, null, null, null));
    }
}
