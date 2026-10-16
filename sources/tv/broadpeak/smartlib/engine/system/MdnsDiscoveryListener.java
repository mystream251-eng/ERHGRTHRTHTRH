package tv.broadpeak.smartlib.engine.system;

import com.hippo.quickjs.android.JSFunction;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSValue;
import javax.jmdns.ServiceInfo;
import tv.broadpeak.motorjs.JSContext;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.executor.CoreJSCallback;
import tv.broadpeak.smartlib.engine.manager.InternalMdnsManager;
import tv.broadpeak.smartlib.engine.manager.LoggerManager;

/* JADX INFO: loaded from: classes3.dex */
public class MdnsDiscoveryListener implements InternalMdnsManager.DiscoveryListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CoreEngine f207a;
    public JSContext b;

    public MdnsDiscoveryListener(CoreEngine coreEngine) {
        this.f207a = coreEngine;
        this.b = coreEngine.getJSContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(ServiceInfo serviceInfo, String str, JSObject jSObject) {
        ((JSFunction) jSObject.getProperty("onServiceFound").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{this.b.createJSString(serviceInfo.getName()), this.b.createJSString(str)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(ServiceInfo serviceInfo, String str, JSObject jSObject) {
        ((JSFunction) jSObject.getProperty("onServiceLost").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{this.b.createJSString(serviceInfo.getName()), this.b.createJSString(str)});
    }

    @Override // tv.broadpeak.smartlib.engine.manager.InternalMdnsManager.DiscoveryListener
    public void onDiscoveryStarted(String str) {
        LoggerManager.getInstance().printDebugLogs("BpkMdnsMgr", "Service discovery started");
    }

    @Override // tv.broadpeak.smartlib.engine.manager.InternalMdnsManager.DiscoveryListener
    public void onDiscoveryStopped(String str) {
        LoggerManager.getInstance().printDebugLogs("BpkMdnsMgr", "Discovery stopped: " + str);
    }

    @Override // tv.broadpeak.smartlib.engine.manager.InternalMdnsManager.DiscoveryListener
    public void onServiceFound(final ServiceInfo serviceInfo) {
        LoggerManager.getInstance().printDebugLogs("BpkMdnsMgr", "Service discovery success: " + serviceInfo.toString());
        final String hostAddress = serviceInfo.getInet4Addresses()[0].getHostAddress();
        this.f207a.getSingleton("MdnsManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.engine.system.-$$Lambda$MdnsDiscoveryListener$mSMH5WK_IPLIoXNFD9lJmt8ie2s
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                this.f$0.a(serviceInfo, hostAddress, jSObject);
            }
        });
    }

    @Override // tv.broadpeak.smartlib.engine.manager.InternalMdnsManager.DiscoveryListener
    public void onServiceLost(final ServiceInfo serviceInfo) {
        final String hostAddress = serviceInfo.getInet4Addresses()[0].getHostAddress();
        this.f207a.getSingleton("MdnsManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.engine.system.-$$Lambda$MdnsDiscoveryListener$N28_M4H4TMoA05SI25EsDRsSqb4
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                this.f$0.b(serviceInfo, hostAddress, jSObject);
            }
        });
    }

    @Override // tv.broadpeak.smartlib.engine.manager.InternalMdnsManager.DiscoveryListener
    public void onStartDiscoveryFailed(String str, int i) {
        LoggerManager.getInstance().printWarnLogs("BpkMdnsMgr", "Discovery failed: Error code:" + i);
    }

    @Override // tv.broadpeak.smartlib.engine.manager.InternalMdnsManager.DiscoveryListener
    public void onStopDiscoveryFailed(String str, int i) {
        LoggerManager.getInstance().printWarnLogs("BpkMdnsMgr", "Discovery failed: Error code:" + i);
    }
}
