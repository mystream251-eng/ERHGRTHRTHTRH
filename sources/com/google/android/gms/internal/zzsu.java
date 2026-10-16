package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

/* JADX INFO: loaded from: classes2.dex */
public final class zzsu extends zzsf {
    private final OnPublisherAdViewLoadedListener zza;

    public zzsu(OnPublisherAdViewLoadedListener onPublisherAdViewLoadedListener) {
        this.zza = onPublisherAdViewLoadedListener;
    }

    @Override // com.google.android.gms.internal.zzse
    public final void zza(zzlt zzltVar, IObjectWrapper iObjectWrapper) {
        if (zzltVar == null || iObjectWrapper == null) {
            return;
        }
        PublisherAdView publisherAdView = new PublisherAdView((Context) zzn.zza(iObjectWrapper));
        try {
            if (zzltVar.zzx() instanceof zzkh) {
                zzkh zzkhVar = (zzkh) zzltVar.zzx();
                publisherAdView.setAdListener(zzkhVar != null ? zzkhVar.zzg() : null);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Failed to get ad listener.", e);
        }
        try {
            if (zzltVar.zzw() instanceof zzkq) {
                zzkq zzkqVar = (zzkq) zzltVar.zzw();
                publisherAdView.setAppEventListener(zzkqVar != null ? zzkqVar.zza() : null);
            }
        } catch (RemoteException e2) {
            zzaky.zzc("Failed to get app event listener.", e2);
        }
        zzako.zza.post(new zzsv(this, publisherAdView, zzltVar));
    }
}
