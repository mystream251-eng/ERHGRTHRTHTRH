package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
final class zzbp implements View.OnTouchListener {
    private /* synthetic */ zzbn zza;

    zzbp(zzbn zzbnVar) {
        this.zza = zzbnVar;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.zza.zzh == null) {
            return false;
        }
        this.zza.zzh.zza(motionEvent);
        return false;
    }
}
