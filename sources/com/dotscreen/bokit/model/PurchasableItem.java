package com.dotscreen.bokit.model;

import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.UserService;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.rxkotlin.Observables;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PurchasableItem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001Bq\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00050\u000f\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\u0002\u0010\u0014R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00128F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0018¨\u0006\u001c"}, d2 = {"Lcom/dotscreen/bokit/model/PurchasableItem;", "Lcom/dotscreen/bokit/model/BrowsableItem;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "id", "", "title", "summary", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "deepLink", "ratable", "", "externalIds", "", "Lcom/dotscreen/bokit/model/ItemExternalIdScheme;", "offers", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/Offer;", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ZLjava/util/Map;Lio/reactivex/Observable;)V", "getBoService", "()Lcom/dotscreen/bokit/services/BOService;", "getOffers", "()Lio/reactivex/Observable;", "playbackRights", "Lcom/dotscreen/bokit/model/PlaybackRight;", "getPlaybackRights", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class PurchasableItem extends BrowsableItem {
    private final BOService boService;
    private final Observable<Offer> offers;

    protected final BOService getBoService() {
        return this.boService;
    }

    public /* synthetic */ PurchasableItem(BOService bOService, String str, String str2, String str3, List list, String str4, boolean z, Map map, Observable observable, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bOService, str, str2, str3, (i & 16) != 0 ? CollectionsKt.emptyList() : list, (i & 32) != 0 ? (String) null : str4, (i & 64) != 0 ? false : z, (i & 128) != 0 ? MapsKt.emptyMap() : map, observable);
    }

    public final Observable<Offer> getOffers() {
        return this.offers;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PurchasableItem(BOService boService, String id, String title, String str, List<? extends Artwork> artworks, String str2, boolean z, Map<ItemExternalIdScheme, String> externalIds, Observable<Offer> offers) {
        super(id, title, str, artworks, str2, z, externalIds);
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artworks, "artworks");
        Intrinsics.checkNotNullParameter(externalIds, "externalIds");
        Intrinsics.checkNotNullParameter(offers, "offers");
        this.boService = boService;
        this.offers = offers;
    }

    public final Observable<PlaybackRight> getPlaybackRights() {
        Observable observableEmpty;
        Maybe<User> activeUser;
        Observable<User> observable;
        UserService userService = this.boService.getUserService();
        if (userService == null || (activeUser = userService.getActiveUser()) == null || (observable = activeUser.toObservable()) == null || (observableEmpty = observable.map(new Function<User, Object>() { // from class: com.dotscreen.bokit.model.PurchasableItem$playbackRights$obs1$1
            @Override // io.reactivex.functions.Function
            public final Object apply(User it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it;
            }
        })) == null) {
            observableEmpty = Observable.empty();
        }
        Observable observableDefaultIfEmpty = observableEmpty.defaultIfEmpty("");
        Intrinsics.checkNotNullExpressionValue(observableDefaultIfEmpty, "(boService.userService?.…defaultIfEmpty(\"\" as Any)");
        Observable obs2 = this.offers.map(new Function<Offer, Object>() { // from class: com.dotscreen.bokit.model.PurchasableItem$playbackRights$obs2$1
            @Override // io.reactivex.functions.Function
            public final Object apply(Offer it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it;
            }
        }).defaultIfEmpty("");
        Observables observables = Observables.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(obs2, "obs2");
        Observable<PlaybackRight> observableFlatMap = observables.combineLatest(observableDefaultIfEmpty, obs2).flatMap(new Function<Pair<? extends Object, ? extends Object>, ObservableSource<? extends PlaybackRight>>() { // from class: com.dotscreen.bokit.model.PurchasableItem$playbackRights$1
            @Override // io.reactivex.functions.Function
            public final ObservableSource<? extends PlaybackRight> apply(Pair<? extends Object, ? extends Object> it) {
                Observable<PlaybackRight> observable2;
                Intrinsics.checkNotNullParameter(it, "it");
                UserService userService2 = this.this$0.getBoService().getUserService();
                if (userService2 != null) {
                    Maybe<PlaybackRight> maybePlaybackRight = userService2.playbackRight((User) (it.getFirst() instanceof User ? it.getFirst() : null), this.this$0, (Offer) (it.getSecond() instanceof Offer ? it.getSecond() : null));
                    if (maybePlaybackRight != null && (observable2 = maybePlaybackRight.toObservable()) != null) {
                        return observable2;
                    }
                }
                return Observable.empty();
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMap, "Observables.combineLates…y()\n                    }");
        return observableFlatMap;
    }
}
