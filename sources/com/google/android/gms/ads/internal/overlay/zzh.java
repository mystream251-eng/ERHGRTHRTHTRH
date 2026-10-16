package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzajc;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
final class zzh extends RelativeLayout {
    boolean zza;
    private zzajc zzb;

    public zzh(Context context, String str, String str2) {
        super(context);
        zzajc zzajcVar = new zzajc(context, str);
        this.zzb = zzajcVar;
        zzajcVar.zzb(str2);
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.zza) {
            return false;
        }
        this.zzb.zza(motionEvent);
        return false;
    }
}
