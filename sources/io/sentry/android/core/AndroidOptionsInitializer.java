package io.sentry.android.core;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import io.sentry.ILogger;
import io.sentry.SendCachedEnvelopeFireAndForgetIntegration;
import io.sentry.SendFireAndForgetEnvelopeSender;
import io.sentry.SendFireAndForgetOutboxSender;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.util.Objects;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes2.dex */
final class AndroidOptionsInitializer {
    private AndroidOptionsInitializer() {
    }

    static void init(SentryAndroidOptions sentryAndroidOptions, Context context) {
        Objects.requireNonNull(context, "The application context is required.");
        Objects.requireNonNull(sentryAndroidOptions, "The options object is required.");
        init(sentryAndroidOptions, context, new AndroidLogger());
    }

    static void init(SentryAndroidOptions sentryAndroidOptions, Context context, ILogger iLogger) {
        init(sentryAndroidOptions, context, iLogger, new BuildInfoProvider());
    }

    static void init(SentryAndroidOptions sentryAndroidOptions, Context context, ILogger iLogger, IBuildInfoProvider iBuildInfoProvider) {
        init(sentryAndroidOptions, context, iLogger, iBuildInfoProvider, new LoadClass());
    }

    static void init(SentryAndroidOptions sentryAndroidOptions, Context context, ILogger iLogger, IBuildInfoProvider iBuildInfoProvider, LoadClass loadClass) {
        Objects.requireNonNull(context, "The context is required.");
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        Objects.requireNonNull(sentryAndroidOptions, "The options object is required.");
        Objects.requireNonNull(iLogger, "The ILogger object is required.");
        sentryAndroidOptions.setLogger(iLogger);
        ManifestMetadataReader.applyMetadata(context, sentryAndroidOptions);
        initializeCacheDirs(context, sentryAndroidOptions);
        ActivityFramesTracker activityFramesTracker = new ActivityFramesTracker(loadClass);
        installDefaultIntegrations(context, sentryAndroidOptions, iBuildInfoProvider, loadClass, activityFramesTracker);
        readDefaultOptionValues(sentryAndroidOptions, context);
        sentryAndroidOptions.addEventProcessor(new DefaultAndroidEventProcessor(context, iLogger, iBuildInfoProvider));
        sentryAndroidOptions.addEventProcessor(new PerformanceAndroidEventProcessor(sentryAndroidOptions, activityFramesTracker));
        sentryAndroidOptions.setTransportGate(new AndroidTransportGate(context, sentryAndroidOptions.getLogger()));
    }

    private static void installDefaultIntegrations(Context context, final SentryOptions sentryOptions, IBuildInfoProvider iBuildInfoProvider, LoadClass loadClass, ActivityFramesTracker activityFramesTracker) {
        sentryOptions.addIntegration(new SendCachedEnvelopeFireAndForgetIntegration(new SendFireAndForgetEnvelopeSender(new SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForgetDirPath() { // from class: io.sentry.android.core.-$$Lambda$AndroidOptionsInitializer$kM-qsbd50H1L5pqZvtR3lA02sgk
            @Override // io.sentry.SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForgetDirPath
            public final String getDirPath() {
                return sentryOptions.getCacheDirPath();
            }
        })));
        sentryOptions.addIntegration(new NdkIntegration(loadNdkIfAvailable(sentryOptions, iBuildInfoProvider, loadClass)));
        sentryOptions.addIntegration(EnvelopeFileObserverIntegration.getOutboxFileObserver());
        sentryOptions.addIntegration(new SendCachedEnvelopeFireAndForgetIntegration(new SendFireAndForgetOutboxSender(new SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForgetDirPath() { // from class: io.sentry.android.core.-$$Lambda$AndroidOptionsInitializer$D4zvv_NA_KabqNWy8rp5CCc_RTM
            @Override // io.sentry.SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForgetDirPath
            public final String getDirPath() {
                return sentryOptions.getOutboxPath();
            }
        })));
        sentryOptions.addIntegration(new AnrIntegration(context));
        sentryOptions.addIntegration(new AppLifecycleIntegration());
        if (context instanceof Application) {
            Application application = (Application) context;
            sentryOptions.addIntegration(new ActivityLifecycleIntegration(application, iBuildInfoProvider, activityFramesTracker));
            sentryOptions.addIntegration(new UserInteractionIntegration(application, loadClass));
        } else {
            sentryOptions.getLogger().log(SentryLevel.WARNING, "ActivityLifecycle and UserInteraction Integrations need an Application class to be installed.", new Object[0]);
        }
        sentryOptions.addIntegration(new AppComponentsBreadcrumbsIntegration(context));
        sentryOptions.addIntegration(new SystemEventsBreadcrumbsIntegration(context));
        sentryOptions.addIntegration(new TempSensorBreadcrumbsIntegration(context));
        sentryOptions.addIntegration(new PhoneStateBreadcrumbsIntegration(context));
    }

