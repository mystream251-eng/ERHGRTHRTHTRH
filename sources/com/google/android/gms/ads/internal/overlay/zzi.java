package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzaof;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzi {
    public final int zza;
    public final ViewGroup.LayoutParams zzb;
    public final ViewGroup zzc;
    public final Context zzd;

    public zzi(zzaof zzaofVar) throws zzg {
        this.zzb = zzaofVar.getLayoutParams();
        ViewParent parent = zzaofVar.getParent();
        this.zzd = zzaofVar.zzq();
        if (parent == null || !(parent instanceof ViewGroup)) {
            throw new zzg("Could not get the parent of the WebView for an overlay.");
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        this.zzc = viewGroup;
        this.zza = viewGroup.indexOfChild(zzaofVar.zzm());
        this.zzc.removeView(zzaofVar.zzm());
        zzaofVar.zzb(true);
    }
}
