package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.PublisherAdView;

/* JADX INFO: loaded from: classes2.dex */
final class zzsv implements Runnable {
    private /* synthetic */ PublisherAdView zza;
    private /* synthetic */ zzlt zzb;
    private /* synthetic */ zzsu zzc;

    zzsv(zzsu zzsuVar, PublisherAdView publisherAdView, zzlt zzltVar) {
        this.zzc = zzsuVar;
        this.zza = publisherAdView;
        this.zzb = zzltVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.zza(this.zzb)) {
            this.zzc.zza.onPublisherAdViewLoaded(this.zza);
        } else {
            zzaky.zze("Could not bind ad manager");
        }
    }
}
