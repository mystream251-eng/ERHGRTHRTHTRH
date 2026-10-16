package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
final class zzqn implements zzpt {
    private /* synthetic */ View zza;
    private /* synthetic */ zzql zzb;

    zzqn(zzql zzqlVar, View view) {
        this.zzb = zzqlVar;
        this.zza = view;
    }

    @Override // com.google.android.gms.internal.zzpt
    public final void zza() {
        if (this.zzb.zza(zzql.zza)) {
            this.zzb.onClick(this.zza);
        }
    }

    @Override // com.google.android.gms.internal.zzpt
    public final void zza(MotionEvent motionEvent) {
        this.zzb.onTouch(null, motionEvent);
    }
}
