package com.dotscreen.portal.model;

import android.database.Cursor;
import android.media.tv.TvContract;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.google.android.media.tv.companionlibrary.model.Channel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: EPGViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.dotscreen.portal.model.TIFEPGViewModel$loadChannels$1", f = "EPGViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class TIFEPGViewModel$loadChannels$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableLiveData $data;
    final /* synthetic */ int $time;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TIFEPGViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TIFEPGViewModel$loadChannels$1(TIFEPGViewModel tIFEPGViewModel, int i, MutableLiveData mutableLiveData, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tIFEPGViewModel;
        this.$time = i;
        this.$data = mutableLiveData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TIFEPGViewModel$loadChannels$1 tIFEPGViewModel$loadChannels$1 = new TIFEPGViewModel$loadChannels$1(this.this$0, this.$time, this.$data, completion);
        tIFEPGViewModel$loadChannels$1.p$ = (CoroutineScope) obj;
        return tIFEPGViewModel$loadChannels$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TIFEPGViewModel$loadChannels$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws IOException {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ArrayList arrayList = new ArrayList();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Cursor cursorQuery = this.this$0.context.getContentResolver().query(TvContract.Channels.CONTENT_URI, Channel.PROJECTION, "(input_id IN " + CommonUtils.getAvailableTVInputIdsQueryParam(this.this$0.context) + ')', null, null);
        if (cursorQuery != null) {
            Cursor cursor = cursorQuery;
            Throwable th = (Throwable) null;
            try {
                Cursor cursor2 = cursor;
                while (cursor2.moveToNext()) {
                    Channel tifChannel = Channel.fromCursor(cursor2);
                    Log.d(EPGViewModelKt.TAG, "tifChannel: " + tifChannel);
                    TIFEPGViewModel tIFEPGViewModel = this.this$0;
                    Intrinsics.checkExpressionValueIsNotNull(tifChannel, "tifChannel");
                    RightVChannel rightVChannel = tIFEPGViewModel.toRightVChannel(tifChannel);
                    if (rightVChannel != null) {
                        Log.d(EPGViewModelKt.TAG, "  -> channel: " + rightVChannel);
                        arrayList.add(rightVChannel);
                        linkedHashMap.put(Boxing.boxLong(tifChannel.getId()), rightVChannel);
                    }
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursor, th);
            } catch (Throwable th2) {
                try {
                    throw th2;
                } catch (Throwable th3) {
                    CloseableKt.closeFinally(cursor, th2);
                    throw th3;
                }
            }
        }
        this.this$0.setChannels(CollectionsKt.toList(CollectionsKt.sortedWith(arrayList, new Comparator<T>() { // from class: com.dotscreen.portal.model.TIFEPGViewModel$loadChannels$1$invokeSuspend$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((com.dotscreen.bokit.model.Channel) t).getZappingNumber()), Integer.valueOf(((com.dotscreen.bokit.model.Channel) t2).getZappingNumber()));
            }
        })));
        this.this$0.channelsMap = MapsKt.toMap(linkedHashMap);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass3(null), 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.model.TIFEPGViewModel$loadChannels$1$3, reason: invalid class name */
    /* JADX INFO: compiled from: EPGViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.portal.model.TIFEPGViewModel$loadChannels$1$3", f = "EPGViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        AnonymousClass3(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass3 anonymousClass3 = TIFEPGViewModel$loadChannels$1.this.new AnonymousClass3(completion);
            anonymousClass3.p$ = (CoroutineScope) obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            TIFEPGViewModel$loadChannels$1.this.this$0.loadValue$MegaCable_Portal_0_75_1_396__prodRelease(TIFEPGViewModel$loadChannels$1.this.$time, TIFEPGViewModel$loadChannels$1.this.$data);
            return Unit.INSTANCE;
        }
    }
}
