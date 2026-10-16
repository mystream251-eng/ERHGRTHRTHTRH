package io.reactivex.rxkotlin;

import androidx.exifinterface.media.ExifInterface;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.functions.BiFunction;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Singles.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\\\u0010\u0003\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b0\u00050\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00070\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\u0007Js\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\r0\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00070\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\b0\n2 \b\u0004\u0010\u000e\u001a\u001a\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\r0\u000fH\u0087\bJ\u008d\u0001\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\r0\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\u0010\"\u0004\b\u0004\u0010\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00070\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\b0\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00100\n2&\b\u0004\u0010\u000e\u001a \u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\r0\u0012H\u0087\bJ§\u0001\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\r0\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\u0010\"\u0004\b\u0004\u0010\u0013\"\u0004\b\u0005\u0010\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00070\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\b0\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00100\n2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00130\n2,\b\u0004\u0010\u000e\u001a&\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\r0\u0015H\u0087\bJÁ\u0001\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\r0\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\u0010\"\u0004\b\u0004\u0010\u0013\"\u0004\b\u0005\u0010\u0016\"\u0004\b\u0006\u0010\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00070\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\b0\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00100\n2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00130\n2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00160\n22\b\u0004\u0010\u000e\u001a,\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\r0\u0018H\u0087\bJÛ\u0001\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\r0\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\u0010\"\u0004\b\u0004\u0010\u0013\"\u0004\b\u0005\u0010\u0016\"\u0004\b\u0006\u0010\u0019\"\u0004\b\u0007\u0010\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00070\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\b0\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00100\n2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00130\n2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00160\n2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00190\n28\b\u0004\u0010\u000e\u001a2\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\r0\u001bH\u0087\bJõ\u0001\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\r0\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\u0010\"\u0004\b\u0004\u0010\u0013\"\u0004\b\u0005\u0010\u0016\"\u0004\b\u0006\u0010\u0019\"\u0004\b\u0007\u0010\u001c\"\u0004\b\b\u0010\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00070\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\b0\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00100\n2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00130\n2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00160\n2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00190\n2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u001c0\n2>\b\u0004\u0010\u000e\u001a8\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001c\u0012\u0004\u0012\u0002H\r0\u001eH\u0087\bJ\u008f\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\r0\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\u0010\"\u0004\b\u0004\u0010\u0013\"\u0004\b\u0005\u0010\u0016\"\u0004\b\u0006\u0010\u0019\"\u0004\b\u0007\u0010\u001c\"\u0004\b\b\u0010\u001f\"\u0004\b\t\u0010\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00070\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\b0\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00100\n2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00130\n2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00160\n2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00190\n2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u001c0\n2\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001f0\n2D\b\u0004\u0010\u000e\u001a>\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001c\u0012\u0004\u0012\u0002H\u001f\u0012\u0004\u0012\u0002H\r0!H\u0087\bJB\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u0002H$0\"0\u0004\"\u0004\b\u0000\u0010#\"\u0004\b\u0001\u0010$2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H#0\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H$0\nH\u0007JY\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\r0\u0004\"\u0004\b\u0000\u0010#\"\u0004\b\u0001\u0010$\"\u0004\b\u0002\u0010\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H#0\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H$0\n2\u001a\b\u0004\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H\r0%H\u0087\b¨\u0006&"}, d2 = {"Lio/reactivex/rxkotlin/Singles;", "", "()V", "zip", "Lio/reactivex/Single;", "Lkotlin/Triple;", "T1", "T2", "T3", "s1", "Lio/reactivex/SingleSource;", "s2", "s3", "R", "zipper", "Lkotlin/Function3;", "T4", "s4", "Lkotlin/Function4;", "T5", "s5", "Lkotlin/Function5;", "T6", "s6", "Lkotlin/Function6;", "T7", "s7", "Lkotlin/Function7;", "T8", "s8", "Lkotlin/Function8;", "T9", "s9", "Lkotlin/Function9;", "Lkotlin/Pair;", ExifInterface.GPS_DIRECTION_TRUE, "U", "Lkotlin/Function2;", "rxkotlin"}, k = 1, mv = {1, 1, 11})
public final class Singles {
    public static final Singles INSTANCE = new Singles();

