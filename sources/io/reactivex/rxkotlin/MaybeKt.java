package io.reactivex.rxkotlin;

import androidx.exifinterface.media.ExifInterface;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.annotations.BackpressureKind;
import io.reactivex.annotations.BackpressureSupport;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: maybe.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u0006\u0012\u0002\b\u00030\u0001H\u0087\b\u001aF\u0010\u0004\u001a&\u0012\f\u0012\n \u0007*\u0004\u0018\u0001H\u0006H\u0006 \u0007*\u0012\u0012\f\u0012\n \u0007*\u0004\u0018\u0001H\u0006H\u0006\u0018\u00010\u00050\u0005\"\b\b\u0000\u0010\u0006*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\t0\bH\u0007\u001aF\u0010\n\u001a&\u0012\f\u0012\n \u0007*\u0004\u0018\u0001H\u0006H\u0006 \u0007*\u0012\u0012\f\u0012\n \u0007*\u0004\u0018\u0001H\u0006H\u0006\u0018\u00010\u00050\u0005\"\b\b\u0000\u0010\u0006*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00010\u0005H\u0007\u001aF\u0010\n\u001a&\u0012\f\u0012\n \u0007*\u0004\u0018\u0001H\u0006H\u0006 \u0007*\u0012\u0012\f\u0012\n \u0007*\u0004\u0018\u0001H\u0006H\u0006\u0018\u00010\u000b0\u000b\"\b\b\u0000\u0010\u0006*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00010\u000bH\u0007\u001a#\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u0006\u0012\u0002\b\u00030\u0001H\u0087\b¨\u0006\r"}, d2 = {"cast", "Lio/reactivex/Maybe;", "R", "", "concatAll", "Lio/reactivex/Flowable;", ExifInterface.GPS_DIRECTION_TRUE, "kotlin.jvm.PlatformType", "", "Lio/reactivex/MaybeSource;", "mergeAllMaybes", "Lio/reactivex/Observable;", "ofType", "rxkotlin"}, k = 2, mv = {1, 1, 11})
public final class MaybeKt {
    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    private static final <R> Maybe<R> cast(Maybe<?> maybe) {
        Intrinsics.reifiedOperationMarker(4, "R");
        Maybe<R> maybe2 = (Maybe<R>) maybe.cast(Object.class);
        Intrinsics.checkExpressionValueIsNotNull(maybe2, "cast(R::class.java)");
        return maybe2;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    private static final <R> Maybe<R> ofType(Maybe<?> maybe) {
        Intrinsics.reifiedOperationMarker(4, "R");
        Maybe<R> maybe2 = (Maybe<R>) maybe.ofType(Object.class);
        Intrinsics.checkExpressionValueIsNotNull(maybe2, "ofType(R::class.java)");
        return maybe2;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T> Observable<T> mergeAllMaybes(Observable<Maybe<T>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (Observable<T>) receiver.flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: io.reactivex.rxkotlin.MaybeKt.mergeAllMaybes.1
            @Override // io.reactivex.functions.Function
            public final Maybe<T> apply(Maybe<T> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it;
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    public static final <T> Flowable<T> mergeAllMaybes(Flowable<Maybe<T>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (Flowable<T>) receiver.flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: io.reactivex.rxkotlin.MaybeKt.mergeAllMaybes.2
            @Override // io.reactivex.functions.Function
            public final Maybe<T> apply(Maybe<T> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it;
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static final <T> Flowable<T> concatAll(Iterable<? extends MaybeSource<T>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Maybe.concat(receiver);
    }
}
