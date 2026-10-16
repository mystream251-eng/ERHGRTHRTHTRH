package com.dotscreen.portal.ads;

import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.portal.ui.screens.home.HomeActivity;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TargetedAdsConfigProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/dotscreen/portal/ads/TargetedAdsConfigProvider;", "", "()V", "Provider", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class TargetedAdsConfigProvider {

    /* JADX INFO: renamed from: Provider, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static TargetAdvertisingConfig targetAdvertisingConfig;

    /* JADX INFO: renamed from: com.dotscreen.portal.ads.TargetedAdsConfigProvider$Provider, reason: from kotlin metadata */
    /* JADX INFO: compiled from: TargetedAdsConfigProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010\f\u001a\u0004\u0018\u00010\u000bJ\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u000bJ\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000bJ\b\u0010\u0017\u001a\u00020\u0006H\u0002J\u0006\u0010\u0018\u001a\u00020\u0006J\u0006\u0010\u0019\u001a\u00020\u0006J\u0006\u0010\u001a\u001a\u00020\u0006J\u0006\u0010\u001b\u001a\u00020\u0006J\u0006\u0010\u001c\u001a\u00020\u0006J\u0006\u0010\u001d\u001a\u00020\u0006J\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001fR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/dotscreen/portal/ads/TargetedAdsConfigProvider$Provider;", "", "()V", "targetAdvertisingConfig", "Lcom/dotscreen/portal/ads/TargetAdvertisingConfig;", "existsDuplicateNativeMappingInPage", "", "nativeAdsConfig", "Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$NativeAdsConfig;", "existsVOAdUnitIdForCustomAd", "dsAdUnitId", "", "getAdProviderBaseURL", "getAdRequestParamOverride", "Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$AdRequestParamOverride;", "getAdUnitIdsForPage", "Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$AdUnitsOfPage;", "className", "getDebugInfoTextSize", "", "initTargetAdvertisingConfig", "", "config", "isDebuggingEnabled", "isLoggingEnabled", "isTrackingEnabled", "shouldDisplayAdBannerBounds", "shouldDisplayDebugInfo", "shouldFetchImagesFromUIOP", "shouldOverrideRequestParams", "whitelistErrorsFromTheseURLs", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void initTargetAdvertisingConfig(String config) {
            Intrinsics.checkParameterIsNotNull(config, "config");
            try {
                TargetedAdsConfigProvider.targetAdvertisingConfig = (TargetAdvertisingConfig) new Gson().fromJson(config, TargetAdvertisingConfig.class);
                TargetAdvertisingConfig targetAdvertisingConfig = TargetedAdsConfigProvider.targetAdvertisingConfig;
                String adProviderBaseURL = targetAdvertisingConfig != null ? targetAdvertisingConfig.getAdProviderBaseURL() : null;
                if (!(adProviderBaseURL == null || adProviderBaseURL.length() == 0)) {
                    if (TargetedAdsConfigProvider.targetAdvertisingConfig != null) {
                        TargetAdvertisingConfig targetAdvertisingConfig2 = TargetedAdsConfigProvider.targetAdvertisingConfig;
                        if (targetAdvertisingConfig2 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (targetAdvertisingConfig2.getAdUnitsOfPage() != null) {
                            TargetAdvertisingConfig targetAdvertisingConfig3 = TargetedAdsConfigProvider.targetAdvertisingConfig;
                            if (targetAdvertisingConfig3 == null) {
                                Intrinsics.throwNpe();
                            }
                            Map<String, TargetAdvertisingConfig.AdUnitsOfPage> adUnitsOfPage = targetAdvertisingConfig3.getAdUnitsOfPage();
                            if (adUnitsOfPage == null) {
                                Intrinsics.throwNpe();
                            }
                            for (Map.Entry<String, TargetAdvertisingConfig.AdUnitsOfPage> entry : adUnitsOfPage.entrySet()) {
                                String key = entry.getKey();
                                if (existsDuplicateNativeMappingInPage(entry.getValue().getNativeAds())) {
                                    TargetedAdsConfigProvider.targetAdvertisingConfig = (TargetAdvertisingConfig) null;
                                    throw new IllegalArgumentException("Duplicate voAdUnitId detected for page:" + key);
                                }
                            }
                            TargetAdvertisingConfig targetAdvertisingConfig4 = TargetedAdsConfigProvider.targetAdvertisingConfig;
                            if (targetAdvertisingConfig4 != null) {
                                TargetAdvertisingConfig targetAdvertisingConfig5 = TargetedAdsConfigProvider.targetAdvertisingConfig;
                                String adProviderBaseURL2 = targetAdvertisingConfig5 != null ? targetAdvertisingConfig5.getAdProviderBaseURL() : null;
                                if (adProviderBaseURL2 == null) {
                                    Intrinsics.throwNpe();
                                }
                                targetAdvertisingConfig4.setAdProviderBaseURL(StringsKt.removeSuffix(adProviderBaseURL2, (CharSequence) "/"));
                            }
                        }
                    }
                    DSLog.INSTANCE.adLogM("[INIT CONFIG] SUCCESS");
                    return;
                }
                TargetedAdsConfigProvider.targetAdvertisingConfig = (TargetAdvertisingConfig) null;
                throw new IllegalArgumentException("adProviderBaseURL is not set");
            } catch (Exception e) {
                DSLog.INSTANCE.adLogE("[INIT CONFIG] FAILURE, no ads will be shown. | error=" + e.getMessage());
            }
        }

        private final boolean isDebuggingEnabled() {
            TargetAdvertisingConfig.DebuggingSetup debugging;
            Boolean debuggingEnabled;
            boolean z = !Intrinsics.areEqual("prod", "prod");
            TargetAdvertisingConfig targetAdvertisingConfig = TargetedAdsConfigProvider.targetAdvertisingConfig;
            return z & ((targetAdvertisingConfig == null || (debugging = targetAdvertisingConfig.getDebugging()) == null || (debuggingEnabled = debugging.getDebuggingEnabled()) == null) ? false : debuggingEnabled.booleanValue());
        }

        public final boolean shouldOverrideRequestParams() {
            TargetAdvertisingConfig targetAdvertisingConfig;
            TargetAdvertisingConfig.DebuggingSetup debugging;
            Boolean shouldOverrideRequestParams;
            if (!isDebuggingEnabled() || (targetAdvertisingConfig = TargetedAdsConfigProvider.targetAdvertisingConfig) == null || (debugging = targetAdvertisingConfig.getDebugging()) == null || (shouldOverrideRequestParams = debugging.getShouldOverrideRequestParams()) == null) {
                return false;
            }
            return shouldOverrideRequestParams.booleanValue();
        }

        public final boolean isLoggingEnabled() {
            TargetAdvertisingConfig.DebuggingSetup debugging;
            Boolean loggingEnabled;
            boolean zIsDebuggingEnabled = isDebuggingEnabled();
            TargetAdvertisingConfig targetAdvertisingConfig = TargetedAdsConfigProvider.targetAdvertisingConfig;
            return zIsDebuggingEnabled & ((targetAdvertisingConfig == null || (debugging = targetAdvertisingConfig.getDebugging()) == null || (loggingEnabled = debugging.getLoggingEnabled()) == null) ? false : loggingEnabled.booleanValue());
        }

        public final boolean isTrackingEnabled() {
            TargetAdvertisingConfig.DebuggingSetup debugging;
            Boolean trackingEnabled;
            if (!isDebuggingEnabled()) {
                return true;
            }
            TargetAdvertisingConfig targetAdvertisingConfig = TargetedAdsConfigProvider.targetAdvertisingConfig;
            if (targetAdvertisingConfig == null || (debugging = targetAdvertisingConfig.getDebugging()) == null || (trackingEnabled = debugging.getTrackingEnabled()) == null) {
                return false;
            }
            return trackingEnabled.booleanValue();
        }

        public final boolean shouldDisplayDebugInfo() {
            TargetAdvertisingConfig targetAdvertisingConfig;
            TargetAdvertisingConfig.DebuggingSetup debugging;
            Boolean displayAdInfo;
            if (!isDebuggingEnabled() || (targetAdvertisingConfig = TargetedAdsConfigProvider.targetAdvertisingConfig) == null || (debugging = targetAdvertisingConfig.getDebugging()) == null || (displayAdInfo = debugging.getDisplayAdInfo()) == null) {
                return false;
            }
            return displayAdInfo.booleanValue();
        }

        public final int getDebugInfoTextSize() {
            TargetAdvertisingConfig.DebuggingSetup debugging;
            Integer adInfoTextSize;
            if (!isDebuggingEnabled()) {
                return 0;
            }
            TargetAdvertisingConfig targetAdvertisingConfig = TargetedAdsConfigProvider.targetAdvertisingConfig;
            if (targetAdvertisingConfig == null || (debugging = targetAdvertisingConfig.getDebugging()) == null || (adInfoTextSize = debugging.getAdInfoTextSize()) == null) {
                return 12;
            }
            return adInfoTextSize.intValue();
        }

        public final boolean shouldDisplayAdBannerBounds() {
            TargetAdvertisingConfig targetAdvertisingConfig;
            TargetAdvertisingConfig.DebuggingSetup debugging;
            Boolean displayAdBannerBounds;
            if (!isDebuggingEnabled() || (targetAdvertisingConfig = TargetedAdsConfigProvider.targetAdvertisingConfig) == null || (debugging = targetAdvertisingConfig.getDebugging()) == null || (displayAdBannerBounds = debugging.getDisplayAdBannerBounds()) == null) {
                return false;
            }
            return displayAdBannerBounds.booleanValue();
        }

        public final boolean shouldFetchImagesFromUIOP() {
            TargetAdvertisingConfig targetAdvertisingConfig;
            TargetAdvertisingConfig.DebuggingSetup debugging;
            Boolean shouldFetchImagesFromUIOP;
            if (!isDebuggingEnabled() || (targetAdvertisingConfig = TargetedAdsConfigProvider.targetAdvertisingConfig) == null || (debugging = targetAdvertisingConfig.getDebugging()) == null || (shouldFetchImagesFromUIOP = debugging.getShouldFetchImagesFromUIOP()) == null) {
                return true;
            }
            return shouldFetchImagesFromUIOP.booleanValue();
        }

        public final TargetAdvertisingConfig.AdRequestParamOverride getAdRequestParamOverride() {
            TargetAdvertisingConfig targetAdvertisingConfig;
            TargetAdvertisingConfig.DebuggingSetup debugging;
            if (!isDebuggingEnabled() || (targetAdvertisingConfig = TargetedAdsConfigProvider.targetAdvertisingConfig) == null || (debugging = targetAdvertisingConfig.getDebugging()) == null) {
                return null;
            }
            return debugging.getAdRequestParamOverride();
        }

        public final TargetAdvertisingConfig.AdUnitsOfPage getAdUnitIdsForPage(String className) {
            Map<String, TargetAdvertisingConfig.AdUnitsOfPage> adUnitsOfPage;
            Intrinsics.checkParameterIsNotNull(className, "className");
            if (TargetedAdsConfigProvider.targetAdvertisingConfig != null) {
                TargetAdvertisingConfig targetAdvertisingConfig = TargetedAdsConfigProvider.targetAdvertisingConfig;
                if (targetAdvertisingConfig == null || (adUnitsOfPage = targetAdvertisingConfig.getAdUnitsOfPage()) == null) {
                    return null;
                }
                return adUnitsOfPage.get(className);
            }
            DSLog.INSTANCE.adLogE("[GET AD UNIT IDs FOR PAGE][" + className + "] Config has not been initialized! Can't get adUnitIds/ads.");
            return null;
        }

        public final List<String> whitelistErrorsFromTheseURLs() {
            List<String> whitelistErrorsFromTheseURLs;
            TargetAdvertisingConfig targetAdvertisingConfig = TargetedAdsConfigProvider.targetAdvertisingConfig;
            return (targetAdvertisingConfig == null || (whitelistErrorsFromTheseURLs = targetAdvertisingConfig.getWhitelistErrorsFromTheseURLs()) == null) ? CollectionsKt.emptyList() : whitelistErrorsFromTheseURLs;
        }

        public final String getAdProviderBaseURL() {
            TargetAdvertisingConfig targetAdvertisingConfig = TargetedAdsConfigProvider.targetAdvertisingConfig;
            if (targetAdvertisingConfig != null) {
                return targetAdvertisingConfig.getAdProviderBaseURL();
            }
            return null;
        }

        public final boolean existsVOAdUnitIdForCustomAd(String dsAdUnitId) {
            TargetAdvertisingConfig.AdUnitsOfPage adUnitsOfPage;
            TargetAdvertisingConfig.NativeAdsConfig nativeAds;
            Map<String, String> custom;
            Intrinsics.checkParameterIsNotNull(dsAdUnitId, "dsAdUnitId");
            if (TargetedAdsConfigProvider.targetAdvertisingConfig == null) {
                return false;
            }
            TargetAdvertisingConfig targetAdvertisingConfig = TargetedAdsConfigProvider.targetAdvertisingConfig;
            if (targetAdvertisingConfig == null) {
                Intrinsics.throwNpe();
            }
            Map<String, TargetAdvertisingConfig.AdUnitsOfPage> adUnitsOfPage2 = targetAdvertisingConfig.getAdUnitsOfPage();
            return !Intrinsics.areEqual((Object) ((adUnitsOfPage2 == null || (adUnitsOfPage = adUnitsOfPage2.get(HomeActivity.class.getSimpleName())) == null || (nativeAds = adUnitsOfPage.getNativeAds()) == null || (custom = nativeAds.getCustom()) == null) ? null : custom.get(dsAdUnitId)), (Object) false);
        }

        private final boolean existsDuplicateNativeMappingInPage(TargetAdvertisingConfig.NativeAdsConfig nativeAdsConfig) {
            if (nativeAdsConfig == null) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (Map map : CollectionsKt.listOf((Object[]) new Map[]{nativeAdsConfig.getInLayout(), nativeAdsConfig.getInSwimlane(), nativeAdsConfig.getCustom()})) {
                if (map != null) {
                    for (Map.Entry entry : map.entrySet()) {
                        String str = (String) entry.getValue();
                        if (str != null) {
                            if (arrayList.contains(str)) {
                                return true;
                            }
                            arrayList.add(str);
                        }
                    }
                }
            }
            return false;
        }
    }
}
