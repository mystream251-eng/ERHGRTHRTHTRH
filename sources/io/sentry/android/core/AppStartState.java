package io.sentry.android.core;

import android.os.SystemClock;
import java.util.Date;

/* JADX INFO: loaded from: classes2.dex */
public final class AppStartState {
    private static AppStartState instance = new AppStartState();
    private Long appStartEndMillis;
    private Long appStartMillis;
    private Date appStartTime;
    private Boolean coldStart = null;

    private AppStartState() {
    }

    public static AppStartState getInstance() {
        return instance;
    }

    void resetInstance() {
        instance = new AppStartState();
    }

    synchronized void setAppStartEnd() {
        setAppStartEnd(SystemClock.uptimeMillis());
    }

    void setAppStartEnd(long j) {
        this.appStartEndMillis = Long.valueOf(j);
    }

    public synchronized Long getAppStartInterval() {
        if (this.appStartMillis != null && this.appStartEndMillis != null && this.coldStart != null) {
            return Long.valueOf(this.appStartEndMillis.longValue() - this.appStartMillis.longValue());
        }
        return null;
    }

    public Boolean isColdStart() {
        return this.coldStart;
    }

    synchronized void setColdStart(boolean z) {
        if (this.coldStart != null) {
            return;
        }
        this.coldStart = Boolean.valueOf(z);
    }

    public Date getAppStartTime() {
        return this.appStartTime;
    }

    synchronized void setAppStartTime(long j, Date date) {
        if (this.appStartTime == null || this.appStartMillis == null) {
            this.appStartTime = date;
            this.appStartMillis = Long.valueOf(j);
        }
    }
}
