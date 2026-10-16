package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BeforeResumeCancelHandler;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedList_commonKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

/* JADX INFO: compiled from: AbstractChannel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0007PQRSTUVB'\u0012 \u0010\u0004\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\u001b\u001a\u00020\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0007J\u0016\u0010\u001b\u001a\u00020\u00062\u000e\u0010\u001c\u001a\n\u0018\u00010\u001ej\u0004\u0018\u0001`\u001fJ\u0017\u0010 \u001a\u00020\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0000¢\u0006\u0002\b!J\u000e\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#H\u0004J\u0016\u0010$\u001a\u00020\n2\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000&H\u0002J\u0016\u0010'\u001a\u00020\n2\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000&H\u0014JR\u0010(\u001a\u00020\n\"\u0004\b\u0001\u0010)2\f\u0010*\u001a\b\u0012\u0004\u0012\u0002H)0+2$\u0010,\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010.\u0012\n\u0012\b\u0012\u0004\u0012\u0002H)0/\u0012\u0006\u0012\u0004\u0018\u00010.0-2\u0006\u00100\u001a\u000201H\u0002ø\u0001\u0000¢\u0006\u0002\u00102J\u000f\u00103\u001a\b\u0012\u0004\u0012\u00028\u000004H\u0086\u0002J\u0010\u00105\u001a\u00020\u00062\u0006\u00106\u001a\u00020\nH\u0014J\b\u00107\u001a\u00020\u0006H\u0014J\b\u00108\u001a\u00020\u0006H\u0014J\r\u00109\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010:J\n\u0010;\u001a\u0004\u0018\u00010.H\u0014J\u0016\u0010<\u001a\u0004\u0018\u00010.2\n\u0010*\u001a\u0006\u0012\u0002\b\u00030+H\u0014J\u0011\u0010%\u001a\u00028\u0000H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010=J\u001f\u0010>\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017H\u0086@ø\u0001\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b?\u0010=J\u0013\u0010@\u001a\u0004\u0018\u00018\u0000H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010=J\u0019\u0010A\u001a\u0004\u0018\u00018\u00002\b\u0010B\u001a\u0004\u0018\u00010.H\u0002¢\u0006\u0002\u0010CJ\u001f\u0010D\u001a\u0002H)\"\u0004\b\u0001\u0010)2\u0006\u00100\u001a\u000201H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010EJR\u0010F\u001a\u00020\u0006\"\u0004\b\u0001\u0010)2\f\u0010*\u001a\b\u0012\u0004\u0012\u0002H)0+2\u0006\u00100\u001a\u0002012$\u0010,\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010.\u0012\n\u0012\b\u0012\u0004\u0012\u0002H)0/\u0012\u0006\u0012\u0004\u0018\u00010.0-H\u0002ø\u0001\u0000¢\u0006\u0002\u0010GJ \u0010H\u001a\u00020\u00062\n\u0010I\u001a\u0006\u0012\u0002\b\u00030J2\n\u0010%\u001a\u0006\u0012\u0002\b\u00030&H\u0002J\u0010\u0010K\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010LH\u0014JX\u0010M\u001a\u00020\u0006\"\u0004\b\u0001\u0010)* \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010.\u0012\n\u0012\b\u0012\u0004\u0012\u0002H)0/\u0012\u0006\u0012\u0004\u0018\u00010.0-2\f\u0010*\u001a\b\u0012\u0004\u0012\u0002H)0+2\u0006\u00100\u001a\u0002012\b\u0010N\u001a\u0004\u0018\u00010.H\u0002ø\u0001\u0000¢\u0006\u0002\u0010OR\u0014\u0010\t\u001a\u00020\n8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\nX¤\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\fR\u0012\u0010\u000e\u001a\u00020\nX¤\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u000f\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\fR\u0014\u0010\u0010\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\fR\u0014\u0010\u0011\u001a\u00020\n8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\fR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R \u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00170\u00138Fø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0015R\u0019\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00138F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0015\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006W"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/AbstractSendChannel;", "Lkotlinx/coroutines/channels/Channel;", "onUndeliveredElement", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "(Lkotlin/jvm/functions/Function1;)V", "hasReceiveOrClosed", "", "getHasReceiveOrClosed", "()Z", "isBufferAlwaysEmpty", "isBufferEmpty", "isClosedForReceive", "isEmpty", "isEmptyImpl", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveOrClosed", "Lkotlinx/coroutines/channels/ValueOrClosed;", "getOnReceiveOrClosed", "onReceiveOrNull", "getOnReceiveOrNull", "cancel", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cancelInternal", "cancelInternal$kotlinx_coroutines_core", "describeTryPoll", "Lkotlinx/coroutines/channels/AbstractChannel$TryPollDesc;", "enqueueReceive", "receive", "Lkotlinx/coroutines/channels/Receive;", "enqueueReceiveInternal", "enqueueReceiveSelect", "R", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "", "Lkotlin/coroutines/Continuation;", "receiveMode", "", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;I)Z", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "onCancelIdempotent", "wasClosed", "onReceiveDequeued", "onReceiveEnqueued", "poll", "()Ljava/lang/Object;", "pollInternal", "pollSelectInternal", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveOrClosed", "receiveOrClosed-ZYPwvRU", "receiveOrNull", "receiveOrNullResult", "result", "(Ljava/lang/Object;)Ljava/lang/Object;", "receiveSuspend", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerSelectReceiveMode", "(Lkotlinx/coroutines/selects/SelectInstance;ILkotlin/jvm/functions/Function2;)V", "removeReceiveOnCancel", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "takeFirstReceiveOrPeekClosed", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "tryStartBlockUnintercepted", "value", "(Lkotlin/jvm/functions/Function2;Lkotlinx/coroutines/selects/SelectInstance;ILjava/lang/Object;)V", "Itr", "ReceiveElement", "ReceiveElementWithUndeliveredHandler", "ReceiveHasNext", "ReceiveSelect", "RemoveReceiveOnCancel", "TryPollDesc", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
public abstract class AbstractChannel<E> extends AbstractSendChannel<E> implements Channel<E> {
    protected abstract boolean isBufferAlwaysEmpty();

    protected abstract boolean isBufferEmpty();

    protected void onReceiveDequeued() {
    }

    protected void onReceiveEnqueued() {
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ void cancel() {
        cancel((CancellationException) null);
    }

    public AbstractChannel(Function1<? super E, Unit> function1) {
        super(function1);
    }

    protected Object pollInternal() {
        while (true) {
            Send sendTakeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
            if (sendTakeFirstSendOrPeekClosed == null) {
                return AbstractChannelKt.POLL_FAILED;
            }
            Symbol symbolTryResumeSend = sendTakeFirstSendOrPeekClosed.tryResumeSend(null);
            if (symbolTryResumeSend != null) {
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(symbolTryResumeSend == CancellableContinuationImplKt.RESUME_TOKEN)) {
                        throw new AssertionError();
                    }
                }
                sendTakeFirstSendOrPeekClosed.completeResumeSend();
                return sendTakeFirstSendOrPeekClosed.getElement();
            }
            sendTakeFirstSendOrPeekClosed.undeliveredElement();
        }
    }

    protected Object pollSelectInternal(SelectInstance<?> select) {
        TryPollDesc<E> tryPollDescDescribeTryPoll = describeTryPoll();
        Object objPerformAtomicTrySelect = select.performAtomicTrySelect(tryPollDescDescribeTryPoll);
        if (objPerformAtomicTrySelect != null) {
            return objPerformAtomicTrySelect;
        }
        tryPollDescDescribeTryPoll.getResult().completeResumeSend();
        return tryPollDescDescribeTryPoll.getResult().getElement();
    }

    protected final boolean getHasReceiveOrClosed() {
        return getQueue().getNextNode() instanceof ReceiveOrClosed;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        return getClosedForReceive() != null && isBufferEmpty();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        return isEmptyImpl();
    }

    protected final boolean isEmptyImpl() {
        return !(getQueue().getNextNode() instanceof Send) && isBufferEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final Object receive(Continuation<? super E> continuation) {
        Object objPollInternal = pollInternal();
        return (objPollInternal == AbstractChannelKt.POLL_FAILED || (objPollInternal instanceof Closed)) ? receiveSuspend(0, continuation) : objPollInternal;
    }

    protected boolean enqueueReceiveInternal(Receive<? super E> receive) {
        int iTryCondAddNext;
        LockFreeLinkedListNode prevOrNull;
        if (isBufferAlwaysEmpty()) {
            LockFreeLinkedListHead queue = getQueue();
            do {
                prevOrNull = queue.getPrevOrNull();
                if (prevOrNull == null || !(!(prevOrNull instanceof Send))) {
                }
            } while (!prevOrNull.addNext(receive, queue));
            return true;
        }
        LockFreeLinkedListHead queue2 = getQueue();
        final Receive<? super E> receive2 = receive;
        LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(receive2) { // from class: kotlinx.coroutines.channels.AbstractChannel$enqueueReceiveInternal$$inlined$addLastIfPrevAndIf$1
            @Override // kotlinx.coroutines.internal.AtomicOp
            public Object prepare(LockFreeLinkedListNode affected) {
                if (this.isBufferEmpty()) {
                    return null;
                }
                return LockFreeLinkedListKt.getCONDITION_FALSE();
            }
        };
        do {
            LockFreeLinkedListNode prevOrNull2 = queue2.getPrevOrNull();
            if (prevOrNull2 == null || !(!(prevOrNull2 instanceof Send))) {
                break;
            }
            iTryCondAddNext = prevOrNull2.tryCondAddNext(receive2, queue2, condAddOp);
            if (iTryCondAddNext == 1) {
                return true;
            }
        } while (iTryCondAddNext != 2);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean enqueueReceive(Receive<? super E> receive) {
        boolean zEnqueueReceiveInternal = enqueueReceiveInternal(receive);
        if (zEnqueueReceiveInternal) {
            onReceiveEnqueued();
        }
        return zEnqueueReceiveInternal;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final Object receiveOrNull(Continuation<? super E> continuation) {
        Object objPollInternal = pollInternal();
        return (objPollInternal == AbstractChannelKt.POLL_FAILED || (objPollInternal instanceof Closed)) ? receiveSuspend(1, continuation) : objPollInternal;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final E receiveOrNullResult(Object result) throws Throwable {
        if (!(result instanceof Closed)) {
            return result;
        }
        Closed closed = (Closed) result;
        if (closed.closeCause == null) {
            return null;
        }
        throw StackTraceRecoveryKt.recoverStackTrace(closed.closeCause);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* JADX INFO: renamed from: receiveOrClosed-ZYPwvRU, reason: not valid java name */
    public final Object mo1375receiveOrClosedZYPwvRU(Continuation<? super ValueOrClosed<? extends E>> continuation) {
        AbstractChannel$receiveOrClosed$1 abstractChannel$receiveOrClosed$1;
        if (continuation instanceof AbstractChannel$receiveOrClosed$1) {
            abstractChannel$receiveOrClosed$1 = (AbstractChannel$receiveOrClosed$1) continuation;
            if ((abstractChannel$receiveOrClosed$1.label & Integer.MIN_VALUE) != 0) {
                abstractChannel$receiveOrClosed$1.label -= Integer.MIN_VALUE;
            } else {
                abstractChannel$receiveOrClosed$1 = new AbstractChannel$receiveOrClosed$1(this, continuation);
            }
        } else {
            abstractChannel$receiveOrClosed$1 = new AbstractChannel$receiveOrClosed$1(this, continuation);
        }
        Object objReceiveSuspend = abstractChannel$receiveOrClosed$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = abstractChannel$receiveOrClosed$1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objReceiveSuspend);
            Object objPollInternal = pollInternal();
            if (objPollInternal == AbstractChannelKt.POLL_FAILED) {
                abstractChannel$receiveOrClosed$1.L$0 = this;
                abstractChannel$receiveOrClosed$1.L$1 = objPollInternal;
                abstractChannel$receiveOrClosed$1.label = 1;
                objReceiveSuspend = receiveSuspend(2, abstractChannel$receiveOrClosed$1);
                if (objReceiveSuspend == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (!(objPollInternal instanceof Closed)) {
                    ValueOrClosed.Companion companion = ValueOrClosed.INSTANCE;
                    return ValueOrClosed.m1378constructorimpl(objPollInternal);
                }
                ValueOrClosed.Companion companion2 = ValueOrClosed.INSTANCE;
                return ValueOrClosed.m1378constructorimpl(new ValueOrClosed.Closed(((Closed) objPollInternal).closeCause));
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Object obj = abstractChannel$receiveOrClosed$1.L$1;
            ResultKt.throwOnFailure(objReceiveSuspend);
        }
        return ((ValueOrClosed) objReceiveSuspend).getHolder();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final E poll() {
        Object objPollInternal = pollInternal();
        if (objPollInternal == AbstractChannelKt.POLL_FAILED) {
            return null;
        }
        return receiveOrNullResult(objPollInternal);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel(CancellationException cause) {
        if (isClosedForReceive()) {
            return;
        }
        if (cause == null) {
            cause = new CancellationException(DebugStringsKt.getClassSimpleName(this) + " was cancelled");
        }
        cancel(cause);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* JADX INFO: renamed from: cancelInternal$kotlinx_coroutines_core, reason: merged with bridge method [inline-methods] */
    public final boolean cancel(Throwable cause) {
        boolean zClose = close(cause);
        onCancelIdempotent(zClose);
        return zClose;
    }

    protected void onCancelIdempotent(boolean wasClosed) {
        Closed<?> closedForSend = getClosedForSend();
        if (closedForSend == null) {
            throw new IllegalStateException("Cannot happen".toString());
        }
        Object objM1401constructorimpl$default = InlineList.m1401constructorimpl$default(null, 1, null);
        while (true) {
            LockFreeLinkedListNode prevNode = closedForSend.getPrevNode();
            if ((prevNode instanceof LockFreeLinkedListHead) || prevNode == closedForSend) {
                break;
            }
            if (DebugKt.getASSERTIONS_ENABLED() && !(prevNode instanceof Send)) {
                throw new AssertionError();
            }
            if (!prevNode.remove()) {
                prevNode.helpRemove();
            } else {
                if (prevNode == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.Send");
                }
                objM1401constructorimpl$default = InlineList.m1406plusUZ7vuAc(objM1401constructorimpl$default, (Send) prevNode);
            }
        }
        if (objM1401constructorimpl$default == null) {
            return;
        }
        if (!(objM1401constructorimpl$default instanceof ArrayList)) {
            ((Send) objM1401constructorimpl$default).resumeSendClosed(closedForSend);
            return;
        }
        if (objM1401constructorimpl$default != null) {
            ArrayList arrayList = (ArrayList) objM1401constructorimpl$default;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((Send) arrayList.get(size)).resumeSendClosed(closedForSend);
            }
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<E> /* = java.util.ArrayList<E> */");
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final ChannelIterator<E> iterator() {
        return new Itr(this);
    }

    protected final TryPollDesc<E> describeTryPoll() {
        return new TryPollDesc<>(getQueue());
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0014\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\u0016\u0010\f\u001a\u0004\u0018\u00010\t2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$TryPollDesc;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/internal/RemoveFirstDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;)V", "failure", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "onPrepare", "prepareOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "Lkotlinx/coroutines/internal/PrepareOp;", "onRemoved", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    protected static final class TryPollDesc<E> extends LockFreeLinkedListNode.RemoveFirstDesc<Send> {
        public TryPollDesc(LockFreeLinkedListHead lockFreeLinkedListHead) {
            super(lockFreeLinkedListHead);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc, kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected Object failure(LockFreeLinkedListNode affected) {
            if (affected instanceof Closed) {
                return affected;
            }
            if (affected instanceof Send) {
                return null;
            }
            return AbstractChannelKt.POLL_FAILED;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public Object onPrepare(LockFreeLinkedListNode.PrepareOp prepareOp) {
            LockFreeLinkedListNode lockFreeLinkedListNode = prepareOp.affected;
            if (lockFreeLinkedListNode == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.Send");
            }
            Symbol symbolTryResumeSend = ((Send) lockFreeLinkedListNode).tryResumeSend(prepareOp);
            if (symbolTryResumeSend == null) {
                return LockFreeLinkedList_commonKt.REMOVE_PREPARED;
            }
            if (symbolTryResumeSend == AtomicKt.RETRY_ATOMIC) {
                return AtomicKt.RETRY_ATOMIC;
            }
            if (!DebugKt.getASSERTIONS_ENABLED()) {
                return null;
            }
            if (symbolTryResumeSend == CancellableContinuationImplKt.RESUME_TOKEN) {
                return null;
            }
            throw new AssertionError();
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public void onRemoved(LockFreeLinkedListNode affected) {
            if (affected == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.Send");
            }
            ((Send) affected).undeliveredElement();
        }
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final SelectClause1<E> getOnReceive() {
        return new SelectClause1<E>() { // from class: kotlinx.coroutines.channels.AbstractChannel$onReceive$1
            @Override // kotlinx.coroutines.selects.SelectClause1
            public <R> void registerSelectClause1(SelectInstance<? super R> select, Function2<? super E, ? super Continuation<? super R>, ? extends Object> block) throws Throwable {
                AbstractChannel abstractChannel = this.this$0;
                if (block == null) {
                    throw new NullPointerException("null cannot be cast to non-null type suspend (kotlin.Any?) -> R");
                }
                abstractChannel.registerSelectReceiveMode(select, 0, block);
            }
        };
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final SelectClause1<E> getOnReceiveOrNull() {
        return new SelectClause1<E>() { // from class: kotlinx.coroutines.channels.AbstractChannel$onReceiveOrNull$1
            @Override // kotlinx.coroutines.selects.SelectClause1
            public <R> void registerSelectClause1(SelectInstance<? super R> select, Function2<? super E, ? super Continuation<? super R>, ? extends Object> block) throws Throwable {
                AbstractChannel abstractChannel = this.this$0;
                if (block == null) {
                    throw new NullPointerException("null cannot be cast to non-null type suspend (kotlin.Any?) -> R");
                }
                abstractChannel.registerSelectReceiveMode(select, 1, block);
            }
        };
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final SelectClause1<ValueOrClosed<E>> getOnReceiveOrClosed() {
        return new SelectClause1<ValueOrClosed<? extends E>>() { // from class: kotlinx.coroutines.channels.AbstractChannel$onReceiveOrClosed$1
            @Override // kotlinx.coroutines.selects.SelectClause1
            public <R> void registerSelectClause1(SelectInstance<? super R> select, Function2<? super ValueOrClosed<? extends E>, ? super Continuation<? super R>, ? extends Object> block) throws Throwable {
                AbstractChannel abstractChannel = this.this$0;
                if (block == null) {
                    throw new NullPointerException("null cannot be cast to non-null type suspend (kotlin.Any?) -> R");
                }
                abstractChannel.registerSelectReceiveMode(select, 2, block);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <R> void registerSelectReceiveMode(SelectInstance<? super R> select, int receiveMode, Function2<Object, ? super Continuation<? super R>, ? extends Object> block) throws Throwable {
        while (!select.isSelected()) {
            if (isEmptyImpl()) {
                if (enqueueReceiveSelect(select, block, receiveMode)) {
                    return;
                }
            } else {
                Object objPollSelectInternal = pollSelectInternal(select);
                if (objPollSelectInternal == SelectKt.getALREADY_SELECTED()) {
                    return;
                }
                if (objPollSelectInternal != AbstractChannelKt.POLL_FAILED && objPollSelectInternal != AtomicKt.RETRY_ATOMIC) {
                    tryStartBlockUnintercepted(block, select, receiveMode, objPollSelectInternal);
                }
            }
        }
    }

    private final <R> void tryStartBlockUnintercepted(Function2<Object, ? super Continuation<? super R>, ? extends Object> function2, SelectInstance<? super R> selectInstance, int i, Object obj) throws Throwable {
        Object objM1378constructorimpl;
        boolean z = obj instanceof Closed;
        if (!z) {
            if (i != 2) {
                UndispatchedKt.startCoroutineUnintercepted(function2, obj, selectInstance.getCompletion());
                return;
            }
            ValueOrClosed.Companion companion = ValueOrClosed.INSTANCE;
            if (z) {
                objM1378constructorimpl = ValueOrClosed.m1378constructorimpl(new ValueOrClosed.Closed(((Closed) obj).closeCause));
            } else {
                objM1378constructorimpl = ValueOrClosed.m1378constructorimpl(obj);
            }
            UndispatchedKt.startCoroutineUnintercepted(function2, ValueOrClosed.m1377boximpl(objM1378constructorimpl), selectInstance.getCompletion());
            return;
        }
        if (i == 0) {
            throw StackTraceRecoveryKt.recoverStackTrace(((Closed) obj).getReceiveException());
        }
        if (i != 1) {
            if (i == 2 && selectInstance.trySelect()) {
                ValueOrClosed.Companion companion2 = ValueOrClosed.INSTANCE;
                UndispatchedKt.startCoroutineUnintercepted(function2, ValueOrClosed.m1377boximpl(ValueOrClosed.m1378constructorimpl(new ValueOrClosed.Closed(((Closed) obj).closeCause))), selectInstance.getCompletion());
                return;
            }
            return;
        }
        Closed closed = (Closed) obj;
        if (closed.closeCause == null) {
            if (selectInstance.trySelect()) {
                UndispatchedKt.startCoroutineUnintercepted(function2, null, selectInstance.getCompletion());
                return;
            }
            return;
        }
        throw StackTraceRecoveryKt.recoverStackTrace(closed.getReceiveException());
    }

    private final <R> boolean enqueueReceiveSelect(SelectInstance<? super R> select, Function2<Object, ? super Continuation<? super R>, ? extends Object> block, int receiveMode) {
        ReceiveSelect receiveSelect = new ReceiveSelect(this, select, block, receiveMode);
        boolean zEnqueueReceive = enqueueReceive(receiveSelect);
        if (zEnqueueReceive) {
            select.disposeOnSelect(receiveSelect);
        }
        return zEnqueueReceive;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        ReceiveOrClosed<E> receiveOrClosedTakeFirstReceiveOrPeekClosed = super.takeFirstReceiveOrPeekClosed();
        if (receiveOrClosedTakeFirstReceiveOrPeekClosed != null && !(receiveOrClosedTakeFirstReceiveOrPeekClosed instanceof Closed)) {
            onReceiveDequeued();
        }
        return receiveOrClosedTakeFirstReceiveOrPeekClosed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeReceiveOnCancel(CancellableContinuation<?> cont, Receive<?> receive) {
        cont.invokeOnCancellation(new RemoveReceiveOnCancel(receive));
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u0012\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$RemoveReceiveOnCancel;", "Lkotlinx/coroutines/BeforeResumeCancelHandler;", "receive", "Lkotlinx/coroutines/channels/Receive;", "(Lkotlinx/coroutines/channels/AbstractChannel;Lkotlinx/coroutines/channels/Receive;)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    private final class RemoveReceiveOnCancel extends BeforeResumeCancelHandler {
        private final Receive<?> receive;

        public RemoveReceiveOnCancel(Receive<?> receive) {
            this.receive = receive;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        @Override // kotlinx.coroutines.CancelHandlerBase
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2(Throwable cause) {
            if (this.receive.remove()) {
                AbstractChannel.this.onReceiveDequeued();
            }
        }

        public String toString() {
            return "RemoveReceiveOnCancel[" + this.receive + ']';
        }
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0018\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u00028\u00010\u0016B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0007\u001a\u00020\u0006H\u0096Bø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\u000b\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\bJ\u0010\u0010\u000e\u001a\u00028\u0001H\u0096\u0002¢\u0006\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u00028\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0010R(\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\t8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$Itr;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/AbstractChannel;", "channel", "<init>", "(Lkotlinx/coroutines/channels/AbstractChannel;)V", "", "hasNext", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "result", "hasNextResult", "(Ljava/lang/Object;)Z", "hasNextSuspend", "next", "()Ljava/lang/Object;", "Lkotlinx/coroutines/channels/AbstractChannel;", "value", "getResult", "setResult", "(Ljava/lang/Object;)V", "kotlinx-coroutines-core", "Lkotlinx/coroutines/channels/ChannelIterator;"}, k = 1, mv = {1, 4, 0})
    private static final class Itr<E> implements ChannelIterator<E> {
        private volatile Object _result = AbstractChannelKt.POLL_FAILED;
        public final AbstractChannel<E> channel;

        public Itr(AbstractChannel<E> abstractChannel) {
            this.channel = abstractChannel;
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.3.0, binary compatibility with versions <= 1.2.x")
        public /* synthetic */ Object next(Continuation<? super E> continuation) {
            return ChannelIterator.DefaultImpls.next(this, continuation);
        }

        /* JADX INFO: renamed from: getResult, reason: from getter */
        public final Object get_result() {
            return this._result;
        }

        public final void setResult(Object obj) {
            this._result = obj;
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public Object hasNext(Continuation<? super Boolean> continuation) {
            if (get_result() != AbstractChannelKt.POLL_FAILED) {
                return Boxing.boxBoolean(hasNextResult(get_result()));
            }
            setResult(this.channel.pollInternal());
            return get_result() != AbstractChannelKt.POLL_FAILED ? Boxing.boxBoolean(hasNextResult(get_result())) : hasNextSuspend(continuation);
        }

        private final boolean hasNextResult(Object result) throws Throwable {
            if (!(result instanceof Closed)) {
                return true;
            }
            Closed closed = (Closed) result;
            if (closed.closeCause == null) {
                return false;
            }
            throw StackTraceRecoveryKt.recoverStackTrace(closed.getReceiveException());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlinx.coroutines.channels.ChannelIterator
        public E next() throws Throwable {
            E e = (E) get_result();
            if (e instanceof Closed) {
                throw StackTraceRecoveryKt.recoverStackTrace(((Closed) e).getReceiveException());
            }
            if (e != AbstractChannelKt.POLL_FAILED) {
                setResult(AbstractChannelKt.POLL_FAILED);
                return e;
            }
            throw new IllegalStateException("'hasNext' should be called prior to 'next' invocation");
        }

        final /* synthetic */ Object hasNextSuspend(Continuation<? super Boolean> continuation) {
            CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation));
            CancellableContinuationImpl cancellableContinuationImpl = orCreateCancellableContinuation;
            ReceiveHasNext receiveHasNext = new ReceiveHasNext(this, cancellableContinuationImpl);
            while (true) {
                ReceiveHasNext receiveHasNext2 = receiveHasNext;
                if (this.channel.enqueueReceive(receiveHasNext2)) {
                    this.channel.removeReceiveOnCancel(cancellableContinuationImpl, receiveHasNext2);
                    break;
                }
                Object objPollInternal = this.channel.pollInternal();
                setResult(objPollInternal);
                if (objPollInternal instanceof Closed) {
                    Closed closed = (Closed) objPollInternal;
                    if (closed.closeCause == null) {
                        Boolean boolBoxBoolean = Boxing.boxBoolean(false);
                        Result.Companion companion = Result.INSTANCE;
                        cancellableContinuationImpl.resumeWith(Result.m37constructorimpl(boolBoxBoolean));
                        break;
                    }
                    Throwable receiveException = closed.getReceiveException();
                    Result.Companion companion2 = Result.INSTANCE;
                    cancellableContinuationImpl.resumeWith(Result.m37constructorimpl(ResultKt.createFailure(receiveException)));
                    break;
                }
                if (objPollInternal != AbstractChannelKt.POLL_FAILED) {
                    Boolean boolBoxBoolean2 = Boxing.boxBoolean(true);
                    Function1<E, Unit> function1 = this.channel.onUndeliveredElement;
                    cancellableContinuationImpl.resume(boolBoxBoolean2, function1 != null ? OnUndeliveredElementKt.bindCancellationFun(function1, objPollInternal, cancellableContinuationImpl.getContext()) : null);
                    break;
                }
            }
            Object result = orCreateCancellableContinuation.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result;
        }
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\b\u0012\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00002\b\u0012\u0004\u0012\u00028\u00010\u001fB\u001f\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u000f\u001a\u00020\n2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u0004\u0018\u00010\u00032\u0006\u0010\t\u001a\u00028\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J#\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\t\u001a\u00028\u00012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u001e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00028D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\u0006\u001a\u00020\u00058\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u001d¨\u0006\u001e"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveElement;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/CancellableContinuation;", "", "cont", "", "receiveMode", "<init>", "(Lkotlinx/coroutines/CancellableContinuation;I)V", "value", "", "completeResumeReceive", "(Ljava/lang/Object;)V", "Lkotlinx/coroutines/channels/Closed;", "closed", "resumeReceiveClosed", "(Lkotlinx/coroutines/channels/Closed;)V", "resumeValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "", "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "otherOp", "Lkotlinx/coroutines/internal/Symbol;", "tryResumeReceive", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "getCont", "()Lkotlinx/coroutines/CancellableContinuation;", "I", "kotlinx-coroutines-core", "Lkotlinx/coroutines/channels/Receive;"}, k = 1, mv = {1, 4, 0})
    private static class ReceiveElement<E> extends Receive<E> {
        private static final AtomicReferenceFieldUpdater _cont$FU = AtomicReferenceFieldUpdater.newUpdater(ReceiveElement.class, Object.class, "_cont");
        private volatile Object _cont;
        public final int receiveMode;

        public ReceiveElement(CancellableContinuation<Object> cancellableContinuation, int i) {
            this.receiveMode = i;
            this._cont = cancellableContinuation;
        }

        protected final CancellableContinuation<Object> getCont() {
            Object obj = this._cont;
            Intrinsics.checkNotNull(obj);
            return (CancellableContinuation) obj;
        }

        public final Object resumeValue(E value) {
            if (this.receiveMode != 2) {
                return value;
            }
            ValueOrClosed.Companion companion = ValueOrClosed.INSTANCE;
            return ValueOrClosed.m1377boximpl(ValueOrClosed.m1378constructorimpl(value));
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public Symbol tryResumeReceive(E value, LockFreeLinkedListNode.PrepareOp otherOp) {
            CancellableContinuation cancellableContinuation = (CancellableContinuation) this._cont;
            if (cancellableContinuation != null) {
                Object objTryResume = cancellableContinuation.tryResume(resumeValue(value), otherOp != null ? otherOp.desc : null, resumeOnCancellationFun(value));
                if (objTryResume != null) {
                    if (DebugKt.getASSERTIONS_ENABLED()) {
                        if (!(objTryResume == CancellableContinuationImplKt.RESUME_TOKEN)) {
                            throw new AssertionError();
                        }
                    }
                    if (otherOp != null) {
                        otherOp.finishPrepare();
                    }
                    return CancellableContinuationImplKt.RESUME_TOKEN;
                }
            }
            return null;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E value) {
            Object andSet = _cont$FU.getAndSet(this, null);
            Intrinsics.checkNotNull(andSet);
            ((CancellableContinuation) andSet).completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(Closed<?> closed) {
            Object andSet = _cont$FU.getAndSet(this, null);
            Intrinsics.checkNotNull(andSet);
            CancellableContinuation cancellableContinuation = (CancellableContinuation) andSet;
            if (this.receiveMode == 1 && closed.closeCause == null) {
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m37constructorimpl(null));
            } else if (this.receiveMode != 2) {
                Throwable receiveException = closed.getReceiveException();
                Result.Companion companion2 = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m37constructorimpl(ResultKt.createFailure(receiveException)));
            } else {
                ValueOrClosed.Companion companion3 = ValueOrClosed.INSTANCE;
                ValueOrClosed valueOrClosedM1377boximpl = ValueOrClosed.m1377boximpl(ValueOrClosed.m1378constructorimpl(new ValueOrClosed.Closed(closed.closeCause)));
                Result.Companion companion4 = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m37constructorimpl(valueOrClosedM1377boximpl));
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "ReceiveElement@" + DebugStringsKt.getHexAddress(this) + "[receiveMode=" + this.receiveMode + ']';
        }
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B;\u0012\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u001c\u0010\b\u001a\u0018\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00028\u0001`\u000b¢\u0006\u0002\u0010\fJ#\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0012R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\b\u001a\u0018\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00028\u0001`\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveElementWithUndeliveredHandler;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/AbstractChannel$ReceiveElement;", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "receiveMode", "", "onUndeliveredElement", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "(Lkotlinx/coroutines/CancellableContinuation;ILkotlin/jvm/functions/Function1;)V", "context", "Lkotlin/coroutines/CoroutineContext;", "resumeOnCancellationFun", "", "value", "(Ljava/lang/Object;)Lkotlin/jvm/functions/Function1;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    private static final class ReceiveElementWithUndeliveredHandler<E> extends ReceiveElement<E> {
        private final CoroutineContext context;
        public final Function1<E, Unit> onUndeliveredElement;

        /* JADX WARN: Multi-variable type inference failed */
        public ReceiveElementWithUndeliveredHandler(CancellableContinuation<Object> cancellableContinuation, int i, Function1<? super E, Unit> function1) {
            super(cancellableContinuation, i);
            this.onUndeliveredElement = function1;
            this.context = cancellableContinuation.getContext();
        }

        @Override // kotlinx.coroutines.channels.Receive
        public Function1<Throwable, Unit> resumeOnCancellationFun(E value) {
            return OnUndeliveredElementKt.bindCancellationFun(this.onUndeliveredElement, value, this.context);
        }
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\b\u0012\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u00028\u00010\"B#\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u000b\u0010\fJ%\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\n\u0018\u00010\r2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0013\u001a\u00020\n2\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J#\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\t\u001a\u00028\u00012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001e\u001a\u00020\u001d8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u00028\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010 ¨\u0006!"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveHasNext;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/AbstractChannel$Itr;", "iterator", "Lkotlinx/coroutines/CancellableContinuation;", "", "cont", "<init>", "(Lkotlinx/coroutines/channels/AbstractChannel$Itr;Lkotlinx/coroutines/CancellableContinuation;)V", "value", "", "completeResumeReceive", "(Ljava/lang/Object;)V", "Lkotlin/Function1;", "", "resumeOnCancellationFun", "(Ljava/lang/Object;)Lkotlin/jvm/functions/Function1;", "Lkotlinx/coroutines/channels/Closed;", "closed", "resumeReceiveClosed", "(Lkotlinx/coroutines/channels/Closed;)V", "", "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "otherOp", "Lkotlinx/coroutines/internal/Symbol;", "tryResumeReceive", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "Lkotlin/coroutines/CoroutineContext;", "context", "Lkotlin/coroutines/CoroutineContext;", "Lkotlinx/coroutines/channels/AbstractChannel$Itr;", "kotlinx-coroutines-core", "Lkotlinx/coroutines/channels/Receive;"}, k = 1, mv = {1, 4, 0})
    private static class ReceiveHasNext<E> extends Receive<E> {
        private static final AtomicReferenceFieldUpdater _cont$FU = AtomicReferenceFieldUpdater.newUpdater(ReceiveHasNext.class, Object.class, "_cont");
        private volatile Object _cont;
        private final CoroutineContext context;
        public final Itr<E> iterator;

        public ReceiveHasNext(Itr<E> itr, CancellableContinuation<? super Boolean> cancellableContinuation) {
            this.iterator = itr;
            this._cont = cancellableContinuation;
            this.context = cancellableContinuation.getContext();
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public Symbol tryResumeReceive(E value, LockFreeLinkedListNode.PrepareOp otherOp) {
            CancellableContinuation cancellableContinuation = (CancellableContinuation) this._cont;
            if (cancellableContinuation != null) {
                Object objTryResume = cancellableContinuation.tryResume(true, otherOp != null ? otherOp.desc : null, resumeOnCancellationFun(value));
                if (objTryResume != null) {
                    if (DebugKt.getASSERTIONS_ENABLED()) {
                        if (!(objTryResume == CancellableContinuationImplKt.RESUME_TOKEN)) {
                            throw new AssertionError();
                        }
                    }
                    if (otherOp != null) {
                        otherOp.finishPrepare();
                    }
                    return CancellableContinuationImplKt.RESUME_TOKEN;
                }
            }
            return null;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E value) {
            this.iterator.setResult(value);
            Object andSet = _cont$FU.getAndSet(this, null);
            Intrinsics.checkNotNull(andSet);
            ((CancellableContinuation) andSet).completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(Closed<?> closed) {
            Object objTryResumeWithException;
            Object andSet = _cont$FU.getAndSet(this, null);
            Intrinsics.checkNotNull(andSet);
            CancellableContinuation cancellableContinuation = (CancellableContinuation) andSet;
            if (closed.closeCause == null) {
                objTryResumeWithException = CancellableContinuation.DefaultImpls.tryResume$default(cancellableContinuation, false, null, 2, null);
            } else {
                objTryResumeWithException = cancellableContinuation.tryResumeWithException(closed.getReceiveException());
            }
            if (objTryResumeWithException != null) {
                this.iterator.setResult(closed);
                cancellableContinuation.completeResume(objTryResumeWithException);
            }
        }

        @Override // kotlinx.coroutines.channels.Receive
        public Function1<Throwable, Unit> resumeOnCancellationFun(E value) {
            Function1<E, Unit> function1 = this.iterator.channel.onUndeliveredElement;
            if (function1 != null) {
                return OnUndeliveredElementKt.bindCancellationFun(function1, value, this.context);
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "ReceiveHasNext@" + DebugStringsKt.getHexAddress(this);
        }
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u00032\u00020\u0004BR\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b\u0012$\u0010\t\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n\u0012\u0006\u0010\r\u001a\u00020\u000eø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\u0012H\u0016J#\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00172\u0006\u0010\u0013\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u00122\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J!\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010\u0013\u001a\u00028\u00022\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016¢\u0006\u0002\u0010#R3\u0010\t\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n8\u0006X\u0087\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0010R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveSelect;", "R", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/Receive;", "Lkotlinx/coroutines/DisposableHandle;", "channel", "Lkotlinx/coroutines/channels/AbstractChannel;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "", "Lkotlin/coroutines/Continuation;", "receiveMode", "", "(Lkotlinx/coroutines/channels/AbstractChannel;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;I)V", "Lkotlin/jvm/functions/Function2;", "completeResumeReceive", "", "value", "(Ljava/lang/Object;)V", "dispose", "resumeOnCancellationFun", "Lkotlin/Function1;", "", "(Ljava/lang/Object;)Lkotlin/jvm/functions/Function1;", "resumeReceiveClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeReceive", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    private static final class ReceiveSelect<R, E> extends Receive<E> implements DisposableHandle {
        public final Function2<Object, Continuation<? super R>, Object> block;
        public final AbstractChannel<E> channel;
        public final int receiveMode;
        public final SelectInstance<R> select;

        /* JADX WARN: Multi-variable type inference failed */
        public ReceiveSelect(AbstractChannel<E> abstractChannel, SelectInstance<? super R> selectInstance, Function2<Object, ? super Continuation<? super R>, ? extends Object> function2, int i) {
            this.channel = abstractChannel;
            this.select = selectInstance;
            this.receiveMode = i;
            this.block = function2;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public Symbol tryResumeReceive(E value, LockFreeLinkedListNode.PrepareOp otherOp) {
            return (Symbol) this.select.trySelectOther(otherOp);
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E value) {
            Object objM1377boximpl;
            CoroutineStart coroutineStart = CoroutineStart.DEFAULT;
            if (this.receiveMode == 2) {
                ValueOrClosed.Companion companion = ValueOrClosed.INSTANCE;
                objM1377boximpl = ValueOrClosed.m1377boximpl(ValueOrClosed.m1378constructorimpl(value));
            } else {
                objM1377boximpl = value;
            }
            BuildersKt.startCoroutineImpl(coroutineStart, objM1377boximpl, this.select.getCompletion(), resumeOnCancellationFun(value), this.block);
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(Closed<?> closed) {
            if (this.select.trySelect()) {
                int i = this.receiveMode;
                if (i == 0) {
                    this.select.resumeSelectWithException(closed.getReceiveException());
                    return;
                }
                if (i == 1) {
                    if (closed.closeCause != null) {
                        this.select.resumeSelectWithException(closed.getReceiveException());
                        return;
                    } else {
                        BuildersKt.startCoroutineImpl(CoroutineStart.DEFAULT, null, this.select.getCompletion(), (Function1) null, this.block);
                        return;
                    }
                }
                if (i != 2) {
                    return;
                }
                CoroutineStart coroutineStart = CoroutineStart.DEFAULT;
                ValueOrClosed.Companion companion = ValueOrClosed.INSTANCE;
                BuildersKt.startCoroutineImpl(coroutineStart, ValueOrClosed.m1377boximpl(ValueOrClosed.m1378constructorimpl(new ValueOrClosed.Closed(closed.closeCause))), this.select.getCompletion(), (Function1) null, this.block);
            }
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            if (remove()) {
                this.channel.onReceiveDequeued();
            }
        }

        @Override // kotlinx.coroutines.channels.Receive
        public Function1<Throwable, Unit> resumeOnCancellationFun(E value) {
            Function1<E, Unit> function1 = this.channel.onUndeliveredElement;
            if (function1 != null) {
                return OnUndeliveredElementKt.bindCancellationFun(function1, value, this.select.getCompletion().getContext());
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "ReceiveSelect@" + DebugStringsKt.getHexAddress(this) + '[' + this.select + ",receiveMode=" + this.receiveMode + ']';
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    final /* synthetic */ <R> Object receiveSuspend(int i, Continuation<? super R> continuation) {
        ReceiveElement receiveElementWithUndeliveredHandler;
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation));
        CancellableContinuationImpl cancellableContinuationImpl = orCreateCancellableContinuation;
        if (this.onUndeliveredElement == null) {
            if (cancellableContinuationImpl == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Any?>");
            }
            receiveElementWithUndeliveredHandler = new ReceiveElement(cancellableContinuationImpl, i);
        } else if (cancellableContinuationImpl != null) {
            receiveElementWithUndeliveredHandler = new ReceiveElementWithUndeliveredHandler(cancellableContinuationImpl, i, this.onUndeliveredElement);
        } else {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Any?>");
        }
        while (true) {
            Receive receive = receiveElementWithUndeliveredHandler;
            if (enqueueReceive(receive)) {
                removeReceiveOnCancel(cancellableContinuationImpl, receive);
                break;
            }
            Object objPollInternal = pollInternal();
            if (objPollInternal instanceof Closed) {
                receiveElementWithUndeliveredHandler.resumeReceiveClosed((Closed) objPollInternal);
                break;
            }
            if (objPollInternal != AbstractChannelKt.POLL_FAILED) {
                cancellableContinuationImpl.resume(receiveElementWithUndeliveredHandler.resumeValue(objPollInternal), receiveElementWithUndeliveredHandler.resumeOnCancellationFun(objPollInternal));
                break;
            }
        }
        Object result = orCreateCancellableContinuation.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
