package tv.broadpeak.smartlib.session.streaming;

import com.hippo.quickjs.android.JSFunction;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSValue;
import tv.broadpeak.motorjs.JSContext;
import tv.broadpeak.motorjs.QuickJSUtils;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.executor.CoreExecutor;

/* JADX INFO: loaded from: classes3.dex */
public class StreamingSessionOptions {
    public static final int CDN_EDGE_PARSER = 400;
    public static final int LEGACY_MULTICAST_ONLY = 1000;
    public static final int MULTICAST_ONLY = 0;
    public static final int PIP_SESSION = 2;
    public static final int REQUEST_BROADPEAK_CDN = 103;
    public static final int REQUEST_DIVERSITY = 101;
    public static final int REQUEST_DIVERSITY_DYNAMIC_FAILOVER = 105;
    public static final int REQUEST_LOCAL_NANO_CDN_IF_CONNECTED_TO_WIFI = 104;
    public static final int REQUEST_NANO_CDN = 102;
    public static final int REQUEST_REDIRECT_THIRD_PARTY_CDN = 100;
    public static final int SESSION_DISABLED = -1;
    public static final int SESSION_ENABLED = 1;
    public static final int SESSION_ENABLED_IF_BROADPEAK_DOMAIN = 0;
    public static final int SESSION_KEEPALIVE = 200;
    public static final int SESSION_REPORTING_MODE = 202;
    public static final int SESSION_REPORTING_MODE_DEFAULT = 0;
    public static final int SESSION_REPORTING_MODE_DISABLED = -1;
    public static final int SESSION_REPORTING_MODE_KEEPALIVE_TEARDOWN = 1;
    public static final int SESSION_REPORTING_MODE_METRICS_RECEIVER = 2;
    public static final int SESSION_TEARDOWN = 201;
    public static final int ULTRA_LOW_LATENCY_SUPPORT = 1;
    public static final int USERAGENT_AD_EVENT = 300;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CoreExecutor f225a = CoreEngine.getInstance().getCoreExecutor();
    public JSContext b = CoreEngine.getInstance().getJSContext();
    public JSObject c;

    public StreamingSessionOptions() {
        this.f225a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$StreamingSessionOptions$D7SaAWfz09snmF5XNIwOqGZwHBI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a() {
        this.c = QuickJSUtils.create(this.b, "smartlib.StreamingSessionOptions");
    }

    public static StreamingSessionOptions create() {
        return new StreamingSessionOptions();
    }

    public JSObject getJSObject() {
        return this.c;
    }

    public StreamingSessionOptions option(final int i, final boolean z) {
        this.f225a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$StreamingSessionOptions$sDF3xQVYPQWGFir88V7wWYAKe-M
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(i, z);
            }
        });
        return this;
    }

    public void set(int i, boolean z) {
        option(i, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, boolean z) {
        ((JSFunction) this.c.getProperty("option").cast(JSFunction.class)).invoke(this.c, new JSValue[]{this.b.createJSNumber(i), this.b.createJSBoolean(z)});
    }

    public StreamingSessionOptions option(final int i, final int i2) {
        this.f225a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$StreamingSessionOptions$exgjCKL0ZQ-gOT_yqbBcOYnzAP0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(i, i2);
            }
        });
        return this;
    }

    public void set(int i, int i2) {
        option(i, i2);
    }

    public StreamingSessionOptions option(final int i, final String str) {
        this.f225a.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$StreamingSessionOptions$uoQpTzvvcAz9yRleRr3UMY5fMlc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(i, str);
            }
        });
        return this;
    }

    public void set(int i, String str) {
        option(i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, int i2) {
        ((JSFunction) this.c.getProperty("option").cast(JSFunction.class)).invoke(this.c, new JSValue[]{this.b.createJSNumber(i), this.b.createJSNumber(i2)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, String str) {
        ((JSFunction) this.c.getProperty("option").cast(JSFunction.class)).invoke(this.c, new JSValue[]{this.b.createJSNumber(i), this.b.createJSString(str)});
    }
}
