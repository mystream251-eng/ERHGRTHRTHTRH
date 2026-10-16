package com.dotscreen.bokit.internal.parsers;

import androidx.tvprovider.media.tv.TvContractCompat;
import com.dotscreen.bokit.ParseException;
import com.dotscreen.bokit.model.AmazinChannelArtwork;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.ArtworkFormat;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.ChannelCapabilities;
import com.dotscreen.bokit.model.ItemSortingOrder;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.model.LiveStream;
import com.dotscreen.bokit.model.MediaMetadata;
import com.dotscreen.bokit.model.Offer;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.LiveTVService;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: LineupParser.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J(\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u000bR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/bokit/internal/parsers/LineupParser;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "parseChannel", "Lcom/dotscreen/bokit/model/Channel;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "data", "", "lineup", "Lcom/dotscreen/bokit/internal/parsers/ChannelLineup;", "zappingNumber", "", "parseLineups", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/Lineup;", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final class LineupParser {
    public static final LineupParser INSTANCE = new LineupParser();
    private static final String TAG = LineupParser.class.getSimpleName();

    private LineupParser() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:103:0x00c7 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:104:0x00bf A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:107:0x00a5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:115:0x0058 A[SYNTHETIC] */
    public final Channel parseChannel(BOService boService, Map<Object, ? extends Object> data, ChannelLineup lineup, int zappingNumber) {
        boolean z;
        boolean z2;
        boolean z3;
        String id;
        boolean z4;
        String[] strArr;
        String str;
        List listSplit$default;
        Object obj = data.get("id");
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
        }
        String strValueOf = String.valueOf(((Long) obj).longValue());
        Object obj2 = data.get("logo_id");
        if (!(obj2 instanceof Long)) {
            obj2 = null;
        }
        Long l = (Long) obj2;
        String strValueOf2 = l != null ? String.valueOf(l.longValue()) : null;
        ArrayList arrayList = new ArrayList();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Object obj3 = data.get("tags");
        if (!(obj3 instanceof List)) {
            obj3 = null;
        }
        List<String> list = (List) obj3;
        if (list != null) {
            boolean z5 = false;
            boolean z6 = false;
            boolean z7 = false;
            for (String str2 : list) {
                int iHashCode = str2.hashCode();
                if (iHashCode != -1573282159) {
                    if (iHashCode != 3388414) {
                        if (iHashCode == 48678559 && str2.equals("catch_up")) {
                            z6 = true;
                        } else {
                            str = str2;
                            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "category", false, 2, (Object) null)) {
                                listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null);
                                if (listSplit$default.size() > 1) {
                                    arrayList.add(listSplit$default.get(1));
                                }
                            } else {
                                linkedHashSet.add(str2);
                            }
                        }
                    } else if (str2.equals("npvr")) {
                        z7 = true;
                    } else {
                        str = str2;
                        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "category", false, 2, (Object) null)) {
                            listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null);
                            if (listSplit$default.size() > 1) {
                                arrayList.add(listSplit$default.get(1));
                            }
                        } else {
                            linkedHashSet.add(str2);
                        }
                    }
                } else if (str2.equals("start_over")) {
                    z5 = true;
                } else {
                    str = str2;
                    if (StringsKt.contains$default((CharSequence) str, (CharSequence) "category", false, 2, (Object) null)) {
                        listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null);
                        if (listSplit$default.size() > 1) {
                            arrayList.add(listSplit$default.get(1));
                        }
                    } else {
                        linkedHashSet.add(str2);
                    }
                }
            }
            z2 = z5;
            z = z6;
            z3 = z7;
        } else {
            z = false;
            z2 = false;
            z3 = false;
        }
        LiveTVService liveTVService = boService.getLiveTVService();
        if (lineup == null || (id = lineup.getId()) == null) {
            id = "";
        }
        if (strValueOf2 == null) {
            strValueOf2 = strValueOf;
        }
        List listListOf = CollectionsKt.listOf(new AmazinChannelArtwork(boService, strValueOf2, ArtworkFormat.Square));
        Object obj4 = data.get("name");
        if (!(obj4 instanceof String)) {
            obj4 = null;
        }
        String str3 = (String) obj4;
        Channel channel = new Channel(liveTVService, strValueOf, id, str3 != null ? str3 : "", null, listListOf, null, zappingNumber, null, new ChannelCapabilities(z, z2, z3, false, 8, null), false, arrayList, 1360, null);
        String id2 = channel.getId();
        String title = channel.getTitle();
        MediaMetadata mediaMetadata = new MediaMetadata(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 65535, null);
        Observable observableJust = Observable.just(new Offer(TvContractCompat.PreviewProgramColumns.COLUMN_LIVE, TvContractCompat.PreviewProgramColumns.COLUMN_LIVE, null, null, null, null, 0.0d, null, 252, null));
        Intrinsics.checkNotNullExpressionValue(observableJust, "Observable.just(Offer(\"live\", \"live\"))");
        channel.setLiveStream$bokit_android_release(new LiveStream(boService, id2, title, null, null, null, channel, mediaMetadata, false, observableJust, 312, null));
        Object obj5 = data.get("streams");
        if (!(obj5 instanceof List)) {
            if (obj5 instanceof String) {
                z4 = true;
                strArr = new String[]{(String) obj5};
            } else {
                z4 = true;
                strArr = null;
            }
        } else {
            Iterable iterable = (Iterable) obj5;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (Object obj6 : iterable) {
                if (obj6 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                arrayList2.add((String) obj6);
            }
            Object[] array = arrayList2.toArray(new String[0]);
            if (array == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            strArr = (String[]) array;
            z4 = true;
        }
        channel.setStaticStreams$bokit_android_release(strArr);
        Object obj7 = data.get("links");
        if (!(obj7 instanceof Map)) {
            obj7 = null;
        }
        Map map = (Map) obj7;
        if (map != null) {
            String str4 = (String) map.get("SUBSCRIPTION_BANNER");
            if (str4 != null) {
                channel.addExtra$bokit_android_release("SUBSCRIPTION_BANNER", str4);
            }
            String str5 = (String) map.get("SUBSCRIPTION");
            if (str5 != null) {
                channel.addExtra$bokit_android_release("SUBSCRIPTION", str5);
            }
        }
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            channel.addExtra$bokit_android_release((String) it.next(), Boolean.valueOf(z4));
        }
        return channel;
    }

    public final Observable<Lineup> parseLineups(final BOService boService, Map<Object, ? extends Object> data) {
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(data, "data");
        try {
            Object obj = data.get("channels");
            if (!(obj instanceof List)) {
                obj = null;
            }
            final List list = (List) obj;
            if (list != null) {
                Observable<Lineup> observableCreate = Observable.create(new ObservableOnSubscribe<Lineup>() { // from class: com.dotscreen.bokit.internal.parsers.LineupParser$parseLineups$$inlined$let$lambda$1
                    @Override // io.reactivex.ObservableOnSubscribe
                    public final void subscribe(ObservableEmitter<Lineup> emitter) {
                        Intrinsics.checkNotNullParameter(emitter, "emitter");
                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                        for (Map map : list) {
                            Object obj2 = map.get("lineups");
                            if (obj2 != null) {
                                Map map2 = (Map) obj2;
                                Channel channel = LineupParser.INSTANCE.parseChannel(boService, map, null, 0);
                                Iterator it = map2.keySet().iterator();
                                while (it.hasNext()) {
                                    String str = (String) it.next();
                                    ChannelLineup channelLineup = (ChannelLineup) linkedHashMap.get(str);
                                    if (channelLineup == null) {
                                        channelLineup = new ChannelLineup(str);
                                        linkedHashMap.put(str, channelLineup);
                                        linkedHashMap2.put(str, new ArrayList());
                                    }
                                    Object obj3 = linkedHashMap2.get(str);
                                    Intrinsics.checkNotNull(obj3);
                                    List list2 = (List) obj3;
                                    LiveTVService liveTVService = boService.getLiveTVService();
                                    String id = channel.getId();
                                    String id2 = channelLineup.getId();
                                    List<Artwork> artworks = channel.getArtworks();
                                    String title = channel.getTitle();
                                    Object obj4 = map2.get(str);
                                    Intrinsics.checkNotNull(obj4);
                                    Map map3 = map2;
                                    Iterator it2 = it;
                                    Channel channel2 = new Channel(liveTVService, id, id2, title, null, artworks, null, (int) ((Number) obj4).longValue(), null, channel.getCapabilities(), false, channel.getGenres(), 1360, null);
                                    channel2.setLiveStream$bokit_android_release(channel.getLiveStream());
                                    channel2.setStaticStreams$bokit_android_release(channel.getStaticStreams());
                                    for (String str2 : channel.getExtraKeys()) {
                                        Object objExtra = channel.extra(str2);
                                        Intrinsics.checkNotNull(objExtra);
                                        channel2.addExtra$bokit_android_release(str2, objExtra);
                                    }
                                    list2.add(channel2);
                                    map2 = map3;
                                    it = it2;
                                }
                            } else {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Long>");
                            }
                        }
                        for (ChannelLineup channelLineup2 : linkedHashMap.values()) {
                            if (emitter.isDisposed()) {
                                break;
                            }
                            List list3 = (List) linkedHashMap2.get(channelLineup2.getId());
                            if (list3 != null) {
                                final List listSortedWith = CollectionsKt.sortedWith(list3, new Comparator<T>() { // from class: com.dotscreen.bokit.internal.parsers.LineupParser$parseLineups$$inlined$let$lambda$1.1
                                    /* JADX WARN: Multi-variable type inference failed */
                                    @Override // java.util.Comparator
                                    public final int compare(T t, T t2) {
                                        return ComparisonsKt.compareValues(Integer.valueOf(((Channel) t).getZappingNumber()), Integer.valueOf(((Channel) t2).getZappingNumber()));
                                    }
                                });
                                emitter.onNext(new Lineup(boService, channelLineup2.getId(), channelLineup2.getId(), null, null, null, false, null, null, new Function1<ItemSortingOrder, Observable<Channel>>() { // from class: com.dotscreen.bokit.internal.parsers.LineupParser$parseLineups$$inlined$let$lambda$1.2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public final Observable<Channel> invoke(ItemSortingOrder order) {
                                        Intrinsics.checkNotNullParameter(order, "order");
                                        Sorters sorters = Sorters.INSTANCE;
                                        Observable<Channel> observableFromIterable = Observable.fromIterable(listSortedWith);
                                        Intrinsics.checkNotNullExpressionValue(observableFromIterable, "Observable.fromIterable(sortedChannels)");
                                        return sorters.channelSorter(observableFromIterable, order);
                                    }
                                }, null, 1528, null));
                            }
                        }
                        emitter.onComplete();
                    }
                });
                Intrinsics.checkNotNullExpressionValue(observableCreate, "Observable.create { emit…plete()\n                }");
                return observableCreate;
            }
        } catch (Exception unused) {
        }
        Observable<Lineup> observableError = Observable.error(new ParseException("Error while parsing lineups", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(observableError, "Observable.error(ParseEx… while parsing lineups\"))");
        return observableError;
    }
}
