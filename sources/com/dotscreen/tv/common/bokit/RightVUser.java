package com.dotscreen.tv.common.bokit;

import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.User;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u0005H\u0016J\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020\nJ\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00190\u001dH\u0016J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u001dH\u0016J\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020\n0$2\u0006\u0010\u0018\u001a\u00020\u0019J\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\n0$2\u0006\u0010\u001b\u001a\u00020\u0005J\f\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u001dJ\u001c\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010)\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\nJ\u0010\u0010*\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010+\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u0005H\u0016R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006,"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RightVUser;", "Lcom/dotscreen/bokit/model/User;", "megacableUserService", "Lcom/dotscreen/tv/common/bokit/MegacableUserService;", "id", "", "name", "compassId", "avatarUrl", "isMain", "", "parentalLevel", "Lcom/dotscreen/bokit/model/ParentalRating;", "(Lcom/dotscreen/tv/common/bokit/MegacableUserService;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLcom/dotscreen/bokit/model/ParentalRating;)V", "getAvatarUrl", "()Ljava/lang/String;", "getCompassId", "()Z", "getMegacableUserService", "()Lcom/dotscreen/tv/common/bokit/MegacableUserService;", "getParentalLevel", "()Lcom/dotscreen/bokit/model/ParentalRating;", "addChannelToFavorites", "Lio/reactivex/Completable;", "channel", "Lcom/dotscreen/tv/common/bokit/RightVChannel;", "addVODToFavorites", "externalId", "bookmarks", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/Media;", "adultBrowsing", "favoritesChannels", "favoritesVOD", "Lcom/dotscreen/bokit/model/BrowsableItem;", "isChannelInFavorites", "Lio/reactivex/Single;", "isVODInFavorites", "lastWatchedChannels", "Lcom/dotscreen/bokit/model/Channel;", "nextEpisode", "serieId", "removeChannelFromFavorites", "removeVODFromFavorites", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class RightVUser extends User {
    private final String avatarUrl;
    private final String compassId;
    private final boolean isMain;
    private final MegacableUserService megacableUserService;
    private final ParentalRating parentalLevel;

    public final MegacableUserService getMegacableUserService() {
        return this.megacableUserService;
    }

    public final String getCompassId() {
        return this.compassId;
    }

    public final String getAvatarUrl() {
        return this.avatarUrl;
    }

    public /* synthetic */ RightVUser(MegacableUserService megacableUserService, String str, String str2, String str3, String str4, boolean z, ParentalRating parentalRating, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(megacableUserService, str, str2, (i & 8) != 0 ? "" : str3, (i & 16) != 0 ? "" : str4, (i & 32) != 0 ? false : z, parentalRating);
    }

    /* JADX INFO: renamed from: isMain, reason: from getter */
    public final boolean getIsMain() {
        return this.isMain;
    }

    public final ParentalRating getParentalLevel() {
        return this.parentalLevel;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RightVUser(MegacableUserService megacableUserService, String id, String name, String compassId, String avatarUrl, boolean z, ParentalRating parentalLevel) {
        super(megacableUserService, id, name);
        Intrinsics.checkParameterIsNotNull(megacableUserService, "megacableUserService");
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(compassId, "compassId");
        Intrinsics.checkParameterIsNotNull(avatarUrl, "avatarUrl");
        Intrinsics.checkParameterIsNotNull(parentalLevel, "parentalLevel");
        this.megacableUserService = megacableUserService;
        this.compassId = compassId;
        this.avatarUrl = avatarUrl;
        this.isMain = z;
        this.parentalLevel = parentalLevel;
    }

    public Observable<RightVChannel> favoritesChannels() {
        return this.megacableUserService.favoritesChannels$common_prodRelease();
    }

    public Completable addChannelToFavorites(RightVChannel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        return this.megacableUserService.addChannelToFavorites$common_prodRelease(channel);
    }

    public Completable removeChannelFromFavorites(RightVChannel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        return this.megacableUserService.removeChannelFromFavorites$common_prodRelease(channel);
    }

    public final Single<Boolean> isChannelInFavorites(final RightVChannel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Observable<String> observableFilter = this.megacableUserService.favoritesChannelExternalIds$common_prodRelease().filter(new Predicate<String>() { // from class: com.dotscreen.tv.common.bokit.RightVUser.isChannelInFavorites.1
            @Override // io.reactivex.functions.Predicate
            public final boolean test(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return Intrinsics.areEqual(it, channel.getExtras().getChannelExternalId());
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableFilter, "megacableUserService.fav…xtras.channelExternalId }");
        Single map = observableFilter.isEmpty().map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.RightVUser.isChannelInFavorites.2
            @Override // io.reactivex.functions.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return Boolean.valueOf(apply((Boolean) obj));
            }

            public final boolean apply(Boolean it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return !it.booleanValue();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "megacableUserService.fav…lId }.isEmpty.map { !it }");
        return map;
    }

    public Observable<BrowsableItem> favoritesVOD() {
        return this.megacableUserService.favoritesVod$common_prodRelease();
    }

    public Completable addVODToFavorites(String externalId) {
        Intrinsics.checkParameterIsNotNull(externalId, "externalId");
        return this.megacableUserService.addVodToFavorites$common_prodRelease(this, externalId);
    }

    public Completable removeVODFromFavorites(String externalId) {
        Intrinsics.checkParameterIsNotNull(externalId, "externalId");
        return this.megacableUserService.removeVodFromFavorites$common_prodRelease(externalId);
    }

    public final Single<Boolean> isVODInFavorites(final String externalId) {
        Intrinsics.checkParameterIsNotNull(externalId, "externalId");
        Observable<String> observableFilter = this.megacableUserService.favoritesVodExternalIds$common_prodRelease().filter(new Predicate<String>() { // from class: com.dotscreen.tv.common.bokit.RightVUser.isVODInFavorites.1
            @Override // io.reactivex.functions.Predicate
            public final boolean test(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return Intrinsics.areEqual(it, externalId);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableFilter, "megacableUserService.fav…lter { it == externalId }");
        Single map = observableFilter.isEmpty().map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.RightVUser.isVODInFavorites.2
            @Override // io.reactivex.functions.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return Boolean.valueOf(apply((Boolean) obj));
            }

            public final boolean apply(Boolean it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return !it.booleanValue();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "megacableUserService.fav…lId }.isEmpty.map { !it }");
        return map;
    }

    public final Observable<Media> bookmarks(boolean adultBrowsing) {
        return this.megacableUserService.bookmarks(adultBrowsing);
    }

    public final Observable<Media> nextEpisode(String serieId, boolean adultBrowsing) {
        Intrinsics.checkParameterIsNotNull(serieId, "serieId");
        return this.megacableUserService.nextEpisode(serieId, adultBrowsing);
    }

    public final Observable<Channel> lastWatchedChannels() {
        return this.megacableUserService.lastWatchedChannels(this);
    }
}
