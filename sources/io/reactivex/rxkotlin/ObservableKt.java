package io.reactivex.rxkotlin;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntProgression;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: observable.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000±\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0018\n\u0002\u0010\u0005\n\u0002\u0010\u0012\n\u0002\u0010\f\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0013\n\u0002\u0010\u0007\n\u0002\u0010\u0014\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\u0010\t\n\u0002\u0010\u0016\n\u0002\u0010\n\n\u0002\u0010\u0017\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u001c\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u0006\u0012\u0002\b\u00030\u0001H\u0087\b\u001a^\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u00010\u00062)\b\u0004\u0010\u0007\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002H\u00050\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00020\bH\u0087\b\u001aF\u0010\r\u001a&\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\u0005H\u0005 \u000e*\u0012\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\u0005H\u0005\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u00010\u0001H\u0007\u001aF\u0010\r\u001a&\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\u0005H\u0005 \u000e*\u0012\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\u0005H\u0005\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u000f0\u0006H\u0007\u001a*\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0003*\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00050\u00060\u0001H\u0007\u001a*\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0003*\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00050\u00060\u0001H\u0007\u001aI\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00050\u00012\u001a\b\u0004\u0010\u0013\u001a\u0014\u0012\u0004\u0012\u0002H\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00140\bH\u0087\b\u001a*\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0003*\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00050\u00010\u0006H\u0007\u001aF\u0010\u0016\u001a&\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\u0005H\u0005 \u000e*\u0012\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\u0005H\u0005\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u00010\u0001H\u0007\u001a*\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0003*\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00050\u00010\u0006H\u0007\u001a#\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u0006\u0012\u0002\b\u00030\u0001H\u0087\b\u001aF\u0010\u0019\u001a&\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\u0005H\u0005 \u000e*\u0012\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\u0005H\u0005\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u00010\u0001H\u0007\u001a(\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u00010\u0001H\u0007\u001a'\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00050\u001c\"\b\b\u0000\u0010\u0005*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00050\u001dH\u0002¢\u0006\u0002\u0010\u001e\u001aÇ\u0001\u0010\u001f\u001a\u0096\u0001\u0012D\u0012B\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\"H\"\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H#H# \u000e* \u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\"H\"\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H#H#\u0018\u00010$0! \u000e*J\u0012D\u0012B\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\"H\"\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H#H# \u000e* \u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\"H\"\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H#H#\u0018\u00010$0!\u0018\u00010 0 \"\b\b\u0000\u0010\"*\u00020\u0003\"\b\b\u0001\u0010#*\u00020\u0003*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u0002H#0%0\u0001H\u0007\u001a¸\u0002\u0010&\u001a\u0087\u0002\u0012|\u0012z\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\"H\"\u0012(\u0012&\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H#H# \u000e*\u0012\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H#H#\u0018\u00010(0' \u000e*<\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\"H\"\u0012(\u0012&\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H#H# \u000e*\u0012\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H#H#\u0018\u00010(0'\u0018\u00010$0! \u000e*\u0082\u0001\u0012|\u0012z\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\"H\"\u0012(\u0012&\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H#H# \u000e*\u0012\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H#H#\u0018\u00010(0' \u000e*<\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\"H\"\u0012(\u0012&\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H#H# \u000e*\u0012\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H#H#\u0018\u00010(0'\u0018\u00010$0!\u0018\u00010 0 \"\b\b\u0000\u0010\"*\u00020\u0003\"\b\b\u0001\u0010#*\u00020\u0003*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u0002H#0%0\u0001H\u0007\u001a'\u0010)\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00050*H\u0007¢\u0006\u0002\u0010+\u001a\u0012\u0010)\u001a\b\u0012\u0004\u0012\u00020,0\u0001*\u00020-H\u0007\u001a\u0012\u0010)\u001a\b\u0012\u0004\u0012\u00020.0\u0001*\u00020/H\u0007\u001a\u0012\u0010)\u001a\b\u0012\u0004\u0012\u0002000\u0001*\u000201H\u0007\u001a\u0012\u0010)\u001a\b\u0012\u0004\u0012\u0002020\u0001*\u000203H\u0007\u001a\u0012\u0010)\u001a\b\u0012\u0004\u0012\u0002040\u0001*\u000205H\u0007\u001a\u0012\u0010)\u001a\b\u0012\u0004\u0012\u0002060\u0001*\u000207H\u0007\u001a\u0012\u0010)\u001a\b\u0012\u0004\u0012\u0002080\u0001*\u000209H\u0007\u001a\u0012\u0010)\u001a\b\u0012\u0004\u0012\u00020:0\u0001*\u00020;H\u0007\u001a\"\u0010)\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00050\u0006H\u0007\u001a\"\u0010)\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00050\u001dH\u0007\u001a\u0012\u0010)\u001a\b\u0012\u0004\u0012\u0002060\u0001*\u00020<H\u0007\u001a\"\u0010)\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00050\u0014H\u0007\u001a^\u0010=\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u00010\u00062)\b\u0004\u0010>\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002H\u00050\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00020\bH\u0087\b¨\u0006?"}, d2 = {"cast", "Lio/reactivex/Observable;", "R", "", "combineLatest", ExifInterface.GPS_DIRECTION_TRUE, "", "combineFunction", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "args", "concatAll", "kotlin.jvm.PlatformType", "Lio/reactivex/ObservableSource;", "concatMapIterable", "flatMapIterable", "flatMapSequence", TtmlNode.TAG_BODY, "Lkotlin/sequences/Sequence;", "merge", "mergeAll", "mergeDelayError", "ofType", "switchLatest", "switchOnNext", "toIterable", "io/reactivex/rxkotlin/ObservableKt$toIterable$1", "", "(Ljava/util/Iterator;)Lio/reactivex/rxkotlin/ObservableKt$toIterable$1;", "toMap", "Lio/reactivex/Single;", "", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "", "Lkotlin/Pair;", "toMultimap", "", "", "toObservable", "", "([Ljava/lang/Object;)Lio/reactivex/Observable;", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "Lkotlin/ranges/IntProgression;", "zip", "zipFunction", "rxkotlin"}, k = 2, mv = {1, 1, 11})
public final class ObservableKt {
    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final Observable<Boolean> toObservable(boolean[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toObservable(ArraysKt.asIterable(receiver));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final Observable<Byte> toObservable(byte[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toObservable(ArraysKt.asIterable(receiver));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final Observable<Character> toObservable(char[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toObservable(ArraysKt.asIterable(receiver));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final Observable<Short> toObservable(short[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toObservable(ArraysKt.asIterable(receiver));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final Observable<Integer> toObservable(int[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toObservable(ArraysKt.asIterable(receiver));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final Observable<Long> toObservable(long[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toObservable(ArraysKt.asIterable(receiver));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final Observable<Float> toObservable(float[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toObservable(ArraysKt.asIterable(receiver));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final Observable<Double> toObservable(double[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toObservable(ArraysKt.asIterable(receiver));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T> Observable<T> toObservable(T[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Observable<T> observableFromArray = Observable.fromArray(Arrays.copyOf(receiver, receiver.length));
        Intrinsics.checkExpressionValueIsNotNull(observableFromArray, "Observable.fromArray(*this)");
        return observableFromArray;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final Observable<Integer> toObservable(IntProgression receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.getStep() != 1 || ((long) receiver.getLast()) - ((long) receiver.getFirst()) >= Integer.MAX_VALUE) {
            Observable<Integer> observableFromIterable = Observable.fromIterable(receiver);
            Intrinsics.checkExpressionValueIsNotNull(observableFromIterable, "Observable.fromIterable(this)");
            return observableFromIterable;
        }
        Observable<Integer> observableRange = Observable.range(receiver.getFirst(), Math.max(0, (receiver.getLast() - receiver.getFirst()) + 1));
        Intrinsics.checkExpressionValueIsNotNull(observableRange, "Observable.range(first, …max(0, last - first + 1))");
        return observableRange;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T> Observable<T> toObservable(Iterator<? extends T> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toObservable(toIterable(receiver));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T> Observable<T> toObservable(Iterable<? extends T> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Observable<T> observableFromIterable = Observable.fromIterable(receiver);
        Intrinsics.checkExpressionValueIsNotNull(observableFromIterable, "Observable.fromIterable(this)");
        return observableFromIterable;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T> Observable<T> toObservable(Sequence<? extends T> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toObservable(SequencesKt.asIterable(receiver));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T> Observable<T> merge(Iterable<? extends Observable<? extends T>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Observable<T> observableMerge = Observable.merge(toObservable(receiver));
        Intrinsics.checkExpressionValueIsNotNull(observableMerge, "Observable.merge(this.toObservable())");
        return observableMerge;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T> Observable<T> mergeDelayError(Iterable<? extends Observable<? extends T>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Observable<T> observableMergeDelayError = Observable.mergeDelayError(toObservable(receiver));
        Intrinsics.checkExpressionValueIsNotNull(observableMergeDelayError, "Observable.mergeDelayError(this.toObservable())");
        return observableMergeDelayError;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T> Observable<T> flatMapIterable(Observable<? extends Iterable<? extends T>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Observable<T> observable = (Observable<T>) receiver.flatMapIterable(new Function<T, Iterable<? extends U>>() { // from class: io.reactivex.rxkotlin.ObservableKt.flatMapIterable.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.reactivex.functions.Function
            public final Iterable<T> apply(Iterable<? extends T> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observable, "flatMapIterable { it }");
        return observable;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T> Observable<T> concatMapIterable(Observable<? extends Iterable<? extends T>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Observable<T> observable = (Observable<T>) receiver.concatMapIterable(new Function<T, Iterable<? extends U>>() { // from class: io.reactivex.rxkotlin.ObservableKt.concatMapIterable.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.reactivex.functions.Function
            public final Iterable<T> apply(Iterable<? extends T> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observable, "concatMapIterable { it }");
        return observable;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T, R> Observable<R> flatMapSequence(Observable<T> receiver, final Function1<? super T, ? extends Sequence<? extends R>> body) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(body, "body");
        Observable<R> observableFlatMap = receiver.flatMap(new Function<T, ObservableSource<? extends R>>() { // from class: io.reactivex.rxkotlin.ObservableKt.flatMapSequence.1
            @Override // io.reactivex.functions.Function
            public final Observable<R> apply(T it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return ObservableKt.toObservable((Sequence) body.invoke(it));
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableFlatMap, "flatMap { body(it).toObservable() }");
        return observableFlatMap;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T, R> Observable<R> combineLatest(Iterable<? extends Observable<T>> receiver, final Function1<? super List<? extends T>, ? extends R> combineFunction) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(combineFunction, "combineFunction");
        Observable<R> observableCombineLatest = Observable.combineLatest(receiver, new Function<Object[], R>() { // from class: io.reactivex.rxkotlin.ObservableKt.combineLatest.1
            @Override // io.reactivex.functions.Function
            public final R apply(Object[] it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Function1 function1 = combineFunction;
                List listAsList = ArraysKt.asList(it);
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listAsList, 10));
                for (T t : listAsList) {
                    if (t == null) {
                        throw new TypeCastException("null cannot be cast to non-null type T");
                    }
                    arrayList.add(t);
                }
                return (R) function1.invoke(arrayList);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableCombineLatest, "Observable.combineLatest…List().map { it as T }) }");
        return observableCombineLatest;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T, R> Observable<R> zip(Iterable<? extends Observable<T>> receiver, final Function1<? super List<? extends T>, ? extends R> zipFunction) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(zipFunction, "zipFunction");
        Observable<R> observableZip = Observable.zip(receiver, new Function<Object[], R>() { // from class: io.reactivex.rxkotlin.ObservableKt.zip.1
            @Override // io.reactivex.functions.Function
            public final R apply(Object[] it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Function1 function1 = zipFunction;
                List listAsList = ArraysKt.asList(it);
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listAsList, 10));
                for (T t : listAsList) {
                    if (t == null) {
                        throw new TypeCastException("null cannot be cast to non-null type T");
                    }
                    arrayList.add(t);
                }
                return (R) function1.invoke(arrayList);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableZip, "Observable.zip(this) { z…List().map { it as T }) }");
        return observableZip;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    private static final <R> Observable<R> cast(Observable<?> observable) {
        Intrinsics.reifiedOperationMarker(4, "R");
        Observable<R> observable2 = (Observable<R>) observable.cast(Object.class);
        Intrinsics.checkExpressionValueIsNotNull(observable2, "cast(R::class.java)");
        return observable2;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    private static final <R> Observable<R> ofType(Observable<?> observable) {
        Intrinsics.reifiedOperationMarker(4, "R");
        Observable<R> observable2 = (Observable<R>) observable.ofType(Object.class);
        Intrinsics.checkExpressionValueIsNotNull(observable2, "ofType(R::class.java)");
        return observable2;
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: io.reactivex.rxkotlin.ObservableKt$toIterable$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: observable.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0011\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002¨\u0006\u0004"}, d2 = {"io/reactivex/rxkotlin/ObservableKt$toIterable$1", "", "iterator", "", "rxkotlin"}, k = 1, mv = {1, 1, 11})
    public static final class C06281<T> implements Iterable<T>, KMappedMarker {
        final /* synthetic */ Iterator receiver$0;

        C06281(Iterator<? extends T> it) {
            this.receiver$0 = it;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return this.receiver$0;
        }
    }

    private static final <T> C06281 toIterable(Iterator<? extends T> it) {
        return new C06281(it);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T> Observable<T> mergeAll(Observable<Observable<T>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (Observable<T>) receiver.flatMap(new Function<T, ObservableSource<? extends R>>() { // from class: io.reactivex.rxkotlin.ObservableKt.mergeAll.1
            @Override // io.reactivex.functions.Function
            public final Observable<T> apply(Observable<T> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it;
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T> Observable<T> concatAll(Observable<Observable<T>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (Observable<T>) receiver.concatMap(new Function<T, ObservableSource<? extends R>>() { // from class: io.reactivex.rxkotlin.ObservableKt.concatAll.1
            @Override // io.reactivex.functions.Function
            public final Observable<T> apply(Observable<T> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it;
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T> Observable<T> switchLatest(Observable<Observable<T>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (Observable<T>) receiver.switchMap(new Function<T, ObservableSource<? extends R>>() { // from class: io.reactivex.rxkotlin.ObservableKt.switchLatest.1
            @Override // io.reactivex.functions.Function
            public final Observable<T> apply(Observable<T> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it;
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T> Observable<T> switchOnNext(Observable<Observable<T>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Observable<T> observableSwitchOnNext = Observable.switchOnNext(receiver);
        Intrinsics.checkExpressionValueIsNotNull(observableSwitchOnNext, "Observable.switchOnNext(this)");
        return observableSwitchOnNext;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <A, B> Single<Map<A, B>> toMap(Observable<Pair<A, B>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (Single<Map<A, B>>) receiver.toMap(new Function<T, K>() { // from class: io.reactivex.rxkotlin.ObservableKt.toMap.1
            /* JADX WARN: Type inference failed for: r2v1, types: [A, java.lang.Object] */
            @Override // io.reactivex.functions.Function
            public final A apply(Pair<? extends A, ? extends B> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getFirst();
            }
        }, new Function<T, V>() { // from class: io.reactivex.rxkotlin.ObservableKt.toMap.2
            /* JADX WARN: Type inference failed for: r2v1, types: [B, java.lang.Object] */
            @Override // io.reactivex.functions.Function
            public final B apply(Pair<? extends A, ? extends B> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getSecond();
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <A, B> Single<Map<A, Collection<B>>> toMultimap(Observable<Pair<A, B>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (Single<Map<A, Collection<B>>>) receiver.toMultimap(new Function<T, K>() { // from class: io.reactivex.rxkotlin.ObservableKt.toMultimap.1
            /* JADX WARN: Type inference failed for: r2v1, types: [A, java.lang.Object] */
            @Override // io.reactivex.functions.Function
            public final A apply(Pair<? extends A, ? extends B> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getFirst();
            }
        }, new Function<T, V>() { // from class: io.reactivex.rxkotlin.ObservableKt.toMultimap.2
            /* JADX WARN: Type inference failed for: r2v1, types: [B, java.lang.Object] */
            @Override // io.reactivex.functions.Function
            public final B apply(Pair<? extends A, ? extends B> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getSecond();
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static final <T> Observable<T> concatAll(Iterable<? extends ObservableSource<T>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Observable.concat(receiver);
    }
}
