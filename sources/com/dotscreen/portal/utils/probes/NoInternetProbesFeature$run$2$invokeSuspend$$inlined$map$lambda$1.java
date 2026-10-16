package com.dotscreen.portal.utils.probes;

import android.util.Log;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: NoInternetProbesFeature.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"<anonymous>", "Lkotlin/Triple;", "", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/dotscreen/portal/utils/probes/NoInternetProbesFeature$run$2$responses$1$1"}, k = 3, mv = {1, 1, 16})
final class NoInternetProbesFeature$run$2$invokeSuspend$$inlined$map$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Triple<? extends String, ? extends String, ? extends Object>>, Object> {
    final /* synthetic */ Probe $it;
    final /* synthetic */ CoroutineScope $this_coroutineScope$inlined;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ NoInternetProbesFeature.AnonymousClass2 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    NoInternetProbesFeature$run$2$invokeSuspend$$inlined$map$lambda$1(Probe probe, Continuation continuation, NoInternetProbesFeature.AnonymousClass2 anonymousClass2, CoroutineScope coroutineScope) {
        super(2, continuation);
        this.$it = probe;
        this.this$0 = anonymousClass2;
        this.$this_coroutineScope$inlined = coroutineScope;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        NoInternetProbesFeature$run$2$invokeSuspend$$inlined$map$lambda$1 noInternetProbesFeature$run$2$invokeSuspend$$inlined$map$lambda$1 = new NoInternetProbesFeature$run$2$invokeSuspend$$inlined$map$lambda$1(this.$it, completion, this.this$0, this.$this_coroutineScope$inlined);
        noInternetProbesFeature$run$2$invokeSuspend$$inlined$map$lambda$1.p$ = (CoroutineScope) obj;
        return noInternetProbesFeature$run$2$invokeSuspend$$inlined$map$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Triple<? extends String, ? extends String, ? extends Object>> continuation) {
        return ((NoInternetProbesFeature$run$2$invokeSuspend$$inlined$map$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0059  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String sentryTag;
        String str;
        String str2;
        String str3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            sentryTag = this.$it.getSentryTag();
            String voPlayerTag = this.$it.getVoPlayerTag();
            try {
                Probe probe = this.$it;
                this.L$0 = coroutineScope;
                this.L$1 = sentryTag;
                this.L$2 = voPlayerTag;
                this.L$3 = sentryTag;
                this.L$4 = voPlayerTag;
                this.label = 1;
                obj = probe.launch(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                str3 = voPlayerTag;
                if (obj == null) {
                    obj = "null";
                }
            } catch (Exception e) {
                e = e;
                str = voPlayerTag;
                str2 = sentryTag;
                Log.w(NoInternetProbesFeature.this.TAG, this.$it.getSentryTag() + ": exception", e);
                obj = "error";
                str3 = str;
                sentryTag = str2;
            }
        } else if (i == 1) {
            str3 = (String) this.L$4;
            sentryTag = (String) this.L$3;
            str = (String) this.L$2;
            str2 = (String) this.L$1;
            try {
                ResultKt.throwOnFailure(obj);
                if (obj == null) {
                    obj = "null";
                }
            } catch (Exception e2) {
                e = e2;
                Log.w(NoInternetProbesFeature.this.TAG, this.$it.getSentryTag() + ": exception", e);
                obj = "error";
                str3 = str;
                sentryTag = str2;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return new Triple(sentryTag, str3, obj);
    }
}
