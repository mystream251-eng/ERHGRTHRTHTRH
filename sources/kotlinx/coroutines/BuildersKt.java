package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"kotlinx/coroutines/BuildersKt__BuildersKt", "kotlinx/coroutines/BuildersKt__Builders_commonKt"}, k = 4, mv = {1, 4, 0})
public final class BuildersKt {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CoroutineStart.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[CoroutineStart.DEFAULT.ordinal()] = 1;
            $EnumSwitchMapping$0[CoroutineStart.ATOMIC.ordinal()] = 2;
            $EnumSwitchMapping$0[CoroutineStart.UNDISPATCHED.ordinal()] = 3;
            $EnumSwitchMapping$0[CoroutineStart.LAZY.ordinal()] = 4;
        }
    }

    public static final <T> Deferred<T> async(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2) {
        return BuildersKt__Builders_commonKt.async(coroutineScope, coroutineContext, coroutineStart, function2);
    }

    public static final <T> Object invoke(CoroutineDispatcher coroutineDispatcher, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        return BuildersKt__Builders_commonKt.invoke(coroutineDispatcher, function2, continuation);
    }

    public static final Job launch(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return BuildersKt__Builders_commonKt.launch(coroutineScope, coroutineContext, coroutineStart, function2);
    }

    public static final <T> T runBlocking(CoroutineContext coroutineContext, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2) throws InterruptedException {
        return (T) BuildersKt__BuildersKt.runBlocking(coroutineContext, function2);
    }

    public static final <T, R> Object saveLazyCoroutine(AbstractCoroutine<? super T> abstractCoroutine, R r, Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2) {
        return BuildersKt__BuildersKt.saveLazyCoroutine(abstractCoroutine, r, function2);
    }

    public static final <T, R> void startAbstractCoroutine(CoroutineStart coroutineStart, R r, AbstractCoroutine<? super T> abstractCoroutine, Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2) {
        BuildersKt__BuildersKt.startAbstractCoroutine(coroutineStart, r, abstractCoroutine, function2);
    }

    public static final <T, R> void startCoroutine(CoroutineStart coroutineStart, R r, Continuation<? super T> continuation, Function1<? super Throwable, Unit> function1, Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2) {
        BuildersKt__BuildersKt.startCoroutine(coroutineStart, r, continuation, function1, function2);
    }

    public static final <T, R> void startCoroutineImpl(CoroutineStart coroutineStart, R r, Continuation<? super T> continuation, Function1<? super Throwable, Unit> function1, Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2) {
        BuildersKt__Builders_commonKt.startCoroutineImpl(coroutineStart, r, continuation, function1, function2);
    }

    public static final <T, R> void startLazyCoroutine(Object obj, AbstractCoroutine<? super T> abstractCoroutine, R r) {
        BuildersKt__BuildersKt.startLazyCoroutine(obj, abstractCoroutine, r);
    }

    public static final <T> Object withContext(CoroutineContext coroutineContext, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        return BuildersKt__Builders_commonKt.withContext(coroutineContext, function2, continuation);
    }
}
