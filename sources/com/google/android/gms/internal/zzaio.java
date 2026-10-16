package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes2.dex */
final class zzaio extends BroadcastReceiver {
    private /* synthetic */ zzaij zza;

    private zzaio(zzaij zzaijVar) {
        this.zza = zzaijVar;
    }

    /* synthetic */ zzaio(zzaij zzaijVar, zzaik zzaikVar) {
        this(zzaijVar);
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
            this.zza.zzc = true;
        } else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
            this.zza.zzc = false;
        }
    }
}
