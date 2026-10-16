package com.dotscreen.bokit.model;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackRight.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\tHÆ\u0003JH\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0013\u0010\u0010¨\u0006\""}, d2 = {"Lcom/dotscreen/bokit/model/PlaybackRight;", "", "start", "Ljava/util/Date;", TtmlNode.END, "rentalDuration", "", "streamDuration", "offer", "Lcom/dotscreen/bokit/model/Offer;", "(Ljava/util/Date;Ljava/util/Date;Ljava/lang/Long;Ljava/lang/Long;Lcom/dotscreen/bokit/model/Offer;)V", "getEnd", "()Ljava/util/Date;", "getOffer", "()Lcom/dotscreen/bokit/model/Offer;", "getRentalDuration", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getStart", "getStreamDuration", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/util/Date;Ljava/util/Date;Ljava/lang/Long;Ljava/lang/Long;Lcom/dotscreen/bokit/model/Offer;)Lcom/dotscreen/bokit/model/PlaybackRight;", "equals", "", "other", "hashCode", "", "toString", "", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final /* data */ class PlaybackRight {
    private final Date end;
    private final Offer offer;
    private final Long rentalDuration;
    private final Date start;
    private final Long streamDuration;

    public static /* synthetic */ PlaybackRight copy$default(PlaybackRight playbackRight, Date date, Date date2, Long l, Long l2, Offer offer, int i, Object obj) {
        if ((i & 1) != 0) {
            date = playbackRight.start;
        }
        if ((i & 2) != 0) {
            date2 = playbackRight.end;
        }
        Date date3 = date2;
        if ((i & 4) != 0) {
            l = playbackRight.rentalDuration;
        }
        Long l3 = l;
        if ((i & 8) != 0) {
            l2 = playbackRight.streamDuration;
        }
        Long l4 = l2;
        if ((i & 16) != 0) {
            offer = playbackRight.offer;
        }
        return playbackRight.copy(date, date3, l3, l4, offer);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Date getStart() {
        return this.start;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Date getEnd() {
        return this.end;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Long getRentalDuration() {
        return this.rentalDuration;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Long getStreamDuration() {
        return this.streamDuration;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Offer getOffer() {
        return this.offer;
    }

    public final PlaybackRight copy(Date start, Date end, Long rentalDuration, Long streamDuration, Offer offer) {
        Intrinsics.checkNotNullParameter(start, "start");
        return new PlaybackRight(start, end, rentalDuration, streamDuration, offer);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaybackRight)) {
            return false;
        }
        PlaybackRight playbackRight = (PlaybackRight) other;
        return Intrinsics.areEqual(this.start, playbackRight.start) && Intrinsics.areEqual(this.end, playbackRight.end) && Intrinsics.areEqual(this.rentalDuration, playbackRight.rentalDuration) && Intrinsics.areEqual(this.streamDuration, playbackRight.streamDuration) && Intrinsics.areEqual(this.offer, playbackRight.offer);
    }

    public int hashCode() {
        Date date = this.start;
        int iHashCode = (date != null ? date.hashCode() : 0) * 31;
        Date date2 = this.end;
        int iHashCode2 = (iHashCode + (date2 != null ? date2.hashCode() : 0)) * 31;
        Long l = this.rentalDuration;
        int iHashCode3 = (iHashCode2 + (l != null ? l.hashCode() : 0)) * 31;
        Long l2 = this.streamDuration;
        int iHashCode4 = (iHashCode3 + (l2 != null ? l2.hashCode() : 0)) * 31;
        Offer offer = this.offer;
        return iHashCode4 + (offer != null ? offer.hashCode() : 0);
    }

    public String toString() {
        return "PlaybackRight(start=" + this.start + ", end=" + this.end + ", rentalDuration=" + this.rentalDuration + ", streamDuration=" + this.streamDuration + ", offer=" + this.offer + ")";
    }

    public PlaybackRight(Date start, Date date, Long l, Long l2, Offer offer) {
        Intrinsics.checkNotNullParameter(start, "start");
        this.start = start;
        this.end = date;
        this.rentalDuration = l;
        this.streamDuration = l2;
        this.offer = offer;
    }

    public final Date getStart() {
        return this.start;
    }

    public /* synthetic */ PlaybackRight(Date date, Date date2, Long l, Long l2, Offer offer, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(date, (i & 2) != 0 ? (Date) null : date2, (i & 4) != 0 ? (Long) null : l, (i & 8) != 0 ? (Long) null : l2, (i & 16) != 0 ? (Offer) null : offer);
    }

    public final Date getEnd() {
        return this.end;
    }

    public final Long getRentalDuration() {
        return this.rentalDuration;
    }

    public final Long getStreamDuration() {
        return this.streamDuration;
    }

    public final Offer getOffer() {
        return this.offer;
    }
}
