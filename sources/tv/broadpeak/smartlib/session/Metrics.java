package tv.broadpeak.smartlib.session;

import com.hippo.quickjs.android.JSNumber;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSString;
import com.hippo.quickjs.android.JSUndefined;
import com.hippo.quickjs.android.JSValue;
import java.io.Serializable;
import java.util.HashMap;
import tv.broadpeak.motorjs.QuickJSUtils;
import tv.broadpeak.smartlib.engine.CoreEngine;

/* JADX INFO: loaded from: classes3.dex */
public class Metrics implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f222a;
    public int b;
    public int c;
    public String d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public HashMap<Integer, Integer> r;
    public int s;

    public Metrics(JSValue jSValue) {
        if (jSValue instanceof JSUndefined) {
            return;
        }
        JSObject jSObject = (JSObject) jSValue.cast(JSObject.class);
        try {
            this.f222a = ((JSNumber) jSObject.getProperty("redirectionTime").cast(JSNumber.class)).getInt();
            this.b = ((JSNumber) jSObject.getProperty("startupTime").cast(JSNumber.class)).getInt();
            this.c = ((JSNumber) jSObject.getProperty("completion").cast(JSNumber.class)).getInt();
            this.d = ((JSString) jSObject.getProperty("playbackType").cast(JSString.class)).getString();
            this.e = ((JSNumber) jSObject.getProperty("playbackDuration").cast(JSNumber.class)).getInt();
            this.f = ((JSNumber) jSObject.getProperty("sessionDuration").cast(JSNumber.class)).getInt();
            this.g = ((JSNumber) jSObject.getProperty("contentDuration").cast(JSNumber.class)).getInt();
            this.h = ((JSNumber) jSObject.getProperty("stallsNumber").cast(JSNumber.class)).getInt();
            this.i = ((JSNumber) jSObject.getProperty("maxStallDuration").cast(JSNumber.class)).getInt();
            this.j = ((JSNumber) jSObject.getProperty("totalStallsDuration").cast(JSNumber.class)).getInt();
            this.k = ((JSNumber) jSObject.getProperty("rebufferingsNumber").cast(JSNumber.class)).getInt();
            this.l = ((JSNumber) jSObject.getProperty("maxRebufferingDuration").cast(JSNumber.class)).getInt();
            this.m = ((JSNumber) jSObject.getProperty("totalRebufferingDuration").cast(JSNumber.class)).getInt();
            this.n = ((JSNumber) jSObject.getProperty("minBitrate").cast(JSNumber.class)).getInt();
            this.o = ((JSNumber) jSObject.getProperty("maxBitrate").cast(JSNumber.class)).getInt();
            this.p = ((JSNumber) jSObject.getProperty("averageBitrate").cast(JSNumber.class)).getInt();
            this.q = ((JSNumber) jSObject.getProperty("layerSwitchesNumber").cast(JSNumber.class)).getInt();
            this.r = new HashMap<>();
            JSObject jSObject2 = (JSObject) jSObject.getProperty("timeSpentPerLayer").cast(JSObject.class);
            for (String str : QuickJSUtils.toMap(CoreEngine.getInstance().getJSContext(), jSObject2).keySet()) {
                try {
                    this.r.put(Integer.valueOf(Integer.parseInt(str)), Integer.valueOf(((JSNumber) jSObject2.getProperty(str).cast(JSNumber.class)).getInt()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            this.s = ((JSNumber) jSObject.getProperty("preStartupTime").cast(JSNumber.class)).getInt();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public int getAverageBitrate() {
        return this.p;
    }

    public int getCompletion() {
        return this.c;
    }

    public int getContentDuration() {
        return this.g;
    }

    public int getLayerSwitchesNumber() {
        return this.q;
    }

    public int getMaxBitrate() {
        return this.o;
    }

    public int getMaxRebufferingDuration() {
        return this.l;
    }

    public int getMaxStallDuration() {
        return this.i;
    }

    public int getMinBitrate() {
        return this.n;
    }

    public int getPlaybackDuration() {
        return this.e;
    }

    public String getPlaybackType() {
        return this.d;
    }

    public int getPreStartupTime() {
        return this.s;
    }

    public int getRebufferingsNumber() {
        return this.k;
    }

    public int getRedirectionTime() {
        return this.f222a;
    }

    public int getSessionDuration() {
        return this.f;
    }

    public int getStallsNumber() {
        return this.h;
    }

    public int getStartupTime() {
        return this.b;
    }

    public HashMap<Integer, Integer> getTimeSpentPerLayer() {
        return this.r;
    }

    public int getTotalRebufferingDuration() {
        return this.m;
    }

    public int getTotalStallsDuration() {
        return this.j;
    }

    public String toString() {
        return "Metrics {\n  RedirectionTime=" + this.f222a + "\n  StartupTime=" + this.b + "\n  Completion=" + this.c + "\n  PlaybackType='" + this.d + "'\n  PlaybackDuration=" + this.e + "\n  SessionDuration=" + this.f + "\n  ContentDuration=" + this.g + "\n  StallsNumber=" + this.h + "\n  MaxStallDuration=" + this.i + "\n  TotalStallsDuration=" + this.j + "\n  RebufferingsNumber=" + this.k + "\n  MaxRebufferingDuration=" + this.l + "\n  TotalRebufferingDuration=" + this.m + "\n  MinBitrate=" + this.n + "\n  MaxBitrate=" + this.o + "\n  AverageBitrate=" + this.p + "\n  LayerSwitchesNumber=" + this.q + "\n  TimeSpentPerLayer=" + this.r + "\n  PreStartupTime=" + this.s + "\n}";
    }
}
