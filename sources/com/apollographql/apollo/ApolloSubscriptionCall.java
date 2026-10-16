package com.apollographql.apollo;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.Subscription;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.internal.util.Cancelable;

/* JADX INFO: loaded from: classes.dex */
public interface ApolloSubscriptionCall<T> extends Cancelable {

    public enum CachePolicy {
        NO_CACHE,
        NETWORK_ONLY,
        CACHE_AND_NETWORK
    }

    public interface Callback<T> {
        void onCompleted();

        void onConnected();

        void onFailure(ApolloException apolloException);

        void onResponse(Response<T> response);

        void onTerminated();
    }

    public interface Factory {
        <D extends Operation.Data, T, V extends Operation.Variables> ApolloSubscriptionCall<T> subscribe(Subscription<D, T, V> subscription);
    }

    ApolloSubscriptionCall<T> cachePolicy(CachePolicy cachePolicy);

    ApolloSubscriptionCall<T> clone();

    void execute(Callback<T> callback);
}
