package com.dotscreen.portal.ui.cards;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.google.android.media.tv.companionlibrary.model.InternalProviderData;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: LiveCardPresenter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/dotscreen/portal/ui/cards/LiveCardPresenter$onBindViewHolder$1$1"}, k = 3, mv = {1, 1, 16})
final class LiveCardPresenter$onBindViewHolder$$inlined$let$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ LiveCardView $cardView$inlined;
    final /* synthetic */ Object $item$inlined;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ LiveCardPresenter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LiveCardPresenter$onBindViewHolder$$inlined$let$lambda$1(Continuation continuation, LiveCardPresenter liveCardPresenter, Object obj, LiveCardView liveCardView) {
        super(2, continuation);
        this.this$0 = liveCardPresenter;
        this.$item$inlined = obj;
        this.$cardView$inlined = liveCardView;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LiveCardPresenter$onBindViewHolder$$inlined$let$lambda$1 liveCardPresenter$onBindViewHolder$$inlined$let$lambda$1 = new LiveCardPresenter$onBindViewHolder$$inlined$let$lambda$1(completion, this.this$0, this.$item$inlined, this.$cardView$inlined);
        liveCardPresenter$onBindViewHolder$$inlined$let$lambda$1.p$ = (CoroutineScope) obj;
        return liveCardPresenter$onBindViewHolder$$inlined$let$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LiveCardPresenter$onBindViewHolder$$inlined$let$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ContentResolver contentResolver;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        try {
            String[] strArr = {"input_id", "internal_provider_data"};
            Context context = this.this$0.context;
            Cursor cursorQuery = (context == null || (contentResolver = context.getContentResolver()) == null) ? null : contentResolver.query((Uri) this.$item$inlined, strArr, null, null, null);
            Throwable th = (Throwable) null;
            try {
                Cursor cursor = cursorQuery;
                if (cursor != null && cursor.moveToNext()) {
                    String string = cursor.getString(0);
                    Intrinsics.checkExpressionValueIsNotNull(string, "c.getString(index++)");
                    byte[] blob = cursor.getBlob(1);
                    Intrinsics.checkExpressionValueIsNotNull(blob, "c.getBlob(index++)");
                    Bundle bundle = new Bundle();
                    if (CommonUtils.getCurrentPlatform() == 1) {
                        try {
                            bundle.putString("net.oregan.tvinput.multicast.MulticastService.uri", new InternalProviderData(blob).getVideoUrl());
                        } catch (Exception unused) {
                        }
                    }
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(string, bundle, null, this), 2, null);
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursorQuery, th);
            } catch (Throwable th2) {
                try {
                    throw th2;
                } catch (Throwable th3) {
                    CloseableKt.closeFinally(cursorQuery, th2);
                    throw th3;
                }
            }
        } catch (Exception e) {
            Log.w(this.this$0.TAG, "Error querying channels", e);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.cards.LiveCardPresenter$onBindViewHolder$$inlined$let$lambda$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: LiveCardPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/dotscreen/portal/ui/cards/LiveCardPresenter$onBindViewHolder$1$1$1$1", "com/dotscreen/portal/ui/cards/LiveCardPresenter$onBindViewHolder$1$1$invokeSuspend$$inlined$use$lambda$1"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $inputId;
        final /* synthetic */ Bundle $params;
        int label;
        private CoroutineScope p$;
        final /* synthetic */ LiveCardPresenter$onBindViewHolder$$inlined$let$lambda$1 this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Bundle bundle, Continuation continuation, LiveCardPresenter$onBindViewHolder$$inlined$let$lambda$1 liveCardPresenter$onBindViewHolder$$inlined$let$lambda$1) {
            super(2, continuation);
            this.$inputId = str;
            this.$params = bundle;
            this.this$0 = liveCardPresenter$onBindViewHolder$$inlined$let$lambda$1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$inputId, this.$params, completion, this.this$0);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.this$0.$cardView$inlined.tune(this.$inputId, (Uri) this.this$0.$item$inlined, this.$params);
            return Unit.INSTANCE;
        }
    }
}
