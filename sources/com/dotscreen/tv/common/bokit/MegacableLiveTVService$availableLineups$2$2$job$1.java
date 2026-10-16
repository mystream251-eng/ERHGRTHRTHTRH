package com.dotscreen.tv.common.bokit;

import io.reactivex.ObservableEmitter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MegacableLiveTVService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.dotscreen.tv.common.bokit.MegacableLiveTVService$availableLineups$2$2$job$1", f = "MegacableLiveTVService.kt", i = {0, 0, 1, 1}, l = {326, 329}, m = "invokeSuspend", n = {"$this$launch", "delay", "$this$launch", "delay"}, s = {"L$0", "J$0", "L$0", "J$0"})
final class MegacableLiveTVService$availableLineups$2$2$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ObservableEmitter $observer;
    long J$0;
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ MegacableLiveTVService$availableLineups$2.AnonymousClass2 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MegacableLiveTVService$availableLineups$2$2$job$1(MegacableLiveTVService$availableLineups$2.AnonymousClass2 anonymousClass2, ObservableEmitter observableEmitter, Continuation continuation) {
        super(2, continuation);
        this.this$0 = anonymousClass2;
        this.$observer = observableEmitter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MegacableLiveTVService$availableLineups$2$2$job$1 megacableLiveTVService$availableLineups$2$2$job$1 = new MegacableLiveTVService$availableLineups$2$2$job$1(this.this$0, this.$observer, completion);
        megacableLiveTVService$availableLineups$2$2$job$1.p$ = (CoroutineScope) obj;
        return megacableLiveTVService$availableLineups$2$2$job$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MegacableLiveTVService$availableLineups$2$2$job$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x00a8 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:16:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:18:0x010d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x011a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x010b -> B:19:0x010e). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x011a -> B:21:0x011d). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:9:0x002a
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r17) {
        /*
            Method dump skipped, instruction units count: 299
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dotscreen.tv.common.bokit.MegacableLiveTVService$availableLineups$2$2$job$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
