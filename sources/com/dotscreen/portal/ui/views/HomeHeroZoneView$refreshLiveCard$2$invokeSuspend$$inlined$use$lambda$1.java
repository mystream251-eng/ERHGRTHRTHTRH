package com.dotscreen.portal.ui.views;

import android.net.Uri;
import androidx.leanback.widget.ArrayObjectAdapter;
import com.dotscreen.tv.common.utils.CommonUtils;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: HomeHeroZoneView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/dotscreen/portal/ui/views/HomeHeroZoneView$refreshLiveCard$2$2$1"}, k = 3, mv = {1, 1, 16})
final class HomeHeroZoneView$refreshLiveCard$2$invokeSuspend$$inlined$use$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    private CoroutineScope p$;
    final /* synthetic */ HomeHeroZoneView.AnonymousClass2 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HomeHeroZoneView$refreshLiveCard$2$invokeSuspend$$inlined$use$lambda$1(Continuation continuation, HomeHeroZoneView.AnonymousClass2 anonymousClass2) {
        super(2, continuation);
        this.this$0 = anonymousClass2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HomeHeroZoneView$refreshLiveCard$2$invokeSuspend$$inlined$use$lambda$1 homeHeroZoneView$refreshLiveCard$2$invokeSuspend$$inlined$use$lambda$1 = new HomeHeroZoneView$refreshLiveCard$2$invokeSuspend$$inlined$use$lambda$1(completion, this.this$0);
        homeHeroZoneView$refreshLiveCard$2$invokeSuspend$$inlined$use$lambda$1.p$ = (CoroutineScope) obj;
        return homeHeroZoneView$refreshLiveCard$2$invokeSuspend$$inlined$use$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HomeHeroZoneView$refreshLiveCard$2$invokeSuspend$$inlined$use$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ArrayObjectAdapter arrayObjectAdapter;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ArrayObjectAdapter arrayObjectAdapter2 = HomeHeroZoneView.this.adapter;
        if ((arrayObjectAdapter2 == null || arrayObjectAdapter2.size() != 0) && !CommonUtils.isTypeSTB(HomeHeroZoneView.this.getContext(), "OTT STB") && (arrayObjectAdapter = HomeHeroZoneView.this.adapter) != null) {
            arrayObjectAdapter.replace(0, (Uri) this.this$0.$lastWatchedChannelUri.element);
        }
        this.this$0.$updateEPGData$1.invoke2();
        return Unit.INSTANCE;
    }
}
