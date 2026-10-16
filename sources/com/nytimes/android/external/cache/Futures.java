package com.nytimes.android.external.cache;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class Futures {
    private Futures() {
    }

    @Nullable
    public static <V> ListenableFuture<V> immediateFuture(@Nullable V v) {
        if (v == null) {
            return ImmediateSuccessfulFuture.NULL;
        }
        return new ImmediateSuccessfulFuture(v);
    }

    @Nonnull
    public static <V> ListenableFuture<V> immediateFailedFuture(Throwable th) {
        Preconditions.checkNotNull(th);
        return new ImmediateFailedFuture(th);
    }

    @Nonnull
    public static <I, O> ListenableFuture<O> transform(@Nonnull ListenableFuture<I> listenableFuture, Function<? super I, ? extends O> function) {
        Preconditions.checkNotNull(function);
        ChainingFuture chainingFuture = new ChainingFuture(listenableFuture, function);
        listenableFuture.addListener(chainingFuture, DirectExecutor.INSTANCE);
        return chainingFuture;
    }

    public static <V, X extends Exception> V getChecked(@Nonnull Future<V> future, Class<X> cls) throws Exception {
        return (V) FuturesGetChecked.getChecked(future, cls);
    }

    public static <V, X extends Exception> V getChecked(@Nonnull Future<V> future, Class<X> cls, long j, @Nonnull TimeUnit timeUnit) throws Exception {
        return (V) FuturesGetChecked.getChecked(future, cls, j, timeUnit);
    }

    private static final class ChainingFuture<I, O> extends AbstractChainingFuture<I, O, Function<? super I, ? extends O>> {
        ChainingFuture(ListenableFuture<? extends I> listenableFuture, Function<? super I, ? extends O> function) {
            super(listenableFuture, function);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.nytimes.android.external.cache.Futures.AbstractChainingFuture
        public void doTransform(@Nonnull Function<? super I, ? extends O> function, I i) {
            set(function.apply(i));
        }
    }

    private static abstract class AbstractChainingFuture<I, O, F> extends AbstractFuture.TrustedFuture<O> implements Runnable {

        @Nullable
        F function;

        @Nullable
        ListenableFuture<? extends I> inputFuture;

        abstract void doTransform(F f, I i) throws Exception;

        AbstractChainingFuture(ListenableFuture<? extends I> listenableFuture, F f) {
            this.inputFuture = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
            this.function = (F) Preconditions.checkNotNull(f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public final void run() {
            try {
                ListenableFuture<? extends I> listenableFuture = this.inputFuture;
                F f = this.function;
                boolean z = true;
                boolean zIsCancelled = isCancelled() | (listenableFuture == null);
                if (f != null) {
                    z = false;
                }
                if (zIsCancelled || z) {
                    return;
                }
                this.inputFuture = null;
                this.function = null;
                try {
                    doTransform(f, Uninterruptibles.getUninterruptibly(listenableFuture));
                } catch (CancellationException unused) {
                    cancel(false);
                } catch (ExecutionException e) {
                    setException(e.getCause());
                }
            } catch (UndeclaredThrowableException e2) {
                setException(e2.getCause());
            } catch (Throwable th) {
                setException(th);
            }
        }

        @Override // com.nytimes.android.external.cache.AbstractFuture
        final void done() {
            maybePropagateCancellation(this.inputFuture);
            this.inputFuture = null;
        }
    }

    private static class ImmediateFailedFuture<V> extends ImmediateFuture<V> {
        private final Throwable thrown;

        ImmediateFailedFuture(Throwable th) {
            super();
            this.thrown = th;
        }

        @Override // com.nytimes.android.external.cache.Futures.ImmediateFuture, java.util.concurrent.Future
        @Nonnull
        public V get() throws ExecutionException {
            throw new ExecutionException(this.thrown);
        }
    }

    private static class ImmediateSuccessfulFuture<V> extends ImmediateFuture<V> {

        @Nullable
        static final ImmediateSuccessfulFuture<Object> NULL = new ImmediateSuccessfulFuture<>(null);
        private final V value;

        ImmediateSuccessfulFuture(V v) {
            super();
            this.value = v;
        }

        @Override // com.nytimes.android.external.cache.Futures.ImmediateFuture, java.util.concurrent.Future
        public V get() {
            return this.value;
        }
    }

    private static abstract class ImmediateFuture<V> implements ListenableFuture<V> {
        private static final Logger log = Logger.getLogger(ImmediateFuture.class.getName());

        @Override // java.util.concurrent.Future
        public boolean cancel(boolean z) {
            return false;
        }

        @Override // java.util.concurrent.Future
        public abstract V get() throws ExecutionException;

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return false;
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return true;
        }

        private ImmediateFuture() {
        }

        @Override // com.nytimes.android.external.cache.ListenableFuture
        public void addListener(@Nonnull Runnable runnable, @Nonnull Executor executor) {
            Preconditions.checkNotNull(runnable, "Runnable was null.");
            Preconditions.checkNotNull(executor, "Executor was null.");
            try {
                executor.execute(runnable);
            } catch (RuntimeException e) {
                log.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e);
            }
        }

        @Override // java.util.concurrent.Future
        public V get(long j, @Nonnull TimeUnit timeUnit) throws ExecutionException {
            Preconditions.checkNotNull(timeUnit);
            return get();
        }
    }
}
