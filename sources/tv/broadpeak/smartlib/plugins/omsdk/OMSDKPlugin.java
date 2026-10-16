package tv.broadpeak.smartlib.plugins.omsdk;

import android.content.Context;
import com.hippo.quickjs.android.JSObject;
import tv.broadpeak.motorjs.JSContext;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.CoreSingleton;
import tv.broadpeak.smartlib.engine.manager.LoggerManager;

/* JADX INFO: loaded from: classes3.dex */
public class OMSDKPlugin {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f218a;
    public CoreSingleton b;
    public JSContext c;
    public Object d;
    public boolean e = false;

    public OMSDKPlugin(Context context, CoreEngine coreEngine) {
        if (context.getApplicationContext() != null) {
            this.f218a = context.getApplicationContext();
        } else {
            this.f218a = context;
        }
        this.b = coreEngine.getCoreSingleton();
        this.c = coreEngine.getJSContext();
    }

    public void activate() {
        if (this.e) {
            try {
                this.d.getClass().getDeclaredMethod("activate", Context.class).invoke(this.d, this.f218a);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void init() {
        if (this.e) {
            return;
        }
        try {
            Object objNewInstance = Class.forName("tv.broadpeak.smartlib.plugins.omsdk.OMSDKHandler").getConstructor(Context.class, JSContext.class).newInstance(this.f218a, this.c);
            this.d = objNewInstance;
            this.b.attachHandler("OMSDKManager", (JSObject) objNewInstance.getClass().getDeclaredMethod("createJSObject", new Class[0]).invoke(this.d, new Object[0]));
            this.e = true;
            LoggerManager.getInstance().printDebugLogs("BpkOMSDKMgr", "OM SDK loaded");
        } catch (Exception e) {
            LoggerManager.getInstance().printDebugLogs("BpkOMSDKMgr", "OM SDK not loaded (" + e.getCause() + ")");
        }
    }

    public void release() {
        if (this.e) {
            this.e = false;
        }
    }
}
