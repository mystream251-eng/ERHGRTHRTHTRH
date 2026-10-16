package kotlinx.coroutines.flow;

import com.dotscreen.tv.common.utils.notifications.NotificationsContract;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: compiled from: SharingStarted.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u000bH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/flow/StartedWhileSubscribed;", "Lkotlinx/coroutines/flow/SharingStarted;", "stopTimeout", "", "replayExpiration", "(JJ)V", "command", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/SharingCommand;", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", "", "equals", "", "other", "", "hashCode", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
final class StartedWhileSubscribed implements SharingStarted {
    private final long replayExpiration;
    private final long stopTimeout;

    public StartedWhileSubscribed(long j, long j2) {
        this.stopTimeout = j;
        this.replayExpiration = j2;
        if (!(j >= 0)) {
            throw new IllegalArgumentException(("stopTimeout(" + this.stopTimeout + " ms) cannot be negative").toString());
        }
        if (this.replayExpiration >= 0) {
            return;
        }
        throw new IllegalArgumentException(("replayExpiration(" + this.replayExpiration + " ms) cannot be negative").toString());
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.StartedWhileSubscribed$command$1, reason: invalid class name */
    /* JADX INFO: compiled from: SharingStarted.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlinx/coroutines/flow/SharingCommand;", NotificationsContract.COLUMN_COUNT, "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
    @DebugMetadata(c = "kotlinx.coroutines.flow.StartedWhileSubscribed$command$1", f = "SharingStarted.kt", i = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4}, l = {179, 181, 183, 184, 186}, m = "invokeSuspend", n = {"$this$transformLatest", NotificationsContract.COLUMN_COUNT, "$this$transformLatest", NotificationsContract.COLUMN_COUNT, "$this$transformLatest", NotificationsContract.COLUMN_COUNT, "$this$transformLatest", NotificationsContract.COLUMN_COUNT, "$this$transformLatest", NotificationsContract.COLUMN_COUNT}, s = {"L$0", "I$0", "L$0", "I$0", "L$0", "I$0", "L$0", "I$0", "L$0", "I$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function3<FlowCollector<? super SharingCommand>, Integer, Continuation<? super Unit>, Object> {
        int I$0;
        Object L$0;
        int label;
        private FlowCollector p$;
        private int p$0;

        AnonymousClass1(Continuation continuation) {
            super(3, continuation);
        }

        public final Continuation<Unit> create(FlowCollector<? super SharingCommand> flowCollector, int i, Continuation<? super Unit> continuation) {
            AnonymousClass1 anonymousClass1 = StartedWhileSubscribed.this.new AnonymousClass1(continuation);
            anonymousClass1.p$ = flowCollector;
            anonymousClass1.p$0 = i;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super SharingCommand> flowCollector, Integer num, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(flowCollector, num.intValue(), continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:27:0x007f  */
        /* JADX WARN: Code duplicated, block: B:29:0x008d A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:30:0x008e  */
        /* JADX WARN: Code duplicated, block: B:33:0x00a1 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:34:0x00a2  */
        /* JADX WARN: Code duplicated, block: B:38:0x00b2 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            int i;
            FlowCollector flowCollector;
            SharingCommand sharingCommand;
            FlowCollector flowCollector2;
            long j;
            FlowCollector flowCollector3;
            SharingCommand sharingCommand2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 == 2) {
                        i = this.I$0;
                        flowCollector = (FlowCollector) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        if (StartedWhileSubscribed.this.replayExpiration > 0) {
                            sharingCommand = SharingCommand.STOP;
                            this.L$0 = flowCollector;
                            this.I$0 = i;
                            this.label = 3;
                            if (flowCollector.emit(sharingCommand, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            flowCollector2 = flowCollector;
                            j = StartedWhileSubscribed.this.replayExpiration;
                            this.L$0 = flowCollector2;
                            this.I$0 = i;
                            this.label = 4;
                            if (DelayKt.delay(j, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            flowCollector3 = flowCollector2;
                        }
                        sharingCommand2 = SharingCommand.STOP_AND_RESET_REPLAY_CACHE;
                        this.L$0 = flowCollector;
                        this.I$0 = i;
                        this.label = 5;
                        if (flowCollector.emit(sharingCommand2, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i2 == 3) {
                        i = this.I$0;
                        flowCollector2 = (FlowCollector) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        j = StartedWhileSubscribed.this.replayExpiration;
                        this.L$0 = flowCollector2;
                        this.I$0 = i;
                        this.label = 4;
                        if (DelayKt.delay(j, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        flowCollector3 = flowCollector2;
                    } else if (i2 == 4) {
                        i = this.I$0;
                        flowCollector3 = (FlowCollector) this.L$0;
                        ResultKt.throwOnFailure(obj);
                    } else if (i2 != 5) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    flowCollector = flowCollector3;
                    sharingCommand2 = SharingCommand.STOP_AND_RESET_REPLAY_CACHE;
                    this.L$0 = flowCollector;
                    this.I$0 = i;
                    this.label = 5;
                    if (flowCollector.emit(sharingCommand2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                ResultKt.throwOnFailure(obj);
            } else {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector4 = this.p$;
                i = this.p$0;
                if (i <= 0) {
                    long j2 = StartedWhileSubscribed.this.stopTimeout;
                    this.L$0 = flowCollector4;
                    this.I$0 = i;
                    this.label = 2;
                    if (DelayKt.delay(j2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowCollector = flowCollector4;
                    if (StartedWhileSubscribed.this.replayExpiration > 0) {
                        sharingCommand = SharingCommand.STOP;
                        this.L$0 = flowCollector;
                        this.I$0 = i;
                        this.label = 3;
                        if (flowCollector.emit(sharingCommand, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        flowCollector2 = flowCollector;
                        j = StartedWhileSubscribed.this.replayExpiration;
                        this.L$0 = flowCollector2;
                        this.I$0 = i;
                        this.label = 4;
                        if (DelayKt.delay(j, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        flowCollector3 = flowCollector2;
                        flowCollector = flowCollector3;
                    }
                    sharingCommand2 = SharingCommand.STOP_AND_RESET_REPLAY_CACHE;
                    this.L$0 = flowCollector;
                    this.I$0 = i;
                    this.label = 5;
                    if (flowCollector.emit(sharingCommand2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    SharingCommand sharingCommand3 = SharingCommand.START;
                    this.L$0 = flowCollector4;
                    this.I$0 = i;
                    this.label = 1;
                    if (flowCollector4.emit(sharingCommand3, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
            return Unit.INSTANCE;
        }
    }

    @Override // kotlinx.coroutines.flow.SharingStarted
    public Flow<SharingCommand> command(StateFlow<Integer> subscriptionCount) {
        return FlowKt.distinctUntilChanged(FlowKt.dropWhile(FlowKt.transformLatest(subscriptionCount, new AnonymousClass1(null)), new AnonymousClass2(null)));
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.StartedWhileSubscribed$command$2, reason: invalid class name */
    /* JADX INFO: compiled from: SharingStarted.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lkotlinx/coroutines/flow/SharingCommand;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
    @DebugMetadata(c = "kotlinx.coroutines.flow.StartedWhileSubscribed$command$2", f = "SharingStarted.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<SharingCommand, Continuation<? super Boolean>, Object> {
        int label;
        private SharingCommand p$0;

        AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation);
            anonymousClass2.p$0 = (SharingCommand) obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SharingCommand sharingCommand, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass2) create(sharingCommand, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(this.p$0 != SharingCommand.START);
        }
    }

    public String toString() {
        List listCreateListBuilder = CollectionsKt.createListBuilder(2);
        if (this.stopTimeout > 0) {
            listCreateListBuilder.add("stopTimeout=" + this.stopTimeout + "ms");
        }
        if (this.replayExpiration < Long.MAX_VALUE) {
            listCreateListBuilder.add("replayExpiration=" + this.replayExpiration + "ms");
        }
        return "SharingStarted.WhileSubscribed(" + CollectionsKt.joinToString$default(CollectionsKt.build(listCreateListBuilder), null, null, null, 0, null, null, 63, null) + ')';
    }

    public boolean equals(Object other) {
        if (other instanceof StartedWhileSubscribed) {
            StartedWhileSubscribed startedWhileSubscribed = (StartedWhileSubscribed) other;
            if (this.stopTimeout == startedWhileSubscribed.stopTimeout && this.replayExpiration == startedWhileSubscribed.replayExpiration) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (Long.valueOf(this.stopTimeout).hashCode() * 31) + Long.valueOf(this.replayExpiration).hashCode();
    }
}
