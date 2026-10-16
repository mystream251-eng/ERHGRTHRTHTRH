package com.dotscreen.bokit.model;

import com.dotscreen.bokit.internal.tools.TVAiringSegmentKt;
import com.dotscreen.bokit.internal.tools.TimeHelper;
import com.dotscreen.bokit.services.LiveTVService;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Channel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u008b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\u0012\b\u0002\u0010\u0015\u001a\f\u0012\b\u0012\u00060\u0005j\u0002`\u00160\n¢\u0006\u0002\u0010\u0017J\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-2\u0006\u0010/\u001a\u000200J\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-2\u0006\u00101\u001a\u000202J\u001c\u00103\u001a\b\u0012\u0004\u0012\u00020.042\u0006\u00105\u001a\u0002022\u0006\u00106\u001a\u000202J*\u00107\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\n2\u0006\u00105\u001a\u0002022\u0006\u00106\u001a\u0002022\b\b\u0002\u00108\u001a\u00020\u0014H\u0007R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001b\u0010\u0015\u001a\f\u0012\b\u0012\u00060\u0005j\u0002`\u00160\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u0006\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR(\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u001e\u001a\u0004\u0018\u00010\u0010@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R$\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010$X\u0080\u000e¢\u0006\u0010\n\u0002\u0010)\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+¨\u00069"}, d2 = {"Lcom/dotscreen/bokit/model/Channel;", "Lcom/dotscreen/bokit/model/BrowsableItem;", "tvService", "Lcom/dotscreen/bokit/services/LiveTVService;", "id", "", "lineupId", "title", "summary", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "deepLink", "zappingNumber", "", "liveStream", "Lcom/dotscreen/bokit/model/LiveStream;", "capabilities", "Lcom/dotscreen/bokit/model/ChannelCapabilities;", "ratable", "", "genres", "Lcom/dotscreen/bokit/model/Genre;", "(Lcom/dotscreen/bokit/services/LiveTVService;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ILcom/dotscreen/bokit/model/LiveStream;Lcom/dotscreen/bokit/model/ChannelCapabilities;ZLjava/util/List;)V", "getCapabilities", "()Lcom/dotscreen/bokit/model/ChannelCapabilities;", "getGenres", "()Ljava/util/List;", "getLineupId$bokit_android_release", "()Ljava/lang/String;", "<set-?>", "getLiveStream", "()Lcom/dotscreen/bokit/model/LiveStream;", "setLiveStream$bokit_android_release", "(Lcom/dotscreen/bokit/model/LiveStream;)V", "staticStreams", "", "getStaticStreams$bokit_android_release", "()[Ljava/lang/String;", "setStaticStreams$bokit_android_release", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "getZappingNumber", "()I", "airing", "Lio/reactivex/Maybe;", "Lcom/dotscreen/bokit/model/TVAiring;", "timeOfDay", "Lcom/dotscreen/bokit/model/TVAiringTimeOfDay;", "time", "Ljava/util/Date;", "airings", "Lio/reactivex/Observable;", "startTime", "endTime", "airingsSync", "autoLoad", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class Channel extends BrowsableItem {
    private final ChannelCapabilities capabilities;
    private final List<String> genres;
    private final String lineupId;
    private LiveStream liveStream;
    private String[] staticStreams;
    private final LiveTVService tvService;
    private final int zappingNumber;

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

    /* JADX INFO: renamed from: getLineupId$bokit_android_release, reason: from getter */
    public final String getLineupId() {
        return this.lineupId;
    }

    public /* synthetic */ Channel(LiveTVService liveTVService, String str, String str2, String str3, String str4, List list, String str5, int i, LiveStream liveStream, ChannelCapabilities channelCapabilities, boolean z, List list2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(liveTVService, str, str2, str3, (i2 & 16) != 0 ? (String) null : str4, (i2 & 32) != 0 ? CollectionsKt.emptyList() : list, (i2 & 64) != 0 ? (String) null : str5, (i2 & 128) != 0 ? 0 : i, (i2 & 256) != 0 ? (LiveStream) null : liveStream, (i2 & 512) != 0 ? new ChannelCapabilities(false, false, false, false, 15, null) : channelCapabilities, (i2 & 1024) != 0 ? false : z, (i2 & 2048) != 0 ? CollectionsKt.emptyList() : list2);
    }

    public final int getZappingNumber() {
        return this.zappingNumber;
    }

    public final ChannelCapabilities getCapabilities() {
        return this.capabilities;
    }

    public final List<String> getGenres() {
        return this.genres;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Channel(LiveTVService tvService, String id, String lineupId, String title, String str, List<? extends Artwork> artworks, String str2, int i, LiveStream liveStream, ChannelCapabilities capabilities, boolean z, List<String> genres) {
        super(id, title, str, artworks, str2, z, null, 64, null);
        Intrinsics.checkNotNullParameter(tvService, "tvService");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(lineupId, "lineupId");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artworks, "artworks");
        Intrinsics.checkNotNullParameter(capabilities, "capabilities");
        Intrinsics.checkNotNullParameter(genres, "genres");
        this.tvService = tvService;
        this.lineupId = lineupId;
        this.zappingNumber = i;
        this.capabilities = capabilities;
        this.genres = genres;
        this.liveStream = liveStream;
    }

    public final LiveStream getLiveStream() {
        return this.liveStream;
    }

    public final void setLiveStream$bokit_android_release(LiveStream liveStream) {
        this.liveStream = liveStream;
    }

    /* JADX INFO: renamed from: getStaticStreams$bokit_android_release, reason: from getter */
    public final String[] getStaticStreams() {
        return this.staticStreams;
    }

    public final void setStaticStreams$bokit_android_release(String[] strArr) {
        this.staticStreams = strArr;
    }

    public final Maybe<TVAiring> airing(TVAiringTimeOfDay timeOfDay) {
        Intrinsics.checkNotNullParameter(timeOfDay, "timeOfDay");
        Observable<TVAiring> observableFilter = this.tvService.airings(TVAiringSegmentKt.segmentIdForDate(TimeHelper.INSTANCE.now()), this.lineupId).filter(new Predicate<TVAiring>() { // from class: com.dotscreen.bokit.model.Channel$airing$obs$1
            @Override // io.reactivex.functions.Predicate
            public final boolean test(TVAiring it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Intrinsics.areEqual(it.getChannel(), this.this$0);
            }
        });
        int i = WhenMappings.$EnumSwitchMapping$0[timeOfDay.ordinal()];
        if (i == 1) {
            Maybe<TVAiring> maybeFirstElement = observableFilter.filter(new Predicate<TVAiring>() { // from class: com.dotscreen.bokit.model.Channel.airing.1
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
            Maybe<TVAiring> maybeFirstElement2 = observableFilter.skipWhile(new Predicate<TVAiring>() { // from class: com.dotscreen.bokit.model.Channel.airing.2
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
            Maybe<TVAiring> maybeFirstElement3 = airings(TimeHelper.INSTANCE.now(), TimeHelper.INSTANCE.endOfDay(TimeHelper.INSTANCE.now())).filter(new Predicate<TVAiring>() { // from class: com.dotscreen.bokit.model.Channel.airing.3
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
        Maybe<TVAiring> maybeFirstElement4 = airings(TimeHelper.INSTANCE.now(), TimeHelper.INSTANCE.endOfDay(TimeHelper.INSTANCE.now())).filter(new Predicate<TVAiring>() { // from class: com.dotscreen.bokit.model.Channel.airing.4
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
        Maybe<TVAiring> maybeFirstElement = this.tvService.airings(TVAiringSegmentKt.segmentIdForDate(time), this.lineupId).filter(new Predicate<TVAiring>() { // from class: com.dotscreen.bokit.model.Channel.airing.5
            @Override // io.reactivex.functions.Predicate
            public final boolean test(TVAiring it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Intrinsics.areEqual(it.getChannel(), Channel.this);
            }
        }).filter(new Predicate<TVAiring>() { // from class: com.dotscreen.bokit.model.Channel.airing.6
            @Override // io.reactivex.functions.Predicate
            public final boolean test(TVAiring it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.isAiredAt(time);
            }
        }).firstElement();
        Intrinsics.checkNotNullExpressionValue(maybeFirstElement, "tvService.airings(segmen…          .firstElement()");
        return maybeFirstElement;
    }

    public final Observable<TVAiring> airings(final Date startTime, final Date endTime) {
        Intrinsics.checkNotNullParameter(startTime, "startTime");
        Intrinsics.checkNotNullParameter(endTime, "endTime");
        long jSegmentIdForDate = TVAiringSegmentKt.segmentIdForDate(startTime);
        Observable<TVAiring> observableDistinct = Observable.rangeLong(jSegmentIdForDate, (TVAiringSegmentKt.segmentIdForDate(endTime) - jSegmentIdForDate) + 1).flatMap(new Function<Long, ObservableSource<? extends TVAiring>>() { // from class: com.dotscreen.bokit.model.Channel.airings.1
            @Override // io.reactivex.functions.Function
            public final ObservableSource<? extends TVAiring> apply(Long it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Channel.this.tvService.airings(it.longValue(), Channel.this.getLineupId());
            }
        }).filter(new Predicate<TVAiring>() { // from class: com.dotscreen.bokit.model.Channel.airings.2
            @Override // io.reactivex.functions.Predicate
            public final boolean test(TVAiring it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Intrinsics.areEqual(it.getChannel(), Channel.this) && it.isAiredBetween(startTime, endTime);
            }
        }).sorted(new Comparator<TVAiring>() { // from class: com.dotscreen.bokit.model.Channel.airings.3
            @Override // java.util.Comparator
            public final int compare(TVAiring tVAiring, TVAiring tVAiring2) {
                return tVAiring.getStartDate().compareTo(tVAiring2.getStartDate());
            }
        }).distinct();
        Intrinsics.checkNotNullExpressionValue(observableDistinct, "Observable.rangeLong(sta…              .distinct()");
        return observableDistinct;
    }

    public static /* synthetic */ List airingsSync$default(Channel channel, Date date, Date date2, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: airingsSync");
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return channel.airingsSync(date, date2, z);
    }

    public final List<TVAiring> airingsSync(Date startTime, Date endTime, boolean autoLoad) {
        Intrinsics.checkNotNullParameter(startTime, "startTime");
        Intrinsics.checkNotNullParameter(endTime, "endTime");
        long jSegmentIdForDate = TVAiringSegmentKt.segmentIdForDate(startTime);
        long jSegmentIdForDate2 = TVAiringSegmentKt.segmentIdForDate(endTime);
        boolean z = true;
        if (jSegmentIdForDate <= jSegmentIdForDate2) {
            while (true) {
                if (!this.tvService.segmentHasCache$bokit_android_release(jSegmentIdForDate, this.lineupId)) {
                    z = false;
                }
                if (jSegmentIdForDate == jSegmentIdForDate2) {
                    break;
                }
                jSegmentIdForDate++;
            }
        }
        if (z) {
            return airings(startTime, endTime).toList().blockingGet();
        }
        if (autoLoad) {
            airings(startTime, endTime).subscribeOn(Schedulers.io()).subscribe(new Consumer<TVAiring>() { // from class: com.dotscreen.bokit.model.Channel.airingsSync.1
                @Override // io.reactivex.functions.Consumer
                public final void accept(TVAiring tVAiring) {
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.bokit.model.Channel.airingsSync.2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                }
            }, new Action() { // from class: com.dotscreen.bokit.model.Channel.airingsSync.3
                @Override // io.reactivex.functions.Action
                public final void run() {
                }
            });
        }
        return null;
    }
}
