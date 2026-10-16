package tv.broadpeak.smartlib.plugins.diversity;

import android.content.Context;
import com.hippo.quickjs.android.JSObject;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.CoreSingleton;
import tv.broadpeak.smartlib.engine.manager.LoggerManager;

/* JADX INFO: loaded from: classes3.dex */
public class DiversityPlugin {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f217a;
    public CoreEngine b;
    public CoreSingleton c;
    public Object d;
    public boolean e = false;

    public DiversityPlugin(Context context, CoreEngine coreEngine) {
        if (context.getApplicationContext() != null) {
            this.f217a = context.getApplicationContext();
        } else {
            this.f217a = context;
        }
        this.b = coreEngine;
        this.c = coreEngine.getCoreSingleton();
        coreEngine.getJSContext();
    }

    public void init() {
        if (this.e) {
            return;
        }
        try {
            Object objNewInstance = Class.forName("tv.broadpeak.smartlib.plugins.diversity.DiversityHandler").getConstructor(Context.class, CoreEngine.class).newInstance(this.f217a, this.b);
            this.d = objNewInstance;
            this.c.attachHandler("DiversityManager2", (JSObject) objNewInstance.getClass().getDeclaredMethod("createJSObject", new Class[0]).invoke(this.d, new Object[0]));
            this.e = true;
            LoggerManager.getInstance().printDebugLogs("BpkDiversityPlugin", "Diversity plugin loaded");
        } catch (Exception e) {
            LoggerManager.getInstance().printDebugLogs("BpkDiversityPlugin", "Diversity plugin not loaded (" + e.getCause() + ")");
        }
    }

    public void release() {
        if (this.e) {
            this.e = false;
        }
    }
}
