package com.google.android.gms.internal;

import android.content.Context;
import android.media.AudioManager;
import com.google.android.exoplayer2.util.MimeTypes;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzanq implements AudioManager.OnAudioFocusChangeListener {
    private final AudioManager zza;
    private final zzanr zzb;
    private boolean zzc;
    private boolean zzd;
    private boolean zze;
    private float zzf = 1.0f;

    public zzanq(Context context, zzanr zzanrVar) {
        this.zza = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        this.zzb = zzanrVar;
    }

    private final void zzd() {
        boolean z;
        boolean z2;
        boolean z3 = this.zzd && !this.zze && this.zzf > 0.0f;
        if (z3 && !(z2 = this.zzc)) {
            AudioManager audioManager = this.zza;
            if (audioManager != null && !z2) {
                this.zzc = audioManager.requestAudioFocus(this, 3, 2) == 1;
            }
            this.zzb.zzi();
            return;
        }
        if (z3 || !(z = this.zzc)) {
            return;
        }
        AudioManager audioManager2 = this.zza;
        if (audioManager2 != null && z) {
            this.zzc = audioManager2.abandonAudioFocus(this) == 0;
        }
        this.zzb.zzi();
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public final void onAudioFocusChange(int i) {
        this.zzc = i > 0;
        this.zzb.zzi();
    }

    public final float zza() {
        float f = this.zze ? 0.0f : this.zzf;
        if (this.zzc) {
            return f;
        }
        return 0.0f;
    }

    public final void zza(float f) {
        this.zzf = f;
        zzd();
    }

    public final void zza(boolean z) {
        this.zze = z;
        zzd();
    }

    public final void zzb() {
        this.zzd = true;
        zzd();
    }

    public final void zzc() {
        this.zzd = false;
        zzd();
    }
}
