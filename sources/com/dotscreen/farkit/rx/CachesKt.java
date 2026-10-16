package com.dotscreen.farkit.rx;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.MaybeSubject;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.SingleSubject;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: Caches.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a2\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0007\u001a2\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00062\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0007\u001a2\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00072\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0007\u001a8\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007\u001a8\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007\u001a8\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007\u001a?\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00072!\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00160\u0011¨\u0006\u0017"}, d2 = {"cacheUntil", "Lio/reactivex/Maybe;", ExifInterface.GPS_DIRECTION_TRUE, "U", "other", "Lio/reactivex/ObservableSource;", "Lio/reactivex/Observable;", "Lio/reactivex/Single;", "cacheWithTimeout", "delay", "", "unit", "Ljava/util/concurrent/TimeUnit;", "scheduler", "Lio/reactivex/Scheduler;", "returnCacheOnError", "errorFilter", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "error", "", "farkit_release"}, k = 2, mv = {1, 1, 16})
public final class CachesKt {
    public static final <T> Maybe<T> cacheWithTimeout(Maybe<T> maybe, long j, TimeUnit timeUnit) {
        return cacheWithTimeout$default(maybe, j, timeUnit, (Scheduler) null, 4, (Object) null);
    }

    public static final <T> Observable<T> cacheWithTimeout(Observable<T> observable, long j, TimeUnit timeUnit) {
        return cacheWithTimeout$default(observable, j, timeUnit, (Scheduler) null, 4, (Object) null);
    }

    public static final <T> Single<T> cacheWithTimeout(Single<T> single, long j, TimeUnit timeUnit) {
        return cacheWithTimeout$default(single, j, timeUnit, (Scheduler) null, 4, (Object) null);
    }

    public static /* synthetic */ Single cacheWithTimeout$default(Single single, long j, TimeUnit timeUnit, Scheduler scheduler, int i, Object obj) {
        if ((i & 4) != 0) {
            scheduler = Schedulers.computation();
            Intrinsics.checkExpressionValueIsNotNull(scheduler, "Schedulers.computation()");
        }
        return cacheWithTimeout(single, j, timeUnit, scheduler);
    }

