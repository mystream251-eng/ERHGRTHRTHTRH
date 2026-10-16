package com.dotscreen.portal.model;

import com.dotscreen.bokit.model.TVAiring;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayerViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0006¨\u0006\u0007"}, d2 = {"addTo", "Lio/reactivex/disposables/Disposable;", "disposableList", "Lio/reactivex/disposables/CompositeDisposable;", "duration", "", "Lcom/dotscreen/bokit/model/TVAiring;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class PlayerViewModelKt {
    public static final long duration(TVAiring duration) {
        Intrinsics.checkParameterIsNotNull(duration, "$this$duration");
        return duration.getEndDate().getTime() - duration.getStartDate().getTime();
    }

    public static final Disposable addTo(Disposable addTo, CompositeDisposable disposableList) {
        Intrinsics.checkParameterIsNotNull(addTo, "$this$addTo");
        Intrinsics.checkParameterIsNotNull(disposableList, "disposableList");
        disposableList.add(addTo);
        return addTo;
    }
}
