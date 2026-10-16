package com.dotscreen.portal.utils;

import android.content.IntentFilter;
import android.media.tv.TvView;
import android.net.Uri;
import android.util.Log;
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

/* JADX INFO: compiled from: DVBTechnicolorManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/dotscreen/portal/utils/DVBTechnicolorManager$getAndTuneLastChannel$1$1$1", "com/dotscreen/portal/utils/DVBTechnicolorManager$$special$$inlined$use$lambda$1"}, k = 3, mv = {1, 1, 16})
final class DVBTechnicolorManager$getAndTuneLastChannel$$inlined$let$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $channelId;
    final /* synthetic */ String $inputId;
    final /* synthetic */ Uri $lastWatchedChannelUri;
    final /* synthetic */ TvView $tvView$inlined;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ DVBTechnicolorManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DVBTechnicolorManager$getAndTuneLastChannel$$inlined$let$lambda$1(long j, Uri uri, String str, Continuation continuation, DVBTechnicolorManager dVBTechnicolorManager, TvView tvView) {
        super(2, continuation);
        this.$channelId = j;
        this.$lastWatchedChannelUri = uri;
        this.$inputId = str;
        this.this$0 = dVBTechnicolorManager;
        this.$tvView$inlined = tvView;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DVBTechnicolorManager$getAndTuneLastChannel$$inlined$let$lambda$1 dVBTechnicolorManager$getAndTuneLastChannel$$inlined$let$lambda$1 = new DVBTechnicolorManager$getAndTuneLastChannel$$inlined$let$lambda$1(this.$channelId, this.$lastWatchedChannelUri, this.$inputId, completion, this.this$0, this.$tvView$inlined);
        dVBTechnicolorManager$getAndTuneLastChannel$$inlined$let$lambda$1.p$ = (CoroutineScope) obj;
        return dVBTechnicolorManager$getAndTuneLastChannel$$inlined$let$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DVBTechnicolorManager$getAndTuneLastChannel$$inlined$let$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        StringBuilder sb = new StringBuilder();
        sb.append(this.$channelId);
        sb.append(' ');
        sb.append(this.$lastWatchedChannelUri);
        Log.d("TAG", sb.toString());
        DVBTechnicolorManager.ScanBroadcastReceiver scanBroadcastReceiver = this.this$0.scanStatusReceiver;
        if (scanBroadcastReceiver != null) {
            this.$tvView$inlined.getContext().unregisterReceiver(scanBroadcastReceiver);
        }
        this.this$0.scanStatusReceiver = new DVBTechnicolorManager.ScanBroadcastReceiver(this.this$0, this.$tvView$inlined);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CommonUtils.ACTION_DVB_SCAN_FINISHED);
        this.$tvView$inlined.getContext().registerReceiver(this.this$0.scanStatusReceiver, intentFilter);
        this.$tvView$inlined.tune(this.$inputId, this.$lastWatchedChannelUri);
        this.$tvView$inlined.setStreamVolume(0.0f);
        return Unit.INSTANCE;
    }
}
