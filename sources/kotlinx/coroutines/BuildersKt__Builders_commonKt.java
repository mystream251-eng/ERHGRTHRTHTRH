package kotlinx.coroutines;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.ContinuationKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.debug.internal.DebugCoroutineInfoImplKt;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Builders.common.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0088\u0001\u0010\u0004\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u0002H\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00060\f2#\u0010\r\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000e2'\u0010\u0013\u001a#\b\u0001\u0012\u0004\u0012\u0002H\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\f\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014¢\u0006\u0002\b\u0016H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001aU\u0010\u0018\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u00062\u0006\u0010\u0019\u001a\u00020\u001a2'\u0010\u0013\u001a#\b\u0001\u0012\u0004\u0012\u00020\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\f\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014¢\u0006\u0002\b\u0016H\u0086@ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\u001c\u001a[\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00060\u001e\"\u0004\b\u0000\u0010\u0006*\u00020\u001b2\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\b\u001a\u00020\t2'\u0010\u0013\u001a#\b\u0001\u0012\u0004\u0012\u00020\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\f\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014¢\u0006\u0002\b\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u001f\u001aF\u0010 \u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u0006*\u00020!2)\b\b\u0010\u0013\u001a#\b\u0001\u0012\u0004\u0012\u00020\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\f\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014¢\u0006\u0002\b\u0016H\u0086Jø\u0001\u0000¢\u0006\u0002\u0010\"\u001aO\u0010#\u001a\u00020$*\u00020\u001b2\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\b\u001a\u00020\t2'\u0010\u0013\u001a#\b\u0001\u0012\u0004\u0012\u00020\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014¢\u0006\u0002\b\u0016ø\u0001\u0000¢\u0006\u0002\u0010%\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, d2 = {"RESUMED", "", DebugCoroutineInfoImplKt.SUSPENDED, "UNDECIDED", "startCoroutineImpl", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "start", "Lkotlinx/coroutines/CoroutineStart;", "receiver", "completion", "Lkotlin/coroutines/Continuation;", "onCancellation", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "block", "Lkotlin/Function2;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineStart;Ljava/lang/Object;Lkotlin/coroutines/Continuation;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "withContext", "context", "Lkotlin/coroutines/CoroutineContext;", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "async", "Lkotlinx/coroutines/Deferred;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Deferred;", "invoke", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "launch", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Job;", "kotlinx-coroutines-core"}, k = 5, mv = {1, 4, 0}, xs = "kotlinx/coroutines/BuildersKt")
public final /* synthetic */ class BuildersKt__Builders_commonKt {
    private static final int RESUMED = 2;
    private static final int SUSPENDED = 1;
    private static final int UNDECIDED = 0;

    public static /* synthetic */ Job launch$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 2) != 0) {
            coroutineStart = CoroutineStart.DEFAULT;
        }
        return BuildersKt.launch(coroutineScope, coroutineContext, coroutineStart, function2);
    }

    public static final Job launch(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        LazyStandaloneCoroutine standaloneCoroutine;
        CoroutineContext coroutineContextNewCoroutineContext = CoroutineContextKt.newCoroutineContext(coroutineScope, coroutineContext);
        if (coroutineStart.isLazy()) {
            standaloneCoroutine = new LazyStandaloneCoroutine(coroutineContextNewCoroutineContext, function2);
        } else {
            standaloneCoroutine = new StandaloneCoroutine(coroutineContextNewCoroutineContext, true);
        }
        standaloneCoroutine.start(coroutineStart, standaloneCoroutine, function2);
        return standaloneCoroutine;
    }

    public static /* synthetic */ Deferred async$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 2) != 0) {
            coroutineStart = CoroutineStart.DEFAULT;
        }
        return BuildersKt.async(coroutineScope, coroutineContext, coroutineStart, function2);
    }

    public static final <T> Deferred<T> async(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2) {
        LazyDeferredCoroutine deferredCoroutine;
        CoroutineContext coroutineContextNewCoroutineContext = CoroutineContextKt.newCoroutineContext(coroutineScope, coroutineContext);
        if (coroutineStart.isLazy()) {
            deferredCoroutine = new LazyDeferredCoroutine(coroutineContextNewCoroutineContext, function2);
        } else {
            deferredCoroutine = new DeferredCoroutine(coroutineContextNewCoroutineContext, true);
        }
        deferredCoroutine.start(coroutineStart, deferredCoroutine, function2);
        return deferredCoroutine;
    }

    public static final <T> Object withContext(CoroutineContext coroutineContext, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        Object result;
        CoroutineContext context = continuation.get$context();
        CoroutineContext coroutineContextPlus = context.plus(coroutineContext);
        YieldKt.checkCompletion(coroutineContextPlus);
        if (coroutineContextPlus == context) {
            ScopeCoroutine scopeCoroutine = new ScopeCoroutine(coroutineContextPlus, continuation);
            result = UndispatchedKt.startUndispatchedOrReturn(scopeCoroutine, scopeCoroutine, function2);
        } else if (Intrinsics.areEqual((ContinuationInterceptor) coroutineContextPlus.get(ContinuationInterceptor.INSTANCE), (ContinuationInterceptor) context.get(ContinuationInterceptor.INSTANCE))) {
            UndispatchedCoroutine undispatchedCoroutine = new UndispatchedCoroutine(coroutineContextPlus, continuation);
            Object objUpdateThreadContext = ThreadContextKt.updateThreadContext(coroutineContextPlus, null);
            try {
                Object objStartUndispatchedOrReturn = UndispatchedKt.startUndispatchedOrReturn(undispatchedCoroutine, undispatchedCoroutine, function2);
                ThreadContextKt.restoreThreadContext(coroutineContextPlus, objUpdateThreadContext);
                result = objStartUndispatchedOrReturn;
            } catch (Throwable th) {
                ThreadContextKt.restoreThreadContext(coroutineContextPlus, objUpdateThreadContext);
                throw th;
            }
        } else {
            DispatchedCoroutine dispatchedCoroutine = new DispatchedCoroutine(coroutineContextPlus, continuation);
            dispatchedCoroutine.start(CoroutineStart.DEFAULT, dispatchedCoroutine, function2);
            result = dispatchedCoroutine.getResult();
        }
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public static final <T> Object invoke(CoroutineDispatcher coroutineDispatcher, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        return BuildersKt.withContext(coroutineDispatcher, function2, continuation);
    }

    private static final Object invoke$$forInline(CoroutineDispatcher coroutineDispatcher, Function2 function2, Continuation continuation) {
        InlineMarker.mark(0);
        Object objWithContext = BuildersKt.withContext(coroutineDispatcher, function2, continuation);
        InlineMarker.mark(1);
        return objWithContext;
    }

    public static final <T, R> void startCoroutineImpl(CoroutineStart coroutineStart, R r, Continuation<? super T> continuation, Function1<? super Throwable, Unit> function1, Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2) {
        int i = BuildersKt.WhenMappings.$EnumSwitchMapping$0[coroutineStart.ordinal()];
        if (i == 1) {
            CancellableKt.startCoroutineCancellable(function2, r, continuation, function1);
            return;
        }
        if (i == 2) {
            ContinuationKt.startCoroutine(function2, r, continuation);
        } else if (i == 3) {
            UndispatchedKt.startCoroutineUndispatched(function2, r, continuation);
        } else if (i != 4) {
            throw new NoWhenBranchMatchedException();
        }
    }
}
