package com.dotscreen.portal.model;

import androidx.lifecycle.MutableLiveData;
import com.dotscreen.bokit.model.Channel;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: EPGViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.dotscreen.portal.model.TIFEPGViewModel$loadAirings$1", f = "EPGViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class TIFEPGViewModel$loadAirings$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableLiveData $data;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TIFEPGViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TIFEPGViewModel$loadAirings$1(TIFEPGViewModel tIFEPGViewModel, MutableLiveData mutableLiveData, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tIFEPGViewModel;
        this.$data = mutableLiveData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TIFEPGViewModel$loadAirings$1 tIFEPGViewModel$loadAirings$1 = new TIFEPGViewModel$loadAirings$1(this.this$0, this.$data, completion);
        tIFEPGViewModel$loadAirings$1.p$ = (CoroutineScope) obj;
        return tIFEPGViewModel$loadAirings$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TIFEPGViewModel$loadAirings$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        MutableLiveData mutableLiveData = this.$data;
        TIFEPGViewModel tIFEPGViewModel = this.this$0;
        List<Channel> channels = tIFEPGViewModel.getChannels();
        if (channels == null) {
            Intrinsics.throwNpe();
        }
        mutableLiveData.postValue(new EPGViewModel.Data(tIFEPGViewModel, channels, CollectionsKt.emptyList()));
        return Unit.INSTANCE;
    }
}
