package com.google.android.gms.ads;

import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzns;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class VideoOptions {
    private final boolean zza;
    private final boolean zzb;
    private final boolean zzc;

    public static final class Builder {
        private boolean zza = true;
        private boolean zzb = false;
        private boolean zzc = false;

        public final VideoOptions build() {
            return new VideoOptions(this);
        }

        public final Builder setClickToExpandRequested(boolean z) {
            this.zzc = z;
            return this;
        }

        public final Builder setCustomControlsRequested(boolean z) {
            this.zzb = z;
            return this;
        }

        public final Builder setStartMuted(boolean z) {
            this.zza = z;
            return this;
        }
    }

    private VideoOptions(Builder builder) {
        this.zza = builder.zza;
        this.zzb = builder.zzb;
        this.zzc = builder.zzc;
    }

    public VideoOptions(zzns zznsVar) {
        this.zza = zznsVar.zza;
        this.zzb = zznsVar.zzb;
        this.zzc = zznsVar.zzc;
    }

    public final boolean getClickToExpandRequested() {
        return this.zzc;
    }

    public final boolean getCustomControlsRequested() {
        return this.zzb;
    }

    public final boolean getStartMuted() {
        return this.zza;
    }
}
