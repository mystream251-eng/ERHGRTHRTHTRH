package io.sentry.android.core;

import io.sentry.SentryOptions;
import io.sentry.protocol.SdkVersion;

/* JADX INFO: loaded from: classes2.dex */
public final class SentryAndroidOptions extends SentryOptions {
    private boolean anrEnabled = true;
    private long anrTimeoutIntervalMillis = 5000;
    private boolean anrReportInDebug = false;
    private boolean enableActivityLifecycleBreadcrumbs = true;
    private boolean enableAppLifecycleBreadcrumbs = true;
    private boolean enableSystemEventBreadcrumbs = true;
    private boolean enableAppComponentBreadcrumbs = true;
    private boolean enableUserInteractionBreadcrumbs = true;
    private boolean enableAutoActivityLifecycleTracing = true;
    private boolean enableActivityLifecycleTracingAutoFinish = true;
    private IDebugImagesLoader debugImagesLoader = NoOpDebugImagesLoader.getInstance();

    public SentryAndroidOptions() {
        setSentryClientName("sentry.java.android/5.6.1");
        setSdkVersion(createSdkVersion());
        setAttachServerName(false);
    }

    private SdkVersion createSdkVersion() {
        SdkVersion sdkVersionUpdateSdkVersion = SdkVersion.updateSdkVersion(getSdkVersion(), BuildConfig.SENTRY_ANDROID_SDK_NAME, "5.6.1");
        sdkVersionUpdateSdkVersion.addPackage("maven:io.sentry:sentry-android-core", "5.6.1");
        return sdkVersionUpdateSdkVersion;
    }

    public boolean isAnrEnabled() {
        return this.anrEnabled;
    }

    public void setAnrEnabled(boolean z) {
        this.anrEnabled = z;
    }

    public long getAnrTimeoutIntervalMillis() {
        return this.anrTimeoutIntervalMillis;
    }

    public void setAnrTimeoutIntervalMillis(long j) {
        this.anrTimeoutIntervalMillis = j;
    }

    public boolean isAnrReportInDebug() {
        return this.anrReportInDebug;
    }

    public void setAnrReportInDebug(boolean z) {
        this.anrReportInDebug = z;
    }

    public boolean isEnableActivityLifecycleBreadcrumbs() {
        return this.enableActivityLifecycleBreadcrumbs;
    }

    public void setEnableActivityLifecycleBreadcrumbs(boolean z) {
        this.enableActivityLifecycleBreadcrumbs = z;
    }

    public boolean isEnableAppLifecycleBreadcrumbs() {
        return this.enableAppLifecycleBreadcrumbs;
    }

    public void setEnableAppLifecycleBreadcrumbs(boolean z) {
        this.enableAppLifecycleBreadcrumbs = z;
    }

    public boolean isEnableSystemEventBreadcrumbs() {
        return this.enableSystemEventBreadcrumbs;
    }

    public void setEnableSystemEventBreadcrumbs(boolean z) {
        this.enableSystemEventBreadcrumbs = z;
    }

    public boolean isEnableAppComponentBreadcrumbs() {
        return this.enableAppComponentBreadcrumbs;
    }

    public void setEnableAppComponentBreadcrumbs(boolean z) {
        this.enableAppComponentBreadcrumbs = z;
    }

    public boolean isEnableUserInteractionBreadcrumbs() {
        return this.enableUserInteractionBreadcrumbs;
    }

    public void setEnableUserInteractionBreadcrumbs(boolean z) {
        this.enableUserInteractionBreadcrumbs = z;
    }

    public void enableAllAutoBreadcrumbs(boolean z) {
        this.enableActivityLifecycleBreadcrumbs = z;
        this.enableAppComponentBreadcrumbs = z;
        this.enableSystemEventBreadcrumbs = z;
        this.enableAppLifecycleBreadcrumbs = z;
        this.enableUserInteractionBreadcrumbs = z;
    }

    public IDebugImagesLoader getDebugImagesLoader() {
        return this.debugImagesLoader;
    }

    public void setDebugImagesLoader(IDebugImagesLoader iDebugImagesLoader) {
        if (iDebugImagesLoader == null) {
            iDebugImagesLoader = NoOpDebugImagesLoader.getInstance();
        }
        this.debugImagesLoader = iDebugImagesLoader;
    }

    public boolean isEnableAutoActivityLifecycleTracing() {
        return this.enableAutoActivityLifecycleTracing;
    }

    public void setEnableAutoActivityLifecycleTracing(boolean z) {
        this.enableAutoActivityLifecycleTracing = z;
    }

    public boolean isEnableActivityLifecycleTracingAutoFinish() {
        return this.enableActivityLifecycleTracingAutoFinish;
    }

    public void setEnableActivityLifecycleTracingAutoFinish(boolean z) {
        this.enableActivityLifecycleTracingAutoFinish = z;
    }
}
