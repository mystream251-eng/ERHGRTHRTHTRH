package tv.broadpeak.smartlib.session.streaming;

import com.hippo.quickjs.android.JSFunction;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSString;
import com.hippo.quickjs.android.JSValue;
import com.hippo.quickjs.android.Method;
import java.util.concurrent.atomic.AtomicReference;
import tv.broadpeak.motorjs.QuickJSUtils;
import tv.broadpeak.smartlib.engine.executor.MotorJSValueCallback;
import tv.broadpeak.smartlib.engine.manager.LoggerManager;
import tv.broadpeak.smartlib.engine.player.PlayerAdapterHandler;
import tv.broadpeak.smartlib.session.SessionReport;

/* JADX INFO: loaded from: classes3.dex */
public class StreamingSession extends AbstractSession {

    public class a extends MotorJSValueCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AtomicReference f224a;

        public a(StreamingSession streamingSession, AtomicReference atomicReference) {
            this.f224a = atomicReference;
        }

        @Override // tv.broadpeak.smartlib.engine.executor.MotorJSValueCallback
        public void callback(JSValue jSValue) {
            this.f224a.set(new StreamingSessionResult((JSObject) jSValue.cast(JSObject.class)));
            super.callback(jSValue);
        }
    }

    static {
        AbstractSession.TAG = "BpkStreamingSession";
    }

    public StreamingSession(JSObject jSObject) {
        super(jSObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str, MotorJSValueCallback motorJSValueCallback) {
        JSObject jSObject = (JSObject) ((JSFunction) this.mObject.getProperty("getURL").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{this.mJSContext.createJSString(str)}).cast(JSObject.class);
        try {
            ((JSFunction) jSObject.getProperty("then").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{this.mJSContext.createJSFunction(motorJSValueCallback, Method.create(Void.class, MotorJSValueCallback.class.getMethod("callback", JSValue.class)))});
            QuickJSUtils.executePendingJobs(this.mJSContext);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(AtomicReference atomicReference) {
        JSValue jSValueInvoke = ((JSFunction) this.mObject.getProperty("stopStreamingSession").cast(JSFunction.class)).invoke(this.mObject, new JSValue[0]);
        if (jSValueInvoke instanceof JSObject) {
            atomicReference.set(new SessionReport((JSObject) ((JSObject) jSValueInvoke.cast(JSObject.class)).getProperty("sessionReport").cast(JSObject.class)));
        }
        QuickJSUtils.executePendingJobs(this.mJSContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(String str, String str2) {
        ((JSFunction) this.mObject.getProperty("startStreamingSession").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{this.mJSContext.createJSString(str), this.mJSContext.createJSString(str2)});
        QuickJSUtils.executePendingJobs(this.mJSContext);
    }

    public String getQuery() {
        final AtomicReference atomicReference = new AtomicReference("");
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$StreamingSession$4Ssqw8dw3CBsXN1GXZnF2tulmqk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(atomicReference);
            }
        });
        return (String) atomicReference.get();
    }

    public StreamingSessionResult getURL(final String str) {
        LoggerManager.getInstance().printDebugLogs(AbstractSession.TAG, "Current thread: " + Thread.currentThread());
        AtomicReference atomicReference = new AtomicReference(null);
        final a aVar = new a(this, atomicReference);
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$StreamingSession$M4KrocGEigK-t82ZxIweiFA5gAE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(str, aVar);
            }
        });
        aVar.waitCallback();
        return (StreamingSessionResult) atomicReference.get();
    }

    public void startStreamingSession(final String str, final String str2) {
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$StreamingSession$Ro2As8Pf8RHOuyD60pUzM5WrsEs
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.c(str, str2);
            }
        });
    }

    public SessionReport stopStreamingSession() {
        final AtomicReference atomicReference = new AtomicReference(null);
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$StreamingSession$ro54sY8jfdk_HRxMK33s-lzpInU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.b(atomicReference);
            }
        });
        PlayerAdapterHandler playerAdapterHandler = this.mPlayerAdapter;
        if (playerAdapterHandler != null) {
            playerAdapterHandler.detachPlayerOnSameThread();
            this.mPlayerAdapter = null;
        }
        return (SessionReport) atomicReference.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(AtomicReference atomicReference) {
        atomicReference.set(((JSString) ((JSFunction) this.mObject.getProperty("getQuery").cast(JSFunction.class)).invoke(this.mObject, new JSValue[0]).cast(JSString.class)).getString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, AtomicReference atomicReference) {
        JSValue jSValueInvoke = ((JSFunction) this.mObject.getProperty("stopStreamingSession").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{this.mJSContext.createJSNumber(i)});
        if (jSValueInvoke instanceof JSObject) {
            atomicReference.set(new SessionReport((JSObject) ((JSObject) jSValueInvoke.cast(JSObject.class)).getProperty("sessionReport").cast(JSObject.class)));
        }
        QuickJSUtils.executePendingJobs(this.mJSContext);
    }

    public SessionReport stopStreamingSession(final int i) {
        final AtomicReference atomicReference = new AtomicReference(null);
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$StreamingSession$CJUAGc4oRoSmRLCK-3Z1xwIEVB4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(i, atomicReference);
            }
        });
        PlayerAdapterHandler playerAdapterHandler = this.mPlayerAdapter;
        if (playerAdapterHandler != null) {
            playerAdapterHandler.detachPlayerOnSameThread();
            this.mPlayerAdapter = null;
        }
        return (SessionReport) atomicReference.get();
    }
}
