package kotlinx.coroutines.internal;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DispatchedTaskKt;
import kotlinx.coroutines.DisposableHandle;

/* JADX INFO: compiled from: Sharing.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u001b\u0010\u0000\u001a\u00020\u00012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003H\u0081\b\u001a\t\u0010\u0005\u001a\u00020\u0006H\u0081\b\u001aT\u0010\u0007\u001a\u00020\u0001\"\u0004\b\u0000\u0010\b*\u0012\u0012\u0004\u0012\u0002H\b0\tj\b\u0012\u0004\u0012\u0002H\b`\n2\u0006\u0010\u000b\u001a\u0002H\b2\"\u0010\f\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u0002H\b0\tj\b\u0012\u0004\u0012\u0002H\b`\n\u0012\u0004\u0012\u00020\u00010\rH\u0081\b¢\u0006\u0002\u0010\u000e\u001a\\\u0010\u0007\u001a\u00020\u0001\"\u0004\b\u0000\u0010\b*\u0012\u0012\u0004\u0012\u0002H\b0\tj\b\u0012\u0004\u0012\u0002H\b`\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u0002H\b2\"\u0010\f\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u0002H\b0\tj\b\u0012\u0004\u0012\u0002H\b`\n\u0012\u0004\u0012\u00020\u00010\rH\u0081\b¢\u0006\u0002\u0010\u0011\u001a\u001f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\b0\u0004\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u0004H\u0081\b\u001a!\u0010\u0013\u001a\n\u0012\u0004\u0012\u0002H\b\u0018\u00010\u0004\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u0004H\u0081\b\u001a \u0010\u0014\u001a\n\u0012\u0004\u0012\u0002H\b\u0018\u00010\u0004\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u0004H\u0001\u001a#\u0010\u0015\u001a\u00020\u0016\"\u0010\b\u0000\u0010\b*\u00020\u0016*\u00060\u0017j\u0002`\u0018*\u0002H\bH\u0001¢\u0006\u0002\u0010\u0019\u001a\u001f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\b0\u0004\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u0004H\u0081\b\u001a\r\u0010\u0015\u001a\u00020\u001a*\u00020\u001aH\u0081\b\u001aY\u0010\u0015\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001c0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u001b\"\u0004\b\u0000\u0010\b\"\u0004\b\u0001\u0010\u001c*\u001e\b\u0001\u0012\u0004\u0012\u0002H\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001c0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u001bH\u0081\bø\u0001\u0000¢\u0006\u0002\u0010\u001d\u001a\u0010\u0010\u001e\u001a\u00020\u0001*\u00060\u0017j\u0002`\u0018H\u0001\u001a/\u0010\u001f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00042\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\b0!H\u0081\bø\u0001\u0000¢\u0006\u0002\u0010\"\u001a/\u0010#\u001a\u00020\u0001\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00042\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\b0!H\u0081\bø\u0001\u0000¢\u0006\u0002\u0010\"\u001a/\u0010$\u001a\u00020\u0001\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0%2\f\u0010&\u001a\b\u0012\u0004\u0012\u0002H\b0\u00042\u0006\u0010'\u001a\u00020\u0006H\u0081\b\u001a\u0011\u0010(\u001a\u0004\u0018\u00010\u0017*\u0004\u0018\u00010\u0017H\u0081\b\u001a\u001f\u0010)\u001a\b\u0012\u0004\u0012\u0002H\b0\u0004\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u0004H\u0081\b\u001a\r\u0010*\u001a\u00020\u0017*\u00020\u0017H\u0081\b*\f\b\u0000\u0010+\"\u00020\u00172\u00020\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, d2 = {"disposeContinuation", "", "cont", "Lkotlin/Function0;", "Lkotlin/coroutines/Continuation;", "isReuseSupportedInPlatform", "", "addOrUpdate", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "element", "update", "Lkotlin/Function1;", "(Ljava/util/ArrayList;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "index", "", "(Ljava/util/ArrayList;ILjava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "asLocal", "asLocalOrNull", "asLocalOrNullIfNotUsed", "asShareable", "Lkotlinx/coroutines/DisposableHandle;", "", "Lkotlinx/coroutines/internal/ShareableRefHolder;", "(Ljava/lang/Object;)Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlin/Function2;", "R", "(Lkotlin/jvm/functions/Function2;)Lkotlin/jvm/functions/Function2;", "disposeSharedRef", "shareableInterceptedResumeCancellableWith", "result", "Lkotlin/Result;", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;)V", "shareableInterceptedResumeWith", "shareableResume", "Lkotlinx/coroutines/CancellableContinuationImpl;", "delegate", "undispatched", "unweakRef", "useLocal", "weakRef", "ShareableRefHolder", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
public final class SharingKt {
    public static /* synthetic */ void ShareableRefHolder$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> Continuation<T> asLocal(Continuation<? super T> continuation) {
        return continuation;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> Continuation<T> asLocalOrNull(Continuation<? super T> continuation) {
        return continuation;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Continuation<T> asLocalOrNullIfNotUsed(Continuation<? super T> continuation) {
        return continuation;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> Continuation<T> asShareable(Continuation<? super T> continuation) {
        return continuation;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T, R> Function2<T, Continuation<? super R>, Object> asShareable(Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        return function2;
    }

    private static final CoroutineDispatcher asShareable(CoroutineDispatcher coroutineDispatcher) {
        return coroutineDispatcher;
    }

    private static final void disposeContinuation(Function0<? extends Continuation<?>> function0) {
    }

    public static final void disposeSharedRef(Object obj) {
    }

    private static final boolean isReuseSupportedInPlatform() {
        return true;
    }

    private static final Object unweakRef(Object obj) {
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> Continuation<T> useLocal(Continuation<? super T> continuation) {
        return continuation;
    }

    private static final Object weakRef(Object obj) {
        return obj;
    }

    public static final <T extends DisposableHandle> DisposableHandle asShareable(T t) {
        return t;
    }

    private static final <T> void shareableInterceptedResumeCancellableWith(Continuation<? super T> continuation, Object obj) {
        DispatchedContinuationKt.resumeCancellableWith$default(IntrinsicsKt.intercepted(continuation), obj, null, 2, null);
    }

    private static final <T> void shareableInterceptedResumeWith(Continuation<? super T> continuation, Object obj) {
        IntrinsicsKt.intercepted(continuation).resumeWith(obj);
    }

    private static final <T> void shareableResume(CancellableContinuationImpl<? super T> cancellableContinuationImpl, Continuation<? super T> continuation, boolean z) {
        DispatchedTaskKt.resume(cancellableContinuationImpl, continuation, z);
    }

    private static final <T> void addOrUpdate(ArrayList<T> arrayList, T t, Function1<? super ArrayList<T>, Unit> function1) {
        arrayList.add(t);
    }

    private static final <T> void addOrUpdate(ArrayList<T> arrayList, int i, T t, Function1<? super ArrayList<T>, Unit> function1) {
        arrayList.add(i, t);
    }
}
