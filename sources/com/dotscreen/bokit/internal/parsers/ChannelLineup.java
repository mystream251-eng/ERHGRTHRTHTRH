package com.dotscreen.bokit.internal.parsers;

import com.dotscreen.bokit.model.Channel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LineupParser.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/dotscreen/bokit/internal/parsers/ChannelLineup;", "", "id", "", "(Ljava/lang/String;)V", "channels", "", "Lcom/dotscreen/bokit/model/Channel;", "getChannels", "()Ljava/util/List;", "setChannels", "(Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
final class ChannelLineup {
    private List<Channel> channels;
    private final String id;

    public ChannelLineup(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
    }

    public final String getId() {
        return this.id;
    }

    public final List<Channel> getChannels() {
        return this.channels;
    }

    public final void setChannels(List<Channel> list) {
        this.channels = list;
    }
}
