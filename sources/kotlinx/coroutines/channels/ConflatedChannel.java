package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
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

/* JADX INFO: compiled from: ConflatedChannel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B'\u0012 \u0010\u0003\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\u0015\u001a\u00020\r2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017H\u0014J\u0015\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u001bJ!\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00028\u00002\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0014¢\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\rH\u0014J\n\u0010\"\u001a\u0004\u0018\u00010\u0019H\u0014J\u0016\u0010#\u001a\u0004\u0018\u00010\u00192\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0014J\u0014\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0002R\u0014\u0010\b\u001a\u00020\t8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\r8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\r8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\r8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000eR\u0014\u0010\u0012\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000eR\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lkotlinx/coroutines/channels/ConflatedChannel;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/AbstractChannel;", "onUndeliveredElement", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "(Lkotlin/jvm/functions/Function1;)V", "bufferDebugString", "", "getBufferDebugString", "()Ljava/lang/String;", "isBufferAlwaysEmpty", "", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "isEmpty", "state", "Lkotlinx/coroutines/channels/ConflatedChannelState;", "enqueueReceiveInternal", "receive", "Lkotlinx/coroutines/channels/Receive;", "offerInternal", "", "element", "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "onCancelIdempotent", "wasClosed", "pollInternal", "pollSelectInternal", "updateValueLocked", "Lkotlinx/coroutines/internal/UndeliveredElementException;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
public class ConflatedChannel<E> extends AbstractChannel<E> {
    private final ConflatedChannelState state;

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean isBufferAlwaysEmpty() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean isBufferAlwaysFull() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean isBufferFull() {
        return false;
    }

    public ConflatedChannel(Function1<? super E, Unit> function1) {
        super(function1);
        this.state = new ConflatedChannelState();
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean isBufferEmpty() {
        return this.state.getValue() == AbstractChannelKt.EMPTY;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        boolean zIsEmptyImpl;
        synchronized (this.state) {
            zIsEmptyImpl = isEmptyImpl();
        }
        return zIsEmptyImpl;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected Object offerInternal(E element) {
        synchronized (this.state) {
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            if (this.state.getValue() == AbstractChannelKt.EMPTY) {
                while (true) {
                    ReceiveOrClosed<E> receiveOrClosedTakeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
                    if (receiveOrClosedTakeFirstReceiveOrPeekClosed == null) {
                        break;
                    }
                    if (receiveOrClosedTakeFirstReceiveOrPeekClosed instanceof Closed) {
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
                        Unit unit = Unit.INSTANCE;
                        Intrinsics.checkNotNull(receiveOrClosedTakeFirstReceiveOrPeekClosed);
                        receiveOrClosedTakeFirstReceiveOrPeekClosed.completeResumeReceive(element);
                        Intrinsics.checkNotNull(receiveOrClosedTakeFirstReceiveOrPeekClosed);
                        return receiveOrClosedTakeFirstReceiveOrPeekClosed.getOfferResult();
                    }
                }
            }
            UndeliveredElementException undeliveredElementExceptionUpdateValueLocked = updateValueLocked(element);
            if (undeliveredElementExceptionUpdateValueLocked != null) {
                throw undeliveredElementExceptionUpdateValueLocked;
            }
            return AbstractChannelKt.OFFER_SUCCESS;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected Object offerSelectInternal(E element, SelectInstance<?> select) {
        synchronized (this.state) {
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            if (this.state.getValue() == AbstractChannelKt.EMPTY) {
                while (true) {
                    AbstractSendChannel.TryOfferDesc<E> tryOfferDescDescribeTryOffer = describeTryOffer(element);
                    Object objPerformAtomicTrySelect = select.performAtomicTrySelect(tryOfferDescDescribeTryOffer);
                    if (objPerformAtomicTrySelect == null) {
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
                        return objPerformAtomicTrySelect;
                    }
                }
            }
            if (!select.trySelect()) {
                return SelectKt.getALREADY_SELECTED();
            }
            UndeliveredElementException undeliveredElementExceptionUpdateValueLocked = updateValueLocked(element);
            if (undeliveredElementExceptionUpdateValueLocked != null) {
                throw undeliveredElementExceptionUpdateValueLocked;
            }
            return AbstractChannelKt.OFFER_SUCCESS;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected Object pollInternal() {
        synchronized (this.state) {
            if (this.state.getValue() == AbstractChannelKt.EMPTY) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = AbstractChannelKt.POLL_FAILED;
                }
                return closedForSend;
            }
            Object value = this.state.getValue();
            this.state.setValue(AbstractChannelKt.EMPTY);
            Unit unit = Unit.INSTANCE;
            return value;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected Object pollSelectInternal(SelectInstance<?> select) {
        synchronized (this.state) {
            if (this.state.getValue() == AbstractChannelKt.EMPTY) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = AbstractChannelKt.POLL_FAILED;
                }
                return closedForSend;
            }
            if (!select.trySelect()) {
                return SelectKt.getALREADY_SELECTED();
            }
            Object value = this.state.getValue();
            this.state.setValue(AbstractChannelKt.EMPTY);
            Unit unit = Unit.INSTANCE;
            return value;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected void onCancelIdempotent(boolean wasClosed) {
        UndeliveredElementException undeliveredElementExceptionUpdateValueLocked;
        synchronized (this.state) {
            undeliveredElementExceptionUpdateValueLocked = updateValueLocked(AbstractChannelKt.EMPTY);
            Unit unit = Unit.INSTANCE;
        }
        super.onCancelIdempotent(wasClosed);
        if (undeliveredElementExceptionUpdateValueLocked != null) {
            throw undeliveredElementExceptionUpdateValueLocked;
        }
    }

    private final UndeliveredElementException updateValueLocked(Object element) {
        Function1<E, Unit> function1;
        Object value = this.state.getValue();
        UndeliveredElementException undeliveredElementExceptionCallUndeliveredElementCatchingException$default = null;
        if (value != AbstractChannelKt.EMPTY && (function1 = this.onUndeliveredElement) != null) {
            undeliveredElementExceptionCallUndeliveredElementCatchingException$default = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, value, null, 2, null);
        }
        this.state.setValue(element);
        return undeliveredElementExceptionCallUndeliveredElementCatchingException$default;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected boolean enqueueReceiveInternal(Receive<? super E> receive) {
        boolean zEnqueueReceiveInternal;
        synchronized (this.state) {
            zEnqueueReceiveInternal = super.enqueueReceiveInternal(receive);
        }
        return zEnqueueReceiveInternal;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected String getBufferDebugString() {
        return "(value=" + this.state.getValue() + ')';
    }
}
