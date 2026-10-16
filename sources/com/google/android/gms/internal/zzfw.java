package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes2.dex */
final class zzfw extends BroadcastReceiver {
    private /* synthetic */ zzfv zza;

    zzfw(zzfv zzfvVar) {
        this.zza = zzfvVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        this.zza.zza(3);
    }
}
