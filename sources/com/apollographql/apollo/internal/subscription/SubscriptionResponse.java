package com.apollographql.apollo.internal.subscription;

import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.Subscription;
import com.apollographql.apollo.cache.normalized.Record;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public final class SubscriptionResponse<T> {
    public final Collection<Record> cacheRecords;
    public final Response<T> response;
    public final Subscription<?, T, ?> subscription;

    public SubscriptionResponse(Subscription<?, T, ?> subscription, Response<T> response, Collection<Record> collection) {
        this.subscription = subscription;
        this.response = response;
        this.cacheRecords = collection;
    }
}
