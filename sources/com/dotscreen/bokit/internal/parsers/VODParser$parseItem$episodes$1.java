package com.dotscreen.bokit.internal.parsers;

import com.dotscreen.bokit.NotImplementedException;
import com.dotscreen.bokit.VODContentEpisodesQuery;
import com.dotscreen.bokit.fragment.ContentFragmentBase;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.Episode;
import com.dotscreen.bokit.model.ItemSortingOrder;
import com.dotscreen.bokit.services.BOService;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: VODParser.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/Episode;", "order", "Lcom/dotscreen/bokit/model/ItemSortingOrder;", "invoke"}, k = 3, mv = {1, 4, 1})
final class VODParser$parseItem$episodes$1 extends Lambda implements Function1<ItemSortingOrder, Observable<Episode>> {
    final /* synthetic */ BOService $boService;
    final /* synthetic */ ContentFragmentBase $contentBase;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VODParser$parseItem$episodes$1(BOService bOService, ContentFragmentBase contentFragmentBase) {
        super(1);
        this.$boService = bOService;
        this.$contentBase = contentFragmentBase;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Observable<Episode> invoke(ItemSortingOrder order) {
        Intrinsics.checkNotNullParameter(order, "order");
        BOService bOService = this.$boService;
        String strId = this.$contentBase.id();
        Intrinsics.checkNotNullExpressionValue(strId, "contentBase.id()");
        Observable observableFlatMapObservable = bOService.fetchVODContentEpisodes$bokit_android_release(strId).flatMapObservable(new Function<VODContentEpisodesQuery.Data, ObservableSource<? extends Episode>>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser$parseItem$episodes$1.1
            @Override // io.reactivex.functions.Function
            public final ObservableSource<? extends Episode> apply(final VODContentEpisodesQuery.Data data) {
                Intrinsics.checkNotNullParameter(data, "data");
                return Observable.create(new ObservableOnSubscribe<Episode>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser.parseItem.episodes.1.1.1
                    @Override // io.reactivex.ObservableOnSubscribe
                    public final void subscribe(ObservableEmitter<Episode> observer) throws NotImplementedException {
                        Intrinsics.checkNotNullParameter(observer, "observer");
                        VODContentEpisodesQuery.LookupContent lookupContent = data.lookupContent();
                        if (lookupContent == null) {
                            throw new NullPointerException("null cannot be cast to non-null type com.dotscreen.bokit.VODContentEpisodesQuery.AsSeries");
                        }
                        List<VODContentEpisodesQuery.Item> listItems = ((VODContentEpisodesQuery.AsSeries) lookupContent).episodes().items();
                        Intrinsics.checkNotNullExpressionValue(listItems, "(data.lookupContent() as…eries).episodes().items()");
                        for (VODContentEpisodesQuery.Item item : listItems) {
                            VODParser vODParser = VODParser.INSTANCE;
                            BOService bOService2 = VODParser$parseItem$episodes$1.this.$boService;
                            ContentFragmentBase contentFragmentBase = item.fragments().contentFragmentBase();
                            Intrinsics.checkNotNull(contentFragmentBase);
                            Intrinsics.checkNotNullExpressionValue(contentFragmentBase, "item.fragments().contentFragmentBase()!!");
                            BrowsableItem item2 = vODParser.parseItem(bOService2, contentFragmentBase, item.fragments().contentFragmentDetails());
                            if (item2 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type com.dotscreen.bokit.model.Episode");
                            }
                            observer.onNext((Episode) item2);
                        }
                        observer.onComplete();
                    }
                });
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMapObservable, "boService.fetchVODConten…                        }");
        return observableFlatMapObservable;
    }
}
