package com.apollographql.apollo.rx2;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloPrefetch;
import com.apollographql.apollo.ApolloQueryWatcher;
import com.apollographql.apollo.ApolloSubscriptionCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.cache.normalized.ApolloStoreOperation;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.internal.subscription.ApolloSubscriptionTerminatedException;
import com.apollographql.apollo.internal.util.Cancelable;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;

/* JADX INFO: loaded from: classes.dex */
public class Rx2Apollo {
    private Rx2Apollo() {
        throw new AssertionError("This class cannot be instantiated");
    }

    @CheckReturnValue
    public static <T> Observable<Response<T>> from(final ApolloQueryWatcher<T> apolloQueryWatcher) {
        Utils.checkNotNull(apolloQueryWatcher, "watcher == null");
        return Observable.create(new ObservableOnSubscribe<Response<T>>() { // from class: com.apollographql.apollo.rx2.Rx2Apollo.1
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(final ObservableEmitter<Response<T>> observableEmitter) throws Exception {
                Rx2Apollo.cancelOnObservableDisposed(observableEmitter, apolloQueryWatcher);
                apolloQueryWatcher.enqueueAndWatch(new ApolloCall.Callback<T>() { // from class: com.apollographql.apollo.rx2.Rx2Apollo.1.1
                    @Override // com.apollographql.apollo.ApolloCall.Callback
                    public void onResponse(Response<T> response) {
                        if (observableEmitter.isDisposed()) {
                            return;
                        }
                        observableEmitter.onNext(response);
                    }

                    @Override // com.apollographql.apollo.ApolloCall.Callback
                    public void onFailure(ApolloException apolloException) {
                        Exceptions.throwIfFatal(apolloException);
                        if (observableEmitter.isDisposed()) {
                            return;
                        }
                        observableEmitter.onError(apolloException);
                    }
                });
            }
        });
    }

    @CheckReturnValue
    public static <T> Observable<Response<T>> from(final ApolloCall<T> apolloCall) {
        Utils.checkNotNull(apolloCall, "call == null");
        return Observable.create(new ObservableOnSubscribe<Response<T>>() { // from class: com.apollographql.apollo.rx2.Rx2Apollo.2
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(final ObservableEmitter<Response<T>> observableEmitter) throws Exception {
                Rx2Apollo.cancelOnObservableDisposed(observableEmitter, apolloCall);
                apolloCall.enqueue(new ApolloCall.Callback<T>() { // from class: com.apollographql.apollo.rx2.Rx2Apollo.2.1
                    @Override // com.apollographql.apollo.ApolloCall.Callback
                    public void onResponse(Response<T> response) {
                        if (observableEmitter.isDisposed()) {
                            return;
                        }
                        observableEmitter.onNext(response);
                    }

                    @Override // com.apollographql.apollo.ApolloCall.Callback
                    public void onFailure(ApolloException apolloException) {
                        Exceptions.throwIfFatal(apolloException);
                        if (observableEmitter.isDisposed()) {
                            return;
                        }
                        observableEmitter.onError(apolloException);
                    }

                    @Override // com.apollographql.apollo.ApolloCall.Callback
                    public void onStatusEvent(ApolloCall.StatusEvent statusEvent) {
                        if (statusEvent != ApolloCall.StatusEvent.COMPLETED || observableEmitter.isDisposed()) {
                            return;
                        }
                        observableEmitter.onComplete();
                    }
                });
            }
        });
    }

    @CheckReturnValue
    public static Completable from(final ApolloPrefetch apolloPrefetch) {
        Utils.checkNotNull(apolloPrefetch, "prefetch == null");
        return Completable.create(new CompletableOnSubscribe() { // from class: com.apollographql.apollo.rx2.Rx2Apollo.3
            @Override // io.reactivex.CompletableOnSubscribe
            public void subscribe(final CompletableEmitter completableEmitter) {
                Rx2Apollo.cancelOnCompletableDisposed(completableEmitter, apolloPrefetch);
                apolloPrefetch.enqueue(new ApolloPrefetch.Callback() { // from class: com.apollographql.apollo.rx2.Rx2Apollo.3.1
                    @Override // com.apollographql.apollo.ApolloPrefetch.Callback
                    public void onSuccess() {
                        if (completableEmitter.isDisposed()) {
                            return;
                        }
                        completableEmitter.onComplete();
                    }

                    @Override // com.apollographql.apollo.ApolloPrefetch.Callback
                    public void onFailure(ApolloException apolloException) {
                        Exceptions.throwIfFatal(apolloException);
                        if (completableEmitter.isDisposed()) {
                            return;
                        }
                        completableEmitter.onError(apolloException);
                    }
                });
            }
        });
    }

