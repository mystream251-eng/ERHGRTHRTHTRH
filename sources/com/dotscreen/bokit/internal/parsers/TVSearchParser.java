package com.dotscreen.bokit.internal.parsers;

import android.util.Log;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.ItemCollection;
import com.dotscreen.bokit.model.ItemSortingOrder;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.LiveTVSearchResponse;
import com.google.android.gms.actions.SearchIntents;
import com.viaccessorca.analytics.fma.FmaDatabaseHelper;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.Date;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: TVSearchParser.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J@\u0010\u0006\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\b0\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u000f\u001a\u00020\u0010R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/bokit/internal/parsers/TVSearchParser;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "parseSearch", "Lio/reactivex/Single;", "Lcom/dotscreen/bokit/services/LiveTVSearchResponse;", "boService", "Lcom/dotscreen/bokit/services/BOService;", SearchIntents.EXTRA_QUERY, "channelsMap", "", "Lcom/dotscreen/bokit/model/Channel;", "jsonObject", "Lorg/json/JSONObject;", "parseSearchAutocomplete", "Lio/reactivex/Observable;", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final class TVSearchParser {
    public static final TVSearchParser INSTANCE = new TVSearchParser();
    private static final String TAG = TVSearchParser.class.getSimpleName();

    private TVSearchParser() {
    }

    /* JADX INFO: renamed from: com.dotscreen.bokit.internal.parsers.TVSearchParser$parseSearch$1, reason: invalid class name */
    /* JADX INFO: compiled from: TVSearchParser.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "observer", "Lio/reactivex/SingleEmitter;", "Lcom/dotscreen/bokit/services/LiveTVSearchResponse;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 4, 1})
    static final class AnonymousClass1<T> implements SingleOnSubscribe<LiveTVSearchResponse> {
        final /* synthetic */ BOService $boService;
        final /* synthetic */ Map $channelsMap;
        final /* synthetic */ JSONObject $jsonObject;
        final /* synthetic */ String $query;

        AnonymousClass1(JSONObject jSONObject, Map map, BOService bOService, String str) {
            this.$jsonObject = jSONObject;
            this.$channelsMap = map;
            this.$boService = bOService;
            this.$query = str;
        }

        @Override // io.reactivex.SingleOnSubscribe
        public final void subscribe(SingleEmitter<LiveTVSearchResponse> observer) throws JSONException {
            Intrinsics.checkNotNullParameter(observer, "observer");
            JSONArray jSONArray = this.$jsonObject.getJSONArray("channels");
            int length = jSONArray.length();
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = jSONArray.optString(i);
            }
            final Observable<R> map = Observable.fromIterable(ArraysKt.toList(strArr)).doOnNext(new Consumer<String>() { // from class: com.dotscreen.bokit.internal.parsers.TVSearchParser$parseSearch$1$channelsObs$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(String str) {
                    Log.d("test", "-> " + str);
                }
            }).filter(new Predicate<String>() { // from class: com.dotscreen.bokit.internal.parsers.TVSearchParser$parseSearch$1$channelsObs$3
                @Override // io.reactivex.functions.Predicate
                public final boolean test(String it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return this.this$0.$channelsMap.get(it) != null;
                }
            }).map(new Function<String, Channel>() { // from class: com.dotscreen.bokit.internal.parsers.TVSearchParser$parseSearch$1$channelsObs$4
                @Override // io.reactivex.functions.Function
                public final Channel apply(String it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Object obj = this.this$0.$channelsMap.get(it);
                    Intrinsics.checkNotNull(obj);
                    return (Channel) obj;
                }
            });
            ItemCollection itemCollection = new ItemCollection(this.$boService, "search_channels_" + this.$query, "", null, null, null, false, null, null, new Function1<ItemSortingOrder, Observable<Channel>>() { // from class: com.dotscreen.bokit.internal.parsers.TVSearchParser$parseSearch$1$channelCollection$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Observable<Channel> invoke(ItemSortingOrder it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Observable<Channel> channelsObs = map;
                    Intrinsics.checkNotNullExpressionValue(channelsObs, "channelsObs");
                    return channelsObs;
                }
            }, null, null, 3576, null);
            JSONArray jSONArray2 = this.$jsonObject.getJSONArray("airings");
            int length2 = jSONArray2.length();
            JSONObject[] jSONObjectArr = new JSONObject[length2];
            for (int i2 = 0; i2 < length2; i2++) {
                jSONObjectArr[i2] = jSONArray2.optJSONObject(i2);
            }
            final Observable observableConcatMapMaybe = Observable.fromIterable(ArraysKt.toList(jSONObjectArr)).concatMapMaybe(new Function<JSONObject, MaybeSource<? extends TVAiring>>() { // from class: com.dotscreen.bokit.internal.parsers.TVSearchParser$parseSearch$1$airingsObs$2
                @Override // io.reactivex.functions.Function
                public final MaybeSource<? extends TVAiring> apply(JSONObject jsonAiring) throws JSONException {
                    Maybe<TVAiring> maybeEmpty;
                    Intrinsics.checkNotNullParameter(jsonAiring, "jsonAiring");
                    long j = jsonAiring.getLong(FmaDatabaseHelper.CONST_TIMESTAMP);
                    Channel channel = (Channel) this.this$0.$channelsMap.get(jsonAiring.getString("channel_id"));
                    if (channel == null || (maybeEmpty = channel.airing(new Date(j * ((long) 1000)))) == null) {
                        maybeEmpty = Maybe.empty();
                    }
                    return maybeEmpty;
                }
            });
            observer.onSuccess(new LiveTVSearchResponse(itemCollection, new ItemCollection(this.$boService, "search_airings_" + this.$query, "", null, null, null, false, null, null, new Function1<ItemSortingOrder, Observable<TVAiring>>() { // from class: com.dotscreen.bokit.internal.parsers.TVSearchParser$parseSearch$1$airingsCollection$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Observable<TVAiring> invoke(ItemSortingOrder it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Observable<TVAiring> airingsObs = observableConcatMapMaybe;
                    Intrinsics.checkNotNullExpressionValue(airingsObs, "airingsObs");
                    return airingsObs;
                }
            }, null, null, 3576, null)));
        }
    }

    public final Single<LiveTVSearchResponse> parseSearch(BOService boService, String query, Map<String, ? extends Channel> channelsMap, JSONObject jsonObject) {
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(channelsMap, "channelsMap");
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        Single<LiveTVSearchResponse> singleCreate = Single.create(new AnonymousClass1(jsonObject, channelsMap, boService, query));
        Intrinsics.checkNotNullExpressionValue(singleCreate, "Single.create<LiveTVSear…airingsCollection))\n    }");
        return singleCreate;
    }

    public final Observable<String> parseSearchAutocomplete(JSONObject jsonObject) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        JSONArray jSONArray = jsonObject.getJSONArray("terms");
        int length = jSONArray.length();
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = jSONArray.optString(i);
        }
        Observable<String> observableFromIterable = Observable.fromIterable(ArraysKt.toList(strArr));
        Intrinsics.checkNotNullExpressionValue(observableFromIterable, "Observable.fromIterable(….optString(i) }.toList())");
        return observableFromIterable;
    }
}
