package com.google.android.gms.internal;

import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
final class zzaam implements ViewTreeObserver.OnScrollChangedListener {
    private /* synthetic */ WeakReference zza;
    private /* synthetic */ zzaaf zzb;

    zzaam(zzaaf zzaafVar, WeakReference weakReference) {
        this.zzb = zzaafVar;
        this.zza = weakReference;
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public final void onScrollChanged() {
        this.zzb.zza((WeakReference<zzaof>) this.zza, true);
    }
}
