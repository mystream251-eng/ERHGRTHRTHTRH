package com.dotscreen.tv.common.bokit;

import android.util.Log;
import com.dotscreen.farkit.rx.CachesKt;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.json.JSONObject;

/* JADX INFO: compiled from: MegacableUserService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/dotscreen/tv/common/bokit/Profile;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 16})
final class MegacableUserService$getCurrentProfile$2 extends Lambda implements Function0<Single<Profile>> {
    final /* synthetic */ MegacableUserService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MegacableUserService$getCurrentProfile$2(MegacableUserService megacableUserService) {
        super(0);
        this.this$0 = megacableUserService;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Single<Profile> invoke() {
        Observable.create(new ObservableOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$getCurrentProfile$2.1

            /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.MegacableUserService$getCurrentProfile$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: MegacableUserService.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
            @DebugMetadata(c = "com.dotscreen.tv.common.bokit.MegacableUserService$getCurrentProfile$2$1$1", f = "MegacableUserService.kt", i = {0}, l = {170}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
            static final class C01651 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ ObservableEmitter $observer;
                Object L$0;
                int label;
                private CoroutineScope p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C01651(ObservableEmitter observableEmitter, Continuation continuation) {
                    super(2, continuation);
                    this.$observer = observableEmitter;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C01651 c01651 = new C01651(this.$observer, completion);
                    c01651.p$ = (CoroutineScope) obj;
                    return c01651;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C01651) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code duplicated, block: B:11:0x003f A[RETURN] */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x003d -> B:12:0x0040). Please report as a decompilation issue!!! */
                /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                    jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:11:0x003f
                    	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                    	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                    	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                    */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final java.lang.Object invokeSuspend(java.lang.Object r6) {
                    /*
                        r5 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r1 = r5.label
                        r2 = 1
                        if (r1 == 0) goto L1c
                        if (r1 != r2) goto L14
                        java.lang.Object r1 = r5.L$0
                        kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                        kotlin.ResultKt.throwOnFailure(r6)
                        r6 = r5
                        goto L40
                    L14:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r0)
                        throw r6
                    L1c:
                        kotlin.ResultKt.throwOnFailure(r6)
                        kotlinx.coroutines.CoroutineScope r6 = r5.p$
                        r1 = r6
                        r6 = r5
                    L23:
                        com.dotscreen.tv.common.bokit.MegacableUserService$getCurrentProfile$2$1 r3 = com.dotscreen.tv.common.bokit.MegacableUserService$getCurrentProfile$2.AnonymousClass1.this
                        com.dotscreen.tv.common.bokit.MegacableUserService$getCurrentProfile$2 r3 = com.dotscreen.tv.common.bokit.MegacableUserService$getCurrentProfile$2.this
                        com.dotscreen.tv.common.bokit.MegacableUserService r3 = r3.this$0
                        java.lang.String r3 = com.dotscreen.tv.common.bokit.MegacableUserService.access$getTAG$p(r3)
                        java.lang.String r4 = "Cache: will set timeout 10 minutes (profile)"
                        android.util.Log.d(r3, r4)
                        r3 = 600000(0x927c0, double:2.964394E-318)
                        r6.L$0 = r1
                        r6.label = r2
                        java.lang.Object r3 = kotlinx.coroutines.DelayKt.delay(r3, r6)
                        if (r3 != r0) goto L40
                        return r0
                    L40:
                        io.reactivex.ObservableEmitter r3 = r6.$observer
                        r4 = 0
                        java.lang.Integer r4 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4)
                        r3.onNext(r4)
                        goto L23
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.dotscreen.tv.common.bokit.MegacableUserService$getCurrentProfile$2.AnonymousClass1.C01651.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter<Integer> observer) {
                Intrinsics.checkParameterIsNotNull(observer, "observer");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C01651(observer, null), 2, null);
            }
        }).subscribe(new Consumer<Integer>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$getCurrentProfile$2.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Integer num) {
                Log.d(MegacableUserService$getCurrentProfile$2.this.this$0.TAG, "Cache: will reset timeout (profile)");
                MegacableUserService$getCurrentProfile$2.this.this$0.cancelProfileCache();
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$getCurrentProfile$2.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.d(MegacableUserService$getCurrentProfile$2.this.this$0.TAG, "Cache: error on single timer (profile) " + th);
            }
        }, new Action() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$getCurrentProfile$2.4
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        });
        Single<R> map = this.this$0.requestManager.getCompassJSONObject("GetProfile", 0, MapsKt.emptyMap()).map((Function) new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$getCurrentProfile$2.5
            @Override // io.reactivex.functions.Function
            public final Profile apply(JSONObject it) throws Exception {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Log.v(MegacableUserService$getCurrentProfile$2.this.this$0.TAG, "CurrentProfile: " + it.toString(4));
                if (it.has("code")) {
                    throw new Exception(it.getString("code"));
                }
                return Profile.INSTANCE.parseProfile(it);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "requestManager\n         …Profile(it)\n            }");
        return CachesKt.cacheUntil(map, this.this$0.profileCacheCanceler);
    }
}
