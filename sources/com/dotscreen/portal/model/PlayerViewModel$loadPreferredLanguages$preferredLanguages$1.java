package com.dotscreen.portal.model;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PlayerViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.dotscreen.portal.model.PlayerViewModel$loadPreferredLanguages$preferredLanguages$1", f = "PlayerViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class PlayerViewModel$loadPreferredLanguages$preferredLanguages$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends String, ? extends String>>, Object> {
    final /* synthetic */ String $currentChannelId;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlayerViewModel$loadPreferredLanguages$preferredLanguages$1(PlayerViewModel playerViewModel, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = playerViewModel;
        this.$currentChannelId = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PlayerViewModel$loadPreferredLanguages$preferredLanguages$1 playerViewModel$loadPreferredLanguages$preferredLanguages$1 = new PlayerViewModel$loadPreferredLanguages$preferredLanguages$1(this.this$0, this.$currentChannelId, completion);
        playerViewModel$loadPreferredLanguages$preferredLanguages$1.p$ = (CoroutineScope) obj;
        return playerViewModel$loadPreferredLanguages$preferredLanguages$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends String, ? extends String>> continuation) {
        return ((PlayerViewModel$loadPreferredLanguages$preferredLanguages$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return this.this$0.languagePreferences.retrieveAudioAndSubtitleLanguages(this.$currentChannelId);
    }
}
