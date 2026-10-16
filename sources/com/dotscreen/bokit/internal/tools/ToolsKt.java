package com.dotscreen.bokit.internal.tools;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.dotscreen.bokit.NotImplementedException;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.ReplaySubject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: Tools.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\b\u001a\u0011\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\b\u001a\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\b\u001a\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\n\"\u0004\b\u0000\u0010\b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\b\u001a\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\b0\f\"\u0004\b\u0000\u0010\b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\b\u001a\"\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00100\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0000\u001a\u001c\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0000\u001a0\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\b0\n\"\u0004\b\u0000\u0010\b\"\u0004\b\u0001\u0010\u0017*\b\u0012\u0004\u0012\u0002H\b0\n2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0019¨\u0006\u001a"}, d2 = {"BO_TODO", "", "reason", "", "BO_TODO_COMPLETABLE", "Lio/reactivex/Completable;", "BO_TODO_MAYBE", "Lio/reactivex/Maybe;", ExifInterface.GPS_DIRECTION_TRUE, "BO_TODO_OBSERVABLE", "Lio/reactivex/Observable;", "BO_TODO_SINGLE", "Lio/reactivex/Single;", "jsonArrayToMap", "", "", "", "jsonArray", "Lorg/json/JSONArray;", "jsonObjectToMap", "jsonObject", "Lorg/json/JSONObject;", "cacheUntil", "U", "other", "Lio/reactivex/ObservableSource;", "bokit-android_release"}, k = 2, mv = {1, 4, 1})
public final class ToolsKt {
    public static final Void BO_TODO(String reason) throws NotImplementedException {
        Intrinsics.checkNotNullParameter(reason, "reason");
        throw new NotImplementedException("An operation is not implemented: " + reason, null, 2, null);
    }

    public static final <T> Observable<T> BO_TODO_OBSERVABLE(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        Observable<T> observableError = Observable.error(new NotImplementedException("An operation is not implemented: " + reason, null, 2, null));
        Intrinsics.checkNotNullExpressionValue(observableError, "Observable.error(NotImpl…t implemented: $reason\"))");
        return observableError;
    }

    public static final <T> Maybe<T> BO_TODO_MAYBE(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        Maybe<T> maybeError = Maybe.error(new NotImplementedException("An operation is not implemented: " + reason, null, 2, null));
        Intrinsics.checkNotNullExpressionValue(maybeError, "Maybe.error(NotImplement…t implemented: $reason\"))");
        return maybeError;
    }

    public static final <T> Single<T> BO_TODO_SINGLE(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        Single<T> singleError = Single.error(new NotImplementedException("An operation is not implemented: " + reason, null, 2, null));
        Intrinsics.checkNotNullExpressionValue(singleError, "Single.error(NotImplemen…t implemented: $reason\"))");
        return singleError;
    }

    public static final Completable BO_TODO_COMPLETABLE(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        Completable completableError = Completable.error(new NotImplementedException("An operation is not implemented: " + reason, null, 2, null));
        Intrinsics.checkNotNullExpressionValue(completableError, "Completable.error(NotImp…t implemented: $reason\"))");
        return completableError;
    }

