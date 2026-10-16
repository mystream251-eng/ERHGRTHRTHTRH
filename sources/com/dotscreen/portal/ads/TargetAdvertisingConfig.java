package com.dotscreen.portal.ads;

import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import io.reactivex.annotations.SchedulerSupport;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TargetedAdsConfigProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001:\u0004%&'(B=\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007\u0012\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u0011\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007HÆ\u0003J\u0017\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0003JI\u0010\u001e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001f\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006)"}, d2 = {"Lcom/dotscreen/portal/ads/TargetAdvertisingConfig;", "", "debugging", "Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$DebuggingSetup;", "adProviderBaseURL", "", "whitelistErrorsFromTheseURLs", "", "adUnitsOfPage", "", "Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$AdUnitsOfPage;", "(Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$DebuggingSetup;Ljava/lang/String;Ljava/util/List;Ljava/util/Map;)V", "getAdProviderBaseURL", "()Ljava/lang/String;", "setAdProviderBaseURL", "(Ljava/lang/String;)V", "getAdUnitsOfPage", "()Ljava/util/Map;", "getDebugging", "()Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$DebuggingSetup;", "setDebugging", "(Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$DebuggingSetup;)V", "getWhitelistErrorsFromTheseURLs", "()Ljava/util/List;", "setWhitelistErrorsFromTheseURLs", "(Ljava/util/List;)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "AdRequestParamOverride", "AdUnitsOfPage", "DebuggingSetup", "NativeAdsConfig", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final /* data */ class TargetAdvertisingConfig {
    private String adProviderBaseURL;
    private final Map<String, AdUnitsOfPage> adUnitsOfPage;
    private DebuggingSetup debugging;
    private List<String> whitelistErrorsFromTheseURLs;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TargetAdvertisingConfig copy$default(TargetAdvertisingConfig targetAdvertisingConfig, DebuggingSetup debuggingSetup, String str, List list, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            debuggingSetup = targetAdvertisingConfig.debugging;
        }
        if ((i & 2) != 0) {
            str = targetAdvertisingConfig.adProviderBaseURL;
        }
        if ((i & 4) != 0) {
            list = targetAdvertisingConfig.whitelistErrorsFromTheseURLs;
        }
        if ((i & 8) != 0) {
            map = targetAdvertisingConfig.adUnitsOfPage;
        }
        return targetAdvertisingConfig.copy(debuggingSetup, str, list, map);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final DebuggingSetup getDebugging() {
        return this.debugging;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAdProviderBaseURL() {
        return this.adProviderBaseURL;
    }

    public final List<String> component3() {
        return this.whitelistErrorsFromTheseURLs;
    }

    public final Map<String, AdUnitsOfPage> component4() {
        return this.adUnitsOfPage;
    }

    public final TargetAdvertisingConfig copy(DebuggingSetup debugging, String adProviderBaseURL, List<String> whitelistErrorsFromTheseURLs, Map<String, AdUnitsOfPage> adUnitsOfPage) {
        Intrinsics.checkParameterIsNotNull(adProviderBaseURL, "adProviderBaseURL");
        return new TargetAdvertisingConfig(debugging, adProviderBaseURL, whitelistErrorsFromTheseURLs, adUnitsOfPage);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TargetAdvertisingConfig)) {
            return false;
        }
        TargetAdvertisingConfig targetAdvertisingConfig = (TargetAdvertisingConfig) other;
        return Intrinsics.areEqual(this.debugging, targetAdvertisingConfig.debugging) && Intrinsics.areEqual(this.adProviderBaseURL, targetAdvertisingConfig.adProviderBaseURL) && Intrinsics.areEqual(this.whitelistErrorsFromTheseURLs, targetAdvertisingConfig.whitelistErrorsFromTheseURLs) && Intrinsics.areEqual(this.adUnitsOfPage, targetAdvertisingConfig.adUnitsOfPage);
    }

    public int hashCode() {
        DebuggingSetup debuggingSetup = this.debugging;
        int iHashCode = (debuggingSetup != null ? debuggingSetup.hashCode() : 0) * 31;
        String str = this.adProviderBaseURL;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        List<String> list = this.whitelistErrorsFromTheseURLs;
        int iHashCode3 = (iHashCode2 + (list != null ? list.hashCode() : 0)) * 31;
        Map<String, AdUnitsOfPage> map = this.adUnitsOfPage;
        return iHashCode3 + (map != null ? map.hashCode() : 0);
    }

    public String toString() {
        return "TargetAdvertisingConfig(debugging=" + this.debugging + ", adProviderBaseURL=" + this.adProviderBaseURL + ", whitelistErrorsFromTheseURLs=" + this.whitelistErrorsFromTheseURLs + ", adUnitsOfPage=" + this.adUnitsOfPage + ")";
    }

    public TargetAdvertisingConfig(DebuggingSetup debuggingSetup, String adProviderBaseURL, List<String> list, Map<String, AdUnitsOfPage> map) {
        Intrinsics.checkParameterIsNotNull(adProviderBaseURL, "adProviderBaseURL");
        this.debugging = debuggingSetup;
        this.adProviderBaseURL = adProviderBaseURL;
        this.whitelistErrorsFromTheseURLs = list;
        this.adUnitsOfPage = map;
    }

    public final DebuggingSetup getDebugging() {
        return this.debugging;
    }

    public final void setDebugging(DebuggingSetup debuggingSetup) {
        this.debugging = debuggingSetup;
    }

    public final String getAdProviderBaseURL() {
        return this.adProviderBaseURL;
    }

    public final void setAdProviderBaseURL(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.adProviderBaseURL = str;
    }

    public final List<String> getWhitelistErrorsFromTheseURLs() {
        return this.whitelistErrorsFromTheseURLs;
    }

    public final void setWhitelistErrorsFromTheseURLs(List<String> list) {
        this.whitelistErrorsFromTheseURLs = list;
    }

    public final Map<String, AdUnitsOfPage> getAdUnitsOfPage() {
        return this.adUnitsOfPage;
    }

    /* JADX INFO: compiled from: TargetedAdsConfigProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001R\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$AdUnitsOfPage;", "", "nativeAds", "Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$NativeAdsConfig;", "floatingAds", "", "", "(Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$NativeAdsConfig;Ljava/util/List;)V", "getFloatingAds", "()Ljava/util/List;", "getNativeAds", "()Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$NativeAdsConfig;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class AdUnitsOfPage {
        private final List<String> floatingAds;
        private final NativeAdsConfig nativeAds;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ AdUnitsOfPage copy$default(AdUnitsOfPage adUnitsOfPage, NativeAdsConfig nativeAdsConfig, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                nativeAdsConfig = adUnitsOfPage.nativeAds;
            }
            if ((i & 2) != 0) {
                list = adUnitsOfPage.floatingAds;
            }
            return adUnitsOfPage.copy(nativeAdsConfig, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final NativeAdsConfig getNativeAds() {
            return this.nativeAds;
        }

        public final List<String> component2() {
            return this.floatingAds;
        }

        public final AdUnitsOfPage copy(NativeAdsConfig nativeAds, List<String> floatingAds) {
            return new AdUnitsOfPage(nativeAds, floatingAds);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AdUnitsOfPage)) {
                return false;
            }
            AdUnitsOfPage adUnitsOfPage = (AdUnitsOfPage) other;
            return Intrinsics.areEqual(this.nativeAds, adUnitsOfPage.nativeAds) && Intrinsics.areEqual(this.floatingAds, adUnitsOfPage.floatingAds);
        }

        public int hashCode() {
            NativeAdsConfig nativeAdsConfig = this.nativeAds;
            int iHashCode = (nativeAdsConfig != null ? nativeAdsConfig.hashCode() : 0) * 31;
            List<String> list = this.floatingAds;
            return iHashCode + (list != null ? list.hashCode() : 0);
        }

        public String toString() {
            return "AdUnitsOfPage(nativeAds=" + this.nativeAds + ", floatingAds=" + this.floatingAds + ")";
        }

        public AdUnitsOfPage(NativeAdsConfig nativeAdsConfig, List<String> list) {
            this.nativeAds = nativeAdsConfig;
            this.floatingAds = list;
        }

        public final NativeAdsConfig getNativeAds() {
            return this.nativeAds;
        }

        public final List<String> getFloatingAds() {
            return this.floatingAds;
        }
    }

    /* JADX INFO: compiled from: TargetedAdsConfigProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003\u0012\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u0019\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003HÆ\u0003J\u0019\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003HÆ\u0003J\u0019\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003HÆ\u0003JW\u0010\u000f\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00032\u0018\b\u0002\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00032\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0004HÖ\u0001R!\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR!\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$NativeAdsConfig;", "", "inLayout", "", "", "inSwimlane", SchedulerSupport.CUSTOM, "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "getCustom", "()Ljava/util/Map;", "getInLayout", "getInSwimlane", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class NativeAdsConfig {
        private final Map<String, String> custom;
        private final Map<String, String> inLayout;
        private final Map<String, String> inSwimlane;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ NativeAdsConfig copy$default(NativeAdsConfig nativeAdsConfig, Map map, Map map2, Map map3, int i, Object obj) {
            if ((i & 1) != 0) {
                map = nativeAdsConfig.inLayout;
            }
            if ((i & 2) != 0) {
                map2 = nativeAdsConfig.inSwimlane;
            }
            if ((i & 4) != 0) {
                map3 = nativeAdsConfig.custom;
            }
            return nativeAdsConfig.copy(map, map2, map3);
        }

        public final Map<String, String> component1() {
            return this.inLayout;
        }

        public final Map<String, String> component2() {
            return this.inSwimlane;
        }

        public final Map<String, String> component3() {
            return this.custom;
        }

        public final NativeAdsConfig copy(Map<String, String> inLayout, Map<String, String> inSwimlane, Map<String, String> custom) {
            return new NativeAdsConfig(inLayout, inSwimlane, custom);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NativeAdsConfig)) {
                return false;
            }
            NativeAdsConfig nativeAdsConfig = (NativeAdsConfig) other;
            return Intrinsics.areEqual(this.inLayout, nativeAdsConfig.inLayout) && Intrinsics.areEqual(this.inSwimlane, nativeAdsConfig.inSwimlane) && Intrinsics.areEqual(this.custom, nativeAdsConfig.custom);
        }

        public int hashCode() {
            Map<String, String> map = this.inLayout;
            int iHashCode = (map != null ? map.hashCode() : 0) * 31;
            Map<String, String> map2 = this.inSwimlane;
            int iHashCode2 = (iHashCode + (map2 != null ? map2.hashCode() : 0)) * 31;
            Map<String, String> map3 = this.custom;
            return iHashCode2 + (map3 != null ? map3.hashCode() : 0);
        }

        public String toString() {
            return "NativeAdsConfig(inLayout=" + this.inLayout + ", inSwimlane=" + this.inSwimlane + ", custom=" + this.custom + ")";
        }

        public NativeAdsConfig(Map<String, String> map, Map<String, String> map2, Map<String, String> map3) {
            this.inLayout = map;
            this.inSwimlane = map2;
            this.custom = map3;
        }

        public final Map<String, String> getInLayout() {
            return this.inLayout;
        }

        public final Map<String, String> getInSwimlane() {
            return this.inSwimlane;
        }

        public final Map<String, String> getCustom() {
            return this.custom;
        }
    }

    /* JADX INFO: compiled from: TargetedAdsConfigProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010%\u001a\u0004\u0018\u00010\rHÆ\u0003Jz\u0010&\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001¢\u0006\u0002\u0010'J\u0013\u0010(\u001a\u00020\u00032\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020\u0006HÖ\u0001J\t\u0010+\u001a\u00020,HÖ\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0017\u0010\u0015R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0018\u0010\u0015R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0019\u0010\u0015R\u0015\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u001a\u0010\u0015R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u001b\u0010\u0015R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u001c\u0010\u0015¨\u0006-"}, d2 = {"Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$DebuggingSetup;", "", "debuggingEnabled", "", "displayAdInfo", "adInfoTextSize", "", "loggingEnabled", "trackingEnabled", "displayAdBannerBounds", "shouldFetchImagesFromUIOP", "shouldOverrideRequestParams", "adRequestParamOverride", "Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$AdRequestParamOverride;", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$AdRequestParamOverride;)V", "getAdInfoTextSize", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getAdRequestParamOverride", "()Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$AdRequestParamOverride;", "getDebuggingEnabled", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getDisplayAdBannerBounds", "getDisplayAdInfo", "getLoggingEnabled", "getShouldFetchImagesFromUIOP", "getShouldOverrideRequestParams", "getTrackingEnabled", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$AdRequestParamOverride;)Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$DebuggingSetup;", "equals", "other", "hashCode", "toString", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class DebuggingSetup {
        private final Integer adInfoTextSize;
        private final AdRequestParamOverride adRequestParamOverride;
        private final Boolean debuggingEnabled;
        private final Boolean displayAdBannerBounds;
        private final Boolean displayAdInfo;
        private final Boolean loggingEnabled;
        private final Boolean shouldFetchImagesFromUIOP;
        private final Boolean shouldOverrideRequestParams;
        private final Boolean trackingEnabled;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Boolean getDebuggingEnabled() {
            return this.debuggingEnabled;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Boolean getDisplayAdInfo() {
            return this.displayAdInfo;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getAdInfoTextSize() {
            return this.adInfoTextSize;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Boolean getLoggingEnabled() {
            return this.loggingEnabled;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Boolean getTrackingEnabled() {
            return this.trackingEnabled;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Boolean getDisplayAdBannerBounds() {
            return this.displayAdBannerBounds;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Boolean getShouldFetchImagesFromUIOP() {
            return this.shouldFetchImagesFromUIOP;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Boolean getShouldOverrideRequestParams() {
            return this.shouldOverrideRequestParams;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final AdRequestParamOverride getAdRequestParamOverride() {
            return this.adRequestParamOverride;
        }

        public final DebuggingSetup copy(Boolean debuggingEnabled, Boolean displayAdInfo, Integer adInfoTextSize, Boolean loggingEnabled, Boolean trackingEnabled, Boolean displayAdBannerBounds, Boolean shouldFetchImagesFromUIOP, Boolean shouldOverrideRequestParams, AdRequestParamOverride adRequestParamOverride) {
            return new DebuggingSetup(debuggingEnabled, displayAdInfo, adInfoTextSize, loggingEnabled, trackingEnabled, displayAdBannerBounds, shouldFetchImagesFromUIOP, shouldOverrideRequestParams, adRequestParamOverride);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DebuggingSetup)) {
                return false;
            }
            DebuggingSetup debuggingSetup = (DebuggingSetup) other;
            return Intrinsics.areEqual(this.debuggingEnabled, debuggingSetup.debuggingEnabled) && Intrinsics.areEqual(this.displayAdInfo, debuggingSetup.displayAdInfo) && Intrinsics.areEqual(this.adInfoTextSize, debuggingSetup.adInfoTextSize) && Intrinsics.areEqual(this.loggingEnabled, debuggingSetup.loggingEnabled) && Intrinsics.areEqual(this.trackingEnabled, debuggingSetup.trackingEnabled) && Intrinsics.areEqual(this.displayAdBannerBounds, debuggingSetup.displayAdBannerBounds) && Intrinsics.areEqual(this.shouldFetchImagesFromUIOP, debuggingSetup.shouldFetchImagesFromUIOP) && Intrinsics.areEqual(this.shouldOverrideRequestParams, debuggingSetup.shouldOverrideRequestParams) && Intrinsics.areEqual(this.adRequestParamOverride, debuggingSetup.adRequestParamOverride);
        }

        public int hashCode() {
            Boolean bool = this.debuggingEnabled;
            int iHashCode = (bool != null ? bool.hashCode() : 0) * 31;
            Boolean bool2 = this.displayAdInfo;
            int iHashCode2 = (iHashCode + (bool2 != null ? bool2.hashCode() : 0)) * 31;
            Integer num = this.adInfoTextSize;
            int iHashCode3 = (iHashCode2 + (num != null ? num.hashCode() : 0)) * 31;
            Boolean bool3 = this.loggingEnabled;
            int iHashCode4 = (iHashCode3 + (bool3 != null ? bool3.hashCode() : 0)) * 31;
            Boolean bool4 = this.trackingEnabled;
            int iHashCode5 = (iHashCode4 + (bool4 != null ? bool4.hashCode() : 0)) * 31;
            Boolean bool5 = this.displayAdBannerBounds;
            int iHashCode6 = (iHashCode5 + (bool5 != null ? bool5.hashCode() : 0)) * 31;
            Boolean bool6 = this.shouldFetchImagesFromUIOP;
            int iHashCode7 = (iHashCode6 + (bool6 != null ? bool6.hashCode() : 0)) * 31;
            Boolean bool7 = this.shouldOverrideRequestParams;
            int iHashCode8 = (iHashCode7 + (bool7 != null ? bool7.hashCode() : 0)) * 31;
            AdRequestParamOverride adRequestParamOverride = this.adRequestParamOverride;
            return iHashCode8 + (adRequestParamOverride != null ? adRequestParamOverride.hashCode() : 0);
        }

        public String toString() {
            return "DebuggingSetup(debuggingEnabled=" + this.debuggingEnabled + ", displayAdInfo=" + this.displayAdInfo + ", adInfoTextSize=" + this.adInfoTextSize + ", loggingEnabled=" + this.loggingEnabled + ", trackingEnabled=" + this.trackingEnabled + ", displayAdBannerBounds=" + this.displayAdBannerBounds + ", shouldFetchImagesFromUIOP=" + this.shouldFetchImagesFromUIOP + ", shouldOverrideRequestParams=" + this.shouldOverrideRequestParams + ", adRequestParamOverride=" + this.adRequestParamOverride + ")";
        }

        public DebuggingSetup(Boolean bool, Boolean bool2, Integer num, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, Boolean bool7, AdRequestParamOverride adRequestParamOverride) {
            this.debuggingEnabled = bool;
            this.displayAdInfo = bool2;
            this.adInfoTextSize = num;
            this.loggingEnabled = bool3;
            this.trackingEnabled = bool4;
            this.displayAdBannerBounds = bool5;
            this.shouldFetchImagesFromUIOP = bool6;
            this.shouldOverrideRequestParams = bool7;
            this.adRequestParamOverride = adRequestParamOverride;
        }

        public final Boolean getDebuggingEnabled() {
            return this.debuggingEnabled;
        }

        public final Boolean getDisplayAdInfo() {
            return this.displayAdInfo;
        }

        public final Integer getAdInfoTextSize() {
            return this.adInfoTextSize;
        }

        public final Boolean getLoggingEnabled() {
            return this.loggingEnabled;
        }

        public final Boolean getTrackingEnabled() {
            return this.trackingEnabled;
        }

        public final Boolean getDisplayAdBannerBounds() {
            return this.displayAdBannerBounds;
        }

        public final Boolean getShouldFetchImagesFromUIOP() {
            return this.shouldFetchImagesFromUIOP;
        }

        public final Boolean getShouldOverrideRequestParams() {
            return this.shouldOverrideRequestParams;
        }

        public final AdRequestParamOverride getAdRequestParamOverride() {
            return this.adRequestParamOverride;
        }
    }

    /* JADX INFO: compiled from: TargetedAdsConfigProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J9\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/dotscreen/portal/ads/TargetAdvertisingConfig$AdRequestParamOverride;", "", "accountId", "", "adUnitId", ToolboxAnalyticsModuleKt.EVENT_PROFILE_ID, "terminalModelId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccountId", "()Ljava/lang/String;", "getAdUnitId", "getProfileId", "getTerminalModelId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class AdRequestParamOverride {
        private final String accountId;
        private final String adUnitId;
        private final String profileId;
        private final String terminalModelId;

        public static /* synthetic */ AdRequestParamOverride copy$default(AdRequestParamOverride adRequestParamOverride, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = adRequestParamOverride.accountId;
            }
            if ((i & 2) != 0) {
                str2 = adRequestParamOverride.adUnitId;
            }
            if ((i & 4) != 0) {
                str3 = adRequestParamOverride.profileId;
            }
            if ((i & 8) != 0) {
                str4 = adRequestParamOverride.terminalModelId;
            }
            return adRequestParamOverride.copy(str, str2, str3, str4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAccountId() {
            return this.accountId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getAdUnitId() {
            return this.adUnitId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getProfileId() {
            return this.profileId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getTerminalModelId() {
            return this.terminalModelId;
        }

        public final AdRequestParamOverride copy(String accountId, String adUnitId, String profileId, String terminalModelId) {
            return new AdRequestParamOverride(accountId, adUnitId, profileId, terminalModelId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AdRequestParamOverride)) {
                return false;
            }
            AdRequestParamOverride adRequestParamOverride = (AdRequestParamOverride) other;
            return Intrinsics.areEqual(this.accountId, adRequestParamOverride.accountId) && Intrinsics.areEqual(this.adUnitId, adRequestParamOverride.adUnitId) && Intrinsics.areEqual(this.profileId, adRequestParamOverride.profileId) && Intrinsics.areEqual(this.terminalModelId, adRequestParamOverride.terminalModelId);
        }

        public int hashCode() {
            String str = this.accountId;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.adUnitId;
            int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.profileId;
            int iHashCode3 = (iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.terminalModelId;
            return iHashCode3 + (str4 != null ? str4.hashCode() : 0);
        }

        public String toString() {
            return "AdRequestParamOverride(accountId=" + this.accountId + ", adUnitId=" + this.adUnitId + ", profileId=" + this.profileId + ", terminalModelId=" + this.terminalModelId + ")";
        }

        public AdRequestParamOverride(String str, String str2, String str3, String str4) {
            this.accountId = str;
            this.adUnitId = str2;
            this.profileId = str3;
            this.terminalModelId = str4;
        }

        public final String getAccountId() {
            return this.accountId;
        }

        public final String getAdUnitId() {
            return this.adUnitId;
        }

        public final String getProfileId() {
            return this.profileId;
        }

        public final String getTerminalModelId() {
            return this.terminalModelId;
        }
    }
}