    public static final <T> Single<T> cacheWithTimeout(Single<T> cacheWithTimeout, long j, TimeUnit unit, Scheduler scheduler) {
        Intrinsics.checkParameterIsNotNull(cacheWithTimeout, "$this$cacheWithTimeout");
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        Intrinsics.checkParameterIsNotNull(scheduler, "scheduler");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (T) ((SingleSubject) null);
        Single<T> singleCreate = Single.create(new C02621(cacheWithTimeout, objectRef, scheduler, j, unit));
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { observer…        }\n        }\n    }");
        return singleCreate;
    }

    /* JADX INFO: renamed from: com.dotscreen.farkit.rx.CachesKt$cacheWithTimeout$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Caches.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0014\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u0001H\u0002H\u00020\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "observer", "Lio/reactivex/SingleEmitter;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
    static final class C02621<T> implements SingleOnSubscribe<T> {
        final /* synthetic */ Ref.ObjectRef $cachedData;
        final /* synthetic */ long $delay;
        final /* synthetic */ Scheduler $scheduler;
        final /* synthetic */ Single $this_cacheWithTimeout;
        final /* synthetic */ TimeUnit $unit;

        C02621(Single single, Ref.ObjectRef objectRef, Scheduler scheduler, long j, TimeUnit timeUnit) {
            this.$this_cacheWithTimeout = single;
            this.$cachedData = objectRef;
            this.$scheduler = scheduler;
            this.$delay = j;
            this.$unit = timeUnit;
        }

        @Override // io.reactivex.SingleOnSubscribe
        public final void subscribe(final SingleEmitter<T> observer) {
            Intrinsics.checkParameterIsNotNull(observer, "observer");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = (T) ((Throwable) null);
            if (((SingleSubject) this.$cachedData.element) == null) {
                this.$cachedData.element = (T) SingleSubject.create();
                Single single = this.$this_cacheWithTimeout;
                SingleSubject singleSubject = (SingleSubject) this.$cachedData.element;
                if (singleSubject == null) {
                    Intrinsics.throwNpe();
                }
                single.subscribe(singleSubject);
                SingleSubject singleSubject2 = (SingleSubject) this.$cachedData.element;
                if (singleSubject2 == null) {
                    Intrinsics.throwNpe();
                }
                singleSubject2.subscribe(new Consumer<T>() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheWithTimeout.1.1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(T t) {
                        C02621.this.$scheduler.scheduleDirect(new Runnable() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheWithTimeout.1.1.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                Log.v("cacheWithTimeout", "Single.cacheWithTimeout: cache invalidated");
                                C02621.this.$cachedData.element = (T) ((SingleSubject) null);
                            }
                        }, C02621.this.$delay, C02621.this.$unit);
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheWithTimeout.1.2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        objectRef.element = th;
                        Log.w("cacheWithTimeout", "Single.cacheWithTimeout: exception: " + th);
                        C02621.this.$cachedData.element = (T) ((SingleSubject) null);
                    }
                });
            }
            if (((Throwable) objectRef.element) != null) {
                if (observer.isDisposed()) {
                    return;
                }
                Throwable th = (Throwable) objectRef.element;
                if (th == null) {
                    Intrinsics.throwNpe();
                }
                observer.onError(th);
                return;
            }
            SingleSubject singleSubject3 = (SingleSubject) this.$cachedData.element;
            if (singleSubject3 == null) {
                Intrinsics.throwNpe();
            }
            final Disposable disposableSubscribe = singleSubject3.subscribe(new Consumer<T>() { // from class: com.dotscreen.farkit.rx.CachesKt$cacheWithTimeout$1$disposable$1
                @Override // io.reactivex.functions.Consumer
                public final void accept(T t) {
                    SingleEmitter observer2 = observer;
                    Intrinsics.checkExpressionValueIsNotNull(observer2, "observer");
                    if (observer2.isDisposed()) {
                        return;
                    }
                    observer.onSuccess(t);
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.farkit.rx.CachesKt$cacheWithTimeout$1$disposable$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th2) {
                    SingleEmitter observer2 = observer;
                    Intrinsics.checkExpressionValueIsNotNull(observer2, "observer");
                    if (observer2.isDisposed()) {
                        return;
                    }
                    observer.onError(th2);
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "cachedData!!.subscribe(\n…d) observer.onError(e) })");
            observer.setCancellable(new Cancellable() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheWithTimeout.1.3
                @Override // io.reactivex.functions.Cancellable
                public final void cancel() {
                    disposableSubscribe.dispose();
                }
            });
        }
    }

    public static /* synthetic */ Maybe cacheWithTimeout$default(Maybe maybe, long j, TimeUnit timeUnit, Scheduler scheduler, int i, Object obj) {
        if ((i & 4) != 0) {
            scheduler = Schedulers.computation();
            Intrinsics.checkExpressionValueIsNotNull(scheduler, "Schedulers.computation()");
        }
        return cacheWithTimeout(maybe, j, timeUnit, scheduler);
    }

    public static final <T> Maybe<T> cacheWithTimeout(Maybe<T> cacheWithTimeout, long j, TimeUnit unit, Scheduler scheduler) {
        Intrinsics.checkParameterIsNotNull(cacheWithTimeout, "$this$cacheWithTimeout");
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        Intrinsics.checkParameterIsNotNull(scheduler, "scheduler");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (T) ((MaybeSubject) null);
        Maybe<T> maybeCreate = Maybe.create(new C02632(cacheWithTimeout, objectRef, scheduler, j, unit));
        Intrinsics.checkExpressionValueIsNotNull(maybeCreate, "Maybe.create { observer …        }\n        }\n    }");
        return maybeCreate;
    }

    /* JADX INFO: renamed from: com.dotscreen.farkit.rx.CachesKt$cacheWithTimeout$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Caches.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0014\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u0001H\u0002H\u00020\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "observer", "Lio/reactivex/MaybeEmitter;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
    static final class C02632<T> implements MaybeOnSubscribe<T> {
        final /* synthetic */ Ref.ObjectRef $cachedData;
        final /* synthetic */ long $delay;
        final /* synthetic */ Scheduler $scheduler;
        final /* synthetic */ Maybe $this_cacheWithTimeout;
        final /* synthetic */ TimeUnit $unit;

        C02632(Maybe maybe, Ref.ObjectRef objectRef, Scheduler scheduler, long j, TimeUnit timeUnit) {
            this.$this_cacheWithTimeout = maybe;
            this.$cachedData = objectRef;
            this.$scheduler = scheduler;
            this.$delay = j;
            this.$unit = timeUnit;
        }

        @Override // io.reactivex.MaybeOnSubscribe
        public final void subscribe(final MaybeEmitter<T> observer) {
            Intrinsics.checkParameterIsNotNull(observer, "observer");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = (T) ((Throwable) null);
            if (((MaybeSubject) this.$cachedData.element) == null) {
                this.$cachedData.element = (T) MaybeSubject.create();
                Maybe maybe = this.$this_cacheWithTimeout;
                MaybeSubject maybeSubject = (MaybeSubject) this.$cachedData.element;
                if (maybeSubject == null) {
                    Intrinsics.throwNpe();
                }
                maybe.subscribe(maybeSubject);
                MaybeSubject maybeSubject2 = (MaybeSubject) this.$cachedData.element;
                if (maybeSubject2 == null) {
                    Intrinsics.throwNpe();
                }
                maybeSubject2.subscribe(new Consumer<T>() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheWithTimeout.2.1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(T t) {
                        C02632.this.$scheduler.scheduleDirect(new Runnable() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheWithTimeout.2.1.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                Log.v("cacheWithTimeout", "Maybe.cacheWithTimeout: cache invalidated");
                                C02632.this.$cachedData.element = (T) ((MaybeSubject) null);
                            }
                        }, C02632.this.$delay, C02632.this.$unit);
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheWithTimeout.2.2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        objectRef.element = th;
                        Log.w("cacheWithTimeout", "Maybe.cacheWithTimeout: exception: " + th);
                        C02632.this.$cachedData.element = (T) ((MaybeSubject) null);
                    }
                }, new Action() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheWithTimeout.2.3
                    @Override // io.reactivex.functions.Action
                    public final void run() {
                        C02632.this.$scheduler.scheduleDirect(new Runnable() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheWithTimeout.2.3.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                Log.v("cacheWithTimeout", "Maybe.cacheWithTimeout: cache invalidated");
                                C02632.this.$cachedData.element = (T) ((MaybeSubject) null);
                            }
                        }, C02632.this.$delay, C02632.this.$unit);
                    }
                });
            }
            if (((Throwable) objectRef.element) != null) {
                if (observer.isDisposed()) {
                    return;
                }
                Throwable th = (Throwable) objectRef.element;
                if (th == null) {
                    Intrinsics.throwNpe();
                }
                observer.onError(th);
                return;
            }
            MaybeSubject maybeSubject3 = (MaybeSubject) this.$cachedData.element;
            if (maybeSubject3 == null) {
                Intrinsics.throwNpe();
            }
            final Disposable disposableSubscribe = maybeSubject3.subscribe(new Consumer<T>() { // from class: com.dotscreen.farkit.rx.CachesKt$cacheWithTimeout$2$disposable$1
                @Override // io.reactivex.functions.Consumer
                public final void accept(T t) {
                    MaybeEmitter observer2 = observer;
                    Intrinsics.checkExpressionValueIsNotNull(observer2, "observer");
                    if (observer2.isDisposed()) {
                        return;
                    }
                    observer.onSuccess(t);
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.farkit.rx.CachesKt$cacheWithTimeout$2$disposable$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th2) {
                    MaybeEmitter observer2 = observer;
                    Intrinsics.checkExpressionValueIsNotNull(observer2, "observer");
                    if (observer2.isDisposed()) {
                        return;
                    }
                    observer.onError(th2);
                }
            }, new Action() { // from class: com.dotscreen.farkit.rx.CachesKt$cacheWithTimeout$2$disposable$3
                @Override // io.reactivex.functions.Action
                public final void run() {
                    MaybeEmitter observer2 = observer;
                    Intrinsics.checkExpressionValueIsNotNull(observer2, "observer");
                    if (observer2.isDisposed()) {
                        return;
                    }
                    observer.onComplete();
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "cachedData!!.subscribe(\n… observer.onComplete() })");
            observer.setCancellable(new Cancellable() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheWithTimeout.2.4
                @Override // io.reactivex.functions.Cancellable
                public final void cancel() {
                    disposableSubscribe.dispose();
                }
            });
        }
    }

    public static /* synthetic */ Observable cacheWithTimeout$default(Observable observable, long j, TimeUnit timeUnit, Scheduler scheduler, int i, Object obj) {
        if ((i & 4) != 0) {
            scheduler = Schedulers.computation();
            Intrinsics.checkExpressionValueIsNotNull(scheduler, "Schedulers.computation()");
        }
        return cacheWithTimeout(observable, j, timeUnit, scheduler);
    }

    public static final <T> Observable<T> cacheWithTimeout(Observable<T> cacheWithTimeout, long j, TimeUnit unit, Scheduler scheduler) {
        Intrinsics.checkParameterIsNotNull(cacheWithTimeout, "$this$cacheWithTimeout");
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        Intrinsics.checkParameterIsNotNull(scheduler, "scheduler");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (T) ((ReplaySubject) null);
        Observable<T> observableCreate = Observable.create(new C02643(cacheWithTimeout, objectRef, scheduler, j, unit));
        Intrinsics.checkExpressionValueIsNotNull(observableCreate, "Observable.create { obse…        }\n        }\n    }");
        return observableCreate;
    }

    /* JADX INFO: renamed from: com.dotscreen.farkit.rx.CachesKt$cacheWithTimeout$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Caches.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0014\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u0001H\u0002H\u00020\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "observer", "Lio/reactivex/ObservableEmitter;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
    static final class C02643<T> implements ObservableOnSubscribe<T> {
        final /* synthetic */ Ref.ObjectRef $cachedData;
        final /* synthetic */ long $delay;
        final /* synthetic */ Scheduler $scheduler;
        final /* synthetic */ Observable $this_cacheWithTimeout;
        final /* synthetic */ TimeUnit $unit;

        C02643(Observable observable, Ref.ObjectRef objectRef, Scheduler scheduler, long j, TimeUnit timeUnit) {
            this.$this_cacheWithTimeout = observable;
            this.$cachedData = objectRef;
            this.$scheduler = scheduler;
            this.$delay = j;
            this.$unit = timeUnit;
        }

        @Override // io.reactivex.ObservableOnSubscribe
        public final void subscribe(final ObservableEmitter<T> observer) {
            Intrinsics.checkParameterIsNotNull(observer, "observer");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = (T) ((Throwable) null);
            if (((ReplaySubject) this.$cachedData.element) == null) {
                this.$cachedData.element = (T) ReplaySubject.create();
                Observable observable = this.$this_cacheWithTimeout;
                ReplaySubject replaySubject = (ReplaySubject) this.$cachedData.element;
                if (replaySubject == null) {
                    Intrinsics.throwNpe();
                }
                observable.subscribe(replaySubject);
                ReplaySubject replaySubject2 = (ReplaySubject) this.$cachedData.element;
                if (replaySubject2 == null) {
                    Intrinsics.throwNpe();
                }
                replaySubject2.subscribe(new Consumer<T>() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheWithTimeout.3.1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(T t) {
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheWithTimeout.3.2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        objectRef.element = th;
                        Log.w("cacheWithTimeout", "Observable.cacheWithTimeout: exception: " + th);
                        C02643.this.$cachedData.element = (T) ((ReplaySubject) null);
                    }
                }, new Action() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheWithTimeout.3.3
                    @Override // io.reactivex.functions.Action
                    public final void run() {
                        C02643.this.$scheduler.scheduleDirect(new Runnable() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheWithTimeout.3.3.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                Log.v("cacheWithTimeout", "Observable.cacheWithTimeout: cache invalidated");
                                C02643.this.$cachedData.element = (T) ((ReplaySubject) null);
                            }
                        }, C02643.this.$delay, C02643.this.$unit);
                    }
                });
            }
            if (((Throwable) objectRef.element) != null) {
                if (observer.isDisposed()) {
                    return;
                }
                Throwable th = (Throwable) objectRef.element;
                if (th == null) {
                    Intrinsics.throwNpe();
                }
                observer.onError(th);
                return;
            }
            ReplaySubject replaySubject3 = (ReplaySubject) this.$cachedData.element;
            if (replaySubject3 == null) {
                Intrinsics.throwNpe();
            }
            final Disposable disposableSubscribe = replaySubject3.subscribe(new Consumer<T>() { // from class: com.dotscreen.farkit.rx.CachesKt$cacheWithTimeout$3$disposable$1
                @Override // io.reactivex.functions.Consumer
                public final void accept(T t) {
                    ObservableEmitter observer2 = observer;
                    Intrinsics.checkExpressionValueIsNotNull(observer2, "observer");
                    if (observer2.isDisposed()) {
                        return;
                    }
                    observer.onNext(t);
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.farkit.rx.CachesKt$cacheWithTimeout$3$disposable$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th2) {
                    ObservableEmitter observer2 = observer;
                    Intrinsics.checkExpressionValueIsNotNull(observer2, "observer");
                    if (observer2.isDisposed()) {
                        return;
                    }
                    observer.onError(th2);
                }
            }, new Action() { // from class: com.dotscreen.farkit.rx.CachesKt$cacheWithTimeout$3$disposable$3
                @Override // io.reactivex.functions.Action
                public final void run() {
                    ObservableEmitter observer2 = observer;
                    Intrinsics.checkExpressionValueIsNotNull(observer2, "observer");
                    if (observer2.isDisposed()) {
                        return;
                    }
                    observer.onComplete();
                }
            });
            observer.setCancellable(new Cancellable() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheWithTimeout.3.4
                @Override // io.reactivex.functions.Cancellable
                public final void cancel() {
                    disposableSubscribe.dispose();
                }
            });
        }
    }

    public static final <T, U> Observable<T> cacheUntil(Observable<T> cacheUntil, ObservableSource<U> other) {
        Intrinsics.checkParameterIsNotNull(cacheUntil, "$this$cacheUntil");
        Intrinsics.checkParameterIsNotNull(other, "other");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (T) ((ReplaySubject) null);
        Observable<T> observableCreate = Observable.create(new AnonymousClass1(cacheUntil, objectRef, other));
        Intrinsics.checkExpressionValueIsNotNull(observableCreate, "Observable.create { obse…        }\n        }\n    }");
        return observableCreate;
    }

    /* JADX INFO: renamed from: com.dotscreen.farkit.rx.CachesKt$cacheUntil$1, reason: invalid class name */
    /* JADX INFO: compiled from: Caches.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0014\u0010\u0004\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u0001H\u0002H\u00020\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "U", "observer", "Lio/reactivex/ObservableEmitter;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass1<T> implements ObservableOnSubscribe<T> {
        final /* synthetic */ Ref.ObjectRef $cachedData;
        final /* synthetic */ ObservableSource $other;
        final /* synthetic */ Observable $this_cacheUntil;

        AnonymousClass1(Observable observable, Ref.ObjectRef objectRef, ObservableSource observableSource) {
            this.$this_cacheUntil = observable;
            this.$cachedData = objectRef;
            this.$other = observableSource;
        }

        @Override // io.reactivex.ObservableOnSubscribe
        public final void subscribe(final ObservableEmitter<T> observer) {
            Intrinsics.checkParameterIsNotNull(observer, "observer");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = (T) ((Throwable) null);
            if (((ReplaySubject) this.$cachedData.element) == null) {
                this.$cachedData.element = (T) ReplaySubject.create();
                Observable observable = this.$this_cacheUntil;
                ReplaySubject replaySubject = (ReplaySubject) this.$cachedData.element;
                if (replaySubject == null) {
                    Intrinsics.throwNpe();
                }
                observable.subscribe(replaySubject);
                ReplaySubject replaySubject2 = (ReplaySubject) this.$cachedData.element;
                if (replaySubject2 == null) {
                    Intrinsics.throwNpe();
                }
                replaySubject2.subscribe(new Consumer<T>() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheUntil.1.1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(T t) {
                        AnonymousClass1.this.$other.subscribe((Observer) new Observer<U>() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheUntil.1.1.1
                            private Disposable disposable;

                            @Override // io.reactivex.Observer
                            public void onComplete() {
                            }

                            @Override // io.reactivex.Observer
                            public void onError(Throwable e) {
                                Intrinsics.checkParameterIsNotNull(e, "e");
                            }

                            public final Disposable getDisposable() {
                                return this.disposable;
                            }

                            public final void setDisposable(Disposable disposable) {
                                this.disposable = disposable;
                            }

                            @Override // io.reactivex.Observer
                            public void onSubscribe(Disposable d) {
                                Intrinsics.checkParameterIsNotNull(d, "d");
                                this.disposable = d;
                            }

                            @Override // io.reactivex.Observer
                            public void onNext(U t2) {
                                Log.v("cacheUntil", "Observable.cacheUntil: cache invalidated");
                                AnonymousClass1.this.$cachedData.element = (T) ((ReplaySubject) null);
                                Disposable disposable = this.disposable;
                                if (disposable != null) {
                                    disposable.dispose();
                                }
                            }
                        });
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheUntil.1.2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        objectRef.element = th;
                        Log.w("cacheUntil", "Observable.cacheUntil: exception: " + th);
                        AnonymousClass1.this.$cachedData.element = (T) ((ReplaySubject) null);
                    }
                });
            }
            if (((Throwable) objectRef.element) != null) {
                if (observer.isDisposed()) {
                    return;
                }
                Throwable th = (Throwable) objectRef.element;
                if (th == null) {
                    Intrinsics.throwNpe();
                }
                observer.onError(th);
                return;
            }
            ReplaySubject replaySubject3 = (ReplaySubject) this.$cachedData.element;
            if (replaySubject3 == null) {
                Intrinsics.throwNpe();
            }
            final Disposable disposableSubscribe = replaySubject3.subscribe(new Consumer<T>() { // from class: com.dotscreen.farkit.rx.CachesKt$cacheUntil$1$disposable$1
                @Override // io.reactivex.functions.Consumer
                public final void accept(T t) {
                    ObservableEmitter observer2 = observer;
                    Intrinsics.checkExpressionValueIsNotNull(observer2, "observer");
                    if (observer2.isDisposed()) {
                        return;
                    }
                    observer.onNext(t);
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.farkit.rx.CachesKt$cacheUntil$1$disposable$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th2) {
                    ObservableEmitter observer2 = observer;
                    Intrinsics.checkExpressionValueIsNotNull(observer2, "observer");
                    if (observer2.isDisposed()) {
                        return;
                    }
                    observer.onError(th2);
                }
            }, new Action() { // from class: com.dotscreen.farkit.rx.CachesKt$cacheUntil$1$disposable$3
                @Override // io.reactivex.functions.Action
                public final void run() {
                    ObservableEmitter observer2 = observer;
                    Intrinsics.checkExpressionValueIsNotNull(observer2, "observer");
                    if (observer2.isDisposed()) {
                        return;
                    }
                    observer.onComplete();
                }
            });
            observer.setCancellable(new Cancellable() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheUntil.1.3
                @Override // io.reactivex.functions.Cancellable
                public final void cancel() {
                    disposableSubscribe.dispose();
                }
            });
        }
    }

    public static final <T, U> Maybe<T> cacheUntil(Maybe<T> cacheUntil, ObservableSource<U> other) {
        Intrinsics.checkParameterIsNotNull(cacheUntil, "$this$cacheUntil");
        Intrinsics.checkParameterIsNotNull(other, "other");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (T) ((MaybeSubject) null);
        Maybe<T> maybeCreate = Maybe.create(new AnonymousClass2(cacheUntil, objectRef, other));
        Intrinsics.checkExpressionValueIsNotNull(maybeCreate, "Maybe.create { observer …        }\n        }\n    }");
        return maybeCreate;
    }

    /* JADX INFO: renamed from: com.dotscreen.farkit.rx.CachesKt$cacheUntil$2, reason: invalid class name */
    /* JADX INFO: compiled from: Caches.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0014\u0010\u0004\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u0001H\u0002H\u00020\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "U", "observer", "Lio/reactivex/MaybeEmitter;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass2<T> implements MaybeOnSubscribe<T> {
        final /* synthetic */ Ref.ObjectRef $cachedData;
        final /* synthetic */ ObservableSource $other;
        final /* synthetic */ Maybe $this_cacheUntil;

        AnonymousClass2(Maybe maybe, Ref.ObjectRef objectRef, ObservableSource observableSource) {
            this.$this_cacheUntil = maybe;
            this.$cachedData = objectRef;
            this.$other = observableSource;
        }

        @Override // io.reactivex.MaybeOnSubscribe
        public final void subscribe(final MaybeEmitter<T> observer) {
            Intrinsics.checkParameterIsNotNull(observer, "observer");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = (T) ((Throwable) null);
            if (((MaybeSubject) this.$cachedData.element) == null) {
                this.$cachedData.element = (T) MaybeSubject.create();
                Maybe maybe = this.$this_cacheUntil;
                MaybeSubject maybeSubject = (MaybeSubject) this.$cachedData.element;
                if (maybeSubject == null) {
                    Intrinsics.throwNpe();
                }
                maybe.subscribe(maybeSubject);
                MaybeSubject maybeSubject2 = (MaybeSubject) this.$cachedData.element;
                if (maybeSubject2 == null) {
                    Intrinsics.throwNpe();
                }
                maybeSubject2.subscribe(new Consumer<T>() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheUntil.2.1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(T t) {
                        AnonymousClass2.this.$other.subscribe((Observer) new Observer<U>() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheUntil.2.1.1
                            private Disposable disposable;

                            @Override // io.reactivex.Observer
                            public void onComplete() {
                            }

                            @Override // io.reactivex.Observer
                            public void onError(Throwable e) {
                                Intrinsics.checkParameterIsNotNull(e, "e");
                            }

                            public final Disposable getDisposable() {
                                return this.disposable;
                            }

                            public final void setDisposable(Disposable disposable) {
                                this.disposable = disposable;
                            }

                            @Override // io.reactivex.Observer
                            public void onSubscribe(Disposable d) {
                                Intrinsics.checkParameterIsNotNull(d, "d");
                                this.disposable = d;
                            }

                            @Override // io.reactivex.Observer
                            public void onNext(U t2) {
                                Log.v("cacheUntil", "Maybe.cacheUntil: cache invalidated for " + ((MaybeSubject) AnonymousClass2.this.$cachedData.element));
                                AnonymousClass2.this.$cachedData.element = (T) ((MaybeSubject) null);
                                Disposable disposable = this.disposable;
                                if (disposable != null) {
                                    disposable.dispose();
                                }
                            }
                        });
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheUntil.2.2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        objectRef.element = th;
                        Log.w("cacheUntil", "Maybe.cacheUntil: exception: " + th);
                        AnonymousClass2.this.$cachedData.element = (T) ((MaybeSubject) null);
                    }
                });
            }
            if (((Throwable) objectRef.element) != null) {
                if (observer.isDisposed()) {
                    return;
                }
                Throwable th = (Throwable) objectRef.element;
                if (th == null) {
                    Intrinsics.throwNpe();
                }
                observer.onError(th);
                return;
            }
            MaybeSubject maybeSubject3 = (MaybeSubject) this.$cachedData.element;
            if (maybeSubject3 == null) {
                Intrinsics.throwNpe();
            }
            final Disposable disposableSubscribe = maybeSubject3.subscribe(new Consumer<T>() { // from class: com.dotscreen.farkit.rx.CachesKt$cacheUntil$2$disposable$1
                @Override // io.reactivex.functions.Consumer
                public final void accept(T t) {
                    MaybeEmitter observer2 = observer;
                    Intrinsics.checkExpressionValueIsNotNull(observer2, "observer");
                    if (observer2.isDisposed()) {
                        return;
                    }
                    observer.onSuccess(t);
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.farkit.rx.CachesKt$cacheUntil$2$disposable$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th2) {
                    MaybeEmitter observer2 = observer;
                    Intrinsics.checkExpressionValueIsNotNull(observer2, "observer");
                    if (observer2.isDisposed()) {
                        return;
                    }
                    observer.onError(th2);
                }
            }, new Action() { // from class: com.dotscreen.farkit.rx.CachesKt$cacheUntil$2$disposable$3
                @Override // io.reactivex.functions.Action
                public final void run() {
                    MaybeEmitter observer2 = observer;
                    Intrinsics.checkExpressionValueIsNotNull(observer2, "observer");
                    if (observer2.isDisposed()) {
                        return;
                    }
                    observer.onComplete();
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "cachedData!!.subscribe(\n… observer.onComplete() })");
            observer.setCancellable(new Cancellable() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheUntil.2.3
                @Override // io.reactivex.functions.Cancellable
                public final void cancel() {
                    disposableSubscribe.dispose();
                }
            });
        }
    }

    public static final <T, U> Single<T> cacheUntil(Single<T> cacheUntil, ObservableSource<U> other) {
        Intrinsics.checkParameterIsNotNull(cacheUntil, "$this$cacheUntil");
        Intrinsics.checkParameterIsNotNull(other, "other");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (T) ((SingleSubject) null);
        Single<T> singleCreate = Single.create(new AnonymousClass3(cacheUntil, objectRef, other));
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { observer…        }\n        }\n    }");
        return singleCreate;
    }

    /* JADX INFO: renamed from: com.dotscreen.farkit.rx.CachesKt$cacheUntil$3, reason: invalid class name */
    /* JADX INFO: compiled from: Caches.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0014\u0010\u0004\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u0001H\u0002H\u00020\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "U", "observer", "Lio/reactivex/SingleEmitter;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass3<T> implements SingleOnSubscribe<T> {
        final /* synthetic */ Ref.ObjectRef $cachedData;
        final /* synthetic */ ObservableSource $other;
        final /* synthetic */ Single $this_cacheUntil;

        AnonymousClass3(Single single, Ref.ObjectRef objectRef, ObservableSource observableSource) {
            this.$this_cacheUntil = single;
            this.$cachedData = objectRef;
            this.$other = observableSource;
        }

        @Override // io.reactivex.SingleOnSubscribe
        public final void subscribe(final SingleEmitter<T> observer) {
            Intrinsics.checkParameterIsNotNull(observer, "observer");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = (T) ((Throwable) null);
            if (((SingleSubject) this.$cachedData.element) == null) {
                this.$cachedData.element = (T) SingleSubject.create();
                Single single = this.$this_cacheUntil;
                SingleSubject singleSubject = (SingleSubject) this.$cachedData.element;
                if (singleSubject == null) {
                    Intrinsics.throwNpe();
                }
                single.subscribe(singleSubject);
                SingleSubject singleSubject2 = (SingleSubject) this.$cachedData.element;
                if (singleSubject2 == null) {
                    Intrinsics.throwNpe();
                }
                singleSubject2.subscribe(new Consumer<T>() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheUntil.3.1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(T t) {
                        AnonymousClass3.this.$other.subscribe((Observer) new Observer<U>() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheUntil.3.1.1
                            private Disposable disposable;

                            @Override // io.reactivex.Observer
                            public void onComplete() {
                            }

                            @Override // io.reactivex.Observer
                            public void onError(Throwable e) {
                                Intrinsics.checkParameterIsNotNull(e, "e");
                            }

                            public final Disposable getDisposable() {
                                return this.disposable;
                            }

                            public final void setDisposable(Disposable disposable) {
                                this.disposable = disposable;
                            }

                            @Override // io.reactivex.Observer
                            public void onSubscribe(Disposable d) {
                                Intrinsics.checkParameterIsNotNull(d, "d");
                                this.disposable = d;
                            }

                            @Override // io.reactivex.Observer
                            public void onNext(U t2) {
                                Log.v("cacheUntil", "Single.cacheUntil: cache invalidated");
                                AnonymousClass3.this.$cachedData.element = (T) ((SingleSubject) null);
                                Disposable disposable = this.disposable;
                                if (disposable != null) {
                                    disposable.dispose();
                                }
                            }
                        });
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheUntil.3.2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        objectRef.element = th;
                        Log.w("cacheUntil", "Single.cacheUntil: exception: " + th);
                        AnonymousClass3.this.$cachedData.element = (T) ((SingleSubject) null);
                    }
                });
            }
            if (((Throwable) objectRef.element) != null) {
                if (observer.isDisposed()) {
                    return;
                }
                Throwable th = (Throwable) objectRef.element;
                if (th == null) {
                    Intrinsics.throwNpe();
                }
                observer.onError(th);
                return;
            }
            SingleSubject singleSubject3 = (SingleSubject) this.$cachedData.element;
            if (singleSubject3 == null) {
                Intrinsics.throwNpe();
            }
            final Disposable disposableSubscribe = singleSubject3.subscribe(new Consumer<T>() { // from class: com.dotscreen.farkit.rx.CachesKt$cacheUntil$3$disposable$1
                @Override // io.reactivex.functions.Consumer
                public final void accept(T t) {
                    SingleEmitter observer2 = observer;
                    Intrinsics.checkExpressionValueIsNotNull(observer2, "observer");
                    if (observer2.isDisposed()) {
                        return;
                    }
                    observer.onSuccess(t);
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.farkit.rx.CachesKt$cacheUntil$3$disposable$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th2) {
                    SingleEmitter observer2 = observer;
                    Intrinsics.checkExpressionValueIsNotNull(observer2, "observer");
                    if (observer2.isDisposed()) {
                        return;
                    }
                    observer.onError(th2);
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "cachedData!!.subscribe(\n…d) observer.onError(e) })");
            observer.setCancellable(new Cancellable() { // from class: com.dotscreen.farkit.rx.CachesKt.cacheUntil.3.3
                @Override // io.reactivex.functions.Cancellable
                public final void cancel() {
                    disposableSubscribe.dispose();
                }
            });
        }
    }

    public static final <T> Single<T> returnCacheOnError(Single<T> returnCacheOnError, Function1<? super Throwable, Boolean> errorFilter) {
        Intrinsics.checkParameterIsNotNull(returnCacheOnError, "$this$returnCacheOnError");
        Intrinsics.checkParameterIsNotNull(errorFilter, "errorFilter");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = null;
        Single<T> singleCreate = Single.create(new C02651(returnCacheOnError, objectRef, errorFilter));
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { observer…dispose()\n        }\n    }");
        return singleCreate;
    }

    /* JADX INFO: renamed from: com.dotscreen.farkit.rx.CachesKt$returnCacheOnError$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Caches.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0014\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u0001H\u0002H\u00020\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "observer", "Lio/reactivex/SingleEmitter;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
    static final class C02651<T> implements SingleOnSubscribe<T> {
        final /* synthetic */ Ref.ObjectRef $cachedData;
        final /* synthetic */ Function1 $errorFilter;
        final /* synthetic */ Single $this_returnCacheOnError;

        C02651(Single single, Ref.ObjectRef objectRef, Function1 function1) {
            this.$this_returnCacheOnError = single;
            this.$cachedData = objectRef;
            this.$errorFilter = function1;
        }

        @Override // io.reactivex.SingleOnSubscribe
        public final void subscribe(final SingleEmitter<T> observer) {
            Intrinsics.checkParameterIsNotNull(observer, "observer");
            final Disposable disposableSubscribe = this.$this_returnCacheOnError.subscribe(new Consumer<T>() { // from class: com.dotscreen.farkit.rx.CachesKt$returnCacheOnError$1$disposable$1
                @Override // io.reactivex.functions.Consumer
                public final void accept(T t) {
                    this.this$0.$cachedData.element = t;
                    SingleEmitter observer2 = observer;
                    Intrinsics.checkExpressionValueIsNotNull(observer2, "observer");
                    if (observer2.isDisposed()) {
                        return;
                    }
                    observer.onSuccess(t);
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.farkit.rx.CachesKt$returnCacheOnError$1$disposable$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable e) {
                    if (this.this$0.$cachedData.element != null) {
                        Function1 function1 = this.this$0.$errorFilter;
                        Intrinsics.checkExpressionValueIsNotNull(e, "e");
                        if (((Boolean) function1.invoke(e)).booleanValue()) {
                            SingleEmitter observer2 = observer;
                            Intrinsics.checkExpressionValueIsNotNull(observer2, "observer");
                            if (observer2.isDisposed()) {
                                return;
                            }
                            SingleEmitter singleEmitter = observer;
                            T t = this.this$0.$cachedData.element;
                            if (t == null) {
                                Intrinsics.throwNpe();
                            }
                            singleEmitter.onSuccess(t);
                            return;
                        }
                    }
                    SingleEmitter observer3 = observer;
                    Intrinsics.checkExpressionValueIsNotNull(observer3, "observer");
                    if (observer3.isDisposed()) {
                        return;
                    }
                    observer.onError(e);
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "this.subscribe(\n        …     }\n                })");
            observer.setCancellable(new Cancellable() { // from class: com.dotscreen.farkit.rx.CachesKt.returnCacheOnError.1.1
                @Override // io.reactivex.functions.Cancellable
                public final void cancel() {
                    disposableSubscribe.dispose();
                }
            });
        }
    }
}