    public static final Map<String, Object> jsonObjectToMap(JSONObject jsonObject) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<String> itKeys = jsonObject.keys();
        Intrinsics.checkNotNullExpressionValue(itKeys, "jsonObject.keys()");
        while (itKeys.hasNext()) {
            String key = itKeys.next();
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                Intrinsics.checkNotNullExpressionValue(key, "key");
                linkedHashMap.put(key, jsonObjectToMap((JSONObject) value));
            } else if (value instanceof JSONArray) {
                Intrinsics.checkNotNullExpressionValue(key, "key");
                linkedHashMap.put(key, jsonArrayToMap((JSONArray) value));
            } else {
                Intrinsics.checkNotNullExpressionValue(key, "key");
                Intrinsics.checkNotNullExpressionValue(value, "value");
                linkedHashMap.put(key, value);
            }
        }
        return MapsKt.toMap(linkedHashMap);
    }

    public static final List<Map<String, Object>> jsonArrayToMap(JSONArray jsonArray) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonArray, "jsonArray");
        ArrayList arrayList = new ArrayList();
        int length = jsonArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jsonArray.getJSONObject(i);
            Intrinsics.checkNotNullExpressionValue(jSONObject, "jsonArray.getJSONObject(i)");
            arrayList.add(jsonObjectToMap(jSONObject));
        }
        return CollectionsKt.toList(arrayList);
    }

    public static final <T, U> Observable<T> cacheUntil(Observable<T> cacheUntil, ObservableSource<U> other) {
        Intrinsics.checkNotNullParameter(cacheUntil, "$this$cacheUntil");
        Intrinsics.checkNotNullParameter(other, "other");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (T) ((ReplaySubject) null);
        Observable<T> observableCreate = Observable.create(new AnonymousClass1(cacheUntil, objectRef, other));
        Intrinsics.checkNotNullExpressionValue(observableCreate, "Observable.create { obse…        }\n        }\n    }");
        return observableCreate;
    }

    /* JADX INFO: renamed from: com.dotscreen.bokit.internal.tools.ToolsKt$cacheUntil$1, reason: invalid class name */
    /* JADX INFO: compiled from: Tools.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0014\u0010\u0004\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u0001H\u0002H\u00020\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "U", "observer", "Lio/reactivex/ObservableEmitter;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 4, 1})
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
            Intrinsics.checkNotNullParameter(observer, "observer");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = (T) ((Throwable) null);
            if (((ReplaySubject) this.$cachedData.element) == null) {
                this.$cachedData.element = (T) ReplaySubject.create();
                Observable observable = this.$this_cacheUntil;
                ReplaySubject replaySubject = (ReplaySubject) this.$cachedData.element;
                Intrinsics.checkNotNull(replaySubject);
                observable.subscribe(replaySubject);
                ReplaySubject replaySubject2 = (ReplaySubject) this.$cachedData.element;
                Intrinsics.checkNotNull(replaySubject2);
                replaySubject2.subscribe(new Consumer<T>() { // from class: com.dotscreen.bokit.internal.tools.ToolsKt.cacheUntil.1.1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(T t) {
                        AnonymousClass1.this.$other.subscribe((Observer) new Observer<U>() { // from class: com.dotscreen.bokit.internal.tools.ToolsKt.cacheUntil.1.1.1
                            private Disposable disposable;

                            @Override // io.reactivex.Observer
                            public void onComplete() {
                            }

                            @Override // io.reactivex.Observer
                            public void onError(Throwable e) {
                                Intrinsics.checkNotNullParameter(e, "e");
                            }

                            public final Disposable getDisposable() {
                                return this.disposable;
                            }

                            public final void setDisposable(Disposable disposable) {
                                this.disposable = disposable;
                            }

                            @Override // io.reactivex.Observer
                            public void onSubscribe(Disposable d) {
                                Intrinsics.checkNotNullParameter(d, "d");
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
                }, new Consumer<Throwable>() { // from class: com.dotscreen.bokit.internal.tools.ToolsKt.cacheUntil.1.2
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
                Throwable th = (Throwable) objectRef.element;
                Intrinsics.checkNotNull(th);
                observer.onError(th);
            } else {
                ReplaySubject replaySubject3 = (ReplaySubject) this.$cachedData.element;
                Intrinsics.checkNotNull(replaySubject3);
                final Disposable disposableSubscribe = replaySubject3.subscribe(new Consumer<T>() { // from class: com.dotscreen.bokit.internal.tools.ToolsKt$cacheUntil$1$disposable$1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(T t) {
                        ObservableEmitter observer2 = observer;
                        Intrinsics.checkNotNullExpressionValue(observer2, "observer");
                        if (observer2.isDisposed()) {
                            return;
                        }
                        observer.onNext(t);
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.bokit.internal.tools.ToolsKt$cacheUntil$1$disposable$2
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th2) {
                        ObservableEmitter observer2 = observer;
                        Intrinsics.checkNotNullExpressionValue(observer2, "observer");
                        if (observer2.isDisposed()) {
                            return;
                        }
                        observer.onError(th2);
                    }
                }, new Action() { // from class: com.dotscreen.bokit.internal.tools.ToolsKt$cacheUntil$1$disposable$3
                    @Override // io.reactivex.functions.Action
                    public final void run() {
                        ObservableEmitter observer2 = observer;
                        Intrinsics.checkNotNullExpressionValue(observer2, "observer");
                        if (observer2.isDisposed()) {
                            return;
                        }
                        observer.onComplete();
                    }
                });
                observer.setCancellable(new Cancellable() { // from class: com.dotscreen.bokit.internal.tools.ToolsKt.cacheUntil.1.3
                    @Override // io.reactivex.functions.Cancellable
                    public final void cancel() {
                        disposableSubscribe.dispose();
                    }
                });
            }
        }
    }
}
