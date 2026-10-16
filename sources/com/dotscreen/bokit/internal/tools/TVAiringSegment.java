package com.dotscreen.bokit.internal.tools;

import android.util.Log;
import android.util.LruCache;
import com.dotscreen.bokit.BOKitKt;
import com.dotscreen.bokit.internal.parsers.AiringParser;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.services.BOService;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* JADX INFO: compiled from: TVAiringSegment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0010\u0018\u0000 %2\u00020\u0001:\u0001%B\u001f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"H\u0002J\b\u0010$\u001a\u00020\u0005H\u0016R\u0016\u0010\t\u001a\n \n*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000fR\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u000fR\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0019@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/dotscreen/bokit/internal/tools/TVAiringSegment;", "", "boService", "Lcom/dotscreen/bokit/services/BOService;", "lineupId", "", "segmentId", "", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;J)V", "TAG", "kotlin.jvm.PlatformType", "airings", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/TVAiring;", "getAirings", "()Lio/reactivex/Observable;", "airingsCache", "airingsCacheFromDevice", "getAiringsCacheFromDevice", "airingsFromRequest", "getAiringsFromRequest", "getBoService", "()Lcom/dotscreen/bokit/services/BOService;", "cacheFilePath", "<set-?>", "", "isCached", "()Z", "lastError", "", "lastErrorTimestamp", "sharedCounterLock", "Ljava/util/concurrent/locks/ReentrantLock;", "cleanObsoleteDeviceCache", "Lio/reactivex/Single;", "", "toString", "Factory", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class TVAiringSegment {

    /* JADX INFO: renamed from: Factory, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static LruCache<String, TVAiringSegment> airingSegmentsCache = new LruCache<>(BOKitKt.getDefaultAiringsDaysCache());
    private final String TAG;
    private Observable<TVAiring> airingsCache;
    private final BOService boService;
    private final String cacheFilePath;
    private boolean isCached;
    private Throwable lastError;
    private long lastErrorTimestamp;
    private final String lineupId;
    private final long segmentId;
    private final ReentrantLock sharedCounterLock;

    protected TVAiringSegment(BOService boService, String lineupId, long j) {
        String strStringPlus;
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(lineupId, "lineupId");
        this.boService = boService;
        this.lineupId = lineupId;
        this.segmentId = j;
        this.TAG = TVAiringSegment.class.getSimpleName();
        this.sharedCounterLock = new ReentrantLock();
        if (this.boService.getSharedCacheDirectory() != null) {
            strStringPlus = Intrinsics.stringPlus(this.boService.getSharedCacheDirectory(), '/' + this.lineupId + '-' + this.segmentId + ".amtv");
        } else {
            strStringPlus = null;
        }
        this.cacheFilePath = strStringPlus;
    }

    protected final BOService getBoService() {
        return this.boService;
    }

    /* JADX INFO: renamed from: com.dotscreen.bokit.internal.tools.TVAiringSegment$Factory, reason: from kotlin metadata */
    /* JADX INFO: compiled from: TVAiringSegment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/dotscreen/bokit/internal/tools/TVAiringSegment$Factory;", "", "()V", "airingSegmentsCache", "Landroid/util/LruCache;", "", "Lcom/dotscreen/bokit/internal/tools/TVAiringSegment;", "getInstance", "boService", "Lcom/dotscreen/bokit/services/BOService;", "lineupId", "segmentId", "", "invalidateCache", "", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TVAiringSegment getInstance(BOService boService, String lineupId, long segmentId) {
            Intrinsics.checkNotNullParameter(boService, "boService");
            Intrinsics.checkNotNullParameter(lineupId, "lineupId");
            String str = lineupId + '-' + segmentId;
            TVAiringSegment tVAiringSegment = (TVAiringSegment) TVAiringSegment.airingSegmentsCache.get(str);
            if (tVAiringSegment != null) {
                return tVAiringSegment;
            }
            TVAiringSegment tVAiringSegment2 = new TVAiringSegment(boService, lineupId, segmentId);
            TVAiringSegment.airingSegmentsCache.put(str, tVAiringSegment2);
            return tVAiringSegment2;
        }

        public final void invalidateCache() {
            TVAiringSegment.airingSegmentsCache.evictAll();
        }
    }

    public String toString() {
        return "[TVAiringSegment] lineupId: " + this.lineupId + ", segmentId: " + this.segmentId;
    }

    /* JADX INFO: renamed from: isCached, reason: from getter */
    public final boolean getIsCached() {
        return this.isCached;
    }

    private final Observable<TVAiring> getAiringsCacheFromDevice() {
        cleanObsoleteDeviceCache();
        Observable<TVAiring> observableNever = Observable.never();
        Intrinsics.checkNotNullExpressionValue(observableNever, "Observable.never()");
        return observableNever;
    }

    private final Observable<TVAiring> getAiringsFromRequest() {
        Single<Map<Object, Object>> singleFetchTVAiringSegment$bokit_android_release = this.boService.fetchTVAiringSegment$bokit_android_release(this.lineupId, this.segmentId);
        Single map = this.boService.getLiveTVService().lineupById(this.lineupId).flatMapObservable(new Function<Lineup, ObservableSource<? extends Channel>>() { // from class: com.dotscreen.bokit.internal.tools.TVAiringSegment$airingsFromRequest$channelsObs$1
            @Override // io.reactivex.functions.Function
            public final ObservableSource<? extends Channel> apply(Lineup it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getItems();
            }
        }).toList().map(new Function<List<Channel>, Map<String, Channel>>() { // from class: com.dotscreen.bokit.internal.tools.TVAiringSegment$airingsFromRequest$channelsObs$2
            @Override // io.reactivex.functions.Function
            public final Map<String, Channel> apply(List<Channel> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Channel it2 : it) {
                    String id = it2.getId();
                    Intrinsics.checkNotNullExpressionValue(it2, "it");
                    linkedHashMap.put(id, it2);
                }
                return linkedHashMap;
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "boService.liveTVService.…map\n                    }");
        Observable<TVAiring> observableFlatMapObservable = Single.zip(singleFetchTVAiringSegment$bokit_android_release.subscribeOn(Schedulers.computation()), map.subscribeOn(Schedulers.computation()), this.boService.getLiveTVService().getGenresReferences().subscribeOn(Schedulers.computation()), new Function3<Map<Object, ? extends Object>, Map<String, Channel>, Map<Object, ? extends Object>, Observable<TVAiring>>() { // from class: com.dotscreen.bokit.internal.tools.TVAiringSegment$airingsFromRequest$1
            @Override // io.reactivex.functions.Function3
            public /* bridge */ /* synthetic */ Observable<TVAiring> apply(Map<Object, ? extends Object> map2, Map<String, Channel> map3, Map<Object, ? extends Object> map4) {
                return apply2(map2, (Map<String, ? extends Channel>) map3, map4);
            }

            /* JADX INFO: renamed from: apply, reason: avoid collision after fix types in other method */
            public final Observable<TVAiring> apply2(Map<Object, ? extends Object> value, Map<String, ? extends Channel> map2, Map<Object, ? extends Object> references) {
                Intrinsics.checkNotNullParameter(value, "value");
                Intrinsics.checkNotNullParameter(map2, "map");
                Intrinsics.checkNotNullParameter(references, "references");
                return AiringParser.INSTANCE.parseAirings(this.this$0.getBoService(), map2, references, value);
            }
        }).flatMapObservable(new Function<Observable<TVAiring>, ObservableSource<? extends TVAiring>>() { // from class: com.dotscreen.bokit.internal.tools.TVAiringSegment$airingsFromRequest$2
            @Override // io.reactivex.functions.Function
            public final ObservableSource<? extends TVAiring> apply(Observable<TVAiring> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it;
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMapObservable, "Single.zip(\n            ….flatMapObservable { it }");
        return observableFlatMapObservable;
    }

    public Observable<TVAiring> getAirings() {
        if (this.airingsCache == null) {
            Observable<TVAiring> observableDoOnComplete = getAiringsFromRequest().sorted(new Comparator<TVAiring>() { // from class: com.dotscreen.bokit.internal.tools.TVAiringSegment$airings$1
                @Override // java.util.Comparator
                public final int compare(TVAiring tVAiring, TVAiring tVAiring2) {
                    if (tVAiring.getChannel().getZappingNumber() == tVAiring2.getChannel().getZappingNumber()) {
                        return tVAiring.getStartDate().compareTo(tVAiring2.getStartDate());
                    }
                    return tVAiring.getChannel().getZappingNumber() - tVAiring2.getChannel().getZappingNumber();
                }
            }).doOnComplete(new Action() { // from class: com.dotscreen.bokit.internal.tools.TVAiringSegment$airings$2
                @Override // io.reactivex.functions.Action
                public final void run() {
                    this.this$0.isCached = true;
                }
            });
            Intrinsics.checkNotNullExpressionValue(observableDoOnComplete, "airingsFromRequest\n     …plete { isCached = true }");
            Observable observableNever = Observable.never();
            Intrinsics.checkNotNullExpressionValue(observableNever, "Observable.never<Int>()");
            this.airingsCache = ToolsKt.cacheUntil(observableDoOnComplete, observableNever);
        }
        Observable<TVAiring> observable = this.airingsCache;
        Intrinsics.checkNotNull(observable);
        return observable;
    }

    private final Single<Unit> cleanObsoleteDeviceCache() {
        Single<Unit> singleCreate = Single.create(new SingleOnSubscribe<Unit>() { // from class: com.dotscreen.bokit.internal.tools.TVAiringSegment.cleanObsoleteDeviceCache.1
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<Unit> observer) {
                List listEmptyList;
                Intrinsics.checkNotNullParameter(observer, "observer");
                String sharedCacheDirectory = TVAiringSegment.this.getBoService().getSharedCacheDirectory();
                if (sharedCacheDirectory != null) {
                    File[] fileArrListFiles = new File(sharedCacheDirectory).listFiles();
                    if (fileArrListFiles == null) {
                        Log.d(TVAiringSegment.this.TAG, "cleanObsoleteDeviceCache: cache is empty");
                    } else {
                        long jSegmentIdForDate = TVAiringSegmentKt.segmentIdForDate(TimeHelper.INSTANCE.now()) - 1;
                        Log.d(TVAiringSegment.this.TAG, "cleanObsoleteDeviceCache: firstNonObsoleteSegmentId = " + jSegmentIdForDate);
                        int length = fileArrListFiles.length;
                        for (int i = 0; i < length; i++) {
                            File f = fileArrListFiles[i];
                            Intrinsics.checkNotNullExpressionValue(f, "f");
                            if (f.isFile() && Intrinsics.areEqual(FilesKt.getExtension(f), "amtv")) {
                                String fileName = f.getName();
                                Log.d(TVAiringSegment.this.TAG, "cleanObsoleteDeviceCache: fileName = " + fileName);
                                Intrinsics.checkNotNullExpressionValue(fileName, "fileName");
                                List<String> listSplit = new Regex("[\\-.]").split(fileName, 0);
                                if (!listSplit.isEmpty()) {
                                    ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
                                    while (true) {
                                        if (listIterator.hasPrevious()) {
                                            if (!(listIterator.previous().length() == 0)) {
                                                listEmptyList = CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                                                break;
                                            }
                                        } else {
                                            listEmptyList = CollectionsKt.emptyList();
                                            break;
                                        }
                                    }
                                } else {
                                    listEmptyList = CollectionsKt.emptyList();
                                    break;
                                }
                                Object[] array = listEmptyList.toArray(new String[0]);
                                if (array != null) {
                                    long j = Long.parseLong(((String[]) array)[1]);
                                    Log.d(TVAiringSegment.this.TAG, "cleanObsoleteDeviceCache:     segmentId = " + j);
                                    if (j < jSegmentIdForDate) {
                                        Log.d(TVAiringSegment.this.TAG, "cleanObsoleteDeviceCache:     -> delete");
                                        f.delete();
                                    }
                                } else {
                                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                                }
                            }
                        }
                    }
                    observer.onSuccess(Unit.INSTANCE);
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "Single.create { observer…)\n            }\n        }");
        return singleCreate;
    }
}
