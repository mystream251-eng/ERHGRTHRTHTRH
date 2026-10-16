package io.sentry;

import io.sentry.cache.EnvelopeCache;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public final class SentryCrashLastRunState {
    private static final SentryCrashLastRunState INSTANCE = new SentryCrashLastRunState();
    private Boolean crashedLastRun;
    private final Object crashedLastRunLock = new Object();
    private boolean readCrashedLastRun;

    private SentryCrashLastRunState() {
    }

    public static SentryCrashLastRunState getInstance() {
        return INSTANCE;
    }

    public Boolean isCrashedLastRun(String str, boolean z) {
        synchronized (this.crashedLastRunLock) {
            if (this.readCrashedLastRun) {
                return this.crashedLastRun;
            }
            if (str == null) {
                return null;
            }
            boolean z2 = true;
            this.readCrashedLastRun = true;
            File file = new File(str, EnvelopeCache.CRASH_MARKER_FILE);
            File file2 = new File(str, EnvelopeCache.NATIVE_CRASH_MARKER_FILE);
            try {
                try {
                    if (file.exists()) {
                        file.delete();
                    } else {
                        if (!file2.exists()) {
                            z2 = false;
                        } else if (z) {
                            file2.delete();
                        }
                        Boolean boolValueOf = Boolean.valueOf(z2);
                        this.crashedLastRun = boolValueOf;
                        return boolValueOf;
                    }
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
            }
            Boolean boolValueOf2 = Boolean.valueOf(z2);
            this.crashedLastRun = boolValueOf2;
            return boolValueOf2;
        }
    }

    public void setCrashedLastRun(boolean z) {
        synchronized (this.crashedLastRunLock) {
            if (!this.readCrashedLastRun) {
                this.crashedLastRun = Boolean.valueOf(z);
                this.readCrashedLastRun = true;
            }
        }
    }

    public void reset() {
        synchronized (this.crashedLastRunLock) {
            this.readCrashedLastRun = false;
            this.crashedLastRun = null;
        }
    }
}
