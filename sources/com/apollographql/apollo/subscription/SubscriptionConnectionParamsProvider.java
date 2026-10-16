package com.apollographql.apollo.subscription;

/* JADX INFO: loaded from: classes.dex */
public interface SubscriptionConnectionParamsProvider {
    SubscriptionConnectionParams provide();

    public static class Const implements SubscriptionConnectionParamsProvider {
        private final SubscriptionConnectionParams params;

        public Const(SubscriptionConnectionParams subscriptionConnectionParams) {
            this.params = subscriptionConnectionParams;
        }

        @Override // com.apollographql.apollo.subscription.SubscriptionConnectionParamsProvider
        public SubscriptionConnectionParams provide() {
            return this.params;
        }
    }
}
