package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahe;

/* JADX INFO: loaded from: classes2.dex */
final class zzbc implements Runnable {
    private /* synthetic */ zzahe zza;
    private /* synthetic */ zzbb zzb;

    zzbc(zzbb zzbbVar, zzahe zzaheVar) {
        this.zzb = zzbbVar;
        this.zza = zzaheVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzb(new zzahd(this.zza, null, null, null, null, null, null, null));
    }
}
