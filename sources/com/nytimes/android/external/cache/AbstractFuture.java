package com.nytimes.android.external.cache;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractFuture<V> implements ListenableFuture<V> {
    private static final AtomicHelper ATOMIC_HELPER;
    private static final Object NULL;
    private static final long SPIN_THRESHOLD_NANOS = 1000;
    private volatile Listener listeners;
    private volatile Object value;
    private volatile Waiter waiters;
    private static final boolean GENERATE_CANCELLATION_CAUSES = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    private static final Logger log = Logger.getLogger(AbstractFuture.class.getName());

    void done() {
    }

    protected void interruptTask() {
    }

    static {
        AtomicHelper synchronizedHelper;
        try {
            synchronizedHelper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Thread.class, "thread"), AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Waiter.class, "next"), AtomicReferenceFieldUpdater.newUpdater(AbstractFuture.class, Waiter.class, "waiters"), AtomicReferenceFieldUpdater.newUpdater(AbstractFuture.class, Listener.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(AbstractFuture.class, Object.class, "value"));
        } catch (Throwable th) {
            log.log(Level.SEVERE, "UnsafeAtomicHelper is broken!");
            log.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
            synchronizedHelper = new SynchronizedHelper();
        }
        ATOMIC_HELPER = synchronizedHelper;
        NULL = new Object();
    }

    public static abstract class TrustedFuture<V> extends AbstractFuture<V> {
        @Override // com.nytimes.android.external.cache.AbstractFuture, java.util.concurrent.Future
        @Nullable
        public final V get() throws ExecutionException, InterruptedException {
            return (V) super.get();
        }

        @Override // com.nytimes.android.external.cache.AbstractFuture, java.util.concurrent.Future
        @Nullable
        public final V get(long j, @Nonnull TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
            return (V) super.get(j, timeUnit);
        }

        @Override // com.nytimes.android.external.cache.AbstractFuture, java.util.concurrent.Future
        public final boolean isDone() {
            return super.isDone();
        }

        @Override // com.nytimes.android.external.cache.AbstractFuture, java.util.concurrent.Future
        public final boolean isCancelled() {
            return super.isCancelled();
        }

        @Override // com.nytimes.android.external.cache.AbstractFuture, com.nytimes.android.external.cache.ListenableFuture
        public final void addListener(@Nonnull Runnable runnable, @Nonnull Executor executor) {
            super.addListener(runnable, executor);
        }
    }

    private static final class Waiter {
        static final Waiter TOMBSTONE = new Waiter(false);
        volatile Waiter next;

        @Nullable
        volatile Thread thread;

        Waiter(boolean z) {
        }

        Waiter() {
            AbstractFuture.ATOMIC_HELPER.putThread(this, Thread.currentThread());
        }

        void setNext(Waiter waiter) {
            AbstractFuture.ATOMIC_HELPER.putNext(this, waiter);
        }

        void unpark() {
            Thread thread = this.thread;
            if (thread != null) {
                this.thread = null;
                LockSupport.unpark(thread);
            }
        }
    }

    private void removeWaiter(@Nonnull Waiter waiter) {
        waiter.thread = null;
        while (true) {
            Waiter waiter2 = this.waiters;
            if (waiter2 == Waiter.TOMBSTONE) {
                return;
            }
            Waiter waiter3 = null;
            while (waiter2 != null) {
                Waiter waiter4 = waiter2.next;
                if (waiter2.thread != null) {
                    waiter3 = waiter2;
                } else if (waiter3 != null) {
                    waiter3.next = waiter4;
                    if (waiter3.thread == null) {
                    }
                } else if (!ATOMIC_HELPER.casWaiters(this, waiter2, waiter4)) {
                }
                waiter2 = waiter4;
            }
            return;
        }
    }

    private static final class Listener {

        @Nullable
        static final Listener TOMBSTONE = new Listener(null, null);
        final Executor executor;

        @Nullable
        Listener next;
        final Runnable task;

        Listener(Runnable runnable, Executor executor) {
            this.task = runnable;
            this.executor = executor;
        }
    }

    private static final class Failure {
        static final Failure FALLBACK_INSTANCE = new Failure(new Throwable("Failure occurred while trying to finish a future.") { // from class: com.nytimes.android.external.cache.AbstractFuture.Failure.1
            @Override // java.lang.Throwable
            @Nonnull
            public synchronized Throwable fillInStackTrace() {
                return this;
            }
        });

        @Nullable
        final Throwable exception;

        Failure(Throwable th) {
            this.exception = (Throwable) Preconditions.checkNotNull(th);
        }
    }

    private static final class Cancellation {
        final Throwable cause;
        final boolean wasInterrupted;

        Cancellation(boolean z, Throwable th) {
            this.wasInterrupted = z;
            this.cause = th;
        }
    }

    private final class SetFuture implements Runnable {
        final ListenableFuture<? extends V> future;

        SetFuture(ListenableFuture<? extends V> listenableFuture) {
            this.future = listenableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (AbstractFuture.this.value != this) {
                return;
            }
            AbstractFuture.this.completeWithFuture(this.future, this);
        }
    }

    protected AbstractFuture() {
    }

    @Override // java.util.concurrent.Future
    @Nullable
    public V get(long j, @Nonnull TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        long nanos = timeUnit.toNanos(j);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj = this.value;
        if ((obj != null) & (!(obj instanceof SetFuture))) {
            return getDoneValue(obj);
        }
        long jNanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
        if (nanos >= 1000) {
            Waiter waiter = this.waiters;
            if (waiter != Waiter.TOMBSTONE) {
                Waiter waiter2 = new Waiter();
                while (true) {
                    waiter2.setNext(waiter);
                    if (ATOMIC_HELPER.casWaiters(this, waiter, waiter2)) {
                        do {
                            LockSupport.parkNanos(this, nanos);
                            if (Thread.interrupted()) {
                                removeWaiter(waiter2);
                                throw new InterruptedException();
                            }
                            Object obj2 = this.value;
                            if ((obj2 != null) & (!(obj2 instanceof SetFuture))) {
                                return getDoneValue(obj2);
                            }
                            nanos = jNanoTime - System.nanoTime();
                        } while (nanos >= 1000);
                        removeWaiter(waiter2);
                        break;
                    }
                    waiter = this.waiters;
                    if (waiter == Waiter.TOMBSTONE) {
                    }
                }
            }
            return getDoneValue(this.value);
        }
        while (nanos > 0) {
            Object obj3 = this.value;
            if ((obj3 != null) & (!(obj3 instanceof SetFuture))) {
                return getDoneValue(obj3);
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            nanos = jNanoTime - System.nanoTime();
        }
        throw new TimeoutException();
    }

    @Override // java.util.concurrent.Future
    @Nullable
    public V get() throws ExecutionException, InterruptedException {
        Object obj;
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj2 = this.value;
        if ((obj2 != null) & (!(obj2 instanceof SetFuture))) {
            return getDoneValue(obj2);
        }
        Waiter waiter = this.waiters;
        if (waiter != Waiter.TOMBSTONE) {
            Waiter waiter2 = new Waiter();
            do {
                waiter2.setNext(waiter);
                if (ATOMIC_HELPER.casWaiters(this, waiter, waiter2)) {
                    do {
                        LockSupport.park(this);
                        if (Thread.interrupted()) {
                            removeWaiter(waiter2);
                            throw new InterruptedException();
                        }
                        obj = this.value;
                    } while (!((obj != null) & (!(obj instanceof SetFuture))));
                    return getDoneValue(obj);
                }
                waiter = this.waiters;
            } while (waiter != Waiter.TOMBSTONE);
        }
        return getDoneValue(this.value);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private V getDoneValue(Object obj) throws ExecutionException {
        if (obj instanceof Cancellation) {
            throw cancellationExceptionWithCause("Task was cancelled.", ((Cancellation) obj).cause);
        }
        if (obj instanceof Failure) {
            throw new ExecutionException(((Failure) obj).exception);
        }
        if (obj == NULL) {
            return null;
        }
        return obj;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        Object obj = this.value;
        return (!(obj instanceof SetFuture)) & (obj != null);
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.value instanceof Cancellation;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        Object obj = this.value;
        if ((obj == null) | (obj instanceof SetFuture)) {
            Cancellation cancellation = new Cancellation(z, GENERATE_CANCELLATION_CAUSES ? newCancellationCause() : null);
            while (!ATOMIC_HELPER.casValue(this, obj, cancellation)) {
                obj = this.value;
                if (!(obj instanceof SetFuture)) {
                }
            }
            if (z) {
                interruptTask();
            }
            complete();
            if (obj instanceof SetFuture) {
                ((SetFuture) obj).future.cancel(z);
            }
            return true;
        }
        return false;
    }

    @Nonnull
    private Throwable newCancellationCause() {
        return new CancellationException("Future.cancel() was called.");
    }

    protected final boolean wasInterrupted() {
        Object obj = this.value;
        return (obj instanceof Cancellation) && ((Cancellation) obj).wasInterrupted;
    }

    @Override // com.nytimes.android.external.cache.ListenableFuture
    public void addListener(@Nonnull Runnable runnable, @Nonnull Executor executor) {
        Preconditions.checkNotNull(runnable, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        Listener listener = this.listeners;
        if (listener != Listener.TOMBSTONE) {
            Listener listener2 = new Listener(runnable, executor);
            do {
                listener2.next = listener;
                if (ATOMIC_HELPER.casListeners(this, listener, listener2)) {
                    return;
                } else {
                    listener = this.listeners;
                }
            } while (listener != Listener.TOMBSTONE);
        }
        executeListener(runnable, executor);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    protected boolean set(@Nullable V v) {
        if (v == null) {
            v = (V) NULL;
        }
        if (!ATOMIC_HELPER.casValue(this, null, v)) {
            return false;
        }
        complete();
        return true;
    }

    protected boolean setException(Throwable th) {
        if (!ATOMIC_HELPER.casValue(this, null, new Failure((Throwable) Preconditions.checkNotNull(th)))) {
            return false;
        }
        complete();
        return true;
    }

    protected boolean setFuture(@Nonnull ListenableFuture<? extends V> listenableFuture) {
        Failure failure;
        Preconditions.checkNotNull(listenableFuture);
        Object obj = this.value;
        if (obj == null) {
            if (listenableFuture.isDone()) {
                return completeWithFuture(listenableFuture, null);
            }
            SetFuture setFuture = new SetFuture(listenableFuture);
            if (ATOMIC_HELPER.casValue(this, null, setFuture)) {
                try {
                    listenableFuture.addListener(setFuture, DirectExecutor.INSTANCE);
                    return true;
                } catch (Throwable th) {
                    try {
                        failure = new Failure(th);
                    } catch (Throwable unused) {
                        failure = Failure.FALLBACK_INSTANCE;
                    }
                    ATOMIC_HELPER.casValue(this, setFuture, failure);
                    return true;
                }
            }
            obj = this.value;
        }
        if (!(obj instanceof Cancellation)) {
            return false;
        }
        listenableFuture.cancel(((Cancellation) obj).wasInterrupted);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean completeWithFuture(ListenableFuture<? extends V> listenableFuture, Object obj) {
        Object failure;
        Object uninterruptibly;
        if (listenableFuture instanceof TrustedFuture) {
            uninterruptibly = ((AbstractFuture) listenableFuture).value;
        } else {
            try {
                uninterruptibly = Uninterruptibles.getUninterruptibly(listenableFuture);
                if (uninterruptibly == null) {
                    uninterruptibly = NULL;
                }
            } catch (CancellationException e) {
                failure = new Cancellation(false, e);
                uninterruptibly = failure;
            } catch (ExecutionException e2) {
                failure = new Failure(e2.getCause());
                uninterruptibly = failure;
            } catch (Throwable th) {
                failure = new Failure(th);
                uninterruptibly = failure;
            }
        }
        if (!ATOMIC_HELPER.casValue(this, obj, uninterruptibly)) {
            return false;
        }
        complete();
        return true;
    }

    private void complete() {
        for (Waiter waiterClearWaiters = clearWaiters(); waiterClearWaiters != null; waiterClearWaiters = waiterClearWaiters.next) {
            waiterClearWaiters.unpark();
        }
        Listener listenerClearListeners = clearListeners();
        Listener listener = null;
        while (listenerClearListeners != null) {
            Listener listener2 = listenerClearListeners.next;
            listenerClearListeners.next = listener;
            listener = listenerClearListeners;
            listenerClearListeners = listener2;
        }
        while (listener != null) {
            executeListener(listener.task, listener.executor);
            listener = listener.next;
        }
        done();
    }

    final void maybePropagateCancellation(@Nullable Future<?> future) {
        if ((future != null) && isCancelled()) {
            future.cancel(wasInterrupted());
        }
    }

    private Waiter clearWaiters() {
        Waiter waiter;
        do {
            waiter = this.waiters;
        } while (!ATOMIC_HELPER.casWaiters(this, waiter, Waiter.TOMBSTONE));
        return waiter;
    }

    private Listener clearListeners() {
        Listener listener;
        do {
            listener = this.listeners;
        } while (!ATOMIC_HELPER.casListeners(this, listener, Listener.TOMBSTONE));
        return listener;
    }

    private static void executeListener(@Nonnull Runnable runnable, @Nonnull Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            log.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e);
        }
    }

    @Nonnull
    static final CancellationException cancellationExceptionWithCause(String str, Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    private static abstract class AtomicHelper {
        abstract boolean casListeners(AbstractFuture<?> abstractFuture, Listener listener, Listener listener2);

        abstract boolean casValue(AbstractFuture<?> abstractFuture, Object obj, Object obj2);

        abstract boolean casWaiters(AbstractFuture<?> abstractFuture, Waiter waiter, Waiter waiter2);

        abstract void putNext(Waiter waiter, Waiter waiter2);

        abstract void putThread(Waiter waiter, Thread thread);

        private AtomicHelper() {
        }
    }

    private static final class SafeAtomicHelper extends AtomicHelper {
        final AtomicReferenceFieldUpdater<AbstractFuture, Listener> listenersUpdater;
        final AtomicReferenceFieldUpdater<AbstractFuture, Object> valueUpdater;
        final AtomicReferenceFieldUpdater<Waiter, Waiter> waiterNextUpdater;
        final AtomicReferenceFieldUpdater<Waiter, Thread> waiterThreadUpdater;
        final AtomicReferenceFieldUpdater<AbstractFuture, Waiter> waitersUpdater;

        SafeAtomicHelper(AtomicReferenceFieldUpdater<Waiter, Thread> atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater<Waiter, Waiter> atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater<AbstractFuture, Waiter> atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater<AbstractFuture, Listener> atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater<AbstractFuture, Object> atomicReferenceFieldUpdater5) {
            super();
            this.waiterThreadUpdater = atomicReferenceFieldUpdater;
            this.waiterNextUpdater = atomicReferenceFieldUpdater2;
            this.waitersUpdater = atomicReferenceFieldUpdater3;
            this.listenersUpdater = atomicReferenceFieldUpdater4;
            this.valueUpdater = atomicReferenceFieldUpdater5;
        }

        @Override // com.nytimes.android.external.cache.AbstractFuture.AtomicHelper
        void putThread(@Nonnull Waiter waiter, Thread thread) {
            this.waiterThreadUpdater.lazySet(waiter, thread);
        }

        @Override // com.nytimes.android.external.cache.AbstractFuture.AtomicHelper
        void putNext(@Nonnull Waiter waiter, Waiter waiter2) {
            this.waiterNextUpdater.lazySet(waiter, waiter2);
        }

        @Override // com.nytimes.android.external.cache.AbstractFuture.AtomicHelper
        boolean casWaiters(@Nonnull AbstractFuture<?> abstractFuture, Waiter waiter, Waiter waiter2) {
            return this.waitersUpdater.compareAndSet(abstractFuture, waiter, waiter2);
        }

        @Override // com.nytimes.android.external.cache.AbstractFuture.AtomicHelper
        boolean casListeners(@Nonnull AbstractFuture<?> abstractFuture, Listener listener, Listener listener2) {
            return this.listenersUpdater.compareAndSet(abstractFuture, listener, listener2);
        }

        @Override // com.nytimes.android.external.cache.AbstractFuture.AtomicHelper
        boolean casValue(@Nonnull AbstractFuture<?> abstractFuture, Object obj, Object obj2) {
            return this.valueUpdater.compareAndSet(abstractFuture, obj, obj2);
        }
    }

    private static final class SynchronizedHelper extends AtomicHelper {
        private SynchronizedHelper() {
            super();
        }

        @Override // com.nytimes.android.external.cache.AbstractFuture.AtomicHelper
        void putThread(@Nonnull Waiter waiter, Thread thread) {
            waiter.thread = thread;
        }

        @Override // com.nytimes.android.external.cache.AbstractFuture.AtomicHelper
        void putNext(@Nonnull Waiter waiter, Waiter waiter2) {
            waiter.next = waiter2;
        }

        @Override // com.nytimes.android.external.cache.AbstractFuture.AtomicHelper
        boolean casWaiters(@Nonnull AbstractFuture<?> abstractFuture, Waiter waiter, Waiter waiter2) {
            synchronized (abstractFuture) {
                if (((AbstractFuture) abstractFuture).waiters != waiter) {
                    return false;
                }
                ((AbstractFuture) abstractFuture).waiters = waiter2;
                return true;
            }
        }

        @Override // com.nytimes.android.external.cache.AbstractFuture.AtomicHelper
        boolean casListeners(@Nonnull AbstractFuture<?> abstractFuture, Listener listener, Listener listener2) {
            synchronized (abstractFuture) {
                if (((AbstractFuture) abstractFuture).listeners != listener) {
                    return false;
                }
                ((AbstractFuture) abstractFuture).listeners = listener2;
                return true;
            }
        }

        @Override // com.nytimes.android.external.cache.AbstractFuture.AtomicHelper
        boolean casValue(@Nonnull AbstractFuture<?> abstractFuture, Object obj, Object obj2) {
            synchronized (abstractFuture) {
                if (((AbstractFuture) abstractFuture).value != obj) {
                    return false;
                }
                ((AbstractFuture) abstractFuture).value = obj2;
                return true;
            }
        }
    }
}
