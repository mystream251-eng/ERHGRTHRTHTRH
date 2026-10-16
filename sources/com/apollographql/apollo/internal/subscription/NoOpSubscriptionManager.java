package com.apollographql.apollo.internal.subscription;

import androidx.exifinterface.media.ExifInterface;
import com.apollographql.apollo.api.Subscription;
import com.apollographql.apollo.subscription.OnSubscriptionManagerStateChangeListener;
import com.apollographql.apollo.subscription.SubscriptionManagerState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NoOpSubscriptionManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\bH\u0016J\b\u0010\u000f\u001a\u00020\bH\u0016J2\u0010\u0010\u001a\u00020\b\"\u0004\b\u0000\u0010\u00112\u0014\u0010\u0012\u001a\u0010\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u0011\u0012\u0002\b\u00030\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0015H\u0016J\u001c\u0010\u0016\u001a\u00020\b2\u0012\u0010\u0012\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0013H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0017"}, d2 = {"Lcom/apollographql/apollo/internal/subscription/NoOpSubscriptionManager;", "Lcom/apollographql/apollo/internal/subscription/SubscriptionManager;", "()V", "errorMessage", "", "getErrorMessage", "()Ljava/lang/String;", "addOnStateChangeListener", "", "onStateChangeListener", "Lcom/apollographql/apollo/subscription/OnSubscriptionManagerStateChangeListener;", "getState", "Lcom/apollographql/apollo/subscription/SubscriptionManagerState;", "removeOnStateChangeListener", "start", "stop", "subscribe", ExifInterface.GPS_DIRECTION_TRUE, "subscription", "Lcom/apollographql/apollo/api/Subscription;", "callback", "Lcom/apollographql/apollo/internal/subscription/SubscriptionManager$Callback;", "unsubscribe", "apollo-runtime"}, k = 1, mv = {1, 1, 16})
public final class NoOpSubscriptionManager implements SubscriptionManager {
    private final String errorMessage = "No `SubscriptionTransport.Factory` found, please add one to your `ApolloClient` with `ApolloClient.Builder.subscriptionTransportFactory`";

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager
    public <T> void subscribe(Subscription<?, T, ?> subscription, SubscriptionManager.Callback<T> callback) {
        Intrinsics.checkParameterIsNotNull(subscription, "subscription");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        throw new IllegalStateException(this.errorMessage);
    }

    @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager
    public void unsubscribe(Subscription<?, ?, ?> subscription) {
        Intrinsics.checkParameterIsNotNull(subscription, "subscription");
        throw new IllegalStateException(this.errorMessage);
    }

    @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager
    public void start() {
        throw new IllegalStateException(this.errorMessage);
    }

    @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager
    public void stop() {
        throw new IllegalStateException(this.errorMessage);
    }

    @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager
    public SubscriptionManagerState getState() {
        return SubscriptionManagerState.DISCONNECTED;
    }

    @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager
    public void addOnStateChangeListener(OnSubscriptionManagerStateChangeListener onStateChangeListener) {
        Intrinsics.checkParameterIsNotNull(onStateChangeListener, "onStateChangeListener");
        throw new IllegalStateException(this.errorMessage);
    }

    @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager
    public void removeOnStateChangeListener(OnSubscriptionManagerStateChangeListener onStateChangeListener) {
        Intrinsics.checkParameterIsNotNull(onStateChangeListener, "onStateChangeListener");
        throw new IllegalStateException(this.errorMessage);
    }
}
