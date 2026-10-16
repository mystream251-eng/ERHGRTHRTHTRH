package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;

/* JADX INFO: loaded from: classes2.dex */
final class zzhb implements zzhe {
    private /* synthetic */ Activity zza;

    zzhb(zzgw zzgwVar, Activity activity) {
        this.zza = activity;
    }

    @Override // com.google.android.gms.internal.zzhe
    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityStopped(this.zza);
    }
}
