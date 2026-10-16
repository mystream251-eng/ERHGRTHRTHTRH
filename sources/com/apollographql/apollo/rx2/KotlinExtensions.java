package com.apollographql.apollo.rx2;

import androidx.exifinterface.media.ExifInterface;
import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.ApolloMutationCall;
import com.apollographql.apollo.ApolloPrefetch;
import com.apollographql.apollo.ApolloQueryCall;
import com.apollographql.apollo.ApolloQueryWatcher;
import com.apollographql.apollo.ApolloSubscriptionCall;
import com.apollographql.apollo.api.Mutation;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.Subscription;
import com.apollographql.apollo.cache.normalized.ApolloStoreOperation;
import com.google.android.gms.actions.SearchIntents;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.CheckReturnValue;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RxJavaExtensions.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000x\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a%\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0005*\u00020\u0006H\u0087\b\u001a%\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0007H\u0087\b\u001a/\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\b\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0087\b\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00030\f\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\rH\u0087\b\u001a\u0081\u0001\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\f\"\b\b\u0000\u0010\u000f*\u00020\u0010\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0011*\u00020\u0012*\u00020\u00132\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00110\u00152\u0006\u0010\u0016\u001a\u0002H\u000f2%\b\u0002\u0010\u0017\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00190\u0018¢\u0006\u0002\b\u001aH\u0087\b¢\u0006\u0002\u0010\u001b\u001at\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\f\"\b\b\u0000\u0010\u000f*\u00020\u0010\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0011*\u00020\u0012*\u00020\u00132\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00110\u00152%\b\u0002\u0010\u0017\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00190\u0018¢\u0006\u0002\b\u001aH\u0087\b\u001aA\u0010\u001c\u001a\u00020\u0005\"\b\b\u0000\u0010\u000f*\u00020\u0010\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0011*\u00020\u0012*\u00020\u00132\u0018\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00110\u001eH\u0087\b\u001at\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\b\b\u0000\u0010\u000f*\u00020\u0010\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0011*\u00020\u0012*\u00020\u00132\u0018\u0010 \u001a\u0014\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00110!2%\b\u0002\u0010\u0017\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\"\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\"0\u0018¢\u0006\u0002\b\u001aH\u0087\b\u001aW\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\b\"\b\b\u0000\u0010\u000f*\u00020\u0010\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0011*\u00020\u0012*\u00020\u00132\u0018\u0010$\u001a\u0014\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00110%2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0087\b¨\u0006&"}, d2 = {"rx", "Lio/reactivex/Observable;", "Lcom/apollographql/apollo/api/Response;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/apollographql/apollo/ApolloCall;", "Lio/reactivex/Completable;", "Lcom/apollographql/apollo/ApolloPrefetch;", "Lcom/apollographql/apollo/ApolloQueryWatcher;", "Lio/reactivex/Flowable;", "Lcom/apollographql/apollo/ApolloSubscriptionCall;", "backpressureStrategy", "Lio/reactivex/BackpressureStrategy;", "Lio/reactivex/Single;", "Lcom/apollographql/apollo/cache/normalized/ApolloStoreOperation;", "rxMutate", "D", "Lcom/apollographql/apollo/api/Operation$Data;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lcom/apollographql/apollo/api/Operation$Variables;", "Lcom/apollographql/apollo/ApolloClient;", "mutation", "Lcom/apollographql/apollo/api/Mutation;", "withOptimisticUpdates", "configure", "Lkotlin/Function1;", "Lcom/apollographql/apollo/ApolloMutationCall;", "Lkotlin/ExtensionFunctionType;", "(Lcom/apollographql/apollo/ApolloClient;Lcom/apollographql/apollo/api/Mutation;Lcom/apollographql/apollo/api/Operation$Data;Lkotlin/jvm/functions/Function1;)Lio/reactivex/Single;", "rxPrefetch", "operation", "Lcom/apollographql/apollo/api/Operation;", "rxQuery", SearchIntents.EXTRA_QUERY, "Lcom/apollographql/apollo/api/Query;", "Lcom/apollographql/apollo/ApolloQueryCall;", "rxSubscribe", "subscription", "Lcom/apollographql/apollo/api/Subscription;", "apollo-rx2-support"}, k = 2, mv = {1, 1, 16})
public final class KotlinExtensions {
    @CheckReturnValue
    public static final /* synthetic */ Completable rx(ApolloPrefetch rx) {
        Intrinsics.checkParameterIsNotNull(rx, "$this$rx");
        Completable completableFrom = Rx2Apollo.from(rx);
        Intrinsics.checkExpressionValueIsNotNull(completableFrom, "Rx2Apollo.from(this)");
        return completableFrom;
    }

