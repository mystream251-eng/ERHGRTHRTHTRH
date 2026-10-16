package com.dotscreen.tv.common.bokit;

import com.dotscreen.bokit.model.ItemCollection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0006R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Lcom/dotscreen/tv/common/bokit/LiveProgramsSearchResponse;", "", "catchups", "Lcom/dotscreen/bokit/model/ItemCollection;", "Lcom/dotscreen/tv/common/bokit/SearchTVProgram;", "airings", "(Lcom/dotscreen/bokit/model/ItemCollection;Lcom/dotscreen/bokit/model/ItemCollection;)V", "getAirings", "()Lcom/dotscreen/bokit/model/ItemCollection;", "getCatchups", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class LiveProgramsSearchResponse {
    private final ItemCollection<SearchTVProgram> airings;
    private final ItemCollection<SearchTVProgram> catchups;

    public LiveProgramsSearchResponse(ItemCollection<SearchTVProgram> catchups, ItemCollection<SearchTVProgram> airings) {
        Intrinsics.checkParameterIsNotNull(catchups, "catchups");
        Intrinsics.checkParameterIsNotNull(airings, "airings");
        this.catchups = catchups;
        this.airings = airings;
    }

    public final ItemCollection<SearchTVProgram> getCatchups() {
        return this.catchups;
    }

    public final ItemCollection<SearchTVProgram> getAirings() {
        return this.airings;
    }
}
