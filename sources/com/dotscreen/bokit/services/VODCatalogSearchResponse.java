package com.dotscreen.bokit.services;

import com.dotscreen.bokit.model.Episode;
import com.dotscreen.bokit.model.ItemCollection;
import com.dotscreen.bokit.model.Movie;
import com.dotscreen.bokit.model.TVSeason;
import com.dotscreen.bokit.model.TVSeries;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VODCatalogService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003¢\u0006\u0002\u0010\u000bJ\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0003HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u0003HÆ\u0003JI\u0010\u0015\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001d"}, d2 = {"Lcom/dotscreen/bokit/services/VODCatalogSearchResponse;", "", "movies", "Lcom/dotscreen/bokit/model/ItemCollection;", "Lcom/dotscreen/bokit/model/Movie;", "tvSeries", "Lcom/dotscreen/bokit/model/TVSeries;", "tvSeasons", "Lcom/dotscreen/bokit/model/TVSeason;", "episodes", "Lcom/dotscreen/bokit/model/Episode;", "(Lcom/dotscreen/bokit/model/ItemCollection;Lcom/dotscreen/bokit/model/ItemCollection;Lcom/dotscreen/bokit/model/ItemCollection;Lcom/dotscreen/bokit/model/ItemCollection;)V", "getEpisodes", "()Lcom/dotscreen/bokit/model/ItemCollection;", "getMovies", "getTvSeasons", "getTvSeries", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final /* data */ class VODCatalogSearchResponse {
    private final ItemCollection<Episode> episodes;
    private final ItemCollection<Movie> movies;
    private final ItemCollection<TVSeason> tvSeasons;
    private final ItemCollection<TVSeries> tvSeries;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ VODCatalogSearchResponse copy$default(VODCatalogSearchResponse vODCatalogSearchResponse, ItemCollection itemCollection, ItemCollection itemCollection2, ItemCollection itemCollection3, ItemCollection itemCollection4, int i, Object obj) {
        if ((i & 1) != 0) {
            itemCollection = vODCatalogSearchResponse.movies;
        }
        if ((i & 2) != 0) {
            itemCollection2 = vODCatalogSearchResponse.tvSeries;
        }
        if ((i & 4) != 0) {
            itemCollection3 = vODCatalogSearchResponse.tvSeasons;
        }
        if ((i & 8) != 0) {
            itemCollection4 = vODCatalogSearchResponse.episodes;
        }
        return vODCatalogSearchResponse.copy(itemCollection, itemCollection2, itemCollection3, itemCollection4);
    }

    public final ItemCollection<Movie> component1() {
        return this.movies;
    }

    public final ItemCollection<TVSeries> component2() {
        return this.tvSeries;
    }

    public final ItemCollection<TVSeason> component3() {
        return this.tvSeasons;
    }

    public final ItemCollection<Episode> component4() {
        return this.episodes;
    }

    public final VODCatalogSearchResponse copy(ItemCollection<Movie> movies, ItemCollection<TVSeries> tvSeries, ItemCollection<TVSeason> tvSeasons, ItemCollection<Episode> episodes) {
        Intrinsics.checkNotNullParameter(movies, "movies");
        Intrinsics.checkNotNullParameter(tvSeries, "tvSeries");
        Intrinsics.checkNotNullParameter(tvSeasons, "tvSeasons");
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        return new VODCatalogSearchResponse(movies, tvSeries, tvSeasons, episodes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VODCatalogSearchResponse)) {
            return false;
        }
        VODCatalogSearchResponse vODCatalogSearchResponse = (VODCatalogSearchResponse) other;
        return Intrinsics.areEqual(this.movies, vODCatalogSearchResponse.movies) && Intrinsics.areEqual(this.tvSeries, vODCatalogSearchResponse.tvSeries) && Intrinsics.areEqual(this.tvSeasons, vODCatalogSearchResponse.tvSeasons) && Intrinsics.areEqual(this.episodes, vODCatalogSearchResponse.episodes);
    }

    public int hashCode() {
        ItemCollection<Movie> itemCollection = this.movies;
        int iHashCode = (itemCollection != null ? itemCollection.hashCode() : 0) * 31;
        ItemCollection<TVSeries> itemCollection2 = this.tvSeries;
        int iHashCode2 = (iHashCode + (itemCollection2 != null ? itemCollection2.hashCode() : 0)) * 31;
        ItemCollection<TVSeason> itemCollection3 = this.tvSeasons;
        int iHashCode3 = (iHashCode2 + (itemCollection3 != null ? itemCollection3.hashCode() : 0)) * 31;
        ItemCollection<Episode> itemCollection4 = this.episodes;
        return iHashCode3 + (itemCollection4 != null ? itemCollection4.hashCode() : 0);
    }

    public String toString() {
        return "VODCatalogSearchResponse(movies=" + this.movies + ", tvSeries=" + this.tvSeries + ", tvSeasons=" + this.tvSeasons + ", episodes=" + this.episodes + ")";
    }

    public VODCatalogSearchResponse(ItemCollection<Movie> movies, ItemCollection<TVSeries> tvSeries, ItemCollection<TVSeason> tvSeasons, ItemCollection<Episode> episodes) {
        Intrinsics.checkNotNullParameter(movies, "movies");
        Intrinsics.checkNotNullParameter(tvSeries, "tvSeries");
        Intrinsics.checkNotNullParameter(tvSeasons, "tvSeasons");
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        this.movies = movies;
        this.tvSeries = tvSeries;
        this.tvSeasons = tvSeasons;
        this.episodes = episodes;
    }

    public final ItemCollection<Movie> getMovies() {
        return this.movies;
    }

    public final ItemCollection<TVSeries> getTvSeries() {
        return this.tvSeries;
    }

    public final ItemCollection<TVSeason> getTvSeasons() {
        return this.tvSeasons;
    }

    public final ItemCollection<Episode> getEpisodes() {
        return this.episodes;
    }
}