    @CheckReturnValue
    public static final /* synthetic */ <T> Single<T> rx(ApolloStoreOperation<T> rx) {
        Intrinsics.checkParameterIsNotNull(rx, "$this$rx");
        Single<T> singleFrom = Rx2Apollo.from(rx);
        Intrinsics.checkExpressionValueIsNotNull(singleFrom, "Rx2Apollo.from(this)");
        return singleFrom;
    }

    @CheckReturnValue
    public static final /* synthetic */ <T> Observable<Response<T>> rx(ApolloQueryWatcher<T> rx) {
        Intrinsics.checkParameterIsNotNull(rx, "$this$rx");
        Observable<Response<T>> observableFrom = Rx2Apollo.from(rx);
        Intrinsics.checkExpressionValueIsNotNull(observableFrom, "Rx2Apollo.from(this)");
        return observableFrom;
    }

    @CheckReturnValue
    public static final /* synthetic */ <T> Observable<Response<T>> rx(ApolloCall<T> rx) {
        Intrinsics.checkParameterIsNotNull(rx, "$this$rx");
        Observable<Response<T>> observableFrom = Rx2Apollo.from(rx);
        Intrinsics.checkExpressionValueIsNotNull(observableFrom, "Rx2Apollo.from(this)");
        return observableFrom;
    }

    public static /* synthetic */ Flowable rx$default(ApolloSubscriptionCall rx, BackpressureStrategy backpressureStrategy, int i, Object obj) {
        if ((i & 1) != 0) {
            backpressureStrategy = BackpressureStrategy.LATEST;
        }
        Intrinsics.checkParameterIsNotNull(rx, "$this$rx");
        Intrinsics.checkParameterIsNotNull(backpressureStrategy, "backpressureStrategy");
        Flowable flowableFrom = Rx2Apollo.from(rx, backpressureStrategy);
        Intrinsics.checkExpressionValueIsNotNull(flowableFrom, "Rx2Apollo.from(this, backpressureStrategy)");
        return flowableFrom;
    }

    @CheckReturnValue
    public static final /* synthetic */ <T> Flowable<Response<T>> rx(ApolloSubscriptionCall<T> rx, BackpressureStrategy backpressureStrategy) {
        Intrinsics.checkParameterIsNotNull(rx, "$this$rx");
        Intrinsics.checkParameterIsNotNull(backpressureStrategy, "backpressureStrategy");
        Flowable<Response<T>> flowableFrom = Rx2Apollo.from(rx, backpressureStrategy);
        Intrinsics.checkExpressionValueIsNotNull(flowableFrom, "Rx2Apollo.from(this, backpressureStrategy)");
        return flowableFrom;
    }

    public static /* synthetic */ Observable rxQuery$default(ApolloClient rxQuery, Query query, Function1 configure, int i, Object obj) {
        if ((i & 2) != 0) {
            configure = new Function1<ApolloQueryCall<T>, ApolloQueryCall<T>>() { // from class: com.apollographql.apollo.rx2.KotlinExtensions.rxQuery.1
                @Override // kotlin.jvm.functions.Function1
                public final ApolloQueryCall<T> invoke(ApolloQueryCall<T> receiver) {
                    Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    return receiver;
                }
            };
        }
        Intrinsics.checkParameterIsNotNull(rxQuery, "$this$rxQuery");
        Intrinsics.checkParameterIsNotNull(query, "query");
        Intrinsics.checkParameterIsNotNull(configure, "configure");
        ApolloQueryCall apolloQueryCallQuery = rxQuery.query(query);
        Intrinsics.checkExpressionValueIsNotNull(apolloQueryCallQuery, "query(query)");
        Observable observableFrom = Rx2Apollo.from((ApolloCall) configure.invoke(apolloQueryCallQuery));
        Intrinsics.checkExpressionValueIsNotNull(observableFrom, "Rx2Apollo.from(this)");
        return observableFrom;
    }

    @CheckReturnValue
    public static final /* synthetic */ <D extends Operation.Data, T, V extends Operation.Variables> Observable<Response<T>> rxQuery(ApolloClient rxQuery, Query<D, T, V> query, Function1<? super ApolloQueryCall<T>, ? extends ApolloQueryCall<T>> configure) {
        Intrinsics.checkParameterIsNotNull(rxQuery, "$this$rxQuery");
        Intrinsics.checkParameterIsNotNull(query, "query");
        Intrinsics.checkParameterIsNotNull(configure, "configure");
        ApolloQueryCall<T> apolloQueryCallQuery = rxQuery.query(query);
        Intrinsics.checkExpressionValueIsNotNull(apolloQueryCallQuery, "query(query)");
        Observable<Response<T>> observableFrom = Rx2Apollo.from(configure.invoke(apolloQueryCallQuery));
        Intrinsics.checkExpressionValueIsNotNull(observableFrom, "Rx2Apollo.from(this)");
        return observableFrom;
    }

