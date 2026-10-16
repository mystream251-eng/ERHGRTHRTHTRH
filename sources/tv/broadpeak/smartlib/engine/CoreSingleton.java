package tv.broadpeak.smartlib.engine;

import com.hippo.quickjs.android.JSFunction;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSValue;
import java.util.HashMap;
import tv.broadpeak.motorjs.QuickJSUtils;

/* JADX INFO: loaded from: classes3.dex */
public class CoreSingleton {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public JSObject f190a;
    public HashMap<String, JSObject> b = new HashMap<>();

    public CoreSingleton(JSObject jSObject) {
        this.f190a = jSObject;
    }

    public void attachHandler(String str, Object... objArr) {
        JSObject singleton = getSingleton(str);
        ((JSFunction) singleton.getProperty("attachHandler").cast(JSFunction.class)).invoke(singleton, QuickJSUtils.toJSValueArray(objArr));
    }

    public JSObject getSingleton(String str) {
        JSObject jSObject = this.b.get(str);
        if (jSObject != null) {
            return jSObject;
        }
        JSObject jSObject2 = (JSObject) this.f190a.getProperty(str).cast(JSObject.class);
        JSObject jSObject3 = (JSObject) ((JSFunction) jSObject2.getProperty("getInstance").cast(JSFunction.class)).invoke(jSObject2, new JSValue[0]).cast(JSObject.class);
        this.b.put(str, jSObject3);
        return jSObject3;
    }

    public void initSingleton(String str, Object... objArr) {
        JSObject singleton = getSingleton(str);
        ((JSFunction) singleton.getProperty("init").cast(JSFunction.class)).invoke(singleton, QuickJSUtils.toJSValueArray(objArr));
    }
}
