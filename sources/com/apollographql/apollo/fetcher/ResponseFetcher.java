package com.apollographql.apollo.fetcher;

import com.apollographql.apollo.api.internal.ApolloLogger;
import com.apollographql.apollo.interceptor.ApolloInterceptor;

/* JADX INFO: loaded from: classes.dex */
public interface ResponseFetcher {
    ApolloInterceptor provideInterceptor(ApolloLogger apolloLogger);
}
