package tv.broadpeak.smartlib;

import android.content.Context;
import com.hippo.quickjs.android.JSBoolean;
import com.hippo.quickjs.android.JSFunction;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSString;
import com.hippo.quickjs.android.JSValue;
import com.hippo.quickjs.android.Method;
import io.sentry.cache.EnvelopeCache;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import tv.broadpeak.motorjs.JSContext;
import tv.broadpeak.motorjs.QuickJSUtils;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.CoreMemory;
import tv.broadpeak.smartlib.engine.executor.CoreJSCallback;
import tv.broadpeak.smartlib.engine.executor.MotorJSStringCallback;
import tv.broadpeak.smartlib.engine.manager.AppStateManager;
import tv.broadpeak.smartlib.engine.manager.LoggerManager;
import tv.broadpeak.smartlib.engine.player.PlayerAdapterHandler;
import tv.broadpeak.smartlib.network.NanoCDNHostManager;
import tv.broadpeak.smartlib.request.RequestManager;
import tv.broadpeak.smartlib.session.SessionReport;
import tv.broadpeak.smartlib.session.analytics.AnalyticsSession;
import tv.broadpeak.smartlib.session.streaming.StreamingSession;
import tv.broadpeak.smartlib.session.streaming.StreamingSessionOptions;

/* JADX INFO: loaded from: classes3.dex */
public class SmartLib {
    public static final int BPAccessRightError = 3004;
    public static final int BPDecodingError = 3002;
    public static final int BPFormatNotSupportedError = 3001;
    public static final int BPNetworkingError = 3003;
    public static final int BPSessionEndsNormally = 200;
    public static final int BPUnspecifiedError = 3005;
    public static final int FORCE_TEARDOWN_DEFAULT = 0;
    public static final int FORCE_TEARDOWN_DISABLED = -1;
    public static final int FORCE_TEARDOWN_ENABLED = 1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static SmartLib f176a;
    public PlayerAdapterHandler b;
    public AppStateManager c;

    public interface NanoCDNReceiver {
        void onNanoCDNFound(String str);

        void onNanoCDNLost();
    }

    public class a extends MotorJSStringCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AtomicReference f177a;

        public a(SmartLib smartLib, AtomicReference atomicReference) {
            this.f177a = atomicReference;
        }

