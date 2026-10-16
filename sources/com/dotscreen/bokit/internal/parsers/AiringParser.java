package com.dotscreen.bokit.internal.parsers;

import com.dotscreen.bokit.model.AmazinGenericArtwork;
import com.dotscreen.bokit.model.ArtworkFormat;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.MediaMetadata;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVProgram;
import com.dotscreen.bokit.services.BOService;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.slf4j.Marker;

/* JADX INFO: compiled from: AiringParser.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JP\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\r0\f2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\f2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\fR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/dotscreen/bokit/internal/parsers/AiringParser;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "parseAirings", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/TVAiring;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "channels", "", "Lcom/dotscreen/bokit/model/Channel;", "references", "data", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final class AiringParser {
    public static final AiringParser INSTANCE = new AiringParser();
    private static final String TAG = AiringParser.class.getSimpleName();

    private AiringParser() {
    }

    public final Observable<TVAiring> parseAirings(BOService boService, Map<String, ? extends Channel> channels, Map<Object, ? extends Object> references, Map<Object, ? extends Object> data) {
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(channels, "channels");
        Intrinsics.checkNotNullParameter(references, "references");
        Intrinsics.checkNotNullParameter(data, "data");
        Observable<TVAiring> observableCreate = Observable.create(new AnonymousClass1(data, references, channels, boService));
        Intrinsics.checkNotNullExpressionValue(observableCreate, "Observable.create<TVAiri…nd: $e\"))\n        }\n    }");
        return observableCreate;
    }

    /* JADX INFO: renamed from: com.dotscreen.bokit.internal.parsers.AiringParser$parseAirings$1, reason: invalid class name */
    /* JADX INFO: compiled from: AiringParser.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "emitter", "Lio/reactivex/ObservableEmitter;", "Lcom/dotscreen/bokit/model/TVAiring;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 4, 1})
    static final class AnonymousClass1<T> implements ObservableOnSubscribe<TVAiring> {
        final /* synthetic */ BOService $boService;
        final /* synthetic */ Map $channels;
        final /* synthetic */ Map $data;
        final /* synthetic */ Map $references;

        AnonymousClass1(Map map, Map map2, Map map3, BOService bOService) {
            this.$data = map;
            this.$references = map2;
            this.$channels = map3;
            this.$boService = bOService;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r12v1 */
        /* JADX WARN: Type inference failed for: r12v2 */
        /* JADX WARN: Type inference failed for: r12v3 */
        /* JADX WARN: Type inference failed for: r12v4, types: [com.dotscreen.bokit.internal.parsers.AiringParser$parseAirings$1$1] */
        /* JADX WARN: Type inference failed for: r12v5 */
        /* JADX WARN: Type inference failed for: r12v6 */
        /* JADX WARN: Type inference failed for: r12v7 */
        /* JADX WARN: Type inference failed for: r1v0 */
        /* JADX WARN: Type inference failed for: r1v1, types: [io.reactivex.ObservableEmitter] */
        /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v25 */
        /* JADX WARN: Type inference failed for: r40v0 */
        /* JADX WARN: Type inference failed for: r40v2, types: [com.dotscreen.bokit.internal.parsers.AiringParser$parseAirings$1$1] */
        /* JADX WARN: Type inference failed for: r40v3 */
        /* JADX WARN: Type inference failed for: r40v4 */
        @Override // io.reactivex.ObservableOnSubscribe
        public final void subscribe(final ObservableEmitter<TVAiring> observableEmitter) {
            ?? r1;
            final Map.Entry entry;
            final String str;
            final ?? r40;
            String str2;
            ObservableEmitter<TVAiring> observableEmitter2;
            Iterator it;
            ObservableEmitter<TVAiring> emitter = observableEmitter;
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            try {
                r1 = "schedule";
                Object obj = this.$data.get("schedule");
                try {
                    if (obj == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.collections.Map<kotlin.Any, kotlin.Any>>");
                    }
                    Function2<String, String, String[]> function2 = new Function2<String, String, String[]>() { // from class: com.dotscreen.bokit.internal.parsers.AiringParser.parseAirings.1.1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final String[] invoke(String typeId, String genreId) {
                            Intrinsics.checkNotNullParameter(typeId, "typeId");
                            Intrinsics.checkNotNullParameter(genreId, "genreId");
                            Object obj2 = AnonymousClass1.this.$references.get("types");
                            if (obj2 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.String>");
                            }
                            Map map = (Map) obj2;
                            Object obj3 = AnonymousClass1.this.$references.get("genres");
                            if (obj3 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.String>");
                            }
                            Map map2 = (Map) obj3;
                            String[] strArr = new String[0];
                            String str3 = (String) map.get(typeId);
                            if (str3 != null) {
                                strArr = (String[]) ArraysKt.plus(strArr, str3);
                            }
                            String str4 = (String) map2.get(genreId);
                            return str4 != null ? (String[]) ArraysKt.plus(strArr, str4) : strArr;
                        }
                    };
                    Iterator it2 = ((Map) obj).entrySet().iterator();
                    ?? r12 = function2;
                    while (it2.hasNext()) {
                        Map.Entry entry2 = (Map.Entry) it2.next();
                        String str3 = (String) entry2.getKey();
                        Channel channel = (Channel) this.$channels.get(str3);
                        if (channel != null) {
                            Map map = (Map) entry2.getValue();
                            Object obj2 = map.get("start");
                            String str4 = "null cannot be cast to non-null type kotlin.Long";
                            if (obj2 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
                            }
                            long jLongValue = ((Long) obj2).longValue();
                            Object obj3 = map.get("airings");
                            if (obj3 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<kotlin.collections.List<kotlin.Any>>");
                            }
                            r12 = r12;
                            for (List list : (List) obj3) {
                                Object obj4 = list.get(0);
                                if (obj4 == null) {
                                    throw new NullPointerException(str4);
                                }
                                long jLongValue2 = ((Long) obj4).longValue();
                                Object obj5 = list.get(1);
                                if (obj5 == null) {
                                    throw new NullPointerException(str4);
                                }
                                long jLongValue3 = ((Long) obj5).longValue();
                                if (jLongValue3 == 0) {
                                    jLongValue += jLongValue2 * ((long) 60);
                                    str2 = str4;
                                    observableEmitter2 = emitter;
                                    r40 = r12;
                                    entry = entry2;
                                    str = str3;
                                } else {
                                    Object obj6 = list.get(2);
                                    if (obj6 == null) {
                                        throw new NullPointerException(str4);
                                    }
                                    String strValueOf = String.valueOf(((Long) obj6).longValue());
                                    Object obj7 = list.get(3);
                                    if (obj7 == null) {
                                        throw new NullPointerException(str4);
                                    }
                                    String strValueOf2 = String.valueOf(((Long) obj7).longValue());
                                    Object obj8 = list.get(4);
                                    if (obj8 == null) {
                                        throw new NullPointerException(str4);
                                    }
                                    AmazinGenericArtwork[] amazinGenericArtworkArr = new AmazinGenericArtwork[1];
                                    String str5 = str4;
                                    try {
                                        amazinGenericArtworkArr[0] = new AmazinGenericArtwork(this.$boService, String.valueOf(((Long) obj8).longValue()), ArtworkFormat.Still);
                                        ArrayList arrayListArrayListOf = CollectionsKt.arrayListOf(amazinGenericArtworkArr);
                                        Object obj9 = list.get(5);
                                        if (obj9 == null) {
                                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                                        }
                                        String str6 = (String) obj9;
                                        Object obj10 = list.get(6);
                                        if (!(obj10 instanceof String)) {
                                            obj10 = null;
                                        }
                                        String str7 = (String) obj10;
                                        if (str7 == null) {
                                            str7 = "";
                                        }
                                        String str8 = str7;
                                        Object obj11 = list.get(7);
                                        if (!(obj11 instanceof List)) {
                                            obj11 = null;
                                        }
                                        List listEmptyList = (List) obj11;
                                        if (listEmptyList == null) {
                                            listEmptyList = CollectionsKt.emptyList();
                                        }
                                        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                                        objectRef.element = (T) ((ParentalRating) null);
                                        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                                        objectRef2.element = (T) ((Double) null);
                                        Iterator it3 = listEmptyList.iterator();
                                        boolean z = false;
                                        boolean z2 = false;
                                        while (it3.hasNext()) {
                                            String str9 = (String) it3.next();
                                            switch (str9.hashCode()) {
                                                case 42:
                                                    it = it3;
                                                    if (str9.equals(Marker.ANY_MARKER)) {
                                                        objectRef2.element = (T) Double.valueOf(1.0d);
                                                    }
                                                    break;
                                                case 1344:
                                                    it = it3;
                                                    if (str9.equals("**")) {
                                                        objectRef2.element = (T) Double.valueOf(2.0d);
                                                    }
                                                    break;
                                                case 41706:
                                                    it = it3;
                                                    if (str9.equals("***")) {
                                                        objectRef2.element = (T) Double.valueOf(3.0d);
                                                    }
                                                    break;
                                                case 1292928:
                                                    it = it3;
                                                    if (str9.equals("****")) {
                                                        objectRef2.element = (T) Double.valueOf(4.0d);
                                                    }
                                                    break;
                                                case 3314342:
                                                    it = it3;
                                                    if (str9.equals("late")) {
                                                        z2 = true;
                                                    }
                                                    break;
                                                case 106934911:
                                                    it = it3;
                                                    if (str9.equals("prime")) {
                                                        z = true;
                                                    }
                                                    break;
                                                default:
                                                    it = it3;
                                                    break;
                                            }
                                            Map.Entry entry3 = entry2;
                                            T t = (T) ParentalRating.INSTANCE.forName(this.$boService, str9);
                                            if (t != null) {
                                                objectRef.element = t;
                                            }
                                            it3 = it;
                                            entry2 = entry3;
                                        }
                                        entry = entry2;
                                        BOService bOService = this.$boService;
                                        StringBuilder sb = new StringBuilder();
                                        sb.append(str3);
                                        str = str3;
                                        sb.append('#');
                                        sb.append(jLongValue);
                                        long j = 1000;
                                        r40 = r12;
                                        long j2 = jLongValue + (jLongValue2 * ((long) 60));
                                        final TVAiring tVAiring = new TVAiring(bOService, sb.toString(), str6, str8, arrayListArrayListOf, null, new MediaMetadata(null, null, r12.invoke(strValueOf, strValueOf2), null, null, null, null, null, null, null, null, null, null, null, (Double) objectRef2.element, (ParentalRating) objectRef.element, 16379, null), new Date(jLongValue * j), new Date(j * j2), channel, null, z, z2, false, 9248, null);
                                        str2 = str5;
                                        Maybe<TVProgram> map2 = this.$boService.getLiveTVService().program(String.valueOf(jLongValue3)).map(new Function<TVProgram, TVProgram>() { // from class: com.dotscreen.bokit.internal.parsers.AiringParser$parseAirings$1$$special$$inlined$let$lambda$1
                                            @Override // io.reactivex.functions.Function
                                            public final TVProgram apply(TVProgram it4) {
                                                Intrinsics.checkNotNullParameter(it4, "it");
                                                return new TVProgram(it4.getId(), it4.getTitle(), it4.getSummary(), it4.getArtworks(), it4.getDeepLink(), new MediaMetadata(it4.getMetadata().getDirectors(), it4.getMetadata().getCast(), it4.getMetadata().getGenres(), it4.getMetadata().getDurationInMinutes(), it4.getMetadata().getCountries(), it4.getMetadata().getAudioLanguages(), it4.getMetadata().getSubtitlesLanguages(), it4.getMetadata().getProductionYear(), null, it4.getMetadata().getExtraArtworks(), it4.getMetadata().getTrailers(), it4.getMetadata().getBonuses(), it4.getMetadata().getRelated(), it4.getMetadata().getRecommendations(), (Double) objectRef2.element, (ParentalRating) objectRef.element, 256, null), null, null, null, it4.getRelatedVodMedia(), this.$boService.getLiveTVService().catchupMediaForProgram(it4, tVAiring), it4.getReruns(), it4.getOffers(), it4.getRatable(), 448, null);
                                            }
                                        });
                                        Intrinsics.checkNotNullExpressionValue(map2, "boService.liveTVService.…                        }");
                                        tVAiring.setTvProgram$bokit_android_release(map2);
                                        observableEmitter2 = observableEmitter;
                                        observableEmitter2.onNext(tVAiring);
                                        jLongValue = j2;
                                    } catch (Exception e) {
                                        e = e;
                                        r1 = observableEmitter;
                                    }
                                }
                                emitter = observableEmitter2;
                                str4 = str2;
                                it2 = it2;
                                entry2 = entry;
                                str3 = str;
                                r12 = r40;
                            }
                        }
                        emitter = emitter;
                        it2 = it2;
                        r12 = r12;
                    }
                    observableEmitter.onComplete();
                    return;
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception e3) {
                e = e3;
                r1 = emitter;
            }
            r1.tryOnError(new Exception("No airing found: " + e));
        }
    }
}
