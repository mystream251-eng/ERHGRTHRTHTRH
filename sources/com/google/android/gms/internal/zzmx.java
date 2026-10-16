package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzmx extends zzle {
    private /* synthetic */ zzmw zza;

    zzmx(zzmw zzmwVar) {
        this.zza = zzmwVar;
    }

    @Override // com.google.android.gms.internal.zzle, com.google.android.gms.ads.AdListener
    public final void onAdFailedToLoad(int i) {
        this.zza.zzd.zza(this.zza.zzn());
        super.onAdFailedToLoad(i);
    }

    @Override // com.google.android.gms.internal.zzle, com.google.android.gms.ads.AdListener
    public final void onAdLoaded() {
        this.zza.zzd.zza(this.zza.zzn());
        super.onAdLoaded();
    }
}