    @CheckReturnValue
    public static <T> Flowable<Response<T>> from(ApolloSubscriptionCall<T> apolloSubscriptionCall) {
        return from(apolloSubscriptionCall, BackpressureStrategy.LATEST);
    }

    @CheckReturnValue
    public static <T> Flowable<Response<T>> from(final ApolloSubscriptionCall<T> apolloSubscriptionCall, BackpressureStrategy backpressureStrategy) {
        Utils.checkNotNull(apolloSubscriptionCall, "originalCall == null");
        Utils.checkNotNull(backpressureStrategy, "backpressureStrategy == null");
        return Flowable.create(new FlowableOnSubscribe<Response<T>>() { // from class: com.apollographql.apollo.rx2.Rx2Apollo.4
            @Override // io.reactivex.FlowableOnSubscribe
            public void subscribe(final FlowableEmitter<Response<T>> flowableEmitter) throws Exception {
                Rx2Apollo.cancelOnFlowableDisposed(flowableEmitter, apolloSubscriptionCall);
                apolloSubscriptionCall.execute(new ApolloSubscriptionCall.Callback<T>() { // from class: com.apollographql.apollo.rx2.Rx2Apollo.4.1
                    @Override // com.apollographql.apollo.ApolloSubscriptionCall.Callback
                    public void onConnected() {
                    }

                    @Override // com.apollographql.apollo.ApolloSubscriptionCall.Callback
                    public void onResponse(Response<T> response) {
                        if (flowableEmitter.isCancelled()) {
                            return;
                        }
                        flowableEmitter.onNext(response);
                    }

                    @Override // com.apollographql.apollo.ApolloSubscriptionCall.Callback
                    public void onFailure(ApolloException apolloException) {
                        Exceptions.throwIfFatal(apolloException);
                        if (flowableEmitter.isCancelled()) {
                            return;
                        }
                        flowableEmitter.onError(apolloException);
                    }

                    @Override // com.apollographql.apollo.ApolloSubscriptionCall.Callback
                    public void onCompleted() {
                        if (flowableEmitter.isCancelled()) {
                            return;
                        }
                        flowableEmitter.onComplete();
                    }

                    @Override // com.apollographql.apollo.ApolloSubscriptionCall.Callback
                    public void onTerminated() {
                        onFailure(new ApolloSubscriptionTerminatedException("Subscription server unexpectedly terminated connection"));
                    }
                });
            }
        }, backpressureStrategy);
    }

    @CheckReturnValue
    public static <T> Single<T> from(final ApolloStoreOperation<T> apolloStoreOperation) {
        Utils.checkNotNull(apolloStoreOperation, "operation == null");
        return Single.create(new SingleOnSubscribe<T>() { // from class: com.apollographql.apollo.rx2.Rx2Apollo.5
            @Override // io.reactivex.SingleOnSubscribe
            public void subscribe(final SingleEmitter<T> singleEmitter) {
                apolloStoreOperation.enqueue(new ApolloStoreOperation.Callback<T>() { // from class: com.apollographql.apollo.rx2.Rx2Apollo.5.1
                    @Override // com.apollographql.apollo.cache.normalized.ApolloStoreOperation.Callback
                    public void onSuccess(T t) {
                        singleEmitter.onSuccess(t);
                    }

                    @Override // com.apollographql.apollo.cache.normalized.ApolloStoreOperation.Callback
                    public void onFailure(Throwable th) {
                        singleEmitter.onError(th);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cancelOnCompletableDisposed(CompletableEmitter completableEmitter, Cancelable cancelable) {
        completableEmitter.setDisposable(getRx2Disposable(cancelable));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void cancelOnObservableDisposed(ObservableEmitter<T> observableEmitter, Cancelable cancelable) {
        observableEmitter.setDisposable(getRx2Disposable(cancelable));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void cancelOnFlowableDisposed(FlowableEmitter<T> flowableEmitter, Cancelable cancelable) {
        flowableEmitter.setDisposable(getRx2Disposable(cancelable));
    }

    private static Disposable getRx2Disposable(final Cancelable cancelable) {
        return new Disposable() { // from class: com.apollographql.apollo.rx2.Rx2Apollo.6
            @Override // io.reactivex.disposables.Disposable
            public void dispose() {
                cancelable.cancel();
            }

            @Override // io.reactivex.disposables.Disposable
            public boolean isDisposed() {
                return cancelable.isCanceled();
            }
        };
    }
}
