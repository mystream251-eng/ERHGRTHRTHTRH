package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
public final class FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2<T> implements Flow<T> {
    final /* synthetic */ Function1 $this_asFlow$inlined;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2$1, reason: invalid class name */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0096@¨\u0006\b"}, d2 = {"collect", "", ExifInterface.GPS_DIRECTION_TRUE, "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1$collect$1"}, k = 3, mv = {1, 4, 0})
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2", f = "Builders.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {113, 113}, m = "collect", n = {"this", "collector", "continuation", "$receiver", "this", "collector", "continuation", "$receiver"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
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
            return FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2.this.collect(null, this);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector, Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2<T> flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2;
        Continuation continuation2;
        FlowCollector flowCollector2;
        FlowCollector flowCollector3;
        FlowCollector flowCollector4;
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
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AnonymousClass1 anonymousClass2 = anonymousClass1;
            Function1 function1 = this.$this_asFlow$inlined;
            anonymousClass1.L$0 = this;
            anonymousClass1.L$1 = flowCollector;
            anonymousClass1.L$2 = anonymousClass2;
            anonymousClass1.L$3 = flowCollector;
            anonymousClass1.L$4 = flowCollector;
            anonymousClass1.label = 1;
            Object objInvoke = function1.invoke(anonymousClass1);
            if (objInvoke == coroutine_suspended) {
                return coroutine_suspended;
            }
            flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2 = this;
            FlowCollector flowCollector5 = flowCollector;
            continuation2 = anonymousClass2;
            obj = objInvoke;
            flowCollector2 = flowCollector5;
            flowCollector4 = flowCollector5;
            flowCollector3 = flowCollector;
        } else {
            if (i == 1) {
                FlowCollector flowCollector6 = (FlowCollector) anonymousClass1.L$4;
                FlowCollector flowCollector7 = (FlowCollector) anonymousClass1.L$3;
                continuation2 = (Continuation) anonymousClass1.L$2;
                FlowCollector flowCollector8 = (FlowCollector) anonymousClass1.L$1;
                flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2 = (FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj);
                flowCollector2 = flowCollector7;
                flowCollector4 = flowCollector8;
                flowCollector3 = flowCollector6;
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
        anonymousClass1.L$0 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2;
        anonymousClass1.L$1 = flowCollector4;
        anonymousClass1.L$2 = continuation2;
        anonymousClass1.L$3 = flowCollector2;
        anonymousClass1.label = 2;
        if (flowCollector3.emit(obj, anonymousClass1) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    public FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2(Function1 function1) {
        this.$this_asFlow$inlined = function1;
    }
}
