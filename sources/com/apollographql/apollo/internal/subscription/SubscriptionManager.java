package com.apollographql.apollo.internal.subscription;

import com.apollographql.apollo.api.Subscription;
import com.apollographql.apollo.subscription.OnSubscriptionManagerStateChangeListener;
import com.apollographql.apollo.subscription.SubscriptionManagerState;

/* JADX INFO: loaded from: classes.dex */
public interface SubscriptionManager {

    public interface Callback<T> {
        void onCompleted();

        void onConnected();

        void onError(ApolloSubscriptionException apolloSubscriptionException);

        void onNetworkError(Throwable th);

        void onResponse(SubscriptionResponse<T> subscriptionResponse);

        void onTerminated();
    }

    void addOnStateChangeListener(OnSubscriptionManagerStateChangeListener onSubscriptionManagerStateChangeListener);

    SubscriptionManagerState getState();

    void removeOnStateChangeListener(OnSubscriptionManagerStateChangeListener onSubscriptionManagerStateChangeListener);

    void start();

    void stop();

    <T> void subscribe(Subscription<?, T, ?> subscription, Callback<T> callback);

    void unsubscribe(Subscription<?, ?, ?> subscription);
}
