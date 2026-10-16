package com.dotscreen.portal.model;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MyContentsViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001aA\u0010\u0002\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00072\u0006\u0010\b\u001a\u0002H\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007H\u0002¢\u0006\u0002\u0010\u000b\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"TAG", "", "bindToLiveData", "Lio/reactivex/disposables/Disposable;", ExifInterface.GPS_DIRECTION_TRUE, "Lio/reactivex/Single;", "liveData", "Landroidx/lifecycle/MutableLiveData;", "defaultOnError", "e", "", "(Lio/reactivex/Single;Landroidx/lifecycle/MutableLiveData;Ljava/lang/Object;Landroidx/lifecycle/MutableLiveData;)Lio/reactivex/disposables/Disposable;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class MyContentsViewModelKt {
    private static final String TAG = MyContentsViewModel.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> Disposable bindToLiveData(Single<T> single, final MutableLiveData<T> mutableLiveData, final T t, final MutableLiveData<Boolean> mutableLiveData2) {
        Disposable disposableSubscribe = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<T>() { // from class: com.dotscreen.portal.model.MyContentsViewModelKt.bindToLiveData.1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T t2) {
                mutableLiveData.postValue(t2);
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.model.MyContentsViewModelKt.bindToLiveData.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.w(MyContentsViewModelKt.TAG, "error", th);
                mutableLiveData.postValue(t);
                mutableLiveData2.postValue(true);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "this\n            .subscr…alue(true)\n            })");
        return disposableSubscribe;
    }
}
