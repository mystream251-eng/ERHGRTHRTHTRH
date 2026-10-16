package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.flow.internal.SafeCollector;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
public final class FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1<T> implements Flow<T> {
    final /* synthetic */ Function3 $action$inlined;
    final /* synthetic */ Flow $this_onCompletion$inlined;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1, reason: invalid class name */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0096@¨\u0006\b"}, d2 = {"collect", "", ExifInterface.GPS_DIRECTION_TRUE, "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1$collect$1"}, k = 3, mv = {1, 4, 0})
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1", f = "Emitters.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {114, 121, 128}, m = "collect", n = {"this", "collector", "continuation", "$receiver", "this", "collector", "continuation", "$receiver", "e", "this", "collector", "continuation", "$receiver", "sc"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"})
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
            return FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.this.collect(null, this);
        }
    }

    public FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1(Flow flow, Function3 function3) {
        this.$this_onCompletion$inlined = flow;
        this.$action$inlined = function3;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x00c3 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:35:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:46:0x00f1 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:56:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector, Continuation continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        AnonymousClass1 anonymousClass2;
        FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1<T> flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
        FlowCollector flowCollector2;
        Throwable th;
        FlowCollector flowCollector3;
        ThrowingCollector throwingCollector;
        Function3 function3;
        SafeCollector safeCollector;
        SafeCollector safeCollector2;
        Object objInvoke;
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
            anonymousClass2 = anonymousClass1;
            try {
                Flow flow = this.$this_onCompletion$inlined;
                anonymousClass1.L$0 = this;
                anonymousClass1.L$1 = flowCollector;
                anonymousClass1.L$2 = anonymousClass2;
                anonymousClass1.L$3 = flowCollector;
                anonymousClass1.label = 1;
                if (flow.collect(flowCollector, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 = this;
                flowCollector2 = flowCollector;
                safeCollector = new SafeCollector(flowCollector, anonymousClass1.getContext());
                Function3 function4 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.$action$inlined;
                anonymousClass1.L$0 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
                anonymousClass1.L$1 = flowCollector2;
                anonymousClass1.L$2 = anonymousClass2;
                anonymousClass1.L$3 = flowCollector;
                anonymousClass1.L$4 = safeCollector;
                anonymousClass1.label = 3;
                InlineMarker.mark(6);
                objInvoke = function4.invoke(safeCollector, null, anonymousClass1);
                InlineMarker.mark(7);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                safeCollector2 = safeCollector;
                safeCollector2.releaseIntercepted();
                return Unit.INSTANCE;
            } catch (Throwable th2) {
                flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 = this;
                flowCollector2 = flowCollector;
                th = th2;
                flowCollector3 = flowCollector2;
                throwingCollector = new ThrowingCollector(th);
                function3 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.$action$inlined;
                anonymousClass1.L$0 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
                anonymousClass1.L$1 = flowCollector2;
                anonymousClass1.L$2 = anonymousClass2;
                anonymousClass1.L$3 = flowCollector3;
                anonymousClass1.L$4 = th;
                anonymousClass1.label = 2;
                if (FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(throwingCollector, function3, th, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                throw th;
            }
        }
        if (i != 1) {
            if (i == 2) {
                Throwable th3 = (Throwable) anonymousClass1.L$4;
                ResultKt.throwOnFailure(obj);
                throw th3;
            }
            if (i != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            safeCollector2 = (SafeCollector) anonymousClass1.L$4;
            try {
                ResultKt.throwOnFailure(obj);
                safeCollector2.releaseIntercepted();
                return Unit.INSTANCE;
            } catch (Throwable th4) {
                th = th4;
                safeCollector2.releaseIntercepted();
                throw th;
            }
        }
        flowCollector = (FlowCollector) anonymousClass1.L$3;
        anonymousClass2 = (Continuation) anonymousClass1.L$2;
        flowCollector2 = (FlowCollector) anonymousClass1.L$1;
        flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 = (FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1) anonymousClass1.L$0;
        try {
            ResultKt.throwOnFailure(obj);
            safeCollector = new SafeCollector(flowCollector, anonymousClass1.getContext());
            try {
                Function3 function5 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.$action$inlined;
                anonymousClass1.L$0 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
                anonymousClass1.L$1 = flowCollector2;
                anonymousClass1.L$2 = anonymousClass2;
                anonymousClass1.L$3 = flowCollector;
                anonymousClass1.L$4 = safeCollector;
                anonymousClass1.label = 3;
                InlineMarker.mark(6);
                objInvoke = function5.invoke(safeCollector, null, anonymousClass1);
                InlineMarker.mark(7);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                safeCollector2 = safeCollector;
                safeCollector2.releaseIntercepted();
                return Unit.INSTANCE;
            } catch (Throwable th5) {
                th = th5;
                safeCollector2 = safeCollector;
                safeCollector2.releaseIntercepted();
                throw th;
            }
        } catch (Throwable th6) {
            flowCollector3 = flowCollector;
            th = th6;
            throwingCollector = new ThrowingCollector(th);
            function3 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.$action$inlined;
            anonymousClass1.L$0 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
            anonymousClass1.L$1 = flowCollector2;
            anonymousClass1.L$2 = anonymousClass2;
            anonymousClass1.L$3 = flowCollector3;
            anonymousClass1.L$4 = th;
            anonymousClass1.label = 2;
            if (FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(throwingCollector, function3, th, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            throw th;
        }
    }
}
