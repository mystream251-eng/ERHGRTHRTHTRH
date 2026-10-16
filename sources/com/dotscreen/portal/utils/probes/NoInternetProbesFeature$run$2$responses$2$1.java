package com.dotscreen.portal.utils.probes;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;

/* JADX INFO: compiled from: NoInternetProbesFeature.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Lkotlin/Triple;", "", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.dotscreen.portal.utils.probes.NoInternetProbesFeature$run$2$responses$2$1", f = "NoInternetProbesFeature.kt", i = {0}, l = {364}, m = "invokeSuspend", n = {"$this$withTimeoutOrNull"}, s = {"L$0"})
final class NoInternetProbesFeature$run$2$responses$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Triple<? extends String, ? extends String, ? extends Object>>, Object> {
    final /* synthetic */ Pair $pair;
    Object L$0;
    int label;
    private CoroutineScope p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    NoInternetProbesFeature$run$2$responses$2$1(Pair pair, Continuation continuation) {
        super(2, continuation);
        this.$pair = pair;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        NoInternetProbesFeature$run$2$responses$2$1 noInternetProbesFeature$run$2$responses$2$1 = new NoInternetProbesFeature$run$2$responses$2$1(this.$pair, completion);
        noInternetProbesFeature$run$2$responses$2$1.p$ = (CoroutineScope) obj;
        return noInternetProbesFeature$run$2$responses$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Triple<? extends String, ? extends String, ? extends Object>> continuation) {
        return ((NoInternetProbesFeature$run$2$responses$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            Deferred deferred = (Deferred) this.$pair.getFirst();
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = deferred.await(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