    private static void readDefaultOptionValues(SentryAndroidOptions sentryAndroidOptions, Context context) {
        PackageInfo packageInfo = ContextUtils.getPackageInfo(context, sentryAndroidOptions.getLogger());
        if (packageInfo != null) {
            if (sentryAndroidOptions.getRelease() == null) {
                sentryAndroidOptions.setRelease(getSentryReleaseVersion(packageInfo, ContextUtils.getVersionCode(packageInfo)));
            }
            String str = packageInfo.packageName;
            if (str != null && !str.startsWith("android.")) {
                sentryAndroidOptions.addInAppInclude(str);
            }
        }
        if (sentryAndroidOptions.getDistinctId() == null) {
            try {
                sentryAndroidOptions.setDistinctId(Installation.id(context));
            } catch (RuntimeException e) {
                sentryAndroidOptions.getLogger().log(SentryLevel.ERROR, "Could not generate distinct Id.", e);
            }
        }
        if (sentryAndroidOptions.getProguardUuid() == null) {
            sentryAndroidOptions.setProguardUuid(getProguardUUID(context, sentryAndroidOptions.getLogger()));
        }
    }

    private static String getProguardUUID(Context context, ILogger iLogger) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(context.getAssets().open("sentry-debug-meta.properties"));
            try {
                Properties properties = new Properties();
                properties.load(bufferedInputStream);
                String property = properties.getProperty("io.sentry.ProguardUuids");
                iLogger.log(SentryLevel.DEBUG, "Proguard UUID found: %s", property);
                bufferedInputStream.close();
                return property;
            } catch (Throwable th) {
                try {
                    bufferedInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (FileNotFoundException unused) {
            iLogger.log(SentryLevel.INFO, "sentry-debug-meta.properties file was not found.", new Object[0]);
            return null;
        } catch (IOException e) {
            iLogger.log(SentryLevel.ERROR, "Error getting Proguard UUIDs.", e);
            return null;
        } catch (RuntimeException e2) {
            iLogger.log(SentryLevel.ERROR, "sentry-debug-meta.properties file is malformed.", e2);
            return null;
        }
    }

    private static String getSentryReleaseVersion(PackageInfo packageInfo, String str) {
        return packageInfo.packageName + "@" + packageInfo.versionName + Marker.ANY_NON_NULL_MARKER + str;
    }

    private static void initializeCacheDirs(Context context, SentryOptions sentryOptions) {
        sentryOptions.setCacheDirPath(new File(context.getCacheDir(), "sentry").getAbsolutePath());
    }

    private static boolean isNdkAvailable(IBuildInfoProvider iBuildInfoProvider) {
        return iBuildInfoProvider.getSdkInfoVersion() >= 16;
    }

    private static Class<?> loadNdkIfAvailable(SentryOptions sentryOptions, IBuildInfoProvider iBuildInfoProvider, LoadClass loadClass) {
        if (!isNdkAvailable(iBuildInfoProvider)) {
            return null;
        }
        try {
            return loadClass.loadClass(NdkIntegration.SENTRY_NDK_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            sentryOptions.getLogger().log(SentryLevel.ERROR, "Failed to load SentryNdk.", e);
            return null;
        } catch (UnsatisfiedLinkError e2) {
            sentryOptions.getLogger().log(SentryLevel.ERROR, "Failed to load (UnsatisfiedLinkError) SentryNdk.", e2);
            return null;
        } catch (Throwable th) {
            sentryOptions.getLogger().log(SentryLevel.ERROR, "Failed to initialize SentryNdk.", th);
            return null;
        }
    }
}
