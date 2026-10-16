package com.google.android.gms.internal;

import android.content.Context;
import android.media.AudioManager;
import com.google.android.exoplayer2.util.MimeTypes;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaja {
    private boolean zza = false;
    private float zzb = 1.0f;

    public static float zza(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        if (audioManager == null) {
            return 0.0f;
        }
        int streamMaxVolume = audioManager.getStreamMaxVolume(3);
        int streamVolume = audioManager.getStreamVolume(3);
        if (streamMaxVolume == 0) {
            return 0.0f;
        }
        return streamVolume / streamMaxVolume;
    }

    private final synchronized boolean zzc() {
        return this.zzb >= 0.0f;
    }

    public final synchronized float zza() {
        if (!zzc()) {
            return 1.0f;
        }
        return this.zzb;
    }

    public final synchronized void zza(float f) {
        this.zzb = f;
    }

    public final synchronized void zza(boolean z) {
        this.zza = z;
    }

    public final synchronized boolean zzb() {
        return this.zza;
    }
}
