package kotlinx.coroutines;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;

/* JADX INFO: compiled from: Await.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u000e:\u0002\u000b\fB\u001d\u0012\u0014\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0086@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tR$\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u00028\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/AwaitAll;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/coroutines/Deferred;", "deferreds", "<init>", "([Lkotlinx/coroutines/Deferred;)V", "", "await", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "[Lkotlinx/coroutines/Deferred;", "AwaitAllNode", "DisposeHandlersOnCancel", "kotlinx-coroutines-core", ""}, k = 1, mv = {1, 4, 0})
final class AwaitAll<T> {
    static final AtomicIntegerFieldUpdater notCompletedCount$FU = AtomicIntegerFieldUpdater.newUpdater(AwaitAll.class, "notCompletedCount");
    private final Deferred<T>[] deferreds;
    volatile int notCompletedCount;

    /* JADX WARN: Multi-variable type inference failed */
    public AwaitAll(Deferred<? extends T>[] deferredArr) {
        this.deferreds = deferredArr;
        this.notCompletedCount = deferredArr.length;
    }

    /* JADX INFO: compiled from: Await.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u000e\u0012\f0\u0004R\b\u0012\u0004\u0012\u00028\u00000\u00050\u0003¢\u0006\u0002\u0010\u0006J\u0006\u0010\b\u001a\u00020\tJ\u0013\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0096\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016R \u0010\u0002\u001a\u0012\u0012\u000e\u0012\f0\u0004R\b\u0012\u0004\u0012\u00028\u00000\u00050\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/AwaitAll$DisposeHandlersOnCancel;", "Lkotlinx/coroutines/CancelHandler;", "nodes", "", "Lkotlinx/coroutines/AwaitAll$AwaitAllNode;", "Lkotlinx/coroutines/AwaitAll;", "(Lkotlinx/coroutines/AwaitAll;[Lkotlinx/coroutines/AwaitAll$AwaitAllNode;)V", "[Lkotlinx/coroutines/AwaitAll$AwaitAllNode;", "disposeAll", "", "invoke", "cause", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    private final class DisposeHandlersOnCancel extends CancelHandler {
        private final AwaitAll<T>.AwaitAllNode[] nodes;

        public DisposeHandlersOnCancel(AwaitAll<T>.AwaitAllNode[] awaitAllNodeArr) {
            this.nodes = awaitAllNodeArr;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        public final void disposeAll() {
            for (AwaitAll<T>.AwaitAllNode awaitAllNode : this.nodes) {
                awaitAllNode.disposeHandle();
            }
        }

        @Override // kotlinx.coroutines.CancelHandlerBase
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2(Throwable cause) {
            disposeAll();
        }

        public String toString() {
            return "DisposeHandlersOnCancel[" + this.nodes + ']';
        }
    }

    /* JADX INFO: compiled from: Await.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00040\u001aB#\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\r\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0096\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0012\u001a\u00020\b2\u0010\u0010\u0011\u001a\f0\u000fR\b\u0012\u0004\u0012\u00028\u00000\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0016\u0010\u0017R\"\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u00018\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0018¨\u0006\u0019"}, d2 = {"Lkotlinx/coroutines/AwaitAll$AwaitAllNode;", "Lkotlinx/coroutines/CancellableContinuation;", "", "continuation", "Lkotlinx/coroutines/Job;", "job", "<init>", "(Lkotlinx/coroutines/AwaitAll;Lkotlinx/coroutines/CancellableContinuation;Lkotlinx/coroutines/Job;)V", "", "disposeHandle", "()V", "", "cause", "invoke", "(Ljava/lang/Throwable;)V", "Lkotlinx/coroutines/AwaitAll$DisposeHandlersOnCancel;", "Lkotlinx/coroutines/AwaitAll;", "disposer", "setDisposer", "(Lkotlinx/coroutines/AwaitAll$DisposeHandlersOnCancel;)V", "Lkotlinx/coroutines/DisposableHandle;", "handle", "setHandle", "(Lkotlinx/coroutines/DisposableHandle;)V", "Lkotlinx/coroutines/CancellableContinuation;", "kotlinx-coroutines-core", "Lkotlinx/coroutines/JobNode;"}, k = 1, mv = {1, 4, 0})
    private final class AwaitAllNode extends JobNode<Job> {
        private volatile Object _disposer;
        private volatile Object _handle;
        private final CancellableContinuation<List<? extends T>> continuation;

        /* JADX WARN: Multi-variable type inference failed */
        public AwaitAllNode(CancellableContinuation<? super List<? extends T>> cancellableContinuation, Job job) {
            super(job);
            this.continuation = cancellableContinuation;
            this._handle = null;
            this._disposer = null;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        public final void setHandle(DisposableHandle handle) {
            this._handle = handle;
        }

        public final void setDisposer(AwaitAll<T>.DisposeHandlersOnCancel disposer) {
            this._disposer = disposer;
        }

        public final void disposeHandle() {
            DisposableHandle disposableHandle = (DisposableHandle) this._handle;
            if (disposableHandle != null) {
                disposableHandle.dispose();
            }
            this._handle = null;
        }

        @Override // kotlinx.coroutines.CompletionHandlerBase
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2(Throwable cause) {
            if (cause != null) {
                Object objTryResumeWithException = this.continuation.tryResumeWithException(cause);
                if (objTryResumeWithException != null) {
                    this.continuation.completeResume(objTryResumeWithException);
                    DisposeHandlersOnCancel disposeHandlersOnCancel = (DisposeHandlersOnCancel) this._disposer;
                    if (disposeHandlersOnCancel != null) {
                        disposeHandlersOnCancel.disposeAll();
                    }
                }
            } else {
                if (AwaitAll.notCompletedCount$FU.decrementAndGet(AwaitAll.this) == 0) {
                    CancellableContinuation<List<? extends T>> cancellableContinuation = this.continuation;
                    Deferred[] deferredArr = AwaitAll.this.deferreds;
                    ArrayList arrayList = new ArrayList(deferredArr.length);
                    for (Deferred deferred : deferredArr) {
                        arrayList.add(deferred.getCompleted());
                    }
                    Result.Companion companion = Result.INSTANCE;
                    cancellableContinuation.resumeWith(Result.m37constructorimpl(arrayList));
                }
            }
            this._handle = null;
            this._disposer = null;
        }
    }

    public final Object await(Continuation<? super List<? extends T>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        int length = this.deferreds.length;
        AwaitAllNode[] awaitAllNodeArr = new AwaitAllNode[length];
        for (int i = 0; i < length; i++) {
            Deferred deferred = this.deferreds[Boxing.boxInt(i).intValue()];
            deferred.start();
            AwaitAllNode awaitAllNode = new AwaitAllNode(cancellableContinuationImpl2, deferred);
            awaitAllNode.setHandle(deferred.invokeOnCompletion(awaitAllNode));
            awaitAllNodeArr[i] = awaitAllNode;
        }
        AwaitAll<T>.DisposeHandlersOnCancel disposeHandlersOnCancel = new DisposeHandlersOnCancel(awaitAllNodeArr);
        for (int i2 = 0; i2 < length; i2++) {
            awaitAllNodeArr[i2].setDisposer(disposeHandlersOnCancel);
        }
        if (cancellableContinuationImpl2.isCompleted()) {
            disposeHandlersOnCancel.disposeAll();
        } else {
            cancellableContinuationImpl2.invokeOnCancellation(disposeHandlersOnCancel);
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
