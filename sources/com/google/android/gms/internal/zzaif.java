package com.google.android.gms.internal;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
final class zzaif implements Callable<Void> {
    private /* synthetic */ Runnable zza;

    zzaif(Runnable runnable) {
        this.zza = runnable;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Void call() throws Exception {
        this.zza.run();
        return null;
    }
}
