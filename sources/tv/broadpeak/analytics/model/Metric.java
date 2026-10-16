package tv.broadpeak.analytics.model;

import tv.broadpeak.smartlib.SmartLib;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class Metric {

    @Deprecated
    public enum BPStatusCode {
        BPSessionEndsNormally(200),
        BPFormatNotSupportedError(3001),
        BPDecodingError(3002),
        BPNetworkingError(SmartLib.BPNetworkingError),
        BPAccessRightError(3004),
        BPUnspecifiedError(3005);

        public int b;

        BPStatusCode(int i) {
            this.b = i;
        }

        public String getStringValue() {
            return "" + this.b;
        }

        public int getValue() {
            return this.b;
        }
    }
}
