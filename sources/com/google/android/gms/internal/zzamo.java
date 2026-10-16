package com.google.android.gms.internal;

import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
abstract class zzamo {
    private final WeakReference<View> zza;

    public zzamo(View view) {
        this.zza = new WeakReference<>(view);
    }

    private final ViewTreeObserver zzc() {
        ViewTreeObserver viewTreeObserver;
        View view = this.zza.get();
        if (view == null || (viewTreeObserver = view.getViewTreeObserver()) == null || !viewTreeObserver.isAlive()) {
            return null;
        }
        return viewTreeObserver;
    }

    public final void zza() {
        ViewTreeObserver viewTreeObserverZzc = zzc();
        if (viewTreeObserverZzc != null) {
            zza(viewTreeObserverZzc);
        }
    }

    protected abstract void zza(ViewTreeObserver viewTreeObserver);

    public final void zzb() {
        ViewTreeObserver viewTreeObserverZzc = zzc();
        if (viewTreeObserverZzc != null) {
            zzb(viewTreeObserverZzc);
        }
    }

    protected abstract void zzb(ViewTreeObserver viewTreeObserver);
}
