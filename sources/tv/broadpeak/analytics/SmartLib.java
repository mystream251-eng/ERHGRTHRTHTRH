package tv.broadpeak.analytics;

import tv.broadpeak.analytics.model.Metric;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class SmartLib extends tv.broadpeak.smartlib.SmartLib {
    public static SmartLib d;

    public static SmartLib getInstance() {
        if (d == null) {
            d = new SmartLib();
        }
        return d;
    }

    @Deprecated
    public void stopStreamingSession(Metric.BPStatusCode bPStatusCode) {
        super.stopStreamingSession(bPStatusCode.getValue());
    }
}
