package com.dotscreen.tv.common.bokit;

import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.Content;
import com.dotscreen.bokit.model.ItemExternalIdScheme;
import com.dotscreen.bokit.model.MediaMetadata;
import com.dotscreen.bokit.model.Offer;
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

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\u008d\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00050\u0013\u0012\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\u0002\u0010\u0018J\u0006\u0010\u001c\u001a\u00020\u0005R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001a¨\u0006\u001d"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RecoContent;", "Lcom/dotscreen/bokit/model/Content;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "id", "", "title", "summary", "category", "type", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "deepLink", TtmlNode.TAG_METADATA, "Lcom/dotscreen/bokit/model/MediaMetadata;", "ratable", "", "externalIds", "", "Lcom/dotscreen/bokit/model/ItemExternalIdScheme;", "offers", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/Offer;", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/dotscreen/bokit/model/MediaMetadata;ZLjava/util/Map;Lio/reactivex/Observable;)V", "getCategory", "()Ljava/lang/String;", "getType", "getEngine", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public class RecoContent extends Content {
    private final String category;
    private final String type;

    public final String getCategory() {
        return this.category;
    }

    public final String getType() {
        return this.type;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ RecoContent(BOService bOService, String str, String str2, String str3, String str4, String str5, List list, String str6, MediaMetadata mediaMetadata, boolean z, Map map, Observable observable, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Observable observable2;
        List listEmptyList = (i & 64) != 0 ? CollectionsKt.emptyList() : list;
        String str7 = (i & 128) != 0 ? (String) null : str6;
        boolean z2 = (i & 512) != 0 ? false : z;
        Map mapEmptyMap = (i & 1024) != 0 ? MapsKt.emptyMap() : map;
        if ((i & 2048) != 0) {
            Observable observableEmpty = Observable.empty();
            Intrinsics.checkExpressionValueIsNotNull(observableEmpty, "Observable.empty()");
            observable2 = observableEmpty;
        } else {
            observable2 = observable;
        }
        this(bOService, str, str2, str3, str4, str5, listEmptyList, str7, mediaMetadata, z2, mapEmptyMap, observable2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecoContent(BOService boService, String id, String title, String str, String str2, String type, List<? extends Artwork> artworks, String str3, MediaMetadata metadata, boolean z, Map<ItemExternalIdScheme, String> externalIds, Observable<Offer> offers) {
        super(boService, id, title, str, artworks, str3, metadata, z, externalIds, offers);
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(artworks, "artworks");
        Intrinsics.checkParameterIsNotNull(metadata, "metadata");
        Intrinsics.checkParameterIsNotNull(externalIds, "externalIds");
        Intrinsics.checkParameterIsNotNull(offers, "offers");
        this.category = str2;
        this.type = type;
    }

    /* JADX INFO: renamed from: getEngine, reason: from getter */
    public final String getType() {
        return this.type;
    }
}
