package com.google.android.gms.internal;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaml {
    public static void zza(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        new zzamm(view, onGlobalLayoutListener).zza();
    }

    public static void zza(View view, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        new zzamn(view, onScrollChangedListener).zza();
    }
}
