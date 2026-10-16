package com.dotscreen.portal.ui.screens.guide;

import com.dotscreen.bokit.model.Channel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: GuideActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¶\u0001\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\b"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/dotscreen/portal/ui/screens/guide/GuideActivity$onRestart$1$1$1$1$1$1", "com/dotscreen/portal/ui/screens/guide/GuideActivity$onRestart$1$$special$$inlined$let$lambda$1", "com/dotscreen/portal/ui/screens/guide/GuideActivity$onRestart$1$$special$$inlined$use$lambda$1", "com/dotscreen/portal/ui/screens/guide/GuideActivity$onRestart$1$$special$$inlined$use$lambda$2"}, k = 3, mv = {1, 1, 16})
final class GuideActivity$onRestart$1$invokeSuspend$$inlined$let$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Channel $lastWatchedChannel;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ GuideActivity.C03851 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GuideActivity$onRestart$1$invokeSuspend$$inlined$let$lambda$1(Channel channel, Continuation continuation, GuideActivity.C03851 c03851) {
        super(2, continuation);
        this.$lastWatchedChannel = channel;
        this.this$0 = c03851;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        GuideActivity$onRestart$1$invokeSuspend$$inlined$let$lambda$1 guideActivity$onRestart$1$invokeSuspend$$inlined$let$lambda$1 = new GuideActivity$onRestart$1$invokeSuspend$$inlined$let$lambda$1(this.$lastWatchedChannel, completion, this.this$0);
        guideActivity$onRestart$1$invokeSuspend$$inlined$let$lambda$1.p$ = (CoroutineScope) obj;
        return guideActivity$onRestart$1$invokeSuspend$$inlined$let$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GuideActivity$onRestart$1$invokeSuspend$$inlined$let$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        GuideActivity.this.selectChannelByZappingNumber(this.$lastWatchedChannel.getZappingNumber());
        return Unit.INSTANCE;
    }
}
