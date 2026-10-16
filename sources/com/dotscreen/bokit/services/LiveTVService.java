package com.dotscreen.bokit.services;

import com.dotscreen.bokit.internal.parsers.LineupParser;
import com.dotscreen.bokit.internal.parsers.ProgramParser;
import com.dotscreen.bokit.internal.parsers.TVSearchParser;
import com.dotscreen.bokit.internal.tools.TVAiringSegment;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVProgram;
import com.google.android.gms.actions.SearchIntents;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* JADX INFO: compiled from: LiveTVService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00062\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u001e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0014H\u0016J\u0006\u0010\u001f\u001a\u00020 J\u001e\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\r2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u001e\u001a\u00020\u0014H\u0016J\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00070\u001a2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0012\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020\u0018H\u0016J\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001a2\u0006\u0010)\u001a\u00020\u0018H\u0016J5\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\r2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010,\u001a\u00020\u00182\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010.H\u0016¢\u0006\u0002\u0010/J-\u00100\u001a\b\u0012\u0004\u0012\u00020\u00180\u00062\u0006\u0010,\u001a\u00020\u00182\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010.H\u0016¢\u0006\u0002\u00101J\u001d\u00102\u001a\u00020\"2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b3J\u0016\u00104\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001e\u001a\u00020\u0014H\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR-\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u000e0\r8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u00065"}, d2 = {"Lcom/dotscreen/bokit/services/LiveTVService;", "", "boService", "Lcom/dotscreen/bokit/services/BOService;", "(Lcom/dotscreen/bokit/services/BOService;)V", "availableLineups", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/Lineup;", "getAvailableLineups", "()Lio/reactivex/Observable;", "getBoService", "()Lcom/dotscreen/bokit/services/BOService;", "genresReferences", "Lio/reactivex/Single;", "", "getGenresReferences", "()Lio/reactivex/Single;", "genresReferences$delegate", "Lkotlin/Lazy;", "airings", "Lcom/dotscreen/bokit/model/TVAiring;", "segmentId", "", "lineupId", "", "catchupMediaForProgram", "Lio/reactivex/Maybe;", "Lcom/dotscreen/bokit/model/Media;", "program", "Lcom/dotscreen/bokit/model/TVProgram;", "airing", "invalidateAiringsCache", "", "isAiringRecordable", "", "mode", "Lcom/dotscreen/bokit/services/PVRStorageMode;", "lineupById", "parentalRatingForName", "Lcom/dotscreen/bokit/model/ParentalRating;", "name", "programId", "search", "Lcom/dotscreen/bokit/services/LiveTVSearchResponse;", SearchIntents.EXTRA_QUERY, "packageIds", "", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lio/reactivex/Single;", "searchAutocomplete", "(Ljava/lang/String;[Ljava/lang/String;)Lio/reactivex/Observable;", "segmentHasCache", "segmentHasCache$bokit_android_release", "startOverMediaForAiring", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class LiveTVService {
    private final BOService boService;

    /* JADX INFO: renamed from: genresReferences$delegate, reason: from kotlin metadata */
    private final Lazy genresReferences;

    public Single<Map<Object, Object>> getGenresReferences() {
        return (Single) this.genresReferences.getValue();
    }

    public LiveTVService(BOService boService) {
        Intrinsics.checkNotNullParameter(boService, "boService");
        this.boService = boService;
        this.genresReferences = LazyKt.lazy(new Function0<Single<Map<Object, ? extends Object>>>() { // from class: com.dotscreen.bokit.services.LiveTVService$genresReferences$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Single<Map<Object, ? extends Object>> invoke() {
                return this.this$0.getBoService().fetchTVReferences$bokit_android_release().cache();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BOService getBoService() {
        return this.boService;
    }

    public Observable<Lineup> getAvailableLineups() {
        Observable observableFlatMapObservable = this.boService.fetchTVAllLineups$bokit_android_release().flatMapObservable(new Function<Map<Object, ? extends Object>, ObservableSource<? extends Lineup>>() { // from class: com.dotscreen.bokit.services.LiveTVService$availableLineups$1
            @Override // io.reactivex.functions.Function
            public final ObservableSource<? extends Lineup> apply(Map<Object, ? extends Object> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return LineupParser.INSTANCE.parseLineups(this.this$0.getBoService(), it);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMapObservable, "boService.fetchTVAllLine…ervice, it)\n            }");
        return observableFlatMapObservable;
    }

    public static /* synthetic */ Single search$default(LiveTVService liveTVService, String str, String str2, String[] strArr, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: search");
        }
        if ((i & 4) != 0) {
            strArr = (String[]) null;
        }
        return liveTVService.search(str, str2, strArr);
    }

    public Single<LiveTVSearchResponse> search(String lineupId, final String query, final String[] packageIds) {
        Intrinsics.checkNotNullParameter(lineupId, "lineupId");
        Intrinsics.checkNotNullParameter(query, "query");
        Single<LiveTVSearchResponse> singleFlatMap = lineupById(lineupId).flatMapObservable(new Function<Lineup, ObservableSource<? extends Channel>>() { // from class: com.dotscreen.bokit.services.LiveTVService.search.1
            @Override // io.reactivex.functions.Function
            public final ObservableSource<? extends Channel> apply(Lineup it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getItems();
            }
        }).toMap(new Function<Channel, String>() { // from class: com.dotscreen.bokit.services.LiveTVService.search.2
            @Override // io.reactivex.functions.Function
            public final String apply(Channel it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getId();
            }
        }).flatMap(new Function<Map<String, Channel>, SingleSource<? extends LiveTVSearchResponse>>() { // from class: com.dotscreen.bokit.services.LiveTVService.search.3
            @Override // io.reactivex.functions.Function
            public final SingleSource<? extends LiveTVSearchResponse> apply(final Map<String, Channel> channelsMap) {
                Intrinsics.checkNotNullParameter(channelsMap, "channelsMap");
                return LiveTVService.this.getBoService().fetchTVSearch$bokit_android_release(query, packageIds).flatMap(new Function<JSONObject, SingleSource<? extends LiveTVSearchResponse>>() { // from class: com.dotscreen.bokit.services.LiveTVService.search.3.1
                    @Override // io.reactivex.functions.Function
                    public final SingleSource<? extends LiveTVSearchResponse> apply(JSONObject it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        TVSearchParser tVSearchParser = TVSearchParser.INSTANCE;
                        BOService boService = LiveTVService.this.getBoService();
                        String str = query;
                        Map<String, ? extends Channel> channelsMap2 = channelsMap;
                        Intrinsics.checkNotNullExpressionValue(channelsMap2, "channelsMap");
                        return tVSearchParser.parseSearch(boService, str, channelsMap2, it);
                    }
                });
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleFlatMap, "lineupById(lineupId)\n   …) }\n                    }");
        return singleFlatMap;
    }

    public static /* synthetic */ Observable searchAutocomplete$default(LiveTVService liveTVService, String str, String[] strArr, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: searchAutocomplete");
        }
        if ((i & 2) != 0) {
            strArr = (String[]) null;
        }
        return liveTVService.searchAutocomplete(str, strArr);
    }

    public Observable<String> searchAutocomplete(String query, String[] packageIds) {
        Intrinsics.checkNotNullParameter(query, "query");
        Observable observableFlatMapObservable = this.boService.fetchTVSearchAutocomplete$bokit_android_release(query, packageIds).flatMapObservable(new Function<JSONObject, ObservableSource<? extends String>>() { // from class: com.dotscreen.bokit.services.LiveTVService.searchAutocomplete.1
            @Override // io.reactivex.functions.Function
            public final ObservableSource<? extends String> apply(JSONObject it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return TVSearchParser.INSTANCE.parseSearchAutocomplete(it);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMapObservable, "boService.fetchTVSearchA…eSearchAutocomplete(it) }");
        return observableFlatMapObservable;
    }

    public Maybe<Lineup> lineupById(final String lineupId) {
        Intrinsics.checkNotNullParameter(lineupId, "lineupId");
        Maybe<Lineup> maybeFirstElement = getAvailableLineups().filter(new Predicate<Lineup>() { // from class: com.dotscreen.bokit.services.LiveTVService.lineupById.1
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Lineup it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Intrinsics.areEqual(it.getId(), lineupId);
            }
        }).firstElement();
        Intrinsics.checkNotNullExpressionValue(maybeFirstElement, "availableLineups.filter …lineupId }.firstElement()");
        return maybeFirstElement;
    }

    public Observable<TVAiring> airings(long segmentId, String lineupId) {
        Intrinsics.checkNotNullParameter(lineupId, "lineupId");
        Observable<TVAiring> observableOnErrorResumeNext = TVAiringSegment.INSTANCE.getInstance(this.boService, lineupId, segmentId).getAirings().onErrorResumeNext(new Function<Throwable, ObservableSource<? extends TVAiring>>() { // from class: com.dotscreen.bokit.services.LiveTVService.airings.1
            @Override // io.reactivex.functions.Function
            public final ObservableSource<? extends TVAiring> apply(Throwable th) {
                Intrinsics.checkNotNullParameter(th, "<anonymous parameter 0>");
                return Observable.empty();
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableOnErrorResumeNext, "TVAiringSegment.getInsta…e -> Observable.empty() }");
        return observableOnErrorResumeNext;
    }

    public Maybe<TVProgram> program(final String programId) {
        Intrinsics.checkNotNullParameter(programId, "programId");
        Maybe maybeFlatMapMaybe = this.boService.fetchTVProgram$bokit_android_release(programId).flatMapMaybe(new Function<Map<Object, ? extends Object>, MaybeSource<? extends TVProgram>>() { // from class: com.dotscreen.bokit.services.LiveTVService.program.1
            @Override // io.reactivex.functions.Function
            public final MaybeSource<? extends TVProgram> apply(Map<Object, ? extends Object> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return ProgramParser.INSTANCE.parseProgram(LiveTVService.this.getBoService(), programId, it);
            }
        });
        Intrinsics.checkNotNullExpressionValue(maybeFlatMapMaybe, "boService.fetchTVProgram…Service, programId, it) }");
        return maybeFlatMapMaybe;
    }

    public Maybe<Media> catchupMediaForProgram(TVProgram program, TVAiring airing) {
        Intrinsics.checkNotNullParameter(program, "program");
        Intrinsics.checkNotNullParameter(airing, "airing");
        Media catchupMedia$bokit_android_release = airing.getCatchupMedia();
        if (catchupMedia$bokit_android_release != null) {
            Maybe<Media> maybeJust = Maybe.just(catchupMedia$bokit_android_release);
            Intrinsics.checkNotNullExpressionValue(maybeJust, "Maybe.just(it)");
            return maybeJust;
        }
        Maybe<Media> maybeEmpty = Maybe.empty();
        Intrinsics.checkNotNullExpressionValue(maybeEmpty, "Maybe.empty()");
        return maybeEmpty;
    }

    public Maybe<Media> startOverMediaForAiring(TVAiring airing) {
        Intrinsics.checkNotNullParameter(airing, "airing");
        Maybe<Media> maybeEmpty = Maybe.empty();
        Intrinsics.checkNotNullExpressionValue(maybeEmpty, "Maybe.empty()");
        return maybeEmpty;
    }

    public Single<Boolean> isAiringRecordable(PVRStorageMode mode, TVAiring airing) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(airing, "airing");
        Single<Boolean> singleJust = Single.just(false);
        Intrinsics.checkNotNullExpressionValue(singleJust, "Single.just(false)");
        return singleJust;
    }

    public ParentalRating parentalRatingForName(String name) {
        int i;
        Intrinsics.checkNotNullParameter(name, "name");
        switch (name) {
            case "CSA:C1":
                i = 0;
                break;
            case "CSA:C2":
                i = 10;
                break;
            case "CSA:C3":
                i = 12;
                break;
            case "CSA:C4":
                i = 16;
                break;
            case "CSA:C5":
                i = 18;
                break;
            default:
                i = -1;
                break;
        }
        if (i == -1 && StringsKt.startsWith$default(name, "RATING", false, 2, (Object) null)) {
            List listSplit$default = StringsKt.split$default((CharSequence) name, new String[]{":"}, false, 0, 6, (Object) null);
            if (listSplit$default.size() > 1) {
                i = Integer.parseInt((String) listSplit$default.get(1));
            }
        }
        int i2 = i;
        if (i2 < 0) {
            return null;
        }
        BOService bOService = this.boService;
        UserService userService = bOService.getUserService();
        return new ParentalRating(bOService, name, name, i2, userService != null ? userService.isParentalRatingLevelAdult(i2) : false);
    }

    public final void invalidateAiringsCache() {
        TVAiringSegment.INSTANCE.invalidateCache();
    }

    public final boolean segmentHasCache$bokit_android_release(long segmentId, String lineupId) {
        Intrinsics.checkNotNullParameter(lineupId, "lineupId");
        return TVAiringSegment.INSTANCE.getInstance(this.boService, lineupId, segmentId).getIsCached();
    }
}
