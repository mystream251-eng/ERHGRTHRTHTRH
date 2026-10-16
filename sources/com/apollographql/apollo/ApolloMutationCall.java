package com.apollographql.apollo;

import com.apollographql.apollo.api.Mutation;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.request.RequestHeaders;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ApolloMutationCall<T> extends ApolloCall<T> {

    public interface Builder<T> extends ApolloCall.Builder<T> {
        @Override // com.apollographql.apollo.ApolloCall.Builder
        ApolloMutationCall<T> build();

        @Override // com.apollographql.apollo.ApolloCall.Builder
        Builder<T> cacheHeaders(CacheHeaders cacheHeaders);

        Builder<T> refetchQueries(List<Query> list);

        Builder<T> refetchQueryNames(List<OperationName> list);

        Builder<T> requestHeaders(RequestHeaders requestHeaders);
    }

    public interface Factory {
        <D extends Operation.Data, T, V extends Operation.Variables> ApolloMutationCall<T> mutate(Mutation<D, T, V> mutation);

        <D extends Operation.Data, T, V extends Operation.Variables> ApolloMutationCall<T> mutate(Mutation<D, T, V> mutation, D d);
    }

    @Override // com.apollographql.apollo.ApolloCall
    ApolloMutationCall<T> cacheHeaders(CacheHeaders cacheHeaders);

    @Override // com.apollographql.apollo.ApolloCall
    ApolloMutationCall<T> clone();

    ApolloMutationCall<T> refetchQueries(OperationName... operationNameArr);

    ApolloMutationCall<T> refetchQueries(Query... queryArr);

    ApolloMutationCall<T> requestHeaders(RequestHeaders requestHeaders);
}
