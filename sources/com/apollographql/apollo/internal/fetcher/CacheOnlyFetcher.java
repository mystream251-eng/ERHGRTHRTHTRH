package com.apollographql.apollo.internal.fetcher;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.internal.ApolloLogger;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.fetcher.ResponseFetcher;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptorChain;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class CacheOnlyFetcher implements ResponseFetcher {
    @Override // com.apollographql.apollo.fetcher.ResponseFetcher
    public ApolloInterceptor provideInterceptor(ApolloLogger apolloLogger) {
        return new CacheOnlyInterceptor();
    }

    private static final class CacheOnlyInterceptor implements ApolloInterceptor {
        @Override // com.apollographql.apollo.interceptor.ApolloInterceptor
        public void dispose() {
        }

        private CacheOnlyInterceptor() {
        }

        @Override // com.apollographql.apollo.interceptor.ApolloInterceptor
        public void interceptAsync(final ApolloInterceptor.InterceptorRequest interceptorRequest, ApolloInterceptorChain apolloInterceptorChain, Executor executor, final ApolloInterceptor.CallBack callBack) {
            apolloInterceptorChain.proceedAsync(interceptorRequest.toBuilder().fetchFromCache(true).build(), executor, new ApolloInterceptor.CallBack() { // from class: com.apollographql.apollo.internal.fetcher.CacheOnlyFetcher.CacheOnlyInterceptor.1
                @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
                public void onResponse(ApolloInterceptor.InterceptorResponse interceptorResponse) {
                    callBack.onResponse(interceptorResponse);
                }

                @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
                public void onFailure(ApolloException apolloException) {
                    callBack.onResponse(CacheOnlyInterceptor.this.cacheMissResponse(interceptorRequest.operation));
                    callBack.onCompleted();
                }

                @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
                public void onCompleted() {
                    callBack.onCompleted();
                }

                @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
                public void onFetch(ApolloInterceptor.FetchSourceType fetchSourceType) {
                    callBack.onFetch(fetchSourceType);
                }
            });
        }

        ApolloInterceptor.InterceptorResponse cacheMissResponse(Operation operation) {
            return new ApolloInterceptor.InterceptorResponse(null, Response.builder(operation).fromCache(true).build(), null);
        }
    }
}
