package com.dotscreen.bokit.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Offer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000fR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/dotscreen/bokit/model/Offer;", "Lcom/dotscreen/bokit/model/BrowsableItem;", "id", "", "title", "summary", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "deepLink", "type", "Lcom/dotscreen/bokit/model/OfferType;", "price", "", "currency", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/dotscreen/bokit/model/OfferType;DLjava/lang/String;)V", "getCurrency", "()Ljava/lang/String;", "getPrice", "()D", "getType", "()Lcom/dotscreen/bokit/model/OfferType;", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class Offer extends BrowsableItem {
    private final String currency;
    private final double price;
    private final OfferType type;

    public /* synthetic */ Offer(String str, String str2, String str3, List list, String str4, OfferType offerType, double d, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? CollectionsKt.emptyList() : list, (i & 16) != 0 ? (String) null : str4, (i & 32) != 0 ? OfferType.Free : offerType, (i & 64) != 0 ? 0.0d : d, (i & 128) != 0 ? (String) null : str5);
    }

    public final OfferType getType() {
        return this.type;
    }

    public final double getPrice() {
        return this.price;
    }

    public final String getCurrency() {
        return this.currency;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Offer(String id, String title, String str, List<? extends Artwork> artworks, String str2, OfferType type, double d, String str3) {
        super(id, title, str, artworks, str2, false, null, 64, null);
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artworks, "artworks");
        Intrinsics.checkNotNullParameter(type, "type");
        this.type = type;
        this.price = d;
        this.currency = str3;
    }
}
