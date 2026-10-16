package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.internal.zzmz;
import com.google.android.gms.internal.zznb;

/* JADX INFO: loaded from: classes2.dex */
public class MobileAds {

    public static final class Settings {
        private final zznb zza = new zznb();

        @Deprecated
        public final String getTrackingId() {
            return null;
        }

        @Deprecated
        public final boolean isGoogleAnalyticsEnabled() {
            return false;
        }

        @Deprecated
        public final Settings setGoogleAnalyticsEnabled(boolean z) {
            return this;
        }

        @Deprecated
        public final Settings setTrackingId(String str) {
            return this;
        }

        final zznb zza() {
            return this.zza;
        }
    }

    private MobileAds() {
    }

    public static RewardedVideoAd getRewardedVideoAdInstance(Context context) {
        return zzmz.zza().zza(context);
    }

    public static void initialize(Context context) {
        initialize(context, null, null);
    }

    public static void initialize(Context context, String str) {
        initialize(context, str, null);
    }

    @Deprecated
    public static void initialize(Context context, String str, Settings settings) {
        zzmz.zza().zza(context, str, settings == null ? null : settings.zza());
    }

    public static void openDebugMenu(Context context, String str) {
        zzmz.zza().zza(context, str);
    }

    public static void setAppMuted(boolean z) {
        zzmz.zza().zza(z);
    }

    public static void setAppVolume(float f) {
        zzmz.zza().zza(f);
    }
}
