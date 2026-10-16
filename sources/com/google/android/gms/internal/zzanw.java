package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
final class zzanw implements Runnable {
    private /* synthetic */ zzanv zza;

    zzanw(zzanv zzanvVar) {
        this.zza = zzanvVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzbt.zzy().zzb(this.zza);
    }
}
