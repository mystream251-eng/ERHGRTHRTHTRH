package kotlinx.coroutines.sync;

import androidx.tvprovider.media.tv.TvContractCompat;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

/* JADX INFO: compiled from: Mutex.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u00112\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u00110 :\u0006$%&'()B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\n\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0096@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\f\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0082@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\u000bJT\u0010\u0014\u001a\u00020\t\"\u0004\b\u0000\u0010\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\"\u0010\u0013\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0010H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0019\u0010\u0019\u001a\u00020\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016¢\u0006\u0004\b\u0019\u0010\bJ\u0019\u0010\u001a\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001c\u001a\u00020\u00018V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001f\u001a\u00020\u00018@@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001dR$\u0010#\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u00110 8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl;", "", TvContractCompat.Channels.COLUMN_LOCKED, "<init>", "(Z)V", "", "owner", "holdsLock", "(Ljava/lang/Object;)Z", "", "lock", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lockSuspend", "R", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "Lkotlin/Function2;", "Lkotlinx/coroutines/sync/Mutex;", "Lkotlin/coroutines/Continuation;", "block", "registerSelectClause2", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "", "toString", "()Ljava/lang/String;", "tryLock", "unlock", "(Ljava/lang/Object;)V", "isLocked", "()Z", "isLockedEmptyQueueState$kotlinx_coroutines_core", "isLockedEmptyQueueState", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnLock", "()Lkotlinx/coroutines/selects/SelectClause2;", "onLock", "LockCont", "LockSelect", "LockWaiter", "LockedQueue", "TryLockDesc", "UnlockOp", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
public final class MutexImpl implements Mutex, SelectClause2<Object, Mutex> {
    static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(MutexImpl.class, Object.class, "_state");
    volatile Object _state;

    public MutexImpl(boolean z) {
        this._state = z ? MutexKt.EMPTY_LOCKED : MutexKt.EMPTY_UNLOCKED;
    }

    public final boolean isLockedEmptyQueueState$kotlinx_coroutines_core() {
        Object obj = this._state;
        return (obj instanceof LockedQueue) && ((LockedQueue) obj).isEmpty();
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public Object lock(Object obj, Continuation<? super Unit> continuation) {
        Object objLockSuspend;
        return (!tryLock(obj) && (objLockSuspend = lockSuspend(obj, continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objLockSuspend : Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public SelectClause2<Object, Mutex> getOnLock() {
        return this;
    }

    @Override // kotlinx.coroutines.selects.SelectClause2
    public <R> void registerSelectClause2(SelectInstance<? super R> select, Object owner, Function2<? super Mutex, ? super Continuation<? super R>, ? extends Object> block) {
        while (!select.isSelected()) {
            final Object obj = this._state;
            if (obj instanceof Empty) {
                Empty empty = (Empty) obj;
                if (empty.locked != MutexKt.UNLOCKED) {
                    _state$FU.compareAndSet(this, obj, new LockedQueue(empty.locked));
                } else {
                    Object objPerformAtomicTrySelect = select.performAtomicTrySelect(new TryLockDesc(this, owner));
                    if (objPerformAtomicTrySelect == null) {
                        UndispatchedKt.startCoroutineUnintercepted(block, this, select.getCompletion());
                        return;
                    }
                    if (objPerformAtomicTrySelect == SelectKt.getALREADY_SELECTED()) {
                        return;
                    }
                    if (objPerformAtomicTrySelect != MutexKt.LOCK_FAIL && objPerformAtomicTrySelect != AtomicKt.RETRY_ATOMIC) {
                        throw new IllegalStateException(("performAtomicTrySelect(TryLockDesc) returned " + objPerformAtomicTrySelect).toString());
                    }
                }
            } else if (obj instanceof LockedQueue) {
                LockedQueue lockedQueue = (LockedQueue) obj;
                boolean z = true;
                if (!(lockedQueue.get_owner() != owner)) {
                    throw new IllegalStateException(("Already locked by " + owner).toString());
                }
                LockSelect lockSelect = new LockSelect(owner, select, block);
                LockedQueue lockedQueue2 = lockedQueue;
                final LockSelect lockSelect2 = lockSelect;
                LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(lockSelect2) { // from class: kotlinx.coroutines.sync.MutexImpl$registerSelectClause2$$inlined$addLastIf$1
                    @Override // kotlinx.coroutines.internal.AtomicOp
                    public Object prepare(LockFreeLinkedListNode affected) {
                        if (this._state == obj) {
                            return null;
                        }
                        return LockFreeLinkedListKt.getCONDITION_FALSE();
                    }
                };
                while (true) {
                    LockFreeLinkedListNode prevOrNull = lockedQueue2.getPrevOrNull();
                    if (prevOrNull != null) {
                        int iTryCondAddNext = prevOrNull.tryCondAddNext(lockSelect2, lockedQueue2, condAddOp);
                        if (iTryCondAddNext == 1) {
                            break;
                        } else if (iTryCondAddNext != 2) {
                        }
                    }
                    z = false;
                    break;
                }
                if (z) {
                    select.disposeOnSelect(lockSelect);
                    return;
                }
            } else {
                if (!(obj instanceof OpDescriptor)) {
                    throw new IllegalStateException(("Illegal state " + obj).toString());
                }
                ((OpDescriptor) obj).perform(this);
            }
        }
    }

    /* JADX INFO: compiled from: Mutex.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001:\u0001\rB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0016J\u0016\u0010\f\u001a\u0004\u0018\u00010\u00052\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0016R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$TryLockDesc;", "Lkotlinx/coroutines/internal/AtomicDesc;", "mutex", "Lkotlinx/coroutines/sync/MutexImpl;", "owner", "", "(Lkotlinx/coroutines/sync/MutexImpl;Ljava/lang/Object;)V", "complete", "", "op", "Lkotlinx/coroutines/internal/AtomicOp;", "failure", "prepare", "PrepareOp", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    private static final class TryLockDesc extends AtomicDesc {
        public final MutexImpl mutex;
        public final Object owner;

        public TryLockDesc(MutexImpl mutexImpl, Object obj) {
            this.mutex = mutexImpl;
            this.owner = obj;
        }

        /* JADX INFO: compiled from: Mutex.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$TryLockDesc$PrepareOp;", "Lkotlinx/coroutines/internal/OpDescriptor;", "atomicOp", "Lkotlinx/coroutines/internal/AtomicOp;", "(Lkotlinx/coroutines/sync/MutexImpl$TryLockDesc;Lkotlinx/coroutines/internal/AtomicOp;)V", "getAtomicOp", "()Lkotlinx/coroutines/internal/AtomicOp;", "perform", "", "affected", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
        private final class PrepareOp extends OpDescriptor {
            private final AtomicOp<?> atomicOp;

            public PrepareOp(AtomicOp<?> atomicOp) {
                this.atomicOp = atomicOp;
            }

            @Override // kotlinx.coroutines.internal.OpDescriptor
            public AtomicOp<?> getAtomicOp() {
                return this.atomicOp;
            }

            @Override // kotlinx.coroutines.internal.OpDescriptor
            public Object perform(Object affected) {
                Object atomicOp = getAtomicOp().isDecided() ? MutexKt.EMPTY_UNLOCKED : getAtomicOp();
                if (affected == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.sync.MutexImpl");
                }
                MutexImpl._state$FU.compareAndSet((MutexImpl) affected, this, atomicOp);
                return null;
            }
        }

        @Override // kotlinx.coroutines.internal.AtomicDesc
        public Object prepare(AtomicOp<?> op) {
            PrepareOp prepareOp = new PrepareOp(op);
            if (!MutexImpl._state$FU.compareAndSet(this.mutex, MutexKt.EMPTY_UNLOCKED, prepareOp)) {
                return MutexKt.LOCK_FAIL;
            }
            return prepareOp.perform(this.mutex);
        }

        @Override // kotlinx.coroutines.internal.AtomicDesc
        public void complete(AtomicOp<?> op, Object failure) {
            Empty empty;
            if (failure != null) {
                empty = MutexKt.EMPTY_UNLOCKED;
            } else {
                empty = this.owner == null ? MutexKt.EMPTY_LOCKED : new Empty(this.owner);
            }
            MutexImpl._state$FU.compareAndSet(this.mutex, op, empty);
        }
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean holdsLock(Object owner) {
        Object obj = this._state;
        if (obj instanceof Empty) {
            if (((Empty) obj).locked == owner) {
                return true;
            }
        } else if ((obj instanceof LockedQueue) && ((LockedQueue) obj).get_owner() == owner) {
            return true;
        }
        return false;
    }

    /* JADX INFO: compiled from: Mutex.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\b\u0002\u0018\u00002\u00020\rB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007R$\u0010\u0002\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0004¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;", "", "owner", "<init>", "(Ljava/lang/Object;)V", "", "toString", "()Ljava/lang/String;", "value", "getOwner", "()Ljava/lang/Object;", "setOwner", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;"}, k = 1, mv = {1, 4, 0})
    private static final class LockedQueue extends LockFreeLinkedListHead {
        private volatile Object _owner;

        public LockedQueue(Object obj) {
            this._owner = obj;
        }

        /* JADX INFO: renamed from: getOwner, reason: from getter */
        public final Object get_owner() {
            return this._owner;
        }

        public final void setOwner(Object obj) {
            this._owner = obj;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "LockedQueue[" + get_owner() + ']';
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: Mutex.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b¢\u0004\u0018\u00002\u00020\u00112\u00020\u0012B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0001H&¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0011\u0010\u000b\u001a\u0004\u0018\u00010\u0001H&¢\u0006\u0004\b\u000b\u0010\fR(\u0010\u0002\u001a\u0004\u0018\u00010\u00012\b\u0010\r\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\f\"\u0004\b\u000f\u0010\b¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockWaiter;", "", "owner", "<init>", "(Lkotlinx/coroutines/sync/MutexImpl;Ljava/lang/Object;)V", "token", "", "completeResumeLockWaiter", "(Ljava/lang/Object;)V", "dispose", "()V", "tryResumeLockWaiter", "()Ljava/lang/Object;", "value", "getOwner", "setOwner", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/DisposableHandle;"}, k = 1, mv = {1, 4, 0})
    abstract class LockWaiter extends LockFreeLinkedListNode implements DisposableHandle {
        private volatile Object _owner;

        public abstract void completeResumeLockWaiter(Object token);

        public abstract Object tryResumeLockWaiter();

        public LockWaiter(Object obj) {
            this._owner = obj;
        }

        /* JADX INFO: renamed from: getOwner, reason: from getter */
        public final Object get_owner() {
            return this._owner;
        }

        public final void setOwner(Object obj) {
            this._owner = obj;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final void dispose() {
            remove();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: Mutex.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u001d\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\n\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0016R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockCont;", "Lkotlinx/coroutines/sync/MutexImpl$LockWaiter;", "Lkotlinx/coroutines/sync/MutexImpl;", "owner", "", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlinx/coroutines/sync/MutexImpl;Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)V", "completeResumeLockWaiter", "token", "toString", "", "tryResumeLockWaiter", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    final class LockCont extends LockWaiter {
        public final CancellableContinuation<Unit> cont;

        /* JADX WARN: Multi-variable type inference failed */
        public LockCont(Object obj, CancellableContinuation<? super Unit> cancellableContinuation) {
            super(obj);
            this.cont = cancellableContinuation;
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public Object tryResumeLockWaiter() {
            return this.cont.tryResume(Unit.INSTANCE, null, new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.sync.MutexImpl$LockCont$tryResumeLockWaiter$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Throwable th) {
                    MutexImpl.this.unlock(this.this$0.get_owner());
                }
            });
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public void completeResumeLockWaiter(Object token) {
            this.cont.completeResume(token);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "LockCont[" + get_owner() + ", " + this.cont + "] for " + MutexImpl.this;
        }
    }

    /* JADX INFO: compiled from: Mutex.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00060\u0002R\u00020\u0003BD\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\"\u0010\b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00050\tø\u0001\u0000¢\u0006\u0002\u0010\fJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0005H\u0016R1\u0010\b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t8\u0006X\u0087\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\rR\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockSelect;", "R", "Lkotlinx/coroutines/sync/MutexImpl$LockWaiter;", "Lkotlinx/coroutines/sync/MutexImpl;", "owner", "", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/sync/Mutex;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/sync/MutexImpl;Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "completeResumeLockWaiter", "", "token", "toString", "", "tryResumeLockWaiter", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    private final class LockSelect<R> extends LockWaiter {
        public final Function2<Mutex, Continuation<? super R>, Object> block;
        public final SelectInstance<R> select;

        /* JADX WARN: Multi-variable type inference failed */
        public LockSelect(Object obj, SelectInstance<? super R> selectInstance, Function2<? super Mutex, ? super Continuation<? super R>, ? extends Object> function2) {
            super(obj);
            this.select = selectInstance;
            this.block = function2;
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public Object tryResumeLockWaiter() {
            if (this.select.trySelect()) {
                return MutexKt.SELECT_SUCCESS;
            }
            return null;
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public void completeResumeLockWaiter(Object token) {
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(token == MutexKt.SELECT_SUCCESS)) {
                    throw new AssertionError();
                }
            }
            BuildersKt.startCoroutineImpl(CoroutineStart.DEFAULT, MutexImpl.this, this.select.getCompletion(), new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.sync.MutexImpl$LockSelect$completeResumeLockWaiter$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Throwable th) {
                    MutexImpl.this.unlock(this.this$0.get_owner());
                }
            }, this.block);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "LockSelect[" + get_owner() + ", " + this.select + "] for " + MutexImpl.this;
        }
    }

    /* JADX INFO: compiled from: Mutex.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\b\u001a\u00020\u0002H\u0016R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$UnlockOp;", "Lkotlinx/coroutines/internal/AtomicOp;", "Lkotlinx/coroutines/sync/MutexImpl;", "queue", "Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;", "(Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;)V", "complete", "", "affected", "failure", "", "prepare", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    private static final class UnlockOp extends AtomicOp<MutexImpl> {
        public final LockedQueue queue;

        public UnlockOp(LockedQueue lockedQueue) {
            this.queue = lockedQueue;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public Object prepare(MutexImpl affected) {
            if (this.queue.isEmpty()) {
                return null;
            }
            return MutexKt.UNLOCK_FAIL;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public void complete(MutexImpl affected, Object failure) {
            MutexImpl._state$FU.compareAndSet(affected, this, failure == null ? MutexKt.EMPTY_UNLOCKED : this.queue);
        }
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean isLocked() {
        while (true) {
            Object obj = this._state;
            if (obj instanceof Empty) {
                return ((Empty) obj).locked != MutexKt.UNLOCKED;
            }
            if (obj instanceof LockedQueue) {
                return true;
            }
            if (!(obj instanceof OpDescriptor)) {
                throw new IllegalStateException(("Illegal state " + obj).toString());
            }
            ((OpDescriptor) obj).perform(this);
        }
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean tryLock(Object owner) {
        while (true) {
            Object obj = this._state;
            if (obj instanceof Empty) {
                if (((Empty) obj).locked != MutexKt.UNLOCKED) {
                    return false;
                }
                if (_state$FU.compareAndSet(this, obj, owner == null ? MutexKt.EMPTY_LOCKED : new Empty(owner))) {
                    return true;
                }
            } else {
                if (obj instanceof LockedQueue) {
                    if (((LockedQueue) obj).get_owner() != owner) {
                        return false;
                    }
                    throw new IllegalStateException(("Already locked by " + owner).toString());
                }
                if (!(obj instanceof OpDescriptor)) {
                    throw new IllegalStateException(("Illegal state " + obj).toString());
                }
                ((OpDescriptor) obj).perform(this);
            }
        }
    }

    final /* synthetic */ Object lockSuspend(final Object obj, Continuation<? super Unit> continuation) {
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation));
        final CancellableContinuationImpl cancellableContinuationImpl = orCreateCancellableContinuation;
        final LockCont lockCont = new LockCont(obj, cancellableContinuationImpl);
        while (true) {
            final Object obj2 = this._state;
            if (obj2 instanceof Empty) {
                Empty empty = (Empty) obj2;
                if (empty.locked != MutexKt.UNLOCKED) {
                    _state$FU.compareAndSet(this, obj2, new LockedQueue(empty.locked));
                } else {
                    if (_state$FU.compareAndSet(this, obj2, obj == null ? MutexKt.EMPTY_LOCKED : new Empty(obj))) {
                        cancellableContinuationImpl.resume(Unit.INSTANCE, new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.sync.MutexImpl$lockSuspend$$inlined$suspendCancellableCoroutineReusable$lambda$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                invoke2(th);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Throwable th) {
                                this.unlock(obj);
                            }
                        });
                        break;
                    }
                }
            } else if (obj2 instanceof LockedQueue) {
                LockedQueue lockedQueue = (LockedQueue) obj2;
                boolean z = true;
                if (!(lockedQueue.get_owner() != obj)) {
                    throw new IllegalStateException(("Already locked by " + obj).toString());
                }
                LockedQueue lockedQueue2 = lockedQueue;
                final LockCont lockCont2 = lockCont;
                LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(lockCont2) { // from class: kotlinx.coroutines.sync.MutexImpl$lockSuspend$$inlined$suspendCancellableCoroutineReusable$lambda$2
                    @Override // kotlinx.coroutines.internal.AtomicOp
                    public Object prepare(LockFreeLinkedListNode affected) {
                        if (this._state == obj2) {
                            return null;
                        }
                        return LockFreeLinkedListKt.getCONDITION_FALSE();
                    }
                };
                while (true) {
                    LockFreeLinkedListNode prevOrNull = lockedQueue2.getPrevOrNull();
                    if (prevOrNull != null) {
                        int iTryCondAddNext = prevOrNull.tryCondAddNext(lockCont2, lockedQueue2, condAddOp);
                        if (iTryCondAddNext == 1) {
                            break;
                        }
                        if (iTryCondAddNext != 2) {
                        }
                    }
                    z = false;
                    break;
                }
                if (z) {
                    CancellableContinuationKt.removeOnCancellation(cancellableContinuationImpl, lockCont2);
                    break;
                }
            } else {
                if (!(obj2 instanceof OpDescriptor)) {
                    throw new IllegalStateException(("Illegal state " + obj2).toString());
                }
                ((OpDescriptor) obj2).perform(this);
            }
        }
        Object result = orCreateCancellableContinuation.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public void unlock(Object owner) {
        while (true) {
            Object obj = this._state;
            if (obj instanceof Empty) {
                if (owner == null) {
                    if (!(((Empty) obj).locked != MutexKt.UNLOCKED)) {
                        throw new IllegalStateException("Mutex is not locked".toString());
                    }
                } else {
                    Empty empty = (Empty) obj;
                    if (!(empty.locked == owner)) {
                        throw new IllegalStateException(("Mutex is locked by " + empty.locked + " but expected " + owner).toString());
                    }
                }
                if (_state$FU.compareAndSet(this, obj, MutexKt.EMPTY_UNLOCKED)) {
                    return;
                }
            } else if (obj instanceof OpDescriptor) {
                ((OpDescriptor) obj).perform(this);
            } else if (obj instanceof LockedQueue) {
                if (owner != null) {
                    LockedQueue lockedQueue = (LockedQueue) obj;
                    if (!(lockedQueue.get_owner() == owner)) {
                        throw new IllegalStateException(("Mutex is locked by " + lockedQueue.get_owner() + " but expected " + owner).toString());
                    }
                }
                LockedQueue lockedQueue2 = (LockedQueue) obj;
                LockFreeLinkedListNode lockFreeLinkedListNodeRemoveFirstOrNull = lockedQueue2.removeFirstOrNull();
                if (lockFreeLinkedListNodeRemoveFirstOrNull == null) {
                    UnlockOp unlockOp = new UnlockOp(lockedQueue2);
                    if (_state$FU.compareAndSet(this, obj, unlockOp) && unlockOp.perform(this) == null) {
                        return;
                    }
                } else {
                    LockWaiter lockWaiter = (LockWaiter) lockFreeLinkedListNodeRemoveFirstOrNull;
                    Object objTryResumeLockWaiter = lockWaiter.tryResumeLockWaiter();
                    if (objTryResumeLockWaiter != null) {
                        Object obj2 = lockWaiter.get_owner();
                        if (obj2 == null) {
                            obj2 = MutexKt.LOCKED;
                        }
                        lockedQueue2.setOwner(obj2);
                        lockWaiter.completeResumeLockWaiter(objTryResumeLockWaiter);
                        return;
                    }
                }
            } else {
                throw new IllegalStateException(("Illegal state " + obj).toString());
            }
        }
    }

    public String toString() {
        while (true) {
            Object obj = this._state;
            if (obj instanceof Empty) {
                return "Mutex[" + ((Empty) obj).locked + ']';
            }
            if (!(obj instanceof OpDescriptor)) {
                if (!(obj instanceof LockedQueue)) {
                    throw new IllegalStateException(("Illegal state " + obj).toString());
                }
                return "Mutex[" + ((LockedQueue) obj).get_owner() + ']';
            }
            ((OpDescriptor) obj).perform(this);
        }
    }
}
