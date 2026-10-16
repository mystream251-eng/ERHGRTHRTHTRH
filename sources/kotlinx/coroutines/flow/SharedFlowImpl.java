package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.flow.internal.SharedFlowState;

/* JADX INFO: compiled from: SharedFlow.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u001d\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\t0e2\b\u0012\u0004\u0012\u00028\u00000f2\b\u0012\u0004\u0012\u00028\u00000g2\b\u0012\u0004\u0012\u00028\u00000h:\u0001cB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0082@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J!\u0010\u0016\u001a\u00020\u000b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0018H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\tH\u0014¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u001e\u0010\u0013J\u001b\u0010 \u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0004\b \u0010!J\u001b\u0010\"\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00028\u0000H\u0082@ø\u0001\u0000¢\u0006\u0004\b\"\u0010!J\u0019\u0010%\u001a\u00020\u000b2\b\u0010$\u001a\u0004\u0018\u00010#H\u0002¢\u0006\u0004\b%\u0010&J3\u0010*\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010(0'2\u0014\u0010)\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010(0'H\u0002¢\u0006\u0004\b*\u0010+J-\u00100\u001a\b\u0012\u0004\u0012\u00028\u00000/2\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b0\u00101J\u0019\u00103\u001a\u0004\u0018\u00010#2\u0006\u00102\u001a\u00020\u0018H\u0002¢\u0006\u0004\b3\u00104J9\u00109\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#052\u0010\u00106\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010#\u0018\u0001052\u0006\u00107\u001a\u00020\u00022\u0006\u00108\u001a\u00020\u0002H\u0002¢\u0006\u0004\b9\u0010:J\u000f\u0010;\u001a\u00020\u000bH\u0016¢\u0006\u0004\b;\u0010\u0013J\u0017\u0010=\u001a\u00020<2\u0006\u0010\u001f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b=\u0010>J\u0017\u0010?\u001a\u00020<2\u0006\u0010\u001f\u001a\u00028\u0000H\u0002¢\u0006\u0004\b?\u0010>J\u0017\u0010@\u001a\u00020<2\u0006\u0010\u001f\u001a\u00028\u0000H\u0002¢\u0006\u0004\b@\u0010>J\u0017\u0010A\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\bA\u0010BJ\u0019\u0010C\u001a\u0004\u0018\u00010#2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\bC\u0010DJ/\u0010I\u001a\u00020\u000b2\u0006\u0010E\u001a\u00020\u00182\u0006\u0010F\u001a\u00020\u00182\u0006\u0010G\u001a\u00020\u00182\u0006\u0010H\u001a\u00020\u0018H\u0002¢\u0006\u0004\bI\u0010JJ%\u0010N\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010(0'2\u0006\u0010K\u001a\u00020\u0018H\u0000¢\u0006\u0004\bL\u0010MJ\u000f\u0010Q\u001a\u00020\u0018H\u0000¢\u0006\u0004\bO\u0010PR\u0016\u0010\u0004\u001a\u00020\u00028\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010RR\u0016\u0010T\u001a\u00020\u00188B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\bS\u0010PR\u0016\u0010V\u001a\u00020\u00188B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\bU\u0010PR\u0016\u0010\u0006\u001a\u00020\u00058\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010WR\u0016\u0010Y\u001a\u00020\u00188B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\bX\u0010PR\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010RR\u001c\u0010]\u001a\b\u0012\u0004\u0012\u00028\u00000Z8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b[\u0010\\R\u0016\u0010`\u001a\u00020\u00028B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b^\u0010_R\u0016\u0010b\u001a\u00020\u00028B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\ba\u0010_\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006d"}, d2 = {"Lkotlinx/coroutines/flow/SharedFlowImpl;", ExifInterface.GPS_DIRECTION_TRUE, "", "replay", "bufferCapacity", "Lkotlinx/coroutines/channels/BufferOverflow;", "onBufferOverflow", "<init>", "(IILkotlinx/coroutines/channels/BufferOverflow;)V", "Lkotlinx/coroutines/flow/SharedFlowSlot;", "slot", "", "awaitValue", "(Lkotlinx/coroutines/flow/SharedFlowSlot;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/flow/SharedFlowImpl$Emitter;", "emitter", "cancelEmitter", "(Lkotlinx/coroutines/flow/SharedFlowImpl$Emitter;)V", "cleanupTailLocked", "()V", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "newHead", "correctCollectorIndexesOnDropOldest", "(J)V", "createSlot", "()Lkotlinx/coroutines/flow/SharedFlowSlot;", "dropOldestLocked", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitSuspend", "", "item", "enqueueLocked", "(Ljava/lang/Object;)V", "", "Lkotlin/coroutines/Continuation;", "resumesIn", "findSlotsToResumeLocked", "([Lkotlin/coroutines/Continuation;)[Lkotlin/coroutines/Continuation;", "Lkotlin/coroutines/CoroutineContext;", "context", "capacity", "Lkotlinx/coroutines/flow/Flow;", "fuse", "(Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)Lkotlinx/coroutines/flow/Flow;", "index", "getPeekedValueLockedAt", "(J)Ljava/lang/Object;", "Lkotlinx/coroutines/flow/internal/SharedFlowState;", "curBuffer", "curSize", "newSize", "growBuffer", "(Lkotlinx/coroutines/flow/internal/SharedFlowState;II)Lkotlinx/coroutines/flow/internal/SharedFlowState;", "resetReplayCache", "", "tryEmit", "(Ljava/lang/Object;)Z", "tryEmitLocked", "tryEmitNoCollectorsLocked", "tryPeekLocked", "(Lkotlinx/coroutines/flow/SharedFlowSlot;)J", "tryTakeValue", "(Lkotlinx/coroutines/flow/SharedFlowSlot;)Ljava/lang/Object;", "newReplayIndex", "newMinCollectorIndex", "newBufferEndIndex", "newQueueEndIndex", "updateBufferLocked", "(JJJJ)V", "oldIndex", "updateCollectorIndexLocked$kotlinx_coroutines_core", "(J)[Lkotlin/coroutines/Continuation;", "updateCollectorIndexLocked", "updateNewCollectorIndexLocked$kotlinx_coroutines_core", "()J", "updateNewCollectorIndexLocked", "I", "getBufferEndIndex", "bufferEndIndex", "getHead", TtmlNode.TAG_HEAD, "Lkotlinx/coroutines/channels/BufferOverflow;", "getQueueEndIndex", "queueEndIndex", "", "getReplayCache", "()Ljava/util/List;", "replayCache", "getReplaySize", "()I", "replaySize", "getTotalSize", "totalSize", "Emitter", "kotlinx-coroutines-core", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlow;", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lkotlinx/coroutines/flow/CancellableFlow;", "Lkotlinx/coroutines/flow/internal/FusibleFlow;"}, k = 1, mv = {1, 4, 0})
final class SharedFlowImpl<T> extends AbstractSharedFlow<SharedFlowSlot> implements MutableSharedFlow<T>, CancellableFlow<T>, FusibleFlow<T> {
    private static final AtomicIntegerFieldUpdater bufferSize$FU = AtomicIntegerFieldUpdater.newUpdater(SharedFlowImpl.class, "bufferSize");
    static final AtomicIntegerFieldUpdater queueSize$FU = AtomicIntegerFieldUpdater.newUpdater(SharedFlowImpl.class, "queueSize");
    private final int bufferCapacity;
    private final BufferOverflow onBufferOverflow;
    private final int replay;
    private volatile Object buffer = null;
    private volatile long replayIndex = 0;
    private volatile long minCollectorIndex = 0;
    private volatile int bufferSize = 0;
    volatile int queueSize = 0;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BufferOverflow.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[BufferOverflow.SUSPEND.ordinal()] = 1;
            $EnumSwitchMapping$0[BufferOverflow.DROP_LATEST.ordinal()] = 2;
            $EnumSwitchMapping$0[BufferOverflow.DROP_OLDEST.ordinal()] = 3;
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.SharedFlowImpl$collect$1, reason: invalid class name */
    /* JADX INFO: compiled from: SharedFlow.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0096@"}, d2 = {"collect", "", ExifInterface.GPS_DIRECTION_TRUE, "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 4, 0})
    @DebugMetadata(c = "kotlinx.coroutines.flow.SharedFlowImpl", f = "SharedFlow.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {330, 337, 340}, m = "collect", n = {"this", "collector", "slot", "this", "collector", "slot", "collectorJob", "newValue", "this", "collector", "slot", "collectorJob", "newValue"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SharedFlowImpl.this.collect(null, this);
        }
    }

    public SharedFlowImpl(int i, int i2, BufferOverflow bufferOverflow) {
        this.replay = i;
        this.bufferCapacity = i2;
        this.onBufferOverflow = bufferOverflow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long getHead() {
        return Math.min(this.minCollectorIndex, this.replayIndex);
    }

    private final int getReplaySize() {
        return (int) ((getHead() + ((long) this.bufferSize)) - this.replayIndex);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getTotalSize() {
        return this.bufferSize + this.queueSize;
    }

    private final long getBufferEndIndex() {
        return getHead() + ((long) this.bufferSize);
    }

    private final long getQueueEndIndex() {
        return getHead() + ((long) this.bufferSize) + ((long) this.queueSize);
    }

    @Override // kotlinx.coroutines.flow.SharedFlow
    public List<T> getReplayCache() {
        synchronized (this) {
            int replaySize = getReplaySize();
            if (replaySize == 0) {
                return CollectionsKt.emptyList();
            }
            ArrayList arrayList = new ArrayList(replaySize);
            Object obj = this.buffer;
            Intrinsics.checkNotNull(obj);
            SharedFlowState sharedFlowState = (SharedFlowState) obj;
            for (int i = 0; i < replaySize; i++) {
                arrayList.add(sharedFlowState.getBufferAt(this.replayIndex + ((long) i)));
            }
            return arrayList;
        }
    }

    /* JADX WARN: Code duplicated, block: B:38:0x00b1 A[Catch: all -> 0x0074, TryCatch #0 {all -> 0x0074, blocks: (B:14:0x003e, B:35:0x00a7, B:38:0x00b1, B:39:0x00b4, B:42:0x00c7, B:19:0x005e, B:22:0x0070, B:33:0x0097), top: B:49:0x0024 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x00c6 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:42:0x00c7 A[Catch: all -> 0x0074, TRY_LEAVE, TryCatch #0 {all -> 0x0074, blocks: (B:14:0x003e, B:35:0x00a7, B:38:0x00b1, B:39:0x00b4, B:42:0x00c7, B:19:0x005e, B:22:0x0070, B:33:0x0097), top: B:49:0x0024 }] */
    /* JADX WARN: Code duplicated, block: B:53:0x00af A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.Object, kotlinx.coroutines.flow.SharedFlowSlot] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.lang.Object, kotlinx.coroutines.flow.SharedFlowSlot] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r6v1, types: [kotlinx.coroutines.flow.SharedFlowImpl] */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.lang.Object, kotlinx.coroutines.flow.SharedFlowImpl] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00c4 -> B:15:0x0041). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:35:0x00a7
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // kotlinx.coroutines.flow.Flow
    public java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector<? super T> r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.SharedFlowImpl.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public boolean tryEmit(T value) {
        int i;
        boolean z;
        Continuation<Unit>[] continuationArrFindSlotsToResumeLocked = AbstractSharedFlowKt.EMPTY_RESUMES;
        synchronized (this) {
            if (tryEmitLocked(value)) {
                continuationArrFindSlotsToResumeLocked = findSlotsToResumeLocked(continuationArrFindSlotsToResumeLocked);
                z = true;
            } else {
                z = false;
            }
        }
        for (Continuation<Unit> continuation : continuationArrFindSlotsToResumeLocked) {
            if (continuation != null) {
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.INSTANCE;
                continuation.resumeWith(Result.m37constructorimpl(unit));
            }
        }
        return z;
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow, kotlinx.coroutines.flow.FlowCollector
    public Object emit(T t, Continuation<? super Unit> continuation) {
        if (tryEmit(t)) {
            return Unit.INSTANCE;
        }
        Object objEmitSuspend = emitSuspend(t, continuation);
        return objEmitSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmitSuspend : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean tryEmitLocked(T value) {
        if (get_nCollectors$internal() == 0) {
            return tryEmitNoCollectorsLocked(value);
        }
        if (this.bufferSize >= this.bufferCapacity && this.minCollectorIndex <= this.replayIndex) {
            int i = WhenMappings.$EnumSwitchMapping$0[this.onBufferOverflow.ordinal()];
            if (i == 1) {
                return false;
            }
            if (i == 2) {
                return true;
            }
        }
        enqueueLocked(value);
        bufferSize$FU.incrementAndGet(this);
        if (this.bufferSize > this.bufferCapacity) {
            dropOldestLocked();
        }
        if (getReplaySize() > this.replay) {
            updateBufferLocked(this.replayIndex + 1, this.minCollectorIndex, getBufferEndIndex(), getQueueEndIndex());
        }
        return true;
    }

    private final boolean tryEmitNoCollectorsLocked(T value) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(get_nCollectors$internal() == 0)) {
                throw new AssertionError();
            }
        }
        if (this.replay == 0) {
            return true;
        }
        enqueueLocked(value);
        bufferSize$FU.incrementAndGet(this);
        if (this.bufferSize > this.replay) {
            dropOldestLocked();
        }
        this.minCollectorIndex = getHead() + ((long) this.bufferSize);
        return true;
    }

    private final void dropOldestLocked() {
        Object obj = this.buffer;
        Intrinsics.checkNotNull(obj);
        ((SharedFlowState) obj).setBufferAt(getHead(), null);
        bufferSize$FU.decrementAndGet(this);
        long head = getHead() + 1;
        if (this.replayIndex < head) {
            this.replayIndex = head;
        }
        if (this.minCollectorIndex < head) {
            correctCollectorIndexesOnDropOldest(head);
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getHead() == head)) {
                throw new AssertionError();
            }
        }
    }

    private final void correctCollectorIndexesOnDropOldest(long newHead) {
        SharedFlowState sharedFlowState;
        SharedFlowImpl<T> sharedFlowImpl = this;
        if (sharedFlowImpl._nCollectors$internal != 0 && (sharedFlowState = (SharedFlowState) sharedFlowImpl._slots$internal) != null) {
            int size = sharedFlowState.getSize();
            for (int i = 0; i < size; i++) {
                AbstractSharedFlowSlot abstractSharedFlowSlot = (AbstractSharedFlowSlot) sharedFlowState.get(i);
                if (abstractSharedFlowSlot != null) {
                    SharedFlowSlot sharedFlowSlot = (SharedFlowSlot) abstractSharedFlowSlot;
                    if (sharedFlowSlot.index >= 0 && sharedFlowSlot.index < newHead) {
                        sharedFlowSlot.index = newHead;
                    }
                }
            }
        }
        this.minCollectorIndex = newHead;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enqueueLocked(Object item) {
        int totalSize = getTotalSize();
        SharedFlowState<Object> sharedFlowStateGrowBuffer = (SharedFlowState) this.buffer;
        if (sharedFlowStateGrowBuffer == null) {
            sharedFlowStateGrowBuffer = growBuffer(null, 0, 2);
        } else if (totalSize >= sharedFlowStateGrowBuffer.getSize()) {
            sharedFlowStateGrowBuffer = growBuffer(sharedFlowStateGrowBuffer, totalSize, sharedFlowStateGrowBuffer.getSize() * 2);
        }
        sharedFlowStateGrowBuffer.setBufferAt(getHead() + ((long) totalSize), item);
    }

    private final SharedFlowState<Object> growBuffer(SharedFlowState<Object> curBuffer, int curSize, int newSize) {
        if (!(newSize > 0)) {
            throw new IllegalStateException("Buffer size overflow".toString());
        }
        SharedFlowState<Object> sharedFlowState = new SharedFlowState<>(newSize);
        this.buffer = sharedFlowState;
        if (curBuffer == null) {
            return sharedFlowState;
        }
        long head = getHead();
        for (int i = 0; i < curSize; i++) {
            long j = ((long) i) + head;
            sharedFlowState.setBufferAt(j, curBuffer.getBufferAt(j));
        }
        return sharedFlowState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelEmitter(Emitter emitter) {
        synchronized (this) {
            if (emitter.index < getHead()) {
                return;
            }
            Object obj = this.buffer;
            Intrinsics.checkNotNull(obj);
            SharedFlowState sharedFlowState = (SharedFlowState) obj;
            if (sharedFlowState.getBufferAt(emitter.index) != emitter) {
                return;
            }
            sharedFlowState.setBufferAt(emitter.index, SharedFlowKt.NO_VALUE);
            cleanupTailLocked();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final long updateNewCollectorIndexLocked$kotlinx_coroutines_core() {
        long j = this.replayIndex;
        if (j < this.minCollectorIndex) {
            this.minCollectorIndex = j;
        }
        return j;
    }

    public final Continuation<Unit>[] updateCollectorIndexLocked$kotlinx_coroutines_core(long oldIndex) {
        int iMin;
        long j;
        long j2;
        SharedFlowState sharedFlowState;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(oldIndex >= this.minCollectorIndex)) {
                throw new AssertionError();
            }
        }
        if (oldIndex > this.minCollectorIndex) {
            return AbstractSharedFlowKt.EMPTY_RESUMES;
        }
        long head = getHead();
        long j3 = ((long) this.bufferSize) + head;
        if (this.bufferCapacity == 0 && this.queueSize > 0) {
            j3++;
        }
        SharedFlowImpl<T> sharedFlowImpl = this;
        if (sharedFlowImpl._nCollectors$internal != 0 && (sharedFlowState = (SharedFlowState) sharedFlowImpl._slots$internal) != null) {
            int size = sharedFlowState.getSize();
            for (int i = 0; i < size; i++) {
                AbstractSharedFlowSlot abstractSharedFlowSlot = (AbstractSharedFlowSlot) sharedFlowState.get(i);
                if (abstractSharedFlowSlot != null) {
                    SharedFlowSlot sharedFlowSlot = (SharedFlowSlot) abstractSharedFlowSlot;
                    if (sharedFlowSlot.index >= 0 && sharedFlowSlot.index < j3) {
                        j3 = sharedFlowSlot.index;
                    }
                }
            }
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(j3 >= this.minCollectorIndex)) {
                throw new AssertionError();
            }
        }
        if (j3 <= this.minCollectorIndex) {
            return AbstractSharedFlowKt.EMPTY_RESUMES;
        }
        long bufferEndIndex = getBufferEndIndex();
        if (get_nCollectors$internal() > 0) {
            iMin = Math.min(this.queueSize, this.bufferCapacity - ((int) (bufferEndIndex - j3)));
        } else {
            iMin = this.queueSize;
        }
        Continuation<Unit>[] continuationArr = AbstractSharedFlowKt.EMPTY_RESUMES;
        long j4 = ((long) this.queueSize) + bufferEndIndex;
        if (iMin > 0) {
            continuationArr = new Continuation[iMin];
            Object obj = this.buffer;
            Intrinsics.checkNotNull(obj);
            SharedFlowState sharedFlowState2 = (SharedFlowState) obj;
            long j5 = bufferEndIndex;
            int i2 = 0;
            while (true) {
                if (bufferEndIndex >= j4) {
                    j = j3;
                    break;
                }
                Object bufferAt = sharedFlowState2.getBufferAt(bufferEndIndex);
                if (bufferAt == SharedFlowKt.NO_VALUE) {
                    j = j3;
                    j2 = 1;
                } else if (bufferAt != null) {
                    Emitter emitter = (Emitter) bufferAt;
                    int i3 = i2 + 1;
                    j = j3;
                    continuationArr[i2] = emitter.cont;
                    sharedFlowState2.setBufferAt(bufferEndIndex, SharedFlowKt.NO_VALUE);
                    sharedFlowState2.setBufferAt(j5, emitter.value);
                    j2 = 1;
                    j5++;
                    if (i3 >= iMin) {
                        break;
                    }
                    i2 = i3;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.flow.SharedFlowImpl.Emitter");
                }
                bufferEndIndex += j2;
                j3 = j;
            }
            bufferEndIndex = j5;
        } else {
            j = j3;
        }
        int i4 = (int) (bufferEndIndex - head);
        long j6 = get_nCollectors$internal() == 0 ? bufferEndIndex : j;
        long jMax = Math.max(this.replayIndex, bufferEndIndex - ((long) Math.min(this.replay, i4)));
        if (this.bufferCapacity == 0 && jMax < j4) {
            Object obj2 = this.buffer;
            Intrinsics.checkNotNull(obj2);
            if (Intrinsics.areEqual(((SharedFlowState) obj2).getBufferAt(jMax), SharedFlowKt.NO_VALUE)) {
                bufferEndIndex++;
                jMax++;
            }
        }
        updateBufferLocked(jMax, j6, bufferEndIndex, j4);
        cleanupTailLocked();
        return true ^ (continuationArr.length == 0) ? findSlotsToResumeLocked(continuationArr) : continuationArr;
    }

    private final void updateBufferLocked(long newReplayIndex, long newMinCollectorIndex, long newBufferEndIndex, long newQueueEndIndex) {
        long jMin = Math.min(newMinCollectorIndex, newReplayIndex);
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(jMin >= getHead())) {
                throw new AssertionError();
            }
        }
        for (long head = getHead(); head < jMin; head++) {
            Object obj = this.buffer;
            Intrinsics.checkNotNull(obj);
            ((SharedFlowState) obj).setBufferAt(head, null);
        }
        this.replayIndex = newReplayIndex;
        this.minCollectorIndex = newMinCollectorIndex;
        this.bufferSize = (int) (newBufferEndIndex - jMin);
        this.queueSize = (int) (newQueueEndIndex - newBufferEndIndex);
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this.bufferSize >= 0)) {
                throw new AssertionError();
            }
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this.queueSize >= 0)) {
                throw new AssertionError();
            }
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this.replayIndex <= getHead() + ((long) this.bufferSize))) {
                throw new AssertionError();
            }
        }
    }

    private final void cleanupTailLocked() {
        if (this.bufferCapacity != 0 || this.queueSize > 1) {
            Object obj = this.buffer;
            Intrinsics.checkNotNull(obj);
            SharedFlowState sharedFlowState = (SharedFlowState) obj;
            while (this.queueSize > 0 && sharedFlowState.getBufferAt((getHead() + ((long) getTotalSize())) - 1) == SharedFlowKt.NO_VALUE) {
                queueSize$FU.decrementAndGet(this);
                sharedFlowState.setBufferAt(getHead() + ((long) getTotalSize()), null);
            }
        }
    }

    private final Object tryTakeValue(SharedFlowSlot slot) {
        Object obj;
        Continuation<Unit>[] continuationArrUpdateCollectorIndexLocked$kotlinx_coroutines_core = AbstractSharedFlowKt.EMPTY_RESUMES;
        synchronized (this) {
            long jTryPeekLocked = tryPeekLocked(slot);
            if (jTryPeekLocked < 0) {
                obj = SharedFlowKt.NO_VALUE;
            } else {
                long j = slot.index;
                Object peekedValueLockedAt = getPeekedValueLockedAt(jTryPeekLocked);
                slot.index = jTryPeekLocked + 1;
                continuationArrUpdateCollectorIndexLocked$kotlinx_coroutines_core = updateCollectorIndexLocked$kotlinx_coroutines_core(j);
                obj = peekedValueLockedAt;
            }
        }
        for (Continuation<Unit> continuation : continuationArrUpdateCollectorIndexLocked$kotlinx_coroutines_core) {
            if (continuation != null) {
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.INSTANCE;
                continuation.resumeWith(Result.m37constructorimpl(unit));
            }
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long tryPeekLocked(SharedFlowSlot slot) {
        long j = slot.index;
        if (j < getBufferEndIndex()) {
            return j;
        }
        if (this.bufferCapacity <= 0 && j <= getHead() && this.queueSize != 0) {
            return j;
        }
        return -1L;
    }

    private final Object getPeekedValueLockedAt(long index) {
        Object obj = this.buffer;
        Intrinsics.checkNotNull(obj);
        Object bufferAt = ((SharedFlowState) obj).getBufferAt(index);
        return bufferAt instanceof Emitter ? ((Emitter) bufferAt).value : bufferAt;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Continuation<Unit>[] findSlotsToResumeLocked(Continuation<Unit>[] resumesIn) {
        SharedFlowState sharedFlowState;
        int length = resumesIn.length;
        SharedFlowImpl<T> sharedFlowImpl = this;
        if (sharedFlowImpl._nCollectors$internal != 0 && (sharedFlowState = (SharedFlowState) sharedFlowImpl._slots$internal) != null) {
            int size = sharedFlowState.getSize();
            for (int i = 0; i < size; i++) {
                AbstractSharedFlowSlot abstractSharedFlowSlot = (AbstractSharedFlowSlot) sharedFlowState.get(i);
                if (abstractSharedFlowSlot != null) {
                    SharedFlowSlot sharedFlowSlot = (SharedFlowSlot) abstractSharedFlowSlot;
                    Continuation<Unit> continuation = (Continuation) sharedFlowSlot.cont;
                    if (continuation != null && tryPeekLocked(sharedFlowSlot) >= 0) {
                        if (length >= resumesIn.length) {
                            Object[] objArrCopyOf = Arrays.copyOf(resumesIn, Math.max(2, resumesIn.length * 2));
                            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
                            resumesIn = (Continuation[]) objArrCopyOf;
                        }
                        resumesIn[length] = continuation;
                        sharedFlowSlot.cont = null;
                        length++;
                    }
                }
            }
        }
        return resumesIn;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public SharedFlowSlot createSlot() {
        return new SharedFlowSlot();
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public void resetReplayCache() {
        synchronized (this) {
            updateBufferLocked(getBufferEndIndex(), this.minCollectorIndex, getBufferEndIndex(), getQueueEndIndex());
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    public Flow<T> fuse(CoroutineContext context, int capacity, BufferOverflow onBufferOverflow) {
        return SharedFlowKt.fuseSharedFlow(this, context, capacity, onBufferOverflow);
    }

    /* JADX INFO: compiled from: SharedFlow.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B1\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\nH\u0016R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/flow/SharedFlowImpl$Emitter;", "Lkotlinx/coroutines/DisposableHandle;", "flow", "Lkotlinx/coroutines/flow/SharedFlowImpl;", "index", "", "value", "", "cont", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/flow/SharedFlowImpl;JLjava/lang/Object;Lkotlin/coroutines/Continuation;)V", "dispose", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    private static final class Emitter implements DisposableHandle {
        public final Continuation<Unit> cont;
        public final SharedFlowImpl<?> flow;
        public long index;
        public final Object value;

        /* JADX WARN: Multi-variable type inference failed */
        public Emitter(SharedFlowImpl<?> sharedFlowImpl, long j, Object obj, Continuation<? super Unit> continuation) {
            this.flow = sharedFlowImpl;
            this.index = j;
            this.value = obj;
            this.cont = continuation;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            this.flow.cancelEmitter(this);
        }
    }

    final /* synthetic */ Object emitSuspend(T t, Continuation<? super Unit> continuation) {
        Continuation<Unit>[] continuationArrFindSlotsToResumeLocked;
        Emitter emitter;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        Continuation<Unit>[] continuationArrFindSlotsToResumeLocked2 = AbstractSharedFlowKt.EMPTY_RESUMES;
        synchronized (this) {
            if (tryEmitLocked(t)) {
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuationImpl2.resumeWith(Result.m37constructorimpl(unit));
                continuationArrFindSlotsToResumeLocked = findSlotsToResumeLocked(continuationArrFindSlotsToResumeLocked2);
                emitter = null;
            } else {
                Emitter emitter2 = new Emitter(this, ((long) getTotalSize()) + getHead(), t, cancellableContinuationImpl2);
                enqueueLocked(emitter2);
                queueSize$FU.incrementAndGet(this);
                if (this.bufferCapacity == 0) {
                    continuationArrFindSlotsToResumeLocked2 = findSlotsToResumeLocked(continuationArrFindSlotsToResumeLocked2);
                }
                continuationArrFindSlotsToResumeLocked = continuationArrFindSlotsToResumeLocked2;
                emitter = emitter2;
            }
        }
        if (emitter != null) {
            CancellableContinuationKt.disposeOnCancellation(cancellableContinuationImpl2, emitter);
        }
        for (Continuation<Unit> continuation2 : continuationArrFindSlotsToResumeLocked) {
            if (continuation2 != null) {
                Unit unit2 = Unit.INSTANCE;
                Result.Companion companion2 = Result.INSTANCE;
                continuation2.resumeWith(Result.m37constructorimpl(unit2));
            }
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    final /* synthetic */ Object awaitValue(SharedFlowSlot sharedFlowSlot, Continuation<? super Unit> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        synchronized (this) {
            if (tryPeekLocked(sharedFlowSlot) < 0) {
                sharedFlowSlot.cont = cancellableContinuationImpl2;
                sharedFlowSlot.cont = cancellableContinuationImpl2;
            } else {
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuationImpl2.resumeWith(Result.m37constructorimpl(unit));
            }
            Unit unit2 = Unit.INSTANCE;
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
