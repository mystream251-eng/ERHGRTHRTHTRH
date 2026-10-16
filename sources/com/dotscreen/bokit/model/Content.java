package com.dotscreen.bokit.model;

import com.dotscreen.bokit.services.BOService;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import io.reactivex.Observable;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Content.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B{\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00050\u0011\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\u0002\u0010\u0016R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/dotscreen/bokit/model/Content;", "Lcom/dotscreen/bokit/model/Media;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "id", "", "title", "summary", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "deepLink", TtmlNode.TAG_METADATA, "Lcom/dotscreen/bokit/model/MediaMetadata;", "ratable", "", "externalIds", "", "Lcom/dotscreen/bokit/model/ItemExternalIdScheme;", "offers", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/Offer;", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/dotscreen/bokit/model/MediaMetadata;ZLjava/util/Map;Lio/reactivex/Observable;)V", "getMetadata", "()Lcom/dotscreen/bokit/model/MediaMetadata;", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class Content extends Media {
    private final MediaMetadata metadata;

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ Content(BOService bOService, String str, String str2, String str3, List list, String str4, MediaMetadata mediaMetadata, boolean z, Map map, Observable observable, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Observable observable2;
        List listEmptyList = (i & 16) != 0 ? CollectionsKt.emptyList() : list;
        String str5 = (i & 32) != 0 ? (String) null : str4;
        boolean z2 = (i & 128) != 0 ? false : z;
        Map mapEmptyMap = (i & 256) != 0 ? MapsKt.emptyMap() : map;
        if ((i & 512) != 0) {
            Observable observableEmpty = Observable.empty();
            Intrinsics.checkNotNullExpressionValue(observableEmpty, "Observable.empty()");
            observable2 = observableEmpty;
        } else {
            observable2 = observable;
        }
        this(bOService, str, str2, str3, listEmptyList, str5, mediaMetadata, z2, mapEmptyMap, observable2);
    }

    public final MediaMetadata getMetadata() {
        return this.metadata;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Content(BOService boService, String id, String title, String str, List<? extends Artwork> artworks, String str2, MediaMetadata metadata, boolean z, Map<ItemExternalIdScheme, String> externalIds, Observable<Offer> offers) {
        super(boService, id, title, str, artworks, str2, z, externalIds, offers);
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artworks, "artworks");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(externalIds, "externalIds");
        Intrinsics.checkNotNullParameter(offers, "offers");
        this.metadata = metadata;
    }
}
