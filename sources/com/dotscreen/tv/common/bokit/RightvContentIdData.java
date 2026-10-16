package com.dotscreen.tv.common.bokit;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003JQ\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001e"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RightvContentIdData;", "", "contentType", "", "contentId", "externalPageId", "attachmentName", "videoExternalId", "assetExternalId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAssetExternalId", "()Ljava/lang/String;", "getAttachmentName", "getContentId", "getContentType", "getExternalPageId", "getVideoExternalId", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final /* data */ class RightvContentIdData {
    private final String assetExternalId;
    private final String attachmentName;
    private final String contentId;
    private final String contentType;
    private final String externalPageId;
    private final String videoExternalId;

    public static /* synthetic */ RightvContentIdData copy$default(RightvContentIdData rightvContentIdData, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = rightvContentIdData.contentType;
        }
        if ((i & 2) != 0) {
            str2 = rightvContentIdData.contentId;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = rightvContentIdData.externalPageId;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = rightvContentIdData.attachmentName;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = rightvContentIdData.videoExternalId;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = rightvContentIdData.assetExternalId;
        }
        return rightvContentIdData.copy(str, str7, str8, str9, str10, str6);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getContentType() {
        return this.contentType;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getContentId() {
        return this.contentId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getExternalPageId() {
        return this.externalPageId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getAttachmentName() {
        return this.attachmentName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getVideoExternalId() {
        return this.videoExternalId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getAssetExternalId() {
        return this.assetExternalId;
    }

    public final RightvContentIdData copy(String contentType, String contentId, String externalPageId, String attachmentName, String videoExternalId, String assetExternalId) {
        return new RightvContentIdData(contentType, contentId, externalPageId, attachmentName, videoExternalId, assetExternalId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RightvContentIdData)) {
            return false;
        }
        RightvContentIdData rightvContentIdData = (RightvContentIdData) other;
        return Intrinsics.areEqual(this.contentType, rightvContentIdData.contentType) && Intrinsics.areEqual(this.contentId, rightvContentIdData.contentId) && Intrinsics.areEqual(this.externalPageId, rightvContentIdData.externalPageId) && Intrinsics.areEqual(this.attachmentName, rightvContentIdData.attachmentName) && Intrinsics.areEqual(this.videoExternalId, rightvContentIdData.videoExternalId) && Intrinsics.areEqual(this.assetExternalId, rightvContentIdData.assetExternalId);
    }

    public int hashCode() {
        String str = this.contentType;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.contentId;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.externalPageId;
        int iHashCode3 = (iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.attachmentName;
        int iHashCode4 = (iHashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.videoExternalId;
        int iHashCode5 = (iHashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.assetExternalId;
        return iHashCode5 + (str6 != null ? str6.hashCode() : 0);
    }

    public String toString() {
        return "RightvContentIdData(contentType=" + this.contentType + ", contentId=" + this.contentId + ", externalPageId=" + this.externalPageId + ", attachmentName=" + this.attachmentName + ", videoExternalId=" + this.videoExternalId + ", assetExternalId=" + this.assetExternalId + ")";
    }

    public RightvContentIdData(String str, String str2, String str3, String str4, String str5, String str6) {
        this.contentType = str;
        this.contentId = str2;
        this.externalPageId = str3;
        this.attachmentName = str4;
        this.videoExternalId = str5;
        this.assetExternalId = str6;
    }

    public final String getContentType() {
        return this.contentType;
    }

    public final String getContentId() {
        return this.contentId;
    }

    public final String getExternalPageId() {
        return this.externalPageId;
    }

    public final String getAttachmentName() {
        return this.attachmentName;
    }

    public final String getVideoExternalId() {
        return this.videoExternalId;
    }

    public final String getAssetExternalId() {
        return this.assetExternalId;
    }
}
