package com.dotscreen.bokit.model;

import com.dotscreen.bokit.services.BOService;
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

/* JADX INFO: compiled from: CatalogSection.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003BÖ\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\u0016\b\u0002\u0010\u0012\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0001\u0018\u00010\u0013\u0012\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015\u0012)\b\u0002\u0010\u0017\u001a#\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00130\u0018\u0012\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e\u0012\u0014\b\u0002\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00070!¢\u0006\u0002\u0010#R\u0019\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e¢\u0006\n\n\u0002\u0010&\u001a\u0004\b$\u0010%R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(¨\u0006)"}, d2 = {"Lcom/dotscreen/bokit/model/CatalogSection;", "Lcom/dotscreen/bokit/model/ItemCollection;", "Lcom/dotscreen/bokit/model/BrowsableItem;", "Lcom/dotscreen/bokit/model/BrowsableItemCollection;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "id", "", "title", "summary", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "deepLink", "ratable", "", "parentalLevel", "Lcom/dotscreen/bokit/model/ParentalRating;", "children", "Lio/reactivex/Observable;", "itemsCount", "Lio/reactivex/Single;", "", "itemsSource", "Lkotlin/Function1;", "Lcom/dotscreen/bokit/model/ItemSortingOrder;", "Lkotlin/ParameterName;", "name", "order", "classifiers", "", "Lcom/dotscreen/bokit/model/Classifier;", "externalIds", "", "Lcom/dotscreen/bokit/model/ItemExternalIdScheme;", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ZLcom/dotscreen/bokit/model/ParentalRating;Lio/reactivex/Observable;Lio/reactivex/Single;Lkotlin/jvm/functions/Function1;[Lcom/dotscreen/bokit/model/Classifier;Ljava/util/Map;)V", "getClassifiers", "()[Lcom/dotscreen/bokit/model/Classifier;", "[Lcom/dotscreen/bokit/model/Classifier;", "getParentalLevel", "()Lcom/dotscreen/bokit/model/ParentalRating;", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class CatalogSection extends ItemCollection<BrowsableItem> {
    private final Classifier[] classifiers;
    private final ParentalRating parentalLevel;

    public /* synthetic */ CatalogSection(BOService bOService, String str, String str2, String str3, List list, String str4, boolean z, ParentalRating parentalRating, Observable observable, Single single, Function1 function1, Classifier[] classifierArr, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bOService, str, str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? CollectionsKt.emptyList() : list, (i & 32) != 0 ? (String) null : str4, (i & 64) != 0 ? false : z, (i & 128) != 0 ? (ParentalRating) null : parentalRating, (i & 256) != 0 ? (Observable) null : observable, (i & 512) != 0 ? Single.just(0) : single, (i & 1024) != 0 ? new Function1<ItemSortingOrder, Observable<BrowsableItem>>() { // from class: com.dotscreen.bokit.model.CatalogSection.1
            @Override // kotlin.jvm.functions.Function1
            public final Observable<BrowsableItem> invoke(ItemSortingOrder itemSortingOrder) {
                Intrinsics.checkNotNullParameter(itemSortingOrder, "<anonymous parameter 0>");
                Observable<BrowsableItem> observableEmpty = Observable.empty();
                Intrinsics.checkNotNullExpressionValue(observableEmpty, "Observable.empty()");
                return observableEmpty;
            }
        } : function1, (i & 2048) != 0 ? new Classifier[0] : classifierArr, (i & 4096) != 0 ? MapsKt.emptyMap() : map);
    }

    public final ParentalRating getParentalLevel() {
        return this.parentalLevel;
    }

    public final Classifier[] getClassifiers() {
        return this.classifiers;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CatalogSection(BOService boService, String id, String title, String str, List<? extends Artwork> artworks, String str2, boolean z, ParentalRating parentalRating, Observable<ItemCollection<BrowsableItem>> observable, Single<Integer> single, Function1<? super ItemSortingOrder, ? extends Observable<BrowsableItem>> itemsSource, Classifier[] classifiers, Map<ItemExternalIdScheme, String> externalIds) {
        super(boService, id, title, str, artworks, str2, z, observable, single, itemsSource, externalIds, null, 2048, null);
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artworks, "artworks");
        Intrinsics.checkNotNullParameter(itemsSource, "itemsSource");
        Intrinsics.checkNotNullParameter(classifiers, "classifiers");
        Intrinsics.checkNotNullParameter(externalIds, "externalIds");
        this.parentalLevel = parentalRating;
        this.classifiers = classifiers;
    }
}
