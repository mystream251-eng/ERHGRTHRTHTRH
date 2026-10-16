package com.dotscreen.portal.ui.screens.guide;

import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.TVAiring;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TableEntry.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007B'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\t¢\u0006\u0002\u0010\u0010J\u0013\u0010%\u001a\u00020\t2\b\u0010&\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u0006\u0010'\u001a\u00020(J\u000e\u0010)\u001a\u00020\t2\u0006\u0010*\u001a\u00020(J\b\u0010+\u001a\u00020\u0015H\u0016J\u0010\u0010,\u001a\u0004\u0018\u00010\u00152\u0006\u0010-\u001a\u00020\u0005R\u000e\u0010\u0011\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u000e\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\r\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001bR\u0011\u0010\u001e\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0017R\u0011\u0010\u000f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010 R\u0011\u0010!\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b!\u0010 R\u0011\u0010\"\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\"\u0010 R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u0006."}, d2 = {"Lcom/dotscreen/portal/ui/screens/guide/TableEntry;", "", "channel", "Lcom/dotscreen/bokit/model/Channel;", "startUtcMillis", "", "endUtcMillis", "(Lcom/dotscreen/bokit/model/Channel;JJ)V", "blocked", "", "(Lcom/dotscreen/bokit/model/Channel;JJZ)V", "program", "Lcom/dotscreen/bokit/model/TVAiring;", "entryStartUtcMillis", "entryEndUtcMillis", "isBlocked", "(Lcom/dotscreen/bokit/model/Channel;Lcom/dotscreen/bokit/model/TVAiring;JJZ)V", "_isGap", "getChannel", "()Lcom/dotscreen/bokit/model/Channel;", "channelId", "", "getChannelId", "()Ljava/lang/String;", "getEntryEndUtcMillis", "()J", "setEntryEndUtcMillis", "(J)V", "getEntryStartUtcMillis", "setEntryStartUtcMillis", "id", "getId", "()Z", "isCurrentProgram", "isGap", "getProgram", "()Lcom/dotscreen/bokit/model/TVAiring;", "equals", "other", "getWidth", "", "hasGenre", "genreId", "toString", "toTimeString", "timeMillis", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public class TableEntry {
    private boolean _isGap;
    private final Channel channel;
    private final String channelId;
    private long entryEndUtcMillis;
    private long entryStartUtcMillis;
    private final boolean isBlocked;
    private final TVAiring program;

    public final boolean hasGenre(int genreId) {
        return false;
    }

    public TableEntry(Channel channel, TVAiring tVAiring, long j, long j2, boolean z) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        this.channel = channel;
        this.program = tVAiring;
        this.channelId = channel.getId();
        this.entryStartUtcMillis = j;
        this.entryEndUtcMillis = j2;
        this.isBlocked = z;
    }

    public final Channel getChannel() {
        return this.channel;
    }

    public final TVAiring getProgram() {
        return this.program;
    }

    public final long getEntryStartUtcMillis() {
        return this.entryStartUtcMillis;
    }

    public final void setEntryStartUtcMillis(long j) {
        this.entryStartUtcMillis = j;
    }

    public final long getEntryEndUtcMillis() {
        return this.entryEndUtcMillis;
    }

    public final void setEntryEndUtcMillis(long j) {
        this.entryEndUtcMillis = j;
    }

    /* JADX INFO: renamed from: isBlocked, reason: from getter */
    public final boolean getIsBlocked() {
        return this.isBlocked;
    }

    public final String getChannelId() {
        return this.channelId;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TableEntry(Channel channel, long j, long j2) {
        this(channel, j, j2, false);
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        this._isGap = true;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TableEntry(Channel channel, long j, long j2, boolean z) {
        this(channel, null, j, j2, z);
        Intrinsics.checkParameterIsNotNull(channel, "channel");
    }

    public final String getId() {
        String id;
        TVAiring tVAiring = this.program;
        return (tVAiring == null || (id = tVAiring.getId()) == null) ? String.valueOf(-this.entryEndUtcMillis) : id;
    }

    /* JADX INFO: renamed from: isGap, reason: from getter */
    public final boolean get_isGap() {
        return this._isGap;
    }

    public final int getWidth() {
        return GuideUtils.INSTANCE.convertMillisToPixel(this.entryStartUtcMillis, this.entryEndUtcMillis);
    }

    public final boolean isCurrentProgram() {
        long j = this.entryStartUtcMillis;
        long j2 = this.entryEndUtcMillis;
        long time = new Date().getTime();
        return j <= time && j2 > time;
    }

    public String toString() {
        return "TableEntry{hashCode=" + hashCode() + ", channelId=" + this.channelId + ", program=" + this.program + ", startTime=" + toTimeString(this.entryStartUtcMillis) + ", endTimeTime=" + toTimeString(this.entryEndUtcMillis) + "}";
    }

    public boolean equals(Object other) {
        if (!(other instanceof TableEntry)) {
            return false;
        }
        long j = 1000;
        TableEntry tableEntry = (TableEntry) other;
        return this.entryStartUtcMillis / j == tableEntry.entryStartUtcMillis / j && this.entryEndUtcMillis / j == tableEntry.entryEndUtcMillis / j && Intrinsics.areEqual(this.channelId, tableEntry.channelId);
    }

    public final String toTimeString(long timeMillis) {
        return new Date(timeMillis).toString();
    }
}
