package com.apollographql.apollo.internal;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloQueryWatcher;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.internal.ApolloLogger;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.cache.normalized.ApolloStore;
import com.apollographql.apollo.exception.ApolloCanceledException;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.exception.ApolloHttpException;
import com.apollographql.apollo.exception.ApolloNetworkException;
import com.apollographql.apollo.exception.ApolloParseException;
import com.apollographql.apollo.fetcher.ApolloResponseFetchers;
import com.apollographql.apollo.fetcher.ResponseFetcher;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class RealApolloQueryWatcher<T> implements ApolloQueryWatcher<T> {
    private RealApolloCall<T> activeCall;
    final ApolloStore apolloStore;
    final ApolloLogger logger;
    private final ApolloCallTracker tracker;
    private ResponseFetcher refetchResponseFetcher = ApolloResponseFetchers.CACHE_FIRST;
    Set<String> dependentKeys = Collections.emptySet();
    final ApolloStore.RecordChangeSubscriber recordChangeSubscriber = new ApolloStore.RecordChangeSubscriber() { // from class: com.apollographql.apollo.internal.RealApolloQueryWatcher.1
        @Override // com.apollographql.apollo.cache.normalized.ApolloStore.RecordChangeSubscriber
        public void onCacheRecordsChanged(Set<String> set) {
            if (RealApolloQueryWatcher.this.dependentKeys.isEmpty() || !RealApolloQueryWatcher.areDisjoint(RealApolloQueryWatcher.this.dependentKeys, set)) {
                RealApolloQueryWatcher.this.refetch();
            }
        }
    };
    private final AtomicReference<CallState> state = new AtomicReference<>(CallState.IDLE);
    private final AtomicReference<ApolloCall.Callback<T>> originalCallback = new AtomicReference<>();

    RealApolloQueryWatcher(RealApolloCall<T> realApolloCall, ApolloStore apolloStore, ApolloLogger apolloLogger, ApolloCallTracker apolloCallTracker) {
        this.activeCall = realApolloCall;
        this.apolloStore = apolloStore;
        this.logger = apolloLogger;
        this.tracker = apolloCallTracker;
    }

    @Override // com.apollographql.apollo.ApolloQueryWatcher
    public ApolloQueryWatcher<T> enqueueAndWatch(ApolloCall.Callback<T> callback) {
        try {
            activate(Optional.fromNullable(callback));
            this.activeCall.enqueue(callbackProxy());
            return this;
        } catch (ApolloCanceledException e) {
            if (callback != null) {
                callback.onCanceledError(e);
            } else {
                this.logger.e(e, "Operation: %s was canceled", operation().name().name());
            }
            return this;
        }
    }

    @Override // com.apollographql.apollo.ApolloQueryWatcher
    public synchronized RealApolloQueryWatcher<T> refetchResponseFetcher(ResponseFetcher responseFetcher) {
        if (this.state.get() != CallState.IDLE) {
            throw new IllegalStateException("Already Executed");
        }
        Utils.checkNotNull(responseFetcher, "responseFetcher == null");
        this.refetchResponseFetcher = responseFetcher;
        return this;
    }

    /* JADX INFO: renamed from: com.apollographql.apollo.internal.RealApolloQueryWatcher$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$apollographql$apollo$internal$CallState;

        static {
            int[] iArr = new int[CallState.values().length];
            $SwitchMap$com$apollographql$apollo$internal$CallState = iArr;
            try {
                iArr[CallState.ACTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$apollographql$apollo$internal$CallState[CallState.IDLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$apollographql$apollo$internal$CallState[CallState.CANCELED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$apollographql$apollo$internal$CallState[CallState.TERMINATED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.apollographql.apollo.ApolloQueryWatcher, com.apollographql.apollo.internal.util.Cancelable
    public synchronized void cancel() {
        int i = AnonymousClass3.$SwitchMap$com$apollographql$apollo$internal$CallState[this.state.get().ordinal()];
        if (i == 1) {
            try {
                this.activeCall.cancel();
                this.apolloStore.unsubscribe(this.recordChangeSubscriber);
                this.tracker.unregisterQueryWatcher(this);
                this.originalCallback.set(null);
                this.state.set(CallState.CANCELED);
            } catch (Throwable th) {
                this.tracker.unregisterQueryWatcher(this);
                this.originalCallback.set(null);
                this.state.set(CallState.CANCELED);
                throw th;
            }
        } else if (i == 2) {
            this.state.set(CallState.CANCELED);
        } else if (i != 3 && i != 4) {
            throw new IllegalStateException("Unknown state");
        }
    }

    @Override // com.apollographql.apollo.internal.util.Cancelable
    public boolean isCanceled() {
        return this.state.get() == CallState.CANCELED;
    }

    @Override // com.apollographql.apollo.ApolloQueryWatcher
    public Operation operation() {
        return this.activeCall.operation();
    }

    @Override // com.apollographql.apollo.ApolloQueryWatcher
    public synchronized void refetch() {
        int i = AnonymousClass3.$SwitchMap$com$apollographql$apollo$internal$CallState[this.state.get().ordinal()];
        if (i != 1) {
            if (i == 2) {
                throw new IllegalStateException("Cannot refetch a watcher which has not first called enqueueAndWatch.");
            }
            if (i == 3) {
                throw new IllegalStateException("Cannot refetch a canceled watcher,");
            }
            if (i == 4) {
                throw new IllegalStateException("Cannot refetch a watcher which has experienced an error.");
            }
            throw new IllegalStateException("Unknown state");
        }
        this.apolloStore.unsubscribe(this.recordChangeSubscriber);
        this.activeCall.cancel();
        RealApolloCall<T> realApolloCallResponseFetcher = this.activeCall.m6clone().responseFetcher(this.refetchResponseFetcher);
        this.activeCall = realApolloCallResponseFetcher;
        realApolloCallResponseFetcher.enqueue(callbackProxy());
    }

    @Override // com.apollographql.apollo.ApolloQueryWatcher
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public ApolloQueryWatcher<T> m8clone() {
        return new RealApolloQueryWatcher(this.activeCall.m6clone(), this.apolloStore, this.logger, this.tracker);
    }

    private ApolloCall.Callback<T> callbackProxy() {
        return new ApolloCall.Callback<T>() { // from class: com.apollographql.apollo.internal.RealApolloQueryWatcher.2
            @Override // com.apollographql.apollo.ApolloCall.Callback
            public void onResponse(Response<T> response) {
                Optional<ApolloCall.Callback<T>> optionalResponseCallback = RealApolloQueryWatcher.this.responseCallback();
                if (!optionalResponseCallback.isPresent()) {
                    RealApolloQueryWatcher.this.logger.d("onResponse for watched operation: %s. No callback present.", RealApolloQueryWatcher.this.operation().name().name());
                    return;
                }
                RealApolloQueryWatcher.this.dependentKeys = response.getDependentKeys();
                RealApolloQueryWatcher.this.apolloStore.subscribe(RealApolloQueryWatcher.this.recordChangeSubscriber);
                optionalResponseCallback.get().onResponse(response);
            }

            @Override // com.apollographql.apollo.ApolloCall.Callback
            public void onFailure(ApolloException apolloException) {
                Optional<ApolloCall.Callback<T>> optionalTerminate = RealApolloQueryWatcher.this.terminate();
                if (!optionalTerminate.isPresent()) {
                    RealApolloQueryWatcher.this.logger.d(apolloException, "onFailure for operation: %s. No callback present.", RealApolloQueryWatcher.this.operation().name().name());
                    return;
                }
                if (apolloException instanceof ApolloHttpException) {
                    optionalTerminate.get().onHttpError((ApolloHttpException) apolloException);
                    return;
                }
                if (apolloException instanceof ApolloParseException) {
                    optionalTerminate.get().onParseError((ApolloParseException) apolloException);
                } else if (apolloException instanceof ApolloNetworkException) {
                    optionalTerminate.get().onNetworkError((ApolloNetworkException) apolloException);
                } else {
                    optionalTerminate.get().onFailure(apolloException);
                }
            }

            @Override // com.apollographql.apollo.ApolloCall.Callback
            public void onStatusEvent(ApolloCall.StatusEvent statusEvent) {
                ApolloCall.Callback callback = (ApolloCall.Callback) RealApolloQueryWatcher.this.originalCallback.get();
                if (callback == null) {
                    RealApolloQueryWatcher.this.logger.d("onStatusEvent for operation: %s. No callback present.", RealApolloQueryWatcher.this.operation().name().name());
                } else {
                    callback.onStatusEvent(statusEvent);
                }
            }
        };
    }

    private synchronized void activate(Optional<ApolloCall.Callback<T>> optional) throws ApolloCanceledException {
        int i = AnonymousClass3.$SwitchMap$com$apollographql$apollo$internal$CallState[this.state.get().ordinal()];
        if (i != 1) {
            if (i == 2) {
                this.originalCallback.set(optional.orNull());
                this.tracker.registerQueryWatcher(this);
                this.state.set(CallState.ACTIVE);
            } else {
                if (i == 3) {
                    throw new ApolloCanceledException();
                }
                if (i != 4) {
                    throw new IllegalStateException("Unknown state");
                }
            }
        }
        throw new IllegalStateException("Already Executed");
    }

    synchronized Optional<ApolloCall.Callback<T>> responseCallback() {
        int i = AnonymousClass3.$SwitchMap$com$apollographql$apollo$internal$CallState[this.state.get().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        throw new IllegalStateException("Unknown state");
                    }
                }
            }
            throw new IllegalStateException(CallState.IllegalStateMessage.forCurrentState(this.state.get()).expected(CallState.ACTIVE, CallState.CANCELED));
        }
        return Optional.fromNullable(this.originalCallback.get());
    }

    synchronized Optional<ApolloCall.Callback<T>> terminate() {
        int i = AnonymousClass3.$SwitchMap$com$apollographql$apollo$internal$CallState[this.state.get().ordinal()];
        if (i == 1) {
            this.tracker.unregisterQueryWatcher(this);
            this.state.set(CallState.TERMINATED);
            return Optional.fromNullable(this.originalCallback.getAndSet(null));
        }
        if (i != 2) {
            if (i == 3) {
                return Optional.fromNullable(this.originalCallback.getAndSet(null));
            }
            if (i != 4) {
                throw new IllegalStateException("Unknown state");
            }
        }
        throw new IllegalStateException(CallState.IllegalStateMessage.forCurrentState(this.state.get()).expected(CallState.ACTIVE, CallState.CANCELED));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> boolean areDisjoint(Set<E> set, Set<E> set2) {
        if (set != null && set2 != null) {
            if (set.size() > set2.size()) {
                set2 = set;
                set = set2;
            }
            Iterator<E> it = set.iterator();
            while (it.hasNext()) {
                if (set2.contains(it.next())) {
                    return false;
                }
            }
        }
        return true;
    }
}
