package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;

/* JADX INFO: loaded from: classes2.dex */
final class zzgz implements zzhe {
    private /* synthetic */ Activity zza;

    zzgz(zzgw zzgwVar, Activity activity) {
        this.zza = activity;
    }

    @Override // com.google.android.gms.internal.zzhe
    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityResumed(this.zza);
    }
}
