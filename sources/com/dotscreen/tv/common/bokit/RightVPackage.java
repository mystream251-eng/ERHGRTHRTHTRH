package com.dotscreen.tv.common.bokit;

import androidx.tvprovider.media.tv.TvContractCompat;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0005¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\u0015\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u000bHÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J[\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u0005HÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u000bHÖ\u0001J\t\u0010%\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006&"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RightVPackage;", "", "price", "", "name", "", "extrafields", "Ljava/util/HashMap;", TvContractCompat.Channels.COLUMN_DESCRIPTION, "externalId", "id", "", "storeProductReferenceId", "(DLjava/lang/String;Ljava/util/HashMap;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "getExternalId", "getExtrafields", "()Ljava/util/HashMap;", "getId", "()I", "getName", "getPrice", "()D", "getStoreProductReferenceId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final /* data */ class RightVPackage {
    private final String description;
    private final String externalId;
    private final HashMap<String, String> extrafields;
    private final int id;
    private final String name;
    private final double price;
    private final String storeProductReferenceId;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final double getPrice() {
        return this.price;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final HashMap<String, String> component3() {
        return this.extrafields;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getExternalId() {
        return this.externalId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getStoreProductReferenceId() {
        return this.storeProductReferenceId;
    }

    public final RightVPackage copy(double price, String name, HashMap<String, String> extrafields, String description, String externalId, int id, String storeProductReferenceId) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(extrafields, "extrafields");
        Intrinsics.checkParameterIsNotNull(description, "description");
        Intrinsics.checkParameterIsNotNull(externalId, "externalId");
        Intrinsics.checkParameterIsNotNull(storeProductReferenceId, "storeProductReferenceId");
        return new RightVPackage(price, name, extrafields, description, externalId, id, storeProductReferenceId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RightVPackage)) {
            return false;
        }
        RightVPackage rightVPackage = (RightVPackage) other;
        return Double.compare(this.price, rightVPackage.price) == 0 && Intrinsics.areEqual(this.name, rightVPackage.name) && Intrinsics.areEqual(this.extrafields, rightVPackage.extrafields) && Intrinsics.areEqual(this.description, rightVPackage.description) && Intrinsics.areEqual(this.externalId, rightVPackage.externalId) && this.id == rightVPackage.id && Intrinsics.areEqual(this.storeProductReferenceId, rightVPackage.storeProductReferenceId);
    }

    public int hashCode() {
        long jDoubleToLongBits = Double.doubleToLongBits(this.price);
        int i = ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32))) * 31;
        String str = this.name;
        int iHashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        HashMap<String, String> map = this.extrafields;
        int iHashCode2 = (iHashCode + (map != null ? map.hashCode() : 0)) * 31;
        String str2 = this.description;
        int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.externalId;
        int iHashCode4 = (((iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.id) * 31;
        String str4 = this.storeProductReferenceId;
        return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "RightVPackage(price=" + this.price + ", name=" + this.name + ", extrafields=" + this.extrafields + ", description=" + this.description + ", externalId=" + this.externalId + ", id=" + this.id + ", storeProductReferenceId=" + this.storeProductReferenceId + ")";
    }

    public RightVPackage(double d, String name, HashMap<String, String> extrafields, String description, String externalId, int i, String storeProductReferenceId) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(extrafields, "extrafields");
        Intrinsics.checkParameterIsNotNull(description, "description");
        Intrinsics.checkParameterIsNotNull(externalId, "externalId");
        Intrinsics.checkParameterIsNotNull(storeProductReferenceId, "storeProductReferenceId");
        this.price = d;
        this.name = name;
        this.extrafields = extrafields;
        this.description = description;
        this.externalId = externalId;
        this.id = i;
        this.storeProductReferenceId = storeProductReferenceId;
    }

    public final double getPrice() {
        return this.price;
    }

    public final String getName() {
        return this.name;
    }

    public final HashMap<String, String> getExtrafields() {
        return this.extrafields;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getExternalId() {
        return this.externalId;
    }

    public final int getId() {
        return this.id;
    }

    public final String getStoreProductReferenceId() {
        return this.storeProductReferenceId;
    }
}