        @Override // tv.broadpeak.smartlib.engine.executor.MotorJSStringCallback
        public void callback(String str) {
            this.f177a.set(str);
            super.callback(str);
        }
    }

    public static /* synthetic */ void a(String str, String str2, String str3, JSObject jSObject) {
        JSContext jSContext = CoreEngine.getInstance().getJSContext();
        ((JSFunction) jSObject.getProperty("init").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{QuickJSUtils.toJS(jSContext, str), QuickJSUtils.toJS(jSContext, str2), QuickJSUtils.toJS(jSContext, str3)});
        QuickJSUtils.executePendingJobs(jSContext);
    }

    public static /* synthetic */ void b(String str, String str2, JSObject jSObject) {
        JSContext jSContext = CoreEngine.getInstance().getJSContext();
        ((JSFunction) jSObject.getProperty("startStreamingSession").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{jSContext.createJSString(str), jSContext.createJSString(str2)});
        QuickJSUtils.executePendingJobs(jSContext);
    }

    public static /* synthetic */ void d(AtomicReference atomicReference, JSObject jSObject) {
        JSContext jSContext = CoreEngine.getInstance().getJSContext();
        JSValue jSValueInvoke = ((JSFunction) jSObject.getProperty("stopStreamingSession").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]);
        if (jSValueInvoke instanceof JSObject) {
            atomicReference.set(new SessionReport((JSObject) ((JSObject) jSValueInvoke.cast(JSObject.class)).getProperty("sessionReport").cast(JSObject.class)));
        }
        QuickJSUtils.executePendingJobs(jSContext);
    }

    public static SmartLib getInstance() {
        if (f176a == null) {
            f176a = new SmartLib();
        }
        return f176a;
    }

    public void attachPlayer(Object obj) {
        attachPlayer(obj, null);
    }

    public AnalyticsSession createAnalyticsSession() {
        return createAnalyticsSession(null);
    }

    public StreamingSession createStreamingSession() {
        return createStreamingSession(null);
    }

    public void detachPlayer() {
        PlayerAdapterHandler playerAdapterHandler;
        if (!CoreEngine.getInstance().isInitialized()) {
            LoggerManager.getInstance().printErrorLogs("BpkSmartLib", "Please call SmartLib.getInstance().init(...) before any calls.");
            Thread.dumpStack();
            return;
        }
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$QyhLjUWcSTC46YkVkMKD44qhl2s
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                atomicBoolean.set(((JSBoolean) ((JSFunction) jSObject.getProperty("detachPlayer").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]).cast(JSBoolean.class)).getBoolean());
            }
        });
        if (!atomicBoolean.get() || (playerAdapterHandler = this.b) == null) {
            return;
        }
        playerAdapterHandler.detachPlayerFromSmartLibSingleton();
        this.b.detachPlayerOnSameThread();
        this.b = null;
    }

    public Object getListener() {
        if (CoreEngine.getInstance().isInitialized()) {
            return this.b;
        }
        LoggerManager.getInstance().printErrorLogs("BpkSmartLib", "Please call SmartLib.getInstance().init(...) before any calls.");
        Thread.dumpStack();
        return null;
    }

    public NanoCDNHostManager getNanoCDNHostManager() {
        return new NanoCDNHostManager(CoreEngine.getInstance());
    }

    public String getQuery() {
        if (CoreEngine.getInstance().isInitialized()) {
            final AtomicReference atomicReference = new AtomicReference("");
            CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$N4zqlqG-OX_ao159N5sPlEw51H4
                @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
                public final void run(JSObject jSObject) {
                    atomicReference.set(((JSString) ((JSFunction) jSObject.getProperty("getQuery").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]).cast(JSString.class)).getString());
                }
            });
            return (String) atomicReference.get();
        }
        LoggerManager.getInstance().printErrorLogs("BpkSmartLib", "Please call SmartLib.getInstance().init(...) before any calls.");
        Thread.dumpStack();
        return "";
    }

    public RequestManager getRequestManager() {
        return new RequestManager(CoreEngine.getInstance());
    }

    public SessionReport getSessionReport() {
        if (CoreEngine.getInstance().isInitialized()) {
            final AtomicReference atomicReference = new AtomicReference(null);
            CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$6sD28HTH_81uiePeQq9ZLdwDQBo
                @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
                public final void run(JSObject jSObject) {
                    SmartLib.b(atomicReference, jSObject);
                }
            });
            return (SessionReport) atomicReference.get();
        }
        LoggerManager.getInstance().printErrorLogs("BpkSmartLib", "Please call SmartLib.getInstance().init(...) before any calls.");
        Thread.dumpStack();
        return null;
    }

    public String getURL(final String str) {
        if (!CoreEngine.getInstance().isInitialized()) {
            LoggerManager.getInstance().printErrorLogs("BpkSmartLib", "Please call SmartLib.getInstance().init(...) before any calls.");
            Thread.dumpStack();
            return "";
        }
        LoggerManager.getInstance().printDebugLogs("BpkSmartLib", "Current thread: " + Thread.currentThread());
        AtomicReference atomicReference = new AtomicReference("");
        final a aVar = new a(this, atomicReference);
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$Bz5PJncP2YRo1zNVWhkVB1h_fhs
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                SmartLib.a(str, aVar, jSObject);
            }
        });
        aVar.waitCallback();
        return (String) atomicReference.get();
    }

    public String getVersion() {
        if (CoreEngine.getInstance().isInitialized()) {
            final AtomicReference atomicReference = new AtomicReference("");
            CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$sjhvOJv-SY9yTTafI5IKAAWIRaw
                @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
                public final void run(JSObject jSObject) {
                    atomicReference.set(((JSString) ((JSFunction) jSObject.getProperty("getVersion").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]).cast(JSString.class)).getString());
                }
            });
            return (String) atomicReference.get();
        }
        LoggerManager.getInstance().printErrorLogs("BpkSmartLib", "Please call SmartLib.getInstance().init(...) before any calls.");
        Thread.dumpStack();
        return "00.00.00";
    }

    public void init(Context context, final String str, final String str2, final String str3) {
        CoreEngine.getInstance().init(context);
        if (this.c == null) {
            this.c = new AppStateManager(context, CoreEngine.getInstance());
        }
        this.c.init();
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$gLzN9ifD4kMX3RXQibXPUEpUHuA
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                SmartLib.a(str, str2, str3, jSObject);
            }
        });
    }

    public void initAnalytics(Context context, final String str) {
        CoreEngine.getInstance().init(context);
        if (this.c == null) {
            this.c = new AppStateManager(context, CoreEngine.getInstance());
        }
        this.c.init();
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$YDV9iVg-tbmoMA6SQF6HIzQCJs8
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                SmartLib.a(str, jSObject);
            }
        });
    }

    public boolean isAnalyticsInitialized() {
        if (!CoreEngine.getInstance().isInitialized()) {
            return false;
        }
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$7Qo-IG5DDx-tZyHZOaOBa41Za8U
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                atomicBoolean.set(((JSBoolean) ((JSFunction) jSObject.getProperty("isAnalyticsInitialized").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]).cast(JSBoolean.class)).getBoolean());
            }
        });
        return atomicBoolean.get();
    }

    public boolean isInitialized() {
        if (!CoreEngine.getInstance().isInitialized()) {
            return false;
        }
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$YAw1IBpzhWDijwY4_MUbyRFCloc
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                atomicBoolean.set(((JSBoolean) ((JSFunction) jSObject.getProperty("isInitialized").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]).cast(JSBoolean.class)).getBoolean());
            }
        });
        return atomicBoolean.get();
    }

    public void registerNanoCDNReceiver(final NanoCDNReceiver nanoCDNReceiver) {
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$QfcHX1MnHyRoWFRIVbr2DhAS7vI
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                SmartLib.a(nanoCDNReceiver, jSObject);
            }
        });
    }

    public void release() {
        if (!CoreEngine.getInstance().isInitialized()) {
            LoggerManager.getInstance().printWarnLogs("BpkSmartLib", "SmartLib was not initialized when released");
            return;
        }
        PlayerAdapterHandler playerAdapterHandler = this.b;
        if (playerAdapterHandler != null) {
            playerAdapterHandler.detachPlayerOnSameThread();
        }
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$SmartLib$s0d7U599wL85147on-5Wv8NEoIY
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                this.f$0.a(jSObject);
            }
        });
    }

    public void resetCustomParameters() {
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$4z8BVyjX1xC9n7ktE3EojG0pZGA
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                ((JSFunction) jSObject.getProperty("resetCustomParameters").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]);
            }
        });
    }

    public void setCustomParameter(final String str, final String str2) {
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$KDuz4vbQeUqPO3mkhsWrj-f0UTs
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                SmartLib.a(str, str2, jSObject);
            }
        });
    }

    public void setDeviceType(final String str) {
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$eb0uGgafdHwqbrYVvzDfO8NPs58
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                ((JSFunction) jSObject.getProperty("setDeviceType").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{CoreEngine.getInstance().getJSContext().createJSString(str)});
            }
        });
    }

    public void setForceTeardown(final int i) {
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$UMe9UAyZLcAhlJ2Ap6n79XXSTw8
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                ((JSFunction) jSObject.getProperty("setForceTeardown").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{CoreEngine.getInstance().getJSContext().createJSNumber(i)});
            }
        });
    }

    public void setNanoCDNResolvingRetryDelay(final long j) {
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$y89W62U5-OulMs_Fnd2fNXDiaGY
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                ((JSFunction) jSObject.getProperty("setNanoCDNResolvingRetryDelay").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{CoreEngine.getInstance().getJSContext().createJSNumber(j)});
            }
        });
    }

    public void setOption(final int i, final boolean z) {
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$a_TNS3MP6sn1RVd5d60i_W3Vyqs
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                SmartLib.a(i, z, jSObject);
            }
        });
    }

    public void setUUID(final String str) {
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$hrcjhNI96MKHIjO3KWFp7w09OJM
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                ((JSFunction) jSObject.getProperty("setUUID").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{CoreEngine.getInstance().getJSContext().createJSString(str)});
            }
        });
    }

    public void setUserAgent(final String str) {
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$9SQYaHwJNOd_TzTWe_JcalZjBEo
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                ((JSFunction) jSObject.getProperty("setUserAgent").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{CoreEngine.getInstance().getJSContext().createJSString(str)});
            }
        });
    }

    public void startStreamingSession(final String str, final String str2) {
        if (CoreEngine.getInstance().isInitialized()) {
            CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$aHJWsaBCy9NqvF6xYdWyuKUYkO8
                @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
                public final void run(JSObject jSObject) {
                    SmartLib.b(str, str2, jSObject);
                }
            });
        } else {
            LoggerManager.getInstance().printErrorLogs("BpkSmartLib", "Please call SmartLib.getInstance().init(...) before any calls.");
            Thread.dumpStack();
        }
    }

    public SessionReport stopStreamingSession() {
        if (CoreEngine.getInstance().isInitialized()) {
            final AtomicReference atomicReference = new AtomicReference(null);
            CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$jen2SIoNWccJ_jlh9MWSMLqgGHQ
                @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
                public final void run(JSObject jSObject) {
                    SmartLib.d(atomicReference, jSObject);
                }
            });
            return (SessionReport) atomicReference.get();
        }
        LoggerManager.getInstance().printErrorLogs("BpkSmartLib", "Please call SmartLib.getInstance().init(...) before any calls.");
        Thread.dumpStack();
        return null;
    }

    public void attachPlayer(Object obj, Object obj2) {
        if (!CoreEngine.getInstance().isInitialized()) {
            LoggerManager.getInstance().printErrorLogs("BpkSmartLib", "Please call SmartLib.getInstance().init(...) before any calls.");
            Thread.dumpStack();
            return;
        }
        final PlayerAdapterHandler playerAdapterHandlerAttachPlayer = CoreEngine.getInstance().getPlayerManager().attachPlayer(obj, obj2);
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$n3KGeRtjRIpZUqXfKrj5mIwcQWM
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                SmartLib.a(playerAdapterHandlerAttachPlayer, jSObject);
            }
        });
        PlayerAdapterHandler playerAdapterHandler = this.b;
        if (playerAdapterHandler != null) {
            playerAdapterHandler.detachPlayerFromSmartLibSingleton();
            this.b.detachPlayerOnSameThread();
        }
        if (playerAdapterHandlerAttachPlayer != null) {
            playerAdapterHandlerAttachPlayer.attachPlayerFromSmartLibSingleton();
        }
        this.b = playerAdapterHandlerAttachPlayer;
    }

    public AnalyticsSession createAnalyticsSession(final StreamingSessionOptions streamingSessionOptions) {
        if (CoreEngine.getInstance().isInitialized()) {
            final AtomicReference atomicReference = new AtomicReference(null);
            CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$l98CfEC8rBhRSZDSvG7ZHcxuewo
                @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
                public final void run(JSObject jSObject) {
                    StreamingSessionOptions streamingSessionOptions2 = streamingSessionOptions;
                    atomicReference.set(new AnalyticsSession(streamingSessionOptions2 == null ? (JSObject) ((JSFunction) jSObject.getProperty("createAnalyticsSession").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]).cast(JSObject.class) : (JSObject) ((JSFunction) jSObject.getProperty("createAnalyticsSession").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{streamingSessionOptions2.getJSObject()}).cast(JSObject.class)));
                }
            });
            return (AnalyticsSession) atomicReference.get();
        }
        LoggerManager.getInstance().printErrorLogs("BpkSmartLib", "Please call SmartLib.getInstance().initAnalytics(...) before any calls.");
        Thread.dumpStack();
        return null;
    }

    public StreamingSession createStreamingSession(final StreamingSessionOptions streamingSessionOptions) {
        if (CoreEngine.getInstance().isInitialized()) {
            final AtomicReference atomicReference = new AtomicReference(null);
            CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$w-6TJwTVr1qX2awPNzr3i6BOygc
                @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
                public final void run(JSObject jSObject) {
                    StreamingSessionOptions streamingSessionOptions2 = streamingSessionOptions;
                    atomicReference.set(new StreamingSession(streamingSessionOptions2 == null ? (JSObject) ((JSFunction) jSObject.getProperty("createStreamingSession").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]).cast(JSObject.class) : (JSObject) ((JSFunction) jSObject.getProperty("createStreamingSession").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{streamingSessionOptions2.getJSObject()}).cast(JSObject.class)));
                }
            });
            return (StreamingSession) atomicReference.get();
        }
        LoggerManager.getInstance().printErrorLogs("BpkSmartLib", "Please call SmartLib.getInstance().init(...) before any calls.");
        Thread.dumpStack();
        return null;
    }

    public void setOption(final int i, final int i2) {
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$rj9uxHp-np4uxq2VHM-p4n7mtpk
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                SmartLib.a(i, i2, jSObject);
            }
        });
    }

    public void setOption(final int i, final String str) {
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$wm26nV7Y0Ai2vy0UHyip1TOoJ2U
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                SmartLib.a(i, str, jSObject);
            }
        });
    }

    public static /* synthetic */ void a(String str, JSObject jSObject) {
        JSContext jSContext = CoreEngine.getInstance().getJSContext();
        ((JSFunction) jSObject.getProperty("initAnalytics").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{QuickJSUtils.toJS(jSContext, str)});
        QuickJSUtils.executePendingJobs(jSContext);
    }

    public static /* synthetic */ void a(String str, MotorJSStringCallback motorJSStringCallback, JSObject jSObject) {
        JSContext jSContext = CoreEngine.getInstance().getJSContext();
        try {
            ((JSFunction) jSObject.getProperty("getURL").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{jSContext.createJSString(str), jSContext.createJSFunction(motorJSStringCallback, Method.create(Void.class, MotorJSStringCallback.class.getMethod("callback", String.class)))});
            QuickJSUtils.executePendingJobs(jSContext);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void b(AtomicReference atomicReference, JSObject jSObject) {
        JSValue property = jSObject.getProperty(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE);
        if (property instanceof JSObject) {
            atomicReference.set(new SessionReport((JSObject) ((JSObject) property.cast(JSObject.class)).getProperty("sessionReport").cast(JSObject.class)));
        }
    }

    public SessionReport stopStreamingSession(final int i) {
        if (!CoreEngine.getInstance().isInitialized()) {
            LoggerManager.getInstance().printErrorLogs("BpkSmartLib", "Please call SmartLib.getInstance().init(...) before any calls.");
            Thread.dumpStack();
            return null;
        }
        final AtomicReference atomicReference = new AtomicReference(null);
        CoreEngine.getInstance().getSingleton("SmartLib", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.-$$Lambda$UQzICfx-bAFUx61_o4WBui8VgDY
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                SmartLib.a(i, atomicReference, jSObject);
            }
        });
        return (SessionReport) atomicReference.get();
    }

    public static /* synthetic */ void a(int i, AtomicReference atomicReference, JSObject jSObject) {
        JSContext jSContext = CoreEngine.getInstance().getJSContext();
        JSValue jSValueInvoke = ((JSFunction) jSObject.getProperty("stopStreamingSession").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{jSContext.createJSNumber(i)});
        if (jSValueInvoke instanceof JSObject) {
            atomicReference.set(new SessionReport((JSObject) ((JSObject) jSValueInvoke.cast(JSObject.class)).getProperty("sessionReport").cast(JSObject.class)));
        }
        QuickJSUtils.executePendingJobs(jSContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(JSObject jSObject) {
        ((JSFunction) jSObject.getProperty("release").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]);
        PlayerAdapterHandler playerAdapterHandler = this.b;
        if (playerAdapterHandler != null) {
            playerAdapterHandler.detachPlayerFromSmartLibSingleton();
            this.b = null;
        }
        AppStateManager appStateManager = this.c;
        if (appStateManager != null) {
            appStateManager.release();
        }
        CoreMemory.getInstance().release();
    }

    public static /* synthetic */ void a(PlayerAdapterHandler playerAdapterHandler, JSObject jSObject) {
        JSContext jSContext = CoreEngine.getInstance().getJSContext();
        ((JSFunction) jSObject.getProperty("attachPlayer").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{playerAdapterHandler != null ? playerAdapterHandler.getJSPlayerAdapter() : jSContext.createJSUndefined(), jSContext.createJSUndefined()});
    }

    public static /* synthetic */ void a(String str, String str2, JSObject jSObject) {
        JSContext jSContext = CoreEngine.getInstance().getJSContext();
        ((JSFunction) jSObject.getProperty("setCustomParameter").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{jSContext.createJSString(str), jSContext.createJSString(str2)});
    }

    public static /* synthetic */ void a(NanoCDNReceiver nanoCDNReceiver, JSObject jSObject) {
        JSValue jSValueCreateJSUndefined;
        JSContext jSContext = CoreEngine.getInstance().getJSContext();
        if (nanoCDNReceiver != null) {
            jSValueCreateJSUndefined = jSContext.createJSObject();
            try {
                ((JSObject) jSValueCreateJSUndefined.cast(JSObject.class)).setProperty("onNanoCDNFound", jSContext.createJSFunction(nanoCDNReceiver, Method.create(Void.class, NanoCDNReceiver.class.getMethod("onNanoCDNFound", String.class))));
                ((JSObject) jSValueCreateJSUndefined.cast(JSObject.class)).setProperty("onNanoCDNLost", jSContext.createJSFunction(nanoCDNReceiver, Method.create(Void.class, NanoCDNReceiver.class.getMethod("onNanoCDNLost", new Class[0]))));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else {
            jSValueCreateJSUndefined = jSContext.createJSUndefined();
        }
        ((JSFunction) jSObject.getProperty("registerNanoCDNReceiver").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{jSValueCreateJSUndefined});
    }

    public static /* synthetic */ void a(int i, boolean z, JSObject jSObject) {
        JSContext jSContext = CoreEngine.getInstance().getJSContext();
        ((JSFunction) jSObject.getProperty("setOption").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{jSContext.createJSNumber(i), jSContext.createJSBoolean(z)});
    }

    public static /* synthetic */ void a(int i, int i2, JSObject jSObject) {
        JSContext jSContext = CoreEngine.getInstance().getJSContext();
        ((JSFunction) jSObject.getProperty("setOption").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{jSContext.createJSNumber(i), jSContext.createJSNumber(i2)});
    }

    public static /* synthetic */ void a(int i, String str, JSObject jSObject) {
        JSContext jSContext = CoreEngine.getInstance().getJSContext();
        ((JSFunction) jSObject.getProperty("setOption").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{jSContext.createJSNumber(i), jSContext.createJSString(str)});
    }
}
