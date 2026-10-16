package com.apollographql.apollo.internal.fetcher;

import com.apollographql.apollo.api.internal.ApolloLogger;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.fetcher.ResponseFetcher;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptorChain;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class CacheFirstFetcher implements ResponseFetcher {
    @Override // com.apollographql.apollo.fetcher.ResponseFetcher
    public ApolloInterceptor provideInterceptor(ApolloLogger apolloLogger) {
        return new CacheFirstInterceptor();
    }

    private static final class CacheFirstInterceptor implements ApolloInterceptor {
        volatile boolean disposed;

        private CacheFirstInterceptor() {
        }

        @Override // com.apollographql.apollo.interceptor.ApolloInterceptor
        public void interceptAsync(final ApolloInterceptor.InterceptorRequest interceptorRequest, final ApolloInterceptorChain apolloInterceptorChain, final Executor executor, final ApolloInterceptor.CallBack callBack) {
            apolloInterceptorChain.proceedAsync(interceptorRequest.toBuilder().fetchFromCache(true).build(), executor, new ApolloInterceptor.CallBack() { // from class: com.apollographql.apollo.internal.fetcher.CacheFirstFetcher.CacheFirstInterceptor.1
                @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
                public void onResponse(ApolloInterceptor.InterceptorResponse interceptorResponse) {
                    callBack.onResponse(interceptorResponse);
                }

                @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
                public void onFailure(ApolloException apolloException) {
                    if (CacheFirstInterceptor.this.disposed) {
                        return;
                    }
                    apolloInterceptorChain.proceedAsync(interceptorRequest.toBuilder().fetchFromCache(false).build(), executor, callBack);
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

        @Override // com.apollographql.apollo.interceptor.ApolloInterceptor
        public void dispose() {
            this.disposed = true;
        }
    }
}
