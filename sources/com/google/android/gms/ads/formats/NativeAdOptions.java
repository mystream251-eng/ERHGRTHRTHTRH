package com.google.android.gms.ads.formats;

import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.internal.zzabh;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class NativeAdOptions {
    public static final int ADCHOICES_BOTTOM_LEFT = 3;
    public static final int ADCHOICES_BOTTOM_RIGHT = 2;
    public static final int ADCHOICES_TOP_LEFT = 0;
    public static final int ADCHOICES_TOP_RIGHT = 1;
    public static final int ORIENTATION_ANY = 0;
    public static final int ORIENTATION_LANDSCAPE = 2;
    public static final int ORIENTATION_PORTRAIT = 1;
    private final boolean zza;
    private final int zzb;
    private final boolean zzc;
    private final int zzd;
    private final VideoOptions zze;

    public @interface AdChoicesPlacement {
    }

    public static final class Builder {
        private VideoOptions zzd;
        private boolean zza = false;
        private int zzb = -1;
        private boolean zzc = false;
        private int zze = 1;

        public final NativeAdOptions build() {
            return new NativeAdOptions(this);
        }

        public final Builder setAdChoicesPlacement(int i) {
            this.zze = i;
            return this;
        }

        public final Builder setImageOrientation(int i) {
            this.zzb = i;
            return this;
        }

        public final Builder setRequestMultipleImages(boolean z) {
            this.zzc = z;
            return this;
        }

        public final Builder setReturnUrlsForImageAssets(boolean z) {
            this.zza = z;
            return this;
        }

        public final Builder setVideoOptions(VideoOptions videoOptions) {
            this.zzd = videoOptions;
            return this;
        }
    }

    private NativeAdOptions(Builder builder) {
        this.zza = builder.zza;
        this.zzb = builder.zzb;
        this.zzc = builder.zzc;
        this.zzd = builder.zze;
        this.zze = builder.zzd;
    }

    public final int getAdChoicesPlacement() {
        return this.zzd;
    }

    public final int getImageOrientation() {
        return this.zzb;
    }

    public final VideoOptions getVideoOptions() {
        return this.zze;
    }

    public final boolean shouldRequestMultipleImages() {
        return this.zzc;
    }

    public final boolean shouldReturnUrlsForImageAssets() {
        return this.zza;
    }
}