    public static /* synthetic */ Single rxMutate$default(ApolloClient rxMutate, Mutation mutation, Function1 configure, int i, Object obj) {
        if ((i & 2) != 0) {
            configure = new Function1<ApolloMutationCall<T>, ApolloMutationCall<T>>() { // from class: com.apollographql.apollo.rx2.KotlinExtensions.rxMutate.1
                @Override // kotlin.jvm.functions.Function1
                public final ApolloMutationCall<T> invoke(ApolloMutationCall<T> receiver) {
                    Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    return receiver;
                }
            };
        }
        Intrinsics.checkParameterIsNotNull(rxMutate, "$this$rxMutate");
        Intrinsics.checkParameterIsNotNull(mutation, "mutation");
        Intrinsics.checkParameterIsNotNull(configure, "configure");
        ApolloMutationCall apolloMutationCallMutate = rxMutate.mutate(mutation);
        Intrinsics.checkExpressionValueIsNotNull(apolloMutationCallMutate, "mutate(mutation)");
        Observable observableFrom = Rx2Apollo.from((ApolloCall) configure.invoke(apolloMutationCallMutate));
        Intrinsics.checkExpressionValueIsNotNull(observableFrom, "Rx2Apollo.from(this)");
        Single singleSingleOrError = observableFrom.singleOrError();
        Intrinsics.checkExpressionValueIsNotNull(singleSingleOrError, "mutate(mutation).configure().rx().singleOrError()");
        return singleSingleOrError;
    }

    @CheckReturnValue
    public static final /* synthetic */ <D extends Operation.Data, T, V extends Operation.Variables> Single<Response<T>> rxMutate(ApolloClient rxMutate, Mutation<D, T, V> mutation, Function1<? super ApolloMutationCall<T>, ? extends ApolloMutationCall<T>> configure) {
        Intrinsics.checkParameterIsNotNull(rxMutate, "$this$rxMutate");
        Intrinsics.checkParameterIsNotNull(mutation, "mutation");
        Intrinsics.checkParameterIsNotNull(configure, "configure");
        ApolloMutationCall<T> apolloMutationCallMutate = rxMutate.mutate(mutation);
        Intrinsics.checkExpressionValueIsNotNull(apolloMutationCallMutate, "mutate(mutation)");
        Observable observableFrom = Rx2Apollo.from(configure.invoke(apolloMutationCallMutate));
        Intrinsics.checkExpressionValueIsNotNull(observableFrom, "Rx2Apollo.from(this)");
        Single<Response<T>> singleSingleOrError = observableFrom.singleOrError();
        Intrinsics.checkExpressionValueIsNotNull(singleSingleOrError, "mutate(mutation).configure().rx().singleOrError()");
        return singleSingleOrError;
    }

    public static /* synthetic */ Single rxMutate$default(ApolloClient rxMutate, Mutation mutation, Operation.Data withOptimisticUpdates, Function1 configure, int i, Object obj) {
        if ((i & 4) != 0) {
            configure = new Function1<ApolloMutationCall<T>, ApolloMutationCall<T>>() { // from class: com.apollographql.apollo.rx2.KotlinExtensions.rxMutate.2
                @Override // kotlin.jvm.functions.Function1
                public final ApolloMutationCall<T> invoke(ApolloMutationCall<T> receiver) {
                    Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    return receiver;
                }
            };
        }
        Intrinsics.checkParameterIsNotNull(rxMutate, "$this$rxMutate");
        Intrinsics.checkParameterIsNotNull(mutation, "mutation");
        Intrinsics.checkParameterIsNotNull(withOptimisticUpdates, "withOptimisticUpdates");
        Intrinsics.checkParameterIsNotNull(configure, "configure");
        ApolloMutationCall apolloMutationCallMutate = rxMutate.mutate(mutation, withOptimisticUpdates);
        Intrinsics.checkExpressionValueIsNotNull(apolloMutationCallMutate, "mutate(mutation, withOptimisticUpdates)");
        Observable observableFrom = Rx2Apollo.from((ApolloCall) configure.invoke(apolloMutationCallMutate));
        Intrinsics.checkExpressionValueIsNotNull(observableFrom, "Rx2Apollo.from(this)");
        Single singleSingleOrError = observableFrom.singleOrError();
        Intrinsics.checkExpressionValueIsNotNull(singleSingleOrError, "mutate(mutation, withOpt…re().rx().singleOrError()");
        return singleSingleOrError;
    }

