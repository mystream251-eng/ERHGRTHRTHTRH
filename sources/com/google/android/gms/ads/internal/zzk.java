package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahe;

/* JADX INFO: loaded from: classes2.dex */
final class zzk implements Runnable {
    private /* synthetic */ zzahe zza;
    private /* synthetic */ zzi zzb;

    zzk(zzi zziVar, zzahe zzaheVar) {
        this.zzb = zziVar;
        this.zza = zzaheVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzb(new zzahd(this.zza, null, null, null, null, null, null, null));
    }
}
