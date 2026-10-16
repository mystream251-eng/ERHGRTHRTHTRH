package tv.broadpeak.smartlib.ad.pal;

import tv.broadpeak.smartlib.engine.manager.LoggerManager;

/* JADX INFO: loaded from: classes3.dex */
public class AdPalSessionRequest {
    public static String TAG = "BpkAdPalSessionRequest";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f186a;
    public AdPalSession b;
    public final Object c;

    public AdPalSessionRequest() {
        Object obj = new Object();
        this.c = obj;
        synchronized (obj) {
            this.f186a = true;
        }
    }

    public AdPalSession getAdPalSession() {
        synchronized (this.c) {
            if (this.f186a) {
                try {
                    LoggerManager.getInstance().printDebugLogs(TAG, "Waiting for an ad PAL session...");
                    this.c.wait(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (this.f186a) {
                    LoggerManager.getInstance().printWarnLogs(TAG, "Ad PAL session request timed out. Please check your connectivity and that session.getURL(...) is not executed from the main thread.");
                }
            }
        }
        return this.b;
    }

    public void setAdPalSession(AdPalSession adPalSession) {
        this.b = adPalSession;
        synchronized (this.c) {
            this.f186a = false;
            this.c.notifyAll();
        }
    }
}
