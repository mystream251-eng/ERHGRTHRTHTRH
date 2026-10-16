package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.BuildersKt;
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
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

/* JADX INFO: compiled from: AbstractChannel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000;:\u0004ghijB)\u0012 \u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ#\u0010\u0013\u001a\u000e\u0012\u0002\b\u00030\u0011j\u0006\u0012\u0002\b\u0003`\u00122\u0006\u0010\u0010\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u00152\u0006\u0010\u0010\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u0016\u0010\u0017J$\u0010\u001d\u001a\u00020\u00032\u0012\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00190\u0018H\u0080\b¢\u0006\u0004\b\u001b\u0010\u001cJ\u0019\u0010!\u001a\u0004\u0018\u00010 2\u0006\u0010\u001f\u001a\u00020\u001eH\u0014¢\u0006\u0004\b!\u0010\"J\u001b\u0010#\u001a\u00020\u00032\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0002¢\u0006\u0004\b#\u0010$J#\u0010%\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00028\u00002\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0002¢\u0006\u0004\b%\u0010&J)\u0010)\u001a\u00020\u00032\u0018\u0010(\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`'H\u0016¢\u0006\u0004\b)\u0010\u0007J\u0019\u0010*\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b*\u0010+J\u0015\u0010,\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00028\u0000¢\u0006\u0004\b,\u0010-J\u0017\u0010.\u001a\u00020 2\u0006\u0010\u0010\u001a\u00028\u0000H\u0014¢\u0006\u0004\b.\u0010/J#\u00102\u001a\u00020 2\u0006\u0010\u0010\u001a\u00028\u00002\n\u00101\u001a\u0006\u0012\u0002\b\u000300H\u0014¢\u0006\u0004\b2\u00103J\u0017\u00105\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u000204H\u0014¢\u0006\u0004\b5\u00106J\u000f\u00107\u001a\u000204H\u0002¢\u0006\u0004\b7\u00108JX\u0010>\u001a\u00020\u0003\"\u0004\b\u0001\u001092\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u0001002\u0006\u0010\u0010\u001a\u00028\u00002(\u0010=\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000;\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010<\u0012\u0006\u0012\u0004\u0018\u00010 0:H\u0002ø\u0001\u0000¢\u0006\u0004\b>\u0010?J\u001b\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00028\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010@J\u001d\u0010B\u001a\b\u0012\u0002\b\u0003\u0018\u00010A2\u0006\u0010\u0010\u001a\u00028\u0000H\u0004¢\u0006\u0004\bB\u0010CJ\u001b\u0010D\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00028\u0000H\u0082@ø\u0001\u0000¢\u0006\u0004\bD\u0010@J\u0017\u0010E\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010AH\u0014¢\u0006\u0004\bE\u0010FJ\u0011\u0010G\u001a\u0004\u0018\u00010\u001eH\u0004¢\u0006\u0004\bG\u0010HJ\u000f\u0010J\u001a\u00020IH\u0016¢\u0006\u0004\bJ\u0010KJ+\u0010L\u001a\u00020\u0003*\u0006\u0012\u0002\b\u00030<2\u0006\u0010\u0010\u001a\u00028\u00002\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0002¢\u0006\u0004\bL\u0010MR\u0016\u0010O\u001a\u00020I8T@\u0014X\u0094\u0004¢\u0006\u0006\u001a\u0004\bN\u0010KR\u001c\u0010R\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00198D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\bP\u0010QR\u001c\u0010T\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00198D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\bS\u0010QR\u0016\u0010U\u001a\u00020\n8$@$X¤\u0004¢\u0006\u0006\u001a\u0004\bU\u0010VR\u0016\u0010W\u001a\u00020\n8$@$X¤\u0004¢\u0006\u0006\u001a\u0004\bW\u0010VR\u0013\u0010X\u001a\u00020\n8F@\u0006¢\u0006\u0006\u001a\u0004\bX\u0010VR\u0016\u0010Y\u001a\u00020\n8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\bY\u0010VR\u0016\u0010Z\u001a\u00020\n8D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\bZ\u0010VR%\u0010^\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000;0[8F@\u0006¢\u0006\u0006\u001a\u0004\b\\\u0010]R0\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u00048\u0004@\u0005X\u0085\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010_R\u001c\u0010a\u001a\u00020`8\u0004@\u0004X\u0084\u0004¢\u0006\f\n\u0004\ba\u0010b\u001a\u0004\bc\u0010dR\u0016\u0010f\u001a\u00020I8B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\be\u0010K\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006k"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel;", ExifInterface.LONGITUDE_EAST, "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "onUndeliveredElement", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "", "cause", "", ToolboxAnalyticsModuleKt.EVENT_CLOSE, "(Ljava/lang/Throwable;)Z", "", "countQueueSize", "()I", "element", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/internal/AddLastDesc;", "describeSendBuffered", "(Ljava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "describeTryOffer", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "Lkotlin/Function0;", "Lkotlinx/coroutines/channels/Closed;", "closed", "disposeQueue$kotlinx_coroutines_core", "(Lkotlin/jvm/functions/Function0;)V", "disposeQueue", "Lkotlinx/coroutines/channels/Send;", "send", "", "enqueueSend", "(Lkotlinx/coroutines/channels/Send;)Ljava/lang/Object;", "helpClose", "(Lkotlinx/coroutines/channels/Closed;)V", "helpCloseAndGetSendException", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/Closed;)Ljava/lang/Throwable;", "Lkotlinx/coroutines/channels/Handler;", "handler", "invokeOnClose", "invokeOnCloseHandler", "(Ljava/lang/Throwable;)V", "offer", "(Ljava/lang/Object;)Z", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "offerSelectInternal", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "onClosedIdempotent", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "queueTail", "()Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "R", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", "block", "registerSelectSend", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "sendBuffered", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/ReceiveOrClosed;", "sendSuspend", "takeFirstReceiveOrPeekClosed", "()Lkotlinx/coroutines/channels/ReceiveOrClosed;", "takeFirstSendOrPeekClosed", "()Lkotlinx/coroutines/channels/Send;", "", "toString", "()Ljava/lang/String;", "helpCloseAndResumeWithSendException", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;Lkotlinx/coroutines/channels/Closed;)V", "getBufferDebugString", "bufferDebugString", "getClosedForReceive", "()Lkotlinx/coroutines/channels/Closed;", "closedForReceive", "getClosedForSend", "closedForSend", "isBufferAlwaysFull", "()Z", "isBufferFull", "isClosedForSend", "isFull", "isFullImpl", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "onSend", "Lkotlin/jvm/functions/Function1;", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "getQueue", "()Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "getQueueDebugStateString", "queueDebugStateString", "SendBuffered", "SendBufferedDesc", "SendSelect", "TryOfferDesc", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
public abstract class AbstractSendChannel<E> implements SendChannel<E> {
    private static final AtomicReferenceFieldUpdater onCloseHandler$FU = AtomicReferenceFieldUpdater.newUpdater(AbstractSendChannel.class, Object.class, "onCloseHandler");
    protected final Function1<E, Unit> onUndeliveredElement;
    private final LockFreeLinkedListHead queue = new LockFreeLinkedListHead();
    private volatile Object onCloseHandler = null;

    public final void disposeQueue$kotlinx_coroutines_core(Function0<? extends Closed<?>> closed) {
    }

    protected String getBufferDebugString() {
        return "";
    }

    protected abstract boolean isBufferAlwaysFull();

    protected abstract boolean isBufferFull();

    protected void onClosedIdempotent(LockFreeLinkedListNode closed) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AbstractSendChannel(Function1<? super E, Unit> function1) {
        this.onUndeliveredElement = function1;
    }

    protected final LockFreeLinkedListHead getQueue() {
        return this.queue;
    }

    protected Object offerInternal(E element) {
        ReceiveOrClosed<E> receiveOrClosedTakeFirstReceiveOrPeekClosed;
        Symbol symbolTryResumeReceive;
        do {
            receiveOrClosedTakeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
            if (receiveOrClosedTakeFirstReceiveOrPeekClosed == null) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            symbolTryResumeReceive = receiveOrClosedTakeFirstReceiveOrPeekClosed.tryResumeReceive(element, null);
        } while (symbolTryResumeReceive == null);
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(symbolTryResumeReceive == CancellableContinuationImplKt.RESUME_TOKEN)) {
                throw new AssertionError();
            }
        }
        receiveOrClosedTakeFirstReceiveOrPeekClosed.completeResumeReceive(element);
        return receiveOrClosedTakeFirstReceiveOrPeekClosed.getOfferResult();
    }

    protected Object offerSelectInternal(E element, SelectInstance<?> select) {
        TryOfferDesc<E> tryOfferDescDescribeTryOffer = describeTryOffer(element);
        Object objPerformAtomicTrySelect = select.performAtomicTrySelect(tryOfferDescDescribeTryOffer);
        if (objPerformAtomicTrySelect != null) {
            return objPerformAtomicTrySelect;
        }
        ReceiveOrClosed<? super E> result = tryOfferDescDescribeTryOffer.getResult();
        result.completeResumeReceive(element);
        return result.getOfferResult();
    }

    protected final Closed<?> getClosedForSend() {
        LockFreeLinkedListNode lockFreeLinkedListNodeQueueTail = queueTail();
        if (!(lockFreeLinkedListNodeQueueTail instanceof Closed)) {
            lockFreeLinkedListNodeQueueTail = null;
        }
        Closed<?> closed = (Closed) lockFreeLinkedListNodeQueueTail;
        if (closed == null) {
            return null;
        }
        helpClose(closed);
        return closed;
    }

    protected final Closed<?> getClosedForReceive() {
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        if (!(nextNode instanceof Closed)) {
            nextNode = null;
        }
        Closed<?> closed = (Closed) nextNode;
        if (closed == null) {
            return null;
        }
        helpClose(closed);
        return closed;
    }

    protected final Send takeFirstSendOrPeekClosed() {
        LockFreeLinkedListNode lockFreeLinkedListNodeRemoveOrNext;
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        while (true) {
            Object next = lockFreeLinkedListHead.getNext();
            if (next == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
            LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
            if (lockFreeLinkedListNode == lockFreeLinkedListHead || !(lockFreeLinkedListNode instanceof Send)) {
                lockFreeLinkedListNode = null;
            } else if ((!(((Send) lockFreeLinkedListNode) instanceof Closed) || lockFreeLinkedListNode.isRemoved()) && (lockFreeLinkedListNodeRemoveOrNext = lockFreeLinkedListNode.removeOrNext()) != null) {
                lockFreeLinkedListNodeRemoveOrNext.helpRemovePrev();
            }
            return (Send) lockFreeLinkedListNode;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected final ReceiveOrClosed<?> sendBuffered(E element) {
        LockFreeLinkedListNode prevOrNull;
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        SendBuffered sendBuffered = new SendBuffered(element);
        do {
            prevOrNull = lockFreeLinkedListHead.getPrevOrNull();
            if (prevOrNull == 0) {
                return null;
            }
            if (prevOrNull instanceof ReceiveOrClosed) {
                return (ReceiveOrClosed) prevOrNull;
            }
        } while (!prevOrNull.addNext(sendBuffered, lockFreeLinkedListHead));
        return null;
    }

    protected final LockFreeLinkedListNode.AddLastDesc<?> describeSendBuffered(E element) {
        return new SendBufferedDesc(this.queue, element);
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0012\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0001¢\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBufferedDesc;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", "Lkotlinx/coroutines/internal/AddLastDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "element", "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;Ljava/lang/Object;)V", "failure", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    private static class SendBufferedDesc<E> extends LockFreeLinkedListNode.AddLastDesc<SendBuffered<? extends E>> {
        public SendBufferedDesc(LockFreeLinkedListHead lockFreeLinkedListHead, E e) {
            super(lockFreeLinkedListHead, new SendBuffered(e));
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected Object failure(LockFreeLinkedListNode affected) {
            if (affected instanceof Closed) {
                return affected;
            }
            if (affected != null ? affected instanceof ReceiveOrClosed : true) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            return null;
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean isClosedForSend() {
        return getClosedForSend() != null;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isFull() {
        return isFullImpl();
    }

    protected final boolean isFullImpl() {
        return !(this.queue.getNextNode() instanceof ReceiveOrClosed) && isBufferFull();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final Object send(E e, Continuation<? super Unit> continuation) {
        Object objSendSuspend;
        return (offerInternal(e) != AbstractChannelKt.OFFER_SUCCESS && (objSendSuspend = sendSuspend(e, continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objSendSuspend : Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean offer(E element) throws Throwable {
        Object objOfferInternal = offerInternal(element);
        if (objOfferInternal == AbstractChannelKt.OFFER_SUCCESS) {
            return true;
        }
        if (objOfferInternal == AbstractChannelKt.OFFER_FAILED) {
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend == null) {
                return false;
            }
            throw StackTraceRecoveryKt.recoverStackTrace(helpCloseAndGetSendException(element, closedForSend));
        }
        if (objOfferInternal instanceof Closed) {
            throw StackTraceRecoveryKt.recoverStackTrace(helpCloseAndGetSendException(element, (Closed) objOfferInternal));
        }
        throw new IllegalStateException(("offerInternal returned " + objOfferInternal).toString());
    }

    private final Throwable helpCloseAndGetSendException(E element, Closed<?> closed) {
        UndeliveredElementException undeliveredElementExceptionCallUndeliveredElementCatchingException$default;
        helpClose(closed);
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        if (function1 != null && (undeliveredElementExceptionCallUndeliveredElementCatchingException$default = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, element, null, 2, null)) != null) {
            UndeliveredElementException undeliveredElementException = undeliveredElementExceptionCallUndeliveredElementCatchingException$default;
            ExceptionsKt.addSuppressed(undeliveredElementException, closed.getSendException());
            throw undeliveredElementException;
        }
        return closed.getSendException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void helpCloseAndResumeWithSendException(Continuation<?> continuation, E e, Closed<?> closed) {
        UndeliveredElementException undeliveredElementExceptionCallUndeliveredElementCatchingException$default;
        helpClose(closed);
        Throwable sendException = closed.getSendException();
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        if (function1 != null && (undeliveredElementExceptionCallUndeliveredElementCatchingException$default = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, e, null, 2, null)) != null) {
            UndeliveredElementException undeliveredElementException = undeliveredElementExceptionCallUndeliveredElementCatchingException$default;
            ExceptionsKt.addSuppressed(undeliveredElementException, sendException);
            Result.Companion companion = Result.INSTANCE;
            continuation.resumeWith(Result.m37constructorimpl(ResultKt.createFailure(undeliveredElementException)));
            return;
        }
        Result.Companion companion2 = Result.INSTANCE;
        continuation.resumeWith(Result.m37constructorimpl(ResultKt.createFailure(sendException)));
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0046  */
    /* JADX WARN: Code duplicated, block: B:28:0x0049 A[ORIG_RETURN, RETURN] */
    protected Object enqueueSend(Send send) {
        boolean z;
        int iTryCondAddNext;
        LockFreeLinkedListNode prevOrNull;
        if (isBufferAlwaysFull()) {
            LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
            do {
                prevOrNull = lockFreeLinkedListHead.getPrevOrNull();
                if (prevOrNull == null) {
                    return null;
                }
                if (prevOrNull instanceof ReceiveOrClosed) {
                    return prevOrNull;
                }
            } while (!prevOrNull.addNext(send, lockFreeLinkedListHead));
            return null;
        }
        LockFreeLinkedListHead lockFreeLinkedListHead2 = this.queue;
        final Send send2 = send;
        LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(send2) { // from class: kotlinx.coroutines.channels.AbstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1
            @Override // kotlinx.coroutines.internal.AtomicOp
            public Object prepare(LockFreeLinkedListNode affected) {
                if (this.isBufferFull()) {
                    return null;
                }
                return LockFreeLinkedListKt.getCONDITION_FALSE();
            }
        };
        do {
            LockFreeLinkedListNode prevOrNull2 = lockFreeLinkedListHead2.getPrevOrNull();
            z = true;
            if (prevOrNull2 == null) {
                break;
            }
            if (prevOrNull2 instanceof ReceiveOrClosed) {
                return prevOrNull2;
            }
            iTryCondAddNext = prevOrNull2.tryCondAddNext(send2, lockFreeLinkedListHead2, condAddOp);
            if (iTryCondAddNext != 1) {
            }
            if (z) {
                return null;
            }
            return AbstractChannelKt.ENQUEUE_FAILED;
        } while (iTryCondAddNext != 2);
        z = false;
        if (z) {
            return AbstractChannelKt.ENQUEUE_FAILED;
        }
        return null;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* JADX INFO: renamed from: close */
    public boolean cancel(Throwable cause) {
        LockFreeLinkedListNode prevOrNull;
        boolean z;
        Closed<?> closed = new Closed<>(cause);
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        do {
            prevOrNull = lockFreeLinkedListHead.getPrevOrNull();
            z = true;
            if (prevOrNull == null || !(!(prevOrNull instanceof Closed))) {
                z = false;
                break;
            }
        } while (!prevOrNull.addNext(closed, lockFreeLinkedListHead));
        if (!z) {
            LockFreeLinkedListNode lockFreeLinkedListNodeQueueTail = queueTail();
            if (lockFreeLinkedListNodeQueueTail == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.Closed<*>");
            }
            closed = (Closed) lockFreeLinkedListNodeQueueTail;
        }
        helpClose(closed);
        if (z) {
            invokeOnCloseHandler(cause);
        }
        return z;
    }

    private final void invokeOnCloseHandler(Throwable cause) {
        Object obj = this.onCloseHandler;
        if (obj == null || obj == AbstractChannelKt.HANDLER_INVOKED || !onCloseHandler$FU.compareAndSet(this, obj, AbstractChannelKt.HANDLER_INVOKED)) {
            return;
        }
        ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 1)).invoke(cause);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(Function1<? super Throwable, Unit> handler) {
        if (!onCloseHandler$FU.compareAndSet(this, null, handler)) {
            Object obj = this.onCloseHandler;
            if (obj == AbstractChannelKt.HANDLER_INVOKED) {
                throw new IllegalStateException("Another handler was already registered and successfully invoked");
            }
            throw new IllegalStateException("Another handler was already registered: " + obj);
        }
        Closed<?> closedForSend = getClosedForSend();
        if (closedForSend == null || !onCloseHandler$FU.compareAndSet(this, handler, AbstractChannelKt.HANDLER_INVOKED)) {
            return;
        }
        handler.invoke(closedForSend.closeCause);
    }

    private final void helpClose(Closed<?> closed) {
        Object objM1401constructorimpl$default = InlineList.m1401constructorimpl$default(null, 1, null);
        while (true) {
            LockFreeLinkedListNode prevNode = closed.getPrevNode();
            if (!(prevNode instanceof Receive)) {
                prevNode = null;
            }
            Receive receive = (Receive) prevNode;
            if (receive == null) {
                break;
            } else if (!receive.remove()) {
                receive.helpRemove();
            } else {
                objM1401constructorimpl$default = InlineList.m1406plusUZ7vuAc(objM1401constructorimpl$default, receive);
            }
        }
        if (objM1401constructorimpl$default != null) {
            if (!(objM1401constructorimpl$default instanceof ArrayList)) {
                ((Receive) objM1401constructorimpl$default).resumeReceiveClosed(closed);
            } else if (objM1401constructorimpl$default != null) {
                ArrayList arrayList = (ArrayList) objM1401constructorimpl$default;
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    ((Receive) arrayList.get(size)).resumeReceiveClosed(closed);
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<E> /* = java.util.ArrayList<E> */");
            }
        }
        onClosedIdempotent(closed);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [kotlinx.coroutines.internal.LockFreeLinkedListNode] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    protected ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        LockFreeLinkedListNode lockFreeLinkedListNodeRemoveOrNext;
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        while (true) {
            Object next = lockFreeLinkedListHead.getNext();
            if (next == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
            ?? r1 = (LockFreeLinkedListNode) next;
            if (r1 == lockFreeLinkedListHead || !(r1 instanceof ReceiveOrClosed)) {
                r1 = 0;
            } else if ((!(((ReceiveOrClosed) r1) instanceof Closed) || r1.isRemoved()) && (lockFreeLinkedListNodeRemoveOrNext = r1.removeOrNext()) != null) {
                lockFreeLinkedListNodeRemoveOrNext.helpRemovePrev();
            }
            return (ReceiveOrClosed) r1;
        }
    }

    protected final TryOfferDesc<E> describeTryOffer(E element) {
        return new TryOfferDesc<>(element, this.queue);
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0014\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\n\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011H\u0016R\u0012\u0010\u0005\u001a\u00028\u00018\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "Lkotlinx/coroutines/internal/RemoveFirstDesc;", "element", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListHead;)V", "Ljava/lang/Object;", "failure", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "onPrepare", "prepareOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "Lkotlinx/coroutines/internal/PrepareOp;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    protected static final class TryOfferDesc<E> extends LockFreeLinkedListNode.RemoveFirstDesc<ReceiveOrClosed<? super E>> {
        public final E element;

        public TryOfferDesc(E e, LockFreeLinkedListHead lockFreeLinkedListHead) {
            super(lockFreeLinkedListHead);
            this.element = e;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc, kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected Object failure(LockFreeLinkedListNode affected) {
            if (affected instanceof Closed) {
                return affected;
            }
            if (affected instanceof ReceiveOrClosed) {
                return null;
            }
            return AbstractChannelKt.OFFER_FAILED;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public Object onPrepare(LockFreeLinkedListNode.PrepareOp prepareOp) {
            Object obj = prepareOp.affected;
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveOrClosed<E>");
            }
            Symbol symbolTryResumeReceive = ((ReceiveOrClosed) obj).tryResumeReceive(this.element, prepareOp);
            if (symbolTryResumeReceive == null) {
                return LockFreeLinkedList_commonKt.REMOVE_PREPARED;
            }
            if (symbolTryResumeReceive == AtomicKt.RETRY_ATOMIC) {
                return AtomicKt.RETRY_ATOMIC;
            }
            if (!DebugKt.getASSERTIONS_ENABLED()) {
                return null;
            }
            if (symbolTryResumeReceive == CancellableContinuationImplKt.RESUME_TOKEN) {
                return null;
            }
            throw new AssertionError();
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final SelectClause2<E, SendChannel<E>> getOnSend() {
        return new SelectClause2<E, SendChannel<? super E>>() { // from class: kotlinx.coroutines.channels.AbstractSendChannel$onSend$1
            @Override // kotlinx.coroutines.selects.SelectClause2
            public <R> void registerSelectClause2(SelectInstance<? super R> select, E param, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> block) throws Throwable {
                this.this$0.registerSelectSend(select, param, block);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <R> void registerSelectSend(SelectInstance<? super R> select, E element, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> block) throws Throwable {
        while (!select.isSelected()) {
            if (isFullImpl()) {
                SendSelect sendSelect = new SendSelect(element, this, select, block);
                Object objEnqueueSend = enqueueSend(sendSelect);
                if (objEnqueueSend == null) {
                    select.disposeOnSelect(sendSelect);
                    return;
                }
                if (objEnqueueSend instanceof Closed) {
                    throw StackTraceRecoveryKt.recoverStackTrace(helpCloseAndGetSendException(element, (Closed) objEnqueueSend));
                }
                if (objEnqueueSend != AbstractChannelKt.ENQUEUE_FAILED && !(objEnqueueSend instanceof Receive)) {
                    throw new IllegalStateException(("enqueueSend returned " + objEnqueueSend + ' ').toString());
                }
            }
            Object objOfferSelectInternal = offerSelectInternal(element, select);
            if (objOfferSelectInternal == SelectKt.getALREADY_SELECTED()) {
                return;
            }
            if (objOfferSelectInternal != AbstractChannelKt.OFFER_FAILED && objOfferSelectInternal != AtomicKt.RETRY_ATOMIC) {
                if (objOfferSelectInternal == AbstractChannelKt.OFFER_SUCCESS) {
                    UndispatchedKt.startCoroutineUnintercepted(block, this, select.getCompletion());
                    return;
                } else {
                    if (objOfferSelectInternal instanceof Closed) {
                        throw StackTraceRecoveryKt.recoverStackTrace(helpCloseAndGetSendException(element, (Closed) objOfferSelectInternal));
                    }
                    throw new IllegalStateException(("offerSelectInternal returned " + objOfferSelectInternal).toString());
                }
            }
        }
    }

    public String toString() {
        return DebugStringsKt.getClassSimpleName(this) + '@' + DebugStringsKt.getHexAddress(this) + '{' + getQueueDebugStateString() + '}' + getBufferDebugString();
    }

    private final String getQueueDebugStateString() {
        String string;
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        if (nextNode == this.queue) {
            return "EmptyQueue";
        }
        if (nextNode instanceof Closed) {
            string = nextNode.toString();
        } else if (nextNode instanceof Receive) {
            string = "ReceiveQueued";
        } else if (nextNode instanceof Send) {
            string = "SendQueued";
        } else {
            string = "UNEXPECTED:" + nextNode;
        }
        LockFreeLinkedListNode lockFreeLinkedListNodeQueueTail = queueTail();
        if (lockFreeLinkedListNodeQueueTail == nextNode) {
            return string;
        }
        String str = string + ",queueSize=" + countQueueSize();
        if (!(lockFreeLinkedListNodeQueueTail instanceof Closed)) {
            return str;
        }
        return str + ",closedForSend=" + lockFreeLinkedListNodeQueueTail;
    }

    private final LockFreeLinkedListNode queueTail() {
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        return prevNode == lockFreeLinkedListHead ? lockFreeLinkedListHead.getNextNode() : prevNode;
    }

    private final int countQueueSize() {
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        LockFreeLinkedListNode lockFreeLinkedListNodeUnwrap = (LockFreeLinkedListNode) lockFreeLinkedListHead.getNext();
        int i = 0;
        while ((!Intrinsics.areEqual(lockFreeLinkedListNodeUnwrap, lockFreeLinkedListHead)) && lockFreeLinkedListNodeUnwrap != null) {
            if (lockFreeLinkedListNodeUnwrap instanceof LockFreeLinkedListNode) {
                i++;
            }
            Object next = lockFreeLinkedListNodeUnwrap.getNext();
            lockFreeLinkedListNodeUnwrap = next != null ? LockFreeLinkedListKt.unwrap(next) : null;
        }
        return i;
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\u00020\u00032\u00020\u0004BV\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\t\u0012(\u0010\n\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000bø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\u0014\u0010\u0017\u001a\u00020\u00152\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0015H\u0016R7\u0010\n\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000b8\u0006X\u0087\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0010R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendSelect;", ExifInterface.LONGITUDE_EAST, "R", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/DisposableHandle;", "pollResult", "channel", "Lkotlinx/coroutines/channels/AbstractSendChannel;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", "", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/AbstractSendChannel;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "getPollResult", "()Ljava/lang/Object;", "Ljava/lang/Object;", "completeResumeSend", "", "dispose", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeSend", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "undeliveredElement", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    private static final class SendSelect<E, R> extends Send implements DisposableHandle {
        public final Function2<SendChannel<? super E>, Continuation<? super R>, Object> block;
        public final AbstractSendChannel<E> channel;
        private final E pollResult;
        public final SelectInstance<R> select;

        @Override // kotlinx.coroutines.channels.Send
        /* JADX INFO: renamed from: getPollResult */
        public E getElement() {
            return this.pollResult;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public SendSelect(E e, AbstractSendChannel<E> abstractSendChannel, SelectInstance<? super R> selectInstance, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) {
            this.pollResult = e;
            this.channel = abstractSendChannel;
            this.select = selectInstance;
            this.block = function2;
        }

        @Override // kotlinx.coroutines.channels.Send
        public Symbol tryResumeSend(LockFreeLinkedListNode.PrepareOp otherOp) {
            return (Symbol) this.select.trySelectOther(otherOp);
        }

        @Override // kotlinx.coroutines.channels.Send
        public void completeResumeSend() {
            BuildersKt.startCoroutineImpl(CoroutineStart.DEFAULT, this.channel, this.select.getCompletion(), (Function1) null, this.block);
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            if (remove()) {
                undeliveredElement();
            }
        }

        @Override // kotlinx.coroutines.channels.Send
        public void resumeSendClosed(Closed<?> closed) {
            if (this.select.trySelect()) {
                this.select.resumeSelectWithException(closed.getSendException());
            }
        }

        @Override // kotlinx.coroutines.channels.Send
        public void undeliveredElement() {
            Function1<E, Unit> function1 = this.channel.onUndeliveredElement;
            if (function1 != null) {
                OnUndeliveredElementKt.callUndeliveredElement(function1, getElement(), this.select.getCompletion().get$context());
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "SendSelect@" + DebugStringsKt.getHexAddress(this) + '(' + getElement() + ")[" + this.channel + ", " + this.select + ']';
        }
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0014\u0010\f\u001a\u00020\u000b2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u0012\u0010\u0003\u001a\u00028\u00018\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/Send;", "element", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "pollResult", "", "getPollResult", "()Ljava/lang/Object;", "completeResumeSend", "", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeSend", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    public static final class SendBuffered<E> extends Send {
        public final E element;

        @Override // kotlinx.coroutines.channels.Send
        public void completeResumeSend() {
        }

        @Override // kotlinx.coroutines.channels.Send
        public void resumeSendClosed(Closed<?> closed) {
        }

        public SendBuffered(E e) {
            this.element = e;
        }

        @Override // kotlinx.coroutines.channels.Send
        /* JADX INFO: renamed from: getPollResult, reason: from getter */
        public Object getElement() {
            return this.element;
        }

        @Override // kotlinx.coroutines.channels.Send
        public Symbol tryResumeSend(LockFreeLinkedListNode.PrepareOp otherOp) {
            Symbol symbol = CancellableContinuationImplKt.RESUME_TOKEN;
            if (otherOp != null) {
                otherOp.finishPrepare();
            }
            return symbol;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "SendBuffered@" + DebugStringsKt.getHexAddress(this) + '(' + this.element + ')';
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x007c  */
    /* JADX WARN: Code duplicated, block: B:31:0x0085 A[EDGE_INSN: B:31:0x0085->B:32:0x008c BREAK  A[LOOP:0: B:3:0x000b->B:43:?]] */
    /* JADX WARN: Code duplicated, block: B:36:0x009a  */
    /* JADX WARN: Code duplicated, block: B:41:0x006e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:42:0x0081 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:43:? A[LOOP:0: B:3:0x000b->B:43:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:36:0x009a, please report this as an issue */
    final /* synthetic */ Object sendSuspend(E e, Continuation<? super Unit> continuation) {
        Object objOfferInternal;
        SendElementWithUndeliveredHandler sendElementWithUndeliveredHandler;
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation));
        CancellableContinuationImpl cancellableContinuationImpl = orCreateCancellableContinuation;
        while (true) {
            if (isFullImpl()) {
                if (this.onUndeliveredElement == null) {
                    sendElementWithUndeliveredHandler = new SendElement(e, cancellableContinuationImpl);
                } else {
                    sendElementWithUndeliveredHandler = new SendElementWithUndeliveredHandler(e, cancellableContinuationImpl, this.onUndeliveredElement);
                }
                Object objEnqueueSend = enqueueSend(sendElementWithUndeliveredHandler);
                if (objEnqueueSend == null) {
                    CancellableContinuationKt.removeOnCancellation(cancellableContinuationImpl, sendElementWithUndeliveredHandler);
                    break;
                }
                if (objEnqueueSend instanceof Closed) {
                    helpCloseAndResumeWithSendException(cancellableContinuationImpl, e, (Closed) objEnqueueSend);
                    break;
                }
                if (objEnqueueSend != AbstractChannelKt.ENQUEUE_FAILED && !(objEnqueueSend instanceof Receive)) {
                    throw new IllegalStateException(("enqueueSend returned " + objEnqueueSend).toString());
                }
                objOfferInternal = offerInternal(e);
                if (objOfferInternal == AbstractChannelKt.OFFER_SUCCESS) {
                    Unit unit = Unit.INSTANCE;
                    Result.Companion companion = Result.INSTANCE;
                    cancellableContinuationImpl.resumeWith(Result.m37constructorimpl(unit));
                    break;
                }
                if (objOfferInternal == AbstractChannelKt.OFFER_FAILED) {
                    if (objOfferInternal instanceof Closed) {
                        helpCloseAndResumeWithSendException(cancellableContinuationImpl, e, (Closed) objOfferInternal);
                        break;
                    }
                    throw new IllegalStateException(("offerInternal returned " + objOfferInternal).toString());
                }
            } else {
                objOfferInternal = offerInternal(e);
                if (objOfferInternal == AbstractChannelKt.OFFER_SUCCESS) {
                    Unit unit2 = Unit.INSTANCE;
                    Result.Companion companion2 = Result.INSTANCE;
                    cancellableContinuationImpl.resumeWith(Result.m37constructorimpl(unit2));
                    break;
                }
                if (objOfferInternal == AbstractChannelKt.OFFER_FAILED) {
                    if (objOfferInternal instanceof Closed) {
                        helpCloseAndResumeWithSendException(cancellableContinuationImpl, e, (Closed) objOfferInternal);
                        break;
                    }
                    throw new IllegalStateException(("offerInternal returned " + objOfferInternal).toString());
                }
            }
        }
        Object result = orCreateCancellableContinuation.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
