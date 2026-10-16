package com.dotscreen.bokit.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrowsableItem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0016\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00030\r¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010 \u001a\u00020!H\u0016J\u0013\u0010\"\u001a\u00020\u000b2\b\u0010#\u001a\u0004\u0018\u00010$H\u0096\u0002J\b\u0010%\u001a\u00020&H\u0016R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001a\u0004\u0018\u00010\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0014R\u0016\u0010\u0019\u001a\u0004\u0018\u00010\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0014R\u0016\u0010\u001b\u001a\u0004\u0018\u00010\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0014R\u0016\u0010\u001d\u001a\u0004\u0018\u00010\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0014¨\u0006'"}, d2 = {"Lcom/dotscreen/bokit/model/BrowsableItem;", "Lcom/dotscreen/bokit/model/Item;", "id", "", "title", "summary", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "deepLink", "ratable", "", "externalIds", "", "Lcom/dotscreen/bokit/model/ItemExternalIdScheme;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ZLjava/util/Map;)V", "getArtworks", "()Ljava/util/List;", "bannerArtwork", "getBannerArtwork", "()Lcom/dotscreen/bokit/model/Artwork;", "getDeepLink", "()Ljava/lang/String;", "posterArtwork", "getPosterArtwork", "showCardArtwork", "getShowCardArtwork", "squareArtwork", "getSquareArtwork", "stillArtwork", "getStillArtwork", "artworksWithFormat", "format", "Lcom/dotscreen/bokit/model/ArtworkFormat;", "equals", "other", "", "hashCode", "", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class BrowsableItem extends Item {
    private final List<Artwork> artworks;
    private final String deepLink;

    public /* synthetic */ BrowsableItem(String str, String str2, String str3, List list, String str4, boolean z, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? CollectionsKt.emptyList() : list, (i & 16) != 0 ? (String) null : str4, (i & 32) != 0 ? false : z, (i & 64) != 0 ? MapsKt.emptyMap() : map);
    }

    public final List<Artwork> getArtworks() {
        return this.artworks;
    }

    public final String getDeepLink() {
        return this.deepLink;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BrowsableItem(String id, String title, String str, List<? extends Artwork> artworks, String str2, boolean z, Map<ItemExternalIdScheme, String> externalIds) {
        super(id, title, str, z, externalIds);
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artworks, "artworks");
        Intrinsics.checkNotNullParameter(externalIds, "externalIds");
        this.artworks = artworks;
        this.deepLink = str2;
    }

    @Override // com.dotscreen.bokit.model.Item
    public boolean equals(Object other) {
        if (!(other instanceof BrowsableItem) || !super.equals(other)) {
            return false;
        }
        BrowsableItem browsableItem = (BrowsableItem) other;
        return Intrinsics.areEqual(browsableItem.artworks, this.artworks) && Intrinsics.areEqual(browsableItem.deepLink, this.deepLink);
    }

    @Override // com.dotscreen.bokit.model.Item
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getSummary(), this.artworks, this.deepLink, Boolean.valueOf(getRatable()));
    }

    public List<Artwork> artworksWithFormat(ArtworkFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        List<Artwork> list = this.artworks;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (format == ((Artwork) obj).getFormat()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public Artwork getPosterArtwork() {
        return (Artwork) CollectionsKt.firstOrNull((List) artworksWithFormat(ArtworkFormat.Poster));
    }

    public Artwork getStillArtwork() {
        return (Artwork) CollectionsKt.firstOrNull((List) artworksWithFormat(ArtworkFormat.Still));
    }

    public Artwork getSquareArtwork() {
        return (Artwork) CollectionsKt.firstOrNull((List) artworksWithFormat(ArtworkFormat.Square));
    }

    public Artwork getBannerArtwork() {
        return (Artwork) CollectionsKt.firstOrNull((List) artworksWithFormat(ArtworkFormat.Banner));
    }

    public Artwork getShowCardArtwork() {
        return (Artwork) CollectionsKt.firstOrNull((List) artworksWithFormat(ArtworkFormat.ShowCard));
    }
}
