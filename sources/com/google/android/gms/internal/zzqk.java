package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
final class zzqk implements zzpt {
    private /* synthetic */ View zza;
    private /* synthetic */ zzqj zzb;

    zzqk(zzqj zzqjVar, View view) {
        this.zzb = zzqjVar;
        this.zza = view;
    }

    @Override // com.google.android.gms.internal.zzpt
    public final void zza() {
        this.zzb.onClick(this.zza);
    }

    @Override // com.google.android.gms.internal.zzpt
    public final void zza(MotionEvent motionEvent) {
        this.zzb.onTouch(null, motionEvent);
    }
}
