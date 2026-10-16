package com.viaccessorca.analytics.dca;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class VoDcaEventHandlerProxy extends VoDcaEventHandler {
    private static final String TAG = "VO_DCA";

    @Override // com.viaccessorca.analytics.dca.VoDcaEventHandler
    public void stop() {
    }

    @Override // com.viaccessorca.analytics.dca.VoDcaEventHandler
    public long tick() {
        return 0L;
    }

    public VoDcaEventHandlerProxy(VoDcaDispatcher voDcaDispatcher, VoDcaConfiguration voDcaConfiguration) {
        super(voDcaDispatcher, voDcaConfiguration);
    }

    @Override // com.viaccessorca.analytics.dca.VoDcaEventHandler
    public void handleEvent(VoDcaEvent voDcaEvent) {
        if (!this.mConfiguration.eventFieldValidate(voDcaEvent)) {
            Log.e(TAG, "Event was not sent! Field validation failed");
            return;
        }
        if (!this.mConfiguration.mParsingSuccess) {
            Log.e(TAG, "Event was not sent! Error in given configuration");
            return;
        }
        String urlByEventType = this.mConfiguration.getUrlByEventType(voDcaEvent.getValue("eventType"));
        if (urlByEventType != null) {
            if (urlByEventType.toLowerCase().indexOf("null".toLowerCase()) == -1) {
                this.mDispatcher.sendEvent(voDcaEvent, urlByEventType);
                return;
            }
            Log.w(TAG, "URL not valid : " + urlByEventType + ", Event " + voDcaEvent.getValue("eventType") + " dropped.");
            return;
        }
        Log.w(TAG, "Event Type " + voDcaEvent.getValue("eventType") + " is not supported in the configuration. Event dropped.");
    }
}
