package com.dotscreen.bokit.services;

import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.ItemCollection;
import com.dotscreen.bokit.model.TVAiring;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveTVService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/bokit/services/LiveTVSearchResponse;", "", "channels", "Lcom/dotscreen/bokit/model/ItemCollection;", "Lcom/dotscreen/bokit/model/Channel;", "airings", "Lcom/dotscreen/bokit/model/TVAiring;", "(Lcom/dotscreen/bokit/model/ItemCollection;Lcom/dotscreen/bokit/model/ItemCollection;)V", "getAirings", "()Lcom/dotscreen/bokit/model/ItemCollection;", "getChannels", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final class LiveTVSearchResponse {
    private final ItemCollection<TVAiring> airings;
    private final ItemCollection<Channel> channels;

    public LiveTVSearchResponse(ItemCollection<Channel> channels, ItemCollection<TVAiring> airings) {
        Intrinsics.checkNotNullParameter(channels, "channels");
        Intrinsics.checkNotNullParameter(airings, "airings");
        this.channels = channels;
        this.airings = airings;
    }

    public final ItemCollection<Channel> getChannels() {
        return this.channels;
    }

    public final ItemCollection<TVAiring> getAirings() {
        return this.airings;
    }
}
