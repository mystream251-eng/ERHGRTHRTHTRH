package kotlinx.coroutines.channels;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;

/* JADX INFO: compiled from: AbstractChannel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\b\u0010\u0018\u00002\u00020\u001cB\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\f\u001a\u00020\u00042\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\nH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00018\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0002\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lkotlinx/coroutines/channels/SendElement;", "", "pollResult", "Lkotlinx/coroutines/CancellableContinuation;", "", "cont", "<init>", "(Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)V", "completeResumeSend", "()V", "Lkotlinx/coroutines/channels/Closed;", "closed", "resumeSendClosed", "(Lkotlinx/coroutines/channels/Closed;)V", "", "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "otherOp", "Lkotlinx/coroutines/internal/Symbol;", "tryResumeSend", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "getCont", "()Lkotlinx/coroutines/CancellableContinuation;", "Ljava/lang/Object;", "getPollResult", "()Ljava/lang/Object;", "kotlinx-coroutines-core", "Lkotlinx/coroutines/channels/Send;"}, k = 1, mv = {1, 4, 0})
public class SendElement extends Send {
    private static final AtomicReferenceFieldUpdater _cont$FU = AtomicReferenceFieldUpdater.newUpdater(SendElement.class, Object.class, "_cont");
    private volatile Object _cont;
    private final Object pollResult;

    @Override // kotlinx.coroutines.channels.Send
    public Object getPollResult() {
        return this.pollResult;
    }

    public SendElement(Object obj, CancellableContinuation<? super Unit> cancellableContinuation) {
        this.pollResult = obj;
        this._cont = cancellableContinuation;
    }

    protected final CancellableContinuation<Unit> getCont() {
        Object obj = this._cont;
        Intrinsics.checkNotNull(obj);
        return (CancellableContinuation) obj;
    }

    @Override // kotlinx.coroutines.channels.Send
    public Symbol tryResumeSend(LockFreeLinkedListNode.PrepareOp otherOp) {
        CancellableContinuation cancellableContinuation = (CancellableContinuation) this._cont;
        if (cancellableContinuation != null) {
            Object objTryResume = cancellableContinuation.tryResume(Unit.INSTANCE, otherOp != null ? otherOp.desc : null);
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

    @Override // kotlinx.coroutines.channels.Send
    public void completeResumeSend() {
        Object andSet = _cont$FU.getAndSet(this, null);
        Intrinsics.checkNotNull(andSet);
        ((CancellableContinuation) andSet).completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
    }

    @Override // kotlinx.coroutines.channels.Send
    public void resumeSendClosed(Closed<?> closed) {
        Object andSet = _cont$FU.getAndSet(this, null);
        Intrinsics.checkNotNull(andSet);
        Throwable sendException = closed.getSendException();
        Result.Companion companion = Result.INSTANCE;
        ((Continuation) andSet).resumeWith(Result.m37constructorimpl(ResultKt.createFailure(sendException)));
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public String toString() {
        return "SendElement@" + DebugStringsKt.getHexAddress(this) + '(' + getPollResult() + ')';
    }
}
