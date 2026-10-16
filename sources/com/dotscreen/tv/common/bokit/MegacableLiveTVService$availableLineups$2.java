package com.dotscreen.tv.common.bokit;

import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.util.Log;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.ItemSortingOrder;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.farkit.network.RequestManager;
import com.dotscreen.farkit.rx.CachesKt;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.common.utils.TIFCompanionLibraryExtensionKt;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: MegacableLiveTVService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/Lineup;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 16})
final class MegacableLiveTVService$availableLineups$2 extends Lambda implements Function0<Observable<Lineup>> {
    final /* synthetic */ BOService $boService;
    final /* synthetic */ MegacableLiveTVService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MegacableLiveTVService$availableLineups$2(MegacableLiveTVService megacableLiveTVService, BOService bOService) {
        super(0);
        this.this$0 = megacableLiveTVService;
        this.$boService = bOService;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Observable<Lineup> invoke() {
        Log.d(this.this$0.TAG, "Cache: will register");
        Log.d(this.this$0.TAG, "availableLineups => " + this.this$0);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CommonUtils.ACTION_SCAN_STATUS_CHANGED);
        this.this$0.context.registerReceiver(this.this$0.scanReceiver, intentFilter);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(new LinkedHashMap());
        this.this$0.setDisposableTimer(Observable.create(new AnonymousClass2()).subscribe(new Consumer<Integer>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$availableLineups$2.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Integer num) {
                Log.d(MegacableLiveTVService$availableLineups$2.this.this$0.TAG, "Cache: will reset timeout (Lineup)");
                MegacableLiveTVService.cancelLineupCache$default(MegacableLiveTVService$availableLineups$2.this.this$0, false, 1, null);
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$availableLineups$2.4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.d(MegacableLiveTVService$availableLineups$2.this.this$0.TAG, "Cache: error on single timer " + th + " (Lineup)");
            }
        }, new Action() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$availableLineups$2.5
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }));
        Observable map = super/*com.dotscreen.bokit.services.LiveTVService*/.getAvailableLineups().map(new AnonymousClass6(anonymousClass1));
        Intrinsics.checkExpressionValueIsNotNull(map, "super.availableLineups.m…      )\n                }");
        return CachesKt.cacheUntil(map, this.this$0.getLineupCacheCanceler());
    }

    /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.MegacableLiveTVService$availableLineups$2$1, reason: invalid class name */
    /* JADX INFO: compiled from: MegacableLiveTVService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"extrasMap", "Lio/reactivex/Single;", "", "", "Lcom/dotscreen/tv/common/bokit/ChannelExtras;", "lineupId", "invoke"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass1 extends Lambda implements Function1<String, Single<Map<String, ? extends ChannelExtras>>> {
        final /* synthetic */ Map $extrasMapMap;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Map map) {
            super(1);
            this.$extrasMapMap = map;
        }

        @Override // kotlin.jvm.functions.Function1
        public final Single<Map<String, ChannelExtras>> invoke(final String lineupId) {
            Single singleJust;
            Intrinsics.checkParameterIsNotNull(lineupId, "lineupId");
            if (!this.$extrasMapMap.containsKey(lineupId)) {
                Single<String> single = MegacableLiveTVService$availableLineups$2.this.this$0.getModelExternalId().defaultIfEmpty("").toSingle();
                if (CommonUtils.isDVBCSTB(MegacableLiveTVService$availableLineups$2.this.this$0.context)) {
                    final int currentPlatform = CommonUtils.getCurrentPlatform();
                    final String str = "type = ? AND (input_id IN " + CommonUtils.getAvailableTVInputIdsQueryParam(MegacableLiveTVService$availableLineups$2.this.this$0.context) + ')';
                    singleJust = Observable.create(new ObservableOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$availableLineups$2$1$dvbTripletListSingle$1
                        @Override // io.reactivex.ObservableOnSubscribe
                        public final void subscribe(ObservableEmitter<String> observer) {
                            Intrinsics.checkParameterIsNotNull(observer, "observer");
                            try {
                                Cursor cursorQuery = MegacableLiveTVService$availableLineups$2.this.this$0.context.getContentResolver().query(TvContractCompat.Channels.CONTENT_URI, new String[]{"internal_provider_data"}, str, new String[]{TvContractCompat.Channels.TYPE_DVB_C}, null);
                                Throwable th = (Throwable) null;
                                try {
                                    Cursor cursor = cursorQuery;
                                    if (cursor != null) {
                                        while (cursor.moveToNext()) {
                                            byte[] data = cursor.getBlob(0);
                                            try {
                                                String str2 = (String) null;
                                                int i = currentPlatform;
                                                if (i == 1) {
                                                    Intrinsics.checkExpressionValueIsNotNull(data, "data");
                                                    str2 = new String(data, Charsets.UTF_8);
                                                } else if (i == 2) {
                                                    Intrinsics.checkExpressionValueIsNotNull(data, "data");
                                                    str2 = new JSONObject(new String(data, Charsets.UTF_8)).getString("uri");
                                                }
                                                if (str2 != null && StringsKt.startsWith$default(str2, "dvb://", false, 2, (Object) null)) {
                                                    observer.onNext(str2);
                                                }
                                            } catch (Exception e) {
                                                Log.w(MegacableLiveTVService$availableLineups$2.this.this$0.TAG, "Exception while searching for channel by comparing DVB triplets: " + e);
                                            }
                                        }
                                    }
                                    Unit unit = Unit.INSTANCE;
                                    CloseableKt.closeFinally(cursorQuery, th);
                                    observer.onComplete();
                                } catch (Throwable th2) {
                                    try {
                                        throw th2;
                                    } catch (Throwable th3) {
                                        CloseableKt.closeFinally(cursorQuery, th2);
                                        throw th3;
                                    }
                                }
                            } catch (Exception unused) {
                            }
                        }
                    }).toList();
                } else {
                    singleJust = Single.just(CollectionsKt.emptyList());
                }
                Map map = this.$extrasMapMap;
                Single singleSubscribeOn = Single.zip(single, singleJust, new BiFunction<String, List<String>, Pair<? extends String, ? extends List<? extends String>>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.availableLineups.2.1.1
                    @Override // io.reactivex.functions.BiFunction
                    public final Pair<String, List<String>> apply(String mId, List<String> list) {
                        Intrinsics.checkParameterIsNotNull(mId, "mId");
                        Intrinsics.checkParameterIsNotNull(list, "list");
                        return new Pair<>(mId, list);
                    }
                }).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.availableLineups.2.1.2
                    @Override // io.reactivex.functions.Function
                    public final Single<Map<String, ChannelExtras>> apply(Pair<String, ? extends List<String>> pair) {
                        Intrinsics.checkParameterIsNotNull(pair, "pair");
                        Log.d(MegacableLiveTVService$availableLineups$2.this.this$0.TAG, "modelExternalId = " + pair.getFirst());
                        final String first = Intrinsics.areEqual(pair.getFirst(), "Android") ? "PC" : pair.getFirst();
                        final List<String> second = pair.getSecond();
                        Log.d(MegacableLiveTVService$availableLineups$2.this.this$0.TAG, "dvbListFromTIF size = " + second.size() + " (from TIF)");
                        return RequestManager.getJSONObject$default(MegacableLiveTVService$availableLineups$2.this.this$0.requestManager, 0, MegacableLiveTVService$availableLineups$2.this.$boService.resolveUrl("api/rightv/v1/lineups/" + lineupId + "/channels.json"), null, 4, null).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.availableLineups.2.1.2.1
                            /* JADX WARN: Code duplicated, block: B:19:0x0070 A[PHI: r18
  0x0070: PHI (r18v1 org.json.JSONArray) = (r18v3 org.json.JSONArray), (r18v4 org.json.JSONArray) binds: [B:24:0x0089, B:17:0x006d] A[DONT_GENERATE, DONT_INLINE]] */
                            /* JADX WARN: Code duplicated, block: B:54:0x0117 A[Catch: Exception -> 0x01c9, TryCatch #2 {Exception -> 0x01c9, blocks: (B:30:0x00ac, B:32:0x00b3, B:35:0x00c2, B:37:0x00d1, B:39:0x00d7, B:41:0x00e3, B:43:0x00e9, B:45:0x00f1, B:48:0x00fc, B:49:0x0100, B:51:0x0106, B:55:0x012b, B:54:0x0117, B:56:0x013d, B:62:0x0174, B:64:0x017a, B:84:0x01b2, B:89:0x01be, B:76:0x01a0, B:78:0x01a6, B:67:0x0183, B:69:0x018b, B:71:0x0193, B:73:0x0199), top: B:107:0x00ac }] */
                            /* JADX WARN: Code duplicated, block: B:76:0x01a0 A[Catch: Exception -> 0x01c9, TryCatch #2 {Exception -> 0x01c9, blocks: (B:30:0x00ac, B:32:0x00b3, B:35:0x00c2, B:37:0x00d1, B:39:0x00d7, B:41:0x00e3, B:43:0x00e9, B:45:0x00f1, B:48:0x00fc, B:49:0x0100, B:51:0x0106, B:55:0x012b, B:54:0x0117, B:56:0x013d, B:62:0x0174, B:64:0x017a, B:84:0x01b2, B:89:0x01be, B:76:0x01a0, B:78:0x01a6, B:67:0x0183, B:69:0x018b, B:71:0x0193, B:73:0x0199), top: B:107:0x00ac }] */
                            /* JADX WARN: Code duplicated, block: B:81:0x01ad  */
                            /* JADX WARN: Code duplicated, block: B:82:0x01af  */
                            @Override // io.reactivex.functions.Function
                            public final Map<String, ChannelExtras> apply(JSONObject json) throws JSONException {
                                JSONObject jSONObject;
                                Iterator<String> it;
                                LinkedHashMap linkedHashMap;
                                boolean z;
                                JSONArray jSONArray;
                                LinkedHashMap linkedHashMap2;
                                String str2;
                                JSONObject jSONObject2;
                                int i;
                                boolean z2;
                                String str3;
                                Intrinsics.checkParameterIsNotNull(json, "json");
                                LinkedHashMap linkedHashMap3 = new LinkedHashMap();
                                JSONObject jSONObject3 = json.getJSONObject("channels");
                                Iterator<String> itKeys = jSONObject3.keys();
                                Intrinsics.checkExpressionValueIsNotNull(itKeys, "jsonChannels.keys()");
                                while (itKeys.hasNext()) {
                                    String next = itKeys.next();
                                    JSONObject jSONObject4 = jSONObject3.getJSONObject(next);
                                    try {
                                        LinkedHashMap linkedHashMap4 = new LinkedHashMap();
                                        JSONArray jSONArray2 = jSONObject4.getJSONArray(TvContractCompat.PreviewProgramColumns.COLUMN_LIVE);
                                        int length = jSONArray2.length();
                                        int i2 = 0;
                                        while (i2 < length) {
                                            JSONObject jSONObject5 = jSONArray2.getJSONObject(i2);
                                            jSONObject = jSONObject3;
                                            it = itKeys;
                                            try {
                                                String strOptString = jSONObject5.optString("model_external_id", null);
                                                String variant = jSONObject5.getString("variant");
                                                if (strOptString != null) {
                                                    jSONArray = jSONArray2;
                                                    if (!Intrinsics.areEqual(strOptString, first)) {
                                                        linkedHashMap2 = linkedHashMap3;
                                                        str2 = next;
                                                        jSONObject2 = jSONObject4;
                                                        i = length;
                                                    }
                                                    i2++;
                                                    jSONObject3 = jSONObject;
                                                    itKeys = it;
                                                    jSONArray2 = jSONArray;
                                                    length = i;
                                                    linkedHashMap3 = linkedHashMap2;
                                                    jSONObject4 = jSONObject2;
                                                    next = str2;
                                                } else {
                                                    jSONArray = jSONArray2;
                                                }
                                                if (Intrinsics.areEqual(variant, "multicast-abr") && Intrinsics.areEqual(first, "DVB-C STB")) {
                                                    linkedHashMap2 = linkedHashMap3;
                                                    str2 = next;
                                                    jSONObject2 = jSONObject4;
                                                    i = length;
                                                } else {
                                                    String strOptString2 = jSONObject5.optString("banner");
                                                    String strOptString3 = jSONObject5.optString("background");
                                                    if (strOptString2 != null) {
                                                        i = length;
                                                        linkedHashMap2 = linkedHashMap3;
                                                        str2 = next;
                                                        jSONObject2 = jSONObject4;
                                                        try {
                                                            if (StringsKt.contains$default((CharSequence) strOptString2, (CharSequence) "://10.9.9.200", false, 2, (Object) null)) {
                                                                strOptString2 = CommonUtils.getResizedImageUrl(strOptString2, 0, 0);
                                                            }
                                                        } catch (Exception unused) {
                                                            linkedHashMap = linkedHashMap2;
                                                        }
                                                    } else {
                                                        linkedHashMap2 = linkedHashMap3;
                                                        str2 = next;
                                                        jSONObject2 = jSONObject4;
                                                        i = length;
                                                    }
                                                    if (strOptString3 != null) {
                                                        z2 = false;
                                                        if (StringsKt.contains$default((CharSequence) strOptString3, (CharSequence) "://10.9.9.200", false, 2, (Object) null)) {
                                                            strOptString3 = CommonUtils.getResizedImageUrl(strOptString3, 0, 0);
                                                        }
                                                    } else {
                                                        z2 = false;
                                                    }
                                                    String src = jSONObject5.getString("src");
                                                    boolean z3 = true;
                                                    if (!Intrinsics.areEqual(variant, "dvb")) {
                                                        Intrinsics.checkExpressionValueIsNotNull(variant, "variant");
                                                        String string = jSONObject5.getString("src");
                                                        Intrinsics.checkExpressionValueIsNotNull(string, "jsonLive.getString(\"src\")");
                                                        linkedHashMap4.put(variant, new ChannelExtraCaster(string, strOptString2, strOptString3));
                                                    } else if (Intrinsics.areEqual(variant, "dvb")) {
                                                        List list = second;
                                                        if (!(list instanceof Collection) || !list.isEmpty()) {
                                                            Iterator<T> it2 = list.iterator();
                                                            do {
                                                                if (!it2.hasNext()) {
                                                                    z3 = z2;
                                                                    break;
                                                                }
                                                                str3 = (String) it2.next();
                                                                Intrinsics.checkExpressionValueIsNotNull(src, "src");
                                                            } while (!TIFCompanionLibraryExtensionKt.dvbAreEquals(str3, src));
                                                        } else {
                                                            z3 = z2;
                                                            break;
                                                        }
                                                        if (z3) {
                                                            Intrinsics.checkExpressionValueIsNotNull(variant, "variant");
                                                            String string2 = jSONObject5.getString("src");
                                                            Intrinsics.checkExpressionValueIsNotNull(string2, "jsonLive.getString(\"src\")");
                                                            linkedHashMap4.put(variant, new ChannelExtraCaster(string2, strOptString2, strOptString3));
                                                        }
                                                    }
                                                }
                                                i2++;
                                                jSONObject3 = jSONObject;
                                                itKeys = it;
                                                jSONArray2 = jSONArray;
                                                length = i;
                                                linkedHashMap3 = linkedHashMap2;
                                                jSONObject4 = jSONObject2;
                                                next = str2;
                                            } catch (Exception unused2) {
                                                linkedHashMap = linkedHashMap3;
                                            }
                                        }
                                        jSONObject = jSONObject3;
                                        LinkedHashMap linkedHashMap5 = linkedHashMap3;
                                        it = itKeys;
                                        String key = next;
                                        Intrinsics.checkExpressionValueIsNotNull(key, "key");
                                        String string3 = jSONObject4.getString("channel_external_id");
                                        Intrinsics.checkExpressionValueIsNotNull(string3, "jsonChannel.getString(\"channel_external_id\")");
                                        String str4 = first;
                                        int iHashCode = str4.hashCode();
                                        if (iHashCode != -1152706742) {
                                            if (iHashCode == 1001905799 && str4.equals("DVB-C STB")) {
                                                if (((ChannelExtraCaster) linkedHashMap4.get("dvb")) != null) {
                                                    z = true;
                                                } else {
                                                    z = false;
                                                }
                                            } else if (!linkedHashMap4.containsKey("dash") || linkedHashMap4.containsKey("multicast-abr")) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                        } else if (!str4.equals("IPTV STB")) {
                                            if (linkedHashMap4.containsKey("dash")) {
                                            }
                                            z = true;
                                        } else if (linkedHashMap4.containsKey("multicast") || linkedHashMap4.containsKey("dash") || linkedHashMap4.containsKey("multicast-abr")) {
                                            z = true;
                                        } else {
                                            z = false;
                                        }
                                        linkedHashMap = linkedHashMap5;
                                        try {
                                            linkedHashMap.put(key, new ChannelExtras(string3, null, null, linkedHashMap4, z || linkedHashMap4.containsKey("interactive")));
                                        } catch (Exception unused3) {
                                        }
                                    } catch (Exception unused4) {
                                        jSONObject = jSONObject3;
                                        it = itKeys;
                                    }
                                    linkedHashMap3 = linkedHashMap;
                                    jSONObject3 = jSONObject;
                                    itKeys = it;
                                }
                                return MapsKt.toMap(linkedHashMap3);
                            }
                        }).doFinally(new Action() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.availableLineups.2.1.2.2
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                if (StringsKt.contains$default((CharSequence) first, (CharSequence) "STB", false, 2, (Object) null)) {
                                    Log.v(MegacableLiveTVService$availableLineups$2.this.this$0.TAG, "STB device will cache TIF");
                                    CommonUtils.cacheTIFChannelList(MegacableLiveTVService$availableLineups$2.this.this$0.context);
                                } else {
                                    Log.v(MegacableLiveTVService$availableLineups$2.this.this$0.TAG, " OTT device no TIF");
                                }
                            }
                        });
                    }
                }).subscribeOn(Schedulers.io());
                Intrinsics.checkExpressionValueIsNotNull(singleSubscribeOn, "Single.zip(modelExternal…scribeOn(Schedulers.io())");
                map.put(lineupId, CachesKt.cacheUntil(singleSubscribeOn, MegacableLiveTVService$availableLineups$2.this.this$0.getLineupCacheCanceler()));
            }
            Object obj = this.$extrasMapMap.get(lineupId);
            if (obj == null) {
                Intrinsics.throwNpe();
            }
            return (Single) obj;
        }
    }

    /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.MegacableLiveTVService$availableLineups$2$2, reason: invalid class name */
    /* JADX INFO: compiled from: MegacableLiveTVService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "observer", "Lio/reactivex/ObservableEmitter;", "", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass2<T> implements ObservableOnSubscribe<T> {
        AnonymousClass2() {
        }

        @Override // io.reactivex.ObservableOnSubscribe
        public final void subscribe(ObservableEmitter<Integer> observer) {
            Intrinsics.checkParameterIsNotNull(observer, "observer");
            final Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new MegacableLiveTVService$availableLineups$2$2$job$1(this, observer, null), 2, null);
            observer.setCancellable(new Cancellable() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.availableLineups.2.2.1
                @Override // io.reactivex.functions.Cancellable
                public final void cancel() {
                    Log.d(MegacableLiveTVService$availableLineups$2.this.this$0.TAG, "Cache: cancel on dispose " + MegacableLiveTVService$availableLineups$2.this.this$0);
                    Job.DefaultImpls.cancel$default(jobLaunch$default, (CancellationException) null, 1, (Object) null);
                }
            });
        }
    }

    /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.MegacableLiveTVService$availableLineups$2$6, reason: invalid class name */
    /* JADX INFO: compiled from: MegacableLiveTVService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lcom/dotscreen/bokit/model/Lineup;", "lineup", "apply"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass6<T, R> implements Function<T, R> {
        final /* synthetic */ AnonymousClass1 $extrasMap$1;

        AnonymousClass6(AnonymousClass1 anonymousClass1) {
            this.$extrasMap$1 = anonymousClass1;
        }

        @Override // io.reactivex.functions.Function
        public final Lineup apply(Lineup lineup) {
            Intrinsics.checkParameterIsNotNull(lineup, "lineup");
            return new Lineup(MegacableLiveTVService$availableLineups$2.this.$boService, lineup.getId(), lineup.getTitle(), lineup.getSummary(), lineup.getArtworks(), lineup.getDeepLink(), lineup.getRatable(), lineup.getChildren(), lineup.getItemsCount(), new AnonymousClass1(lineup), null, 1024, null);
        }

        /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.MegacableLiveTVService$availableLineups$2$6$1, reason: invalid class name */
        /* JADX INFO: compiled from: MegacableLiveTVService.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/Channel;", "kotlin.jvm.PlatformType", "order", "Lcom/dotscreen/bokit/model/ItemSortingOrder;", "invoke"}, k = 3, mv = {1, 1, 16})
        static final class AnonymousClass1 extends Lambda implements Function1<ItemSortingOrder, Observable<Channel>> {
            final /* synthetic */ Lineup $lineup;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(Lineup lineup) {
                super(1);
                this.$lineup = lineup;
            }

            @Override // kotlin.jvm.functions.Function1
            public final Observable<Channel> invoke(final ItemSortingOrder order) {
                Intrinsics.checkParameterIsNotNull(order, "order");
                Observable<R> observableFlatMapObservable = AnonymousClass6.this.$extrasMap$1.invoke(this.$lineup.getId()).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.availableLineups.2.6.1.1
                    @Override // io.reactivex.functions.Function
                    public final Observable<RightVChannel> apply(final Map<String, ChannelExtras> extras) {
                        Intrinsics.checkParameterIsNotNull(extras, "extras");
                        return AnonymousClass1.this.$lineup.getItemsSource().invoke(order).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.availableLineups.2.6.1.1.1
                            @Override // io.reactivex.functions.Function
                            public final RightVChannel apply(Channel it) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                Context context = MegacableLiveTVService$availableLineups$2.this.this$0.context;
                                BOService bOService = MegacableLiveTVService$availableLineups$2.this.$boService;
                                String id = AnonymousClass1.this.$lineup.getId();
                                ChannelExtras channelExtras = (ChannelExtras) extras.get(it.getId());
                                return new RightVChannel(context, it, bOService, id, channelExtras != null ? channelExtras : new ChannelExtras("", "", true, MapsKt.emptyMap(), false));
                            }
                        }).filter(new Predicate<RightVChannel>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.availableLineups.2.6.1.1.2
                            @Override // io.reactivex.functions.Predicate
                            public final boolean test(RightVChannel channel) {
                                Intrinsics.checkParameterIsNotNull(channel, "channel");
                                return channel.getExtras().getPlayableOnDevice();
                            }
                        });
                    }
                });
                Intrinsics.checkExpressionValueIsNotNull(observableFlatMapObservable, "extrasMap(lineup.id).fla…                        }");
                return observableFlatMapObservable;
            }
        }
    }
}
