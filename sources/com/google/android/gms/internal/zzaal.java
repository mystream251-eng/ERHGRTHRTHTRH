package com.google.android.gms.internal;

import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
final class zzaal implements ViewTreeObserver.OnGlobalLayoutListener {
    private /* synthetic */ WeakReference zza;
    private /* synthetic */ zzaaf zzb;

    zzaal(zzaaf zzaafVar, WeakReference weakReference) {
        this.zzb = zzaafVar;
        this.zza = weakReference;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        this.zzb.zza((WeakReference<zzaof>) this.zza, false);
    }
}
