package com.apollographql.apollo.internal.interceptor;

import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptorChain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class RealApolloInterceptorChain implements ApolloInterceptorChain {
    private final int interceptorIndex;
    private final List<ApolloInterceptor> interceptors;

    public RealApolloInterceptorChain(List<ApolloInterceptor> list) {
        this(list, 0);
    }

    private RealApolloInterceptorChain(List<ApolloInterceptor> list, int i) {
        if (i > list.size()) {
            throw new IllegalArgumentException();
        }
        this.interceptors = new ArrayList((Collection) Utils.checkNotNull(list, "interceptors == null"));
        this.interceptorIndex = i;
    }

    @Override // com.apollographql.apollo.interceptor.ApolloInterceptorChain
    public void proceedAsync(ApolloInterceptor.InterceptorRequest interceptorRequest, Executor executor, ApolloInterceptor.CallBack callBack) {
        if (this.interceptorIndex >= this.interceptors.size()) {
            throw new IllegalStateException();
        }
        this.interceptors.get(this.interceptorIndex).interceptAsync(interceptorRequest, new RealApolloInterceptorChain(this.interceptors, this.interceptorIndex + 1), executor, callBack);
    }

    @Override // com.apollographql.apollo.interceptor.ApolloInterceptorChain
    public void dispose() {
        Iterator<ApolloInterceptor> it = this.interceptors.iterator();
        while (it.hasNext()) {
            it.next().dispose();
        }
    }
}
