package com.google.android.gms.internal;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.ads.doubleclick.CustomRenderedAd;
import com.google.android.gms.dynamic.zzn;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzox implements CustomRenderedAd {
    private final zzoy zza;

    public zzox(zzoy zzoyVar) {
        this.zza = zzoyVar;
    }

    @Override // com.google.android.gms.ads.doubleclick.CustomRenderedAd
    public final String getBaseUrl() {
        try {
            return this.zza.zza();
        } catch (RemoteException e) {
            zzaky.zzc("Could not delegate getBaseURL to CustomRenderedAd", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.doubleclick.CustomRenderedAd
    public final String getContent() {
        try {
            return this.zza.zzb();
        } catch (RemoteException e) {
            zzaky.zzc("Could not delegate getContent to CustomRenderedAd", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.doubleclick.CustomRenderedAd
    public final void onAdRendered(View view) {
        try {
            this.zza.zza(view != null ? zzn.zza(view) : null);
        } catch (RemoteException e) {
            zzaky.zzc("Could not delegate onAdRendered to CustomRenderedAd", e);
        }
    }

    @Override // com.google.android.gms.ads.doubleclick.CustomRenderedAd
    public final void recordClick() {
        try {
            this.zza.zzc();
        } catch (RemoteException e) {
            zzaky.zzc("Could not delegate recordClick to CustomRenderedAd", e);
        }
    }

    @Override // com.google.android.gms.ads.doubleclick.CustomRenderedAd
    public final void recordImpression() {
        try {
            this.zza.zzd();
        } catch (RemoteException e) {
            zzaky.zzc("Could not delegate recordImpression to CustomRenderedAd", e);
        }
    }
}
