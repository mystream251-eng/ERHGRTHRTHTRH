package com.dotscreen.tv.common.bokit;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RightVParentalRating;", "", "name", "", "level", "", "(Ljava/lang/String;I)V", "getLevel", "()I", "getName", "()Ljava/lang/String;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class RightVParentalRating {
    private final int level;
    private final String name;

    public RightVParentalRating(String name, int i) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.name = name;
        this.level = i;
    }

    public final int getLevel() {
        return this.level;
    }

    public final String getName() {
        return this.name;
    }
}
