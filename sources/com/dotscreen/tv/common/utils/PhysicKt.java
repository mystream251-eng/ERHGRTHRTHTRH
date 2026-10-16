package com.dotscreen.tv.common.utils;

import kotlin.Metadata;

/* JADX INFO: compiled from: Physic.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0001\u001a\u0012\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"convertdBmtodBmV", "", "dBmValue", "systemImpedance", "round", "decimals", "", "common_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class PhysicKt {
    public static /* synthetic */ double convertdBmtodBmV$default(double d, double d2, int i, Object obj) {
        if ((i & 2) != 0) {
            d2 = 75.0d;
        }
        return convertdBmtodBmV(d, d2);
    }

    public static final double convertdBmtodBmV(double d, double d2) {
        return d + ((double) 30) + (((double) 20) * Math.log10(Math.sqrt(d2)));
    }

    public static final double round(double d, int i) {
        double d2 = 1.0d;
        for (int i2 = 0; i2 < i; i2++) {
            d2 *= (double) 10;
        }
        return Math.rint(d * d2) / d2;
    }
}
