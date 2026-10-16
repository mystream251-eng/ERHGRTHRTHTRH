package tv.broadpeak.smartlib.request;

import com.hippo.quickjs.android.JSFunction;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSValue;
import com.hippo.quickjs.android.Method;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;
import tv.broadpeak.motorjs.JSContext;
import tv.broadpeak.motorjs.QuickJSUtils;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.executor.CoreJSCallback;
import tv.broadpeak.smartlib.engine.executor.MotorJSStringCallback;

/* JADX INFO: loaded from: classes3.dex */
public class RequestManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CoreEngine f219a;
    public JSContext b;

    public class a extends MotorJSStringCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AtomicReference f220a;

        public a(RequestManager requestManager, AtomicReference atomicReference) {
            this.f220a = atomicReference;
        }

        @Override // tv.broadpeak.smartlib.engine.executor.MotorJSStringCallback
        public void callback(String str) {
            this.f220a.set(str);
            super.callback(str);
        }
    }

    public RequestManager(CoreEngine coreEngine) {
        this.f219a = coreEngine;
        this.b = coreEngine.getJSContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(HashMap map, String str, MotorJSStringCallback motorJSStringCallback, JSObject jSObject) {
        JSObject jSObjectCreateJSObject = this.b.createJSObject();
        jSObjectCreateJSObject.setProperty("analyticsAddress", this.b.createJSString((String) map.get("analyticsAddress")));
        jSObjectCreateJSObject.setProperty("nanoCDNHost", this.b.createJSString((String) map.get("nanoCDNHost")));
        jSObjectCreateJSObject.setProperty("nanoCDNHostConfigured", this.b.createJSString((String) map.get("nanoCDNHostConfigured")));
        jSObjectCreateJSObject.setProperty("broadpeakDomainNames", this.b.createJSString((String) map.get("broadpeakDomainNames")));
        jSObjectCreateJSObject.setProperty("forceTeardown", this.b.createJSNumber(((Integer) map.get("forceTeardown")).intValue()));
        jSObjectCreateJSObject.setProperty("userAgent", this.b.createJSString((String) map.get("userAgent")));
        JSObject jSObject2 = (JSObject) ((JSFunction) jSObject.getProperty("getURL").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{this.b.createJSString(str), jSObjectCreateJSObject}).cast(JSObject.class);
        try {
            ((JSFunction) jSObject2.getProperty("then").cast(JSFunction.class)).invoke(jSObject2, new JSValue[]{this.b.createJSFunction(motorJSStringCallback, Method.create(Void.class, MotorJSStringCallback.class.getMethod("callback", String.class)))});
            QuickJSUtils.executePendingJobs(this.b);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Object> createOptions(String str, String str2, String str3, String str4, int i, String str5) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("analyticsAddress", str);
        map.put("nanoCDNHost", str2);
        map.put("nanoCDNHostConfigured", str3);
        map.put("broadpeakDomainNames", str4);
        map.put("forceTeardown", Integer.valueOf(i));
        map.put("userAgent", str5);
        return map;
    }

    public String getURL(final String str, final HashMap<String, Object> map) {
        AtomicReference atomicReference = new AtomicReference("");
        final a aVar = new a(this, atomicReference);
        this.f219a.getSingleton("RequestManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.request.-$$Lambda$RequestManager$v6y3PqqmQiLwebIovPNCcVGAMB8
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                this.f$0.a(map, str, aVar, jSObject);
            }
        });
        aVar.waitCallback();
        return (String) atomicReference.get();
    }
}
