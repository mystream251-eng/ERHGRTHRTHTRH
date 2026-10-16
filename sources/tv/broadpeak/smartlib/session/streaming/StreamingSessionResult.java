package tv.broadpeak.smartlib.session.streaming;

import com.google.android.gms.common.internal.ImagesContract;
import com.hippo.quickjs.android.JSArray;
import com.hippo.quickjs.android.JSFunction;
import com.hippo.quickjs.android.JSNumber;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSString;
import com.hippo.quickjs.android.JSValue;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import tv.broadpeak.motorjs.JSContext;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.executor.CoreExecutor;

/* JADX INFO: loaded from: classes3.dex */
public class StreamingSessionResult {
    public static final int RESULT_API_NOT_INITIALIZED = 3401;
    public static final int RESULT_API_PARAMETER_FORMAT_ERROR = 3400;
    public static final int RESULT_CDN_RESPONSE_UNREADABLE = 3101;
    public static final int RESULT_DETAILED_ERROR_PARSING_ERROR = -1;
    public static final int RESULT_NANOCDN_CHANNEL_UNAVAILABLE = 3204;
    public static final int RESULT_NANOCDN_REQUEST_LOCAL_IF_CONNECTED_TO_WIFI_NOT_ALLOWED = 3205;
    public static final int RESULT_NANOCDN_RESPONSE_UNREADABLE = 3206;
    public static final int RESULT_NANOCDN_SERVICE_UNAVAILABLE = 3203;
    public static final int RESULT_NANOCDN_SESSION_LIMIT_REACHED = 3201;
    public static final int RESULT_NANOCDN_SESSION_LIMIT_REACHED_ON_REQUESTED_CHANNEL = 3202;
    public static final int RESULT_NO_DETAILED_ERROR = 0;
    public static final int RESULT_NO_ERROR = 0;
    public static final int RESULT_NO_STREAMING_URL_FOUND = 3100;
    public static final int RESULT_REQUESTED_URL_CDN_ERROR = 3104;
    public static final int RESULT_REQUESTED_URL_NOT_FOUND_ON_CDN = 3102;
    public static final int RESULT_REQUESTED_URL_NOT_FOUND_ON_NANOCDN = 3103;
    public static final int RESULT_REQUEST_ALREADY_DONE = 3501;
    public static final int RESULT_SESSION_HAS_BEEN_STOPPED_DURING_REQUEST = 3500;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CoreExecutor f226a = CoreEngine.getInstance().getCoreExecutor();
    public JSContext b = CoreEngine.getInstance().getJSContext();
    public JSObject c;
    public String d;
    public int e;
    public int f;
    public String g;

    public StreamingSessionResult(JSObject jSObject) {
        this.c = jSObject;
        this.d = ((JSString) jSObject.getProperty(ImagesContract.URL).cast(JSString.class)).getString();
        this.e = ((JSNumber) jSObject.getProperty("errorCode").cast(JSNumber.class)).getInt();
        this.f = ((JSNumber) jSObject.getProperty("detailedErrorCode").cast(JSNumber.class)).getInt();
        this.g = ((JSString) jSObject.getProperty("detailedErrorMessage").cast(JSString.class)).getString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(AtomicReference atomicReference) {
        atomicReference.set(((JSString) ((JSFunction) ((JSObject) ((JSObject) this.b.getGlobalObject().getProperty("smartlib").cast(JSObject.class)).getProperty("StreamingSessionResult").cast(JSObject.class)).getProperty("getErrorMessage").cast(JSFunction.class)).invoke(null, new JSValue[]{this.b.createJSNumber(this.e)}).cast(JSString.class)).getString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(AtomicReference atomicReference) {
        JSArray jSArray = (JSArray) ((JSFunction) this.c.getProperty("getFallbackURLList").cast(JSFunction.class)).invoke(this.c, new JSValue[0]).cast(JSArray.class);
        for (int i = 0; i < jSArray.getLength(); i++) {
            ((ArrayList) atomicReference.get()).add(((JSString) jSArray.getProperty(i).cast(JSString.class)).getString());
        }
    }

    public int getDetailedErrorCode() {
        return this.f;
    }

    public String getDetailedErrorMessage() {
        return this.g;
    }

    public int getErrorCode() {
        return this.e;
    }

    public String getErrorMessage() {
        final AtomicReference atomicReference = new AtomicReference("");
        this.f226a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$StreamingSessionResult$7TbdDSLr5WniYFvWNcxE0e-c8Rk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(atomicReference);
            }
        });
        return (String) atomicReference.get();
    }

    public ArrayList<String> getFallbackURLList() {
        final AtomicReference atomicReference = new AtomicReference(new ArrayList());
        this.f226a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$StreamingSessionResult$PqOVP2ixnNY-fHcTatCqOYLC8lk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.b(atomicReference);
            }
        });
        return (ArrayList) atomicReference.get();
    }

    public String getURL() {
        return this.d;
    }

    public boolean isError() {
        return this.e != 0;
    }

    public String toString() {
        return "StreamingSessionResult{mURL='" + this.d + "', mErrorCode=" + this.e + ", mDetailedErrorCode=" + this.f + ", mDetailedErrorMessage='" + this.g + "'}";
    }
}
