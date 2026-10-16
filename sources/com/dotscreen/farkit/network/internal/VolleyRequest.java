package com.dotscreen.farkit.network.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.dotscreen.farkit.network.RequestManagerKt;
import com.dotscreen.farkit.tools.DSLog;
import com.google.android.gms.common.internal.ImagesContract;
import com.viaccessorca.drm.impl.JniCDxJavaHttpAgent;
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

/* JADX INFO: compiled from: VolleyRequest.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b \u0018\u0000 (*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001(BI\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u0015\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u0004\u0018\u00010\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\bH\u0002J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\bH\u0016J\u0014\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\nH\u0016J\n\u0010\u001c\u001a\u0004\u0018\u00010\u0019H\u0017J\b\u0010\u001d\u001a\u00020\bH\u0017J\b\u0010\u001e\u001a\u00020\bH\u0016J\u0012\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010\u0014\u001a\u00020!H\u0002J)\u0010\"\u001a\u00028\u00002\u0006\u0010#\u001a\u00020\u00192\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\nH$¢\u0006\u0002\u0010%J\u0016\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000'2\u0006\u0010\u0014\u001a\u00020!H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/dotscreen/farkit/network/internal/VolleyRequest;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/android/volley/Request;", "context", "Landroid/content/Context;", "method", "", ImagesContract.URL, "", "mParameters", "", "", "mListener", "Lcom/android/volley/Response$Listener;", "errorListener", "Lcom/android/volley/Response$ErrorListener;", "(Landroid/content/Context;ILjava/lang/String;Ljava/util/Map;Lcom/android/volley/Response$Listener;Lcom/android/volley/Response$ErrorListener;)V", "mAuthToken", "deliverResponse", "", "response", "(Ljava/lang/Object;)V", "extractContentType", "str", "getBody", "", "getBodyContentType", "getHeaders", "getPostBody", "getPostBodyContentType", "getUrl", "parseCacheHeaders", "Lcom/android/volley/Cache$Entry;", "Lcom/android/volley/NetworkResponse;", "parseData", "data", "headers", "([BLjava/util/Map;)Ljava/lang/Object;", "parseNetworkResponse", "Lcom/android/volley/Response;", "Companion", "farkit_release"}, k = 1, mv = {1, 1, 16})
public abstract class VolleyRequest<T> extends Request<T> {
    private static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private final Context context;
    private final String mAuthToken;
    private final Response.Listener<T> mListener;
    private final Map<String, Object> mParameters;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = VolleyRequest.class.getSimpleName();

    @Override // com.android.volley.Request
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    protected abstract T parseData(byte[] data, Map<String, String> headers) throws Exception;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VolleyRequest(Context context, int i, String url, Map<String, ? extends Object> map, Response.Listener<T> mListener, Response.ErrorListener errorListener) {
        super(i, url, errorListener);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(mListener, "mListener");
        Intrinsics.checkParameterIsNotNull(errorListener, "errorListener");
        this.context = context;
        this.mParameters = map;
        this.mListener = mListener;
        setRetryPolicy(new DefaultRetryPolicy((int) RequestManagerKt.getDefaultConnectionTimeout(), 0, 1.0f));
        Log.v(TAG, "start downloading [" + i + "] \"" + getUrl() + "\"");
        if (i == 1 || i == 2) {
            Log.v(TAG, "  with params: " + this.mParameters);
        }
    }

    @Override // com.android.volley.Request
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        Map<String, String> map;
        Intrinsics.checkParameterIsNotNull(response, "response");
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
            byte[] bArr = response.data;
            Intrinsics.checkExpressionValueIsNotNull(bArr, "response.data");
            Response<T> responseSuccess = Response.success(parseData(bArr, map), parseCacheHeaders(response));
            Intrinsics.checkExpressionValueIsNotNull(responseSuccess, "Response.success(t, parseCacheHeaders(response))");
            return responseSuccess;
        } catch (Throwable th) {
            Response<T> responseError = Response.error(new ParseError(th));
            Intrinsics.checkExpressionValueIsNotNull(responseError, "Response.error(ParseError(e))");
            return responseError;
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
            Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(charsetName)");
            if (strQueryString == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            byte[] bytes = strQueryString.getBytes(charsetForName);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
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
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    @Override // com.android.volley.Request
    public Map<String, String> getHeaders() throws PackageManager.NameNotFoundException, AuthFailureError {
        HashMap map = new HashMap(super.getHeaders());
        if (this.mAuthToken != null) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("Token token=\"%s\"", Arrays.copyOf(new Object[]{this.mAuthToken}, 1));
            Intrinsics.checkExpressionValueIsNotNull(str, "java.lang.String.format(format, *args)");
            map.put("Authorization", str);
        }
        String packageName = this.context.getPackageName();
        PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo(packageName, 0);
        Intrinsics.checkExpressionValueIsNotNull(packageInfo, "context.packageManager.g…ckageInfo(packageName, 0)");
        String str2 = (String) map.get(JniCDxJavaHttpAgent.DX_USER_AGENT_HDR);
        Log.v(TAG, "Default user agent : " + str2);
        if (str2 != null) {
            map.put(JniCDxJavaHttpAgent.DX_USER_AGENT_HDR, str2 + ' ' + packageName + ' ' + packageInfo.versionName + "  ");
        } else {
            map.put(JniCDxJavaHttpAgent.DX_USER_AGENT_HDR, System.getProperty("http.agent") + ' ' + packageName + ' ' + packageInfo.versionName + "  ");
        }
        Log.v(TAG, "Updated user agent : " + str2);
        return map;
    }

    /* JADX INFO: compiled from: VolleyRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\b\u001a\u00020\u00042\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0004J,\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000e0\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0010\u001a\u00020\u0001H\u0002J.\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000e0\r2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/farkit/network/internal/VolleyRequest$Companion;", "", "()V", "PROTOCOL_CHARSET", "", "PROTOCOL_CONTENT_TYPE", "TAG", "kotlin.jvm.PlatformType", "queryString", "params", "", "encoding", "queryStringPairs", "", "Lkotlin/Pair;", "key", "value", "map", "farkit_release"}, k = 1, mv = {1, 1, 16})
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
                    Arrays.sort(array, new Comparator<T>() { // from class: com.dotscreen.farkit.network.internal.VolleyRequest$Companion$queryStringPairs$1
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
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            } else if (value instanceof List) {
                for (Object obj3 : (List) value) {
                    Companion companion = this;
                    StringBuilder sb = new StringBuilder();
                    if (key == null) {
                        Intrinsics.throwNpe();
                    }
                    sb.append(key);
                    sb.append("[]");
                    String string = sb.toString();
                    if (obj3 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Any");
                    }
                    arrayList.addAll(companion.queryStringPairs(string, obj3));
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
