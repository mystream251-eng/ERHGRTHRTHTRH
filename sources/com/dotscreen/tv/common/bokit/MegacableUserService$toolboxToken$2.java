package com.dotscreen.tv.common.bokit;

import android.util.Log;
import com.dotscreen.farkit.rx.CachesKt;
import com.google.android.gms.common.util.GmsVersion;
import io.reactivex.Single;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: MegacableUserService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 16})
final class MegacableUserService$toolboxToken$2 extends Lambda implements Function0<Single<String>> {
    final /* synthetic */ MegacableUserService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MegacableUserService$toolboxToken$2(MegacableUserService megacableUserService) {
        super(0);
        this.this$0 = megacableUserService;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Single<String> invoke() {
        final PublishSubject publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(publishSubjectCreate, "PublishSubject.create<Boolean>()");
        Single map = this.this$0.loginToolbox().map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$toolboxToken$2.1
            @Override // io.reactivex.functions.Function
            public final String apply(JSONObject it) throws JSONException {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Date expirationDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).parse(it.getString("expirationDate"));
                String string = it.getString("accessToken");
                Intrinsics.checkExpressionValueIsNotNull(expirationDate, "expirationDate");
                long time = (expirationDate.getTime() - new Date().getTime()) - ((long) GmsVersion.VERSION_PARMESAN);
                if (time <= 0) {
                    time = 0;
                }
                Log.d(MegacableUserService$toolboxToken$2.this.this$0.TAG, "Toolbox token expired => " + expirationDate);
                Single.timer(time, TimeUnit.MILLISECONDS).subscribe(new BiConsumer<Long, Throwable>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.toolboxToken.2.1.1
                    @Override // io.reactivex.functions.BiConsumer
                    public final void accept(Long l, Throwable th) {
                        Log.d(MegacableUserService$toolboxToken$2.this.this$0.TAG, "Toolbox token will renew");
                        publishSubjectCreate.onNext(true);
                    }
                });
                return string;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "loginToolbox().map {\n   …ssToken\n                }");
        return CachesKt.cacheUntil(map, publishSubjectCreate).doOnError(new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$toolboxToken$2.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                publishSubjectCreate.onNext(true);
            }
        });
    }
}
