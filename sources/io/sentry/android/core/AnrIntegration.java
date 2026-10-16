package io.sentry.android.core;

import android.content.Context;
import io.sentry.IHub;
import io.sentry.ILogger;
import io.sentry.Integration;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.exception.ExceptionMechanismException;
import io.sentry.protocol.Mechanism;
import io.sentry.util.Objects;
import java.io.Closeable;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class AnrIntegration implements Integration, Closeable {
    private static ANRWatchDog anrWatchDog;
    private static final Object watchDogLock = new Object();
    private final Context context;
    private SentryOptions options;

    public AnrIntegration(Context context) {
        this.context = context;
    }

    @Override // io.sentry.Integration
    public final void register(IHub iHub, SentryOptions sentryOptions) {
        this.options = (SentryOptions) Objects.requireNonNull(sentryOptions, "SentryOptions is required");
        register(iHub, (SentryAndroidOptions) sentryOptions);
    }

    private void register(final IHub iHub, final SentryAndroidOptions sentryAndroidOptions) {
        sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "AnrIntegration enabled: %s", Boolean.valueOf(sentryAndroidOptions.isAnrEnabled()));
        if (sentryAndroidOptions.isAnrEnabled()) {
            synchronized (watchDogLock) {
                if (anrWatchDog == null) {
                    sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "ANR timeout in milliseconds: %d", Long.valueOf(sentryAndroidOptions.getAnrTimeoutIntervalMillis()));
                    ANRWatchDog aNRWatchDog = new ANRWatchDog(sentryAndroidOptions.getAnrTimeoutIntervalMillis(), sentryAndroidOptions.isAnrReportInDebug(), new ANRWatchDog.ANRListener() { // from class: io.sentry.android.core.-$$Lambda$AnrIntegration$hRvUVYRYnOZD36jN40KfQaw4dLY
                        @Override // io.sentry.android.core.ANRWatchDog.ANRListener
                        public final void onAppNotResponding(ApplicationNotResponding applicationNotResponding) {
                            this.f$0.lambda$register$0$AnrIntegration(iHub, sentryAndroidOptions, applicationNotResponding);
                        }
                    }, sentryAndroidOptions.getLogger(), this.context);
                    anrWatchDog = aNRWatchDog;
                    aNRWatchDog.start();
                    sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "AnrIntegration installed.", new Object[0]);
                }
            }
        }
    }

    public /* synthetic */ void lambda$register$0$AnrIntegration(IHub iHub, SentryAndroidOptions sentryAndroidOptions, ApplicationNotResponding applicationNotResponding) {
        reportANR(iHub, sentryAndroidOptions.getLogger(), applicationNotResponding);
    }

    void reportANR(IHub iHub, ILogger iLogger, ApplicationNotResponding applicationNotResponding) {
        iLogger.log(SentryLevel.INFO, "ANR triggered with message: %s", applicationNotResponding.getMessage());
        Mechanism mechanism = new Mechanism();
        mechanism.setType("ANR");
        iHub.captureException(new ExceptionMechanismException(mechanism, applicationNotResponding, applicationNotResponding.getThread(), true));
    }

    ANRWatchDog getANRWatchDog() {
        return anrWatchDog;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (watchDogLock) {
            if (anrWatchDog != null) {
                anrWatchDog.interrupt();
                anrWatchDog = null;
                if (this.options != null) {
                    this.options.getLogger().log(SentryLevel.DEBUG, "AnrIntegration removed.", new Object[0]);
                }
            }
        }
    }
}
