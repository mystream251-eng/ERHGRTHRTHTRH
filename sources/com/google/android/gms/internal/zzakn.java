package com.google.android.gms.internal;

import android.app.Activity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzakn {
    private final View zza;
    private Activity zzb;
    private boolean zzc;
    private boolean zzd;
    private boolean zze;
    private ViewTreeObserver.OnGlobalLayoutListener zzf;
    private ViewTreeObserver.OnScrollChangedListener zzg;

    public zzakn(Activity activity, View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        this.zzb = activity;
        this.zza = view;
        this.zzf = onGlobalLayoutListener;
        this.zzg = onScrollChangedListener;
    }

    private static ViewTreeObserver zzb(Activity activity) {
        Window window;
        View decorView;
        if (activity == null || (window = activity.getWindow()) == null || (decorView = window.getDecorView()) == null) {
            return null;
        }
        return decorView.getViewTreeObserver();
    }

    private final void zze() {
        ViewTreeObserver viewTreeObserverZzb;
        ViewTreeObserver viewTreeObserverZzb2;
        if (this.zzc) {
            return;
        }
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = this.zzf;
        if (onGlobalLayoutListener != null) {
            Activity activity = this.zzb;
            if (activity != null && (viewTreeObserverZzb2 = zzb(activity)) != null) {
                viewTreeObserverZzb2.addOnGlobalLayoutListener(onGlobalLayoutListener);
            }
            zzbt.zzz();
            zzaml.zza(this.zza, this.zzf);
        }
        ViewTreeObserver.OnScrollChangedListener onScrollChangedListener = this.zzg;
        if (onScrollChangedListener != null) {
            Activity activity2 = this.zzb;
            if (activity2 != null && (viewTreeObserverZzb = zzb(activity2)) != null) {
                viewTreeObserverZzb.addOnScrollChangedListener(onScrollChangedListener);
            }
            zzbt.zzz();
            zzaml.zza(this.zza, this.zzg);
        }
        this.zzc = true;
    }

    private final void zzf() {
        ViewTreeObserver viewTreeObserverZzb;
        ViewTreeObserver viewTreeObserverZzb2;
        Activity activity = this.zzb;
        if (activity != null && this.zzc) {
            ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = this.zzf;
            if (onGlobalLayoutListener != null && (viewTreeObserverZzb2 = zzb(activity)) != null) {
                zzbt.zzg().zza(viewTreeObserverZzb2, onGlobalLayoutListener);
            }
            ViewTreeObserver.OnScrollChangedListener onScrollChangedListener = this.zzg;
            if (onScrollChangedListener != null && (viewTreeObserverZzb = zzb(this.zzb)) != null) {
                viewTreeObserverZzb.removeOnScrollChangedListener(onScrollChangedListener);
            }
            this.zzc = false;
        }
    }

    public final void zza() {
        this.zze = true;
        if (this.zzd) {
            zze();
        }
    }

    public final void zza(Activity activity) {
        this.zzb = activity;
    }

    public final void zzb() {
        this.zze = false;
        zzf();
    }

    public final void zzc() {
        this.zzd = true;
        if (this.zze) {
            zze();
        }
    }

    public final void zzd() {
        this.zzd = false;
        zzf();
    }
}
