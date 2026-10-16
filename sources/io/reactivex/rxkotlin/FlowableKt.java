package io.reactivex.rxkotlin;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.annotations.BackpressureKind;
import io.reactivex.annotations.BackpressureSupport;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntProgression;
import kotlin.reflect.KDeclarationContainer;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.reactivestreams.Publisher;

/* JADX INFO: compiled from: flowable.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000¹\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0018\n\u0002\u0010\u0005\n\u0002\u0010\u0012\n\u0002\u0010\f\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0013\n\u0002\u0010\u0007\n\u0002\u0010\u0014\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\u0010\t\n\u0002\u0010\u0016\n\u0002\u0010\n\n\u0002\u0010\u0017\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\u001f\n\u0002\u0010\u001e\n\u0002\b\u0003*\u00015\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u0006\u0012\u0002\b\u00030\u0001H\u0087\b\u001aF\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00020\u00050\u0001\"\b\b\u0000\u0010\u0006*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00060\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0007\u001ad\u0010\u0004\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\b0\u0001\"\b\b\u0000\u0010\u0006*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u00020\u0003\"\b\b\u0002\u0010\t*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00060\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\u0001H\u0007\u001a^\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0006*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00010\f2)\b\u0004\u0010\r\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002H\u00060\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u0002H\u00020\u000eH\u0087\b\u001aF\u0010\u0013\u001a&\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H\u0006H\u0006 \u0014*\u0012\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H\u0006H\u0006\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0006*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00010\u0001H\u0007\u001aF\u0010\u0013\u001a&\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H\u0006H\u0006 \u0014*\u0012\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H\u0006H\u0006\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0006*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00150\fH\u0007\u001aI\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0006*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00060\u00012\u001a\b\u0004\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u0002H\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00180\u000eH\u0087\b\u001a*\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0001\"\b\b\u0000\u0010\u0006*\u00020\u0003*\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00060\u00010\fH\u0007\u001aF\u0010\u001a\u001a&\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H\u0006H\u0006 \u0014*\u0012\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H\u0006H\u0006\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0006*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00010\u0001H\u0007\u001a*\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0001\"\b\b\u0000\u0010\u0006*\u00020\u0003*\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00060\u00010\fH\u0007\u001a#\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u0006\u0012\u0002\b\u00030\u0001H\u0087\b\u001aF\u0010\u001d\u001a&\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H\u0006H\u0006 \u0014*\u0012\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H\u0006H\u0006\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0006*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00010\u0001H\u0007\u001a(\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0001\"\b\b\u0000\u0010\u0006*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00010\u0001H\u0007\u001a'\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0001\"\b\b\u0000\u0010\u0006*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00060 H\u0007¢\u0006\u0002\u0010!\u001a\u0012\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\"0\u0001*\u00020#H\u0007\u001a\u0012\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020$0\u0001*\u00020%H\u0007\u001a\u0012\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020&0\u0001*\u00020'H\u0007\u001a\u0012\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020(0\u0001*\u00020)H\u0007\u001a\u0012\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020*0\u0001*\u00020+H\u0007\u001a\u0012\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020,0\u0001*\u00020-H\u0007\u001a\u0012\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020.0\u0001*\u00020/H\u0007\u001a\u0012\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002000\u0001*\u000201H\u0007\u001a\"\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0001\"\b\b\u0000\u0010\u0006*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00060\fH\u0007\u001a \u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0001\"\b\b\u0000\u0010\u0006*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u000602\u001a\u0012\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020,0\u0001*\u000203H\u0007\u001a \u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0001\"\b\b\u0000\u0010\u0006*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00060\u0018\u001a'\u00104\u001a\b\u0012\u0004\u0012\u0002H\u000605\"\b\b\u0000\u0010\u0006*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u000602H\u0002¢\u0006\u0002\u00106\u001aÇ\u0001\u00107\u001a\u0096\u0001\u0012D\u0012B\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H:H:\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H;H; \u0014* \u0012\f\u0012\n \u0014*\u0004\u0018\u0001H:H:\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H;H;\u0018\u00010<09 \u0014*J\u0012D\u0012B\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H:H:\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H;H; \u0014* \u0012\f\u0012\n \u0014*\u0004\u0018\u0001H:H:\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H;H;\u0018\u00010<09\u0018\u00010808\"\b\b\u0000\u0010:*\u00020\u0003\"\b\b\u0001\u0010;*\u00020\u0003*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H:\u0012\u0004\u0012\u0002H;0\u00050\u0001H\u0007\u001a¸\u0002\u0010=\u001a\u0087\u0002\u0012|\u0012z\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H:H:\u0012(\u0012&\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H;H; \u0014*\u0012\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H;H;\u0018\u00010?0> \u0014*<\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H:H:\u0012(\u0012&\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H;H; \u0014*\u0012\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H;H;\u0018\u00010?0>\u0018\u00010<09 \u0014*\u0082\u0001\u0012|\u0012z\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H:H:\u0012(\u0012&\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H;H; \u0014*\u0012\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H;H;\u0018\u00010?0> \u0014*<\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H:H:\u0012(\u0012&\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H;H; \u0014*\u0012\u0012\f\u0012\n \u0014*\u0004\u0018\u0001H;H;\u0018\u00010?0>\u0018\u00010<09\u0018\u00010808\"\b\b\u0000\u0010:*\u00020\u0003\"\b\b\u0001\u0010;*\u00020\u0003*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H:\u0012\u0004\u0012\u0002H;0\u00050\u0001H\u0007\u001a^\u0010@\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0006*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00010\f2)\b\u0004\u0010A\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002H\u00060\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u0002H\u00020\u000eH\u0087\b¨\u0006B"}, d2 = {"cast", "Lio/reactivex/Flowable;", "R", "", "combineLatest", "Lkotlin/Pair;", ExifInterface.GPS_DIRECTION_TRUE, "flowable", "Lkotlin/Triple;", "U", "flowable1", "flowable2", "", "combineFunction", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "args", "concatAll", "kotlin.jvm.PlatformType", "Lorg/reactivestreams/Publisher;", "flatMapSequence", TtmlNode.TAG_BODY, "Lkotlin/sequences/Sequence;", "merge", "mergeAll", "mergeDelayError", "ofType", "switchLatest", "switchOnNext", "toFlowable", "", "([Ljava/lang/Object;)Lio/reactivex/Flowable;", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "Lkotlin/ranges/IntProgression;", "toIterable", "io/reactivex/rxkotlin/FlowableKt$toIterable$1", "(Ljava/util/Iterator;)Lio/reactivex/rxkotlin/FlowableKt$toIterable$1;", "toMap", "Lio/reactivex/Single;", "", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "", "toMultimap", "", "", "zip", "zipFunction", "rxkotlin"}, k = 2, mv = {1, 1, 11})
public final class FlowableKt {
    @CheckReturnValue
    public static final Flowable<Boolean> toFlowable(boolean[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toFlowable(ArraysKt.asIterable(receiver));
    }

    @CheckReturnValue
    public static final Flowable<Byte> toFlowable(byte[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toFlowable(ArraysKt.asIterable(receiver));
    }

    @CheckReturnValue
    public static final Flowable<Character> toFlowable(char[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toFlowable(ArraysKt.asIterable(receiver));
    }

    @CheckReturnValue
    public static final Flowable<Short> toFlowable(short[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toFlowable(ArraysKt.asIterable(receiver));
    }

    @CheckReturnValue
    public static final Flowable<Integer> toFlowable(int[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toFlowable(ArraysKt.asIterable(receiver));
    }

    @CheckReturnValue
    public static final Flowable<Long> toFlowable(long[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toFlowable(ArraysKt.asIterable(receiver));
    }

    @CheckReturnValue
    public static final Flowable<Float> toFlowable(float[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toFlowable(ArraysKt.asIterable(receiver));
    }

    @CheckReturnValue
    public static final Flowable<Double> toFlowable(double[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toFlowable(ArraysKt.asIterable(receiver));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static final <T> Flowable<T> toFlowable(T[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Flowable<T> flowableFromArray = Flowable.fromArray(Arrays.copyOf(receiver, receiver.length));
        Intrinsics.checkExpressionValueIsNotNull(flowableFromArray, "Flowable.fromArray(*this)");
        return flowableFromArray;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static final Flowable<Integer> toFlowable(IntProgression receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.getStep() != 1 || ((long) receiver.getLast()) - ((long) receiver.getFirst()) >= Integer.MAX_VALUE) {
            Flowable<Integer> flowableFromIterable = Flowable.fromIterable(receiver);
            Intrinsics.checkExpressionValueIsNotNull(flowableFromIterable, "Flowable.fromIterable(this)");
            return flowableFromIterable;
        }
        Flowable<Integer> flowableRange = Flowable.range(receiver.getFirst(), Math.max(0, (receiver.getLast() - receiver.getFirst()) + 1));
        Intrinsics.checkExpressionValueIsNotNull(flowableRange, "Flowable.range(first, Ma…max(0, last - first + 1))");
        return flowableRange;
    }

    public static final <T> Flowable<T> toFlowable(Iterator<? extends T> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toFlowable(toIterable(receiver));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static final <T> Flowable<T> toFlowable(Iterable<? extends T> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Flowable<T> flowableFromIterable = Flowable.fromIterable(receiver);
        Intrinsics.checkExpressionValueIsNotNull(flowableFromIterable, "Flowable.fromIterable(this)");
        return flowableFromIterable;
    }

    public static final <T> Flowable<T> toFlowable(Sequence<? extends T> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return toFlowable(SequencesKt.asIterable(receiver));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static final <T> Flowable<T> merge(Iterable<? extends Flowable<? extends T>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Flowable<T> flowableMerge = Flowable.merge(toFlowable(receiver));
        Intrinsics.checkExpressionValueIsNotNull(flowableMerge, "Flowable.merge(this.toFlowable())");
        return flowableMerge;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static final <T> Flowable<T> mergeDelayError(Iterable<? extends Flowable<? extends T>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Flowable<T> flowableMergeDelayError = Flowable.mergeDelayError(toFlowable(receiver));
        Intrinsics.checkExpressionValueIsNotNull(flowableMergeDelayError, "Flowable.mergeDelayError(this.toFlowable())");
        return flowableMergeDelayError;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static final <T, R> Flowable<R> flatMapSequence(Flowable<T> receiver, final Function1<? super T, ? extends Sequence<? extends R>> body) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(body, "body");
        Flowable<R> flowableFlatMap = receiver.flatMap(new Function<T, Publisher<? extends R>>() { // from class: io.reactivex.rxkotlin.FlowableKt.flatMapSequence.1
            @Override // io.reactivex.functions.Function
            public final Flowable<R> apply(T it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return FlowableKt.toFlowable((Sequence) body.invoke(it));
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(flowableFlatMap, "flatMap { body(it).toFlowable() }");
        return flowableFlatMap;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static final <T, R> Flowable<R> combineLatest(Iterable<? extends Flowable<T>> receiver, final Function1<? super List<? extends T>, ? extends R> combineFunction) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(combineFunction, "combineFunction");
        Flowable<R> flowableCombineLatest = Flowable.combineLatest(receiver, new Function<Object[], R>() { // from class: io.reactivex.rxkotlin.FlowableKt.combineLatest.1
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
        Intrinsics.checkExpressionValueIsNotNull(flowableCombineLatest, "Flowable.combineLatest(t…List().map { it as T }) }");
        return flowableCombineLatest;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static final <T, R> Flowable<R> zip(Iterable<? extends Flowable<T>> receiver, final Function1<? super List<? extends T>, ? extends R> zipFunction) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(zipFunction, "zipFunction");
        Flowable<R> flowableZip = Flowable.zip(receiver, new Function<Object[], R>() { // from class: io.reactivex.rxkotlin.FlowableKt.zip.1
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
        Intrinsics.checkExpressionValueIsNotNull(flowableZip, "Flowable.zip(this) { zip…List().map { it as T }) }");
        return flowableZip;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    private static final <R> Flowable<R> cast(Flowable<?> flowable) {
        Intrinsics.reifiedOperationMarker(4, "R");
        Flowable<R> flowable2 = (Flowable<R>) flowable.cast(Object.class);
        Intrinsics.checkExpressionValueIsNotNull(flowable2, "cast(R::class.java)");
        return flowable2;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    private static final <R> Flowable<R> ofType(Flowable<?> flowable) {
        Intrinsics.reifiedOperationMarker(4, "R");
        Flowable<R> flowable2 = (Flowable<R>) flowable.ofType(Object.class);
        Intrinsics.checkExpressionValueIsNotNull(flowable2, "ofType(R::class.java)");
        return flowable2;
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: io.reactivex.rxkotlin.FlowableKt$toIterable$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: flowable.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0011\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002¨\u0006\u0004"}, d2 = {"io/reactivex/rxkotlin/FlowableKt$toIterable$1", "", "iterator", "", "rxkotlin"}, k = 1, mv = {1, 1, 11})
    public static final class C06031<T> implements Iterable<T>, KMappedMarker {
        final /* synthetic */ Iterator receiver$0;

        C06031(Iterator<? extends T> it) {
            this.receiver$0 = it;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return this.receiver$0;
        }
    }

    private static final <T> C06031 toIterable(Iterator<? extends T> it) {
        return new C06031(it);
    }

    /* JADX INFO: Add missing generic type declarations: [R, T] */
    /* JADX INFO: renamed from: io.reactivex.rxkotlin.FlowableKt$combineLatest$2, reason: invalid class name */
    /* JADX INFO: compiled from: flowable.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u00042\u0015\u0010\u0005\u001a\u0011H\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b2\u0015\u0010\t\u001a\u0011H\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n¢\u0006\u0004\b\u000b\u0010\f"}, d2 = {"<anonymous>", "Lkotlin/Pair;", ExifInterface.GPS_DIRECTION_TRUE, "R", "", "p1", "Lkotlin/ParameterName;", "name", "first", "p2", "second", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;"}, k = 3, mv = {1, 1, 11})
    static final class AnonymousClass2<R, T> extends FunctionReference implements Function2<T, R, Pair<? extends T, ? extends R>> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        AnonymousClass2() {
            super(2);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "<init>";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(Pair.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "<init>(Ljava/lang/Object;Ljava/lang/Object;)V";
        }

        @Override // kotlin.jvm.functions.Function2
        public final Pair<T, R> invoke(T p1, R p2) {
            Intrinsics.checkParameterIsNotNull(p1, "p1");
            Intrinsics.checkParameterIsNotNull(p2, "p2");
            return new Pair<>(p1, p2);
        }
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static final <T, R> Flowable<Pair<T, R>> combineLatest(Flowable<T> receiver, Flowable<R> flowable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(flowable, "flowable");
        Flowable<T> flowable2 = receiver;
        Flowable<R> flowable3 = flowable;
        final AnonymousClass2 anonymousClass2 = AnonymousClass2.INSTANCE;
        Object obj = anonymousClass2;
        if (anonymousClass2 != null) {
            obj = new BiFunction() { // from class: io.reactivex.rxkotlin.FlowableKt$sam$io_reactivex_functions_BiFunction$0
                /* JADX WARN: Type inference failed for: r2v1, types: [R, java.lang.Object] */
                @Override // io.reactivex.functions.BiFunction
                public final /* synthetic */ R apply(T1 t1, T2 t2) {
                    return anonymousClass2.invoke(t1, t2);
                }
            };
        }
        Flowable<Pair<T, R>> flowableCombineLatest = Flowable.combineLatest(flowable2, flowable3, (BiFunction) obj);
        Intrinsics.checkExpressionValueIsNotNull(flowableCombineLatest, "Flowable.combineLatest(t…able, BiFunction(::Pair))");
        return flowableCombineLatest;
    }

    /* JADX INFO: Add missing generic type declarations: [R, T, U] */
    /* JADX INFO: renamed from: io.reactivex.rxkotlin.FlowableKt$combineLatest$3, reason: invalid class name */
    /* JADX INFO: compiled from: flowable.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0010\u0000\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0005\"\b\b\u0001\u0010\u0003*\u00020\u0005\"\b\b\u0002\u0010\u0004*\u00020\u00052\u0015\u0010\u0006\u001a\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t2\u0015\u0010\n\u001a\u0011H\u0003¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b2\u0015\u0010\f\u001a\u0011H\u0004¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r¢\u0006\u0004\b\u000e\u0010\u000f"}, d2 = {"<anonymous>", "Lkotlin/Triple;", ExifInterface.GPS_DIRECTION_TRUE, "R", "U", "", "p1", "Lkotlin/ParameterName;", "name", "first", "p2", "second", "p3", "third", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Triple;"}, k = 3, mv = {1, 1, 11})
    static final class AnonymousClass3<R, T, U> extends FunctionReference implements Function3<T, R, U, Triple<? extends T, ? extends R, ? extends U>> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        AnonymousClass3() {
            super(3);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "<init>";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(Triple.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "<init>(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V";
        }

        @Override // kotlin.jvm.functions.Function3
        public final Triple<T, R, U> invoke(T p1, R p2, U p3) {
            Intrinsics.checkParameterIsNotNull(p1, "p1");
            Intrinsics.checkParameterIsNotNull(p2, "p2");
            Intrinsics.checkParameterIsNotNull(p3, "p3");
            return new Triple<>(p1, p2, p3);
        }
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static final <T, R, U> Flowable<Triple<T, R, U>> combineLatest(Flowable<T> receiver, Flowable<R> flowable1, Flowable<U> flowable2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(flowable1, "flowable1");
        Intrinsics.checkParameterIsNotNull(flowable2, "flowable2");
        Flowable<T> flowable = receiver;
        Flowable<R> flowable3 = flowable1;
        Flowable<U> flowable4 = flowable2;
        final AnonymousClass3 anonymousClass3 = AnonymousClass3.INSTANCE;
        Object obj = anonymousClass3;
        if (anonymousClass3 != null) {
            obj = new io.reactivex.functions.Function3() { // from class: io.reactivex.rxkotlin.FlowableKt$sam$io_reactivex_functions_Function3$0
                /* JADX WARN: Type inference failed for: r2v1, types: [R, java.lang.Object] */
                @Override // io.reactivex.functions.Function3
                public final /* synthetic */ R apply(T1 t1, T2 t2, T3 t3) {
                    return anonymousClass3.invoke(t1, t2, t3);
                }
            };
        }
        Flowable<Triple<T, R, U>> flowableCombineLatest = Flowable.combineLatest(flowable, flowable3, flowable4, (io.reactivex.functions.Function3) obj);
        Intrinsics.checkExpressionValueIsNotNull(flowableCombineLatest, "Flowable.combineLatest(t…le2, Function3(::Triple))");
        return flowableCombineLatest;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static final <T> Flowable<T> mergeAll(Flowable<Flowable<T>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (Flowable<T>) receiver.flatMap(new Function<T, Publisher<? extends R>>() { // from class: io.reactivex.rxkotlin.FlowableKt.mergeAll.1
            @Override // io.reactivex.functions.Function
            public final Flowable<T> apply(Flowable<T> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it;
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static final <T> Flowable<T> concatAll(Flowable<Flowable<T>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (Flowable<T>) receiver.concatMap(new Function<T, Publisher<? extends R>>() { // from class: io.reactivex.rxkotlin.FlowableKt.concatAll.1
            @Override // io.reactivex.functions.Function
            public final Flowable<T> apply(Flowable<T> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it;
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static final <T> Flowable<T> switchLatest(Flowable<Flowable<T>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (Flowable<T>) receiver.switchMap(new Function<T, Publisher<? extends R>>() { // from class: io.reactivex.rxkotlin.FlowableKt.switchLatest.1
            @Override // io.reactivex.functions.Function
            public final Flowable<T> apply(Flowable<T> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it;
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static final <T> Flowable<T> switchOnNext(Flowable<Flowable<T>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Flowable<T> flowableSwitchOnNext = Flowable.switchOnNext(receiver);
        Intrinsics.checkExpressionValueIsNotNull(flowableSwitchOnNext, "Flowable.switchOnNext(this)");
        return flowableSwitchOnNext;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    public static final <A, B> Single<Map<A, B>> toMap(Flowable<Pair<A, B>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (Single<Map<A, B>>) receiver.toMap(new Function<T, K>() { // from class: io.reactivex.rxkotlin.FlowableKt.toMap.1
            /* JADX WARN: Type inference failed for: r2v1, types: [A, java.lang.Object] */
            @Override // io.reactivex.functions.Function
            public final A apply(Pair<? extends A, ? extends B> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getFirst();
            }
        }, new Function<T, V>() { // from class: io.reactivex.rxkotlin.FlowableKt.toMap.2
            /* JADX WARN: Type inference failed for: r2v1, types: [B, java.lang.Object] */
            @Override // io.reactivex.functions.Function
            public final B apply(Pair<? extends A, ? extends B> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getSecond();
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    public static final <A, B> Single<Map<A, Collection<B>>> toMultimap(Flowable<Pair<A, B>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (Single<Map<A, Collection<B>>>) receiver.toMultimap(new Function<T, K>() { // from class: io.reactivex.rxkotlin.FlowableKt.toMultimap.1
            /* JADX WARN: Type inference failed for: r2v1, types: [A, java.lang.Object] */
            @Override // io.reactivex.functions.Function
            public final A apply(Pair<? extends A, ? extends B> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getFirst();
            }
        }, new Function<T, V>() { // from class: io.reactivex.rxkotlin.FlowableKt.toMultimap.2
            /* JADX WARN: Type inference failed for: r2v1, types: [B, java.lang.Object] */
            @Override // io.reactivex.functions.Function
            public final B apply(Pair<? extends A, ? extends B> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getSecond();
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static final <T> Flowable<T> concatAll(Iterable<? extends Publisher<T>> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Flowable.concat(receiver);
    }
}
