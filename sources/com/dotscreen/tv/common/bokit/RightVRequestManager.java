package com.dotscreen.tv.common.bokit;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.dotscreen.farkit.network.RequestManager;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.reactivex.Single;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: RightVRequestManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b#\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u001a\u0010#\u001a\u00020$\"\u0004\b\u0000\u0010%2\f\u0010&\u001a\b\u0012\u0004\u0012\u0002H%0'J\u0010\u0010(\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0002J6\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+2\u0006\u0010)\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020.2\u0016\b\u0002\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u000201\u0018\u000100JJ\u00102\u001a\b\u0012\u0004\u0012\u0002030+2\u0006\u0010)\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020.2\u0016\b\u0002\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u000201\u0018\u0001002\b\b\u0002\u00104\u001a\u00020\u000f2\b\b\u0002\u00105\u001a\u00020\u000fJ6\u00106\u001a\b\u0012\u0004\u0012\u00020,0+2\u0006\u0010)\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020.2\u0016\b\u0002\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u000201\u0018\u000100J\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00050+J(\u00108\u001a\b\u0012\u0004\u0012\u00020,0+2\u0006\u0010)\u001a\u00020\u00052\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020100JZ\u00109\u001a\b\u0012\u0004\u0012\u0002030+2\u0006\u0010)\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020.2\u0016\b\u0002\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u000201\u0018\u0001002\n\b\u0002\u0010:\u001a\u0004\u0018\u00010,2\u0016\b\u0002\u0010;\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u000100JZ\u0010<\u001a\b\u0012\u0004\u0012\u00020,0+2\u0006\u0010)\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020.2\u0016\b\u0002\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u000201\u0018\u0001002\n\b\u0002\u0010:\u001a\u0004\u0018\u00010,2\u0016\b\u0002\u0010;\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u000100J6\u0010=\u001a\b\u0012\u0004\u0012\u0002030+2\u0006\u0010)\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020.2\u0016\b\u0002\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u000201\u0018\u000100JN\u0010>\u001a\b\u0012\u0004\u0012\u00020,0+2\u0006\u0010)\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020.2\u0016\b\u0002\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u000201\u0018\u0001002\u0016\b\u0002\u0010;\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u000100J\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00050+J6\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00050+2\u0006\u0010)\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020.2\u0016\b\u0002\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u000201\u0018\u000100J6\u0010A\u001a\b\u0012\u0004\u0012\u0002030+2\u0006\u0010)\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020.2\u0016\b\u0002\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u000201\u0018\u000100J6\u0010B\u001a\b\u0012\u0004\u0012\u00020,0+2\u0006\u0010)\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020.2\u0016\b\u0002\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u000201\u0018\u000100J6\u0010C\u001a\b\u0012\u0004\u0012\u00020,0+2\u0006\u0010)\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020.2\u0016\b\u0002\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u000201\u0018\u000100J@\u0010D\u001a\b\u0012\u0004\u0012\u00020,0+2\b\b\u0002\u0010)\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020.2\u0016\b\u0002\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u000201\u0018\u0001002\u0006\u0010E\u001a\u00020\u0005JB\u0010F\u001a\b\u0012\u0004\u0012\u00020,0+2\u0006\u0010)\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020.2\u0016\b\u0002\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u000201\u0018\u0001002\n\b\u0002\u0010:\u001a\u0004\u0018\u00010,J\u0014\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00050+2\u0006\u0010H\u001a\u00020\u0005J\u0010\u0010I\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0002J$\u0010J\u001a\b\u0012\u0004\u0012\u00020,0+2\u0006\u0010K\u001a\u00020\u00052\u0006\u0010L\u001a\u00020\u00052\u0006\u0010M\u001a\u00020\u0005J\u0010\u0010N\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0002J\u0010\u0010O\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0002J\u0010\u0010P\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0002J\u0010\u0010Q\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0002J\u0010\u0010R\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0002J\u0010\u0010S\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0002J\u0006\u0010T\u001a\u00020$J\u0006\u0010U\u001a\u00020$R\u0016\u0010\u0011\u001a\n \u0012*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n \u0012*\u0004\u0018\u00010\u001c0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u00058F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u0006V"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RightVRequestManager;", "Lcom/dotscreen/farkit/network/RequestManager;", "context", "Landroid/content/Context;", "rteBaseUrl", "", "compassBaseUrl", "searchUrl", "pcwBaseUrl", "idpmBaseUrl", "tbxEventsBaseUrl", "sessionManagementBaseUrl", "padiBaseUrl", "cachePath", "sharedCookies", "", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "TAG", "kotlin.jvm.PlatformType", "mockCompass", "getMockCompass", "()Z", "setMockCompass", "(Z)V", "mockRightv", "getMockRightv", "setMockRightv", "sharedPreferences", "Landroid/content/SharedPreferences;", "value", "userAgentDevId", "getUserAgentDevId", "()Ljava/lang/String;", "setUserAgentDevId", "(Ljava/lang/String;)V", "addResponseListener", "", ExifInterface.GPS_DIRECTION_TRUE, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/android/volley/Response$Listener;", "compassActionToPath", "action", "getAdsJSONObject", "Lio/reactivex/Single;", "Lorg/json/JSONObject;", "method", "", "params", "", "", "getCompassJSONArray", "Lorg/json/JSONArray;", "anonymousCall", "forceNoCache", "getCompassJSONObject", "getCompassStatus", "getPADIJSONObject", "getPCWJSONArray", "paramsJson", "customHeaders", "getPCWJSONObject", "getRTEJSONArray", "getRTEJSONObject", "getRTEStatus", "getRTEString", "getSearchJSONArray", "getSearchJSONObject", "getSessionManagementJSONObject", "getTbxEventJSONObject", "toolboxToken", "getTbxJSONObject", "getUIOPStatus", "type", "idpmActionToPath", "megacablePackageSubscribe", "account", "deviceId", "packageId", "padiActionToPath", "pcwActionToPath", "rteActionToPath", "searchActionToPath", "sessionManagerActionToPath", "tbxEventsActionToPath", "toggleCompassBackendState", "toggleRightVBackendState", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class RightVRequestManager extends RequestManager {
    private final String TAG;
    private final String compassBaseUrl;
    private final String idpmBaseUrl;
    private boolean mockCompass;
    private boolean mockRightv;
    private final String padiBaseUrl;
    private final String pcwBaseUrl;
    private final String rteBaseUrl;
    private final String searchUrl;
    private final String sessionManagementBaseUrl;
    private final SharedPreferences sharedPreferences;
    private final String tbxEventsBaseUrl;
    private String userAgentDevId;

    public final <T> void addResponseListener(Response.Listener<T> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
    }

    public /* synthetic */ RightVRequestManager(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, str2, str3, str4, str5, str6, str7, str8, (i & 512) != 0 ? (String) null : str9, (i & 1024) != 0 ? false : z);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RightVRequestManager(Context context, String rteBaseUrl, String compassBaseUrl, String searchUrl, String pcwBaseUrl, String idpmBaseUrl, String tbxEventsBaseUrl, String sessionManagementBaseUrl, String padiBaseUrl, String str, boolean z) {
        super(context, str, z);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(rteBaseUrl, "rteBaseUrl");
        Intrinsics.checkParameterIsNotNull(compassBaseUrl, "compassBaseUrl");
        Intrinsics.checkParameterIsNotNull(searchUrl, "searchUrl");
        Intrinsics.checkParameterIsNotNull(pcwBaseUrl, "pcwBaseUrl");
        Intrinsics.checkParameterIsNotNull(idpmBaseUrl, "idpmBaseUrl");
        Intrinsics.checkParameterIsNotNull(tbxEventsBaseUrl, "tbxEventsBaseUrl");
        Intrinsics.checkParameterIsNotNull(sessionManagementBaseUrl, "sessionManagementBaseUrl");
        Intrinsics.checkParameterIsNotNull(padiBaseUrl, "padiBaseUrl");
        this.rteBaseUrl = rteBaseUrl;
        this.compassBaseUrl = compassBaseUrl;
        this.searchUrl = searchUrl;
        this.pcwBaseUrl = pcwBaseUrl;
        this.idpmBaseUrl = idpmBaseUrl;
        this.tbxEventsBaseUrl = tbxEventsBaseUrl;
        this.sessionManagementBaseUrl = sessionManagementBaseUrl;
        this.padiBaseUrl = padiBaseUrl;
        this.sharedPreferences = context.getSharedPreferences("RightVRequestManager", 0);
        this.userAgentDevId = "";
        this.TAG = RightVRequestManager.class.getSimpleName();
    }

    public final boolean getMockCompass() {
        return this.mockCompass;
    }

    public final void setMockCompass(boolean z) {
        this.mockCompass = z;
    }

    public final boolean getMockRightv() {
        return this.mockRightv;
    }

    public final void setMockRightv(boolean z) {
        this.mockRightv = z;
    }

    public final String getUserAgentDevId() {
        String string = this.sharedPreferences.getString("userAgentDevId", null);
        return string != null ? string : "";
    }

    public final void setUserAgentDevId(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.userAgentDevId = value;
        this.sharedPreferences.edit().putString("userAgentDevId", value).commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String rteActionToPath(String action) {
        if (this.mockRightv) {
            return "http://rgrg.cm/";
        }
        return this.rteBaseUrl + '/' + action;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String compassActionToPath(String action) {
        if (this.mockCompass) {
            return "http://rgrg.cm/";
        }
        return this.compassBaseUrl + '/' + action;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String searchActionToPath(String action) {
        return this.searchUrl + '/' + action;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String pcwActionToPath(String action) {
        return this.pcwBaseUrl + '/' + action;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String idpmActionToPath(String action) {
        return this.idpmBaseUrl + '/' + action;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String sessionManagerActionToPath(String action) {
        return this.sessionManagementBaseUrl + '/' + action;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String tbxEventsActionToPath(String action) {
        return this.tbxEventsBaseUrl + '/' + action;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String padiActionToPath(String action) {
        return this.padiBaseUrl + '/' + action;
    }

    public final void toggleRightVBackendState() {
        Log.v(this.TAG, "Toggle rightv state current state: " + this.mockRightv);
        this.mockRightv = this.mockRightv ^ true;
    }

    public final void toggleCompassBackendState() {
        Log.v(this.TAG, "Toggle compass state current state: " + this.mockCompass);
        this.mockCompass = this.mockCompass ^ true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getRTEJSONObject$default(RightVRequestManager rightVRequestManager, String str, int i, Map map, Map map2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        if ((i2 & 8) != 0) {
            map2 = (Map) null;
        }
        return rightVRequestManager.getRTEJSONObject(str, i, map, map2);
    }

    public final Single<JSONObject> getRTEJSONObject(String action, int method, Map<String, ? extends Object> params, Map<String, String> customHeaders) {
        LinkedHashMap linkedHashMap;
        Intrinsics.checkParameterIsNotNull(action, "action");
        Log.d(this.TAG, "forceNoConnection == " + getForceNoConnection());
        if (getForceNoConnection()) {
            Single<JSONObject> singleError = Single.error(new RequestManager.ForcedNoConnectionError());
            Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error(ForcedNoConnectionError())");
            return singleError;
        }
        if (params == null || (linkedHashMap = MapsKt.toMutableMap(params)) == null) {
            linkedHashMap = new LinkedHashMap();
        }
        Map map = linkedHashMap;
        map.put("client", "json");
        Single<JSONObject> singleCreate = Single.create(new C05761(action, customHeaders, method, map));
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib…tQueue.add(req)\n        }");
        return singleCreate;
    }

    /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.RightVRequestManager$getRTEJSONObject$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RightVRequestManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "subscriber", "Lio/reactivex/SingleEmitter;", "Lorg/json/JSONObject;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
    static final class C05761<T> implements SingleOnSubscribe<T> {
        final /* synthetic */ String $action;
        final /* synthetic */ Map $customHeaders;
        final /* synthetic */ int $method;
        final /* synthetic */ Map $mutableParams;

        C05761(String str, Map map, int i, Map map2) {
            this.$action = str;
            this.$customHeaders = map;
            this.$method = i;
            this.$mutableParams = map2;
        }

        @Override // io.reactivex.SingleOnSubscribe
        public final void subscribe(SingleEmitter<JSONObject> subscriber) {
            Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper = new RequestManager.VolleyResponseObserverWrapper(subscriber, new URL(RightVRequestManager.this.rteActionToPath(this.$action)));
            final int iConvertMethod = RightVRequestManager.this.convertMethod(this.$method);
            final String strRteActionToPath = RightVRequestManager.this.rteActionToPath(this.$action);
            final RightVRequestManager rightVRequestManager = RightVRequestManager.this;
            final Map map = this.$mutableParams;
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
            final BackendName backendName = BackendName.RightV;
            final String userAgentDevId = RightVRequestManager.this.getUserAgentDevId();
            RightVRequestManager.this.getVolleyRequestQueue().add(new RightVRequest<JSONObject>(iConvertMethod, strRteActionToPath, rightVRequestManager, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, backendName, userAgentDevId) { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager$getRTEJSONObject$1$req$1
                @Override // com.dotscreen.tv.common.bokit.RightVRequest
                public /* bridge */ /* synthetic */ JSONObject parseData(byte[] bArr, Map map2) {
                    return parseData2(bArr, (Map<String, String>) map2);
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest
                /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                protected JSONObject parseData2(byte[] data, Map<String, String> headers) throws Exception {
                    Intrinsics.checkParameterIsNotNull(data, "data");
                    Intrinsics.checkParameterIsNotNull(headers, "headers");
                    Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                    Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                    JSONObject jSONObject = new JSONObject(new String(data, charsetForName));
                    if (jSONObject.has("response")) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("response");
                        Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "jsonResult.getJSONObject(\"response\")");
                        return jSONObject2;
                    }
                    return new JSONObject();
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest, com.android.volley.Request
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = super.getHeaders();
                    if (this.this$0.$customHeaders == null) {
                        return headers;
                    }
                    Map mutableMap = MapsKt.toMutableMap(headers);
                    mutableMap.putAll(this.this$0.$customHeaders);
                    Log.e("REQUESTHEADERS", String.valueOf(mutableMap));
                    return MapsKt.toMap(mutableMap);
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getRTEJSONArray$default(RightVRequestManager rightVRequestManager, String str, int i, Map map, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        return rightVRequestManager.getRTEJSONArray(str, i, map);
    }

    public final Single<JSONArray> getRTEJSONArray(final String action, final int method, Map<String, ? extends Object> params) {
        final LinkedHashMap linkedHashMap;
        Intrinsics.checkParameterIsNotNull(action, "action");
        Log.d(this.TAG, "forceNoConnection == " + getForceNoConnection());
        if (getForceNoConnection()) {
            Single<JSONArray> singleError = Single.error(new RequestManager.ForcedNoConnectionError());
            Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error(ForcedNoConnectionError())");
            return singleError;
        }
        if (params == null || (linkedHashMap = MapsKt.toMutableMap(params)) == null) {
            linkedHashMap = new LinkedHashMap();
        }
        linkedHashMap.put("client", "json");
        Single<JSONArray> singleCreate = Single.create(new SingleOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager.getRTEJSONArray.1
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<JSONArray> subscriber) {
                Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
                final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper = new RequestManager.VolleyResponseObserverWrapper(subscriber, new URL(RightVRequestManager.this.rteActionToPath(action)));
                final int iConvertMethod = RightVRequestManager.this.convertMethod(method);
                final String strRteActionToPath = RightVRequestManager.this.rteActionToPath(action);
                final RightVRequestManager rightVRequestManager = RightVRequestManager.this;
                final Map map = linkedHashMap;
                final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
                final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
                final BackendName backendName = BackendName.RightV;
                final String userAgentDevId = RightVRequestManager.this.getUserAgentDevId();
                RightVRequestManager.this.getVolleyRequestQueue().add(new RightVRequest<JSONArray>(iConvertMethod, strRteActionToPath, rightVRequestManager, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, backendName, userAgentDevId) { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager$getRTEJSONArray$1$req$1
                    @Override // com.dotscreen.tv.common.bokit.RightVRequest
                    public /* bridge */ /* synthetic */ JSONArray parseData(byte[] bArr, Map map2) {
                        return parseData2(bArr, (Map<String, String>) map2);
                    }

                    @Override // com.dotscreen.tv.common.bokit.RightVRequest
                    /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                    protected JSONArray parseData2(byte[] data, Map<String, String> headers) throws Exception {
                        Intrinsics.checkParameterIsNotNull(data, "data");
                        Intrinsics.checkParameterIsNotNull(headers, "headers");
                        Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                        Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                        JSONObject jSONObject = new JSONObject(new String(data, charsetForName));
                        if (jSONObject.has("response")) {
                            JSONArray jSONArray = jSONObject.getJSONArray("response");
                            Intrinsics.checkExpressionValueIsNotNull(jSONArray, "jsonResult.getJSONArray(\"response\")");
                            return jSONArray;
                        }
                        return new JSONArray();
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib…tQueue.add(req)\n        }");
        return singleCreate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getSearchJSONObject$default(RightVRequestManager rightVRequestManager, String str, int i, Map map, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        return rightVRequestManager.getSearchJSONObject(str, i, map);
    }

    public final Single<JSONObject> getSearchJSONObject(final String action, final int method, Map<String, ? extends Object> params) {
        final LinkedHashMap linkedHashMap;
        Intrinsics.checkParameterIsNotNull(action, "action");
        Log.d(this.TAG, "forceNoConnection == " + getForceNoConnection());
        if (getForceNoConnection()) {
            Single<JSONObject> singleError = Single.error(new RequestManager.ForcedNoConnectionError());
            Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error(ForcedNoConnectionError())");
            return singleError;
        }
        if (params == null || (linkedHashMap = MapsKt.toMutableMap(params)) == null) {
            linkedHashMap = new LinkedHashMap();
        }
        linkedHashMap.put("client", "json");
        Single<JSONObject> singleCreate = Single.create(new SingleOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager.getSearchJSONObject.1
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<JSONObject> subscriber) {
                Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
                final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper = new RequestManager.VolleyResponseObserverWrapper(subscriber, new URL(RightVRequestManager.this.searchActionToPath(action)));
                final int iConvertMethod = RightVRequestManager.this.convertMethod(method);
                final String strSearchActionToPath = RightVRequestManager.this.searchActionToPath(action);
                final RightVRequestManager rightVRequestManager = RightVRequestManager.this;
                final Map map = linkedHashMap;
                final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
                final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
                final BackendName backendName = BackendName.Compass;
                final String userAgentDevId = RightVRequestManager.this.getUserAgentDevId();
                RightVRequestManager.this.getVolleyRequestQueue().add(new RightVRequest<JSONObject>(iConvertMethod, strSearchActionToPath, rightVRequestManager, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, backendName, userAgentDevId) { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager$getSearchJSONObject$1$req$1
                    @Override // com.dotscreen.tv.common.bokit.RightVRequest
                    public /* bridge */ /* synthetic */ JSONObject parseData(byte[] bArr, Map map2) {
                        return parseData2(bArr, (Map<String, String>) map2);
                    }

                    @Override // com.dotscreen.tv.common.bokit.RightVRequest
                    /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                    protected JSONObject parseData2(byte[] data, Map<String, String> headers) throws Exception {
                        Intrinsics.checkParameterIsNotNull(data, "data");
                        Intrinsics.checkParameterIsNotNull(headers, "headers");
                        Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                        Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                        JSONObject jSONObject = new JSONObject(new String(data, charsetForName));
                        if (jSONObject.has("response")) {
                            JSONObject jSONObject2 = jSONObject.getJSONObject("response");
                            Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "jsonResult.getJSONObject(\"response\")");
                            return jSONObject2;
                        }
                        return new JSONObject();
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib…tQueue.add(req)\n        }");
        return singleCreate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getSearchJSONArray$default(RightVRequestManager rightVRequestManager, String str, int i, Map map, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        return rightVRequestManager.getSearchJSONArray(str, i, map);
    }

    public final Single<JSONArray> getSearchJSONArray(final String action, final int method, Map<String, ? extends Object> params) {
        final LinkedHashMap linkedHashMap;
        Intrinsics.checkParameterIsNotNull(action, "action");
        Log.d(this.TAG, "forceNoConnection == " + getForceNoConnection());
        if (getForceNoConnection()) {
            Single<JSONArray> singleError = Single.error(new RequestManager.ForcedNoConnectionError());
            Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error(ForcedNoConnectionError())");
            return singleError;
        }
        if (params == null || (linkedHashMap = MapsKt.toMutableMap(params)) == null) {
            linkedHashMap = new LinkedHashMap();
        }
        linkedHashMap.put("client", "json");
        Single<JSONArray> singleCreate = Single.create(new SingleOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager.getSearchJSONArray.1
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<JSONArray> subscriber) {
                Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
                final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper = new RequestManager.VolleyResponseObserverWrapper(subscriber, new URL(RightVRequestManager.this.searchActionToPath(action)));
                final int iConvertMethod = RightVRequestManager.this.convertMethod(method);
                final String strSearchActionToPath = RightVRequestManager.this.searchActionToPath(action);
                final RightVRequestManager rightVRequestManager = RightVRequestManager.this;
                final Map map = linkedHashMap;
                final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
                final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
                final BackendName backendName = BackendName.Compass;
                final String userAgentDevId = RightVRequestManager.this.getUserAgentDevId();
                RightVRequestManager.this.getVolleyRequestQueue().add(new RightVRequest<JSONArray>(iConvertMethod, strSearchActionToPath, rightVRequestManager, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, backendName, userAgentDevId) { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager$getSearchJSONArray$1$req$1
                    @Override // com.dotscreen.tv.common.bokit.RightVRequest
                    public /* bridge */ /* synthetic */ JSONArray parseData(byte[] bArr, Map map2) {
                        return parseData2(bArr, (Map<String, String>) map2);
                    }

                    @Override // com.dotscreen.tv.common.bokit.RightVRequest
                    /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                    protected JSONArray parseData2(byte[] data, Map<String, String> headers) throws Exception {
                        Intrinsics.checkParameterIsNotNull(data, "data");
                        Intrinsics.checkParameterIsNotNull(headers, "headers");
                        Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                        Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                        JSONObject jSONObject = new JSONObject(new String(data, charsetForName));
                        if (jSONObject.has("response")) {
                            JSONArray jSONArray = jSONObject.getJSONArray("response");
                            Intrinsics.checkExpressionValueIsNotNull(jSONArray, "jsonResult.getJSONArray(\"response\")");
                            return jSONArray;
                        }
                        return new JSONArray();
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib…tQueue.add(req)\n        }");
        return singleCreate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getCompassJSONObject$default(RightVRequestManager rightVRequestManager, String str, int i, Map map, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        return rightVRequestManager.getCompassJSONObject(str, i, map);
    }

    public final Single<JSONObject> getCompassJSONObject(final String action, final int method, Map<String, ? extends Object> params) {
        final LinkedHashMap linkedHashMap;
        Intrinsics.checkParameterIsNotNull(action, "action");
        Log.d(this.TAG, "forceNoConnection == " + getForceNoConnection());
        if (getForceNoConnection()) {
            Single<JSONObject> singleError = Single.error(new RequestManager.ForcedNoConnectionError());
            Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error(ForcedNoConnectionError())");
            return singleError;
        }
        if (params == null || (linkedHashMap = MapsKt.toMutableMap(params)) == null) {
            linkedHashMap = new LinkedHashMap();
        }
        linkedHashMap.put("client", "json");
        Single<JSONObject> singleCreate = Single.create(new SingleOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager.getCompassJSONObject.1
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<JSONObject> subscriber) {
                Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
                final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper = new RequestManager.VolleyResponseObserverWrapper(subscriber, new URL(RightVRequestManager.this.compassActionToPath(action)));
                final int iConvertMethod = RightVRequestManager.this.convertMethod(method);
                final String strCompassActionToPath = RightVRequestManager.this.compassActionToPath(action);
                final RightVRequestManager rightVRequestManager = RightVRequestManager.this;
                final Map map = linkedHashMap;
                final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
                final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
                final BackendName backendName = BackendName.Compass;
                final String userAgentDevId = RightVRequestManager.this.getUserAgentDevId();
                RightVRequestManager.this.getVolleyRequestQueue().add(new RightVRequest<JSONObject>(iConvertMethod, strCompassActionToPath, rightVRequestManager, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, backendName, userAgentDevId) { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager$getCompassJSONObject$1$req$1
                    @Override // com.dotscreen.tv.common.bokit.RightVRequest
                    public /* bridge */ /* synthetic */ JSONObject parseData(byte[] bArr, Map map2) {
                        return parseData2(bArr, (Map<String, String>) map2);
                    }

                    @Override // com.dotscreen.tv.common.bokit.RightVRequest
                    /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                    protected JSONObject parseData2(byte[] data, Map<String, String> headers) throws Exception {
                        Intrinsics.checkParameterIsNotNull(data, "data");
                        Intrinsics.checkParameterIsNotNull(headers, "headers");
                        Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                        Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                        JSONObject jSONObject = new JSONObject(new String(data, charsetForName));
                        if (jSONObject.has("response")) {
                            JSONObject jSONObject2 = jSONObject.getJSONObject("response");
                            Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "jsonResult.getJSONObject(\"response\")");
                            return jSONObject2;
                        }
                        return new JSONObject();
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib…tQueue.add(req)\n        }");
        return singleCreate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getCompassJSONArray$default(RightVRequestManager rightVRequestManager, String str, int i, Map map, boolean z, boolean z2, int i2, Object obj) {
        int i3 = (i2 & 2) != 0 ? 0 : i;
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        return rightVRequestManager.getCompassJSONArray(str, i3, map, (i2 & 8) != 0 ? false : z, (i2 & 16) != 0 ? false : z2);
    }

    public final Single<JSONArray> getCompassJSONArray(String action, int method, Map<String, ? extends Object> params, boolean anonymousCall, boolean forceNoCache) {
        LinkedHashMap linkedHashMap;
        Intrinsics.checkParameterIsNotNull(action, "action");
        Log.d(this.TAG, "forceNoConnection == " + getForceNoConnection());
        if (getForceNoConnection()) {
            Single<JSONArray> singleError = Single.error(new RequestManager.ForcedNoConnectionError());
            Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error(ForcedNoConnectionError())");
            return singleError;
        }
        if (params == null || (linkedHashMap = MapsKt.toMutableMap(params)) == null) {
            linkedHashMap = new LinkedHashMap();
        }
        Map map = linkedHashMap;
        map.put("client", "json");
        Single<JSONArray> singleCreate = Single.create(new AnonymousClass1(action, anonymousCall, method, map, forceNoCache));
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib…tQueue.add(req)\n        }");
        return singleCreate;
    }

    /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.RightVRequestManager$getCompassJSONArray$1, reason: invalid class name */
    /* JADX INFO: compiled from: RightVRequestManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "subscriber", "Lio/reactivex/SingleEmitter;", "Lorg/json/JSONArray;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass1<T> implements SingleOnSubscribe<T> {
        final /* synthetic */ String $action;
        final /* synthetic */ boolean $anonymousCall;
        final /* synthetic */ boolean $forceNoCache;
        final /* synthetic */ int $method;
        final /* synthetic */ Map $mutableParams;

        AnonymousClass1(String str, boolean z, int i, Map map, boolean z2) {
            this.$action = str;
            this.$anonymousCall = z;
            this.$method = i;
            this.$mutableParams = map;
            this.$forceNoCache = z2;
        }

        @Override // io.reactivex.SingleOnSubscribe
        public final void subscribe(SingleEmitter<JSONArray> subscriber) {
            Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper = new RequestManager.VolleyResponseObserverWrapper(subscriber, new URL(RightVRequestManager.this.compassActionToPath(this.$action)));
            final int iConvertMethod = RightVRequestManager.this.convertMethod(this.$method);
            final String strCompassActionToPath = RightVRequestManager.this.compassActionToPath(this.$action);
            final RightVRequestManager rightVRequestManager = RightVRequestManager.this;
            final Map map = this.$mutableParams;
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
            final BackendName backendName = BackendName.Compass;
            final String userAgentDevId = RightVRequestManager.this.getUserAgentDevId();
            RightVRequest<JSONArray> rightVRequest = new RightVRequest<JSONArray>(iConvertMethod, strCompassActionToPath, rightVRequestManager, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, backendName, userAgentDevId) { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager$getCompassJSONArray$1$req$1
                @Override // com.dotscreen.tv.common.bokit.RightVRequest
                public /* bridge */ /* synthetic */ JSONArray parseData(byte[] bArr, Map map2) {
                    return parseData2(bArr, (Map<String, String>) map2);
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest
                /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                protected JSONArray parseData2(byte[] data, Map<String, String> headers) throws Exception {
                    Intrinsics.checkParameterIsNotNull(data, "data");
                    Intrinsics.checkParameterIsNotNull(headers, "headers");
                    Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                    Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                    JSONObject jSONObject = new JSONObject(new String(data, charsetForName));
                    if (jSONObject.has("response")) {
                        JSONArray jSONArray = jSONObject.getJSONArray("response");
                        Intrinsics.checkExpressionValueIsNotNull(jSONArray, "jsonResult.getJSONArray(\"response\")");
                        return jSONArray;
                    }
                    return new JSONArray();
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest, com.android.volley.Request
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = super.getHeaders();
                    if (!this.this$0.$anonymousCall || !headers.containsValue("compass-identity")) {
                        return headers;
                    }
                    Map mutableMap = MapsKt.toMutableMap(headers);
                    mutableMap.remove("compass-identity");
                    return MapsKt.toMap(mutableMap);
                }
            };
            rightVRequest.setShouldCache(!this.$forceNoCache);
            RightVRequestManager.this.getVolleyRequestQueue().add(rightVRequest);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getAdsJSONObject$default(RightVRequestManager rightVRequestManager, String str, int i, Map map, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        return rightVRequestManager.getAdsJSONObject(str, i, map);
    }

    public final Single<JSONObject> getAdsJSONObject(final String action, final int method, final Map<String, ? extends Object> params) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        Log.d(this.TAG, "forceNoConnection == " + getForceNoConnection());
        if (getForceNoConnection()) {
            Single<JSONObject> singleError = Single.error(new RequestManager.ForcedNoConnectionError());
            Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error(ForcedNoConnectionError())");
            return singleError;
        }
        Single<JSONObject> singleCreate = Single.create(new SingleOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager$getAdsJSONObject$obs$1
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<JSONObject> subscriber) {
                Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
                final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper = new RequestManager.VolleyResponseObserverWrapper(subscriber, new URL(action));
                final int iConvertMethod = this.this$0.convertMethod(method);
                final String str = action;
                final RightVRequestManager rightVRequestManager = this.this$0;
                final Map map = params;
                final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
                final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
                final BackendName backendName = BackendName.ADS;
                this.this$0.getVolleyRequestQueue().add(new RightVRequest<JSONObject>(iConvertMethod, str, rightVRequestManager, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, backendName) { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager$getAdsJSONObject$obs$1$req$1
                    {
                        String str2 = null;
                        int i = 128;
                        DefaultConstructorMarker defaultConstructorMarker = null;
                    }

                    @Override // com.dotscreen.tv.common.bokit.RightVRequest
                    public /* bridge */ /* synthetic */ JSONObject parseData(byte[] bArr, Map map2) {
                        return parseData2(bArr, (Map<String, String>) map2);
                    }

                    @Override // com.dotscreen.tv.common.bokit.RightVRequest
                    /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                    protected JSONObject parseData2(byte[] data, Map<String, String> headers) throws Exception {
                        Intrinsics.checkParameterIsNotNull(data, "data");
                        Intrinsics.checkParameterIsNotNull(headers, "headers");
                        Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                        Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                        return new JSONObject(new String(data, charsetForName));
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib…tQueue.add(req)\n        }");
        return singleCreate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getPCWJSONObject$default(RightVRequestManager rightVRequestManager, String str, int i, Map map, JSONObject jSONObject, Map map2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        int i3 = i;
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        Map map3 = map;
        if ((i2 & 8) != 0) {
            jSONObject = (JSONObject) null;
        }
        JSONObject jSONObject2 = jSONObject;
        if ((i2 & 16) != 0) {
            map2 = (Map) null;
        }
        return rightVRequestManager.getPCWJSONObject(str, i3, map3, jSONObject2, map2);
    }

    public final Single<JSONObject> getPCWJSONObject(String action, int method, Map<String, ? extends Object> params, JSONObject paramsJson, Map<String, String> customHeaders) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        Log.d(this.TAG, "forceNoConnection == " + getForceNoConnection());
        if (getForceNoConnection()) {
            Single<JSONObject> singleError = Single.error(new RequestManager.ForcedNoConnectionError());
            Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error(ForcedNoConnectionError())");
            return singleError;
        }
        Single<JSONObject> singleCreate = Single.create(new C05741(action, paramsJson, customHeaders, method, params));
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib…tQueue.add(req)\n        }");
        return singleCreate;
    }

    /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.RightVRequestManager$getPCWJSONObject$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RightVRequestManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "subscriber", "Lio/reactivex/SingleEmitter;", "Lorg/json/JSONObject;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
    static final class C05741<T> implements SingleOnSubscribe<T> {
        final /* synthetic */ String $action;
        final /* synthetic */ Map $customHeaders;
        final /* synthetic */ int $method;
        final /* synthetic */ Map $params;
        final /* synthetic */ JSONObject $paramsJson;

        C05741(String str, JSONObject jSONObject, Map map, int i, Map map2) {
            this.$action = str;
            this.$paramsJson = jSONObject;
            this.$customHeaders = map;
            this.$method = i;
            this.$params = map2;
        }

        @Override // io.reactivex.SingleOnSubscribe
        public final void subscribe(SingleEmitter<JSONObject> subscriber) {
            Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper = new RequestManager.VolleyResponseObserverWrapper(subscriber, new URL(RightVRequestManager.this.pcwActionToPath(this.$action)));
            final int iConvertMethod = RightVRequestManager.this.convertMethod(this.$method);
            final String strPcwActionToPath = RightVRequestManager.this.pcwActionToPath(this.$action);
            final RightVRequestManager rightVRequestManager = RightVRequestManager.this;
            final Map map = this.$params;
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
            final BackendName backendName = BackendName.PCW;
            final String userAgentDevId = RightVRequestManager.this.getUserAgentDevId();
            RightVRequest<JSONObject> rightVRequest = new RightVRequest<JSONObject>(iConvertMethod, strPcwActionToPath, rightVRequestManager, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, backendName, userAgentDevId) { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager$getPCWJSONObject$1$req$1
                @Override // com.dotscreen.tv.common.bokit.RightVRequest
                public /* bridge */ /* synthetic */ JSONObject parseData(byte[] bArr, Map map2) {
                    return parseData2(bArr, (Map<String, String>) map2);
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest
                /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                protected JSONObject parseData2(byte[] data, Map<String, String> headers) throws Exception {
                    Intrinsics.checkParameterIsNotNull(data, "data");
                    Intrinsics.checkParameterIsNotNull(headers, "headers");
                    if (data.length == 0) {
                        return new JSONObject();
                    }
                    Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                    Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                    return new JSONObject(new String(data, charsetForName));
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest, com.android.volley.Request
                public byte[] getBody() {
                    if (this.this$0.$paramsJson != null) {
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
                    return super.getBody();
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest, com.android.volley.Request
                public Map<String, String> getHeaders() {
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.putAll(super.getHeaders());
                    if (this.this$0.$customHeaders != null) {
                        linkedHashMap.putAll(this.this$0.$customHeaders);
                    }
                    linkedHashMap.put("Accept-Language", "es");
                    linkedHashMap.put("Accept", "application/json");
                    if (this.this$0.$paramsJson != null) {
                        linkedHashMap.put("Content-Type", "application/vnd.vo.ClientEvent+json");
                    }
                    return MapsKt.toMap(linkedHashMap);
                }
            };
            rightVRequest.setShouldCache(false);
            RightVRequestManager.this.getVolleyRequestQueue().add(rightVRequest);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getPCWJSONArray$default(RightVRequestManager rightVRequestManager, String str, int i, Map map, JSONObject jSONObject, Map map2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        int i3 = i;
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        Map map3 = map;
        if ((i2 & 8) != 0) {
            jSONObject = (JSONObject) null;
        }
        JSONObject jSONObject2 = jSONObject;
        if ((i2 & 16) != 0) {
            map2 = (Map) null;
        }
        return rightVRequestManager.getPCWJSONArray(str, i3, map3, jSONObject2, map2);
    }

    public final Single<JSONArray> getPCWJSONArray(String action, int method, Map<String, ? extends Object> params, JSONObject paramsJson, Map<String, String> customHeaders) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        Log.d(this.TAG, "forceNoConnection == " + getForceNoConnection());
        if (getForceNoConnection()) {
            Single<JSONArray> singleError = Single.error(new RequestManager.ForcedNoConnectionError());
            Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error(ForcedNoConnectionError())");
            return singleError;
        }
        Single<JSONArray> singleCreate = Single.create(new C05731(action, paramsJson, customHeaders, method, params));
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib…tQueue.add(req)\n        }");
        return singleCreate;
    }

    /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.RightVRequestManager$getPCWJSONArray$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RightVRequestManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "subscriber", "Lio/reactivex/SingleEmitter;", "Lorg/json/JSONArray;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
    static final class C05731<T> implements SingleOnSubscribe<T> {
        final /* synthetic */ String $action;
        final /* synthetic */ Map $customHeaders;
        final /* synthetic */ int $method;
        final /* synthetic */ Map $params;
        final /* synthetic */ JSONObject $paramsJson;

        C05731(String str, JSONObject jSONObject, Map map, int i, Map map2) {
            this.$action = str;
            this.$paramsJson = jSONObject;
            this.$customHeaders = map;
            this.$method = i;
            this.$params = map2;
        }

        @Override // io.reactivex.SingleOnSubscribe
        public final void subscribe(SingleEmitter<JSONArray> subscriber) {
            Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper = new RequestManager.VolleyResponseObserverWrapper(subscriber, new URL(RightVRequestManager.this.pcwActionToPath(this.$action)));
            final int iConvertMethod = RightVRequestManager.this.convertMethod(this.$method);
            final String strPcwActionToPath = RightVRequestManager.this.pcwActionToPath(this.$action);
            final RightVRequestManager rightVRequestManager = RightVRequestManager.this;
            final Map map = this.$params;
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
            final BackendName backendName = BackendName.PCW;
            final String userAgentDevId = RightVRequestManager.this.getUserAgentDevId();
            RightVRequest<JSONArray> rightVRequest = new RightVRequest<JSONArray>(iConvertMethod, strPcwActionToPath, rightVRequestManager, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, backendName, userAgentDevId) { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager$getPCWJSONArray$1$req$1
                @Override // com.dotscreen.tv.common.bokit.RightVRequest
                public /* bridge */ /* synthetic */ JSONArray parseData(byte[] bArr, Map map2) {
                    return parseData2(bArr, (Map<String, String>) map2);
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest
                /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                protected JSONArray parseData2(byte[] data, Map<String, String> headers) throws Exception {
                    Intrinsics.checkParameterIsNotNull(data, "data");
                    Intrinsics.checkParameterIsNotNull(headers, "headers");
                    if (data.length == 0) {
                        return new JSONArray();
                    }
                    Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                    Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                    return new JSONArray(new String(data, charsetForName));
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest, com.android.volley.Request
                public byte[] getBody() {
                    if (this.this$0.$paramsJson != null) {
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
                    return super.getBody();
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest, com.android.volley.Request
                public Map<String, String> getHeaders() {
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.putAll(super.getHeaders());
                    if (this.this$0.$customHeaders != null) {
                        linkedHashMap.putAll(this.this$0.$customHeaders);
                    }
                    linkedHashMap.put("Accept-Language", "es");
                    linkedHashMap.put("Accept", "application/json");
                    if (this.this$0.$paramsJson != null) {
                        linkedHashMap.put("Content-Type", "application/vnd.vo.ClientEvent+json");
                    }
                    return MapsKt.toMap(linkedHashMap);
                }
            };
            rightVRequest.setShouldCache(false);
            RightVRequestManager.this.getVolleyRequestQueue().add(rightVRequest);
        }
    }

    public final Single<JSONObject> megacablePackageSubscribe(String account, String deviceId, String packageId) throws JSONException {
        Intrinsics.checkParameterIsNotNull(account, "account");
        Intrinsics.checkParameterIsNotNull(deviceId, "deviceId");
        Intrinsics.checkParameterIsNotNull(packageId, "packageId");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("account", account);
        jSONObject.put("deviceId", deviceId);
        jSONObject.put("packageId", packageId);
        Single<JSONObject> singleCreate = Single.create(new C05821(jSONObject));
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib…tQueue.add(req)\n        }");
        return singleCreate;
    }

    /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.RightVRequestManager$megacablePackageSubscribe$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RightVRequestManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "subscriber", "Lio/reactivex/SingleEmitter;", "Lorg/json/JSONObject;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
    static final class C05821<T> implements SingleOnSubscribe<T> {
        final /* synthetic */ JSONObject $paramsJson;

        C05821(JSONObject jSONObject) {
            this.$paramsJson = jSONObject;
        }

        @Override // io.reactivex.SingleOnSubscribe
        public final void subscribe(SingleEmitter<JSONObject> subscriber) {
            Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper = new RequestManager.VolleyResponseObserverWrapper(subscriber, new URL("https://bss.rcs.megafon.com.mx:9065/apro/ssvc"));
            final RightVRequestManager rightVRequestManager = RightVRequestManager.this;
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
            final BackendName backendName = BackendName.Megacable;
            final int i = 1;
            final String str = "https://bss.rcs.megafon.com.mx:9065/apro/ssvc";
            final Map map = null;
            RightVRequest<JSONObject> rightVRequest = new RightVRequest<JSONObject>(i, str, rightVRequestManager, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, backendName) { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager$megacablePackageSubscribe$1$req$1
                {
                    String str2 = null;
                    int i2 = 128;
                    DefaultConstructorMarker defaultConstructorMarker = null;
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest
                public /* bridge */ /* synthetic */ JSONObject parseData(byte[] bArr, Map map2) {
                    return parseData2(bArr, (Map<String, String>) map2);
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest
                /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                protected JSONObject parseData2(byte[] data, Map<String, String> headers) throws Exception {
                    Intrinsics.checkParameterIsNotNull(data, "data");
                    Intrinsics.checkParameterIsNotNull(headers, "headers");
                    if (data.length == 0) {
                        return new JSONObject();
                    }
                    Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                    Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                    return new JSONObject(new String(data, charsetForName));
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
                    linkedHashMap.put("Content-Type", "application/json");
                    return MapsKt.toMap(linkedHashMap);
                }
            };
            rightVRequest.setShouldCache(false);
            RightVRequestManager.this.getVolleyRequestQueue().add(rightVRequest);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getTbxJSONObject$default(RightVRequestManager rightVRequestManager, String str, int i, Map map, JSONObject jSONObject, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 1;
        }
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        if ((i2 & 8) != 0) {
            jSONObject = (JSONObject) null;
        }
        return rightVRequestManager.getTbxJSONObject(str, i, map, jSONObject);
    }

    public final Single<JSONObject> getTbxJSONObject(String action, int method, Map<String, ? extends Object> params, JSONObject paramsJson) {
        LinkedHashMap linkedHashMap;
        Intrinsics.checkParameterIsNotNull(action, "action");
        Log.d(this.TAG, "forceNoConnection == " + getForceNoConnection());
        if (getForceNoConnection()) {
            Single<JSONObject> singleError = Single.error(new RequestManager.ForcedNoConnectionError());
            Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error(ForcedNoConnectionError())");
            return singleError;
        }
        if (params == null || (linkedHashMap = MapsKt.toMutableMap(params)) == null) {
            linkedHashMap = new LinkedHashMap();
        }
        Single<JSONObject> singleCreate = Single.create(new C05811(action, paramsJson, method, linkedHashMap));
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib…tQueue.add(req)\n        }");
        return singleCreate;
    }

    /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.RightVRequestManager$getTbxJSONObject$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RightVRequestManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "subscriber", "Lio/reactivex/SingleEmitter;", "Lorg/json/JSONObject;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
    static final class C05811<T> implements SingleOnSubscribe<T> {
        final /* synthetic */ String $action;
        final /* synthetic */ int $method;
        final /* synthetic */ Map $mutableParams;
        final /* synthetic */ JSONObject $paramsJson;

        C05811(String str, JSONObject jSONObject, int i, Map map) {
            this.$action = str;
            this.$paramsJson = jSONObject;
            this.$method = i;
            this.$mutableParams = map;
        }

        @Override // io.reactivex.SingleOnSubscribe
        public final void subscribe(SingleEmitter<JSONObject> subscriber) {
            Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper = new RequestManager.VolleyResponseObserverWrapper(subscriber, new URL(RightVRequestManager.this.idpmActionToPath(this.$action)));
            final int iConvertMethod = RightVRequestManager.this.convertMethod(this.$method);
            final String strIdpmActionToPath = RightVRequestManager.this.idpmActionToPath(this.$action);
            final RightVRequestManager rightVRequestManager = RightVRequestManager.this;
            final Map map = this.$mutableParams;
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
            final BackendName backendName = BackendName.IDPM;
            RightVRequestManager.this.getVolleyRequestQueue().add(new RightVRequest<JSONObject>(iConvertMethod, strIdpmActionToPath, rightVRequestManager, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, backendName) { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager$getTbxJSONObject$1$req$1
                {
                    String str = null;
                    int i = 128;
                    DefaultConstructorMarker defaultConstructorMarker = null;
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest
                public /* bridge */ /* synthetic */ JSONObject parseData(byte[] bArr, Map map2) {
                    return parseData2(bArr, (Map<String, String>) map2);
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest
                /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                protected JSONObject parseData2(byte[] data, Map<String, String> headers) throws Exception {
                    Intrinsics.checkParameterIsNotNull(data, "data");
                    Intrinsics.checkParameterIsNotNull(headers, "headers");
                    Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                    Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                    return new JSONObject(new String(data, charsetForName));
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest, com.android.volley.Request
                public byte[] getBody() {
                    if (this.this$0.$paramsJson != null) {
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
                    return super.getBody();
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest, com.android.volley.Request
                public Map<String, String> getHeaders() {
                    Map mutableMap = MapsKt.toMutableMap(super.getHeaders());
                    mutableMap.put("Content-Type", "application/json");
                    return MapsKt.toMap(mutableMap);
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getTbxEventJSONObject$default(RightVRequestManager rightVRequestManager, String str, int i, Map map, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = ToolboxAnalyticsModuleKt.KEY_EVENTS;
        }
        if ((i2 & 2) != 0) {
            i = 1;
        }
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        return rightVRequestManager.getTbxEventJSONObject(str, i, map, str2);
    }

    public final Single<JSONObject> getTbxEventJSONObject(String action, int method, Map<String, ? extends Object> params, String toolboxToken) {
        LinkedHashMap linkedHashMap;
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(toolboxToken, "toolboxToken");
        Log.d(this.TAG, "forceNoConnection == " + getForceNoConnection());
        if (getForceNoConnection()) {
            Single<JSONObject> singleError = Single.error(new RequestManager.ForcedNoConnectionError());
            Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error(ForcedNoConnectionError())");
            return singleError;
        }
        if (params == null || (linkedHashMap = MapsKt.toMutableMap(params)) == null) {
            linkedHashMap = new LinkedHashMap();
        }
        Single<JSONObject> singleCreate = Single.create(new C05801(action, params, toolboxToken, method, linkedHashMap));
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib…tQueue.add(req)\n        }");
        return singleCreate;
    }

    /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.RightVRequestManager$getTbxEventJSONObject$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RightVRequestManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "subscriber", "Lio/reactivex/SingleEmitter;", "Lorg/json/JSONObject;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
    static final class C05801<T> implements SingleOnSubscribe<T> {
        final /* synthetic */ String $action;
        final /* synthetic */ int $method;
        final /* synthetic */ Map $mutableParams;
        final /* synthetic */ Map $params;
        final /* synthetic */ String $toolboxToken;

        C05801(String str, Map map, String str2, int i, Map map2) {
            this.$action = str;
            this.$params = map;
            this.$toolboxToken = str2;
            this.$method = i;
            this.$mutableParams = map2;
        }

        @Override // io.reactivex.SingleOnSubscribe
        public final void subscribe(SingleEmitter<JSONObject> subscriber) {
            Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper = new RequestManager.VolleyResponseObserverWrapper(subscriber, new URL(RightVRequestManager.this.tbxEventsActionToPath(this.$action)));
            final int iConvertMethod = RightVRequestManager.this.convertMethod(this.$method);
            final String strTbxEventsActionToPath = RightVRequestManager.this.tbxEventsActionToPath(this.$action);
            final RightVRequestManager rightVRequestManager = RightVRequestManager.this;
            final Map map = this.$mutableParams;
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
            final BackendName backendName = BackendName.Toolbox;
            RightVRequestManager.this.getVolleyRequestQueue().add(new RightVRequest<JSONObject>(iConvertMethod, strTbxEventsActionToPath, rightVRequestManager, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, backendName) { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager$getTbxEventJSONObject$1$req$1
                {
                    String str = null;
                    int i = 128;
                    DefaultConstructorMarker defaultConstructorMarker = null;
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest
                public /* bridge */ /* synthetic */ JSONObject parseData(byte[] bArr, Map map2) {
                    return parseData2(bArr, (Map<String, String>) map2);
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest
                /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                protected JSONObject parseData2(byte[] data, Map<String, String> headers) throws Exception {
                    Intrinsics.checkParameterIsNotNull(data, "data");
                    Intrinsics.checkParameterIsNotNull(headers, "headers");
                    Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                    Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                    return new JSONObject(new String(data, charsetForName));
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest, com.android.volley.Request
                public byte[] getBody() {
                    if (this.this$0.$params != null) {
                        JSONObject jSONObject = new JSONObject(this.this$0.$params);
                        Log.v(RightVRequestManager.this.TAG, "TbxEvents Post => \n" + jSONObject.toString(4));
                        String string = jSONObject.toString();
                        Intrinsics.checkExpressionValueIsNotNull(string, "body.toString()");
                        Charset charsetForName = Charset.forName("utf-8");
                        Intrinsics.checkNotNullExpressionValue(charsetForName, "Charset.forName(charsetName)");
                        if (string == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                        }
                        byte[] bytes = string.getBytes(charsetForName);
                        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
                        return bytes;
                    }
                    return super.getBody();
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest, com.android.volley.Request
                public Map<String, String> getHeaders() {
                    Map mutableMap = MapsKt.toMutableMap(super.getHeaders());
                    mutableMap.put("Authorization", "JWT " + this.this$0.$toolboxToken);
                    mutableMap.put("Content-Type", "application/json");
                    Log.e("REQUESTHEADERS", String.valueOf(mutableMap));
                    return MapsKt.toMap(mutableMap);
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getSessionManagementJSONObject$default(RightVRequestManager rightVRequestManager, String str, int i, Map map, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        return rightVRequestManager.getSessionManagementJSONObject(str, i, map);
    }

    public final Single<JSONObject> getSessionManagementJSONObject(final String action, final int method, Map<String, ? extends Object> params) {
        final LinkedHashMap linkedHashMap;
        Intrinsics.checkParameterIsNotNull(action, "action");
        Log.d(this.TAG, "forceNoConnection == " + getForceNoConnection());
        if (getForceNoConnection()) {
            Single<JSONObject> singleError = Single.error(new RequestManager.ForcedNoConnectionError());
            Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error(ForcedNoConnectionError())");
            return singleError;
        }
        if (params == null || (linkedHashMap = MapsKt.toMutableMap(params)) == null) {
            linkedHashMap = new LinkedHashMap();
        }
        Single<JSONObject> singleCreate = Single.create(new SingleOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager.getSessionManagementJSONObject.1
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<JSONObject> subscriber) {
                Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
                final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper = new RequestManager.VolleyResponseObserverWrapper(subscriber, new URL(RightVRequestManager.this.sessionManagerActionToPath(action)));
                final int iConvertMethod = RightVRequestManager.this.convertMethod(method);
                final String strSessionManagerActionToPath = RightVRequestManager.this.sessionManagerActionToPath(action);
                final RightVRequestManager rightVRequestManager = RightVRequestManager.this;
                final Map map = linkedHashMap;
                final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
                final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
                final BackendName backendName = BackendName.SessionManagement;
                final String userAgentDevId = RightVRequestManager.this.getUserAgentDevId();
                RightVRequestManager.this.getVolleyRequestQueue().add(new RightVRequest<JSONObject>(iConvertMethod, strSessionManagerActionToPath, rightVRequestManager, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, backendName, userAgentDevId) { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager$getSessionManagementJSONObject$1$req$1
                    @Override // com.dotscreen.tv.common.bokit.RightVRequest
                    public /* bridge */ /* synthetic */ JSONObject parseData(byte[] bArr, Map map2) {
                        return parseData2(bArr, (Map<String, String>) map2);
                    }

                    @Override // com.dotscreen.tv.common.bokit.RightVRequest
                    /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                    protected JSONObject parseData2(byte[] data, Map<String, String> headers) throws Exception {
                        Intrinsics.checkParameterIsNotNull(data, "data");
                        Intrinsics.checkParameterIsNotNull(headers, "headers");
                        if (data.length == 0) {
                            return new JSONObject();
                        }
                        Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                        Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                        return new JSONObject(new String(data, charsetForName));
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib…tQueue.add(req)\n        }");
        return singleCreate;
    }

    public final Single<String> getUIOPStatus(String type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Single<String> singleCreate = Single.create(new RightVRequestManager$getUIOPStatus$obs$1(this, type, new JSONObject()));
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib…tQueue.add(req)\n        }");
        return singleCreate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getRTEString$default(RightVRequestManager rightVRequestManager, String str, int i, Map map, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        return rightVRequestManager.getRTEString(str, i, map);
    }

    public final Single<String> getRTEString(String action, int method, Map<String, ? extends Object> params) {
        LinkedHashMap linkedHashMap;
        Intrinsics.checkParameterIsNotNull(action, "action");
        JSONObject jSONObject = new JSONObject();
        if (params == null || (linkedHashMap = MapsKt.toMutableMap(params)) == null) {
            linkedHashMap = new LinkedHashMap();
        }
        Map map = linkedHashMap;
        map.put("client", "json");
        Single<String> singleCreate = Single.create(new RightVRequestManager$getRTEString$obs$1(this, action, jSONObject, method, map));
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib…tQueue.add(req)\n        }");
        return singleCreate;
    }

    public final Single<String> getRTEStatus() {
        Single<String> singleCreate = Single.create(new RightVRequestManager$getRTEStatus$obs$1(this, new JSONObject()));
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib…tQueue.add(req)\n        }");
        return singleCreate;
    }

    public final Single<String> getCompassStatus() {
        Single<String> singleCreate = Single.create(new RightVRequestManager$getCompassStatus$obs$1(this, new JSONObject()));
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib…tQueue.add(req)\n        }");
        return singleCreate;
    }

    public final Single<JSONObject> getPADIJSONObject(String action, Map<String, ? extends Object> params) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(params, "params");
        Log.d(this.TAG, "forceNoConnection == " + getForceNoConnection());
        if (getForceNoConnection()) {
            Single<JSONObject> singleError = Single.error(new RequestManager.ForcedNoConnectionError());
            Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error(ForcedNoConnectionError())");
            return singleError;
        }
        Single<JSONObject> singleCreate = Single.create(new C05721(action, params, MapsKt.toMutableMap(params)));
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib…tQueue.add(req)\n        }");
        return singleCreate;
    }

    /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.RightVRequestManager$getPADIJSONObject$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RightVRequestManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "subscriber", "Lio/reactivex/SingleEmitter;", "Lorg/json/JSONObject;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
    static final class C05721<T> implements SingleOnSubscribe<T> {
        final /* synthetic */ String $action;
        final /* synthetic */ Map $mutableParams;
        final /* synthetic */ Map $params;

        C05721(String str, Map map, Map map2) {
            this.$action = str;
            this.$params = map;
            this.$mutableParams = map2;
        }

        @Override // io.reactivex.SingleOnSubscribe
        public final void subscribe(SingleEmitter<JSONObject> subscriber) {
            Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper = new RequestManager.VolleyResponseObserverWrapper(subscriber, new URL(RightVRequestManager.this.padiActionToPath(this.$action)));
            final String strPadiActionToPath = RightVRequestManager.this.padiActionToPath(this.$action);
            final RightVRequestManager rightVRequestManager = RightVRequestManager.this;
            final Map map = this.$mutableParams;
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
            final RequestManager.VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
            final BackendName backendName = BackendName.PADI;
            final int i = 1;
            RightVRequestManager.this.getVolleyRequestQueue().add(new RightVRequest<JSONObject>(i, strPadiActionToPath, rightVRequestManager, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, backendName) { // from class: com.dotscreen.tv.common.bokit.RightVRequestManager$getPADIJSONObject$1$req$1
                {
                    String str = null;
                    int i2 = 128;
                    DefaultConstructorMarker defaultConstructorMarker = null;
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest
                public /* bridge */ /* synthetic */ JSONObject parseData(byte[] bArr, Map map2) {
                    return parseData2(bArr, (Map<String, String>) map2);
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest
                /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                protected JSONObject parseData2(byte[] data, Map<String, String> headers) throws Exception {
                    Intrinsics.checkParameterIsNotNull(data, "data");
                    Intrinsics.checkParameterIsNotNull(headers, "headers");
                    Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                    Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                    return new JSONObject(new String(data, charsetForName));
                }

                @Override // com.dotscreen.tv.common.bokit.RightVRequest, com.android.volley.Request
                public byte[] getBody() {
                    JSONObject jSONObject = new JSONObject(this.this$0.$params);
                    Log.v(RightVRequestManager.this.TAG, "PADI Post => \n" + jSONObject.toString(4));
                    String string = jSONObject.toString();
                    Intrinsics.checkExpressionValueIsNotNull(string, "body.toString()");
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
                    Map mutableMap = MapsKt.toMutableMap(super.getHeaders());
                    mutableMap.put("Content-Type", "application/json");
                    Log.e("REQUESTHEADERS", String.valueOf(mutableMap));
                    return MapsKt.toMap(mutableMap);
                }
            });
        }
    }
}
