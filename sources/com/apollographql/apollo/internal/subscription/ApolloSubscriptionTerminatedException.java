package com.apollographql.apollo.internal.subscription;

import com.apollographql.apollo.exception.ApolloException;

/* JADX INFO: loaded from: classes.dex */
public class ApolloSubscriptionTerminatedException extends ApolloException {
    public ApolloSubscriptionTerminatedException(String str) {
        super(str);
    }

    public ApolloSubscriptionTerminatedException(String str, Throwable th) {
        super(str, th);
    }
}
