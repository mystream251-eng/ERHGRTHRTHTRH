package tv.broadpeak.smartlib.engine.system;

import android.content.Context;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.Method;
import tv.broadpeak.motorjs.JSContext;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.manager.InternalMdnsManager;

/* JADX INFO: loaded from: classes3.dex */
public class MdnsHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f208a;
    public CoreEngine b;
    public JSContext c;
    public boolean d = false;
    public InternalMdnsManager e;
    public InternalMdnsManager.DiscoveryListener f;

    public MdnsHandler(Context context, CoreEngine coreEngine) {
        if (context.getApplicationContext() != null) {
            this.f208a = context.getApplicationContext();
        } else {
            this.f208a = context;
        }
        this.b = coreEngine;
        this.c = coreEngine.getJSContext();
    }

    public JSObject createJSObject() {
        JSObject jSObjectCreateJSObject = this.c.createJSObject();
        try {
            jSObjectCreateJSObject.setProperty("start", this.c.createJSFunction(this, Method.create(Void.class, MdnsHandler.class.getMethod("start", String.class))));
            jSObjectCreateJSObject.setProperty("stop", this.c.createJSFunction(this, Method.create(Void.class, MdnsHandler.class.getMethod("stop", new Class[0]))));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return jSObjectCreateJSObject;
    }

    public void release() {
        stop();
    }

    public void start(String str) {
        if (this.d) {
            return;
        }
        this.d = true;
        InternalMdnsManager internalMdnsManager = this.e;
        if (internalMdnsManager != null) {
            internalMdnsManager.stopServiceDiscovery(this.f);
        }
        InternalMdnsManager internalMdnsManager2 = new InternalMdnsManager(this.f208a, str);
        this.e = internalMdnsManager2;
        InternalMdnsManager.DiscoveryListener discoveryListener = this.f;
        if (discoveryListener != null) {
            internalMdnsManager2.stopServiceDiscovery(discoveryListener);
            this.f = null;
        }
        MdnsDiscoveryListener mdnsDiscoveryListener = new MdnsDiscoveryListener(this.b);
        this.f = mdnsDiscoveryListener;
        this.e.discoverServices(mdnsDiscoveryListener);
    }

    public void stop() {
        InternalMdnsManager.DiscoveryListener discoveryListener = this.f;
        if (discoveryListener != null) {
            this.e.stopServiceDiscovery(discoveryListener);
            this.f = null;
        }
        this.d = false;
    }
}
