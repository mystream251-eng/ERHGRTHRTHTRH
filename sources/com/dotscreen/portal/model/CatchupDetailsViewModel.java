package com.dotscreen.portal.model;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.Offer;
import com.dotscreen.bokit.model.OfferType;
import com.dotscreen.bokit.model.PlaybackRight;
import com.dotscreen.bokit.model.PurchasableItem;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.bokit.MegacableUtilsKt;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.dotscreen.tv.common.bokit.RightvLiveOfferData;
import com.dotscreen.tv.common.utils.RxViewModel;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: CatchupDetailsViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\n\u000bB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/dotscreen/portal/model/CatchupDetailsViewModel;", "Lcom/dotscreen/tv/common/utils/RxViewModel;", "Lcom/dotscreen/portal/model/CatchupDetailsViewModel$Result;", "context", "Landroid/content/Context;", "contentId", "", "(Landroid/content/Context;Ljava/lang/String;)V", "getSingle", "Lio/reactivex/Single;", "Factory", "Result", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class CatchupDetailsViewModel extends RxViewModel<Result> {
    private final String contentId;
    private final Context context;

    /* JADX INFO: compiled from: CatchupDetailsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/dotscreen/portal/model/CatchupDetailsViewModel$Result;", "", "media", "Lcom/dotscreen/bokit/model/Media;", "channel", "Lcom/dotscreen/bokit/model/Channel;", TtmlNode.RIGHT, "Lcom/dotscreen/bokit/model/PlaybackRight;", "(Lcom/dotscreen/bokit/model/Media;Lcom/dotscreen/bokit/model/Channel;Lcom/dotscreen/bokit/model/PlaybackRight;)V", "getChannel", "()Lcom/dotscreen/bokit/model/Channel;", "getMedia", "()Lcom/dotscreen/bokit/model/Media;", "getRight", "()Lcom/dotscreen/bokit/model/PlaybackRight;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class Result {
        private final Channel channel;
        private final Media media;
        private final PlaybackRight right;

        public static /* synthetic */ Result copy$default(Result result, Media media, Channel channel, PlaybackRight playbackRight, int i, Object obj) {
            if ((i & 1) != 0) {
                media = result.media;
            }
            if ((i & 2) != 0) {
                channel = result.channel;
            }
            if ((i & 4) != 0) {
                playbackRight = result.right;
            }
            return result.copy(media, channel, playbackRight);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Media getMedia() {
            return this.media;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Channel getChannel() {
            return this.channel;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final PlaybackRight getRight() {
            return this.right;
        }

        public final Result copy(Media media, Channel channel, PlaybackRight right) {
            Intrinsics.checkParameterIsNotNull(media, "media");
            Intrinsics.checkParameterIsNotNull(channel, "channel");
            return new Result(media, channel, right);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return Intrinsics.areEqual(this.media, result.media) && Intrinsics.areEqual(this.channel, result.channel) && Intrinsics.areEqual(this.right, result.right);
        }

        public int hashCode() {
            Media media = this.media;
            int iHashCode = (media != null ? media.hashCode() : 0) * 31;
            Channel channel = this.channel;
            int iHashCode2 = (iHashCode + (channel != null ? channel.hashCode() : 0)) * 31;
            PlaybackRight playbackRight = this.right;
            return iHashCode2 + (playbackRight != null ? playbackRight.hashCode() : 0);
        }

        public String toString() {
            return "Result(media=" + this.media + ", channel=" + this.channel + ", right=" + this.right + ")";
        }

        public Result(Media media, Channel channel, PlaybackRight playbackRight) {
            Intrinsics.checkParameterIsNotNull(media, "media");
            Intrinsics.checkParameterIsNotNull(channel, "channel");
            this.media = media;
            this.channel = channel;
            this.right = playbackRight;
        }

        public final Media getMedia() {
            return this.media;
        }

        public final Channel getChannel() {
            return this.channel;
        }

        public final PlaybackRight getRight() {
            return this.right;
        }
    }

    public CatchupDetailsViewModel(Context context, String contentId) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(contentId, "contentId");
        this.context = context;
        this.contentId = contentId;
    }

    /* JADX INFO: compiled from: CatchupDetailsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J'\u0010\u0007\u001a\u0002H\b\"\n\b\u0000\u0010\b*\u0004\u0018\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0016¢\u0006\u0002\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/model/CatchupDetailsViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "context", "Landroid/content/Context;", "contentId", "", "(Landroid/content/Context;Ljava/lang/String;)V", "create", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
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
            return new CatchupDetailsViewModel(this.context, this.contentId);
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [T, com.dotscreen.bokit.model.Media] */
    @Override // com.dotscreen.tv.common.utils.RxViewModel
    protected Single<Result> getSingle() {
        BOService bOService = PortalApplication.INSTANCE.getSingleton(this.context).getBOService();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (Media) 0;
        Single<Result> singleDoOnError = bOService.getVodCatalogService().itemById(this.contentId).flatMapObservable((Function) new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.model.CatchupDetailsViewModel.getSingle.1
            @Override // io.reactivex.functions.Function
            public final Observable<Offer> apply(BrowsableItem content) {
                Intrinsics.checkParameterIsNotNull(content, "content");
                objectRef.element = (T) ((Media) content);
                return ((PurchasableItem) content).getOffers();
            }
        }).filter(new Predicate<Offer>() { // from class: com.dotscreen.portal.model.CatchupDetailsViewModel.getSingle.2
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Offer offer) {
                Intrinsics.checkParameterIsNotNull(offer, "offer");
                return offer.getType() == OfferType.CatchupTV;
            }
        }).map(new Function<T, R>() { // from class: com.dotscreen.portal.model.CatchupDetailsViewModel.getSingle.3
            @Override // io.reactivex.functions.Function
            public final RightvLiveOfferData apply(Offer it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return MegacableUtilsKt.extractLiveOfferData(it);
            }
        }).firstOrError().flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.model.CatchupDetailsViewModel.getSingle.4
            @Override // io.reactivex.functions.Function
            public final Single<Result> apply(RightvLiveOfferData currentOffer) {
                Intrinsics.checkParameterIsNotNull(currentOffer, "currentOffer");
                final String channelExternalId = currentOffer.getChannelExternalId();
                Single<R> singleFirstOrError = PortalApplication.INSTANCE.getSingleton(CatchupDetailsViewModel.this.context).getCurrentLineup().flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.model.CatchupDetailsViewModel.getSingle.4.1
                    @Override // io.reactivex.functions.Function
                    public final Observable<Channel> apply(Lineup it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return it.getItems();
                    }
                }).filter(new Predicate<Channel>() { // from class: com.dotscreen.portal.model.CatchupDetailsViewModel.getSingle.4.2
                    @Override // io.reactivex.functions.Predicate
                    public final boolean test(Channel channel) {
                        Intrinsics.checkParameterIsNotNull(channel, "channel");
                        return Intrinsics.areEqual(((RightVChannel) channel).getExtras().getChannelExternalId(), channelExternalId);
                    }
                }).doOnError(new Consumer<Throwable>() { // from class: com.dotscreen.portal.model.CatchupDetailsViewModel.getSingle.4.3
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        th.printStackTrace();
                    }
                }).firstOrError();
                Media media = (Media) objectRef.element;
                if (media == null) {
                    Intrinsics.throwNpe();
                }
                return Single.zip(singleFirstOrError, media.getPlaybackRights().toList(), new BiFunction<Channel, List<PlaybackRight>, Result>() { // from class: com.dotscreen.portal.model.CatchupDetailsViewModel.getSingle.4.4
                    @Override // io.reactivex.functions.BiFunction
                    public final Result apply(Channel currentChannel, List<PlaybackRight> rights) {
                        Intrinsics.checkParameterIsNotNull(currentChannel, "currentChannel");
                        Intrinsics.checkParameterIsNotNull(rights, "rights");
                        Media media2 = (Media) objectRef.element;
                        if (media2 == null) {
                            Intrinsics.throwNpe();
                        }
                        return new Result(media2, currentChannel, rights.isEmpty() ^ true ? rights.get(0) : null);
                    }
                }).doOnError(new Consumer<Throwable>() { // from class: com.dotscreen.portal.model.CatchupDetailsViewModel.getSingle.4.5
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        th.printStackTrace();
                    }
                });
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.dotscreen.portal.model.CatchupDetailsViewModel.getSingle.5
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                th.printStackTrace();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleDoOnError, "boService.vodCatalogServ…Trace()\n                }");
        return singleDoOnError;
    }
}
