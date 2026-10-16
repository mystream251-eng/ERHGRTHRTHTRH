package tv.broadpeak.smartlib.session;

import com.hippo.quickjs.android.JSArray;
import com.hippo.quickjs.android.JSBoolean;
import com.hippo.quickjs.android.JSFunction;
import com.hippo.quickjs.android.JSNumber;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSString;
import com.hippo.quickjs.android.JSUndefined;
import com.hippo.quickjs.android.JSValue;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class SessionReport implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f223a;
    public String b;
    public boolean c;
    public String d;
    public boolean e;
    public HashMap<String, Object> f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public int n;
    public int o;
    public int p;
    public int q;
    public Metrics r;
    public ArrayList<AdMetrics> s;
    public long t;
    public long u;
    public long v;
    public long w;
    public long x;

    public SessionReport(JSObject jSObject) {
        try {
            this.f223a = ((JSString) jSObject.getProperty("requestedURL").cast(JSString.class)).getString();
            this.b = ((JSString) jSObject.getProperty("redirectedURL").cast(JSString.class)).getString();
            this.c = ((JSBoolean) jSObject.getProperty("sessionRunning").cast(JSBoolean.class)).getBoolean();
            this.d = ((JSString) jSObject.getProperty("sessionId").cast(JSString.class)).getString();
            this.e = ((JSBoolean) jSObject.getProperty("teardownActivated").cast(JSBoolean.class)).getBoolean();
            this.f = new HashMap<>();
            JSValue property = jSObject.getProperty("smartLibParameters");
            if (!(property instanceof JSUndefined)) {
                JSObject jSObject2 = (JSObject) property.cast(JSObject.class);
                this.f.put("analyticsAddress", ((JSString) jSObject2.getProperty("analyticsAddress").cast(JSString.class)).getString());
                this.f.put("nanoCDNHost", ((JSString) jSObject2.getProperty("nanoCDNHost").cast(JSString.class)).getString());
                this.f.put("broadpeakDomainNames", ((JSString) jSObject2.getProperty("broadpeakDomainNames").cast(JSString.class)).getString());
                this.f.put("forceTeardown", Integer.valueOf(((JSNumber) jSObject2.getProperty("forceTeardown").cast(JSNumber.class)).getInt()));
                this.f.put("uuid", ((JSString) jSObject2.getProperty("uuid").cast(JSString.class)).getString());
                this.f.put("deviceType", ((JSString) jSObject2.getProperty("deviceType").cast(JSString.class)).getString());
            }
            this.g = ((JSString) jSObject.getProperty("playerName").cast(JSString.class)).getString();
            this.h = ((JSString) jSObject.getProperty("playerVersion").cast(JSString.class)).getString();
            this.i = ((JSString) jSObject.getProperty("osName").cast(JSString.class)).getString();
            this.j = ((JSString) jSObject.getProperty("osVersion").cast(JSString.class)).getString();
            this.k = ((JSString) jSObject.getProperty("deviceType").cast(JSString.class)).getString();
            this.l = ((JSString) jSObject.getProperty("smartLibVersion").cast(JSString.class)).getString();
            this.m = ((JSString) ((JSFunction) jSObject.getProperty("getNetworkTypeListStr").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]).cast(JSString.class)).getString();
            this.n = ((JSNumber) jSObject.getProperty("nanoCDNStatus").cast(JSNumber.class)).getInt();
            this.o = ((JSNumber) jSObject.getProperty("statusCode").cast(JSNumber.class)).getInt();
            this.p = ((JSNumber) jSObject.getProperty("cdnStatusCode").cast(JSNumber.class)).getInt();
            this.q = ((JSNumber) jSObject.getProperty("nanoCDNStatusCode").cast(JSNumber.class)).getInt();
            this.r = new Metrics(jSObject.getProperty("metrics"));
            this.s = new ArrayList<>();
            JSValue property2 = jSObject.getProperty("adMetrics");
            if (property2 instanceof JSArray) {
                JSArray jSArray = (JSArray) property2.cast(JSArray.class);
                for (int i = 0; i < jSArray.getLength(); i++) {
                    this.s.add(new AdMetrics((JSObject) jSArray.getProperty(i).cast(JSObject.class)));
                }
            }
            this.t = (long) ((JSNumber) jSObject.getProperty("endSessionDate").cast(JSNumber.class)).getDouble();
            this.u = (long) ((JSNumber) jSObject.getProperty("startSessionRequestDate").cast(JSNumber.class)).getDouble();
            this.w = (long) ((JSNumber) jSObject.getProperty("keepaliveRequestDate").cast(JSNumber.class)).getDouble();
            this.x = (long) ((JSNumber) jSObject.getProperty("teardownRequestDate").cast(JSNumber.class)).getDouble();
            this.v = (long) ((JSNumber) jSObject.getProperty("endSessionRequestDate").cast(JSNumber.class)).getDouble();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<AdMetrics> getAdMetrics() {
        return this.s;
    }

    public int getCDNStatusCode() {
        return this.p;
    }

    public String getDeviceType() {
        return this.k;
    }

    public String getDiversity() {
        return null;
    }

    public long getEndSessionDate() {
        return this.t;
    }

    public long getEndSessionRequestDate() {
        return this.v;
    }

    public long getKeepaliveRequestDate() {
        return this.w;
    }

    public Metrics getMetrics() {
        return this.r;
    }

    public int getNanoCDNStatus() {
        return this.n;
    }

    public int getNanoCDNStatusCode() {
        return this.q;
    }

    public String getOsName() {
        return this.i;
    }

    public String getOsVersion() {
        return this.j;
    }

    public String getPlayerName() {
        return this.g;
    }

    public String getPlayerVersion() {
        return this.h;
    }

    public String getRedirectedURL() {
        return this.b;
    }

    public String getRequestedURL() {
        return this.f223a;
    }

    public String getSessionId() {
        return this.d;
    }

    public HashMap<String, Object> getSmartLibParameters() {
        return this.f;
    }

    public String getSmartLibVersion() {
        return this.l;
    }

    public long getStartSessionRequestDate() {
        return this.u;
    }

    public int getStatusCode() {
        return this.o;
    }

    public long getTeardownRequestDate() {
        return this.x;
    }

    public String getTimeline() {
        return null;
    }

    public boolean isSessionRunning() {
        return this.c;
    }

    public boolean isTeardownActivated() {
        return this.e;
    }

    public String toString() {
        return "SessionReport {\nRequestedURL='" + this.f223a + "'\nRedirectedURL='" + this.b + "'\nSessionRunning=" + this.c + "\nSessionId='" + this.d + "'\nTeardownActivated=" + this.e + "\nSmartLibParameters=" + this.f + "\nPlayerName='" + this.g + "'\nPlayerVersion='" + this.h + "'\nOsName='" + this.i + "'\nOsVersion='" + this.j + "'\nDeviceType='" + this.k + "'\nSmartLibVersion='" + this.l + "'\nNanoCDNStatus=" + this.n + "\nStatusCode=" + this.o + "\nCDNStatusCode=" + this.p + "\nNanoCDNStatusCode=" + this.q + "\nMetrics=" + this.r + "\nAdMetrics=" + this.s + "\nTimeline='" + ((String) null) + "'\nDiversity='" + ((String) null) + "'\nStartSessionRequestDate=" + this.u + "\nEndSessionRequestDate=" + this.v + "\nKeepaliveRequestDate=" + this.w + "\nNetworkType=" + this.m + "\n}";
    }
}
