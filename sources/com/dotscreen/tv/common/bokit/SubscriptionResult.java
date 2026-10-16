package com.dotscreen.tv.common.bokit;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/dotscreen/tv/common/bokit/SubscriptionResult;", "", "resultCode", "", "sp", "en", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEn", "()Ljava/lang/String;", "getResultCode", "getSp", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final /* data */ class SubscriptionResult {
    private final String en;
    private final String resultCode;
    private final String sp;

    public static /* synthetic */ SubscriptionResult copy$default(SubscriptionResult subscriptionResult, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = subscriptionResult.resultCode;
        }
        if ((i & 2) != 0) {
            str2 = subscriptionResult.sp;
        }
        if ((i & 4) != 0) {
            str3 = subscriptionResult.en;
        }
        return subscriptionResult.copy(str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getResultCode() {
        return this.resultCode;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSp() {
        return this.sp;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getEn() {
        return this.en;
    }

    public final SubscriptionResult copy(String resultCode, String sp, String en) {
        Intrinsics.checkParameterIsNotNull(resultCode, "resultCode");
        Intrinsics.checkParameterIsNotNull(sp, "sp");
        Intrinsics.checkParameterIsNotNull(en, "en");
        return new SubscriptionResult(resultCode, sp, en);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SubscriptionResult)) {
            return false;
        }
        SubscriptionResult subscriptionResult = (SubscriptionResult) other;
        return Intrinsics.areEqual(this.resultCode, subscriptionResult.resultCode) && Intrinsics.areEqual(this.sp, subscriptionResult.sp) && Intrinsics.areEqual(this.en, subscriptionResult.en);
    }

    public int hashCode() {
        String str = this.resultCode;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.sp;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.en;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "SubscriptionResult(resultCode=" + this.resultCode + ", sp=" + this.sp + ", en=" + this.en + ")";
    }

    public SubscriptionResult(String resultCode, String sp, String en) {
        Intrinsics.checkParameterIsNotNull(resultCode, "resultCode");
        Intrinsics.checkParameterIsNotNull(sp, "sp");
        Intrinsics.checkParameterIsNotNull(en, "en");
        this.resultCode = resultCode;
        this.sp = sp;
        this.en = en;
    }

    public final String getEn() {
        return this.en;
    }

    public final String getResultCode() {
        return this.resultCode;
    }

    public final String getSp() {
        return this.sp;
    }
}
