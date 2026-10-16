package tv.broadpeak.smartlib.engine.player;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.hippo.quickjs.android.JSBoolean;
import com.hippo.quickjs.android.JSFunction;
import com.hippo.quickjs.android.JSNumber;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSUndefined;
import com.hippo.quickjs.android.JSValue;
import com.hippo.quickjs.android.Method;
import io.sentry.cache.EnvelopeCache;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import tv.broadpeak.motorjs.JSContext;
import tv.broadpeak.motorjs.QuickJSUtils;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.executor.CoreExecutor;
import tv.broadpeak.smartlib.engine.manager.LoggerManager;

/* JADX INFO: loaded from: classes3.dex */
public abstract class PlayerAdapterHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CoreExecutor f205a = CoreEngine.getInstance().getCoreExecutor();
    public JSContext b = CoreEngine.getInstance().getJSContext();
    public JSObject mJSPlayerAdapter;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i) {
        ((JSFunction) this.mJSPlayerAdapter.getProperty("notifyLayerSwitch").cast(JSFunction.class)).invoke(this.mJSPlayerAdapter, new JSValue[]{this.b.createJSNumber(i)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b() {
        ((JSFunction) this.mJSPlayerAdapter.getProperty("notifyFirstImage").cast(JSFunction.class)).invoke(this.mJSPlayerAdapter, new JSValue[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c() {
        ((JSFunction) this.mJSPlayerAdapter.getProperty("notifyLoading").cast(JSFunction.class)).invoke(this.mJSPlayerAdapter, new JSValue[0]);
        QuickJSUtils.executePendingJobs(this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d() {
        ((JSFunction) this.mJSPlayerAdapter.getProperty("notifyPause").cast(JSFunction.class)).invoke(this.mJSPlayerAdapter, new JSValue[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e() {
        ((JSFunction) this.mJSPlayerAdapter.getProperty("notifyResume").cast(JSFunction.class)).invoke(this.mJSPlayerAdapter, new JSValue[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f() {
        ((JSFunction) this.mJSPlayerAdapter.getProperty("notifyStallStart").cast(JSFunction.class)).invoke(this.mJSPlayerAdapter, new JSValue[0]);
    }

    public boolean attachPlayer(JSObject jSObject, Object obj, Object obj2) {
        this.mJSPlayerAdapter = jSObject;
        try {
            jSObject.setProperty("getName", this.b.createJSFunction(this, Method.create(String.class, PlayerAdapterHandler.class.getMethod("getName", new Class[0]))));
            jSObject.setProperty("getVersion", this.b.createJSFunction(this, Method.create(String.class, PlayerAdapterHandler.class.getMethod("getVersion", new Class[0]))));
            jSObject.setProperty("getOSName", this.b.createJSFunction(this, Method.create(String.class, PlayerAdapterHandler.class.getMethod("getOSName", new Class[0]))));
            jSObject.setProperty("getOSVersion", this.b.createJSFunction(this, Method.create(String.class, PlayerAdapterHandler.class.getMethod("getOSVersion", new Class[0]))));
            jSObject.setProperty("getDeviceType", this.b.createJSFunction(this, Method.create(String.class, PlayerAdapterHandler.class.getMethod("getDeviceType", new Class[0]))));
            jSObject.setProperty("getBitrate", this.b.createJSFunction(this, Method.create(Integer.TYPE, PlayerAdapterHandler.class.getMethod("getBitrate", new Class[0]))));
            jSObject.setProperty("getPosition", this.b.createJSFunction(this, Method.create(Double.TYPE, PlayerAdapterHandler.class.getMethod("getPosition", new Class[0]))));
            jSObject.setProperty("getDuration", this.b.createJSFunction(this, Method.create(Double.TYPE, PlayerAdapterHandler.class.getMethod("getDuration", new Class[0]))));
            jSObject.setProperty("getCapabilities", this.b.createJSFunction(this, Method.create(JSValue.class, PlayerAdapterHandler.class.getMethod("getCapabilities", new Class[0]))));
            jSObject.setProperty("detachPlayer", this.b.createJSFunction(this, Method.create(Void.class, PlayerAdapterHandler.class.getMethod("detachPlayer", new Class[0]))));
            jSObject.setProperty("initSessionPlayerObjects", this.b.createJSFunction(this, Method.create(Void.class, PlayerAdapterHandler.class.getMethod("initSessionPlayerObjects", new Class[0]))));
            jSObject.setProperty("releaseSessionPlayerObjects", this.b.createJSFunction(this, Method.create(Void.class, PlayerAdapterHandler.class.getMethod("releaseSessionPlayerObjects", new Class[0]))));
            return false;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void attachPlayerFromSmartLibSingleton() {
    }

    public void attachPlayerOnSameThread(Object obj, Object obj2) {
    }

    public abstract boolean checkPlayer(Object obj, Object obj2);

    public void detachPlayer() {
    }

    public void detachPlayerFromSmartLibSingleton() {
    }

    public void detachPlayerOnSameThread() {
    }

    public abstract int getBitrate();

    public abstract JSValue getCapabilities();

    public long getDeviceInitDate() {
        final AtomicLong atomicLong = new AtomicLong(0L);
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$E2-XKplx4evkfvPMaxZFCLvbsMg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(atomicLong);
            }
        });
        return atomicLong.get();
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0070  */
    public String getDeviceType() {
        boolean z;
        WindowManager windowManager;
        Context context = CoreEngine.getInstance().getContext();
        if ((context == null || context.getPackageManager() == null || (!context.getPackageManager().hasSystemFeature("com.google.android.tv") && !context.getPackageManager().hasSystemFeature("android.software.leanback"))) ? false : true) {
            return "tv";
        }
        Context context2 = CoreEngine.getInstance().getContext();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (context2 != null && (windowManager = (WindowManager) context2.getSystemService("window")) != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.heightPixels / displayMetrics.ydpi;
            float f2 = displayMetrics.widthPixels / displayMetrics.xdpi;
            z = Math.sqrt((double) ((f2 * f2) + (f * f))) >= 6.5d;
        }
        return z ? "tablet" : "smartphone";
    }

    public abstract double getDuration();

    public JSObject getJSPlayerAdapter() {
        return this.mJSPlayerAdapter;
    }

    public long getManifestInitDate() {
        final AtomicLong atomicLong = new AtomicLong(0L);
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$om1JOe0DrMOzA3xbk4jnoWLM2ww
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.b(atomicLong);
            }
        });
        return atomicLong.get();
    }

    public abstract String getName();

    public String getOSName() {
        return "Android";
    }

    public String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    public abstract double getPosition();

    public abstract String getVersion();

    public double getVolume() {
        return 1.0d;
    }

    public abstract void initSessionPlayerObjects();

    public boolean isBuffering() {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$l5Jg6skFjZALa4zsAXw7Qbf6kYo
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(atomicBoolean);
            }
        });
        return atomicBoolean.get();
    }

    public boolean isPlaying() {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$qq_UcOx1hQ3vQYE29FZrnpt_8DY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.b(atomicBoolean);
            }
        });
        return atomicBoolean.get();
    }

    public boolean isStarted() {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$qMzTNZHMwtQanDsvlOvbYfo5ek8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.c(atomicBoolean);
            }
        });
        return atomicBoolean.get();
    }

    public void notifyClose() {
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$sXPAoqU9-rkxfMERq6yX6ts6XAU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a();
            }
        });
    }

    public void notifyFirstImage() {
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$XrM76AlstmK90a_HRySO5iSs8Jo
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.b();
            }
        });
    }

    public void notifyLayerSwitch(final int i) {
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$WDWTdHRZSHnMmhbJPxuBiM9K4lo
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(i);
            }
        });
    }

    public void notifyLoading() {
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$VCtBH1cWjVWHPec6F4l2THgj4gY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.c();
            }
        });
    }

    public void notifyPause() {
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$RVkOeoZsNBCjwAhDUKK-6aSOU-M
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.d();
            }
        });
    }

    public void notifyPlayerError(final int i, final String str) {
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$PkVGuPHtSTRKm7k5a8wcRF0nrjw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(i, str);
            }
        });
    }

    public void notifyResume() {
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$cCC4Rwjf_pILfv28gt5jAQK0uEc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.e();
            }
        });
    }

    public void notifySeek(final long j, final long j2) {
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$2fsRKVC8HLnqhCPBQWslL2-xzt4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(j, j2);
            }
        });
    }

    public void notifyStallEnd(final boolean z) {
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$q4hTjqUUrZfqvBZLzFRn8p6uTfs
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(z);
            }
        });
    }

    public void notifyStallStart() {
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$AiSLf1oHKTuiyZ3dj0Ull-kRjmc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.f();
            }
        });
    }

    public void notifyVolumeChanged(final double d) {
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$ypQ5CCKzebUqySW2XIAP1pmaGhg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(d);
            }
        });
    }

    public void onDashEvent(final String str) {
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$Vmz33hbrsANumJWQYAdyUw9cfHk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(str);
            }
        });
    }

    public abstract void releaseSessionPlayerObjects();

    public void setCustomParameter(final String str, final String str2) {
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$krFSr0wOuKLC-AlmvWAZitNH26g
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(str, str2);
            }
        });
    }

    public void setPlayerErrorCode(final String str) {
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$pWMWsaC5NF7jZop6sH-pGA-BOHQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.b(str);
            }
        });
    }

    public void setStatusCode(final int i) {
        this.f205a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerAdapterHandler$Jf-iXFZ08mnRCYY8aYNGUL_Elds
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.b(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(long j, long j2) {
        ((JSFunction) this.mJSPlayerAdapter.getProperty("notifySeek").cast(JSFunction.class)).invoke(this.mJSPlayerAdapter, new JSValue[]{this.b.createJSNumber(j), this.b.createJSNumber(j2)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i) {
        JSValue property = this.mJSPlayerAdapter.getProperty(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE);
        if (property instanceof JSObject) {
            ((JSObject) ((JSObject) property.cast(JSObject.class)).getProperty("sessionReport").cast(JSObject.class)).setProperty("statusCode", this.b.createJSNumber(i));
        } else {
            LoggerManager.getInstance().printWarnLogs("BpkPlayerAdapterHandler", "Session is undefined, status code not set");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(boolean z) {
        ((JSFunction) this.mJSPlayerAdapter.getProperty("notifyStallEnd").cast(JSFunction.class)).invoke(this.mJSPlayerAdapter, new JSValue[]{this.b.createJSBoolean(z)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a() {
        ((JSFunction) this.mJSPlayerAdapter.getProperty("notifyClose").cast(JSFunction.class)).invoke(this.mJSPlayerAdapter, new JSValue[0]);
        QuickJSUtils.executePendingJobs(this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(AtomicBoolean atomicBoolean) {
        atomicBoolean.set(((JSBoolean) ((JSObject) ((JSObject) this.mJSPlayerAdapter.getProperty(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE).cast(JSObject.class)).getProperty("metricsManager").cast(JSObject.class)).getProperty("started").cast(JSBoolean.class)).getBoolean());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(double d) {
        ((JSFunction) this.mJSPlayerAdapter.getProperty("notifyVolumeChanged").cast(JSFunction.class)).invoke(this.mJSPlayerAdapter, new JSValue[]{this.b.createJSNumber(d)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, String str) {
        ((JSFunction) this.mJSPlayerAdapter.getProperty("notifyPlayerError").cast(JSFunction.class)).invoke(this.mJSPlayerAdapter, new JSValue[]{this.b.createJSNumber(i), this.b.createJSString(str)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str, String str2) {
        ((JSFunction) this.mJSPlayerAdapter.getProperty("setCustomParameter").cast(JSFunction.class)).invoke(this.mJSPlayerAdapter, new JSValue[]{this.b.createJSString(str), this.b.createJSString(str2)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(AtomicBoolean atomicBoolean) {
        atomicBoolean.set(((JSBoolean) ((JSObject) ((JSObject) this.mJSPlayerAdapter.getProperty(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE).cast(JSObject.class)).getProperty("metricsManager").cast(JSObject.class)).getProperty("buffering").cast(JSBoolean.class)).getBoolean());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(String str) {
        JSValue property = this.mJSPlayerAdapter.getProperty(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE);
        if (property instanceof JSObject) {
            ((JSObject) ((JSObject) property.cast(JSObject.class)).getProperty("sessionReport").cast(JSObject.class)).setProperty("playerErrorCode", this.b.createJSString(str));
        } else {
            LoggerManager.getInstance().printWarnLogs("BpkPlayerAdapterHandler", "Session is undefined, player error code not set");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(AtomicLong atomicLong) {
        atomicLong.set(((JSNumber) ((JSObject) this.mJSPlayerAdapter.getProperty(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE).cast(JSObject.class)).getProperty("deviceInitDate").cast(JSNumber.class)).getLong());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str) {
        JSValue property = ((JSObject) this.mJSPlayerAdapter.getProperty(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE).cast(JSObject.class)).getProperty("adSession");
        if (property instanceof JSUndefined) {
            return;
        }
        ((JSFunction) ((JSObject) property.cast(JSObject.class)).getProperty("onDashEvent").cast(JSFunction.class)).invoke(property, new JSValue[]{this.b.createJSString(str)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(AtomicBoolean atomicBoolean) {
        atomicBoolean.set(((JSBoolean) ((JSObject) ((JSObject) this.mJSPlayerAdapter.getProperty(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE).cast(JSObject.class)).getProperty("metricsManager").cast(JSObject.class)).getProperty("playing").cast(JSBoolean.class)).getBoolean());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(AtomicLong atomicLong) {
        atomicLong.set(((JSNumber) ((JSObject) this.mJSPlayerAdapter.getProperty(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE).cast(JSObject.class)).getProperty("manifestInitDate").cast(JSNumber.class)).getLong());
    }
}
