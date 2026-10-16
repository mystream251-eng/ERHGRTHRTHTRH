package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzzr extends zzzo {
    private Object zzd;
    private PopupWindow zze;
    private boolean zzf;

    zzzr(Context context, zzahe zzaheVar, zzaof zzaofVar, zzzn zzznVar) {
        super(context, zzaheVar, zzaofVar, zzznVar);
        this.zzd = new Object();
        this.zzf = false;
    }

    private final void zze() {
        synchronized (this.zzd) {
            this.zzf = true;
            if ((this.zza instanceof Activity) && ((Activity) this.zza).isDestroyed()) {
                this.zze = null;
            }
            if (this.zze != null) {
                if (this.zze.isShowing()) {
                    this.zze.dismiss();
                }
                this.zze = null;
            }
        }
    }

    @Override // com.google.android.gms.internal.zzzg
    protected final void zza(int i) {
        zze();
        super.zza(i);
    }

    @Override // com.google.android.gms.internal.zzzg, com.google.android.gms.internal.zzajb
    public final void zzb() {
        zze();
        super.zzb();
    }

    @Override // com.google.android.gms.internal.zzzo
    protected final void zzd() {
        Window window = this.zza instanceof Activity ? ((Activity) this.zza).getWindow() : null;
        if (window == null || window.getDecorView() == null || ((Activity) this.zza).isDestroyed()) {
            return;
        }
        FrameLayout frameLayout = new FrameLayout(this.zza);
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        frameLayout.addView(this.zzb.zzm(), -1, -1);
        synchronized (this.zzd) {
            if (this.zzf) {
                return;
            }
            PopupWindow popupWindow = new PopupWindow((View) frameLayout, 1, 1, false);
            this.zze = popupWindow;
            popupWindow.setOutsideTouchable(true);
            this.zze.setClippingEnabled(false);
            zzahw.zzb("Displaying the 1x1 popup off the screen.");
            try {
                this.zze.showAtLocation(window.getDecorView(), 0, -1, -1);
            } catch (Exception unused) {
                this.zze = null;
            }
        }
    }
}
