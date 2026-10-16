package com.dotscreen.tv.common.bokit;

import androidx.tvprovider.media.tv.TvContractCompat;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b%\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B}\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\u0007\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\u0006\u0010\u0011\u001a\u00020\u0005¢\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\u0015\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u000bHÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\u0015\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\u0007HÆ\u0003J\t\u0010.\u001a\u00020\u0005HÆ\u0003J\u0099\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00052\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u0005HÆ\u0001J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00103\u001a\u00020\u000bHÖ\u0001J\t\u00104\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014R\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014¨\u00065"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RightVService;", "", "price", "", "name", "", "extrafields", "Ljava/util/HashMap;", TvContractCompat.Channels.COLUMN_DESCRIPTION, "externalId", "id", "", "storeProductReferenceId", "serviceCatalogFolders", "quotaInHours", "concurrentRecordingsLimit", "simultaneousViewsLimit", "model", "(DLjava/lang/String;Ljava/util/HashMap;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/util/HashMap;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getConcurrentRecordingsLimit", "()Ljava/lang/String;", "getDescription", "getExternalId", "getExtrafields", "()Ljava/util/HashMap;", "getId", "()I", "getModel", "getName", "getPrice", "()D", "getQuotaInHours", "getServiceCatalogFolders", "getSimultaneousViewsLimit", "getStoreProductReferenceId", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final /* data */ class RightVService {
    private final String concurrentRecordingsLimit;
    private final String description;
    private final String externalId;
    private final HashMap<String, String> extrafields;
    private final int id;
    private final String model;
    private final String name;
    private final double price;
    private final String quotaInHours;
    private final HashMap<String, Integer> serviceCatalogFolders;
    private final String simultaneousViewsLimit;
    private final String storeProductReferenceId;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final double getPrice() {
        return this.price;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getConcurrentRecordingsLimit() {
        return this.concurrentRecordingsLimit;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getSimultaneousViewsLimit() {
        return this.simultaneousViewsLimit;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getModel() {
        return this.model;
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

    public final HashMap<String, Integer> component8() {
        return this.serviceCatalogFolders;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getQuotaInHours() {
        return this.quotaInHours;
    }

    public final RightVService copy(double price, String name, HashMap<String, String> extrafields, String description, String externalId, int id, String storeProductReferenceId, HashMap<String, Integer> serviceCatalogFolders, String quotaInHours, String concurrentRecordingsLimit, String simultaneousViewsLimit, String model) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(extrafields, "extrafields");
        Intrinsics.checkParameterIsNotNull(description, "description");
        Intrinsics.checkParameterIsNotNull(externalId, "externalId");
        Intrinsics.checkParameterIsNotNull(storeProductReferenceId, "storeProductReferenceId");
        Intrinsics.checkParameterIsNotNull(serviceCatalogFolders, "serviceCatalogFolders");
        Intrinsics.checkParameterIsNotNull(quotaInHours, "quotaInHours");
        Intrinsics.checkParameterIsNotNull(concurrentRecordingsLimit, "concurrentRecordingsLimit");
        Intrinsics.checkParameterIsNotNull(simultaneousViewsLimit, "simultaneousViewsLimit");
        Intrinsics.checkParameterIsNotNull(model, "model");
        return new RightVService(price, name, extrafields, description, externalId, id, storeProductReferenceId, serviceCatalogFolders, quotaInHours, concurrentRecordingsLimit, simultaneousViewsLimit, model);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RightVService)) {
            return false;
        }
        RightVService rightVService = (RightVService) other;
        return Double.compare(this.price, rightVService.price) == 0 && Intrinsics.areEqual(this.name, rightVService.name) && Intrinsics.areEqual(this.extrafields, rightVService.extrafields) && Intrinsics.areEqual(this.description, rightVService.description) && Intrinsics.areEqual(this.externalId, rightVService.externalId) && this.id == rightVService.id && Intrinsics.areEqual(this.storeProductReferenceId, rightVService.storeProductReferenceId) && Intrinsics.areEqual(this.serviceCatalogFolders, rightVService.serviceCatalogFolders) && Intrinsics.areEqual(this.quotaInHours, rightVService.quotaInHours) && Intrinsics.areEqual(this.concurrentRecordingsLimit, rightVService.concurrentRecordingsLimit) && Intrinsics.areEqual(this.simultaneousViewsLimit, rightVService.simultaneousViewsLimit) && Intrinsics.areEqual(this.model, rightVService.model);
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
        int iHashCode5 = (iHashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        HashMap<String, Integer> map2 = this.serviceCatalogFolders;
        int iHashCode6 = (iHashCode5 + (map2 != null ? map2.hashCode() : 0)) * 31;
        String str5 = this.quotaInHours;
        int iHashCode7 = (iHashCode6 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.concurrentRecordingsLimit;
        int iHashCode8 = (iHashCode7 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.simultaneousViewsLimit;
        int iHashCode9 = (iHashCode8 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.model;
        return iHashCode9 + (str8 != null ? str8.hashCode() : 0);
    }

    public String toString() {
        return "RightVService(price=" + this.price + ", name=" + this.name + ", extrafields=" + this.extrafields + ", description=" + this.description + ", externalId=" + this.externalId + ", id=" + this.id + ", storeProductReferenceId=" + this.storeProductReferenceId + ", serviceCatalogFolders=" + this.serviceCatalogFolders + ", quotaInHours=" + this.quotaInHours + ", concurrentRecordingsLimit=" + this.concurrentRecordingsLimit + ", simultaneousViewsLimit=" + this.simultaneousViewsLimit + ", model=" + this.model + ")";
    }

    public RightVService(double d, String name, HashMap<String, String> extrafields, String description, String externalId, int i, String storeProductReferenceId, HashMap<String, Integer> serviceCatalogFolders, String quotaInHours, String concurrentRecordingsLimit, String simultaneousViewsLimit, String model) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(extrafields, "extrafields");
        Intrinsics.checkParameterIsNotNull(description, "description");
        Intrinsics.checkParameterIsNotNull(externalId, "externalId");
        Intrinsics.checkParameterIsNotNull(storeProductReferenceId, "storeProductReferenceId");
        Intrinsics.checkParameterIsNotNull(serviceCatalogFolders, "serviceCatalogFolders");
        Intrinsics.checkParameterIsNotNull(quotaInHours, "quotaInHours");
        Intrinsics.checkParameterIsNotNull(concurrentRecordingsLimit, "concurrentRecordingsLimit");
        Intrinsics.checkParameterIsNotNull(simultaneousViewsLimit, "simultaneousViewsLimit");
        Intrinsics.checkParameterIsNotNull(model, "model");
        this.price = d;
        this.name = name;
        this.extrafields = extrafields;
        this.description = description;
        this.externalId = externalId;
        this.id = i;
        this.storeProductReferenceId = storeProductReferenceId;
        this.serviceCatalogFolders = serviceCatalogFolders;
        this.quotaInHours = quotaInHours;
        this.concurrentRecordingsLimit = concurrentRecordingsLimit;
        this.simultaneousViewsLimit = simultaneousViewsLimit;
        this.model = model;
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

    public final HashMap<String, Integer> getServiceCatalogFolders() {
        return this.serviceCatalogFolders;
    }

    public final String getQuotaInHours() {
        return this.quotaInHours;
    }

    public final String getConcurrentRecordingsLimit() {
        return this.concurrentRecordingsLimit;
    }

    public final String getSimultaneousViewsLimit() {
        return this.simultaneousViewsLimit;
    }

    public final String getModel() {
        return this.model;
    }
}
