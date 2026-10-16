package com.dotscreen.tv.common.bokit;

import com.android.volley.NetworkError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.dotscreen.farkit.network.RequestManager;
import com.dotscreen.tv.common.utils.CommonUtils;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: RightVRequestManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "subscriber", "Lio/reactivex/SingleEmitter;", "", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
final class RightVRequestManager$getUIOPStatus$obs$1<T> implements SingleOnSubscribe<T> {
    final /* synthetic */ JSONObject $paramsJson;
    final /* synthetic */ String $type;
    final /* synthetic */ RightVRequestManager this$0;

    RightVRequestManager$getUIOPStatus$obs$1(RightVRequestManager rightVRequestManager, String str, JSONObject jSONObject) {
        this.this$0 = rightVRequestManager;
        this.$type = str;
        this.$paramsJson = jSONObject;
    }

    @Override // io.reactivex.SingleOnSubscribe
    public final void subscribe(final SingleEmitter<String> subscriber) {
        Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
        final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper = new RequestManager.VolleyResponseObserverWrapper(subscriber, new URL(CommonUtils.OPTIMIZER_URL + "/api/" + this.$type));
        final String str = CommonUtils.OPTIMIZER_URL + "/api/" + this.$type;
        final RightVRequestManager rightVRequestManager = this.this$0;
        final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
        final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
        final BackendName backendName = BackendName.RightV;
        final int i = 0;
        final Map map = null;
        RightVRequest<String> rightVRequest = new RightVRequest<String>(i, str, rightVRequestManager, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, backendName) { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager$getUIOPStatus$obs$1$req$1
            {
                String str2 = null;
                int i2 = 128;
                DefaultConstructorMarker defaultConstructorMarker = null;
            }

            @Override // com.dotscreen.tv.common.bokit.RightVRequest
            public /* bridge */ /* synthetic */ String parseData(byte[] bArr, Map map2) {
                return parseData2(bArr, (Map<String, String>) map2);
            }

            @Override // com.dotscreen.tv.common.bokit.RightVRequest
            /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
            protected String parseData2(byte[] data, Map<String, String> headers) throws Exception {
                Intrinsics.checkParameterIsNotNull(data, "data");
                Intrinsics.checkParameterIsNotNull(headers, "headers");
                if (data.length == 0) {
                    subscriber.onError(new NetworkError());
                    return "";
                }
                Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                return new String(data, charsetForName);
            }

            @Override // com.dotscreen.tv.common.bokit.RightVRequest, com.android.volley.Request
            public byte[] getBody() {
                String string = this.this$0.$paramsJson.toString();
                Intrinsics.checkExpressionValueIsNotNull(string, "paramsJson.toString()");
                Charset charsetForName = Charset.forName("utf-8");
                Intrinsics.checkNotNullExpressionValue(charsetForName, "Charset.forName(charsetName)");
                if (string == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                byte[] bytes = string.getBytes(charsetForName);
                Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
                return bytes;
            }

            @Override // com.dotscreen.tv.common.bokit.RightVRequest, com.android.volley.Request
            public Map<String, String> getHeaders() {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.putAll(super.getHeaders());
                linkedHashMap.put("Accept-Language", "en");
                linkedHashMap.put("Accept", "application/json");
                return MapsKt.toMap(linkedHashMap);
            }
        };
        rightVRequest.setShouldCache(false);
        this.this$0.getVolleyRequestQueue().add(rightVRequest);
    }
}
