package tv.broadpeak.smartlib.network;

import com.hippo.quickjs.android.JSArray;
import com.hippo.quickjs.android.JSFunction;
import com.hippo.quickjs.android.JSNumber;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSString;
import com.hippo.quickjs.android.JSValue;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.executor.CoreJSCallback;

/* JADX INFO: loaded from: classes3.dex */
public class NanoCDNHostManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CoreEngine f214a;

    public NanoCDNHostManager(CoreEngine coreEngine) {
        this.f214a = coreEngine;
        coreEngine.getJSContext();
    }

    public static /* synthetic */ void a(ArrayList arrayList, JSObject jSObject) {
        JSObject jSObject2 = (JSObject) jSObject.getProperty("nanoCDNHostManager").cast(JSObject.class);
        JSArray jSArray = (JSArray) ((JSFunction) jSObject2.getProperty("getNanoCDNDescrList").cast(JSFunction.class)).invoke(jSObject2, new JSValue[0]).cast(JSArray.class);
        for (int i = 0; i < jSArray.getLength(); i++) {
            JSObject jSObject3 = (JSObject) jSArray.getProperty(i).cast(JSObject.class);
            arrayList.add(new NanoCDNDescr(((JSString) jSObject3.getProperty("hostIP").cast(JSString.class)).getString(), ((JSNumber) jSObject3.getProperty("nbSessions").cast(JSNumber.class)).getInt(), ((JSNumber) jSObject3.getProperty("maxNbSessions").cast(JSNumber.class)).getInt(), ((JSNumber) jSObject3.getProperty("ratio").cast(JSNumber.class)).getDouble(), ((JSString) jSObject3.getProperty("jsonAccountId").cast(JSString.class)).getString(), ((JSNumber) jSObject3.getProperty("profilePrefetched").cast(JSNumber.class)).getInt(), ((JSNumber) jSObject3.getProperty("mcastFrame").cast(JSNumber.class)).getInt()));
        }
    }

    public NanoCDNDescr getCurrentNanoCDNDescr() {
        final AtomicReference atomicReference = new AtomicReference(null);
        this.f214a.getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.network.-$$Lambda$DOxMf0SeKOvPxPFz4IYJdoD3nsc
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                NanoCDNHostManager.a(atomicReference, jSObject);
            }
        });
        return (NanoCDNDescr) atomicReference.get();
    }

    public List<NanoCDNDescr> getNanoCDNDescrList() {
        final ArrayList arrayList = new ArrayList();
        this.f214a.getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.network.-$$Lambda$IW8i0FUFnn9RnZfyGB5HrZGTfdE
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                NanoCDNHostManager.a(arrayList, jSObject);
            }
        });
        return arrayList;
    }

    public static /* synthetic */ void a(AtomicReference atomicReference, JSObject jSObject) {
        JSObject jSObject2 = (JSObject) jSObject.getProperty("nanoCDNHostManager").cast(JSObject.class);
        try {
            JSObject jSObject3 = (JSObject) ((JSFunction) jSObject2.getProperty("getCurrentNanoCDNDescr").cast(JSFunction.class)).invoke(jSObject2, new JSValue[0]).cast(JSObject.class);
            atomicReference.set(new NanoCDNDescr(((JSString) jSObject3.getProperty("hostIP").cast(JSString.class)).getString(), ((JSNumber) jSObject3.getProperty("nbSessions").cast(JSNumber.class)).getInt(), ((JSNumber) jSObject3.getProperty("maxNbSessions").cast(JSNumber.class)).getInt(), ((JSNumber) jSObject3.getProperty("ratio").cast(JSNumber.class)).getDouble(), ((JSString) jSObject3.getProperty("jsonAccountId").cast(JSString.class)).getString(), ((JSNumber) jSObject3.getProperty("profilePrefetched").cast(JSNumber.class)).getInt(), ((JSNumber) jSObject3.getProperty("mcastFrame").cast(JSNumber.class)).getInt()));
        } catch (Exception unused) {
            atomicReference.set(null);
        }
    }
}
