package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.flow.internal.AbortFlowException;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: Limit.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/FlowKt__LimitKt$collectWhile$collector$1", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__LimitKt$$special$$inlined$collectWhile$1"}, k = 1, mv = {1, 4, 0})
public final class FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1<T> implements FlowCollector<T> {
    final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
    final /* synthetic */ FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1 this$0;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1$1, reason: invalid class name */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@¨\u0006\b"}, d2 = {"emit", "", ExifInterface.GPS_DIRECTION_TRUE, "value", "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/FlowKt__LimitKt$collectWhile$collector$1$emit$1", "kotlinx/coroutines/flow/FlowKt__LimitKt$$special$$inlined$collectWhile$1$1"}, k = 3, mv = {1, 4, 0})
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1.this.emit(null, this);
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:30:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object emit(Object obj, Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        Object obj2;
        Continuation continuation2;
        FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1<T> flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1;
        Object obj3;
        Object obj4;
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
        Object obj5 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        boolean z = true;
        if (i == 0) {
            ResultKt.throwOnFailure(obj5);
            AnonymousClass1 anonymousClass2 = anonymousClass1;
            Function2 function2 = this.this$0.$predicate$inlined;
            anonymousClass1.L$0 = this;
            anonymousClass1.L$1 = obj;
            anonymousClass1.L$2 = anonymousClass2;
            anonymousClass1.L$3 = obj;
            anonymousClass1.label = 1;
            InlineMarker.mark(6);
            Object objInvoke = function2.invoke(obj, anonymousClass1);
            InlineMarker.mark(7);
            if (objInvoke == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj2 = obj;
            continuation2 = anonymousClass2;
            flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1 = this;
            obj3 = objInvoke;
            obj4 = obj2;
        } else {
            if (i == 1) {
                Object obj6 = anonymousClass1.L$3;
                Continuation continuation3 = (Continuation) anonymousClass1.L$2;
                obj2 = anonymousClass1.L$1;
                FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1<T> flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$2 = (FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj5);
                obj4 = obj6;
                flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1 = flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$2;
                continuation2 = continuation3;
                obj3 = obj5;
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Object obj7 = anonymousClass1.L$3;
                Object obj8 = anonymousClass1.L$1;
                flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1 = (FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj5);
            }
            if (Boxing.boxBoolean(z).booleanValue()) {
                throw new AbortFlowException(flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1);
            }
            return Unit.INSTANCE;
        }
        if (((Boolean) obj3).booleanValue()) {
            FlowCollector flowCollector = flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1.$this_unsafeFlow$inlined;
            anonymousClass1.L$0 = flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1;
            anonymousClass1.L$1 = obj2;
            anonymousClass1.L$2 = continuation2;
            anonymousClass1.L$3 = obj4;
            anonymousClass1.label = 2;
            if (flowCollector.emit(obj4, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            z = false;
        }
        if (Boxing.boxBoolean(z).booleanValue()) {
            throw new AbortFlowException(flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1);
        }
        return Unit.INSTANCE;
    }

    public FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$lambda$1(FlowCollector flowCollector, FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1 flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1) {
        this.$this_unsafeFlow$inlined = flowCollector;
        this.this$0 = flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1;
    }
}
