package com.dotscreen.bokit.model;

import com.dotscreen.bokit.services.BOService;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TVSeason.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BÔ\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012\u0012\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0015\u0012)\b\u0002\u0010\u0016\u001a#\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u001c0\u0017\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u001e\u0012\u0014\b\u0002\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00060 \u0012\u000e\b\u0002\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u001c¢\u0006\u0002\u0010$J\u0014\u00100\u001a\b\u0012\u0004\u0012\u00020\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u0018R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0019\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0015¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0019\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/¨\u00061"}, d2 = {"Lcom/dotscreen/bokit/model/TVSeason;", "Lcom/dotscreen/bokit/model/ItemCollection;", "Lcom/dotscreen/bokit/model/Episode;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "id", "", "title", "summary", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "deepLink", "seasonNumber", "", TtmlNode.TAG_METADATA, "Lcom/dotscreen/bokit/model/MediaMetadata;", "series", "Lio/reactivex/Maybe;", "Lcom/dotscreen/bokit/model/TVSeries;", "episodesCount", "Lio/reactivex/Single;", "episodesSource", "Lkotlin/Function1;", "Lcom/dotscreen/bokit/model/ItemSortingOrder;", "Lkotlin/ParameterName;", "name", "order", "Lio/reactivex/Observable;", "ratable", "", "externalIds", "", "Lcom/dotscreen/bokit/model/ItemExternalIdScheme;", "offers", "Lcom/dotscreen/bokit/model/Offer;", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ILcom/dotscreen/bokit/model/MediaMetadata;Lio/reactivex/Maybe;Lio/reactivex/Single;Lkotlin/jvm/functions/Function1;ZLjava/util/Map;Lio/reactivex/Observable;)V", "episodes", "getEpisodes", "()Lio/reactivex/Observable;", "getEpisodesCount", "()Lio/reactivex/Single;", "getMetadata", "()Lcom/dotscreen/bokit/model/MediaMetadata;", "getSeasonNumber", "()I", "getSeries", "()Lio/reactivex/Maybe;", "episodesSortedBy", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final class TVSeason extends ItemCollection<Episode> {
    private final Observable<Episode> episodes;
    private final Single<Integer> episodesCount;
    private final MediaMetadata metadata;
    private final int seasonNumber;
    private final Maybe<TVSeries> series;

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TVSeason(BOService bOService, String str, String str2, String str3, List list, String str4, int i, MediaMetadata mediaMetadata, Maybe maybe, Single single, Function1 function1, boolean z, Map map, Observable observable, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        Observable observable2;
        List listEmptyList = (i2 & 16) != 0 ? CollectionsKt.emptyList() : list;
        String str5 = (i2 & 32) != 0 ? (String) null : str4;
        int i3 = (i2 & 64) != 0 ? 0 : i;
        Maybe maybeEmpty = (i2 & 256) != 0 ? Maybe.empty() : maybe;
        Single singleJust = (i2 & 512) != 0 ? Single.just(0) : single;
        Function1 function2 = (i2 & 1024) != 0 ? new Function1<ItemSortingOrder, Observable<Episode>>() { // from class: com.dotscreen.bokit.model.TVSeason.1
            @Override // kotlin.jvm.functions.Function1
            public final Observable<Episode> invoke(ItemSortingOrder itemSortingOrder) {
                Intrinsics.checkNotNullParameter(itemSortingOrder, "<anonymous parameter 0>");
                Observable<Episode> observableEmpty = Observable.empty();
                Intrinsics.checkNotNullExpressionValue(observableEmpty, "Observable.empty()");
                return observableEmpty;
            }
        } : function1;
        boolean z2 = (i2 & 2048) != 0 ? false : z;
        Map mapEmptyMap = (i2 & 4096) != 0 ? MapsKt.emptyMap() : map;
        if ((i2 & 8192) != 0) {
            Observable observableEmpty = Observable.empty();
            Intrinsics.checkNotNullExpressionValue(observableEmpty, "Observable.empty()");
            observable2 = observableEmpty;
        } else {
            observable2 = observable;
        }
        this(bOService, str, str2, str3, listEmptyList, str5, i3, mediaMetadata, maybeEmpty, singleJust, function2, z2, mapEmptyMap, observable2);
    }

    public final int getSeasonNumber() {
        return this.seasonNumber;
    }

    public final MediaMetadata getMetadata() {
        return this.metadata;
    }

    public final Maybe<TVSeries> getSeries() {
        return this.series;
    }

    public final Single<Integer> getEpisodesCount() {
        return this.episodesCount;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TVSeason(BOService boService, String id, String title, String str, List<? extends Artwork> artworks, String str2, int i, MediaMetadata metadata, Maybe<TVSeries> maybe, Single<Integer> single, Function1<? super ItemSortingOrder, ? extends Observable<Episode>> episodesSource, boolean z, Map<ItemExternalIdScheme, String> externalIds, Observable<Offer> offers) {
        super(boService, id, title, str, artworks, str2, z, null, single, episodesSource, externalIds, offers);
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artworks, "artworks");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(episodesSource, "episodesSource");
        Intrinsics.checkNotNullParameter(externalIds, "externalIds");
        Intrinsics.checkNotNullParameter(offers, "offers");
        this.seasonNumber = i;
        this.metadata = metadata;
        this.series = maybe;
        this.episodesCount = single;
        this.episodes = getItems();
    }

    public final Observable<Episode> getEpisodes() {
        return this.episodes;
    }

    public final Observable<Episode> episodesSortedBy(ItemSortingOrder order) {
        Intrinsics.checkNotNullParameter(order, "order");
        return itemsSortedBy(order);
    }
}
