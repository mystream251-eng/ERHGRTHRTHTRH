package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes2.dex */
final class zzgt extends BroadcastReceiver {
    private /* synthetic */ zzgr zza;

    zzgt(zzgr zzgrVar) {
        this.zza = zzgrVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        this.zza.zza(3);
    }
}
