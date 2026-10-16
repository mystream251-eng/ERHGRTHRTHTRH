package com.dotscreen.tv.common.bokit;

import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RightVProgramInfo;", "", "startDate", "Ljava/util/Date;", "endDate", "(Ljava/util/Date;Ljava/util/Date;)V", "getStartDate", "()Ljava/util/Date;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class RightVProgramInfo {
    private final Date startDate;

    public RightVProgramInfo(Date startDate, Date endDate) {
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        this.startDate = startDate;
    }

    public final Date getStartDate() {
        return this.startDate;
    }
}
