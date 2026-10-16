package com.dotscreen.bokit.model;

import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MediaMetadata.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\b\u0016\u0018\u00002\u00020\u0001B\u0093\u0002\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0012\b\u0002\u0010\u0006\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0012\b\u0002\u0010\u000b\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\f0\u0003\u0012\u0012\b\u0002\u0010\r\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\u000e0\u0003\u0012\u0012\b\u0002\u0010\u000f\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\u000e0\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\n\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0003\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0003\u0012\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016\u0012\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016\u0012\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a\u0012\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a\u0012\u0010\b\u0002\u0010\u001d\u001a\n\u0018\u00010\u001ej\u0004\u0018\u0001`\u001f\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!¢\u0006\u0002\u0010\"R\u001d\u0010\r\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\u000e0\u0003¢\u0006\n\n\u0002\u0010%\u001a\u0004\b#\u0010$R\u0019\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0019\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010*\u001a\u0004\b(\u0010)R\u0019\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0003¢\u0006\n\n\u0002\u0010-\u001a\u0004\b+\u0010,R\u001d\u0010\u000b\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\f0\u0003¢\u0006\n\n\u0002\u0010%\u001a\u0004\b.\u0010$R\u0019\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010*\u001a\u0004\b/\u0010)R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u00102\u001a\u0004\b0\u00101R\u0019\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0003¢\u0006\n\n\u0002\u00105\u001a\u0004\b3\u00104R\u001d\u0010\u0006\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u0003¢\u0006\n\n\u0002\u0010%\u001a\u0004\b6\u0010$R\u0013\u0010 \u001a\u0004\u0018\u00010!¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u00102\u001a\u0004\b9\u00101R\u001b\u0010\u001d\u001a\n\u0018\u00010\u001ej\u0004\u0018\u0001`\u001f¢\u0006\n\n\u0002\u0010<\u001a\u0004\b:\u0010;R\u0019\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u0019\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a¢\u0006\b\n\u0000\u001a\u0004\b?\u0010>R\u001d\u0010\u000f\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\u000e0\u0003¢\u0006\n\n\u0002\u0010%\u001a\u0004\b@\u0010$R\u0019\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\bA\u0010'¨\u0006B"}, d2 = {"Lcom/dotscreen/bokit/model/MediaMetadata;", "", "directors", "", "Lcom/dotscreen/bokit/model/Person;", "cast", "genres", "", "Lcom/dotscreen/bokit/model/Genre;", "durationInMinutes", "", "countries", "Lcom/dotscreen/bokit/model/CountryCode;", "audioLanguages", "Lcom/dotscreen/bokit/model/LanguageCode;", "subtitlesLanguages", "productionYear", "classifiers", "Lcom/dotscreen/bokit/model/Classifier;", "extraArtworks", "Lcom/dotscreen/bokit/model/Artwork;", "trailers", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/Media;", "bonuses", "related", "Lcom/dotscreen/bokit/model/ItemCollection;", "Lcom/dotscreen/bokit/model/BrowsableItem;", "recommendations", "rating", "", "Lcom/dotscreen/bokit/model/MediaRating;", "parentalLevel", "Lcom/dotscreen/bokit/model/ParentalRating;", "([Lcom/dotscreen/bokit/model/Person;[Lcom/dotscreen/bokit/model/Person;[Ljava/lang/String;Ljava/lang/Integer;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/Integer;[Lcom/dotscreen/bokit/model/Classifier;[Lcom/dotscreen/bokit/model/Artwork;Lio/reactivex/Observable;Lio/reactivex/Observable;Lcom/dotscreen/bokit/model/ItemCollection;Lcom/dotscreen/bokit/model/ItemCollection;Ljava/lang/Double;Lcom/dotscreen/bokit/model/ParentalRating;)V", "getAudioLanguages", "()[Ljava/lang/String;", "[Ljava/lang/String;", "getBonuses", "()Lio/reactivex/Observable;", "getCast", "()[Lcom/dotscreen/bokit/model/Person;", "[Lcom/dotscreen/bokit/model/Person;", "getClassifiers", "()[Lcom/dotscreen/bokit/model/Classifier;", "[Lcom/dotscreen/bokit/model/Classifier;", "getCountries", "getDirectors", "getDurationInMinutes", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getExtraArtworks", "()[Lcom/dotscreen/bokit/model/Artwork;", "[Lcom/dotscreen/bokit/model/Artwork;", "getGenres", "getParentalLevel", "()Lcom/dotscreen/bokit/model/ParentalRating;", "getProductionYear", "getRating", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getRecommendations", "()Lcom/dotscreen/bokit/model/ItemCollection;", "getRelated", "getSubtitlesLanguages", "getTrailers", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class MediaMetadata {
    private final String[] audioLanguages;
    private final Observable<Media> bonuses;
    private final Person[] cast;
    private final Classifier[] classifiers;
    private final String[] countries;
    private final Person[] directors;
    private final Integer durationInMinutes;
    private final Artwork[] extraArtworks;
    private final String[] genres;
    private final ParentalRating parentalLevel;
    private final Integer productionYear;
    private final Double rating;
    private final ItemCollection<BrowsableItem> recommendations;
    private final ItemCollection<BrowsableItem> related;
    private final String[] subtitlesLanguages;
    private final Observable<Media> trailers;

    public MediaMetadata() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 65535, null);
    }

    public MediaMetadata(Person[] directors, Person[] cast, String[] genres, Integer num, String[] countries, String[] audioLanguages, String[] subtitlesLanguages, Integer num2, Classifier[] classifiers, Artwork[] extraArtworks, Observable<Media> observable, Observable<Media> observable2, ItemCollection<BrowsableItem> itemCollection, ItemCollection<BrowsableItem> itemCollection2, Double d, ParentalRating parentalRating) {
        Intrinsics.checkNotNullParameter(directors, "directors");
        Intrinsics.checkNotNullParameter(cast, "cast");
        Intrinsics.checkNotNullParameter(genres, "genres");
        Intrinsics.checkNotNullParameter(countries, "countries");
        Intrinsics.checkNotNullParameter(audioLanguages, "audioLanguages");
        Intrinsics.checkNotNullParameter(subtitlesLanguages, "subtitlesLanguages");
        Intrinsics.checkNotNullParameter(classifiers, "classifiers");
        Intrinsics.checkNotNullParameter(extraArtworks, "extraArtworks");
        this.directors = directors;
        this.cast = cast;
        this.genres = genres;
        this.durationInMinutes = num;
        this.countries = countries;
        this.audioLanguages = audioLanguages;
        this.subtitlesLanguages = subtitlesLanguages;
        this.productionYear = num2;
        this.classifiers = classifiers;
        this.extraArtworks = extraArtworks;
        this.trailers = observable;
        this.bonuses = observable2;
        this.related = itemCollection;
        this.recommendations = itemCollection2;
        this.rating = d;
        this.parentalLevel = parentalRating;
    }

    public final Person[] getDirectors() {
        return this.directors;
    }

    public final Person[] getCast() {
        return this.cast;
    }

    public final String[] getGenres() {
        return this.genres;
    }

    public /* synthetic */ MediaMetadata(Person[] personArr, Person[] personArr2, String[] strArr, Integer num, String[] strArr2, String[] strArr3, String[] strArr4, Integer num2, Classifier[] classifierArr, Artwork[] artworkArr, Observable observable, Observable observable2, ItemCollection itemCollection, ItemCollection itemCollection2, Double d, ParentalRating parentalRating, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Person[0] : personArr, (i & 2) != 0 ? new Person[0] : personArr2, (i & 4) != 0 ? new String[0] : strArr, (i & 8) != 0 ? (Integer) null : num, (i & 16) != 0 ? new String[0] : strArr2, (i & 32) != 0 ? new String[0] : strArr3, (i & 64) != 0 ? new String[0] : strArr4, (i & 128) != 0 ? (Integer) null : num2, (i & 256) != 0 ? new Classifier[0] : classifierArr, (i & 512) != 0 ? new Artwork[0] : artworkArr, (i & 1024) != 0 ? (Observable) null : observable, (i & 2048) != 0 ? (Observable) null : observable2, (i & 4096) != 0 ? (ItemCollection) null : itemCollection, (i & 8192) != 0 ? (ItemCollection) null : itemCollection2, (i & 16384) != 0 ? (Double) null : d, (i & 32768) != 0 ? (ParentalRating) null : parentalRating);
    }

    public final Integer getDurationInMinutes() {
        return this.durationInMinutes;
    }

    public final String[] getCountries() {
        return this.countries;
    }

    public final String[] getAudioLanguages() {
        return this.audioLanguages;
    }

    public final String[] getSubtitlesLanguages() {
        return this.subtitlesLanguages;
    }

    public final Integer getProductionYear() {
        return this.productionYear;
    }

    public final Classifier[] getClassifiers() {
        return this.classifiers;
    }

    public final Artwork[] getExtraArtworks() {
        return this.extraArtworks;
    }

    public final Observable<Media> getTrailers() {
        return this.trailers;
    }

    public final Observable<Media> getBonuses() {
        return this.bonuses;
    }

    public final ItemCollection<BrowsableItem> getRelated() {
        return this.related;
    }

    public final ItemCollection<BrowsableItem> getRecommendations() {
        return this.recommendations;
    }

    public final Double getRating() {
        return this.rating;
    }

    public final ParentalRating getParentalLevel() {
        return this.parentalLevel;
    }
}
