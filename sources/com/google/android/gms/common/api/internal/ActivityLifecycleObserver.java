package com.google.android.gms.common.api.internal;

import android.app.Activity;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityLifecycleObserver {
    public abstract ActivityLifecycleObserver onStopCallOnce(Runnable runnable);

    public static final ActivityLifecycleObserver of(Activity activity) {
        return new zaa(activity);
    }
}
