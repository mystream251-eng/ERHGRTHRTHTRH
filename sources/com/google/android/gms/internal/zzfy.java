package com.google.android.gms.internal;

import android.database.ContentObserver;
import android.os.Handler;

/* JADX INFO: loaded from: classes2.dex */
final class zzfy extends ContentObserver {
    private /* synthetic */ zzfv zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfy(zzfv zzfvVar, Handler handler) {
        super(handler);
        this.zza = zzfvVar;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        super.onChange(z);
        this.zza.zza();
    }
}
