package kotlinx.coroutines.flow.internal;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;

/* JADX INFO: compiled from: Combine.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\u00020\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/internal/CombineKt$zipImpl$1$1"}, k = 3, mv = {1, 4, 0})
final class CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FlowCollector $this_unsafeFlow;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ CombineKt$zipImpl$$inlined$unsafeFlow$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1(FlowCollector flowCollector, Continuation continuation, CombineKt$zipImpl$$inlined$unsafeFlow$1 combineKt$zipImpl$$inlined$unsafeFlow$1) {
        super(2, continuation);
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = combineKt$zipImpl$$inlined$unsafeFlow$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 = new CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1(this.$this_unsafeFlow, continuation, this.this$0);
        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.p$ = (CoroutineScope) obj;
        return combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: Combine.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u00020\u00060\u0005H\u008a@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "Lkotlinx/coroutines/channels/ProducerScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/internal/CombineKt$zipImpl$1$1$second$1"}, k = 3, mv = {1, 4, 0})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<ProducerScope<? super Object>, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;
        private ProducerScope p$;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.this.new AnonymousClass1(continuation);
            anonymousClass1.p$ = (ProducerScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super Object> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final ProducerScope producerScope = this.p$;
                Flow flow = CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.this.this$0.$flow2$inlined;
                FlowCollector flowCollector = new FlowCollector<T2>() { // from class: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$.inlined.unsafeFlow.1.lambda.1.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object obj2, Continuation continuation) {
                        SendChannel channel = producerScope.getChannel();
                        if (obj2 == null) {
                            obj2 = NullSurrogateKt.NULL;
                        }
                        Object objSend = channel.send(obj2, continuation);
                        return objSend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSend : Unit.INSTANCE;
                    }
                };
                this.L$0 = producerScope;
                this.L$1 = flow;
                this.label = 1;
                if (flow.collect(flowCollector, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v15, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v2, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v7 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        ReceiveChannel receiveChannel;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r1 = this.label;
        try {
            if (r1 != 0) {
                if (r1 == 1) {
                    receiveChannel = (ReceiveChannel) this.L$1;
                    try {
                        ResultKt.throwOnFailure(obj);
                        r1 = receiveChannel;
                    } catch (AbortFlowException e) {
                        e = e;
                        FlowExceptions_commonKt.checkOwnership(e, this.$this_unsafeFlow);
                        r1 = receiveChannel;
                    }
                    ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) r1, (CancellationException) null, 1, (Object) null);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            ReceiveChannel receiveChannelProduce$default = ProduceKt.produce$default(coroutineScope, null, 0, new AnonymousClass1(null), 3, null);
            final CompletableJob completableJobJob$default = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
            if (receiveChannelProduce$default != null) {
                ((SendChannel) receiveChannelProduce$default).invokeOnClose(new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                        invoke2(th);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Throwable th) {
                        if (completableJobJob$default.isActive()) {
                            completableJobJob$default.cancel((CancellationException) new AbortFlowException(CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.this.$this_unsafeFlow));
                        }
                    }
                });
                try {
                    CoroutineContext coroutineContext = coroutineScope.getCoroutineContext();
                    Object objThreadContextElements = ThreadContextKt.threadContextElements(coroutineContext);
                    CoroutineContext coroutineContextPlus = coroutineScope.getCoroutineContext().plus(completableJobJob$default);
                    Unit unit = Unit.INSTANCE;
                    AnonymousClass3 anonymousClass3 = new AnonymousClass3(coroutineContext, objThreadContextElements, receiveChannelProduce$default, null);
                    this.L$0 = coroutineScope;
                    this.L$1 = receiveChannelProduce$default;
                    this.L$2 = completableJobJob$default;
                    this.L$3 = coroutineContext;
                    this.L$4 = objThreadContextElements;
                    this.label = 1;
                    if (ChannelFlowKt.withContextUndispatched$default(coroutineContextPlus, unit, null, anonymousClass3, this, 4, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    r1 = receiveChannelProduce$default;
                    ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) r1, (CancellationException) null, 1, (Object) null);
                    return Unit.INSTANCE;
                } catch (AbortFlowException e2) {
                    e = e2;
                    receiveChannel = receiveChannelProduce$default;
                    FlowExceptions_commonKt.checkOwnership(e, this.$this_unsafeFlow);
                    r1 = receiveChannel;
                } catch (Throwable th) {
                    th = th;
                    r1 = receiveChannelProduce$default;
                    ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) r1, (CancellationException) null, 1, (Object) null);
                    throw th;
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.SendChannel<*>");
            }
            FlowExceptions_commonKt.checkOwnership(e, this.$this_unsafeFlow);
            r1 = receiveChannel;
            ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) r1, (CancellationException) null, 1, (Object) null);
            return Unit.INSTANCE;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1$3, reason: invalid class name */
    /* JADX INFO: compiled from: Combine.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "it", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/internal/CombineKt$zipImpl$1$1$2"}, k = 3, mv = {1, 4, 0})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
        final /* synthetic */ Object $cnt;
        final /* synthetic */ CoroutineContext $scopeContext;
        final /* synthetic */ ReceiveChannel $second;
        Object L$0;
        Object L$1;
        int label;
        private Unit p$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(CoroutineContext coroutineContext, Object obj, ReceiveChannel receiveChannel, Continuation continuation) {
            super(2, continuation);
            this.$scopeContext = coroutineContext;
            this.$cnt = obj;
            this.$second = receiveChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.this.new AnonymousClass3(this.$scopeContext, this.$cnt, this.$second, continuation);
            anonymousClass3.p$0 = (Unit) obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Unit unit, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Unit unit = this.p$0;
                Flow flow = CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.this.this$0.$flow$inlined;
                FlowCollector flowCollector = new FlowCollector<T1>() { // from class: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$.inlined.unsafeFlow.1.lambda.1.3.1

                    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1$3$1$1, reason: invalid class name and collision with other inner class name */
                    /* JADX INFO: compiled from: Combine.kt */
                    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "it", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/internal/CombineKt$zipImpl$1$1$2$1$1", "kotlinx/coroutines/flow/internal/CombineKt$zipImpl$1$1$2$invokeSuspend$$inlined$collect$1$lambda$1"}, k = 3, mv = {1, 4, 0})
                    static final class C01931 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
                        final /* synthetic */ Object $value;
                        Object L$0;
                        Object L$1;
                        Object L$2;
                        int label;
                        private Unit p$0;
                        final /* synthetic */ AnonymousClass1 this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C01931(Object obj, Continuation continuation, AnonymousClass1 anonymousClass1) {
                            super(2, continuation);
                            this.$value = obj;
                            this.this$0 = anonymousClass1;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            C01931 c01931 = new C01931(this.$value, continuation, this.this$0);
                            c01931.p$0 = (Unit) obj;
                            return c01931;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Unit unit, Continuation<? super Unit> continuation) {
                            return ((C01931) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        /* JADX WARN: Code duplicated, block: B:27:0x009a A[RETURN] */
                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            Unit unit;
                            FlowCollector flowCollector;
                            Object obj2;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                Unit unit2 = this.p$0;
                                ReceiveChannel receiveChannel = AnonymousClass3.this.$second;
                                this.L$0 = unit2;
                                this.label = 1;
                                Object objReceiveOrNull = ChannelsKt.receiveOrNull(receiveChannel, this);
                                if (objReceiveOrNull == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                unit = unit2;
                                obj = objReceiveOrNull;
                            } else {
                                if (i == 1) {
                                    Unit unit3 = (Unit) this.L$0;
                                    ResultKt.throwOnFailure(obj);
                                    unit = unit3;
                                } else if (i == 2) {
                                    flowCollector = (FlowCollector) this.L$2;
                                    obj2 = this.L$1;
                                    unit = (Unit) this.L$0;
                                    ResultKt.throwOnFailure(obj);
                                    this.L$0 = unit;
                                    this.L$1 = obj2;
                                    this.label = 3;
                                    if (flowCollector.emit(obj, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                } else {
                                    if (i != 3) {
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    ResultKt.throwOnFailure(obj);
                                }
                                return Unit.INSTANCE;
                            }
                            if (obj == null) {
                                throw new AbortFlowException(CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.this.$this_unsafeFlow);
                            }
                            flowCollector = CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.this.$this_unsafeFlow;
                            Function3 function3 = CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.this.this$0.$transform$inlined;
                            Object obj3 = this.$value;
                            Object obj4 = obj == NullSurrogateKt.NULL ? null : obj;
                            this.L$0 = unit;
                            this.L$1 = obj;
                            this.L$2 = flowCollector;
                            this.label = 2;
                            InlineMarker.mark(6);
                            InlineMarker.mark(6);
                            Object objInvoke = function3.invoke(obj3, obj4, this);
                            InlineMarker.mark(7);
                            InlineMarker.mark(7);
                            if (objInvoke == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            obj2 = obj;
                            obj = objInvoke;
                            this.L$0 = unit;
                            this.L$1 = obj2;
                            this.label = 3;
                            if (flowCollector.emit(obj, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object obj2, Continuation continuation) {
                        Object objWithContextUndispatched = ChannelFlowKt.withContextUndispatched(AnonymousClass3.this.$scopeContext, Unit.INSTANCE, AnonymousClass3.this.$cnt, new C01931(obj2, null, this), continuation);
                        return objWithContextUndispatched == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContextUndispatched : Unit.INSTANCE;
                    }
                };
                this.L$0 = unit;
                this.L$1 = flow;
                this.label = 1;
                if (flow.collect(flowCollector, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }
}
