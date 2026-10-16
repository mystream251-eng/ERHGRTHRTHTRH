package com.dotscreen.portal.ads;

import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AdModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001:\t456789:;<BW\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010%\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u0010&\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bHÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003Jp\u0010,\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010-J\u0013\u0010.\u001a\u00020\u00052\b\u0010/\u001a\u0004\u0018\u000100HÖ\u0003J\t\u00101\u001a\u000202HÖ\u0001J\t\u00103\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0012\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0012R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010!¨\u0006="}, d2 = {"Lcom/dotscreen/portal/ads/Ad;", "Lcom/dotscreen/bokit/model/BrowsableItem;", "dsAdUnitId", "", "dsIsSelectable", "", "dsNativeAdType", "Lcom/dotscreen/portal/ads/Ad$DSNativeAdType;", "adUnitId", "type", "imageResources", "", "Lcom/dotscreen/portal/ads/Ad$ImageResource;", "videoResources", "Lcom/dotscreen/portal/ads/Ad$VideoResource;", "advertisingVideoResourceVAST", "(Ljava/lang/String;Ljava/lang/Boolean;Lcom/dotscreen/portal/ads/Ad$DSNativeAdType;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;)V", "getAdUnitId", "()Ljava/lang/String;", "getAdvertisingVideoResourceVAST", "getDsAdUnitId", "setDsAdUnitId", "(Ljava/lang/String;)V", "getDsIsSelectable", "()Ljava/lang/Boolean;", "setDsIsSelectable", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getDsNativeAdType", "()Lcom/dotscreen/portal/ads/Ad$DSNativeAdType;", "setDsNativeAdType", "(Lcom/dotscreen/portal/ads/Ad$DSNativeAdType;)V", "getImageResources", "()Ljava/util/List;", "getType", "getVideoResources", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/Boolean;Lcom/dotscreen/portal/ads/Ad$DSNativeAdType;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;)Lcom/dotscreen/portal/ads/Ad;", "equals", "other", "", "hashCode", "", "toString", "DSNativeAdType", "DisplayTrigger", "HorizontalAlignment", "ImageResource", "PositionType", "ResourceTemplate", "TrackingURLs", "VerticalAlignment", "VideoResource", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final /* data */ class Ad extends BrowsableItem {
    private final String adUnitId;
    private final String advertisingVideoResourceVAST;
    private String dsAdUnitId;
    private Boolean dsIsSelectable;
    private DSNativeAdType dsNativeAdType;
    private final List<ImageResource> imageResources;
    private final String type;
    private final List<VideoResource> videoResources;

    /* JADX INFO: compiled from: AdModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/dotscreen/portal/ads/Ad$DSNativeAdType;", "", "(Ljava/lang/String;I)V", "IN_LAYOUT", "IN_SWIMLANE", "CUSTOM", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public enum DSNativeAdType {
        IN_LAYOUT,
        IN_SWIMLANE,
        CUSTOM
    }

    /* JADX INFO: compiled from: AdModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/dotscreen/portal/ads/Ad$DisplayTrigger;", "", "(Ljava/lang/String;I)V", "Auto", "OnOffset", "OnBrowse", "OnSelect", "OnClick", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public enum DisplayTrigger {
        Auto,
        OnOffset,
        OnBrowse,
        OnSelect,
        OnClick
    }

    /* JADX INFO: compiled from: AdModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/dotscreen/portal/ads/Ad$HorizontalAlignment;", "", "(Ljava/lang/String;I)V", TtmlNode.LEFT, TtmlNode.CENTER, TtmlNode.RIGHT, "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public enum HorizontalAlignment {
        left,
        center,
        right
    }

    /* JADX INFO: compiled from: AdModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/dotscreen/portal/ads/Ad$PositionType;", "", "(Ljava/lang/String;I)V", "floating", "native", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public enum PositionType {
        floating,
        f0native
    }

    /* JADX INFO: compiled from: AdModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/dotscreen/portal/ads/Ad$VerticalAlignment;", "", "(Ljava/lang/String;I)V", "top", TtmlNode.CENTER, "bottom", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public enum VerticalAlignment {
        top,
        center,
        bottom
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDsAdUnitId() {
        return this.dsAdUnitId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Boolean getDsIsSelectable() {
        return this.dsIsSelectable;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final DSNativeAdType getDsNativeAdType() {
        return this.dsNativeAdType;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getAdUnitId() {
        return this.adUnitId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getType() {
        return this.type;
    }

    public final List<ImageResource> component6() {
        return this.imageResources;
    }

    public final List<VideoResource> component7() {
        return this.videoResources;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getAdvertisingVideoResourceVAST() {
        return this.advertisingVideoResourceVAST;
    }

    public final Ad copy(String dsAdUnitId, Boolean dsIsSelectable, DSNativeAdType dsNativeAdType, String adUnitId, String type, List<ImageResource> imageResources, List<VideoResource> videoResources, String advertisingVideoResourceVAST) {
        Intrinsics.checkParameterIsNotNull(adUnitId, "adUnitId");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(imageResources, "imageResources");
        Intrinsics.checkParameterIsNotNull(videoResources, "videoResources");
        Intrinsics.checkParameterIsNotNull(advertisingVideoResourceVAST, "advertisingVideoResourceVAST");
        return new Ad(dsAdUnitId, dsIsSelectable, dsNativeAdType, adUnitId, type, imageResources, videoResources, advertisingVideoResourceVAST);
    }

    @Override // com.dotscreen.bokit.model.BrowsableItem, com.dotscreen.bokit.model.Item
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Ad)) {
            return false;
        }
        Ad ad = (Ad) other;
        return Intrinsics.areEqual(this.dsAdUnitId, ad.dsAdUnitId) && Intrinsics.areEqual(this.dsIsSelectable, ad.dsIsSelectable) && Intrinsics.areEqual(this.dsNativeAdType, ad.dsNativeAdType) && Intrinsics.areEqual(this.adUnitId, ad.adUnitId) && Intrinsics.areEqual(this.type, ad.type) && Intrinsics.areEqual(this.imageResources, ad.imageResources) && Intrinsics.areEqual(this.videoResources, ad.videoResources) && Intrinsics.areEqual(this.advertisingVideoResourceVAST, ad.advertisingVideoResourceVAST);
    }

    @Override // com.dotscreen.bokit.model.BrowsableItem, com.dotscreen.bokit.model.Item
    public int hashCode() {
        String str = this.dsAdUnitId;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        Boolean bool = this.dsIsSelectable;
        int iHashCode2 = (iHashCode + (bool != null ? bool.hashCode() : 0)) * 31;
        DSNativeAdType dSNativeAdType = this.dsNativeAdType;
        int iHashCode3 = (iHashCode2 + (dSNativeAdType != null ? dSNativeAdType.hashCode() : 0)) * 31;
        String str2 = this.adUnitId;
        int iHashCode4 = (iHashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.type;
        int iHashCode5 = (iHashCode4 + (str3 != null ? str3.hashCode() : 0)) * 31;
        List<ImageResource> list = this.imageResources;
        int iHashCode6 = (iHashCode5 + (list != null ? list.hashCode() : 0)) * 31;
        List<VideoResource> list2 = this.videoResources;
        int iHashCode7 = (iHashCode6 + (list2 != null ? list2.hashCode() : 0)) * 31;
        String str4 = this.advertisingVideoResourceVAST;
        return iHashCode7 + (str4 != null ? str4.hashCode() : 0);
    }

    @Override // com.dotscreen.bokit.model.Item
    public String toString() {
        return "Ad(dsAdUnitId=" + this.dsAdUnitId + ", dsIsSelectable=" + this.dsIsSelectable + ", dsNativeAdType=" + this.dsNativeAdType + ", adUnitId=" + this.adUnitId + ", type=" + this.type + ", imageResources=" + this.imageResources + ", videoResources=" + this.videoResources + ", advertisingVideoResourceVAST=" + this.advertisingVideoResourceVAST + ")";
    }

    public final String getDsAdUnitId() {
        return this.dsAdUnitId;
    }

    public final void setDsAdUnitId(String str) {
        this.dsAdUnitId = str;
    }

    public final Boolean getDsIsSelectable() {
        return this.dsIsSelectable;
    }

    public final void setDsIsSelectable(Boolean bool) {
        this.dsIsSelectable = bool;
    }

    public final DSNativeAdType getDsNativeAdType() {
        return this.dsNativeAdType;
    }

    public final void setDsNativeAdType(DSNativeAdType dSNativeAdType) {
        this.dsNativeAdType = dSNativeAdType;
    }

    public final String getAdUnitId() {
        return this.adUnitId;
    }

    public final String getType() {
        return this.type;
    }

    public final List<ImageResource> getImageResources() {
        return this.imageResources;
    }

    public final List<VideoResource> getVideoResources() {
        return this.videoResources;
    }

    public final String getAdvertisingVideoResourceVAST() {
        return this.advertisingVideoResourceVAST;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Ad(String str, Boolean bool, DSNativeAdType dSNativeAdType, String adUnitId, String type, List<ImageResource> imageResources, List<VideoResource> videoResources, String advertisingVideoResourceVAST) {
        super(adUnitId, "", null, null, null, false, null, 124, null);
        Intrinsics.checkParameterIsNotNull(adUnitId, "adUnitId");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(imageResources, "imageResources");
        Intrinsics.checkParameterIsNotNull(videoResources, "videoResources");
        Intrinsics.checkParameterIsNotNull(advertisingVideoResourceVAST, "advertisingVideoResourceVAST");
        this.dsAdUnitId = str;
        this.dsIsSelectable = bool;
        this.dsNativeAdType = dSNativeAdType;
        this.adUnitId = adUnitId;
        this.type = type;
        this.imageResources = imageResources;
        this.videoResources = videoResources;
        this.advertisingVideoResourceVAST = advertisingVideoResourceVAST;
    }

    /* JADX INFO: compiled from: AdModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0007HÆ\u0003J\t\u0010 \u001a\u00020\u000bHÆ\u0003J\t\u0010!\u001a\u00020\rHÆ\u0003JO\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020'HÖ\u0001J\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016¨\u0006)"}, d2 = {"Lcom/dotscreen/portal/ads/Ad$ImageResource;", "", "resourceURL", "", "whenResourceIsDisplayed", "Lcom/dotscreen/portal/ads/Ad$DisplayTrigger;", "whenResourceIsDisplayedOffset", "", "whenCloseButtonAppears", "whenResourceDisappears", "trackingURLs", "Lcom/dotscreen/portal/ads/Ad$TrackingURLs;", "resourceTemplate", "Lcom/dotscreen/portal/ads/Ad$ResourceTemplate;", "(Ljava/lang/String;Lcom/dotscreen/portal/ads/Ad$DisplayTrigger;JJJLcom/dotscreen/portal/ads/Ad$TrackingURLs;Lcom/dotscreen/portal/ads/Ad$ResourceTemplate;)V", "getResourceTemplate", "()Lcom/dotscreen/portal/ads/Ad$ResourceTemplate;", "getResourceURL", "()Ljava/lang/String;", "getTrackingURLs", "()Lcom/dotscreen/portal/ads/Ad$TrackingURLs;", "getWhenCloseButtonAppears", "()J", "getWhenResourceDisappears", "getWhenResourceIsDisplayed", "()Lcom/dotscreen/portal/ads/Ad$DisplayTrigger;", "getWhenResourceIsDisplayedOffset", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class ImageResource {
        private final ResourceTemplate resourceTemplate;
        private final String resourceURL;
        private final TrackingURLs trackingURLs;
        private final long whenCloseButtonAppears;
        private final long whenResourceDisappears;
        private final DisplayTrigger whenResourceIsDisplayed;
        private final long whenResourceIsDisplayedOffset;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getResourceURL() {
            return this.resourceURL;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final DisplayTrigger getWhenResourceIsDisplayed() {
            return this.whenResourceIsDisplayed;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getWhenResourceIsDisplayedOffset() {
            return this.whenResourceIsDisplayedOffset;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getWhenCloseButtonAppears() {
            return this.whenCloseButtonAppears;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final long getWhenResourceDisappears() {
            return this.whenResourceDisappears;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final TrackingURLs getTrackingURLs() {
            return this.trackingURLs;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final ResourceTemplate getResourceTemplate() {
            return this.resourceTemplate;
        }

        public final ImageResource copy(String resourceURL, DisplayTrigger whenResourceIsDisplayed, long whenResourceIsDisplayedOffset, long whenCloseButtonAppears, long whenResourceDisappears, TrackingURLs trackingURLs, ResourceTemplate resourceTemplate) {
            Intrinsics.checkParameterIsNotNull(resourceURL, "resourceURL");
            Intrinsics.checkParameterIsNotNull(whenResourceIsDisplayed, "whenResourceIsDisplayed");
            Intrinsics.checkParameterIsNotNull(trackingURLs, "trackingURLs");
            Intrinsics.checkParameterIsNotNull(resourceTemplate, "resourceTemplate");
            return new ImageResource(resourceURL, whenResourceIsDisplayed, whenResourceIsDisplayedOffset, whenCloseButtonAppears, whenResourceDisappears, trackingURLs, resourceTemplate);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ImageResource)) {
                return false;
            }
            ImageResource imageResource = (ImageResource) other;
            return Intrinsics.areEqual(this.resourceURL, imageResource.resourceURL) && Intrinsics.areEqual(this.whenResourceIsDisplayed, imageResource.whenResourceIsDisplayed) && this.whenResourceIsDisplayedOffset == imageResource.whenResourceIsDisplayedOffset && this.whenCloseButtonAppears == imageResource.whenCloseButtonAppears && this.whenResourceDisappears == imageResource.whenResourceDisappears && Intrinsics.areEqual(this.trackingURLs, imageResource.trackingURLs) && Intrinsics.areEqual(this.resourceTemplate, imageResource.resourceTemplate);
        }

        public int hashCode() {
            String str = this.resourceURL;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            DisplayTrigger displayTrigger = this.whenResourceIsDisplayed;
            int iHashCode2 = (((((((iHashCode + (displayTrigger != null ? displayTrigger.hashCode() : 0)) * 31) + Long.hashCode(this.whenResourceIsDisplayedOffset)) * 31) + Long.hashCode(this.whenCloseButtonAppears)) * 31) + Long.hashCode(this.whenResourceDisappears)) * 31;
            TrackingURLs trackingURLs = this.trackingURLs;
            int iHashCode3 = (iHashCode2 + (trackingURLs != null ? trackingURLs.hashCode() : 0)) * 31;
            ResourceTemplate resourceTemplate = this.resourceTemplate;
            return iHashCode3 + (resourceTemplate != null ? resourceTemplate.hashCode() : 0);
        }

        public String toString() {
            return "ImageResource(resourceURL=" + this.resourceURL + ", whenResourceIsDisplayed=" + this.whenResourceIsDisplayed + ", whenResourceIsDisplayedOffset=" + this.whenResourceIsDisplayedOffset + ", whenCloseButtonAppears=" + this.whenCloseButtonAppears + ", whenResourceDisappears=" + this.whenResourceDisappears + ", trackingURLs=" + this.trackingURLs + ", resourceTemplate=" + this.resourceTemplate + ")";
        }

        public ImageResource(String resourceURL, DisplayTrigger whenResourceIsDisplayed, long j, long j2, long j3, TrackingURLs trackingURLs, ResourceTemplate resourceTemplate) {
            Intrinsics.checkParameterIsNotNull(resourceURL, "resourceURL");
            Intrinsics.checkParameterIsNotNull(whenResourceIsDisplayed, "whenResourceIsDisplayed");
            Intrinsics.checkParameterIsNotNull(trackingURLs, "trackingURLs");
            Intrinsics.checkParameterIsNotNull(resourceTemplate, "resourceTemplate");
            this.resourceURL = resourceURL;
            this.whenResourceIsDisplayed = whenResourceIsDisplayed;
            this.whenResourceIsDisplayedOffset = j;
            this.whenCloseButtonAppears = j2;
            this.whenResourceDisappears = j3;
            this.trackingURLs = trackingURLs;
            this.resourceTemplate = resourceTemplate;
        }

        public final String getResourceURL() {
            return this.resourceURL;
        }

        public final DisplayTrigger getWhenResourceIsDisplayed() {
            return this.whenResourceIsDisplayed;
        }

        public final long getWhenResourceIsDisplayedOffset() {
            return this.whenResourceIsDisplayedOffset;
        }

        public final long getWhenCloseButtonAppears() {
            return this.whenCloseButtonAppears;
        }

        public final long getWhenResourceDisappears() {
            return this.whenResourceDisappears;
        }

        public final TrackingURLs getTrackingURLs() {
            return this.trackingURLs;
        }

        public final ResourceTemplate getResourceTemplate() {
            return this.resourceTemplate;
        }
    }

    /* JADX INFO: compiled from: AdModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0007HÆ\u0003J\t\u0010 \u001a\u00020\u000bHÆ\u0003J\t\u0010!\u001a\u00020\rHÆ\u0003JO\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020'HÖ\u0001J\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016¨\u0006)"}, d2 = {"Lcom/dotscreen/portal/ads/Ad$VideoResource;", "", "resourceURL", "", "whenResourceIsDisplayed", "Lcom/dotscreen/portal/ads/Ad$DisplayTrigger;", "whenResourceIsDisplayedOffset", "", "whenCloseButtonAppears", "whenResourceDisappears", "trackingURLs", "Lcom/dotscreen/portal/ads/Ad$TrackingURLs;", "resourceTemplate", "Lcom/dotscreen/portal/ads/Ad$ResourceTemplate;", "(Ljava/lang/String;Lcom/dotscreen/portal/ads/Ad$DisplayTrigger;JJJLcom/dotscreen/portal/ads/Ad$TrackingURLs;Lcom/dotscreen/portal/ads/Ad$ResourceTemplate;)V", "getResourceTemplate", "()Lcom/dotscreen/portal/ads/Ad$ResourceTemplate;", "getResourceURL", "()Ljava/lang/String;", "getTrackingURLs", "()Lcom/dotscreen/portal/ads/Ad$TrackingURLs;", "getWhenCloseButtonAppears", "()J", "getWhenResourceDisappears", "getWhenResourceIsDisplayed", "()Lcom/dotscreen/portal/ads/Ad$DisplayTrigger;", "getWhenResourceIsDisplayedOffset", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class VideoResource {
        private final ResourceTemplate resourceTemplate;
        private final String resourceURL;
        private final TrackingURLs trackingURLs;
        private final long whenCloseButtonAppears;
        private final long whenResourceDisappears;
        private final DisplayTrigger whenResourceIsDisplayed;
        private final long whenResourceIsDisplayedOffset;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getResourceURL() {
            return this.resourceURL;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final DisplayTrigger getWhenResourceIsDisplayed() {
            return this.whenResourceIsDisplayed;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getWhenResourceIsDisplayedOffset() {
            return this.whenResourceIsDisplayedOffset;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getWhenCloseButtonAppears() {
            return this.whenCloseButtonAppears;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final long getWhenResourceDisappears() {
            return this.whenResourceDisappears;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final TrackingURLs getTrackingURLs() {
            return this.trackingURLs;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final ResourceTemplate getResourceTemplate() {
            return this.resourceTemplate;
        }

        public final VideoResource copy(String resourceURL, DisplayTrigger whenResourceIsDisplayed, long whenResourceIsDisplayedOffset, long whenCloseButtonAppears, long whenResourceDisappears, TrackingURLs trackingURLs, ResourceTemplate resourceTemplate) {
            Intrinsics.checkParameterIsNotNull(resourceURL, "resourceURL");
            Intrinsics.checkParameterIsNotNull(whenResourceIsDisplayed, "whenResourceIsDisplayed");
            Intrinsics.checkParameterIsNotNull(trackingURLs, "trackingURLs");
            Intrinsics.checkParameterIsNotNull(resourceTemplate, "resourceTemplate");
            return new VideoResource(resourceURL, whenResourceIsDisplayed, whenResourceIsDisplayedOffset, whenCloseButtonAppears, whenResourceDisappears, trackingURLs, resourceTemplate);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoResource)) {
                return false;
            }
            VideoResource videoResource = (VideoResource) other;
            return Intrinsics.areEqual(this.resourceURL, videoResource.resourceURL) && Intrinsics.areEqual(this.whenResourceIsDisplayed, videoResource.whenResourceIsDisplayed) && this.whenResourceIsDisplayedOffset == videoResource.whenResourceIsDisplayedOffset && this.whenCloseButtonAppears == videoResource.whenCloseButtonAppears && this.whenResourceDisappears == videoResource.whenResourceDisappears && Intrinsics.areEqual(this.trackingURLs, videoResource.trackingURLs) && Intrinsics.areEqual(this.resourceTemplate, videoResource.resourceTemplate);
        }

        public int hashCode() {
            String str = this.resourceURL;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            DisplayTrigger displayTrigger = this.whenResourceIsDisplayed;
            int iHashCode2 = (((((((iHashCode + (displayTrigger != null ? displayTrigger.hashCode() : 0)) * 31) + Long.hashCode(this.whenResourceIsDisplayedOffset)) * 31) + Long.hashCode(this.whenCloseButtonAppears)) * 31) + Long.hashCode(this.whenResourceDisappears)) * 31;
            TrackingURLs trackingURLs = this.trackingURLs;
            int iHashCode3 = (iHashCode2 + (trackingURLs != null ? trackingURLs.hashCode() : 0)) * 31;
            ResourceTemplate resourceTemplate = this.resourceTemplate;
            return iHashCode3 + (resourceTemplate != null ? resourceTemplate.hashCode() : 0);
        }

        public String toString() {
            return "VideoResource(resourceURL=" + this.resourceURL + ", whenResourceIsDisplayed=" + this.whenResourceIsDisplayed + ", whenResourceIsDisplayedOffset=" + this.whenResourceIsDisplayedOffset + ", whenCloseButtonAppears=" + this.whenCloseButtonAppears + ", whenResourceDisappears=" + this.whenResourceDisappears + ", trackingURLs=" + this.trackingURLs + ", resourceTemplate=" + this.resourceTemplate + ")";
        }

        public VideoResource(String resourceURL, DisplayTrigger whenResourceIsDisplayed, long j, long j2, long j3, TrackingURLs trackingURLs, ResourceTemplate resourceTemplate) {
            Intrinsics.checkParameterIsNotNull(resourceURL, "resourceURL");
            Intrinsics.checkParameterIsNotNull(whenResourceIsDisplayed, "whenResourceIsDisplayed");
            Intrinsics.checkParameterIsNotNull(trackingURLs, "trackingURLs");
            Intrinsics.checkParameterIsNotNull(resourceTemplate, "resourceTemplate");
            this.resourceURL = resourceURL;
            this.whenResourceIsDisplayed = whenResourceIsDisplayed;
            this.whenResourceIsDisplayedOffset = j;
            this.whenCloseButtonAppears = j2;
            this.whenResourceDisappears = j3;
            this.trackingURLs = trackingURLs;
            this.resourceTemplate = resourceTemplate;
        }

        public final String getResourceURL() {
            return this.resourceURL;
        }

        public final DisplayTrigger getWhenResourceIsDisplayed() {
            return this.whenResourceIsDisplayed;
        }

        public final long getWhenResourceIsDisplayedOffset() {
            return this.whenResourceIsDisplayedOffset;
        }

        public final long getWhenCloseButtonAppears() {
            return this.whenCloseButtonAppears;
        }

        public final long getWhenResourceDisappears() {
            return this.whenResourceDisappears;
        }

        public final TrackingURLs getTrackingURLs() {
            return this.trackingURLs;
        }

        public final ResourceTemplate getResourceTemplate() {
            return this.resourceTemplate;
        }
    }

    /* JADX INFO: compiled from: AdModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/dotscreen/portal/ads/Ad$TrackingURLs;", "", "start", "", "(Ljava/lang/String;)V", "getStart", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class TrackingURLs {
        private final String start;

        public static /* synthetic */ TrackingURLs copy$default(TrackingURLs trackingURLs, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = trackingURLs.start;
            }
            return trackingURLs.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getStart() {
            return this.start;
        }

        public final TrackingURLs copy(String start) {
            Intrinsics.checkParameterIsNotNull(start, "start");
            return new TrackingURLs(start);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof TrackingURLs) && Intrinsics.areEqual(this.start, ((TrackingURLs) other).start);
            }
            return true;
        }

        public int hashCode() {
            String str = this.start;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "TrackingURLs(start=" + this.start + ")";
        }

        public TrackingURLs(String start) {
            Intrinsics.checkParameterIsNotNull(start, "start");
            this.start = start;
        }

        public final String getStart() {
            return this.start;
        }
    }

    /* JADX INFO: compiled from: AdModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u001c\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000b¢\u0006\u0002\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001f\u001a\u00020\tHÆ\u0003J\t\u0010 \u001a\u00020\u000bHÆ\u0003J\t\u0010!\u001a\u00020\u000bHÆ\u0003J\t\u0010\"\u001a\u00020\u000bHÆ\u0003J\t\u0010#\u001a\u00020\u000bHÆ\u0003JY\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000bHÆ\u0001J\u0013\u0010%\u001a\u00020\u00052\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020*HÖ\u0001R\u0011\u0010\u000e\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0011\u0010\r\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011¨\u0006+"}, d2 = {"Lcom/dotscreen/portal/ads/Ad$ResourceTemplate;", "", "position", "Lcom/dotscreen/portal/ads/Ad$PositionType;", "isFullscreen", "", "horizontal", "Lcom/dotscreen/portal/ads/Ad$HorizontalAlignment;", "vertical", "Lcom/dotscreen/portal/ads/Ad$VerticalAlignment;", "verticalOffset", "", "horizontalOffset", ToolboxAnalyticsModuleKt.M_WIDTH, ToolboxAnalyticsModuleKt.M_HEIGHT, "(Lcom/dotscreen/portal/ads/Ad$PositionType;ZLcom/dotscreen/portal/ads/Ad$HorizontalAlignment;Lcom/dotscreen/portal/ads/Ad$VerticalAlignment;JJJJ)V", "getHeight", "()J", "getHorizontal", "()Lcom/dotscreen/portal/ads/Ad$HorizontalAlignment;", "getHorizontalOffset", "()Z", "getPosition", "()Lcom/dotscreen/portal/ads/Ad$PositionType;", "getVertical", "()Lcom/dotscreen/portal/ads/Ad$VerticalAlignment;", "getVerticalOffset", "getWidth", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class ResourceTemplate {
        private final long height;
        private final HorizontalAlignment horizontal;
        private final long horizontalOffset;
        private final boolean isFullscreen;
        private final PositionType position;
        private final VerticalAlignment vertical;
        private final long verticalOffset;
        private final long width;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final PositionType getPosition() {
            return this.position;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsFullscreen() {
            return this.isFullscreen;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final HorizontalAlignment getHorizontal() {
            return this.horizontal;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final VerticalAlignment getVertical() {
            return this.vertical;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final long getVerticalOffset() {
            return this.verticalOffset;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final long getHorizontalOffset() {
            return this.horizontalOffset;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final long getWidth() {
            return this.width;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final long getHeight() {
            return this.height;
        }

        public final ResourceTemplate copy(PositionType position, boolean isFullscreen, HorizontalAlignment horizontal, VerticalAlignment vertical, long verticalOffset, long horizontalOffset, long width, long height) {
            Intrinsics.checkParameterIsNotNull(position, "position");
            Intrinsics.checkParameterIsNotNull(horizontal, "horizontal");
            Intrinsics.checkParameterIsNotNull(vertical, "vertical");
            return new ResourceTemplate(position, isFullscreen, horizontal, vertical, verticalOffset, horizontalOffset, width, height);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ResourceTemplate)) {
                return false;
            }
            ResourceTemplate resourceTemplate = (ResourceTemplate) other;
            return Intrinsics.areEqual(this.position, resourceTemplate.position) && this.isFullscreen == resourceTemplate.isFullscreen && Intrinsics.areEqual(this.horizontal, resourceTemplate.horizontal) && Intrinsics.areEqual(this.vertical, resourceTemplate.vertical) && this.verticalOffset == resourceTemplate.verticalOffset && this.horizontalOffset == resourceTemplate.horizontalOffset && this.width == resourceTemplate.width && this.height == resourceTemplate.height;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v4, types: [int] */
        /* JADX WARN: Type inference failed for: r2v1, types: [int] */
        /* JADX WARN: Type inference failed for: r2v7 */
        /* JADX WARN: Type inference failed for: r2v8 */
        public int hashCode() {
            PositionType positionType = this.position;
            int iHashCode = (positionType != null ? positionType.hashCode() : 0) * 31;
            boolean z = this.isFullscreen;
            ?? r2 = z;
            if (z) {
                r2 = 1;
            }
            int i = (iHashCode + r2) * 31;
            HorizontalAlignment horizontalAlignment = this.horizontal;
            int iHashCode2 = (i + (horizontalAlignment != null ? horizontalAlignment.hashCode() : 0)) * 31;
            VerticalAlignment verticalAlignment = this.vertical;
            return ((((((((iHashCode2 + (verticalAlignment != null ? verticalAlignment.hashCode() : 0)) * 31) + Long.hashCode(this.verticalOffset)) * 31) + Long.hashCode(this.horizontalOffset)) * 31) + Long.hashCode(this.width)) * 31) + Long.hashCode(this.height);
        }

        public String toString() {
            return "ResourceTemplate(position=" + this.position + ", isFullscreen=" + this.isFullscreen + ", horizontal=" + this.horizontal + ", vertical=" + this.vertical + ", verticalOffset=" + this.verticalOffset + ", horizontalOffset=" + this.horizontalOffset + ", width=" + this.width + ", height=" + this.height + ")";
        }

        public ResourceTemplate(PositionType position, boolean z, HorizontalAlignment horizontal, VerticalAlignment vertical, long j, long j2, long j3, long j4) {
            Intrinsics.checkParameterIsNotNull(position, "position");
            Intrinsics.checkParameterIsNotNull(horizontal, "horizontal");
            Intrinsics.checkParameterIsNotNull(vertical, "vertical");
            this.position = position;
            this.isFullscreen = z;
            this.horizontal = horizontal;
            this.vertical = vertical;
            this.verticalOffset = j;
            this.horizontalOffset = j2;
            this.width = j3;
            this.height = j4;
        }

        public final PositionType getPosition() {
            return this.position;
        }

        public final boolean isFullscreen() {
            return this.isFullscreen;
        }

        public final HorizontalAlignment getHorizontal() {
            return this.horizontal;
        }

        public final VerticalAlignment getVertical() {
            return this.vertical;
        }

        public final long getVerticalOffset() {
            return this.verticalOffset;
        }

        public final long getHorizontalOffset() {
            return this.horizontalOffset;
        }

        public final long getWidth() {
            return this.width;
        }

        public final long getHeight() {
            return this.height;
        }
    }
}
