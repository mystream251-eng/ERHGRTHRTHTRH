package com.dotscreen.tv.common.bokit;

import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.farkit.rx.CachesKt;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: MegacableLiveTVService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/reactivex/Maybe;", "", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 16})
final class MegacableLiveTVService$householdTerminalId$2 extends Lambda implements Function0<Maybe<String>> {
    final /* synthetic */ BOService $boService;
    final /* synthetic */ MegacableLiveTVService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MegacableLiveTVService$householdTerminalId$2(MegacableLiveTVService megacableLiveTVService, BOService bOService) {
        super(0);
        this.this$0 = megacableLiveTVService;
        this.$boService = bOService;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Maybe<String> invoke() {
        final PublishSubject publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(publishSubjectCreate, "PublishSubject.create<Boolean>()");
        MegacableUserService megacableUserService = (MegacableUserService) this.$boService.getUserService();
        if (megacableUserService == null) {
            Intrinsics.throwNpe();
        }
        Maybe maybeFlatMapMaybe = MegacableUserService.login$default(megacableUserService, false, 1, null).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$householdTerminalId$2.1
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(JSONObject it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return RightVRequestManager.getRTEJSONObject$default(MegacableLiveTVService$householdTerminalId$2.this.this$0.requestManager, "GetTerminalList", 0, null, null, 14, null);
            }
        }).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$householdTerminalId$2.2
            @Override // io.reactivex.functions.Function
            public final Maybe<String> apply(final JSONObject json) {
                Intrinsics.checkParameterIsNotNull(json, "json");
                return Maybe.create(new MaybeOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService.householdTerminalId.2.2.1
                    @Override // io.reactivex.MaybeOnSubscribe
                    public final void subscribe(MaybeEmitter<String> emitter) throws JSONException {
                        Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                        JSONArray jSONArray = json.getJSONArray("terminals");
                        int length = jSONArray.length();
                        for (int i = 0; i < length; i++) {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            UserService userService = MegacableLiveTVService$householdTerminalId$2.this.$boService.getUserService();
                            if (userService == null) {
                                Intrinsics.throwNpe();
                            }
                            if (userService == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                            }
                            String string = jSONObject.getString("serialNumber");
                            Intrinsics.checkExpressionValueIsNotNull(string, "terminal.getString(\"serialNumber\")");
                            if (StringsKt.compareTo(string, ((MegacableUserService) userService).getSerialNumber(), true) == 0) {
                                emitter.onSuccess(jSONObject.getString("id"));
                                return;
                            }
                        }
                        emitter.onComplete();
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(maybeFlatMapMaybe, "(boService.userService a…  }\n                    }");
        return CachesKt.cacheUntil(maybeFlatMapMaybe, publishSubjectCreate).doOnError(new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.bokit.MegacableLiveTVService$householdTerminalId$2.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                publishSubjectCreate.onNext(true);
            }
        });
    }
}
