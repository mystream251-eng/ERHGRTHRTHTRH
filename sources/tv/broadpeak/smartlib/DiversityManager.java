package tv.broadpeak.smartlib;

import com.hippo.quickjs.android.JSBoolean;
import com.hippo.quickjs.android.JSFunction;
import com.hippo.quickjs.android.JSNumber;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSString;
import com.hippo.quickjs.android.JSValue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import tv.broadpeak.motorjs.JSContext;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.executor.CoreJSCallback;

/* JADX INFO: loaded from: classes3.dex */
public class DiversityManager {
    public static final int MULTIPATH_LOG_LEVEL_DEBUG = 7;
    public static final int MULTIPATH_LOG_LEVEL_ERROR = 3;
    public static final int MULTIPATH_LOG_LEVEL_FATAL = 2;
    public static final int MULTIPATH_LOG_LEVEL_INFO = 6;
    public static final int MULTIPATH_LOG_LEVEL_NOTICE = 5;
    public static final int MULTIPATH_LOG_LEVEL_PANIC = 1;
    public static final int MULTIPATH_LOG_LEVEL_WARN = 4;

    public String getJobStats(final String str, final int i) {
        final AtomicReference atomicReference = new AtomicReference("");
        CoreEngine.getInstance().getSingleton("DiversityManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$5K8PX5azTrpi0gFFkMmrjEgIm50
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                DiversityManager.a(str, i, atomicReference, jSObject);
            }
        });
        return (String) atomicReference.get();
    }

    public int getNbConnection() {
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        CoreEngine.getInstance().getSingleton("DiversityManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$KTM1-scdGE9A1Bppcgj3diedz00
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                atomicInteger.set(((JSNumber) ((JSFunction) jSObject.getProperty("getNbConnection").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]).cast(JSNumber.class)).getInt());
            }
        });
        return atomicInteger.get();
    }

    public String getStats(final String str, final int i) {
        final AtomicReference atomicReference = new AtomicReference("");
        CoreEngine.getInstance().getSingleton("DiversityManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$32ouRXJgxhllo2WBc4p9accLOCA
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                DiversityManager.b(str, i, atomicReference, jSObject);
            }
        });
        return (String) atomicReference.get();
    }

    public String getVersion() {
        final AtomicReference atomicReference = new AtomicReference("");
        CoreEngine.getInstance().getSingleton("DiversityManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$5tZsDdAX6dEsrzIJKbce2BDX1sw
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                atomicReference.set(((JSString) ((JSFunction) jSObject.getProperty("getVersion").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]).cast(JSString.class)).getString());
            }
        });
        return (String) atomicReference.get();
    }

    public void init() {
        CoreEngine.getInstance().getSingleton("DiversityManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$g6LDxw8Y4s_vdHHtbOX3DWiXo5I
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                ((JSFunction) jSObject.getProperty("init").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]);
            }
        });
    }

    public boolean isActive() {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        CoreEngine.getInstance().getSingleton("DiversityManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$GGSwZbD9pfsJs9scSH8gQa7FgUw
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                atomicBoolean.set(((JSBoolean) ((JSFunction) jSObject.getProperty("isActive").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]).cast(JSBoolean.class)).getBoolean());
            }
        });
        return atomicBoolean.get();
    }

    public void release() {
        CoreEngine.getInstance().getSingleton("DiversityManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$_7Ncz0IroHAcqJ64kWAr39AomW0
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                ((JSFunction) jSObject.getProperty("release").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]);
            }
        });
    }

    public String rewriteUrl(final String str) {
        final AtomicReference atomicReference = new AtomicReference(str);
        CoreEngine.getInstance().getSingleton("DiversityManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$qBSsbHHQyl1JZ9nONTKEKdc61PY
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                atomicReference.set(((JSString) ((JSFunction) jSObject.getProperty("rewriteUrl").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{CoreEngine.getInstance().getJSContext().createJSString(str)}).cast(JSString.class)).getString());
            }
        });
        return (String) atomicReference.get();
    }

    public void setLogLevel(final int i) {
        CoreEngine.getInstance().getSingleton("DiversityManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$26eVz24bIVKwMuBwemOQpztNay8
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                ((JSFunction) jSObject.getProperty("setLogLevel").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{CoreEngine.getInstance().getJSContext().createJSNumber(i)});
            }
        });
    }

    public void setNbConnection(final int i) {
        CoreEngine.getInstance().getSingleton("DiversityManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$JuvXDlR_MT3eAlBBZoa0Hgu7MQI
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                ((JSFunction) jSObject.getProperty("setNbConnection").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{CoreEngine.getInstance().getJSContext().createJSNumber(i)});
            }
        });
    }

    public void setRcvTimeoutMs(final int i) {
        CoreEngine.getInstance().getSingleton("DiversityManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$WELzs7TUJnyYZpKAP62YuCpBQCk
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                ((JSFunction) jSObject.getProperty("setRcvTimeoutMs").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{CoreEngine.getInstance().getJSContext().createJSNumber(i)});
            }
        });
    }

    public static /* synthetic */ void b(String str, int i, AtomicReference atomicReference, JSObject jSObject) {
        JSContext jSContext = CoreEngine.getInstance().getJSContext();
        atomicReference.set(((JSString) ((JSFunction) jSObject.getProperty("getStats").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{jSContext.createJSString(str), jSContext.createJSNumber(i)}).cast(JSString.class)).getString());
    }

    public static /* synthetic */ void a(String str, int i, AtomicReference atomicReference, JSObject jSObject) {
        JSContext jSContext = CoreEngine.getInstance().getJSContext();
        atomicReference.set(((JSString) ((JSFunction) jSObject.getProperty("getJobStats").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{jSContext.createJSString(str), jSContext.createJSNumber(i)}).cast(JSString.class)).getString());
    }
}
