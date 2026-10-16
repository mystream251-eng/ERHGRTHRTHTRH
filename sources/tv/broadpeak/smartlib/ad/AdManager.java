package tv.broadpeak.smartlib.ad;

import com.hippo.quickjs.android.JSFunction;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSValue;
import com.hippo.quickjs.android.Method;
import tv.broadpeak.motorjs.JSContext;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.executor.CoreJSCallback;

/* JADX INFO: loaded from: classes3.dex */
public class AdManager {
    public static String TAG = "BpkAdMgr";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static AdManager f179a;

    public interface AdEventsListener {
        void onAdBegin(long j, long j2, String str);

        void onAdBreakBegin(long j, long j2);

        void onAdBreakEnd();

        void onAdEnd();

        void onAdSkippable(long j, long j2);
    }

    public static /* synthetic */ void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, int i2, boolean z, boolean z2, JSObject jSObject) {
        JSContext jSContext = CoreEngine.getInstance().getJSContext();
        ((JSFunction) jSObject.getProperty("setPalParameters").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{jSContext.createJSString(str), jSContext.createJSString(str2), jSContext.createJSString(str3), jSContext.createJSString(str4), jSContext.createJSString(str5), jSContext.createJSString(str6), jSContext.createJSString(str7), jSContext.createJSNumber(i), jSContext.createJSNumber(i2), jSContext.createJSBoolean(z), jSContext.createJSBoolean(z2)});
    }

    public static /* synthetic */ void b(String str, String str2, JSObject jSObject) {
        JSContext jSContext = CoreEngine.getInstance().getJSContext();
        ((JSFunction) jSObject.getProperty("setPartner").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{jSContext.createJSString(str), jSContext.createJSString(str2)});
    }

    public static AdManager getInstance() {
        if (f179a == null) {
            f179a = new AdManager();
        }
        return f179a;
    }

    public void activateAdvertising() {
        CoreEngine.getInstance().getSingleton("AdManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.ad.-$$Lambda$mztJgjncLwYSP7PVYZX3wbfeVJE
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                ((JSFunction) jSObject.getProperty("activateAdvertising").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]);
            }
        });
    }

    public void setAdEventsListener(final AdEventsListener adEventsListener) {
        CoreEngine.getInstance().getSingleton("AdManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.ad.-$$Lambda$eZV4NpR1K3C4xRinnmcmsIuaZAs
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                AdManager.a(adEventsListener, jSObject);
            }
        });
    }

    public void setAdParameter(final String str, final String str2) {
        CoreEngine.getInstance().getSingleton("AdManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.ad.-$$Lambda$rP3tADzgXW8unHgPDh0by-Chrsg
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                AdManager.a(str, str2, jSObject);
            }
        });
    }

    public void setConsentSettings(Object obj) {
        CoreEngine.getInstance().getInternalAdManager().setConsentSettings(obj);
    }

    public void setEventCallbackEnabled(final boolean z) {
        CoreEngine.getInstance().getSingleton("AdManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.ad.-$$Lambda$AjVi1uO6ETHayzsxFLgUMBAyAGk
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                ((JSFunction) jSObject.getProperty("setEventCallbackEnabled").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{CoreEngine.getInstance().getJSContext().createJSBoolean(z)});
            }
        });
    }

    public void setPalParameters(final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final int i, final int i2, final boolean z, final boolean z2) {
        CoreEngine.getInstance().getSingleton("AdManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.ad.-$$Lambda$VrXyormltBZIp7rgLym6jDXQwZc
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                AdManager.a(str, str2, str3, str4, str5, str6, str7, i, i2, z, z2, jSObject);
            }
        });
    }

    public void setPartner(final String str, final String str2) {
        CoreEngine.getInstance().getSingleton("AdManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.ad.-$$Lambda$V9NDGH0xLI1l3lsF591lywWylOA
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                AdManager.b(str, str2, jSObject);
            }
        });
    }

    public static /* synthetic */ void a(String str, String str2, JSObject jSObject) {
        JSContext jSContext = CoreEngine.getInstance().getJSContext();
        ((JSFunction) jSObject.getProperty("setAdParameter").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{jSContext.createJSString(str), jSContext.createJSString(str2)});
    }

    public static /* synthetic */ void a(AdEventsListener adEventsListener, JSObject jSObject) {
        JSContext jSContext = CoreEngine.getInstance().getJSContext();
        JSObject jSObjectCreateJSObject = jSContext.createJSObject();
        try {
            Class cls = Long.TYPE;
            jSObjectCreateJSObject.setProperty("onAdBreakBegin", jSContext.createJSFunction(adEventsListener, Method.create(Void.class, AdEventsListener.class.getMethod("onAdBreakBegin", cls, cls))));
            jSObjectCreateJSObject.setProperty("onAdBegin", jSContext.createJSFunction(adEventsListener, Method.create(Void.class, AdEventsListener.class.getMethod("onAdBegin", cls, cls, String.class))));
            jSObjectCreateJSObject.setProperty("onAdSkippable", jSContext.createJSFunction(adEventsListener, Method.create(Void.class, AdEventsListener.class.getMethod("onAdSkippable", cls, cls))));
            jSObjectCreateJSObject.setProperty("onAdEnd", jSContext.createJSFunction(adEventsListener, Method.create(Void.class, AdEventsListener.class.getMethod("onAdEnd", new Class[0]))));
            jSObjectCreateJSObject.setProperty("onAdBreakEnd", jSContext.createJSFunction(adEventsListener, Method.create(Void.class, AdEventsListener.class.getMethod("onAdBreakEnd", new Class[0]))));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        ((JSFunction) jSObject.getProperty("setAdEventsListener").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{jSObjectCreateJSObject});
    }
}