    private Singles() {
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T, U, R> Single<R> zip(SingleSource<T> s1, SingleSource<U> s2, final Function2<? super T, ? super U, ? extends R> zipper) {
        Intrinsics.checkParameterIsNotNull(s1, "s1");
        Intrinsics.checkParameterIsNotNull(s2, "s2");
        Intrinsics.checkParameterIsNotNull(zipper, "zipper");
        Single<R> singleZip = Single.zip(s1, s2, new BiFunction<T, U, R>() { // from class: io.reactivex.rxkotlin.Singles.zip.1
            @Override // io.reactivex.functions.BiFunction
            public final R apply(T t, U u) {
                return (R) zipper.invoke(t, u);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleZip, "Single.zip(s1, s2, BiFun…-> zipper.invoke(t, u) })");
        return singleZip;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T, U> Single<Pair<T, U>> zip(SingleSource<T> s1, SingleSource<U> s2) {
        Intrinsics.checkParameterIsNotNull(s1, "s1");
        Intrinsics.checkParameterIsNotNull(s2, "s2");
        Single<Pair<T, U>> singleZip = Single.zip(s1, s2, new BiFunction<T, U, Pair<? extends T, ? extends U>>() { // from class: io.reactivex.rxkotlin.Singles.zip.2
            @Override // io.reactivex.functions.BiFunction
            public final Pair<T, U> apply(T t, U u) {
                return new Pair<>(t, u);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleZip, "Single.zip(s1, s2, BiFun…on { t, u -> Pair(t,u) })");
        return singleZip;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, R> Single<R> zip(SingleSource<T1> s1, SingleSource<T2> s2, SingleSource<T3> s3, final Function3<? super T1, ? super T2, ? super T3, ? extends R> zipper) {
        Intrinsics.checkParameterIsNotNull(s1, "s1");
        Intrinsics.checkParameterIsNotNull(s2, "s2");
        Intrinsics.checkParameterIsNotNull(s3, "s3");
        Intrinsics.checkParameterIsNotNull(zipper, "zipper");
        Single<R> singleZip = Single.zip(s1, s2, s3, new io.reactivex.functions.Function3<T1, T2, T3, R>() { // from class: io.reactivex.rxkotlin.Singles.zip.3
            @Override // io.reactivex.functions.Function3
            public final R apply(T1 t1, T2 t2, T3 t3) {
                return (R) zipper.invoke(t1, t2, t3);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleZip, "Single.zip(s1, s2, s3, F…per.invoke(t1, t2, t3) })");
        return singleZip;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3> Single<Triple<T1, T2, T3>> zip(SingleSource<T1> s1, SingleSource<T2> s2, SingleSource<T3> s3) {
        Intrinsics.checkParameterIsNotNull(s1, "s1");
        Intrinsics.checkParameterIsNotNull(s2, "s2");
        Intrinsics.checkParameterIsNotNull(s3, "s3");
        Single<Triple<T1, T2, T3>> singleZip = Single.zip(s1, s2, s3, new io.reactivex.functions.Function3<T1, T2, T3, Triple<? extends T1, ? extends T2, ? extends T3>>() { // from class: io.reactivex.rxkotlin.Singles.zip.4
            @Override // io.reactivex.functions.Function3
            public final Triple<T1, T2, T3> apply(T1 t1, T2 t2, T3 t3) {
                return new Triple<>(t1, t2, t3);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleZip, "Single.zip(s1, s2, s3, F…t3 -> Triple(t1,t2,t3) })");
        return singleZip;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, T4, R> Single<R> zip(SingleSource<T1> s1, SingleSource<T2> s2, SingleSource<T3> s3, SingleSource<T4> s4, final Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> zipper) {
        Intrinsics.checkParameterIsNotNull(s1, "s1");
        Intrinsics.checkParameterIsNotNull(s2, "s2");
        Intrinsics.checkParameterIsNotNull(s3, "s3");
        Intrinsics.checkParameterIsNotNull(s4, "s4");
        Intrinsics.checkParameterIsNotNull(zipper, "zipper");
        Single<R> singleZip = Single.zip(s1, s2, s3, s4, new io.reactivex.functions.Function4<T1, T2, T3, T4, R>() { // from class: io.reactivex.rxkotlin.Singles.zip.5
            @Override // io.reactivex.functions.Function4
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4) {
                return (R) zipper.invoke(t1, t2, t3, t4);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleZip, "Single.zip(s1, s2, s3, s…invoke(t1, t2, t3, t4) })");
        return singleZip;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, T4, T5, R> Single<R> zip(SingleSource<T1> s1, SingleSource<T2> s2, SingleSource<T3> s3, SingleSource<T4> s4, SingleSource<T5> s5, final Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> zipper) {
        Intrinsics.checkParameterIsNotNull(s1, "s1");
        Intrinsics.checkParameterIsNotNull(s2, "s2");
        Intrinsics.checkParameterIsNotNull(s3, "s3");
        Intrinsics.checkParameterIsNotNull(s4, "s4");
        Intrinsics.checkParameterIsNotNull(s5, "s5");
        Intrinsics.checkParameterIsNotNull(zipper, "zipper");
        Single<R> singleZip = Single.zip(s1, s2, s3, s4, s5, new io.reactivex.functions.Function5<T1, T2, T3, T4, T5, R>() { // from class: io.reactivex.rxkotlin.Singles.zip.6
            @Override // io.reactivex.functions.Function5
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
                return (R) zipper.invoke(t1, t2, t3, t4, t5);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleZip, "Single.zip(s1, s2, s3, s…ke(t1, t2, t3, t4, t5) })");
        return singleZip;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, T4, T5, T6, R> Single<R> zip(SingleSource<T1> s1, SingleSource<T2> s2, SingleSource<T3> s3, SingleSource<T4> s4, SingleSource<T5> s5, SingleSource<T6> s6, final Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> zipper) {
        Intrinsics.checkParameterIsNotNull(s1, "s1");
        Intrinsics.checkParameterIsNotNull(s2, "s2");
        Intrinsics.checkParameterIsNotNull(s3, "s3");
        Intrinsics.checkParameterIsNotNull(s4, "s4");
        Intrinsics.checkParameterIsNotNull(s5, "s5");
        Intrinsics.checkParameterIsNotNull(s6, "s6");
        Intrinsics.checkParameterIsNotNull(zipper, "zipper");
        Single<R> singleZip = Single.zip(s1, s2, s3, s4, s5, s6, new io.reactivex.functions.Function6<T1, T2, T3, T4, T5, T6, R>() { // from class: io.reactivex.rxkotlin.Singles.zip.7
            @Override // io.reactivex.functions.Function6
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
                return (R) zipper.invoke(t1, t2, t3, t4, t5, t6);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleZip, "Single.zip(s1, s2, s3, s…1, t2, t3, t4, t5, t6) })");
        return singleZip;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, T4, T5, T6, T7, R> Single<R> zip(SingleSource<T1> s1, SingleSource<T2> s2, SingleSource<T3> s3, SingleSource<T4> s4, SingleSource<T5> s5, SingleSource<T6> s6, SingleSource<T7> s7, final Function7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> zipper) {
        Intrinsics.checkParameterIsNotNull(s1, "s1");
        Intrinsics.checkParameterIsNotNull(s2, "s2");
        Intrinsics.checkParameterIsNotNull(s3, "s3");
        Intrinsics.checkParameterIsNotNull(s4, "s4");
        Intrinsics.checkParameterIsNotNull(s5, "s5");
        Intrinsics.checkParameterIsNotNull(s6, "s6");
        Intrinsics.checkParameterIsNotNull(s7, "s7");
        Intrinsics.checkParameterIsNotNull(zipper, "zipper");
        Single<R> singleZip = Single.zip(s1, s2, s3, s4, s5, s6, s7, new io.reactivex.functions.Function7<T1, T2, T3, T4, T5, T6, T7, R>() { // from class: io.reactivex.rxkotlin.Singles.zip.8
            @Override // io.reactivex.functions.Function7
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
                return (R) zipper.invoke(t1, t2, t3, t4, t5, t6, t7);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleZip, "Single.zip(s1, s2, s3, s…2, t3, t4, t5, t6, t7) })");
        return singleZip;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, T4, T5, T6, T7, T8, R> Single<R> zip(SingleSource<T1> s1, SingleSource<T2> s2, SingleSource<T3> s3, SingleSource<T4> s4, SingleSource<T5> s5, SingleSource<T6> s6, SingleSource<T7> s7, SingleSource<T8> s8, final Function8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> zipper) {
        Intrinsics.checkParameterIsNotNull(s1, "s1");
        Intrinsics.checkParameterIsNotNull(s2, "s2");
        Intrinsics.checkParameterIsNotNull(s3, "s3");
        Intrinsics.checkParameterIsNotNull(s4, "s4");
        Intrinsics.checkParameterIsNotNull(s5, "s5");
        Intrinsics.checkParameterIsNotNull(s6, "s6");
        Intrinsics.checkParameterIsNotNull(s7, "s7");
        Intrinsics.checkParameterIsNotNull(s8, "s8");
        Intrinsics.checkParameterIsNotNull(zipper, "zipper");
        Single<R> singleZip = Single.zip(s1, s2, s3, s4, s5, s6, s7, s8, new io.reactivex.functions.Function8<T1, T2, T3, T4, T5, T6, T7, T8, R>() { // from class: io.reactivex.rxkotlin.Singles.zip.9
            @Override // io.reactivex.functions.Function8
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
                return (R) zipper.invoke(t1, t2, t3, t4, t5, t6, t7, t8);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleZip, "Single.zip(s1, s2, s3, s…3, t4, t5, t6, t7, t8) })");
        return singleZip;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Single<R> zip(SingleSource<T1> s1, SingleSource<T2> s2, SingleSource<T3> s3, SingleSource<T4> s4, SingleSource<T5> s5, SingleSource<T6> s6, SingleSource<T7> s7, SingleSource<T8> s8, SingleSource<T9> s9, final Function9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> zipper) {
        Intrinsics.checkParameterIsNotNull(s1, "s1");
        Intrinsics.checkParameterIsNotNull(s2, "s2");
        Intrinsics.checkParameterIsNotNull(s3, "s3");
        Intrinsics.checkParameterIsNotNull(s4, "s4");
        Intrinsics.checkParameterIsNotNull(s5, "s5");
        Intrinsics.checkParameterIsNotNull(s6, "s6");
        Intrinsics.checkParameterIsNotNull(s7, "s7");
        Intrinsics.checkParameterIsNotNull(s8, "s8");
        Intrinsics.checkParameterIsNotNull(s9, "s9");
        Intrinsics.checkParameterIsNotNull(zipper, "zipper");
        Single<R> singleZip = Single.zip(s1, s2, s3, s4, s5, s6, s7, s8, s9, new io.reactivex.functions.Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R>() { // from class: io.reactivex.rxkotlin.Singles.zip.10
            @Override // io.reactivex.functions.Function9
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return (R) zipper.invoke(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleZip, "Single.zip(s1, s2, s3, s…4, t5, t6, t7, t8, t9) })");
        return singleZip;
    }
}
