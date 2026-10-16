package com.google.firebase.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* JADX INFO: compiled from: com.google.firebase:firebase-messaging@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
final class zzw extends BroadcastReceiver {
    final /* synthetic */ zzx zza;
    private zzx zzb;

    public zzw(zzx zzxVar, zzx zzxVar2) {
        this.zza = zzxVar;
        this.zzb = zzxVar2;
    }

    @Override // android.content.BroadcastReceiver
    public final synchronized void onReceive(Context context, Intent intent) {
        if (this.zzb == null) {
            return;
        }
        if (this.zza.zzb()) {
            return;
        }
        if (zzx.zzc()) {
            Log.d("FirebaseMessaging", "Connectivity changed. Starting background sync.");
        }
        this.zzb.zzd.zza(this.zzb, 0L);
        context.unregisterReceiver(this);
        this.zzb = null;
    }
}
