package com.apollographql.apollo.internal.fetcher;

import com.apollographql.apollo.api.internal.ApolloLogger;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.fetcher.ResponseFetcher;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptorChain;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class CacheAndNetworkFetcher implements ResponseFetcher {
    @Override // com.apollographql.apollo.fetcher.ResponseFetcher
    public ApolloInterceptor provideInterceptor(ApolloLogger apolloLogger) {
        return new CacheAndNetworkInterceptor();
    }

    private static final class CacheAndNetworkInterceptor implements ApolloInterceptor {
        private Optional<ApolloException> cacheException;
        private Optional<ApolloInterceptor.InterceptorResponse> cacheResponse;
        private boolean dispatchedCacheResult;
        private volatile boolean disposed;
        private Optional<ApolloException> networkException;
        private Optional<ApolloInterceptor.InterceptorResponse> networkResponse;
        private ApolloInterceptor.CallBack originalCallback;

        private CacheAndNetworkInterceptor() {
            this.cacheResponse = Optional.absent();
            this.networkResponse = Optional.absent();
            this.cacheException = Optional.absent();
            this.networkException = Optional.absent();
        }

        @Override // com.apollographql.apollo.interceptor.ApolloInterceptor
        public void interceptAsync(ApolloInterceptor.InterceptorRequest interceptorRequest, ApolloInterceptorChain apolloInterceptorChain, Executor executor, final ApolloInterceptor.CallBack callBack) {
            if (this.disposed) {
                return;
            }
            this.originalCallback = callBack;
            apolloInterceptorChain.proceedAsync(interceptorRequest.toBuilder().fetchFromCache(true).build(), executor, new ApolloInterceptor.CallBack() { // from class: com.apollographql.apollo.internal.fetcher.CacheAndNetworkFetcher.CacheAndNetworkInterceptor.1
                @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
                public void onCompleted() {
                }

                @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
                public void onResponse(ApolloInterceptor.InterceptorResponse interceptorResponse) {
                    CacheAndNetworkInterceptor.this.handleCacheResponse(interceptorResponse);
                }

                @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
                public void onFailure(ApolloException apolloException) {
                    CacheAndNetworkInterceptor.this.handleCacheError(apolloException);
                }

                @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
                public void onFetch(ApolloInterceptor.FetchSourceType fetchSourceType) {
                    callBack.onFetch(fetchSourceType);
                }
            });
            apolloInterceptorChain.proceedAsync(interceptorRequest.toBuilder().fetchFromCache(false).build(), executor, new ApolloInterceptor.CallBack() { // from class: com.apollographql.apollo.internal.fetcher.CacheAndNetworkFetcher.CacheAndNetworkInterceptor.2
                @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
                public void onCompleted() {
                }

                @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
                public void onResponse(ApolloInterceptor.InterceptorResponse interceptorResponse) {
                    CacheAndNetworkInterceptor.this.handleNetworkResponse(interceptorResponse);
                }

                @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
                public void onFailure(ApolloException apolloException) {
                    CacheAndNetworkInterceptor.this.handleNetworkError(apolloException);
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

        synchronized void handleNetworkResponse(ApolloInterceptor.InterceptorResponse interceptorResponse) {
            this.networkResponse = Optional.of(interceptorResponse);
            dispatch();
        }

        synchronized void handleNetworkError(ApolloException apolloException) {
            this.networkException = Optional.of(apolloException);
            dispatch();
        }

        synchronized void handleCacheResponse(ApolloInterceptor.InterceptorResponse interceptorResponse) {
            this.cacheResponse = Optional.of(interceptorResponse);
            dispatch();
        }

        synchronized void handleCacheError(ApolloException apolloException) {
            this.cacheException = Optional.of(apolloException);
            dispatch();
        }

        private synchronized void dispatch() {
            if (this.disposed) {
                return;
            }
            if (!this.dispatchedCacheResult) {
                if (this.cacheResponse.isPresent()) {
                    this.originalCallback.onResponse(this.cacheResponse.get());
                    this.dispatchedCacheResult = true;
                } else if (this.cacheException.isPresent()) {
                    this.dispatchedCacheResult = true;
                }
            }
            if (this.dispatchedCacheResult) {
                if (this.networkResponse.isPresent()) {
                    this.originalCallback.onResponse(this.networkResponse.get());
                    this.originalCallback.onCompleted();
                } else if (this.networkException.isPresent()) {
                    this.originalCallback.onFailure(this.networkException.get());
                }
            }
        }
    }
}
