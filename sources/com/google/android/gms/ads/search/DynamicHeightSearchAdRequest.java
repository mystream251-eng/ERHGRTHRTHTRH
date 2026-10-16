package com.google.android.gms.ads.search;

import android.content.Context;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.internal.zzmu;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: loaded from: classes2.dex */
public final class DynamicHeightSearchAdRequest {
    private final SearchAdRequest zza;

    public static final class Builder {
        private final SearchAdRequest.Builder zza = new SearchAdRequest.Builder();
        private final Bundle zzb = new Bundle();

        public final Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> cls, Bundle bundle) {
            this.zza.addCustomEventExtrasBundle(cls, bundle);
            return this;
        }

        public final Builder addNetworkExtras(NetworkExtras networkExtras) {
            this.zza.addNetworkExtras(networkExtras);
            return this;
        }

        public final Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> cls, Bundle bundle) {
            this.zza.addNetworkExtrasBundle(cls, bundle);
            return this;
        }

        public final DynamicHeightSearchAdRequest build() {
            this.zza.addNetworkExtrasBundle(AdMobAdapter.class, this.zzb);
            return new DynamicHeightSearchAdRequest(this);
        }

        public final Builder setAdBorderSelectors(String str) {
            this.zzb.putString("csa_adBorderSelectors", str);
            return this;
        }

        public final Builder setAdTest(boolean z) {
            this.zzb.putString("csa_adtest", z ? DebugKt.DEBUG_PROPERTY_VALUE_ON : DebugKt.DEBUG_PROPERTY_VALUE_OFF);
            return this;
        }

        public final Builder setAdjustableLineHeight(int i) {
            this.zzb.putString("csa_adjustableLineHeight", Integer.toString(i));
            return this;
        }

        public final Builder setAdvancedOptionValue(String str, String str2) {
            this.zzb.putString(str, str2);
            return this;
        }

        public final Builder setAttributionSpacingBelow(int i) {
            this.zzb.putString("csa_attributionSpacingBelow", Integer.toString(i));
            return this;
        }

        public final Builder setBorderSelections(String str) {
            this.zzb.putString("csa_borderSelections", str);
            return this;
        }

        public final Builder setChannel(String str) {
            this.zzb.putString("csa_channel", str);
            return this;
        }

        public final Builder setColorAdBorder(String str) {
            this.zzb.putString("csa_colorAdBorder", str);
            return this;
        }

        public final Builder setColorAdSeparator(String str) {
            this.zzb.putString("csa_colorAdSeparator", str);
            return this;
        }

        public final Builder setColorAnnotation(String str) {
            this.zzb.putString("csa_colorAnnotation", str);
            return this;
        }

        public final Builder setColorAttribution(String str) {
            this.zzb.putString("csa_colorAttribution", str);
            return this;
        }

        public final Builder setColorBackground(String str) {
            this.zzb.putString("csa_colorBackground", str);
            return this;
        }

        public final Builder setColorBorder(String str) {
            this.zzb.putString("csa_colorBorder", str);
            return this;
        }

        public final Builder setColorDomainLink(String str) {
            this.zzb.putString("csa_colorDomainLink", str);
            return this;
        }

        public final Builder setColorText(String str) {
            this.zzb.putString("csa_colorText", str);
            return this;
        }

        public final Builder setColorTitleLink(String str) {
            this.zzb.putString("csa_colorTitleLink", str);
            return this;
        }

        public final Builder setCssWidth(int i) {
            this.zzb.putString("csa_width", Integer.toString(i));
            return this;
        }

        public final Builder setDetailedAttribution(boolean z) {
            this.zzb.putString("csa_detailedAttribution", Boolean.toString(z));
            return this;
        }

        public final Builder setFontFamily(String str) {
            this.zzb.putString("csa_fontFamily", str);
            return this;
        }

        public final Builder setFontFamilyAttribution(String str) {
            this.zzb.putString("csa_fontFamilyAttribution", str);
            return this;
        }

        public final Builder setFontSizeAnnotation(int i) {
            this.zzb.putString("csa_fontSizeAnnotation", Integer.toString(i));
            return this;
        }

        public final Builder setFontSizeAttribution(int i) {
            this.zzb.putString("csa_fontSizeAttribution", Integer.toString(i));
            return this;
        }

        public final Builder setFontSizeDescription(int i) {
            this.zzb.putString("csa_fontSizeDescription", Integer.toString(i));
            return this;
        }

        public final Builder setFontSizeDomainLink(int i) {
            this.zzb.putString("csa_fontSizeDomainLink", Integer.toString(i));
            return this;
        }

        public final Builder setFontSizeTitle(int i) {
            this.zzb.putString("csa_fontSizeTitle", Integer.toString(i));
            return this;
        }

        public final Builder setHostLanguage(String str) {
            this.zzb.putString("csa_hl", str);
            return this;
        }

        public final Builder setIsClickToCallEnabled(boolean z) {
            this.zzb.putString("csa_clickToCall", Boolean.toString(z));
            return this;
        }

        public final Builder setIsLocationEnabled(boolean z) {
            this.zzb.putString("csa_location", Boolean.toString(z));
            return this;
        }

        public final Builder setIsPlusOnesEnabled(boolean z) {
            this.zzb.putString("csa_plusOnes", Boolean.toString(z));
            return this;
        }

        public final Builder setIsSellerRatingsEnabled(boolean z) {
            this.zzb.putString("csa_sellerRatings", Boolean.toString(z));
            return this;
        }

        public final Builder setIsSiteLinksEnabled(boolean z) {
            this.zzb.putString("csa_siteLinks", Boolean.toString(z));
            return this;
        }

        public final Builder setIsTitleBold(boolean z) {
            this.zzb.putString("csa_titleBold", Boolean.toString(z));
            return this;
        }

        public final Builder setIsTitleUnderlined(boolean z) {
            this.zzb.putString("csa_noTitleUnderline", Boolean.toString(!z));
            return this;
        }

        public final Builder setLocationColor(String str) {
            this.zzb.putString("csa_colorLocation", str);
            return this;
        }

        public final Builder setLocationFontSize(int i) {
            this.zzb.putString("csa_fontSizeLocation", Integer.toString(i));
            return this;
        }

        public final Builder setLongerHeadlines(boolean z) {
            this.zzb.putString("csa_longerHeadlines", Boolean.toString(z));
            return this;
        }

        public final Builder setNumber(int i) {
            this.zzb.putString("csa_number", Integer.toString(i));
            return this;
        }

        public final Builder setPage(int i) {
            this.zzb.putString("csa_adPage", Integer.toString(i));
            return this;
        }

        public final Builder setQuery(String str) {
            this.zza.setQuery(str);
            return this;
        }

        public final Builder setVerticalSpacing(int i) {
            this.zzb.putString("csa_verticalSpacing", Integer.toString(i));
            return this;
        }
    }

    private DynamicHeightSearchAdRequest(Builder builder) {
        this.zza = builder.zza.build();
    }

    public final <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> cls) {
        return this.zza.getCustomEventExtrasBundle(cls);
    }

    @Deprecated
    public final <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return (T) this.zza.getNetworkExtras(cls);
    }

    public final <T extends MediationAdapter> Bundle getNetworkExtrasBundle(Class<T> cls) {
        return this.zza.getNetworkExtrasBundle(cls);
    }

    public final String getQuery() {
        return this.zza.getQuery();
    }

    public final boolean isTestDevice(Context context) {
        return this.zza.isTestDevice(context);
    }

    final zzmu zza() {
        return this.zza.zza();
    }
}
