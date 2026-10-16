package com.google.android.gms.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzmu;
import com.google.android.gms.internal.zzmv;
import java.util.Date;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class AdRequest {
    public static final String DEVICE_ID_EMULATOR = "B3EEABB8EE11C2BE770B684D95219ECB";
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_UNKNOWN = 0;
    public static final int MAX_CONTENT_URL_LENGTH = 512;
    private final zzmu zza;

    public static final class Builder {
        private final zzmv zza;

        public Builder() {
            zzmv zzmvVar = new zzmv();
            this.zza = zzmvVar;
            zzmvVar.zzb("B3EEABB8EE11C2BE770B684D95219ECB");
        }

        public final Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> cls, Bundle bundle) {
            this.zza.zzb(cls, bundle);
            return this;
        }

        public final Builder addKeyword(String str) {
            this.zza.zza(str);
            return this;
        }

        public final Builder addNetworkExtras(NetworkExtras networkExtras) {
            this.zza.zza(networkExtras);
            return this;
        }

        public final Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> cls, Bundle bundle) {
            this.zza.zza(cls, bundle);
            if (cls.equals(AdMobAdapter.class) && bundle.getBoolean("_emulatorLiveAds")) {
                this.zza.zzc("B3EEABB8EE11C2BE770B684D95219ECB");
            }
            return this;
        }

        public final Builder addTestDevice(String str) {
            this.zza.zzb(str);
            return this;
        }

        public final AdRequest build() {
            return new AdRequest(this);
        }

        public final Builder setBirthday(Date date) {
            this.zza.zza(date);
            return this;
        }

        public final Builder setContentUrl(String str) {
            zzbq.zza(str, "Content URL must be non-null.");
            zzbq.zza(str, "Content URL must be non-empty.");
            zzbq.zzb(str.length() <= 512, "Content URL must not exceed %d in length.  Provided length was %d.", new Object[]{512, Integer.valueOf(str.length())});
            this.zza.zzd(str);
            return this;
        }

        public final Builder setGender(int i) {
            this.zza.zza(i);
            return this;
        }

        public final Builder setIsDesignedForFamilies(boolean z) {
            this.zza.zzc(z);
            return this;
        }

        public final Builder setLocation(Location location) {
            this.zza.zza(location);
            return this;
        }

        public final Builder setRequestAgent(String str) {
            this.zza.zzf(str);
            return this;
        }

        public final Builder tagForChildDirectedTreatment(boolean z) {
            this.zza.zzb(z);
            return this;
        }
    }

    private AdRequest(Builder builder) {
        this.zza = new zzmu(builder.zza);
    }

    public final Date getBirthday() {
        return this.zza.zza();
    }

    public final String getContentUrl() {
        return this.zza.zzb();
    }

    public final <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> cls) {
        return this.zza.zzc(cls);
    }

    public final int getGender() {
        return this.zza.zzc();
    }

    public final Set<String> getKeywords() {
        return this.zza.zzd();
    }

    public final Location getLocation() {
        return this.zza.zze();
    }

    @Deprecated
    public final <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return (T) this.zza.zza(cls);
    }

    public final <T extends MediationAdapter> Bundle getNetworkExtrasBundle(Class<T> cls) {
        return this.zza.zzb(cls);
    }

    public final boolean isTestDevice(Context context) {
        return this.zza.zza(context);
    }

    public final zzmu zza() {
        return this.zza;
    }
}
