package com.apollographql.apollo.internal.fetcher;

import com.apollographql.apollo.api.internal.ApolloLogger;
import com.apollographql.apollo.fetcher.ResponseFetcher;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptorChain;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkOnlyFetcher implements ResponseFetcher {
    @Override // com.apollographql.apollo.fetcher.ResponseFetcher
    public ApolloInterceptor provideInterceptor(ApolloLogger apolloLogger) {
        return new NetworkOnlyInterceptor();
    }

    private static final class NetworkOnlyInterceptor implements ApolloInterceptor {
        @Override // com.apollographql.apollo.interceptor.ApolloInterceptor
        public void dispose() {
        }

        private NetworkOnlyInterceptor() {
        }

        @Override // com.apollographql.apollo.interceptor.ApolloInterceptor
        public void interceptAsync(ApolloInterceptor.InterceptorRequest interceptorRequest, ApolloInterceptorChain apolloInterceptorChain, Executor executor, ApolloInterceptor.CallBack callBack) {
            apolloInterceptorChain.proceedAsync(interceptorRequest.toBuilder().fetchFromCache(false).build(), executor, callBack);
        }
    }
}
