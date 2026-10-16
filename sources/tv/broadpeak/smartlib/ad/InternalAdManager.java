package tv.broadpeak.smartlib.ad;

import android.content.Context;
import com.hippo.quickjs.android.JSFunction;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSValue;
import com.hippo.quickjs.android.Method;
import tv.broadpeak.motorjs.JSContext;
import tv.broadpeak.motorjs.QuickJSUtils;
import tv.broadpeak.smartlib.ad.pal.AdPalManager;
import tv.broadpeak.smartlib.ad.pal.AdPalManager16_3_4;
import tv.broadpeak.smartlib.ad.pal.AdPalManager17_1_0;
import tv.broadpeak.smartlib.ad.pal.AdPalSession;
import tv.broadpeak.smartlib.ad.pal.AdPalSessionRequest;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.manager.LoggerManager;

/* JADX INFO: loaded from: classes3.dex */
public class InternalAdManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f180a;
    public CoreEngine b;
    public JSContext c;
    public JSObject d;
    public boolean e = false;
    public boolean f = false;
    public AdPalManager g;
    public AdPalSessionRequest h;
    public b i;

    public class a implements AdPalManager.AdPalManagerCallback {
        public a() {
        }

        @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager.AdPalManagerCallback
        public void onPalSessionGeneration(AdPalSession adPalSession) {
            AdPalSessionRequest adPalSessionRequest = InternalAdManager.this.h;
            if (adPalSessionRequest != null) {
                adPalSessionRequest.setAdPalSession(adPalSession);
            } else {
                LoggerManager.getInstance().printDebugLogs("BpkAdMgr", "Received a nonce success while the request has been reset");
            }
        }

        @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager.AdPalManagerCallback
        public void onPalSessionGenerationFailure(Exception exc) {
            AdPalSessionRequest adPalSessionRequest = InternalAdManager.this.h;
            if (adPalSessionRequest != null) {
                adPalSessionRequest.setAdPalSession(null);
            } else {
                LoggerManager.getInstance().printDebugLogs("BpkAdMgr", "Received a nonce failure while the request has been reset");
            }
        }
    }

    public class b extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f182a = true;

        public b() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException unused) {
            }
            if (this.f182a) {
                InternalAdManager.this.h = null;
            }
        }
    }

    public InternalAdManager(Context context, CoreEngine coreEngine) {
        if (context.getApplicationContext() != null) {
            this.f180a = context.getApplicationContext();
        } else {
            this.f180a = context;
        }
        this.b = coreEngine;
    }

    public AdPalSession generateAdPalSession(boolean z) {
        if (this.g == null) {
            return null;
        }
        b bVar = this.i;
        if (bVar != null) {
            bVar.f182a = false;
            bVar.interrupt();
        }
        if (this.h == null) {
            this.h = new AdPalSessionRequest();
            this.g.generateAdPalSession();
        }
        AdPalSession adPalSession = this.h.getAdPalSession();
        if (z) {
            b bVar2 = new b();
            this.i = bVar2;
            bVar2.start();
        } else {
            this.h = null;
        }
        return adPalSession;
    }

    public AdPalManager getPalManager() {
        return this.g;
    }

    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    public void init() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        if (this.f) {
            return;
        }
        this.b.getCoreExecutor();
        this.c = this.b.getJSContext();
        try {
            if (this.g == null) {
                try {
                    tryLoad16_3_4();
                    str4 = "reset";
                    try {
                        LoggerManager.getInstance().printDebugLogs("BpkAdMgr", "PAL SDK 16.3.4 loaded");
                    } catch (Exception e) {
                        e = e;
                        LoggerManager.getInstance().printDebugLogs("BpkAdMgr", "PAL SDK not loaded (" + e.getMessage() + ", " + e.getCause() + ")");
                    }
                } catch (Exception e2) {
                    e = e2;
                    str4 = "reset";
                }
            } else {
                str4 = "reset";
            }
            this.g.registerPalManagerCallback(new a());
            this.d = (JSObject) this.b.getCoreSingleton().getSingleton("SmartLib").getProperty("internalAdManager").cast(JSObject.class);
            JSObject jSObjectCreate = QuickJSUtils.create(this.c, "smartlib.AdPalManager");
            try {
                str = ")";
                str2 = ", ";
                str5 = "BpkAdMgr";
                try {
                    try {
                        jSObjectCreate.setProperty("setDescriptionURL", this.c.createJSFunction(this.g, Method.create(Void.class, AdPalManager.class.getMethod("setDescriptionURL", String.class))));
                        jSObjectCreate.setProperty("setOmidPartner", this.c.createJSFunction(this.g, Method.create(Void.class, AdPalManager.class.getMethod("setOmidPartner", String.class, String.class))));
                        jSObjectCreate.setProperty("setOmidVersion", this.c.createJSFunction(this.g, Method.create(Void.class, AdPalManager.class.getMethod("setOmidVersion", String.class))));
                        jSObjectCreate.setProperty("setPlayerType", this.c.createJSFunction(this.g, Method.create(Void.class, AdPalManager.class.getMethod("setPlayerType", String.class))));
                        jSObjectCreate.setProperty("setPlayerVersion", this.c.createJSFunction(this.g, Method.create(Void.class, AdPalManager.class.getMethod("setPlayerVersion", String.class))));
                        jSObjectCreate.setProperty("setPpid", this.c.createJSFunction(this.g, Method.create(Void.class, AdPalManager.class.getMethod("setPpid", String.class))));
                        JSContext jSContext = this.c;
                        AdPalManager adPalManager = this.g;
                        Class cls = Integer.TYPE;
                        jSObjectCreate.setProperty("setVideoPlayerHeight", jSContext.createJSFunction(adPalManager, Method.create(Void.class, AdPalManager.class.getMethod("setVideoPlayerHeight", cls))));
                        jSObjectCreate.setProperty("setVideoPlayerWidth", this.c.createJSFunction(this.g, Method.create(Void.class, AdPalManager.class.getMethod("setVideoPlayerWidth", cls))));
                        JSContext jSContext2 = this.c;
                        AdPalManager adPalManager2 = this.g;
                        Class cls2 = Boolean.TYPE;
                        jSObjectCreate.setProperty("setWillAdAutoPlay", jSContext2.createJSFunction(adPalManager2, Method.create(Void.class, AdPalManager.class.getMethod("setWillAdAutoPlay", cls2))));
                        jSObjectCreate.setProperty("setWillAdPlayMuted", this.c.createJSFunction(this.g, Method.create(Void.class, AdPalManager.class.getMethod("setWillAdPlayMuted", cls2))));
                        String str6 = str4;
                        jSObjectCreate.setProperty(str6, this.c.createJSFunction(this.g, Method.create(Void.class, AdPalManager.class.getMethod(str6, new Class[0]))));
                    } catch (NoSuchMethodException e3) {
                        e = e3;
                        e.printStackTrace();
                    }
                    ((JSFunction) this.d.getProperty("setPalManager").cast(JSFunction.class)).invoke(this.d, new JSValue[]{jSObjectCreate});
                    this.f = true;
                    str3 = str5;
                    try {
                        LoggerManager.getInstance().printDebugLogs(str3, "PAL manager loaded");
                    } catch (Exception e4) {
                        e = e4;
                        LoggerManager.getInstance().printDebugLogs(str3, "PAL manager not loaded (" + e.getMessage() + str2 + e.getCause() + str);
                    }
                } catch (Exception e5) {
                    e = e5;
                    str3 = str5;
                    LoggerManager.getInstance().printDebugLogs(str3, "PAL manager not loaded (" + e.getMessage() + str2 + e.getCause() + str);
                }
            } catch (NoSuchMethodException e6) {
                e = e6;
                str = ")";
                str2 = ", ";
                str5 = "BpkAdMgr";
            }
        } catch (Exception e7) {
            e = e7;
            str = ")";
            str2 = ", ";
            str3 = "BpkAdMgr";
            LoggerManager.getInstance().printDebugLogs(str3, "PAL manager not loaded (" + e.getMessage() + str2 + e.getCause() + str);
        }
    }

    public void initOnSameThread() {
        if (this.e) {
            return;
        }
        try {
            tryLoad17_1_0();
            LoggerManager.getInstance().printDebugLogs("BpkAdMgr", "PAL SDK 17.1.0 loaded");
        } catch (Exception e) {
            LoggerManager.getInstance().printDebugLogs("BpkAdMgr", "PAL SDK 17.1.0 not present (" + e.getMessage() + ", " + e.getCause() + ")");
        }
        this.e = true;
    }

    public void release() {
        if (this.f) {
            this.g.reset();
            this.g.release();
            this.g = null;
            this.h = null;
            this.f = false;
            this.e = false;
        }
    }

    public void setConsentSettings(Object obj) {
        AdPalManager adPalManager = this.g;
        if (adPalManager != null) {
            adPalManager.setConsentSettings(obj);
        }
    }

    public void tryLoad16_3_4() throws Exception {
        LoggerManager.getInstance().printDebugLogs("BpkAdMgr", "Trying to load PAL SDK 16.3.4...");
        Class.forName("com.google.ads.interactivemedia.pal.NonceGenerator");
        Class.forName("com.google.ads.interactivemedia.pal.NonceGeneratorException");
        Class.forName("com.google.ads.interactivemedia.pal.NonceGenerator$NonceGeneratorCallback");
        Class.forName("com.google.android.gms.dynamic.RemoteCreator");
        this.g = new AdPalManager16_3_4(this.f180a);
    }

    public void tryLoad17_1_0() throws Exception {
        LoggerManager.getInstance().printDebugLogs("BpkAdMgr", "Trying to load PAL SDK 17.1.0...");
        Class.forName("com.google.ads.interactivemedia.pal.ConsentSettings");
        Class.forName("com.google.ads.interactivemedia.pal.NonceLoader");
        Class.forName("com.google.ads.interactivemedia.pal.NonceRequest");
        Class.forName("com.google.android.gms.tasks.OnSuccessListener");
        Class.forName("com.google.android.gms.tasks.OnFailureListener");
        Class.forName("com.google.ads.interactivemedia.pal.NonceManager");
        this.g = new AdPalManager17_1_0(this.f180a);
    }
}
