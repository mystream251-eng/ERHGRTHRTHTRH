package okhttp3.internal.http;

import com.android.volley.toolbox.HttpClientStack;
import com.viaccessorca.common.VOUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class HttpMethod {
    public static boolean invalidatesCache(String str) {
        return str.equals(VOUtils.HTTP_POST) || str.equals(HttpClientStack.HttpPatch.METHOD_NAME) || str.equals(VOUtils.HTTP_PUT) || str.equals("DELETE") || str.equals("MOVE");
    }

    public static boolean requiresRequestBody(String str) {
        return str.equals(VOUtils.HTTP_POST) || str.equals(VOUtils.HTTP_PUT) || str.equals(HttpClientStack.HttpPatch.METHOD_NAME) || str.equals("PROPPATCH") || str.equals("REPORT");
    }

    public static boolean permitsRequestBody(String str) {
        return (str.equals(VOUtils.HTTP_GET) || str.equals("HEAD")) ? false : true;
    }

    public static boolean redirectsWithBody(String str) {
        return str.equals("PROPFIND");
    }

    public static boolean redirectsToGet(String str) {
        return !str.equals("PROPFIND");
    }

    private HttpMethod() {
    }
}
