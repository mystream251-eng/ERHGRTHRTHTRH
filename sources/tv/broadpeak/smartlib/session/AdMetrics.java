package tv.broadpeak.smartlib.session;

import com.hippo.quickjs.android.JSBoolean;
import com.hippo.quickjs.android.JSNumber;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSString;
import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public class AdMetrics implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f221a;
    public boolean b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public String i;
    public String j;
    public String k;

    public AdMetrics(JSObject jSObject) {
        try {
            this.f221a = ((JSBoolean) jSObject.getProperty("adSkippable").cast(JSBoolean.class)).getBoolean();
            this.b = ((JSBoolean) jSObject.getProperty("adSkipped").cast(JSBoolean.class)).getBoolean();
            this.c = ((JSNumber) jSObject.getProperty("adProgress").cast(JSNumber.class)).getInt();
            this.d = ((JSNumber) jSObject.getProperty("adDuration").cast(JSNumber.class)).getInt();
            this.e = ((JSNumber) jSObject.getProperty("stallsNumber").cast(JSNumber.class)).getInt();
            this.f = ((JSNumber) jSObject.getProperty("stallsDuration").cast(JSNumber.class)).getInt();
            this.g = ((JSNumber) jSObject.getProperty("layerSwitchesNumber").cast(JSNumber.class)).getInt();
            this.h = ((JSNumber) jSObject.getProperty("averageBitrate").cast(JSNumber.class)).getInt();
            this.i = ((JSString) jSObject.getProperty("sessionToken").cast(JSString.class)).getString();
            this.j = ((JSString) jSObject.getProperty("creativeId").cast(JSString.class)).getString();
            this.k = ((JSString) jSObject.getProperty("adId").cast(JSString.class)).getString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getAdDuration() {
        return this.d;
    }

    public String getAdId() {
        return this.k;
    }

    public int getAdProgress() {
        return this.c;
    }

    public int getAverageBitrate() {
        return this.h;
    }

    public String getCreativeId() {
        return this.j;
    }

    public int getLayerSwitchesNumber() {
        return this.g;
    }

    public String getSessionToken() {
        return this.i;
    }

    public int getStallsDuration() {
        return this.f;
    }

    public int getStallsNumber() {
        return this.e;
    }

    public boolean isAdSkippable() {
        return this.f221a;
    }

    public boolean isAdSkipped() {
        return this.b;
    }

    public String toString() {
        return "AdMetrics{mAdSkippable=" + this.f221a + ", mAdSkipped=" + this.b + ", mAdProgress=" + this.c + ", mAdDuration=" + this.d + ", mStallsNumber=" + this.e + ", mStallsDuration=" + this.f + ", mLayerSwitchesNumber=" + this.g + ", mAverageBitrate=" + this.h + ", mSessionToken='" + this.i + "', mCreativeId='" + this.j + "', mAdId='" + this.k + "'}";
    }
}
