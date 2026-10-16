package io.reactivex.rxkotlin;

import androidx.exifinterface.media.ExifInterface;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.BackpressureKind;
import io.reactivex.annotations.BackpressureSupport;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: subscribers.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\n*\u00020\u0007*\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00020\u0004H\u0002\u001a\u0012\u0010\u000b\u001a\u00020\f*\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0002\u001a\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\t*\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00020\u0004H\u0002\u001aX\u0010\u000e\u001a\u00020\u0002\"\b\b\u0000\u0010\n*\u00020\u0007*\b\u0012\u0004\u0012\u0002H\n0\u000f2\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00020\u00042\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00020\u0004H\u0007\u001aX\u0010\u000e\u001a\u00020\u0002\"\b\b\u0000\u0010\n*\u00020\u0007*\b\u0012\u0004\u0012\u0002H\n0\u00132\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00020\u00042\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00020\u0004H\u0007\u001a2\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00020\u00042\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0007\u001aX\u0010\u0014\u001a\u00020\u0015\"\b\b\u0000\u0010\n*\u00020\u0007*\b\u0012\u0004\u0012\u0002H\n0\u000f2\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00020\u00042\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00020\u0004H\u0007\u001aX\u0010\u0014\u001a\u00020\u0015\"\b\b\u0000\u0010\n*\u00020\u0007*\b\u0012\u0004\u0012\u0002H\n0\u00172\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00020\u00042\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00020\u0004H\u0007\u001aX\u0010\u0014\u001a\u00020\u0015\"\b\b\u0000\u0010\n*\u00020\u0007*\b\u0012\u0004\u0012\u0002H\n0\u00132\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00020\u00042\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00020\u0004H\u0007\u001aH\u0010\u0014\u001a\u00020\u0015\"\b\b\u0000\u0010\n*\u00020\u0007*\b\u0012\u0004\u0012\u0002H\n0\u00192\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00020\u00042\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00020\u0004H\u0007\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"onCompleteStub", "Lkotlin/Function0;", "", "onErrorStub", "Lkotlin/Function1;", "", "onNextStub", "", "asConsumer", "Lio/reactivex/functions/Consumer;", ExifInterface.GPS_DIRECTION_TRUE, "asOnCompleteAction", "Lio/reactivex/functions/Action;", "asOnErrorConsumer", "blockingSubscribeBy", "Lio/reactivex/Flowable;", "onError", "onComplete", "onNext", "Lio/reactivex/Observable;", "subscribeBy", "Lio/reactivex/disposables/Disposable;", "Lio/reactivex/Completable;", "Lio/reactivex/Maybe;", "onSuccess", "Lio/reactivex/Single;", "rxkotlin"}, k = 2, mv = {1, 1, 11})
public final class SubscribersKt {
    private static final Function1<Object, Unit> onNextStub = new Function1<Object, Unit>() { // from class: io.reactivex.rxkotlin.SubscribersKt$onNextStub$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
            invoke2(obj);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Object it) {
            Intrinsics.checkParameterIsNotNull(it, "it");
        }
    };
    private static final Function1<Throwable, Unit> onErrorStub = new Function1<Throwable, Unit>() { // from class: io.reactivex.rxkotlin.SubscribersKt$onErrorStub$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Throwable it) {
            Intrinsics.checkParameterIsNotNull(it, "it");
        }
    };
    private static final Function0<Unit> onCompleteStub = new Function0<Unit>() { // from class: io.reactivex.rxkotlin.SubscribersKt$onCompleteStub$1
        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
        }
    };

    private static final <T> Consumer<T> asConsumer(Function1<? super T, Unit> function1) {
        if (function1 == onNextStub) {
            Consumer<T> consumerEmptyConsumer = Functions.emptyConsumer();
            Intrinsics.checkExpressionValueIsNotNull(consumerEmptyConsumer, "Functions.emptyConsumer()");
            return consumerEmptyConsumer;
        }
        if (function1 != null) {
            function1 = (Function1<? super T, Unit>) new SubscribersKt$sam$io_reactivex_functions_Consumer$0(function1);
        }
        return (Consumer) function1;
    }

    private static final Consumer<Throwable> asOnErrorConsumer(Function1<? super Throwable, Unit> function1) {
        Object subscribersKt$sam$io_reactivex_functions_Consumer$0 = function1;
        if (function1 == onErrorStub) {
            Consumer<Throwable> consumer = Functions.ON_ERROR_MISSING;
            Intrinsics.checkExpressionValueIsNotNull(consumer, "Functions.ON_ERROR_MISSING");
            return consumer;
        }
        if (function1 != null) {
            subscribersKt$sam$io_reactivex_functions_Consumer$0 = new SubscribersKt$sam$io_reactivex_functions_Consumer$0(function1);
        }
        return (Consumer) subscribersKt$sam$io_reactivex_functions_Consumer$0;
    }

    private static final Action asOnCompleteAction(Function0<Unit> function0) {
        Object subscribersKt$sam$io_reactivex_functions_Action$0 = function0;
        if (function0 == onCompleteStub) {
            Action action = Functions.EMPTY_ACTION;
            Intrinsics.checkExpressionValueIsNotNull(action, "Functions.EMPTY_ACTION");
            return action;
        }
        if (function0 != null) {
            subscribersKt$sam$io_reactivex_functions_Action$0 = new SubscribersKt$sam$io_reactivex_functions_Action$0(function0);
        }
        return (Action) subscribersKt$sam$io_reactivex_functions_Action$0;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static /* bridge */ /* synthetic */ Disposable subscribeBy$default(Observable observable, Function1 function1, Function0 function0, Function1 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = onErrorStub;
        }
        if ((i & 2) != 0) {
            function0 = onCompleteStub;
        }
        if ((i & 4) != 0) {
            function2 = onNextStub;
        }
        return subscribeBy(observable, (Function1<? super Throwable, Unit>) function1, (Function0<Unit>) function0, function2);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T> Disposable subscribeBy(Observable<T> receiver, Function1<? super Throwable, Unit> onError, Function0<Unit> onComplete, Function1<? super T, Unit> onNext) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        Intrinsics.checkParameterIsNotNull(onComplete, "onComplete");
        Intrinsics.checkParameterIsNotNull(onNext, "onNext");
        Disposable disposableSubscribe = receiver.subscribe(asConsumer(onNext), asOnErrorConsumer(onError), asOnCompleteAction(onComplete));
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "subscribe(onNext.asConsu…ete.asOnCompleteAction())");
        return disposableSubscribe;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    public static /* bridge */ /* synthetic */ Disposable subscribeBy$default(Flowable flowable, Function1 function1, Function0 function0, Function1 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = onErrorStub;
        }
        if ((i & 2) != 0) {
            function0 = onCompleteStub;
        }
        if ((i & 4) != 0) {
            function2 = onNextStub;
        }
        return subscribeBy(flowable, (Function1<? super Throwable, Unit>) function1, (Function0<Unit>) function0, function2);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    public static final <T> Disposable subscribeBy(Flowable<T> receiver, Function1<? super Throwable, Unit> onError, Function0<Unit> onComplete, Function1<? super T, Unit> onNext) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        Intrinsics.checkParameterIsNotNull(onComplete, "onComplete");
        Intrinsics.checkParameterIsNotNull(onNext, "onNext");
        Disposable disposableSubscribe = receiver.subscribe(asConsumer(onNext), asOnErrorConsumer(onError), asOnCompleteAction(onComplete));
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "subscribe(onNext.asConsu…ete.asOnCompleteAction())");
        return disposableSubscribe;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static /* bridge */ /* synthetic */ Disposable subscribeBy$default(Single single, Function1 function1, Function1 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = onErrorStub;
        }
        if ((i & 2) != 0) {
            function2 = onNextStub;
        }
        return subscribeBy(single, (Function1<? super Throwable, Unit>) function1, function2);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T> Disposable subscribeBy(Single<T> receiver, Function1<? super Throwable, Unit> onError, Function1<? super T, Unit> onSuccess) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        Intrinsics.checkParameterIsNotNull(onSuccess, "onSuccess");
        Disposable disposableSubscribe = receiver.subscribe(asConsumer(onSuccess), asOnErrorConsumer(onError));
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "subscribe(onSuccess.asCo…rror.asOnErrorConsumer())");
        return disposableSubscribe;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static /* bridge */ /* synthetic */ Disposable subscribeBy$default(Maybe maybe, Function1 function1, Function0 function0, Function1 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = onErrorStub;
        }
        if ((i & 2) != 0) {
            function0 = onCompleteStub;
        }
        if ((i & 4) != 0) {
            function2 = onNextStub;
        }
        return subscribeBy(maybe, (Function1<? super Throwable, Unit>) function1, (Function0<Unit>) function0, function2);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T> Disposable subscribeBy(Maybe<T> receiver, Function1<? super Throwable, Unit> onError, Function0<Unit> onComplete, Function1<? super T, Unit> onSuccess) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        Intrinsics.checkParameterIsNotNull(onComplete, "onComplete");
        Intrinsics.checkParameterIsNotNull(onSuccess, "onSuccess");
        Disposable disposableSubscribe = receiver.subscribe(asConsumer(onSuccess), asOnErrorConsumer(onError), asOnCompleteAction(onComplete));
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "subscribe(onSuccess.asCo…ete.asOnCompleteAction())");
        return disposableSubscribe;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static /* bridge */ /* synthetic */ Disposable subscribeBy$default(Completable completable, Function1 function1, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = onErrorStub;
        }
        if ((i & 2) != 0) {
            function0 = onCompleteStub;
        }
        return subscribeBy(completable, (Function1<? super Throwable, Unit>) function1, (Function0<Unit>) function0);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final Disposable subscribeBy(Completable receiver, Function1<? super Throwable, Unit> onError, Function0<Unit> onComplete) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        Intrinsics.checkParameterIsNotNull(onComplete, "onComplete");
        if (onError == onErrorStub && onComplete == onCompleteStub) {
            Disposable disposableSubscribe = receiver.subscribe();
            Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "subscribe()");
            return disposableSubscribe;
        }
        if (onError == onErrorStub) {
            Disposable disposableSubscribe2 = receiver.subscribe(new SubscribersKt$sam$io_reactivex_functions_Action$0(onComplete));
            Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe2, "subscribe(onComplete)");
            return disposableSubscribe2;
        }
        Disposable disposableSubscribe3 = receiver.subscribe(asOnCompleteAction(onComplete), new SubscribersKt$sam$io_reactivex_functions_Consumer$0(onError));
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe3, "subscribe(onComplete.asO…ion(), Consumer(onError))");
        return disposableSubscribe3;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static /* bridge */ /* synthetic */ void blockingSubscribeBy$default(Observable observable, Function1 function1, Function0 function0, Function1 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = onErrorStub;
        }
        if ((i & 2) != 0) {
            function0 = onCompleteStub;
        }
        if ((i & 4) != 0) {
            function2 = onNextStub;
        }
        blockingSubscribeBy(observable, (Function1<? super Throwable, Unit>) function1, (Function0<Unit>) function0, function2);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static final <T> void blockingSubscribeBy(Observable<T> receiver, Function1<? super Throwable, Unit> onError, Function0<Unit> onComplete, Function1<? super T, Unit> onNext) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        Intrinsics.checkParameterIsNotNull(onComplete, "onComplete");
        Intrinsics.checkParameterIsNotNull(onNext, "onNext");
        receiver.blockingSubscribe(asConsumer(onNext), asOnErrorConsumer(onError), asOnCompleteAction(onComplete));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    public static /* bridge */ /* synthetic */ void blockingSubscribeBy$default(Flowable flowable, Function1 function1, Function0 function0, Function1 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = onErrorStub;
        }
        if ((i & 2) != 0) {
            function0 = onCompleteStub;
        }
        if ((i & 4) != 0) {
            function2 = onNextStub;
        }
        blockingSubscribeBy(flowable, (Function1<? super Throwable, Unit>) function1, (Function0<Unit>) function0, function2);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    public static final <T> void blockingSubscribeBy(Flowable<T> receiver, Function1<? super Throwable, Unit> onError, Function0<Unit> onComplete, Function1<? super T, Unit> onNext) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        Intrinsics.checkParameterIsNotNull(onComplete, "onComplete");
        Intrinsics.checkParameterIsNotNull(onNext, "onNext");
        receiver.blockingSubscribe(asConsumer(onNext), asOnErrorConsumer(onError), asOnCompleteAction(onComplete));
    }
}
