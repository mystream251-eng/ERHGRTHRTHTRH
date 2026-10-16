package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes2.dex */
final class zzakl extends BroadcastReceiver {
    private /* synthetic */ zzakk zza;

    zzakl(zzakk zzakkVar) {
        this.zza = zzakkVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        this.zza.zza(context, intent);
    }
}
