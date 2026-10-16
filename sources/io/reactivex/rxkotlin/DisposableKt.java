package io.reactivex.rxkotlin;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: disposable.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0015\u0010\u0004\u001a\u00020\u0005*\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0001H\u0086\u0002¨\u0006\u0007"}, d2 = {"addTo", "Lio/reactivex/disposables/Disposable;", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "plusAssign", "", "disposable", "rxkotlin"}, k = 2, mv = {1, 1, 11})
public final class DisposableKt {
    public static final void plusAssign(CompositeDisposable receiver, Disposable disposable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(disposable, "disposable");
        receiver.add(disposable);
    }

    public static final Disposable addTo(Disposable receiver, CompositeDisposable compositeDisposable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(compositeDisposable, "compositeDisposable");
        compositeDisposable.add(receiver);
        return receiver;
    }
}
