package com.dotscreen.bokit.services;

import androidx.core.app.NotificationCompat;
import com.dotscreen.bokit.NotImplementedException;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.LiveStream;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.Offer;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.PlaybackInfo;
import com.dotscreen.bokit.model.PlaybackInfoMimeType;
import com.dotscreen.bokit.model.PlaybackRight;
import com.dotscreen.bokit.model.PurchasableItem;
import com.dotscreen.bokit.model.TVProgram;
import com.dotscreen.bokit.model.User;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.google.android.exoplayer2.util.MimeTypes;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: UserService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0012\u0010\u001f\u001a\u00020 2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020#H\u0016J\u001a\u0010$\u001a\u00020 2\b\u0010\u001a\u001a\u0004\u0018\u00010\u00072\u0006\u0010%\u001a\u00020&H\u0016J\u0018\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\r2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0007H\u0016J\u0018\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u0007H\u0016J2\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\u00062\b\u0010\u001a\u001a\u0004\u0018\u00010\u00072\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/2\u0006\u00100\u001a\u00020\u0019H\u0016J*\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u00062\b\u0010\u001a\u001a\u0004\u0018\u00010\u00072\u0006\u00101\u001a\u00020(2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J&\u00102\u001a\b\u0012\u0004\u0012\u0002030\r2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00104\u001a\u0004\u0018\u000105H\u0016J\"\u00106\u001a\u00020 2\b\u0010\u001a\u001a\u0004\u0018\u00010\u00072\u0006\u0010,\u001a\u00020-2\u0006\u00107\u001a\u000208H\u0016J\u001a\u00109\u001a\u00020 2\b\u0010\u001a\u001a\u0004\u0018\u00010\u00072\u0006\u0010:\u001a\u00020\u000eH\u0016J\u0018\u0010;\u001a\b\u0012\u0004\u0012\u00020&0\r2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0007H\u0016R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001f\u0010\u0011\u001a\u0010\u0012\f\u0012\n \u0014*\u0004\u0018\u00010\u00130\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006<"}, d2 = {"Lcom/dotscreen/bokit/services/UserService;", "", "boService", "Lcom/dotscreen/bokit/services/BOService;", "(Lcom/dotscreen/bokit/services/BOService;)V", "activeUser", "Lio/reactivex/Maybe;", "Lcom/dotscreen/bokit/model/User;", "getActiveUser", "()Lio/reactivex/Maybe;", "getBoService", "()Lcom/dotscreen/bokit/services/BOService;", "parentalRatings", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/ParentalRating;", "getParentalRatings", "()Lio/reactivex/Observable;", NotificationCompat.CATEGORY_STATUS, "Lio/reactivex/subjects/BehaviorSubject;", "Lcom/dotscreen/bokit/services/UserStatus;", "kotlin.jvm.PlatformType", "getStatus", "()Lio/reactivex/subjects/BehaviorSubject;", "checkPincode", "Lio/reactivex/Single;", "", ToolboxAnalyticsModuleKt.KEY_USER, "type", "Lcom/dotscreen/bokit/services/PincodeType;", "pinCode", "", "deauthenticateUser", "Lio/reactivex/Completable;", "isParentalRatingLevelAdult", "parentalRatingLevel", "", "orderOffer", "offer", "Lcom/dotscreen/bokit/model/Offer;", "orderedItems", "Lcom/dotscreen/bokit/model/PurchasableItem;", "parentalLevel", "playbackInfo", "Lcom/dotscreen/bokit/model/PlaybackInfo;", "media", "Lcom/dotscreen/bokit/model/Media;", "playbackRight", "Lcom/dotscreen/bokit/model/PlaybackRight;", ToolboxAnalyticsModuleKt.EVENT_RESUME, "purchasable", "recommendations", "Lcom/dotscreen/bokit/model/BrowsableItem;", "program", "Lcom/dotscreen/bokit/model/TVProgram;", "saveMediaPosition", "position", "", "setParentalLevel", "rating", "subscribedOffers", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class UserService {
    private final BOService boService;
    private final BehaviorSubject<UserStatus> status;

    public UserService(BOService boService) {
        Intrinsics.checkNotNullParameter(boService, "boService");
        this.boService = boService;
        BehaviorSubject<UserStatus> behaviorSubjectCreateDefault = BehaviorSubject.createDefault(new UserStatus.Anonymous());
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault, "BehaviorSubject.createDe…>(UserStatus.Anonymous())");
        this.status = behaviorSubjectCreateDefault;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BOService getBoService() {
        return this.boService;
    }

    public final BehaviorSubject<UserStatus> getStatus() {
        return this.status;
    }

    public final Maybe<User> getActiveUser() {
        Maybe maybeFlatMap = this.status.take(1L).firstElement().flatMap(new Function<UserStatus, MaybeSource<? extends User>>() { // from class: com.dotscreen.bokit.services.UserService$activeUser$1
            @Override // io.reactivex.functions.Function
            public final MaybeSource<? extends User> apply(UserStatus status) {
                Intrinsics.checkNotNullParameter(status, "status");
                return status instanceof UserStatus.Authenticated ? Maybe.just(((UserStatus.Authenticated) status).getUser()) : Maybe.empty();
            }
        });
        Intrinsics.checkNotNullExpressionValue(maybeFlatMap, "status.take(1).firstElem…          }\n            }");
        return maybeFlatMap;
    }

    public static /* synthetic */ Observable recommendations$default(UserService userService, User user, TVProgram tVProgram, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: recommendations");
        }
        if ((i & 1) != 0) {
            user = (User) null;
        }
        if ((i & 2) != 0) {
            tVProgram = (TVProgram) null;
        }
        return userService.recommendations(user, tVProgram);
    }

    public Maybe<PlaybackInfo> playbackInfo(User user, Media media, PlaybackRight playbackRight, boolean resume) {
        Intrinsics.checkNotNullParameter(media, "media");
        if (media instanceof LiveStream) {
            LiveStream liveStream = (LiveStream) media;
            String[] staticStreams$bokit_android_release = liveStream.getChannel().getStaticStreams();
            if (staticStreams$bokit_android_release != null) {
                if (!(staticStreams$bokit_android_release.length == 0)) {
                    Maybe<PlaybackInfo> maybeJust = Maybe.just(new PlaybackInfo(0L, MapsKt.mapOf(new Pair(PlaybackInfoMimeType.DEFAULT, staticStreams$bokit_android_release[0]))));
                    Intrinsics.checkNotNullExpressionValue(maybeJust, "Maybe.just(PlaybackInfo(0, mapOf(pair)))");
                    return maybeJust;
                }
            }
            Maybe maybeFlatMapMaybe = this.boService.fetchLive(liveStream.getChannel().getId()).flatMapMaybe(new Function<JSONObject, MaybeSource<? extends PlaybackInfo>>() { // from class: com.dotscreen.bokit.services.UserService.playbackInfo.1
                /* JADX WARN: Code duplicated, block: B:18:0x0054  */
                @Override // io.reactivex.functions.Function
                public final MaybeSource<? extends PlaybackInfo> apply(JSONObject json) throws JSONException {
                    PlaybackInfoMimeType playbackInfoMimeType;
                    Intrinsics.checkNotNullParameter(json, "json");
                    JSONArray jSONArray = json.getJSONArray(MimeTypes.BASE_TYPE_VIDEO);
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        String url = jSONObject.getString("src");
                        String string = jSONObject.getString("variant");
                        if (string != null) {
                            int iHashCode = string.hashCode();
                            if (iHashCode == -1206514696) {
                                if (string.equals("multicast")) {
                                    playbackInfoMimeType = PlaybackInfoMimeType.MULTICAST;
                                } else {
                                    playbackInfoMimeType = PlaybackInfoMimeType.DEFAULT;
                                }
                            } else if (iHashCode == 3075986 && string.equals("dash")) {
                                playbackInfoMimeType = PlaybackInfoMimeType.DASH;
                            } else {
                                playbackInfoMimeType = PlaybackInfoMimeType.DEFAULT;
                            }
                        } else {
                            playbackInfoMimeType = PlaybackInfoMimeType.DEFAULT;
                        }
                        Intrinsics.checkNotNullExpressionValue(url, "url");
                        linkedHashMap.put(playbackInfoMimeType, url);
                    }
                    return Maybe.just(new PlaybackInfo(0L, linkedHashMap));
                }
            });
            Intrinsics.checkNotNullExpressionValue(maybeFlatMapMaybe, "boService.fetchLive(medi…                        }");
            return maybeFlatMapMaybe;
        }
        Maybe<PlaybackInfo> maybeError = Maybe.error(new NotImplementedException("An operation is not implemented: UserServiceInternal.playbackInfo", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(maybeError, "Maybe.error(NotImplement…t implemented: $reason\"))");
        return maybeError;
    }

    public Observable<BrowsableItem> recommendations(User user, TVProgram program) {
        Observable<BrowsableItem> observableError = Observable.error(new NotImplementedException("An operation is not implemented: UserService.recommendations", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(observableError, "Observable.error(NotImpl…t implemented: $reason\"))");
        return observableError;
    }

    public final Observable<ParentalRating> getParentalRatings() {
        Observable<ParentalRating> observableError = Observable.error(new NotImplementedException("An operation is not implemented: UserService.parentalRatings", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(observableError, "Observable.error(NotImpl…t implemented: $reason\"))");
        return observableError;
    }

    public Completable deauthenticateUser(User user) {
        Completable completableError = Completable.error(new NotImplementedException("An operation is not implemented: UserServiceInternal.deauthenticateUser", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(completableError, "Completable.error(NotImp…t implemented: $reason\"))");
        return completableError;
    }

    public Single<Boolean> checkPincode(User user, PincodeType type, String pinCode) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(pinCode, "pinCode");
        Single<Boolean> singleError = Single.error(new NotImplementedException("An operation is not implemented: UserServiceInternal.checkPincode", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(singleError, "Single.error(NotImplemen…t implemented: $reason\"))");
        return singleError;
    }

    public Single<ParentalRating> parentalLevel(User user) {
        Single<ParentalRating> singleError = Single.error(new NotImplementedException("An operation is not implemented: UserServiceInternal.parentalLevel", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(singleError, "Single.error(NotImplemen…t implemented: $reason\"))");
        return singleError;
    }

    public Completable setParentalLevel(User user, ParentalRating rating) {
        Intrinsics.checkNotNullParameter(rating, "rating");
        Completable completableError = Completable.error(new NotImplementedException("An operation is not implemented: UserServiceInternal.setParentalLevel", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(completableError, "Completable.error(NotImp…t implemented: $reason\"))");
        return completableError;
    }

    public Maybe<PlaybackRight> playbackRight(User user, PurchasableItem purchasable, Offer offer) {
        Intrinsics.checkNotNullParameter(purchasable, "purchasable");
        Maybe<PlaybackRight> maybeError = Maybe.error(new NotImplementedException("An operation is not implemented: UserServiceInternal.playbackRight", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(maybeError, "Maybe.error(NotImplement…t implemented: $reason\"))");
        return maybeError;
    }

    public Completable saveMediaPosition(User user, Media media, long position) {
        Intrinsics.checkNotNullParameter(media, "media");
        Completable completableError = Completable.error(new NotImplementedException("An operation is not implemented: UserServiceInternal.saveMediaPosition", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(completableError, "Completable.error(NotImp…t implemented: $reason\"))");
        return completableError;
    }

    public boolean isParentalRatingLevelAdult(int parentalRatingLevel) throws NotImplementedException {
        throw new NotImplementedException("An operation is not implemented: UserServiceInternal.isParentalRatingAdult", null, 2, null);
    }

    public Observable<Offer> subscribedOffers(User user) {
        Observable<Offer> observableError = Observable.error(new NotImplementedException("An operation is not implemented: UserServiceInternal.subscribedOffers", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(observableError, "Observable.error(NotImpl…t implemented: $reason\"))");
        return observableError;
    }

    public Observable<PurchasableItem> orderedItems(User user) {
        Observable<PurchasableItem> observableError = Observable.error(new NotImplementedException("An operation is not implemented: UserServiceInternal.orderedItems", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(observableError, "Observable.error(NotImpl…t implemented: $reason\"))");
        return observableError;
    }

    public Completable orderOffer(User user, Offer offer) {
        Intrinsics.checkNotNullParameter(offer, "offer");
        Completable completableError = Completable.error(new NotImplementedException("An operation is not implemented: UserServiceInternal.orderOffer", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(completableError, "Completable.error(NotImp…t implemented: $reason\"))");
        return completableError;
    }
}
