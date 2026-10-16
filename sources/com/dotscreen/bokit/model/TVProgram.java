package com.dotscreen.bokit.model;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TVProgram.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0016\u0018\u00002\u00020\u0001B½\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\u001a\b\u0002\u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00190\u00180\u0015\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u001b¢\u0006\u0002\u0010\u001cR\u0015\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R#\u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00190\u00180\u0015¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b(\u0010'R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\b\n\u0000\u001a\u0004\b)\u0010%R\u0015\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b*\u0010\u001e¨\u0006+"}, d2 = {"Lcom/dotscreen/bokit/model/TVProgram;", "Lcom/dotscreen/bokit/model/BrowsableItem;", "id", "", "title", "summary", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "deepLink", TtmlNode.TAG_METADATA, "Lcom/dotscreen/bokit/model/MediaMetadata;", "seasonNumber", "", "episodeNumber", "episodeTitle", "relatedVodMedia", "Lio/reactivex/Maybe;", "Lcom/dotscreen/bokit/model/Media;", "relatedCatchupMedia", "reruns", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/TVAiring;", "offers", "", "", "ratable", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/dotscreen/bokit/model/MediaMetadata;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lio/reactivex/Maybe;Lio/reactivex/Maybe;Lio/reactivex/Observable;Lio/reactivex/Observable;Z)V", "getEpisodeNumber", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisodeTitle", "()Ljava/lang/String;", "getMetadata", "()Lcom/dotscreen/bokit/model/MediaMetadata;", "getOffers", "()Lio/reactivex/Observable;", "getRelatedCatchupMedia", "()Lio/reactivex/Maybe;", "getRelatedVodMedia", "getReruns", "getSeasonNumber", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class TVProgram extends BrowsableItem {
    private final Integer episodeNumber;
    private final String episodeTitle;
    private final MediaMetadata metadata;
    private final Observable<Map<String, Object>> offers;
    private final Maybe<Media> relatedCatchupMedia;
    private final Maybe<Media> relatedVodMedia;
    private final Observable<TVAiring> reruns;
    private final Integer seasonNumber;

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TVProgram(String str, String str2, String str3, List list, String str4, MediaMetadata mediaMetadata, Integer num, Integer num2, String str5, Maybe maybe, Maybe maybe2, Observable observable, Observable observable2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Maybe maybe3;
        Maybe maybe4;
        Observable observable3;
        Observable observable4;
        List listEmptyList = (i & 8) != 0 ? CollectionsKt.emptyList() : list;
        String str6 = (i & 16) != 0 ? (String) null : str4;
        Integer num3 = (i & 64) != 0 ? (Integer) null : num;
        Integer num4 = (i & 128) != 0 ? (Integer) null : num2;
        String str7 = (i & 256) != 0 ? (String) null : str5;
        if ((i & 512) != 0) {
            Maybe maybeEmpty = Maybe.empty();
            Intrinsics.checkNotNullExpressionValue(maybeEmpty, "Maybe.empty()");
            maybe3 = maybeEmpty;
        } else {
            maybe3 = maybe;
        }
        if ((i & 1024) != 0) {
            Maybe maybeEmpty2 = Maybe.empty();
            Intrinsics.checkNotNullExpressionValue(maybeEmpty2, "Maybe.empty()");
            maybe4 = maybeEmpty2;
        } else {
            maybe4 = maybe2;
        }
        if ((i & 2048) != 0) {
            Observable observableEmpty = Observable.empty();
            Intrinsics.checkNotNullExpressionValue(observableEmpty, "Observable.empty()");
            observable3 = observableEmpty;
        } else {
            observable3 = observable;
        }
        if ((i & 4096) != 0) {
            Observable observableEmpty2 = Observable.empty();
            Intrinsics.checkNotNullExpressionValue(observableEmpty2, "Observable.empty()");
            observable4 = observableEmpty2;
        } else {
            observable4 = observable2;
        }
        this(str, str2, str3, listEmptyList, str6, mediaMetadata, num3, num4, str7, maybe3, maybe4, observable3, observable4, (i & 8192) != 0 ? false : z);
    }

    public final MediaMetadata getMetadata() {
        return this.metadata;
    }

    public final Integer getSeasonNumber() {
        return this.seasonNumber;
    }

    public final Integer getEpisodeNumber() {
        return this.episodeNumber;
    }

    public final String getEpisodeTitle() {
        return this.episodeTitle;
    }

    public final Maybe<Media> getRelatedVodMedia() {
        return this.relatedVodMedia;
    }

    public final Maybe<Media> getRelatedCatchupMedia() {
        return this.relatedCatchupMedia;
    }

    public final Observable<TVAiring> getReruns() {
        return this.reruns;
    }

    public final Observable<Map<String, Object>> getOffers() {
        return this.offers;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TVProgram(String id, String title, String str, List<? extends Artwork> artworks, String str2, MediaMetadata metadata, Integer num, Integer num2, String str3, Maybe<Media> relatedVodMedia, Maybe<Media> relatedCatchupMedia, Observable<TVAiring> reruns, Observable<Map<String, Object>> offers, boolean z) {
        super(id, title, str, artworks, str2, z, null, 64, null);
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artworks, "artworks");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(relatedVodMedia, "relatedVodMedia");
        Intrinsics.checkNotNullParameter(relatedCatchupMedia, "relatedCatchupMedia");
        Intrinsics.checkNotNullParameter(reruns, "reruns");
        Intrinsics.checkNotNullParameter(offers, "offers");
        this.metadata = metadata;
        this.seasonNumber = num;
        this.episodeNumber = num2;
        this.episodeTitle = str3;
        this.relatedVodMedia = relatedVodMedia;
        this.relatedCatchupMedia = relatedCatchupMedia;
        this.reruns = reruns;
        this.offers = offers;
    }
}
