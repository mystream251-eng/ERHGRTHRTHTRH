package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: CancellableContinuationImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\\\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012%\b\u0002\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u000e\u0010\u000fJd\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032%\b\u0002\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0010\u0010\u0011J!\u0010\u0014\u001a\u00020\n2\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\n¢\u0006\u0004\b\u0016\u0010\u0017R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00068\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u0018R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u00038F@\u0006¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u001e\u001a\u00020\u001b8F@\u0006¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0018\u0010\f\u001a\u0004\u0018\u00010\u00018\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\f\u0010\u001fR0\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u00058F@\u0006¢\u0006\u0006\u001a\u0004\b \u0010!R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00018\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u001f¨\u0006\""}, d2 = {"Lkotlinx/coroutines/CompletedContinuation;", "", "result", "Lkotlinx/coroutines/CancelHandler;", "cancelHandler", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "onCancellation", "idempotentResume", "cancelCause", "<init>", "(Ljava/lang/Object;Lkotlinx/coroutines/CancelHandler;Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Ljava/lang/Throwable;)V", "copy", "(Ljava/lang/Object;Lkotlinx/coroutines/CancelHandler;Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Ljava/lang/Throwable;)Lkotlinx/coroutines/CompletedContinuation;", "Lkotlinx/coroutines/CancellableContinuationImpl;", "cont", "invokeHandlers", "(Lkotlinx/coroutines/CancellableContinuationImpl;Ljava/lang/Throwable;)V", "releaseHandlers", "()V", "Ljava/lang/Throwable;", "getCancelHandler", "()Lkotlinx/coroutines/CancelHandler;", "", "getCancelled", "()Z", "cancelled", "Ljava/lang/Object;", "getOnCancellation", "()Lkotlin/jvm/functions/Function1;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
final class CompletedContinuation {
    private volatile Object _cancelHandler;
    private volatile Object _onCancellation;
    public final Throwable cancelCause;
    public final Object idempotentResume;
    public final Object result;

    public CompletedContinuation(Object obj, CancelHandler cancelHandler, Function1<? super Throwable, Unit> function1, Object obj2, Throwable th) {
        this.result = obj;
        this.idempotentResume = obj2;
        this.cancelCause = th;
        this._cancelHandler = cancelHandler;
        this._onCancellation = function1;
    }

    public /* synthetic */ CompletedContinuation(Object obj, CancelHandler cancelHandler, Function1 function1, Object obj2, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, (i & 2) != 0 ? (CancelHandler) null : cancelHandler, (i & 4) != 0 ? (Function1) null : function1, (i & 8) != 0 ? null : obj2, (i & 16) != 0 ? (Throwable) null : th);
    }

    public final CancelHandler getCancelHandler() {
        return (CancelHandler) this._cancelHandler;
    }

    public final Function1<Throwable, Unit> getOnCancellation() {
        return (Function1) this._onCancellation;
    }

    public final boolean getCancelled() {
        return this.cancelCause != null;
    }

    public final void releaseHandlers() {
        this._cancelHandler = null;
        this._onCancellation = null;
    }

    public final void invokeHandlers(CancellableContinuationImpl<?> cont, Throwable cause) {
        CancelHandler cancelHandler = getCancelHandler();
        if (cancelHandler != null) {
            cont.callCancelHandler(cancelHandler, cause);
        }
        Function1<Throwable, Unit> onCancellation = getOnCancellation();
        if (onCancellation != null) {
            cont.callOnCancellation(onCancellation, cause);
        }
        releaseHandlers();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CompletedContinuation copy$default(CompletedContinuation completedContinuation, Object obj, CancelHandler cancelHandler, Function1 function1, Object obj2, Throwable th, int i, Object obj3) {
        if ((i & 1) != 0) {
            obj = completedContinuation.result;
        }
        if ((i & 2) != 0) {
            cancelHandler = completedContinuation.getCancelHandler();
        }
        CancelHandler cancelHandler2 = cancelHandler;
        if ((i & 4) != 0) {
            function1 = completedContinuation.getOnCancellation();
        }
        Function1 function2 = function1;
        if ((i & 8) != 0) {
            obj2 = completedContinuation.idempotentResume;
        }
        Object obj4 = obj2;
        if ((i & 16) != 0) {
            th = completedContinuation.cancelCause;
        }
        return completedContinuation.copy(obj, cancelHandler2, function2, obj4, th);
    }

    public final CompletedContinuation copy(Object result, CancelHandler cancelHandler, Function1<? super Throwable, Unit> onCancellation, Object idempotentResume, Throwable cancelCause) {
        return new CompletedContinuation(result, cancelHandler, onCancellation, idempotentResume, cancelCause);
    }
}
