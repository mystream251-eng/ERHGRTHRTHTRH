package com.dotscreen.bokit.model;

import com.dotscreen.bokit.internal.tools.TVAiringSegmentKt;
import com.dotscreen.bokit.internal.tools.TimeHelper;
import com.dotscreen.bokit.services.BOService;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Lineup.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001Bº\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0001\u0018\u00010\u0010\u0012\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012\u0012)\b\u0002\u0010\u0014\u001a#\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00100\u0015\u0012\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00060\u001b¢\u0006\u0002\u0010\u001dJ\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010!\u001a\u00020\"J\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010#\u001a\u00020$J\u001c\u0010%\u001a\b\u0012\u0004\u0012\u00020 0\u00102\u0006\u0010&\u001a\u00020$2\u0006\u0010'\u001a\u00020$¨\u0006("}, d2 = {"Lcom/dotscreen/bokit/model/Lineup;", "Lcom/dotscreen/bokit/model/ItemCollection;", "Lcom/dotscreen/bokit/model/Channel;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "id", "", "title", "summary", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "deepLink", "ratable", "", "children", "Lio/reactivex/Observable;", "itemsCount", "Lio/reactivex/Single;", "", "itemsSource", "Lkotlin/Function1;", "Lcom/dotscreen/bokit/model/ItemSortingOrder;", "Lkotlin/ParameterName;", "name", "order", "externalIds", "", "Lcom/dotscreen/bokit/model/ItemExternalIdScheme;", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ZLio/reactivex/Observable;Lio/reactivex/Single;Lkotlin/jvm/functions/Function1;Ljava/util/Map;)V", "airing", "Lio/reactivex/Maybe;", "Lcom/dotscreen/bokit/model/TVAiring;", "timeOfDay", "Lcom/dotscreen/bokit/model/TVAiringTimeOfDay;", "time", "Ljava/util/Date;", "airings", "startTime", "endTime", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class Lineup extends ItemCollection<Channel> {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 1})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TVAiringTimeOfDay.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[TVAiringTimeOfDay.Now.ordinal()] = 1;
            $EnumSwitchMapping$0[TVAiringTimeOfDay.Next.ordinal()] = 2;
            $EnumSwitchMapping$0[TVAiringTimeOfDay.PrimeTime.ordinal()] = 3;
            $EnumSwitchMapping$0[TVAiringTimeOfDay.LateNight.ordinal()] = 4;
        }
    }

    public /* synthetic */ Lineup(BOService bOService, String str, String str2, String str3, List list, String str4, boolean z, Observable observable, Single single, Function1 function1, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bOService, str, str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? CollectionsKt.emptyList() : list, (i & 32) != 0 ? (String) null : str4, (i & 64) != 0 ? false : z, (i & 128) != 0 ? (Observable) null : observable, (i & 256) != 0 ? Single.just(0) : single, (i & 512) != 0 ? new Function1<ItemSortingOrder, Observable<Channel>>() { // from class: com.dotscreen.bokit.model.Lineup.1
            @Override // kotlin.jvm.functions.Function1
            public final Observable<Channel> invoke(ItemSortingOrder itemSortingOrder) {
                Intrinsics.checkNotNullParameter(itemSortingOrder, "<anonymous parameter 0>");
                Observable<Channel> observableEmpty = Observable.empty();
                Intrinsics.checkNotNullExpressionValue(observableEmpty, "Observable.empty()");
                return observableEmpty;
            }
        } : function1, (i & 1024) != 0 ? MapsKt.emptyMap() : map);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Lineup(BOService boService, String id, String title, String str, List<? extends Artwork> artworks, String str2, boolean z, Observable<ItemCollection<Channel>> observable, Single<Integer> single, Function1<? super ItemSortingOrder, ? extends Observable<Channel>> itemsSource, Map<ItemExternalIdScheme, String> externalIds) {
        super(boService, id, title, str, artworks, str2, z, observable, single, itemsSource, externalIds, null, 2048, null);
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artworks, "artworks");
        Intrinsics.checkNotNullParameter(itemsSource, "itemsSource");
        Intrinsics.checkNotNullParameter(externalIds, "externalIds");
    }

    public final Maybe<TVAiring> airing(TVAiringTimeOfDay timeOfDay) {
        Intrinsics.checkNotNullParameter(timeOfDay, "timeOfDay");
        Observable<TVAiring> observableAirings = getBoService().getLiveTVService().airings(TVAiringSegmentKt.segmentIdForDate(TimeHelper.INSTANCE.now()), getId());
        int i = WhenMappings.$EnumSwitchMapping$0[timeOfDay.ordinal()];
        if (i == 1) {
            Maybe<TVAiring> maybeFirstElement = observableAirings.filter(new Predicate<TVAiring>() { // from class: com.dotscreen.bokit.model.Lineup.airing.1
                @Override // io.reactivex.functions.Predicate
                public final boolean test(TVAiring it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return it.isLive();
                }
            }).firstElement();
            Intrinsics.checkNotNullExpressionValue(maybeFirstElement, "obs\n                    …          .firstElement()");
            return maybeFirstElement;
        }
        if (i == 2) {
            Maybe<TVAiring> maybeFirstElement2 = observableAirings.skipWhile(new Predicate<TVAiring>() { // from class: com.dotscreen.bokit.model.Lineup.airing.2
                @Override // io.reactivex.functions.Predicate
                public final boolean test(TVAiring it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return it.isPast() || it.isLive();
                }
            }).firstElement();
            Intrinsics.checkNotNullExpressionValue(maybeFirstElement2, "obs\n                    …          .firstElement()");
            return maybeFirstElement2;
        }
        if (i == 3) {
            Maybe<TVAiring> maybeFirstElement3 = airings(TimeHelper.INSTANCE.now(), TimeHelper.INSTANCE.endOfDay(TimeHelper.INSTANCE.now())).filter(new Predicate<TVAiring>() { // from class: com.dotscreen.bokit.model.Lineup.airing.3
                @Override // io.reactivex.functions.Predicate
                public final boolean test(TVAiring it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return it.getIsPrimeTime();
                }
            }).firstElement();
            Intrinsics.checkNotNullExpressionValue(maybeFirstElement3, "airings(TimeHelper.now()…          .firstElement()");
            return maybeFirstElement3;
        }
        if (i != 4) {
            throw new NoWhenBranchMatchedException();
        }
        Maybe<TVAiring> maybeFirstElement4 = airings(TimeHelper.INSTANCE.now(), TimeHelper.INSTANCE.endOfDay(TimeHelper.INSTANCE.now())).filter(new Predicate<TVAiring>() { // from class: com.dotscreen.bokit.model.Lineup.airing.4
            @Override // io.reactivex.functions.Predicate
            public final boolean test(TVAiring it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getIsLateNight();
            }
        }).firstElement();
        Intrinsics.checkNotNullExpressionValue(maybeFirstElement4, "airings(TimeHelper.now()…          .firstElement()");
        return maybeFirstElement4;
    }

    public final Maybe<TVAiring> airing(final Date time) {
        Intrinsics.checkNotNullParameter(time, "time");
        Maybe<TVAiring> maybeFirstElement = getBoService().getLiveTVService().airings(TVAiringSegmentKt.segmentIdForDate(time), getId()).filter(new Predicate<TVAiring>() { // from class: com.dotscreen.bokit.model.Lineup.airing.5
            @Override // io.reactivex.functions.Predicate
            public final boolean test(TVAiring it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.isAiredAt(time);
            }
        }).firstElement();
        Intrinsics.checkNotNullExpressionValue(maybeFirstElement, "boService.liveTVService.…          .firstElement()");
        return maybeFirstElement;
    }

    public final Observable<TVAiring> airings(final Date startTime, final Date endTime) {
        Intrinsics.checkNotNullParameter(startTime, "startTime");
        Intrinsics.checkNotNullParameter(endTime, "endTime");
        long jSegmentIdForDate = TVAiringSegmentKt.segmentIdForDate(startTime);
        Observable<TVAiring> observableDistinct = Observable.rangeLong(jSegmentIdForDate, (TVAiringSegmentKt.segmentIdForDate(endTime) - jSegmentIdForDate) + 1).concatMap(new Function<Long, ObservableSource<? extends TVAiring>>() { // from class: com.dotscreen.bokit.model.Lineup.airings.1
            @Override // io.reactivex.functions.Function
            public final ObservableSource<? extends TVAiring> apply(Long it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Lineup.this.getBoService().getLiveTVService().airings(it.longValue(), Lineup.this.getId());
            }
        }).filter(new Predicate<TVAiring>() { // from class: com.dotscreen.bokit.model.Lineup.airings.2
            @Override // io.reactivex.functions.Predicate
            public final boolean test(TVAiring it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.isAiredBetween(startTime, endTime);
            }
        }).distinct();
        Intrinsics.checkNotNullExpressionValue(observableDistinct, "Observable.rangeLong(sta…              .distinct()");
        return observableDistinct;
    }
}
