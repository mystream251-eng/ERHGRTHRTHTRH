package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzyr;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzs extends zzyr {
    private AdOverlayInfoParcel zza;
    private Activity zzb;
    private boolean zzc = false;
    private boolean zzd = false;

    public zzs(Activity activity, AdOverlayInfoParcel adOverlayInfoParcel) {
        this.zza = adOverlayInfoParcel;
        this.zzb = activity;
    }

    private final synchronized void zza() {
        if (!this.zzd) {
            if (this.zza.zzc != null) {
                this.zza.zzc.zzz();
            }
            this.zzd = true;
        }
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zza(int i, int i2, Intent intent) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zza(Bundle bundle) {
        boolean z = bundle != null ? bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false) : false;
        AdOverlayInfoParcel adOverlayInfoParcel = this.zza;
        if (adOverlayInfoParcel == null || z) {
            this.zzb.finish();
            return;
        }
        if (bundle == null) {
            if (adOverlayInfoParcel.zzb != null) {
                this.zza.zzb.onAdClicked();
            }
            if (this.zzb.getIntent() != null && this.zzb.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true) && this.zza.zzc != null) {
                this.zza.zzc.zzaa();
            }
        }
        zzbt.zzb();
        if (zza.zza(this.zzb, this.zza.zza, this.zza.zzi)) {
            return;
        }
        this.zzb.finish();
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zza(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzb(Bundle bundle) throws RemoteException {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzc);
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzd() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.zzyq
    public final boolean zze() throws RemoteException {
        return false;
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzf() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzg() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzh() throws RemoteException {
        if (this.zzc) {
            this.zzb.finish();
            return;
        }
        this.zzc = true;
        if (this.zza.zzc != null) {
            this.zza.zzc.zzac();
        }
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzi() throws RemoteException {
        if (this.zza.zzc != null) {
            this.zza.zzc.zzab();
        }
        if (this.zzb.isFinishing()) {
            zza();
        }
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzj() throws RemoteException {
        if (this.zzb.isFinishing()) {
            zza();
        }
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzk() throws RemoteException {
        if (this.zzb.isFinishing()) {
            zza();
        }
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzl() throws RemoteException {
    }
}
