package com.apollographql.apollo;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.fetcher.ResponseFetcher;
import com.apollographql.apollo.internal.util.Cancelable;

/* JADX INFO: loaded from: classes.dex */
public interface ApolloQueryWatcher<T> extends Cancelable {
    @Override // com.apollographql.apollo.internal.util.Cancelable
    void cancel();

    ApolloQueryWatcher<T> clone();

    ApolloQueryWatcher<T> enqueueAndWatch(ApolloCall.Callback<T> callback);

    Operation operation();

    void refetch();

    ApolloQueryWatcher<T> refetchResponseFetcher(ResponseFetcher responseFetcher);
}
