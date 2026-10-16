package com.dotscreen.tv.common.bokit;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RightVServiceSubscriptionInfo;", "", "externalId", "", "model", "(Ljava/lang/String;Ljava/lang/String;)V", "getExternalId", "()Ljava/lang/String;", "getModel", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final /* data */ class RightVServiceSubscriptionInfo {
    private final String externalId;
    private final String model;

    public static /* synthetic */ RightVServiceSubscriptionInfo copy$default(RightVServiceSubscriptionInfo rightVServiceSubscriptionInfo, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = rightVServiceSubscriptionInfo.externalId;
        }
        if ((i & 2) != 0) {
            str2 = rightVServiceSubscriptionInfo.model;
        }
        return rightVServiceSubscriptionInfo.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getExternalId() {
        return this.externalId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getModel() {
        return this.model;
    }

    public final RightVServiceSubscriptionInfo copy(String externalId, String model) {
        Intrinsics.checkParameterIsNotNull(externalId, "externalId");
        Intrinsics.checkParameterIsNotNull(model, "model");
        return new RightVServiceSubscriptionInfo(externalId, model);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RightVServiceSubscriptionInfo)) {
            return false;
        }
        RightVServiceSubscriptionInfo rightVServiceSubscriptionInfo = (RightVServiceSubscriptionInfo) other;
        return Intrinsics.areEqual(this.externalId, rightVServiceSubscriptionInfo.externalId) && Intrinsics.areEqual(this.model, rightVServiceSubscriptionInfo.model);
    }

    public int hashCode() {
        String str = this.externalId;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.model;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "RightVServiceSubscriptionInfo(externalId=" + this.externalId + ", model=" + this.model + ")";
    }

    public RightVServiceSubscriptionInfo(String externalId, String model) {
        Intrinsics.checkParameterIsNotNull(externalId, "externalId");
        Intrinsics.checkParameterIsNotNull(model, "model");
        this.externalId = externalId;
        this.model = model;
    }

    public final String getExternalId() {
        return this.externalId;
    }

    public final String getModel() {
        return this.model;
    }
}
