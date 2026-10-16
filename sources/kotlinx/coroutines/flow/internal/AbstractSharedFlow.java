package kotlinx.coroutines.flow.internal;

import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;

/* JADX INFO: compiled from: AbstractSharedFlow.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018\u0000*\f\b\u0000\u0010\u0002*\u0006\u0012\u0002\b\u00030\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00028\u0000H$¢\u0006\u0004\b\u0007\u0010\u0006J$\u0010\u000b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t0\bH\u0084\b¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0013\u001a\u00020\u00108D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00148D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00100\u00188F@\u0006¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001c"}, d2 = {"Lkotlinx/coroutines/flow/internal/AbstractSharedFlow;", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", ExifInterface.LATITUDE_SOUTH, "<init>", "()V", "allocateSlot", "()Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "createSlot", "Lkotlin/Function1;", "", "block", "forEachSlotLocked", "(Lkotlin/jvm/functions/Function1;)V", "slot", "freeSlot", "(Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;)V", "", "getNCollectors", "()I", "nCollectors", "Lkotlinx/coroutines/flow/internal/SharedFlowState;", "getSlots", "()Lkotlinx/coroutines/flow/internal/SharedFlowState;", "slots", "Lkotlinx/coroutines/flow/StateFlow;", "getSubscriptionCount", "()Lkotlinx/coroutines/flow/StateFlow;", "subscriptionCount", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
public abstract class AbstractSharedFlow<S extends AbstractSharedFlowSlot<?>> {
    public static final /* synthetic */ AtomicIntegerFieldUpdater _nCollectors$FU$internal = AtomicIntegerFieldUpdater.newUpdater(AbstractSharedFlow.class, "_nCollectors$internal");
    public volatile /* synthetic */ Object _slots$internal = null;
    public volatile /* synthetic */ int _nCollectors$internal = 0;
    private volatile int nextIndex = 0;
    private volatile Object _subscriptionCount = null;

    protected abstract S createSlot();

    protected final SharedFlowState<S> getSlots() {
        return (SharedFlowState) this._slots$internal;
    }

    /* JADX INFO: renamed from: getNCollectors, reason: from getter */
    protected final int get_nCollectors$internal() {
        return this._nCollectors$internal;
    }

    public final StateFlow<Integer> getSubscriptionCount() {
        MutableStateFlow MutableStateFlow;
        synchronized (this) {
            MutableStateFlow = (MutableStateFlow) this._subscriptionCount;
            if (MutableStateFlow == null) {
                MutableStateFlow = StateFlowKt.MutableStateFlow(Integer.valueOf(get_nCollectors$internal()));
                this._subscriptionCount = MutableStateFlow;
            }
        }
        return MutableStateFlow;
    }

    protected final S allocateSlot() {
        S s;
        MutableStateFlow mutableStateFlow;
        synchronized (this) {
            SharedFlowState sharedFlowState = (SharedFlowState) this._slots$internal;
            if (sharedFlowState == null) {
                sharedFlowState = new SharedFlowState(2);
                this._slots$internal = sharedFlowState;
            } else if (get_nCollectors$internal() >= sharedFlowState.getSize()) {
                SharedFlowState sharedFlowState2 = new SharedFlowState(sharedFlowState.getSize() * 2);
                sharedFlowState.copyInto(sharedFlowState2);
                this._slots$internal = sharedFlowState2;
                sharedFlowState = sharedFlowState2;
            }
            int i = this.nextIndex;
            do {
                s = (S) sharedFlowState.get(i);
                if (s == null) {
                    s = (S) createSlot();
                    sharedFlowState.set(i, s);
                }
                i++;
                if (i >= sharedFlowState.getSize()) {
                    i = 0;
                }
                if (s == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot<kotlin.Any>");
                }
            } while (!s.allocateLocked(this));
            this.nextIndex = i;
            _nCollectors$FU$internal.incrementAndGet(this);
            mutableStateFlow = (MutableStateFlow) this._subscriptionCount;
        }
        if (mutableStateFlow != null) {
            StateFlowKt.increment(mutableStateFlow, 1);
        }
        return s;
    }

    protected final void freeSlot(S slot) {
        MutableStateFlow mutableStateFlow;
        int i;
        Continuation<Unit>[] continuationArrFreeLocked;
        synchronized (this) {
            _nCollectors$FU$internal.decrementAndGet(this);
            mutableStateFlow = (MutableStateFlow) this._subscriptionCount;
            if (this._nCollectors$internal == 0) {
                this.nextIndex = 0;
            }
            if (slot != null) {
                continuationArrFreeLocked = slot.freeLocked(this);
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot<kotlin.Any>");
            }
        }
        for (Continuation<Unit> continuation : continuationArrFreeLocked) {
            if (continuation != null) {
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.INSTANCE;
                continuation.resumeWith(Result.m37constructorimpl(unit));
            }
        }
        if (mutableStateFlow != null) {
            StateFlowKt.increment(mutableStateFlow, -1);
        }
    }

    protected final void forEachSlotLocked(Function1<? super S, Unit> block) {
        SharedFlowState sharedFlowState;
        if (this._nCollectors$internal == 0 || (sharedFlowState = (SharedFlowState) this._slots$internal) == null) {
            return;
        }
        int size = sharedFlowState.getSize();
        for (int i = 0; i < size; i++) {
            AbstractSharedFlowSlot abstractSharedFlowSlot = (AbstractSharedFlowSlot) sharedFlowState.get(i);
            if (abstractSharedFlowSlot != null) {
                block.invoke(abstractSharedFlowSlot);
            }
        }
    }
}
