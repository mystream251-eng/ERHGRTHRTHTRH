package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\b"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__MergeKt$unsafeTransform$$inlined$unsafeFlow$2"}, k = 1, mv = {1, 4, 0})
public final class FlowKt__MergeKt$map$$inlined$unsafeTransform$2<R> implements Flow<R> {
    final /* synthetic */ Flow $this_unsafeTransform$inlined;
    final /* synthetic */ Function2 $transform$inlined$1;

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2$2, reason: invalid class name */
    /* JADX INFO: compiled from: Collect.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\b"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__MergeKt$$special$$inlined$collect$2", "kotlinx/coroutines/flow/FlowKt__MergeKt$unsafeTransform$$inlined$unsafeFlow$2$lambda$1"}, k = 1, mv = {1, 4, 0})
    public static final class AnonymousClass2<T> implements FlowCollector<T> {
        final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
        final /* synthetic */ FlowKt__MergeKt$map$$inlined$unsafeTransform$2 this$0;

        /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2$2$1, reason: invalid class name */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@¨\u0006\t"}, d2 = {"emit", "", ExifInterface.GPS_DIRECTION_TRUE, "value", "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3$emit$1", "kotlinx/coroutines/flow/FlowKt__MergeKt$$special$$inlined$collect$2$1", "kotlinx/coroutines/flow/FlowKt__MergeKt$unsafeTransform$$inlined$unsafeFlow$2$lambda$1$1"}, k = 3, mv = {1, 4, 0})
        @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2$2", f = "Transform.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, l = {TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO}, m = "emit", n = {"this", "value", "continuation", "value", "continuation", "value", "$receiver", "this", "value", "continuation", "value", "continuation", "value", "$receiver"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"})
        public static final class AnonymousClass1 extends ContinuationImpl {
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            Object L$5;
            Object L$6;
            Object L$7;
            int label;
            /* synthetic */ Object result;

            public AnonymousClass1(Continuation continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass2.this.emit(null, this);
            }
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        public Object emit(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1;
            AnonymousClass1 anonymousClass2;
            AnonymousClass2<T> anonymousClass3;
            Object obj2;
            Object obj3;
            Object obj4;
            AnonymousClass1 anonymousClass4;
            FlowCollector flowCollector;
            FlowCollector flowCollector2;
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
            if (i != 0) {
                if (i == 1) {
                    FlowCollector flowCollector3 = (FlowCollector) anonymousClass1.L$7;
                    FlowCollector flowCollector4 = (FlowCollector) anonymousClass1.L$6;
                    obj2 = anonymousClass1.L$5;
                    anonymousClass2 = (Continuation) anonymousClass1.L$4;
                    obj3 = anonymousClass1.L$3;
                    anonymousClass4 = (AnonymousClass1) anonymousClass1.L$2;
                    obj4 = anonymousClass1.L$1;
                    anonymousClass3 = (AnonymousClass2) anonymousClass1.L$0;
                    ResultKt.throwOnFailure(obj5);
                    flowCollector = flowCollector4;
                    flowCollector2 = flowCollector3;
                } else {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    Object obj6 = anonymousClass1.L$5;
                    Object obj7 = anonymousClass1.L$3;
                    Object obj8 = anonymousClass1.L$1;
                    ResultKt.throwOnFailure(obj5);
                }
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj5);
            FlowCollector flowCollector5 = this.$this_unsafeFlow$inlined;
            anonymousClass2 = anonymousClass1;
            Function2 function2 = this.this$0.$transform$inlined$1;
            anonymousClass1.L$0 = this;
            anonymousClass1.L$1 = obj;
            anonymousClass1.L$2 = anonymousClass1;
            anonymousClass1.L$3 = obj;
            anonymousClass1.L$4 = anonymousClass2;
            anonymousClass1.L$5 = obj;
            anonymousClass1.L$6 = flowCollector5;
            anonymousClass1.L$7 = flowCollector5;
            anonymousClass1.label = 1;
            Object objInvoke = function2.invoke(obj, anonymousClass1);
            if (objInvoke == coroutine_suspended) {
                return coroutine_suspended;
            }
            anonymousClass3 = this;
            obj2 = obj;
            obj3 = obj2;
            obj4 = obj3;
            FlowCollector flowCollector6 = flowCollector5;
            anonymousClass4 = anonymousClass1;
            obj5 = objInvoke;
            flowCollector = flowCollector6;
            flowCollector2 = flowCollector6;
            anonymousClass1.L$0 = anonymousClass3;
            anonymousClass1.L$1 = obj4;
            anonymousClass1.L$2 = anonymousClass4;
            anonymousClass1.L$3 = obj3;
            anonymousClass1.L$4 = anonymousClass2;
            anonymousClass1.L$5 = obj2;
            anonymousClass1.L$6 = flowCollector;
            anonymousClass1.label = 2;
            if (flowCollector2.emit(obj5, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        public AnonymousClass2(FlowCollector flowCollector, FlowKt__MergeKt$map$$inlined$unsafeTransform$2 flowKt__MergeKt$map$$inlined$unsafeTransform$2) {
            this.$this_unsafeFlow$inlined = flowCollector;
            this.this$0 = flowKt__MergeKt$map$$inlined$unsafeTransform$2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Object emit$$forInline(Object obj, Continuation continuation) {
            InlineMarker.mark(4);
            new AnonymousClass1(continuation);
            InlineMarker.mark(5);
            FlowCollector flowCollector = this.$this_unsafeFlow$inlined;
            Object objInvoke = this.this$0.$transform$inlined$1.invoke(obj, continuation);
            InlineMarker.mark(0);
            Object objEmit = flowCollector.emit(objInvoke, continuation);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            return objEmit;
        }
    }

    public FlowKt__MergeKt$map$$inlined$unsafeTransform$2(Flow flow, Function2 function2) {
        this.$this_unsafeTransform$inlined = flow;
        this.$transform$inlined$1 = function2;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector, Continuation continuation) {
        Object objCollect = this.$this_unsafeTransform$inlined.collect(new AnonymousClass2(flowCollector, this), continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    public Object collect$$forInline(FlowCollector flowCollector, Continuation continuation) {
        InlineMarker.mark(4);
        new ContinuationImpl(continuation) { // from class: kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2.1
            int label;
            /* synthetic */ Object result;

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return FlowKt__MergeKt$map$$inlined$unsafeTransform$2.this.collect(null, this);
            }
        };
        InlineMarker.mark(5);
        Flow flow = this.$this_unsafeTransform$inlined;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(flowCollector, this);
        InlineMarker.mark(0);
        flow.collect(anonymousClass2, continuation);
        InlineMarker.mark(2);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }
}
