package com.dotscreen.farkit.updater;

import android.content.Intent;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: UpdateManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/dotscreen/farkit/updater/UpdateManager$InstallSessionReceiver$onReceive$1$3"}, k = 3, mv = {1, 1, 16})
final class UpdateManager$InstallSessionReceiver$onReceive$$inlined$let$lambda$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Intent $intent$inlined;
    final /* synthetic */ String $message;
    final /* synthetic */ String $packageName;
    final /* synthetic */ Integer $status;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ UpdateManager.InstallSessionReceiver this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UpdateManager$InstallSessionReceiver$onReceive$$inlined$let$lambda$2(String str, Integer num, String str2, Continuation continuation, UpdateManager.InstallSessionReceiver installSessionReceiver, Intent intent) {
        super(2, continuation);
        this.$packageName = str;
        this.$status = num;
        this.$message = str2;
        this.this$0 = installSessionReceiver;
        this.$intent$inlined = intent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UpdateManager$InstallSessionReceiver$onReceive$$inlined$let$lambda$2 updateManager$InstallSessionReceiver$onReceive$$inlined$let$lambda$2 = new UpdateManager$InstallSessionReceiver$onReceive$$inlined$let$lambda$2(this.$packageName, this.$status, this.$message, completion, this.this$0, this.$intent$inlined);
        updateManager$InstallSessionReceiver$onReceive$$inlined$let$lambda$2.p$ = (CoroutineScope) obj;
        return updateManager$InstallSessionReceiver$onReceive$$inlined$let$lambda$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UpdateManager$InstallSessionReceiver$onReceive$$inlined$let$lambda$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        for (UpdateManager.IUpdateManagerListener iUpdateManagerListener : UpdateManager.this.getSafeListeners()) {
            String str = this.$packageName;
            String str2 = "";
            if (str == null) {
                str = "";
            }
            int i = -this.$status.intValue();
            String str3 = this.$message;
            if (str3 != null) {
                str2 = str3;
            }
            iUpdateManagerListener.onInstallFinished(str, i, new InstallFailedException(str2));
        }
        return Unit.INSTANCE;
    }
}
