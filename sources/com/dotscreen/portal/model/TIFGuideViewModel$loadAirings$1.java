package com.dotscreen.portal.model;

import android.database.Cursor;
import android.media.tv.TvContract;
import android.net.Uri;
import androidx.lifecycle.MutableLiveData;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.TVAiring;
import com.google.android.media.tv.companionlibrary.model.Program;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
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

/* JADX INFO: compiled from: GuideViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.dotscreen.portal.model.TIFGuideViewModel$loadAirings$1", f = "GuideViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class TIFGuideViewModel$loadAirings$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableLiveData $data;
    final /* synthetic */ int $time;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TIFGuideViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TIFGuideViewModel$loadAirings$1(TIFGuideViewModel tIFGuideViewModel, int i, MutableLiveData mutableLiveData, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tIFGuideViewModel;
        this.$time = i;
        this.$data = mutableLiveData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TIFGuideViewModel$loadAirings$1 tIFGuideViewModel$loadAirings$1 = new TIFGuideViewModel$loadAirings$1(this.this$0, this.$time, this.$data, completion);
        tIFGuideViewModel$loadAirings$1.p$ = (CoroutineScope) obj;
        return tIFGuideViewModel$loadAirings$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TIFGuideViewModel$loadAirings$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws IOException {
        Cursor cursorQuery;
        List listSortedWith;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        HashMap map = new HashMap();
        int i = this.$time;
        if (i == 6) {
            Calendar end = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(end, "end");
            end.setTime(new Date());
            end.set(10, 23);
            end.set(12, 59);
            end.set(13, 59);
            end.add(5, 0);
            Calendar start = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(start, "start");
            start.setTime(new Date());
            start.add(5, -2);
            Uri.Builder builderBuildUpon = TvContract.Programs.CONTENT_URI.buildUpon();
            Date time = start.getTime();
            Intrinsics.checkExpressionValueIsNotNull(time, "start.time");
            Uri.Builder builderAppendQueryParameter = builderBuildUpon.appendQueryParameter(TvContractCompat.PARAM_START_TIME, String.valueOf(time.getTime()));
            Date time2 = end.getTime();
            Intrinsics.checkExpressionValueIsNotNull(time2, "end.time");
            cursorQuery = this.this$0.context.getContentResolver().query(builderAppendQueryParameter.appendQueryParameter(TvContractCompat.PARAM_END_TIME, String.valueOf(time2.getTime())).build(), Program.PROJECTION, null, null, null);
        } else if (i != 7) {
            cursorQuery = null;
        } else {
            Calendar start2 = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(start2, "start");
            start2.setTime(new Date());
            start2.set(11, 0);
            start2.set(12, 0);
            start2.set(13, 0);
            start2.add(5, 1);
            Uri.Builder builderBuildUpon2 = TvContract.Programs.CONTENT_URI.buildUpon();
            Date time3 = start2.getTime();
            Intrinsics.checkExpressionValueIsNotNull(time3, "start.time");
            Uri.Builder builderAppendQueryParameter2 = builderBuildUpon2.appendQueryParameter(TvContractCompat.PARAM_START_TIME, String.valueOf(time3.getTime()));
            Date time4 = GuideViewModel.INSTANCE.getLastDay().getTime();
            Intrinsics.checkExpressionValueIsNotNull(time4, "lastDay.time");
            cursorQuery = this.this$0.context.getContentResolver().query(builderAppendQueryParameter2.appendQueryParameter(TvContractCompat.PARAM_END_TIME, String.valueOf(time4.getTime())).build(), Program.PROJECTION, null, null, null);
        }
        if (cursorQuery != null) {
            Cursor cursor = cursorQuery;
            Throwable th = (Throwable) null;
            try {
                Cursor cursor2 = cursor;
                while (cursor2.moveToNext()) {
                    Program tifProgram = Program.fromCursor(cursor2);
                    TIFGuideViewModel tIFGuideViewModel = this.this$0;
                    Intrinsics.checkExpressionValueIsNotNull(tifProgram, "tifProgram");
                    TVAiring tVAiring = tIFGuideViewModel.toTVAiring(tifProgram);
                    if (tVAiring != null) {
                        ArrayList arrayList = (List) map.get(tVAiring.getChannel().getId());
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                            map.put(tVAiring.getChannel().getId(), arrayList);
                        }
                        Boxing.boxBoolean(arrayList.add(tVAiring));
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
        Set<Map.Entry> setEntrySet = map.entrySet();
        Intrinsics.checkExpressionValueIsNotNull(setEntrySet, "airings.entries");
        for (Map.Entry entry : setEntrySet) {
            List list = (List) entry.getValue();
            entry.setValue((list == null || (listSortedWith = CollectionsKt.sortedWith(list, new Comparator<T>() { // from class: com.dotscreen.portal.model.TIFGuideViewModel$loadAirings$1$$special$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(((TVAiring) t).getStartDate(), ((TVAiring) t2).getStartDate());
                }
            })) == null) ? null : CollectionsKt.toMutableList((Collection) listSortedWith));
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass3(map, null), 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.model.TIFGuideViewModel$loadAirings$1$3, reason: invalid class name */
    /* JADX INFO: compiled from: GuideViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.portal.model.TIFGuideViewModel$loadAirings$1$3", f = "GuideViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ HashMap $airings;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(HashMap map, Continuation continuation) {
            super(2, continuation);
            this.$airings = map;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass3 anonymousClass3 = TIFGuideViewModel$loadAirings$1.this.new AnonymousClass3(this.$airings, completion);
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
            MutableLiveData mutableLiveData = TIFGuideViewModel$loadAirings$1.this.$data;
            TIFGuideViewModel tIFGuideViewModel = TIFGuideViewModel$loadAirings$1.this.this$0;
            List<Channel> channels = TIFGuideViewModel$loadAirings$1.this.this$0.getChannels();
            if (channels == null) {
                Intrinsics.throwNpe();
            }
            mutableLiveData.postValue(new GuideViewModel.Data(tIFGuideViewModel, channels, this.$airings));
            return Unit.INSTANCE;
        }
    }
}
