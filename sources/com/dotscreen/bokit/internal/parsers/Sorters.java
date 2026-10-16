package com.dotscreen.bokit.internal.parsers;

import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Item;
import com.dotscreen.bokit.model.ItemSortingOrder;
import com.dotscreen.bokit.model.TVAiring;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Sorters.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0007\u001a\u00020\bJ\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\n0\u00042\u0006\u0010\u0007\u001a\u00020\bJ\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\f0\u00042\u0006\u0010\u0007\u001a\u00020\b¨\u0006\r"}, d2 = {"Lcom/dotscreen/bokit/internal/parsers/Sorters;", "", "()V", "airingsSorter", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/TVAiring;", "obs", "order", "Lcom/dotscreen/bokit/model/ItemSortingOrder;", "channelSorter", "Lcom/dotscreen/bokit/model/Channel;", "itemSorter", "Lcom/dotscreen/bokit/model/Item;", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final class Sorters {
    public static final Sorters INSTANCE = new Sorters();

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 1})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[ItemSortingOrder.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[ItemSortingOrder.Descending.ordinal()] = 1;
            $EnumSwitchMapping$0[ItemSortingOrder.Alphabetical.ordinal()] = 2;
            $EnumSwitchMapping$0[ItemSortingOrder.AlphabeticalInverted.ordinal()] = 3;
            int[] iArr2 = new int[ItemSortingOrder.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[ItemSortingOrder.Descending.ordinal()] = 1;
            int[] iArr3 = new int[ItemSortingOrder.values().length];
            $EnumSwitchMapping$2 = iArr3;
            iArr3[ItemSortingOrder.Descending.ordinal()] = 1;
        }
    }

    private Sorters() {
    }

    public final Observable<Item> itemSorter(Observable<Item> obs, ItemSortingOrder order) {
        Intrinsics.checkNotNullParameter(obs, "obs");
        Intrinsics.checkNotNullParameter(order, "order");
        int i = WhenMappings.$EnumSwitchMapping$0[order.ordinal()];
        if (i == 1) {
            Observable<Item> observableFlatMapObservable = obs.toSortedList().map(new Function<List<Item>, List<? extends Item>>() { // from class: com.dotscreen.bokit.internal.parsers.Sorters.itemSorter.1
                @Override // io.reactivex.functions.Function
                public final List<Item> apply(List<Item> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return CollectionsKt.reversed(it);
                }
            }).flatMapObservable(new Function<List<? extends Item>, ObservableSource<? extends Item>>() { // from class: com.dotscreen.bokit.internal.parsers.Sorters.itemSorter.2
                @Override // io.reactivex.functions.Function
                public final ObservableSource<? extends Item> apply(List<? extends Item> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Observable.fromIterable(it);
                }
            });
            Intrinsics.checkNotNullExpressionValue(observableFlatMapObservable, "obs.toSortedList().map {…rvable.fromIterable(it) }");
            return observableFlatMapObservable;
        }
        if (i == 2) {
            Observable<Item> observableSorted = obs.sorted(new Comparator<Item>() { // from class: com.dotscreen.bokit.internal.parsers.Sorters.itemSorter.3
                @Override // java.util.Comparator
                public final int compare(Item item, Item item2) {
                    return item.getTitle().compareTo(item2.getTitle());
                }
            });
            Intrinsics.checkNotNullExpressionValue(observableSorted, "obs.sorted { item1, item….compareTo(item2.title) }");
            return observableSorted;
        }
        if (i != 3) {
            return obs;
        }
        Observable<Item> observableSorted2 = obs.sorted(new Comparator<Item>() { // from class: com.dotscreen.bokit.internal.parsers.Sorters.itemSorter.4
            @Override // java.util.Comparator
            public final int compare(Item item, Item item2) {
                return item2.getTitle().compareTo(item.getTitle());
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableSorted2, "obs.sorted { item1, item….compareTo(item1.title) }");
        return observableSorted2;
    }

    public final Observable<Channel> channelSorter(Observable<Channel> obs, ItemSortingOrder order) {
        Intrinsics.checkNotNullParameter(obs, "obs");
        Intrinsics.checkNotNullParameter(order, "order");
        if (WhenMappings.$EnumSwitchMapping$1[order.ordinal()] == 1) {
            Observable<Channel> observableSorted = obs.sorted(new Comparator<Channel>() { // from class: com.dotscreen.bokit.internal.parsers.Sorters.channelSorter.1
                @Override // java.util.Comparator
                public final int compare(Channel channel, Channel channel2) {
                    return channel2.getZappingNumber() - channel.getZappingNumber();
                }
            });
            Intrinsics.checkNotNullExpressionValue(observableSorted, "obs.sorted { channel1, c…ppingNumber\n            }");
            return observableSorted;
        }
        Observable<Item> map = obs.map(new Function<Channel, Item>() { // from class: com.dotscreen.bokit.internal.parsers.Sorters.channelSorter.2
            @Override // io.reactivex.functions.Function
            public final Item apply(Channel it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it;
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "obs.map { it as Item }");
        Observable map2 = itemSorter(map, order).map(new Function<Item, Channel>() { // from class: com.dotscreen.bokit.internal.parsers.Sorters.channelSorter.3
            @Override // io.reactivex.functions.Function
            public final Channel apply(Item it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return (Channel) it;
            }
        });
        Intrinsics.checkNotNullExpressionValue(map2, "itemSorter(obs.map { it …er).map { it as Channel }");
        return map2;
    }

    public final Observable<TVAiring> airingsSorter(Observable<TVAiring> obs, ItemSortingOrder order) {
        Intrinsics.checkNotNullParameter(obs, "obs");
        Intrinsics.checkNotNullParameter(order, "order");
        if (WhenMappings.$EnumSwitchMapping$2[order.ordinal()] == 1) {
            Observable<TVAiring> observableSorted = obs.sorted(new Comparator<TVAiring>() { // from class: com.dotscreen.bokit.internal.parsers.Sorters.airingsSorter.1
                @Override // java.util.Comparator
                public final int compare(TVAiring tVAiring, TVAiring tVAiring2) {
                    return tVAiring.getStartDate().compareTo(tVAiring2.getStartDate());
                }
            });
            Intrinsics.checkNotNullExpressionValue(observableSorted, "obs.sorted { airing1, ai…reTo(airing2.startDate) }");
            return observableSorted;
        }
        Observable<Item> map = obs.map(new Function<TVAiring, Item>() { // from class: com.dotscreen.bokit.internal.parsers.Sorters.airingsSorter.2
            @Override // io.reactivex.functions.Function
            public final Item apply(TVAiring it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it;
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "obs.map { it as Item }");
        Observable map2 = itemSorter(map, order).map(new Function<Item, TVAiring>() { // from class: com.dotscreen.bokit.internal.parsers.Sorters.airingsSorter.3
            @Override // io.reactivex.functions.Function
            public final TVAiring apply(Item it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return (TVAiring) it;
            }
        });
        Intrinsics.checkNotNullExpressionValue(map2, "itemSorter(obs.map { it …r).map { it as TVAiring }");
        return map2;
    }
}
