package com.dotscreen.tv.common.bokit;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J=\u0010\u0016\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RightvVideoOfferData;", "", "serviceExternalId", "", "videoExternalId", "pricingOptionId", "type", "toolboxContent", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getPricingOptionId", "()Ljava/lang/String;", "getServiceExternalId", "getToolboxContent", "()Z", "getType", "getVideoExternalId", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final /* data */ class RightvVideoOfferData {
    private final String pricingOptionId;
    private final String serviceExternalId;
    private final boolean toolboxContent;
    private final String type;
    private final String videoExternalId;

    public static /* synthetic */ RightvVideoOfferData copy$default(RightvVideoOfferData rightvVideoOfferData, String str, String str2, String str3, String str4, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = rightvVideoOfferData.serviceExternalId;
        }
        if ((i & 2) != 0) {
            str2 = rightvVideoOfferData.videoExternalId;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = rightvVideoOfferData.pricingOptionId;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = rightvVideoOfferData.type;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            z = rightvVideoOfferData.toolboxContent;
        }
        return rightvVideoOfferData.copy(str, str5, str6, str7, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getServiceExternalId() {
        return this.serviceExternalId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getVideoExternalId() {
        return this.videoExternalId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPricingOptionId() {
        return this.pricingOptionId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getToolboxContent() {
        return this.toolboxContent;
    }

    public final RightvVideoOfferData copy(String serviceExternalId, String videoExternalId, String pricingOptionId, String type, boolean toolboxContent) {
        Intrinsics.checkParameterIsNotNull(videoExternalId, "videoExternalId");
        Intrinsics.checkParameterIsNotNull(pricingOptionId, "pricingOptionId");
        Intrinsics.checkParameterIsNotNull(type, "type");
        return new RightvVideoOfferData(serviceExternalId, videoExternalId, pricingOptionId, type, toolboxContent);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RightvVideoOfferData)) {
            return false;
        }
        RightvVideoOfferData rightvVideoOfferData = (RightvVideoOfferData) other;
        return Intrinsics.areEqual(this.serviceExternalId, rightvVideoOfferData.serviceExternalId) && Intrinsics.areEqual(this.videoExternalId, rightvVideoOfferData.videoExternalId) && Intrinsics.areEqual(this.pricingOptionId, rightvVideoOfferData.pricingOptionId) && Intrinsics.areEqual(this.type, rightvVideoOfferData.type) && this.toolboxContent == rightvVideoOfferData.toolboxContent;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6 */
    public int hashCode() {
        String str = this.serviceExternalId;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.videoExternalId;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.pricingOptionId;
        int iHashCode3 = (iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.type;
        int iHashCode4 = (iHashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        boolean z = this.toolboxContent;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode4 + r1;
    }

    public String toString() {
        return "RightvVideoOfferData(serviceExternalId=" + this.serviceExternalId + ", videoExternalId=" + this.videoExternalId + ", pricingOptionId=" + this.pricingOptionId + ", type=" + this.type + ", toolboxContent=" + this.toolboxContent + ")";
    }

    public RightvVideoOfferData(String str, String videoExternalId, String pricingOptionId, String type, boolean z) {
        Intrinsics.checkParameterIsNotNull(videoExternalId, "videoExternalId");
        Intrinsics.checkParameterIsNotNull(pricingOptionId, "pricingOptionId");
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.serviceExternalId = str;
        this.videoExternalId = videoExternalId;
        this.pricingOptionId = pricingOptionId;
        this.type = type;
        this.toolboxContent = z;
    }

    public final String getServiceExternalId() {
        return this.serviceExternalId;
    }

    public final String getVideoExternalId() {
        return this.videoExternalId;
    }

    public final String getPricingOptionId() {
        return this.pricingOptionId;
    }

    public final String getType() {
        return this.type;
    }

    public /* synthetic */ RightvVideoOfferData(String str, String str2, String str3, String str4, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, (i & 16) != 0 ? false : z);
    }

    public final boolean getToolboxContent() {
        return this.toolboxContent;
    }
}
