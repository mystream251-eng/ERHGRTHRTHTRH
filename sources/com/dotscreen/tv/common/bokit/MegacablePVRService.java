package com.dotscreen.tv.common.bokit;

import android.content.Context;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.ArtworkFormat;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.model.MediaMetadata;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.Person;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVProgram;
import com.dotscreen.bokit.model.TVRecording;
import com.dotscreen.bokit.model.TVRecordingStatus;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.PVRService;
import com.dotscreen.bokit.services.PVRStorageInfo;
import com.dotscreen.bokit.services.PVRStorageMode;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.bokit.services.VODCatalogService;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.common.utils.notifications.NotificationsContract;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: MegacablePVRService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u00014B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001a\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u00110\nH\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J&\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011H\u0002J\u001e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001b0 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016J\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001b0&2\u0006\u0010!\u001a\u00020\"J\u0016\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001b0&2\u0006\u0010!\u001a\u00020\"H\u0016J.\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001b0\n2\u0006\u0010!\u001a\u00020\"2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020\u0013H\u0016J7\u0010-\u001a\b\u0012\u0004\u0012\u00020\u001b0\n2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u00010/H\u0016¢\u0006\u0002\u00101J\u000e\u00102\u001a\b\u0012\u0004\u0012\u00020\u000b0&H\u0016J\u0010\u00103\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u00065"}, d2 = {"Lcom/dotscreen/tv/common/bokit/MegacablePVRService;", "Lcom/dotscreen/bokit/services/PVRService;", "context", "Landroid/content/Context;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "requestManager", "Lcom/dotscreen/tv/common/bokit/RightVRequestManager;", "(Landroid/content/Context;Lcom/dotscreen/bokit/services/BOService;Lcom/dotscreen/tv/common/bokit/RightVRequestManager;)V", "storageInfoFromQuota", "Lio/reactivex/Single;", "Lcom/dotscreen/bokit/services/PVRStorageInfo;", "getStorageInfoFromQuota", "()Lio/reactivex/Single;", "storageInfoFromQuota$delegate", "Lkotlin/Lazy;", "channelsMap", "", "", "Lcom/dotscreen/bokit/model/Channel;", "convertStatus", "Lcom/dotscreen/bokit/model/TVRecordingStatus;", NotificationCompat.CATEGORY_STATUS, "", "deleteRecording", "Lio/reactivex/Completable;", "recording", "Lcom/dotscreen/bokit/model/TVRecording;", "parseRecordingTicket", "ticket", "Lorg/json/JSONObject;", "recordingForAiring", "Lio/reactivex/Maybe;", "mode", "Lcom/dotscreen/bokit/services/PVRStorageMode;", "airing", "Lcom/dotscreen/bokit/model/TVAiring;", "recordingTickets", "Lio/reactivex/Observable;", "recordings", "schedule", "startDate", "Ljava/util/Date;", "endDate", "channel", "scheduleAiring", "beforeDelay", "", "afterDelay", "(Lcom/dotscreen/bokit/services/PVRStorageMode;Lcom/dotscreen/bokit/model/TVAiring;Ljava/lang/Long;Ljava/lang/Long;)Lio/reactivex/Single;", "storageInfos", "updateRecording", "RightVPVRStorageInfo", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class MegacablePVRService extends PVRService {
    private final Context context;
    private final RightVRequestManager requestManager;

    /* JADX INFO: renamed from: storageInfoFromQuota$delegate, reason: from kotlin metadata */
    private final Lazy storageInfoFromQuota;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;
        public static final /* synthetic */ int[] $EnumSwitchMapping$6;

        static {
            int[] iArr = new int[PVRStorageMode.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[PVRStorageMode.Network.ordinal()] = 1;
            $EnumSwitchMapping$0[PVRStorageMode.HardDiskDrive.ordinal()] = 2;
            int[] iArr2 = new int[PVRStorageMode.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[PVRStorageMode.Network.ordinal()] = 1;
            $EnumSwitchMapping$1[PVRStorageMode.HardDiskDrive.ordinal()] = 2;
            int[] iArr3 = new int[PVRStorageMode.values().length];
            $EnumSwitchMapping$2 = iArr3;
            iArr3[PVRStorageMode.Network.ordinal()] = 1;
            $EnumSwitchMapping$2[PVRStorageMode.HardDiskDrive.ordinal()] = 2;
            int[] iArr4 = new int[PVRStorageMode.values().length];
            $EnumSwitchMapping$3 = iArr4;
            iArr4[PVRStorageMode.Network.ordinal()] = 1;
            $EnumSwitchMapping$3[PVRStorageMode.HardDiskDrive.ordinal()] = 2;
            int[] iArr5 = new int[PVRStorageMode.values().length];
            $EnumSwitchMapping$4 = iArr5;
            iArr5[PVRStorageMode.Network.ordinal()] = 1;
            $EnumSwitchMapping$4[PVRStorageMode.HardDiskDrive.ordinal()] = 2;
            int[] iArr6 = new int[PVRStorageMode.values().length];
            $EnumSwitchMapping$5 = iArr6;
            iArr6[PVRStorageMode.Network.ordinal()] = 1;
            $EnumSwitchMapping$5[PVRStorageMode.HardDiskDrive.ordinal()] = 2;
            int[] iArr7 = new int[PVRStorageMode.values().length];
            $EnumSwitchMapping$6 = iArr7;
            iArr7[PVRStorageMode.Network.ordinal()] = 1;
            $EnumSwitchMapping$6[PVRStorageMode.HardDiskDrive.ordinal()] = 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Single<PVRStorageInfo> getStorageInfoFromQuota() {
        return (Single) this.storageInfoFromQuota.getValue();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MegacablePVRService(Context context, BOService boService, RightVRequestManager requestManager) {
        super(boService);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        Intrinsics.checkParameterIsNotNull(requestManager, "requestManager");
        this.context = context;
        this.requestManager = requestManager;
        this.storageInfoFromQuota = LazyKt.lazy(new Function0<Single<PVRStorageInfo>>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService$storageInfoFromQuota$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Single<PVRStorageInfo> invoke() {
                return RightVRequestManager.getRTEJSONObject$default(this.this$0.requestManager, "GetNPVRQuotaStatus", 0, null, null, 14, null).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService$storageInfoFromQuota$2.1
                    @Override // io.reactivex.functions.Function
                    public final PVRStorageInfo apply(JSONObject quota) {
                        Intrinsics.checkParameterIsNotNull(quota, "quota");
                        double d = 3600;
                        return new MegacablePVRService.RightVPVRStorageInfo(PVRStorageMode.Network, "N-PVR", (long) ((quota.getDouble("subscribedQuota") - quota.getDouble("recorded")) * d), (long) (quota.getDouble("subscribedQuota") * d), (long) (quota.getDouble("scheduled") * d));
                    }
                }).cache();
            }
        });
    }

    /* JADX INFO: compiled from: MegacablePVRService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nR\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/dotscreen/tv/common/bokit/MegacablePVRService$RightVPVRStorageInfo;", "Lcom/dotscreen/bokit/services/PVRStorageInfo;", "mode", "Lcom/dotscreen/bokit/services/PVRStorageMode;", "name", "", "remainingSeconds", "", "totalSeconds", "scheduledSeconds", "(Lcom/dotscreen/bokit/services/PVRStorageMode;Ljava/lang/String;JJJ)V", "getScheduledSeconds", "()J", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class RightVPVRStorageInfo extends PVRStorageInfo {
        private final long scheduledSeconds;

        public final long getScheduledSeconds() {
            return this.scheduledSeconds;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RightVPVRStorageInfo(PVRStorageMode mode, String name, long j, long j2, long j3) {
            super(mode, name, 0L, j, 0L, j2);
            Intrinsics.checkParameterIsNotNull(mode, "mode");
            Intrinsics.checkParameterIsNotNull(name, "name");
            this.scheduledSeconds = j3;
        }
    }

    @Override // com.dotscreen.bokit.services.PVRService
    public Observable<PVRStorageInfo> storageInfos() {
        Observable<PVRStorageInfo> observable = RightVRequestManager.getRTEJSONObject$default(this.requestManager, "GetHouseholdSubscriptions", 0, null, null, 14, null).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService.storageInfos.1
            @Override // io.reactivex.functions.Function
            public final Maybe<PVRStorageInfo> apply(JSONObject response) throws JSONException {
                Intrinsics.checkParameterIsNotNull(response, "response");
                JSONArray jSONArray = response.getJSONArray("serviceSubscriptionInfoList");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    try {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        if (Intrinsics.areEqual(jSONObject.getString("responseElementType"), "ServiceSubscriptionInfo") && Intrinsics.areEqual(jSONObject.getJSONObject(NotificationCompat.CATEGORY_SERVICE).getString("model"), "NPVR")) {
                            return MegacablePVRService.this.getStorageInfoFromQuota().toMaybe().onErrorResumeNext(new Function<Throwable, MaybeSource<? extends PVRStorageInfo>>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService.storageInfos.1.1
                                @Override // io.reactivex.functions.Function
                                public final Maybe<PVRStorageInfo> apply(Throwable error) {
                                    Intrinsics.checkParameterIsNotNull(error, "error");
                                    error.printStackTrace();
                                    return Maybe.empty();
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return Maybe.empty();
            }
        }).toObservable();
        Intrinsics.checkExpressionValueIsNotNull(observable, "requestManager.getRTEJSO…         }.toObservable()");
        return observable;
    }

    private final Single<Map<String, Channel>> channelsMap() {
        Single<Map<String, Channel>> map = CommonUtils.getCurrentLineup(getBoService()).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService.channelsMap.1
            @Override // io.reactivex.functions.Function
            public final Observable<Channel> apply(Lineup it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getItems();
            }
        }).toList().map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService.channelsMap.2
            @Override // io.reactivex.functions.Function
            public final Map<String, Channel> apply(List<Channel> channels) {
                Intrinsics.checkParameterIsNotNull(channels, "channels");
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Channel channel : channels) {
                    if (channel == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVChannel");
                    }
                    linkedHashMap.put(((RightVChannel) channel).getExtras().getChannelExternalId(), channel);
                }
                return MapsKt.toMap(linkedHashMap);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "CommonUtils.getCurrentLi…toMap()\n                }");
        return map;
    }

    private final TVRecordingStatus convertStatus(int status) {
        if (status == 1) {
            return TVRecordingStatus.Pending;
        }
        if (status == 2) {
            return TVRecordingStatus.InProgress;
        }
        if (status == 3) {
            return TVRecordingStatus.Done;
        }
        if (status == 4) {
            return TVRecordingStatus.Error;
        }
        if (status == 5) {
            return TVRecordingStatus.Deleted;
        }
        return TVRecordingStatus.Idle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:44:0x0293  */
    public final TVRecording parseRecordingTicket(JSONObject ticket, Map<String, ? extends Channel> channelsMap) {
        int i;
        Map mapMapOf;
        ParentalRating parentalRating;
        Integer num;
        Integer numValueOf;
        Exception exc;
        Maybe maybeJust;
        try {
            List<Artwork> listEmptyList = CollectionsKt.emptyList();
            Integer num2 = (Integer) null;
            ParentalRating parentalRating2 = (ParentalRating) null;
            try {
                i = ticket.getInt("prLevel");
            } catch (Exception unused) {
                i = 0;
            }
            try {
                List<Artwork> list = listEmptyList;
                String str = "";
                if (ticket.has("extendedProgram")) {
                    JSONObject extendedProgram = ticket.optJSONObject("extendedProgram");
                    int i2 = extendedProgram.getInt(NotificationsContract.COLUMN_FLAGS);
                    Pair[] pairArr = new Pair[3];
                    pairArr[0] = new Pair("npvr", Boolean.valueOf((i2 & 2) == 2));
                    pairArr[1] = new Pair("start_over", Boolean.valueOf((i2 & 8) == 8));
                    pairArr[2] = new Pair("catch_up", Boolean.valueOf((i2 & 4) == 4));
                    Map mapMapOf2 = MapsKt.mapOf(pairArr);
                    BOService boService = getBoService();
                    Intrinsics.checkExpressionValueIsNotNull(extendedProgram, "extendedProgram");
                    List<Artwork> listExtractArtworks = MegacableUtilsKt.extractArtworks(boService, extendedProgram, ArtworkFormat.Still);
                    Integer numValueOf2 = Integer.valueOf(extendedProgram.optInt("year"));
                    String string = ticket.getString("programId");
                    Intrinsics.checkExpressionValueIsNotNull(string, "ticket.getString(\"programId\")");
                    BOService boService2 = getBoService();
                    String string2 = ticket.getString("prName");
                    Intrinsics.checkExpressionValueIsNotNull(string2, "ticket.getString(\"prName\")");
                    String string3 = ticket.getString("prName");
                    Intrinsics.checkExpressionValueIsNotNull(string3, "ticket.getString(\"prName\")");
                    UserService userService = getBoService().getUserService();
                    ParentalRating parentalRating3 = new ParentalRating(boService2, string2, string3, i, userService != null ? userService.isParentalRatingLevelAdult(i) : false);
                    mapMapOf = MapsKt.mapOf(new Pair("channel_external_id", ticket.getString("channelExternalId")), new Pair("program_external_id", ticket.getString("programExternalId")), new Pair(NotificationsContract.COLUMN_FLAGS, mapMapOf2));
                    list = listExtractArtworks;
                    num = numValueOf2;
                    str = string;
                    parentalRating = parentalRating3;
                } else {
                    mapMapOf = MapsKt.mapOf(new Pair("channel_external_id", ticket.getString("channelExternalId")), new Pair("program_external_id", ticket.getString("programExternalId")));
                    parentalRating = parentalRating2;
                    num = num2;
                }
                String string4 = ticket.getString("name");
                Intrinsics.checkExpressionValueIsNotNull(string4, "ticket.getString(\"name\")");
                String strOptString = ticket.optString(TvContractCompat.Channels.COLUMN_DESCRIPTION);
                String str2 = null;
                Person[] personArr = null;
                Person[] personArr2 = null;
                String[] strArr = null;
                try {
                    numValueOf = Integer.valueOf(ticket.getInt("duration") / 60000);
                } catch (Exception unused2) {
                    numValueOf = null;
                }
                Observable observableJust = Observable.just(MapsKt.mapOf(new Pair("rightv", mapMapOf)));
                Intrinsics.checkExpressionValueIsNotNull(observableJust, "Observable.just(mapOf(Pa…rightv\",rightvOfferMap)))");
                Maybe maybeJust2 = Maybe.just(new TVProgram(str, string4, strOptString, list, str2, new MediaMetadata(personArr, personArr2, strArr, numValueOf, null, null, null, num, null, null, null, null, null, null, null, parentalRating, 32631, null), null, null, null, null, null, null, observableJust, false, 12240, null));
                Intrinsics.checkExpressionValueIsNotNull(maybeJust2, "Maybe.just(\n            …          )\n            )");
                BOService boService3 = getBoService();
                String string5 = ticket.getString("otfRecordingId");
                Intrinsics.checkExpressionValueIsNotNull(string5, "ticket.getString(\"otfRecordingId\")");
                String string6 = ticket.getString("name");
                Intrinsics.checkExpressionValueIsNotNull(string6, "ticket.getString(\"name\")");
                String strOptString2 = ticket.optString(TvContractCompat.Channels.COLUMN_DESCRIPTION);
                String str3 = null;
                MediaMetadata mediaMetadata = new MediaMetadata(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, parentalRating, 32767, null);
                Date date = new Date(ticket.getLong("startDate"));
                Date date2 = new Date(ticket.getLong("endDate"));
                Channel channel = channelsMap.get(ticket.getString("channelExternalId"));
                if (channel == null) {
                    throw new IllegalStateException("Channel not found".toString());
                }
                PVRStorageMode pVRStorageMode = PVRStorageMode.Network;
                TVRecordingStatus tVRecordingStatusConvertStatus = convertStatus(ticket.getInt(NotificationCompat.CATEGORY_STATUS));
                if (ticket.has("failureReason")) {
                    String string7 = ticket.getString("failureReason");
                    Intrinsics.checkExpressionValueIsNotNull(string7, "ticket.getString(\"failureReason\")");
                    if (string7.length() > 0) {
                        exc = new Exception(ticket.getString("failureReason"));
                    } else {
                        exc = null;
                    }
                } else {
                    exc = null;
                }
                if (ticket.getInt(NotificationCompat.CATEGORY_STATUS) == 2 || ticket.getInt(NotificationCompat.CATEGORY_STATUS) == 3) {
                    BOService boService4 = getBoService();
                    String string8 = ticket.getString("otfRecordingId");
                    Intrinsics.checkExpressionValueIsNotNull(string8, "ticket.getString(\"otfRecordingId\")");
                    String string9 = ticket.getString("channelExternalId");
                    Intrinsics.checkExpressionValueIsNotNull(string9, "ticket.getString(\"channelExternalId\")");
                    String string10 = ticket.getString("name");
                    Intrinsics.checkExpressionValueIsNotNull(string10, "ticket.getString(\"name\")");
                    maybeJust = Maybe.just(new NPVRMedia(boService4, string8, string9, string10, ticket.optString(TvContractCompat.Channels.COLUMN_DESCRIPTION), list));
                } else {
                    maybeJust = Maybe.empty();
                }
                Intrinsics.checkExpressionValueIsNotNull(maybeJust, "if (ticket.getInt(\"statu…>()\n                    }");
                return new TVRecording(boService3, string5, string6, strOptString2, list, str3, mediaMetadata, date, date2, channel, pVRStorageMode, tVRecordingStatusConvertStatus, exc, maybeJust2, maybeJust, false, 32800, null);
            } catch (Exception unused3) {
                return null;
            }
        } catch (Exception unused4) {
            return null;
        }
    }

    public final Observable<TVRecording> recordingTickets(PVRStorageMode mode) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        int i = WhenMappings.$EnumSwitchMapping$0[mode.ordinal()];
        if (i == 1) {
            Observable<TVRecording> observableFlatMapObservable = Single.zip(channelsMap(), RightVRequestManager.getRTEJSONArray$default(this.requestManager, "GetBasicRecordingTicketList", 0, null, 6, null), new BiFunction<Map<String, ? extends Channel>, JSONArray, Observable<TVRecording>>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService.recordingTickets.1
                @Override // io.reactivex.functions.BiFunction
                public final Observable<TVRecording> apply(final Map<String, ? extends Channel> channelsMap, final JSONArray tickets) {
                    Intrinsics.checkParameterIsNotNull(channelsMap, "channelsMap");
                    Intrinsics.checkParameterIsNotNull(tickets, "tickets");
                    return Observable.create(new ObservableOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService.recordingTickets.1.1
                        @Override // io.reactivex.ObservableOnSubscribe
                        public final void subscribe(ObservableEmitter<TVRecording> emitter) throws JSONException {
                            Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                            int length = tickets.length();
                            for (int i2 = 0; i2 < length; i2++) {
                                MegacablePVRService megacablePVRService = MegacablePVRService.this;
                                JSONObject jSONObject = tickets.getJSONObject(i2);
                                Intrinsics.checkExpressionValueIsNotNull(jSONObject, "tickets.getJSONObject(i)");
                                Map channelsMap2 = channelsMap;
                                Intrinsics.checkExpressionValueIsNotNull(channelsMap2, "channelsMap");
                                TVRecording recordingTicket = megacablePVRService.parseRecordingTicket(jSONObject, channelsMap2);
                                if (recordingTicket != null) {
                                    emitter.onNext(recordingTicket);
                                }
                            }
                            emitter.onComplete();
                        }
                    });
                }
            }).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService.recordingTickets.2
                @Override // io.reactivex.functions.Function
                public final Observable<TVRecording> apply(Observable<TVRecording> it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it;
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(observableFlatMapObservable, "Single.zip(\n            ….flatMapObservable { it }");
            return observableFlatMapObservable;
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        Observable<TVRecording> observableEmpty = Observable.empty();
        Intrinsics.checkExpressionValueIsNotNull(observableEmpty, "Observable.empty<TVRecording>()");
        return observableEmpty;
    }

    @Override // com.dotscreen.bokit.services.PVRService
    public Observable<TVRecording> recordings(PVRStorageMode mode) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        int i = WhenMappings.$EnumSwitchMapping$1[mode.ordinal()];
        if (i == 1) {
            Observable<TVRecording> observableFlatMapObservable = Single.zip(channelsMap(), RightVRequestManager.getRTEJSONArray$default(this.requestManager, "GetRecordingTicketList", 0, null, 6, null), new BiFunction<Map<String, ? extends Channel>, JSONArray, Observable<TVRecording>>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService.recordings.1
                @Override // io.reactivex.functions.BiFunction
                public final Observable<TVRecording> apply(final Map<String, ? extends Channel> channelsMap, final JSONArray tickets) {
                    Intrinsics.checkParameterIsNotNull(channelsMap, "channelsMap");
                    Intrinsics.checkParameterIsNotNull(tickets, "tickets");
                    return Observable.create(new ObservableOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService.recordings.1.1
                        @Override // io.reactivex.ObservableOnSubscribe
                        public final void subscribe(ObservableEmitter<TVRecording> emitter) throws JSONException {
                            Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                            int length = tickets.length();
                            for (int i2 = 0; i2 < length; i2++) {
                                MegacablePVRService megacablePVRService = MegacablePVRService.this;
                                JSONObject jSONObject = tickets.getJSONObject(i2);
                                Intrinsics.checkExpressionValueIsNotNull(jSONObject, "tickets.getJSONObject(i)");
                                Map channelsMap2 = channelsMap;
                                Intrinsics.checkExpressionValueIsNotNull(channelsMap2, "channelsMap");
                                TVRecording recordingTicket = megacablePVRService.parseRecordingTicket(jSONObject, channelsMap2);
                                if (recordingTicket != null) {
                                    emitter.onNext(recordingTicket);
                                }
                            }
                            emitter.onComplete();
                        }
                    });
                }
            }).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService.recordings.2
                @Override // io.reactivex.functions.Function
                public final Observable<TVRecording> apply(Observable<TVRecording> it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it;
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(observableFlatMapObservable, "Single.zip(\n            ….flatMapObservable { it }");
            return observableFlatMapObservable;
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        Observable<TVRecording> observableEmpty = Observable.empty();
        Intrinsics.checkExpressionValueIsNotNull(observableEmpty, "Observable.empty<TVRecording>()");
        return observableEmpty;
    }

    @Override // com.dotscreen.bokit.services.PVRService
    public Maybe<TVRecording> recordingForAiring(PVRStorageMode mode, TVAiring airing) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(airing, "airing");
        int i = WhenMappings.$EnumSwitchMapping$2[mode.ordinal()];
        if (i == 1) {
            Maybe<TVRecording> maybeDoOnError = Single.zip(airing.getTvProgram().flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService.recordingForAiring.1
                @Override // io.reactivex.functions.Function
                public final Single<List<Map<String, Object>>> apply(TVProgram it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.getOffers().firstElement().toObservable().toList();
                }
            }), recordingTickets(mode).doOnNext(new Consumer<TVRecording>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService.recordingForAiring.2
                @Override // io.reactivex.functions.Consumer
                public final void accept(TVRecording tVRecording) {
                    Log.v("RecordManager", String.valueOf(tVRecording));
                }
            }).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService.recordingForAiring.3
                @Override // io.reactivex.functions.Function
                public final Maybe<Pair<TVRecording, Map<String, Object>>> apply(TVRecording recording) {
                    Intrinsics.checkParameterIsNotNull(recording, "recording");
                    return Maybe.zip(Maybe.just(recording), recording.getTvProgram().flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService.recordingForAiring.3.1
                        @Override // io.reactivex.functions.Function
                        public final Observable<Map<String, Object>> apply(TVProgram it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return it.getOffers();
                        }
                    }).firstElement(), new BiFunction<TVRecording, Map<String, ? extends Object>, Pair<? extends TVRecording, ? extends Map<String, ? extends Object>>>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService.recordingForAiring.3.2
                        @Override // io.reactivex.functions.BiFunction
                        public final Pair<TVRecording, Map<String, Object>> apply(TVRecording rec, Map<String, ? extends Object> offer) {
                            Intrinsics.checkParameterIsNotNull(rec, "rec");
                            Intrinsics.checkParameterIsNotNull(offer, "offer");
                            return new Pair<>(rec, offer);
                        }
                    });
                }
            }).toList(), new BiFunction<List<? extends Map<String, ? extends Object>>, List<? extends Pair<? extends TVRecording, ? extends Map<String, ? extends Object>>>, TVRecording>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService.recordingForAiring.4
                @Override // io.reactivex.functions.BiFunction
                public final TVRecording apply(List<? extends Map<String, ? extends Object>> airingProgramOffers, List<? extends Pair<? extends TVRecording, ? extends Map<String, ? extends Object>>> pairsRecordingOffer) {
                    Intrinsics.checkParameterIsNotNull(airingProgramOffers, "airingProgramOffers");
                    Intrinsics.checkParameterIsNotNull(pairsRecordingOffer, "pairsRecordingOffer");
                    Map<String, ? extends Object> map = airingProgramOffers.get(0);
                    Log.v("RecordManager", "before compare");
                    Iterator<T> it = pairsRecordingOffer.iterator();
                    while (it.hasNext()) {
                        Pair pair = (Pair) it.next();
                        try {
                            Object obj = map.get("rightv");
                            if (!(obj instanceof Map)) {
                                obj = null;
                            }
                            Map map2 = (Map) obj;
                            Object obj2 = map2 != null ? map2.get("channel_external_id") : null;
                            if (obj2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                            }
                            String str = (String) obj2;
                            Object obj3 = map.get("rightv");
                            if (!(obj3 instanceof Map)) {
                                obj3 = null;
                            }
                            Map map3 = (Map) obj3;
                            Object obj4 = map3 != null ? map3.get("program_external_id") : null;
                            if (obj4 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                            }
                            String str2 = (String) obj4;
                            Object obj5 = ((Map) pair.getSecond()).get("rightv");
                            if (!(obj5 instanceof Map)) {
                                obj5 = null;
                            }
                            Map map4 = (Map) obj5;
                            Object obj6 = map4 != null ? map4.get("channel_external_id") : null;
                            if (obj6 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                            }
                            String str3 = (String) obj6;
                            Object obj7 = ((Map) pair.getSecond()).get("rightv");
                            if (!(obj7 instanceof Map)) {
                                obj7 = null;
                            }
                            Map map5 = (Map) obj7;
                            Object obj8 = map5 != null ? map5.get("program_external_id") : null;
                            if (obj8 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                            }
                            boolean z = Intrinsics.areEqual(str, str3) && Intrinsics.areEqual(str2, (String) obj8);
                            if (z) {
                                return (TVRecording) pair.getFirst();
                            }
                        } catch (Exception e) {
                            Log.v("RecordManager", "compare failed : " + e.getCause());
                        }
                    }
                    throw new NoSuchElementException("Collection contains no element matching the predicate.");
                }
            }).toMaybe().doAfterSuccess(new Consumer<TVRecording>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService.recordingForAiring.5
                @Override // io.reactivex.functions.Consumer
                public final void accept(TVRecording tVRecording) {
                    Log.e("RecordManager", "Record found in the list");
                }
            }).doOnError(new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService.recordingForAiring.6
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    Log.e("RecordManager", "Record not found in the list");
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(maybeDoOnError, "Single.zip(\n            …                        }");
            return maybeDoOnError;
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        Maybe<TVRecording> maybeEmpty = Maybe.empty();
        Intrinsics.checkExpressionValueIsNotNull(maybeEmpty, "Maybe.empty<TVRecording>()");
        return maybeEmpty;
    }

    @Override // com.dotscreen.bokit.services.PVRService
    public Single<TVRecording> scheduleAiring(PVRStorageMode mode, TVAiring airing, Long beforeDelay, Long afterDelay) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(airing, "airing");
        int i = WhenMappings.$EnumSwitchMapping$3[mode.ordinal()];
        if (i == 1) {
            Single<TVRecording> singleFlatMapSingle = airing.getTvProgram().flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService.scheduleAiring.1
                @Override // io.reactivex.functions.Function
                public final Observable<Map<String, Object>> apply(TVProgram it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.getOffers();
                }
            }).firstElement().flatMapSingle(new C04662(beforeDelay, afterDelay, mode, airing));
            Intrinsics.checkExpressionValueIsNotNull(singleFlatMapSingle, "airing.tvProgram.flatMap…                        }");
            return singleFlatMapSingle;
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        Single<TVRecording> singleError = Single.error(new Exception("Unsupported mode 'HardDiskDrive'"));
        Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error<TVRecording…d mode 'HardDiskDrive'\"))");
        return singleError;
    }

    /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.MegacablePVRService$scheduleAiring$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MegacablePVRService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/dotscreen/bokit/model/TVRecording;", "kotlin.jvm.PlatformType", "offer", "", "", "", "apply"}, k = 3, mv = {1, 1, 16})
    static final class C04662<T, R> implements Function<T, SingleSource<? extends R>> {
        final /* synthetic */ Long $afterDelay;
        final /* synthetic */ TVAiring $airing;
        final /* synthetic */ Long $beforeDelay;
        final /* synthetic */ PVRStorageMode $mode;

        C04662(Long l, Long l2, PVRStorageMode pVRStorageMode, TVAiring tVAiring) {
            this.$beforeDelay = l;
            this.$afterDelay = l2;
            this.$mode = pVRStorageMode;
            this.$airing = tVAiring;
        }

        @Override // io.reactivex.functions.Function
        public final Single<TVRecording> apply(Map<String, ? extends Object> offer) {
            Intrinsics.checkParameterIsNotNull(offer, "offer");
            Object obj = offer.get("rightv");
            if (!(obj instanceof Map)) {
                obj = null;
            }
            Map map = (Map) obj;
            if (map != null) {
                Pair[] pairArr = new Pair[2];
                Object obj2 = map.get("program_external_id");
                if (obj2 == null) {
                    obj2 = "";
                }
                pairArr[0] = new Pair("program_external_id", obj2);
                Object obj3 = map.get("channel_external_id");
                pairArr[1] = new Pair("channel_external_id", obj3 != null ? obj3 : "");
                Map mapMutableMapOf = MapsKt.mutableMapOf(pairArr);
                Long l = this.$beforeDelay;
                if (l != null) {
                    mapMutableMapOf.put("lead_duration", Integer.valueOf((int) Math.ceil(l.longValue() / 60000.0d)));
                }
                Long l2 = this.$afterDelay;
                if (l2 != null) {
                    mapMutableMapOf.put("lag_duration", Integer.valueOf((int) Math.ceil(l2.longValue() / 60000.0d)));
                }
                return RightVRequestManager.getRTEJSONObject$default(MegacablePVRService.this.requestManager, "OrderRecording", 0, mapMutableMapOf, null, 10, null).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService$scheduleAiring$2$$special$$inlined$let$lambda$1
                    @Override // io.reactivex.functions.Function
                    public final Maybe<TVRecording> apply(JSONObject it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return MegacablePVRService.this.recordingForAiring(this.this$0.$mode, this.this$0.$airing);
                    }
                }).toSingle().doFinally(new Action() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService$scheduleAiring$2$$special$$inlined$let$lambda$2
                    @Override // io.reactivex.functions.Action
                    public final void run() {
                        VODCatalogService vodCatalogService = MegacablePVRService.this.getBoService().getVodCatalogService();
                        if (vodCatalogService == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableVODCatalogService");
                        }
                        MegacableVODCatalogService.registerClick$default((MegacableVODCatalogService) vodCatalogService, this.this$0.$airing, "record", null, null, 12, null).subscribe(new Action() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService$scheduleAiring$2$1$4$1
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                            }
                        }, new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.bokit.MegacablePVRService$scheduleAiring$2$1$4$2
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Throwable th) {
                            }
                        });
                    }
                });
            }
            return Single.error(new Exception("No available offer"));
        }
    }

    @Override // com.dotscreen.bokit.services.PVRService
    public Single<TVRecording> schedule(PVRStorageMode mode, Date startDate, Date endDate, Channel channel) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        int i = WhenMappings.$EnumSwitchMapping$4[mode.ordinal()];
        if (i == 1) {
            Single<TVRecording> singleError = Single.error(new NotImplementedError("RightV backend does not support 'schedule'"));
            Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error<TVRecording…not support 'schedule'\"))");
            return singleError;
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        Single<TVRecording> singleError2 = Single.error(new Exception("Unsupported mode 'HardDiskDrive'"));
        Intrinsics.checkExpressionValueIsNotNull(singleError2, "Single.error<TVRecording…d mode 'HardDiskDrive'\"))");
        return singleError2;
    }

    @Override // com.dotscreen.bokit.services.PVRService
    public Completable updateRecording(TVRecording recording) {
        Intrinsics.checkParameterIsNotNull(recording, "recording");
        int i = WhenMappings.$EnumSwitchMapping$5[recording.getStorageMode().ordinal()];
        if (i == 1) {
            Completable completableError = Completable.error(new NotImplementedError("RightV backend does not support 'updateRecording'"));
            Intrinsics.checkExpressionValueIsNotNull(completableError, "Completable.error(NotImp…port 'updateRecording'\"))");
            return completableError;
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        Completable completableComplete = Completable.complete();
        Intrinsics.checkExpressionValueIsNotNull(completableComplete, "Completable.complete()");
        return completableComplete;
    }

    @Override // com.dotscreen.bokit.services.PVRService
    public Completable deleteRecording(TVRecording recording) {
        Intrinsics.checkParameterIsNotNull(recording, "recording");
        int i = WhenMappings.$EnumSwitchMapping$6[recording.getStorageMode().ordinal()];
        if (i == 1) {
            Completable completableIgnoreElement = RightVRequestManager.getRTEJSONObject$default(this.requestManager, "DeleteRecording", 0, MapsKt.mapOf(new Pair("otf_recording_id", recording.getId())), null, 8, null).ignoreElement();
            Intrinsics.checkExpressionValueIsNotNull(completableIgnoreElement, "requestManager.getRTEJSO…ing.id))).ignoreElement()");
            return completableIgnoreElement;
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        Completable completableComplete = Completable.complete();
        Intrinsics.checkExpressionValueIsNotNull(completableComplete, "Completable.complete()");
        return completableComplete;
    }
}
