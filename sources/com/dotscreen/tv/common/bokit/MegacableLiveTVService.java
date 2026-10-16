package com.dotscreen.tv.common.bokit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.tv.TvContentRating;
import android.os.Build;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.android.tv.common.R;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.ArtworkFormat;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Classifier;
import com.dotscreen.bokit.model.ItemCollection;
import com.dotscreen.bokit.model.ItemSortingOrder;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.MediaMetadata;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.Person;
import com.dotscreen.bokit.model.StaticArtwork;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVAiringTimeOfDay;
import com.dotscreen.bokit.model.TVProgram;
import com.dotscreen.bokit.model.TVRecording;
import com.dotscreen.bokit.model.User;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.LiveTVService;
import com.dotscreen.bokit.services.PVRStorageMode;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.farkit.tools.OptionalCompat;
import com.dotscreen.tv.common.IForegroudStateProvider;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.common.utils.notifications.NotificationsContract;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.util.GmsVersion;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: MegacableLiveTVService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¿\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005*\u00010\u0018\u00002\u00020\u0001:\u0001^B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u00020)J\u001e\u00108\u001a\b\u0012\u0004\u0012\u0002090 2\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0016J\u0006\u0010>\u001a\u000206J\u001c\u0010?\u001a\b\u0012\u0004\u0012\u00020@032\u0006\u0010A\u001a\u00020\n2\u0006\u0010B\u001a\u00020\nJ0\u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020=0D032\u0006\u0010E\u001a\u00020\n2\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010G\u001a\u00020)J\u001e\u0010H\u001a\b\u0012\u0004\u0012\u00020)032\u0006\u0010I\u001a\u00020J2\u0006\u0010<\u001a\u00020=H\u0016J\u0012\u0010K\u001a\u0004\u0018\u00010L2\u0006\u0010M\u001a\u00020\nH\u0016J\u0018\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020Q2\b\b\u0002\u0010R\u001a\u00020\nJ\u000e\u0010S\u001a\u00020O2\u0006\u0010:\u001a\u00020;J\u000e\u0010S\u001a\u00020O2\u0006\u0010T\u001a\u00020\nJ\u0014\u0010U\u001a\b\u0012\u0004\u0012\u00020V032\u0006\u0010W\u001a\u00020\nJ\u0016\u0010X\u001a\b\u0012\u0004\u0012\u0002090 2\u0006\u0010<\u001a\u00020=H\u0016J\u0010\u0010Y\u001a\u0004\u0018\u00010Z2\u0006\u0010[\u001a\u00020LJ\u0010\u0010\\\u001a\u0004\u0018\u00010Z2\u0006\u0010]\u001a\u00020\nR\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR!\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\n0 8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b#\u0010\u0018\u001a\u0004\b!\u0010\"R\u001a\u0010$\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u000f\"\u0004\b&\u0010\u0011R\u001f\u0010'\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u00010)0)0(¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R!\u0010,\u001a\b\u0012\u0004\u0012\u00020\n0 8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b.\u0010\u0018\u001a\u0004\b-\u0010\"R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0004\n\u0002\u00101R\u001c\u00102\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u0001040403X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006_"}, d2 = {"Lcom/dotscreen/tv/common/bokit/MegacableLiveTVService;", "Lcom/dotscreen/bokit/services/LiveTVService;", "context", "Landroid/content/Context;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "requestManager", "Lcom/dotscreen/tv/common/bokit/RightVRequestManager;", "(Landroid/content/Context;Lcom/dotscreen/bokit/services/BOService;Lcom/dotscreen/tv/common/bokit/RightVRequestManager;)V", "TAG", "", "kotlin.jvm.PlatformType", "additionnalCacheCancelDelay", "", "getAdditionnalCacheCancelDelay", "()J", "setAdditionnalCacheCancelDelay", "(J)V", "availableLineups", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/Lineup;", "getAvailableLineups", "()Lio/reactivex/Observable;", "availableLineups$delegate", "Lkotlin/Lazy;", "disposableTimer", "Lio/reactivex/disposables/Disposable;", "getDisposableTimer", "()Lio/reactivex/disposables/Disposable;", "setDisposableTimer", "(Lio/reactivex/disposables/Disposable;)V", "householdTerminalId", "Lio/reactivex/Maybe;", "getHouseholdTerminalId", "()Lio/reactivex/Maybe;", "householdTerminalId$delegate", "lastCacheCancelTime", "getLastCacheCancelTime", "setLastCacheCancelTime", "lineupCacheCanceler", "Lio/reactivex/subjects/PublishSubject;", "", "getLineupCacheCanceler", "()Lio/reactivex/subjects/PublishSubject;", "modelExternalId", "getModelExternalId", "modelExternalId$delegate", "scanReceiver", "com/dotscreen/tv/common/bokit/MegacableLiveTVService$scanReceiver$1", "Lcom/dotscreen/tv/common/bokit/MegacableLiveTVService$scanReceiver$1;", "timeShiftingServicesConfig", "Lio/reactivex/Single;", "Lcom/dotscreen/tv/common/bokit/MegacableLiveTVService$TimeShiftingServicesConfig;", "cancelLineupCache", "", "force", "catchupMediaForProgram", "Lcom/dotscreen/bokit/model/Media;", "program", "Lcom/dotscreen/bokit/model/TVProgram;", "airing", "Lcom/dotscreen/bokit/model/TVAiring;", "dispose", "getExtendedProgramInfo", "Lcom/dotscreen/tv/common/bokit/RightVProgramInfo;", "channelExternalId", "programExternalId", "getRecommendations", "Lcom/dotscreen/bokit/model/ItemCollection;", "blend", "contentId", "isAdult", "isAiringRecordable", "mode", "Lcom/dotscreen/bokit/services/PVRStorageMode;", "parentalRatingForName", "Lcom/dotscreen/bokit/model/ParentalRating;", "name", "registerLiveEvent", "Lio/reactivex/Completable;", "channel", "Lcom/dotscreen/bokit/model/Channel;", "type", "registerPlayProgramEvent", "programId", "searchProgram", "Lcom/dotscreen/tv/common/bokit/LiveProgramsSearchResponse;", SearchIntents.EXTRA_QUERY, "startOverMediaForAiring", "toTvContentRating", "Landroid/media/tv/TvContentRating;", "parentalLevel", "tvContentRatingFromString", "rating", "TimeShiftingServicesConfig", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class MegacableLiveTVService extends LiveTVService {
    private final String TAG;
    private long additionnalCacheCancelDelay;

    /* JADX INFO: renamed from: availableLineups$delegate, reason: from kotlin metadata */
    private final Lazy availableLineups;
    private final Context context;
    private Disposable disposableTimer;

    /* JADX INFO: renamed from: householdTerminalId$delegate, reason: from kotlin metadata */
    private final Lazy householdTerminalId;
    private long lastCacheCancelTime;
    private final PublishSubject<Boolean> lineupCacheCanceler;

    /* JADX INFO: renamed from: modelExternalId$delegate, reason: from kotlin metadata */
    private final Lazy modelExternalId;
    private final RightVRequestManager requestManager;
    private final MegacableLiveTVService$scanReceiver$1 scanReceiver;
    private final Single<TimeShiftingServicesConfig> timeShiftingServicesConfig;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PVRStorageMode.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[PVRStorageMode.Network.ordinal()] = 1;
            $EnumSwitchMapping$0[PVRStorageMode.HardDiskDrive.ordinal()] = 2;
        }
    }

    @Override // com.dotscreen.bokit.services.LiveTVService
    public Observable<Lineup> getAvailableLineups() {
        return (Observable) this.availableLineups.getValue();
    }

    public final Maybe<String> getHouseholdTerminalId() {
        return (Maybe) this.householdTerminalId.getValue();
    }

    public final Maybe<String> getModelExternalId() {
        return (Maybe) this.modelExternalId.getValue();
    }

    /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.MegacableLiveTVService$getRecommendations$2, reason: invalid class name */
    /* JADX INFO: compiled from: MegacableLiveTVService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0003 \u0004*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\n¢\u0006\u0002\b\f"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/dotscreen/bokit/model/ItemCollection;", "Lcom/dotscreen/bokit/model/TVAiring;", "kotlin.jvm.PlatformType", "<anonymous parameter 0>", "Lcom/dotscreen/bokit/model/User;", "modelExternalId", "", "channels", "", "Lcom/dotscreen/bokit/model/Channel;", "apply"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass2<T1, T2, T3, R> implements Function3<User, String, List<Channel>, Single<ItemCollection<TVAiring>>> {
        final /* synthetic */ String $blend;
        final /* synthetic */ String $contentId;
        final /* synthetic */ boolean $isAdult;

        AnonymousClass2(String str, String str2, boolean z) {
            this.$blend = str;
            this.$contentId = str2;
            this.$isAdult = z;
        }

        @Override // io.reactivex.functions.Function3
        public /* bridge */ /* synthetic */ Single<ItemCollection<TVAiring>> apply(User user, String str, List<Channel> list) {
            return apply2(user, str, (List<? extends Channel>) list);
        }

        /* JADX INFO: renamed from: apply, reason: avoid collision after fix types in other method */
        public final Single<ItemCollection<TVAiring>> apply2(User user, String modelExternalId, List<? extends Channel> channels) {
            Intrinsics.checkParameterIsNotNull(user, "<anonymous parameter 0>");
            Intrinsics.checkParameterIsNotNull(modelExternalId, "modelExternalId");
            Intrinsics.checkParameterIsNotNull(channels, "channels");
            Map mapMutableMapOf = MapsKt.mutableMapOf(new Pair("blend", this.$blend), new Pair("max_pr_level", 5), new Pair("max_results", 100));
            if (this.$contentId != null) {
                mapMutableMapOf.put("params", "program_id:" + this.$contentId);
            }
            if (this.$isAdult) {
                mapMutableMapOf.put("min_pr_level", 6);
            } else {
                mapMutableMapOf.put("max_pr_level", 5);
            }
            if ((!Intrinsics.areEqual(modelExternalId, "DVB-C STB")) && (!Intrinsics.areEqual(modelExternalId, "IPTV STB"))) {
                mapMutableMapOf.put("device_models", modelExternalId);
            }
            return RightVRequestManager.getCompassJSONArray$default(MegacableLiveTVService.this.requestManager, "GetLiveRecommendationList", 0, mapMutableMapOf, false, false, 26, null).flatMap(new AnonymousClass1(channels));
        }

        /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.MegacableLiveTVService$getRecommendations$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: MegacableLiveTVService.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0003 \u0004*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/dotscreen/bokit/model/ItemCollection;", "Lcom/dotscreen/bokit/model/TVAiring;", "kotlin.jvm.PlatformType", "response", "Lorg/json/JSONArray;", "apply"}, k = 3, mv = {1, 1, 16})
        static final class AnonymousClass1<T, R> implements Function<T, SingleSource<? extends R>> {
            final /* synthetic */ List $channels;

            AnonymousClass1(List list) {
                this.$channels = list;
            }

            @Override // io.reactivex.functions.Function
            public final Single<ItemCollection<TVAiring>> apply(final JSONArray response) {
                Intrinsics.checkParameterIsNotNull(response, "response");
                return Single.just(new ItemCollection(MegacableLiveTVService.this.getBoService(), "reco", "reco", null, null, null, false, null, Single.just(Integer.valueOf(response.length())), new Function1<ItemSortingOrder, Observable<TVAiring>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.getRecommendations.2.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Observable<TVAiring> invoke(ItemSortingOrder it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Observable<R> map = Observable.create(new ObservableOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.getRecommendations.2.1.1.1
                            @Override // io.reactivex.ObservableOnSubscribe
                            public final void subscribe(ObservableEmitter<JSONObject> emitter) {
                                Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                                int length = response.length();
                                for (int i = 0; i < length; i++) {
                                    emitter.onNext(response.getJSONObject(i));
                                }
                                emitter.onComplete();
                            }
                        }).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.getRecommendations.2.1.1.2
                            @Override // io.reactivex.functions.Function
                            public final OptionalCompat<TVAiring> apply(JSONObject reco) throws JSONException {
                                Intrinsics.checkParameterIsNotNull(reco, "reco");
                                String string = reco.getString("channelId");
                                try {
                                    for (T t : AnonymousClass1.this.$channels) {
                                        Channel channel = (Channel) t;
                                        if (channel != null) {
                                            if (Intrinsics.areEqual(((RightVChannel) channel).getExtras().getChannelExternalId(), string)) {
                                                Channel channel2 = (Channel) t;
                                                long j = reco.getLong("startDate");
                                                long j2 = reco.getLong("endDate");
                                                ArrayList arrayList = new ArrayList();
                                                JSONArray jSONArrayOptJSONArray = reco.optJSONArray("genres");
                                                int length = jSONArrayOptJSONArray != null ? jSONArrayOptJSONArray.length() : 0;
                                                for (int i = 0; i < length; i++) {
                                                    String strOptString = jSONArrayOptJSONArray.optJSONObject(i).optString("name");
                                                    if (strOptString != null) {
                                                        Boolean.valueOf(arrayList.add(strOptString));
                                                    }
                                                }
                                                ArrayList arrayList2 = new ArrayList();
                                                JSONArray jSONArray = reco.getJSONArray("ContentProperties");
                                                if (jSONArray.length() > 0) {
                                                    int length2 = jSONArray.length();
                                                    int i2 = 0;
                                                    while (i2 < length2) {
                                                        Object obj = jSONArray.get(i2);
                                                        if (obj == null) {
                                                            throw new TypeCastException("null cannot be cast to non-null type org.json.JSONObject");
                                                        }
                                                        JSONObject jSONObject = (JSONObject) obj;
                                                        JSONArray jSONArray2 = jSONArray;
                                                        String string2 = jSONObject.getString("name");
                                                        Intrinsics.checkExpressionValueIsNotNull(string2, "image.getString(\"name\")");
                                                        ArtworkFormat rightVArtworkFormat = MegacableUtilsKt.getRightVArtworkFormat(string2, ArtworkFormat.Still);
                                                        String url = jSONObject.getString("value");
                                                        long j3 = j2;
                                                        BOService boService = MegacableLiveTVService.this.getBoService();
                                                        Intrinsics.checkExpressionValueIsNotNull(url, "url");
                                                        arrayList2.add(new StaticArtwork(boService, url, rightVArtworkFormat));
                                                        i2++;
                                                        jSONArray = jSONArray2;
                                                        length2 = length2;
                                                        j2 = j3;
                                                    }
                                                }
                                                long j4 = j2;
                                                String programId = reco.getString("programId");
                                                Map mapMapOf = MapsKt.mapOf(new Pair("channel_external_id", string), new Pair("program_external_id", programId));
                                                Intrinsics.checkExpressionValueIsNotNull(programId, "programId");
                                                String string3 = reco.getString("name");
                                                Intrinsics.checkExpressionValueIsNotNull(string3, "reco.getString(\"name\")");
                                                Observable observableJust = Observable.just(MapsKt.mapOf(new Pair("rightv", mapMapOf)));
                                                Intrinsics.checkExpressionValueIsNotNull(observableJust, "Observable.just(mapOf(Pa…ightv\", rightvOfferMap)))");
                                                Maybe maybeJust = Maybe.just(new TVProgram(programId, string3, null, arrayList2, null, new MediaMetadata(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 65535, null), null, null, null, null, null, null, observableJust, false, 12240, null));
                                                Intrinsics.checkExpressionValueIsNotNull(maybeJust, "Maybe.just(\n            …                        )");
                                                BOService boService2 = MegacableLiveTVService.this.getBoService();
                                                String str = string + '#' + j;
                                                String string4 = reco.getString("name");
                                                Intrinsics.checkExpressionValueIsNotNull(string4, "reco.getString(\"name\")");
                                                Person[] personArr = null;
                                                Person[] personArr2 = null;
                                                ParentalRating parentalRatingForName = ParentalRating.INSTANCE.forName(MegacableLiveTVService.this.getBoService(), "RATING:" + reco.optInt("prLevel", 5));
                                                Integer num = null;
                                                String[] strArr = null;
                                                String[] strArr2 = null;
                                                String[] strArr3 = null;
                                                Integer num2 = null;
                                                Classifier[] classifierArr = null;
                                                Artwork[] artworkArr = null;
                                                Observable observable = null;
                                                Observable observable2 = null;
                                                ItemCollection itemCollection = null;
                                                ItemCollection itemCollection2 = null;
                                                Double d = null;
                                                Object[] array = arrayList.toArray(new String[0]);
                                                if (array != null) {
                                                    return OptionalCompat.of(new TVAiring(boService2, str, string4, null, arrayList2, null, new MediaMetadata(personArr, personArr2, (String[]) array, num, strArr, strArr2, strArr3, num2, classifierArr, artworkArr, observable, observable2, itemCollection, itemCollection2, d, parentalRatingForName, 32763, null), new Date(j), new Date(j4), channel2, maybeJust, false, false, false, 14336, null));
                                                }
                                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                                            }
                                        } else {
                                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVChannel");
                                        }
                                    }
                                    throw new NoSuchElementException("Collection contains no element matching the predicate.");
                                } catch (NoSuchElementException unused) {
                                    Log.d(MegacableLiveTVService.this.TAG, "GetLiveRecommendationList : " + string + " is not in the user bouquet");
                                    return OptionalCompat.empty();
                                }
                            }
                        }).filter(new Predicate<OptionalCompat<TVAiring>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.getRecommendations.2.1.1.3
                            @Override // io.reactivex.functions.Predicate
                            public final boolean test(OptionalCompat<TVAiring> it2) {
                                Intrinsics.checkParameterIsNotNull(it2, "it");
                                return it2.isPresent();
                            }
                        }).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.getRecommendations.2.1.1.4
                            @Override // io.reactivex.functions.Function
                            public final TVAiring apply(OptionalCompat<TVAiring> it2) {
                                Intrinsics.checkParameterIsNotNull(it2, "it");
                                return it2.get();
                            }
                        });
                        Intrinsics.checkExpressionValueIsNotNull(map, "Observable.create<JSONOb…resent }.map { it.get() }");
                        return map;
                    }
                }, null, null, 3320, null));
            }
        }
    }

    /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.MegacableLiveTVService$searchProgram$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MegacableLiveTVService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\n¢\u0006\u0002\b\u000b"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/dotscreen/tv/common/bokit/LiveProgramsSearchResponse;", "kotlin.jvm.PlatformType", "<anonymous parameter 0>", "Lcom/dotscreen/bokit/model/User;", "modelExternalId", "", "channels", "", "Lcom/dotscreen/bokit/model/Channel;", "apply"}, k = 3, mv = {1, 1, 16})
    static final class C04562<T1, T2, T3, R> implements Function3<User, String, List<Channel>, Single<LiveProgramsSearchResponse>> {
        final /* synthetic */ String $query;

        C04562(String str) {
            this.$query = str;
        }

        @Override // io.reactivex.functions.Function3
        public /* bridge */ /* synthetic */ Single<LiveProgramsSearchResponse> apply(User user, String str, List<Channel> list) {
            return apply2(user, str, (List<? extends Channel>) list);
        }

        /* JADX INFO: renamed from: apply, reason: avoid collision after fix types in other method */
        public final Single<LiveProgramsSearchResponse> apply2(User user, String modelExternalId, final List<? extends Channel> channels) {
            Intrinsics.checkParameterIsNotNull(user, "<anonymous parameter 0>");
            Intrinsics.checkParameterIsNotNull(modelExternalId, "modelExternalId");
            Intrinsics.checkParameterIsNotNull(channels, "channels");
            Map mapMutableMapOf = MapsKt.mutableMapOf(TuplesKt.to("text", this.$query), TuplesKt.to("max_results", 100), TuplesKt.to("max_pr_level", 5), TuplesKt.to("fuzzy", true), TuplesKt.to("availability_type", "all"));
            if ((!Intrinsics.areEqual(modelExternalId, "DVB-C STB")) && (!Intrinsics.areEqual(modelExternalId, "IPTV STB"))) {
                mapMutableMapOf.put("device_models", modelExternalId);
            }
            return RightVRequestManager.getSearchJSONArray$default(MegacableLiveTVService.this.requestManager, "SearchLivePrograms", 0, mapMutableMapOf, 2, null).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.searchProgram.2.1
                @Override // io.reactivex.functions.Function
                public final Single<LiveProgramsSearchResponse> apply(final JSONArray results) {
                    Intrinsics.checkParameterIsNotNull(results, "results");
                    return Single.create(new SingleOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.searchProgram.2.1.1
                        /* JADX WARN: Multi-variable type inference failed */
                        /* JADX WARN: Type inference failed for: r21v0 */
                        /* JADX WARN: Type inference failed for: r3v19 */
                        /* JADX WARN: Type inference failed for: r3v2 */
                        /* JADX WARN: Type inference failed for: r3v36 */
                        /* JADX WARN: Type inference failed for: r9v0, types: [java.util.List] */
                        /* JADX WARN: Type inference failed for: r9v11 */
                        /* JADX WARN: Type inference failed for: r9v12 */
                        /* JADX WARN: Type inference failed for: r9v13 */
                        /* JADX WARN: Type inference failed for: r9v22 */
                        /* JADX WARN: Type inference failed for: r9v29 */
                        /* JADX WARN: Type inference failed for: r9v3 */
                        /* JADX WARN: Type inference failed for: r9v30 */
                        /* JADX WARN: Type inference failed for: r9v31 */
                        /* JADX WARN: Type inference failed for: r9v32, types: [java.util.List] */
                        /* JADX WARN: Type inference failed for: r9v33 */
                        /* JADX WARN: Type inference failed for: r9v34 */
                        /* JADX WARN: Type inference failed for: r9v4 */
                        @Override // io.reactivex.SingleOnSubscribe
                        public final void subscribe(SingleEmitter<LiveProgramsSearchResponse> observer) throws JSONException {
                            ?? r9;
                            int i;
                            int i2;
                            Intrinsics.checkParameterIsNotNull(observer, "observer");
                            final ArrayList arrayList = new ArrayList();
                            ArrayList arrayList2 = new ArrayList();
                            int length = results.length();
                            int i3 = 0;
                            int i4 = 0;
                            ?? r3 = arrayList2;
                            while (i4 < length) {
                                JSONObject jSONObject = results.getJSONObject(i4);
                                if (Intrinsics.areEqual(jSONObject.getString("entityType"), ToolboxAnalyticsModuleKt.KEY_CONTENT)) {
                                    String title = jSONObject.getString("name");
                                    String string = jSONObject.getString("externalChannelId");
                                    String searchId = jSONObject.getString("id");
                                    long j = jSONObject.getLong("broadcastStartTime");
                                    ?? r21 = r3;
                                    i = length;
                                    long j2 = jSONObject.getLong("broadcastEndTime");
                                    int iOptInt = jSONObject.optInt("year", i3);
                                    ArrayList arrayList3 = new ArrayList();
                                    JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("genres");
                                    int length2 = jSONArrayOptJSONArray != null ? jSONArrayOptJSONArray.length() : 0;
                                    i2 = i4;
                                    int i5 = 0;
                                    while (i5 < length2) {
                                        int i6 = length2;
                                        String strOptString = jSONArrayOptJSONArray.optJSONObject(i5).optString("name");
                                        if (strOptString != null) {
                                            arrayList3.add(strOptString);
                                        }
                                        i5++;
                                        length2 = i6;
                                    }
                                    Person[] personArr = null;
                                    Person[] personArr2 = null;
                                    String str = null;
                                    Object[] array = arrayList3.toArray(new String[0]);
                                    if (array != null) {
                                        MediaMetadata mediaMetadata = new MediaMetadata(personArr, personArr2, (String[]) array, Integer.valueOf((int) ((j2 - j) / DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS)), null, null, null, Integer.valueOf(iOptInt), null, null, null, null, null, null, null, null, 65395, null);
                                        String str2 = string + '#' + j;
                                        try {
                                            Iterator<T> it = channels.iterator();
                                            r9 = iOptInt;
                                            while (true) {
                                                try {
                                                    if (!it.hasNext()) {
                                                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                                                    }
                                                    T next = it.next();
                                                    Channel channel = (Channel) next;
                                                    if (channel != null) {
                                                        boolean zAreEqual = Intrinsics.areEqual(((RightVChannel) channel).getExtras().getChannelExternalId(), string);
                                                        if (zAreEqual) {
                                                            try {
                                                                Channel channel2 = (Channel) next;
                                                                BOService boService = MegacableLiveTVService.this.getBoService();
                                                                Intrinsics.checkExpressionValueIsNotNull(title, "title");
                                                                Date date = new Date(j);
                                                                Date date2 = new Date(j2);
                                                                Intrinsics.checkExpressionValueIsNotNull(searchId, "searchId");
                                                                JSONObject jSONObject2 = jSONObject;
                                                                long j3 = j;
                                                                try {
                                                                    final SearchTVProgram searchTVProgram = new SearchTVProgram(boService, str2, title, mediaMetadata, channel2, date, date2, null, searchId, 128, null);
                                                                    Maybe<R> map = channel2.airing(new Date(j3)).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$searchProgram$2$1$1$airing$1
                                                                        @Override // io.reactivex.functions.Function
                                                                        public final SearchTVProgram apply(TVAiring it2) {
                                                                            Intrinsics.checkParameterIsNotNull(it2, "it");
                                                                            return SearchTVProgram.INSTANCE.addArtworks(searchTVProgram, it2.getArtworks());
                                                                        }
                                                                    });
                                                                    Intrinsics.checkExpressionValueIsNotNull(map, "channel\n                …                        }");
                                                                    if (jSONObject2.getInt("isCatchup") == 1) {
                                                                        r9 = r21;
                                                                        try {
                                                                            r9.add(map);
                                                                            break;
                                                                        } catch (NoSuchElementException unused) {
                                                                            str = string;
                                                                            Log.d(MegacableLiveTVService.this.TAG, "SearchLivePrograms : " + str + " is not in the user bouquet");
                                                                        }
                                                                    } else {
                                                                        try {
                                                                            arrayList.add(map);
                                                                            r9 = r21;
                                                                            break;
                                                                        } catch (NoSuchElementException unused2) {
                                                                            str = string;
                                                                            r9 = r21;
                                                                            Log.d(MegacableLiveTVService.this.TAG, "SearchLivePrograms : " + str + " is not in the user bouquet");
                                                                        }
                                                                    }
                                                                } catch (NoSuchElementException unused3) {
                                                                    r9 = r21;
                                                                }
                                                            } catch (NoSuchElementException unused4) {
                                                                r9 = r21;
                                                                str = string;
                                                            }
                                                        } else {
                                                            str = null;
                                                            jSONObject = jSONObject;
                                                            it = it;
                                                            j = j;
                                                            r9 = zAreEqual;
                                                        }
                                                    } else {
                                                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVChannel");
                                                    }
                                                } catch (NoSuchElementException unused5) {
                                                }
                                                r9 = r21;
                                                Log.d(MegacableLiveTVService.this.TAG, "SearchLivePrograms : " + str + " is not in the user bouquet");
                                            }
                                        } catch (NoSuchElementException unused6) {
                                            str = string;
                                        }
                                    } else {
                                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                                    }
                                } else {
                                    r9 = r3;
                                    i = length;
                                    i2 = i4;
                                }
                                i4 = i2 + 1;
                                r3 = r9;
                                length = i;
                                i3 = 0;
                            }
                            final ?? r10 = r3;
                            observer.onSuccess(new LiveProgramsSearchResponse(new ItemCollection(MegacableLiveTVService.this.getBoService(), "search_catchup_" + C04562.this.$query, "", null, null, null, false, null, Single.just(Integer.valueOf(r10.size())), new Function1<ItemSortingOrder, Observable<SearchTVProgram>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$searchProgram$2$1$1$catchupCollection$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Observable<SearchTVProgram> invoke(ItemSortingOrder it2) {
                                    Intrinsics.checkParameterIsNotNull(it2, "it");
                                    Observable<SearchTVProgram> observableFlatMapMaybe = Observable.fromIterable(r10).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$searchProgram$2$1$1$catchupCollection$1.1
                                        @Override // io.reactivex.functions.Function
                                        public final Maybe<SearchTVProgram> apply(Maybe<SearchTVProgram> it3) {
                                            Intrinsics.checkParameterIsNotNull(it3, "it");
                                            return it3;
                                        }
                                    });
                                    Intrinsics.checkExpressionValueIsNotNull(observableFlatMapMaybe, "Observable.fromIterable(…hups).flatMapMaybe { it }");
                                    return observableFlatMapMaybe;
                                }
                            }, null, null, 3320, null), new ItemCollection(MegacableLiveTVService.this.getBoService(), "search_programs_" + C04562.this.$query, "", null, null, null, false, null, Single.just(Integer.valueOf(arrayList.size())), new Function1<ItemSortingOrder, Observable<SearchTVProgram>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$searchProgram$2$1$1$programCollection$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Observable<SearchTVProgram> invoke(ItemSortingOrder it2) {
                                    Intrinsics.checkParameterIsNotNull(it2, "it");
                                    Observable<SearchTVProgram> observableFlatMapMaybe = Observable.fromIterable(arrayList).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$searchProgram$2$1$1$programCollection$1.1
                                        @Override // io.reactivex.functions.Function
                                        public final Maybe<SearchTVProgram> apply(Maybe<SearchTVProgram> it3) {
                                            Intrinsics.checkParameterIsNotNull(it3, "it");
                                            return it3;
                                        }
                                    });
                                    Intrinsics.checkExpressionValueIsNotNull(observableFlatMapMaybe, "Observable.fromIterable(…rams).flatMapMaybe { it }");
                                    return observableFlatMapMaybe;
                                }
                            }, null, null, 3320, null)));
                        }
                    });
                }
            });
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r8v6, types: [com.dotscreen.tv.common.bokit.MegacableLiveTVService$scanReceiver$1] */
    public MegacableLiveTVService(Context context, BOService boService, RightVRequestManager requestManager) {
        super(boService);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        Intrinsics.checkParameterIsNotNull(requestManager, "requestManager");
        this.context = context;
        this.requestManager = requestManager;
        this.TAG = MegacableLiveTVService.class.getSimpleName();
        Single<TimeShiftingServicesConfig> map = RightVRequestManager.getRTEJSONObject$default(this.requestManager, "GetTimeShiftingServicesConfig", 0, null, null, 14, null).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$timeShiftingServicesConfig$1
            @Override // io.reactivex.functions.Function
            public final MegacableLiveTVService.TimeShiftingServicesConfig apply(JSONObject json) {
                Intrinsics.checkParameterIsNotNull(json, "json");
                return new MegacableLiveTVService.TimeShiftingServicesConfig(json.getInt("catchUpAvailabilityPeriodInHours"));
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "requestManager.getRTEJSO…InHours\")\n        )\n    }");
        this.timeShiftingServicesConfig = map;
        PublishSubject<Boolean> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(publishSubjectCreate, "PublishSubject.create<Boolean>()");
        this.lineupCacheCanceler = publishSubjectCreate;
        this.scanReceiver = new BroadcastReceiver() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$scanReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                Log.d(this.this$0.TAG, "ScanStatusReceiver: onReceive(" + intent + ')');
                if ((intent != null ? intent.getAction() : null) != null) {
                    Log.d(this.this$0.TAG, "ScanStatusReceiver: action = " + intent.getAction());
                    Log.d(this.this$0.TAG, "ScanStatusReceiver: status = " + intent.getStringExtra(CommonUtils.BUNDLE_KEY_SCAN_STATUS));
                    if (Intrinsics.areEqual(CommonUtils.ACTION_SCAN_STATUS_CHANGED, intent.getAction()) && Intrinsics.areEqual(intent.getStringExtra(CommonUtils.BUNDLE_KEY_SCAN_STATUS), CommonUtils.SCAN_STATUS_FINISHED)) {
                        Log.d(this.this$0.TAG, "Cache: will reset cache on scan finished");
                        if (this.this$0.getLastCacheCancelTime() > 0) {
                            this.this$0.setAdditionnalCacheCancelDelay(System.currentTimeMillis() - this.this$0.getLastCacheCancelTime());
                        }
                        this.this$0.cancelLineupCache(true);
                    }
                }
            }
        };
        this.modelExternalId = LazyKt.lazy(new MegacableLiveTVService$modelExternalId$2(this, boService));
        this.householdTerminalId = LazyKt.lazy(new MegacableLiveTVService$householdTerminalId$2(this, boService));
        this.availableLineups = LazyKt.lazy(new MegacableLiveTVService$availableLineups$2(this, boService));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: MegacableLiveTVService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\u0003HÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/dotscreen/tv/common/bokit/MegacableLiveTVService$TimeShiftingServicesConfig;", "", "catchUpAvailabilityPeriodInHours", "", "(I)V", "getCatchUpAvailabilityPeriodInHours", "()I", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    static final /* data */ class TimeShiftingServicesConfig {
        private final int catchUpAvailabilityPeriodInHours;

        public static /* synthetic */ TimeShiftingServicesConfig copy$default(TimeShiftingServicesConfig timeShiftingServicesConfig, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = timeShiftingServicesConfig.catchUpAvailabilityPeriodInHours;
            }
            return timeShiftingServicesConfig.copy(i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCatchUpAvailabilityPeriodInHours() {
            return this.catchUpAvailabilityPeriodInHours;
        }

        public final TimeShiftingServicesConfig copy(int catchUpAvailabilityPeriodInHours) {
            return new TimeShiftingServicesConfig(catchUpAvailabilityPeriodInHours);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof TimeShiftingServicesConfig) && this.catchUpAvailabilityPeriodInHours == ((TimeShiftingServicesConfig) other).catchUpAvailabilityPeriodInHours;
            }
            return true;
        }

        public int hashCode() {
            return this.catchUpAvailabilityPeriodInHours;
        }

        public String toString() {
            return "TimeShiftingServicesConfig(catchUpAvailabilityPeriodInHours=" + this.catchUpAvailabilityPeriodInHours + ")";
        }

        public TimeShiftingServicesConfig(int i) {
            this.catchUpAvailabilityPeriodInHours = i;
        }

        public final int getCatchUpAvailabilityPeriodInHours() {
            return this.catchUpAvailabilityPeriodInHours;
        }
    }

    public final Disposable getDisposableTimer() {
        return this.disposableTimer;
    }

    public final void setDisposableTimer(Disposable disposable) {
        this.disposableTimer = disposable;
    }

    public final PublishSubject<Boolean> getLineupCacheCanceler() {
        return this.lineupCacheCanceler;
    }

    public final long getLastCacheCancelTime() {
        return this.lastCacheCancelTime;
    }

    public final void setLastCacheCancelTime(long j) {
        this.lastCacheCancelTime = j;
    }

    public final long getAdditionnalCacheCancelDelay() {
        return this.additionnalCacheCancelDelay;
    }

    public final void setAdditionnalCacheCancelDelay(long j) {
        this.additionnalCacheCancelDelay = j;
    }

    public static /* synthetic */ void cancelLineupCache$default(MegacableLiveTVService megacableLiveTVService, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        megacableLiveTVService.cancelLineupCache(z);
    }

    public final void cancelLineupCache(final boolean force) {
        CommonUtils.invalidateLineupCache();
        invalidateAiringsCache();
        this.lineupCacheCanceler.onNext(true);
        this.lastCacheCancelTime = System.currentTimeMillis();
        final Calendar tomorrow = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        tomorrow.set(11, 0);
        tomorrow.set(12, 0);
        tomorrow.set(13, 1);
        tomorrow.add(6, 1);
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Intrinsics.checkExpressionValueIsNotNull(tomorrow, "tomorrow");
        long timeInMillis = tomorrow.getTimeInMillis();
        Intrinsics.checkExpressionValueIsNotNull(now, "now");
        final long timeInMillis2 = timeInMillis - now.getTimeInMillis();
        DSLog.INSTANCE.m("now = " + now);
        DSLog.INSTANCE.m("delta = " + timeInMillis2);
        Object applicationContext = this.context.getApplicationContext();
        if (!(applicationContext instanceof IForegroudStateProvider)) {
            applicationContext = null;
        }
        IForegroudStateProvider iForegroudStateProvider = (IForegroudStateProvider) applicationContext;
        if (iForegroudStateProvider != null) {
            Log.d(this.TAG, "Force: " + force);
            if (iForegroudStateProvider.getPortalIsInForeground() || force) {
                DSLog.INSTANCE.m("cancelLineupCache: Prefetching data...");
                DSLog.INSTANCE.m("cancelLineupCache: instance " + this);
                Intrinsics.checkExpressionValueIsNotNull(CommonUtils.getCurrentLineup(getBoService()).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$cancelLineupCache$1$1
                    @Override // io.reactivex.functions.Function
                    public final Observable<Channel> apply(Lineup it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return it.getItems();
                    }
                }).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$cancelLineupCache$$inlined$also$lambda$1
                    @Override // io.reactivex.functions.Function
                    public final Maybe<Channel> apply(final Channel channel) {
                        Intrinsics.checkParameterIsNotNull(channel, "channel");
                        if (timeInMillis2 < GmsVersion.VERSION_PARMESAN) {
                            Calendar tomorrow2 = tomorrow;
                            Intrinsics.checkExpressionValueIsNotNull(tomorrow2, "tomorrow");
                            Date time = tomorrow2.getTime();
                            Intrinsics.checkExpressionValueIsNotNull(time, "tomorrow.time");
                            return channel.airing(time).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$cancelLineupCache$$inlined$also$lambda$1.1
                                @Override // io.reactivex.functions.Function
                                public final Channel apply(TVAiring it) {
                                    Intrinsics.checkParameterIsNotNull(it, "it");
                                    return channel;
                                }
                            }).onErrorReturn(new Function<Throwable, Channel>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$cancelLineupCache$$inlined$also$lambda$1.2
                                @Override // io.reactivex.functions.Function
                                public final Channel apply(Throwable it) {
                                    Intrinsics.checkParameterIsNotNull(it, "it");
                                    return channel;
                                }
                            });
                        }
                        return Maybe.just(channel);
                    }
                }).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$cancelLineupCache$1$3
                    @Override // io.reactivex.functions.Function
                    public final Maybe<TVAiring> apply(Channel channel) {
                        Intrinsics.checkParameterIsNotNull(channel, "channel");
                        return channel.airing(TVAiringTimeOfDay.Now);
                    }
                }).toList().subscribe(new Consumer<List<TVAiring>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$cancelLineupCache$1$4
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(List<TVAiring> list) {
                        DSLog.INSTANCE.m("cancelLineupCache: Data prefetched!");
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$cancelLineupCache$1$5
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        DSLog.INSTANCE.m("cancelLineupCache: error " + th);
                    }
                }), "CommonUtils.getCurrentLi…                       })");
                return;
            }
            DSLog.INSTANCE.m("cancelLineupCache: App (" + this.context.getPackageName() + ") in background: not prefetching data.");
        }
    }

    public static /* synthetic */ Single getRecommendations$default(MegacableLiveTVService megacableLiveTVService, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return megacableLiveTVService.getRecommendations(str, str2, z);
    }

    public final Single<ItemCollection<TVAiring>> getRecommendations(String blend, String contentId, boolean isAdult) {
        Intrinsics.checkParameterIsNotNull(blend, "blend");
        UserService userService = getBoService().getUserService();
        if (userService == null) {
            Intrinsics.throwNpe();
        }
        Single<ItemCollection<TVAiring>> singleFlatMap = Single.zip(userService.getActiveUser().toSingle(), getModelExternalId().toSingle(), CommonUtils.getCurrentLineup(getBoService()).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.getRecommendations.1
            @Override // io.reactivex.functions.Function
            public final Observable<Channel> apply(Lineup it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getItems();
            }
        }).toList(), new AnonymousClass2(blend, contentId, isAdult)).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.getRecommendations.3
            @Override // io.reactivex.functions.Function
            public final Single<ItemCollection<TVAiring>> apply(Single<ItemCollection<TVAiring>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleFlatMap, "Single.zip(\n            …        }).flatMap { it }");
        return singleFlatMap;
    }

    @Override // com.dotscreen.bokit.services.LiveTVService
    public Maybe<Media> catchupMediaForProgram(final TVProgram program, final TVAiring airing) {
        Maybe<Media> maybeEmpty;
        Intrinsics.checkParameterIsNotNull(program, "program");
        Intrinsics.checkParameterIsNotNull(airing, "airing");
        if (airing instanceof TVRecording) {
            Maybe<Media> maybeEmpty2 = Maybe.empty();
            Intrinsics.checkExpressionValueIsNotNull(maybeEmpty2, "Maybe.empty<Media>()");
            return maybeEmpty2;
        }
        if (airing.getChannel().getCapabilities().getCatchup() && airing.isPast()) {
            maybeEmpty = program.getOffers().flatMapMaybe((Function) new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.catchupMediaForProgram.1
                @Override // io.reactivex.functions.Function
                public final Maybe<? extends Media> apply(Map<String, ? extends Object> offer) {
                    Intrinsics.checkParameterIsNotNull(offer, "offer");
                    Object obj = offer.get("rightv");
                    if (!(obj instanceof Map)) {
                        obj = null;
                    }
                    Map map = (Map) obj;
                    if (map != null && (new Date().getTime() - airing.getStartDate().getTime()) / 3600000.0d < 48) {
                        Object obj2 = map.get(NotificationsContract.COLUMN_FLAGS);
                        if (!(obj2 instanceof Map)) {
                            obj2 = null;
                        }
                        Map map2 = (Map) obj2;
                        if (Intrinsics.areEqual((Object) (map2 != null ? (Boolean) map2.get("catch_up") : null), (Object) true)) {
                            BOService boService = MegacableLiveTVService.this.getBoService();
                            Object obj3 = map.get("program_external_id");
                            if (obj3 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                            }
                            String str = (String) obj3;
                            Object obj4 = map.get("channel_external_id");
                            if (obj4 != null) {
                                return Maybe.just(new CatchupMedia(boService, str, (String) obj4, program.getTitle(), program.getSummary(), program.getArtworks(), airing));
                            }
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                        }
                    }
                    return Maybe.empty();
                }
            }).firstElement();
        } else {
            maybeEmpty = Maybe.empty();
        }
        Intrinsics.checkExpressionValueIsNotNull(maybeEmpty, "if (airing.channel.capab…edia>()\n                }");
        return maybeEmpty;
    }

    @Override // com.dotscreen.bokit.services.LiveTVService
    public Maybe<Media> startOverMediaForAiring(final TVAiring airing) {
        Maybe<Media> maybeEmpty;
        Intrinsics.checkParameterIsNotNull(airing, "airing");
        if (airing instanceof TVRecording) {
            Maybe<Media> maybeEmpty2 = Maybe.empty();
            Intrinsics.checkExpressionValueIsNotNull(maybeEmpty2, "Maybe.empty<Media>()");
            return maybeEmpty2;
        }
        if (airing.getChannel().getCapabilities().getStartover() && airing.isLive()) {
            maybeEmpty = airing.getTvProgram().flatMapObservable((Function) new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.startOverMediaForAiring.1
                @Override // io.reactivex.functions.Function
                public final Observable<Media> apply(final TVProgram program) {
                    Intrinsics.checkParameterIsNotNull(program, "program");
                    return program.getOffers().flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.startOverMediaForAiring.1.1
                        @Override // io.reactivex.functions.Function
                        public final Maybe<? extends Media> apply(Map<String, ? extends Object> offer) {
                            Intrinsics.checkParameterIsNotNull(offer, "offer");
                            Object obj = offer.get("rightv");
                            if (!(obj instanceof Map)) {
                                obj = null;
                            }
                            Map map = (Map) obj;
                            if (map != null) {
                                Object obj2 = map.get(NotificationsContract.COLUMN_FLAGS);
                                if (!(obj2 instanceof Map)) {
                                    obj2 = null;
                                }
                                Map map2 = (Map) obj2;
                                if (Intrinsics.areEqual((Object) (map2 != null ? (Boolean) map2.get("start_over") : null), (Object) true)) {
                                    BOService boService = MegacableLiveTVService.this.getBoService();
                                    Object obj3 = map.get("program_external_id");
                                    if (obj3 == null) {
                                        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                                    }
                                    String str = (String) obj3;
                                    Object obj4 = map.get("channel_external_id");
                                    if (obj4 != null) {
                                        return Maybe.just(new StartOverMedia(boService, str, (String) obj4, program.getTitle(), program.getSummary(), program.getArtworks(), airing));
                                    }
                                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                                }
                            }
                            return Maybe.empty();
                        }
                    });
                }
            }).firstElement().onErrorResumeNext(Maybe.empty());
        } else {
            maybeEmpty = Maybe.empty();
        }
        Intrinsics.checkExpressionValueIsNotNull(maybeEmpty, "if (airing.channel.capab…edia>()\n                }");
        return maybeEmpty;
    }

    @Override // com.dotscreen.bokit.services.LiveTVService
    public Single<Boolean> isAiringRecordable(PVRStorageMode mode, TVAiring airing) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(airing, "airing");
        int i = WhenMappings.$EnumSwitchMapping$0[mode.ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            Single<Boolean> singleJust = Single.just(false);
            Intrinsics.checkExpressionValueIsNotNull(singleJust, "Single.just(false)");
            return singleJust;
        }
        if (airing instanceof TVRecording) {
            Single<Boolean> singleJust2 = Single.just(false);
            Intrinsics.checkExpressionValueIsNotNull(singleJust2, "Single.just(false)");
            return singleJust2;
        }
        Single<Boolean> singleJust3 = Single.just(Boolean.valueOf(!airing.isPast() && airing.getChannel().getCapabilities().getNetworkPVR()));
        Intrinsics.checkExpressionValueIsNotNull(singleJust3, "Single.just(!airing.isPa….capabilities.networkPVR)");
        return singleJust3;
    }

    public final Single<RightVProgramInfo> getExtendedProgramInfo(String channelExternalId, String programExternalId) {
        Intrinsics.checkParameterIsNotNull(channelExternalId, "channelExternalId");
        Intrinsics.checkParameterIsNotNull(programExternalId, "programExternalId");
        Single<RightVProgramInfo> map = RightVRequestManager.getRTEJSONObject$default(this.requestManager, "GetExtendedProgram", 0, MapsKt.mapOf(new Pair("program_external_id", programExternalId), new Pair("channel_external_id", channelExternalId)), null, 10, null).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.getExtendedProgramInfo.1
            @Override // io.reactivex.functions.Function
            public final RightVProgramInfo apply(JSONObject json) {
                Intrinsics.checkParameterIsNotNull(json, "json");
                return new RightVProgramInfo(new Date(json.getLong("startDate")), new Date(json.getLong("endDate")));
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "requestManager.getRTEJSO…)\n            )\n        }");
        return map;
    }

    public final Single<LiveProgramsSearchResponse> searchProgram(String query) {
        Intrinsics.checkParameterIsNotNull(query, "query");
        UserService userService = getBoService().getUserService();
        if (userService == null) {
            Intrinsics.throwNpe();
        }
        Single<LiveProgramsSearchResponse> singleFlatMap = Single.zip(userService.getActiveUser().toSingle(), getModelExternalId().toSingle(), CommonUtils.getCurrentLineup(getBoService()).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.searchProgram.1
            @Override // io.reactivex.functions.Function
            public final Observable<Channel> apply(Lineup it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getItems();
            }
        }).toList(), new C04562(query)).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.searchProgram.3
            @Override // io.reactivex.functions.Function
            public final Single<LiveProgramsSearchResponse> apply(Single<LiveProgramsSearchResponse> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleFlatMap, "Single.zip(\n            …\n        ).flatMap { it }");
        return singleFlatMap;
    }

    @Override // com.dotscreen.bokit.services.LiveTVService
    public ParentalRating parentalRatingForName(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (StringsKt.startsWith$default(name, "RATING:", false, 2, (Object) null)) {
            String[] stringArray = this.context.getResources().getStringArray(R.array.parental_rating_array);
            List listSplit$default = StringsKt.split$default((CharSequence) name, new String[]{":"}, false, 0, 6, (Object) null);
            int i = listSplit$default.size() > 1 ? Integer.parseInt((String) listSplit$default.get(1)) : 0;
            BOService boService = getBoService();
            String str = stringArray[i];
            Intrinsics.checkExpressionValueIsNotNull(str, "parentalLevelList[level]");
            String str2 = stringArray[i];
            Intrinsics.checkExpressionValueIsNotNull(str2, "parentalLevelList[level]");
            UserService userService = getBoService().getUserService();
            return new ParentalRating(boService, str, str2, i, userService != null ? userService.isParentalRatingLevelAdult(i) : false);
        }
        return super.parentalRatingForName(name);
    }

    public static /* synthetic */ Completable registerLiveEvent$default(MegacableLiveTVService megacableLiveTVService, Channel channel, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "zap";
        }
        return megacableLiveTVService.registerLiveEvent(channel, str);
    }

    public final Completable registerLiveEvent(Channel channel, final String type) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Maybe map = channel.airing(TVAiringTimeOfDay.Now).flatMap(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$registerLiveEvent$itemIdMaybe$1
            @Override // io.reactivex.functions.Function
            public final Maybe<TVProgram> apply(TVAiring it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getTvProgram();
            }
        }).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$registerLiveEvent$itemIdMaybe$2
            @Override // io.reactivex.functions.Function
            public final String apply(TVProgram program) {
                Intrinsics.checkParameterIsNotNull(program, "program");
                return program.getId();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "channel.airing(TVAiringT…{ program -> program.id }");
        Maybe maybe = map;
        LiveTVService liveTVService = getBoService().getLiveTVService();
        if (liveTVService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
        }
        Completable completableFlatMapCompletable = Maybe.zip(maybe, ((MegacableLiveTVService) liveTVService).getModelExternalId(), new BiFunction<String, String, Single<JSONObject>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.registerLiveEvent.1
            @Override // io.reactivex.functions.BiFunction
            public final Single<JSONObject> apply(String itemId, String modelExternalId) {
                Intrinsics.checkParameterIsNotNull(itemId, "itemId");
                Intrinsics.checkParameterIsNotNull(modelExternalId, "modelExternalId");
                Map mapMutableMapOf = MapsKt.mutableMapOf(TuplesKt.to("type", type), TuplesKt.to("device_model", modelExternalId));
                mapMutableMapOf.put("program_id", itemId);
                return RightVRequestManager.getCompassJSONObject$default(MegacableLiveTVService.this.requestManager, "RegisterEvent", 0, mapMutableMapOf, 2, null).doOnError(new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.registerLiveEvent.1.1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        Log.d("CompassAnalytics", "RegisterEvent Failed " + th.getMessage());
                    }
                });
            }
        }).flatMapCompletable(new Function<Single<JSONObject>, CompletableSource>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.registerLiveEvent.2
            @Override // io.reactivex.functions.Function
            public final Completable apply(Single<JSONObject> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.ignoreElement();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(completableFlatMapCompletable, "Maybe.zip(itemIdMaybe, (…le { it.ignoreElement() }");
        return completableFlatMapCompletable;
    }

    public final Completable registerPlayProgramEvent(final TVProgram program) {
        Intrinsics.checkParameterIsNotNull(program, "program");
        Completable completableIgnoreElement = getModelExternalId().flatMapSingle((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.registerPlayProgramEvent.1
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(String modelExternalId) {
                Intrinsics.checkParameterIsNotNull(modelExternalId, "modelExternalId");
                return RightVRequestManager.getCompassJSONObject$default(MegacableLiveTVService.this.requestManager, "RegisterEvent", 0, MapsKt.mutableMapOf(TuplesKt.to("type", "play"), TuplesKt.to("program_id", program.getId()), TuplesKt.to("device_model", modelExternalId)), 2, null).doOnError(new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.registerPlayProgramEvent.1.1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        Log.d("CompassAnalytics", "RegisterEvent Failed " + th.getMessage());
                    }
                });
            }
        }).ignoreElement();
        Intrinsics.checkExpressionValueIsNotNull(completableIgnoreElement, "modelExternalId.flatMapS…        }.ignoreElement()");
        return completableIgnoreElement;
    }

    public final Completable registerPlayProgramEvent(final String programId) {
        Intrinsics.checkParameterIsNotNull(programId, "programId");
        Completable completableIgnoreElement = getModelExternalId().flatMapSingle((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.registerPlayProgramEvent.2
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(String modelExternalId) {
                Intrinsics.checkParameterIsNotNull(modelExternalId, "modelExternalId");
                return RightVRequestManager.getCompassJSONObject$default(MegacableLiveTVService.this.requestManager, "RegisterEvent", 0, MapsKt.mutableMapOf(TuplesKt.to("type", "play"), TuplesKt.to("program_id", programId), TuplesKt.to("device_model", modelExternalId)), 2, null).doOnError(new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.registerPlayProgramEvent.2.1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        Log.d("CompassAnalytics", "RegisterEvent Failed " + th.getMessage());
                    }
                });
            }
        }).ignoreElement();
        Intrinsics.checkExpressionValueIsNotNull(completableIgnoreElement, "modelExternalId.flatMapS…        }.ignoreElement()");
        return completableIgnoreElement;
    }

    public final TvContentRating toTvContentRating(ParentalRating parentalLevel) {
        Intrinsics.checkParameterIsNotNull(parentalLevel, "parentalLevel");
        if (Build.VERSION.SDK_INT < 21) {
            return null;
        }
        switch (parentalLevel.getLevel()) {
            case 0:
                return TvContentRating.createRating(this.context.getPackageName(), "MX_TV", "NR", new String[0]);
            case 1:
                return TvContentRating.createRating(this.context.getPackageName(), "MX_TV", "AA", new String[0]);
            case 2:
                return TvContentRating.createRating(this.context.getPackageName(), "MX_TV", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, new String[0]);
            case 3:
                return TvContentRating.createRating(this.context.getPackageName(), "MX_TV", "B", new String[0]);
            case 4:
                return TvContentRating.createRating(this.context.getPackageName(), "MX_TV", "B-15", new String[0]);
            case 5:
                return TvContentRating.createRating(this.context.getPackageName(), "MX_TV", "C", new String[0]);
            case 6:
                return TvContentRating.createRating(this.context.getPackageName(), "MX_TV", "D", new String[0]);
            default:
                return TvContentRating.createRating(this.context.getPackageName(), "MX_TV", "NR", new String[0]);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0025, code lost:
    
        if (r5.equals("D") != false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002e, code lost:
    
        if (r5.equals("C") != false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0037, code lost:
    
        if (r5.equals("B") != false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0040, code lost:
    
        if (r5.equals(androidx.exifinterface.media.ExifInterface.GPS_MEASUREMENT_IN_PROGRESS) != false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0049, code lost:
    
        if (r5.equals("B-15") != false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0052, code lost:
    
        if (r5.equals("AA") != false) goto L27;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.media.tv.TvContentRating tvContentRatingFromString(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r0 = "rating"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            if (r0 < r1) goto L70
            int r0 = r5.hashCode()
            r1 = 2080(0x820, float:2.915E-42)
            r2 = 0
            java.lang.String r3 = "MX_TV"
            if (r0 == r1) goto L4c
            r1 = 2011023(0x1eaf8f, float:2.818043E-39)
            if (r0 == r1) goto L43
            switch(r0) {
                case 65: goto L3a;
                case 66: goto L31;
                case 67: goto L28;
                case 68: goto L1f;
                default: goto L1e;
            }
        L1e:
            goto L61
        L1f:
            java.lang.String r0 = "D"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L61
            goto L54
        L28:
            java.lang.String r0 = "C"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L61
            goto L54
        L31:
            java.lang.String r0 = "B"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L61
            goto L54
        L3a:
            java.lang.String r0 = "A"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L61
            goto L54
        L43:
            java.lang.String r0 = "B-15"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L61
            goto L54
        L4c:
            java.lang.String r0 = "AA"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L61
        L54:
            android.content.Context r0 = r4.context
            java.lang.String r0 = r0.getPackageName()
            java.lang.String[] r1 = new java.lang.String[r2]
            android.media.tv.TvContentRating r5 = android.media.tv.TvContentRating.createRating(r0, r3, r5, r1)
            goto L6f
        L61:
            android.content.Context r5 = r4.context
            java.lang.String r5 = r5.getPackageName()
            java.lang.String[] r0 = new java.lang.String[r2]
            java.lang.String r1 = "NR"
            android.media.tv.TvContentRating r5 = android.media.tv.TvContentRating.createRating(r5, r3, r1, r0)
        L6f:
            return r5
        L70:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dotscreen.tv.common.bokit.MegacableLiveTVService.tvContentRatingFromString(java.lang.String):android.media.tv.TvContentRating");
    }

    public final void dispose() {
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Dispose timer : ");
        Disposable disposable = this.disposableTimer;
        sb.append(disposable != null ? Boolean.valueOf(disposable.isDisposed()) : null);
        Log.v(str, sb.toString());
        Disposable disposable2 = this.disposableTimer;
        if (disposable2 != null) {
            if (disposable2 != null) {
                disposable2.dispose();
            }
            this.disposableTimer = (Disposable) null;
        }
    }
}
