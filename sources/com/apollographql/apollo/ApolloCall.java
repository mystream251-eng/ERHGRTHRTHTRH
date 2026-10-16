package com.apollographql.apollo;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.exception.ApolloCanceledException;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.exception.ApolloHttpException;
import com.apollographql.apollo.exception.ApolloNetworkException;
import com.apollographql.apollo.exception.ApolloParseException;
import com.apollographql.apollo.internal.util.Cancelable;

/* JADX INFO: loaded from: classes.dex */
public interface ApolloCall<T> extends Cancelable {

    public interface Builder<T> {
        ApolloCall<T> build();

        Builder<T> cacheHeaders(CacheHeaders cacheHeaders);
    }

    public enum StatusEvent {
        SCHEDULED,
        FETCH_CACHE,
        FETCH_NETWORK,
        COMPLETED
    }

    @Deprecated
    ApolloCall<T> cacheHeaders(CacheHeaders cacheHeaders);

    @Override // com.apollographql.apollo.internal.util.Cancelable
    void cancel();

    @Deprecated
    ApolloCall<T> clone();

    void enqueue(Callback<T> callback);

    Operation operation();

    Builder<T> toBuilder();

    public static abstract class Callback<T> {
        public abstract void onFailure(ApolloException apolloException);

        public abstract void onResponse(Response<T> response);

        public void onStatusEvent(StatusEvent statusEvent) {
        }

        public void onHttpError(ApolloHttpException apolloHttpException) {
            onFailure(apolloHttpException);
            okhttp3.Response responseRawResponse = apolloHttpException.rawResponse();
            if (responseRawResponse != null) {
                responseRawResponse.close();
            }
        }

        public void onNetworkError(ApolloNetworkException apolloNetworkException) {
            onFailure(apolloNetworkException);
        }

        public void onParseError(ApolloParseException apolloParseException) {
            onFailure(apolloParseException);
        }

        public void onCanceledError(ApolloCanceledException apolloCanceledException) {
            onFailure(apolloCanceledException);
        }
    }
}
