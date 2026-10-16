package com.google.android.gms.internal;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
final class zzaie implements Callable<Void> {
    private /* synthetic */ Runnable zza;

    zzaie(Runnable runnable) {
        this.zza = runnable;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Void call() throws Exception {
        this.zza.run();
        return null;
    }
}
