package io.sentry.android.core;

import androidx.lifecycle.ProcessLifecycleOwner;
import io.sentry.IHub;
import io.sentry.Integration;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.android.core.internal.util.MainThreadChecker;
import io.sentry.util.Objects;
import java.io.Closeable;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class AppLifecycleIntegration implements Integration, Closeable {
    private final IHandler handler;
    private SentryAndroidOptions options;
    LifecycleWatcher watcher;

    public AppLifecycleIntegration() {
        this(new MainLooperHandler());
    }

    AppLifecycleIntegration(IHandler iHandler) {
        this.handler = iHandler;
    }

    @Override // io.sentry.Integration
    public void register(final IHub iHub, SentryOptions sentryOptions) {
        Objects.requireNonNull(iHub, "Hub is required");
        SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) Objects.requireNonNull(sentryOptions instanceof SentryAndroidOptions ? (SentryAndroidOptions) sentryOptions : null, "SentryAndroidOptions is required");
        this.options = sentryAndroidOptions;
        sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "enableSessionTracking enabled: %s", Boolean.valueOf(this.options.isEnableAutoSessionTracking()));
        this.options.getLogger().log(SentryLevel.DEBUG, "enableAppLifecycleBreadcrumbs enabled: %s", Boolean.valueOf(this.options.isEnableAppLifecycleBreadcrumbs()));
        if (this.options.isEnableAutoSessionTracking() || this.options.isEnableAppLifecycleBreadcrumbs()) {
            try {
                Class.forName("androidx.lifecycle.DefaultLifecycleObserver");
                Class.forName("androidx.lifecycle.ProcessLifecycleOwner");
                if (MainThreadChecker.isMainThread()) {
                    lambda$register$0$AppLifecycleIntegration(iHub);
                } else {
                    this.handler.post(new Runnable() { // from class: io.sentry.android.core.-$$Lambda$AppLifecycleIntegration$q1qzdtzMh5E4OH5R-VItJ38UlZ8
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$register$0$AppLifecycleIntegration(iHub);
                        }
                    });
                }
            } catch (ClassNotFoundException e) {
                sentryOptions.getLogger().log(SentryLevel.INFO, "androidx.lifecycle is not available, AppLifecycleIntegration won't be installed", e);
            } catch (IllegalStateException e2) {
                sentryOptions.getLogger().log(SentryLevel.ERROR, "AppLifecycleIntegration could not be installed", e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: addObserver, reason: merged with bridge method [inline-methods] */
    public void lambda$register$0$AppLifecycleIntegration(IHub iHub) {
        if (this.options == null) {
            return;
        }
        this.watcher = new LifecycleWatcher(iHub, this.options.getSessionTrackingIntervalMillis(), this.options.isEnableAutoSessionTracking(), this.options.isEnableAppLifecycleBreadcrumbs());
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this.watcher);
        this.options.getLogger().log(SentryLevel.DEBUG, "AppLifecycleIntegration installed.", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: removeObserver, reason: merged with bridge method [inline-methods] */
    public void lambda$close$1$AppLifecycleIntegration() {
        ProcessLifecycleOwner.get().getLifecycle().removeObserver(this.watcher);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.watcher != null) {
            if (MainThreadChecker.isMainThread()) {
                lambda$close$1$AppLifecycleIntegration();
            } else {
                this.handler.post(new Runnable() { // from class: io.sentry.android.core.-$$Lambda$AppLifecycleIntegration$8Bm-91OsZn3s945u6qBp4djC2FE
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$close$1$AppLifecycleIntegration();
                    }
                });
            }
            this.watcher = null;
            SentryAndroidOptions sentryAndroidOptions = this.options;
            if (sentryAndroidOptions != null) {
                sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "AppLifecycleIntegration removed.", new Object[0]);
            }
        }
    }
}
