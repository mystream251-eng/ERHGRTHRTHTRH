package com.dotscreen.bokit.model;

import com.dotscreen.bokit.services.BOService;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import io.reactivex.Observable;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveStream.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\u0002\u0010\u0015R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/dotscreen/bokit/model/LiveStream;", "Lcom/dotscreen/bokit/model/Media;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "id", "", "title", "summary", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "deepLink", "channel", "Lcom/dotscreen/bokit/model/Channel;", TtmlNode.TAG_METADATA, "Lcom/dotscreen/bokit/model/MediaMetadata;", "ratable", "", "offers", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/Offer;", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/dotscreen/bokit/model/Channel;Lcom/dotscreen/bokit/model/MediaMetadata;ZLio/reactivex/Observable;)V", "getChannel", "()Lcom/dotscreen/bokit/model/Channel;", "getMetadata", "()Lcom/dotscreen/bokit/model/MediaMetadata;", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final class LiveStream extends Media {
    private final Channel channel;
    private final MediaMetadata metadata;

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ LiveStream(BOService bOService, String str, String str2, String str3, List list, String str4, Channel channel, MediaMetadata mediaMetadata, boolean z, Observable observable, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Observable observable2;
        String str5 = (i & 8) != 0 ? (String) null : str3;
        List listEmptyList = (i & 16) != 0 ? CollectionsKt.emptyList() : list;
        String str6 = (i & 32) != 0 ? (String) null : str4;
        boolean z2 = (i & 256) != 0 ? false : z;
        if ((i & 512) != 0) {
            Observable observableEmpty = Observable.empty();
            Intrinsics.checkNotNullExpressionValue(observableEmpty, "Observable.empty()");
            observable2 = observableEmpty;
        } else {
            observable2 = observable;
        }
        this(bOService, str, str2, str5, listEmptyList, str6, channel, mediaMetadata, z2, observable2);
    }

    public final Channel getChannel() {
        return this.channel;
    }

    public final MediaMetadata getMetadata() {
        return this.metadata;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveStream(BOService boService, String id, String title, String str, List<? extends Artwork> artworks, String str2, Channel channel, MediaMetadata metadata, boolean z, Observable<Offer> offers) {
        super(boService, id, title, str, artworks, str2, z, MapsKt.emptyMap(), offers);
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artworks, "artworks");
        Intrinsics.checkNotNullParameter(channel, "channel");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(offers, "offers");
        this.channel = channel;
        this.metadata = metadata;
    }
}
