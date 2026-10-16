package com.dotscreen.bokit.model;

import com.dotscreen.bokit.services.PincodeType;
import com.dotscreen.bokit.services.UserService;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: User.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0016J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\tH\u0016J\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00142\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u0017H\u0016J\u000e\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/dotscreen/bokit/model/User;", "Lcom/dotscreen/bokit/model/Item;", "userService", "Lcom/dotscreen/bokit/services/UserService;", "id", "", "title", "(Lcom/dotscreen/bokit/services/UserService;Ljava/lang/String;Ljava/lang/String;)V", "checkPincode", "Lio/reactivex/Single;", "", "type", "Lcom/dotscreen/bokit/services/PincodeType;", "pinCode", "deauthenticate", "Lio/reactivex/Completable;", "orderOffer", "offer", "Lcom/dotscreen/bokit/model/Offer;", "orderedItems", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/PurchasableItem;", "parentalLevel", "Lcom/dotscreen/bokit/model/ParentalRating;", "recommendations", "Lcom/dotscreen/bokit/model/BrowsableItem;", "program", "Lcom/dotscreen/bokit/model/TVProgram;", "saveMediaPosition", "media", "Lcom/dotscreen/bokit/model/Media;", "position", "", "setParentalLevel", "level", "subscribedOffers", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class User extends Item {
    private final UserService userService;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public User(UserService userService, String id, String title) {
        super(id, title, null, false, null, 28, null);
        Intrinsics.checkNotNullParameter(userService, "userService");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        this.userService = userService;
    }

    public Completable deauthenticate() {
        return this.userService.deauthenticateUser(this);
    }

    public static /* synthetic */ Observable recommendations$default(User user, TVProgram tVProgram, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: recommendations");
        }
        if ((i & 1) != 0) {
            tVProgram = (TVProgram) null;
        }
        return user.recommendations(tVProgram);
    }

    public Observable<BrowsableItem> recommendations(TVProgram program) {
        return this.userService.recommendations(this, program);
    }

    public Single<Boolean> checkPincode(PincodeType type, String pinCode) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(pinCode, "pinCode");
        return this.userService.checkPincode(this, type, pinCode);
    }

    public Single<ParentalRating> parentalLevel() {
        return this.userService.parentalLevel(this);
    }

    public Completable setParentalLevel(ParentalRating level) {
        Intrinsics.checkNotNullParameter(level, "level");
        return this.userService.setParentalLevel(this, level);
    }

    public Completable saveMediaPosition(Media media, long position) {
        Intrinsics.checkNotNullParameter(media, "media");
        return this.userService.saveMediaPosition(this, media, position);
    }

    public Observable<Offer> subscribedOffers() {
        return this.userService.subscribedOffers(this);
    }

    public Observable<PurchasableItem> orderedItems() {
        return this.userService.orderedItems(this);
    }

    public Completable orderOffer(Offer offer) {
        Intrinsics.checkNotNullParameter(offer, "offer");
        return this.userService.orderOffer(this, offer);
    }
}
