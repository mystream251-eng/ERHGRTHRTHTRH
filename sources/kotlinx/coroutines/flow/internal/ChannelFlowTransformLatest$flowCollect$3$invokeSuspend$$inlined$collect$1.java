package kotlinx.coroutines.flow.internal;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: Collect.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
public final class ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1<T> implements FlowCollector<T> {
    final /* synthetic */ Ref.ObjectRef $previousFlow$inlined;
    final /* synthetic */ CoroutineScope $this_flowScope$inlined;
    final /* synthetic */ ChannelFlowTransformLatest.AnonymousClass3 this$0;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1$1, reason: invalid class name */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@¨\u0006\u0007"}, d2 = {"emit", "", ExifInterface.GPS_DIRECTION_TRUE, "value", "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3$emit$1"}, k = 3, mv = {1, 4, 0})
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1", f = "Merge.kt", i = {0, 0, 0, 0, 0}, l = {TsExtractor.TS_STREAM_TYPE_E_AC3}, m = "emit", n = {"this", "value", "continuation", "value", "$this$apply"}, s = {"L$0", "L$1", "L$2", "L$3", "L$5"})
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1.this.emit(null, this);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object emit(Object obj, Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1<T> channelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1;
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
        Object obj2 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj2);
            AnonymousClass1 anonymousClass2 = anonymousClass1;
            Job job = (Job) this.$previousFlow$inlined.element;
            if (job != null) {
                job.cancel((CancellationException) new ChildCancelledException());
                anonymousClass1.L$0 = this;
                anonymousClass1.L$1 = obj;
                anonymousClass1.L$2 = anonymousClass2;
                anonymousClass1.L$3 = obj;
                anonymousClass1.L$4 = job;
                anonymousClass1.L$5 = job;
                anonymousClass1.label = 1;
                if (job.join(anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            channelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1 = this;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            obj = anonymousClass1.L$3;
            Object obj3 = anonymousClass1.L$1;
            channelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1 = (ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj2);
        }
        channelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1.$previousFlow$inlined.element = (T) BuildersKt__Builders_commonKt.launch$default(channelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1.$this_flowScope$inlined, null, CoroutineStart.UNDISPATCHED, new ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1$lambda$1(obj, null, channelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1), 1, null);
        return Unit.INSTANCE;
    }

    public ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1(ChannelFlowTransformLatest.AnonymousClass3 anonymousClass3, CoroutineScope coroutineScope, Ref.ObjectRef objectRef) {
        this.this$0 = anonymousClass3;
        this.$this_flowScope$inlined = coroutineScope;
        this.$previousFlow$inlined = objectRef;
    }
}
