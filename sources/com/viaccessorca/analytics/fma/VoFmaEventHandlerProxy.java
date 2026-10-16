package com.viaccessorca.analytics.fma;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class VoFmaEventHandlerProxy extends VoFmaEventHandler {
    private static final String TAG = "VO_FMA";

    @Override // com.viaccessorca.analytics.fma.VoFmaEventHandler
    public void stop() {
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaEventHandler
    public long tick() {
        return 0L;
    }

    public VoFmaEventHandlerProxy(VoFmaDispatcher voFmaDispatcher, VoFmaConfiguration voFmaConfiguration) {
        super(voFmaDispatcher, voFmaConfiguration);
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaEventHandler
    public void handleEvent(VoFmaEvent voFmaEvent) {
        if (!this.mConfiguration.eventFieldValidate(voFmaEvent)) {
            Log.e(TAG, "Event was not sent! Field validation failed");
            return;
        }
        if (!this.mConfiguration.mParsingSuccess) {
            Log.e(TAG, "Event was not sent! Error in given configuration");
            return;
        }
        String urlByEventType = this.mConfiguration.getUrlByEventType(voFmaEvent.getValue("event_type"));
        if (urlByEventType != null) {
            if (urlByEventType.toLowerCase().indexOf("null".toLowerCase()) == -1) {
                this.mDispatcher.sendEvent(voFmaEvent, urlByEventType);
                return;
            }
            Log.w(TAG, "URL not valid : " + urlByEventType + ", Event " + voFmaEvent.getValue("event_type") + " dropped.");
            return;
        }
        Log.w(TAG, "Event Type " + voFmaEvent.getValue("event_type") + " is not supported in the configuration. Event dropped.");
    }
}
