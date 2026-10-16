package com.dotscreen.bokit.model;

import androidx.exifinterface.media.ExifInterface;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.services.BOService;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Predicate;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ItemCollection.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003BÊ\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0000\u0018\u00010\u0011\u0012\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013\u0012)\b\u0002\u0010\u0015\u001a#\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00110\u0016\u0012\u0014\b\u0002\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00070\u001c\u0012\u000e\b\u0002\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0011¢\u0006\u0002\u0010 J\u001a\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00000*2\u0006\u0010+\u001a\u00020\u0007J\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\u0006\u0010\u001a\u001a\u00020\u0017R\u001f\u0010\u0010\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0000\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R\u0019\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R2\u0010\u0015\u001a#\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00110\u0016¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(¨\u0006-"}, d2 = {"Lcom/dotscreen/bokit/model/ItemCollection;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/dotscreen/bokit/model/BrowsableItem;", "Lcom/dotscreen/bokit/model/PurchasableItem;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "id", "", "title", "summary", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "deepLink", "ratable", "", "children", "Lio/reactivex/Observable;", "itemsCount", "Lio/reactivex/Single;", "", "itemsSource", "Lkotlin/Function1;", "Lcom/dotscreen/bokit/model/ItemSortingOrder;", "Lkotlin/ParameterName;", "name", "order", "externalIds", "", "Lcom/dotscreen/bokit/model/ItemExternalIdScheme;", "offers", "Lcom/dotscreen/bokit/model/Offer;", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ZLio/reactivex/Observable;Lio/reactivex/Single;Lkotlin/jvm/functions/Function1;Ljava/util/Map;Lio/reactivex/Observable;)V", "getChildren", "()Lio/reactivex/Observable;", "items", "getItems", "getItemsCount", "()Lio/reactivex/Single;", "getItemsSource", "()Lkotlin/jvm/functions/Function1;", "childWithId", "Lio/reactivex/Maybe;", "childId", "itemsSortedBy", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class ItemCollection<T extends BrowsableItem> extends PurchasableItem {
    private final Observable<ItemCollection<T>> children;
    private final Observable<T> items;
    private final Single<Integer> itemsCount;
    private final Function1<ItemSortingOrder, Observable<T>> itemsSource;

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ItemCollection(BOService bOService, String str, String str2, String str3, List list, String str4, boolean z, Observable observable, Single single, Function1 function1, Map map, Observable observable2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Observable observable3;
        String str5 = (i & 8) != 0 ? (String) null : str3;
        List listEmptyList = (i & 16) != 0 ? CollectionsKt.emptyList() : list;
        String str6 = (i & 32) != 0 ? (String) null : str4;
        boolean z2 = (i & 64) != 0 ? false : z;
        Observable observable4 = (i & 128) != 0 ? (Observable) null : observable;
        Single singleJust = (i & 256) != 0 ? Single.just(0) : single;
        Function1 function2 = (i & 512) != 0 ? new Function1<ItemSortingOrder, Observable<T>>() { // from class: com.dotscreen.bokit.model.ItemCollection.1
            @Override // kotlin.jvm.functions.Function1
            public final Observable<T> invoke(ItemSortingOrder itemSortingOrder) {
                Intrinsics.checkNotNullParameter(itemSortingOrder, "<anonymous parameter 0>");
                Observable<T> observableEmpty = Observable.empty();
                Intrinsics.checkNotNullExpressionValue(observableEmpty, "Observable.empty()");
                return observableEmpty;
            }
        } : function1;
        Map mapEmptyMap = (i & 1024) != 0 ? MapsKt.emptyMap() : map;
        if ((i & 2048) != 0) {
            Observable observableEmpty = Observable.empty();
            Intrinsics.checkNotNullExpressionValue(observableEmpty, "Observable.empty()");
            observable3 = observableEmpty;
        } else {
            observable3 = observable2;
        }
        this(bOService, str, str2, str5, listEmptyList, str6, z2, observable4, singleJust, function2, mapEmptyMap, observable3);
    }

    public final Observable<ItemCollection<T>> getChildren() {
        return this.children;
    }

    public final Single<Integer> getItemsCount() {
        return this.itemsCount;
    }

    public final Function1<ItemSortingOrder, Observable<T>> getItemsSource() {
        return this.itemsSource;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ItemCollection(BOService boService, String id, String title, String str, List<? extends Artwork> artworks, String str2, boolean z, Observable<ItemCollection<T>> observable, Single<Integer> single, Function1<? super ItemSortingOrder, ? extends Observable<T>> itemsSource, Map<ItemExternalIdScheme, String> externalIds, Observable<Offer> offers) {
        super(boService, id, title, str, artworks, str2, z, externalIds, offers);
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artworks, "artworks");
        Intrinsics.checkNotNullParameter(itemsSource, "itemsSource");
        Intrinsics.checkNotNullParameter(externalIds, "externalIds");
        Intrinsics.checkNotNullParameter(offers, "offers");
        this.children = observable;
        this.itemsCount = single;
        this.itemsSource = itemsSource;
        this.items = itemsSortedBy(ItemSortingOrder.Default);
    }

    public final Observable<T> getItems() {
        return this.items;
    }

    public final Observable<T> itemsSortedBy(ItemSortingOrder order) {
        Intrinsics.checkNotNullParameter(order, "order");
        return this.itemsSource.invoke(order);
    }

    public final Maybe<ItemCollection<T>> childWithId(final String childId) {
        Observable<ItemCollection<T>> observableFilter;
        Maybe<ItemCollection<T>> maybeFirstElement;
        Intrinsics.checkNotNullParameter(childId, "childId");
        Observable<ItemCollection<T>> observable = this.children;
        if (observable != null && (observableFilter = observable.filter(new Predicate<ItemCollection<T>>() { // from class: com.dotscreen.bokit.model.ItemCollection.childWithId.1
            @Override // io.reactivex.functions.Predicate
            public final boolean test(ItemCollection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Intrinsics.areEqual(childId, it.getId());
            }
        })) != null && (maybeFirstElement = observableFilter.firstElement()) != null) {
            return maybeFirstElement;
        }
        Maybe<ItemCollection<T>> maybeEmpty = Maybe.empty();
        Intrinsics.checkNotNullExpressionValue(maybeEmpty, "Maybe.empty()");
        return maybeEmpty;
    }
}
