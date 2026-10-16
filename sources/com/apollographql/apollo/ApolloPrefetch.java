package com.apollographql.apollo;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.exception.ApolloCanceledException;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.exception.ApolloHttpException;
import com.apollographql.apollo.exception.ApolloNetworkException;
import com.apollographql.apollo.internal.util.Cancelable;
import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public interface ApolloPrefetch extends Cancelable {

    public interface Factory {
        <D extends Operation.Data, T, V extends Operation.Variables> ApolloPrefetch prefetch(Operation<D, T, V> operation);
    }

    @Override // com.apollographql.apollo.internal.util.Cancelable
    void cancel();

    ApolloPrefetch clone();

    void enqueue(Callback callback);

    Operation operation();

    public static abstract class Callback {
        public abstract void onFailure(ApolloException apolloException);

        public abstract void onSuccess();

        public void onHttpError(ApolloHttpException apolloHttpException) {
            onFailure(apolloHttpException);
            Response responseRawResponse = apolloHttpException.rawResponse();
            if (responseRawResponse != null) {
                responseRawResponse.close();
            }
        }

        public void onNetworkError(ApolloNetworkException apolloNetworkException) {
            onFailure(apolloNetworkException);
        }

        public void onCanceledError(ApolloCanceledException apolloCanceledException) {
            onFailure(apolloCanceledException);
        }
    }
}
