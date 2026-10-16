package com.google.android.gms.internal;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
final class zzaoj implements View.OnAttachStateChangeListener {
    private /* synthetic */ zzagq zza;
    private /* synthetic */ zzaog zzb;

    zzaoj(zzaog zzaogVar, zzagq zzagqVar) {
        this.zzb = zzaogVar;
        this.zza = zzagqVar;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.zzb.zza(view, this.zza, 10);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }
}
