package com.dotscreen.tv.common.bokit;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.dotscreen.farkit.network.RequestManager;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.dotscreen.tv.common.utils.SentryReportHint;
import com.google.android.gms.common.internal.ImagesContract;
import com.viaccessorca.drm.impl.JniCDxJavaHttpAgent;
import io.sentry.Sentry;
import io.sentry.SentryEvent;
import io.sentry.protocol.Message;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* JADX INFO: compiled from: RightVRequestManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 ,*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001,B[\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0006¢\u0006\u0002\u0010\u0013J\b\u0010\u0015\u001a\u00020\u0006H\u0002J\u0015\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u0006H\u0002J\n\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0006H\u0016J\u0014\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\nH\u0016J\n\u0010 \u001a\u0004\u0018\u00010\u001dH\u0017J\b\u0010!\u001a\u00020\u0006H\u0017J\b\u0010\"\u001a\u00020\u0006H\u0016J\u0012\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010\u0018\u001a\u00020%H\u0002J)\u0010&\u001a\u00028\u00002\u0006\u0010'\u001a\u00020\u001d2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\nH$¢\u0006\u0002\u0010)J\u0016\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000+2\u0006\u0010\u0018\u001a\u00020%H\u0014R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RightVRequest;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/android/volley/Request;", "method", "", ImagesContract.URL, "", "mRequestManager", "Lcom/dotscreen/farkit/network/RequestManager;", "mParameters", "", "", "mListener", "Lcom/android/volley/Response$Listener;", "errorListener", "Lcom/android/volley/Response$ErrorListener;", "serviceName", "Lcom/dotscreen/tv/common/bokit/BackendName;", "userAgentDevId", "(ILjava/lang/String;Lcom/dotscreen/farkit/network/RequestManager;Ljava/util/Map;Lcom/android/volley/Response$Listener;Lcom/android/volley/Response$ErrorListener;Lcom/dotscreen/tv/common/bokit/BackendName;Ljava/lang/String;)V", "mAuthToken", "debugParameters", "deliverResponse", "", "response", "(Ljava/lang/Object;)V", "extractContentType", "str", "getBody", "", "getBodyContentType", "getHeaders", "getPostBody", "getPostBodyContentType", "getUrl", "parseCacheHeaders", "Lcom/android/volley/Cache$Entry;", "Lcom/android/volley/NetworkResponse;", "parseData", "data", "headers", "([BLjava/util/Map;)Ljava/lang/Object;", "parseNetworkResponse", "Lcom/android/volley/Response;", "Companion", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public abstract class RightVRequest<T> extends Request<T> {
    private static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private final String mAuthToken;
    private final Response.Listener<T> mListener;
    private final Map<String, Object> mParameters;
    private final RequestManager mRequestManager;
    private final BackendName serviceName;
    private final String userAgentDevId;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = RightVRequest.class.getSimpleName();

    @Override // com.android.volley.Request
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    protected abstract T parseData(byte[] data, Map<String, String> headers) throws Exception;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RightVRequest(int i, String url, RequestManager mRequestManager, Map<String, ? extends Object> map, Response.Listener<T> mListener, Response.ErrorListener errorListener, BackendName serviceName, String userAgentDevId) {
        super(i, url, errorListener);
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(mRequestManager, "mRequestManager");
        Intrinsics.checkParameterIsNotNull(mListener, "mListener");
        Intrinsics.checkParameterIsNotNull(errorListener, "errorListener");
        Intrinsics.checkParameterIsNotNull(serviceName, "serviceName");
        Intrinsics.checkParameterIsNotNull(userAgentDevId, "userAgentDevId");
        this.mRequestManager = mRequestManager;
        this.mParameters = map;
        this.mListener = mListener;
        this.serviceName = serviceName;
        this.userAgentDevId = userAgentDevId;
        setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));
        Log.v(TAG, "start downloading [" + i + "] \"" + getUrl() + "\"");
        if (i == 1 || i == 2) {
            Log.v(TAG, "  with params: " + debugParameters());
        }
    }

    public /* synthetic */ RightVRequest(int i, String str, RequestManager requestManager, Map map, Response.Listener listener, Response.ErrorListener errorListener, BackendName backendName, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, requestManager, map, listener, errorListener, backendName, (i2 & 128) != 0 ? "" : str2);
    }

    private final String debugParameters() {
        String string;
        Map<String, Object> map = this.mParameters;
        if (map == null || (string = map.toString()) == null) {
            try {
                byte[] body = getBody();
                if (body == null) {
                    body = new byte[0];
                }
                Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(getHeaders()));
                Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                string = new JSONObject(new String(body, charsetForName)).toString(4);
            } catch (Exception unused) {
                string = "Unknown params format";
            }
            Intrinsics.checkExpressionValueIsNotNull(string, "try {\n                  …format\"\n                }");
        }
        return string;
    }

    @Override // com.android.volley.Request
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        JSONObject jSONObject;
        Map<String, String> map;
        Intrinsics.checkParameterIsNotNull(response, "response");
        JSONObject jSONObject2 = null;
        try {
            Log.v(TAG, "   done downloading \"" + StringsKt.replace$default(getUrl(), "[]", "%5B%5D", false, 4, (Object) null) + "\"");
            Log.v(TAG, "   status: " + response.statusCode);
            DSLog.INSTANCE.r(getUrl(), response.notModified && getCacheEntry() != null);
            if (response.notModified && getCacheEntry() != null) {
                map = getCacheEntry().responseHeaders;
                Intrinsics.checkExpressionValueIsNotNull(map, "cacheEntry.responseHeaders");
            } else {
                map = response.headers;
                Intrinsics.checkExpressionValueIsNotNull(map, "response.headers");
            }
            try {
                byte[] bArr = response.data;
                Intrinsics.checkExpressionValueIsNotNull(bArr, "response.data");
                Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(map));
                Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                JSONObject jSONObject3 = new JSONObject(new String(bArr, charsetForName)).getJSONObject("response");
                if (Intrinsics.areEqual(jSONObject3.getString(NotificationCompat.CATEGORY_STATUS), "FAILURE")) {
                    String strOptString = jSONObject3.optString("message");
                    Intrinsics.checkExpressionValueIsNotNull(strOptString, "jsonResponse.optString(\"message\")");
                    String shortName = this.serviceName.getShortName();
                    String strOptString2 = jSONObject3.optString("code");
                    Intrinsics.checkExpressionValueIsNotNull(strOptString2, "jsonResponse.optString(\"code\")");
                    Response<T> responseError = Response.error(new NetworkError(new RightVBackendErrorException(strOptString, shortName, strOptString2, getUrl(), response.statusCode)));
                    Intrinsics.checkExpressionValueIsNotNull(responseError, "Response.error(NetworkEr…e\n                    )))");
                    return responseError;
                }
            } catch (Throwable unused) {
            }
            byte[] bArr2 = response.data;
            Intrinsics.checkExpressionValueIsNotNull(bArr2, "response.data");
            Object data = parseData(bArr2, map);
            if (this.serviceName == BackendName.IDPM && (data instanceof JSONObject)) {
                String string = ((JSONObject) data).getString("result");
                Intrinsics.checkExpressionValueIsNotNull(string, "t.getString(\"result\")");
                if (StringsKt.startsWith$default(string, "error", false, 2, (Object) null)) {
                    Log.e(TAG, " Error IDPM (toolbox login): " + data);
                    String errorMessage = ((JSONObject) data).getString("result");
                    Pattern patternCompile = Pattern.compile("-?\\d+");
                    Intrinsics.checkExpressionValueIsNotNull(patternCompile, "Pattern.compile(\"-?\\\\d+\")");
                    Matcher matcher = patternCompile.matcher(errorMessage);
                    Intrinsics.checkExpressionValueIsNotNull(matcher, "p.matcher(errorMessage)");
                    String errorCode = matcher.find() ? matcher.group() : LocalSearchProvider.NO_LIVE_CONTENTS;
                    Intrinsics.checkExpressionValueIsNotNull(errorMessage, "errorMessage");
                    String shortName2 = this.serviceName.getShortName();
                    Intrinsics.checkExpressionValueIsNotNull(errorCode, "errorCode");
                    Response<T> responseError2 = Response.error(new NetworkError(new RightVBackendErrorException(errorMessage, shortName2, errorCode, getUrl(), response.statusCode)));
                    Intrinsics.checkExpressionValueIsNotNull(responseError2, "Response.error(NetworkEr…sCode\n                )))");
                    return responseError2;
                }
            }
            Response<T> responseSuccess = Response.success(data, parseCacheHeaders(response));
            Intrinsics.checkExpressionValueIsNotNull(responseSuccess, "Response.success(t, parseCacheHeaders(response))");
            return responseSuccess;
        } catch (Throwable th) {
            byte[] bArr3 = response.data;
            Intrinsics.checkExpressionValueIsNotNull(bArr3, "response.data");
            Charset charsetForName2 = Charset.forName(HttpHeaderParser.parseCharset(getHeaders()));
            Intrinsics.checkExpressionValueIsNotNull(charsetForName2, "Charset.forName(HttpHead…er.parseCharset(headers))");
            String str = new String(bArr3, charsetForName2);
            try {
                jSONObject = new JSONObject(str);
            } catch (Exception unused2) {
                jSONObject = null;
            }
            SentryEvent sentryEvent = new SentryEvent();
            try {
                byte[] body = getBody();
                if (body == null) {
                    body = new byte[0];
                }
                Charset charsetForName3 = Charset.forName(HttpHeaderParser.parseCharset(getHeaders()));
                Intrinsics.checkExpressionValueIsNotNull(charsetForName3, "Charset.forName(HttpHead…er.parseCharset(headers))");
                jSONObject2 = new JSONObject(new String(body, charsetForName3));
            } catch (Exception unused3) {
            }
            Message message = new Message();
            message.setMessage(this.serviceName.name() + " request error");
            sentryEvent.setMessage(message);
            sentryEvent.setTag(ImagesContract.URL, getUrl());
            sentryEvent.setExtra(ImagesContract.URL, getUrl());
            Map<String, Object> map2 = this.mParameters;
            if (map2 != null) {
                sentryEvent.setExtra("parameters", map2);
            }
            if (jSONObject2 != null) {
                sentryEvent.setExtra("requestBody", jSONObject2);
            }
            sentryEvent.setExtra("serviceName", this.serviceName.name());
            Object obj = str;
            if (jSONObject != null) {
                obj = jSONObject;
            }
            sentryEvent.setExtra("response", obj);
            sentryEvent.setExtra("cause", th);
            Sentry.captureEvent(sentryEvent, SentryReportHint.RightV);
            Log.e("RequestManager", String.valueOf(this.serviceName));
            Response<T> responseError3 = Response.error(new RightVServerError(response, this.serviceName));
            Intrinsics.checkExpressionValueIsNotNull(responseError3, "Response.error(RightVSer…r(response, serviceName))");
            return responseError3;
        }
    }

    private final Cache.Entry parseCacheHeaders(NetworkResponse response) {
        return HttpHeaderParser.parseCacheHeaders(response);
    }

    @Override // com.android.volley.Request
    protected void deliverResponse(T response) {
        this.mListener.onResponse(response);
    }

    @Override // com.android.volley.Request
    public String getUrl() {
        String url;
        int method = getMethod();
        if (method == 0 || method == 3) {
            try {
                String strQueryString = INSTANCE.queryString(this.mParameters, PROTOCOL_CHARSET);
                if (strQueryString.length() > 0) {
                    strQueryString = '?' + strQueryString;
                }
                url = super.getUrl() + strQueryString;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                url = super.getUrl();
            }
            Intrinsics.checkExpressionValueIsNotNull(url, "try {\n                  …etUrl()\n                }");
            return url;
        }
        String url2 = super.getUrl();
        Intrinsics.checkExpressionValueIsNotNull(url2, "super.getUrl()");
        return url2;
    }

    @Override // com.android.volley.Request
    @Deprecated(message = "Use {@link #getBodyContentType()}.")
    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    @Override // com.android.volley.Request
    @Deprecated(message = "Use {@link #getBody()}.")
    public byte[] getPostBody() {
        return getBody();
    }

    @Override // com.android.volley.Request
    public byte[] getBody() {
        try {
            if (this.mParameters == null) {
                return null;
            }
            String strQueryString = INSTANCE.queryString(this.mParameters, PROTOCOL_CHARSET);
            Charset charsetForName = Charset.forName(PROTOCOL_CHARSET);
            Intrinsics.checkNotNullExpressionValue(charsetForName, "Charset.forName(charsetName)");
            if (strQueryString == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            byte[] bytes = strQueryString.getBytes(charsetForName);
            Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
            return bytes;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private final String extractContentType(String str) {
        List listEmptyList;
        if (str == null) {
            return null;
        }
        List<String> listSplit = new Regex("\\;").split(str, 0);
        if (!listSplit.isEmpty()) {
            ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
            while (true) {
                if (listIterator.hasPrevious()) {
                    if (!(listIterator.previous().length() == 0)) {
                        listEmptyList = CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                        break;
                    }
                } else {
                    listEmptyList = CollectionsKt.emptyList();
                    break;
                }
            }
        } else {
            listEmptyList = CollectionsKt.emptyList();
            break;
        }
        Object[] array = listEmptyList.toArray(new String[0]);
        if (array != null) {
            String str2 = ((String[]) array)[0];
            int length = str2.length() - 1;
            int i = 0;
            boolean z = false;
            while (i <= length) {
                boolean z2 = str2.charAt(!z ? i : length) <= ' ';
                if (z) {
                    if (!z2) {
                        break;
                    }
                    length--;
                } else if (z2) {
                    i++;
                } else {
                    z = true;
                }
            }
            return str2.subSequence(i, length + 1).toString();
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    @Override // com.android.volley.Request
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap map = new HashMap(super.getHeaders());
        if (this.mAuthToken != null) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("Token token=\"%s\"", Arrays.copyOf(new Object[]{this.mAuthToken}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
            map.put("Authorization", str);
        }
        Context context = this.mRequestManager.getContext();
        String packageName = context.getPackageName();
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        Intrinsics.checkExpressionValueIsNotNull(packageInfo, "context.packageManager.g…ckageInfo(packageName, 0)");
        String str2 = (String) map.get(JniCDxJavaHttpAgent.DX_USER_AGENT_HDR);
        Log.v(TAG, "Default user agent : " + str2);
        if (true ^ StringsKt.isBlank(this.userAgentDevId)) {
            if (str2 != null) {
                map.put(JniCDxJavaHttpAgent.DX_USER_AGENT_HDR, str2 + ' ' + packageName + ' ' + packageInfo.versionName + " DevID/" + this.userAgentDevId);
            } else {
                map.put(JniCDxJavaHttpAgent.DX_USER_AGENT_HDR, System.getProperty("http.agent") + ' ' + packageName + ' ' + packageInfo.versionName + " DevID/" + this.userAgentDevId);
            }
        } else if (str2 != null) {
            map.put(JniCDxJavaHttpAgent.DX_USER_AGENT_HDR, str2 + ' ' + packageName + ' ' + packageInfo.versionName);
        } else {
            map.put(JniCDxJavaHttpAgent.DX_USER_AGENT_HDR, System.getProperty("http.agent") + ' ' + packageName + ' ' + packageInfo.versionName);
        }
        Log.v(TAG, "Updated user agent : " + str2);
        HashMap map2 = map;
        for (Map.Entry entry : map2.entrySet()) {
            Log.v(TAG, "    headers: " + ((String) entry.getKey()) + " , " + ((String) entry.getValue()));
        }
        return map2;
    }

    /* JADX INFO: compiled from: RightVRequestManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\b\u001a\u00020\u00042\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0004J,\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000e0\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0010\u001a\u00020\u0001H\u0002J.\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000e0\r2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RightVRequest$Companion;", "", "()V", "PROTOCOL_CHARSET", "", "PROTOCOL_CONTENT_TYPE", "TAG", "kotlin.jvm.PlatformType", "queryString", "params", "", "encoding", "queryStringPairs", "", "Lkotlin/Pair;", "key", "value", "map", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String queryString(Map<String, ? extends Object> params, String encoding) throws UnsupportedEncodingException {
            Intrinsics.checkParameterIsNotNull(encoding, "encoding");
            String str = new String();
            if (params != null) {
                int i = 0;
                for (Pair<String, String> pair : queryStringPairs(params)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    int i2 = i + 1;
                    sb.append(i == 0 ? "" : "&");
                    i = i2;
                    str = sb.toString() + URLEncoder.encode(pair.getFirst(), encoding) + "=" + URLEncoder.encode(pair.getSecond(), encoding);
                }
            }
            return str;
        }

        private final List<Pair<String, String>> queryStringPairs(Map<String, ? extends Object> map) {
            return queryStringPairs(null, map);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final List<Pair<String, String>> queryStringPairs(String key, Object value) {
            ArrayList arrayList = new ArrayList();
            if (value instanceof Map) {
                Map map = (Map) value;
                Object[] array = map.keySet().toArray(new Object[0]);
                if (array != null) {
                    Arrays.sort(array, new Comparator<T>() { // from class: com.dotscreen.tv.common.bokit.RightVRequest$Companion$queryStringPairs$1
                        @Override // java.util.Comparator
                        public final int compare(Object obj, Object obj2) {
                            return obj.toString().compareTo(obj2.toString());
                        }
                    });
                    for (Object obj : array) {
                        Object obj2 = map.get(obj);
                        if (obj2 != null) {
                            arrayList.addAll(queryStringPairs(key != null ? key + '[' + obj + ']' : String.valueOf(obj), obj2));
                        }
                    }
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            } else if (value instanceof List) {
                for (Object obj3 : (List) value) {
                    Companion companion = this;
                    if (key == null) {
                        Intrinsics.throwNpe();
                    }
                    if (obj3 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Any");
                    }
                    arrayList.addAll(companion.queryStringPairs(key, obj3));
                }
            } else {
                if (key == null) {
                    Intrinsics.throwNpe();
                }
                arrayList.add(new Pair(key, value.toString()));
            }
            return arrayList;
        }
    }
}
