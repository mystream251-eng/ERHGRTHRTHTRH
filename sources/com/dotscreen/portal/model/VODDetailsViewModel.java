package com.dotscreen.portal.model;

import android.content.Context;
import android.util.Base64;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.Episode;
import com.dotscreen.bokit.model.ItemSortingOrder;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.Movie;
import com.dotscreen.bokit.model.Offer;
import com.dotscreen.bokit.model.PlaybackRight;
import com.dotscreen.bokit.model.TVSeason;
import com.dotscreen.bokit.model.TVSeries;
import com.dotscreen.bokit.model.User;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.bokit.services.VODCatalogService;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.bokit.MegacableVODCatalogService;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.utils.RxViewModel;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Function5;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: VODDetailsViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0012\u0013B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0014J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/dotscreen/portal/model/VODDetailsViewModel;", "Lcom/dotscreen/tv/common/utils/RxViewModel;", "Lcom/dotscreen/portal/model/VODDetailsViewModel$Result;", "context", "Landroid/content/Context;", "contentId", "", "(Landroid/content/Context;Ljava/lang/String;)V", "getContentId", "()Ljava/lang/String;", "getContext", "()Landroid/content/Context;", "getSingle", "Lio/reactivex/Single;", "registerClick", "", "item", "Lcom/dotscreen/bokit/model/BrowsableItem;", "Factory", "Result", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class VODDetailsViewModel extends RxViewModel<Result> {
    private final String contentId;
    private final Context context;

    public VODDetailsViewModel(Context context, String contentId) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(contentId, "contentId");
        this.context = context;
        this.contentId = contentId;
    }

    public final String getContentId() {
        return this.contentId;
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX INFO: compiled from: VODDetailsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bHÆ\u0003J\t\u0010&\u001a\u00020\u000fHÆ\u0003Ja\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0013\u0010(\u001a\u00020\u000f2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020-HÖ\u0001R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018¨\u0006."}, d2 = {"Lcom/dotscreen/portal/model/VODDetailsViewModel$Result;", "", "media", "Lcom/dotscreen/bokit/model/Media;", "series", "Lcom/dotscreen/bokit/model/TVSeries;", "season", "Lcom/dotscreen/bokit/model/TVSeason;", TtmlNode.RIGHT, "Lcom/dotscreen/bokit/model/PlaybackRight;", "offers", "", "Lcom/dotscreen/bokit/model/Offer;", "trailers", "VODIsInFavorites", "", "(Lcom/dotscreen/bokit/model/Media;Lcom/dotscreen/bokit/model/TVSeries;Lcom/dotscreen/bokit/model/TVSeason;Lcom/dotscreen/bokit/model/PlaybackRight;Ljava/util/List;Ljava/util/List;Z)V", "getVODIsInFavorites", "()Z", "setVODIsInFavorites", "(Z)V", "getMedia", "()Lcom/dotscreen/bokit/model/Media;", "getOffers", "()Ljava/util/List;", "getRight", "()Lcom/dotscreen/bokit/model/PlaybackRight;", "getSeason", "()Lcom/dotscreen/bokit/model/TVSeason;", "getSeries", "()Lcom/dotscreen/bokit/model/TVSeries;", "getTrailers", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class Result {
        private boolean VODIsInFavorites;
        private final Media media;
        private final List<Offer> offers;
        private final PlaybackRight right;
        private final TVSeason season;
        private final TVSeries series;
        private final List<Media> trailers;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Result copy$default(Result result, Media media, TVSeries tVSeries, TVSeason tVSeason, PlaybackRight playbackRight, List list, List list2, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                media = result.media;
            }
            if ((i & 2) != 0) {
                tVSeries = result.series;
            }
            TVSeries tVSeries2 = tVSeries;
            if ((i & 4) != 0) {
                tVSeason = result.season;
            }
            TVSeason tVSeason2 = tVSeason;
            if ((i & 8) != 0) {
                playbackRight = result.right;
            }
            PlaybackRight playbackRight2 = playbackRight;
            if ((i & 16) != 0) {
                list = result.offers;
            }
            List list3 = list;
            if ((i & 32) != 0) {
                list2 = result.trailers;
            }
            List list4 = list2;
            if ((i & 64) != 0) {
                z = result.VODIsInFavorites;
            }
            return result.copy(media, tVSeries2, tVSeason2, playbackRight2, list3, list4, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Media getMedia() {
            return this.media;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final TVSeries getSeries() {
            return this.series;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final TVSeason getSeason() {
            return this.season;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final PlaybackRight getRight() {
            return this.right;
        }

        public final List<Offer> component5() {
            return this.offers;
        }

        public final List<Media> component6() {
            return this.trailers;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getVODIsInFavorites() {
            return this.VODIsInFavorites;
        }

        public final Result copy(Media media, TVSeries series, TVSeason season, PlaybackRight right, List<? extends Offer> offers, List<? extends Media> trailers, boolean VODIsInFavorites) {
            Intrinsics.checkParameterIsNotNull(media, "media");
            Intrinsics.checkParameterIsNotNull(offers, "offers");
            Intrinsics.checkParameterIsNotNull(trailers, "trailers");
            return new Result(media, series, season, right, offers, trailers, VODIsInFavorites);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return Intrinsics.areEqual(this.media, result.media) && Intrinsics.areEqual(this.series, result.series) && Intrinsics.areEqual(this.season, result.season) && Intrinsics.areEqual(this.right, result.right) && Intrinsics.areEqual(this.offers, result.offers) && Intrinsics.areEqual(this.trailers, result.trailers) && this.VODIsInFavorites == result.VODIsInFavorites;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v14, types: [int] */
        /* JADX WARN: Type inference failed for: r1v3, types: [int] */
        /* JADX WARN: Type inference failed for: r1v4 */
        /* JADX WARN: Type inference failed for: r1v6 */
        public int hashCode() {
            Media media = this.media;
            int iHashCode = (media != null ? media.hashCode() : 0) * 31;
            TVSeries tVSeries = this.series;
            int iHashCode2 = (iHashCode + (tVSeries != null ? tVSeries.hashCode() : 0)) * 31;
            TVSeason tVSeason = this.season;
            int iHashCode3 = (iHashCode2 + (tVSeason != null ? tVSeason.hashCode() : 0)) * 31;
            PlaybackRight playbackRight = this.right;
            int iHashCode4 = (iHashCode3 + (playbackRight != null ? playbackRight.hashCode() : 0)) * 31;
            List<Offer> list = this.offers;
            int iHashCode5 = (iHashCode4 + (list != null ? list.hashCode() : 0)) * 31;
            List<Media> list2 = this.trailers;
            int iHashCode6 = (iHashCode5 + (list2 != null ? list2.hashCode() : 0)) * 31;
            boolean z = this.VODIsInFavorites;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            return iHashCode6 + r1;
        }

        public String toString() {
            return "Result(media=" + this.media + ", series=" + this.series + ", season=" + this.season + ", right=" + this.right + ", offers=" + this.offers + ", trailers=" + this.trailers + ", VODIsInFavorites=" + this.VODIsInFavorites + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Result(Media media, TVSeries tVSeries, TVSeason tVSeason, PlaybackRight playbackRight, List<? extends Offer> offers, List<? extends Media> trailers, boolean z) {
            Intrinsics.checkParameterIsNotNull(media, "media");
            Intrinsics.checkParameterIsNotNull(offers, "offers");
            Intrinsics.checkParameterIsNotNull(trailers, "trailers");
            this.media = media;
            this.series = tVSeries;
            this.season = tVSeason;
            this.right = playbackRight;
            this.offers = offers;
            this.trailers = trailers;
            this.VODIsInFavorites = z;
        }

        public final Media getMedia() {
            return this.media;
        }

        public /* synthetic */ Result(Media media, TVSeries tVSeries, TVSeason tVSeason, PlaybackRight playbackRight, List list, List list2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(media, (i & 2) != 0 ? (TVSeries) null : tVSeries, (i & 4) != 0 ? (TVSeason) null : tVSeason, (i & 8) != 0 ? (PlaybackRight) null : playbackRight, (i & 16) != 0 ? CollectionsKt.emptyList() : list, (i & 32) != 0 ? CollectionsKt.emptyList() : list2, z);
        }

        public final TVSeries getSeries() {
            return this.series;
        }

        public final TVSeason getSeason() {
            return this.season;
        }

        public final PlaybackRight getRight() {
            return this.right;
        }

        public final List<Offer> getOffers() {
            return this.offers;
        }

        public final List<Media> getTrailers() {
            return this.trailers;
        }

        public final boolean getVODIsInFavorites() {
            return this.VODIsInFavorites;
        }

        public final void setVODIsInFavorites(boolean z) {
            this.VODIsInFavorites = z;
        }
    }

    /* JADX INFO: compiled from: VODDetailsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J'\u0010\u0007\u001a\u0002H\b\"\n\b\u0000\u0010\b*\u0004\u0018\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0016¢\u0006\u0002\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/model/VODDetailsViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "context", "Landroid/content/Context;", "contentId", "", "(Landroid/content/Context;Ljava/lang/String;)V", "create", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Factory implements ViewModelProvider.Factory {
        private final String contentId;
        private final Context context;

        public Factory(Context context, String contentId) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(contentId, "contentId");
            this.context = context;
            this.contentId = contentId;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> modelClass) {
            Intrinsics.checkParameterIsNotNull(modelClass, "modelClass");
            return new VODDetailsViewModel(this.context, this.contentId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void registerClick(BrowsableItem item) {
        Context applicationContext = this.context.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        VODCatalogService vodCatalogService = ((PortalApplication) applicationContext).getBOService().getVodCatalogService();
        if (vodCatalogService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableVODCatalogService");
        }
        MegacableVODCatalogService.registerClick$default((MegacableVODCatalogService) vodCatalogService, item, null, null, null, 14, null).subscribe(new Action() { // from class: com.dotscreen.portal.model.VODDetailsViewModel.registerClick.1
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.model.VODDetailsViewModel.registerClick.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
            }
        });
    }

    @Override // com.dotscreen.tv.common.utils.RxViewModel
    protected Single<Result> getSingle() {
        Single singleFlatMapSingle = PortalApplication.INSTANCE.getSingleton(this.context).getBOService().getVodCatalogService().itemById(this.contentId).flatMapSingle(new AnonymousClass1());
        Intrinsics.checkExpressionValueIsNotNull(singleFlatMapSingle, "PortalApplication.getSin…      }\n                }");
        return singleFlatMapSingle;
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.model.VODDetailsViewModel$getSingle$1, reason: invalid class name */
    /* JADX INFO: compiled from: VODDetailsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/dotscreen/portal/model/VODDetailsViewModel$Result;", ToolboxAnalyticsModuleKt.KEY_CONTENT, "Lcom/dotscreen/bokit/model/BrowsableItem;", "apply"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass1<T, R> implements Function<T, SingleSource<? extends R>> {
        AnonymousClass1() {
        }

        @Override // io.reactivex.functions.Function
        public final Single<Result> apply(BrowsableItem content) throws JSONException {
            String string;
            Intrinsics.checkParameterIsNotNull(content, "content");
            if (StringsKt.startsWith$default(VODDetailsViewModel.this.getContentId(), "rightv", false, 2, (Object) null)) {
                string = (String) StringsKt.split$default((CharSequence) VODDetailsViewModel.this.getContentId(), new String[]{":"}, false, 0, 6, (Object) null).get(2);
            } else {
                byte[] bArrDecode = Base64.decode(VODDetailsViewModel.this.getContentId(), 0);
                Intrinsics.checkExpressionValueIsNotNull(bArrDecode, "Base64.decode(contentId, Base64.DEFAULT)");
                string = new JSONObject(new String(bArrDecode, Charsets.UTF_8)).getString(TvContractCompat.PreviewProgramColumns.COLUMN_CONTENT_ID);
            }
            UserService userService = PortalApplication.INSTANCE.getSingleton(VODDetailsViewModel.this.getContext()).getBOService().getUserService();
            if (userService == null) {
                Intrinsics.throwNpe();
            }
            return userService.getActiveUser().map(new Function<T, R>() { // from class: com.dotscreen.portal.model.VODDetailsViewModel.getSingle.1.1
                @Override // io.reactivex.functions.Function
                public final RightVUser apply(User it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return (RightVUser) it;
                }
            }).flatMapSingle(new AnonymousClass2(content, string));
        }

        /* JADX INFO: renamed from: com.dotscreen.portal.model.VODDetailsViewModel$getSingle$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: VODDetailsViewModel.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/dotscreen/portal/model/VODDetailsViewModel$Result;", ToolboxAnalyticsModuleKt.KEY_USER, "Lcom/dotscreen/tv/common/bokit/RightVUser;", "apply"}, k = 3, mv = {1, 1, 16})
        static final class AnonymousClass2<T, R> implements Function<T, SingleSource<? extends R>> {
            final /* synthetic */ BrowsableItem $content;
            final /* synthetic */ String $contentIdDecoded;

            AnonymousClass2(BrowsableItem browsableItem, String str) {
                this.$content = browsableItem;
                this.$contentIdDecoded = str;
            }

            @Override // io.reactivex.functions.Function
            public final Single<? extends Result> apply(final RightVUser user) {
                Observable<TVSeason> observable;
                Single<List<TVSeason>> list;
                Observable<TVSeries> observable2;
                Single<List<TVSeries>> list2;
                Intrinsics.checkParameterIsNotNull(user, "user");
                BrowsableItem browsableItem = this.$content;
                if (browsableItem instanceof Movie) {
                    Single<List<PlaybackRight>> list3 = ((Movie) browsableItem).getPlaybackRights().toList();
                    Single<List<Offer>> singleOnErrorReturn = ((Movie) this.$content).getOffers().toList().onErrorReturn(new Function<Throwable, List<Offer>>() { // from class: com.dotscreen.portal.model.VODDetailsViewModel.getSingle.1.2.1
                        @Override // io.reactivex.functions.Function
                        public final ArrayList<Offer> apply(Throwable it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return new ArrayList<>();
                        }
                    });
                    Observable<Media> trailers = ((Movie) this.$content).getMetadata().getTrailers();
                    if (trailers == null) {
                        trailers = Observable.empty();
                    }
                    Single<List<Media>> singleOnErrorReturn2 = trailers.toList().onErrorReturn(new Function<Throwable, List<Media>>() { // from class: com.dotscreen.portal.model.VODDetailsViewModel.getSingle.1.2.2
                        @Override // io.reactivex.functions.Function
                        public final ArrayList<Media> apply(Throwable it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return new ArrayList<>();
                        }
                    });
                    String contentIdDecoded = this.$contentIdDecoded;
                    Intrinsics.checkExpressionValueIsNotNull(contentIdDecoded, "contentIdDecoded");
                    Single<? extends Result> singleZip = Single.zip(list3, singleOnErrorReturn, singleOnErrorReturn2, user.isVODInFavorites(contentIdDecoded).onErrorReturn(new Function<Throwable, Boolean>() { // from class: com.dotscreen.portal.model.VODDetailsViewModel.getSingle.1.2.3
                        /* JADX INFO: renamed from: apply, reason: avoid collision after fix types in other method */
                        public final boolean apply2(Throwable it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return false;
                        }

                        @Override // io.reactivex.functions.Function
                        public /* bridge */ /* synthetic */ Boolean apply(Throwable th) {
                            return Boolean.valueOf(apply2(th));
                        }
                    }), new Function4<List<PlaybackRight>, List<Offer>, List<Media>, Boolean, Result>() { // from class: com.dotscreen.portal.model.VODDetailsViewModel.getSingle.1.2.4
                        @Override // io.reactivex.functions.Function4
                        public /* bridge */ /* synthetic */ Result apply(List<PlaybackRight> list4, List<Offer> list5, List<Media> list6, Boolean bool) {
                            return apply(list4, list5, list6, bool.booleanValue());
                        }

                        public final Result apply(List<PlaybackRight> rights, List<? extends Offer> offers, List<? extends Media> trailers2, boolean z) {
                            Intrinsics.checkParameterIsNotNull(rights, "rights");
                            Intrinsics.checkParameterIsNotNull(offers, "offers");
                            Intrinsics.checkParameterIsNotNull(trailers2, "trailers");
                            BrowsableItem content = AnonymousClass2.this.$content;
                            Intrinsics.checkExpressionValueIsNotNull(content, "content");
                            return new Result((Media) content, null, null, rights.isEmpty() ^ true ? rights.get(0) : null, offers, trailers2, z, 6, null);
                        }
                    });
                    Intrinsics.checkExpressionValueIsNotNull(singleZip, "Single.zip(\n            …                       })");
                    return singleZip;
                }
                if (browsableItem instanceof Episode) {
                    Maybe<TVSeries> series = ((Episode) browsableItem).getSeries();
                    Single<List<TVSeries>> singleOnErrorReturn3 = (series == null || (observable2 = series.toObservable()) == null || (list2 = observable2.toList()) == null) ? null : list2.onErrorReturn(new Function<Throwable, List<TVSeries>>() { // from class: com.dotscreen.portal.model.VODDetailsViewModel.getSingle.1.2.5
                        @Override // io.reactivex.functions.Function
                        public final ArrayList<TVSeries> apply(Throwable it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return new ArrayList<>();
                        }
                    });
                    Maybe<TVSeason> season = ((Episode) this.$content).getSeason();
                    Single<List<TVSeason>> singleOnErrorReturn4 = (season == null || (observable = season.toObservable()) == null || (list = observable.toList()) == null) ? null : list.onErrorReturn(new Function<Throwable, List<TVSeason>>() { // from class: com.dotscreen.portal.model.VODDetailsViewModel.getSingle.1.2.6
                        @Override // io.reactivex.functions.Function
                        public final ArrayList<TVSeason> apply(Throwable it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return new ArrayList<>();
                        }
                    });
                    Single<List<PlaybackRight>> list4 = ((Episode) this.$content).getPlaybackRights().toList();
                    Single<List<PlaybackRight>> singleOnErrorReturn5 = list4 != null ? list4.onErrorReturn(new Function<Throwable, List<PlaybackRight>>() { // from class: com.dotscreen.portal.model.VODDetailsViewModel.getSingle.1.2.7
                        @Override // io.reactivex.functions.Function
                        public final ArrayList<PlaybackRight> apply(Throwable it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return new ArrayList<>();
                        }
                    }) : null;
                    Single<List<Offer>> list5 = ((Episode) this.$content).getOffers().toList();
                    Single<List<Offer>> singleOnErrorReturn6 = list5 != null ? list5.onErrorReturn(new Function<Throwable, List<Offer>>() { // from class: com.dotscreen.portal.model.VODDetailsViewModel.getSingle.1.2.8
                        @Override // io.reactivex.functions.Function
                        public final ArrayList<Offer> apply(Throwable it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return new ArrayList<>();
                        }
                    }) : null;
                    String contentIdDecoded2 = this.$contentIdDecoded;
                    Intrinsics.checkExpressionValueIsNotNull(contentIdDecoded2, "contentIdDecoded");
                    Single<? extends Result> singleZip2 = Single.zip(singleOnErrorReturn3, singleOnErrorReturn4, singleOnErrorReturn5, singleOnErrorReturn6, user.isVODInFavorites(contentIdDecoded2).onErrorReturn(new Function<Throwable, Boolean>() { // from class: com.dotscreen.portal.model.VODDetailsViewModel.getSingle.1.2.9
                        /* JADX INFO: renamed from: apply, reason: avoid collision after fix types in other method */
                        public final boolean apply2(Throwable it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return false;
                        }

                        @Override // io.reactivex.functions.Function
                        public /* bridge */ /* synthetic */ Boolean apply(Throwable th) {
                            return Boolean.valueOf(apply2(th));
                        }
                    }), new Function5<List<TVSeries>, List<TVSeason>, List<PlaybackRight>, List<Offer>, Boolean, Result>() { // from class: com.dotscreen.portal.model.VODDetailsViewModel.getSingle.1.2.10
                        @Override // io.reactivex.functions.Function5
                        public /* bridge */ /* synthetic */ Result apply(List<TVSeries> list6, List<TVSeason> list7, List<PlaybackRight> list8, List<Offer> list9, Boolean bool) {
                            return apply(list6, list7, list8, list9, bool.booleanValue());
                        }

                        public final Result apply(List<TVSeries> series2, List<TVSeason> season2, List<PlaybackRight> rights, List<? extends Offer> offers, boolean z) {
                            Intrinsics.checkParameterIsNotNull(series2, "series");
                            Intrinsics.checkParameterIsNotNull(season2, "season");
                            Intrinsics.checkParameterIsNotNull(rights, "rights");
                            Intrinsics.checkParameterIsNotNull(offers, "offers");
                            List<TVSeason> list6 = season2;
                            if (!list6.isEmpty()) {
                                VODDetailsViewModel.this.registerClick(season2.get(0));
                            }
                            VODDetailsViewModel vODDetailsViewModel = VODDetailsViewModel.this;
                            BrowsableItem content = AnonymousClass2.this.$content;
                            Intrinsics.checkExpressionValueIsNotNull(content, "content");
                            vODDetailsViewModel.registerClick(content);
                            BrowsableItem content2 = AnonymousClass2.this.$content;
                            Intrinsics.checkExpressionValueIsNotNull(content2, "content");
                            return new Result((Media) content2, series2.isEmpty() ^ true ? series2.get(0) : null, list6.isEmpty() ^ true ? season2.get(0) : null, rights.isEmpty() ^ true ? rights.get(0) : null, offers, null, z, 32, null);
                        }
                    });
                    Intrinsics.checkExpressionValueIsNotNull(singleZip2, "Single.zip(\n            …                       })");
                    return singleZip2;
                }
                if (browsableItem instanceof TVSeries) {
                    Single<R> singleFlatMap = ((TVSeries) browsableItem).episodesSortedBy(ItemSortingOrder.Descending).firstOrError().flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.model.VODDetailsViewModel.getSingle.1.2.11
                        @Override // io.reactivex.functions.Function
                        public final Single<Result> apply(Episode episode) throws JSONException {
                            String contentIdSerieDecoded;
                            Single<List<TVSeason>> list6;
                            Intrinsics.checkParameterIsNotNull(episode, "episode");
                            if (StringsKt.startsWith$default(episode.getId(), "rightv", false, 2, (Object) null)) {
                                contentIdSerieDecoded = (String) StringsKt.split$default((CharSequence) episode.getId(), new String[]{":"}, false, 0, 6, (Object) null).get(2);
                            } else {
                                byte[] bArrDecode = Base64.decode(episode.getId(), 0);
                                Intrinsics.checkExpressionValueIsNotNull(bArrDecode, "Base64.decode(episode.id, Base64.DEFAULT)");
                                contentIdSerieDecoded = new JSONObject(new String(bArrDecode, Charsets.UTF_8)).getString(TvContractCompat.PreviewProgramColumns.COLUMN_CONTENT_ID);
                            }
                            Single<BrowsableItem> single = PortalApplication.INSTANCE.getSingleton(VODDetailsViewModel.this.getContext()).getBOService().getVodCatalogService().itemById(episode.getId()).toSingle(episode);
                            Observable<TVSeason> seasons = ((TVSeries) AnonymousClass2.this.$content).getSeasons();
                            Single<List<TVSeason>> singleOnErrorReturn7 = (seasons == null || (list6 = seasons.toList()) == null) ? null : list6.onErrorReturn(new Function<Throwable, List<TVSeason>>() { // from class: com.dotscreen.portal.model.VODDetailsViewModel.getSingle.1.2.11.1
                                @Override // io.reactivex.functions.Function
                                public final ArrayList<TVSeason> apply(Throwable it) {
                                    Intrinsics.checkParameterIsNotNull(it, "it");
                                    return new ArrayList<>();
                                }
                            });
                            Single<List<PlaybackRight>> list7 = episode.getPlaybackRights().toList();
                            Single<List<PlaybackRight>> singleOnErrorReturn8 = list7 != null ? list7.onErrorReturn(new Function<Throwable, List<PlaybackRight>>() { // from class: com.dotscreen.portal.model.VODDetailsViewModel.getSingle.1.2.11.2
                                @Override // io.reactivex.functions.Function
                                public final ArrayList<PlaybackRight> apply(Throwable it) {
                                    Intrinsics.checkParameterIsNotNull(it, "it");
                                    return new ArrayList<>();
                                }
                            }) : null;
                            Single<List<Offer>> list8 = episode.getOffers().toList();
                            Single<List<Offer>> singleOnErrorReturn9 = list8 != null ? list8.onErrorReturn(new Function<Throwable, List<Offer>>() { // from class: com.dotscreen.portal.model.VODDetailsViewModel.getSingle.1.2.11.3
                                @Override // io.reactivex.functions.Function
                                public final ArrayList<Offer> apply(Throwable it) {
                                    Intrinsics.checkParameterIsNotNull(it, "it");
                                    return new ArrayList<>();
                                }
                            }) : null;
                            RightVUser rightVUser = user;
                            Intrinsics.checkExpressionValueIsNotNull(contentIdSerieDecoded, "contentIdSerieDecoded");
                            return Single.zip(single, singleOnErrorReturn7, singleOnErrorReturn8, singleOnErrorReturn9, rightVUser.isVODInFavorites(contentIdSerieDecoded).onErrorReturn(new Function<Throwable, Boolean>() { // from class: com.dotscreen.portal.model.VODDetailsViewModel.getSingle.1.2.11.4
                                /* JADX INFO: renamed from: apply, reason: avoid collision after fix types in other method */
                                public final boolean apply2(Throwable it) {
                                    Intrinsics.checkParameterIsNotNull(it, "it");
                                    return false;
                                }

                                @Override // io.reactivex.functions.Function
                                public /* bridge */ /* synthetic */ Boolean apply(Throwable th) {
                                    return Boolean.valueOf(apply2(th));
                                }
                            }), new Function5<BrowsableItem, List<TVSeason>, List<PlaybackRight>, List<Offer>, Boolean, Result>() { // from class: com.dotscreen.portal.model.VODDetailsViewModel.getSingle.1.2.11.5
                                @Override // io.reactivex.functions.Function5
                                public /* bridge */ /* synthetic */ Result apply(BrowsableItem browsableItem2, List<TVSeason> list9, List<PlaybackRight> list10, List<Offer> list11, Boolean bool) {
                                    return apply(browsableItem2, list9, list10, list11, bool.booleanValue());
                                }

                                public final Result apply(BrowsableItem fullEpisode, List<TVSeason> seasons2, List<PlaybackRight> rights, List<? extends Offer> offers, boolean z) {
                                    Intrinsics.checkParameterIsNotNull(fullEpisode, "fullEpisode");
                                    Intrinsics.checkParameterIsNotNull(seasons2, "seasons");
                                    Intrinsics.checkParameterIsNotNull(rights, "rights");
                                    Intrinsics.checkParameterIsNotNull(offers, "offers");
                                    List<TVSeason> list9 = seasons2;
                                    if (!list9.isEmpty()) {
                                        VODDetailsViewModel.this.registerClick(seasons2.get(0));
                                    }
                                    VODDetailsViewModel.this.registerClick(fullEpisode);
                                    return new Result((Episode) fullEpisode, (TVSeries) AnonymousClass2.this.$content, list9.isEmpty() ^ true ? seasons2.get(0) : null, rights.isEmpty() ^ true ? rights.get(0) : null, offers, null, z, 32, null);
                                }
                            });
                        }
                    });
                    Intrinsics.checkExpressionValueIsNotNull(singleFlatMap, "content.episodesSortedBy…                        }");
                    return singleFlatMap;
                }
                Single<? extends Result> singleJust = Single.just(null);
                Intrinsics.checkExpressionValueIsNotNull(singleJust, "Single.just(null)");
                return singleJust;
            }
        }
    }
}
