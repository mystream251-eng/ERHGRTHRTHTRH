package com.dotscreen.bokit.model;

import com.dotscreen.bokit.internal.tools.TimeHelper;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.PVRStorageMode;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TVAiring.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0091\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0017¢\u0006\u0002\u0010\u001aJ\u0010\u00103\u001a\u00020\u00172\u0006\u00104\u001a\u00020\u000fH\u0016J\u0018\u00105\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u0016\u00106\u001a\b\u0012\u0004\u0012\u00020\u0017072\u0006\u00108\u001a\u000209H\u0016J\b\u0010:\u001a\u00020\u0005H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0010\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0011\u0010\u0018\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010&R\u0014\u0010'\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010&R\u0014\u0010(\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010&R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010&R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b+\u0010$R\u001a\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.R0\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014@PX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010.\"\u0004\b1\u00102¨\u0006;"}, d2 = {"Lcom/dotscreen/bokit/model/TVAiring;", "Lcom/dotscreen/bokit/model/BrowsableItem;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "id", "", "title", "summary", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "deepLink", TtmlNode.TAG_METADATA, "Lcom/dotscreen/bokit/model/MediaMetadata;", "startDate", "Ljava/util/Date;", "endDate", "channel", "Lcom/dotscreen/bokit/model/Channel;", "tvProgram", "Lio/reactivex/Maybe;", "Lcom/dotscreen/bokit/model/TVProgram;", "isPrimeTime", "", "isLateNight", "ratable", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/dotscreen/bokit/model/MediaMetadata;Ljava/util/Date;Ljava/util/Date;Lcom/dotscreen/bokit/model/Channel;Lio/reactivex/Maybe;ZZZ)V", "getBoService", "()Lcom/dotscreen/bokit/services/BOService;", "catchupMedia", "Lcom/dotscreen/bokit/model/Media;", "getCatchupMedia$bokit_android_release", "()Lcom/dotscreen/bokit/model/Media;", "getChannel", "()Lcom/dotscreen/bokit/model/Channel;", "getEndDate", "()Ljava/util/Date;", "isFuture", "()Z", "isLive", "isPast", "getMetadata", "()Lcom/dotscreen/bokit/model/MediaMetadata;", "getStartDate", "startOverMedia", "getStartOverMedia", "()Lio/reactivex/Maybe;", "<set-?>", "getTvProgram", "setTvProgram$bokit_android_release", "(Lio/reactivex/Maybe;)V", "isAiredAt", "date", "isAiredBetween", "isRecordable", "Lio/reactivex/Single;", "mode", "Lcom/dotscreen/bokit/services/PVRStorageMode;", "toString", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class TVAiring extends BrowsableItem {
    private final BOService boService;
    private final Media catchupMedia;
    private final Channel channel;
    private final Date endDate;
    private final boolean isLateNight;
    private final boolean isPrimeTime;
    private final MediaMetadata metadata;
    private final Date startDate;
    private Maybe<TVProgram> tvProgram;

    protected final BOService getBoService() {
        return this.boService;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TVAiring(BOService bOService, String str, String str2, String str3, List list, String str4, MediaMetadata mediaMetadata, Date date, Date date2, Channel channel, Maybe maybe, boolean z, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Maybe maybe2;
        List listEmptyList = (i & 16) != 0 ? CollectionsKt.emptyList() : list;
        String str5 = (i & 32) != 0 ? (String) null : str4;
        if ((i & 1024) != 0) {
            Maybe maybeEmpty = Maybe.empty();
            Intrinsics.checkNotNullExpressionValue(maybeEmpty, "Maybe.empty()");
            maybe2 = maybeEmpty;
        } else {
            maybe2 = maybe;
        }
        this(bOService, str, str2, str3, listEmptyList, str5, mediaMetadata, date, date2, channel, maybe2, (i & 2048) != 0 ? false : z, (i & 4096) != 0 ? false : z2, (i & 8192) != 0 ? false : z3);
    }

    public final MediaMetadata getMetadata() {
        return this.metadata;
    }

    public final Date getStartDate() {
        return this.startDate;
    }

    public final Date getEndDate() {
        return this.endDate;
    }

    public final Channel getChannel() {
        return this.channel;
    }

    /* JADX INFO: renamed from: isPrimeTime, reason: from getter */
    public final boolean getIsPrimeTime() {
        return this.isPrimeTime;
    }

    /* JADX INFO: renamed from: isLateNight, reason: from getter */
    public final boolean getIsLateNight() {
        return this.isLateNight;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TVAiring(BOService boService, String id, String title, String str, List<? extends Artwork> artworks, String str2, MediaMetadata metadata, Date startDate, Date endDate, Channel channel, Maybe<TVProgram> tvProgram, boolean z, boolean z2, boolean z3) {
        super(id, title, str, artworks, str2, z3, null, 64, null);
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artworks, "artworks");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(channel, "channel");
        Intrinsics.checkNotNullParameter(tvProgram, "tvProgram");
        this.boService = boService;
        this.metadata = metadata;
        this.startDate = startDate;
        this.endDate = endDate;
        this.channel = channel;
        this.isPrimeTime = z;
        this.isLateNight = z2;
        Maybe<TVProgram> maybeEmpty = Maybe.empty();
        Intrinsics.checkNotNullExpressionValue(maybeEmpty, "Maybe.empty()");
        this.tvProgram = maybeEmpty;
        setTvProgram$bokit_android_release(tvProgram);
    }

    /* JADX INFO: renamed from: getCatchupMedia$bokit_android_release, reason: from getter */
    public final Media getCatchupMedia() {
        return this.catchupMedia;
    }

    public Maybe<TVProgram> getTvProgram() {
        return this.tvProgram;
    }

    public void setTvProgram$bokit_android_release(Maybe<TVProgram> maybe) {
        Intrinsics.checkNotNullParameter(maybe, "<set-?>");
        this.tvProgram = maybe;
    }

    public Maybe<Media> getStartOverMedia() {
        return this.boService.getLiveTVService().startOverMediaForAiring(this);
    }

    public boolean isLive() {
        return isAiredAt(TimeHelper.INSTANCE.now());
    }

    public boolean isPast() {
        return this.endDate.before(TimeHelper.INSTANCE.now());
    }

    public boolean isFuture() {
        return this.startDate.after(TimeHelper.INSTANCE.now());
    }

    public boolean isAiredAt(Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        long time = this.startDate.getTime();
        long time2 = this.endDate.getTime();
        long time3 = date.getTime();
        return time <= time3 && time2 > time3;
    }

    public boolean isAiredBetween(Date startDate, Date endDate) {
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        return this.startDate.before(endDate) && this.endDate.after(startDate);
    }

    public Single<Boolean> isRecordable(PVRStorageMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        return this.boService.getLiveTVService().isAiringRecordable(mode, this);
    }

    @Override // com.dotscreen.bokit.model.Item
    public String toString() {
        return '[' + getClass().getSimpleName() + "]: " + getTitle() + ", " + this.channel.getZappingNumber() + '-' + this.channel.getTitle() + ", " + this.startDate + " -> " + this.endDate;
    }
}
