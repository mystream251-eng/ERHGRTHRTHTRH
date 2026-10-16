package com.google.android.gms.internal;

import android.content.Context;
import android.view.ViewGroup;
import com.google.android.gms.common.internal.zzbq;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzane {
    private final Context zza;
    private final zzann zzb;
    private final ViewGroup zzc;
    private zzanb zzd;

    private zzane(Context context, ViewGroup viewGroup, zzann zzannVar, zzanb zzanbVar) {
        this.zza = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzc = viewGroup;
        this.zzb = zzannVar;
        this.zzd = null;
    }

    public zzane(Context context, ViewGroup viewGroup, zzaof zzaofVar) {
        this(context, viewGroup, zzaofVar, null);
    }

    public final zzanb zza() {
        zzbq.zzb("getAdVideoUnderlay must be called from the UI thread.");
        return this.zzd;
    }

    public final void zza(int i, int i2, int i3, int i4) {
        zzbq.zzb("The underlay may only be modified from the UI thread.");
        zzanb zzanbVar = this.zzd;
        if (zzanbVar != null) {
            zzanbVar.zza(i, i2, i3, i4);
        }
    }

    public final void zza(int i, int i2, int i3, int i4, int i5, boolean z, zzanm zzanmVar) {
        if (this.zzd != null) {
            return;
        }
        zzoo.zza(this.zzb.zzh().zza(), this.zzb.zzc(), "vpr2");
        Context context = this.zza;
        zzann zzannVar = this.zzb;
        zzanb zzanbVar = new zzanb(context, zzannVar, i5, z, zzannVar.zzh().zza(), zzanmVar);
        this.zzd = zzanbVar;
        this.zzc.addView(zzanbVar, 0, new ViewGroup.LayoutParams(-1, -1));
        this.zzd.zza(i, i2, i3, i4);
        this.zzb.zza(false);
    }

    public final void zzb() {
        zzbq.zzb("onPause must be called from the UI thread.");
        zzanb zzanbVar = this.zzd;
        if (zzanbVar != null) {
            zzanbVar.zzi();
        }
    }

    public final void zzc() {
        zzbq.zzb("onDestroy must be called from the UI thread.");
        zzanb zzanbVar = this.zzd;
        if (zzanbVar != null) {
            zzanbVar.zzn();
            this.zzc.removeView(this.zzd);
            this.zzd = null;
        }
    }
}
