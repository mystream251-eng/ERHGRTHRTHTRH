package com.apollographql.apollo.interceptor;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.internal.ApolloLogger;
import kotlin.Metadata;

/* JADX INFO: compiled from: ApolloInterceptorFactory.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0007H&¨\u0006\b"}, d2 = {"Lcom/apollographql/apollo/interceptor/ApolloInterceptorFactory;", "", "newInterceptor", "Lcom/apollographql/apollo/interceptor/ApolloInterceptor;", "logger", "Lcom/apollographql/apollo/api/internal/ApolloLogger;", "operation", "Lcom/apollographql/apollo/api/Operation;", "apollo-runtime"}, k = 1, mv = {1, 1, 16})
public interface ApolloInterceptorFactory {
    ApolloInterceptor newInterceptor(ApolloLogger logger, Operation<?, ?, ?> operation);
}
