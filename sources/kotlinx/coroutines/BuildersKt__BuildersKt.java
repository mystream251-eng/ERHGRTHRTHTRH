package kotlinx.coroutines;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.intrinsics.CancellableKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Builders.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001aT\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032'\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\n\u001a^\u0010\u000b\u001a\u00020\b\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000e2\u0006\u0010\u000f\u001a\u0002H\f2)\b\b\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u0002H\f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tH\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001af\u0010\u0011\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u0002H\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000e2)\b\b\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u0002H\f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tH\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u008d\u0001\u0010\u0016\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u0002H\f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00072%\b\n\u0010\u0018\u001a\u001f\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00192)\b\b\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u0002H\f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tH\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a8\u0010\u001f\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\f2\u0006\u0010 \u001a\u00020\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000e2\u0006\u0010\u000f\u001a\u0002H\fH\u0080\b¢\u0006\u0002\u0010!\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"runBlocking", ExifInterface.GPS_DIRECTION_TRUE, "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "saveLazyCoroutine", "R", "coroutine", "Lkotlinx/coroutines/AbstractCoroutine;", "receiver", "(Lkotlinx/coroutines/AbstractCoroutine;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "startAbstractCoroutine", "", "start", "Lkotlinx/coroutines/CoroutineStart;", "(Lkotlinx/coroutines/CoroutineStart;Ljava/lang/Object;Lkotlinx/coroutines/AbstractCoroutine;Lkotlin/jvm/functions/Function2;)V", "startCoroutine", "completion", "onCancellation", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "(Lkotlinx/coroutines/CoroutineStart;Ljava/lang/Object;Lkotlin/coroutines/Continuation;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "startLazyCoroutine", "saved", "(Ljava/lang/Object;Lkotlinx/coroutines/AbstractCoroutine;Ljava/lang/Object;)V", "kotlinx-coroutines-core"}, k = 5, mv = {1, 4, 0}, xs = "kotlinx/coroutines/BuildersKt")
public final /* synthetic */ class BuildersKt__BuildersKt {
    public static /* synthetic */ Object runBlocking$default(CoroutineContext coroutineContext, Function2 function2, int i, Object obj) throws InterruptedException {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return BuildersKt.runBlocking(coroutineContext, function2);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x003b  */
    public static final <T> T runBlocking(CoroutineContext coroutineContext, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2) throws InterruptedException {
        EventLoop eventLoopCurrentOrNull$kotlinx_coroutines_core;
        CoroutineContext coroutineContextNewCoroutineContext;
        Thread threadCurrentThread = Thread.currentThread();
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) coroutineContext.get(ContinuationInterceptor.INSTANCE);
        if (continuationInterceptor == null) {
            eventLoopCurrentOrNull$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
            coroutineContextNewCoroutineContext = CoroutineContextKt.newCoroutineContext(GlobalScope.INSTANCE, coroutineContext.plus(eventLoopCurrentOrNull$kotlinx_coroutines_core));
        } else {
            if (!(continuationInterceptor instanceof EventLoop)) {
                continuationInterceptor = null;
            }
            EventLoop eventLoop = (EventLoop) continuationInterceptor;
            if (eventLoop == null) {
                eventLoopCurrentOrNull$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.currentOrNull$kotlinx_coroutines_core();
            } else {
                EventLoop eventLoop2 = eventLoop.shouldBeProcessedFromContext() ? eventLoop : null;
                if (eventLoop2 != null) {
                    eventLoopCurrentOrNull$kotlinx_coroutines_core = eventLoop2;
                } else {
                    eventLoopCurrentOrNull$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.currentOrNull$kotlinx_coroutines_core();
                }
            }
            coroutineContextNewCoroutineContext = CoroutineContextKt.newCoroutineContext(GlobalScope.INSTANCE, coroutineContext);
        }
        BlockingCoroutine blockingCoroutine = new BlockingCoroutine(coroutineContextNewCoroutineContext, threadCurrentThread, eventLoopCurrentOrNull$kotlinx_coroutines_core);
        blockingCoroutine.start(CoroutineStart.DEFAULT, blockingCoroutine, function2);
        return (T) blockingCoroutine.joinBlocking();
    }

    public static final <T, R> void startCoroutine(CoroutineStart coroutineStart, R r, Continuation<? super T> continuation, Function1<? super Throwable, Unit> function1, Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2) {
        BuildersKt.startCoroutineImpl(coroutineStart, r, continuation, function1, function2);
    }

    public static /* synthetic */ void startCoroutine$default(CoroutineStart coroutineStart, Object obj, Continuation continuation, Function1 function1, Function2 function2, int i, Object obj2) {
        if ((i & 8) != 0) {
            function1 = (Function1) null;
        }
        BuildersKt.startCoroutineImpl(coroutineStart, obj, continuation, function1, function2);
    }

    public static final <T, R> void startAbstractCoroutine(CoroutineStart coroutineStart, R r, AbstractCoroutine<? super T> abstractCoroutine, Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2) {
        abstractCoroutine.initParentJob$kotlinx_coroutines_core();
        BuildersKt.startCoroutineImpl(coroutineStart, r, abstractCoroutine, null, function2);
    }

    public static final <T, R> Object saveLazyCoroutine(AbstractCoroutine<? super T> abstractCoroutine, R r, Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2) {
        return IntrinsicsKt.createCoroutineUnintercepted(function2, r, abstractCoroutine);
    }

    public static final <T, R> void startLazyCoroutine(Object obj, AbstractCoroutine<? super T> abstractCoroutine, R r) {
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Unit>");
        }
        CancellableKt.startCoroutineCancellable((Continuation<? super Unit>) obj, abstractCoroutine);
    }
}
