package com.dotscreen.tv.common.bokit;

import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0006HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RightVVideoTicket;", "", "assetExternalId", "", "videoExternalId", "endDate", "Ljava/util/Date;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Date;)V", "getAssetExternalId", "()Ljava/lang/String;", "getEndDate", "()Ljava/util/Date;", "getVideoExternalId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final /* data */ class RightVVideoTicket {
    private final String assetExternalId;
    private final Date endDate;
    private final String videoExternalId;

    public static /* synthetic */ RightVVideoTicket copy$default(RightVVideoTicket rightVVideoTicket, String str, String str2, Date date, int i, Object obj) {
        if ((i & 1) != 0) {
            str = rightVVideoTicket.assetExternalId;
        }
        if ((i & 2) != 0) {
            str2 = rightVVideoTicket.videoExternalId;
        }
        if ((i & 4) != 0) {
            date = rightVVideoTicket.endDate;
        }
        return rightVVideoTicket.copy(str, str2, date);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAssetExternalId() {
        return this.assetExternalId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getVideoExternalId() {
        return this.videoExternalId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Date getEndDate() {
        return this.endDate;
    }

    public final RightVVideoTicket copy(String assetExternalId, String videoExternalId, Date endDate) {
        Intrinsics.checkParameterIsNotNull(assetExternalId, "assetExternalId");
        Intrinsics.checkParameterIsNotNull(videoExternalId, "videoExternalId");
        return new RightVVideoTicket(assetExternalId, videoExternalId, endDate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RightVVideoTicket)) {
            return false;
        }
        RightVVideoTicket rightVVideoTicket = (RightVVideoTicket) other;
        return Intrinsics.areEqual(this.assetExternalId, rightVVideoTicket.assetExternalId) && Intrinsics.areEqual(this.videoExternalId, rightVVideoTicket.videoExternalId) && Intrinsics.areEqual(this.endDate, rightVVideoTicket.endDate);
    }

    public int hashCode() {
        String str = this.assetExternalId;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.videoExternalId;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Date date = this.endDate;
        return iHashCode2 + (date != null ? date.hashCode() : 0);
    }

    public String toString() {
        return "RightVVideoTicket(assetExternalId=" + this.assetExternalId + ", videoExternalId=" + this.videoExternalId + ", endDate=" + this.endDate + ")";
    }

    public RightVVideoTicket(String assetExternalId, String videoExternalId, Date date) {
        Intrinsics.checkParameterIsNotNull(assetExternalId, "assetExternalId");
        Intrinsics.checkParameterIsNotNull(videoExternalId, "videoExternalId");
        this.assetExternalId = assetExternalId;
        this.videoExternalId = videoExternalId;
        this.endDate = date;
    }

    public final String getAssetExternalId() {
        return this.assetExternalId;
    }

    public final String getVideoExternalId() {
        return this.videoExternalId;
    }

    public final Date getEndDate() {
        return this.endDate;
    }
}