    @CheckReturnValue
    public static final /* synthetic */ <D extends Operation.Data, T, V extends Operation.Variables> Single<Response<T>> rxMutate(ApolloClient rxMutate, Mutation<D, T, V> mutation, D withOptimisticUpdates, Function1<? super ApolloMutationCall<T>, ? extends ApolloMutationCall<T>> configure) {
        Intrinsics.checkParameterIsNotNull(rxMutate, "$this$rxMutate");
        Intrinsics.checkParameterIsNotNull(mutation, "mutation");
        Intrinsics.checkParameterIsNotNull(withOptimisticUpdates, "withOptimisticUpdates");
        Intrinsics.checkParameterIsNotNull(configure, "configure");
        ApolloMutationCall<T> apolloMutationCallMutate = rxMutate.mutate(mutation, withOptimisticUpdates);
        Intrinsics.checkExpressionValueIsNotNull(apolloMutationCallMutate, "mutate(mutation, withOptimisticUpdates)");
        Observable observableFrom = Rx2Apollo.from(configure.invoke(apolloMutationCallMutate));
        Intrinsics.checkExpressionValueIsNotNull(observableFrom, "Rx2Apollo.from(this)");
        Single<Response<T>> singleSingleOrError = observableFrom.singleOrError();
        Intrinsics.checkExpressionValueIsNotNull(singleSingleOrError, "mutate(mutation, withOpt…re().rx().singleOrError()");
        return singleSingleOrError;
    }

    @CheckReturnValue
    public static final /* synthetic */ <D extends Operation.Data, T, V extends Operation.Variables> Completable rxPrefetch(ApolloClient rxPrefetch, Operation<D, T, V> operation) {
        Intrinsics.checkParameterIsNotNull(rxPrefetch, "$this$rxPrefetch");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        ApolloPrefetch apolloPrefetchPrefetch = rxPrefetch.prefetch(operation);
        Intrinsics.checkExpressionValueIsNotNull(apolloPrefetchPrefetch, "prefetch(operation)");
        Completable completableFrom = Rx2Apollo.from(apolloPrefetchPrefetch);
        Intrinsics.checkExpressionValueIsNotNull(completableFrom, "Rx2Apollo.from(this)");
        return completableFrom;
    }

    public static /* synthetic */ Flowable rxSubscribe$default(ApolloClient rxSubscribe, Subscription subscription, BackpressureStrategy backpressureStrategy, int i, Object obj) {
        if ((i & 2) != 0) {
            backpressureStrategy = BackpressureStrategy.LATEST;
        }
        Intrinsics.checkParameterIsNotNull(rxSubscribe, "$this$rxSubscribe");
        Intrinsics.checkParameterIsNotNull(subscription, "subscription");
        Intrinsics.checkParameterIsNotNull(backpressureStrategy, "backpressureStrategy");
        ApolloSubscriptionCall apolloSubscriptionCallSubscribe = rxSubscribe.subscribe(subscription);
        Intrinsics.checkExpressionValueIsNotNull(apolloSubscriptionCallSubscribe, "subscribe(subscription)");
        Flowable flowableFrom = Rx2Apollo.from(apolloSubscriptionCallSubscribe, backpressureStrategy);
        Intrinsics.checkExpressionValueIsNotNull(flowableFrom, "Rx2Apollo.from(this, backpressureStrategy)");
        return flowableFrom;
    }

    @CheckReturnValue
    public static final /* synthetic */ <D extends Operation.Data, T, V extends Operation.Variables> Flowable<Response<T>> rxSubscribe(ApolloClient rxSubscribe, Subscription<D, T, V> subscription, BackpressureStrategy backpressureStrategy) {
        Intrinsics.checkParameterIsNotNull(rxSubscribe, "$this$rxSubscribe");
        Intrinsics.checkParameterIsNotNull(subscription, "subscription");
        Intrinsics.checkParameterIsNotNull(backpressureStrategy, "backpressureStrategy");
        ApolloSubscriptionCall<T> apolloSubscriptionCallSubscribe = rxSubscribe.subscribe(subscription);
        Intrinsics.checkExpressionValueIsNotNull(apolloSubscriptionCallSubscribe, "subscribe(subscription)");
        Flowable<Response<T>> flowableFrom = Rx2Apollo.from(apolloSubscriptionCallSubscribe, backpressureStrategy);
        Intrinsics.checkExpressionValueIsNotNull(flowableFrom, "Rx2Apollo.from(this, backpressureStrategy)");
        return flowableFrom;
    }
}
