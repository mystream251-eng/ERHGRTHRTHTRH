package kotlinx.coroutines.flow.internal;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.YieldKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: Collect.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
public final class CombineKt$combineInternal$2$1$invokeSuspend$$inlined$collect$1<T> implements FlowCollector<T> {
    final /* synthetic */ CombineKt.AnonymousClass2.AnonymousClass1 this$0;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1$invokeSuspend$$inlined$collect$1$1, reason: invalid class name */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@¨\u0006\u0007"}, d2 = {"emit", "", ExifInterface.GPS_DIRECTION_TRUE, "value", "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3$emit$1"}, k = 3, mv = {1, 4, 0})
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1$invokeSuspend$$inlined$collect$1", f = "Combine.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {133, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO}, m = "emit", n = {"this", "value", "continuation", "value", "this", "value", "continuation", "value"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
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
            return CombineKt$combineInternal$2$1$invokeSuspend$$inlined$collect$1.this.emit(null, this);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object emit(Object obj, Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        AnonymousClass1 anonymousClass2;
        CombineKt$combineInternal$2$1$invokeSuspend$$inlined$collect$1<T> combineKt$combineInternal$2$1$invokeSuspend$$inlined$collect$1;
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
            anonymousClass2 = anonymousClass1;
            Channel channel = this.this$0.$resultChannel;
            IndexedValue indexedValue = new IndexedValue(this.this$0.$i, obj);
            anonymousClass1.L$0 = this;
            anonymousClass1.L$1 = obj;
            anonymousClass1.L$2 = anonymousClass2;
            anonymousClass1.L$3 = obj;
            anonymousClass1.label = 1;
            if (channel.send(indexedValue, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            combineKt$combineInternal$2$1$invokeSuspend$$inlined$collect$1 = this;
            obj2 = obj;
        } else {
            if (i == 1) {
                Object obj4 = anonymousClass1.L$3;
                anonymousClass2 = (Continuation) anonymousClass1.L$2;
                Object obj5 = anonymousClass1.L$1;
                combineKt$combineInternal$2$1$invokeSuspend$$inlined$collect$1 = (CombineKt$combineInternal$2$1$invokeSuspend$$inlined$collect$1) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj3);
                obj2 = obj4;
                obj = obj5;
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Object obj6 = anonymousClass1.L$3;
                Object obj7 = anonymousClass1.L$1;
                ResultKt.throwOnFailure(obj3);
            }
            return Unit.INSTANCE;
        }
        anonymousClass1.L$0 = combineKt$combineInternal$2$1$invokeSuspend$$inlined$collect$1;
        anonymousClass1.L$1 = obj;
        anonymousClass1.L$2 = anonymousClass2;
        anonymousClass1.L$3 = obj2;
        anonymousClass1.label = 2;
        if (YieldKt.yield(anonymousClass1) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    public CombineKt$combineInternal$2$1$invokeSuspend$$inlined$collect$1(CombineKt.AnonymousClass2.AnonymousClass1 anonymousClass1) {
        this.this$0 = anonymousClass1;
    }
}
