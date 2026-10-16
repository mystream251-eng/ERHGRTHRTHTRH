package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
final class zzoj implements Callable<Void> {
    private /* synthetic */ Context zza;

    zzoj(Context context) {
        this.zza = context;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Void call() throws Exception {
        zzlc.zzf().zza(this.zza);
        return null;
    }
}
