package com.google.android.gms.internal;

import com.google.android.gms.ads.VideoController;

/* JADX INFO: loaded from: classes2.dex */
public final class zznr extends zzmq {
    private final VideoController.VideoLifecycleCallbacks zza;

    public zznr(VideoController.VideoLifecycleCallbacks videoLifecycleCallbacks) {
        this.zza = videoLifecycleCallbacks;
    }

    @Override // com.google.android.gms.internal.zzmp
    public final void zza() {
        this.zza.onVideoStart();
    }

    @Override // com.google.android.gms.internal.zzmp
    public final void zza(boolean z) {
        this.zza.onVideoMute(z);
    }

    @Override // com.google.android.gms.internal.zzmp
    public final void zzb() {
        this.zza.onVideoPlay();
    }

    @Override // com.google.android.gms.internal.zzmp
    public final void zzc() {
        this.zza.onVideoPause();
    }

    @Override // com.google.android.gms.internal.zzmp
    public final void zzd() {
        this.zza.onVideoEnd();
    }
}
