package com.dotscreen.farkit.rx;

import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LifecycleAwareDisposable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¨\u0006\u0004"}, d2 = {"addTo", "Lio/reactivex/disposables/Disposable;", "lifecycleAwareDisposable", "Lcom/dotscreen/farkit/rx/LifecycleAwareDisposable;", "farkit_release"}, k = 2, mv = {1, 1, 16})
public final class LifecycleAwareDisposableKt {
    public static final Disposable addTo(Disposable addTo, LifecycleAwareDisposable lifecycleAwareDisposable) {
        Intrinsics.checkParameterIsNotNull(addTo, "$this$addTo");
        if (lifecycleAwareDisposable != null) {
            lifecycleAwareDisposable.add(addTo);
        }
        return addTo;
    }
}
