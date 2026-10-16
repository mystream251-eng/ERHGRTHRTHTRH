package tv.broadpeak.smartlib.engine;

import android.content.Context;
import com.hippo.quickjs.android.JSObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import tv.broadpeak.motorjs.JSContext;
import tv.broadpeak.motorjs.JSRuntime;
import tv.broadpeak.motorjs.QuickJS;
import tv.broadpeak.smartlib.BuildConfig;
import tv.broadpeak.smartlib.ad.InternalAdManager;
import tv.broadpeak.smartlib.engine.executor.CoreExecutor;
import tv.broadpeak.smartlib.engine.executor.CoreJSCallback;
import tv.broadpeak.smartlib.engine.executor.CoreWorker;
import tv.broadpeak.smartlib.engine.manager.LoggerManager;
import tv.broadpeak.smartlib.engine.player.PlayerManagerHandler;
import tv.broadpeak.smartlib.engine.system.LoggerHandler;
import tv.broadpeak.smartlib.engine.system.MdnsHandler;
import tv.broadpeak.smartlib.engine.system.RequestHandler;
import tv.broadpeak.smartlib.plugins.omsdk.OMSDKPlugin;

/* JADX INFO: loaded from: classes3.dex */
public class CoreEngine {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static CoreEngine f187a;
    public Context c;
    public QuickJS e;
    public JSRuntime f;
    public JSContext g;
    public JSObject h;
    public CoreWorker i;
    public CoreSingleton j;
    public RequestHandler l;
    public MdnsHandler m;
    public InternalAdManager n;
    public PlayerManagerHandler o;
    public OMSDKPlugin p;
    public boolean b = false;
    public LoggerHandler k = new LoggerHandler();
    public CoreExecutor d = new CoreExecutor();

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, long j) {
        InputStream inputStreamOpen;
        QuickJS quickJSBuild = new QuickJS.Builder().build();
        this.e = quickJSBuild;
        JSRuntime jSRuntimeCreateJSRuntime = quickJSBuild.createJSRuntime();
        this.f = jSRuntimeCreateJSRuntime;
        jSRuntimeCreateJSRuntime.setMaxStackSize(10000000);
        JSContext jSContextCreateJSContext = this.f.createJSContext();
        this.g = jSContextCreateJSContext;
        String str = null;
        try {
            try {
                inputStreamOpen = context.getAssets().open(BuildConfig.SMARTLIB_CORE_PATH);
            } catch (IOException e) {
                e.printStackTrace();
                inputStreamOpen = null;
            }
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            inputStreamOpen.close();
            str = new String(bArr, StandardCharsets.UTF_8);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        jSContextCreateJSContext.evaluate(str, "smartlib.js");
        JSObject jSObject = (JSObject) this.g.getGlobalObject().getProperty("smartlib").cast(JSObject.class);
        this.h = jSObject;
        this.j = new CoreSingleton(jSObject);
        LoggerManager.getInstance().printDebugLogs("BpkCoreEngine", "Registering system functions...");
        this.i = new CoreWorker(this);
        this.j.initSingleton("LoggerManager", this.k.createJSObject(this.g));
        RequestHandler requestHandler = new RequestHandler(this.c, this.g);
        this.l = requestHandler;
        this.j.initSingleton("RequestManager", requestHandler.createJSObject());
        MdnsHandler mdnsHandler = new MdnsHandler(this.c, this);
        this.m = mdnsHandler;
        this.j.initSingleton("MdnsManager", mdnsHandler.createJSObject());
        this.n.init();
        OMSDKPlugin oMSDKPlugin = new OMSDKPlugin(this.c, this);
        this.p = oMSDKPlugin;
        oMSDKPlugin.init();
        PlayerManagerHandler playerManagerHandler = new PlayerManagerHandler(this.g);
        this.o = playerManagerHandler;
        this.j.initSingleton("PlayerManager", playerManagerHandler.createJSObject());
        LoggerManager.getInstance().printDebugLogs("BpkCoreEngine", "Took " + (System.currentTimeMillis() - j) + "ms to load");
    }

    public static CoreEngine getInstance() {
        if (f187a == null) {
            f187a = new CoreEngine();
        }
        return f187a;
    }

    public Context getContext() {
        return this.c;
    }

    public CoreExecutor getCoreExecutor() {
        return this.d;
    }

    public CoreSingleton getCoreSingleton() {
        return this.j;
    }

    public InternalAdManager getInternalAdManager() {
        return this.n;
    }

    public JSContext getJSContext() {
        return this.g;
    }

    public void getManager(final String str, final CoreJSCallback coreJSCallback) {
        this.d.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.-$$Lambda$CoreEngine$3Pi06ijMxHaRQhd1YBoPX-t7G5E
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(str, coreJSCallback);
            }
        });
    }

    public PlayerManagerHandler getPlayerManager() {
        return this.o;
    }

    public RequestHandler getRequestHandler() {
        return this.l;
    }

    public void getSingleton(final String str, final CoreJSCallback coreJSCallback) {
        this.d.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.-$$Lambda$CoreEngine$2Dcpq-yGEq8h-9WG3kyLCem6_Zo
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(coreJSCallback, str);
            }
        });
    }

    public CoreWorker getWorker() {
        return this.i;
    }

    public synchronized void init(final Context context) {
        if (!this.b) {
            final long jCurrentTimeMillis = System.currentTimeMillis();
            LoggerManager.getInstance().printDebugLogs("BpkCoreEngine", "Loading engine...");
            LoggerManager.getInstance().printDebugLogs("BpkCoreEngine", "Version: 50f043");
            this.c = context;
            InternalAdManager internalAdManager = new InternalAdManager(context, this);
            this.n = internalAdManager;
            internalAdManager.initOnSameThread();
            this.d.executeFirst(new Runnable() { // from class: tv.broadpeak.smartlib.engine.-$$Lambda$CoreEngine$rZZ-a6y_U5d737V2XfvSdZlUmpE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.a(context, jCurrentTimeMillis);
                }
            });
            this.d.start();
            this.p.activate();
            this.b = true;
        }
    }

    public boolean isInitialized() {
        return this.b;
    }

    public void release() {
        if (this.b) {
            this.b = false;
            this.d.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.-$$Lambda$CoreEngine$VkpXyDIHHiRvzWUXN5RbWgwerZ4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.a();
                }
            });
            this.d.stop();
            this.d = new CoreExecutor();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.m.release();
        this.n.release();
        this.p.release();
        this.g.close();
        this.f.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(CoreJSCallback coreJSCallback, String str) {
        coreJSCallback.run(this.j.getSingleton(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str, CoreJSCallback coreJSCallback) {
        coreJSCallback.run((JSObject) this.j.getSingleton("SmartLib").getProperty(str).cast(JSObject.class));
    }
}
