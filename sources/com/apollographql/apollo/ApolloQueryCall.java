package com.apollographql.apollo;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.cache.http.HttpCachePolicy;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.fetcher.ResponseFetcher;
import com.apollographql.apollo.request.RequestHeaders;

/* JADX INFO: loaded from: classes.dex */
public interface ApolloQueryCall<T> extends ApolloCall<T> {

    public interface Builder<T> extends ApolloCall.Builder<T> {
        @Override // com.apollographql.apollo.ApolloCall.Builder
        ApolloQueryCall<T> build();

        @Override // com.apollographql.apollo.ApolloCall.Builder
        Builder<T> cacheHeaders(CacheHeaders cacheHeaders);

        Builder<T> httpCachePolicy(HttpCachePolicy.Policy policy);

        Builder<T> requestHeaders(RequestHeaders requestHeaders);

        Builder<T> responseFetcher(ResponseFetcher responseFetcher);
    }

    public interface Factory {
        <D extends Operation.Data, T, V extends Operation.Variables> ApolloQueryCall<T> query(Query<D, T, V> query);
    }

    @Override // com.apollographql.apollo.ApolloCall
    ApolloQueryCall<T> cacheHeaders(CacheHeaders cacheHeaders);

    @Override // com.apollographql.apollo.ApolloCall
    ApolloQueryCall<T> clone();

    ApolloQueryCall<T> httpCachePolicy(HttpCachePolicy.Policy policy);

    @Deprecated
    ApolloQueryCall<T> requestHeaders(RequestHeaders requestHeaders);

    @Deprecated
    ApolloQueryCall<T> responseFetcher(ResponseFetcher responseFetcher);

    @Override // com.apollographql.apollo.ApolloCall
    Builder<T> toBuilder();

    ApolloQueryWatcher<T> watcher();
}
