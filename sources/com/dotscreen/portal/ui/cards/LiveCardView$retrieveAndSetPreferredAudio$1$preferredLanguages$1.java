package com.dotscreen.portal.ui.cards;

import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.farkit.tools.LanguagePreferences;
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

/* JADX INFO: compiled from: LiveCardView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.dotscreen.portal.ui.cards.LiveCardView$retrieveAndSetPreferredAudio$1$preferredLanguages$1", f = "LiveCardView.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class LiveCardView$retrieveAndSetPreferredAudio$1$preferredLanguages$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends String, ? extends String>>, Object> {
    int label;
    private CoroutineScope p$;
    final /* synthetic */ LiveCardView.C03361 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LiveCardView$retrieveAndSetPreferredAudio$1$preferredLanguages$1(LiveCardView.C03361 c03361, Continuation continuation) {
        super(2, continuation);
        this.this$0 = c03361;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LiveCardView$retrieveAndSetPreferredAudio$1$preferredLanguages$1 liveCardView$retrieveAndSetPreferredAudio$1$preferredLanguages$1 = new LiveCardView$retrieveAndSetPreferredAudio$1$preferredLanguages$1(this.this$0, completion);
        liveCardView$retrieveAndSetPreferredAudio$1$preferredLanguages$1.p$ = (CoroutineScope) obj;
        return liveCardView$retrieveAndSetPreferredAudio$1$preferredLanguages$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends String, ? extends String>> continuation) {
        return ((LiveCardView$retrieveAndSetPreferredAudio$1$preferredLanguages$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Channel channel;
        String id;
        LanguagePreferences languagePreferences;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        TVAiring airing = LiveCardView.this.getAiring();
        if (airing == null || (channel = airing.getChannel()) == null || (id = channel.getId()) == null || (languagePreferences = LiveCardView.this.languagePreferences) == null) {
            return null;
        }
        return languagePreferences.retrieveAudioAndSubtitleLanguages(id);
    }
}
