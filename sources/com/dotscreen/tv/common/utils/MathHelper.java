package com.dotscreen.tv.common.utils;

import kotlin.Metadata;

/* JADX INFO: compiled from: Physic.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/tv/common/utils/MathHelper;", "", "()V", "getLinearInterpolation", "", "xp", "", "x0", "x1", "y0", "y1", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class MathHelper {
    public static final MathHelper INSTANCE = new MathHelper();

    public final double getLinearInterpolation(int xp, int x0, int x1, int y0, int y1) {
        return ((double) y0) + ((((double) (y1 - y0)) / ((double) (x1 - x0))) * ((double) (xp - x0)));
    }

    private MathHelper() {
    }
}
