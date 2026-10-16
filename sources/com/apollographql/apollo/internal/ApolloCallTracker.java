package com.apollographql.apollo.internal;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloMutationCall;
import com.apollographql.apollo.ApolloPrefetch;
import com.apollographql.apollo.ApolloQueryCall;
import com.apollographql.apollo.ApolloQueryWatcher;
import com.apollographql.apollo.IdleResourceCallback;
import com.apollographql.apollo.api.Mutation;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.internal.Utils;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class ApolloCallTracker {
    private IdleResourceCallback idleResourceCallback;
    private final Map<OperationName, Set<ApolloPrefetch>> activePrefetchCalls = new HashMap();
    private final Map<OperationName, Set<ApolloQueryCall>> activeQueryCalls = new HashMap();
    private final Map<OperationName, Set<ApolloMutationCall>> activeMutationCalls = new HashMap();
    private final Map<OperationName, Set<ApolloQueryWatcher>> activeQueryWatchers = new HashMap();
    private final AtomicInteger activeCallCount = new AtomicInteger();

    void registerCall(ApolloCall apolloCall) {
        Utils.checkNotNull(apolloCall, "call == null");
        Operation operation = apolloCall.operation();
        if (operation instanceof Query) {
            registerQueryCall((ApolloQueryCall) apolloCall);
        } else {
            if (operation instanceof Mutation) {
                registerMutationCall((ApolloMutationCall) apolloCall);
                return;
            }
            throw new IllegalArgumentException("Unknown call type");
        }
    }

    void unregisterCall(ApolloCall apolloCall) {
        Utils.checkNotNull(apolloCall, "call == null");
        Operation operation = apolloCall.operation();
        if (operation instanceof Query) {
            unregisterQueryCall((ApolloQueryCall) apolloCall);
        } else {
            if (operation instanceof Mutation) {
                unregisterMutationCall((ApolloMutationCall) apolloCall);
                return;
            }
            throw new IllegalArgumentException("Unknown call type");
        }
    }

    void registerPrefetchCall(ApolloPrefetch apolloPrefetch) {
        Utils.checkNotNull(apolloPrefetch, "apolloPrefetch == null");
        registerCall(this.activePrefetchCalls, apolloPrefetch.operation().name(), apolloPrefetch);
        this.activeCallCount.incrementAndGet();
    }

    void unregisterPrefetchCall(ApolloPrefetch apolloPrefetch) {
        Utils.checkNotNull(apolloPrefetch, "apolloPrefetch == null");
        unregisterCall(this.activePrefetchCalls, apolloPrefetch.operation().name(), apolloPrefetch);
        decrementActiveCallCountAndNotify();
    }

    Set<ApolloPrefetch> activePrefetchCalls(OperationName operationName) {
        return activeCalls(this.activePrefetchCalls, operationName);
    }

    void registerQueryCall(ApolloQueryCall apolloQueryCall) {
        Utils.checkNotNull(apolloQueryCall, "apolloQueryCall == null");
        registerCall(this.activeQueryCalls, apolloQueryCall.operation().name(), apolloQueryCall);
        this.activeCallCount.incrementAndGet();
    }

    void unregisterQueryCall(ApolloQueryCall apolloQueryCall) {
        Utils.checkNotNull(apolloQueryCall, "apolloQueryCall == null");
        unregisterCall(this.activeQueryCalls, apolloQueryCall.operation().name(), apolloQueryCall);
        decrementActiveCallCountAndNotify();
    }

    Set<ApolloQueryCall> activeQueryCalls(OperationName operationName) {
        return activeCalls(this.activeQueryCalls, operationName);
    }

    void registerMutationCall(ApolloMutationCall apolloMutationCall) {
        Utils.checkNotNull(apolloMutationCall, "apolloMutationCall == null");
        registerCall(this.activeMutationCalls, apolloMutationCall.operation().name(), apolloMutationCall);
        this.activeCallCount.incrementAndGet();
    }

    void unregisterMutationCall(ApolloMutationCall apolloMutationCall) {
        Utils.checkNotNull(apolloMutationCall, "apolloMutationCall == null");
        unregisterCall(this.activeMutationCalls, apolloMutationCall.operation().name(), apolloMutationCall);
        decrementActiveCallCountAndNotify();
    }

    Set<ApolloMutationCall> activeMutationCalls(OperationName operationName) {
        return activeCalls(this.activeMutationCalls, operationName);
    }

    void registerQueryWatcher(ApolloQueryWatcher apolloQueryWatcher) {
        Utils.checkNotNull(apolloQueryWatcher, "queryWatcher == null");
        registerCall(this.activeQueryWatchers, apolloQueryWatcher.operation().name(), apolloQueryWatcher);
    }

    void unregisterQueryWatcher(ApolloQueryWatcher apolloQueryWatcher) {
        Utils.checkNotNull(apolloQueryWatcher, "queryWatcher == null");
        unregisterCall(this.activeQueryWatchers, apolloQueryWatcher.operation().name(), apolloQueryWatcher);
    }

    Set<ApolloQueryWatcher> activeQueryWatchers(OperationName operationName) {
        return activeCalls(this.activeQueryWatchers, operationName);
    }

    public synchronized void setIdleResourceCallback(IdleResourceCallback idleResourceCallback) {
        this.idleResourceCallback = idleResourceCallback;
    }

    public int activeCallsCount() {
        return this.activeCallCount.get();
    }

    private <CALL> void registerCall(Map<OperationName, Set<CALL>> map, OperationName operationName, CALL call) {
        synchronized (map) {
            Set<CALL> hashSet = map.get(operationName);
            if (hashSet == null) {
                hashSet = new HashSet<>();
                map.put(operationName, hashSet);
            }
            hashSet.add(call);
        }
    }

    private <CALL> void unregisterCall(Map<OperationName, Set<CALL>> map, OperationName operationName, CALL call) {
        synchronized (map) {
            Set<CALL> set = map.get(operationName);
            if (set == null || !set.remove(call)) {
                throw new AssertionError("Call wasn't registered before");
            }
            if (set.isEmpty()) {
                map.remove(operationName);
            }
        }
    }

    private <CALL> Set<CALL> activeCalls(Map<OperationName, Set<CALL>> map, OperationName operationName) {
        Set<CALL> hashSet;
        Utils.checkNotNull(operationName, "operationName == null");
        synchronized (map) {
            Set<CALL> set = map.get(operationName);
            hashSet = set != null ? new HashSet<>(set) : Collections.emptySet();
        }
        return hashSet;
    }

    private void decrementActiveCallCountAndNotify() {
        IdleResourceCallback idleResourceCallback;
        if (this.activeCallCount.decrementAndGet() != 0 || (idleResourceCallback = this.idleResourceCallback) == null) {
            return;
        }
        idleResourceCallback.onIdle();
    }
}
