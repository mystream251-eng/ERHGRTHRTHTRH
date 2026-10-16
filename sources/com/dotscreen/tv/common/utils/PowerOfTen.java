package com.dotscreen.tv.common.utils;

import kotlin.Metadata;

/* JADX INFO: compiled from: Physic.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/dotscreen/tv/common/utils/PowerOfTen;", "", "power", "", "shortName", "", "(Ljava/lang/String;IDLjava/lang/String;)V", "getPower", "()D", "getShortName", "()Ljava/lang/String;", "UNIT", "KILO", "MEGA", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public enum PowerOfTen {
    UNIT(0.0d, ""),
    KILO(3.0d, "k"),
    MEGA(6.0d, "M");

    private final double power;
    private final String shortName;

    PowerOfTen(double d, String str) {
        this.power = d;
        this.shortName = str;
    }

    public final double getPower() {
        return this.power;
    }

    public final String getShortName() {
        return this.shortName;
    }
}
