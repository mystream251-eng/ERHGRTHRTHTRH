package com.dotscreen.bokit.model;

import com.dotscreen.bokit.services.BOService;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TVSeries.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BÊ\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010\u0012\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013\u0012)\b\u0002\u0010\u0015\u001a#\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00100\u0016\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u001c\u0012\u0014\b\u0002\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00060\u001e\u0012\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0010¢\u0006\u0002\u0010\"J\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0017R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0019\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0019\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b*\u0010%¨\u0006,"}, d2 = {"Lcom/dotscreen/bokit/model/TVSeries;", "Lcom/dotscreen/bokit/model/ItemCollection;", "Lcom/dotscreen/bokit/model/Episode;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "id", "", "title", "summary", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "deepLink", TtmlNode.TAG_METADATA, "Lcom/dotscreen/bokit/model/MediaMetadata;", "seasons", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/TVSeason;", "episodesCount", "Lio/reactivex/Single;", "", "episodesSource", "Lkotlin/Function1;", "Lcom/dotscreen/bokit/model/ItemSortingOrder;", "Lkotlin/ParameterName;", "name", "order", "ratable", "", "externalIds", "", "Lcom/dotscreen/bokit/model/ItemExternalIdScheme;", "offers", "Lcom/dotscreen/bokit/model/Offer;", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/dotscreen/bokit/model/MediaMetadata;Lio/reactivex/Observable;Lio/reactivex/Single;Lkotlin/jvm/functions/Function1;ZLjava/util/Map;Lio/reactivex/Observable;)V", "episodes", "getEpisodes", "()Lio/reactivex/Observable;", "getEpisodesCount", "()Lio/reactivex/Single;", "getMetadata", "()Lcom/dotscreen/bokit/model/MediaMetadata;", "getSeasons", "episodesSortedBy", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final class TVSeries extends ItemCollection<Episode> {
    private final Observable<Episode> episodes;
    private final Single<Integer> episodesCount;
    private final MediaMetadata metadata;
    private final Observable<TVSeason> seasons;

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TVSeries(BOService bOService, String str, String str2, String str3, List list, String str4, MediaMetadata mediaMetadata, Observable observable, Single single, Function1 function1, boolean z, Map map, Observable observable2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Observable observable3;
        List listEmptyList = (i & 16) != 0 ? CollectionsKt.emptyList() : list;
        String str5 = (i & 32) != 0 ? (String) null : str4;
        Observable observable4 = (i & 128) != 0 ? (Observable) null : observable;
        Single singleJust = (i & 256) != 0 ? Single.just(0) : single;
        Function1 function2 = (i & 512) != 0 ? new Function1<ItemSortingOrder, Observable<Episode>>() { // from class: com.dotscreen.bokit.model.TVSeries.1
            @Override // kotlin.jvm.functions.Function1
            public final Observable<Episode> invoke(ItemSortingOrder itemSortingOrder) {
                Intrinsics.checkNotNullParameter(itemSortingOrder, "<anonymous parameter 0>");
                Observable<Episode> observableEmpty = Observable.empty();
                Intrinsics.checkNotNullExpressionValue(observableEmpty, "Observable.empty()");
                return observableEmpty;
            }
        } : function1;
        boolean z2 = (i & 1024) != 0 ? false : z;
        Map mapEmptyMap = (i & 2048) != 0 ? MapsKt.emptyMap() : map;
        if ((i & 4096) != 0) {
            Observable observableEmpty = Observable.empty();
            Intrinsics.checkNotNullExpressionValue(observableEmpty, "Observable.empty()");
            observable3 = observableEmpty;
        } else {
            observable3 = observable2;
        }
        this(bOService, str, str2, str3, listEmptyList, str5, mediaMetadata, observable4, singleJust, function2, z2, mapEmptyMap, observable3);
    }

    public final MediaMetadata getMetadata() {
        return this.metadata;
    }

    public final Observable<TVSeason> getSeasons() {
        return this.seasons;
    }

    public final Single<Integer> getEpisodesCount() {
        return this.episodesCount;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TVSeries(BOService boService, String id, String title, String str, List<? extends Artwork> artworks, String str2, MediaMetadata metadata, Observable<TVSeason> observable, Single<Integer> single, Function1<? super ItemSortingOrder, ? extends Observable<Episode>> episodesSource, boolean z, Map<ItemExternalIdScheme, String> externalIds, Observable<Offer> offers) {
        super(boService, id, title, str, artworks, str2, z, observable != null ? observable.map(new Function<TVSeason, ItemCollection<Episode>>() { // from class: com.dotscreen.bokit.model.TVSeries.2
            @Override // io.reactivex.functions.Function
            public final ItemCollection<Episode> apply(TVSeason it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it;
            }
        }) : null, single, episodesSource, externalIds, offers);
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artworks, "artworks");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(episodesSource, "episodesSource");
        Intrinsics.checkNotNullParameter(externalIds, "externalIds");
        Intrinsics.checkNotNullParameter(offers, "offers");
        this.metadata = metadata;
        this.seasons = observable;
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
