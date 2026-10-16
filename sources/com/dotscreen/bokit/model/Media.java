package com.dotscreen.bokit.model;

import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
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

/* JADX INFO: compiled from: Media.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001Bu\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00050\u000f\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u001a\u001a\u00020\rR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00168F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Lcom/dotscreen/bokit/model/Media;", "Lcom/dotscreen/bokit/model/PurchasableItem;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "id", "", "title", "summary", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "deepLink", "ratable", "", "externalIds", "", "Lcom/dotscreen/bokit/model/ItemExternalIdScheme;", "offers", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/Offer;", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ZLjava/util/Map;Lio/reactivex/Observable;)V", "playbackInfo", "Lio/reactivex/Maybe;", "Lcom/dotscreen/bokit/model/PlaybackInfo;", "getPlaybackInfo", "()Lio/reactivex/Maybe;", ToolboxAnalyticsModuleKt.EVENT_RESUME, "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class Media extends PurchasableItem {
    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ Media(BOService bOService, String str, String str2, String str3, List list, String str4, boolean z, Map map, Observable observable, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Observable observable2;
        String str5 = (i & 8) != 0 ? (String) null : str3;
        List listEmptyList = (i & 16) != 0 ? CollectionsKt.emptyList() : list;
        String str6 = (i & 32) != 0 ? (String) null : str4;
        boolean z2 = (i & 64) != 0 ? false : z;
        Map mapEmptyMap = (i & 128) != 0 ? MapsKt.emptyMap() : map;
        if ((i & 256) != 0) {
            Observable observableEmpty = Observable.empty();
            Intrinsics.checkNotNullExpressionValue(observableEmpty, "Observable.empty()");
            observable2 = observableEmpty;
        } else {
            observable2 = observable;
        }
        this(bOService, str, str2, str5, listEmptyList, str6, z2, mapEmptyMap, observable2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Media(BOService boService, String id, String title, String str, List<? extends Artwork> artworks, String str2, boolean z, Map<ItemExternalIdScheme, String> externalIds, Observable<Offer> offers) {
        super(boService, id, title, str, artworks, str2, z, externalIds, offers);
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artworks, "artworks");
        Intrinsics.checkNotNullParameter(externalIds, "externalIds");
        Intrinsics.checkNotNullParameter(offers, "offers");
    }

    public final Maybe<PlaybackInfo> playbackInfo(final boolean resume) {
        Observable observableEmpty;
        Maybe<User> activeUser;
        Observable<User> observable;
        UserService userService = getBoService().getUserService();
        if (userService == null || (activeUser = userService.getActiveUser()) == null || (observable = activeUser.toObservable()) == null || (observableEmpty = observable.map(new Function<User, Object>() { // from class: com.dotscreen.bokit.model.Media$playbackInfo$obs1$1
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
        Maybe<PlaybackInfo> maybeFirstElement = Observables.INSTANCE.combineLatest(observableDefaultIfEmpty, getPlaybackRights()).flatMapMaybe(new Function<Pair<? extends Object, ? extends PlaybackRight>, MaybeSource<? extends PlaybackInfo>>() { // from class: com.dotscreen.bokit.model.Media.playbackInfo.1
            @Override // io.reactivex.functions.Function
            public /* bridge */ /* synthetic */ MaybeSource<? extends PlaybackInfo> apply(Pair<? extends Object, ? extends PlaybackRight> pair) {
                return apply2((Pair<? extends Object, PlaybackRight>) pair);
            }

            /* JADX INFO: renamed from: apply, reason: avoid collision after fix types in other method */
            public final MaybeSource<? extends PlaybackInfo> apply2(Pair<? extends Object, PlaybackRight> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                UserService userService2 = Media.this.getBoService().getUserService();
                if (userService2 != null) {
                    Maybe<PlaybackInfo> maybePlaybackInfo = userService2.playbackInfo((User) (it.getFirst() instanceof User ? it.getFirst() : null), Media.this, it.getSecond(), resume);
                    if (maybePlaybackInfo != null) {
                        return maybePlaybackInfo;
                    }
                }
                return Maybe.empty();
            }
        }).firstElement();
        Intrinsics.checkNotNullExpressionValue(maybeFirstElement, "Observables.combineLates…         }.firstElement()");
        return maybeFirstElement;
    }

    public final Maybe<PlaybackInfo> getPlaybackInfo() {
        return playbackInfo(false);
    }
}
