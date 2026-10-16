package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Ref;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: Collect.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__LimitKt$$special$$inlined$collect$2"}, k = 1, mv = {1, 4, 0})
public final class FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1<T> implements FlowCollector<T> {
    final /* synthetic */ Ref.BooleanRef $matched$inlined;
    final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
    final /* synthetic */ FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1 this$0;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1$1, reason: invalid class name */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@¨\u0006\b"}, d2 = {"emit", "", ExifInterface.GPS_DIRECTION_TRUE, "value", "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3$emit$1", "kotlinx/coroutines/flow/FlowKt__LimitKt$$special$$inlined$collect$2$1"}, k = 3, mv = {1, 4, 0})
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
            return FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1.this.emit(null, this);
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x009b  */
    /* JADX WARN: Code duplicated, block: B:31:0x00b1 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object emit(Object obj, Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        AnonymousClass1 anonymousClass2;
        FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1<T> flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1;
        Object obj2;
        FlowCollector flowCollector;
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
        Object objInvoke = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    obj = anonymousClass1.L$3;
                    anonymousClass2 = (Continuation) anonymousClass1.L$2;
                    obj2 = anonymousClass1.L$1;
                    flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1 = (FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1) anonymousClass1.L$0;
                    ResultKt.throwOnFailure(objInvoke);
                    if (!((Boolean) objInvoke).booleanValue()) {
                        flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1.$matched$inlined.element = true;
                        flowCollector = flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1.$this_unsafeFlow$inlined;
                        anonymousClass1.L$0 = flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1;
                        anonymousClass1.L$1 = obj2;
                        anonymousClass1.L$2 = anonymousClass2;
                        anonymousClass1.L$3 = obj;
                        anonymousClass1.label = 3;
                        if (flowCollector.emit(obj, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
            Object obj3 = anonymousClass1.L$3;
            Object obj4 = anonymousClass1.L$1;
            ResultKt.throwOnFailure(objInvoke);
        } else {
            ResultKt.throwOnFailure(objInvoke);
            anonymousClass2 = anonymousClass1;
            if (this.$matched$inlined.element) {
                FlowCollector flowCollector2 = this.$this_unsafeFlow$inlined;
                anonymousClass1.L$0 = this;
                anonymousClass1.L$1 = obj;
                anonymousClass1.L$2 = anonymousClass2;
                anonymousClass1.L$3 = obj;
                anonymousClass1.label = 1;
                if (flowCollector2.emit(obj, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                Function2 function2 = this.this$0.$predicate$inlined;
                anonymousClass1.L$0 = this;
                anonymousClass1.L$1 = obj;
                anonymousClass1.L$2 = anonymousClass2;
                anonymousClass1.L$3 = obj;
                anonymousClass1.label = 2;
                InlineMarker.mark(6);
                objInvoke = function2.invoke(obj, anonymousClass1);
                InlineMarker.mark(7);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1 = this;
                obj2 = obj;
                if (!((Boolean) objInvoke).booleanValue()) {
                    flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1.$matched$inlined.element = true;
                    flowCollector = flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1.$this_unsafeFlow$inlined;
                    anonymousClass1.L$0 = flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1;
                    anonymousClass1.L$1 = obj2;
                    anonymousClass1.L$2 = anonymousClass2;
                    anonymousClass1.L$3 = obj;
                    anonymousClass1.label = 3;
                    if (flowCollector.emit(obj, anonymousClass1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
        }
        return Unit.INSTANCE;
    }

    public FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1(FlowCollector flowCollector, Ref.BooleanRef booleanRef, FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1 flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1) {
        this.$this_unsafeFlow$inlined = flowCollector;
        this.$matched$inlined = booleanRef;
        this.this$0 = flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1;
    }
}
