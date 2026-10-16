package tv.broadpeak.smartlib.engine.system;

import android.content.Context;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.hippo.quickjs.android.JSFunction;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSValue;
import com.hippo.quickjs.android.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import tv.broadpeak.motorjs.JSContext;
import tv.broadpeak.motorjs.QuickJSUtils;
import tv.broadpeak.smartlib.engine.executor.CoreWorker;

/* JADX INFO: loaded from: classes3.dex */
public class RequestHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConnectionPool f209a = new ConnectionPool(15, 5, TimeUnit.MINUTES);
    public JSContext b;

    public static class GetRequest implements CoreWorker.WorkerTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Call f210a;

        public GetRequest(Call call) {
            this.f210a = call;
        }

        @Override // tv.broadpeak.smartlib.engine.executor.CoreWorker.WorkerTask
        public void cancel() {
            Call call = this.f210a;
            if (call != null) {
                try {
                    call.cancel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public RequestResult execute() {
            String str;
            int i;
            ResponseBody responseBody;
            String strMessage;
            Response responseExecute;
            String strString = "";
            HashMap map = new HashMap();
            ResponseBody responseBodyBody = null;
            int iCode = -1;
            try {
                responseExecute = this.f210a.execute();
                try {
                    for (String str2 : responseExecute.headers().names()) {
                        map.put(str2.toLowerCase(), responseExecute.headers(str2).get(0));
                    }
                    iCode = responseExecute.code();
                    strMessage = responseExecute.message();
                    try {
                        responseBodyBody = responseExecute.body();
                        if (responseBodyBody != null) {
                            strString = responseBodyBody.string();
                        }
                    } catch (Exception e) {
                        responseBody = responseBodyBody;
                        responseBodyBody = responseExecute;
                        i = iCode;
                        str = strMessage;
                        e = e;
                        e.printStackTrace();
                        strMessage = str;
                        int i2 = i;
                        responseExecute = responseBodyBody;
                        responseBodyBody = responseBody;
                        iCode = i2;
                    }
                } catch (Exception e2) {
                    e = e2;
                    str = "";
                    int i3 = iCode;
                    responseBody = null;
                    responseBodyBody = responseExecute;
                    i = i3;
                }
            } catch (Exception e3) {
                e = e3;
                str = "";
                i = -1;
                responseBody = null;
            }
            if (responseBodyBody != null) {
                try {
                    responseBodyBody.close();
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
            if (responseExecute != null) {
                try {
                    responseExecute.close();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
            return new RequestResult(strString, map, iCode, strMessage);
        }
    }

    public static class PostRequest implements CoreWorker.WorkerTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Call f211a;

        public PostRequest(Call call) {
            this.f211a = call;
        }

        @Override // tv.broadpeak.smartlib.engine.executor.CoreWorker.WorkerTask
        public void cancel() {
            Call call = this.f211a;
            if (call != null) {
                try {
                    call.cancel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public RequestResult execute() {
            String str;
            int i;
            ResponseBody responseBody;
            String strMessage;
            Response responseExecute;
            String strString = "";
            HashMap map = new HashMap();
            ResponseBody responseBodyBody = null;
            int iCode = -1;
            try {
                responseExecute = this.f211a.execute();
                try {
                    for (String str2 : responseExecute.headers().names()) {
                        map.put(str2.toLowerCase(), responseExecute.headers(str2).get(0));
                    }
                    iCode = responseExecute.code();
                    strMessage = responseExecute.message();
                    try {
                        responseBodyBody = responseExecute.body();
                        if (responseBodyBody != null) {
                            strString = responseBodyBody.string();
                        }
                    } catch (Exception e) {
                        responseBody = responseBodyBody;
                        responseBodyBody = responseExecute;
                        i = iCode;
                        str = strMessage;
                        e = e;
                        e.printStackTrace();
                        strMessage = str;
                        int i2 = i;
                        responseExecute = responseBodyBody;
                        responseBodyBody = responseBody;
                        iCode = i2;
                    }
                } catch (Exception e2) {
                    e = e2;
                    str = "";
                    int i3 = iCode;
                    responseBody = null;
                    responseBodyBody = responseExecute;
                    i = i3;
                }
            } catch (Exception e3) {
                e = e3;
                str = "";
                i = -1;
                responseBody = null;
            }
            if (responseBodyBody != null) {
                try {
                    responseBodyBody.close();
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
            if (responseExecute != null) {
                try {
                    responseExecute.close();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
            return new RequestResult(strString, map, iCode, strMessage);
        }
    }

    public static class RequestResult implements CoreWorker.WorkerResult {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f212a;
        public Map<String, String> b;
        public int c;
        public String d;

        public RequestResult(String str, Map<String, String> map, int i, String str2) {
            this.f212a = str;
            this.b = map;
            this.c = i;
            this.d = str2;
        }

        @Override // tv.broadpeak.smartlib.engine.executor.CoreWorker.WorkerResult
        public JSObject toJSObject(JSContext jSContext) {
            JSObject jSObjectCreateJSObject = jSContext.createJSObject();
            JSObject jSObjectCreateJSObject2 = jSContext.createJSObject();
            Map<String, String> map = this.b;
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    jSObjectCreateJSObject2.setProperty(entry.getKey(), jSContext.createJSString(entry.getValue()));
                }
            }
            jSObjectCreateJSObject.setProperty(TtmlNode.TAG_BODY, QuickJSUtils.toJS(jSContext, this.f212a));
            jSObjectCreateJSObject.setProperty("headers", jSObjectCreateJSObject2);
            jSObjectCreateJSObject.setProperty("statusCode", jSContext.createJSNumber(this.c));
            jSObjectCreateJSObject.setProperty("message", jSContext.createJSString(this.d));
            return jSObjectCreateJSObject;
        }

        public String toString() {
            return "RequestResult{mBody='" + this.f212a + "', mHeaders=" + this.b + ", mStatusCode=" + this.c + ", mMessage='" + this.d + "'}";
        }
    }

    public RequestHandler(Context context, JSContext jSContext) {
        this.b = jSContext;
    }

    public final OkHttpClient a(int i) {
        OkHttpClient.Builder builderFollowRedirects = new OkHttpClient().newBuilder().followRedirects(false);
        long j = i;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        return builderFollowRedirects.connectTimeout(j, timeUnit).writeTimeout(j, timeUnit).readTimeout(j, timeUnit).connectionPool(f209a).build();
    }

    public GetRequest createGetRequest(String str, Map<String, String> map, int i) {
        return new GetRequest(a(i).newCall(new Request.Builder().url(str).headers(a(map)).build()));
    }

    public JSObject createJSObject() {
        JSObject jSObjectCreateJSObject = this.b.createJSObject();
        try {
            JSContext jSContext = this.b;
            Class cls = Integer.TYPE;
            jSObjectCreateJSObject.setProperty("get", jSContext.createJSFunction(this, Method.create(Void.class, RequestHandler.class.getMethod("get", String.class, JSValue.class, cls, JSValue.class))));
            jSObjectCreateJSObject.setProperty("post", this.b.createJSFunction(this, Method.create(Void.class, RequestHandler.class.getMethod("post", String.class, JSValue.class, String.class, cls, JSValue.class))));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return jSObjectCreateJSObject;
    }

    public PostRequest createPostRequest(String str, Map<String, String> map, String str2, int i) {
        return new PostRequest(a(i).newCall(new Request.Builder().url(str).post(RequestBody.create(MediaType.parse("text"), str2)).headers(a(map)).build()));
    }

    public void get(String str, JSValue jSValue, int i, JSValue jSValue2) {
        ((JSFunction) jSValue2.cast(JSFunction.class)).invoke(null, new JSValue[]{createGetRequest(str, a((JSObject) jSValue.cast(JSObject.class)), i).execute().toJSObject(this.b)});
    }

    public void post(String str, JSValue jSValue, String str2, int i, JSValue jSValue2) {
        ((JSFunction) jSValue2.cast(JSFunction.class)).invoke(null, new JSValue[]{createPostRequest(str, a((JSObject) jSValue.cast(JSObject.class)), str2, i).execute().toJSObject(this.b)});
    }

    public final Map<String, String> a(JSObject jSObject) {
        Map<String, Object> map = QuickJSUtils.toMap(this.b, jSObject);
        HashMap map2 = new HashMap();
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj instanceof String) {
                map2.put(str, (String) obj);
            }
        }
        return map2;
    }

    public final Headers a(Map<String, String> map) {
        Headers.Builder builder = new Headers.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }
}
