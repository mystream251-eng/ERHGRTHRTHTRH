package com.apollographql.apollo.internal.subscription;

import com.apollographql.apollo.api.internal.Utils;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ApolloSubscriptionServerException extends ApolloSubscriptionException {
    public final Map<String, Object> errorPayload;

    public ApolloSubscriptionServerException(Map<String, Object> map) {
        super("Subscription failed");
        this.errorPayload = Collections.unmodifiableMap((Map) Utils.checkNotNull(map, "errorPayload == null"));
    }
}
