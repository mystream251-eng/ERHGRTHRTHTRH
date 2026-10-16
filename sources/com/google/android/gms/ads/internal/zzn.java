package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
final class zzn implements View.OnTouchListener {
    private /* synthetic */ zzw zza;
    private /* synthetic */ zzl zzb;

    zzn(zzl zzlVar, zzw zzwVar) {
        this.zzb = zzlVar;
        this.zza = zzwVar;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        this.zza.zza();
        if (this.zzb.zzb == null) {
            return false;
        }
        this.zzb.zzb.zzc();
        return false;
    }
}
