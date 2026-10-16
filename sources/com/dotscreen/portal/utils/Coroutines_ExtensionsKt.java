package com.dotscreen.portal.utils;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.random.Random;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: compiled from: Coroutines+Extensions.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\u001a#\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"randomizedDelay", "", "timeMillis", "", "biasFactor", "", "(JDLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class Coroutines_ExtensionsKt {
    public static /* synthetic */ Object randomizedDelay$default(long j, double d, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            d = 0.1d;
        }
        return randomizedDelay(j, d, continuation);
    }

    public static final Object randomizedDelay(long j, double d, Continuation<? super Unit> continuation) {
        double d2 = j;
        Object objDelay = DelayKt.delay(RangesKt.random(new LongRange((long) ((1.0d - d) * d2), (long) (d2 * (d + 1.0d))), Random.INSTANCE), continuation);
        return objDelay == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDelay : Unit.INSTANCE;
    }
}
