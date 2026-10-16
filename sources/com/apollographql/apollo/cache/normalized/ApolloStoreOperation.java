package com.apollographql.apollo.cache.normalized;

import androidx.exifinterface.media.ExifInterface;
import com.apollographql.apollo.exception.ApolloException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ApolloStoreOperation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0007\b&\u0018\u0000 \u0018*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0002\u0017\u0018B\u000f\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\r\u001a\u00020\f2\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\bH\u0016J\u000b\u0010\u000e\u001a\u00028\u0000¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012J\u0013\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00028\u0000¢\u0006\u0002\u0010\u0015J\r\u0010\u0016\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u000fR\u001c\u0010\u0006\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/ApolloStoreOperation;", ExifInterface.GPS_DIRECTION_TRUE, "", "dispatcher", "Ljava/util/concurrent/Executor;", "(Ljava/util/concurrent/Executor;)V", "callback", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/apollographql/apollo/cache/normalized/ApolloStoreOperation$Callback;", "executed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "checkIfExecuted", "", "enqueue", "execute", "()Ljava/lang/Object;", "notifyFailure", "t", "", "notifySuccess", "result", "(Ljava/lang/Object;)V", "perform", "Callback", "Companion", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
public abstract class ApolloStoreOperation<T> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final AtomicReference<Callback<T>> callback;
    private final Executor dispatcher;
    private final AtomicBoolean executed;

    /* JADX INFO: compiled from: ApolloStoreOperation.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0015\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00028\u0001H&¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/ApolloStoreOperation$Callback;", ExifInterface.GPS_DIRECTION_TRUE, "", "onFailure", "", "t", "", "onSuccess", "result", "(Ljava/lang/Object;)V", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
    public interface Callback<T> {
        void onFailure(Throwable t);

        void onSuccess(T result);
    }

    @JvmStatic
    public static final Executor emptyExecutor() {
        return INSTANCE.emptyExecutor();
    }

    @JvmStatic
    public static final <T> ApolloStoreOperation<T> emptyOperation(T t) {
        return INSTANCE.emptyOperation(t);
    }

    protected abstract T perform();

    protected ApolloStoreOperation(Executor dispatcher) {
        Intrinsics.checkParameterIsNotNull(dispatcher, "dispatcher");
        this.dispatcher = dispatcher;
        this.callback = new AtomicReference<>();
        this.executed = new AtomicBoolean();
    }

    public final T execute() throws ApolloException {
        checkIfExecuted();
        try {
            return perform();
        } catch (Exception e) {
            throw new ApolloException("Failed to perform store operation", e);
        }
    }

    public void enqueue(Callback<T> callback) {
        checkIfExecuted();
        this.callback.set(callback);
        this.dispatcher.execute(new Runnable() { // from class: com.apollographql.apollo.cache.normalized.ApolloStoreOperation.enqueue.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    ApolloStoreOperation.this.notifySuccess(ApolloStoreOperation.this.perform());
                } catch (Exception e) {
                    ApolloStoreOperation.this.notifyFailure(new ApolloException("Failed to perform store operation", e));
                }
            }
        });
    }

    public final void notifySuccess(T result) {
        Callback<T> andSet = this.callback.getAndSet(null);
        if (andSet != null) {
            andSet.onSuccess(result);
        }
    }

    public final void notifyFailure(Throwable t) {
        Intrinsics.checkParameterIsNotNull(t, "t");
        Callback<T> andSet = this.callback.getAndSet(null);
        if (andSet != null) {
            andSet.onFailure(t);
        }
    }

    private final void checkIfExecuted() {
        if (!this.executed.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed".toString());
        }
    }

    /* JADX INFO: compiled from: ApolloStoreOperation.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J!\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0001\u0010\u00072\u0006\u0010\b\u001a\u0002H\u0007H\u0007¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/ApolloStoreOperation$Companion;", "", "()V", "emptyExecutor", "Ljava/util/concurrent/Executor;", "emptyOperation", "Lcom/apollographql/apollo/cache/normalized/ApolloStoreOperation;", ExifInterface.GPS_DIRECTION_TRUE, "result", "(Ljava/lang/Object;)Lcom/apollographql/apollo/cache/normalized/ApolloStoreOperation;", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final <T> ApolloStoreOperation<T> emptyOperation(final T result) {
            final Executor executorEmptyExecutor = emptyExecutor();
            return new ApolloStoreOperation<T>(executorEmptyExecutor) { // from class: com.apollographql.apollo.cache.normalized.ApolloStoreOperation$Companion$emptyOperation$1
                @Override // com.apollographql.apollo.cache.normalized.ApolloStoreOperation
                protected T perform() {
                    return (T) result;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.apollographql.apollo.cache.normalized.ApolloStoreOperation
                public void enqueue(ApolloStoreOperation.Callback<T> callback) {
                    if (callback != 0) {
                        callback.onSuccess(result);
                    }
                }
            };
        }

        @JvmStatic
        public final Executor emptyExecutor() {
            return new Executor() { // from class: com.apollographql.apollo.cache.normalized.ApolloStoreOperation$Companion$emptyExecutor$1
                @Override // java.util.concurrent.Executor
                public final void execute(Runnable runnable) {
                }
            };
        }
    }
}
