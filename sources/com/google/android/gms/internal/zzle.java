package com.google.android.gms.internal;

import com.google.android.gms.ads.AdListener;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public class zzle extends AdListener {
    private final Object zza = new Object();
    private AdListener zzb;

    @Override // com.google.android.gms.ads.AdListener
    public void onAdClosed() {
        synchronized (this.zza) {
            if (this.zzb != null) {
                this.zzb.onAdClosed();
            }
        }
    }

    @Override // com.google.android.gms.ads.AdListener
    public void onAdFailedToLoad(int i) {
        synchronized (this.zza) {
            if (this.zzb != null) {
                this.zzb.onAdFailedToLoad(i);
            }
        }
    }

    @Override // com.google.android.gms.ads.AdListener
    public void onAdLeftApplication() {
        synchronized (this.zza) {
            if (this.zzb != null) {
                this.zzb.onAdLeftApplication();
            }
        }
    }

    @Override // com.google.android.gms.ads.AdListener
    public void onAdLoaded() {
        synchronized (this.zza) {
            if (this.zzb != null) {
                this.zzb.onAdLoaded();
            }
        }
    }

    @Override // com.google.android.gms.ads.AdListener
    public void onAdOpened() {
        synchronized (this.zza) {
            if (this.zzb != null) {
                this.zzb.onAdOpened();
            }
        }
    }

    public final void zza(AdListener adListener) {
        synchronized (this.zza) {
            this.zzb = adListener;
        }
    }
}
