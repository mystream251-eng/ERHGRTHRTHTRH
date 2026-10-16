package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;

/* JADX INFO: loaded from: classes2.dex */
final class zzdd implements zzdi {
    private /* synthetic */ Activity zza;

    zzdd(zzda zzdaVar, Activity activity) {
        this.zza = activity;
    }

    @Override // com.google.android.gms.internal.zzdi
    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityResumed(this.zza);
    }
}
