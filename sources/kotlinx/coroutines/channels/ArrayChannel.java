package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

/* JADX INFO: compiled from: ArrayChannel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B7\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012 \u0010\u0007\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\n¢\u0006\u0002\u0010\u000bJ\u001d\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u001eJ\u0016\u0010\u001f\u001a\u00020\u00112\f\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000!H\u0014J\u0012\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%H\u0014J\u0015\u0010&\u001a\u00020#2\u0006\u0010\u001d\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010'J!\u0010(\u001a\u00020#2\u0006\u0010\u001d\u001a\u00028\u00002\n\u0010)\u001a\u0006\u0012\u0002\b\u00030*H\u0014¢\u0006\u0002\u0010+J\u0010\u0010,\u001a\u00020\t2\u0006\u0010-\u001a\u00020\u0011H\u0014J\n\u0010.\u001a\u0004\u0018\u00010#H\u0014J\u0016\u0010/\u001a\u0004\u0018\u00010#2\n\u0010)\u001a\u0006\u0012\u0002\b\u00030*H\u0014J\u0012\u00100\u001a\u0004\u0018\u0001012\u0006\u0010\u001c\u001a\u00020\u0004H\u0002R\u0014\u0010\f\u001a\u00020\r8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u00118DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00118DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00118DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u00118DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0012R\u0014\u0010\u0016\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0012R\u0014\u0010\u0017\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0012R\u0014\u0010\u0018\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0012R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lkotlinx/coroutines/channels/ArrayChannel;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/AbstractChannel;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "onUndeliveredElement", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "(ILkotlinx/coroutines/channels/BufferOverflow;Lkotlin/jvm/functions/Function1;)V", "bufferDebugString", "", "getBufferDebugString", "()Ljava/lang/String;", "isBufferAlwaysEmpty", "", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "isClosedForReceive", "isEmpty", "isFull", "state", "Lkotlinx/coroutines/channels/ArrayChannelState;", "enqueueElement", "currentSize", "element", "(ILjava/lang/Object;)V", "enqueueReceiveInternal", "receive", "Lkotlinx/coroutines/channels/Receive;", "enqueueSend", "", "send", "Lkotlinx/coroutines/channels/Send;", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "onCancelIdempotent", "wasClosed", "pollInternal", "pollSelectInternal", "updateBufferSize", "Lkotlinx/coroutines/internal/Symbol;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
public class ArrayChannel<E> extends AbstractChannel<E> {
    private final int capacity;
    private final BufferOverflow onBufferOverflow;
    private final ArrayChannelState state;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BufferOverflow.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[BufferOverflow.SUSPEND.ordinal()] = 1;
            $EnumSwitchMapping$0[BufferOverflow.DROP_LATEST.ordinal()] = 2;
            $EnumSwitchMapping$0[BufferOverflow.DROP_OLDEST.ordinal()] = 3;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean isBufferAlwaysEmpty() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean isBufferAlwaysFull() {
        return false;
    }

    public ArrayChannel(int i, BufferOverflow bufferOverflow, Function1<? super E, Unit> function1) {
        super(function1);
        this.capacity = i;
        this.onBufferOverflow = bufferOverflow;
        if (!(i >= 1)) {
            throw new IllegalArgumentException(("ArrayChannel capacity must be at least 1, but " + this.capacity + " was specified").toString());
        }
        this.state = new ArrayChannelState(Math.min(this.capacity, 8));
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean isBufferEmpty() {
        return this.state.getSize() == 0;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean isBufferFull() {
        return this.state.getSize() == this.capacity;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel, kotlinx.coroutines.channels.SendChannel
    public boolean isFull() {
        boolean zIsFullImpl;
        synchronized (this.state) {
            zIsFullImpl = isFullImpl();
        }
        return zIsFullImpl;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        boolean zIsEmptyImpl;
        synchronized (this.state) {
            zIsEmptyImpl = isEmptyImpl();
        }
        return zIsEmptyImpl;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        boolean zIsClosedForReceive;
        synchronized (this.state) {
            zIsClosedForReceive = super.isClosedForReceive();
        }
        return zIsClosedForReceive;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected Object offerInternal(E element) {
        synchronized (this.state) {
            int size = this.state.getSize();
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            Symbol symbolUpdateBufferSize = updateBufferSize(size);
            if (symbolUpdateBufferSize != null) {
                return symbolUpdateBufferSize;
            }
            if (size == 0) {
                while (true) {
                    ReceiveOrClosed<E> receiveOrClosedTakeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
                    if (receiveOrClosedTakeFirstReceiveOrPeekClosed == null) {
                        break;
                    }
                    if (receiveOrClosedTakeFirstReceiveOrPeekClosed instanceof Closed) {
                        this.state.setSize(size);
                        Intrinsics.checkNotNull(receiveOrClosedTakeFirstReceiveOrPeekClosed);
                        return receiveOrClosedTakeFirstReceiveOrPeekClosed;
                    }
                    Intrinsics.checkNotNull(receiveOrClosedTakeFirstReceiveOrPeekClosed);
                    Symbol symbolTryResumeReceive = receiveOrClosedTakeFirstReceiveOrPeekClosed.tryResumeReceive(element, null);
                    if (symbolTryResumeReceive != null) {
                        if (DebugKt.getASSERTIONS_ENABLED()) {
                            if (!(symbolTryResumeReceive == CancellableContinuationImplKt.RESUME_TOKEN)) {
                                throw new AssertionError();
                            }
                        }
                        this.state.setSize(size);
                        Unit unit = Unit.INSTANCE;
                        Intrinsics.checkNotNull(receiveOrClosedTakeFirstReceiveOrPeekClosed);
                        receiveOrClosedTakeFirstReceiveOrPeekClosed.completeResumeReceive(element);
                        Intrinsics.checkNotNull(receiveOrClosedTakeFirstReceiveOrPeekClosed);
                        return receiveOrClosedTakeFirstReceiveOrPeekClosed.getOfferResult();
                    }
                }
            }
            enqueueElement(size, element);
            return AbstractChannelKt.OFFER_SUCCESS;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected Object offerSelectInternal(E element, SelectInstance<?> select) {
        synchronized (this.state) {
            int size = this.state.getSize();
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            Symbol symbolUpdateBufferSize = updateBufferSize(size);
            if (symbolUpdateBufferSize != null) {
                return symbolUpdateBufferSize;
            }
            if (size == 0) {
                while (true) {
                    AbstractSendChannel.TryOfferDesc<E> tryOfferDescDescribeTryOffer = describeTryOffer(element);
                    Object objPerformAtomicTrySelect = select.performAtomicTrySelect(tryOfferDescDescribeTryOffer);
                    if (objPerformAtomicTrySelect == null) {
                        this.state.setSize(size);
                        ReceiveOrClosed<? super E> result = tryOfferDescDescribeTryOffer.getResult();
                        Unit unit = Unit.INSTANCE;
                        Intrinsics.checkNotNull(result);
                        result.completeResumeReceive(element);
                        Intrinsics.checkNotNull(result);
                        return result.getOfferResult();
                    }
                    if (objPerformAtomicTrySelect == AbstractChannelKt.OFFER_FAILED) {
                        break;
                    }
                    if (objPerformAtomicTrySelect != AtomicKt.RETRY_ATOMIC) {
                        if (objPerformAtomicTrySelect != SelectKt.getALREADY_SELECTED() && !(objPerformAtomicTrySelect instanceof Closed)) {
                            throw new IllegalStateException(("performAtomicTrySelect(describeTryOffer) returned " + objPerformAtomicTrySelect).toString());
                        }
                        this.state.setSize(size);
                        return objPerformAtomicTrySelect;
                    }
                }
            }
            if (!select.trySelect()) {
                this.state.setSize(size);
                return SelectKt.getALREADY_SELECTED();
            }
            enqueueElement(size, element);
            return AbstractChannelKt.OFFER_SUCCESS;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected Object enqueueSend(Send send) {
        Object objEnqueueSend;
        synchronized (this.state) {
            objEnqueueSend = super.enqueueSend(send);
        }
        return objEnqueueSend;
    }

    private final Symbol updateBufferSize(int currentSize) {
        if (currentSize < this.capacity) {
            this.state.setSize(currentSize + 1);
            return null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[this.onBufferOverflow.ordinal()];
        if (i == 1) {
            return AbstractChannelKt.OFFER_FAILED;
        }
        if (i == 2) {
            return AbstractChannelKt.OFFER_SUCCESS;
        }
        if (i == 3) {
            return null;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final void enqueueElement(int currentSize, E element) {
        int i = this.capacity;
        if (currentSize < i) {
            this.state.ensureCapacity(currentSize, i);
            ArrayChannelState arrayChannelState = this.state;
            arrayChannelState.setBufferAt((arrayChannelState.getHead() + currentSize) % this.state.getBufferSize(), element);
            return;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this.onBufferOverflow == BufferOverflow.DROP_OLDEST)) {
                throw new AssertionError();
            }
        }
        ArrayChannelState arrayChannelState2 = this.state;
        arrayChannelState2.setBufferAt(arrayChannelState2.getHead() % this.state.getBufferSize(), null);
        ArrayChannelState arrayChannelState3 = this.state;
        arrayChannelState3.setBufferAt((arrayChannelState3.getHead() + currentSize) % this.state.getBufferSize(), element);
        ArrayChannelState arrayChannelState4 = this.state;
        arrayChannelState4.setHead((arrayChannelState4.getHead() + 1) % this.state.getBufferSize());
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected Object pollInternal() {
        Send send = (Send) null;
        synchronized (this.state) {
            int size = this.state.getSize();
            if (size == 0) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = AbstractChannelKt.POLL_FAILED;
                }
                return closedForSend;
            }
            Object bufferAt = this.state.getBufferAt(this.state.getHead());
            this.state.setBufferAt(this.state.getHead(), null);
            this.state.setSize(size - 1);
            Object element = AbstractChannelKt.POLL_FAILED;
            boolean z = false;
            if (size == this.capacity) {
                while (true) {
                    Send sendTakeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
                    if (sendTakeFirstSendOrPeekClosed == null) {
                        break;
                    }
                    ArrayChannel<E> arrayChannel = this;
                    Intrinsics.checkNotNull(sendTakeFirstSendOrPeekClosed);
                    Symbol symbolTryResumeSend = sendTakeFirstSendOrPeekClosed.tryResumeSend(null);
                    if (symbolTryResumeSend != null) {
                        if (DebugKt.getASSERTIONS_ENABLED()) {
                            if (!(symbolTryResumeSend == CancellableContinuationImplKt.RESUME_TOKEN)) {
                                throw new AssertionError();
                            }
                        }
                        Intrinsics.checkNotNull(sendTakeFirstSendOrPeekClosed);
                        element = sendTakeFirstSendOrPeekClosed.getElement();
                        send = sendTakeFirstSendOrPeekClosed;
                        z = true;
                        break;
                    }
                    Intrinsics.checkNotNull(sendTakeFirstSendOrPeekClosed);
                    sendTakeFirstSendOrPeekClosed.undeliveredElement();
                    send = sendTakeFirstSendOrPeekClosed;
                }
            }
            if (element != AbstractChannelKt.POLL_FAILED && !(element instanceof Closed)) {
                this.state.setSize(size);
                this.state.setBufferAt((this.state.getHead() + size) % this.state.getBufferSize(), element);
            }
            this.state.setHead((this.state.getHead() + 1) % this.state.getBufferSize());
            Unit unit = Unit.INSTANCE;
            if (z) {
                Intrinsics.checkNotNull(send);
                send.completeResumeSend();
            }
            return bufferAt;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected Object pollSelectInternal(SelectInstance<?> select) {
        boolean z;
        Send result = (Send) null;
        synchronized (this.state) {
            int size = this.state.getSize();
            if (size == 0) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = AbstractChannelKt.POLL_FAILED;
                }
                return closedForSend;
            }
            Object bufferAt = this.state.getBufferAt(this.state.getHead());
            this.state.setBufferAt(this.state.getHead(), null);
            this.state.setSize(size - 1);
            Object element = AbstractChannelKt.POLL_FAILED;
            if (size != this.capacity) {
                z = false;
                break;
            }
            while (true) {
                AbstractChannel.TryPollDesc<E> tryPollDescDescribeTryPoll = describeTryPoll();
                Object objPerformAtomicTrySelect = select.performAtomicTrySelect(tryPollDescDescribeTryPoll);
                if (objPerformAtomicTrySelect == null) {
                    result = tryPollDescDescribeTryPoll.getResult();
                    Intrinsics.checkNotNull(result);
                    element = result.getElement();
                    z = true;
                    break;
                }
                if (objPerformAtomicTrySelect == AbstractChannelKt.POLL_FAILED) {
                    z = false;
                    break;
                }
                if (objPerformAtomicTrySelect != AtomicKt.RETRY_ATOMIC) {
                    if (objPerformAtomicTrySelect == SelectKt.getALREADY_SELECTED()) {
                        this.state.setSize(size);
                        this.state.setBufferAt(this.state.getHead(), bufferAt);
                        return objPerformAtomicTrySelect;
                    }
                    if (objPerformAtomicTrySelect instanceof Closed) {
                        result = (Send) objPerformAtomicTrySelect;
                        z = true;
                        element = objPerformAtomicTrySelect;
                        break;
                    }
                    throw new IllegalStateException(("performAtomicTrySelect(describeTryOffer) returned " + objPerformAtomicTrySelect).toString());
                }
            }
            if (element != AbstractChannelKt.POLL_FAILED && !(element instanceof Closed)) {
                this.state.setSize(size);
                this.state.setBufferAt((this.state.getHead() + size) % this.state.getBufferSize(), element);
            } else if (!select.trySelect()) {
                this.state.setSize(size);
                this.state.setBufferAt(this.state.getHead(), bufferAt);
                return SelectKt.getALREADY_SELECTED();
            }
            this.state.setHead((this.state.getHead() + 1) % this.state.getBufferSize());
            Unit unit = Unit.INSTANCE;
            if (z) {
                Intrinsics.checkNotNull(result);
                result.completeResumeSend();
            }
            return bufferAt;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected boolean enqueueReceiveInternal(Receive<? super E> receive) {
        boolean zEnqueueReceiveInternal;
        synchronized (this.state) {
            zEnqueueReceiveInternal = super.enqueueReceiveInternal(receive);
        }
        return zEnqueueReceiveInternal;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected void onCancelIdempotent(boolean wasClosed) {
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        UndeliveredElementException undeliveredElementExceptionCallUndeliveredElementCatchingException = (UndeliveredElementException) null;
        synchronized (this.state) {
            int size = this.state.getSize();
            for (int i = 0; i < size; i++) {
                Object bufferAt = this.state.getBufferAt(this.state.getHead());
                if (function1 != null && bufferAt != AbstractChannelKt.EMPTY) {
                    undeliveredElementExceptionCallUndeliveredElementCatchingException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function1, bufferAt, undeliveredElementExceptionCallUndeliveredElementCatchingException);
                }
                this.state.setBufferAt(this.state.getHead(), null);
                this.state.setHead((this.state.getHead() + 1) % this.state.getBufferSize());
            }
            this.state.setSize(0);
            Unit unit = Unit.INSTANCE;
        }
        super.onCancelIdempotent(wasClosed);
        if (undeliveredElementExceptionCallUndeliveredElementCatchingException != null) {
            throw undeliveredElementExceptionCallUndeliveredElementCatchingException;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected String getBufferDebugString() {
        String str;
        synchronized (this.state) {
            str = "(buffer:capacity=" + this.capacity + ",size=" + this.state.getSize() + ')';
        }
        return str;
    }
}
