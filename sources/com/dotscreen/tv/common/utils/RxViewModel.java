package com.dotscreen.tv.common.utils;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RxViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rH$J\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u00020\u000fH\u0002J\b\u0010\u0011\u001a\u00020\u000fH\u0014R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/tv/common/utils/RxViewModel;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "()V", "disposable", "Lio/reactivex/disposables/Disposable;", "internalValue", "Landroidx/lifecycle/MutableLiveData;", "value", "Landroidx/lifecycle/LiveData;", "getValue", "()Landroidx/lifecycle/LiveData;", "getSingle", "Lio/reactivex/Single;", "invalidateData", "", "loadValue", "onCleared", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public abstract class RxViewModel<T> extends ViewModel {
    private Disposable disposable;
    private MutableLiveData<T> internalValue;

    protected abstract Single<T> getSingle();

    public final LiveData<T> getValue() {
        if (this.internalValue == null) {
            this.internalValue = new MutableLiveData<>();
            loadValue();
        }
        MutableLiveData<T> mutableLiveData = this.internalValue;
        if (mutableLiveData == null) {
            Intrinsics.throwNpe();
        }
        return mutableLiveData;
    }

    private final void loadValue() {
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.disposable = getSingle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<T>() { // from class: com.dotscreen.tv.common.utils.RxViewModel.loadValue.1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T t) {
                MutableLiveData mutableLiveData = RxViewModel.this.internalValue;
                if (mutableLiveData == null) {
                    Intrinsics.throwNpe();
                }
                mutableLiveData.postValue(t);
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.utils.RxViewModel.loadValue.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.w("RxViewModel", "loadValue error", th);
                MutableLiveData mutableLiveData = RxViewModel.this.internalValue;
                if (mutableLiveData == null) {
                    Intrinsics.throwNpe();
                }
                mutableLiveData.postValue(null);
            }
        });
    }

    public final void invalidateData() {
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
        loadValue();
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
