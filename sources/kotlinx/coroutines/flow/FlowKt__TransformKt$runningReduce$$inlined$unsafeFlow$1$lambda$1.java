package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: Collect.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__TransformKt$$special$$inlined$collect$10"}, k = 1, mv = {1, 4, 0})
public final class FlowKt__TransformKt$runningReduce$$inlined$unsafeFlow$1$lambda$1<T> implements FlowCollector<T> {
    final /* synthetic */ Ref.ObjectRef $accumulator$inlined;
    final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
    final /* synthetic */ FlowKt__TransformKt$runningReduce$$inlined$unsafeFlow$1 this$0;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$runningReduce$$inlined$unsafeFlow$1$lambda$1$1, reason: invalid class name */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@¨\u0006\b"}, d2 = {"emit", "", ExifInterface.GPS_DIRECTION_TRUE, "value", "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3$emit$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$$special$$inlined$collect$10$1"}, k = 3, mv = {1, 4, 0})
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__TransformKt$runningReduce$$inlined$unsafeFlow$1$lambda$1.this.emit(null, this);
        }
    }

    public FlowKt__TransformKt$runningReduce$$inlined$unsafeFlow$1$lambda$1(FlowCollector flowCollector, Ref.ObjectRef objectRef, FlowKt__TransformKt$runningReduce$$inlined$unsafeFlow$1 flowKt__TransformKt$runningReduce$$inlined$unsafeFlow$1) {
        this.$this_unsafeFlow$inlined = flowCollector;
        this.$accumulator$inlined = objectRef;
        this.this$0 = flowKt__TransformKt$runningReduce$$inlined$unsafeFlow$1;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object emit(Object obj, Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        Ref.ObjectRef objectRef;
        Continuation continuation2;
        Object obj2;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj3 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj3);
            AnonymousClass1 anonymousClass2 = anonymousClass1;
            objectRef = this.$accumulator$inlined;
            if (objectRef.element != NullSurrogateKt.NULL) {
                Function3 function3 = this.this$0.$operation$inlined;
                T t = this.$accumulator$inlined.element;
                anonymousClass1.L$0 = this;
                anonymousClass1.L$1 = obj;
                anonymousClass1.L$2 = anonymousClass2;
                anonymousClass1.L$3 = obj;
                anonymousClass1.L$4 = objectRef;
                anonymousClass1.label = 1;
                InlineMarker.mark(6);
                Object objInvoke = function3.invoke(t, obj, anonymousClass1);
                InlineMarker.mark(7);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = objInvoke;
            }
            continuation2 = anonymousClass2;
            obj2 = obj;
        } else {
            if (i == 1) {
                Ref.ObjectRef objectRef2 = (Ref.ObjectRef) anonymousClass1.L$4;
                Object obj4 = anonymousClass1.L$3;
                continuation2 = (Continuation) anonymousClass1.L$2;
                obj = anonymousClass1.L$1;
                this = (FlowKt__TransformKt$runningReduce$$inlined$unsafeFlow$1$lambda$1) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj3);
                objectRef = objectRef2;
                obj = (T) obj3;
                obj2 = obj4;
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Object obj5 = anonymousClass1.L$3;
                Object obj6 = anonymousClass1.L$1;
                ResultKt.throwOnFailure(obj3);
            }
            return Unit.INSTANCE;
        }
        objectRef.element = (T) obj;
        FlowCollector flowCollector = this.$this_unsafeFlow$inlined;
        T t2 = this.$accumulator$inlined.element;
        anonymousClass1.L$0 = this;
        anonymousClass1.L$1 = obj;
        anonymousClass1.L$2 = continuation2;
        anonymousClass1.L$3 = obj2;
        anonymousClass1.label = 2;
        if (flowCollector.emit(t2, anonymousClass1) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
