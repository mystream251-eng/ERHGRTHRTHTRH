package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes2.dex */
final class zzdw extends BroadcastReceiver {
    private /* synthetic */ zzdu zza;

    zzdw(zzdu zzduVar) {
        this.zza = zzduVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        this.zza.zzc();
    }
}
