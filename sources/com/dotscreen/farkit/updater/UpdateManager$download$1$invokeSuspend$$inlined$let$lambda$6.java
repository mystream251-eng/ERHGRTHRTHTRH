package com.dotscreen.farkit.updater;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: UpdateManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/dotscreen/farkit/updater/UpdateManager$download$1$4$3"}, k = 3, mv = {1, 1, 16})
final class UpdateManager$download$1$invokeSuspend$$inlined$let$lambda$6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef $session$inlined;
    final /* synthetic */ Ref.ObjectRef $timeoutJob$inlined;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ UpdateManager.C02661 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UpdateManager$download$1$invokeSuspend$$inlined$let$lambda$6(Continuation continuation, UpdateManager.C02661 c02661, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2) {
        super(2, continuation);
        this.this$0 = c02661;
        this.$session$inlined = objectRef;
        this.$timeoutJob$inlined = objectRef2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UpdateManager$download$1$invokeSuspend$$inlined$let$lambda$6 updateManager$download$1$invokeSuspend$$inlined$let$lambda$6 = new UpdateManager$download$1$invokeSuspend$$inlined$let$lambda$6(completion, this.this$0, this.$session$inlined, this.$timeoutJob$inlined);
        updateManager$download$1$invokeSuspend$$inlined$let$lambda$6.p$ = (CoroutineScope) obj;
        return updateManager$download$1$invokeSuspend$$inlined$let$lambda$6;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UpdateManager$download$1$invokeSuspend$$inlined$let$lambda$6) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Iterator it = UpdateManager.this.getSafeListeners().iterator();
        while (it.hasNext()) {
            UpdateManager.IUpdateManagerListener.DefaultImpls.onDownloadFinished$default((UpdateManager.IUpdateManagerListener) it.next(), this.this$0.$packageName, 0, null, 4, null);
        }
        return Unit.INSTANCE;
    }
}
