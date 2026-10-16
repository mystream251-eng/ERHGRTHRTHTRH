package com.apollographql.apollo.interceptor;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public interface ApolloInterceptorChain {
    void dispose();

    void proceedAsync(ApolloInterceptor.InterceptorRequest interceptorRequest, Executor executor, ApolloInterceptor.CallBack callBack);
}
